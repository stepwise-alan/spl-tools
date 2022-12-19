import de.fosd.typechef.conditional.Opt

import scala.io.Source


trait Linux extends CaseStudy {
  private val srcPath = "linux26333"

  private val source = Source.fromFile(f"$srcPath/pcs/x86.flist")

  override val filePaths: Iterator[String] = source.getLines().map(line => s"$srcPath/linux-2.6.33.3/$line.c")

  override val frontendArgs: Array[String] = (
    "--bdd " +
      "-x CONFIG_ " +
      // "--xtc " +
//      s"--smallFeatureModelFExpr $srcPath/approx.fm " +
      s"--featureModelDimacs=$srcPath/pcs/x86.dimacs " +
      s"--include=$srcPath/pcs/x86.completed.h " +
      s"--include=$srcPath/pcs/x86.nonbool.h " +
      s"--include=$srcPath/partialConf.h " +
      "-c linux-redhat.properties " +
      s"--openFeat $srcPath/pcs/x86.open " +
      "--writePI --recordTiming --lexdebug " +
      "--errorXML --interface " +
      "--dumpcfg --parserstatistics " +
      "--adjustLines " +
      s"-I $srcPath/linux-2.6.33.3/include " +
      s"-I $srcPath/linux-2.6.33.3/arch/x86/include " +
      "-D __KERNEL__ " +
      "-DCONFIG_AS_CFI=1 " +
      "-DCONFIG_AS_CFI_SIGNAL_FRAME=1 " +
      "-DKBUILD_BASENAME=\"aes_glue\" " +
      "-DKBUILD_MODNAME=\"aes_glue\""
    ).split(' ')
  //    "--bdd -x CONFIG_ --xtc --smallFeatureModelFExpr approx.fm --featureModelDimacs=pcs/x86.dimacs " +
  //      "--include=pcs/x86.completed.h --include=pcs/x86.nonbool.h --include=partialConf.h -c " +
  //      "../linux-redhat.properties --openFeat pcs/x86.open --writePI --recordTiming --lexdebug " +
  //      "--errorXML --interface --dumpcfg --parserstatistics --adjustLines").split(' ')

  override def run(): Unit = {
    super.run()
    source.close()
  }
}
