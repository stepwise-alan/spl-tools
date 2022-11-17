import de.fosd.typechef.conditional.{Choice, Conditional, One, Opt}
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.*

import scala.annotation.tailrec

trait Preprocessor {
  private def getExternalDefs(using featureModel: FeatureModel, translationUnit: TranslationUnit)
                             (names: Map[String, FeatureExpr] = Map(),
                              types: Map[String, FeatureExpr] = Map(),
                              enums: Map[String, FeatureExpr] = Map(),
                              structs: Map[String, FeatureExpr] = Map(),
                              unions: Map[String, FeatureExpr] = Map(),
                              result: Set[Int] = Set(),
                              skip: Int => Boolean = _ => false): Set[Int] = {

    translationUnit.defs.zipWithIndex.foldLeft(result) {
      case (set, (Opt(condition, externalDef), i)) =>
        def hasSpecifier(specifiers: List[Opt[Specifier]]): Boolean = specifiers.exists(p => p.entry match {
          case specifier: EnumSpecifier =>
            specifier.enumerators match {
              case Some(l) =>
                val c = condition.and(p.condition)
                (specifier.id match {
                  case Some(id) =>
                    enums.get(id.name) match {
                      case Some(featureExpr) => c.and(featureExpr).isSatisfiable(featureModel)
                      case None => false
                    }
                  case None => false
                }) || l.exists(q => names.get(q.entry.id.name) match {
                  case Some(featureExpr) => c.and(q.condition).and(featureExpr).isSatisfiable(featureModel)
                  case None => false
                })
              case None => false
            }
          case specifier: StructOrUnionSpecifier =>
            Util.isConcreteStruct(specifier) && (specifier.id match {
              case Some(id) => (if (specifier.isUnion) unions else structs).get(id.name) match {
                case Some(featureExpr) => condition.and(p.condition).and(featureExpr).isSatisfiable(featureModel)
                case None => false
              }
              case None => false
            })
          case _ => false
        })

        def hasInitDeclarator(map: Map[String, FeatureExpr],
                              initDeclarators: List[Opt[InitDeclarator]]): Boolean =
          initDeclarators.exists(p => map.get(p.entry.getName) match {
            case Some(featureExpr) => condition.and(p.condition).and(featureExpr).isSatisfiable(featureModel)
            case None => false
          })

        def shouldKeep(): Boolean =
          externalDef match {
            case declaration: Declaration =>
              hasInitDeclarator(if (Util.isTypeDef(declaration)) types else names, declaration.init)
                || hasSpecifier(declaration.declSpecs)
            case functionDef: FunctionDef =>
              names.get(functionDef.getName) match {
                case Some(featureExpr) =>
                  condition.and(featureExpr).isSatisfiable(featureModel)
                    || hasSpecifier(functionDef.specifiers)
                case None => false
              }
            case declaration: TypelessDeclaration => hasInitDeclarator(names, declaration.declList)
            case _ => false
          }

        if (skip(i) || set.contains(i)) set else if (shouldKeep()) set + i else set
    }
  }

  @tailrec
  private def loop(using featureModel: FeatureModel,
                   translationUnit: TranslationUnit)
                  (frontier: Set[Int],
                   visited: Set[Int] = Set()): Set[Int] =
    if (frontier.isEmpty)
      visited
    else {
      val globalExtractor = GlobalUsageVisitor(featureModel).apply(translationUnit.defs(frontier.head))
      loop(getExternalDefs(globalExtractor.nameUsages,
        globalExtractor.typeUsages,
        globalExtractor.enumUsages,
        globalExtractor.structUsages,
        globalExtractor.unionUsages,
        frontier.tail, visited),
        visited + frontier.head)
    }

  def extract(using featureModel: FeatureModel,
              translationUnit: TranslationUnit)
             (names: Map[String, FeatureExpr] = Map(),
              types: Map[String, FeatureExpr] = Map(),
              enums: Map[String, FeatureExpr] = Map(),
              structs: Map[String, FeatureExpr] = Map(),
              unions: Map[String, FeatureExpr] = Map()): TranslationUnit = {
    val shouldKeep = loop(getExternalDefs(names, types, enums, structs, unions))
    TranslationUnit(translationUnit.defs.zipWithIndex.collect { case (value, i) if shouldKeep(i) => value })
  }
}
