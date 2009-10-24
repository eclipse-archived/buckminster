/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.b3.beeLang.BeeLangPackage
 * @generated
 */
public interface BeeLangFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  BeeLangFactory eINSTANCE = org.eclipse.b3.beeLang.impl.BeeLangFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Bee Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Bee Model</em>'.
   * @generated
   */
  BeeModel createBeeModel();

  /**
   * Returns a new object of class '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Import</em>'.
   * @generated
   */
  Import createImport();

  /**
   * Returns a new object of class '<em>Build Unit</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Build Unit</em>'.
   * @generated
   */
  BuildUnit createBuildUnit();

  /**
   * Returns a new object of class '<em>Provided Capability</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Provided Capability</em>'.
   * @generated
   */
  ProvidedCapability createProvidedCapability();

  /**
   * Returns a new object of class '<em>Required Capability</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Required Capability</em>'.
   * @generated
   */
  RequiredCapability createRequiredCapability();

  /**
   * Returns a new object of class '<em>Property Statements</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Property Statements</em>'.
   * @generated
   */
  PropertyStatements createPropertyStatements();

  /**
   * Returns a new object of class '<em>Property Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Property Statement</em>'.
   * @generated
   */
  PropertyStatement createPropertyStatement();

  /**
   * Returns a new object of class '<em>Property Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Property Expression</em>'.
   * @generated
   */
  PropertyExpression createPropertyExpression();

  /**
   * Returns a new object of class '<em>Synchronization</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Synchronization</em>'.
   * @generated
   */
  Synchronization createSynchronization();

  /**
   * Returns a new object of class '<em>Path Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Path Group</em>'.
   * @generated
   */
  PathGroup createPathGroup();

  /**
   * Returns a new object of class '<em>Path Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Path Expression</em>'.
   * @generated
   */
  PathExpression createPathExpression();

  /**
   * Returns a new object of class '<em>Expr Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expr Statement</em>'.
   * @generated
   */
  ExprStatement createExprStatement();

  /**
   * Returns a new object of class '<em>Prerequisite</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Prerequisite</em>'.
   * @generated
   */
  Prerequisite createPrerequisite();

  /**
   * Returns a new object of class '<em>Closure</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Closure</em>'.
   * @generated
   */
  Closure createClosure();

  /**
   * Returns a new object of class '<em>Prerequisite Entry</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Prerequisite Entry</em>'.
   * @generated
   */
  PrerequisiteEntry createPrerequisiteEntry();

  /**
   * Returns a new object of class '<em>Part In Self</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Part In Self</em>'.
   * @generated
   */
  PartInSelf createPartInSelf();

  /**
   * Returns a new object of class '<em>Capability Referenced Part</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Capability Referenced Part</em>'.
   * @generated
   */
  CapabilityReferencedPart createCapabilityReferencedPart();

  /**
   * Returns a new object of class '<em>Compound References</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Compound References</em>'.
   * @generated
   */
  CompoundReferences createCompoundReferences();

  /**
   * Returns a new object of class '<em>Part</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Part</em>'.
   * @generated
   */
  Part createPart();

  /**
   * Returns a new object of class '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter</em>'.
   * @generated
   */
  Parameter createParameter();

  /**
   * Returns a new object of class '<em>Layout</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Layout</em>'.
   * @generated
   */
  Layout createLayout();

  /**
   * Returns a new object of class '<em>Action Input Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Action Input Group</em>'.
   * @generated
   */
  ActionInputGroup createActionInputGroup();

  /**
   * Returns a new object of class '<em>Repository Configuration</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Repository Configuration</em>'.
   * @generated
   */
  RepositoryConfiguration createRepositoryConfiguration();

  /**
   * Returns a new object of class '<em>Named Advice</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Named Advice</em>'.
   * @generated
   */
  NamedAdvice createNamedAdvice();

  /**
   * Returns a new object of class '<em>Advice</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Advice</em>'.
   * @generated
   */
  Advice createAdvice();

  /**
   * Returns a new object of class '<em>Compound Advice</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Compound Advice</em>'.
   * @generated
   */
  CompoundAdvice createCompoundAdvice();

  /**
   * Returns a new object of class '<em>Advice Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Advice Statement</em>'.
   * @generated
   */
  AdviceStatement createAdviceStatement();

  /**
   * Returns a new object of class '<em>Advice Path</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Advice Path</em>'.
   * @generated
   */
  AdvicePath createAdvicePath();

  /**
   * Returns a new object of class '<em>Advice Path Element</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Advice Path Element</em>'.
   * @generated
   */
  AdvicePathElement createAdvicePathElement();

  /**
   * Returns a new object of class '<em>Advice Path Children</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Advice Path Children</em>'.
   * @generated
   */
  AdvicePathChildren createAdvicePathChildren();

  /**
   * Returns a new object of class '<em>Filter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Filter</em>'.
   * @generated
   */
  Filter createFilter();

  /**
   * Returns a new object of class '<em>Pre Condition Assert</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Pre Condition Assert</em>'.
   * @generated
   */
  PreConditionAssert createPreConditionAssert();

  /**
   * Returns a new object of class '<em>Post Condition Assert</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Post Condition Assert</em>'.
   * @generated
   */
  PostConditionAssert createPostConditionAssert();

  /**
   * Returns a new object of class '<em>Assertion Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Assertion Expression</em>'.
   * @generated
   */
  AssertionExpression createAssertionExpression();

  /**
   * Returns a new object of class '<em>Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Statement</em>'.
   * @generated
   */
  Statement createStatement();

  /**
   * Returns a new object of class '<em>Break Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Break Statement</em>'.
   * @generated
   */
  BreakStatement createBreakStatement();

  /**
   * Returns a new object of class '<em>Continue Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Continue Statement</em>'.
   * @generated
   */
  ContinueStatement createContinueStatement();

  /**
   * Returns a new object of class '<em>Compound Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Compound Statement</em>'.
   * @generated
   */
  CompoundStatement createCompoundStatement();

  /**
   * Returns a new object of class '<em>Statements</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Statements</em>'.
   * @generated
   */
  Statements createStatements();

  /**
   * Returns a new object of class '<em>While Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>While Statement</em>'.
   * @generated
   */
  WhileStatement createWhileStatement();

  /**
   * Returns a new object of class '<em>Switch Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Switch Statement</em>'.
   * @generated
   */
  SwitchStatement createSwitchStatement();

  /**
   * Returns a new object of class '<em>Case</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Case</em>'.
   * @generated
   */
  Case createCase();

  /**
   * Returns a new object of class '<em>For Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>For Statement</em>'.
   * @generated
   */
  ForStatement createForStatement();

  /**
   * Returns a new object of class '<em>Do While Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Do While Statement</em>'.
   * @generated
   */
  DoWhileStatement createDoWhileStatement();

  /**
   * Returns a new object of class '<em>Return Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Return Statement</em>'.
   * @generated
   */
  ReturnStatement createReturnStatement();

  /**
   * Returns a new object of class '<em>Function Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Function Statement</em>'.
   * @generated
   */
  FunctionStatement createFunctionStatement();

  /**
   * Returns a new object of class '<em>If Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>If Statement</em>'.
   * @generated
   */
  IfStatement createIfStatement();

  /**
   * Returns a new object of class '<em>Label Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Label Statement</em>'.
   * @generated
   */
  LabelStatement createLabelStatement();

  /**
   * Returns a new object of class '<em>Throw Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Throw Statement</em>'.
   * @generated
   */
  ThrowStatement createThrowStatement();

  /**
   * Returns a new object of class '<em>Try Catch Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Try Catch Statement</em>'.
   * @generated
   */
  TryCatchStatement createTryCatchStatement();

  /**
   * Returns a new object of class '<em>Catch Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Catch Block</em>'.
   * @generated
   */
  CatchBlock createCatchBlock();

  /**
   * Returns a new object of class '<em>Finally Block</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Finally Block</em>'.
   * @generated
   */
  FinallyBlock createFinallyBlock();

  /**
   * Returns a new object of class '<em>With Statement</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>With Statement</em>'.
   * @generated
   */
  WithStatement createWithStatement();

  /**
   * Returns a new object of class '<em>Syntax Element</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Syntax Element</em>'.
   * @generated
   */
  SyntaxElement createSyntaxElement();

  /**
   * Returns a new object of class '<em>Var Expression List</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Var Expression List</em>'.
   * @generated
   */
  VarExpressionList createVarExpressionList();

  /**
   * Returns a new object of class '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression</em>'.
   * @generated
   */
  Expression createExpression();

  /**
   * Returns a new object of class '<em>Var Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Var Expression</em>'.
   * @generated
   */
  VarExpression createVarExpression();

  /**
   * Returns a new object of class '<em>Value Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Value Expression</em>'.
   * @generated
   */
  ValueExpression createValueExpression();

  /**
   * Returns a new object of class '<em>Operation Call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Operation Call</em>'.
   * @generated
   */
  OperationCall createOperationCall();

  /**
   * Returns a new object of class '<em>Property Value</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Property Value</em>'.
   * @generated
   */
  PropertyValue createPropertyValue();

  /**
   * Returns a new object of class '<em>Variable Value</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variable Value</em>'.
   * @generated
   */
  VariableValue createVariableValue();

  /**
   * Returns a new object of class '<em>Keyword Variables</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Keyword Variables</em>'.
   * @generated
   */
  KeywordVariables createKeywordVariables();

  /**
   * Returns a new object of class '<em>Constructor Call Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constructor Call Expression</em>'.
   * @generated
   */
  ConstructorCallExpression createConstructorCallExpression();

  /**
   * Returns a new object of class '<em>Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal</em>'.
   * @generated
   */
  Literal createLiteral();

  /**
   * Returns a new object of class '<em>Literal Array</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal Array</em>'.
   * @generated
   */
  LiteralArray createLiteralArray();

  /**
   * Returns a new object of class '<em>Literal Object</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal Object</em>'.
   * @generated
   */
  LiteralObject createLiteralObject();

  /**
   * Returns a new object of class '<em>Literal Function</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal Function</em>'.
   * @generated
   */
  LiteralFunction createLiteralFunction();

  /**
   * Returns a new object of class '<em>Feature</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature</em>'.
   * @generated
   */
  Feature createFeature();

  /**
   * Returns a new object of class '<em>Value Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Value Literal</em>'.
   * @generated
   */
  ValueLiteral createValueLiteral();

  /**
   * Returns a new object of class '<em>Boolean Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Literal</em>'.
   * @generated
   */
  BooleanLiteral createBooleanLiteral();

  /**
   * Returns a new object of class '<em>Integer Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Integer Literal</em>'.
   * @generated
   */
  IntegerLiteral createIntegerLiteral();

  /**
   * Returns a new object of class '<em>Null Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Null Literal</em>'.
   * @generated
   */
  NullLiteral createNullLiteral();

  /**
   * Returns a new object of class '<em>Void Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Void Literal</em>'.
   * @generated
   */
  VoidLiteral createVoidLiteral();

  /**
   * Returns a new object of class '<em>String Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Literal</em>'.
   * @generated
   */
  StringLiteral createStringLiteral();

  /**
   * Returns a new object of class '<em>Real Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Real Literal</em>'.
   * @generated
   */
  RealLiteral createRealLiteral();

  /**
   * Returns a new object of class '<em>Regexp Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Regexp Literal</em>'.
   * @generated
   */
  RegexpLiteral createRegexpLiteral();

  /**
   * Returns a new object of class '<em>Assignment Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Assignment Operation</em>'.
   * @generated
   */
  AssignmentOperation createAssignmentOperation();

  /**
   * Returns a new object of class '<em>If Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>If Expression</em>'.
   * @generated
   */
  IfExpression createIfExpression();

  /**
   * Returns a new object of class '<em>Boolean Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Operation</em>'.
   * @generated
   */
  BooleanOperation createBooleanOperation();

  /**
   * Returns a new object of class '<em>Post Op Call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Post Op Call</em>'.
   * @generated
   */
  PostOpCall createPostOpCall();

  /**
   * Returns a new object of class '<em>At Call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>At Call</em>'.
   * @generated
   */
  AtCall createAtCall();

  /**
   * Returns a new object of class '<em>Feature Call</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Feature Call</em>'.
   * @generated
   */
  FeatureCall createFeatureCall();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  BeeLangPackage getBeeLangPackage();

} //BeeLangFactory
