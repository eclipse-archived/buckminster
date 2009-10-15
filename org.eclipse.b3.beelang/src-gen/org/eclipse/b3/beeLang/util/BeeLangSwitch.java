/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.util;

import java.util.List;

import org.eclipse.b3.beeLang.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.b3.beeLang.BeeLangPackage
 * @generated
 */
public class BeeLangSwitch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static BeeLangPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeeLangSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = BeeLangPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch(eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case BeeLangPackage.BEE_MODEL:
      {
        BeeModel beeModel = (BeeModel)theEObject;
        T result = caseBeeModel(beeModel);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.IMPORT:
      {
        Import import_ = (Import)theEObject;
        T result = caseImport(import_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.BUILD_UNIT:
      {
        BuildUnit buildUnit = (BuildUnit)theEObject;
        T result = caseBuildUnit(buildUnit);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PROVIDED_CAPABILITY:
      {
        ProvidedCapability providedCapability = (ProvidedCapability)theEObject;
        T result = caseProvidedCapability(providedCapability);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.REQUIRED_CAPABILITY:
      {
        RequiredCapability requiredCapability = (RequiredCapability)theEObject;
        T result = caseRequiredCapability(requiredCapability);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.STRING_PROPERTY:
      {
        StringProperty stringProperty = (StringProperty)theEObject;
        T result = caseStringProperty(stringProperty);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PROPERTY_EXPRESSION:
      {
        PropertyExpression propertyExpression = (PropertyExpression)theEObject;
        T result = casePropertyExpression(propertyExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.SYNCHRONIZATION:
      {
        Synchronization synchronization = (Synchronization)theEObject;
        T result = caseSynchronization(synchronization);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.BUILD_PART:
      {
        BuildPart buildPart = (BuildPart)theEObject;
        T result = caseBuildPart(buildPart);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ARTIFACTS_PART:
      {
        ArtifactsPart artifactsPart = (ArtifactsPart)theEObject;
        T result = caseArtifactsPart(artifactsPart);
        if (result == null) result = caseBuildPart(artifactsPart);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PATH_GROUP:
      {
        PathGroup pathGroup = (PathGroup)theEObject;
        T result = casePathGroup(pathGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.GROUP_PART:
      {
        GroupPart groupPart = (GroupPart)theEObject;
        T result = caseGroupPart(groupPart);
        if (result == null) result = caseBuildPart(groupPart);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PREREQUISITE:
      {
        Prerequisite prerequisite = (Prerequisite)theEObject;
        T result = casePrerequisite(prerequisite);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.CLOSURE:
      {
        Closure closure = (Closure)theEObject;
        T result = caseClosure(closure);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PREREQUISITE_ENTRY:
      {
        PrerequisiteEntry prerequisiteEntry = (PrerequisiteEntry)theEObject;
        T result = casePrerequisiteEntry(prerequisiteEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PART_IN_SELF:
      {
        PartInSelf partInSelf = (PartInSelf)theEObject;
        T result = casePartInSelf(partInSelf);
        if (result == null) result = casePrerequisiteEntry(partInSelf);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.CAPABILITY_REFERENCED_PART:
      {
        CapabilityReferencedPart capabilityReferencedPart = (CapabilityReferencedPart)theEObject;
        T result = caseCapabilityReferencedPart(capabilityReferencedPart);
        if (result == null) result = casePrerequisiteEntry(capabilityReferencedPart);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.COMPOUND_REFERENCES:
      {
        CompoundReferences compoundReferences = (CompoundReferences)theEObject;
        T result = caseCompoundReferences(compoundReferences);
        if (result == null) result = casePrerequisiteEntry(compoundReferences);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ACTION_PART:
      {
        ActionPart actionPart = (ActionPart)theEObject;
        T result = caseActionPart(actionPart);
        if (result == null) result = caseBuildPart(actionPart);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PARAMETER:
      {
        Parameter parameter = (Parameter)theEObject;
        T result = caseParameter(parameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.RESULT:
      {
        Result result = (Result)theEObject;
        T theResult = caseResult(result);
        if (theResult == null) theResult = defaultCase(theEObject);
        return theResult;
      }
      case BeeLangPackage.RESULT_GROUP:
      {
        ResultGroup resultGroup = (ResultGroup)theEObject;
        T result = caseResultGroup(resultGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.REPOSITORY_CONFIGURATION:
      {
        RepositoryConfiguration repositoryConfiguration = (RepositoryConfiguration)theEObject;
        T result = caseRepositoryConfiguration(repositoryConfiguration);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.NAMED_ADVICE:
      {
        NamedAdvice namedAdvice = (NamedAdvice)theEObject;
        T result = caseNamedAdvice(namedAdvice);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.COMPOUND_ADVICE:
      {
        CompoundAdvice compoundAdvice = (CompoundAdvice)theEObject;
        T result = caseCompoundAdvice(compoundAdvice);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ADVICE_STATEMENT:
      {
        AdviceStatement adviceStatement = (AdviceStatement)theEObject;
        T result = caseAdviceStatement(adviceStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ADVICE_PATH:
      {
        AdvicePath advicePath = (AdvicePath)theEObject;
        T result = caseAdvicePath(advicePath);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ADVICE_PATH_ELEMENT:
      {
        AdvicePathElement advicePathElement = (AdvicePathElement)theEObject;
        T result = caseAdvicePathElement(advicePathElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ADVICE_PATH_CHILDREN:
      {
        AdvicePathChildren advicePathChildren = (AdvicePathChildren)theEObject;
        T result = caseAdvicePathChildren(advicePathChildren);
        if (result == null) result = caseAdvicePathElement(advicePathChildren);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FILTER:
      {
        Filter filter = (Filter)theEObject;
        T result = caseFilter(filter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PRE_CONDITION_ASSERT:
      {
        PreConditionAssert preConditionAssert = (PreConditionAssert)theEObject;
        T result = casePreConditionAssert(preConditionAssert);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.POST_CONDITION_ASSERT:
      {
        PostConditionAssert postConditionAssert = (PostConditionAssert)theEObject;
        T result = casePostConditionAssert(postConditionAssert);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ASSERTION_EXPRESSION:
      {
        AssertionExpression assertionExpression = (AssertionExpression)theEObject;
        T result = caseAssertionExpression(assertionExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.SYNTAX_ELEMENT:
      {
        SyntaxElement syntaxElement = (SyntaxElement)theEObject;
        T result = caseSyntaxElement(syntaxElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.EXPRESSION:
      {
        Expression expression = (Expression)theEObject;
        T result = caseExpression(expression);
        if (result == null) result = casePropertyExpression(expression);
        if (result == null) result = caseSyntaxElement(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VALUE_EXPRESSION:
      {
        ValueExpression valueExpression = (ValueExpression)theEObject;
        T result = caseValueExpression(valueExpression);
        if (result == null) result = caseExpression(valueExpression);
        if (result == null) result = casePropertyExpression(valueExpression);
        if (result == null) result = caseSyntaxElement(valueExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.OPERATION_CALL:
      {
        OperationCall operationCall = (OperationCall)theEObject;
        T result = caseOperationCall(operationCall);
        if (result == null) result = caseExpression(operationCall);
        if (result == null) result = casePropertyExpression(operationCall);
        if (result == null) result = caseSyntaxElement(operationCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.LITERAL:
      {
        Literal literal = (Literal)theEObject;
        T result = caseLiteral(literal);
        if (result == null) result = caseExpression(literal);
        if (result == null) result = casePropertyExpression(literal);
        if (result == null) result = caseSyntaxElement(literal);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.BOOLEAN_LITERAL:
      {
        BooleanLiteral booleanLiteral = (BooleanLiteral)theEObject;
        T result = caseBooleanLiteral(booleanLiteral);
        if (result == null) result = caseLiteral(booleanLiteral);
        if (result == null) result = caseExpression(booleanLiteral);
        if (result == null) result = casePropertyExpression(booleanLiteral);
        if (result == null) result = caseSyntaxElement(booleanLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.INTEGER_LITERAL:
      {
        IntegerLiteral integerLiteral = (IntegerLiteral)theEObject;
        T result = caseIntegerLiteral(integerLiteral);
        if (result == null) result = caseLiteral(integerLiteral);
        if (result == null) result = caseExpression(integerLiteral);
        if (result == null) result = casePropertyExpression(integerLiteral);
        if (result == null) result = caseSyntaxElement(integerLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.NULL_LITERAL:
      {
        NullLiteral nullLiteral = (NullLiteral)theEObject;
        T result = caseNullLiteral(nullLiteral);
        if (result == null) result = caseLiteral(nullLiteral);
        if (result == null) result = caseExpression(nullLiteral);
        if (result == null) result = casePropertyExpression(nullLiteral);
        if (result == null) result = caseSyntaxElement(nullLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.STRING_LITERAL:
      {
        StringLiteral stringLiteral = (StringLiteral)theEObject;
        T result = caseStringLiteral(stringLiteral);
        if (result == null) result = caseLiteral(stringLiteral);
        if (result == null) result = caseExpression(stringLiteral);
        if (result == null) result = casePropertyExpression(stringLiteral);
        if (result == null) result = caseSyntaxElement(stringLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.GLOBAL_VAR_EXPRESSION:
      {
        GlobalVarExpression globalVarExpression = (GlobalVarExpression)theEObject;
        T result = caseGlobalVarExpression(globalVarExpression);
        if (result == null) result = caseExpression(globalVarExpression);
        if (result == null) result = casePropertyExpression(globalVarExpression);
        if (result == null) result = caseSyntaxElement(globalVarExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.BOOLEAN_OPERATION:
      {
        BooleanOperation booleanOperation = (BooleanOperation)theEObject;
        T result = caseBooleanOperation(booleanOperation);
        if (result == null) result = caseExpression(booleanOperation);
        if (result == null) result = casePropertyExpression(booleanOperation);
        if (result == null) result = caseSyntaxElement(booleanOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bee Model</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bee Model</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBeeModel(BeeModel object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Import</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Import</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseImport(Import object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Build Unit</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Build Unit</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBuildUnit(BuildUnit object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Provided Capability</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Provided Capability</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseProvidedCapability(ProvidedCapability object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Required Capability</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Required Capability</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRequiredCapability(RequiredCapability object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Property</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Property</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringProperty(StringProperty object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertyExpression(PropertyExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Synchronization</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Synchronization</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSynchronization(Synchronization object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Build Part</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Build Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBuildPart(BuildPart object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Artifacts Part</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Artifacts Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseArtifactsPart(ArtifactsPart object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Path Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Path Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePathGroup(PathGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group Part</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroupPart(GroupPart object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Prerequisite</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Prerequisite</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrerequisite(Prerequisite object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Closure</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Closure</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseClosure(Closure object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Prerequisite Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Prerequisite Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrerequisiteEntry(PrerequisiteEntry object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Part In Self</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Part In Self</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePartInSelf(PartInSelf object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Capability Referenced Part</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Capability Referenced Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCapabilityReferencedPart(CapabilityReferencedPart object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compound References</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compound References</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompoundReferences(CompoundReferences object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Action Part</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Action Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseActionPart(ActionPart object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParameter(Parameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Result</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Result</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseResult(Result object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Result Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Result Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseResultGroup(ResultGroup object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Repository Configuration</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Repository Configuration</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRepositoryConfiguration(RepositoryConfiguration object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Named Advice</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Advice</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedAdvice(NamedAdvice object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compound Advice</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compound Advice</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompoundAdvice(CompoundAdvice object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Advice Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Advice Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAdviceStatement(AdviceStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Advice Path</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Advice Path</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAdvicePath(AdvicePath object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Advice Path Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Advice Path Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAdvicePathElement(AdvicePathElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Advice Path Children</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Advice Path Children</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAdvicePathChildren(AdvicePathChildren object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Filter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Filter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFilter(Filter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Pre Condition Assert</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pre Condition Assert</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePreConditionAssert(PreConditionAssert object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Post Condition Assert</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Post Condition Assert</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePostConditionAssert(PostConditionAssert object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Assertion Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Assertion Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssertionExpression(AssertionExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Syntax Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Syntax Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSyntaxElement(SyntaxElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpression(Expression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Value Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Value Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseValueExpression(ValueExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperationCall(OperationCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLiteral(Literal object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanLiteral(BooleanLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntegerLiteral(IntegerLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Null Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Null Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNullLiteral(NullLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringLiteral(StringLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Global Var Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Global Var Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGlobalVarExpression(GlobalVarExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanOperation(BooleanOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object)
  {
    return null;
  }

} //BeeLangSwitch
