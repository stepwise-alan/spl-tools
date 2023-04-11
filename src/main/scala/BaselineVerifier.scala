import de.fosd.typechef.conditional.Opt
import de.fosd.typechef.featureexpr.FeatureExprFactory
import de.fosd.typechef.parser.c.*
import org.bytedeco.javacpp.BytePointer
import org.bytedeco.javacpp.LLVM.*

import java.io.{BufferedWriter, File, FileWriter}
import scala.collection.mutable
import scala.sys.process.{Process, ProcessLogger}

class BaselineVerifier(seaPath: String, z3Path: String) {
  val ndPrefix = "_nd_"

  def getNdFunctionName(variableName: String): String = {
    ndPrefix + variableName
  }

  def createNAryExpr(exprs: List[Expr], op: String): Expr = {
    if (exprs.isEmpty)
      if (op == "&&") Constant("1") else Constant("0")
    else if (exprs.size == 1)
      if (op == "!") UnaryExpr(op, exprs.head) else exprs.head
    else
      NAryExpr(exprs.head, exprs.tail.map(e => Util.optTrue(NArySubExpr(op, e))))
  }

  def ndCall(variableName: String): PostfixExpr = {
    PostfixExpr(Id(getNdFunctionName(variableName)), FunctionCall(ExprList(List())))
  }

  def ndDecl(variableName: String,
             pointersAndSpecifiers: (List[Opt[Pointer]], List[Opt[Specifier]])): Opt[Declaration] = {
    Util.optTrue(Declaration(
      Util.optTrue(ExternSpecifier()) +: pointersAndSpecifiers._2,
      List(Util.optTrue(InitDeclaratorI(
        AtomicNamedDeclarator(
          pointersAndSpecifiers._1,
          Id(getNdFunctionName(variableName)),
          List(Util.optTrue(DeclParameterDeclList(List(
            Util.optTrue(PlainParameterDeclaration(List(Util.optTrue(VoidSpecifier())), List()))
          ))))
        ),
        List(),
        None
      )))
    ))
  }

  def getLLMapping(llFilepath: String, isVariable: String => Boolean): Map[String, String] = {
    val source = io.Source.fromFile(llFilepath)
    val regex = "^\\s*(%\\w+)\\s*=\\s*(?:tail\\s+)?call\\s+(?:\\w+\\s+)+@_nd_(\\w+)\\(\\)(?:\\s+#7)+\\s*$".r
    val map = source.getLines().foldLeft(Map[String, String]())((map, line) =>
      regex.findFirstMatchIn(line) match {
        case Some(regex(llVariableName, variableName)) if isVariable(variableName) =>
          map + (llVariableName -> variableName)
        case Some(_) => assert(false)
        case None => map
      }
    )
    source.close()
    map
  }

  def sExprToCExpr(sExpr: SExpr, llVariableNames: List[String], llMapping: Map[String, String],
                   isFeature: String => Boolean): Either[Expr, Expr] = {
    val regex = "p_init[^_]*_(\\d+)_n".r
    sExpr match {
      case SNumber(value) => Left(Constant(value))
      case SSymbol(value) => regex.findFirstMatchIn(value) match
        case Some(regex(idxString)) =>
          val variableName = llMapping(llVariableNames(idxString.toInt))
          if (isFeature(variableName)) Left(Id(variableName)) else Right(Id(variableName))
        case Some(_) => assert(false)
        case None => value match
          case "#t" => Left(Constant("1"))
          case "#f" => Left(Constant("0"))
          case _ => assert(false)
      case SString(value) => Left(StringLit(List(Util.optTrue(value))))
      case SList(SSymbol(sExprOp) :: sExprs) =>
        val op = Map("and" -> "&&", "or" -> "||", "=" -> "==", "not" -> "!", "div" -> "/", "mod" -> "%").getOrElse(sExprOp, sExprOp)
        val (exprs, allLeft) = sExprs.map(sExprToCExpr(_, llVariableNames, llMapping, isFeature))
          .foldLeft((List[Expr](), true))((acc, e) => e match
            case Left(value) => (acc._1 :+ value, acc._2)
            case Right(value) => (acc._1 :+ value, false))
        if (allLeft) Left(createNAryExpr(exprs, op)) else Right(createNAryExpr(exprs, op))
      case SList(List(sExpr)) => sExprToCExpr(sExpr, llVariableNames, llMapping, isFeature)
      case SList(_) => assert(false)
    }
  }

