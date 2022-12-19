import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory}
import de.fosd.typechef.parser.c.AST

import scala.annotation.targetName

class Trace(val condition: FeatureExpr = FeatureExprFactory.True, val nodes: List[Any] = List()) {
  @targetName("add")
  def +(node: Any): Trace = Trace(condition, node +: nodes)

  @targetName("add")
  def +(node: Any, featureExpr: FeatureExpr): Trace = Trace(condition.and(featureExpr), node +: nodes)

  def asts: List[AST] = nodes.collect { case ast: AST => ast }
}
