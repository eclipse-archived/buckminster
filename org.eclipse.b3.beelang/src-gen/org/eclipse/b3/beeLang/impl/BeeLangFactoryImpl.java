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
      case BeeLangPackage.JAVA_IMPORTER: return createJavaImporter();
      case BeeLangPackage.NATIVE_IMPORTER: return createNativeImporter();
      case BeeLangPackage.BUILD_UNIT: return createBuildUnit();
      case BeeLangPackage.PROVIDED_CAPABILITY: return createProvidedCapability();
      case BeeLangPackage.REQUIRED_CAPABILITY: return createRequiredCapability();
      case BeeLangPackage.NAMED_PROPERTIES: return createNamedProperties();
      case BeeLangPackage.COMPOUND_PROPERTY_OPERATION: return createCompoundPropertyOperation();
      case BeeLangPackage.PROPERTY_OPERATION: return createPropertyOperation();
      case BeeLangPackage.FILTERED_PROPERTY_OPERATION: return createFilteredPropertyOperation();
      case BeeLangPackage.SET_PROPERTY_OPERATION: return createSetPropertyOperation();
      case BeeLangPackage.UNSET_PROPERTY_OPERATION: return createUnsetPropertyOperation();
      case BeeLangPackage.SYNCHRONIZATION: return createSynchronization();
      case BeeLangPackage.PATH_GROUP: return createPathGroup();
      case BeeLangPackage.PATH_VECTOR_ELEMENT: return createPathVectorElement();
      case BeeLangPackage.FILTERED_PATH_VECTOR: return createFilteredPathVector();
      case BeeLangPackage.PATH_VECTOR: return createPathVector();
      case BeeLangPackage.COMPOUND_PATH_VECTOR: return createCompoundPathVector();
      case BeeLangPackage.PREREQUISITE: return createPrerequisite();
      case BeeLangPackage.CLOSURE: return createClosure();
      case BeeLangPackage.PREREQUISITE_ENTRY: return createPrerequisiteEntry();
      case BeeLangPackage.PART_IN_SELF: return createPartInSelf();
      case BeeLangPackage.CAPABILITY_REFERENCED_PART: return createCapabilityReferencedPart();
      case BeeLangPackage.COMPOUND_REFERENCES: return createCompoundReferences();
      case BeeLangPackage.BUILD_METHOD: return createBuildMethod();
      case BeeLangPackage.PARAMETER: return createParameter();
      case BeeLangPackage.LAYOUT: return createLayout();
      case BeeLangPackage.GROUP: return createGroup();
      case BeeLangPackage.REPOSITORY_CONFIGURATION: return createRepositoryConfiguration();
      case BeeLangPackage.NAMED_ADVICE: return createNamedAdvice();
      case BeeLangPackage.ADVICE: return createAdvice();
      case BeeLangPackage.COMPOUND_ADVICE: return createCompoundAdvice();
      case BeeLangPackage.ADVICE_STATEMENT: return createAdviceStatement();
      case BeeLangPackage.ADVICE_PATH: return createAdvicePath();
      case BeeLangPackage.SELECTOR: return createSelector();
      case BeeLangPackage.NAME_SELECTOR: return createNameSelector();
      case BeeLangPackage.THIS_SELECTOR: return createThisSelector();
      case BeeLangPackage.PARENT_SELECTOR: return createParentSelector();
      case BeeLangPackage.CHILDREN_SELECTOR: return createChildrenSelector();
      case BeeLangPackage.ANCESTOR_SELECTOR: return createAncestorSelector();
      case BeeLangPackage.REGEXP_SELECTOR: return createRegexpSelector();
      case BeeLangPackage.FILTER: return createFilter();
      case BeeLangPackage.PRE_CONDITION: return createPreCondition();
      case BeeLangPackage.POST_CONDITION: return createPostCondition();
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
      case BeeLangPackage.VAR_EXPRESSION_LIST: return createVarExpressionList();
      case BeeLangPackage.EXPRESSION_LIST: return createExpressionList();
      case BeeLangPackage.EXPRESSION: return createExpression();
      case BeeLangPackage.UNARY_EXPRESSION: return createUnaryExpression();
      case BeeLangPackage.FEATURE: return createFeature();
      case BeeLangPackage.VERSION: return createVersion();
      case BeeLangPackage.VERSION_RANGE: return createVersionRange();
      case BeeLangPackage.VAR_EXPRESSION: return createVarExpression();
      case BeeLangPackage.ASSIGNMENT_OPERATION: return createAssignmentOperation();
      case BeeLangPackage.IF_EXPRESSION: return createIfExpression();
      case BeeLangPackage.BOOLEAN_OPERATION: return createBooleanOperation();
      case BeeLangPackage.OPERATION_CALL: return createOperationCall();
      case BeeLangPackage.RELATIONAL_OPERATION: return createRelationalOperation();
      case BeeLangPackage.SET_OPERATION_CALL: return createSetOperationCall();
      case BeeLangPackage.UNARY_OPERATION: return createUnaryOperation();
      case BeeLangPackage.AT_CALL: return createAtCall();
      case BeeLangPackage.FEATURE_CALL: return createFeatureCall();
      case BeeLangPackage.PROPERTY_VALUE: return createPropertyValue();
      case BeeLangPackage.VARIABLE_VALUE: return createVariableValue();
      case BeeLangPackage.KEYWORD_VARIABLE: return createKeywordVariable();
      case BeeLangPackage.CREATOR_CALL: return createCreatorCall();
      case BeeLangPackage.LITERAL_ARRAY: return createLiteralArray();
      case BeeLangPackage.LITERAL_OBJECT: return createLiteralObject();
      case BeeLangPackage.LITERAL_FUNCTION: return createLiteralFunction();
      case BeeLangPackage.BOOLEAN_LITERAL: return createBooleanLiteral();
      case BeeLangPackage.INTEGER_LITERAL: return createIntegerLiteral();
      case BeeLangPackage.NULL_LITERAL: return createNullLiteral();
      case BeeLangPackage.UNDEFINED_LITERAL: return createUndefinedLiteral();
      case BeeLangPackage.STRING_LITERAL: return createStringLiteral();
      case BeeLangPackage.REAL_LITERAL: return createRealLiteral();
      case BeeLangPackage.REGEXP_LITERAL: return createRegexpLiteral();
      case BeeLangPackage.QUERY_LITERAL: return createQueryLiteral();
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
      case BeeLangPackage.ASSIGNMENT_OPERATOR:
        return createAssignmentOperatorFromString(eDataType, initialValue);
      case BeeLangPackage.RELATIONAL_OPERATOR:
        return createRelationalOperatorFromString(eDataType, initialValue);
      case BeeLangPackage.SET_OPERATOR:
        return createSetOperatorFromString(eDataType, initialValue);
      case BeeLangPackage.VISIBILITY:
        return createVisibilityFromString(eDataType, initialValue);
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
      case BeeLangPackage.ASSIGNMENT_OPERATOR:
        return convertAssignmentOperatorToString(eDataType, instanceValue);
      case BeeLangPackage.RELATIONAL_OPERATOR:
        return convertRelationalOperatorToString(eDataType, instanceValue);
      case BeeLangPackage.SET_OPERATOR:
        return convertSetOperatorToString(eDataType, instanceValue);
      case BeeLangPackage.VISIBILITY:
        return convertVisibilityToString(eDataType, instanceValue);
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
  public JavaImporter createJavaImporter()
  {
    JavaImporterImpl javaImporter = new JavaImporterImpl();
    return javaImporter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NativeImporter createNativeImporter()
  {
    NativeImporterImpl nativeImporter = new NativeImporterImpl();
    return nativeImporter;
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
  public NamedProperties createNamedProperties()
  {
    NamedPropertiesImpl namedProperties = new NamedPropertiesImpl();
    return namedProperties;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundPropertyOperation createCompoundPropertyOperation()
  {
    CompoundPropertyOperationImpl compoundPropertyOperation = new CompoundPropertyOperationImpl();
    return compoundPropertyOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyOperation createPropertyOperation()
  {
    PropertyOperationImpl propertyOperation = new PropertyOperationImpl();
    return propertyOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FilteredPropertyOperation createFilteredPropertyOperation()
  {
    FilteredPropertyOperationImpl filteredPropertyOperation = new FilteredPropertyOperationImpl();
    return filteredPropertyOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetPropertyOperation createSetPropertyOperation()
  {
    SetPropertyOperationImpl setPropertyOperation = new SetPropertyOperationImpl();
    return setPropertyOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnsetPropertyOperation createUnsetPropertyOperation()
  {
    UnsetPropertyOperationImpl unsetPropertyOperation = new UnsetPropertyOperationImpl();
    return unsetPropertyOperation;
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
  public PathVectorElement createPathVectorElement()
  {
    PathVectorElementImpl pathVectorElement = new PathVectorElementImpl();
    return pathVectorElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FilteredPathVector createFilteredPathVector()
  {
    FilteredPathVectorImpl filteredPathVector = new FilteredPathVectorImpl();
    return filteredPathVector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PathVector createPathVector()
  {
    PathVectorImpl pathVector = new PathVectorImpl();
    return pathVector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CompoundPathVector createCompoundPathVector()
  {
    CompoundPathVectorImpl compoundPathVector = new CompoundPathVectorImpl();
    return compoundPathVector;
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
  public BuildMethod createBuildMethod()
  {
    BuildMethodImpl buildMethod = new BuildMethodImpl();
    return buildMethod;
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
  public Group createGroup()
  {
    GroupImpl group = new GroupImpl();
    return group;
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
  public Selector createSelector()
  {
    SelectorImpl selector = new SelectorImpl();
    return selector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NameSelector createNameSelector()
  {
    NameSelectorImpl nameSelector = new NameSelectorImpl();
    return nameSelector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ThisSelector createThisSelector()
  {
    ThisSelectorImpl thisSelector = new ThisSelectorImpl();
    return thisSelector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParentSelector createParentSelector()
  {
    ParentSelectorImpl parentSelector = new ParentSelectorImpl();
    return parentSelector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChildrenSelector createChildrenSelector()
  {
    ChildrenSelectorImpl childrenSelector = new ChildrenSelectorImpl();
    return childrenSelector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AncestorSelector createAncestorSelector()
  {
    AncestorSelectorImpl ancestorSelector = new AncestorSelectorImpl();
    return ancestorSelector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RegexpSelector createRegexpSelector()
  {
    RegexpSelectorImpl regexpSelector = new RegexpSelectorImpl();
    return regexpSelector;
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
  public PreCondition createPreCondition()
  {
    PreConditionImpl preCondition = new PreConditionImpl();
    return preCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PostCondition createPostCondition()
  {
    PostConditionImpl postCondition = new PostConditionImpl();
    return postCondition;
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
  public ExpressionList createExpressionList()
  {
    ExpressionListImpl expressionList = new ExpressionListImpl();
    return expressionList;
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
  public UnaryExpression createUnaryExpression()
  {
    UnaryExpressionImpl unaryExpression = new UnaryExpressionImpl();
    return unaryExpression;
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
  public Version createVersion()
  {
    VersionImpl version = new VersionImpl();
    return version;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VersionRange createVersionRange()
  {
    VersionRangeImpl versionRange = new VersionRangeImpl();
    return versionRange;
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
  public RelationalOperation createRelationalOperation()
  {
    RelationalOperationImpl relationalOperation = new RelationalOperationImpl();
    return relationalOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetOperationCall createSetOperationCall()
  {
    SetOperationCallImpl setOperationCall = new SetOperationCallImpl();
    return setOperationCall;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnaryOperation createUnaryOperation()
  {
    UnaryOperationImpl unaryOperation = new UnaryOperationImpl();
    return unaryOperation;
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
  public KeywordVariable createKeywordVariable()
  {
    KeywordVariableImpl keywordVariable = new KeywordVariableImpl();
    return keywordVariable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CreatorCall createCreatorCall()
  {
    CreatorCallImpl creatorCall = new CreatorCallImpl();
    return creatorCall;
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
  public UndefinedLiteral createUndefinedLiteral()
  {
    UndefinedLiteralImpl undefinedLiteral = new UndefinedLiteralImpl();
    return undefinedLiteral;
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
  public QueryLiteral createQueryLiteral()
  {
    QueryLiteralImpl queryLiteral = new QueryLiteralImpl();
    return queryLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AssignmentOperator createAssignmentOperatorFromString(EDataType eDataType, String initialValue)
  {
    AssignmentOperator result = AssignmentOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertAssignmentOperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RelationalOperator createRelationalOperatorFromString(EDataType eDataType, String initialValue)
  {
    RelationalOperator result = RelationalOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertRelationalOperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SetOperator createSetOperatorFromString(EDataType eDataType, String initialValue)
  {
    SetOperator result = SetOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertSetOperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
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
