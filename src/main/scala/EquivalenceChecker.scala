import de.fosd.typechef.conditional.Opt
import de.fosd.typechef.featureexpr.sat.True
import de.fosd.typechef.parser.c.{AsmExpr, CompoundStatement, Declaration, EmptyExternalDef, FunctionDef, Pragma, Statement, TranslationUnit, TypelessDeclaration}

import java.io.File
import scala.sys.process.{Process, ProcessLogger}

object EquivalenceChecker {
  def verify(preConditions: List[Opt[Statement]], translationUnit: TranslationUnit,
             postConditions: List[Opt[Statement]]): Option[String => String] = {
    val cFilepath = "verify_task.c"
    val bcFilepath = "verify_task.bc"
    val ppBcFilepath = "verify_task.pp.bc"
    val ppMsBcFilepath = "verify_task.pp.ms.bc"
    val ppMsOBcFilepath = "verify_task.pp.ms.o.bc"
    val llFilepath = "verify_task.ll"
    val harnessFilepath = "verify_task.harness.ll"
    val smt2Filepath = "verify_task.smt2"
    Util.writeToFile(TranslationUnit(translationUnit.defs.map(e => e.entry match {
      case f@FunctionDef(specifiers, declarator, oldStyleParameters, stmt) if f.getName == "main" =>
        Opt(e.condition, FunctionDef(specifiers, declarator, oldStyleParameters,
          CompoundStatement(preConditions ++ stmt.innerStatements ++ postConditions)))
      case _ => e
    })), cFilepath)
    Process(
      s"/usr/bin/clang-14 -c -emit-llvm -D__SEAHORN__ -fdeclspec -O1 -Xclang -disable-llvm-optzns -fgnu89-inline " +
        s"-m64 -I/mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/include -g -o $bcFilepath $cFilepath"
    ).!!
    Process(
      s"/mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/bin/seapp -o $ppBcFilepath " +
        s"--simplifycfg-sink-common=false --strip-extern=false --promote-assumptions=false --kill-vaarg=true " +
        s"--horn-keep-arith-overflow=false $bcFilepath"
    ).!!
    Process(
      s"/mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/bin/seapp --simplifycfg-sink-common=false " +
        s"-o $ppMsBcFilepath --horn-mixed-sem --ms-reduce-main $ppBcFilepath"
    ).!!
    Process(
      s"/mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/bin/seaopt -f --simplifycfg-sink-common=false " +
        s"-o $ppMsOBcFilepath --seaopt-enable-indvar=false --seaopt-enable-loop-idiom=false --unroll-threshold=150 " +
        s"--unroll-allow-partial=false --unroll-partial-threshold=0 --vectorize-slp=false $ppMsBcFilepath"
    ).!!
    val result = Process(
      "/mnt/c/Users/shuolin/CLionProjects/seahorn/build/run/bin/seahorn --keep-shadows=true --sea-dsa=ci " +
        s"--horn-solve -horn-cex-pass -horn-cex=$harnessFilepath -oll $llFilepath --horn-cex-bv=true " +
        s"-horn-inter-proc -horn-sem-lvl=mem --horn-step=large -o $smt2Filepath $ppMsOBcFilepath --horn-bv-part-mem"
    ).!!
    result.linesIterator.foldLeft(Option.empty[String]) { case (_, line) => Some(line) } match {
      case Some("sat") => Some(Map())
      case Some("unsat") => None
      case _ => assert(false)
    }
  }

  def generalize(counterexample: String => String) = {
//    TODO
    val smt2Filepath = "verify_task.smt2"
    val logFilepath = "z3.txt"
//    val result = Process(f"z3 proof=true fp.engine=spacer fp.spacer.order_children=2 " +
//      f"fp.xform.subsumption_checker=false fp.xform.inline_eager=false " +
//      f"fp.xform.inline_linear=false fp.spacer.trace_file=$logFilepath " +
//      f"-v:2 $smt2Filepath").!!

  }

  def getNeqSummary(oldSuperposition: TranslationUnit, oldFunctionName: String,
                    newSuperposition: TranslationUnit, newFunctionName: String,
                    features: Set[String]): List[String] = {
    val translationUnit = CleverSelfComposer(
      oldSuperposition, oldFunctionName,
      newSuperposition, newFunctionName)
    var summary = List()
    var preConditions = List()
    var postConditions = List()
    var counterexample = verify(preConditions, translationUnit, postConditions)
    print(counterexample)
    while (counterexample.isDefined) {
      val c = generalize(counterexample.get)
    }
    List()
  }

  def main(args: Array[String]): Unit = {
    val (oldTranslationUnit, _, oldFeatures) = Util.parse(Array(args(0)))
    val (newTranslationUnit, featureModel, newFeatures) = Util.parse(Array(args(1)))
    val features = newFeatures ++ oldFeatures

    VariabilitySearcher(featureModel)(oldTranslationUnit)
    VariabilitySearcher(featureModel)(newTranslationUnit)

    val oldSuperposition = Superposer(featureModel)(oldTranslationUnit, features)
    val newSuperposition = Superposer(featureModel)(newTranslationUnit, features)

    print(getNeqSummary(oldSuperposition, args(2), newSuperposition, args(2), features))
  }
}
