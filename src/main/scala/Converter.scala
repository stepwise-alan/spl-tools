import de.fosd.typechef.conditional.{Choice, Conditional, One, Opt}
import de.fosd.typechef.featureexpr.{FeatureExpr, FeatureExprFactory}
import de.fosd.typechef.parser.c.*

import scala.annotation.unused

//noinspection DuplicatedCode
trait Converter {
  type F[U] = Trace ?=> U => U

  def convert(using Trace)(x: Expr): Expr = x match {
    case y: PrimaryExpr =>
      convert(y)
    case y: PostfixExpr =>
      convert(y)
    case y: UnaryExpr =>
      convert(y)
    case y: SizeOfExprT =>
      convert(y)
    case y: SizeOfExprU =>
      convert(y)
    case y: CastExpr =>
      convert(y)
    case y: PointerDerefExpr =>
      convert(y)
    case y: PointerCreationExpr =>
      convert(y)
    case y: UnaryOpExpr =>
      convert(y)
    case y: NAryExpr =>
      convert(y)
    case y: ConditionalExpr =>
      convert(y)
    case y: AssignExpr =>
      convert(y)
    case y: ExprList =>
      convert(y)
    case y: LcurlyInitializer =>
      convert(y)
    case y: AlignOfExprT =>
      convert(y)
    case y: AlignOfExprU =>
      convert(y)
    case y: GnuAsmExpr =>
      convert(y)
    case y: RangeExpr =>
      convert(y)
  }

  def convert(using Trace)(x: PrimaryExpr): PrimaryExpr = x match {
    case y: Id =>
      convert(y)
    case y: Constant =>
      convert(y)
    case y: StringLit =>
      convert(y)
    case y: BuiltinOffsetof =>
      convert(y)
    case y: BuiltinTypesCompatible =>
      convert(y)
    case y: BuiltinVaArgs =>
      convert(y)
    case y: CompoundStatementExpr =>
      convert(y)
  }

  def convert(using trace: Trace)(x: Id): Id = x match {
    case Id(name) => given Trace = trace + x

      Id(convert(name))
  }

  def convert(using trace: Trace)(x: Constant): Constant = x match {
    case Constant(value) => given Trace = trace + x

      Constant(convert(value))
  }

  def convert(using trace: Trace)(x: StringLit): StringLit = x match {
    case StringLit(name) => given Trace = trace + x

      StringLit(convert(name))
  }

  def convert(using Trace)(x: PostfixSuffix): PostfixSuffix = x match {
    case y: SimplePostfixSuffix =>
      convert(y)
    case y: PointerPostfixSuffix =>
      convert(y)
    case y: FunctionCall =>
      convert(y)
    case y: ArrayAccess =>
      convert(y)
  }

  def convert(using trace: Trace)(x: SimplePostfixSuffix): SimplePostfixSuffix = x match {
    case SimplePostfixSuffix(t) => given Trace = trace + x

      SimplePostfixSuffix(convert(t))
  }

  def convert(using trace: Trace)(x: PointerPostfixSuffix): PointerPostfixSuffix = x match {
    case PointerPostfixSuffix(kind, id) => given Trace = trace + x

      PointerPostfixSuffix(convert(kind), convert(id))
  }

  def convert(using trace: Trace)(x: FunctionCall): FunctionCall = x match {
    case FunctionCall(params) => given Trace = trace + x

      FunctionCall(convert(params))
  }

  def convert(using trace: Trace)(x: ArrayAccess): ArrayAccess = x match {
    case ArrayAccess(expr) => given Trace = trace + x

      ArrayAccess(convert(expr))
  }

  def convert(using trace: Trace)(x: PostfixExpr): PostfixExpr = x match {
    case PostfixExpr(p, s) => given Trace = trace + x

      PostfixExpr(convert(p), convert(s))
  }

  def convert(using trace: Trace)(x: UnaryExpr): UnaryExpr = x match {
    case UnaryExpr(kind, e) => given Trace = trace + x

      UnaryExpr(convert(kind), convert(e))
  }

  def convert(using trace: Trace)(x: SizeOfExprT): SizeOfExprT = x match {
    case SizeOfExprT(typeName) => given Trace = trace + x

      SizeOfExprT(convert(typeName))
  }

  def convert(using trace: Trace)(x: SizeOfExprU): SizeOfExprU = x match {
    case SizeOfExprU(expr) => given Trace = trace + x

      SizeOfExprU(convert(expr))
  }

  def convert(using trace: Trace)(x: CastExpr): CastExpr = x match {
    case CastExpr(typeName, expr) => given Trace = trace + x

      CastExpr(convert(typeName), convert(expr))
  }

  def convert(using trace: Trace)(x: PointerDerefExpr): PointerDerefExpr = x match {
    case PointerDerefExpr(castExpr) => given Trace = trace + x

      PointerDerefExpr(convert(castExpr))
  }

  def convert(using trace: Trace)(x: PointerCreationExpr): PointerCreationExpr = x match {
    case PointerCreationExpr(castExpr) => given Trace = trace + x

      PointerCreationExpr(convert(castExpr))
  }

