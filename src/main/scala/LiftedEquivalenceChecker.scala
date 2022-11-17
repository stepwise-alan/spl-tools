import de.fosd.typechef.conditional.Opt
import de.fosd.typechef.featureexpr.sat.True
import de.fosd.typechef.parser.c.{AsmExpr, CompoundStatement, Declaration, EmptyExternalDef, FunctionDef, Pragma, Statement, TranslationUnit, TypelessDeclaration}

import java.io.File
import scala.sys.process.{Process, ProcessLogger}

object LiftedEquivalenceChecker {
  def verify(preConditions: List[Opt[Statement]], translationUnit: TranslationUnit,
             postConditions: List[Opt[Statement]]): Option[String => String] = {
    Util.writeToFile(TranslationUnit(translationUnit.defs.map(e => e.entry match {
      case f@FunctionDef(specifiers, declarator, oldStyleParameters, stmt) if f.getName == "main" =>
        Opt(e.condition, FunctionDef(specifiers, declarator, oldStyleParameters,
          CompoundStatement(preConditions ++ stmt.innerStatements ++ postConditions)))
      case _ => e
    })), "verify_task.c")
    val result = Process(f"sea fpf -m64 --log=cex --horn-bmc-cexgen --solve " +
      f"--horn-bmc-engine=mono --horn-bv2-extra-widemem --horn-bv2-lambdas=false " +
      f"--horn-bmc --horn-bv2=true --keep-shadows=true --cex=verify_task.cex.ll -m64 verify_task.c"
    ).!!<
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
    val result = Process(f"z3 proof=true fp.engine=spacer fp.spacer.order_children=2 " +
      f"fp.xform.subsumption_checker=false fp.xform.inline_eager=false " +
      f"fp.xform.inline_linear=false " +
      f"fp.spacer.trace_file=$logFilepath " +
      f"-v:2 $smt2Filepath").!!<
  }

  def getNeqSummary(oldTranslationUnit: TranslationUnit, oldFunctionName: String,
                    newTranslationUnit: TranslationUnit, newFunctionName: String,
                    features: Set[String]): List[String] = {
    val translationUnit = CleverSelfComposer(
      oldTranslationUnit, oldFunctionName,
      newTranslationUnit, newFunctionName)
    var summary = List()
    var preConditions = List()
    var postConditions = List()
    var counterexample = verify(preConditions, translationUnit, postConditions)
    while (counterexample.isDefined) {
      val c = generalize(counterexample.get)
      ???
    }
    ???
  }

  def main(args: Array[String]): Unit = {

  }
}
