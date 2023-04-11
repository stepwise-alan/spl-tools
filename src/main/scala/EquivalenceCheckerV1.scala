import scala.sys.process.Process
import System.{currentTimeMillis, nanoTime}
object EquivalenceCheckerV1 {
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

//    val frontendArgs = (partialPreprocessFlags + flags).split(' ')
    val frontendArgs = Array[String]()

    val t0 = currentTimeMillis()
    val (oldTranslationUnit, _, oldFeatures) = Util.parse(frontendArgs :+ args(0), parserResults = true)
    val (newTranslationUnit, featureModel, newFeatures) = Util.parse(frontendArgs :+ args(1), parserResults = true)
    val t1 = currentTimeMillis()

    val features = newFeatures ++ oldFeatures
    VariabilitySearcher(featureModel)(oldTranslationUnit)
    VariabilitySearcher(featureModel)(newTranslationUnit)
    val t2 = currentTimeMillis()

    val oldSuperposition = Superposer(featureModel, features)(oldTranslationUnit)
    val newSuperposition = Superposer(featureModel, features)(newTranslationUnit)
    val t3 = currentTimeMillis()

    val selfComposition = CleverSelfComposer(oldSuperposition, args(2), newSuperposition, args(2), frontendArgs)
    val t4 = currentTimeMillis()

    //    Verifier(args(3), args(4))(selfComposition, features)
    VerifierV2(args(3), args(4))(selfComposition, features)
    val t5 = currentTimeMillis()

    println(s"Parser:        ${"%10d".format(t1 - t0)} ms")
    println(s"Preprocessor:  ${"%10d".format(t2 - t1)} ms")
    println(s"Superposer:    ${"%10d".format(t3 - t2)} ms")
    println(s"SelfComposer:  ${"%10d".format(t4 - t3)} ms")
    println(s"Verifier:      ${"%10d".format(t5 - t4)} ms")
    println()
    println(s"Total:         ${"%10d".format(t5 - t0)} ms")
  }
}