  def convert(using trace: Trace)(x: UnaryOpExpr): UnaryOpExpr = x match {
    case UnaryOpExpr(kind, castExpr) => given Trace = trace + x

      UnaryOpExpr(convert(kind), convert(castExpr))
  }

  def convert(using trace: Trace)(x: NAryExpr): NAryExpr = x match {
    case NAryExpr(e, others) => given Trace = trace + x

      NAryExpr(convert(e), convert(others))
  }

  def convert(using trace: Trace)(x: NArySubExpr): NArySubExpr = x match {
    case NArySubExpr(op, e) => given Trace = trace + x

      NArySubExpr(convert(op), convert(e))
  }

  def convert(using trace: Trace)(x: ConditionalExpr): ConditionalExpr = x match {
    case ConditionalExpr(condition, thenExpr, elseExpr) => given Trace = trace + x

      ConditionalExpr(convert(condition), convert(thenExpr), convert(elseExpr))
  }

  def convert(using trace: Trace)(x: AssignExpr): AssignExpr = x match {
    case AssignExpr(target, operation, source) => given Trace = trace + x

      AssignExpr(convert(target), convert(operation), convert(source))
  }

  def convert(using trace: Trace)(x: ExprList): ExprList = x match {
    case ExprList(exprs) => given Trace = trace + x

      ExprList(convert(exprs))
  }

  def convert(using Trace)(x: Statement): Statement = x match {
    case y: CompoundStatement =>
      convert(y)
    case y: EmptyStatement =>
      convert(y)
    case y: ExprStatement =>
      convert(y)
    case y: WhileStatement =>
      convert(y)
    case y: DoStatement =>
      convert(y)
    case y: ForStatement =>
      convert(y)
    case y: GotoStatement =>
      convert(y)
    case y: ContinueStatement =>
      convert(y)
    case y: BreakStatement =>
      convert(y)
    case y: ReturnStatement =>
      convert(y)
    case y: LabelStatement =>
      convert(y)
    case y: CaseStatement =>
      convert(y)
    case y: DefaultStatement =>
      convert(y)
    case y: IfStatement =>
      convert(y)
    case y: SwitchStatement =>
      convert(y)
    case y: CompoundDeclaration =>
      convert(y)
  }

  def convert(using trace: Trace)(x: CompoundStatement): CompoundStatement = x match {
    case CompoundStatement(innerStatements) => given Trace = trace + x

      CompoundStatement(convert(innerStatements))
  }

  def convert(using Trace)(x: EmptyStatement): EmptyStatement = x match {
    case EmptyStatement() => x
  }

  def convert(using trace: Trace)(x: ExprStatement): ExprStatement = x match {
    case ExprStatement(expr) => given Trace = trace + x

      ExprStatement(convert(expr))
  }

  def convert(using trace: Trace)(x: WhileStatement): WhileStatement = x match {
    case WhileStatement(expr, s) => given Trace = trace + x

      WhileStatement(convert(expr), convert(s))
  }

  def convert(using trace: Trace)(x: DoStatement): DoStatement = x match {
    case DoStatement(expr, s) => given Trace = trace + x

      DoStatement(convert(expr), convert(s))
  }

  def convert(using trace: Trace)(x: ForStatement): ForStatement = x match {
    case ForStatement(expr1, expr2, expr3, s) => given Trace = trace + x

      ForStatement(convert(expr1), convert(expr2), convert(expr3), convert(s))
  }

  def convert(using trace: Trace)(x: GotoStatement): GotoStatement = x match {
    case GotoStatement(target) => given Trace = trace + x

      GotoStatement(convert(target))
  }

  def convert(using Trace)(x: ContinueStatement): ContinueStatement = x match {
    case ContinueStatement() => x
  }

  def convert(using Trace)(x: BreakStatement): BreakStatement = x match {
    case BreakStatement() => x
  }

  def convert(using trace: Trace)(x: ReturnStatement): ReturnStatement = x match {
    case ReturnStatement(expr) => given Trace = trace + x

      ReturnStatement(convert(expr))
  }

  def convert(using trace: Trace)(x: LabelStatement): LabelStatement = x match {
    case LabelStatement(id, attribute) => given Trace = trace + x

      LabelStatement(convert(id), convert(attribute))
  }

  def convert(using trace: Trace)(x: CaseStatement): CaseStatement = x match {
    case CaseStatement(c) => given Trace = trace + x

      CaseStatement(convert(c))
  }

  def convert(using Trace)(x: DefaultStatement): DefaultStatement = x match {
    case DefaultStatement() => x
  }

  def convert(using trace: Trace)(x: IfStatement): IfStatement = x match {
    case IfStatement(condition, thenBranch, elifs, elseBranch) => given Trace = trace + x

      IfStatement(convert(condition), convert(thenBranch), convert(elifs), convert(elseBranch))
  }

  def convert(using trace: Trace)(x: ElifStatement): ElifStatement = x match {
    case ElifStatement(condition, thenBranch) => given Trace = trace + x

      ElifStatement(convert(condition), convert(thenBranch))
  }

