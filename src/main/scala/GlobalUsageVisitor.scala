import de.fosd.typechef.conditional.Opt
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.*

import scala.annotation.tailrec

class GlobalUsageVisitor(val featureModel: FeatureModel,
                         val names: Map[String, FeatureExpr] = Map(),
                         val types: Map[String, FeatureExpr] = Map(),
                         val enums: Map[String, FeatureExpr] = Map(),
                         val structs: Map[String, FeatureExpr] = Map(),
                         val unions: Map[String, FeatureExpr] = Map(),
                         val nameUsages: Map[String, FeatureExpr] = Map(),
                         val typeUsages: Map[String, FeatureExpr] = Map(),
                         val enumUsages: Map[String, FeatureExpr] = Map(),
                         val structUsages: Map[String, FeatureExpr] = Map(),
                         val unionUsages: Map[String, FeatureExpr] = Map(),
                        ) extends UsageVisitor {

  private def updateNames(newNames: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, newNames, types, enums, structs, unions,
      nameUsages, typeUsages, enumUsages, structUsages, unionUsages)

  private def updateTypes(newTypes: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, newTypes, enums, structs, unions,
      nameUsages, typeUsages, enumUsages, structUsages, unionUsages)

  private def updateEnums(newEnums: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, types, newEnums, structs, unions,
      nameUsages, typeUsages, enumUsages, structUsages, unionUsages)

  private def updateStructs(newStructs: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, types, enums, newStructs, unions,
      nameUsages, typeUsages, enumUsages, structUsages, unionUsages)

  private def updateUnions(newUnions: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, types, enums, structs, newUnions,
      nameUsages, typeUsages, enumUsages, structUsages, unionUsages)

  private def updateNameUsages(newNameUsages: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, types, enums, structs, unions,
      newNameUsages, typeUsages, enumUsages, structUsages, unionUsages)

  private def updateTypeUsages(newTypeUsages: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, types, enums, structs, unions,
      nameUsages, newTypeUsages, enumUsages, structUsages, unionUsages)

  private def updateEnumUsages(newEnumUsages: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, types, enums, structs, unions,
      nameUsages, typeUsages, newEnumUsages, structUsages, unionUsages)

  private def updateStructUsages(newStructUsages: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, types, enums, structs, unions,
      nameUsages, typeUsages, enumUsages, newStructUsages, unionUsages)

  private def updateUnionUsages(newUnionUsages: Map[String, FeatureExpr]): GlobalUsageVisitor =
    GlobalUsageVisitor(featureModel, names, types, enums, structs, unions,
      nameUsages, typeUsages, enumUsages, structUsages, newUnionUsages)

  private def add(using featureExpr: FeatureExpr)
                 (map: Map[String, FeatureExpr], name: String): Map[String, FeatureExpr] = map.updatedWith(name) {
    case Some(value) => Some(value.or(featureExpr))
    case None => Some(featureExpr)
  }

  private def addUsage(using featureExpr: FeatureExpr)
                      (map: Map[String, FeatureExpr], name: String): Map[String, FeatureExpr] = map.updatedWith(name) {
    case Some(value) => Some(value.or(featureExpr))
    case None => Some(featureExpr)
  }

  private def define(using FeatureExpr)(name: String): GlobalUsageVisitor =
    updateNames(add(names, name))

  private def defineType(using FeatureExpr)(name: String): GlobalUsageVisitor =
    updateTypes(add(types, name))

  private def defineEnum(using FeatureExpr)(name: String): GlobalUsageVisitor =
    updateEnums(add(enums, name))

  private def defineStruct(using FeatureExpr)(name: String): GlobalUsageVisitor =
    updateStructs(add(structs, name))

  private def defineUnion(using FeatureExpr)(name: String): GlobalUsageVisitor =
    updateUnions(add(unions, name))

  override def use(using FeatureExpr)(name: String): UsageVisitor = updateNameUsages(addUsage(nameUsages, name))

  override def useType(using FeatureExpr)(name: String): UsageVisitor = updateTypeUsages(addUsage(typeUsages, name))

  override def useEnum(using FeatureExpr)(name: String): UsageVisitor = updateEnumUsages(addUsage(enumUsages, name))

  override def useStruct(using FeatureExpr)(name: String): UsageVisitor = updateStructUsages(addUsage(structUsages, name))

  override def useUnion(using FeatureExpr)(name: String): UsageVisitor = updateUnionUsages(addUsage(unionUsages, name))

  override def enter(using trace: Trace)(compoundStatement: CompoundStatement): Visitor = trace.asts.head match {
    case _: CDef => assert(false)
    case _: CompoundStatementExpr => LocalUsageVisitor(this, featureModel)
    case _ => assert(false)
  }

  override def leave(using trace: Trace)(compoundStatement: CompoundStatement): Visitor = assert(false)

  override def enter(using trace: Trace)(id: Id): Visitor = {
    given FeatureExpr = trace.condition

    val name = id.name
    trace.asts.head match {
      case _: PointerPostfixSuffix => this // may appear in expressions
      case _: LabelStatement => assert(false)
      case _: TypeDefTypeSpecifier => useType(name)
      case _: AtomicNamedDeclarator =>
        trace.asts.tail.dropWhile(_.isInstanceOf[NestedNamedDeclarator]) match {
          case (_: InitDeclarator) :: tail =>
            tail match {
              case (d: Declaration) :: tail =>
                tail match {
                  case (_: CDef) :: _ => this // old style parameter declaration
                  case (_: DeclarationStatement) :: _ => assert(false)
                  case (_: TranslationUnit) :: _ | Nil =>
                    val g = d.declSpecs.foldLeft(FeatureExprFactory.False)((e, o) =>
                      if (o.entry.isInstanceOf[TypedefSpecifier]) {
                        val f = trace.condition.and(e.and(o.condition))
                        if (f.isSatisfiable(featureModel))
                          System.err.println(s"duplicate 'typedef' under feature configuration: $f")
                        e.or(o.condition)
                      } else e)
                    defineType(using trace.condition.and(g))(name).define(using trace.condition.andNot(g))(name)
                  case _ => assert(false)
                }
              case (_: TypelessDeclaration) :: _ => define(name)
              case _ => assert(false)
            }
          case (_: NestedNamedDeclarator) :: _ => assert(false)
          case (_: ParameterDeclarationD) :: c :: d :: e :: _ =>
            assert(c.isInstanceOf[DeclParameterDeclList])
            d match {
              case _: AtomicNamedDeclarator => e match {
                case _: CDef => assert(false)
                case _: InitDeclarator => this // parameters in function declaration
                case _ => assert(false)
              }
              case _: NestedNamedDeclarator => this
              case _: AbstractDeclarator => this
              case _ => assert(false)
            }
          case (_: StructDeclarator) :: _ => this // struct fields
          case (_: FunctionDef) :: _ => LocalUsageVisitor(define(name), featureModel)
          case (_: NestedFunctionDef) :: _ => assert(false)
          case _ => assert(false)
        }
      case _: Enumerator => define(name)
      case _: InitializerDesignatorC => this
      case _: InitializerDesignatorD => this
      case _: OffsetofMemberDesignatorID => this
      case _: LocalLabelDeclaration => assert(false)
      case _: DeclIdentifierList => define(name) // old style parameter declaration
      case e: EnumSpecifier => if (e.enumerators.isEmpty) useEnum(name) else defineEnum(name)
      case s: StructOrUnionSpecifier =>
        if (s.enumerators.isEmpty) if (s.isUnion) useUnion(name) else useStruct(name)
        else if (s.isUnion) defineUnion(name) else defineStruct(name)
      case _ => use(name)
    }
  }

  def apply(externalDef: Opt[ExternalDef], featureExpr: FeatureExpr = FeatureExprFactory.True): GlobalUsageVisitor =
    visit(using Trace(featureExpr))(externalDef).asInstanceOf[GlobalUsageVisitor]
}
