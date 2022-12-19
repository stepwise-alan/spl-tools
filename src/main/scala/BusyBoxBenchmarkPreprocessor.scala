import de.fosd.typechef.featureexpr.{FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.TranslationUnit

object BusyBoxBenchmarkPreprocessor extends BusyBox with Preprocessor {
  override val filePaths: Iterator[String] = List(
    "busybox-1.18.5/util-linux/fsck_minix.c",
  ).iterator

  private val getFunctionName: Map[String, String] = Map(
    ("busybox-1.18.5/util-linux/fsck_minix.c", "check_counts"),
  )

  private val getNewFilePath: Map[String, String] = Map(
    ("busybox-1.18.5/util-linux/fsck_minix.c", "fsck_minix.preprocessed.c"),
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
