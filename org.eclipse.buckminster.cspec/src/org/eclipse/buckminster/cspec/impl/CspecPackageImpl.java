/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import org.eclipse.buckminster.cspec.Action;
import org.eclipse.buckminster.cspec.ActionAttribute;
import org.eclipse.buckminster.cspec.AlterAction;
import org.eclipse.buckminster.cspec.AlterArtifact;
import org.eclipse.buckminster.cspec.AlterAttribute;
import org.eclipse.buckminster.cspec.AlterGroup;
import org.eclipse.buckminster.cspec.Artifact;
import org.eclipse.buckminster.cspec.Attribute;
import org.eclipse.buckminster.cspec.CSpec;
import org.eclipse.buckminster.cspec.CSpecExtension;
import org.eclipse.buckminster.cspec.CspecFactory;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Generator;
import org.eclipse.buckminster.cspec.Group;
import org.eclipse.buckminster.cspec.PathGroup;
import org.eclipse.buckminster.cspec.Prerequisite;
import org.eclipse.buckminster.cspec.Remove;
import org.eclipse.buckminster.cspec.RemovePath;
import org.eclipse.buckminster.cspec.Rename;
import org.eclipse.buckminster.cspec.UpToDatePolicy;

import org.eclipse.buckminster.model.common.CommonPackage;

