import de.fosd.typechef.conditional.{Choice, Conditional, One, Opt}
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.*

import scala.annotation.tailrec


trait UsageVisitor extends Visitor {

  def use(using FeatureExpr)(name: String): UsageVisitor

  def useType(using FeatureExpr)(name: String): UsageVisitor

  def useEnum(using FeatureExpr)(name: String): UsageVisitor

  def useStruct(using FeatureExpr)(name: String): UsageVisitor

  def useUnion(using FeatureExpr)(name: String): UsageVisitor
}
