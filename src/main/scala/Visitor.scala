import de.fosd.typechef.conditional.{Choice, Conditional, One, Opt}
import de.fosd.typechef.featureexpr.FeatureExpr
import de.fosd.typechef.parser.c.*

import scala.annotation.unused

//noinspection DuplicatedCode
trait Visitor {
  type F[U] = Trace ?=> (Visitor, U) => Visitor

  def enterAny(using Trace)(@unused any: Any): Visitor = this

  def enterAST(using Trace)(@unused ast: AST): Visitor = enterAny(ast)

  def enterAbstractAST(using Trace)(@unused ast: AST): Visitor = enterAny(ast)

  def enter(using Trace)(@unused expr: Expr): Visitor = enterAbstractAST(expr)

  def enter(using Trace)(@unused primaryExpr: PrimaryExpr): Visitor = enterAbstractAST(primaryExpr)

  def enter(using Trace)(@unused id: Id): Visitor = enterAST(id)

  def enter(using Trace)(@unused constant: Constant): Visitor = enterAST(constant)

  def enter(using Trace)(@unused stringLit: StringLit): Visitor = enterAST(stringLit)

  def enter(using Trace)(@unused postfixSuffix: PostfixSuffix): Visitor = enterAbstractAST(postfixSuffix)

  def enter(using Trace)(@unused simplePostfixSuffix: SimplePostfixSuffix): Visitor = enterAST(simplePostfixSuffix)

  def enter(using Trace)(@unused pointerPostfixSuffix: PointerPostfixSuffix): Visitor = enterAST(pointerPostfixSuffix)

  def enter(using Trace)(@unused functionCall: FunctionCall): Visitor = enterAST(functionCall)

  def enter(using Trace)(@unused arrayAccess: ArrayAccess): Visitor = enterAST(arrayAccess)

  def enter(using Trace)(@unused postfixExpr: PostfixExpr): Visitor = enterAST(postfixExpr)

  def enter(using Trace)(@unused unaryExpr: UnaryExpr): Visitor = enterAST(unaryExpr)

  def enter(using Trace)(@unused sizeOfExprT: SizeOfExprT): Visitor = enterAST(sizeOfExprT)

  def enter(using Trace)(@unused sizeOfExprU: SizeOfExprU): Visitor = enterAST(sizeOfExprU)

  def enter(using Trace)(@unused castExpr: CastExpr): Visitor = enterAST(castExpr)

  def enter(using Trace)(@unused pointerDerefExpr: PointerDerefExpr): Visitor = enterAST(pointerDerefExpr)

  def enter(using Trace)(@unused pointerCreationExpr: PointerCreationExpr): Visitor = enterAST(pointerCreationExpr)

  def enter(using Trace)(@unused unaryOpExpr: UnaryOpExpr): Visitor = enterAST(unaryOpExpr)

  def enter(using Trace)(@unused nAryExpr: NAryExpr): Visitor = enterAST(nAryExpr)

  def enter(using Trace)(@unused nArySubExpr: NArySubExpr): Visitor = enterAST(nArySubExpr)

  def enter(using Trace)(@unused conditionalExpr: ConditionalExpr): Visitor = enterAST(conditionalExpr)

  def enter(using Trace)(@unused assignExpr: AssignExpr): Visitor = enterAST(assignExpr)

  def enter(using Trace)(@unused exprList: ExprList): Visitor = enterAST(exprList)

  def enter(using Trace)(@unused statement: Statement): Visitor = enterAbstractAST(statement)

  def enter(using Trace)(@unused compoundStatement: CompoundStatement): Visitor = enterAST(compoundStatement)

  def enter(using Trace)(@unused emptyStatement: EmptyStatement): Visitor = enterAST(emptyStatement)

  def enter(using Trace)(@unused exprStatement: ExprStatement): Visitor = enterAST(exprStatement)

  def enter(using Trace)(@unused whileStatement: WhileStatement): Visitor = enterAST(whileStatement)

  def enter(using Trace)(@unused doStatement: DoStatement): Visitor = enterAST(doStatement)

  def enter(using Trace)(@unused forStatement: ForStatement): Visitor = enterAST(forStatement)

  def enter(using Trace)(@unused gotoStatement: GotoStatement): Visitor = enterAST(gotoStatement)

  def enter(using Trace)(@unused continueStatement: ContinueStatement): Visitor = enterAST(continueStatement)

  def enter(using Trace)(@unused breakStatement: BreakStatement): Visitor = enterAST(breakStatement)

  def enter(using Trace)(@unused returnStatement: ReturnStatement): Visitor = enterAST(returnStatement)

  def enter(using Trace)(@unused labelStatement: LabelStatement): Visitor = enterAST(labelStatement)

  def enter(using Trace)(@unused caseStatement: CaseStatement): Visitor = enterAST(caseStatement)

  def enter(using Trace)(@unused defaultStatement: DefaultStatement): Visitor = enterAST(defaultStatement)

  def enter(using Trace)(@unused ifStatement: IfStatement): Visitor = enterAST(ifStatement)

  def enter(using Trace)(@unused elifStatement: ElifStatement): Visitor = enterAST(elifStatement)

  def enter(using Trace)(@unused switchStatement: SwitchStatement): Visitor = enterAST(switchStatement)

  def enter(using Trace)(@unused compoundDeclaration: CompoundDeclaration): Visitor = enterAbstractAST(compoundDeclaration)

  def enter(using Trace)(@unused declarationStatement: DeclarationStatement): Visitor = enterAST(declarationStatement)

  def enter(using Trace)(@unused localLabelDeclaration: LocalLabelDeclaration): Visitor = enterAST(localLabelDeclaration)

  def enter(using Trace)(@unused specifier: Specifier): Visitor = enterAbstractAST(specifier)

  def enter(using Trace)(@unused typeSpecifier: TypeSpecifier): Visitor = enterAbstractAST(typeSpecifier)

  def enter(using Trace)(@unused primitiveTypeSpecifier: PrimitiveTypeSpecifier): Visitor = enterAbstractAST(primitiveTypeSpecifier)

  def enter(using Trace)(@unused otherSpecifier: OtherSpecifier): Visitor = enterAbstractAST(otherSpecifier)

  def enter(using Trace)(@unused otherPrimitiveTypeSpecifier: OtherPrimitiveTypeSpecifier): Visitor = enterAST(otherPrimitiveTypeSpecifier)

  def enter(using Trace)(@unused voidSpecifier: VoidSpecifier): Visitor = enterAST(voidSpecifier)

  def enter(using Trace)(@unused shortSpecifier: ShortSpecifier): Visitor = enterAST(shortSpecifier)

  def enter(using Trace)(@unused intSpecifier: IntSpecifier): Visitor = enterAST(intSpecifier)

  def enter(using Trace)(@unused floatSpecifier: FloatSpecifier): Visitor = enterAST(floatSpecifier)

  def enter(using Trace)(@unused doubleSpecifier: DoubleSpecifier): Visitor = enterAST(doubleSpecifier)

  def enter(using Trace)(@unused longSpecifier: LongSpecifier): Visitor = enterAST(longSpecifier)

  def enter(using Trace)(@unused int128Specifier: Int128Specifier): Visitor = enterAST(int128Specifier)

  def enter(using Trace)(@unused charSpecifier: CharSpecifier): Visitor = enterAST(charSpecifier)

  def enter(using Trace)(@unused typedefSpecifier: TypedefSpecifier): Visitor = enterAST(typedefSpecifier)

  def enter(using Trace)(@unused typeDefTypeSpecifier: TypeDefTypeSpecifier): Visitor = enterAST(typeDefTypeSpecifier)

  def enter(using Trace)(@unused signedSpecifier: SignedSpecifier): Visitor = enterAST(signedSpecifier)

  def enter(using Trace)(@unused unsignedSpecifier: UnsignedSpecifier): Visitor = enterAST(unsignedSpecifier)

  def enter(using Trace)(@unused inlineSpecifier: InlineSpecifier): Visitor = enterAST(inlineSpecifier)

  def enter(using Trace)(@unused autoSpecifier: AutoSpecifier): Visitor = enterAST(autoSpecifier)

  def enter(using Trace)(@unused registerSpecifier: RegisterSpecifier): Visitor = enterAST(registerSpecifier)

  def enter(using Trace)(@unused volatileSpecifier: VolatileSpecifier): Visitor = enterAST(volatileSpecifier)

  def enter(using Trace)(@unused externSpecifier: ExternSpecifier): Visitor = enterAST(externSpecifier)

  def enter(using Trace)(@unused constSpecifier: ConstSpecifier): Visitor = enterAST(constSpecifier)

  def enter(using Trace)(@unused restrictSpecifier: RestrictSpecifier): Visitor = enterAST(restrictSpecifier)

  def enter(using Trace)(@unused threadSpecifier: ThreadSpecifier): Visitor = enterAST(threadSpecifier)

  def enter(using Trace)(@unused staticSpecifier: StaticSpecifier): Visitor = enterAST(staticSpecifier)

  def enter(using Trace)(@unused attribute: Attribute): Visitor = enterAbstractAST(attribute)

  def enter(using Trace)(@unused atomicAttribute: AtomicAttribute): Visitor = enterAST(atomicAttribute)

  def enter(using Trace)(@unused attributeSequence: AttributeSequence): Visitor = enterAST(attributeSequence)

