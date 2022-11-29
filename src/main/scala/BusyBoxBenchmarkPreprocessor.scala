import de.fosd.typechef.featureexpr.{FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.TranslationUnit

object BusyBoxBenchmarkPreprocessor extends BusyBox with Preprocessor {
  override val filePaths: Iterator[String] = List(
    "clever/coreutils.ls.6b01b71e/old.c",
    "clever/coreutils.ls.6b01b71e/new.c",
  ).iterator

  private val getFunctionName: Map[String, String] = Map(
    ("clever/coreutils.ls.6b01b71e/old.c", "sortcmp"),
    ("clever/coreutils.ls.6b01b71e/new.c", "sortcmp"),
  )

  private val getNewFilePath: Map[String, String] = Map(
    ("clever/coreutils.ls.6b01b71e/old.c", "clever/coreutils.ls.6b01b71e/old.processed.c"),
    ("clever/coreutils.ls.6b01b71e/new.c", "clever/coreutils.ls.6b01b71e/new.processed.c"),
  )

  override def process(translationUnit: TranslationUnit, filePath: String, featureModel: FeatureModel, features: Set[String]): Unit = {
    getFunctionName.get(filePath) match {
      case Some(functionName) =>
        Util.writeToFile(
          extract(using featureModel, translationUnit)(names = Map().updated(functionName, FeatureExprFactory.True)),
          getNewFilePath(filePath))
      case None =>
    }
  }

  def main(args: Array[String]): Unit = {
    run()
  }
}
