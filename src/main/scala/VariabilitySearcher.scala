import de.fosd.typechef.conditional.{Choice, Opt}
import de.fosd.typechef.featureexpr.{FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.{ExternalDef, TranslationUnit}

class VariabilitySearcher(featureModel: FeatureModel,
                          val features: Set[String] = Set(),
                          val optCount: Int = 0,
                          val choiceCount: Int = 0) extends Visitor {
  override def enterAny(using trace: Trace)(x: Any): Visitor = this

  override def leaveAny(using trace: Trace)(x: Any): Visitor = this

  override def enter[T](using trace: Trace)(x: Opt[T]): Visitor = x match {
    case Opt(condition, entry) =>
      val field = x.getClass.getDeclaredField("condition")
      field.setAccessible(true)

      if (trace.condition.implies(condition).isTautology(featureModel)) {
        field.set(x, FeatureExprFactory.True)
        this
      } else if (trace.condition.implies(condition.not()).isTautology(featureModel)) {
        field.set(x, FeatureExprFactory.False)
        this
      } else
        VariabilitySearcher(featureModel, features | condition.collectDistinctFeatures, optCount + 1, choiceCount)
  }

  override def enter[T](using trace: Trace)(x: Choice[T]): Visitor = x match {
    case Choice(condition, thenBranch, elseBranch) =>
      val field = x.getClass.getDeclaredField("condition")
      field.setAccessible(true)

      if (trace.condition.implies(condition).isTautology(featureModel)) {
        field.set(x, FeatureExprFactory.True)
        this
      } else if (trace.condition.implies(condition.not()).isTautology(featureModel)) {
        field.set(x, FeatureExprFactory.False)
        this
      } else
        VariabilitySearcher(featureModel, features | condition.collectDistinctFeatures, optCount, choiceCount + 1)
  }

  def apply(x: Opt[ExternalDef]): VariabilitySearcher = visit(using Trace(x.condition))(x.entry).asInstanceOf[VariabilitySearcher]

  def apply(x: TranslationUnit): VariabilitySearcher = visit(using Trace())(x).asInstanceOf[VariabilitySearcher]

  override def toString: String = s"number of distinct features: ${features.size}; " +
    s"number of Opts: $optCount; " +
    s"number of choices: $choiceCount"
}