  def enter(using Trace)(@unused compoundAttribute: CompoundAttribute): Visitor = enterAST(compoundAttribute)

  def enter(using Trace)(@unused declaration: Declaration): Visitor = enterAST(declaration)

  def enter(using Trace)(@unused initDeclarator: InitDeclarator): Visitor = enterAbstractAST(initDeclarator)

  def enter(using Trace)(@unused initDeclaratorI: InitDeclaratorI): Visitor = enterAST(initDeclaratorI)

  def enter(using Trace)(@unused initDeclaratorE: InitDeclaratorE): Visitor = enterAST(initDeclaratorE)

  def enter(using Trace)(@unused abstractDeclarator: AbstractDeclarator): Visitor = enterAbstractAST(abstractDeclarator)

  def enter(using Trace)(@unused declarator: Declarator): Visitor = enterAbstractAST(declarator)

  def enter(using Trace)(@unused atomicNamedDeclarator: AtomicNamedDeclarator): Visitor = enterAST(atomicNamedDeclarator)

  def enter(using Trace)(@unused nestedNamedDeclarator: NestedNamedDeclarator): Visitor = enterAST(nestedNamedDeclarator)

  def enter(using Trace)(@unused atomicAbstractDeclarator: AtomicAbstractDeclarator): Visitor = enterAST(atomicAbstractDeclarator)

  def enter(using Trace)(@unused nestedAbstractDeclarator: NestedAbstractDeclarator): Visitor = enterAST(nestedAbstractDeclarator)

  def enter(using Trace)(@unused declaratorExtension: DeclaratorExtension): Visitor = enterAbstractAST(declaratorExtension)

  def enter(using Trace)(@unused declaratorAbstrExtension: DeclaratorAbstrExtension): Visitor = enterAbstractAST(declaratorAbstrExtension)

  def enter(using Trace)(@unused declIdentifierList: DeclIdentifierList): Visitor = enterAST(declIdentifierList)

  def enter(using Trace)(@unused declParameterDeclList: DeclParameterDeclList): Visitor = enterAST(declParameterDeclList)

  def enter(using Trace)(@unused declArrayAccess: DeclArrayAccess): Visitor = enterAST(declArrayAccess)

  def enter(using Trace)(@unused initializer: Initializer): Visitor = enterAST(initializer)

  def enter(using Trace)(@unused pointer: Pointer): Visitor = enterAST(pointer)

  def enter(using Trace)(@unused parameterDeclaration: ParameterDeclaration): Visitor = enterAbstractAST(parameterDeclaration)

  def enter(using Trace)(@unused plainParameterDeclaration: PlainParameterDeclaration): Visitor = enterAST(plainParameterDeclaration)

  def enter(using Trace)(@unused parameterDeclarationD: ParameterDeclarationD): Visitor = enterAST(parameterDeclarationD)

  def enter(using Trace)(@unused parameterDeclarationAD: ParameterDeclarationAD): Visitor = enterAST(parameterDeclarationAD)

  def enter(using Trace)(@unused oldParameterDeclaration: OldParameterDeclaration): Visitor = enterAbstractAST(oldParameterDeclaration)

  def enter(using Trace)(@unused varArgs: VarArgs): Visitor = enterAST(varArgs)

  def enter(using Trace)(@unused enumSpecifier: EnumSpecifier): Visitor = enterAST(enumSpecifier)

  def enter(using Trace)(@unused enumerator: Enumerator): Visitor = enterAST(enumerator)

  def enter(using Trace)(@unused structOrUnionSpecifier: StructOrUnionSpecifier): Visitor = enterAST(structOrUnionSpecifier)

  def enter(using Trace)(@unused structDeclaration: StructDeclaration): Visitor = enterAST(structDeclaration)

  def enter(using Trace)(@unused structDecl: StructDecl): Visitor = enterAbstractAST(structDecl)

  def enter(using Trace)(@unused structDeclarator: StructDeclarator): Visitor = enterAST(structDeclarator)

  def enter(using Trace)(@unused structInitializer: StructInitializer): Visitor = enterAST(structInitializer)

  def enter(using Trace)(@unused asmExpr: AsmExpr): Visitor = enterAST(asmExpr)

  def enter(using Trace)(@unused functionDef: FunctionDef): Visitor = enterAST(functionDef)

  def enter(using Trace)(@unused nestedFunctionDef: NestedFunctionDef): Visitor = enterAST(nestedFunctionDef)

  def enter(using Trace)(@unused externalDef: ExternalDef): Visitor = enterAbstractAST(externalDef)

  def enter(using Trace)(@unused emptyExternalDef: EmptyExternalDef): Visitor = enterAST(emptyExternalDef)

  def enter(using Trace)(@unused typelessDeclaration: TypelessDeclaration): Visitor = enterAST(typelessDeclaration)

  def enter(using Trace)(@unused typeName: TypeName): Visitor = enterAST(typeName)

  def enter(using Trace)(@unused translationUnit: TranslationUnit): Visitor = enterAST(translationUnit)

  def enter(using Trace)(@unused attributeSpecifier: AttributeSpecifier): Visitor = enterAbstractAST(attributeSpecifier)

  def enter(using Trace)(@unused gnuAttributeSpecifier: GnuAttributeSpecifier): Visitor = enterAST(gnuAttributeSpecifier)

  def enter(using Trace)(@unused asmAttributeSpecifier: AsmAttributeSpecifier): Visitor = enterAST(asmAttributeSpecifier)

  def enter(using Trace)(@unused lcurlyInitializer: LcurlyInitializer): Visitor = enterAST(lcurlyInitializer)

  def enter(using Trace)(@unused alignOfExprT: AlignOfExprT): Visitor = enterAST(alignOfExprT)

  def enter(using Trace)(@unused alignOfExprU: AlignOfExprU): Visitor = enterAST(alignOfExprU)

  def enter(using Trace)(@unused gnuAsmExpr: GnuAsmExpr): Visitor = enterAST(gnuAsmExpr)

  def enter(using Trace)(@unused rangeExpr: RangeExpr): Visitor = enterAST(rangeExpr)

  def enter(using Trace)(@unused typeOfSpecifierT: TypeOfSpecifierT): Visitor = enterAST(typeOfSpecifierT)

  def enter(using Trace)(@unused typeOfSpecifierU: TypeOfSpecifierU): Visitor = enterAST(typeOfSpecifierU)

  def enter(using Trace)(@unused initializerElementLabel: InitializerElementLabel): Visitor = enterAbstractAST(initializerElementLabel)

  def enter(using Trace)(@unused initializerArrayDesignator: InitializerArrayDesignator): Visitor = enterAST(initializerArrayDesignator)

  def enter(using Trace)(@unused initializerDesignatorC: InitializerDesignatorC): Visitor = enterAST(initializerDesignatorC)

  def enter(using Trace)(@unused initializerDesignatorD: InitializerDesignatorD): Visitor = enterAST(initializerDesignatorD)

  def enter(using Trace)(@unused initializerAssigment: InitializerAssigment): Visitor = enterAST(initializerAssigment)

  def enter(using Trace)(@unused builtinOffsetof: BuiltinOffsetof): Visitor = enterAST(builtinOffsetof)

  def enter(using Trace)(@unused offsetofMemberDesignator: OffsetofMemberDesignator): Visitor = enterAbstractAST(offsetofMemberDesignator)

  def enter(using Trace)(@unused offsetofMemberDesignatorID: OffsetofMemberDesignatorID): Visitor = enterAST(offsetofMemberDesignatorID)

  def enter(using Trace)(@unused offsetofMemberDesignatorExpr: OffsetofMemberDesignatorExpr): Visitor = enterAST(offsetofMemberDesignatorExpr)

  def enter(using Trace)(@unused builtinTypesCompatible: BuiltinTypesCompatible): Visitor = enterAST(builtinTypesCompatible)

  def enter(using Trace)(@unused builtinVaArgs: BuiltinVaArgs): Visitor = enterAST(builtinVaArgs)

  def enter(using Trace)(@unused compoundStatementExpr: CompoundStatementExpr): Visitor = enterAST(compoundStatementExpr)

  def enter(using Trace)(@unused pragma: Pragma): Visitor = enterAST(pragma)

  def enter(using Trace)(@unused any: Any): Visitor = enterAny(any)

  def enter(using Trace)(@unused boolean: Boolean): Visitor = enterAny(boolean)

  def enter(using Trace)(@unused string: String): Visitor = enterAny(string)

  def enter(using Trace)(@unused featureExpr: FeatureExpr): Visitor = enterAny(featureExpr)

  def enter[U](using Trace)(@unused opt: Opt[U]): Visitor = enterAny(opt)

  def enter[U](using Trace)(@unused conditional: Conditional[U]): Visitor = enterAny(conditional)

  def enter[U](using Trace)(@unused choice: Choice[U]): Visitor = enterAny(choice)

  def enter[U](using Trace)(@unused one: One[U]): Visitor = enterAny(one)

  def enter[U](using Trace)(@unused iterableOnce: IterableOnce[U]): Visitor = enterAny(iterableOnce)

  def leaveAny(using Trace)(@unused any: Any): Visitor = this

  def leaveAST(using Trace)(@unused ast: AST): Visitor = leaveAny(ast)