  def convert(using trace: Trace)(x: SwitchStatement): SwitchStatement = x match {
    case SwitchStatement(expr, s) => given Trace = trace + x

      SwitchStatement(convert(expr), convert(s))
  }

  def convert(using Trace)(x: CompoundDeclaration): CompoundDeclaration = x match {
    case y: DeclarationStatement =>
      convert(y)
    case y: LocalLabelDeclaration =>
      convert(y)
    case y: NestedFunctionDef =>
      convert(y)
  }

  def convert(using trace: Trace)(x: DeclarationStatement): DeclarationStatement = x match {
    case DeclarationStatement(decl) => given Trace = trace + x

      DeclarationStatement(convert(decl))
  }

  def convert(using trace: Trace)(x: LocalLabelDeclaration): LocalLabelDeclaration = x match {
    case LocalLabelDeclaration(ids) => given Trace = trace + x

      LocalLabelDeclaration(convert(ids))
  }

  def convert(using Trace)(x: Specifier): Specifier = x match {
    case y: TypeSpecifier =>
      convert(y)
    case y: OtherSpecifier =>
      convert(y)
    case y: TypedefSpecifier =>
      convert(y)
    case y: AttributeSpecifier =>
      convert(y)
  }

  def convert(using Trace)(x: TypeSpecifier): TypeSpecifier = x match {
    case y: PrimitiveTypeSpecifier =>
      convert(y)
    case y: OtherPrimitiveTypeSpecifier =>
      convert(y)
    case y: TypeDefTypeSpecifier =>
      convert(y)
    case y: SignedSpecifier =>
      convert(y)
    case y: UnsignedSpecifier =>
      convert(y)
    case y: EnumSpecifier =>
      convert(y)
    case y: StructOrUnionSpecifier =>
      convert(y)
    case y: TypeOfSpecifierT =>
      convert(y)
    case y: TypeOfSpecifierU =>
      convert(y)
  }

  def convert(using Trace)(x: PrimitiveTypeSpecifier): PrimitiveTypeSpecifier = x match {
    case y: VoidSpecifier =>
      convert(y)
    case y: ShortSpecifier =>
      convert(y)
    case y: IntSpecifier =>
      convert(y)
    case y: FloatSpecifier =>
      convert(y)
    case y: DoubleSpecifier =>
      convert(y)
    case y: LongSpecifier =>
      convert(y)
    case y: Int128Specifier =>
      convert(y)
    case y: CharSpecifier =>
      convert(y)
  }

  def convert(using Trace)(x: OtherSpecifier): OtherSpecifier = x match {
    case y: InlineSpecifier =>
      convert(y)
    case y: AutoSpecifier =>
      convert(y)
    case y: RegisterSpecifier =>
      convert(y)
    case y: VolatileSpecifier =>
      convert(y)
    case y: ExternSpecifier =>
      convert(y)
    case y: ConstSpecifier =>
      convert(y)
    case y: RestrictSpecifier =>
      convert(y)
    case y: ThreadSpecifier =>
      convert(y)
    case y: StaticSpecifier =>
      convert(y)
  }

  def convert(using trace: Trace)(x: OtherPrimitiveTypeSpecifier): OtherPrimitiveTypeSpecifier = x match {
    case OtherPrimitiveTypeSpecifier(typeName) => given Trace = trace + x

      OtherPrimitiveTypeSpecifier(convert(typeName))
  }

  def convert(using Trace)(x: VoidSpecifier): VoidSpecifier = x match {
    case VoidSpecifier() => x
  }

  def convert(using Trace)(x: ShortSpecifier): ShortSpecifier = x match {
    case ShortSpecifier() => x
  }

  def convert(using Trace)(x: IntSpecifier): IntSpecifier = x match {
    case IntSpecifier() => x
  }

  def convert(using Trace)(x: FloatSpecifier): FloatSpecifier = x match {
    case FloatSpecifier() => x
  }

  def convert(using Trace)(x: DoubleSpecifier): DoubleSpecifier = x match {
    case DoubleSpecifier() => x
  }

  def convert(using Trace)(x: LongSpecifier): LongSpecifier = x match {
    case LongSpecifier() => x
  }

  def convert(using Trace)(x: Int128Specifier): Int128Specifier = x match {
    case Int128Specifier() => x
  }

  def convert(using Trace)(x: CharSpecifier): CharSpecifier = x match {
    case CharSpecifier() => x
  }

  def convert(using Trace)(x: TypedefSpecifier): TypedefSpecifier = x match {
    case TypedefSpecifier() => x
  }

  def convert(using trace: Trace)(x: TypeDefTypeSpecifier): TypeDefTypeSpecifier = x match {
    case TypeDefTypeSpecifier(name) => given Trace = trace + x

      TypeDefTypeSpecifier(convert(name))
  }

  def convert(using Trace)(x: SignedSpecifier): SignedSpecifier = x match {
    case SignedSpecifier() => x
  }

  def convert(using Trace)(x: UnsignedSpecifier): UnsignedSpecifier = x match {
    case UnsignedSpecifier() => x
  }

  def convert(using Trace)(x: InlineSpecifier): InlineSpecifier = x match {
    case InlineSpecifier() => x
  }

