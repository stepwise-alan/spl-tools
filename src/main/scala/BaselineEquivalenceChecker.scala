import java.lang.System.{currentTimeMillis, nanoTime}
import scala.sys.process.{Process, ProcessLogger}
import de.fosd.typechef.crewrite.ProductDerivation

object BaselineEquivalenceChecker {
  def main(args: Array[String]): Unit = {
    val system = "ubuntu"

    val srcPath = "busybox-1.18.5"

    val typeChefBusyboxAnalysisPath = args(5)

    val partialPreprocessFlags = s"-c $typeChefBusyboxAnalysisPath/$system.properties " +
      "-x CONFIG_ " +
      s"--include $typeChefBusyboxAnalysisPath/busybox/config.h " +
      s"-I $typeChefBusyboxAnalysisPath/$srcPath/include " +
      s"--featureModelFExpr $typeChefBusyboxAnalysisPath/busybox/featureModel " +
      "--writePI --recordTiming --parserstatistics --lexdebug " +
      "--dumpcfg -t --interface --debugInterface --errorXML "

    val flags = "-U HAVE_LIBDMALLOC " +
      "-DCONFIG_FIND " +
      "-U CONFIG_FEATURE_WGET_LONG_OPTIONS " +
      "-U ENABLE_NC_110_COMPAT " +
      "-U CONFIG_EXTRA_COMPAT " +
      "-D_GNU_SOURCE "

    val frontendArgs = (partialPreprocessFlags + flags).split(' ')

    val t0 = currentTimeMillis()
    val (oldTranslationUnit, _, oldFeatures) = Util.parse(frontendArgs :+ args(0), parserResults = true)
    val (newTranslationUnit, featureModel, newFeatures) = Util.parse(frontendArgs :+ args(1), parserResults = true)
    val t1 = currentTimeMillis()

    VariabilitySearcher(featureModel)(oldTranslationUnit)
    VariabilitySearcher(featureModel)(newTranslationUnit)
    val t2 = currentTimeMillis()

    var selfComposerTime: Long = 0
    var verifierTime: Long = 0

    val features = newFeatures ++ oldFeatures
    features.subsets()
      .map(selectedFeatures => (
        ProductDerivation.deriveProduct(oldTranslationUnit, selectedFeatures),
        ProductDerivation.deriveProduct(newTranslationUnit, selectedFeatures)
      )).zipWithIndex.map((products, i) => {
      val t0 = currentTimeMillis()
      val oldFilepath = s"$i.old.c"
      Util.writeToFile(products._1, oldFilepath)
      val newFilepath = s"$i.new.c"
      Util.writeToFile(products._2, newFilepath)
      val outputFilePath = s"$i.c"
      Process(f"python3 /home/shuolin/IdeaProjects/spl-tools/src/main/py/self_composer.py " +
        f"--old $oldFilepath --new $newFilepath --function ${args(2)} --out $outputFilePath"
      ).!!(ProcessLogger(_ => ()))
      val selfComposition = Util.parse(frontendArgs :+ outputFilePath)._1
      val t1 = currentTimeMillis()

      val cex = BaselineVerifier(args(3), args(4))(selfComposition, features, i)
      val t2 = currentTimeMillis()
      selfComposerTime += t1 - t0
      verifierTime += t2 - t1
      cex
    }).toList.filter(_.nonEmpty).zipWithIndex.foreach((cex, i) =>
      println(s"Counter Example $i:")
      println(s"\t${cex.get._1}     &&     ${cex.get._2}")
    )

    println(s"Parser:        ${"%10d".format(t1 - t0)} ms")
    println(s"Preprocessor:  ${"%10d".format(t2 - t1)} ms")
    println(s"SelfComposer:  ${"%10d".format(selfComposerTime)} ms")
    println(s"Verifier:      ${"%10d".format(verifierTime)} ms")
    println()
    println(s"Total:         ${"%10d".format(t2 - t0 + selfComposerTime + verifierTime)} ms")
  }
}
