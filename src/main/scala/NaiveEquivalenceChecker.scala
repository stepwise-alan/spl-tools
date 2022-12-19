import de.fosd.typechef.conditional.*
import de.fosd.typechef.crewrite.ProductDerivation
import de.fosd.typechef.featureexpr.FeatureExprFactory
import de.fosd.typechef.parser.c.*
import de.fosd.typechef.parser.c.PrettyPrinter.{layout, prettyPrint}

import java.io.{BufferedWriter, File, FileWriter}
import scala.util.Using

object NaiveEquivalenceChecker {
  val frontendArgs: Array[String] = (
    "-c windows.properties -x CONFIG_ --include busybox/config.h -I busybox-1.18.5/include " +
      "--featureModelFExpr busybox/featureModel --writePI --recordTiming --parserstatistics --lexdebug " +
      "--dumpcfg -t --interface --debugInterface --errorXML -U HAVE_LIBDMALLOC -DCONFIG_FIND -U " +
      "CONFIG_FEATURE_WGET_LONG_OPTIONS -U ENABLE_NC_110_COMPAT -U CONFIG_EXTRA_COMPAT -D_GNU_SOURCE").split(' ')

  def renameDeclarator(declarator: Declarator, getName: String => String
                      ): Declarator = declarator match {
    case AtomicNamedDeclarator(pointers, id, extensions) =>
      AtomicNamedDeclarator(pointers, Id(getName(id.name)), extensions)
    case NestedNamedDeclarator(pointers, nestedDecl, extensions, attr) =>
      NestedNamedDeclarator(pointers, renameDeclarator(nestedDecl, getName), extensions, attr)
  }

  def rename(functionDef: FunctionDef, getName: String => String): FunctionDef = functionDef match {
    case FunctionDef(specifiers, declarator, oldStyleParameters, stmt) =>
      FunctionDef(specifiers, renameDeclarator(declarator, getName), oldStyleParameters, stmt)
  }

  def merge(oldTranslationUnit: TranslationUnit, newTranslationUnit: TranslationUnit,
            clientName: String, libraryName: String): TranslationUnit = {
    var args: List[List[Opt[Specifier]]] = Nil
    TranslationUnit(
      oldTranslationUnit.defs.map(p => Opt(p.condition, p.entry match {
        case functionDef: FunctionDef => rename(functionDef, _ + "_old")
        case _ => p.entry
      })) ++ newTranslationUnit.defs.collect(p => p.entry match {
        case functionDef: FunctionDef if functionDef.getName == libraryName =>
          functionDef.declarator.extensions.foreach(f => f.entry match {
            case extension: DeclaratorAbstrExtension => extension match {
              case DeclParameterDeclList(parameterDecls) => parameterDecls.foreach(_.entry match {
                case PlainParameterDeclaration(specifiers, attr) => args :+= specifiers
                case ParameterDeclarationD(specifiers, decl, attr) => args :+= specifiers
                case ParameterDeclarationAD(specifiers, decl, attr) => args :+= specifiers
                case VarArgs() => ???
              })
              case DeclArrayAccess(expr) => ???
            }
            case DeclIdentifierList(idList) => ???
          })
          Opt(p.condition, rename(functionDef, _ + "_new"))
      }) ++ args.zipWithIndex.map((f, i) =>
        Opt(FeatureExprFactory.True, Declaration(
          Opt(FeatureExprFactory.True, ExternSpecifier()) +: f,
          List(Opt(FeatureExprFactory.True, InitDeclaratorI(
            AtomicNamedDeclarator(
              List(),
              Id(s"nd_$i"),
              List(Opt(FeatureExprFactory.True, DeclParameterDeclList(List(Opt(FeatureExprFactory.True,
                PlainParameterDeclaration(List(Opt(FeatureExprFactory.True, VoidSpecifier())), List())
              )))))
            ),
            List(),
            None
          )))
        ))) :+ Opt(FeatureExprFactory.True, FunctionDef(
        List(Opt(FeatureExprFactory.True, IntSpecifier())),
        AtomicNamedDeclarator(List(), Id("main"), List()),
        List(),
        CompoundStatement(
          args.zipWithIndex.map((f, i) => Opt(FeatureExprFactory.True, DeclarationStatement(Declaration(
            f,
            List(Opt(FeatureExprFactory.True, InitDeclaratorI(
              AtomicNamedDeclarator(
                List(),
                Id(s"x_$i"),
                List(),
              ),
              List(),
              Some(Initializer(None, PostfixExpr(Id(s"nd_$i"), FunctionCall(ExprList(List())))))
            )))
          )))) :+
            Opt(FeatureExprFactory.True, ExprStatement(PostfixExpr(
              Id("sassert"),
              FunctionCall(ExprList(List(Opt(FeatureExprFactory.True, NAryExpr(
                PostfixExpr(Id(libraryName + "_old"), FunctionCall(
                  ExprList(args.zipWithIndex.map((f, i) => Opt(FeatureExprFactory.True, Id(s"x_i"))))
                )),
                List(Opt(FeatureExprFactory.True, NArySubExpr(
                  "==",
                  PostfixExpr(Id(libraryName + "_new"), FunctionCall(
                    ExprList(args.zipWithIndex.map((f, i) => Opt(FeatureExprFactory.True, Id(s"x_i"))))
                  )),
                )))
              )))))
            )))
        )
      ))
    )
  }

  def run(oldFilepath: String, newFilepath: String,
          clientName: String, libraryName: String): Unit = {
    val (oldTranslationUnit, oldFeatureModel, oldFeatures) = Util.parse(frontendArgs :+ oldFilepath)
    val (newTranslationUnit, newFeatureModel, newFeatures) = Util.parse(frontendArgs :+ newFilepath)
    val mergedTranslationUnit = merge(oldTranslationUnit, newTranslationUnit, clientName, libraryName)

    var i = 0
    (oldFeatures | newFeatures).subsets().foreach(selectedFeatures => {
      val e = selectedFeatures.foldLeft(FeatureExprFactory.True)((e, f) =>
        e.and(FeatureExprFactory.createDefinedExternal(f)))
      if (e.isSatisfiable(oldFeatureModel) && e.isSatisfiable(newFeatureModel)) {
        Using(new BufferedWriter(new FileWriter(new File(s"merged.$i.c"))))(
          _.write(layout(prettyPrint(
            ProductDerivation.deriveProduct(mergedTranslationUnit, selectedFeatures)))))
        i += 1
      }
    })
  }

  def main(args: Array[String]): Unit =
    run("clever/sortcmp/old.c", "clever/sortcmp/new.c", "client", "sortcmp")
}