  def convert(using Trace)(x: AutoSpecifier): AutoSpecifier = x match {
    case AutoSpecifier() => x
  }

  def convert(using Trace)(x: RegisterSpecifier): RegisterSpecifier = x match {
    case RegisterSpecifier() => x
  }

  def convert(using Trace)(x: VolatileSpecifier): VolatileSpecifier = x match {
    case VolatileSpecifier() => x
  }

  def convert(using Trace)(x: ExternSpecifier): ExternSpecifier = x match {
    case ExternSpecifier() => x
  }

  def convert(using Trace)(x: ConstSpecifier): ConstSpecifier = x match {
    case ConstSpecifier() => x
  }

  def convert(using Trace)(x: RestrictSpecifier): RestrictSpecifier = x match {
    case RestrictSpecifier() => x
  }

  def convert(using Trace)(x: ThreadSpecifier): ThreadSpecifier = x match {
    case ThreadSpecifier() => x
  }

  def convert(using Trace)(x: StaticSpecifier): StaticSpecifier = x match {
    case StaticSpecifier() => x
  }

  def convert(using Trace)(x: Attribute): Attribute = x match {
    case y: AtomicAttribute =>
      convert(y)
    case y: CompoundAttribute =>
      convert(y)
  }

  def convert(using trace: Trace)(x: AtomicAttribute): AtomicAttribute = x match {
    case AtomicAttribute(n) => given Trace = trace + x

      AtomicAttribute(convert(n))
  }

  def convert(using trace: Trace)(x: AttributeSequence): AttributeSequence = x match {
    case AttributeSequence(attributes) => given Trace = trace + x

      AttributeSequence(convert(attributes))
  }

  def convert(using trace: Trace)(x: CompoundAttribute): CompoundAttribute = x match {
    case CompoundAttribute(inner) => given Trace = trace + x

      CompoundAttribute(convert(inner))
  }

  def convert(using trace: Trace)(x: Declaration): Declaration = x match {
    case Declaration(declSpecs, init) => given Trace = trace + x

      Declaration(convert(declSpecs), convert(init))
  }

  def convert(using Trace)(x: InitDeclarator): InitDeclarator = x match {
    case y: InitDeclaratorI =>
      convert(y)
    case y: InitDeclaratorE =>
      convert(y)
  }

  def convert(using trace: Trace)(x: InitDeclaratorI): InitDeclaratorI = x match {
    case InitDeclaratorI(declarator, attributes, i) => given Trace = trace + x

      InitDeclaratorI(convert(declarator), convert(attributes), convert(i))
  }

  def convert(using trace: Trace)(x: InitDeclaratorE): InitDeclaratorE = x match {
    case InitDeclaratorE(declarator, attributes, e) => given Trace = trace + x

      InitDeclaratorE(convert(declarator), convert(attributes), convert(e))
  }

  def convert(using Trace)(x: AbstractDeclarator): AbstractDeclarator = x match {
    case y: AtomicAbstractDeclarator =>
      convert(y)
    case y: NestedAbstractDeclarator =>
      convert(y)
  }

  def convert(using Trace)(x: Declarator): Declarator = x match {
    case y: AtomicNamedDeclarator =>
      convert(y)
    case y: NestedNamedDeclarator =>
      convert(y)
  }

  def convert(using trace: Trace)(x: AtomicNamedDeclarator): AtomicNamedDeclarator = x match {
    case AtomicNamedDeclarator(pointers, id, extensions) => given Trace = trace + x

      AtomicNamedDeclarator(convert(pointers), convert(id), convert(extensions))
  }

  def convert(using trace: Trace)(x: NestedNamedDeclarator): NestedNamedDeclarator = x match {
    case NestedNamedDeclarator(pointers, nestedDecl, extensions, attr) => given Trace = trace + x

      NestedNamedDeclarator(convert(pointers), convert(nestedDecl), convert(extensions), convert(attr))
  }

  def convert(using trace: Trace)(x: AtomicAbstractDeclarator): AtomicAbstractDeclarator = x match {
    case AtomicAbstractDeclarator(pointers, extensions) => given Trace = trace + x

      AtomicAbstractDeclarator(convert(pointers), convert(extensions))
  }

  def convert(using trace: Trace)(x: NestedAbstractDeclarator): NestedAbstractDeclarator = x match {
    case NestedAbstractDeclarator(pointers, nestedDecl, extensions, attr) => given Trace = trace + x

      NestedAbstractDeclarator(convert(pointers), convert(nestedDecl), convert(extensions), convert(attr))
  }

  def convert(using Trace)(x: DeclaratorExtension): DeclaratorExtension = x match {
    case y: DeclaratorAbstrExtension =>
      convert(y)
    case y: DeclIdentifierList =>
      convert(y)
  }

  def convert(using Trace)(x: DeclaratorAbstrExtension): DeclaratorAbstrExtension = x match {
    case y: DeclParameterDeclList =>
      convert(y)
    case y: DeclArrayAccess =>
      convert(y)
  }

  def convert(using trace: Trace)(x: DeclIdentifierList): DeclIdentifierList = x match {
    case DeclIdentifierList(idList) => given Trace = trace + x

      DeclIdentifierList(convert(idList))
  }

