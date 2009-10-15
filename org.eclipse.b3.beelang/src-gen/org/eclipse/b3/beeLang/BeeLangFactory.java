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
   * Returns a new object of class '<em>String Property</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Property</em>'.
   * @generated
   */
  StringProperty createStringProperty();

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
   * Returns a new object of class '<em>Build Part</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Build Part</em>'.
   * @generated
   */
  BuildPart createBuildPart();

  /**
   * Returns a new object of class '<em>Artifacts Part</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Artifacts Part</em>'.
   * @generated
   */
  ArtifactsPart createArtifactsPart();

  /**
   * Returns a new object of class '<em>Path Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Path Group</em>'.
   * @generated
   */
  PathGroup createPathGroup();

  /**
   * Returns a new object of class '<em>Group Part</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Group Part</em>'.
   * @generated
   */
  GroupPart createGroupPart();

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
   * Returns a new object of class '<em>Action Part</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Action Part</em>'.
   * @generated
   */
  ActionPart createActionPart();

  /**
   * Returns a new object of class '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parameter</em>'.
   * @generated
   */
  Parameter createParameter();

  /**
   * Returns a new object of class '<em>Result</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Result</em>'.
   * @generated
   */
  Result createResult();

  /**
   * Returns a new object of class '<em>Result Group</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Result Group</em>'.
   * @generated
   */
  ResultGroup createResultGroup();

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
   * Returns a new object of class '<em>Syntax Element</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Syntax Element</em>'.
   * @generated
   */
  SyntaxElement createSyntaxElement();

  /**
   * Returns a new object of class '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression</em>'.
   * @generated
   */
  Expression createExpression();

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
   * Returns a new object of class '<em>Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Literal</em>'.
   * @generated
   */
  Literal createLiteral();

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
   * Returns a new object of class '<em>String Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Literal</em>'.
   * @generated
   */
  StringLiteral createStringLiteral();

  /**
   * Returns a new object of class '<em>Global Var Expression</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Global Var Expression</em>'.
   * @generated
   */
  GlobalVarExpression createGlobalVarExpression();

  /**
   * Returns a new object of class '<em>Boolean Operation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Operation</em>'.
   * @generated
   */
  BooleanOperation createBooleanOperation();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  BeeLangPackage getBeeLangPackage();

} //BeeLangFactory