  def leaveAbstractAST(using Trace)(@unused ast: AST): Visitor = leaveAny(ast)

  def leave(using Trace)(@unused expr: Expr): Visitor = leaveAbstractAST(expr)

  def leave(using Trace)(@unused primaryExpr: PrimaryExpr): Visitor = leaveAbstractAST(primaryExpr)

  def leave(using Trace)(@unused id: Id): Visitor = leaveAST(id)

  def leave(using Trace)(@unused constant: Constant): Visitor = leaveAST(constant)

  def leave(using Trace)(@unused stringLit: StringLit): Visitor = leaveAST(stringLit)

  def leave(using Trace)(@unused postfixSuffix: PostfixSuffix): Visitor = leaveAbstractAST(postfixSuffix)

  def leave(using Trace)(@unused simplePostfixSuffix: SimplePostfixSuffix): Visitor = leaveAST(simplePostfixSuffix)

  def leave(using Trace)(@unused pointerPostfixSuffix: PointerPostfixSuffix): Visitor = leaveAST(pointerPostfixSuffix)

  def leave(using Trace)(@unused functionCall: FunctionCall): Visitor = leaveAST(functionCall)

  def leave(using Trace)(@unused arrayAccess: ArrayAccess): Visitor = leaveAST(arrayAccess)

  def leave(using Trace)(@unused postfixExpr: PostfixExpr): Visitor = leaveAST(postfixExpr)

  def leave(using Trace)(@unused unaryExpr: UnaryExpr): Visitor = leaveAST(unaryExpr)

  def leave(using Trace)(@unused sizeOfExprT: SizeOfExprT): Visitor = leaveAST(sizeOfExprT)

  def leave(using Trace)(@unused sizeOfExprU: SizeOfExprU): Visitor = leaveAST(sizeOfExprU)

  def leave(using Trace)(@unused castExpr: CastExpr): Visitor = leaveAST(castExpr)

  def leave(using Trace)(@unused pointerDerefExpr: PointerDerefExpr): Visitor = leaveAST(pointerDerefExpr)

  def leave(using Trace)(@unused pointerCreationExpr: PointerCreationExpr): Visitor = leaveAST(pointerCreationExpr)

  def leave(using Trace)(@unused unaryOpExpr: UnaryOpExpr): Visitor = leaveAST(unaryOpExpr)

  def leave(using Trace)(@unused nAryExpr: NAryExpr): Visitor = leaveAST(nAryExpr)

  def leave(using Trace)(@unused nArySubExpr: NArySubExpr): Visitor = leaveAST(nArySubExpr)

  def leave(using Trace)(@unused conditionalExpr: ConditionalExpr): Visitor = leaveAST(conditionalExpr)

  def leave(using Trace)(@unused assignExpr: AssignExpr): Visitor = leaveAST(assignExpr)

  def leave(using Trace)(@unused exprList: ExprList): Visitor = leaveAST(exprList)

  def leave(using Trace)(@unused statement: Statement): Visitor = leaveAbstractAST(statement)

  def leave(using Trace)(@unused compoundStatement: CompoundStatement): Visitor = leaveAST(compoundStatement)

  def leave(using Trace)(@unused emptyStatement: EmptyStatement): Visitor = leaveAST(emptyStatement)

  def leave(using Trace)(@unused exprStatement: ExprStatement): Visitor = leaveAST(exprStatement)

  def leave(using Trace)(@unused whileStatement: WhileStatement): Visitor = leaveAST(whileStatement)

  def leave(using Trace)(@unused doStatement: DoStatement): Visitor = leaveAST(doStatement)

  def leave(using Trace)(@unused forStatement: ForStatement): Visitor = leaveAST(forStatement)

  def leave(using Trace)(@unused gotoStatement: GotoStatement): Visitor = leaveAST(gotoStatement)

  def leave(using Trace)(@unused continueStatement: ContinueStatement): Visitor = leaveAST(continueStatement)

  def leave(using Trace)(@unused breakStatement: BreakStatement): Visitor = leaveAST(breakStatement)

  def leave(using Trace)(@unused returnStatement: ReturnStatement): Visitor = leaveAST(returnStatement)

  def leave(using Trace)(@unused labelStatement: LabelStatement): Visitor = leaveAST(labelStatement)

  def leave(using Trace)(@unused caseStatement: CaseStatement): Visitor = leaveAST(caseStatement)

  def leave(using Trace)(@unused defaultStatement: DefaultStatement): Visitor = leaveAST(defaultStatement)

  def leave(using Trace)(@unused ifStatement: IfStatement): Visitor = leaveAST(ifStatement)

  def leave(using Trace)(@unused elifStatement: ElifStatement): Visitor = leaveAST(elifStatement)

  def leave(using Trace)(@unused switchStatement: SwitchStatement): Visitor = leaveAST(switchStatement)

  def leave(using Trace)(@unused compoundDeclaration: CompoundDeclaration): Visitor = leaveAbstractAST(compoundDeclaration)

  def leave(using Trace)(@unused declarationStatement: DeclarationStatement): Visitor = leaveAST(declarationStatement)

  def leave(using Trace)(@unused localLabelDeclaration: LocalLabelDeclaration): Visitor = leaveAST(localLabelDeclaration)

  def leave(using Trace)(@unused specifier: Specifier): Visitor = leaveAbstractAST(specifier)

  def leave(using Trace)(@unused typeSpecifier: TypeSpecifier): Visitor = leaveAbstractAST(typeSpecifier)

  def leave(using Trace)(@unused primitiveTypeSpecifier: PrimitiveTypeSpecifier): Visitor = leaveAbstractAST(primitiveTypeSpecifier)

  def leave(using Trace)(@unused otherSpecifier: OtherSpecifier): Visitor = leaveAbstractAST(otherSpecifier)

  def leave(using Trace)(@unused otherPrimitiveTypeSpecifier: OtherPrimitiveTypeSpecifier): Visitor = leaveAST(otherPrimitiveTypeSpecifier)

  def leave(using Trace)(@unused voidSpecifier: VoidSpecifier): Visitor = leaveAST(voidSpecifier)

  def leave(using Trace)(@unused shortSpecifier: ShortSpecifier): Visitor = leaveAST(shortSpecifier)

  def leave(using Trace)(@unused intSpecifier: IntSpecifier): Visitor = leaveAST(intSpecifier)

  def leave(using Trace)(@unused floatSpecifier: FloatSpecifier): Visitor = leaveAST(floatSpecifier)

  def leave(using Trace)(@unused doubleSpecifier: DoubleSpecifier): Visitor = leaveAST(doubleSpecifier)

  def leave(using Trace)(@unused longSpecifier: LongSpecifier): Visitor = leaveAST(longSpecifier)

  def leave(using Trace)(@unused int128Specifier: Int128Specifier): Visitor = leaveAST(int128Specifier)

  def leave(using Trace)(@unused charSpecifier: CharSpecifier): Visitor = leaveAST(charSpecifier)

  def leave(using Trace)(@unused typedefSpecifier: TypedefSpecifier): Visitor = leaveAST(typedefSpecifier)

  def leave(using Trace)(@unused typeDefTypeSpecifier: TypeDefTypeSpecifier): Visitor = leaveAST(typeDefTypeSpecifier)

  def leave(using Trace)(@unused signedSpecifier: SignedSpecifier): Visitor = leaveAST(signedSpecifier)

  def leave(using Trace)(@unused unsignedSpecifier: UnsignedSpecifier): Visitor = leaveAST(unsignedSpecifier)

  def leave(using Trace)(@unused inlineSpecifier: InlineSpecifier): Visitor = leaveAST(inlineSpecifier)

  def leave(using Trace)(@unused autoSpecifier: AutoSpecifier): Visitor = leaveAST(autoSpecifier)

  def leave(using Trace)(@unused registerSpecifier: RegisterSpecifier): Visitor = leaveAST(registerSpecifier)

  def leave(using Trace)(@unused volatileSpecifier: VolatileSpecifier): Visitor = leaveAST(volatileSpecifier)

  def leave(using Trace)(@unused externSpecifier: ExternSpecifier): Visitor = leaveAST(externSpecifier)

  def leave(using Trace)(@unused constSpecifier: ConstSpecifier): Visitor = leaveAST(constSpecifier)

  def leave(using Trace)(@unused restrictSpecifier: RestrictSpecifier): Visitor = leaveAST(restrictSpecifier)

  def leave(using Trace)(@unused threadSpecifier: ThreadSpecifier): Visitor = leaveAST(threadSpecifier)

  def leave(using Trace)(@unused staticSpecifier: StaticSpecifier): Visitor = leaveAST(staticSpecifier)

  def leave(using Trace)(@unused attribute: Attribute): Visitor = leaveAbstractAST(attribute)

  def leave(using Trace)(@unused atomicAttribute: AtomicAttribute): Visitor = leaveAST(atomicAttribute)

  def leave(using Trace)(@unused attributeSequence: AttributeSequence): Visitor = leaveAST(attributeSequence)

  def leave(using Trace)(@unused compoundAttribute: CompoundAttribute): Visitor = leaveAST(compoundAttribute)

  def leave(using Trace)(@unused declaration: Declaration): Visitor = leaveAST(declaration)

  def leave(using Trace)(@unused initDeclarator: InitDeclarator): Visitor = leaveAbstractAST(initDeclarator)

