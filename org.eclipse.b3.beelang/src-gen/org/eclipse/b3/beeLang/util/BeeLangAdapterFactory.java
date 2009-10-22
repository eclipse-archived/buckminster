/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.util;

import org.eclipse.b3.beeLang.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.b3.beeLang.BeeLangPackage
 * @generated
 */
public class BeeLangAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static BeeLangPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeeLangAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = BeeLangPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BeeLangSwitch<Adapter> modelSwitch =
    new BeeLangSwitch<Adapter>()
    {
      @Override
      public Adapter caseBeeModel(BeeModel object)
      {
        return createBeeModelAdapter();
      }
      @Override
      public Adapter caseImport(Import object)
      {
        return createImportAdapter();
      }
      @Override
      public Adapter caseBuildUnit(BuildUnit object)
      {
        return createBuildUnitAdapter();
      }
      @Override
      public Adapter caseProvidedCapability(ProvidedCapability object)
      {
        return createProvidedCapabilityAdapter();
      }
      @Override
      public Adapter caseRequiredCapability(RequiredCapability object)
      {
        return createRequiredCapabilityAdapter();
      }
      @Override
      public Adapter casePropertyStatements(PropertyStatements object)
      {
        return createPropertyStatementsAdapter();
      }
      @Override
      public Adapter casePropertyStatement(PropertyStatement object)
      {
        return createPropertyStatementAdapter();
      }
      @Override
      public Adapter casePropertyExpression(PropertyExpression object)
      {
        return createPropertyExpressionAdapter();
      }
      @Override
      public Adapter caseSynchronization(Synchronization object)
      {
        return createSynchronizationAdapter();
      }
      @Override
      public Adapter caseBuildPart(BuildPart object)
      {
        return createBuildPartAdapter();
      }
      @Override
      public Adapter caseArtifactsPart(ArtifactsPart object)
      {
        return createArtifactsPartAdapter();
      }
      @Override
      public Adapter casePathGroup(PathGroup object)
      {
        return createPathGroupAdapter();
      }
      @Override
      public Adapter casePathExpression(PathExpression object)
      {
        return createPathExpressionAdapter();
      }
      @Override
      public Adapter caseExprStatement(ExprStatement object)
      {
        return createExprStatementAdapter();
      }
      @Override
      public Adapter caseGroupPart(GroupPart object)
      {
        return createGroupPartAdapter();
      }
      @Override
      public Adapter casePrerequisite(Prerequisite object)
      {
        return createPrerequisiteAdapter();
      }
      @Override
      public Adapter caseClosure(Closure object)
      {
        return createClosureAdapter();
      }
      @Override
      public Adapter casePrerequisiteEntry(PrerequisiteEntry object)
      {
        return createPrerequisiteEntryAdapter();
      }
      @Override
      public Adapter casePartInSelf(PartInSelf object)
      {
        return createPartInSelfAdapter();
      }
      @Override
      public Adapter caseCapabilityReferencedPart(CapabilityReferencedPart object)
      {
        return createCapabilityReferencedPartAdapter();
      }
      @Override
      public Adapter caseCompoundReferences(CompoundReferences object)
      {
        return createCompoundReferencesAdapter();
      }
      @Override
      public Adapter caseActionPart(ActionPart object)
      {
        return createActionPartAdapter();
      }
      @Override
      public Adapter caseParameter(Parameter object)
      {
        return createParameterAdapter();
      }
      @Override
      public Adapter caseLayout(Layout object)
      {
        return createLayoutAdapter();
      }
      @Override
      public Adapter caseActionInputGroup(ActionInputGroup object)
      {
        return createActionInputGroupAdapter();
      }
      @Override
      public Adapter caseRepositoryConfiguration(RepositoryConfiguration object)
      {
        return createRepositoryConfigurationAdapter();
      }
      @Override
      public Adapter caseNamedAdvice(NamedAdvice object)
      {
        return createNamedAdviceAdapter();
      }
      @Override
      public Adapter caseAdvice(Advice object)
      {
        return createAdviceAdapter();
      }
      @Override
      public Adapter caseCompoundAdvice(CompoundAdvice object)
      {
        return createCompoundAdviceAdapter();
      }
      @Override
      public Adapter caseAdviceStatement(AdviceStatement object)
      {
        return createAdviceStatementAdapter();
      }
      @Override
      public Adapter caseAdvicePath(AdvicePath object)
      {
        return createAdvicePathAdapter();
      }
      @Override
      public Adapter caseAdvicePathElement(AdvicePathElement object)
      {
        return createAdvicePathElementAdapter();
      }
      @Override
      public Adapter caseAdvicePathChildren(AdvicePathChildren object)
      {
        return createAdvicePathChildrenAdapter();
      }
      @Override
      public Adapter caseFilter(Filter object)
      {
        return createFilterAdapter();
      }
      @Override
      public Adapter casePreConditionAssert(PreConditionAssert object)
      {
        return createPreConditionAssertAdapter();
      }
      @Override
      public Adapter casePostConditionAssert(PostConditionAssert object)
      {
        return createPostConditionAssertAdapter();
      }
      @Override
      public Adapter caseAssertionExpression(AssertionExpression object)
      {
        return createAssertionExpressionAdapter();
      }
      @Override
      public Adapter caseStatement(Statement object)
      {
        return createStatementAdapter();
      }
      @Override
      public Adapter caseBreakStatement(BreakStatement object)
      {
        return createBreakStatementAdapter();
      }
      @Override
      public Adapter caseContinueStatement(ContinueStatement object)
      {
        return createContinueStatementAdapter();
      }
      @Override
      public Adapter caseCompoundStatement(CompoundStatement object)
      {
        return createCompoundStatementAdapter();
      }
      @Override
      public Adapter caseStatements(Statements object)
      {
        return createStatementsAdapter();
      }
      @Override
      public Adapter caseWhileStatement(WhileStatement object)
      {
        return createWhileStatementAdapter();
      }
      @Override
      public Adapter caseSwitchStatement(SwitchStatement object)
      {
        return createSwitchStatementAdapter();
      }
      @Override
      public Adapter caseCase(Case object)
      {
        return createCaseAdapter();
      }
      @Override
      public Adapter caseForStatement(ForStatement object)
      {
        return createForStatementAdapter();
      }
      @Override
      public Adapter caseReturnStatement(ReturnStatement object)
      {
        return createReturnStatementAdapter();
      }
      @Override
      public Adapter caseSyntaxElement(SyntaxElement object)
      {
        return createSyntaxElementAdapter();
      }
      @Override
      public Adapter caseVarExpressionList(VarExpressionList object)
      {
        return createVarExpressionListAdapter();
      }
      @Override
      public Adapter caseExpression(Expression object)
      {
        return createExpressionAdapter();
      }
      @Override
      public Adapter caseVarExpression(VarExpression object)
      {
        return createVarExpressionAdapter();
      }
      @Override
      public Adapter caseValueExpression(ValueExpression object)
      {
        return createValueExpressionAdapter();
      }
      @Override
      public Adapter caseOperationCall(OperationCall object)
      {
        return createOperationCallAdapter();
      }
      @Override
      public Adapter casePropertyValue(PropertyValue object)
      {
        return createPropertyValueAdapter();
      }
      @Override
      public Adapter caseVariableValue(VariableValue object)
      {
        return createVariableValueAdapter();
      }
      @Override
      public Adapter caseFeatureCall(FeatureCall object)
      {
        return createFeatureCallAdapter();
      }
      @Override
      public Adapter caseLiteral(Literal object)
      {
        return createLiteralAdapter();
      }
      @Override
      public Adapter caseValueLiteral(ValueLiteral object)
      {
        return createValueLiteralAdapter();
      }
      @Override
      public Adapter caseBooleanLiteral(BooleanLiteral object)
      {
        return createBooleanLiteralAdapter();
      }
      @Override
      public Adapter caseIntegerLiteral(IntegerLiteral object)
      {
        return createIntegerLiteralAdapter();
      }
      @Override
      public Adapter caseNullLiteral(NullLiteral object)
      {
        return createNullLiteralAdapter();
      }
      @Override
      public Adapter caseStringLiteral(StringLiteral object)
      {
        return createStringLiteralAdapter();
      }
      @Override
      public Adapter caseRealLiteral(RealLiteral object)
      {
        return createRealLiteralAdapter();
      }
      @Override
      public Adapter caseRegexpLiteral(RegexpLiteral object)
      {
        return createRegexpLiteralAdapter();
      }
      @Override
      public Adapter caseAssignmentOperation(AssignmentOperation object)
      {
        return createAssignmentOperationAdapter();
      }
      @Override
      public Adapter caseIfExpression(IfExpression object)
      {
        return createIfExpressionAdapter();
      }
      @Override
      public Adapter caseBooleanOperation(BooleanOperation object)
      {
        return createBooleanOperationAdapter();
      }
      @Override
      public Adapter casePostOpCall(PostOpCall object)
      {
        return createPostOpCallAdapter();
      }
      @Override
      public Adapter caseAtCall(AtCall object)
      {
        return createAtCallAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.BeeModel <em>Bee Model</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.BeeModel
   * @generated
   */
  public Adapter createBeeModelAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Import <em>Import</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Import
   * @generated
   */
  public Adapter createImportAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.BuildUnit <em>Build Unit</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.BuildUnit
   * @generated
   */
  public Adapter createBuildUnitAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ProvidedCapability <em>Provided Capability</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ProvidedCapability
   * @generated
   */
  public Adapter createProvidedCapabilityAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.RequiredCapability <em>Required Capability</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.RequiredCapability
   * @generated
   */
  public Adapter createRequiredCapabilityAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PropertyStatements <em>Property Statements</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PropertyStatements
   * @generated
   */
  public Adapter createPropertyStatementsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PropertyStatement <em>Property Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PropertyStatement
   * @generated
   */
  public Adapter createPropertyStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PropertyExpression <em>Property Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PropertyExpression
   * @generated
   */
  public Adapter createPropertyExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Synchronization <em>Synchronization</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Synchronization
   * @generated
   */
  public Adapter createSynchronizationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.BuildPart <em>Build Part</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.BuildPart
   * @generated
   */
  public Adapter createBuildPartAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ArtifactsPart <em>Artifacts Part</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ArtifactsPart
   * @generated
   */
  public Adapter createArtifactsPartAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PathGroup <em>Path Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PathGroup
   * @generated
   */
  public Adapter createPathGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PathExpression <em>Path Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PathExpression
   * @generated
   */
  public Adapter createPathExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ExprStatement <em>Expr Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ExprStatement
   * @generated
   */
  public Adapter createExprStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.GroupPart <em>Group Part</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.GroupPart
   * @generated
   */
  public Adapter createGroupPartAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Prerequisite <em>Prerequisite</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Prerequisite
   * @generated
   */
  public Adapter createPrerequisiteAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Closure <em>Closure</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Closure
   * @generated
   */
  public Adapter createClosureAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PrerequisiteEntry <em>Prerequisite Entry</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PrerequisiteEntry
   * @generated
   */
  public Adapter createPrerequisiteEntryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PartInSelf <em>Part In Self</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PartInSelf
   * @generated
   */
  public Adapter createPartInSelfAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.CapabilityReferencedPart <em>Capability Referenced Part</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.CapabilityReferencedPart
   * @generated
   */
  public Adapter createCapabilityReferencedPartAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.CompoundReferences <em>Compound References</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.CompoundReferences
   * @generated
   */
  public Adapter createCompoundReferencesAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ActionPart <em>Action Part</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ActionPart
   * @generated
   */
  public Adapter createActionPartAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Parameter
   * @generated
   */
  public Adapter createParameterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Layout <em>Layout</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Layout
   * @generated
   */
  public Adapter createLayoutAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ActionInputGroup <em>Action Input Group</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ActionInputGroup
   * @generated
   */
  public Adapter createActionInputGroupAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.RepositoryConfiguration <em>Repository Configuration</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.RepositoryConfiguration
   * @generated
   */
  public Adapter createRepositoryConfigurationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.NamedAdvice <em>Named Advice</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.NamedAdvice
   * @generated
   */
  public Adapter createNamedAdviceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Advice <em>Advice</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Advice
   * @generated
   */
  public Adapter createAdviceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.CompoundAdvice <em>Compound Advice</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.CompoundAdvice
   * @generated
   */
  public Adapter createCompoundAdviceAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.AdviceStatement <em>Advice Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.AdviceStatement
   * @generated
   */
  public Adapter createAdviceStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.AdvicePath <em>Advice Path</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.AdvicePath
   * @generated
   */
  public Adapter createAdvicePathAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.AdvicePathElement <em>Advice Path Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.AdvicePathElement
   * @generated
   */
  public Adapter createAdvicePathElementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.AdvicePathChildren <em>Advice Path Children</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.AdvicePathChildren
   * @generated
   */
  public Adapter createAdvicePathChildrenAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Filter <em>Filter</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Filter
   * @generated
   */
  public Adapter createFilterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PreConditionAssert <em>Pre Condition Assert</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PreConditionAssert
   * @generated
   */
  public Adapter createPreConditionAssertAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PostConditionAssert <em>Post Condition Assert</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PostConditionAssert
   * @generated
   */
  public Adapter createPostConditionAssertAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.AssertionExpression <em>Assertion Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.AssertionExpression
   * @generated
   */
  public Adapter createAssertionExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Statement <em>Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Statement
   * @generated
   */
  public Adapter createStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.BreakStatement <em>Break Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.BreakStatement
   * @generated
   */
  public Adapter createBreakStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ContinueStatement <em>Continue Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ContinueStatement
   * @generated
   */
  public Adapter createContinueStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.CompoundStatement <em>Compound Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.CompoundStatement
   * @generated
   */
  public Adapter createCompoundStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Statements <em>Statements</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Statements
   * @generated
   */
  public Adapter createStatementsAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.WhileStatement <em>While Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.WhileStatement
   * @generated
   */
  public Adapter createWhileStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.SwitchStatement <em>Switch Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.SwitchStatement
   * @generated
   */
  public Adapter createSwitchStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Case <em>Case</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Case
   * @generated
   */
  public Adapter createCaseAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ForStatement <em>For Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ForStatement
   * @generated
   */
  public Adapter createForStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ReturnStatement <em>Return Statement</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ReturnStatement
   * @generated
   */
  public Adapter createReturnStatementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.SyntaxElement <em>Syntax Element</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.SyntaxElement
   * @generated
   */
  public Adapter createSyntaxElementAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.VarExpressionList <em>Var Expression List</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.VarExpressionList
   * @generated
   */
  public Adapter createVarExpressionListAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Expression
   * @generated
   */
  public Adapter createExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.VarExpression <em>Var Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.VarExpression
   * @generated
   */
  public Adapter createVarExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ValueExpression <em>Value Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ValueExpression
   * @generated
   */
  public Adapter createValueExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.OperationCall <em>Operation Call</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.OperationCall
   * @generated
   */
  public Adapter createOperationCallAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PropertyValue <em>Property Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PropertyValue
   * @generated
   */
  public Adapter createPropertyValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.VariableValue <em>Variable Value</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.VariableValue
   * @generated
   */
  public Adapter createVariableValueAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.FeatureCall <em>Feature Call</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.FeatureCall
   * @generated
   */
  public Adapter createFeatureCallAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.Literal <em>Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.Literal
   * @generated
   */
  public Adapter createLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.ValueLiteral <em>Value Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.ValueLiteral
   * @generated
   */
  public Adapter createValueLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.BooleanLiteral <em>Boolean Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.BooleanLiteral
   * @generated
   */
  public Adapter createBooleanLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.IntegerLiteral <em>Integer Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.IntegerLiteral
   * @generated
   */
  public Adapter createIntegerLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.NullLiteral <em>Null Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.NullLiteral
   * @generated
   */
  public Adapter createNullLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.StringLiteral <em>String Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.StringLiteral
   * @generated
   */
  public Adapter createStringLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.RealLiteral <em>Real Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.RealLiteral
   * @generated
   */
  public Adapter createRealLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.RegexpLiteral <em>Regexp Literal</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.RegexpLiteral
   * @generated
   */
  public Adapter createRegexpLiteralAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.AssignmentOperation <em>Assignment Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.AssignmentOperation
   * @generated
   */
  public Adapter createAssignmentOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.IfExpression <em>If Expression</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.IfExpression
   * @generated
   */
  public Adapter createIfExpressionAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.BooleanOperation <em>Boolean Operation</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.BooleanOperation
   * @generated
   */
  public Adapter createBooleanOperationAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.PostOpCall <em>Post Op Call</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.PostOpCall
   * @generated
   */
  public Adapter createPostOpCallAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.b3.beeLang.AtCall <em>At Call</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.b3.beeLang.AtCall
   * @generated
   */
  public Adapter createAtCallAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //BeeLangAdapterFactory