  def split(sExpr: SExpr, llVariableNames: List[String], llMapping: Map[String, String],
            isFeature: String => Boolean): (Expr, Expr) = {
    sExpr match {
      case SList(SSymbol("and") :: sExprs) =>
        val (left, right) = sExprs.map(sExprToCExpr(_, llVariableNames, llMapping, isFeature)).partitionMap(identity)
        (createNAryExpr(left, "&&"), createNAryExpr(right, "&&"))
      case SSymbol("true") => (Constant("1"), Constant("1"))
      case _ => assert(false)
    }
  }

  def modifySmtFile(smtFilepath: String, newSmtFilepath: String, llMapping: Map[String, String]): List[String] = {
    val source = io.Source.fromFile(smtFilepath)
    val lines = source.getLines().toList
    source.close()

    val regex = "^\\s*\\(declare-var\\s+main@(%\\w+)_0\\s+(\\w+)\\s*\\)\\s*$".r
    val llVariableNames = mutable.ListBuffer[String]()
    val smtVariableNameAndTypeNamePairs: List[(String, String)] = lines.flatMap(
      regex.findFirstMatchIn(_) match {
        case Some(regex(llVariableName, smtTypeName)) if llMapping.contains(llVariableName) =>
          llVariableNames += llVariableName
          Some((s"main@${llVariableName}_0", smtTypeName))
        case _ => None
      }
    )

    val (declareLines, ruleLines) = lines.span(_ != "(rule (verifier.error false false false))")
    assert(declareLines.nonEmpty && ruleLines.nonEmpty)

    val bw = new BufferedWriter(new FileWriter(new File(newSmtFilepath)))
    declareLines.foreach(line => {
      bw.write(line)
      bw.newLine()
    })
    val pInitCall = "(p_init " + smtVariableNameAndTypeNamePairs.map(_._1).mkString(" ") + ")"
    val targetCall = "(target " + smtVariableNameAndTypeNamePairs.map(_._1).mkString(" ") + ")"
    bw.write("(declare-rel p_init ("
      + smtVariableNameAndTypeNamePairs.map(_._2).mkString(" ")
      + "))")
    bw.newLine()
    bw.write("(declare-rel target ("
      + smtVariableNameAndTypeNamePairs.map(_._2).mkString(" ")
      + "))")
    bw.newLine()
    bw.write("(rule (forall ("
      + smtVariableNameAndTypeNamePairs.map("(" + _ + " " + _ + ")").mkString(" ")
      + ") " + targetCall + "))")
    bw.newLine()
    bw.write("(rule (forall ("
      + smtVariableNameAndTypeNamePairs.map("(" + _ + " " + _ + ")").mkString(" ")
      + ") (=> " + targetCall + " " + pInitCall + ")))")
    bw.newLine()
    ruleLines.foldLeft(false)((found, line) => {
      if (!found && line.trim == "true") {
        bw.write(line.takeWhile(_.isSpaceChar))
        bw.write(pInitCall)
        bw.newLine()
        true
      } else {
        bw.write(line)
        bw.newLine()
        found
      }
    })
    bw.close()

    llVariableNames.toList
  }