  def leave(using Trace)(@unused initDeclaratorI: InitDeclaratorI): Visitor = leaveAST(initDeclaratorI)

  def leave(using Trace)(@unused initDeclaratorE: InitDeclaratorE): Visitor = leaveAST(initDeclaratorE)

  def leave(using Trace)(@unused abstractDeclarator: AbstractDeclarator): Visitor = leaveAbstractAST(abstractDeclarator)

  def leave(using Trace)(@unused declarator: Declarator): Visitor = leaveAbstractAST(declarator)

  def leave(using Trace)(@unused atomicNamedDeclarator: AtomicNamedDeclarator): Visitor = leaveAST(atomicNamedDeclarator)

  def leave(using Trace)(@unused nestedNamedDeclarator: NestedNamedDeclarator): Visitor = leaveAST(nestedNamedDeclarator)

  def leave(using Trace)(@unused atomicAbstractDeclarator: AtomicAbstractDeclarator): Visitor = leaveAST(atomicAbstractDeclarator)

  def leave(using Trace)(@unused nestedAbstractDeclarator: NestedAbstractDeclarator): Visitor = leaveAST(nestedAbstractDeclarator)

  def leave(using Trace)(@unused declaratorExtension: DeclaratorExtension): Visitor = leaveAbstractAST(declaratorExtension)

  def leave(using Trace)(@unused declaratorAbstrExtension: DeclaratorAbstrExtension): Visitor = leaveAbstractAST(declaratorAbstrExtension)

  def leave(using Trace)(@unused declIdentifierList: DeclIdentifierList): Visitor = leaveAST(declIdentifierList)

  def leave(using Trace)(@unused declParameterDeclList: DeclParameterDeclList): Visitor = leaveAST(declParameterDeclList)

  def leave(using Trace)(@unused declArrayAccess: DeclArrayAccess): Visitor = leaveAST(declArrayAccess)

  def leave(using Trace)(@unused initializer: Initializer): Visitor = leaveAST(initializer)

  def leave(using Trace)(@unused pointer: Pointer): Visitor = leaveAST(pointer)

  def leave(using Trace)(@unused parameterDeclaration: ParameterDeclaration): Visitor = leaveAbstractAST(parameterDeclaration)

  def leave(using Trace)(@unused plainParameterDeclaration: PlainParameterDeclaration): Visitor = leaveAST(plainParameterDeclaration)

  def leave(using Trace)(@unused parameterDeclarationD: ParameterDeclarationD): Visitor = leaveAST(parameterDeclarationD)

  def leave(using Trace)(@unused parameterDeclarationAD: ParameterDeclarationAD): Visitor = leaveAST(parameterDeclarationAD)

  def leave(using Trace)(@unused oldParameterDeclaration: OldParameterDeclaration): Visitor = leaveAbstractAST(oldParameterDeclaration)

  def leave(using Trace)(@unused varArgs: VarArgs): Visitor = leaveAST(varArgs)

  def leave(using Trace)(@unused enumSpecifier: EnumSpecifier): Visitor = leaveAST(enumSpecifier)

  def leave(using Trace)(@unused enumerator: Enumerator): Visitor = leaveAST(enumerator)

  def leave(using Trace)(@unused structOrUnionSpecifier: StructOrUnionSpecifier): Visitor = leaveAST(structOrUnionSpecifier)

  def leave(using Trace)(@unused structDeclaration: StructDeclaration): Visitor = leaveAST(structDeclaration)

  def leave(using Trace)(@unused structDecl: StructDecl): Visitor = leaveAbstractAST(structDecl)

  def leave(using Trace)(@unused structDeclarator: StructDeclarator): Visitor = leaveAST(structDeclarator)

  def leave(using Trace)(@unused structInitializer: StructInitializer): Visitor = leaveAST(structInitializer)

  def leave(using Trace)(@unused asmExpr: AsmExpr): Visitor = leaveAST(asmExpr)

  def leave(using Trace)(@unused functionDef: FunctionDef): Visitor = leaveAST(functionDef)

  def leave(using Trace)(@unused nestedFunctionDef: NestedFunctionDef): Visitor = leaveAST(nestedFunctionDef)

  def leave(using Trace)(@unused externalDef: ExternalDef): Visitor = leaveAbstractAST(externalDef)

  def leave(using Trace)(@unused emptyExternalDef: EmptyExternalDef): Visitor = leaveAST(emptyExternalDef)

  def leave(using Trace)(@unused typelessDeclaration: TypelessDeclaration): Visitor = leaveAST(typelessDeclaration)

  def leave(using Trace)(@unused typeName: TypeName): Visitor = leaveAST(typeName)

  def leave(using Trace)(@unused translationUnit: TranslationUnit): Visitor = leaveAST(translationUnit)

  def leave(using Trace)(@unused attributeSpecifier: AttributeSpecifier): Visitor = leaveAbstractAST(attributeSpecifier)

  def leave(using Trace)(@unused gnuAttributeSpecifier: GnuAttributeSpecifier): Visitor = leaveAST(gnuAttributeSpecifier)

  def leave(using Trace)(@unused asmAttributeSpecifier: AsmAttributeSpecifier): Visitor = leaveAST(asmAttributeSpecifier)

  def leave(using Trace)(@unused lcurlyInitializer: LcurlyInitializer): Visitor = leaveAST(lcurlyInitializer)

  def leave(using Trace)(@unused alignOfExprT: AlignOfExprT): Visitor = leaveAST(alignOfExprT)

  def leave(using Trace)(@unused alignOfExprU: AlignOfExprU): Visitor = leaveAST(alignOfExprU)

  def leave(using Trace)(@unused gnuAsmExpr: GnuAsmExpr): Visitor = leaveAST(gnuAsmExpr)

  def leave(using Trace)(@unused rangeExpr: RangeExpr): Visitor = leaveAST(rangeExpr)

  def leave(using Trace)(@unused typeOfSpecifierT: TypeOfSpecifierT): Visitor = leaveAST(typeOfSpecifierT)

  def leave(using Trace)(@unused typeOfSpecifierU: TypeOfSpecifierU): Visitor = leaveAST(typeOfSpecifierU)

  def leave(using Trace)(@unused initializerElementLabel: InitializerElementLabel): Visitor = leaveAbstractAST(initializerElementLabel)

  def leave(using Trace)(@unused initializerArrayDesignator: InitializerArrayDesignator): Visitor = leaveAST(initializerArrayDesignator)

  def leave(using Trace)(@unused initializerDesignatorC: InitializerDesignatorC): Visitor = leaveAST(initializerDesignatorC)

  def leave(using Trace)(@unused initializerDesignatorD: InitializerDesignatorD): Visitor = leaveAST(initializerDesignatorD)

  def leave(using Trace)(@unused initializerAssigment: InitializerAssigment): Visitor = leaveAST(initializerAssigment)

  def leave(using Trace)(@unused builtinOffsetof: BuiltinOffsetof): Visitor = leaveAST(builtinOffsetof)

  def leave(using Trace)(@unused offsetofMemberDesignator: OffsetofMemberDesignator): Visitor = leaveAbstractAST(offsetofMemberDesignator)

  def leave(using Trace)(@unused offsetofMemberDesignatorID: OffsetofMemberDesignatorID): Visitor = leaveAST(offsetofMemberDesignatorID)

  def leave(using Trace)(@unused offsetofMemberDesignatorExpr: OffsetofMemberDesignatorExpr): Visitor = leaveAST(offsetofMemberDesignatorExpr)

  def leave(using Trace)(@unused builtinTypesCompatible: BuiltinTypesCompatible): Visitor = leaveAST(builtinTypesCompatible)

  def leave(using Trace)(@unused builtinVaArgs: BuiltinVaArgs): Visitor = leaveAST(builtinVaArgs)

  def leave(using Trace)(@unused compoundStatementExpr: CompoundStatementExpr): Visitor = leaveAST(compoundStatementExpr)

  def leave(using Trace)(@unused pragma: Pragma): Visitor = leaveAST(pragma)

  def leave(using Trace)(@unused any: Any): Visitor = leaveAny(any)

  def leave(using Trace)(@unused boolean: Boolean): Visitor = leaveAny(boolean)

  def leave(using Trace)(@unused string: String): Visitor = leaveAny(string)

  def leave(using Trace)(@unused featureExpr: FeatureExpr): Visitor = leaveAny(featureExpr)

  def leave[U](using Trace)(@unused opt: Opt[U]): Visitor = leaveAny(opt)

  def leave[U](using Trace)(@unused conditional: Conditional[U]): Visitor = leaveAny(conditional)

  def leave[U](using Trace)(@unused choice: Choice[U]): Visitor = leaveAny(choice)

  def leave[U](using Trace)(@unused one: One[U]): Visitor = leaveAny(one)

  def leave[U](using Trace)(@unused iterableOnce: IterableOnce[U]): Visitor = leaveAny(iterableOnce)

