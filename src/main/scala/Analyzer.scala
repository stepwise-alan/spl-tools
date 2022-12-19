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
          case _: VarArgs => "#VarArgs"
        ).mkString("", ", ", "")
        case _: DeclArrayAccess => "#DeclArrayAccess"
      }
      case _: DeclIdentifierList => "#DeclIdentifierList"
    ).mkString("", ", ", "")
    case NestedNamedDeclarator(_, nestedDecl, _, _) => getInputAsString(nestedDecl)
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
                result.features.nonEmpty.toString)
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
      "File Path", "Function Name", "Feature Count",
      "Opt Count", "Choice Count", "Inputs", "Analyzable"))
    super.run()
    csvWriter.close()
  }
}
