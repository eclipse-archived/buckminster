/**
 * <copyright>
 * </copyright>
 *
 */
package org.eclipse.b3.beeLang.impl;

import org.eclipse.b3.beeLang.ActionInputGroup;
import org.eclipse.b3.beeLang.ActionPart;
import org.eclipse.b3.beeLang.Advice;
import org.eclipse.b3.beeLang.AdvicePath;
import org.eclipse.b3.beeLang.AdvicePathChildren;
import org.eclipse.b3.beeLang.AdvicePathElement;
import org.eclipse.b3.beeLang.AdviceStatement;
import org.eclipse.b3.beeLang.ArtifactsPart;
import org.eclipse.b3.beeLang.AssertionExpression;
import org.eclipse.b3.beeLang.AssertionScope;
import org.eclipse.b3.beeLang.AssignmentOperation;
import org.eclipse.b3.beeLang.AtCall;
import org.eclipse.b3.beeLang.BeeLangFactory;
import org.eclipse.b3.beeLang.BeeLangPackage;
import org.eclipse.b3.beeLang.BeeModel;
import org.eclipse.b3.beeLang.BooleanLiteral;
import org.eclipse.b3.beeLang.BooleanOperation;
import org.eclipse.b3.beeLang.BreakStatement;
import org.eclipse.b3.beeLang.BuildPart;
import org.eclipse.b3.beeLang.BuildUnit;
import org.eclipse.b3.beeLang.CapabilityReferencedPart;
import org.eclipse.b3.beeLang.Case;
import org.eclipse.b3.beeLang.Closure;
import org.eclipse.b3.beeLang.CompoundAdvice;
import org.eclipse.b3.beeLang.CompoundReferences;
import org.eclipse.b3.beeLang.CompoundStatement;
import org.eclipse.b3.beeLang.ContinueStatement;
import org.eclipse.b3.beeLang.ExecutionMode;
import org.eclipse.b3.beeLang.ExprStatement;
import org.eclipse.b3.beeLang.Expression;
import org.eclipse.b3.beeLang.FeatureCall;
import org.eclipse.b3.beeLang.Filter;
import org.eclipse.b3.beeLang.ForStatement;
import org.eclipse.b3.beeLang.GroupPart;
import org.eclipse.b3.beeLang.IfExpression;
import org.eclipse.b3.beeLang.Import;
import org.eclipse.b3.beeLang.IntegerLiteral;
import org.eclipse.b3.beeLang.Layout;
import org.eclipse.b3.beeLang.Literal;
import org.eclipse.b3.beeLang.NamedAdvice;
import org.eclipse.b3.beeLang.NullLiteral;
import org.eclipse.b3.beeLang.OperationCall;
import org.eclipse.b3.beeLang.Parameter;
import org.eclipse.b3.beeLang.PartInSelf;
import org.eclipse.b3.beeLang.PathExpression;
import org.eclipse.b3.beeLang.PathGroup;
import org.eclipse.b3.beeLang.PostConditionAssert;
import org.eclipse.b3.beeLang.PostOpCall;
import org.eclipse.b3.beeLang.PreConditionAssert;
import org.eclipse.b3.beeLang.Prerequisite;
import org.eclipse.b3.beeLang.PrerequisiteEntry;
import org.eclipse.b3.beeLang.PropertyExpression;
import org.eclipse.b3.beeLang.PropertyStatement;
import org.eclipse.b3.beeLang.PropertyStatements;
import org.eclipse.b3.beeLang.PropertyValue;
import org.eclipse.b3.beeLang.ProvidedCapability;
import org.eclipse.b3.beeLang.RealLiteral;
import org.eclipse.b3.beeLang.RegexpLiteral;
import org.eclipse.b3.beeLang.RepositoryConfiguration;
import org.eclipse.b3.beeLang.RequiredCapability;
import org.eclipse.b3.beeLang.ReturnStatement;
import org.eclipse.b3.beeLang.Statement;
import org.eclipse.b3.beeLang.Statements;
import org.eclipse.b3.beeLang.StringLiteral;
import org.eclipse.b3.beeLang.SwitchStatement;
import org.eclipse.b3.beeLang.Synchronization;
import org.eclipse.b3.beeLang.SyntaxElement;
import org.eclipse.b3.beeLang.ValueExpression;
import org.eclipse.b3.beeLang.ValueLiteral;
import org.eclipse.b3.beeLang.VarExpression;
import org.eclipse.b3.beeLang.VarExpressionList;
import org.eclipse.b3.beeLang.VariableValue;
import org.eclipse.b3.beeLang.Visibility;
import org.eclipse.b3.beeLang.WhileStatement;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class BeeLangPackageImpl extends EPackageImpl implements BeeLangPackage
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass beeModelEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass importEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass buildUnitEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass providedCapabilityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass requiredCapabilityEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyStatementsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass synchronizationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass buildPartEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass artifactsPartEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pathGroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass pathExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass exprStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass groupPartEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass prerequisiteEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass closureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass prerequisiteEntryEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass partInSelfEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass capabilityReferencedPartEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compoundReferencesEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass actionPartEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass layoutEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass actionInputGroupEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass repositoryConfigurationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namedAdviceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass adviceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compoundAdviceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass adviceStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass advicePathEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass advicePathElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass advicePathChildrenEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass filterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass preConditionAssertEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass postConditionAssertEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass assertionExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass statementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass breakStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass continueStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass compoundStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass statementsEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass whileStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass switchStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass caseEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass forStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass returnStatementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass syntaxElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass varExpressionListEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass varExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operationCallEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableValueEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureCallEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass literalEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass valueLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass booleanLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass integerLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass nullLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass realLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass regexpLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass assignmentOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifExpressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass booleanOperationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass postOpCallEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass atCallEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum visibilityEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum assertionScopeEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum executionModeEEnum = null;

  /**
   * Creates an instance of the model <b>Package</b>, registered with
   * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
   * package URI value.
   * <p>Note: the correct way to create the package is via the static
   * factory method {@link #init init()}, which also performs
   * initialization of the package, or returns the registered package,
   * if one already exists.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.EPackage.Registry
   * @see org.eclipse.b3.beeLang.BeeLangPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private BeeLangPackageImpl()
  {
    super(eNS_URI, BeeLangFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
   * 
   * <p>This method is used to initialize {@link BeeLangPackage#eINSTANCE} when that field is accessed.
   * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static BeeLangPackage init()
  {
    if (isInited) return (BeeLangPackage)EPackage.Registry.INSTANCE.getEPackage(BeeLangPackage.eNS_URI);

    // Obtain or create and register package
    BeeLangPackageImpl theBeeLangPackage = (BeeLangPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof BeeLangPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new BeeLangPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theBeeLangPackage.createPackageContents();

    // Initialize created meta-data
    theBeeLangPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theBeeLangPackage.freeze();

  
    // Update the registry and return the package
    EPackage.Registry.INSTANCE.put(BeeLangPackage.eNS_URI, theBeeLangPackage);
    return theBeeLangPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBeeModel()
  {
    return beeModelEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBeeModel_Imports()
  {
    return (EReference)beeModelEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBeeModel_Body()
  {
    return (EReference)beeModelEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getImport()
  {
    return importEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getImport_ImportClass()
  {
    return (EAttribute)importEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBuildUnit()
  {
    return buildUnitEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBuildUnit_ExecutionMode()
  {
    return (EAttribute)buildUnitEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBuildUnit_Name()
  {
    return (EAttribute)buildUnitEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBuildUnit_Version()
  {
    return (EAttribute)buildUnitEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBuildUnit_Implements()
  {
    return (EAttribute)buildUnitEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildUnit_PropertyStatements()
  {
    return (EReference)buildUnitEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildUnit_ProvidedCapability()
  {
    return (EReference)buildUnitEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildUnit_RequiredCapabilities()
  {
    return (EReference)buildUnitEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildUnit_MetaRequiredCapabilities()
  {
    return (EReference)buildUnitEClass.getEStructuralFeatures().get(7);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildUnit_Advice()
  {
    return (EReference)buildUnitEClass.getEStructuralFeatures().get(8);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildUnit_Synchronize()
  {
    return (EReference)buildUnitEClass.getEStructuralFeatures().get(9);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildUnit_Parts()
  {
    return (EReference)buildUnitEClass.getEStructuralFeatures().get(10);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildUnit_RepositoryConfig()
  {
    return (EReference)buildUnitEClass.getEStructuralFeatures().get(11);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getProvidedCapability()
  {
    return providedCapabilityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getProvidedCapability_Filter()
  {
    return (EReference)providedCapabilityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProvidedCapability_Interface()
  {
    return (EAttribute)providedCapabilityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProvidedCapability_Name()
  {
    return (EAttribute)providedCapabilityEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getProvidedCapability_Version()
  {
    return (EAttribute)providedCapabilityEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRequiredCapability()
  {
    return requiredCapabilityEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRequiredCapability_Filter()
  {
    return (EReference)requiredCapabilityEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRequiredCapability_Interface()
  {
    return (EAttribute)requiredCapabilityEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRequiredCapability_Name()
  {
    return (EAttribute)requiredCapabilityEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRequiredCapability_Range()
  {
    return (EAttribute)requiredCapabilityEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyStatements()
  {
    return propertyStatementsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPropertyStatements_Name()
  {
    return (EAttribute)propertyStatementsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyStatements_Statements()
  {
    return (EReference)propertyStatementsEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyStatement()
  {
    return propertyStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyStatement_Filter()
  {
    return (EReference)propertyStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyStatement_Statements()
  {
    return (EReference)propertyStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPropertyStatement_Immutable()
  {
    return (EAttribute)propertyStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPropertyStatement_Key()
  {
    return (EAttribute)propertyStatementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPropertyStatement_Op()
  {
    return (EAttribute)propertyStatementEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyStatement_Value()
  {
    return (EReference)propertyStatementEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPropertyStatement_UnsetProperties()
  {
    return (EAttribute)propertyStatementEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyExpression()
  {
    return propertyExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSynchronization()
  {
    return synchronizationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getSynchronization_Partrefs()
  {
    return (EAttribute)synchronizationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBuildPart()
  {
    return buildPartEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBuildPart_Visibility()
  {
    return (EAttribute)buildPartEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBuildPart_ProvidedCapabilities()
  {
    return (EReference)buildPartEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getArtifactsPart()
  {
    return artifactsPartEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getArtifactsPart_Name()
  {
    return (EAttribute)artifactsPartEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifactsPart_Asserts()
  {
    return (EReference)artifactsPartEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getArtifactsPart_Paths()
  {
    return (EReference)artifactsPartEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPathGroup()
  {
    return pathGroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPathGroup_Filter()
  {
    return (EReference)pathGroupEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPathGroup_Paths()
  {
    return (EReference)pathGroupEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPathGroup_FirstIsBase()
  {
    return (EAttribute)pathGroupEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPathGroup_UnsetProperties()
  {
    return (EAttribute)pathGroupEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPathGroup_SetProperties()
  {
    return (EReference)pathGroupEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPathExpression()
  {
    return pathExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExprStatement()
  {
    return exprStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExprStatement_Expression()
  {
    return (EReference)exprStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getGroupPart()
  {
    return groupPartEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGroupPart_ExecutionMode()
  {
    return (EAttribute)groupPartEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getGroupPart_Name()
  {
    return (EAttribute)groupPartEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupPart_Asserts()
  {
    return (EReference)groupPartEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getGroupPart_Prerequisites()
  {
    return (EReference)groupPartEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPrerequisite()
  {
    return prerequisiteEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPrerequisite_Surpressed()
  {
    return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrerequisite_Filter()
  {
    return (EReference)prerequisiteEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPrerequisite_Alias()
  {
    return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrerequisite_PartReference()
  {
    return (EReference)prerequisiteEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPrerequisite_Closure()
  {
    return (EReference)prerequisiteEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getClosure()
  {
    return closureEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getClosure_UnsetProperties()
  {
    return (EAttribute)closureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClosure_SetProperties()
  {
    return (EReference)closureEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getClosure_Advice()
  {
    return (EReference)closureEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPrerequisiteEntry()
  {
    return prerequisiteEntryEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPartInSelf()
  {
    return partInSelfEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPartInSelf_PartName()
  {
    return (EAttribute)partInSelfEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCapabilityReferencedPart()
  {
    return capabilityReferencedPartEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCapabilityReferencedPart_Interface()
  {
    return (EAttribute)capabilityReferencedPartEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCapabilityReferencedPart_Name()
  {
    return (EAttribute)capabilityReferencedPartEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCapabilityReferencedPart_Range()
  {
    return (EAttribute)capabilityReferencedPartEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCapabilityReferencedPart_PartName()
  {
    return (EAttribute)capabilityReferencedPartEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompoundReferences()
  {
    return compoundReferencesEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompoundReferences_Prerequisites()
  {
    return (EReference)compoundReferencesEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getActionPart()
  {
    return actionPartEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getActionPart_ExecutionMode()
  {
    return (EAttribute)actionPartEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActionPart_Asserts()
  {
    return (EReference)actionPartEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActionPart_Properties()
  {
    return (EReference)actionPartEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActionPart_Advice()
  {
    return (EReference)actionPartEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActionPart_ResultGroup()
  {
    return (EReference)actionPartEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActionPart_Layout()
  {
    return (EReference)actionPartEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActionPart_Statements()
  {
    return (EReference)actionPartEClass.getEStructuralFeatures().get(6);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getParameter()
  {
    return parameterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getParameter_Val()
  {
    return (EReference)parameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLayout()
  {
    return layoutEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLayout_Filter()
  {
    return (EReference)layoutEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getLayout_Name()
  {
    return (EAttribute)layoutEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLayout_Asserts()
  {
    return (EReference)layoutEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLayout_Paths()
  {
    return (EReference)layoutEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getActionInputGroup()
  {
    return actionInputGroupEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActionInputGroup_Asserts()
  {
    return (EReference)actionInputGroupEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getActionInputGroup_Prerequisites()
  {
    return (EReference)actionInputGroupEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRepositoryConfiguration()
  {
    return repositoryConfigurationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRepositoryConfiguration_Location()
  {
    return (EAttribute)repositoryConfigurationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRepositoryConfiguration_ResolverClass()
  {
    return (EAttribute)repositoryConfigurationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getRepositoryConfiguration_Advice()
  {
    return (EReference)repositoryConfigurationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamedAdvice()
  {
    return namedAdviceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamedAdvice_Name()
  {
    return (EAttribute)namedAdviceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamedAdvice_Advice()
  {
    return (EReference)namedAdviceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAdvice()
  {
    return adviceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompoundAdvice()
  {
    return compoundAdviceEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompoundAdvice_Advice()
  {
    return (EReference)compoundAdviceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAdviceStatement()
  {
    return adviceStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAdviceStatement_Path()
  {
    return (EReference)adviceStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAdviceStatement_Value()
  {
    return (EReference)adviceStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAdviceStatement_Advice()
  {
    return (EReference)adviceStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAdvicePath()
  {
    return advicePathEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAdvicePath_PathElements()
  {
    return (EReference)advicePathEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAdvicePath_PathElement()
  {
    return (EReference)advicePathEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAdvicePathElement()
  {
    return advicePathElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAdvicePathElement_Node()
  {
    return (EAttribute)advicePathElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAdvicePathElement_Predicate()
  {
    return (EReference)advicePathElementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAdvicePathChildren()
  {
    return advicePathChildrenEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFilter()
  {
    return filterEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFilter_Predicate()
  {
    return (EReference)filterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPreConditionAssert()
  {
    return preConditionAssertEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPreConditionAssert_Scope()
  {
    return (EAttribute)preConditionAssertEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPreConditionAssert_Asserts()
  {
    return (EReference)preConditionAssertEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPostConditionAssert()
  {
    return postConditionAssertEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPostConditionAssert_Scope()
  {
    return (EAttribute)postConditionAssertEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPostConditionAssert_Asserts()
  {
    return (EReference)postConditionAssertEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAssertionExpression()
  {
    return assertionExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAssertionExpression_Expr()
  {
    return (EReference)assertionExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAssertionExpression_Message()
  {
    return (EAttribute)assertionExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStatement()
  {
    return statementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBreakStatement()
  {
    return breakStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getContinueStatement()
  {
    return continueStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCompoundStatement()
  {
    return compoundStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCompoundStatement_Statements()
  {
    return (EReference)compoundStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStatements()
  {
    return statementsEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getStatements_Statements()
  {
    return (EReference)statementsEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getWhileStatement()
  {
    return whileStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWhileStatement_Condition()
  {
    return (EReference)whileStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getWhileStatement_Body()
  {
    return (EReference)whileStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSwitchStatement()
  {
    return switchStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSwitchStatement_SwitchExpr()
  {
    return (EReference)switchStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSwitchStatement_Case()
  {
    return (EReference)switchStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getSwitchStatement_Statements()
  {
    return (EReference)switchStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCase()
  {
    return caseEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCase_Condition()
  {
    return (EReference)caseEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCase_Statements()
  {
    return (EReference)caseEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getForStatement()
  {
    return forStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getForStatement_Init()
  {
    return (EReference)forStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getForStatement_Cond()
  {
    return (EReference)forStatementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getForStatement_Iterate()
  {
    return (EReference)forStatementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getForStatement_Body()
  {
    return (EReference)forStatementEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getReturnStatement()
  {
    return returnStatementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getReturnStatement_Return()
  {
    return (EReference)returnStatementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getSyntaxElement()
  {
    return syntaxElementEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVarExpressionList()
  {
    return varExpressionListEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVarExpressionList_Expr()
  {
    return (EReference)varExpressionListEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpression()
  {
    return expressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExpression_Expr()
  {
    return (EReference)expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVarExpression()
  {
    return varExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVarExpression_Name()
  {
    return (EAttribute)varExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVarExpression_Value()
  {
    return (EReference)varExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getValueExpression()
  {
    return valueExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperationCall()
  {
    return operationCallEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOperationCall_Params()
  {
    return (EReference)operationCallEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyValue()
  {
    return propertyValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariableValue()
  {
    return variableValueEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureCall()
  {
    return featureCallEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getFeatureCall_Target()
  {
    return (EReference)featureCallEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getFeatureCall_Type()
  {
    return (EAttribute)featureCallEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLiteral()
  {
    return literalEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getValueLiteral()
  {
    return valueLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBooleanLiteral()
  {
    return booleanLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBooleanLiteral_Val()
  {
    return (EAttribute)booleanLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntegerLiteral()
  {
    return integerLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntegerLiteral_Val()
  {
    return (EAttribute)integerLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNullLiteral()
  {
    return nullLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNullLiteral_Val()
  {
    return (EAttribute)nullLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringLiteral()
  {
    return stringLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStringLiteral_Val()
  {
    return (EAttribute)stringLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRealLiteral()
  {
    return realLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRealLiteral_Val()
  {
    return (EAttribute)realLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRegexpLiteral()
  {
    return regexpLiteralEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRegexpLiteral_Val()
  {
    return (EAttribute)regexpLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAssignmentOperation()
  {
    return assignmentOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAssignmentOperation_Left()
  {
    return (EReference)assignmentOperationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getAssignmentOperation_Op()
  {
    return (EAttribute)assignmentOperationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAssignmentOperation_Right()
  {
    return (EReference)assignmentOperationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfExpression()
  {
    return ifExpressionEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfExpression_Condition()
  {
    return (EReference)ifExpressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfExpression_ThenPart()
  {
    return (EReference)ifExpressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfExpression_ElsePart()
  {
    return (EReference)ifExpressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBooleanOperation()
  {
    return booleanOperationEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBooleanOperation_Left()
  {
    return (EReference)booleanOperationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBooleanOperation_Operator()
  {
    return (EAttribute)booleanOperationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getBooleanOperation_Right()
  {
    return (EReference)booleanOperationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPostOpCall()
  {
    return postOpCallEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPostOpCall_Target()
  {
    return (EReference)postOpCallEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getAtCall()
  {
    return atCallEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAtCall_Target()
  {
    return (EReference)atCallEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAtCall_Index()
  {
    return (EReference)atCallEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getAtCall_Params()
  {
    return (EReference)atCallEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getVisibility()
  {
    return visibilityEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getAssertionScope()
  {
    return assertionScopeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getExecutionMode()
  {
    return executionModeEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BeeLangFactory getBeeLangFactory()
  {
    return (BeeLangFactory)getEFactoryInstance();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isCreated = false;

  /**
   * Creates the meta-model objects for the package.  This method is
   * guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void createPackageContents()
  {
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    beeModelEClass = createEClass(BEE_MODEL);
    createEReference(beeModelEClass, BEE_MODEL__IMPORTS);
    createEReference(beeModelEClass, BEE_MODEL__BODY);

    importEClass = createEClass(IMPORT);
    createEAttribute(importEClass, IMPORT__IMPORT_CLASS);

    buildUnitEClass = createEClass(BUILD_UNIT);
    createEAttribute(buildUnitEClass, BUILD_UNIT__EXECUTION_MODE);
    createEAttribute(buildUnitEClass, BUILD_UNIT__NAME);
    createEAttribute(buildUnitEClass, BUILD_UNIT__VERSION);
    createEAttribute(buildUnitEClass, BUILD_UNIT__IMPLEMENTS);
    createEReference(buildUnitEClass, BUILD_UNIT__PROPERTY_STATEMENTS);
    createEReference(buildUnitEClass, BUILD_UNIT__PROVIDED_CAPABILITY);
    createEReference(buildUnitEClass, BUILD_UNIT__REQUIRED_CAPABILITIES);
    createEReference(buildUnitEClass, BUILD_UNIT__META_REQUIRED_CAPABILITIES);
    createEReference(buildUnitEClass, BUILD_UNIT__ADVICE);
    createEReference(buildUnitEClass, BUILD_UNIT__SYNCHRONIZE);
    createEReference(buildUnitEClass, BUILD_UNIT__PARTS);
    createEReference(buildUnitEClass, BUILD_UNIT__REPOSITORY_CONFIG);

    providedCapabilityEClass = createEClass(PROVIDED_CAPABILITY);
    createEReference(providedCapabilityEClass, PROVIDED_CAPABILITY__FILTER);
    createEAttribute(providedCapabilityEClass, PROVIDED_CAPABILITY__INTERFACE);
    createEAttribute(providedCapabilityEClass, PROVIDED_CAPABILITY__NAME);
    createEAttribute(providedCapabilityEClass, PROVIDED_CAPABILITY__VERSION);

    requiredCapabilityEClass = createEClass(REQUIRED_CAPABILITY);
    createEReference(requiredCapabilityEClass, REQUIRED_CAPABILITY__FILTER);
    createEAttribute(requiredCapabilityEClass, REQUIRED_CAPABILITY__INTERFACE);
    createEAttribute(requiredCapabilityEClass, REQUIRED_CAPABILITY__NAME);
    createEAttribute(requiredCapabilityEClass, REQUIRED_CAPABILITY__RANGE);

    propertyStatementsEClass = createEClass(PROPERTY_STATEMENTS);
    createEAttribute(propertyStatementsEClass, PROPERTY_STATEMENTS__NAME);
    createEReference(propertyStatementsEClass, PROPERTY_STATEMENTS__STATEMENTS);

    propertyStatementEClass = createEClass(PROPERTY_STATEMENT);
    createEReference(propertyStatementEClass, PROPERTY_STATEMENT__FILTER);
    createEReference(propertyStatementEClass, PROPERTY_STATEMENT__STATEMENTS);
    createEAttribute(propertyStatementEClass, PROPERTY_STATEMENT__IMMUTABLE);
    createEAttribute(propertyStatementEClass, PROPERTY_STATEMENT__KEY);
    createEAttribute(propertyStatementEClass, PROPERTY_STATEMENT__OP);
    createEReference(propertyStatementEClass, PROPERTY_STATEMENT__VALUE);
    createEAttribute(propertyStatementEClass, PROPERTY_STATEMENT__UNSET_PROPERTIES);

    propertyExpressionEClass = createEClass(PROPERTY_EXPRESSION);

    synchronizationEClass = createEClass(SYNCHRONIZATION);
    createEAttribute(synchronizationEClass, SYNCHRONIZATION__PARTREFS);

    buildPartEClass = createEClass(BUILD_PART);
    createEAttribute(buildPartEClass, BUILD_PART__VISIBILITY);
    createEReference(buildPartEClass, BUILD_PART__PROVIDED_CAPABILITIES);

    artifactsPartEClass = createEClass(ARTIFACTS_PART);
    createEAttribute(artifactsPartEClass, ARTIFACTS_PART__NAME);
    createEReference(artifactsPartEClass, ARTIFACTS_PART__ASSERTS);
    createEReference(artifactsPartEClass, ARTIFACTS_PART__PATHS);

    pathGroupEClass = createEClass(PATH_GROUP);
    createEReference(pathGroupEClass, PATH_GROUP__FILTER);
    createEReference(pathGroupEClass, PATH_GROUP__PATHS);
    createEAttribute(pathGroupEClass, PATH_GROUP__FIRST_IS_BASE);
    createEAttribute(pathGroupEClass, PATH_GROUP__UNSET_PROPERTIES);
    createEReference(pathGroupEClass, PATH_GROUP__SET_PROPERTIES);

    pathExpressionEClass = createEClass(PATH_EXPRESSION);

    exprStatementEClass = createEClass(EXPR_STATEMENT);
    createEReference(exprStatementEClass, EXPR_STATEMENT__EXPRESSION);

    groupPartEClass = createEClass(GROUP_PART);
    createEAttribute(groupPartEClass, GROUP_PART__EXECUTION_MODE);
    createEAttribute(groupPartEClass, GROUP_PART__NAME);
    createEReference(groupPartEClass, GROUP_PART__ASSERTS);
    createEReference(groupPartEClass, GROUP_PART__PREREQUISITES);

    prerequisiteEClass = createEClass(PREREQUISITE);
    createEAttribute(prerequisiteEClass, PREREQUISITE__SURPRESSED);
    createEReference(prerequisiteEClass, PREREQUISITE__FILTER);
    createEAttribute(prerequisiteEClass, PREREQUISITE__ALIAS);
    createEReference(prerequisiteEClass, PREREQUISITE__PART_REFERENCE);
    createEReference(prerequisiteEClass, PREREQUISITE__CLOSURE);

    closureEClass = createEClass(CLOSURE);
    createEAttribute(closureEClass, CLOSURE__UNSET_PROPERTIES);
    createEReference(closureEClass, CLOSURE__SET_PROPERTIES);
    createEReference(closureEClass, CLOSURE__ADVICE);

    prerequisiteEntryEClass = createEClass(PREREQUISITE_ENTRY);

    partInSelfEClass = createEClass(PART_IN_SELF);
    createEAttribute(partInSelfEClass, PART_IN_SELF__PART_NAME);

    capabilityReferencedPartEClass = createEClass(CAPABILITY_REFERENCED_PART);
    createEAttribute(capabilityReferencedPartEClass, CAPABILITY_REFERENCED_PART__INTERFACE);
    createEAttribute(capabilityReferencedPartEClass, CAPABILITY_REFERENCED_PART__NAME);
    createEAttribute(capabilityReferencedPartEClass, CAPABILITY_REFERENCED_PART__RANGE);
    createEAttribute(capabilityReferencedPartEClass, CAPABILITY_REFERENCED_PART__PART_NAME);

    compoundReferencesEClass = createEClass(COMPOUND_REFERENCES);
    createEReference(compoundReferencesEClass, COMPOUND_REFERENCES__PREREQUISITES);

    actionPartEClass = createEClass(ACTION_PART);
    createEAttribute(actionPartEClass, ACTION_PART__EXECUTION_MODE);
    createEReference(actionPartEClass, ACTION_PART__ASSERTS);
    createEReference(actionPartEClass, ACTION_PART__PROPERTIES);
    createEReference(actionPartEClass, ACTION_PART__ADVICE);
    createEReference(actionPartEClass, ACTION_PART__RESULT_GROUP);
    createEReference(actionPartEClass, ACTION_PART__LAYOUT);
    createEReference(actionPartEClass, ACTION_PART__STATEMENTS);

    parameterEClass = createEClass(PARAMETER);
    createEReference(parameterEClass, PARAMETER__VAL);

    layoutEClass = createEClass(LAYOUT);
    createEReference(layoutEClass, LAYOUT__FILTER);
    createEAttribute(layoutEClass, LAYOUT__NAME);
    createEReference(layoutEClass, LAYOUT__ASSERTS);
    createEReference(layoutEClass, LAYOUT__PATHS);

    actionInputGroupEClass = createEClass(ACTION_INPUT_GROUP);
    createEReference(actionInputGroupEClass, ACTION_INPUT_GROUP__ASSERTS);
    createEReference(actionInputGroupEClass, ACTION_INPUT_GROUP__PREREQUISITES);

    repositoryConfigurationEClass = createEClass(REPOSITORY_CONFIGURATION);
    createEAttribute(repositoryConfigurationEClass, REPOSITORY_CONFIGURATION__LOCATION);
    createEAttribute(repositoryConfigurationEClass, REPOSITORY_CONFIGURATION__RESOLVER_CLASS);
    createEReference(repositoryConfigurationEClass, REPOSITORY_CONFIGURATION__ADVICE);

    namedAdviceEClass = createEClass(NAMED_ADVICE);
    createEAttribute(namedAdviceEClass, NAMED_ADVICE__NAME);
    createEReference(namedAdviceEClass, NAMED_ADVICE__ADVICE);

    adviceEClass = createEClass(ADVICE);

    compoundAdviceEClass = createEClass(COMPOUND_ADVICE);
    createEReference(compoundAdviceEClass, COMPOUND_ADVICE__ADVICE);

    adviceStatementEClass = createEClass(ADVICE_STATEMENT);
    createEReference(adviceStatementEClass, ADVICE_STATEMENT__PATH);
    createEReference(adviceStatementEClass, ADVICE_STATEMENT__VALUE);
    createEReference(adviceStatementEClass, ADVICE_STATEMENT__ADVICE);

    advicePathEClass = createEClass(ADVICE_PATH);
    createEReference(advicePathEClass, ADVICE_PATH__PATH_ELEMENTS);
    createEReference(advicePathEClass, ADVICE_PATH__PATH_ELEMENT);

    advicePathElementEClass = createEClass(ADVICE_PATH_ELEMENT);
    createEAttribute(advicePathElementEClass, ADVICE_PATH_ELEMENT__NODE);
    createEReference(advicePathElementEClass, ADVICE_PATH_ELEMENT__PREDICATE);

    advicePathChildrenEClass = createEClass(ADVICE_PATH_CHILDREN);

    filterEClass = createEClass(FILTER);
    createEReference(filterEClass, FILTER__PREDICATE);

    preConditionAssertEClass = createEClass(PRE_CONDITION_ASSERT);
    createEAttribute(preConditionAssertEClass, PRE_CONDITION_ASSERT__SCOPE);
    createEReference(preConditionAssertEClass, PRE_CONDITION_ASSERT__ASSERTS);

    postConditionAssertEClass = createEClass(POST_CONDITION_ASSERT);
    createEAttribute(postConditionAssertEClass, POST_CONDITION_ASSERT__SCOPE);
    createEReference(postConditionAssertEClass, POST_CONDITION_ASSERT__ASSERTS);

    assertionExpressionEClass = createEClass(ASSERTION_EXPRESSION);
    createEReference(assertionExpressionEClass, ASSERTION_EXPRESSION__EXPR);
    createEAttribute(assertionExpressionEClass, ASSERTION_EXPRESSION__MESSAGE);

    statementEClass = createEClass(STATEMENT);

    breakStatementEClass = createEClass(BREAK_STATEMENT);

    continueStatementEClass = createEClass(CONTINUE_STATEMENT);

    compoundStatementEClass = createEClass(COMPOUND_STATEMENT);
    createEReference(compoundStatementEClass, COMPOUND_STATEMENT__STATEMENTS);

    statementsEClass = createEClass(STATEMENTS);
    createEReference(statementsEClass, STATEMENTS__STATEMENTS);

    whileStatementEClass = createEClass(WHILE_STATEMENT);
    createEReference(whileStatementEClass, WHILE_STATEMENT__CONDITION);
    createEReference(whileStatementEClass, WHILE_STATEMENT__BODY);

    switchStatementEClass = createEClass(SWITCH_STATEMENT);
    createEReference(switchStatementEClass, SWITCH_STATEMENT__SWITCH_EXPR);
    createEReference(switchStatementEClass, SWITCH_STATEMENT__CASE);
    createEReference(switchStatementEClass, SWITCH_STATEMENT__STATEMENTS);

    caseEClass = createEClass(CASE);
    createEReference(caseEClass, CASE__CONDITION);
    createEReference(caseEClass, CASE__STATEMENTS);

    forStatementEClass = createEClass(FOR_STATEMENT);
    createEReference(forStatementEClass, FOR_STATEMENT__INIT);
    createEReference(forStatementEClass, FOR_STATEMENT__COND);
    createEReference(forStatementEClass, FOR_STATEMENT__ITERATE);
    createEReference(forStatementEClass, FOR_STATEMENT__BODY);

    returnStatementEClass = createEClass(RETURN_STATEMENT);
    createEReference(returnStatementEClass, RETURN_STATEMENT__RETURN);

    syntaxElementEClass = createEClass(SYNTAX_ELEMENT);

    varExpressionListEClass = createEClass(VAR_EXPRESSION_LIST);
    createEReference(varExpressionListEClass, VAR_EXPRESSION_LIST__EXPR);

    expressionEClass = createEClass(EXPRESSION);
    createEReference(expressionEClass, EXPRESSION__EXPR);

    varExpressionEClass = createEClass(VAR_EXPRESSION);
    createEAttribute(varExpressionEClass, VAR_EXPRESSION__NAME);
    createEReference(varExpressionEClass, VAR_EXPRESSION__VALUE);

    valueExpressionEClass = createEClass(VALUE_EXPRESSION);

    operationCallEClass = createEClass(OPERATION_CALL);
    createEReference(operationCallEClass, OPERATION_CALL__PARAMS);

    propertyValueEClass = createEClass(PROPERTY_VALUE);

    variableValueEClass = createEClass(VARIABLE_VALUE);

    featureCallEClass = createEClass(FEATURE_CALL);
    createEReference(featureCallEClass, FEATURE_CALL__TARGET);
    createEAttribute(featureCallEClass, FEATURE_CALL__TYPE);

    literalEClass = createEClass(LITERAL);

    valueLiteralEClass = createEClass(VALUE_LITERAL);

    booleanLiteralEClass = createEClass(BOOLEAN_LITERAL);
    createEAttribute(booleanLiteralEClass, BOOLEAN_LITERAL__VAL);

    integerLiteralEClass = createEClass(INTEGER_LITERAL);
    createEAttribute(integerLiteralEClass, INTEGER_LITERAL__VAL);

    nullLiteralEClass = createEClass(NULL_LITERAL);
    createEAttribute(nullLiteralEClass, NULL_LITERAL__VAL);

    stringLiteralEClass = createEClass(STRING_LITERAL);
    createEAttribute(stringLiteralEClass, STRING_LITERAL__VAL);

    realLiteralEClass = createEClass(REAL_LITERAL);
    createEAttribute(realLiteralEClass, REAL_LITERAL__VAL);

    regexpLiteralEClass = createEClass(REGEXP_LITERAL);
    createEAttribute(regexpLiteralEClass, REGEXP_LITERAL__VAL);

    assignmentOperationEClass = createEClass(ASSIGNMENT_OPERATION);
    createEReference(assignmentOperationEClass, ASSIGNMENT_OPERATION__LEFT);
    createEAttribute(assignmentOperationEClass, ASSIGNMENT_OPERATION__OP);
    createEReference(assignmentOperationEClass, ASSIGNMENT_OPERATION__RIGHT);

    ifExpressionEClass = createEClass(IF_EXPRESSION);
    createEReference(ifExpressionEClass, IF_EXPRESSION__CONDITION);
    createEReference(ifExpressionEClass, IF_EXPRESSION__THEN_PART);
    createEReference(ifExpressionEClass, IF_EXPRESSION__ELSE_PART);

    booleanOperationEClass = createEClass(BOOLEAN_OPERATION);
    createEReference(booleanOperationEClass, BOOLEAN_OPERATION__LEFT);
    createEAttribute(booleanOperationEClass, BOOLEAN_OPERATION__OPERATOR);
    createEReference(booleanOperationEClass, BOOLEAN_OPERATION__RIGHT);

    postOpCallEClass = createEClass(POST_OP_CALL);
    createEReference(postOpCallEClass, POST_OP_CALL__TARGET);

    atCallEClass = createEClass(AT_CALL);
    createEReference(atCallEClass, AT_CALL__TARGET);
    createEReference(atCallEClass, AT_CALL__INDEX);
    createEReference(atCallEClass, AT_CALL__PARAMS);

    // Create enums
    visibilityEEnum = createEEnum(VISIBILITY);
    assertionScopeEEnum = createEEnum(ASSERTION_SCOPE);
    executionModeEEnum = createEEnum(EXECUTION_MODE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private boolean isInitialized = false;

  /**
   * Complete the initialization of the package and its meta-model.  This
   * method is guarded to have no affect on any invocation but its first.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void initializePackageContents()
  {
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    artifactsPartEClass.getESuperTypes().add(this.getBuildPart());
    exprStatementEClass.getESuperTypes().add(this.getPathExpression());
    exprStatementEClass.getESuperTypes().add(this.getPrerequisiteEntry());
    groupPartEClass.getESuperTypes().add(this.getBuildPart());
    partInSelfEClass.getESuperTypes().add(this.getPrerequisiteEntry());
    capabilityReferencedPartEClass.getESuperTypes().add(this.getPrerequisiteEntry());
    compoundReferencesEClass.getESuperTypes().add(this.getPrerequisiteEntry());
    actionPartEClass.getESuperTypes().add(this.getBuildPart());
    compoundAdviceEClass.getESuperTypes().add(this.getAdvice());
    advicePathChildrenEClass.getESuperTypes().add(this.getAdvicePathElement());
    breakStatementEClass.getESuperTypes().add(this.getStatement());
    continueStatementEClass.getESuperTypes().add(this.getStatement());
    compoundStatementEClass.getESuperTypes().add(this.getStatement());
    whileStatementEClass.getESuperTypes().add(this.getStatement());
    switchStatementEClass.getESuperTypes().add(this.getStatement());
    forStatementEClass.getESuperTypes().add(this.getStatement());
    returnStatementEClass.getESuperTypes().add(this.getStatement());
    expressionEClass.getESuperTypes().add(this.getPropertyExpression());
    expressionEClass.getESuperTypes().add(this.getSyntaxElement());
    expressionEClass.getESuperTypes().add(this.getVarExpression());
    varExpressionEClass.getESuperTypes().add(this.getStatement());
    valueExpressionEClass.getESuperTypes().add(this.getExpression());
    operationCallEClass.getESuperTypes().add(this.getExpression());
    operationCallEClass.getESuperTypes().add(this.getFeatureCall());
    propertyValueEClass.getESuperTypes().add(this.getExpression());
    variableValueEClass.getESuperTypes().add(this.getExpression());
    featureCallEClass.getESuperTypes().add(this.getExpression());
    literalEClass.getESuperTypes().add(this.getExpression());
    valueLiteralEClass.getESuperTypes().add(this.getLiteral());
    booleanLiteralEClass.getESuperTypes().add(this.getValueLiteral());
    integerLiteralEClass.getESuperTypes().add(this.getValueLiteral());
    nullLiteralEClass.getESuperTypes().add(this.getValueLiteral());
    stringLiteralEClass.getESuperTypes().add(this.getValueLiteral());
    realLiteralEClass.getESuperTypes().add(this.getValueLiteral());
    regexpLiteralEClass.getESuperTypes().add(this.getLiteral());
    assignmentOperationEClass.getESuperTypes().add(this.getExpression());
    ifExpressionEClass.getESuperTypes().add(this.getExpression());
    booleanOperationEClass.getESuperTypes().add(this.getExpression());
    postOpCallEClass.getESuperTypes().add(this.getExpression());
    atCallEClass.getESuperTypes().add(this.getExpression());

    // Initialize classes and features; add operations and parameters
    initEClass(beeModelEClass, BeeModel.class, "BeeModel", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBeeModel_Imports(), this.getImport(), null, "imports", null, 0, -1, BeeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBeeModel_Body(), this.getBuildUnit(), null, "body", null, 0, 1, BeeModel.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(importEClass, Import.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getImport_ImportClass(), ecorePackage.getEString(), "importClass", null, 0, 1, Import.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(buildUnitEClass, BuildUnit.class, "BuildUnit", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getBuildUnit_ExecutionMode(), this.getExecutionMode(), "executionMode", null, 0, 1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBuildUnit_Name(), ecorePackage.getEString(), "name", null, 0, 1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBuildUnit_Version(), ecorePackage.getEString(), "version", null, 0, 1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBuildUnit_Implements(), ecorePackage.getEString(), "implements", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildUnit_PropertyStatements(), this.getPropertyStatements(), null, "propertyStatements", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildUnit_ProvidedCapability(), this.getProvidedCapability(), null, "providedCapability", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildUnit_RequiredCapabilities(), this.getRequiredCapability(), null, "requiredCapabilities", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildUnit_MetaRequiredCapabilities(), this.getRequiredCapability(), null, "metaRequiredCapabilities", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildUnit_Advice(), this.getNamedAdvice(), null, "advice", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildUnit_Synchronize(), this.getSynchronization(), null, "synchronize", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildUnit_Parts(), this.getBuildPart(), null, "parts", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildUnit_RepositoryConfig(), this.getRepositoryConfiguration(), null, "repositoryConfig", null, 0, -1, BuildUnit.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(providedCapabilityEClass, ProvidedCapability.class, "ProvidedCapability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getProvidedCapability_Filter(), this.getFilter(), null, "filter", null, 0, 1, ProvidedCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getProvidedCapability_Interface(), ecorePackage.getEString(), "interface", null, 0, 1, ProvidedCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getProvidedCapability_Name(), ecorePackage.getEString(), "name", null, 0, 1, ProvidedCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getProvidedCapability_Version(), ecorePackage.getEString(), "version", null, 0, 1, ProvidedCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(requiredCapabilityEClass, RequiredCapability.class, "RequiredCapability", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getRequiredCapability_Filter(), this.getFilter(), null, "filter", null, 0, 1, RequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRequiredCapability_Interface(), ecorePackage.getEString(), "interface", null, 0, 1, RequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRequiredCapability_Name(), ecorePackage.getEString(), "name", null, 0, 1, RequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRequiredCapability_Range(), ecorePackage.getEString(), "range", null, 0, 1, RequiredCapability.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyStatementsEClass, PropertyStatements.class, "PropertyStatements", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPropertyStatements_Name(), ecorePackage.getEString(), "name", null, 0, 1, PropertyStatements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPropertyStatements_Statements(), this.getPropertyStatement(), null, "statements", null, 0, 1, PropertyStatements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyStatementEClass, PropertyStatement.class, "PropertyStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPropertyStatement_Filter(), this.getFilter(), null, "filter", null, 0, 1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPropertyStatement_Statements(), this.getPropertyStatement(), null, "statements", null, 0, -1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPropertyStatement_Immutable(), ecorePackage.getEBoolean(), "immutable", null, 0, 1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPropertyStatement_Key(), ecorePackage.getEString(), "key", null, 0, 1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPropertyStatement_Op(), ecorePackage.getEString(), "op", null, 0, 1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPropertyStatement_Value(), this.getPropertyExpression(), null, "value", null, 0, 1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPropertyStatement_UnsetProperties(), ecorePackage.getEString(), "unsetProperties", null, 0, -1, PropertyStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyExpressionEClass, PropertyExpression.class, "PropertyExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(synchronizationEClass, Synchronization.class, "Synchronization", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getSynchronization_Partrefs(), ecorePackage.getEString(), "partrefs", null, 0, -1, Synchronization.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(buildPartEClass, BuildPart.class, "BuildPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getBuildPart_Visibility(), this.getVisibility(), "visibility", null, 0, 1, BuildPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBuildPart_ProvidedCapabilities(), this.getProvidedCapability(), null, "providedCapabilities", null, 0, -1, BuildPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(artifactsPartEClass, ArtifactsPart.class, "ArtifactsPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getArtifactsPart_Name(), ecorePackage.getEString(), "name", null, 0, 1, ArtifactsPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifactsPart_Asserts(), this.getPostConditionAssert(), null, "asserts", null, 0, -1, ArtifactsPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getArtifactsPart_Paths(), this.getPathGroup(), null, "paths", null, 0, -1, ArtifactsPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pathGroupEClass, PathGroup.class, "PathGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPathGroup_Filter(), this.getFilter(), null, "filter", null, 0, 1, PathGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPathGroup_Paths(), this.getPathExpression(), null, "paths", null, 0, -1, PathGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPathGroup_FirstIsBase(), ecorePackage.getEBoolean(), "firstIsBase", null, 0, 1, PathGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPathGroup_UnsetProperties(), ecorePackage.getEString(), "unsetProperties", null, 0, -1, PathGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPathGroup_SetProperties(), this.getPropertyStatement(), null, "setProperties", null, 0, -1, PathGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(pathExpressionEClass, PathExpression.class, "PathExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(exprStatementEClass, ExprStatement.class, "ExprStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExprStatement_Expression(), this.getExpression(), null, "expression", null, 0, 1, ExprStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(groupPartEClass, GroupPart.class, "GroupPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getGroupPart_ExecutionMode(), this.getExecutionMode(), "executionMode", null, 0, 1, GroupPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getGroupPart_Name(), ecorePackage.getEString(), "name", null, 0, 1, GroupPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGroupPart_Asserts(), ecorePackage.getEObject(), null, "asserts", null, 0, -1, GroupPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getGroupPart_Prerequisites(), this.getPrerequisite(), null, "prerequisites", null, 0, -1, GroupPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(prerequisiteEClass, Prerequisite.class, "Prerequisite", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPrerequisite_Surpressed(), ecorePackage.getEBoolean(), "surpressed", null, 0, 1, Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrerequisite_Filter(), this.getFilter(), null, "filter", null, 0, 1, Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getPrerequisite_Alias(), ecorePackage.getEString(), "alias", null, 0, 1, Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrerequisite_PartReference(), this.getPrerequisiteEntry(), null, "partReference", null, 0, 1, Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPrerequisite_Closure(), this.getClosure(), null, "closure", null, 0, 1, Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(closureEClass, Closure.class, "Closure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getClosure_UnsetProperties(), ecorePackage.getEString(), "unsetProperties", null, 0, -1, Closure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClosure_SetProperties(), this.getPropertyStatement(), null, "setProperties", null, 0, -1, Closure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getClosure_Advice(), this.getCompoundAdvice(), null, "advice", null, 0, -1, Closure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(prerequisiteEntryEClass, PrerequisiteEntry.class, "PrerequisiteEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(partInSelfEClass, PartInSelf.class, "PartInSelf", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPartInSelf_PartName(), ecorePackage.getEString(), "partName", null, 0, 1, PartInSelf.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(capabilityReferencedPartEClass, CapabilityReferencedPart.class, "CapabilityReferencedPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getCapabilityReferencedPart_Interface(), ecorePackage.getEString(), "interface", null, 0, 1, CapabilityReferencedPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCapabilityReferencedPart_Name(), ecorePackage.getEString(), "name", null, 0, 1, CapabilityReferencedPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCapabilityReferencedPart_Range(), ecorePackage.getEString(), "range", null, 0, 1, CapabilityReferencedPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getCapabilityReferencedPart_PartName(), ecorePackage.getEString(), "partName", null, 0, 1, CapabilityReferencedPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(compoundReferencesEClass, CompoundReferences.class, "CompoundReferences", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCompoundReferences_Prerequisites(), this.getPrerequisite(), null, "prerequisites", null, 0, -1, CompoundReferences.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(actionPartEClass, ActionPart.class, "ActionPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getActionPart_ExecutionMode(), this.getExecutionMode(), "executionMode", null, 0, 1, ActionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActionPart_Asserts(), ecorePackage.getEObject(), null, "asserts", null, 0, -1, ActionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActionPart_Properties(), this.getPropertyStatement(), null, "properties", null, 0, -1, ActionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActionPart_Advice(), this.getAdvice(), null, "advice", null, 0, 1, ActionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActionPart_ResultGroup(), this.getActionInputGroup(), null, "resultGroup", null, 0, 1, ActionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActionPart_Layout(), this.getLayout(), null, "layout", null, 0, -1, ActionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActionPart_Statements(), this.getStatements(), null, "statements", null, 0, 1, ActionPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(parameterEClass, Parameter.class, "Parameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getParameter_Val(), this.getExpression(), null, "val", null, 0, 1, Parameter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(layoutEClass, Layout.class, "Layout", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getLayout_Filter(), this.getFilter(), null, "filter", null, 0, 1, Layout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getLayout_Name(), ecorePackage.getEString(), "name", null, 0, 1, Layout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLayout_Asserts(), this.getPostConditionAssert(), null, "asserts", null, 0, -1, Layout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getLayout_Paths(), this.getPathGroup(), null, "paths", null, 0, -1, Layout.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(actionInputGroupEClass, ActionInputGroup.class, "ActionInputGroup", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getActionInputGroup_Asserts(), ecorePackage.getEObject(), null, "asserts", null, 0, -1, ActionInputGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getActionInputGroup_Prerequisites(), this.getPrerequisite(), null, "prerequisites", null, 0, -1, ActionInputGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(repositoryConfigurationEClass, RepositoryConfiguration.class, "RepositoryConfiguration", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRepositoryConfiguration_Location(), ecorePackage.getEString(), "location", null, 0, 1, RepositoryConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getRepositoryConfiguration_ResolverClass(), ecorePackage.getEString(), "resolverClass", null, 0, 1, RepositoryConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getRepositoryConfiguration_Advice(), this.getCompoundAdvice(), null, "advice", null, 0, 1, RepositoryConfiguration.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(namedAdviceEClass, NamedAdvice.class, "NamedAdvice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNamedAdvice_Name(), ecorePackage.getEString(), "name", null, 0, 1, NamedAdvice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getNamedAdvice_Advice(), this.getCompoundAdvice(), null, "advice", null, 0, 1, NamedAdvice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(adviceEClass, Advice.class, "Advice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(compoundAdviceEClass, CompoundAdvice.class, "CompoundAdvice", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCompoundAdvice_Advice(), this.getAdviceStatement(), null, "advice", null, 0, -1, CompoundAdvice.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(adviceStatementEClass, AdviceStatement.class, "AdviceStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAdviceStatement_Path(), this.getAdvicePath(), null, "path", null, 0, 1, AdviceStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAdviceStatement_Value(), this.getExpression(), null, "value", null, 0, 1, AdviceStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAdviceStatement_Advice(), this.getCompoundAdvice(), null, "advice", null, 0, 1, AdviceStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(advicePathEClass, AdvicePath.class, "AdvicePath", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAdvicePath_PathElements(), this.getAdvicePathElement(), null, "pathElements", null, 0, -1, AdvicePath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAdvicePath_PathElement(), this.getAdvicePathElement(), null, "pathElement", null, 0, -1, AdvicePath.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(advicePathElementEClass, AdvicePathElement.class, "AdvicePathElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getAdvicePathElement_Node(), ecorePackage.getEString(), "node", null, 0, 1, AdvicePathElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAdvicePathElement_Predicate(), this.getExpression(), null, "predicate", null, 0, 1, AdvicePathElement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(advicePathChildrenEClass, AdvicePathChildren.class, "AdvicePathChildren", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(filterEClass, Filter.class, "Filter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFilter_Predicate(), this.getExpression(), null, "predicate", null, 0, 1, Filter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(preConditionAssertEClass, PreConditionAssert.class, "PreConditionAssert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPreConditionAssert_Scope(), ecorePackage.getEString(), "scope", null, 0, 1, PreConditionAssert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPreConditionAssert_Asserts(), this.getAssertionExpression(), null, "asserts", null, 0, -1, PreConditionAssert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(postConditionAssertEClass, PostConditionAssert.class, "PostConditionAssert", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getPostConditionAssert_Scope(), ecorePackage.getEString(), "scope", null, 0, 1, PostConditionAssert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getPostConditionAssert_Asserts(), this.getAssertionExpression(), null, "asserts", null, 0, -1, PostConditionAssert.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(assertionExpressionEClass, AssertionExpression.class, "AssertionExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAssertionExpression_Expr(), this.getExpression(), null, "expr", null, 0, 1, AssertionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAssertionExpression_Message(), ecorePackage.getEString(), "message", null, 0, 1, AssertionExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(statementEClass, Statement.class, "Statement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(breakStatementEClass, BreakStatement.class, "BreakStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(continueStatementEClass, ContinueStatement.class, "ContinueStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(compoundStatementEClass, CompoundStatement.class, "CompoundStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCompoundStatement_Statements(), this.getStatements(), null, "statements", null, 0, 1, CompoundStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(statementsEClass, Statements.class, "Statements", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getStatements_Statements(), this.getStatement(), null, "statements", null, 0, -1, Statements.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(whileStatementEClass, WhileStatement.class, "WhileStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getWhileStatement_Condition(), this.getVarExpression(), null, "condition", null, 0, 1, WhileStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getWhileStatement_Body(), this.getStatement(), null, "body", null, 0, 1, WhileStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(switchStatementEClass, SwitchStatement.class, "SwitchStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getSwitchStatement_SwitchExpr(), this.getVarExpression(), null, "switchExpr", null, 0, 1, SwitchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSwitchStatement_Case(), this.getCase(), null, "case", null, 0, -1, SwitchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getSwitchStatement_Statements(), this.getStatements(), null, "statements", null, 0, 1, SwitchStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(caseEClass, Case.class, "Case", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getCase_Condition(), this.getValueLiteral(), null, "condition", null, 0, 1, Case.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getCase_Statements(), this.getStatement(), null, "statements", null, 0, -1, Case.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(forStatementEClass, ForStatement.class, "ForStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getForStatement_Init(), this.getVarExpressionList(), null, "init", null, 0, 1, ForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getForStatement_Cond(), this.getExpression(), null, "cond", null, 0, 1, ForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getForStatement_Iterate(), this.getExpression(), null, "iterate", null, 0, 1, ForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getForStatement_Body(), this.getStatement(), null, "body", null, 0, 1, ForStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(returnStatementEClass, ReturnStatement.class, "ReturnStatement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getReturnStatement_Return(), this.getExpression(), null, "return", null, 0, 1, ReturnStatement.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(syntaxElementEClass, SyntaxElement.class, "SyntaxElement", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(varExpressionListEClass, VarExpressionList.class, "VarExpressionList", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getVarExpressionList_Expr(), this.getVarExpression(), null, "expr", null, 0, -1, VarExpressionList.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(expressionEClass, Expression.class, "Expression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getExpression_Expr(), this.getExpression(), null, "expr", null, 0, -1, Expression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(varExpressionEClass, VarExpression.class, "VarExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getVarExpression_Name(), ecorePackage.getEString(), "name", null, 0, 1, VarExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getVarExpression_Value(), this.getExpression(), null, "value", null, 0, 1, VarExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(valueExpressionEClass, ValueExpression.class, "ValueExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(operationCallEClass, OperationCall.class, "OperationCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getOperationCall_Params(), ecorePackage.getEObject(), null, "params", null, 0, -1, OperationCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(propertyValueEClass, PropertyValue.class, "PropertyValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(variableValueEClass, VariableValue.class, "VariableValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(featureCallEClass, FeatureCall.class, "FeatureCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getFeatureCall_Target(), this.getExpression(), null, "target", null, 0, 1, FeatureCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getFeatureCall_Type(), ecorePackage.getEString(), "type", null, 0, 1, FeatureCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(literalEClass, Literal.class, "Literal", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(valueLiteralEClass, ValueLiteral.class, "ValueLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

    initEClass(booleanLiteralEClass, BooleanLiteral.class, "BooleanLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getBooleanLiteral_Val(), ecorePackage.getEString(), "val", null, 0, 1, BooleanLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(integerLiteralEClass, IntegerLiteral.class, "IntegerLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getIntegerLiteral_Val(), ecorePackage.getEInt(), "val", null, 0, 1, IntegerLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(nullLiteralEClass, NullLiteral.class, "NullLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getNullLiteral_Val(), ecorePackage.getEString(), "val", null, 0, 1, NullLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(stringLiteralEClass, StringLiteral.class, "StringLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getStringLiteral_Val(), ecorePackage.getEString(), "val", null, 0, 1, StringLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(realLiteralEClass, RealLiteral.class, "RealLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRealLiteral_Val(), ecorePackage.getEString(), "val", null, 0, 1, RealLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(regexpLiteralEClass, RegexpLiteral.class, "RegexpLiteral", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEAttribute(getRegexpLiteral_Val(), ecorePackage.getEString(), "val", null, 0, 1, RegexpLiteral.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(assignmentOperationEClass, AssignmentOperation.class, "AssignmentOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAssignmentOperation_Left(), this.getExpression(), null, "left", null, 0, -1, AssignmentOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getAssignmentOperation_Op(), ecorePackage.getEString(), "op", null, 0, 1, AssignmentOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAssignmentOperation_Right(), this.getExpression(), null, "right", null, 0, -1, AssignmentOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(ifExpressionEClass, IfExpression.class, "IfExpression", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getIfExpression_Condition(), this.getExpression(), null, "condition", null, 0, 1, IfExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfExpression_ThenPart(), this.getExpression(), null, "thenPart", null, 0, 1, IfExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getIfExpression_ElsePart(), this.getExpression(), null, "elsePart", null, 0, 1, IfExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(booleanOperationEClass, BooleanOperation.class, "BooleanOperation", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getBooleanOperation_Left(), this.getExpression(), null, "left", null, 0, 1, BooleanOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEAttribute(getBooleanOperation_Operator(), ecorePackage.getEString(), "operator", null, 0, 1, BooleanOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getBooleanOperation_Right(), this.getExpression(), null, "right", null, 0, 1, BooleanOperation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(postOpCallEClass, PostOpCall.class, "PostOpCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getPostOpCall_Target(), this.getExpression(), null, "target", null, 0, 1, PostOpCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    initEClass(atCallEClass, AtCall.class, "AtCall", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
    initEReference(getAtCall_Target(), this.getExpression(), null, "target", null, 0, 1, AtCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAtCall_Index(), this.getExpression(), null, "index", null, 0, 1, AtCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
    initEReference(getAtCall_Params(), this.getParameter(), null, "params", null, 0, -1, AtCall.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

    // Initialize enums and add enum literals
    initEEnum(visibilityEEnum, Visibility.class, "Visibility");
    addEEnumLiteral(visibilityEEnum, Visibility.PUBLIC);
    addEEnumLiteral(visibilityEEnum, Visibility.PRIVATE);

    initEEnum(assertionScopeEEnum, AssertionScope.class, "AssertionScope");
    addEEnumLiteral(assertionScopeEEnum, AssertionScope.PRECONDITION);
    addEEnumLiteral(assertionScopeEEnum, AssertionScope.POSTCONDITION);

    initEEnum(executionModeEEnum, ExecutionMode.class, "ExecutionMode");
    addEEnumLiteral(executionModeEEnum, ExecutionMode.PARALLEL);
    addEEnumLiteral(executionModeEEnum, ExecutionMode.SEQUENTIAL);

    // Create resource
    createResource(eNS_URI);
  }

} //BeeLangPackageImpl
