import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureModel, FeatureExprFactory}
import de.fosd.typechef.parser.c.*

import scala.annotation.tailrec

class LocalUsageVisitor(extractor: UsageVisitor,
                        featureModel: FeatureModel,
                        labels: Map[String, FeatureExpr] = Map(),
                        names: Map[String, FeatureExpr] = Map(),
                        types: Map[String, FeatureExpr] = Map(),
                        localLabels: Map[String, FeatureExpr] = Map(),
                        enums: Map[String, FeatureExpr] = Map(),
                        structs: Map[String, FeatureExpr] = Map(),
                        unions: Map[String, FeatureExpr] = Map(),
                    ) extends UsageVisitor {

  private def updateExtractor(newExtractor: UsageVisitor): LocalUsageVisitor =
    LocalUsageVisitor(newExtractor, featureModel, labels, names, types, localLabels, enums, structs, unions)

  private def updateLabels(newLabels: Map[String, FeatureExpr]): LocalUsageVisitor =
    LocalUsageVisitor(extractor, featureModel, newLabels, names, types, localLabels, enums, structs, unions)

  private def updateNames(newNames: Map[String, FeatureExpr]): LocalUsageVisitor =
    LocalUsageVisitor(extractor, featureModel, labels, newNames, types, localLabels, enums, structs, unions)

  private def updateTypes(newTypes: Map[String, FeatureExpr]): LocalUsageVisitor =
    LocalUsageVisitor(extractor, featureModel, labels, names, newTypes, localLabels, enums, structs, unions)

  private def updateLocalLabels(newLocalLabels: Map[String, FeatureExpr]): LocalUsageVisitor =
    LocalUsageVisitor(extractor, featureModel, labels, names, types, newLocalLabels, enums, structs, unions)

  private def updateEnums(newEnums: Map[String, FeatureExpr]): LocalUsageVisitor =
    LocalUsageVisitor(extractor, featureModel, labels, names, types, localLabels, newEnums, structs, unions)

  private def updateStructs(newStructs: Map[String, FeatureExpr]): LocalUsageVisitor =
    LocalUsageVisitor(extractor, featureModel, labels, names, types, localLabels, enums, newStructs, unions)

  private def updateUnions(newUnions: Map[String, FeatureExpr]): LocalUsageVisitor =
    LocalUsageVisitor(extractor, featureModel, labels, names, types, localLabels, enums, structs, newUnions)

  private def add(using featureExpr: FeatureExpr)
                 (map: Map[String, FeatureExpr], name: String): Map[String, FeatureExpr] = map.updatedWith(name) {
    case Some(value) =>
      val e = value.and(featureExpr)
      if (e.isSatisfiable(featureModel))
        System.err.println(s"redefinition of '$name' under feature configuration: $e")
      Some(value.or(featureExpr))
    case None => Some(featureExpr)
  }

  private def whenUndefined(using featureExpr: FeatureExpr)
                           (map: Map[String, FeatureExpr], name: String): FeatureExpr = map.get(name) match {
    case Some(value) => featureExpr.andNot(value)
    case None => featureExpr
  }

  private def define(using FeatureExpr)(name: String): LocalUsageVisitor =
    updateNames(add(names, name))

  private def defineType(using FeatureExpr)(name: String): LocalUsageVisitor =
    updateTypes(add(types, name))

  private def defineLabel(using FeatureExpr)(name: String): LocalUsageVisitor =
    updateLabels(add(using whenUndefined(localLabels, name))(labels, name))

  private def defineLocalLabel(using FeatureExpr)(name: String): LocalUsageVisitor =
    updateLocalLabels(add(localLabels, name))

  private def defineEnum(using FeatureExpr)(name: String): LocalUsageVisitor =
    updateEnums(add(enums, name))

  private def defineStruct(using FeatureExpr)(name: String): LocalUsageVisitor =
    updateStructs(add(structs, name))

  private def defineUnion(using FeatureExpr)(name: String): LocalUsageVisitor =
    updateUnions(add(unions, name))

  override def use(using featureExpr: FeatureExpr)(name: String): UsageVisitor =
    if (featureExpr.isSatisfiable(featureModel))
      updateExtractor(extractor.use(using whenUndefined(names, name))(name))
    else this

  override def useType(using featureExpr: FeatureExpr)(name: String): UsageVisitor =
    if (featureExpr.isSatisfiable(featureModel))
      updateExtractor(extractor.useType(using whenUndefined(types, name))(name))
    else this

  override def useEnum(using featureExpr: FeatureExpr)(name: String): UsageVisitor =
    if (featureExpr.isSatisfiable(featureModel))
      updateExtractor(extractor.useEnum(using whenUndefined(enums, name))(name))
    else this

  override def useStruct(using featureExpr: FeatureExpr)(name: String): UsageVisitor =
    if (featureExpr.isSatisfiable(featureModel))
      updateExtractor(extractor.useStruct(using whenUndefined(structs, name))(name))
    else this

  override def useUnion(using featureExpr: FeatureExpr)(name: String): UsageVisitor =
    if (featureExpr.isSatisfiable(featureModel))
      updateExtractor(extractor.useUnion(using whenUndefined(unions, name))(name))
    else this

  override def enter(using trace: Trace)(compoundStatement: CompoundStatement): Visitor = trace.asts.head match {
    case _: CDef => this
    case _ => LocalUsageVisitor(this, featureModel, labels)
  }

  override def leave(using trace: Trace)(compoundStatement: CompoundStatement): Visitor = extractor match {
    case extractor: LocalUsageVisitor => extractor.updateLabels(labels)
    case _: GlobalUsageVisitor => extractor
    case _ => assert(false)
  }

  override def enter(using trace: Trace)(id: Id): Visitor = {
    given FeatureExpr = trace.condition

    val name = id.name
    trace.asts.head match {
      case _: PointerPostfixSuffix => this
      case _: LabelStatement => defineLabel(name)
      case _: TypeDefTypeSpecifier => useType(name)
      case _: AtomicNamedDeclarator =>
        trace.asts.tail.dropWhile(_.isInstanceOf[NestedNamedDeclarator]) match {
          case (_: InitDeclarator) :: d :: f :: _ => d match {
            case d: Declaration => f match {
              case _: CDef => this // old style parameter declaration
              case _: DeclarationStatement =>
                val g = d.declSpecs.foldLeft(FeatureExprFactory.False)((e, o) =>
                  if (o.entry.isInstanceOf[TypedefSpecifier]) {
                    val f = trace.condition.and(e.and(o.condition))
                    if (f.isSatisfiable(featureModel))
                      System.err.println(s"duplicate 'typedef' under feature configuration: $f")
                    e.or(o.condition)
                  } else e)
                defineType(using trace.condition.and(g))(name).define(using trace.condition.andNot(g))(name)
              case _: TranslationUnit => assert(false)
              case _ => assert(false)
            }
            case _: TypelessDeclaration => assert(false)
            case _ => assert(false)
          }
          case (_: NestedNamedDeclarator) :: _ => assert(false)
          case (_: ParameterDeclarationD) :: c :: d :: e :: _ =>
            assert(c.isInstanceOf[DeclParameterDeclList])
            d match {
              case _: AtomicNamedDeclarator => e match {
                case _: CDef => define(name)
                case _: InitDeclarator => this // function declaration
                case _ => this
              }
              case _: NestedNamedDeclarator => this
              case _: AbstractDeclarator => this
              case _ => assert(false)
            }
          case (_: StructDeclarator) :: _ => this
          case (_: FunctionDef) :: _ => assert(false)
          case (_: NestedFunctionDef) :: _ => LocalUsageVisitor(define(name), featureModel)
          case _ => assert(false)
        }
      case _: Enumerator => define(name)
      case _: InitializerDesignatorC => this
      case _: InitializerDesignatorD => this
      case _: OffsetofMemberDesignatorID => this
      case _: LocalLabelDeclaration => defineLocalLabel(name)
      case _: DeclIdentifierList => define(name)
      case e: EnumSpecifier => if (e.enumerators.isEmpty) useEnum(name) else defineEnum(name)
      case s: StructOrUnionSpecifier =>
        if (s.enumerators.isEmpty) if (s.isUnion) useUnion(name) else useStruct(name)
        else if (s.isUnion) defineUnion(name) else defineStruct(name)
      case _ => use(name)
    }
  }
}