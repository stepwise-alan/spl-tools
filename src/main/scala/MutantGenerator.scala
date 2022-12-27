import de.fosd.typechef.conditional.Opt
import de.fosd.typechef.featureexpr.sat.{And, DefinedExternal, Not, Or, SATFeatureExpr}
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory, SingleFeatureExpr}
import de.fosd.typechef.parser.c.*
import org.apache.commons.lang3.NotImplementedException

import java.nio.file.{Files, Paths}
import scala.util.Random

class MutantGenerator(newFeatures: List[SingleFeatureExpr],
                      splRandom: Random, operatorMutationRandom: Random, featureExprMutationRandom: Random,
                      maxEnabledFeatures: Int, maxDisabledFeatures: Int,
                      operatorMutationProbability: Double, featureExprMutationProbability: Double) extends Converter {
  override def convert[U](using trace: Trace)(x: Opt[U])(using f: F[U]): Opt[U] = x match {
    case Opt(condition, entry) if condition == FeatureExprFactory.True && entry.isInstanceOf[ExprStatement] =>
      val r = Random(splRandom.nextInt())
      val shuffled = r.shuffle(newFeatures)

      val enabled = shuffled.take(r.nextInt(maxEnabledFeatures))
      val disabled = shuffled.takeRight(r.nextInt(maxDisabledFeatures))

      super.convert(Opt(FeatureExprFactory.createFeatureExprFast(enabled.toSet, disabled.toSet), entry))
    case _ => super.convert(x)
  }

  private def mutateOperator(op: String): String =
    if (operatorMutationRandom.nextDouble() < operatorMutationProbability) Map(
      "-" -> "+", "+" -> "-",
      "++" -> "--", "--" -> "++",
      "|" -> "&", "&" -> "|",
      "||" -> "&&", "&&" -> "||",
      "<<" -> ">>", ">>" -> "<<"
    ).getOrElse(op, op) else op

  private def mutateFeatureExpr(x: SATFeatureExpr): SATFeatureExpr = x match {
    case And(clauses) => new And(clauses.map(mutateFeatureExpr))
    case Or(clauses) => new Or(clauses.map(mutateFeatureExpr))
    case Not(d: DefinedExternal) =>
      if (featureExprMutationRandom.nextDouble() < featureExprMutationProbability) d else x
    case d: DefinedExternal =>
      if (featureExprMutationRandom.nextDouble() < featureExprMutationProbability) Not(d) else d
    case _ => throw NotImplementedException()
  }

  override def convert(using trace: Trace)(x: FeatureExpr): FeatureExpr = x match {
    case expr: SATFeatureExpr => mutateFeatureExpr(expr)
    case _ => throw NotImplementedException()
  }

  override def convert(using trace: Trace)(x: UnaryExpr): UnaryExpr = x match {
    case UnaryExpr(kind, e) =>
      UnaryExpr(mutateOperator(kind), e)
  }

  override def convert(using trace: Trace)(x: UnaryOpExpr): UnaryOpExpr = x match {
    case UnaryOpExpr(kind, castExpr) =>
      UnaryOpExpr(mutateOperator(kind), castExpr)
  }

  override def convert(using trace: Trace)(x: SimplePostfixSuffix): SimplePostfixSuffix = x match {
    case SimplePostfixSuffix(t) =>
      SimplePostfixSuffix(mutateOperator(t))
  }

  override def convert(using trace: Trace)(x: PointerPostfixSuffix): PointerPostfixSuffix = x match {
    case PointerPostfixSuffix(kind, id) =>
      PointerPostfixSuffix(mutateOperator(kind), id)
  }

  override def convert(using trace: Trace)(x: NArySubExpr): NArySubExpr = x match {
    case NArySubExpr(op, e) =>
      NArySubExpr(mutateOperator(op), e)
  }

  def apply(x: Opt[FunctionDef]): Opt[FunctionDef] = convert(using Trace(x.condition))(x)
}

