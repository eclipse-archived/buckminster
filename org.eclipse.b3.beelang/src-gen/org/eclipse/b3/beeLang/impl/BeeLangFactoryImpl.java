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
      case BeeLangPackage.FILTERED_CAPABILITY: return createFilteredCapability();
      case BeeLangPackage.CAPABILITY: return createCapability();
      case BeeLangPackage.NAMED_PROPERTY_SET: return createNamedPropertySet();
      case BeeLangPackage.PROPERTY_SET: return createPropertySet();
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
      case BeeLangPackage.WITH_CLAUSE: return createWithClause();
      case BeeLangPackage.PREREQUISITE_ENTRY: return createPrerequisiteEntry();
      case BeeLangPackage.DIRECT_PART_REFEREMCE: return createDirectPartReferemce();
      case BeeLangPackage.CAPABILITY_REFERENCED_PART: return createCapabilityReferencedPart();
      case BeeLangPackage.COMPOUND_REFERENCES: return createCompoundReferences();
      case BeeLangPackage.BUILDER: return createBuilder();
      case BeeLangPackage.PARAMETER: return createParameter();
      case BeeLangPackage.PARAMETER_LIST: return createParameterList();
      case BeeLangPackage.PARAMETER_DECLARATION_LIST: return createParameterDeclarationList();
      case BeeLangPackage.PARAMETER_DECLARATION: return createParameterDeclaration();
      case BeeLangPackage.BUILDER_OUTPUT: return createBuilderOutput();
      case BeeLangPackage.BUILDER_INPUT: return createBuilderInput();
      case BeeLangPackage.REPOSITORY_CONFIGURATION: return createRepositoryConfiguration();
      case BeeLangPackage.REPOSITORY_DECLARATION: return createRepositoryDeclaration();
      case BeeLangPackage.RESOLUTION_STRATEGY: return createResolutionStrategy();
      case BeeLangPackage.CONTAINER_CONFIGURATION: return createContainerConfiguration();
      case BeeLangPackage.CONCERN: return createConcern();
      case BeeLangPackage.CONCERN_BLOCK: return createConcernBlock();
      case BeeLangPackage.QUERY: return createQuery();
      case BeeLangPackage.QUERY_PATH: return createQueryPath();
      case BeeLangPackage.SELECTOR: return createSelector();
      case BeeLangPackage.FILTER: return createFilter();
      case BeeLangPackage.PRE_CONDITION: return createPreCondition();
      case BeeLangPackage.POST_CONDITION: return createPostCondition();
      case BeeLangPackage.ASSERTION_EXPRESSION: return createAssertionExpression();
      case BeeLangPackage.EXPRESSION_LIST: return createExpressionList();
      case BeeLangPackage.STATMENT: return createStatment();
      case BeeLangPackage.STATEMENT: return createStatement();
      case BeeLangPackage.FUNCTION_OR_METHOD: return createFunctionOrMethod();
      case BeeLangPackage.TYPE_PARAM_DECLARATION: return createTypeParamDeclaration();
      case BeeLangPackage.EXPRESSION: return createExpression();
      case BeeLangPackage.TYPE_REF: return createTypeRef();
      case BeeLangPackage.SIMPLE_TYPE_REF: return createSimpleTypeRef();
      case BeeLangPackage.CLOSURE_TYPE_REF: return createClosureTypeRef();
      case BeeLangPackage.RULE_TYPE_PARAM: return createRuleTypeParam();
      case BeeLangPackage.RULE_TYPE_REF_PARAM: return createRuleTypeRefParam();
      case BeeLangPackage.WILDCARD_REF_PARAM: return createWildcardRefParam();
      case BeeLangPackage.UNARY_EXPRESSION: return createUnaryExpression();
      case BeeLangPackage.EXPRESION: return createExpresion();
      case BeeLangPackage.TRY_CATCH_EXPRESSION: return createTryCatchExpression();
      case BeeLangPackage.CATCH_BLOCK: return createCatchBlock();
      case BeeLangPackage.FINALLY_BLOCK: return createFinallyBlock();
      case BeeLangPackage.SWITCH_EXPRESSION: return createSwitchExpression();
      case BeeLangPackage.CASE: return createCase();
      case BeeLangPackage.IF_EXPRESSION_TAIL: return createIfExpressionTail();
      case BeeLangPackage.FEATURE_CALL: return createFeatureCall();
      case BeeLangPackage.OPERATION_CALL: return createOperationCall();
      case BeeLangPackage.CONTEXT: return createContext();
      case BeeLangPackage.CONTEXT_SELECTOR: return createContextSelector();
      case BeeLangPackage.EXPRESSION_SELECTOR: return createExpressionSelector();
      case BeeLangPackage.UNIT_SELECTOR: return createUnitSelector();
      case BeeLangPackage.CONTEXT_BLOCK: return createContextBlock();
      case BeeLangPackage.CLOSURE_EXPRESSION: return createClosureExpression();
      case BeeLangPackage.FEATURE: return createFeature();
      case BeeLangPackage.VERSION: return createVersion();
      case BeeLangPackage.VERSION_RANGE: return createVersionRange();
      case BeeLangPackage.VARARG_PARAMETER_DECLARATION: return createVarargParameterDeclaration();
      case BeeLangPackage.FUNCTION: return createFunction();
      case BeeLangPackage.METHOD: return createMethod();
      case BeeLangPackage.RETURN_EXPRESSION: return createReturnExpression();
      case BeeLangPackage.ASSIGNMENT_OPERATION: return createAssignmentOperation();
      case BeeLangPackage.VAR_DECLARATION: return createVarDeclaration();
      case BeeLangPackage.BOOLEAN_OPERATION: return createBooleanOperation();
      case BeeLangPackage.RELATIONAL_OPERATION: return createRelationalOperation();
      case BeeLangPackage.SET_OPERATION_CALL: return createSetOperationCall();
      case BeeLangPackage.UNARY_OPERATION: return createUnaryOperation();
      case BeeLangPackage.AT_CALL: return createAtCall();
      case BeeLangPackage.WITH_CLAUSE_EXPRESSION: return createWithClauseExpression();
      case BeeLangPackage.WITH_CONTEXT_EXPRESSION: return createWithContextExpression();
      case BeeLangPackage.WILDCARD_EXPRESSION: return createWildcardExpression();
      case BeeLangPackage.THROW_EXPRESSION: return createThrowExpression();
      case BeeLangPackage.BLOCK_EXPRESSION: return createBlockExpression();
      case BeeLangPackage.IF_EXPRESSION: return createIfExpression();
      case BeeLangPackage.ELSE_IF_EXPRESSION: return createElseIfExpression();
      case BeeLangPackage.ELSE_EXPRESSION: return createElseExpression();
      case BeeLangPackage.PROPERTY_VALUE: return createPropertyValue();
      case BeeLangPackage.VARIABLE_VALUE: return createVariableValue();
      case BeeLangPackage.KEYWORD_VARIABLE: return createKeywordVariable();
      case BeeLangPackage.CREATOR_CALL: return createCreatorCall();
      case BeeLangPackage.EXPRESSION_STATEMENT: return createExpressionStatement();
      case BeeLangPackage.PROPERTIES_STATEMENT: return createPropertiesStatement();
      case BeeLangPackage.LITERAL_LIST: return createLiteralList();
      case BeeLangPackage.LITERAL_MAP: return createLiteralMap();
      case BeeLangPackage.LITERAL_FUNCTION: return createLiteralFunction();
      case BeeLangPackage.BOOLEAN_LITERAL: return createBooleanLiteral();
      case BeeLangPackage.INTEGER_LITERAL: return createIntegerLiteral();
      case BeeLangPackage.NULL_LITERAL: return createNullLiteral();
      case BeeLangPackage.THIS_LITERAL: return createThisLiteral();
      case BeeLangPackage.SUPER_LITERAL: return createSuperLiteral();
      case BeeLangPackage.UNIT_LITERAL: return createUnitLiteral();
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
      case BeeLangPackage.SELECTOR_OPERATOR:
        return createSelectorOperatorFromString(eDataType, initialValue);
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
      case BeeLangPackage.SELECTOR_OPERATOR:
        return convertSelectorOperatorToString(eDataType, instanceValue);
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
  public FilteredCapability createFilteredCapability()
  {
    FilteredCapabilityImpl filteredCapability = new FilteredCapabilityImpl();
    return filteredCapability;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Capability createCapability()
  {
    CapabilityImpl capability = new CapabilityImpl();
    return capability;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamedPropertySet createNamedPropertySet()
  {
    NamedPropertySetImpl namedPropertySet = new NamedPropertySetImpl();
    return namedPropertySet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertySet createPropertySet()
  {
    PropertySetImpl propertySet = new PropertySetImpl();
    return propertySet;
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
  public WithClause createWithClause()
  {
    WithClauseImpl withClause = new WithClauseImpl();
    return withClause;
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
  public DirectPartReferemce createDirectPartReferemce()
  {
    DirectPartReferemceImpl directPartReferemce = new DirectPartReferemceImpl();
    return directPartReferemce;
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
  public Builder createBuilder()
  {
    BuilderImpl builder = new BuilderImpl();
    return builder;
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
  public ParameterList createParameterList()
  {
    ParameterListImpl parameterList = new ParameterListImpl();
    return parameterList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterDeclarationList createParameterDeclarationList()
  {
    ParameterDeclarationListImpl parameterDeclarationList = new ParameterDeclarationListImpl();
    return parameterDeclarationList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ParameterDeclaration createParameterDeclaration()
  {
    ParameterDeclarationImpl parameterDeclaration = new ParameterDeclarationImpl();
    return parameterDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BuilderOutput createBuilderOutput()
  {
    BuilderOutputImpl builderOutput = new BuilderOutputImpl();
    return builderOutput;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BuilderInput createBuilderInput()
  {
    BuilderInputImpl builderInput = new BuilderInputImpl();
    return builderInput;
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
  public RepositoryDeclaration createRepositoryDeclaration()
  {
    RepositoryDeclarationImpl repositoryDeclaration = new RepositoryDeclarationImpl();
    return repositoryDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResolutionStrategy createResolutionStrategy()
  {
    ResolutionStrategyImpl resolutionStrategy = new ResolutionStrategyImpl();
    return resolutionStrategy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContainerConfiguration createContainerConfiguration()
  {
    ContainerConfigurationImpl containerConfiguration = new ContainerConfigurationImpl();
    return containerConfiguration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Concern createConcern()
  {
    ConcernImpl concern = new ConcernImpl();
    return concern;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConcernBlock createConcernBlock()
  {
    ConcernBlockImpl concernBlock = new ConcernBlockImpl();
    return concernBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Query createQuery()
  {
    QueryImpl query = new QueryImpl();
    return query;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public QueryPath createQueryPath()
  {
    QueryPathImpl queryPath = new QueryPathImpl();
    return queryPath;
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
  public Statment createStatment()
  {
    StatmentImpl statment = new StatmentImpl();
    return statment;
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
  public FunctionOrMethod createFunctionOrMethod()
  {
    FunctionOrMethodImpl functionOrMethod = new FunctionOrMethodImpl();
    return functionOrMethod;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeParamDeclaration createTypeParamDeclaration()
  {
    TypeParamDeclarationImpl typeParamDeclaration = new TypeParamDeclarationImpl();
    return typeParamDeclaration;
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
  public TypeRef createTypeRef()
  {
    TypeRefImpl typeRef = new TypeRefImpl();
    return typeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SimpleTypeRef createSimpleTypeRef()
  {
    SimpleTypeRefImpl simpleTypeRef = new SimpleTypeRefImpl();
    return simpleTypeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClosureTypeRef createClosureTypeRef()
  {
    ClosureTypeRefImpl closureTypeRef = new ClosureTypeRefImpl();
    return closureTypeRef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RuleTypeParam createRuleTypeParam()
  {
    RuleTypeParamImpl ruleTypeParam = new RuleTypeParamImpl();
    return ruleTypeParam;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RuleTypeRefParam createRuleTypeRefParam()
  {
    RuleTypeRefParamImpl ruleTypeRefParam = new RuleTypeRefParamImpl();
    return ruleTypeRefParam;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WildcardRefParam createWildcardRefParam()
  {
    WildcardRefParamImpl wildcardRefParam = new WildcardRefParamImpl();
    return wildcardRefParam;
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
  public Expresion createExpresion()
  {
    ExpresionImpl expresion = new ExpresionImpl();
    return expresion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TryCatchExpression createTryCatchExpression()
  {
    TryCatchExpressionImpl tryCatchExpression = new TryCatchExpressionImpl();
    return tryCatchExpression;
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
  public SwitchExpression createSwitchExpression()
  {
    SwitchExpressionImpl switchExpression = new SwitchExpressionImpl();
    return switchExpression;
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
  public IfExpressionTail createIfExpressionTail()
  {
    IfExpressionTailImpl ifExpressionTail = new IfExpressionTailImpl();
    return ifExpressionTail;
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
  public Context createContext()
  {
    ContextImpl context = new ContextImpl();
    return context;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContextSelector createContextSelector()
  {
    ContextSelectorImpl contextSelector = new ContextSelectorImpl();
    return contextSelector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExpressionSelector createExpressionSelector()
  {
    ExpressionSelectorImpl expressionSelector = new ExpressionSelectorImpl();
    return expressionSelector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnitSelector createUnitSelector()
  {
    UnitSelectorImpl unitSelector = new UnitSelectorImpl();
    return unitSelector;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ContextBlock createContextBlock()
  {
    ContextBlockImpl contextBlock = new ContextBlockImpl();
    return contextBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ClosureExpression createClosureExpression()
  {
    ClosureExpressionImpl closureExpression = new ClosureExpressionImpl();
    return closureExpression;
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
  public VarargParameterDeclaration createVarargParameterDeclaration()
  {
    VarargParameterDeclarationImpl varargParameterDeclaration = new VarargParameterDeclarationImpl();
    return varargParameterDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Function createFunction()
  {
    FunctionImpl function = new FunctionImpl();
    return function;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Method createMethod()
  {
    MethodImpl method = new MethodImpl();
    return method;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReturnExpression createReturnExpression()
  {
    ReturnExpressionImpl returnExpression = new ReturnExpressionImpl();
    return returnExpression;
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
  public VarDeclaration createVarDeclaration()
  {
    VarDeclarationImpl varDeclaration = new VarDeclarationImpl();
    return varDeclaration;
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
  public WithClauseExpression createWithClauseExpression()
  {
    WithClauseExpressionImpl withClauseExpression = new WithClauseExpressionImpl();
    return withClauseExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WithContextExpression createWithContextExpression()
  {
    WithContextExpressionImpl withContextExpression = new WithContextExpressionImpl();
    return withContextExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public WildcardExpression createWildcardExpression()
  {
    WildcardExpressionImpl wildcardExpression = new WildcardExpressionImpl();
    return wildcardExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ThrowExpression createThrowExpression()
  {
    ThrowExpressionImpl throwExpression = new ThrowExpressionImpl();
    return throwExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BlockExpression createBlockExpression()
  {
    BlockExpressionImpl blockExpression = new BlockExpressionImpl();
    return blockExpression;
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
  public ElseIfExpression createElseIfExpression()
  {
    ElseIfExpressionImpl elseIfExpression = new ElseIfExpressionImpl();
    return elseIfExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ElseExpression createElseExpression()
  {
    ElseExpressionImpl elseExpression = new ElseExpressionImpl();
    return elseExpression;
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
  public ExpressionStatement createExpressionStatement()
  {
    ExpressionStatementImpl expressionStatement = new ExpressionStatementImpl();
    return expressionStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertiesStatement createPropertiesStatement()
  {
    PropertiesStatementImpl propertiesStatement = new PropertiesStatementImpl();
    return propertiesStatement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LiteralList createLiteralList()
  {
    LiteralListImpl literalList = new LiteralListImpl();
    return literalList;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LiteralMap createLiteralMap()
  {
    LiteralMapImpl literalMap = new LiteralMapImpl();
    return literalMap;
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
  public ThisLiteral createThisLiteral()
  {
    ThisLiteralImpl thisLiteral = new ThisLiteralImpl();
    return thisLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public SuperLiteral createSuperLiteral()
  {
    SuperLiteralImpl superLiteral = new SuperLiteralImpl();
    return superLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnitLiteral createUnitLiteral()
  {
    UnitLiteralImpl unitLiteral = new UnitLiteralImpl();
    return unitLiteral;
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
  public SelectorOperator createSelectorOperatorFromString(EDataType eDataType, String initialValue)
  {
    SelectorOperator result = SelectorOperator.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertSelectorOperatorToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
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
