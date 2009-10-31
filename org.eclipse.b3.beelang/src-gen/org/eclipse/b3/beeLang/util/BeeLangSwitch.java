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
      case BeeLangPackage.JAVA_IMPORTER:
      {
        JavaImporter javaImporter = (JavaImporter)theEObject;
        T result = caseJavaImporter(javaImporter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.NATIVE_IMPORTER:
      {
        NativeImporter nativeImporter = (NativeImporter)theEObject;
        T result = caseNativeImporter(nativeImporter);
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
      case BeeLangPackage.NAMED_PROPERTIES:
      {
        NamedProperties namedProperties = (NamedProperties)theEObject;
        T result = caseNamedProperties(namedProperties);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.COMPOUND_PROPERTY_OPERATION:
      {
        CompoundPropertyOperation compoundPropertyOperation = (CompoundPropertyOperation)theEObject;
        T result = caseCompoundPropertyOperation(compoundPropertyOperation);
        if (result == null) result = casePropertyOperation(compoundPropertyOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PROPERTY_OPERATION:
      {
        PropertyOperation propertyOperation = (PropertyOperation)theEObject;
        T result = casePropertyOperation(propertyOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FILTERED_PROPERTY_OPERATION:
      {
        FilteredPropertyOperation filteredPropertyOperation = (FilteredPropertyOperation)theEObject;
        T result = caseFilteredPropertyOperation(filteredPropertyOperation);
        if (result == null) result = casePropertyOperation(filteredPropertyOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.SET_PROPERTY_OPERATION:
      {
        SetPropertyOperation setPropertyOperation = (SetPropertyOperation)theEObject;
        T result = caseSetPropertyOperation(setPropertyOperation);
        if (result == null) result = casePropertyOperation(setPropertyOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.UNSET_PROPERTY_OPERATION:
      {
        UnsetPropertyOperation unsetPropertyOperation = (UnsetPropertyOperation)theEObject;
        T result = caseUnsetPropertyOperation(unsetPropertyOperation);
        if (result == null) result = casePropertyOperation(unsetPropertyOperation);
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
      case BeeLangPackage.PATH_GROUP:
      {
        PathGroup pathGroup = (PathGroup)theEObject;
        T result = casePathGroup(pathGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PATH_VECTOR_ELEMENT:
      {
        PathVectorElement pathVectorElement = (PathVectorElement)theEObject;
        T result = casePathVectorElement(pathVectorElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FILTERED_PATH_VECTOR:
      {
        FilteredPathVector filteredPathVector = (FilteredPathVector)theEObject;
        T result = caseFilteredPathVector(filteredPathVector);
        if (result == null) result = casePathVectorElement(filteredPathVector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PATH_VECTOR:
      {
        PathVector pathVector = (PathVector)theEObject;
        T result = casePathVector(pathVector);
        if (result == null) result = casePathVectorElement(pathVector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.COMPOUND_PATH_VECTOR:
      {
        CompoundPathVector compoundPathVector = (CompoundPathVector)theEObject;
        T result = caseCompoundPathVector(compoundPathVector);
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
      case BeeLangPackage.BUILD_METHOD:
      {
        BuildMethod buildMethod = (BuildMethod)theEObject;
        T result = caseBuildMethod(buildMethod);
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
      case BeeLangPackage.LAYOUT:
      {
        Layout layout = (Layout)theEObject;
        T result = caseLayout(layout);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.GROUP:
      {
        Group group = (Group)theEObject;
        T result = caseGroup(group);
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
      case BeeLangPackage.ADVICE:
      {
        Advice advice = (Advice)theEObject;
        T result = caseAdvice(advice);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.COMPOUND_ADVICE:
      {
        CompoundAdvice compoundAdvice = (CompoundAdvice)theEObject;
        T result = caseCompoundAdvice(compoundAdvice);
        if (result == null) result = caseAdvice(compoundAdvice);
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
      case BeeLangPackage.SELECTOR:
      {
        Selector selector = (Selector)theEObject;
        T result = caseSelector(selector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.NAME_SELECTOR:
      {
        NameSelector nameSelector = (NameSelector)theEObject;
        T result = caseNameSelector(nameSelector);
        if (result == null) result = caseSelector(nameSelector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.THIS_SELECTOR:
      {
        ThisSelector thisSelector = (ThisSelector)theEObject;
        T result = caseThisSelector(thisSelector);
        if (result == null) result = caseSelector(thisSelector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PARENT_SELECTOR:
      {
        ParentSelector parentSelector = (ParentSelector)theEObject;
        T result = caseParentSelector(parentSelector);
        if (result == null) result = caseSelector(parentSelector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.CHILDREN_SELECTOR:
      {
        ChildrenSelector childrenSelector = (ChildrenSelector)theEObject;
        T result = caseChildrenSelector(childrenSelector);
        if (result == null) result = caseSelector(childrenSelector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ANCESTOR_SELECTOR:
      {
        AncestorSelector ancestorSelector = (AncestorSelector)theEObject;
        T result = caseAncestorSelector(ancestorSelector);
        if (result == null) result = caseSelector(ancestorSelector);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.REGEXP_SELECTOR:
      {
        RegexpSelector regexpSelector = (RegexpSelector)theEObject;
        T result = caseRegexpSelector(regexpSelector);
        if (result == null) result = caseSelector(regexpSelector);
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
      case BeeLangPackage.PRE_CONDITION:
      {
        PreCondition preCondition = (PreCondition)theEObject;
        T result = casePreCondition(preCondition);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.POST_CONDITION:
      {
        PostCondition postCondition = (PostCondition)theEObject;
        T result = casePostCondition(postCondition);
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
      case BeeLangPackage.STATEMENT:
      {
        Statement statement = (Statement)theEObject;
        T result = caseStatement(statement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.BREAK_STATEMENT:
      {
        BreakStatement breakStatement = (BreakStatement)theEObject;
        T result = caseBreakStatement(breakStatement);
        if (result == null) result = caseStatement(breakStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.CONTINUE_STATEMENT:
      {
        ContinueStatement continueStatement = (ContinueStatement)theEObject;
        T result = caseContinueStatement(continueStatement);
        if (result == null) result = caseStatement(continueStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.COMPOUND_STATEMENT:
      {
        CompoundStatement compoundStatement = (CompoundStatement)theEObject;
        T result = caseCompoundStatement(compoundStatement);
        if (result == null) result = caseStatement(compoundStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.STATEMENTS:
      {
        Statements statements = (Statements)theEObject;
        T result = caseStatements(statements);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.WHILE_STATEMENT:
      {
        WhileStatement whileStatement = (WhileStatement)theEObject;
        T result = caseWhileStatement(whileStatement);
        if (result == null) result = caseStatement(whileStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.SWITCH_STATEMENT:
      {
        SwitchStatement switchStatement = (SwitchStatement)theEObject;
        T result = caseSwitchStatement(switchStatement);
        if (result == null) result = caseStatement(switchStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.CASE:
      {
        Case case_ = (Case)theEObject;
        T result = caseCase(case_);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FOR_STATEMENT:
      {
        ForStatement forStatement = (ForStatement)theEObject;
        T result = caseForStatement(forStatement);
        if (result == null) result = caseStatement(forStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.DO_WHILE_STATEMENT:
      {
        DoWhileStatement doWhileStatement = (DoWhileStatement)theEObject;
        T result = caseDoWhileStatement(doWhileStatement);
        if (result == null) result = caseStatement(doWhileStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.RETURN_STATEMENT:
      {
        ReturnStatement returnStatement = (ReturnStatement)theEObject;
        T result = caseReturnStatement(returnStatement);
        if (result == null) result = caseStatement(returnStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FUNCTION_STATEMENT:
      {
        FunctionStatement functionStatement = (FunctionStatement)theEObject;
        T result = caseFunctionStatement(functionStatement);
        if (result == null) result = caseStatement(functionStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.IF_STATEMENT:
      {
        IfStatement ifStatement = (IfStatement)theEObject;
        T result = caseIfStatement(ifStatement);
        if (result == null) result = caseStatement(ifStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.LABEL_STATEMENT:
      {
        LabelStatement labelStatement = (LabelStatement)theEObject;
        T result = caseLabelStatement(labelStatement);
        if (result == null) result = caseStatement(labelStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.THROW_STATEMENT:
      {
        ThrowStatement throwStatement = (ThrowStatement)theEObject;
        T result = caseThrowStatement(throwStatement);
        if (result == null) result = caseStatement(throwStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.TRY_CATCH_STATEMENT:
      {
        TryCatchStatement tryCatchStatement = (TryCatchStatement)theEObject;
        T result = caseTryCatchStatement(tryCatchStatement);
        if (result == null) result = caseStatement(tryCatchStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.CATCH_BLOCK:
      {
        CatchBlock catchBlock = (CatchBlock)theEObject;
        T result = caseCatchBlock(catchBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FINALLY_BLOCK:
      {
        FinallyBlock finallyBlock = (FinallyBlock)theEObject;
        T result = caseFinallyBlock(finallyBlock);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.WITH_STATEMENT:
      {
        WithStatement withStatement = (WithStatement)theEObject;
        T result = caseWithStatement(withStatement);
        if (result == null) result = caseStatement(withStatement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VAR_EXPRESSION_LIST:
      {
        VarExpressionList varExpressionList = (VarExpressionList)theEObject;
        T result = caseVarExpressionList(varExpressionList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.EXPRESSION_LIST:
      {
        ExpressionList expressionList = (ExpressionList)theEObject;
        T result = caseExpressionList(expressionList);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.EXPRESSION:
      {
        Expression expression = (Expression)theEObject;
        T result = caseExpression(expression);
        if (result == null) result = caseStatement(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.UNARY_EXPRESSION:
      {
        UnaryExpression unaryExpression = (UnaryExpression)theEObject;
        T result = caseUnaryExpression(unaryExpression);
        if (result == null) result = caseExpression(unaryExpression);
        if (result == null) result = caseStatement(unaryExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FEATURE:
      {
        Feature feature = (Feature)theEObject;
        T result = caseFeature(feature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VERSION:
      {
        Version version = (Version)theEObject;
        T result = caseVersion(version);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VERSION_RANGE:
      {
        VersionRange versionRange = (VersionRange)theEObject;
        T result = caseVersionRange(versionRange);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VAR_EXPRESSION:
      {
        VarExpression varExpression = (VarExpression)theEObject;
        T result = caseVarExpression(varExpression);
        if (result == null) result = caseExpression(varExpression);
        if (result == null) result = caseStatement(varExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ASSIGNMENT_OPERATION:
      {
        AssignmentOperation assignmentOperation = (AssignmentOperation)theEObject;
        T result = caseAssignmentOperation(assignmentOperation);
        if (result == null) result = caseExpression(assignmentOperation);
        if (result == null) result = caseStatement(assignmentOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.IF_EXPRESSION:
      {
        IfExpression ifExpression = (IfExpression)theEObject;
        T result = caseIfExpression(ifExpression);
        if (result == null) result = caseExpression(ifExpression);
        if (result == null) result = caseStatement(ifExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.BOOLEAN_OPERATION:
      {
        BooleanOperation booleanOperation = (BooleanOperation)theEObject;
        T result = caseBooleanOperation(booleanOperation);
        if (result == null) result = caseExpression(booleanOperation);
        if (result == null) result = caseStatement(booleanOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.OPERATION_CALL:
      {
        OperationCall operationCall = (OperationCall)theEObject;
        T result = caseOperationCall(operationCall);
        if (result == null) result = caseExpression(operationCall);
        if (result == null) result = caseStatement(operationCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.RELATIONAL_OPERATION:
      {
        RelationalOperation relationalOperation = (RelationalOperation)theEObject;
        T result = caseRelationalOperation(relationalOperation);
        if (result == null) result = caseExpression(relationalOperation);
        if (result == null) result = caseStatement(relationalOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.SET_OPERATION_CALL:
      {
        SetOperationCall setOperationCall = (SetOperationCall)theEObject;
        T result = caseSetOperationCall(setOperationCall);
        if (result == null) result = caseExpression(setOperationCall);
        if (result == null) result = caseStatement(setOperationCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.UNARY_OPERATION:
      {
        UnaryOperation unaryOperation = (UnaryOperation)theEObject;
        T result = caseUnaryOperation(unaryOperation);
        if (result == null) result = caseUnaryExpression(unaryOperation);
        if (result == null) result = caseExpression(unaryOperation);
        if (result == null) result = caseStatement(unaryOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.AT_CALL:
      {
        AtCall atCall = (AtCall)theEObject;
        T result = caseAtCall(atCall);
        if (result == null) result = caseExpression(atCall);
        if (result == null) result = caseStatement(atCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FEATURE_CALL:
      {
        FeatureCall featureCall = (FeatureCall)theEObject;
        T result = caseFeatureCall(featureCall);
        if (result == null) result = caseExpression(featureCall);
        if (result == null) result = caseStatement(featureCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PROPERTY_VALUE:
      {
        PropertyValue propertyValue = (PropertyValue)theEObject;
        T result = casePropertyValue(propertyValue);
        if (result == null) result = caseExpression(propertyValue);
        if (result == null) result = caseStatement(propertyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VARIABLE_VALUE:
      {
        VariableValue variableValue = (VariableValue)theEObject;
        T result = caseVariableValue(variableValue);
        if (result == null) result = caseExpression(variableValue);
        if (result == null) result = caseStatement(variableValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.KEYWORD_VARIABLE:
      {
        KeywordVariable keywordVariable = (KeywordVariable)theEObject;
        T result = caseKeywordVariable(keywordVariable);
        if (result == null) result = caseExpression(keywordVariable);
        if (result == null) result = caseStatement(keywordVariable);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.CREATOR_CALL:
      {
        CreatorCall creatorCall = (CreatorCall)theEObject;
        T result = caseCreatorCall(creatorCall);
        if (result == null) result = caseExpression(creatorCall);
        if (result == null) result = caseStatement(creatorCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.LITERAL_ARRAY:
      {
        LiteralArray literalArray = (LiteralArray)theEObject;
        T result = caseLiteralArray(literalArray);
        if (result == null) result = caseExpression(literalArray);
        if (result == null) result = caseStatement(literalArray);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.LITERAL_OBJECT:
      {
        LiteralObject literalObject = (LiteralObject)theEObject;
        T result = caseLiteralObject(literalObject);
        if (result == null) result = caseExpression(literalObject);
        if (result == null) result = caseStatement(literalObject);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.LITERAL_FUNCTION:
      {
        LiteralFunction literalFunction = (LiteralFunction)theEObject;
        T result = caseLiteralFunction(literalFunction);
        if (result == null) result = caseExpression(literalFunction);
        if (result == null) result = caseStatement(literalFunction);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.BOOLEAN_LITERAL:
      {
        BooleanLiteral booleanLiteral = (BooleanLiteral)theEObject;
        T result = caseBooleanLiteral(booleanLiteral);
        if (result == null) result = caseExpression(booleanLiteral);
        if (result == null) result = caseStatement(booleanLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.INTEGER_LITERAL:
      {
        IntegerLiteral integerLiteral = (IntegerLiteral)theEObject;
        T result = caseIntegerLiteral(integerLiteral);
        if (result == null) result = caseExpression(integerLiteral);
        if (result == null) result = caseStatement(integerLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.NULL_LITERAL:
      {
        NullLiteral nullLiteral = (NullLiteral)theEObject;
        T result = caseNullLiteral(nullLiteral);
        if (result == null) result = caseExpression(nullLiteral);
        if (result == null) result = caseStatement(nullLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.UNDEFINED_LITERAL:
      {
        UndefinedLiteral undefinedLiteral = (UndefinedLiteral)theEObject;
        T result = caseUndefinedLiteral(undefinedLiteral);
        if (result == null) result = caseExpression(undefinedLiteral);
        if (result == null) result = caseStatement(undefinedLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.STRING_LITERAL:
      {
        StringLiteral stringLiteral = (StringLiteral)theEObject;
        T result = caseStringLiteral(stringLiteral);
        if (result == null) result = caseExpression(stringLiteral);
        if (result == null) result = caseStatement(stringLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.REAL_LITERAL:
      {
        RealLiteral realLiteral = (RealLiteral)theEObject;
        T result = caseRealLiteral(realLiteral);
        if (result == null) result = caseExpression(realLiteral);
        if (result == null) result = caseStatement(realLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.REGEXP_LITERAL:
      {
        RegexpLiteral regexpLiteral = (RegexpLiteral)theEObject;
        T result = caseRegexpLiteral(regexpLiteral);
        if (result == null) result = caseExpression(regexpLiteral);
        if (result == null) result = caseStatement(regexpLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.QUERY_LITERAL:
      {
        QueryLiteral queryLiteral = (QueryLiteral)theEObject;
        T result = caseQueryLiteral(queryLiteral);
        if (result == null) result = caseExpression(queryLiteral);
        if (result == null) result = caseStatement(queryLiteral);
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
   * Returns the result of interpreting the object as an instance of '<em>Java Importer</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Java Importer</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseJavaImporter(JavaImporter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Native Importer</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Native Importer</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNativeImporter(NativeImporter object)
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
   * Returns the result of interpreting the object as an instance of '<em>Named Properties</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Named Properties</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamedProperties(NamedProperties object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compound Property Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compound Property Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompoundPropertyOperation(CompoundPropertyOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertyOperation(PropertyOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Filtered Property Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Filtered Property Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFilteredPropertyOperation(FilteredPropertyOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Property Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Property Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetPropertyOperation(SetPropertyOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unset Property Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unset Property Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnsetPropertyOperation(UnsetPropertyOperation object)
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
   * Returns the result of interpreting the object as an instance of '<em>Path Vector Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Path Vector Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePathVectorElement(PathVectorElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Filtered Path Vector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Filtered Path Vector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFilteredPathVector(FilteredPathVector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Path Vector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Path Vector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePathVector(PathVector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compound Path Vector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compound Path Vector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompoundPathVector(CompoundPathVector object)
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
   * Returns the result of interpreting the object as an instance of '<em>Build Method</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Build Method</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBuildMethod(BuildMethod object)
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
   * Returns the result of interpreting the object as an instance of '<em>Layout</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Layout</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLayout(Layout object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseGroup(Group object)
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
   * Returns the result of interpreting the object as an instance of '<em>Advice</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Advice</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAdvice(Advice object)
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
   * Returns the result of interpreting the object as an instance of '<em>Selector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Selector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSelector(Selector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Name Selector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Name Selector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNameSelector(NameSelector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>This Selector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>This Selector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseThisSelector(ThisSelector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Parent Selector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Parent Selector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseParentSelector(ParentSelector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Children Selector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Children Selector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseChildrenSelector(ChildrenSelector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ancestor Selector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ancestor Selector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAncestorSelector(AncestorSelector object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Regexp Selector</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Regexp Selector</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRegexpSelector(RegexpSelector object)
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
   * Returns the result of interpreting the object as an instance of '<em>Pre Condition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Pre Condition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePreCondition(PreCondition object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Post Condition</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Post Condition</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePostCondition(PostCondition object)
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
   * Returns the result of interpreting the object as an instance of '<em>Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStatement(Statement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Break Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Break Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBreakStatement(BreakStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Continue Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Continue Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseContinueStatement(ContinueStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Compound Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Compound Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCompoundStatement(CompoundStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Statements</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Statements</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStatements(Statements object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>While Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>While Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWhileStatement(WhileStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Switch Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Switch Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSwitchStatement(SwitchStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Case</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Case</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCase(Case object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>For Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>For Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseForStatement(ForStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Do While Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Do While Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseDoWhileStatement(DoWhileStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Return Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Return Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseReturnStatement(ReturnStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Function Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Function Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFunctionStatement(FunctionStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>If Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>If Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIfStatement(IfStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Label Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Label Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLabelStatement(LabelStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Throw Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Throw Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseThrowStatement(ThrowStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Try Catch Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Try Catch Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTryCatchStatement(TryCatchStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Catch Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Catch Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCatchBlock(CatchBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Finally Block</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Finally Block</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFinallyBlock(FinallyBlock object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>With Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>With Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseWithStatement(WithStatement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Var Expression List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Var Expression List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVarExpressionList(VarExpressionList object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression List</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression List</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpressionList(ExpressionList object)
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
   * Returns the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unary Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnaryExpression(UnaryExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeature(Feature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Version</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Version</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVersion(Version object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Version Range</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Version Range</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVersionRange(VersionRange object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Var Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Var Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVarExpression(VarExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Assignment Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Assignment Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAssignmentOperation(AssignmentOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>If Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>If Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIfExpression(IfExpression object)
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
   * Returns the result of interpreting the object as an instance of '<em>Relational Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Relational Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRelationalOperation(RelationalOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Set Operation Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Set Operation Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseSetOperationCall(SetOperationCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unary Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unary Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnaryOperation(UnaryOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>At Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>At Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseAtCall(AtCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureCall(FeatureCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertyValue(PropertyValue object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variable Value</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Value</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariableValue(VariableValue object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Keyword Variable</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Keyword Variable</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseKeywordVariable(KeywordVariable object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Creator Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Creator Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCreatorCall(CreatorCall object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Literal Array</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal Array</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLiteralArray(LiteralArray object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Literal Object</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal Object</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLiteralObject(LiteralObject object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Literal Function</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal Function</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLiteralFunction(LiteralFunction object)
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
   * Returns the result of interpreting the object as an instance of '<em>Undefined Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Undefined Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUndefinedLiteral(UndefinedLiteral object)
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
   * Returns the result of interpreting the object as an instance of '<em>Real Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Real Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRealLiteral(RealLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Regexp Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Regexp Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRegexpLiteral(RegexpLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Query Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Query Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseQueryLiteral(QueryLiteral object)
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