object MutantGenerator {
  def main(args: Array[String]): Unit = {
    val oldTranslationUnit = Util.parse(Array("examples/coreutils.ls.6b01b71e.unsat/old.processed.bak.c"))._1
    val (oldFunctionDefs, oldExternalDefs) = oldTranslationUnit.defs
      .partitionMap[Opt[FunctionDef], Opt[ExternalDef]](_.entry match {
        case FunctionDef(specifiers, declarator, oldStyleParameters, stmt) =>
          val functionDef = Util.optTrue(FunctionDef(
            specifiers.filterNot(_.entry.isInstanceOf[StaticSpecifier]),
            declarator, oldStyleParameters, stmt
          ))
          if declarator.getName == "sortcmp" then Left(functionDef) else Right(functionDef)
        case e => Right(Util.optTrue(e))
      })

    assert(oldFunctionDefs.size == 1)
    val oldFunctionDef = oldFunctionDefs.head

    val newTranslationUnit = Util.parse(Array("examples/coreutils.ls.6b01b71e.unsat/new.processed.bak.c"))._1
    val (newFunctionDefs, newExternalDefs) = newTranslationUnit.defs
      .partitionMap[Opt[FunctionDef], Opt[ExternalDef]](_.entry match {
        case FunctionDef(specifiers, declarator, newStyleParameters, stmt) =>
          val functionDef = Util.optTrue(FunctionDef(
            specifiers.filterNot(_.entry.isInstanceOf[StaticSpecifier]),
            declarator, newStyleParameters, stmt
          ))
          if declarator.getName == "sortcmp" then Left(functionDef) else Right(functionDef)
        case e => Right(Util.optTrue(e))
      })

    assert(newFunctionDefs.size == 1)
    val newFunctionDef = newFunctionDefs.head

    val newFeatureCount = 9
    val newFeatures = (0 to newFeatureCount).toList.map(
      i => FeatureExprFactory.createDefinedExternal(s"F$i"))
    val maxEnabledFeatures = 6
    val maxDisabledFeatures = 3
    val operatorMutationProbability = 0.3
    val featureExprMutationProbability = 0.1

    for (i <- 1 to 20) {
      val directory = s"examples/hard/coreutils.ls.6b01b71e.sat.hard.spl$i"
      Files.createDirectories(Paths.get(directory))

      Util.writeToFile(TranslationUnit(oldExternalDefs :+
        MutantGenerator(newFeatures, Random(9 * i), Random(0), Random(0),
          maxEnabledFeatures, maxDisabledFeatures, 0, 0
        ).apply(oldFunctionDef)), s"$directory/old.c")
      Util.writeToFile(TranslationUnit(oldExternalDefs :+
        MutantGenerator(newFeatures, Random(9 * i), Random(9 * i + 1), Random(0),
          maxEnabledFeatures, maxDisabledFeatures, operatorMutationProbability, 0
        ).apply(oldFunctionDef)), s"$directory/old.op.c")
      Util.writeToFile(TranslationUnit(oldExternalDefs :+
        MutantGenerator(newFeatures, Random(9 * i), Random(0), Random(9 * i + 2),
          maxEnabledFeatures, maxDisabledFeatures, 0, featureExprMutationProbability
        ).apply(oldFunctionDef)), s"$directory/old.fe.c")
      Util.writeToFile(TranslationUnit(oldExternalDefs :+
        MutantGenerator(newFeatures, Random(9 * i), Random(9 * i + 3), Random(9 * i + 4),
          maxEnabledFeatures, maxDisabledFeatures, operatorMutationProbability, featureExprMutationProbability
        ).apply(oldFunctionDef)), s"$directory/old.op.fe.c")

      Util.writeToFile(TranslationUnit(newExternalDefs :+
        MutantGenerator(newFeatures, Random(9 * i), Random(0), Random(0),
          maxEnabledFeatures, maxDisabledFeatures, 0, 0
        ).apply(newFunctionDef)), s"$directory/new.c")
      Util.writeToFile(TranslationUnit(newExternalDefs :+
        MutantGenerator(newFeatures, Random(9 * i), Random(9 * i + 5), Random(0),
          maxEnabledFeatures, maxDisabledFeatures, operatorMutationProbability, 0
        ).apply(newFunctionDef)), s"$directory/new.op.c")
      Util.writeToFile(TranslationUnit(newExternalDefs :+
        MutantGenerator(newFeatures, Random(9 * i), Random(0), Random(9 * i + 6),
          maxEnabledFeatures, maxDisabledFeatures, 0, featureExprMutationProbability
        ).apply(newFunctionDef)), s"$directory/new.fe.c")
      Util.writeToFile(TranslationUnit(newExternalDefs :+
        MutantGenerator(newFeatures, Random(9 * i), Random(9 * i + 7), Random(9 * i + 8),
          maxEnabledFeatures, maxDisabledFeatures, operatorMutationProbability, featureExprMutationProbability
        ).apply(newFunctionDef)), s"$directory/new.op.fe.c")
    }
  }
}