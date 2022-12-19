import de.fosd.typechef.conditional.Opt

import scala.io.Source


trait BusyBox extends CaseStudy {
  private val source = Source.fromFile(s"busybox/busybox_files")

  private val system = "windows"

  private val srcPath = s"busybox-1.18.5"

  override val filePaths: Iterator[String] = source.getLines().map(line => s"$srcPath/$line.c")

  private val partialPreprocessFlags = s"-c $system.properties " +
    "-x CONFIG_ " +
    "--include busybox/config.h " +
    s"-I $srcPath/include " +
    "--featureModelFExpr busybox/featureModel " +
    "--writePI --recordTiming --parserstatistics --lexdebug " +
    "--dumpcfg -t --interface --debugInterface --errorXML "

  private val flags = "-U HAVE_LIBDMALLOC " +
    "-DCONFIG_FIND " +
    "-U CONFIG_FEATURE_WGET_LONG_OPTIONS " +
    "-U ENABLE_NC_110_COMPAT " +
    "-U CONFIG_EXTRA_COMPAT " +
    "-D_GNU_SOURCE "

  override val frontendArgs: Array[String] = (partialPreprocessFlags + flags).split(' ')

  override def run(): Unit = {
    super.run()
    source.close()
  }
}
