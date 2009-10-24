/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BeeLangFactoryImpl extends EFactoryImpl implements BeeLangFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static BeeLangFactory init()
  {
    try
    {
      BeeLangFactory theBeeLangFactory = (BeeLangFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/b3/BeeLang"); 
      if (theBeeLangFactory != null)
      {
        return theBeeLangFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new BeeLangFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeeLangFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case BeeLangPackage.BEE_MODEL: return createBeeModel();
      case BeeLangPackage.IMPORT: return createImport();
      case BeeLangPackage.BUILD_UNIT: return createBuildUnit();
      case BeeLangPackage.PROVIDED_CAPABILITY: return createProvidedCapability();
      case BeeLangPackage.REQUIRED_CAPABILITY: return createRequiredCapability();
      case BeeLangPackage.PROPERTY_STATEMENTS: return createPropertyStatements();
      case BeeLangPackage.PROPERTY_STATEMENT: return createPropertyStatement();
      case BeeLangPackage.PROPERTY_EXPRESSION: return createPropertyExpression();
      case BeeLangPackage.SYNCHRONIZATION: return createSynchronization();
      case BeeLangPackage.PATH_GROUP: return createPathGroup();
      case BeeLangPackage.PATH_EXPRESSION: return createPathExpression();
      case BeeLangPackage.EXPR_STATEMENT: return createExprStatement();
      case BeeLangPackage.PREREQUISITE: return createPrerequisite();
      case BeeLangPackage.CLOSURE: return createClosure();
      case BeeLangPackage.PREREQUISITE_ENTRY: return createPrerequisiteEntry();
      case BeeLangPackage.PART_IN_SELF: return createPartInSelf();
      case BeeLangPackage.CAPABILITY_REFERENCED_PART: return createCapabilityReferencedPart();
      case BeeLangPackage.COMPOUND_REFERENCES: return createCompoundReferences();
      case BeeLangPackage.PART: return createPart();
      case BeeLangPackage.PARAMETER: return createParameter();
      case BeeLangPackage.LAYOUT: return createLayout();
      case BeeLangPackage.ACTION_INPUT_GROUP: return createActionInputGroup();
      case BeeLangPackage.REPOSITORY_CONFIGURATION: return createRepositoryConfiguration();
      case BeeLangPackage.NAMED_ADVICE: return createNamedAdvice();
      case BeeLangPackage.ADVICE: return createAdvice();
      case BeeLangPackage.COMPOUND_ADVICE: return createCompoundAdvice();
      case BeeLangPackage.ADVICE_STATEMENT: return createAdviceStatement();
      case BeeLangPackage.ADVICE_PATH: return createAdvicePath();
      case BeeLangPackage.ADVICE_PATH_ELEMENT: return createAdvicePathElement();
      case BeeLangPackage.ADVICE_PATH_CHILDREN: return createAdvicePathChildren();
      case BeeLangPackage.FILTER: return createFilter();
      case BeeLangPackage.PRE_CONDITION_ASSERT: return createPreConditionAssert();
      case BeeLangPackage.POST_CONDITION_ASSERT: return createPostConditionAssert();
      case BeeLangPackage.ASSERTION_EXPRESSION: return createAssertionExpression();
      case BeeLangPackage.STATEMENT: return createStatement();
      case BeeLangPackage.BREAK_STATEMENT: return createBreakStatement();
      case BeeLangPackage.CONTINUE_STATEMENT: return createContinueStatement();
      case BeeLangPackage.COMPOUND_STATEMENT: return createCompoundStatement();
      case BeeLangPackage.STATEMENTS: return createStatements();
      case BeeLangPackage.WHILE_STATEMENT: return createWhileStatement();
      case BeeLangPackage.SWITCH_STATEMENT: return createSwitchStatement();
      case BeeLangPackage.CASE: return createCase();
      case BeeLangPackage.FOR_STATEMENT: return createForStatement();
      case BeeLangPackage.DO_WHILE_STATEMENT: return createDoWhileStatement();
      case BeeLangPackage.RETURN_STATEMENT: return createReturnStatement();
      case BeeLangPackage.FUNCTION_STATEMENT: return createFunctionStatement();
      case BeeLangPackage.IF_STATEMENT: return createIfStatement();
      case BeeLangPackage.LABEL_STATEMENT: return createLabelStatement();
      case BeeLangPackage.THROW_STATEMENT: return createThrowStatement();
      case BeeLangPackage.TRY_CATCH_STATEMENT: return createTryCatchStatement();
      case BeeLangPackage.CATCH_BLOCK: return createCatchBlock();
      case BeeLangPackage.FINALLY_BLOCK: return createFinallyBlock();
      case BeeLangPackage.WITH_STATEMENT: return createWithStatement();
      case BeeLangPackage.SYNTAX_ELEMENT: return createSyntaxElement();
      case BeeLangPackage.VAR_EXPRESSION_LIST: return createVarExpressionList();
      case BeeLangPackage.EXPRESSION: return createExpression();
      case BeeLangPackage.VAR_EXPRESSION: return createVarExpression();
      case BeeLangPackage.VALUE_EXPRESSION: return createValueExpression();
      case BeeLangPackage.OPERATION_CALL: return createOperationCall();
      case BeeLangPackage.PROPERTY_VALUE: return createPropertyValue();
      case BeeLangPackage.VARIABLE_VALUE: return createVariableValue();
      case BeeLangPackage.KEYWORD_VARIABLES: return createKeywordVariables();
      case BeeLangPackage.CONSTRUCTOR_CALL_EXPRESSION: return createConstructorCallExpression();
      case BeeLangPackage.LITERAL: return createLiteral();
      case BeeLangPackage.LITERAL_ARRAY: return createLiteralArray();
      case BeeLangPackage.LITERAL_OBJECT: return createLiteralObject();
      case BeeLangPackage.LITERAL_FUNCTION: return createLiteralFunction();
      case BeeLangPackage.FEATURE: return createFeature();
      case BeeLangPackage.VALUE_LITERAL: return createValueLiteral();
      case BeeLangPackage.BOOLEAN_LITERAL: return createBooleanLiteral();
      case BeeLangPackage.INTEGER_LITERAL: return createIntegerLiteral();
      case BeeLangPackage.NULL_LITERAL: return createNullLiteral();
      case BeeLangPackage.VOID_LITERAL: return createVoidLiteral();
      case BeeLangPackage.STRING_LITERAL: return createStringLiteral();
      case BeeLangPackage.REAL_LITERAL: return createRealLiteral();
      case BeeLangPackage.REGEXP_LITERAL: return createRegexpLiteral();
      case BeeLangPackage.ASSIGNMENT_OPERATION: return createAssignmentOperation();
      case BeeLangPackage.IF_EXPRESSION: return createIfExpression();
      case BeeLangPackage.BOOLEAN_OPERATION: return createBooleanOperation();
      case BeeLangPackage.POST_OP_CALL: return createPostOpCall();
      case BeeLangPackage.AT_CALL: return createAtCall();
      case BeeLangPackage.FEATURE_CALL: return createFeatureCall();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case BeeLangPackage.VISIBILITY:
        return createVisibilityFromString(eDataType, initialValue);
      case BeeLangPackage.ASSERTION_SCOPE:
        return createAssertionScopeFromString(eDataType, initialValue);
      case BeeLangPackage.EXECUTION_MODE:
        return createExecutionModeFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case BeeLangPackage.VISIBILITY:
        return convertVisibilityToString(eDataType, instanceValue);
      case BeeLangPackage.ASSERTION_SCOPE:
        return convertAssertionScopeToString(eDataType, instanceValue);
      case BeeLangPackage.EXECUTION_MODE:
        return convertExecutionModeToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeeModel createBeeModel()
  {
    BeeModelImpl beeModel = new BeeModelImpl();
    return beeModel;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Import createImport()
  {
    ImportImpl import_ = new ImportImpl();
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BuildUnit createBuildUnit()
  {
    BuildUnitImpl buildUnit = new BuildUnitImpl();
    return buildUnit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ProvidedCapability createProvidedCapability()
  {
    ProvidedCapabilityImpl providedCapability = new ProvidedCapabilityImpl();
    return providedCapability;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RequiredCapability createRequiredCapability()
  {
    RequiredCapabilityImpl requiredCapability = new RequiredCapabilityImpl();
    return requiredCapability;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyStatements createPropertyStatements()
  {
    PropertyStatementsImpl propertyStatements = new PropertyStatementsImpl();
    return propertyStatements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyStatement createPropertyStatement()
  {
    PropertyStatementImpl propertyStatement = new PropertyStatementImpl();
    return propertyStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyExpression createPropertyExpression()
  {
    PropertyExpressionImpl propertyExpression = new PropertyExpressionImpl();
    return propertyExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Synchronization createSynchronization()
  {
    SynchronizationImpl synchronization = new SynchronizationImpl();
    return synchronization;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PathGroup createPathGroup()
  {
    PathGroupImpl pathGroup = new PathGroupImpl();
    return pathGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PathExpression createPathExpression()
  {
    PathExpressionImpl pathExpression = new PathExpressionImpl();
    return pathExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExprStatement createExprStatement()
  {
    ExprStatementImpl exprStatement = new ExprStatementImpl();
    return exprStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Prerequisite createPrerequisite()
  {
    PrerequisiteImpl prerequisite = new PrerequisiteImpl();
    return prerequisite;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Closure createClosure()
  {
    ClosureImpl closure = new ClosureImpl();
    return closure;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PrerequisiteEntry createPrerequisiteEntry()
  {
    PrerequisiteEntryImpl prerequisiteEntry = new PrerequisiteEntryImpl();
    return prerequisiteEntry;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PartInSelf createPartInSelf()
  {
    PartInSelfImpl partInSelf = new PartInSelfImpl();
    return partInSelf;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CapabilityReferencedPart createCapabilityReferencedPart()
  {
    CapabilityReferencedPartImpl capabilityReferencedPart = new CapabilityReferencedPartImpl();
    return capabilityReferencedPart;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundReferences createCompoundReferences()
  {
    CompoundReferencesImpl compoundReferences = new CompoundReferencesImpl();
    return compoundReferences;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Part createPart()
  {
    PartImpl part = new PartImpl();
    return part;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Parameter createParameter()
  {
    ParameterImpl parameter = new ParameterImpl();
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Layout createLayout()
  {
    LayoutImpl layout = new LayoutImpl();
    return layout;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ActionInputGroup createActionInputGroup()
  {
    ActionInputGroupImpl actionInputGroup = new ActionInputGroupImpl();
    return actionInputGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RepositoryConfiguration createRepositoryConfiguration()
  {
    RepositoryConfigurationImpl repositoryConfiguration = new RepositoryConfigurationImpl();
    return repositoryConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedAdvice createNamedAdvice()
  {
    NamedAdviceImpl namedAdvice = new NamedAdviceImpl();
    return namedAdvice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Advice createAdvice()
  {
    AdviceImpl advice = new AdviceImpl();
    return advice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundAdvice createCompoundAdvice()
  {
    CompoundAdviceImpl compoundAdvice = new CompoundAdviceImpl();
    return compoundAdvice;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AdviceStatement createAdviceStatement()
  {
    AdviceStatementImpl adviceStatement = new AdviceStatementImpl();
    return adviceStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AdvicePath createAdvicePath()
  {
    AdvicePathImpl advicePath = new AdvicePathImpl();
    return advicePath;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AdvicePathElement createAdvicePathElement()
  {
    AdvicePathElementImpl advicePathElement = new AdvicePathElementImpl();
    return advicePathElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AdvicePathChildren createAdvicePathChildren()
  {
    AdvicePathChildrenImpl advicePathChildren = new AdvicePathChildrenImpl();
    return advicePathChildren;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Filter createFilter()
  {
    FilterImpl filter = new FilterImpl();
    return filter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PreConditionAssert createPreConditionAssert()
  {
    PreConditionAssertImpl preConditionAssert = new PreConditionAssertImpl();
    return preConditionAssert;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PostConditionAssert createPostConditionAssert()
  {
    PostConditionAssertImpl postConditionAssert = new PostConditionAssertImpl();
    return postConditionAssert;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AssertionExpression createAssertionExpression()
  {
    AssertionExpressionImpl assertionExpression = new AssertionExpressionImpl();
    return assertionExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Statement createStatement()
  {
    StatementImpl statement = new StatementImpl();
    return statement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BreakStatement createBreakStatement()
  {
    BreakStatementImpl breakStatement = new BreakStatementImpl();
    return breakStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContinueStatement createContinueStatement()
  {
    ContinueStatementImpl continueStatement = new ContinueStatementImpl();
    return continueStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundStatement createCompoundStatement()
  {
    CompoundStatementImpl compoundStatement = new CompoundStatementImpl();
    return compoundStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Statements createStatements()
  {
    StatementsImpl statements = new StatementsImpl();
    return statements;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WhileStatement createWhileStatement()
  {
    WhileStatementImpl whileStatement = new WhileStatementImpl();
    return whileStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SwitchStatement createSwitchStatement()
  {
    SwitchStatementImpl switchStatement = new SwitchStatementImpl();
    return switchStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Case createCase()
  {
    CaseImpl case_ = new CaseImpl();
    return case_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ForStatement createForStatement()
  {
    ForStatementImpl forStatement = new ForStatementImpl();
    return forStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public DoWhileStatement createDoWhileStatement()
  {
    DoWhileStatementImpl doWhileStatement = new DoWhileStatementImpl();
    return doWhileStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReturnStatement createReturnStatement()
  {
    ReturnStatementImpl returnStatement = new ReturnStatementImpl();
    return returnStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FunctionStatement createFunctionStatement()
  {
    FunctionStatementImpl functionStatement = new FunctionStatementImpl();
    return functionStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfStatement createIfStatement()
  {
    IfStatementImpl ifStatement = new IfStatementImpl();
    return ifStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LabelStatement createLabelStatement()
  {
    LabelStatementImpl labelStatement = new LabelStatementImpl();
    return labelStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ThrowStatement createThrowStatement()
  {
    ThrowStatementImpl throwStatement = new ThrowStatementImpl();
    return throwStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TryCatchStatement createTryCatchStatement()
  {
    TryCatchStatementImpl tryCatchStatement = new TryCatchStatementImpl();
    return tryCatchStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CatchBlock createCatchBlock()
  {
    CatchBlockImpl catchBlock = new CatchBlockImpl();
    return catchBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FinallyBlock createFinallyBlock()
  {
    FinallyBlockImpl finallyBlock = new FinallyBlockImpl();
    return finallyBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WithStatement createWithStatement()
  {
    WithStatementImpl withStatement = new WithStatementImpl();
    return withStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SyntaxElement createSyntaxElement()
  {
    SyntaxElementImpl syntaxElement = new SyntaxElementImpl();
    return syntaxElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VarExpressionList createVarExpressionList()
  {
    VarExpressionListImpl varExpressionList = new VarExpressionListImpl();
    return varExpressionList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression createExpression()
  {
    ExpressionImpl expression = new ExpressionImpl();
    return expression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VarExpression createVarExpression()
  {
    VarExpressionImpl varExpression = new VarExpressionImpl();
    return varExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValueExpression createValueExpression()
  {
    ValueExpressionImpl valueExpression = new ValueExpressionImpl();
    return valueExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OperationCall createOperationCall()
  {
    OperationCallImpl operationCall = new OperationCallImpl();
    return operationCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyValue createPropertyValue()
  {
    PropertyValueImpl propertyValue = new PropertyValueImpl();
    return propertyValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariableValue createVariableValue()
  {
    VariableValueImpl variableValue = new VariableValueImpl();
    return variableValue;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public KeywordVariables createKeywordVariables()
  {
    KeywordVariablesImpl keywordVariables = new KeywordVariablesImpl();
    return keywordVariables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstructorCallExpression createConstructorCallExpression()
  {
    ConstructorCallExpressionImpl constructorCallExpression = new ConstructorCallExpressionImpl();
    return constructorCallExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Literal createLiteral()
  {
    LiteralImpl literal = new LiteralImpl();
    return literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LiteralArray createLiteralArray()
  {
    LiteralArrayImpl literalArray = new LiteralArrayImpl();
    return literalArray;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LiteralObject createLiteralObject()
  {
    LiteralObjectImpl literalObject = new LiteralObjectImpl();
    return literalObject;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LiteralFunction createLiteralFunction()
  {
    LiteralFunctionImpl literalFunction = new LiteralFunctionImpl();
    return literalFunction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Feature createFeature()
  {
    FeatureImpl feature = new FeatureImpl();
    return feature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ValueLiteral createValueLiteral()
  {
    ValueLiteralImpl valueLiteral = new ValueLiteralImpl();
    return valueLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanLiteral createBooleanLiteral()
  {
    BooleanLiteralImpl booleanLiteral = new BooleanLiteralImpl();
    return booleanLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerLiteral createIntegerLiteral()
  {
    IntegerLiteralImpl integerLiteral = new IntegerLiteralImpl();
    return integerLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NullLiteral createNullLiteral()
  {
    NullLiteralImpl nullLiteral = new NullLiteralImpl();
    return nullLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VoidLiteral createVoidLiteral()
  {
    VoidLiteralImpl voidLiteral = new VoidLiteralImpl();
    return voidLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringLiteral createStringLiteral()
  {
    StringLiteralImpl stringLiteral = new StringLiteralImpl();
    return stringLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RealLiteral createRealLiteral()
  {
    RealLiteralImpl realLiteral = new RealLiteralImpl();
    return realLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RegexpLiteral createRegexpLiteral()
  {
    RegexpLiteralImpl regexpLiteral = new RegexpLiteralImpl();
    return regexpLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AssignmentOperation createAssignmentOperation()
  {
    AssignmentOperationImpl assignmentOperation = new AssignmentOperationImpl();
    return assignmentOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfExpression createIfExpression()
  {
    IfExpressionImpl ifExpression = new IfExpressionImpl();
    return ifExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanOperation createBooleanOperation()
  {
    BooleanOperationImpl booleanOperation = new BooleanOperationImpl();
    return booleanOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PostOpCall createPostOpCall()
  {
    PostOpCallImpl postOpCall = new PostOpCallImpl();
    return postOpCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AtCall createAtCall()
  {
    AtCallImpl atCall = new AtCallImpl();
    return atCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureCall createFeatureCall()
  {
    FeatureCallImpl featureCall = new FeatureCallImpl();
    return featureCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Visibility createVisibilityFromString(EDataType eDataType, String initialValue)
  {
    Visibility result = Visibility.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertVisibilityToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AssertionScope createAssertionScopeFromString(EDataType eDataType, String initialValue)
  {
    AssertionScope result = AssertionScope.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertAssertionScopeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExecutionMode createExecutionModeFromString(EDataType eDataType, String initialValue)
  {
    ExecutionMode result = ExecutionMode.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertExecutionModeToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeeLangPackage getBeeLangPackage()
  {
    return (BeeLangPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static BeeLangPackage getPackage()
  {
    return BeeLangPackage.eINSTANCE;
  }

} //BeeLangFactoryImpl