import org.eclipse.core.runtime.IPath;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class CspecPackageImpl extends EPackageImpl implements CspecPackage
{
	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link CspecPackage#eINSTANCE} when that field is accessed. Clients should not
	 * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CspecPackage init()
	{
		if(isInited)
			return (CspecPackage)EPackage.Registry.INSTANCE.getEPackage(CspecPackage.eNS_URI);

		// Obtain or create and register package
		CspecPackageImpl theCspecPackage = (CspecPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CspecPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new CspecPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CommonPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCspecPackage.createPackageContents();

		// Initialize created meta-data
		theCspecPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCspecPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CspecPackage.eNS_URI, theCspecPackage);
		return theCspecPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass cSpecEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass attributeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass groupEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass prerequisiteEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass actionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass artifactEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass actionAttributeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pathGroupEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass generatorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass alterAttributeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass alterArtifactEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass alterGroupEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass alterActionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass removePathEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass renameEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass removeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass cSpecExtensionEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum upToDatePolicyEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType iPathEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.buckminster.cspec.CspecPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CspecPackageImpl()
	{
		super(eNS_URI, CspecFactory.eINSTANCE);
	}

	/**
	 * Creates the meta-model objects for the package. This method is guarded to have no affect on any invocation but
	 * its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents()
	{
		if(isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		cSpecEClass = createEClass(CSPEC);
		createEReference(cSpecEClass, CSPEC__DEPENDENCIES);
		createEReference(cSpecEClass, CSPEC__GENERATORS);
		createEReference(cSpecEClass, CSPEC__ATTRIBUTES);
		createEReference(cSpecEClass, CSPEC__DOCUMENTATION);
		createEAttribute(cSpecEClass, CSPEC__SHORT_DESC);
		createEAttribute(cSpecEClass, CSPEC__FILTER);
		createEAttribute(cSpecEClass, CSPEC__PROJECT_INFO);

		attributeEClass = createEClass(ATTRIBUTE);
		createEAttribute(attributeEClass, ATTRIBUTE__NAME);
		createEAttribute(attributeEClass, ATTRIBUTE__PUBLIC);
		createEAttribute(attributeEClass, ATTRIBUTE__FILTER);
		createEReference(attributeEClass, ATTRIBUTE__CSPEC);
		createEReference(attributeEClass, ATTRIBUTE__DOCUMENTATION);

		groupEClass = createEClass(GROUP);
		createEReference(groupEClass, GROUP__PREREQUISITES);
		createEAttribute(groupEClass, GROUP__REBASE);

		prerequisiteEClass = createEClass(PREREQUISITE);
		createEReference(prerequisiteEClass, PREREQUISITE__COMPONENT);
		createEAttribute(prerequisiteEClass, PREREQUISITE__ATTRIBUTE);
		createEAttribute(prerequisiteEClass, PREREQUISITE__ALIAS);
		createEAttribute(prerequisiteEClass, PREREQUISITE__CONTRIBUTOR);
		createEAttribute(prerequisiteEClass, PREREQUISITE__OPTIONAL);
		createEAttribute(prerequisiteEClass, PREREQUISITE__FILTER);
		createEAttribute(prerequisiteEClass, PREREQUISITE__INCLUDE_PATTERN);
		createEAttribute(prerequisiteEClass, PREREQUISITE__EXCLUDE_PATTERN);

		actionEClass = createEClass(ACTION);
		createEReference(actionEClass, ACTION__PROPERTIES);
		createEReference(actionEClass, ACTION__ACTOR_PROPERTIES);
		createEAttribute(actionEClass, ACTION__ACTOR);
		createEReference(actionEClass, ACTION__PRODUCT);
		createEReference(actionEClass, ACTION__PRODUCTS);
		createEAttribute(actionEClass, ACTION__PREREQUISITES_ALIAS);
		createEAttribute(actionEClass, ACTION__PREREQUISITES_REBASE);
		createEAttribute(actionEClass, ACTION__PRODUCT_ALIAS);
		createEAttribute(actionEClass, ACTION__UP_TO_DATE_POLICY);
		createEAttribute(actionEClass, ACTION__PRODUCT_FILE_COUNT);
		createEAttribute(actionEClass, ACTION__PATTERN);
		createEAttribute(actionEClass, ACTION__REPLACEMENT);

		artifactEClass = createEClass(ARTIFACT);

		actionAttributeEClass = createEClass(ACTION_ATTRIBUTE);
		createEAttribute(actionAttributeEClass, ACTION_ATTRIBUTE__ALIAS);
		createEReference(actionAttributeEClass, ACTION_ATTRIBUTE__ACTION);

		pathGroupEClass = createEClass(PATH_GROUP);
		createEAttribute(pathGroupEClass, PATH_GROUP__BASE);
		createEAttribute(pathGroupEClass, PATH_GROUP__PATHS);

		generatorEClass = createEClass(GENERATOR);
		createEAttribute(generatorEClass, GENERATOR__ATTRIBUTE);
		createEReference(generatorEClass, GENERATOR__COMPONENT);
		createEReference(generatorEClass, GENERATOR__GENERATES);
		createEReference(generatorEClass, GENERATOR__CSPEC);

		alterAttributeEClass = createEClass(ALTER_ATTRIBUTE);
		createEReference(alterAttributeEClass, ALTER_ATTRIBUTE__CSPECEXT);

		alterArtifactEClass = createEClass(ALTER_ARTIFACT);
		createEReference(alterArtifactEClass, ALTER_ARTIFACT__REMOVE_PATHS);

		alterGroupEClass = createEClass(ALTER_GROUP);
		createEReference(alterGroupEClass, ALTER_GROUP__REPLACE_PREREQUISITES);
		createEReference(alterGroupEClass, ALTER_GROUP__REMOVE_PREREQUISITES);

		alterActionEClass = createEClass(ALTER_ACTION);
		createEReference(alterActionEClass, ALTER_ACTION__REPLACE_PROPERTIES);
		createEReference(alterActionEClass, ALTER_ACTION__REPLACE_ACTOR_PROPERTIES);
		createEReference(alterActionEClass, ALTER_ACTION__REMOVE_PROPERTIES);
		createEReference(alterActionEClass, ALTER_ACTION__REMOVE_ACTOR_PROPERTIES);
		createEReference(alterActionEClass, ALTER_ACTION__REMOVE_PRODUCTS);
		createEReference(alterActionEClass, ALTER_ACTION__REMOVE_PATHS);

		removePathEClass = createEClass(REMOVE_PATH);
		createEAttribute(removePathEClass, REMOVE_PATH__PATH);

		renameEClass = createEClass(RENAME);
		createEAttribute(renameEClass, RENAME__OLD_NAME);
		createEAttribute(renameEClass, RENAME__NEW_NAME);

		removeEClass = createEClass(REMOVE);
		createEAttribute(removeEClass, REMOVE__NAME);

		cSpecExtensionEClass = createEClass(CSPEC_EXTENSION);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__RENAME_ATTRIBUTES);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__REMOVE_DEPENDENCIES);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__REMOVE_GENERATORS);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__REPLACE_GENERATORS);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__REPLACE_DEPENDENCIES);

		// Create enums
		upToDatePolicyEEnum = createEEnum(UP_TO_DATE_POLICY);

		// Create data types
		iPathEDataType = createEDataType(IPATH);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAction()
	{
		return actionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_Actor()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_ActorProperties()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_Pattern()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_PrerequisitesAlias()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_PrerequisitesRebase()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_Product()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_ProductAlias()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_ProductFileCount()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_Products()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_Properties()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_Replacement()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_UpToDatePolicy()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getActionAttribute()
	{
		return actionAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getActionAttribute_Action()
	{
		return (EReference)actionAttributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActionAttribute_Alias()
	{
		return (EAttribute)actionAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterAction()
	{
		return alterActionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_RemoveActorProperties()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_RemovePaths()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_RemoveProducts()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_RemoveProperties()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_ReplaceActorProperties()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_ReplaceProperties()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterArtifact()
	{
		return alterArtifactEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterArtifact_RemovePaths()
	{
		return (EReference)alterArtifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterAttribute()
	{
		return alterAttributeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAttribute_Cspecext()
	{
		return (EReference)alterAttributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterGroup()
	{
		return alterGroupEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterGroup_RemovePrerequisites()
	{
		return (EReference)alterGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterGroup_ReplacePrerequisites()
	{
		return (EReference)alterGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getArtifact()
	{
		return artifactEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAttribute()
	{
		return attributeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAttribute_Cspec()
	{
		return (EReference)attributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAttribute_Documentation()
	{
		return (EReference)attributeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAttribute_Filter()
	{
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAttribute_Name()
	{
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAttribute_Public()
	{
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCSpec()
	{
		return cSpecEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpec_Attributes()
	{
		return (EReference)cSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpec_Dependencies()
	{
		return (EReference)cSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpec_Documentation()
	{
		return (EReference)cSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCSpec_Filter()
	{
		return (EAttribute)cSpecEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpec_Generators()
	{
		return (EReference)cSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCSpec_ProjectInfo()
	{
		return (EAttribute)cSpecEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCSpec_ShortDesc()
	{
		return (EAttribute)cSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCSpecExtension()
	{
		return cSpecExtensionEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_AttributeAlterations()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_RemoveDependencies()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_RemoveGenerators()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_RenameAttributes()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_ReplaceDependencies()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_ReplaceGenerators()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CspecFactory getCspecFactory()
	{
		return (CspecFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getGenerator()
	{
		return generatorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGenerator_Attribute()
	{
		return (EAttribute)generatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getGenerator_Component()
	{
		return (EReference)generatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getGenerator_Cspec()
	{
		return (EReference)generatorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getGenerator_Generates()
	{
		return (EReference)generatorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getGroup()
	{
		return groupEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getGroup_Prerequisites()
	{
		return (EReference)groupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGroup_Rebase()
	{
		return (EAttribute)groupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getIPath()
	{
		return iPathEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPathGroup()
	{
		return pathGroupEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPathGroup_Base()
	{
		return (EAttribute)pathGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPathGroup_Paths()
	{
		return (EAttribute)pathGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPrerequisite()
	{
		return prerequisiteEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_Alias()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_Attribute()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPrerequisite_Component()
	{
		return (EReference)prerequisiteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_Contributor()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_ExcludePattern()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_Filter()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_IncludePattern()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_Optional()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRemove()
	{
		return removeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRemove_Name()
	{
		return (EAttribute)removeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRemovePath()
	{
		return removePathEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRemovePath_Path()
	{
		return (EAttribute)removePathEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRename()
	{
		return renameEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRename_NewName()
	{
		return (EAttribute)renameEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRename_OldName()
	{
		return (EAttribute)renameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getUpToDatePolicy()
	{
		return upToDatePolicyEEnum;
	}

	/**
	 * Complete the initialization of the package and its meta-model. This method is guarded to have no affect on any
	 * invocation but its first. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents()
	{
		if(isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		cSpecEClass.getESuperTypes().add(theCommonPackage.getComponentIdentifier());
		groupEClass.getESuperTypes().add(this.getAttribute());
		actionEClass.getESuperTypes().add(this.getGroup());
		artifactEClass.getESuperTypes().add(this.getAttribute());
		artifactEClass.getESuperTypes().add(this.getPathGroup());
		actionAttributeEClass.getESuperTypes().add(this.getArtifact());
		alterArtifactEClass.getESuperTypes().add(this.getArtifact());
		alterArtifactEClass.getESuperTypes().add(this.getAlterAttribute());
		alterGroupEClass.getESuperTypes().add(this.getGroup());
		alterGroupEClass.getESuperTypes().add(this.getAlterAttribute());
		alterActionEClass.getESuperTypes().add(this.getAction());
		alterActionEClass.getESuperTypes().add(this.getAlterGroup());
		cSpecExtensionEClass.getESuperTypes().add(this.getCSpec());

		// Initialize classes and features; add operations and parameters
		initEClass(cSpecEClass, CSpec.class, "CSpec", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCSpec_Dependencies(), theCommonPackage.getComponentRequest(), null, "dependencies", null, 0,
				-1, CSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCSpec_Generators(), this.getGenerator(), this.getGenerator_Cspec(), "generators", null, 0,
				-1, CSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCSpec_Attributes(), this.getAttribute(), this.getAttribute_Cspec(), "attributes", null, 0,
				-1, CSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCSpec_Documentation(), theCommonPackage.getDocumentation(), null, "documentation", null, 0,
				1, CSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCSpec_ShortDesc(), ecorePackage.getEString(), "shortDesc", null, 0, 1, CSpec.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCSpec_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1, CSpec.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCSpec_ProjectInfo(), theCommonPackage.getURL(), "projectInfo", null, 0, 1, CSpec.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, Attribute.class, "Attribute", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAttribute_Name(), ecorePackage.getEString(), "name", null, 1, 1, Attribute.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Public(), ecorePackage.getEBoolean(), "public", "true", 0, 1, Attribute.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1, Attribute.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttribute_Cspec(), this.getCSpec(), this.getCSpec_Attributes(), "cspec", null, 1, 1,
				Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttribute_Documentation(), theCommonPackage.getDocumentation(), null, "documentation", null,
				0, 1, Attribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupEClass, Group.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGroup_Prerequisites(), this.getPrerequisite(), null, "prerequisites", null, 0, -1,
				Group.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroup_Rebase(), this.getIPath(), "rebase", null, 0, 1, Group.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(prerequisiteEClass, Prerequisite.class, "Prerequisite", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPrerequisite_Component(), theCommonPackage.getComponentRequest(), null, "component", null, 0,
				1, Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Attribute(), ecorePackage.getEString(), "attribute", null, 1, 1,
				Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Alias(), ecorePackage.getEString(), "alias", null, 0, 1, Prerequisite.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Contributor(), ecorePackage.getEBoolean(), "contributor", "true", 0, 1,
				Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Optional(), ecorePackage.getEBoolean(), "optional", null, 0, 1,
				Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1,
				Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_IncludePattern(), theCommonPackage.getPattern(), "includePattern", null, 0, 1,
				Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_ExcludePattern(), theCommonPackage.getPattern(), "excludePattern", null, 0, 1,
				Prerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(actionEClass, Action.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAction_Properties(), theCommonPackage.getPropertyConstant(), null, "properties", null, 0, -1,
				Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_ActorProperties(), theCommonPackage.getPropertyConstant(), null, "actorProperties",
				null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Actor(), ecorePackage.getEString(), "actor", null, 0, 1, Action.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Product(), this.getPathGroup(), null, "product", null, 0, 1, Action.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Products(), this.getActionAttribute(), this.getActionAttribute_Action(), "products",
				null, 0, -1, Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_PrerequisitesAlias(), ecorePackage.getEString(), "prerequisitesAlias", null, 0, 1,
				Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_PrerequisitesRebase(), this.getIPath(), "prerequisitesRebase", null, 0, 1,
				Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ProductAlias(), ecorePackage.getEString(), "productAlias", null, 0, 1, Action.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_UpToDatePolicy(), this.getUpToDatePolicy(), "upToDatePolicy", "DEFAULT", 0, 1,
				Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_ProductFileCount(), ecorePackage.getEInt(), "productFileCount", null, 0, 1,
				Action.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Pattern(), theCommonPackage.getPattern(), "pattern", null, 0, 1, Action.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Replacement(), ecorePackage.getEString(), "replacement", null, 0, 1, Action.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(artifactEClass, Artifact.class, "Artifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(actionAttributeEClass, ActionAttribute.class, "ActionAttribute", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActionAttribute_Alias(), ecorePackage.getEString(), "alias", null, 0, 1,
				ActionAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getActionAttribute_Action(), this.getAction(), this.getAction_Products(), "action", null, 1, 1,
				ActionAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(pathGroupEClass, PathGroup.class, "PathGroup", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPathGroup_Base(), this.getIPath(), "base", null, 1, 1, PathGroup.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPathGroup_Paths(), this.getIPath(), "paths", null, 0, -1, PathGroup.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(generatorEClass, Generator.class, "Generator", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGenerator_Attribute(), ecorePackage.getEString(), "attribute", null, 1, 1, Generator.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGenerator_Component(), theCommonPackage.getComponentRequest(), null, "component", null, 0, 1,
				Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGenerator_Generates(), theCommonPackage.getComponentIdentifier(), null, "generates", null, 1,
				1, Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGenerator_Cspec(), this.getCSpec(), this.getCSpec_Generators(), "cspec", null, 1, 1,
				Generator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(alterAttributeEClass, AlterAttribute.class, "AlterAttribute", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAlterAttribute_Cspecext(), this.getCSpecExtension(),
				this.getCSpecExtension_AttributeAlterations(), "cspecext", null, 1, 1, AlterAttribute.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(alterArtifactEClass, AlterArtifact.class, "AlterArtifact", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAlterArtifact_RemovePaths(), this.getRemove(), null, "removePaths", null, 0, -1,
				AlterArtifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(alterGroupEClass, AlterGroup.class, "AlterGroup", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAlterGroup_ReplacePrerequisites(), this.getPrerequisite(), null, "replacePrerequisites",
				null, 0, -1, AlterGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterGroup_RemovePrerequisites(), this.getRemove(), null, "removePrerequisites", null, 0, -1,
				AlterGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(alterActionEClass, AlterAction.class, "AlterAction", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAlterAction_ReplaceProperties(), theCommonPackage.getPropertyConstant(), null,
				"replaceProperties", null, 0, -1, AlterAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_ReplaceActorProperties(), theCommonPackage.getPropertyConstant(), null,
				"replaceActorProperties", null, 0, -1, AlterAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_RemoveProperties(), this.getRemove(), null, "removeProperties", null, 0, -1,
				AlterAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_RemoveActorProperties(), this.getRemove(), null, "removeActorProperties", null,
				0, -1, AlterAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_RemoveProducts(), this.getRemove(), null, "removeProducts", null, 0, -1,
				AlterAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_RemovePaths(), this.getRemove(), null, "removePaths", null, 0, -1,
				AlterAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removePathEClass, RemovePath.class, "RemovePath", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemovePath_Path(), ecorePackage.getEString(), "path", null, 1, 1, RemovePath.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(renameEClass, Rename.class, "Rename", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRename_OldName(), ecorePackage.getEString(), "oldName", null, 1, 1, Rename.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRename_NewName(), ecorePackage.getEString(), "newName", null, 1, 1, Rename.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeEClass, Remove.class, "Remove", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemove_Name(), ecorePackage.getEString(), "name", null, 1, 1, Remove.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cSpecExtensionEClass, CSpecExtension.class, "CSpecExtension", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCSpecExtension_AttributeAlterations(), this.getAlterAttribute(),
				this.getAlterAttribute_Cspecext(), "attributeAlterations", null, 0, -1, CSpecExtension.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_RenameAttributes(), this.getRename(), null, "renameAttributes", null, 0, -1,
				CSpecExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_RemoveDependencies(), this.getRemove(), null, "removeDependencies", null, 0,
				-1, CSpecExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_RemoveGenerators(), this.getRemove(), null, "removeGenerators", null, 0, -1,
				CSpecExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_ReplaceGenerators(), this.getGenerator(), null, "replaceGenerators", null, 0,
				-1, CSpecExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_ReplaceDependencies(), theCommonPackage.getComponentRequest(), null,
				"replaceDependencies", null, 0, -1, CSpecExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(upToDatePolicyEEnum, UpToDatePolicy.class, "UpToDatePolicy");
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.DEFAULT);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.ACTOR);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.COUNT);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.MAPPER);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.NOT_EMPTY);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.NEVER);

		// Initialize data types
		initEDataType(iPathEDataType, IPath.class, "IPath", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} // CspecPackageImpl
