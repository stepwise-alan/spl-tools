import de.fosd.typechef.conditional.Opt

import scala.io.Source


trait Linux extends CaseStudy {
  private val srcPath = "../TypeChef-LinuxAnalysis/linux26333/linux-2.6.33.3/"

  private val source = Source.fromFile(f"$srcPath/pcs/x86.flist")

  override val filePaths: Iterator[String] = source.getLines().map(line => s"$srcPath/$line.c")

  override val frontendArgs: Array[String] = (
//    "--bdd " +
      "-x CONFIG_ " +
      // "--xtc " +
      "--smallFeatureModelFExpr approx.fm " +
      "--featureModelDimacs=pcs/x86.dimacs " +
      "--include=pcs/x86.completed.h " +
      "--include=pcs/x86.nonbool.h " +
      "--include=partialConf.h " +
      "-c ../windows.properties " +
      "--openFeat pcs/x86.open " +
      "--writePI --recordTiming --lexdebug " +
      "--errorXML --interface " +
      "--dumpcfg --parserstatistics " +
      "--adjustLines " +
      "-I linux-2.6.33.3/include " +
      "-I linux-2.6.33.3/arch/x86/include " +
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
