import de.fosd.typechef.conditional.Opt

import scala.io.Source


trait GitBusyBox extends CaseStudy {
  private val source = Source.fromFile("gitbusybox/filelist")

  val system: String = "windows"

  val srcPath: String = "gitbusybox"

  override val filePaths: Iterator[String] = source.getLines().map(line => s"$srcPath/$line.c")

  private val partialPreprocessFlags = s"-c $system.properties " +
    "--bdd " +
    "--openFeat=gitbusybox/features " +
    "--include gitbusybox/header.h " +
    "--include mheader.h " +
    "--featureModelDimacs gitbusybox/featureModel.dimacs " +
    s"-I $srcPath/include " +
    "--writePI --recordTiming --parserstatistics --lexdebug " +
    "--dumpcfg -t --interface --debugInterface --errorXML "

  private val flags = "-U HAVE_LIBDMALLOC " +
    "-U ENABLE_NC_110_COMPAT " +
    "-D_GNU_SOURCE "

  override val frontendArgs: Array[String] = (partialPreprocessFlags + flags).split(' ')

  override def run(): Unit = {
    super.run()
    source.close()
  }
}