  def getGeneralizedCounterExample(isVariable: String => Boolean,
                                    externalDefs: List[Opt[ExternalDef]],
                                    declarationStatements: List[Opt[DeclarationStatement]],
                                    otherStatements: List[Opt[Statement]],
                                    features: Set[String],
                                    numberOfIterations: Int): Option[(String, String)] = {

    val cFilepath = s"$numberOfIterations.c"
    val smtFilepath = s"$numberOfIterations.a.smt2"
    val newSmtFilepath = s"$numberOfIterations.b.smt2"
    val llFilepath = s"$numberOfIterations.ll"
    val traceFilepath = s"$numberOfIterations.z3.txt"

    val bw = new BufferedWriter(new FileWriter(new File(cFilepath)))
    bw.write("#include \"seahorn/seahorn.h\"\n")
    bw.write("#define assert sassert\n")
    bw.write(Util.writeToString(TranslationUnit(
      externalDefs :+ Util.optTrue(FunctionDef(
        List(Util.optTrue(IntSpecifier())),
        AtomicNamedDeclarator(
          List(),
          Id("main"),
          List(Util.optTrue(DeclParameterDeclList(List(
            Util.optTrue(PlainParameterDeclaration(List(Util.optTrue(VoidSpecifier())), List()))
          ))))
        ),
        List(),
        CompoundStatement(declarationStatements ++ otherStatements)
      ))
    )))
    bw.close()

    val seaOutput = Process(s"$seaPath -m64 smt --solve -O0 $cFilepath -o $smtFilepath --oll=$llFilepath").!!(ProcessLogger(_ => ()))
    val result = seaOutput.linesIterator.toList.last
    if (result == "unsat") {
      None
    } else if (result == "sat") {
      val llMapping = getLLMapping(llFilepath, isVariable)
      val llVariableNames = modifySmtFile(smtFilepath, newSmtFilepath, llMapping)

      val command = s"$z3Path proof=true fp.engine=spacer fp.spacer.order_children=2 " +
        s"fp.xform.subsumption_checker=false fp.xform.inline_eager=false " +
        s"fp.xform.inline_linear=false " +
        s"fp.spacer.trace_file=$traceFilepath -v:2 $newSmtFilepath"
      val z3Output = Process(command).!!(ProcessLogger(_ => ()))
      assert(z3Output.linesIterator.next().trim == "sat")

      val traceSource = io.Source.fromFile(traceFilepath)
      traceSource.getLines().find(_.trim.startsWith("** expand-pob: p_init"))
      val sExprLines = traceSource.getLines().takeWhile(_.trim.nonEmpty).toList
      smtlib.parser.Parser(smtlib.lexer.Lexer(java.io.StringReader(sExprLines.mkString(" "))))
      val sExpr = SExprParser(sExprLines.mkString("\n")).get
      val (left, right) = split(sExpr, llVariableNames, llMapping, features.contains)
      traceSource.close()
      Some((Util.writeToString(left), Util.writeToString(right)))
    } else {
      print(seaOutput)
      assert(false)
    }
  }

  def apply(translationUnit: TranslationUnit, features: Set[String], numberOfIterations: Int): Option[(String, String)] = {
    val variableNameToSpecifiers = mutable.Map[String, (List[Opt[Pointer]], List[Opt[Specifier]])]()

    val (mainFunctionDefs, externalDefs) = translationUnit.defs
      .partitionMap[Opt[FunctionDef], Opt[ExternalDef]](_.entry match {
        case FunctionDef(specifiers, declarator, oldStyleParameters, stmt) =>
          val functionDef = Util.optTrue(FunctionDef(
            specifiers.filterNot(_.entry.isInstanceOf[StaticSpecifier]),
            declarator, oldStyleParameters, stmt
          ))
          if declarator.getName == "main" then Left(functionDef) else Right(functionDef)
        case e => Right(Util.optTrue(e))
      })

    assert(mainFunctionDefs.size == 1)
    val (declarationStatements, otherStatements) = mainFunctionDefs.head.entry.stmt.innerStatements
      .partitionMap[Opt[DeclarationStatement], Opt[Statement]](_.entry match {
        case DeclarationStatement(Declaration(declSpecs, List(Opt(_, InitDeclaratorI(declarator, attributes, None)))))
          if !declSpecs.exists(_.entry.isInstanceOf[TypedefSpecifier]) =>
          variableNameToSpecifiers += declarator.getName -> (declarator.pointers, declSpecs)
          Left(Util.optTrue(DeclarationStatement(Declaration(
            declSpecs,
            List(Util.optTrue(InitDeclaratorI(
              declarator,
              attributes,
              Some(Initializer(None, ndCall(declarator.getName)))
            )))
          ))))
        case s => Right(Util.optTrue(s))
      })
    getGeneralizedCounterExample(variableNameToSpecifiers.contains,
      externalDefs ++ variableNameToSpecifiers.map(ndDecl),
      declarationStatements, otherStatements, features, numberOfIterations)
  }
}

