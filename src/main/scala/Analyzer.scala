import com.opencsv.CSVWriter
import de.fosd.typechef.conditional.Opt
import de.fosd.typechef.featureexpr.FeatureModel
import de.fosd.typechef.parser.c.*
import de.fosd.typechef.parser.c.PrettyPrinter.{layout, prettyPrint}

import java.io.{BufferedWriter, File, FileWriter}

trait Analyzer extends CaseStudy {
  var fileCount = 0
  var functionCount = 0
  // var variationalFunctionCount = 0

  val csvWriter = new CSVWriter(new BufferedWriter(new FileWriter("results.csv")))

  def isDefinedInFile(externalDef: ExternalDef, filepath: String): Boolean =
    externalDef.getFile.isDefined && new File(externalDef.getFile.get.stripPrefix("file ")).equals(new File(filepath))

  def getInputAsString(declarator: Declarator): String = declarator match {
    case AtomicNamedDeclarator(_, _, extensions) => extensions.map(f = f => f.entry match
      case extension: DeclaratorAbstrExtension => extension match {
        case DeclParameterDeclList(parameterDecls) => parameterDecls.map(f => f.entry match
          case PlainParameterDeclaration(specifiers, _) =>
            specifiers.map(s => layout(prettyPrint(s.entry))).mkString("", " ", "")
          case ParameterDeclarationD(specifiers, decl, _) =>
            specifiers.map(s => layout(prettyPrint(s.entry))).mkString("", " ", "") + " "
              + decl.pointers.map(s => layout(prettyPrint(s.entry))).mkString("", "", "")
          case ParameterDeclarationAD(specifiers, decl, _) =>
            specifiers.map(s => layout(prettyPrint(s.entry))).mkString("", " ", "") + " "
              + decl.pointers.map(s => layout(prettyPrint(s.entry))).mkString("", "", "")
          case args: VarArgs => "#VarArgs"
        ).mkString("", ", ", "")
        case access: DeclArrayAccess => "#DeclArrayAccess"
      }
      case list: DeclIdentifierList => "#DeclIdentifierList"
    ).mkString("", ", ", "")
    case NestedNamedDeclarator(_, nestedDecl, _, _) => getInputAsString(nestedDecl)
  }

  def areAllInputsIntegers(declarator: Declarator): Boolean = declarator match {
    case AtomicNamedDeclarator(_, _, extensions) => extensions.forall(f => f.entry match
      case extension: DeclaratorAbstrExtension => extension match {
        case DeclParameterDeclList(parameterDecls) => parameterDecls.forall(p => p.entry match
          case PlainParameterDeclaration(specifiers, _) => specifiers.forall(s =>
            s.entry.isInstanceOf[IntSpecifier] || s.entry.isInstanceOf[CharSpecifier]
            || s.entry.isInstanceOf[LongSpecifier] || s.entry.isInstanceOf[Int128Specifier]
            || s.entry.isInstanceOf[UnsignedSpecifier] || s.entry.isInstanceOf[SignedSpecifier]
            || s.entry.isInstanceOf[ConstSpecifier]
          )
          case ParameterDeclarationD(specifiers, decl, _) => specifiers.forall(s =>
            s.entry.isInstanceOf[IntSpecifier] || s.entry.isInstanceOf[CharSpecifier]
              || s.entry.isInstanceOf[LongSpecifier] || s.entry.isInstanceOf[Int128Specifier]
              || s.entry.isInstanceOf[UnsignedSpecifier] || s.entry.isInstanceOf[SignedSpecifier]
              || s.entry.isInstanceOf[ConstSpecifier]
          ) && decl.pointers.isEmpty
          case ParameterDeclarationAD(specifiers, decl, _) => specifiers.forall(s =>
            s.entry.isInstanceOf[IntSpecifier] || s.entry.isInstanceOf[CharSpecifier]
              || s.entry.isInstanceOf[LongSpecifier] || s.entry.isInstanceOf[Int128Specifier]
              || s.entry.isInstanceOf[UnsignedSpecifier] || s.entry.isInstanceOf[SignedSpecifier]
              || s.entry.isInstanceOf[ConstSpecifier]
          ) && decl.pointers.isEmpty
          case VarArgs() => true
        )
        case DeclArrayAccess(_) => true
      }
      case DeclIdentifierList(_) => true
    )
    case NestedNamedDeclarator(_, nestedDecl, _, _) => areAllInputsIntegers(nestedDecl)
  }

  override def process(translationUnit: TranslationUnit, filepath: String,
                       featureModel: FeatureModel, features: Set[String]): Unit = {
    fileCount += 1

    translationUnit.defs.foreach(d => {
      if (isDefinedInFile(d.entry, filepath))
        d.entry match {
          case functionDef: FunctionDef =>
            if (isDefinedInFile(functionDef, filepath)) {
              functionCount += 1
              val result = new VariabilitySearcher(featureModel)(d)
              // if (result.features.nonEmpty) variationalFunctionCount += 1
              val output: Array[String] = Array(
                filepath, functionDef.getName, result.features.size.toString,
                result.optCount.toString, result.choiceCount.toString,
                getInputAsString(functionDef.declarator),
                (result.features.nonEmpty && areAllInputsIntegers(functionDef.declarator)).toString)
              println(output.mkString("", "; ", ""))
              csvWriter.writeNext(output)
              println(s"$filepath: ${functionDef.getName}: $result")
            }
          case _ =>
        }
    })
    // new VariabilitySearcher(featureModel)(translationUnit)
    // println(layout(prettyPrint(translationUnit)))
    //
    // println("=================================================")
    println(s"Processed $functionCount functions in $fileCount files")
    // println(s"Found functions with variability: $variationalFunctionCount / $functionCount")
  }

  override def run(): Unit = {
    csvWriter.writeNext(Array(
      "File Path", "Function Name", "Number of Distinct Features",
      "Number of Opts", "Number of Choices", "Inputs", "Analyzable"))
    super.run()
    csvWriter.close()
  }
}
