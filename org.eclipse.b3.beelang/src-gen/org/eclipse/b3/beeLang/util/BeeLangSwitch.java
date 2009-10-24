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
      case BeeLangPackage.PROPERTY_STATEMENTS:
      {
        PropertyStatements propertyStatements = (PropertyStatements)theEObject;
        T result = casePropertyStatements(propertyStatements);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PROPERTY_STATEMENT:
      {
        PropertyStatement propertyStatement = (PropertyStatement)theEObject;
        T result = casePropertyStatement(propertyStatement);
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
      case BeeLangPackage.PATH_GROUP:
      {
        PathGroup pathGroup = (PathGroup)theEObject;
        T result = casePathGroup(pathGroup);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PATH_EXPRESSION:
      {
        PathExpression pathExpression = (PathExpression)theEObject;
        T result = casePathExpression(pathExpression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.EXPR_STATEMENT:
      {
        ExprStatement exprStatement = (ExprStatement)theEObject;
        T result = caseExprStatement(exprStatement);
        if (result == null) result = casePathExpression(exprStatement);
        if (result == null) result = casePrerequisiteEntry(exprStatement);
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
      case BeeLangPackage.PART:
      {
        Part part = (Part)theEObject;
        T result = casePart(part);
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
      case BeeLangPackage.ACTION_INPUT_GROUP:
      {
        ActionInputGroup actionInputGroup = (ActionInputGroup)theEObject;
        T result = caseActionInputGroup(actionInputGroup);
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
      case BeeLangPackage.SYNTAX_ELEMENT:
      {
        SyntaxElement syntaxElement = (SyntaxElement)theEObject;
        T result = caseSyntaxElement(syntaxElement);
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
      case BeeLangPackage.EXPRESSION:
      {
        Expression expression = (Expression)theEObject;
        T result = caseExpression(expression);
        if (result == null) result = casePropertyExpression(expression);
        if (result == null) result = caseSyntaxElement(expression);
        if (result == null) result = caseVarExpression(expression);
        if (result == null) result = caseStatement(expression);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VAR_EXPRESSION:
      {
        VarExpression varExpression = (VarExpression)theEObject;
        T result = caseVarExpression(varExpression);
        if (result == null) result = caseStatement(varExpression);
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
        if (result == null) result = caseVarExpression(valueExpression);
        if (result == null) result = caseStatement(valueExpression);
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
        if (result == null) result = caseVarExpression(operationCall);
        if (result == null) result = caseStatement(operationCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.PROPERTY_VALUE:
      {
        PropertyValue propertyValue = (PropertyValue)theEObject;
        T result = casePropertyValue(propertyValue);
        if (result == null) result = caseExpression(propertyValue);
        if (result == null) result = casePropertyExpression(propertyValue);
        if (result == null) result = caseSyntaxElement(propertyValue);
        if (result == null) result = caseVarExpression(propertyValue);
        if (result == null) result = caseStatement(propertyValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VARIABLE_VALUE:
      {
        VariableValue variableValue = (VariableValue)theEObject;
        T result = caseVariableValue(variableValue);
        if (result == null) result = caseExpression(variableValue);
        if (result == null) result = casePropertyExpression(variableValue);
        if (result == null) result = caseSyntaxElement(variableValue);
        if (result == null) result = caseVarExpression(variableValue);
        if (result == null) result = caseStatement(variableValue);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.KEYWORD_VARIABLES:
      {
        KeywordVariables keywordVariables = (KeywordVariables)theEObject;
        T result = caseKeywordVariables(keywordVariables);
        if (result == null) result = caseExpression(keywordVariables);
        if (result == null) result = casePropertyExpression(keywordVariables);
        if (result == null) result = caseSyntaxElement(keywordVariables);
        if (result == null) result = caseVarExpression(keywordVariables);
        if (result == null) result = caseStatement(keywordVariables);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.CONSTRUCTOR_CALL_EXPRESSION:
      {
        ConstructorCallExpression constructorCallExpression = (ConstructorCallExpression)theEObject;
        T result = caseConstructorCallExpression(constructorCallExpression);
        if (result == null) result = caseExpression(constructorCallExpression);
        if (result == null) result = casePropertyExpression(constructorCallExpression);
        if (result == null) result = caseSyntaxElement(constructorCallExpression);
        if (result == null) result = caseVarExpression(constructorCallExpression);
        if (result == null) result = caseStatement(constructorCallExpression);
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
        if (result == null) result = caseVarExpression(literal);
        if (result == null) result = caseStatement(literal);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.LITERAL_ARRAY:
      {
        LiteralArray literalArray = (LiteralArray)theEObject;
        T result = caseLiteralArray(literalArray);
        if (result == null) result = caseLiteral(literalArray);
        if (result == null) result = caseExpression(literalArray);
        if (result == null) result = casePropertyExpression(literalArray);
        if (result == null) result = caseSyntaxElement(literalArray);
        if (result == null) result = caseVarExpression(literalArray);
        if (result == null) result = caseStatement(literalArray);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.LITERAL_OBJECT:
      {
        LiteralObject literalObject = (LiteralObject)theEObject;
        T result = caseLiteralObject(literalObject);
        if (result == null) result = caseLiteral(literalObject);
        if (result == null) result = caseExpression(literalObject);
        if (result == null) result = casePropertyExpression(literalObject);
        if (result == null) result = caseSyntaxElement(literalObject);
        if (result == null) result = caseVarExpression(literalObject);
        if (result == null) result = caseStatement(literalObject);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.LITERAL_FUNCTION:
      {
        LiteralFunction literalFunction = (LiteralFunction)theEObject;
        T result = caseLiteralFunction(literalFunction);
        if (result == null) result = caseLiteral(literalFunction);
        if (result == null) result = caseExpression(literalFunction);
        if (result == null) result = casePropertyExpression(literalFunction);
        if (result == null) result = caseSyntaxElement(literalFunction);
        if (result == null) result = caseVarExpression(literalFunction);
        if (result == null) result = caseStatement(literalFunction);
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
      case BeeLangPackage.VALUE_LITERAL:
      {
        ValueLiteral valueLiteral = (ValueLiteral)theEObject;
        T result = caseValueLiteral(valueLiteral);
        if (result == null) result = caseLiteral(valueLiteral);
        if (result == null) result = caseExpression(valueLiteral);
        if (result == null) result = casePropertyExpression(valueLiteral);
        if (result == null) result = caseSyntaxElement(valueLiteral);
        if (result == null) result = caseVarExpression(valueLiteral);
        if (result == null) result = caseStatement(valueLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.BOOLEAN_LITERAL:
      {
        BooleanLiteral booleanLiteral = (BooleanLiteral)theEObject;
        T result = caseBooleanLiteral(booleanLiteral);
        if (result == null) result = caseValueLiteral(booleanLiteral);
        if (result == null) result = caseLiteral(booleanLiteral);
        if (result == null) result = caseExpression(booleanLiteral);
        if (result == null) result = casePropertyExpression(booleanLiteral);
        if (result == null) result = caseSyntaxElement(booleanLiteral);
        if (result == null) result = caseVarExpression(booleanLiteral);
        if (result == null) result = caseStatement(booleanLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.INTEGER_LITERAL:
      {
        IntegerLiteral integerLiteral = (IntegerLiteral)theEObject;
        T result = caseIntegerLiteral(integerLiteral);
        if (result == null) result = caseValueLiteral(integerLiteral);
        if (result == null) result = caseLiteral(integerLiteral);
        if (result == null) result = caseExpression(integerLiteral);
        if (result == null) result = casePropertyExpression(integerLiteral);
        if (result == null) result = caseSyntaxElement(integerLiteral);
        if (result == null) result = caseVarExpression(integerLiteral);
        if (result == null) result = caseStatement(integerLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.NULL_LITERAL:
      {
        NullLiteral nullLiteral = (NullLiteral)theEObject;
        T result = caseNullLiteral(nullLiteral);
        if (result == null) result = caseValueLiteral(nullLiteral);
        if (result == null) result = caseLiteral(nullLiteral);
        if (result == null) result = caseExpression(nullLiteral);
        if (result == null) result = casePropertyExpression(nullLiteral);
        if (result == null) result = caseSyntaxElement(nullLiteral);
        if (result == null) result = caseVarExpression(nullLiteral);
        if (result == null) result = caseStatement(nullLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.VOID_LITERAL:
      {
        VoidLiteral voidLiteral = (VoidLiteral)theEObject;
        T result = caseVoidLiteral(voidLiteral);
        if (result == null) result = caseValueLiteral(voidLiteral);
        if (result == null) result = caseLiteral(voidLiteral);
        if (result == null) result = caseExpression(voidLiteral);
        if (result == null) result = casePropertyExpression(voidLiteral);
        if (result == null) result = caseSyntaxElement(voidLiteral);
        if (result == null) result = caseVarExpression(voidLiteral);
        if (result == null) result = caseStatement(voidLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.STRING_LITERAL:
      {
        StringLiteral stringLiteral = (StringLiteral)theEObject;
        T result = caseStringLiteral(stringLiteral);
        if (result == null) result = caseValueLiteral(stringLiteral);
        if (result == null) result = caseLiteral(stringLiteral);
        if (result == null) result = caseExpression(stringLiteral);
        if (result == null) result = casePropertyExpression(stringLiteral);
        if (result == null) result = caseSyntaxElement(stringLiteral);
        if (result == null) result = caseVarExpression(stringLiteral);
        if (result == null) result = caseStatement(stringLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.REAL_LITERAL:
      {
        RealLiteral realLiteral = (RealLiteral)theEObject;
        T result = caseRealLiteral(realLiteral);
        if (result == null) result = caseValueLiteral(realLiteral);
        if (result == null) result = caseLiteral(realLiteral);
        if (result == null) result = caseExpression(realLiteral);
        if (result == null) result = casePropertyExpression(realLiteral);
        if (result == null) result = caseSyntaxElement(realLiteral);
        if (result == null) result = caseVarExpression(realLiteral);
        if (result == null) result = caseStatement(realLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.REGEXP_LITERAL:
      {
        RegexpLiteral regexpLiteral = (RegexpLiteral)theEObject;
        T result = caseRegexpLiteral(regexpLiteral);
        if (result == null) result = caseLiteral(regexpLiteral);
        if (result == null) result = caseExpression(regexpLiteral);
        if (result == null) result = casePropertyExpression(regexpLiteral);
        if (result == null) result = caseSyntaxElement(regexpLiteral);
        if (result == null) result = caseVarExpression(regexpLiteral);
        if (result == null) result = caseStatement(regexpLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.ASSIGNMENT_OPERATION:
      {
        AssignmentOperation assignmentOperation = (AssignmentOperation)theEObject;
        T result = caseAssignmentOperation(assignmentOperation);
        if (result == null) result = caseExpression(assignmentOperation);
        if (result == null) result = casePropertyExpression(assignmentOperation);
        if (result == null) result = caseSyntaxElement(assignmentOperation);
        if (result == null) result = caseVarExpression(assignmentOperation);
        if (result == null) result = caseStatement(assignmentOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.IF_EXPRESSION:
      {
        IfExpression ifExpression = (IfExpression)theEObject;
        T result = caseIfExpression(ifExpression);
        if (result == null) result = caseExpression(ifExpression);
        if (result == null) result = casePropertyExpression(ifExpression);
        if (result == null) result = caseSyntaxElement(ifExpression);
        if (result == null) result = caseVarExpression(ifExpression);
        if (result == null) result = caseStatement(ifExpression);
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
        if (result == null) result = caseVarExpression(booleanOperation);
        if (result == null) result = caseStatement(booleanOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.POST_OP_CALL:
      {
        PostOpCall postOpCall = (PostOpCall)theEObject;
        T result = casePostOpCall(postOpCall);
        if (result == null) result = caseExpression(postOpCall);
        if (result == null) result = casePropertyExpression(postOpCall);
        if (result == null) result = caseSyntaxElement(postOpCall);
        if (result == null) result = caseVarExpression(postOpCall);
        if (result == null) result = caseStatement(postOpCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.AT_CALL:
      {
        AtCall atCall = (AtCall)theEObject;
        T result = caseAtCall(atCall);
        if (result == null) result = caseExpression(atCall);
        if (result == null) result = casePropertyExpression(atCall);
        if (result == null) result = caseSyntaxElement(atCall);
        if (result == null) result = caseVarExpression(atCall);
        if (result == null) result = caseStatement(atCall);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case BeeLangPackage.FEATURE_CALL:
      {
        FeatureCall featureCall = (FeatureCall)theEObject;
        T result = caseFeatureCall(featureCall);
        if (result == null) result = caseExpression(featureCall);
        if (result == null) result = casePropertyExpression(featureCall);
        if (result == null) result = caseSyntaxElement(featureCall);
        if (result == null) result = caseVarExpression(featureCall);
        if (result == null) result = caseStatement(featureCall);
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
   * Returns the result of interpreting the object as an instance of '<em>Property Statements</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Statements</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertyStatements(PropertyStatements object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertyStatement(PropertyStatement object)
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
   * Returns the result of interpreting the object as an instance of '<em>Path Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Path Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePathExpression(PathExpression object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expr Statement</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expr Statement</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExprStatement(ExprStatement object)
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
   * Returns the result of interpreting the object as an instance of '<em>Part</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Part</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePart(Part object)
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
   * Returns the result of interpreting the object as an instance of '<em>Action Input Group</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Action Input Group</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseActionInputGroup(ActionInputGroup object)
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
   * Returns the result of interpreting the object as an instance of '<em>Keyword Variables</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Keyword Variables</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseKeywordVariables(KeywordVariables object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constructor Call Expression</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constructor Call Expression</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstructorCallExpression(ConstructorCallExpression object)
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
   * Returns the result of interpreting the object as an instance of '<em>Value Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Value Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseValueLiteral(ValueLiteral object)
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
   * Returns the result of interpreting the object as an instance of '<em>Void Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Void Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVoidLiteral(VoidLiteral object)
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
   * Returns the result of interpreting the object as an instance of '<em>Post Op Call</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Post Op Call</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePostOpCall(PostOpCall object)
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
