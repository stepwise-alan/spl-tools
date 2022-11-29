import de.fosd.typechef.ErrorXML
import de.fosd.typechef.Frontend.{lex, prepareAST}
import de.fosd.typechef.conditional.Opt
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureModel}
import de.fosd.typechef.options.{FrontendOptions, FrontendOptionsWithConfigFiles, Options}
import de.fosd.typechef.parser.c.PrettyPrinter.{layout, prettyPrint}
import de.fosd.typechef.parser.c.{AST, AsmExpr, AtomicNamedDeclarator, CParser, Declaration, Declarator, EmptyExternalDef, EnumSpecifier, ExternalDef, FunctionDef, InitDeclarator, NestedNamedDeclarator, ParserMain, Pragma, StructOrUnionSpecifier, TranslationUnit, TypedefSpecifier, TypelessDeclaration}
import de.fosd.typechef.featureexpr.FeatureExprFactory

import java.io.{BufferedWriter, File, FileWriter}
import java.lang.reflect.Field
import scala.annotation.tailrec


object Util {
  private val maxOptionIdField = {
    val field = classOf[Options].getDeclaredField("maxOptionId")
    field.setAccessible(true)
    field
  }

  private val maxOptionId = {
    new FrontendOptionsWithConfigFiles()
    maxOptionIdField.get(null)
  }

  def resetMaxOptionId(): Unit = maxOptionIdField.set(null, maxOptionId)

  def parse(args: Array[String],
            parserResults: Boolean = false,
            parserStatistics: Boolean = false,
            isPrintingLexingSuccess: Boolean = false
           ): (TranslationUnit, FeatureModel, Set[String]) = {
    val opt = new FrontendOptionsWithConfigFiles {
      override def isPrintLexingSuccess: Boolean = isPrintingLexingSuccess
    }
    resetMaxOptionId()
    opt.parseOptions(args)
    opt.parserResults = parserResults
    opt.parserStatistics = parserStatistics

    val errorXML = new ErrorXML(opt.getErrorXMLFile)
    opt.setRenderParserError(errorXML.renderParserError)

    val smallFM = opt.getSmallFeatureModel.and(opt.getLocalFeatureModel).and(opt.getFilePresenceCondition)
    opt.setSmallFeatureModel(smallFM)

    val fullFM = opt.getFullFeatureModel.and(opt.getLocalFeatureModel).and(opt.getFilePresenceCondition)
    opt.setFullFeatureModel(fullFM)

    val in = lex(opt)
    val parserMain = new ParserMain(new CParser(smallFM))
    (prepareAST(parserMain.parserMain(in, opt, fullFM)), fullFM, parserMain.getDistinctFeatures(in.tokens))
  }

  def print(ast: AST): Unit =
    println(writeToString(ast))
  
  def writeToString(ast: AST): String =
    layout(prettyPrint(ast))

  def writeToFile(ast: AST, filePath: String): Unit = {
    val bw = new BufferedWriter(new FileWriter(new File(filePath)))
    bw.write(writeToString(ast))
    bw.close()
  }

  @tailrec
  def getAtomicNamedDeclarator(declarator: Declarator): AtomicNamedDeclarator = declarator match {
    case declarator: AtomicNamedDeclarator => declarator
    case declarator: NestedNamedDeclarator => getAtomicNamedDeclarator(declarator.nestedDecl)
  }

  def isFunctionDeclaration(initDeclarator: InitDeclarator): Boolean =
    getAtomicNamedDeclarator(initDeclarator.declarator).extensions.nonEmpty

  def isTypeDef(declaration: Declaration): Boolean =
    declaration.declSpecs.exists(_.entry.isInstanceOf[TypedefSpecifier])
    
  def isConcreteStruct(structOrUnionSpecifier: StructOrUnionSpecifier): Boolean =
    structOrUnionSpecifier.enumerators.isDefined
    
  def isConcreteEnum(enumSpecifier: EnumSpecifier): Boolean =
    enumSpecifier.enumerators.isDefined

  def optTrue[T](t: T): Opt[T] = {
    Opt(FeatureExprFactory.True, t)
  }
}
