import de.fosd.typechef.conditional.{Choice, Conditional, One, Opt}
import de.fosd.typechef.featureexpr.sat.*
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory, FeatureModel}
import de.fosd.typechef.parser.c.*
import org.apache.commons.lang3.NotImplementedException

class Superposer(val featureModel: FeatureModel) {
  def convertClausesToExpr(clauses: Set[SATFeatureExpr], operator: String): Expr = {
    if (clauses.isEmpty) {
      if (operator == "&&") {
        Constant("1")
      } else if (operator == "||") {
        Constant("0")
      } else {
        assert(false)
      }
    } else if (clauses.size == 1) {
      convertFeatureExpr(clauses.head)
    } else {
      NAryExpr(convertFeatureExpr(clauses.head), clauses.tail.map(c =>
        Util.optTrue(NArySubExpr(operator, convertFeatureExpr(c)))
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

  def convertStringLitName(name: List[Opt[String]], name0: List[Opt[String]]): Expr = {
    if (name.isEmpty) {
      StringLit(name0)
    } else if (name.head.condition == FeatureExprFactory.True) {
      convertStringLitName(name.tail, name0 :+ name.head)
    } else {
      ConditionalExpr(
        convertFeatureExpr(name.head.condition),
        Some(convertStringLitName(name.tail, name0 :+ Util.optTrue(name.head.entry))),
        convertStringLitName(name.tail, name0)
      )
    }
  }

  def convertExpr(expr: Expr): Expr = {
    expr match {
      case expr: PrimaryExpr =>
        expr match {
          case id: Id => id
          case constant: Constant => constant
          case StringLit(name) => convertStringLitName(name, List())
          case BuiltinOffsetof(typeName, offsetofMemberDesignator) => ???
          case BuiltinTypesCompatible(typeName1, typeName2) => ???
          case BuiltinVaArgs(expr, typeName) => ???
          case CompoundStatementExpr(compoundStatement) => ???
        }
      case PostfixExpr(p, s) => ???
      case UnaryExpr(kind, e) => ???
      case SizeOfExprT(typeName) => ???
      case SizeOfExprU(expr) => ???
      case CastExpr(typeName, expr) => ???
      case PointerDerefExpr(castExpr) => ???
      case PointerCreationExpr(castExpr) => ???
      case UnaryOpExpr(kind, castExpr) => ???
      case NAryExpr(e, others) => ???
      case ConditionalExpr(condition, thenExpr, elseExpr) => ???
      case AssignExpr(target, operation, source) => ???
      case ExprList(exprs) => ???
      case LcurlyInitializer(inits) => ???
      case AlignOfExprT(typeName) => ???
      case AlignOfExprU(expr) => ???
      case GnuAsmExpr(isVolatile, isGoto, expr, stuff) => ???
      case RangeExpr(from, to) => ???
    }
  }

  def convertConditionalStatement(conditionalStatement: Conditional[Statement]): One[Statement] = {
    conditionalStatement match {
      case Choice(condition, thenBranch, elseBranch) =>
        One(IfStatement(
          One(convertFeatureExpr(condition)),
          convertConditionalStatement(thenBranch),
          List(),
          Some(convertConditionalStatement(elseBranch))
        ))
      case One(value) =>
        value match {
          case statement: CompoundStatement => One(convertCompoundStatement(statement))
          case _ => One(convertCompoundStatement(CompoundStatement(List(Util.optTrue(value)))))
        }
    }
  }

  def convertConditionalExpr(conditionalExpr: Conditional[Expr]): Expr = {
    conditionalExpr match {
      case Choice(condition, thenBranch, elseBranch) =>
        if (condition == FeatureExprFactory.True) {
          convertConditionalExpr(thenBranch)
        } else {
          ConditionalExpr(
            convertFeatureExpr(condition),
            Some(convertConditionalExpr(thenBranch)),
            convertConditionalExpr(elseBranch)
          )
        }
      case One(value) => value
    }
  }

  def convertCompoundStatement(compoundStatement: CompoundStatement): CompoundStatement = {
    val (optStatements, optDeclarationStatements) = compoundStatement.innerStatements
      .foldLeft(List[Opt[Statement]](), List[Opt[DeclarationStatement]]())((acc, optStatement) => {
        val (optStatements, optDeclarationStatements) = optStatement.entry match {
          case statement: CompoundStatement =>
            val compoundStatement = convertCompoundStatement(statement)
            (List(Util.optTrue(compoundStatement)), List())
          case statement: EmptyStatement => (List(Util.optTrue(statement)), List())
          case statement: ExprStatement => (List(Util.optTrue(statement)), List())
          case WhileStatement(expr, s) =>
            (List(Util.optTrue(WhileStatement(expr, convertConditionalStatement(s)))), List())
          case DoStatement(expr, s) =>
            (List(Util.optTrue(DoStatement(expr, convertConditionalStatement(s)))), List())
          case ForStatement(expr1, expr2, expr3, s) =>
            (List(Util.optTrue(ForStatement(expr1, expr2, expr3, convertConditionalStatement(s)))), List())
          case statement: GotoStatement => (List(Util.optTrue(statement)), List())
          case statement: ContinueStatement => (List(Util.optTrue(statement)), List())
          case statement: BreakStatement => (List(Util.optTrue(statement)), List())
          case statement: ReturnStatement => (List(Util.optTrue(statement)), List())
          case statement: LabelStatement => (List(Util.optTrue(statement)), List())
          case statement: CaseStatement => (List(Util.optTrue(statement)), List())
          case statement: DefaultStatement => (List(Util.optTrue(statement)), List())
          case IfStatement(condition, thenBranch, elifs, elseBranch) =>
            (
              List(Util.optTrue(IfStatement(
                condition,
                convertConditionalStatement(thenBranch),
                elifs.map(elif => elif match
                  case Opt(condition, ElifStatement(condition1, thenBranch)) =>
                    Util.optTrue(ElifStatement(
                      One(NAryExpr(
                        convertFeatureExpr(condition),
                        List(Util.optTrue(NArySubExpr("&&", convertConditionalExpr(condition1))))
                      )),
                      convertConditionalStatement(thenBranch))
                    )),
                elseBranch.map(convertConditionalStatement)
              ))),
              List()
            )
          case SwitchStatement(expr, s) =>
            (List(Util.optTrue(SwitchStatement(expr, convertConditionalStatement(s)))), List())
          case declaration: CompoundDeclaration => declaration match {
            case DeclarationStatement(decl) =>
              if (decl.declSpecs.exists(_.entry.isInstanceOf[TypedefSpecifier])) {
                (List(Util.optTrue(declaration)), List())
              } else {
                decl.init.foldLeft(
                  List[Opt[Statement]](), List[Opt[DeclarationStatement]]()
                )((acc, optInitDeclarator) => (
                  optInitDeclarator.entry.getExpr.map(
                    AssignExpr(Id(optInitDeclarator.entry.getName), "=", _)
                  ) match {
                    case Some(a) =>
                      acc._1 :+ Util.optTrue(if (optInitDeclarator.condition == FeatureExprFactory.True) {
                        ExprStatement(a)
                      } else {
                        IfStatement(
                          One(convertFeatureExpr(optInitDeclarator.condition)),
                          One(ExprStatement(a)), List(), None
                        )
                      })
                    case None => acc._1
                  },
                  acc._2 :+ Util.optTrue(DeclarationStatement(Declaration(decl.declSpecs, List(Util.optTrue(InitDeclaratorI(
                    optInitDeclarator.entry.declarator, optInitDeclarator.entry.attributes, None
                  ))))))
                ))
              }
            case declaration: LocalLabelDeclaration => (List(Util.optTrue(declaration)), List())
            case functionDef: NestedFunctionDef => (List(Util.optTrue(functionDef)), List())
          }
        }
        if (optStatement.condition == FeatureExprFactory.True) {
          (acc._1 ++ optStatements, acc._2 ++ optDeclarationStatements)
        } else {
          (
            acc._1 :+ Util.optTrue(IfStatement(
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
          Util.optTrue(optDeclaratorExtension.entry match {
            case extension: DeclaratorAbstrExtension => extension match {
              case DeclParameterDeclList(parameterDecls) => DeclParameterDeclList(parameterDecls ++ features.map(s =>
                Util.optTrue(ParameterDeclarationD(
                  List(Util.optTrue(OtherPrimitiveTypeSpecifier("_Bool"))),
                  AtomicNamedDeclarator(List(), Id(s), List()),
                  List()
                ))
              ))
              case _: DeclArrayAccess => throw NotImplementedException()
            }
            case DeclIdentifierList(idList) => DeclIdentifierList(idList ++ features.map(s => Util.optTrue(Id(s))))
          })
        ))
      case _: NestedNamedDeclarator => throw NotImplementedException()
    }
  }

  def apply(translationUnit: TranslationUnit, features: Set[String]): TranslationUnit = {
    TranslationUnit(translationUnit.defs.map(e => e.entry match {
      case _: Declaration => Util.optTrue(e.entry)
      case _: AsmExpr => Util.optTrue(e.entry)
      case FunctionDef(specifiers, declarator, oldStyleParameters, stmt) =>
        Util.optTrue(FunctionDef(
          specifiers,
          convertFunctionDeclarator(declarator, features),
          if (oldStyleParameters.isEmpty) {
            oldStyleParameters
          } else {
            oldStyleParameters ++ features.map(s => Util.optTrue(Declaration(
              List(Util.optTrue(OtherPrimitiveTypeSpecifier("_Bool"))),
              List(Util.optTrue(InitDeclaratorI(AtomicNamedDeclarator(List(), Id(s), List()), List(), None)))
            )))
          },
          convertCompoundStatement(stmt)
        ))
      case _: EmptyExternalDef => Util.optTrue(e.entry)
      case _: TypelessDeclaration => Util.optTrue(e.entry)
      case _: Pragma => Util.optTrue(e.entry)
    }))
  }
}

object Superposer {
  def main(args: Array[String]): Unit = {
    val (translationUnit, featureModel, features) = Util.parse(Array("examples/coreutils.ls.6b01b71e/new.processed.bak.c"))
    println("-------")
    val superposition = Superposer(featureModel)(translationUnit, features)
    VariabilitySearcher(featureModel)(superposition)
    Util.print(superposition)
  }
}
