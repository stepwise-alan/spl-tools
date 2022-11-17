import de.fosd.typechef.featureexpr.FeatureModel
import de.fosd.typechef.options.{FrontendOptionsWithConfigFiles, Options}
import de.fosd.typechef.parser.c.TranslationUnit

import java.lang.reflect.Field

abstract class CaseStudy {
  val filePaths: Iterator[String]
  val frontendArgs: Array[String]

  def process(translationUnit: TranslationUnit, filePath: String,
              featureModel: FeatureModel, features: Set[String]): Unit

  def run(): Unit = filePaths.foreach(filepath => {
    try {
      val (translationUnit, featureModel, features) = Util.parse(frontendArgs :+ filepath)
      process(translationUnit, filepath, featureModel, features)
    } catch {
      case e: Throwable =>
        println(s"filepath: $filepath")
        e.printStackTrace()
    }
  })
}
