import de.fosd.typechef.featureexpr.{FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.TranslationUnit

object LinuxBenchmarkPreprocessor extends Preprocessor with Linux {
  override val filePaths: Iterator[String] = List(
    "linux26333/linux/net/sunrpc/addr.c",
  ).iterator

  private val getFunctionName: Map[String, String] = Map(
    ("linux26333/linux/net/sunrpc/addr.c", "rpc_uaddr2sockaddr"),
  )

  private val getNewFilePath: Map[String, String] = Map(
    ("linux26333/linux/net/sunrpc/addr.c", "addr.preprocessed.c"),
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
