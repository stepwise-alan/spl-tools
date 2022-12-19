import de.fosd.typechef.featureexpr.FeatureModel
import de.fosd.typechef.parser.c.PrettyPrinter.{layout, prettyPrint}
import de.fosd.typechef.parser.c.TranslationUnit
import java.io.{BufferedWriter, FileWriter, File}

object Printer extends GitBusyBox {
  override val filePaths: Iterator[String] = List(s"$srcPath/util-linux/mkfs_minix.c").iterator

  override def process(translationUnit: TranslationUnit,
                       filePath: String,
                       featureModel: FeatureModel,
                       features: Set[String]): Unit = {
    val newFilePath = "mkfs_minix.old.c"
    new VariabilitySearcher(featureModel)(translationUnit)
    Util.writeToFile(translationUnit, newFilePath)
  }

  def main(args: Array[String]): Unit = {
    run()
  }
}
