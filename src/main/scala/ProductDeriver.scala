import de.fosd.typechef.conditional.{Choice, Conditional, One, Opt}
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory}
import de.fosd.typechef.parser.c.*

import scala.annotation.unused

//noinspection DuplicatedCode
class ProductDeriver(features: Set[String]) extends Converter {
  override def convert(using Trace)(x: FeatureExpr): FeatureExpr =
    if (x.evaluate(features)) FeatureExprFactory.True else FeatureExprFactory.False

  override def convert[U](using trace: Trace)(x: Opt[U])(using f: F[U]): Opt[U] = x match {
    case Opt(condition, entry) => given Trace = trace + x

      Opt(convert(condition), f(entry))
  }

  override def convert[U](using trace: Trace)(x: Conditional[U])(using f: F[U]): Conditional[U] = x match {
    case Choice(condition, thenBranch, elseBranch) =>
        convert(if (convert(condition) == FeatureExprFactory.True) thenBranch else elseBranch)
    case One(value) => given Trace = trace + x

      One(f(value))
  }

  override def convert[U](using Trace)(x: List[U])(using f: F[U]): List[U] = x.map(f).filter {
    case Opt(condition, value) if condition == FeatureExprFactory.False => false
    case _ => true
  }

  override def convert[U](using Trace)(x: Option[U])(using f: F[U]): Option[U] = x.map(f).filter {
    case Opt(condition, value) if condition == FeatureExprFactory.False => false
    case _ => true
  }

  def apply(x: TranslationUnit): TranslationUnit = convert(using Trace())(x)
}
