import de.fosd.typechef.parser.c.*
import de.fosd.typechef.conditional.{One, Opt}
import de.fosd.typechef.featureexpr.sat.{BinaryLogicConnective, *}
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory, FeatureModel}
import org.apache.commons.lang3.NotImplementedException

class Superposer(val featureModel: FeatureModel) {
  def OptTrue[T](t: T): Opt[T] = {
    Opt(FeatureExprFactory.True, t)
  }

  def convertClausesToExpr(clauses: Set[SATFeatureExpr], operator: String): Expr = {
    if (clauses.size == 1) {
      convertFeatureExpr(clauses.head)
    } else {
      NAryExpr(convertFeatureExpr(clauses.head), clauses.tail.map(c =>
        OptTrue(NArySubExpr(operator, convertFeatureExpr(c)))
      ).toList)
    }
  }

  def convertFeatureExpr(featureExpr: FeatureExpr): Expr = {
    featureExpr match {
      case expr: SATFeatureExpr => expr match {
        case And(clauses) => convertClausesToExpr(clauses, "&&")
        case Or(clauses) => convertClausesToExpr(clauses, "||")
        case Not(clause) => UnaryOpExpr("!", convertFeatureExpr(clause))
        case d: DefinedExternal => Id(d.feature)
        case _ => throw NotImplementedException()
      }
      case _ => assert(false)
    }
  }

  def convertCompoundStatement(compoundStatement: CompoundStatement): CompoundStatement = {
    val (optStatements, optDeclarationStatements) = compoundStatement.innerStatements
      .foldLeft(List[Opt[Statement]](), List[Opt[DeclarationStatement]]())((acc, optStatement) => {
        val (optStatements, optDeclarationStatements) = optStatement.entry match {
          case statement: CompoundStatement =>
            val compoundStatement = convertCompoundStatement(statement)
            (List(OptTrue(compoundStatement)), List())
          case statement: EmptyStatement => (List(OptTrue(statement)), List())
          case statement: ExprStatement => (List(OptTrue(statement)), List())
          case statement: WhileStatement => (List(OptTrue(statement)), List())
          case statement: DoStatement => (List(OptTrue(statement)), List())
          case statement: ForStatement => (List(OptTrue(statement)), List())
          case statement: GotoStatement => (List(OptTrue(statement)), List())
          case statement: ContinueStatement => (List(OptTrue(statement)), List())
          case statement: BreakStatement => (List(OptTrue(statement)), List())
          case statement: ReturnStatement => (List(OptTrue(statement)), List())
          case statement: LabelStatement => (List(OptTrue(statement)), List())
          case statement: CaseStatement => (List(OptTrue(statement)), List())
          case statement: DefaultStatement => (List(OptTrue(statement)), List())
          case statement: IfStatement => (List(OptTrue(statement)), List())
          case statement: SwitchStatement => (List(OptTrue(statement)), List())
          case declaration: CompoundDeclaration => declaration match {
            case DeclarationStatement(decl) =>
              if (decl.declSpecs.exists(_.entry.isInstanceOf[TypedefSpecifier])) {
                (List(OptTrue(declaration)), List())
              } else {
                decl.init.foldLeft(
                  List[Opt[Statement]](), List[Opt[DeclarationStatement]]()
                )((acc, optInitDeclarator) => (
                  optInitDeclarator.entry.getExpr.map(
                    AssignExpr(Id(optInitDeclarator.entry.getName), "=", _)
                  ) match {
                    case Some(a) =>
                      acc._1 :+ OptTrue(if (optInitDeclarator.condition == FeatureExprFactory.True) {
                        ExprStatement(a)
                      } else {
                        IfStatement(
                          One(convertFeatureExpr(optInitDeclarator.condition)),
                          One(ExprStatement(a)), List(), None
                        )
                      })
                    case None => acc._1
                  },
                  acc._2 :+ OptTrue(DeclarationStatement(Declaration(decl.declSpecs, List(OptTrue(InitDeclaratorI(
                    optInitDeclarator.entry.declarator, optInitDeclarator.entry.attributes, None
                  ))))))
                ))
              }
            case declaration: LocalLabelDeclaration => (List(OptTrue(declaration)), List())
            case functionDef: NestedFunctionDef => (List(OptTrue(functionDef)), List())
          }
        }
        if (optStatement.condition == FeatureExprFactory.True) {
          (acc._1 ++ optStatements, acc._2 ++ optDeclarationStatements)
        } else {
          (
            acc._1 :+ OptTrue(IfStatement(
              One(convertFeatureExpr(optStatement.condition)),
              One(CompoundStatement(optStatements)), List(), None
            )),
            acc._2 ++ optDeclarationStatements
          )
        }
      }
      )
    CompoundStatement(optDeclarationStatements ++ optStatements)
  }

  def convertFunctionDeclarator(declarator: Declarator, features: Set[String]): Declarator = {
    declarator match {
      case AtomicNamedDeclarator(pointers, id, extensions) =>
        AtomicNamedDeclarator(pointers, id, extensions.map(optDeclaratorExtension =>
          OptTrue(optDeclaratorExtension.entry match {
            case extension: DeclaratorAbstrExtension => extension match {
              case DeclParameterDeclList(parameterDecls) => DeclParameterDeclList(parameterDecls ++ features.map(s =>
                OptTrue(ParameterDeclarationD(
                  List(OptTrue(OtherPrimitiveTypeSpecifier("_Bool"))),
                  AtomicNamedDeclarator(List(), Id(s), List()),
                  List()
                ))
              ))
              case _: DeclArrayAccess => throw NotImplementedException()
            }
            case DeclIdentifierList(idList) => DeclIdentifierList(idList ++ features.map(s => OptTrue(Id(s))))
          })
        ))
      case _: NestedNamedDeclarator => throw NotImplementedException()
    }
  }

  def apply(translationUnit: TranslationUnit, features: Set[String]): TranslationUnit = {
    TranslationUnit(translationUnit.defs.map(e => e.entry match {
      case _: Declaration => OptTrue(e.entry)
      case _: AsmExpr => OptTrue(e.entry)
      case FunctionDef(specifiers, declarator, oldStyleParameters, stmt) =>
        //        FunctionDef(specifiers, convertFunctionDeclarator(declarator, features),
        //          if (oldStyleParameters.isEmpty) {
        //            oldStyleParameters
        //          } else {
        //            oldStyleParameters ++ features.map(s => OptTrue(Declaration(
        //              List(OptTrue(OtherPrimitiveTypeSpecifier("_Bool"))),
        //              List(OptTrue(InitDeclaratorI(AtomicNamedDeclarator(List(), Id(s), List()), List(), None)))
        //            )))
        //          },
        //          compoundStatement
        //        )
        OptTrue(FunctionDef(specifiers, declarator, oldStyleParameters, convertCompoundStatement(stmt)))
      case _: EmptyExternalDef => OptTrue(e.entry)
      case _: TypelessDeclaration => OptTrue(e.entry)
      case _: Pragma => OptTrue(e.entry)
    }))
  }
}

//object Superposer {
//  def main(args: Array[String]): Unit = {
//    val (translationUnit, featureModel, _) = Util.parse(Array("examples/toy/new.c"))
//    println("-------")
//    Util.print(Superposer(featureModel)(translationUnit))
//  }
//}