  def convert(using trace: Trace)(x: DeclParameterDeclList): DeclParameterDeclList = x match {
    case DeclParameterDeclList(parameterDecls) => given Trace = trace + x

      DeclParameterDeclList(convert(parameterDecls))
  }

  def convert(using trace: Trace)(x: DeclArrayAccess): DeclArrayAccess = x match {
    case DeclArrayAccess(expr) => given Trace = trace + x

      DeclArrayAccess(convert(expr))
  }

  def convert(using trace: Trace)(x: Initializer): Initializer = x match {
    case Initializer(initializerElementLabel, expr) => given Trace = trace + x

      Initializer(convert(initializerElementLabel), convert(expr))
  }

  def convert(using trace: Trace)(x: Pointer): Pointer = x match {
    case Pointer(specifier) => given Trace = trace + x

      Pointer(convert(specifier))
  }

  def convert(using Trace)(x: ParameterDeclaration): ParameterDeclaration = x match {
    case y: PlainParameterDeclaration =>
      convert(y)
    case y: ParameterDeclarationD =>
      convert(y)
    case y: ParameterDeclarationAD =>
      convert(y)
    case y: VarArgs =>
      convert(y)
  }

  def convert(using trace: Trace)(x: PlainParameterDeclaration): PlainParameterDeclaration = x match {
    case PlainParameterDeclaration(specifiers, attr) => given Trace = trace + x

      PlainParameterDeclaration(convert(specifiers), convert(attr))
  }

  def convert(using trace: Trace)(x: ParameterDeclarationD): ParameterDeclarationD = x match {
    case ParameterDeclarationD(specifiers, decl, attr) => given Trace = trace + x

      ParameterDeclarationD(convert(specifiers), convert(decl), convert(attr))
  }

  def convert(using trace: Trace)(x: ParameterDeclarationAD): ParameterDeclarationAD = x match {
    case ParameterDeclarationAD(specifiers, decl, attr) => given Trace = trace + x

      ParameterDeclarationAD(convert(specifiers), convert(decl), convert(attr))
  }

  def convert(using Trace)(x: OldParameterDeclaration): OldParameterDeclaration = x match {
    case y: Declaration =>
      convert(y)
    case y: VarArgs =>
      convert(y)
  }

  def convert(using Trace)(x: VarArgs): VarArgs = x match {
    case VarArgs() => x
  }

  def convert(using trace: Trace)(x: EnumSpecifier): EnumSpecifier = x match {
    case EnumSpecifier(id, enumerators) => given Trace = trace + x

      EnumSpecifier(convert(id), convert(enumerators))
  }

  def convert(using trace: Trace)(x: Enumerator): Enumerator = x match {
    case Enumerator(id, assignment) => given Trace = trace + x

      Enumerator(convert(id), convert(assignment))
  }

  def convert(using trace: Trace)(x: StructOrUnionSpecifier): StructOrUnionSpecifier = x match {
    case StructOrUnionSpecifier(isUnion, id, enumerators, attributesBeforeBody, attributesAfterBody) => given Trace = trace + x

      StructOrUnionSpecifier(convert(isUnion), convert(id), convert(enumerators), convert(attributesBeforeBody), convert(attributesAfterBody))
  }

  def convert(using trace: Trace)(x: StructDeclaration): StructDeclaration = x match {
    case StructDeclaration(qualifierList, declaratorList) => given Trace = trace + x

      StructDeclaration(convert(qualifierList), convert(declaratorList))
  }

  def convert(using Trace)(x: StructDecl): StructDecl = x match {
    case y: StructDeclarator =>
      convert(y)
    case y: StructInitializer =>
      convert(y)
  }

  def convert(using trace: Trace)(x: StructDeclarator): StructDeclarator = x match {
    case StructDeclarator(decl, initializer, attributes) => given Trace = trace + x

      StructDeclarator(convert(decl), convert(initializer), convert(attributes))
  }

  def convert(using trace: Trace)(x: StructInitializer): StructInitializer = x match {
    case StructInitializer(expr, attributes) => given Trace = trace + x

      StructInitializer(convert(expr), convert(attributes))
  }

  def convert(using trace: Trace)(x: AsmExpr): AsmExpr = x match {
    case AsmExpr(isVolatile, expr) => given Trace = trace + x

      AsmExpr(convert(isVolatile), convert(expr))
  }

  def convert(using trace: Trace)(x: FunctionDef): FunctionDef = x match {
    case FunctionDef(specifiers, declarator, oldStyleParameters, stmt) => given Trace = trace + x

      FunctionDef(convert(specifiers), convert(declarator), convert(oldStyleParameters), convert(stmt))
  }

  def convert(using trace: Trace)(x: NestedFunctionDef): NestedFunctionDef = x match {
    case NestedFunctionDef(isAuto, specifiers, declarator, parameters, stmt) => given Trace = trace + x

      NestedFunctionDef(convert(isAuto), convert(specifiers), convert(declarator), convert(parameters), convert(stmt))
  }

