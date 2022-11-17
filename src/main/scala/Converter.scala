//import de.fosd.typechef.conditional.Opt
//import de.fosd.typechef.parser.c._
//
//class Converter {
//  type F[U] = U => U
//
////  given(using FeatureExpr): F[ExternalDef] = {
////    case FunctionDef(specifiers, declarator, oldStyleParameters, stmt) =>
////      FunctionDef(v(specifiers), v(declarator), v(oldStyleParameters), v(stmt))
////    case declaration: Declaration => v(declaration)
////    case AsmExpr(isVolatile, expr) => AsmExpr(isVolatile, v(expr))
////    case externalDef: EmptyExternalDef => externalDef
////    case Pragma(command) => Pragma(v(command))
////    case TypelessDeclaration(declList) => TypelessDeclaration(v(declList))
////  }
//
////  given(using FeatureExpr): F[Specifier] = {
////    case specifier: TypeSpecifier => specifier match
////      case specifier: PrimitiveTypeSpecifier => specifier
////      case OtherPrimitiveTypeSpecifier(_) => specifier
////      case TypeDefTypeSpecifier(_) => specifier
////      case SignedSpecifier() => specifier
////      case UnsignedSpecifier() => specifier
////      case EnumSpecifier(id, enumerators) => EnumSpecifier(id, visit(enumerators))
////      case StructOrUnionSpecifier(isUnion, id, enumerators, attributesBeforeBody, attributesAfterBody) =>
////        StructOrUnionSpecifier(isUnion, id, visit(enumerators), visit(attributesBeforeBody), visit(attributesAfterBody))
////      case TypeOfSpecifierT(typeName) => TypeOfSpecifierT(visit(typeName))
////      case TypeOfSpecifierU(expr) => TypeOfSpecifierU(visit(expr))
////    case specifier: OtherSpecifier => specifier
////    case specifier: TypedefSpecifier => specifier
////    case specifier: AttributeSpecifier => visit(specifier)
////  }
////
////  given(using FeatureExpr): F[Expr] = {
////    case expr: PrimaryExpr =>
////      expr match
////        case Id(_) => expr
////        case Constant(_) => expr
////        case stringLit: StringLit => visit(stringLit)
////        case BuiltinOffsetof(typeName, offsetofMemberDesignator) => BuiltinOffsetof(visit(typeName), visit(offsetofMemberDesignator))
////        case BuiltinTypesCompatible(typeName1, typeName2) => BuiltinTypesCompatible(visit(typeName1), visit(typeName2))
////        case BuiltinVaArgs(expr, typeName) => BuiltinVaArgs(visit(expr), visit(typeName))
////        case CompoundStatementExpr(compoundStatement) => CompoundStatementExpr(visit(compoundStatement))
////    case PostfixExpr(p, s) => PostfixExpr(visit(p), visit(s))
////    case UnaryExpr(_, e) => visit(e)
////    case SizeOfExprT(typeName) => visit(typeName)
////    case SizeOfExprU(expr) => visit(expr)
////    case CastExpr(typeName, expr) => visit(typeName) + visit(expr)
////    case PointerDerefExpr(castExpr) => visit(castExpr)
////    case PointerCreationExpr(castExpr) => visit(castExpr)
////    case UnaryOpExpr(_, castExpr) => visit(castExpr)
////    case NAryExpr(e, others) => visit(e) + visit(others)
////    case ConditionalExpr(condition, thenExpr, elseExpr) => visit(condition) + visit(thenExpr) + visit(elseExpr)
////    case AssignExpr(target, _, source) => visit(target) + visit(source)
////    case ExprList(exprs) => visit(exprs)
////    case LcurlyInitializer(inits) => visit(inits)
////    case AlignOfExprT(typeName) => visit(typeName)
////    case AlignOfExprU(expr) => visit(expr)
////    case GnuAsmExpr(_, _, expr, _) => visit(expr)
////    case RangeExpr(from, to) => visit(from) + visit(to)
////  }
////
////  given(using FeatureExpr): F[ExprList] = exprList => visit(exprList.exprs)
////
////  given(using FeatureExpr): F[AttributeSpecifier] = {
////    case GnuAttributeSpecifier(attributeList) => visit(attributeList)
////    case AsmAttributeSpecifier(stringConst) => visit(stringConst)
////  }
////
////  given(using FeatureExpr): F[Enumerator] = enumerator => visit(enumerator.assignment)
////
////  given(using FeatureExpr): F[StructDeclaration] = structDeclaration =>
////    visit(structDeclaration.qualifierList) + visit(structDeclaration.declaratorList)
////
////  given(using FeatureExpr): F[StructDecl] = {
////    case StructDeclarator(decl, initializer, attributes) => visit(decl) + visit(initializer) + visit(attributes)
////    case StructInitializer(expr, attributes) => visit(expr) + visit(attributes)
////  }
////
////  given(using FeatureExpr): F[TypeName] = typeName => visit(typeName.decl) + visit(typeName.specifiers)
////
////  given(using FeatureExpr): F[AbstractDeclarator] = {
////    case AtomicAbstractDeclarator(pointers, extensions) => visit(pointers) + visit(extensions)
////    case NestedAbstractDeclarator(pointers, nestedDecl, extensions, attr) =>
////      visit(pointers) + visit(nestedDecl) + visit(extensions) + visit(attr)
////  }
////
////  given(using FeatureExpr): F[DeclaratorAbstrExtension] = {
////    case DeclParameterDeclList(parameterDecls) => visit(parameterDecls)
////    case DeclArrayAccess(expr) => visit(expr)
////  }
////
////  given(using FeatureExpr): F[AttributeSequence] = attributeSequence => visit(attributeSequence.attributes)
////
////  given(using FeatureExpr): F[Attribute] = {
////    case AtomicAttribute(_) => default()
////    case CompoundAttribute(inner) => visit(inner)
////  }
////
////  given(using FeatureExpr): F[StringLit] = stringLit => visit(stringLit.name)
////
////  given(using FeatureExpr): F[String] = _ => default()
////
////  given(using FeatureExpr): F[Declarator] = declarator =>
////    visit(declarator.pointers) + visit(declarator.extensions)
////
////  given(using FeatureExpr): F[Pointer] = pointer => visit(pointer.specifier)
////
////  given(using FeatureExpr): F[DeclaratorExtension] = {
////    case extension: DeclaratorAbstrExtension => visit(extension)
////    case DeclIdentifierList(_) => default()
////  }
////
////  given(using FeatureExpr): F[ParameterDeclaration] = {
////    case PlainParameterDeclaration(specifiers, attr) => visit(specifiers) + visit(attr)
////    case ParameterDeclarationD(specifiers, decl, attr) => visit(specifiers) + visit(decl) + visit(attr)
////    case ParameterDeclarationAD(specifiers, decl, attr) => visit(specifiers) + visit(decl) + visit(attr)
////    case VarArgs() => default()
////  }
////
////  given(using FeatureExpr): F[OldParameterDeclaration] = {
////    case Declaration(declSpecs, init) => visit(declSpecs) + visit(init)
////    case VarArgs() => default()
////    case _ => default()
////  }
////
////  given(using FeatureExpr): F[InitDeclarator] = {
////    case InitDeclaratorI(declarator, attributes, i) => visit(declarator) + visit(attributes) + visit(i)
////    case InitDeclaratorE(declarator, attributes, e) => visit(declarator) + visit(attributes) + visit(e)
////  }
////
////  given(using FeatureExpr): F[Initializer] = i => visit(i.initializerElementLabel) + visit(i.expr)
////
////  given(using FeatureExpr): F[InitializerElementLabel] = {
////    case InitializerArrayDesignator(expr) => visit(expr)
////    case InitializerDesignatorC(_) => default()
////    case InitializerDesignatorD(_) => default()
////    case InitializerAssigment(designators) => visit(designators)
////  }
////
////  given(using FeatureExpr): F[CompoundStatement] = compoundStatement => visit(compoundStatement.innerStatements)
////
////  given(using FeatureExpr): F[Statement] = {
////    case CompoundStatement(innerStatements) => visit(innerStatements)
////    case EmptyStatement() => default()
////    case ExprStatement(expr) => visit(expr)
////    case WhileStatement(expr, s) => visit(expr) + visit(s)
////    case DoStatement(expr, s) => visit(expr) + visit(s)
////    case ForStatement(expr1, expr2, expr3, s) => visit(expr1) + visit(expr2) + visit(expr3) + visit(s)
////    case GotoStatement(target) => visit(target)
////    case ContinueStatement() => default()
////    case BreakStatement() => default()
////    case ReturnStatement(expr) => visit(expr)
////    case LabelStatement(_, attribute) => visit(attribute)
////    case CaseStatement(c) => visit(c)
////    case DefaultStatement() => default()
////    case IfStatement(condition, thenBranch, elifs, elseBranch) =>
////      visit(condition) + visit(thenBranch) + visit(elifs) + visit(elseBranch)
////    case SwitchStatement(expr, s) =>
////      visit(expr) + visit(s)
////    case declaration: CompoundDeclaration => declaration match
////      case DeclarationStatement(decl) => visit(decl)
////      case LocalLabelDeclaration(ids) => visit(ids)
////      case NestedFunctionDef(_, specifiers, declarator, parameters, stmt) =>
////        visit(specifiers) + visit(declarator) + visit(parameters) + visit(stmt)
////  }
////
////  given(using FeatureExpr): F[ElifStatement] = elif =>
////    visit(elif.condition) + visit(elif.thenBranch)
////
////  given(using FeatureExpr): F[Declaration] = declaration =>
////    visit(declaration.declSpecs) + visit(declaration.init)
////
////  given(using FeatureExpr): F[OffsetofMemberDesignator] = {
////    case OffsetofMemberDesignatorID(_) => default()
////    case OffsetofMemberDesignatorExpr(expr) => visit(expr)
////  }
////
////  given(using FeatureExpr): F[PostfixSuffix] = {
////    case SimplePostfixSuffix(_) => default()
////    case PointerPostfixSuffix(_, _) => default()
////    case FunctionCall(params) => visit(params)
////    case ArrayAccess(expr) => visit(expr)
////  }
////
////  given(using FeatureExpr): F[NArySubExpr] = nArySubExpr => visit(nArySubExpr.e)
////
////  given optVisitor[U] (using presenceCondition: FeatureExpr)(using FeatureExpr ?=> F[U]): F[Opt[U]]
////
////  given conditionalVisitor[U] (using presenceCondition: FeatureExpr)(using FeatureExpr ?=> F[U]): F[Conditional[U]]
////
////  given[U](using FeatureExpr, FeatureExpr ?=> F[U]): F[IterableOnce[U]] =
////    _.iterator.map(visit).fold(default())(_ + _)
////
////  def visit[U](using FeatureExpr)(t: U)(using visitor: FeatureExpr ?=> F[U]): T = visitor(t)
////
////  def apply(opt: Opt[ExternalDef]): T = visit(using opt.condition)(opt.entry)
//}