  def visit(using Trace)(x: Expr): Visitor = x match {
    case y: PrimaryExpr =>
      enter(x).visit(y).leave(x)
    case y: PostfixExpr =>
      enter(x).visit(y).leave(x)
    case y: UnaryExpr =>
      enter(x).visit(y).leave(x)
    case y: SizeOfExprT =>
      enter(x).visit(y).leave(x)
    case y: SizeOfExprU =>
      enter(x).visit(y).leave(x)
    case y: CastExpr =>
      enter(x).visit(y).leave(x)
    case y: PointerDerefExpr =>
      enter(x).visit(y).leave(x)
    case y: PointerCreationExpr =>
      enter(x).visit(y).leave(x)
    case y: UnaryOpExpr =>
      enter(x).visit(y).leave(x)
    case y: NAryExpr =>
      enter(x).visit(y).leave(x)
    case y: ConditionalExpr =>
      enter(x).visit(y).leave(x)
    case y: AssignExpr =>
      enter(x).visit(y).leave(x)
    case y: ExprList =>
      enter(x).visit(y).leave(x)
    case y: LcurlyInitializer =>
      enter(x).visit(y).leave(x)
    case y: AlignOfExprT =>
      enter(x).visit(y).leave(x)
    case y: AlignOfExprU =>
      enter(x).visit(y).leave(x)
    case y: GnuAsmExpr =>
      enter(x).visit(y).leave(x)
    case y: RangeExpr =>
      enter(x).visit(y).leave(x)
  }

  def visit(using Trace)(x: PrimaryExpr): Visitor = x match {
    case y: Id =>
      enter(x).visit(y).leave(x)
    case y: Constant =>
      enter(x).visit(y).leave(x)
    case y: StringLit =>
      enter(x).visit(y).leave(x)
    case y: BuiltinOffsetof =>
      enter(x).visit(y).leave(x)
    case y: BuiltinTypesCompatible =>
      enter(x).visit(y).leave(x)
    case y: BuiltinVaArgs =>
      enter(x).visit(y).leave(x)
    case y: CompoundStatementExpr =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: Id): Visitor = x match {
    case Id(name) => given Trace = trace + x

      enter(using trace)(x).visit(name).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: Constant): Visitor = x match {
    case Constant(value) => given Trace = trace + x

      enter(using trace)(x).visit(value).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: StringLit): Visitor = x match {
    case StringLit(name) => given Trace = trace + x

      enter(using trace)(x).visit(name).leave(using trace)(x)
  }