  def convert(using Trace)(x: ExternalDef): ExternalDef = x match {
    case y: Declaration =>
      convert(y)
    case y: AsmExpr =>
      convert(y)
    case y: FunctionDef =>
      convert(y)
    case y: EmptyExternalDef =>
      convert(y)
    case y: TypelessDeclaration =>
      convert(y)
    case y: Pragma =>
      convert(y)
  }

  def convert(using Trace)(x: EmptyExternalDef): EmptyExternalDef = x match {
    case EmptyExternalDef() => x
  }

  def convert(using trace: Trace)(x: TypelessDeclaration): TypelessDeclaration = x match {
    case TypelessDeclaration(declList) => given Trace = trace + x

      TypelessDeclaration(convert(declList))
  }

  def convert(using trace: Trace)(x: TypeName): TypeName = x match {
    case TypeName(specifiers, decl) => given Trace = trace + x

      TypeName(convert(specifiers), convert(decl))
  }

  def convert(using trace: Trace)(x: TranslationUnit): TranslationUnit = x match {
    case TranslationUnit(defs) => given Trace = trace + x

      TranslationUnit(convert(defs))
  }

  def convert(using Trace)(x: AttributeSpecifier): AttributeSpecifier = x match {
    case y: GnuAttributeSpecifier =>
      convert(y)
    case y: AsmAttributeSpecifier =>
      convert(y)
  }

  def convert(using trace: Trace)(x: GnuAttributeSpecifier): GnuAttributeSpecifier = x match {
    case GnuAttributeSpecifier(attributeList) => given Trace = trace + x

      GnuAttributeSpecifier(convert(attributeList))
  }

  def convert(using trace: Trace)(x: AsmAttributeSpecifier): AsmAttributeSpecifier = x match {
    case AsmAttributeSpecifier(stringConst) => given Trace = trace + x

      AsmAttributeSpecifier(convert(stringConst))
  }

  def convert(using trace: Trace)(x: LcurlyInitializer): LcurlyInitializer = x match {
    case LcurlyInitializer(inits) => given Trace = trace + x

      LcurlyInitializer(convert(inits))
  }

  def convert(using trace: Trace)(x: AlignOfExprT): AlignOfExprT = x match {
    case AlignOfExprT(typeName) => given Trace = trace + x

      AlignOfExprT(convert(typeName))
  }

  def convert(using trace: Trace)(x: AlignOfExprU): AlignOfExprU = x match {
    case AlignOfExprU(expr) => given Trace = trace + x

      AlignOfExprU(convert(expr))
  }

  def convert(using trace: Trace)(x: GnuAsmExpr): GnuAsmExpr = x match {
    case GnuAsmExpr(isVolatile, isGoto, expr, stuff) => given Trace = trace + x

      GnuAsmExpr(convert(isVolatile), convert(isGoto), convert(expr), stuff)
  }

  def convert(using trace: Trace)(x: RangeExpr): RangeExpr = x match {
    case RangeExpr(from, to) => given Trace = trace + x

      RangeExpr(convert(from), convert(to))
  }

  def convert(using trace: Trace)(x: TypeOfSpecifierT): TypeOfSpecifierT = x match {
    case TypeOfSpecifierT(typeName) => given Trace = trace + x

      TypeOfSpecifierT(convert(typeName))
  }

  def convert(using trace: Trace)(x: TypeOfSpecifierU): TypeOfSpecifierU = x match {
    case TypeOfSpecifierU(expr) => given Trace = trace + x

      TypeOfSpecifierU(convert(expr))
  }

  def convert(using Trace)(x: InitializerElementLabel): InitializerElementLabel = x match {
    case y: InitializerArrayDesignator =>
      convert(y)
    case y: InitializerDesignatorC =>
      convert(y)
    case y: InitializerDesignatorD =>
      convert(y)
    case y: InitializerAssigment =>
      convert(y)
  }

  def convert(using trace: Trace)(x: InitializerArrayDesignator): InitializerArrayDesignator = x match {
    case InitializerArrayDesignator(expr) => given Trace = trace + x

      InitializerArrayDesignator(convert(expr))
  }

  def convert(using trace: Trace)(x: InitializerDesignatorC): InitializerDesignatorC = x match {
    case InitializerDesignatorC(id) => given Trace = trace + x

      InitializerDesignatorC(convert(id))
  }

  def convert(using trace: Trace)(x: InitializerDesignatorD): InitializerDesignatorD = x match {
    case InitializerDesignatorD(id) => given Trace = trace + x

      InitializerDesignatorD(convert(id))
  }

  def convert(using trace: Trace)(x: InitializerAssigment): InitializerAssigment = x match {
    case InitializerAssigment(designators) => given Trace = trace + x

      InitializerAssigment(convert(designators))
  }

  def convert(using trace: Trace)(x: BuiltinOffsetof): BuiltinOffsetof = x match {
    case BuiltinOffsetof(typeName, offsetofMemberDesignator) => given Trace = trace + x

      BuiltinOffsetof(convert(typeName), convert(offsetofMemberDesignator))
  }

