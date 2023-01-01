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
      "<<" -> ">>", ">>" -> "<<",
//      "<" -> ">=", ">" -> "<=",
//      "<=" -> ">", ">=" -> "<",
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

class MutantGenerator2(newFeatures: List[SingleFeatureExpr],
                       seed: Int, enableNewFeatureProbability: Double, disableNewFeatureProbability: Double,
                       operatorMutationSeed: Int, operatorMutationProbability: Double,
                       featureExprMutationSeed: Int, featureExprMutationProbability: Double) extends Converter {
  private val random = Random(seed)
  private val operatorMutationRandom = Random(operatorMutationSeed)
  private val featureExprMutationRandom = Random(featureExprMutationSeed)

  override def convert[U](using trace: Trace)(x: Opt[U])(using f: F[U]): Opt[U] = x match {
    case Opt(condition, entry) if condition == FeatureExprFactory.True && entry.isInstanceOf[ExprStatement] =>
      val (enabled, disabled) = newFeatures.flatMap(f => {
        val r = random.nextDouble()
        if (r < enableNewFeatureProbability) {
          Some(Left(f))
        } else if (r > 1 - disableNewFeatureProbability) {
          Some(Right(f))
        } else {
          None
        }
      }).partitionMap(identity)
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

    val enableNewFeatureProbability = 0.150
    val disableNewFeatureProbability = 0.075
    val operatorMutationProbability = 0.150
    val featureExprMutationProbability = 0.075

    for (newFeatureCount <- 6 to 10) {
      val newFeatures = (0 to newFeatureCount).toList.map(
        i => FeatureExprFactory.createDefinedExternal(s"F$i"))
      //    val maxEnabledFeatures = 5
      //    val maxDisabledFeatures = 3

      for (i <- 1 to 20) {
        val directory = s"examples/hard/${newFeatureCount}/coreutils.ls.6b01b71e.sat.spl$i"
        Files.createDirectories(Paths.get(directory))

        Util.writeToFile(TranslationUnit(oldExternalDefs :+
          MutantGenerator2(newFeatures, 9 * i, enableNewFeatureProbability, disableNewFeatureProbability,
            0, 0, 0, 0
          ).apply(oldFunctionDef)), s"$directory/old.c")
        Util.writeToFile(TranslationUnit(oldExternalDefs :+
          MutantGenerator2(newFeatures, 9 * i, enableNewFeatureProbability, disableNewFeatureProbability,
            9 * i + 1, operatorMutationProbability, 0, 0
          ).apply(oldFunctionDef)), s"$directory/old.op.c")
        Util.writeToFile(TranslationUnit(oldExternalDefs :+
          MutantGenerator2(newFeatures, 9 * i, enableNewFeatureProbability, disableNewFeatureProbability,
            0, 0, 9 * i + 2, featureExprMutationProbability
          ).apply(oldFunctionDef)), s"$directory/old.fe.c")
        Util.writeToFile(TranslationUnit(oldExternalDefs :+
          MutantGenerator2(newFeatures, 9 * i, enableNewFeatureProbability, disableNewFeatureProbability,
            9 * i + 3, operatorMutationProbability, 9 * i + 4, featureExprMutationProbability
          ).apply(oldFunctionDef)), s"$directory/old.op.fe.c")

        Util.writeToFile(TranslationUnit(newExternalDefs :+
          MutantGenerator2(newFeatures, 9 * i, enableNewFeatureProbability, disableNewFeatureProbability,
            0, 0, 0, 0
          ).apply(newFunctionDef)), s"$directory/new.c")
        Util.writeToFile(TranslationUnit(newExternalDefs :+
          MutantGenerator2(newFeatures, 9 * i, enableNewFeatureProbability, disableNewFeatureProbability,
            9 * i + 5, operatorMutationProbability, 0, 0
          ).apply(newFunctionDef)), s"$directory/new.op.c")
        Util.writeToFile(TranslationUnit(newExternalDefs :+
          MutantGenerator2(newFeatures, 9 * i, enableNewFeatureProbability, disableNewFeatureProbability,
            0, 0, 9 * i + 6, featureExprMutationProbability
          ).apply(newFunctionDef)), s"$directory/new.fe.c")
        Util.writeToFile(TranslationUnit(newExternalDefs :+
          MutantGenerator2(newFeatures, 9 * i, enableNewFeatureProbability, disableNewFeatureProbability,
            9 * i + 7, operatorMutationProbability, 9 * i + 8, featureExprMutationProbability
          ).apply(newFunctionDef)), s"$directory/new.op.fe.c")
      }
    }
  }
}