import de.fosd.typechef.crewrite.ProductDerivation
import de.fosd.typechef.featureexpr.FeatureModel
import de.fosd.typechef.parser.c.PrettyPrinter.{layout, prettyPrint}
import de.fosd.typechef.parser.c.{CASTEnv, TranslationUnit}

import java.io.{BufferedWriter, File, FileWriter}
import scala.util.Using

trait ProductGenerator extends CaseStudy {
  override def process(translationUnit: TranslationUnit, filePath: String,
                       featureModel: FeatureModel, features: Set[String]): Unit = {
    features.subsets()
      .map(ProductDerivation.deriveProduct(translationUnit, _))
      .zipWithIndex.foreach((product, i) => {
      val path = filePath.replace(".c", s".$i.c")
      Using(new BufferedWriter(new FileWriter(new File(path))))(_.write(layout(prettyPrint(product))))
      println(path)
    })
  }
}