  def convert(using Trace)(x: OffsetofMemberDesignator): OffsetofMemberDesignator = x match {
    case y: OffsetofMemberDesignatorID =>
      convert(y)
    case y: OffsetofMemberDesignatorExpr =>
      convert(y)
  }

  def convert(using trace: Trace)(x: OffsetofMemberDesignatorID): OffsetofMemberDesignatorID = x match {
    case OffsetofMemberDesignatorID(id) => given Trace = trace + x

      OffsetofMemberDesignatorID(convert(id))
  }

  def convert(using trace: Trace)(x: OffsetofMemberDesignatorExpr): OffsetofMemberDesignatorExpr = x match {
    case OffsetofMemberDesignatorExpr(expr) => given Trace = trace + x

      OffsetofMemberDesignatorExpr(convert(expr))
  }

  def convert(using trace: Trace)(x: BuiltinTypesCompatible): BuiltinTypesCompatible = x match {
    case BuiltinTypesCompatible(typeName1, typeName2) => given Trace = trace + x

      BuiltinTypesCompatible(convert(typeName1), convert(typeName2))
  }

  def convert(using trace: Trace)(x: BuiltinVaArgs): BuiltinVaArgs = x match {
    case BuiltinVaArgs(expr, typeName) => given Trace = trace + x

      BuiltinVaArgs(convert(expr), convert(typeName))
  }

  def convert(using trace: Trace)(x: CompoundStatementExpr): CompoundStatementExpr = x match {
    case CompoundStatementExpr(compoundStatement) => given Trace = trace + x

      CompoundStatementExpr(convert(compoundStatement))
  }

  def convert(using trace: Trace)(x: Pragma): Pragma = x match {
    case Pragma(command) => given Trace = trace + x

      Pragma(convert(command))
  }

  def convert(using Trace)(x: Boolean): Boolean = x

  def convert(using Trace)(x: String): String = x

  def convert(using Trace)(x: FeatureExpr): FeatureExpr = x

  def convert[U](using trace: Trace)(x: Opt[U])(using f: F[U]): Opt[U] = x match {
    case Opt(condition, entry) => given Trace = trace + x

      Opt(convert(condition), f(entry))
  }

  def convert[U](using trace: Trace)(x: Conditional[U])(using F[U]): Conditional[U] = x match {
    case y: Choice[U] =>
      convert(y)
    case y: One[U] =>
      convert(y)
  }

  def convert[U](using trace: Trace)(x: Choice[U])(using f: F[Conditional[U]]): Choice[U] = x match {
    case Choice(condition, thenBranch, elseBranch) => given Trace = trace + x
      
      Choice(convert(condition), f(thenBranch), f(elseBranch))
  }

  def convert[U](using trace: Trace)(x: One[U])(using f: F[U]): One[U] = x match {
    case One(value) => given Trace = trace + x
      
      One(f(value))
  }

  def convert[U](using Trace)(x: List[U])(using f: F[U]): List[U] = x.map(f)

  def convert[U](using Trace)(x: Option[U])(using f: F[U]): Option[U] = x.map(f)

  given F[Expr] = convert(_)

  given F[PrimaryExpr] = convert(_)

  given F[Id] = convert(_)

  given F[Constant] = convert(_)

  given F[StringLit] = convert(_)

  given F[PostfixSuffix] = convert(_)

  given F[SimplePostfixSuffix] = convert(_)

  given F[PointerPostfixSuffix] = convert(_)

  given F[FunctionCall] = convert(_)

  given F[ArrayAccess] = convert(_)

  given F[PostfixExpr] = convert(_)

  given F[UnaryExpr] = convert(_)

  given F[SizeOfExprT] = convert(_)

  given F[SizeOfExprU] = convert(_)

  given F[CastExpr] = convert(_)

  given F[PointerDerefExpr] = convert(_)

  given F[PointerCreationExpr] = convert(_)

  given F[UnaryOpExpr] = convert(_)

  given F[NAryExpr] = convert(_)

  given F[NArySubExpr] = convert(_)

  given F[ConditionalExpr] = convert(_)

  given F[AssignExpr] = convert(_)

  given F[ExprList] = convert(_)

  given F[Statement] = convert(_)

  given F[CompoundStatement] = convert(_)

  given F[EmptyStatement] = convert(_)

  given F[ExprStatement] = convert(_)

  given F[WhileStatement] = convert(_)

  given F[DoStatement] = convert(_)

  given F[ForStatement] = convert(_)

  given F[GotoStatement] = convert(_)

  given F[ContinueStatement] = convert(_)

  given F[BreakStatement] = convert(_)

  given F[ReturnStatement] = convert(_)

  given F[LabelStatement] = convert(_)

  given F[CaseStatement] = convert(_)

  given F[DefaultStatement] = convert(_)

  given F[IfStatement] = convert(_)

  given F[ElifStatement] = convert(_)

  given F[SwitchStatement] = convert(_)

  given F[CompoundDeclaration] = convert(_)

  given F[DeclarationStatement] = convert(_)

  given F[LocalLabelDeclaration] = convert(_)

  given F[Specifier] = convert(_)

  given F[TypeSpecifier] = convert(_)

  given F[PrimitiveTypeSpecifier] = convert(_)

