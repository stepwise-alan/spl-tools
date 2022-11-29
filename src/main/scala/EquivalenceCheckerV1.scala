import scala.sys.process.Process

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

    val frontendArgs = (partialPreprocessFlags + flags).split(' ')

    val (oldTranslationUnit, _, oldFeatures) = Util.parse(frontendArgs :+ args(0), parserResults = true)
    val (newTranslationUnit, featureModel, newFeatures) = Util.parse(frontendArgs :+ args(1), parserResults = true)
    val features = newFeatures ++ oldFeatures

    VariabilitySearcher(featureModel)(oldTranslationUnit)
    VariabilitySearcher(featureModel)(newTranslationUnit)

    val oldSuperposition = Superposer(featureModel)(oldTranslationUnit, features)
    val newSuperposition = Superposer(featureModel)(newTranslationUnit, features)

    val selfComposition = CleverSelfComposer(oldSuperposition, args(2), newSuperposition, args(2), frontendArgs)
    
    Verifier(args(3), args(4))(selfComposition, features)
  }
}
