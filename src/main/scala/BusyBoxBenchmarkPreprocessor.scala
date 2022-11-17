import de.fosd.typechef.featureexpr.{FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.TranslationUnit

object BusyBoxBenchmarkPreprocessor extends BusyBox with Preprocessor {
  override val filePaths: Iterator[String] = List(
    "clever/unpack_lzma_stream/uncompress_unlzma.old.c",
    "clever/unpack_lzma_stream/uncompress_unlzma.new.c",
  ).iterator

  private val getFunctionName: Map[String, String] = Map(
    ("clever/unpack_lzma_stream/uncompress_unlzma.old.c", "unpack_lzma_stream"),
    ("clever/unpack_lzma_stream/uncompress_unlzma.new.c", "unpack_lzma_stream"),
  )

  private val getNewFilePath: Map[String, String] = Map(
    ("clever/unpack_lzma_stream/uncompress_unlzma.old.c", "clever/unpack_lzma_stream/uncompress_unlzma.old.extracted.c"),
    ("clever/unpack_lzma_stream/uncompress_unlzma.new.c", "clever/unpack_lzma_stream/uncompress_unlzma.new.extracted.c"),
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