  given F[OtherSpecifier] = convert(_)

  given F[OtherPrimitiveTypeSpecifier] = convert(_)

  given F[VoidSpecifier] = convert(_)

  given F[ShortSpecifier] = convert(_)

  given F[IntSpecifier] = convert(_)

  given F[FloatSpecifier] = convert(_)

  given F[DoubleSpecifier] = convert(_)

  given F[LongSpecifier] = convert(_)

  given F[Int128Specifier] = convert(_)

  given F[CharSpecifier] = convert(_)

  given F[TypedefSpecifier] = convert(_)

  given F[TypeDefTypeSpecifier] = convert(_)

  given F[SignedSpecifier] = convert(_)

  given F[UnsignedSpecifier] = convert(_)

  given F[InlineSpecifier] = convert(_)

  given F[AutoSpecifier] = convert(_)

  given F[RegisterSpecifier] = convert(_)

  given F[VolatileSpecifier] = convert(_)

  given F[ExternSpecifier] = convert(_)

  given F[ConstSpecifier] = convert(_)

  given F[RestrictSpecifier] = convert(_)

  given F[ThreadSpecifier] = convert(_)

  given F[StaticSpecifier] = convert(_)

  given F[Attribute] = convert(_)

  given F[AtomicAttribute] = convert(_)

  given F[AttributeSequence] = convert(_)

  given F[CompoundAttribute] = convert(_)

  given F[Declaration] = convert(_)

  given F[InitDeclarator] = convert(_)

  given F[InitDeclaratorI] = convert(_)

  given F[InitDeclaratorE] = convert(_)

  given F[AbstractDeclarator] = convert(_)

  given F[Declarator] = convert(_)

  given F[AtomicNamedDeclarator] = convert(_)

  given F[NestedNamedDeclarator] = convert(_)

  given F[AtomicAbstractDeclarator] = convert(_)

  given F[NestedAbstractDeclarator] = convert(_)

  given F[DeclaratorExtension] = convert(_)

  given F[DeclaratorAbstrExtension] = convert(_)

  given F[DeclIdentifierList] = convert(_)

  given F[DeclParameterDeclList] = convert(_)

  given F[DeclArrayAccess] = convert(_)

  given F[Initializer] = convert(_)

  given F[Pointer] = convert(_)

  given F[ParameterDeclaration] = convert(_)

  given F[PlainParameterDeclaration] = convert(_)

  given F[ParameterDeclarationD] = convert(_)

  given F[ParameterDeclarationAD] = convert(_)

  given F[OldParameterDeclaration] = convert(_)

  given F[VarArgs] = convert(_)

  given F[EnumSpecifier] = convert(_)

  given F[Enumerator] = convert(_)

  given F[StructOrUnionSpecifier] = convert(_)

  given F[StructDeclaration] = convert(_)

  given F[StructDecl] = convert(_)

  given F[StructDeclarator] = convert(_)

  given F[StructInitializer] = convert(_)

  given F[AsmExpr] = convert(_)

  given F[FunctionDef] = convert(_)

  given F[NestedFunctionDef] = convert(_)

  given F[ExternalDef] = convert(_)

  given F[EmptyExternalDef] = convert(_)

  given F[TypelessDeclaration] = convert(_)

  given F[TypeName] = convert(_)

  given F[TranslationUnit] = convert(_)

  given F[AttributeSpecifier] = convert(_)

  given F[GnuAttributeSpecifier] = convert(_)

  given F[AsmAttributeSpecifier] = convert(_)

  given F[LcurlyInitializer] = convert(_)

  given F[AlignOfExprT] = convert(_)

  given F[AlignOfExprU] = convert(_)

  given F[GnuAsmExpr] = convert(_)

  given F[RangeExpr] = convert(_)

  given F[TypeOfSpecifierT] = convert(_)

  given F[TypeOfSpecifierU] = convert(_)

  given F[InitializerElementLabel] = convert(_)

  given F[InitializerArrayDesignator] = convert(_)

  given F[InitializerDesignatorC] = convert(_)

  given F[InitializerDesignatorD] = convert(_)

  given F[InitializerAssigment] = convert(_)

  given F[BuiltinOffsetof] = convert(_)

  given F[OffsetofMemberDesignator] = convert(_)

  given F[OffsetofMemberDesignatorID] = convert(_)

  given F[OffsetofMemberDesignatorExpr] = convert(_)

  given F[BuiltinTypesCompatible] = convert(_)

  given F[BuiltinVaArgs] = convert(_)

  given F[CompoundStatementExpr] = convert(_)

  given F[Pragma] = convert(_)

  //  given F[Any] = convert(_)

  given F[Boolean] = convert(_)

  given F[String] = convert(_)

  given F[FeatureExpr] = convert(_)

  given[U](using F[U]): F[Opt[U]] = convert(_)

  given[U](using F[U]): F[Conditional[U]] = convert(_)

  given[U](using F[U]): F[Choice[U]] = convert(_)

  given[U](using F[U]): F[One[U]] = convert(_)

  given[U](using F[U]): F[List[U]] = convert(_)

  given[U](using F[U]): F[Option[U]] = convert(_)
}