  def visit(using Trace)(x: PostfixSuffix): Visitor = x match {
    case y: SimplePostfixSuffix =>
      enter(x).visit(y).leave(x)
    case y: PointerPostfixSuffix =>
      enter(x).visit(y).leave(x)
    case y: FunctionCall =>
      enter(x).visit(y).leave(x)
    case y: ArrayAccess =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: SimplePostfixSuffix): Visitor = x match {
    case SimplePostfixSuffix(t) => given Trace = trace + x

      enter(using trace)(x).visit(t).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: PointerPostfixSuffix): Visitor = x match {
    case PointerPostfixSuffix(kind, id) => given Trace = trace + x

      enter(using trace)(x).visit(kind).visit(id).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: FunctionCall): Visitor = x match {
    case FunctionCall(params) => given Trace = trace + x

      enter(using trace)(x).visit(params).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: ArrayAccess): Visitor = x match {
    case ArrayAccess(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: PostfixExpr): Visitor = x match {
    case PostfixExpr(p, s) => given Trace = trace + x

      enter(using trace)(x).visit(p).visit(s).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: UnaryExpr): Visitor = x match {
    case UnaryExpr(kind, e) => given Trace = trace + x

      enter(using trace)(x).visit(kind).visit(e).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: SizeOfExprT): Visitor = x match {
    case SizeOfExprT(typeName) => given Trace = trace + x

      enter(using trace)(x).visit(typeName).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: SizeOfExprU): Visitor = x match {
    case SizeOfExprU(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: CastExpr): Visitor = x match {
    case CastExpr(typeName, expr) => given Trace = trace + x

      enter(using trace)(x).visit(typeName).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: PointerDerefExpr): Visitor = x match {
    case PointerDerefExpr(castExpr) => given Trace = trace + x

      enter(using trace)(x).visit(castExpr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: PointerCreationExpr): Visitor = x match {
    case PointerCreationExpr(castExpr) => given Trace = trace + x

      enter(using trace)(x).visit(castExpr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: UnaryOpExpr): Visitor = x match {
    case UnaryOpExpr(kind, castExpr) => given Trace = trace + x

      enter(using trace)(x).visit(kind).visit(castExpr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: NAryExpr): Visitor = x match {
    case NAryExpr(e, others) => given Trace = trace + x

      enter(using trace)(x).visit(e).visit(others).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: NArySubExpr): Visitor = x match {
    case NArySubExpr(op, e) => given Trace = trace + x

      enter(using trace)(x).visit(op).visit(e).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: ConditionalExpr): Visitor = x match {
    case ConditionalExpr(condition, thenExpr, elseExpr) => given Trace = trace + x

      enter(using trace)(x).visit(condition).visit(thenExpr).visit(elseExpr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: AssignExpr): Visitor = x match {
    case AssignExpr(target, operation, source) => given Trace = trace + x

      enter(using trace)(x).visit(target).visit(operation).visit(source).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: ExprList): Visitor = x match {
    case ExprList(exprs) => given Trace = trace + x

      enter(using trace)(x).visit(exprs).leave(using trace)(x)
  }

  def visit(using Trace)(x: Statement): Visitor = x match {
    case y: CompoundStatement =>
      enter(x).visit(y).leave(x)
    case y: EmptyStatement =>
      enter(x).visit(y).leave(x)
    case y: ExprStatement =>
      enter(x).visit(y).leave(x)
    case y: WhileStatement =>
      enter(x).visit(y).leave(x)
    case y: DoStatement =>
      enter(x).visit(y).leave(x)
    case y: ForStatement =>
      enter(x).visit(y).leave(x)
    case y: GotoStatement =>
      enter(x).visit(y).leave(x)
    case y: ContinueStatement =>
      enter(x).visit(y).leave(x)
    case y: BreakStatement =>
      enter(x).visit(y).leave(x)
    case y: ReturnStatement =>
      enter(x).visit(y).leave(x)
    case y: LabelStatement =>
      enter(x).visit(y).leave(x)
    case y: CaseStatement =>
      enter(x).visit(y).leave(x)
    case y: DefaultStatement =>
      enter(x).visit(y).leave(x)
    case y: IfStatement =>
      enter(x).visit(y).leave(x)
    case y: SwitchStatement =>
      enter(x).visit(y).leave(x)
    case y: CompoundDeclaration =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: CompoundStatement): Visitor = x match {
    case CompoundStatement(innerStatements) => given Trace = trace + x

      enter(using trace)(x).visit(innerStatements).leave(using trace)(x)
  }

  def visit(using Trace)(x: EmptyStatement): Visitor = x match {
    case EmptyStatement() =>
      enter(x).leave(x)
  }

  def visit(using trace: Trace)(x: ExprStatement): Visitor = x match {
    case ExprStatement(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: WhileStatement): Visitor = x match {
    case WhileStatement(expr, s) => given Trace = trace + x

      enter(using trace)(x).visit(expr).visit(s).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: DoStatement): Visitor = x match {
    case DoStatement(expr, s) => given Trace = trace + x

      enter(using trace)(x).visit(expr).visit(s).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: ForStatement): Visitor = x match {
    case ForStatement(expr1, expr2, expr3, s) => given Trace = trace + x

      enter(using trace)(x).visit(expr1).visit(expr2).visit(expr3).visit(s).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: GotoStatement): Visitor = x match {
    case GotoStatement(target) => given Trace = trace + x

      enter(using trace)(x).visit(target).leave(using trace)(x)
  }

  def visit(using Trace)(x: ContinueStatement): Visitor = x match {
    case ContinueStatement() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: BreakStatement): Visitor = x match {
    case BreakStatement() =>
      enter(x).leave(x)
  }

  def visit(using trace: Trace)(x: ReturnStatement): Visitor = x match {
    case ReturnStatement(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: LabelStatement): Visitor = x match {
    case LabelStatement(id, attribute) => given Trace = trace + x

      enter(using trace)(x).visit(id).visit(attribute).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: CaseStatement): Visitor = x match {
    case CaseStatement(c) => given Trace = trace + x

      enter(using trace)(x).visit(c).leave(using trace)(x)
  }

  def visit(using Trace)(x: DefaultStatement): Visitor = x match {
    case DefaultStatement() =>
      enter(x).leave(x)
  }

  def visit(using trace: Trace)(x: IfStatement): Visitor = x match {
    case IfStatement(condition, thenBranch, elifs, elseBranch) => given Trace = trace + x

      enter(using trace)(x).visit(condition).visit(thenBranch).visit(elifs).visit(elseBranch).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: ElifStatement): Visitor = x match {
    case ElifStatement(condition, thenBranch) => given Trace = trace + x

      enter(using trace)(x).visit(condition).visit(thenBranch).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: SwitchStatement): Visitor = x match {
    case SwitchStatement(expr, s) => given Trace = trace + x

      enter(using trace)(x).visit(expr).visit(s).leave(using trace)(x)
  }

  def visit(using Trace)(x: CompoundDeclaration): Visitor = x match {
    case y: DeclarationStatement =>
      enter(x).visit(y).leave(x)
    case y: LocalLabelDeclaration =>
      enter(x).visit(y).leave(x)
    case y: NestedFunctionDef =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: DeclarationStatement): Visitor = x match {
    case DeclarationStatement(decl) => given Trace = trace + x

      enter(using trace)(x).visit(decl).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: LocalLabelDeclaration): Visitor = x match {
    case LocalLabelDeclaration(ids) => given Trace = trace + x

      enter(using trace)(x).visit(ids).leave(using trace)(x)
  }

  def visit(using Trace)(x: Specifier): Visitor = x match {
    case y: TypeSpecifier =>
      enter(x).visit(y).leave(x)
    case y: OtherSpecifier =>
      enter(x).visit(y).leave(x)
    case y: TypedefSpecifier =>
      enter(x).visit(y).leave(x)
    case y: AttributeSpecifier =>
      enter(x).visit(y).leave(x)
  }

  def visit(using Trace)(x: TypeSpecifier): Visitor = x match {
    case y: PrimitiveTypeSpecifier =>
      enter(x).visit(y).leave(x)
    case y: OtherPrimitiveTypeSpecifier =>
      enter(x).visit(y).leave(x)
    case y: TypeDefTypeSpecifier =>
      enter(x).visit(y).leave(x)
    case y: SignedSpecifier =>
      enter(x).visit(y).leave(x)
    case y: UnsignedSpecifier =>
      enter(x).visit(y).leave(x)
    case y: EnumSpecifier =>
      enter(x).visit(y).leave(x)
    case y: StructOrUnionSpecifier =>
      enter(x).visit(y).leave(x)
    case y: TypeOfSpecifierT =>
      enter(x).visit(y).leave(x)
    case y: TypeOfSpecifierU =>
      enter(x).visit(y).leave(x)
  }

  def visit(using Trace)(x: PrimitiveTypeSpecifier): Visitor = x match {
    case y: VoidSpecifier =>
      enter(x).visit(y).leave(x)
    case y: ShortSpecifier =>
      enter(x).visit(y).leave(x)
    case y: IntSpecifier =>
      enter(x).visit(y).leave(x)
    case y: FloatSpecifier =>
      enter(x).visit(y).leave(x)
    case y: DoubleSpecifier =>
      enter(x).visit(y).leave(x)
    case y: LongSpecifier =>
      enter(x).visit(y).leave(x)
    case y: Int128Specifier =>
      enter(x).visit(y).leave(x)
    case y: CharSpecifier =>
      enter(x).visit(y).leave(x)
  }

  def visit(using Trace)(x: OtherSpecifier): Visitor = x match {
    case y: InlineSpecifier =>
      enter(x).visit(y).leave(x)
    case y: AutoSpecifier =>
      enter(x).visit(y).leave(x)
    case y: RegisterSpecifier =>
      enter(x).visit(y).leave(x)
    case y: VolatileSpecifier =>
      enter(x).visit(y).leave(x)
    case y: ExternSpecifier =>
      enter(x).visit(y).leave(x)
    case y: ConstSpecifier =>
      enter(x).visit(y).leave(x)
    case y: RestrictSpecifier =>
      enter(x).visit(y).leave(x)
    case y: ThreadSpecifier =>
      enter(x).visit(y).leave(x)
    case y: StaticSpecifier =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: OtherPrimitiveTypeSpecifier): Visitor = x match {
    case OtherPrimitiveTypeSpecifier(typeName) => given Trace = trace + x

      enter(using trace)(x).visit(typeName).leave(using trace)(x)
  }

  def visit(using Trace)(x: VoidSpecifier): Visitor = x match {
    case VoidSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: ShortSpecifier): Visitor = x match {
    case ShortSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: IntSpecifier): Visitor = x match {
    case IntSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: FloatSpecifier): Visitor = x match {
    case FloatSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: DoubleSpecifier): Visitor = x match {
    case DoubleSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: LongSpecifier): Visitor = x match {
    case LongSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: Int128Specifier): Visitor = x match {
    case Int128Specifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: CharSpecifier): Visitor = x match {
    case CharSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: TypedefSpecifier): Visitor = x match {
    case TypedefSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using trace: Trace)(x: TypeDefTypeSpecifier): Visitor = x match {
    case TypeDefTypeSpecifier(name) => given Trace = trace + x

      enter(using trace)(x).visit(name).leave(using trace)(x)
  }

  def visit(using Trace)(x: SignedSpecifier): Visitor = x match {
    case SignedSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: UnsignedSpecifier): Visitor = x match {
    case UnsignedSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: InlineSpecifier): Visitor = x match {
    case InlineSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: AutoSpecifier): Visitor = x match {
    case AutoSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: RegisterSpecifier): Visitor = x match {
    case RegisterSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: VolatileSpecifier): Visitor = x match {
    case VolatileSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: ExternSpecifier): Visitor = x match {
    case ExternSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: ConstSpecifier): Visitor = x match {
    case ConstSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: RestrictSpecifier): Visitor = x match {
    case RestrictSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: ThreadSpecifier): Visitor = x match {
    case ThreadSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: StaticSpecifier): Visitor = x match {
    case StaticSpecifier() =>
      enter(x).leave(x)
  }

  def visit(using Trace)(x: Attribute): Visitor = x match {
    case y: AtomicAttribute =>
      enter(x).visit(y).leave(x)
    case y: CompoundAttribute =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: AtomicAttribute): Visitor = x match {
    case AtomicAttribute(n) => given Trace = trace + x

      enter(using trace)(x).visit(n).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: AttributeSequence): Visitor = x match {
    case AttributeSequence(attributes) => given Trace = trace + x

      enter(using trace)(x).visit(attributes).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: CompoundAttribute): Visitor = x match {
    case CompoundAttribute(inner) => given Trace = trace + x

      enter(using trace)(x).visit(inner).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: Declaration): Visitor = x match {
    case Declaration(declSpecs, init) => given Trace = trace + x

      enter(using trace)(x).visit(declSpecs).visit(init).leave(using trace)(x)
  }

  def visit(using Trace)(x: InitDeclarator): Visitor = x match {
    case y: InitDeclaratorI =>
      enter(x).visit(y).leave(x)
    case y: InitDeclaratorE =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: InitDeclaratorI): Visitor = x match {
    case InitDeclaratorI(declarator, attributes, i) => given Trace = trace + x

      enter(using trace)(x).visit(declarator).visit(attributes).visit(i).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: InitDeclaratorE): Visitor = x match {
    case InitDeclaratorE(declarator, attributes, e) => given Trace = trace + x

      enter(using trace)(x).visit(declarator).visit(attributes).visit(e).leave(using trace)(x)
  }

  def visit(using Trace)(x: AbstractDeclarator): Visitor = x match {
    case y: AtomicAbstractDeclarator =>
      enter(x).visit(y).leave(x)
    case y: NestedAbstractDeclarator =>
      enter(x).visit(y).leave(x)
  }

  def visit(using Trace)(x: Declarator): Visitor = x match {
    case y: AtomicNamedDeclarator =>
      enter(x).visit(y).leave(x)
    case y: NestedNamedDeclarator =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: AtomicNamedDeclarator): Visitor = x match {
    case AtomicNamedDeclarator(pointers, id, extensions) => given Trace = trace + x

      enter(using trace)(x).visit(pointers).visit(id).visit(extensions).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: NestedNamedDeclarator): Visitor = x match {
    case NestedNamedDeclarator(pointers, nestedDecl, extensions, attr) => given Trace = trace + x

      enter(using trace)(x).visit(pointers).visit(nestedDecl).visit(extensions).visit(attr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: AtomicAbstractDeclarator): Visitor = x match {
    case AtomicAbstractDeclarator(pointers, extensions) => given Trace = trace + x

      enter(using trace)(x).visit(pointers).visit(extensions).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: NestedAbstractDeclarator): Visitor = x match {
    case NestedAbstractDeclarator(pointers, nestedDecl, extensions, attr) => given Trace = trace + x

      enter(using trace)(x).visit(pointers).visit(nestedDecl).visit(extensions).visit(attr).leave(using trace)(x)
  }

  def visit(using Trace)(x: DeclaratorExtension): Visitor = x match {
    case y: DeclaratorAbstrExtension =>
      enter(x).visit(y).leave(x)
    case y: DeclIdentifierList =>
      enter(x).visit(y).leave(x)
  }

  def visit(using Trace)(x: DeclaratorAbstrExtension): Visitor = x match {
    case y: DeclParameterDeclList =>
      enter(x).visit(y).leave(x)
    case y: DeclArrayAccess =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: DeclIdentifierList): Visitor = x match {
    case DeclIdentifierList(idList) => given Trace = trace + x

      enter(using trace)(x).visit(idList).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: DeclParameterDeclList): Visitor = x match {
    case DeclParameterDeclList(parameterDecls) => given Trace = trace + x

      enter(using trace)(x).visit(parameterDecls).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: DeclArrayAccess): Visitor = x match {
    case DeclArrayAccess(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: Initializer): Visitor = x match {
    case Initializer(initializerElementLabel, expr) => given Trace = trace + x

      enter(using trace)(x).visit(initializerElementLabel).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: Pointer): Visitor = x match {
    case Pointer(specifier) => given Trace = trace + x

      enter(using trace)(x).visit(specifier).leave(using trace)(x)
  }

  def visit(using Trace)(x: ParameterDeclaration): Visitor = x match {
    case y: PlainParameterDeclaration =>
      enter(x).visit(y).leave(x)
    case y: ParameterDeclarationD =>
      enter(x).visit(y).leave(x)
    case y: ParameterDeclarationAD =>
      enter(x).visit(y).leave(x)
    case y: VarArgs =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: PlainParameterDeclaration): Visitor = x match {
    case PlainParameterDeclaration(specifiers, attr) => given Trace = trace + x

      enter(using trace)(x).visit(specifiers).visit(attr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: ParameterDeclarationD): Visitor = x match {
    case ParameterDeclarationD(specifiers, decl, attr) => given Trace = trace + x

      enter(using trace)(x).visit(specifiers).visit(decl).visit(attr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: ParameterDeclarationAD): Visitor = x match {
    case ParameterDeclarationAD(specifiers, decl, attr) => given Trace = trace + x

      enter(using trace)(x).visit(specifiers).visit(decl).visit(attr).leave(using trace)(x)
  }

  def visit(using Trace)(x: OldParameterDeclaration): Visitor = x match {
    case y: Declaration =>
      enter(x).visit(y).leave(x)
    case y: VarArgs =>
      enter(x).visit(y).leave(x)
  }

  def visit(using Trace)(x: VarArgs): Visitor = x match {
    case VarArgs() =>
      enter(x).leave(x)
  }

  def visit(using trace: Trace)(x: EnumSpecifier): Visitor = x match {
    case EnumSpecifier(id, enumerators) => given Trace = trace + x

      enter(using trace)(x).visit(id).visit(enumerators).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: Enumerator): Visitor = x match {
    case Enumerator(id, assignment) => given Trace = trace + x

      enter(using trace)(x).visit(id).visit(assignment).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: StructOrUnionSpecifier): Visitor = x match {
    case StructOrUnionSpecifier(isUnion, id, enumerators, attributesBeforeBody, attributesAfterBody) => given Trace = trace + x

      enter(using trace)(x).visit(isUnion).visit(id).visit(enumerators).visit(attributesBeforeBody).visit(attributesAfterBody).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: StructDeclaration): Visitor = x match {
    case StructDeclaration(qualifierList, declaratorList) => given Trace = trace + x

      enter(using trace)(x).visit(qualifierList).visit(declaratorList).leave(using trace)(x)
  }

  def visit(using Trace)(x: StructDecl): Visitor = x match {
    case y: StructDeclarator =>
      enter(x).visit(y).leave(x)
    case y: StructInitializer =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: StructDeclarator): Visitor = x match {
    case StructDeclarator(decl, initializer, attributes) => given Trace = trace + x

      enter(using trace)(x).visit(decl).visit(initializer).visit(attributes).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: StructInitializer): Visitor = x match {
    case StructInitializer(expr, attributes) => given Trace = trace + x

      enter(using trace)(x).visit(expr).visit(attributes).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: AsmExpr): Visitor = x match {
    case AsmExpr(isVolatile, expr) => given Trace = trace + x

      enter(using trace)(x).visit(isVolatile).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: FunctionDef): Visitor = x match {
    case FunctionDef(specifiers, declarator, oldStyleParameters, stmt) => given Trace = trace + x

      enter(using trace)(x).visit(specifiers).visit(declarator).visit(oldStyleParameters).visit(stmt).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: NestedFunctionDef): Visitor = x match {
    case NestedFunctionDef(isAuto, specifiers, declarator, parameters, stmt) => given Trace = trace + x

      enter(using trace)(x).visit(isAuto).visit(specifiers).visit(declarator).visit(parameters).visit(stmt).leave(using trace)(x)
  }

  def visit(using Trace)(x: ExternalDef): Visitor = x match {
    case y: Declaration =>
      enter(x).visit(y).leave(x)
    case y: AsmExpr =>
      enter(x).visit(y).leave(x)
    case y: FunctionDef =>
      enter(x).visit(y).leave(x)
    case y: EmptyExternalDef =>
      enter(x).visit(y).leave(x)
    case y: TypelessDeclaration =>
      enter(x).visit(y).leave(x)
    case y: Pragma =>
      enter(x).visit(y).leave(x)
  }

  def visit(using Trace)(x: EmptyExternalDef): Visitor = x match {
    case EmptyExternalDef() =>
      enter(x).leave(x)
  }

  def visit(using trace: Trace)(x: TypelessDeclaration): Visitor = x match {
    case TypelessDeclaration(declList) => given Trace = trace + x

      enter(using trace)(x).visit(declList).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: TypeName): Visitor = x match {
    case TypeName(specifiers, decl) => given Trace = trace + x

      enter(using trace)(x).visit(specifiers).visit(decl).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: TranslationUnit): Visitor = x match {
    case TranslationUnit(defs) => given Trace = trace + x

      enter(using trace)(x).visit(defs).leave(using trace)(x)
  }

  def visit(using Trace)(x: AttributeSpecifier): Visitor = x match {
    case y: GnuAttributeSpecifier =>
      enter(x).visit(y).leave(x)
    case y: AsmAttributeSpecifier =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: GnuAttributeSpecifier): Visitor = x match {
    case GnuAttributeSpecifier(attributeList) => given Trace = trace + x

      enter(using trace)(x).visit(attributeList).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: AsmAttributeSpecifier): Visitor = x match {
    case AsmAttributeSpecifier(stringConst) => given Trace = trace + x

      enter(using trace)(x).visit(stringConst).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: LcurlyInitializer): Visitor = x match {
    case LcurlyInitializer(inits) => given Trace = trace + x

      enter(using trace)(x).visit(inits).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: AlignOfExprT): Visitor = x match {
    case AlignOfExprT(typeName) => given Trace = trace + x

      enter(using trace)(x).visit(typeName).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: AlignOfExprU): Visitor = x match {
    case AlignOfExprU(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: GnuAsmExpr): Visitor = x match {
    case GnuAsmExpr(isVolatile, isGoto, expr, stuff) => given Trace = trace + x

      enter(using trace)(x).visit(isVolatile).visit(isGoto).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: RangeExpr): Visitor = x match {
    case RangeExpr(from, to) => given Trace = trace + x

      enter(using trace)(x).visit(from).visit(to).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: TypeOfSpecifierT): Visitor = x match {
    case TypeOfSpecifierT(typeName) => given Trace = trace + x

      enter(using trace)(x).visit(typeName).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: TypeOfSpecifierU): Visitor = x match {
    case TypeOfSpecifierU(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using Trace)(x: InitializerElementLabel): Visitor = x match {
    case y: InitializerArrayDesignator =>
      enter(x).visit(y).leave(x)
    case y: InitializerDesignatorC =>
      enter(x).visit(y).leave(x)
    case y: InitializerDesignatorD =>
      enter(x).visit(y).leave(x)
    case y: InitializerAssigment =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: InitializerArrayDesignator): Visitor = x match {
    case InitializerArrayDesignator(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: InitializerDesignatorC): Visitor = x match {
    case InitializerDesignatorC(id) => given Trace = trace + x

      enter(using trace)(x).visit(id).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: InitializerDesignatorD): Visitor = x match {
    case InitializerDesignatorD(id) => given Trace = trace + x

      enter(using trace)(x).visit(id).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: InitializerAssigment): Visitor = x match {
    case InitializerAssigment(designators) => given Trace = trace + x

      enter(using trace)(x).visit(designators).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: BuiltinOffsetof): Visitor = x match {
    case BuiltinOffsetof(typeName, offsetofMemberDesignator) => given Trace = trace + x

      enter(using trace)(x).visit(typeName).visit(offsetofMemberDesignator).leave(using trace)(x)
  }

  def visit(using Trace)(x: OffsetofMemberDesignator): Visitor = x match {
    case y: OffsetofMemberDesignatorID =>
      enter(x).visit(y).leave(x)
    case y: OffsetofMemberDesignatorExpr =>
      enter(x).visit(y).leave(x)
  }

  def visit(using trace: Trace)(x: OffsetofMemberDesignatorID): Visitor = x match {
    case OffsetofMemberDesignatorID(id) => given Trace = trace + x

      enter(using trace)(x).visit(id).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: OffsetofMemberDesignatorExpr): Visitor = x match {
    case OffsetofMemberDesignatorExpr(expr) => given Trace = trace + x

      enter(using trace)(x).visit(expr).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: BuiltinTypesCompatible): Visitor = x match {
    case BuiltinTypesCompatible(typeName1, typeName2) => given Trace = trace + x

      enter(using trace)(x).visit(typeName1).visit(typeName2).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: BuiltinVaArgs): Visitor = x match {
    case BuiltinVaArgs(expr, typeName) => given Trace = trace + x

      enter(using trace)(x).visit(expr).visit(typeName).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: CompoundStatementExpr): Visitor = x match {
    case CompoundStatementExpr(compoundStatement) => given Trace = trace + x

      enter(using trace)(x).visit(compoundStatement).leave(using trace)(x)
  }

  def visit(using trace: Trace)(x: Pragma): Visitor = x match {
    case Pragma(command) => given Trace = trace + x

      enter(using trace)(x).visit(command).leave(using trace)(x)
  }

//  def visit(using Trace)(x: Any): Visitor2 = enter(x).leave(x)

  def visit(using Trace)(x: Boolean): Visitor = enter(x).leave(x)

  def visit(using Trace)(x: String): Visitor = enter(x).leave(x)

  def visit(using Trace)(x: FeatureExpr): Visitor = enter(x).leave(x)

  def visit[U](using trace: Trace)(x: Opt[U])(using f: F[U]): Visitor = x match {
    case Opt(condition, entry) =>
      f(using trace + (x, condition))(enter(using trace)(x).visit(using trace + x)(condition), entry).leave(using trace)(x)
  }

  def visit[U](using Trace)(x: Conditional[U])(using F[U]): Visitor = x match {
    case y: Choice[U] =>
      enter(x).visit(y).leave(x)
    case y: One[U] =>
      enter(x).visit(y).leave(x)
  }

  def visit[U](using trace: Trace)(x: Choice[U])(using f: F[Conditional[U]]): Visitor = x match {
    case Choice(condition, thenBranch, elseBranch) =>
      f(using trace + (x, condition.not()))(f(using trace + (x, condition))(enter(using trace)(x).visit(using trace + x)(condition), thenBranch), elseBranch).leave(using trace)(x)
  }

  def visit[U](using trace: Trace)(x: One[U])(using f: F[U]): Visitor = x match {
    case One(value) =>
      f(using trace + x)(enter(using trace)(x), value).leave(using trace)(x)
  }

  def visit[U](using Trace)(x: IterableOnce[U])(using f: F[U]): Visitor = x.iterator.foldLeft(enter(x))(f).leave(x)

  given F[Expr] = _.visit(_)

  given F[PrimaryExpr] = _.visit(_)

  given F[Id] = _.visit(_)

  given F[Constant] = _.visit(_)

  given F[StringLit] = _.visit(_)

  given F[PostfixSuffix] = _.visit(_)

  given F[SimplePostfixSuffix] = _.visit(_)

  given F[PointerPostfixSuffix] = _.visit(_)

  given F[FunctionCall] = _.visit(_)

  given F[ArrayAccess] = _.visit(_)

  given F[PostfixExpr] = _.visit(_)

  given F[UnaryExpr] = _.visit(_)

  given F[SizeOfExprT] = _.visit(_)

  given F[SizeOfExprU] = _.visit(_)

  given F[CastExpr] = _.visit(_)

  given F[PointerDerefExpr] = _.visit(_)

  given F[PointerCreationExpr] = _.visit(_)

  given F[UnaryOpExpr] = _.visit(_)

  given F[NAryExpr] = _.visit(_)

  given F[NArySubExpr] = _.visit(_)

  given F[ConditionalExpr] = _.visit(_)

  given F[AssignExpr] = _.visit(_)

  given F[ExprList] = _.visit(_)

  given F[Statement] = _.visit(_)

  given F[CompoundStatement] = _.visit(_)

  given F[EmptyStatement] = _.visit(_)

  given F[ExprStatement] = _.visit(_)

  given F[WhileStatement] = _.visit(_)

  given F[DoStatement] = _.visit(_)

  given F[ForStatement] = _.visit(_)

  given F[GotoStatement] = _.visit(_)

  given F[ContinueStatement] = _.visit(_)

  given F[BreakStatement] = _.visit(_)

  given F[ReturnStatement] = _.visit(_)

  given F[LabelStatement] = _.visit(_)

  given F[CaseStatement] = _.visit(_)

  given F[DefaultStatement] = _.visit(_)

  given F[IfStatement] = _.visit(_)

  given F[ElifStatement] = _.visit(_)

  given F[SwitchStatement] = _.visit(_)

  given F[CompoundDeclaration] = _.visit(_)

  given F[DeclarationStatement] = _.visit(_)

  given F[LocalLabelDeclaration] = _.visit(_)

  given F[Specifier] = _.visit(_)

  given F[TypeSpecifier] = _.visit(_)

  given F[PrimitiveTypeSpecifier] = _.visit(_)

  given F[OtherSpecifier] = _.visit(_)

  given F[OtherPrimitiveTypeSpecifier] = _.visit(_)

  given F[VoidSpecifier] = _.visit(_)

  given F[ShortSpecifier] = _.visit(_)

  given F[IntSpecifier] = _.visit(_)

  given F[FloatSpecifier] = _.visit(_)

  given F[DoubleSpecifier] = _.visit(_)

  given F[LongSpecifier] = _.visit(_)

  given F[Int128Specifier] = _.visit(_)

  given F[CharSpecifier] = _.visit(_)

  given F[TypedefSpecifier] = _.visit(_)

  given F[TypeDefTypeSpecifier] = _.visit(_)

  given F[SignedSpecifier] = _.visit(_)

  given F[UnsignedSpecifier] = _.visit(_)

  given F[InlineSpecifier] = _.visit(_)

  given F[AutoSpecifier] = _.visit(_)

  given F[RegisterSpecifier] = _.visit(_)

  given F[VolatileSpecifier] = _.visit(_)

  given F[ExternSpecifier] = _.visit(_)

  given F[ConstSpecifier] = _.visit(_)

  given F[RestrictSpecifier] = _.visit(_)

  given F[ThreadSpecifier] = _.visit(_)

  given F[StaticSpecifier] = _.visit(_)

  given F[Attribute] = _.visit(_)

  given F[AtomicAttribute] = _.visit(_)

  given F[AttributeSequence] = _.visit(_)

  given F[CompoundAttribute] = _.visit(_)

  given F[Declaration] = _.visit(_)

  given F[InitDeclarator] = _.visit(_)

  given F[InitDeclaratorI] = _.visit(_)

  given F[InitDeclaratorE] = _.visit(_)

  given F[AbstractDeclarator] = _.visit(_)

  given F[Declarator] = _.visit(_)

  given F[AtomicNamedDeclarator] = _.visit(_)

  given F[NestedNamedDeclarator] = _.visit(_)

  given F[AtomicAbstractDeclarator] = _.visit(_)

  given F[NestedAbstractDeclarator] = _.visit(_)

  given F[DeclaratorExtension] = _.visit(_)

  given F[DeclaratorAbstrExtension] = _.visit(_)

  given F[DeclIdentifierList] = _.visit(_)

  given F[DeclParameterDeclList] = _.visit(_)

  given F[DeclArrayAccess] = _.visit(_)

  given F[Initializer] = _.visit(_)

  given F[Pointer] = _.visit(_)

  given F[ParameterDeclaration] = _.visit(_)

  given F[PlainParameterDeclaration] = _.visit(_)

  given F[ParameterDeclarationD] = _.visit(_)

  given F[ParameterDeclarationAD] = _.visit(_)

  given F[OldParameterDeclaration] = _.visit(_)

  given F[VarArgs] = _.visit(_)

  given F[EnumSpecifier] = _.visit(_)

  given F[Enumerator] = _.visit(_)

  given F[StructOrUnionSpecifier] = _.visit(_)

  given F[StructDeclaration] = _.visit(_)

  given F[StructDecl] = _.visit(_)

  given F[StructDeclarator] = _.visit(_)

  given F[StructInitializer] = _.visit(_)

  given F[AsmExpr] = _.visit(_)

  given F[FunctionDef] = _.visit(_)

  given F[NestedFunctionDef] = _.visit(_)

  given F[ExternalDef] = _.visit(_)

  given F[EmptyExternalDef] = _.visit(_)

  given F[TypelessDeclaration] = _.visit(_)

  given F[TypeName] = _.visit(_)

  given F[TranslationUnit] = _.visit(_)

  given F[AttributeSpecifier] = _.visit(_)

  given F[GnuAttributeSpecifier] = _.visit(_)

  given F[AsmAttributeSpecifier] = _.visit(_)

  given F[LcurlyInitializer] = _.visit(_)

  given F[AlignOfExprT] = _.visit(_)

  given F[AlignOfExprU] = _.visit(_)

  given F[GnuAsmExpr] = _.visit(_)

  given F[RangeExpr] = _.visit(_)

  given F[TypeOfSpecifierT] = _.visit(_)

  given F[TypeOfSpecifierU] = _.visit(_)

  given F[InitializerElementLabel] = _.visit(_)

  given F[InitializerArrayDesignator] = _.visit(_)

  given F[InitializerDesignatorC] = _.visit(_)

  given F[InitializerDesignatorD] = _.visit(_)

  given F[InitializerAssigment] = _.visit(_)

  given F[BuiltinOffsetof] = _.visit(_)

  given F[OffsetofMemberDesignator] = _.visit(_)

  given F[OffsetofMemberDesignatorID] = _.visit(_)

  given F[OffsetofMemberDesignatorExpr] = _.visit(_)

  given F[BuiltinTypesCompatible] = _.visit(_)

  given F[BuiltinVaArgs] = _.visit(_)

  given F[CompoundStatementExpr] = _.visit(_)

  given F[Pragma] = _.visit(_)

//  given F[Any] = _.visit(_)

  given F[Boolean] = _.visit(_)

  given F[String] = _.visit(_)

  given F[FeatureExpr] = _.visit(_)

  given[U](using F[U]): F[Opt[U]] = _.visit(_)

  given[U](using F[U]): F[Conditional[U]] = _.visit(_)

  given[U](using F[U]): F[Choice[U]] = _.visit(_)

  given[U](using F[U]): F[One[U]] = _.visit(_)

  given[U](using F[U]): F[IterableOnce[U]] = _.visit(_)
}

