/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import org.eclipse.buckminster.cspecxml.IAction;
import org.eclipse.buckminster.cspecxml.IActionArtifact;
import org.eclipse.buckminster.cspecxml.IActionsType;
import org.eclipse.buckminster.cspecxml.IAlterAction;
import org.eclipse.buckminster.cspecxml.IAlterActionsType;
import org.eclipse.buckminster.cspecxml.IAlterArtifact;
import org.eclipse.buckminster.cspecxml.IAlterArtifactsType;
import org.eclipse.buckminster.cspecxml.IAlterDependenciesType;
import org.eclipse.buckminster.cspecxml.IAlterGroup;
import org.eclipse.buckminster.cspecxml.IAlterGroupsType;
import org.eclipse.buckminster.cspecxml.IAlterPrerequisites;
import org.eclipse.buckminster.cspecxml.IAlterProductsType;
import org.eclipse.buckminster.cspecxml.IAlterProperties;
import org.eclipse.buckminster.cspecxml.IArtifact;
import org.eclipse.buckminster.cspecxml.IArtifactsType;
import org.eclipse.buckminster.cspecxml.IAttribute;
import org.eclipse.buckminster.cspecxml.ICSpecExtension;
import org.eclipse.buckminster.cspecxml.ICSpecXMLFactory;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IComponentRequest;
import org.eclipse.buckminster.cspecxml.IComponentSpec;
import org.eclipse.buckminster.cspecxml.IComponentSpecBase;
import org.eclipse.buckminster.cspecxml.IDefinitions;
import org.eclipse.buckminster.cspecxml.IDependenciesType;
import org.eclipse.buckminster.cspecxml.IDocumentRoot;
import org.eclipse.buckminster.cspecxml.IGenerator;
import org.eclipse.buckminster.cspecxml.IGeneratorsType;
import org.eclipse.buckminster.cspecxml.IGroup;
import org.eclipse.buckminster.cspecxml.IGroupsType;
import org.eclipse.buckminster.cspecxml.IImport;
import org.eclipse.buckminster.cspecxml.IParameterType;
import org.eclipse.buckminster.cspecxml.IPath;
import org.eclipse.buckminster.cspecxml.IPrerequisite;
import org.eclipse.buckminster.cspecxml.IPrerequisites;
import org.eclipse.buckminster.cspecxml.IProductsType;
import org.eclipse.buckminster.cspecxml.IProperties;
import org.eclipse.buckminster.cspecxml.IRemove;
import org.eclipse.buckminster.cspecxml.IRemovePath;
import org.eclipse.buckminster.cspecxml.IRemoveProperty;
import org.eclipse.buckminster.cspecxml.IRename;
import org.eclipse.buckminster.cspecxml.UpToDatePolicy;

import org.eclipse.buckminster.model.common.CommonPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.eclipse.emf.ecore.xml.namespace.XMLNamespacePackage;

import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class CSpecXMLPackageImpl extends EPackageImpl implements ICSpecXMLPackage
{
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
	private EClass actionArtifactEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass actionsTypeEClass = null;

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
	private EClass alterActionsTypeEClass = null;

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
	private EClass alterArtifactsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass alterDependenciesTypeEClass = null;

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
	private EClass alterGroupsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass alterPrerequisitesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass alterProductsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass alterPropertiesEClass = null;

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
	private EClass artifactsTypeEClass = null;

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
	private EClass componentRequestEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass componentSpecEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass componentSpecBaseEClass = null;

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
	private EClass definitionsEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass dependenciesTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass documentRootEClass = null;

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
	private EClass generatorsTypeEClass = null;

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
	private EClass groupsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass importEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass parameterTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pathEClass = null;

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
	private EClass prerequisitesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass productsTypeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass propertiesEClass = null;

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
	private EClass removePathEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass removePropertyEClass = null;

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
	private EEnum upToDatePolicyEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType upToDatePolicyObjectEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link ICSpecXMLPackage#eINSTANCE} when that field is accessed. Clients should
	 * not invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ICSpecXMLPackage init()
	{
		if(isInited)
			return (ICSpecXMLPackage)EPackage.Registry.INSTANCE.getEPackage(ICSpecXMLPackage.eNS_URI);

		// Obtain or create and register package
		CSpecXMLPackageImpl theCSpecXMLPackage = (CSpecXMLPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CSpecXMLPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new CSpecXMLPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CommonPackage.eINSTANCE.eClass();
		XMLNamespacePackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theCSpecXMLPackage.createPackageContents();

		// Initialize created meta-data
		theCSpecXMLPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCSpecXMLPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ICSpecXMLPackage.eNS_URI, theCSpecXMLPackage);
		return theCSpecXMLPackage;
	}

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
	 * @see org.eclipse.buckminster.cspecxml.ICSpecXMLPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CSpecXMLPackageImpl()
	{
		super(eNS_URI, ICSpecXMLFactory.eINSTANCE);
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
		actionEClass = createEClass(ACTION);
		createEAttribute(actionEClass, ACTION__GROUP);
		createEReference(actionEClass, ACTION__DEFINITIONS);
		createEReference(actionEClass, ACTION__ACTOR_PROPERTIES);
		createEReference(actionEClass, ACTION__PROPERTIES);
		createEReference(actionEClass, ACTION__PREREQUISITES);
		createEReference(actionEClass, ACTION__PRODUCTS);
		createEAttribute(actionEClass, ACTION__ACTOR);
		createEAttribute(actionEClass, ACTION__ALWAYS);
		createEAttribute(actionEClass, ACTION__ASSIGN_CONSOLE_SUPPORT);
		createEAttribute(actionEClass, ACTION__ENABLED);
		createEAttribute(actionEClass, ACTION__FILTER);

		actionArtifactEClass = createEClass(ACTION_ARTIFACT);
		createEAttribute(actionArtifactEClass, ACTION_ARTIFACT__ALIAS);

		actionsTypeEClass = createEClass(ACTIONS_TYPE);
		createEAttribute(actionsTypeEClass, ACTIONS_TYPE__GROUP);
		createEReference(actionsTypeEClass, ACTIONS_TYPE__PUBLIC);
		createEReference(actionsTypeEClass, ACTIONS_TYPE__PRIVATE);

		alterActionEClass = createEClass(ALTER_ACTION);
		createEAttribute(alterActionEClass, ALTER_ACTION__GROUP1);
		createEReference(alterActionEClass, ALTER_ACTION__ALTER_PREREQUISITES);
		createEReference(alterActionEClass, ALTER_ACTION__ALTER_ACTOR_PROPERTIES);
		createEReference(alterActionEClass, ALTER_ACTION__ALTER_PROPERTIES);
		createEReference(alterActionEClass, ALTER_ACTION__ALTER_PRODUCTS);

		alterActionsTypeEClass = createEClass(ALTER_ACTIONS_TYPE);
		createEAttribute(alterActionsTypeEClass, ALTER_ACTIONS_TYPE__GROUP);
		createEReference(alterActionsTypeEClass, ALTER_ACTIONS_TYPE__PUBLIC);
		createEReference(alterActionsTypeEClass, ALTER_ACTIONS_TYPE__PRIVATE);
		createEReference(alterActionsTypeEClass, ALTER_ACTIONS_TYPE__REMOVE);
		createEReference(alterActionsTypeEClass, ALTER_ACTIONS_TYPE__RENAME);

		alterArtifactEClass = createEClass(ALTER_ARTIFACT);
		createEAttribute(alterArtifactEClass, ALTER_ARTIFACT__GROUP1);
		createEReference(alterArtifactEClass, ALTER_ARTIFACT__REMOVE_PATH);

		alterArtifactsTypeEClass = createEClass(ALTER_ARTIFACTS_TYPE);
		createEAttribute(alterArtifactsTypeEClass, ALTER_ARTIFACTS_TYPE__GROUP);
		createEReference(alterArtifactsTypeEClass, ALTER_ARTIFACTS_TYPE__PUBLIC);
		createEReference(alterArtifactsTypeEClass, ALTER_ARTIFACTS_TYPE__PRIVATE);
		createEReference(alterArtifactsTypeEClass, ALTER_ARTIFACTS_TYPE__REMOVE);
		createEReference(alterArtifactsTypeEClass, ALTER_ARTIFACTS_TYPE__RENAME);

		alterDependenciesTypeEClass = createEClass(ALTER_DEPENDENCIES_TYPE);
		createEAttribute(alterDependenciesTypeEClass, ALTER_DEPENDENCIES_TYPE__GROUP);
		createEReference(alterDependenciesTypeEClass, ALTER_DEPENDENCIES_TYPE__DEPENDENCY);
		createEReference(alterDependenciesTypeEClass, ALTER_DEPENDENCIES_TYPE__REMOVE);

		alterGroupEClass = createEClass(ALTER_GROUP);
		createEAttribute(alterGroupEClass, ALTER_GROUP__GROUP1);
		createEReference(alterGroupEClass, ALTER_GROUP__ALTER_ATTRIBUTE);
		createEReference(alterGroupEClass, ALTER_GROUP__REMOVE);

		alterGroupsTypeEClass = createEClass(ALTER_GROUPS_TYPE);
		createEAttribute(alterGroupsTypeEClass, ALTER_GROUPS_TYPE__GROUP);
		createEReference(alterGroupsTypeEClass, ALTER_GROUPS_TYPE__PUBLIC);
		createEReference(alterGroupsTypeEClass, ALTER_GROUPS_TYPE__PRIVATE);
		createEReference(alterGroupsTypeEClass, ALTER_GROUPS_TYPE__REMOVE);
		createEReference(alterGroupsTypeEClass, ALTER_GROUPS_TYPE__RENAME);

		alterPrerequisitesEClass = createEClass(ALTER_PREREQUISITES);
		createEAttribute(alterPrerequisitesEClass, ALTER_PREREQUISITES__GROUP1);
		createEReference(alterPrerequisitesEClass, ALTER_PREREQUISITES__ALTER_ATTRIBUTE);
		createEReference(alterPrerequisitesEClass, ALTER_PREREQUISITES__REMOVE);

		alterProductsTypeEClass = createEClass(ALTER_PRODUCTS_TYPE);
		createEAttribute(alterProductsTypeEClass, ALTER_PRODUCTS_TYPE__GROUP);
		createEReference(alterProductsTypeEClass, ALTER_PRODUCTS_TYPE__PUBLIC);
		createEReference(alterProductsTypeEClass, ALTER_PRODUCTS_TYPE__PRIVATE);
		createEReference(alterProductsTypeEClass, ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT);
		createEReference(alterProductsTypeEClass, ALTER_PRODUCTS_TYPE__REMOVE_PATH);

		alterPropertiesEClass = createEClass(ALTER_PROPERTIES);
		createEAttribute(alterPropertiesEClass, ALTER_PROPERTIES__GROUP);
		createEReference(alterPropertiesEClass, ALTER_PROPERTIES__PROPERTY);
		createEReference(alterPropertiesEClass, ALTER_PROPERTIES__REMOVE);

		artifactEClass = createEClass(ARTIFACT);
		createEAttribute(artifactEClass, ARTIFACT__GROUP);
		createEReference(artifactEClass, ARTIFACT__DEFINITIONS);
		createEReference(artifactEClass, ARTIFACT__PATH);
		createEAttribute(artifactEClass, ARTIFACT__BASE);
		createEAttribute(artifactEClass, ARTIFACT__FILTER);
		createEAttribute(artifactEClass, ARTIFACT__PATH1);
		createEAttribute(artifactEClass, ARTIFACT__TYPE);

		artifactsTypeEClass = createEClass(ARTIFACTS_TYPE);
		createEAttribute(artifactsTypeEClass, ARTIFACTS_TYPE__GROUP);
		createEReference(artifactsTypeEClass, ARTIFACTS_TYPE__PUBLIC);
		createEReference(artifactsTypeEClass, ARTIFACTS_TYPE__PRIVATE);

		attributeEClass = createEClass(ATTRIBUTE);
		createEReference(attributeEClass, ATTRIBUTE__DOCUMENTATION);
		createEReference(attributeEClass, ATTRIBUTE__PARAMETER);
		createEAttribute(attributeEClass, ATTRIBUTE__NAME);
		createEAttribute(attributeEClass, ATTRIBUTE__VERSION);

		componentRequestEClass = createEClass(COMPONENT_REQUEST);
		createEReference(componentRequestEClass, COMPONENT_REQUEST__IMPORT);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__COMPONENT_TYPE);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__FILTER);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__NAME);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__VERSION_DESIGNATOR);
		createEAttribute(componentRequestEClass, COMPONENT_REQUEST__VERSION_TYPE);

		componentSpecEClass = createEClass(COMPONENT_SPEC);
		createEAttribute(componentSpecEClass, COMPONENT_SPEC__NAME);

		componentSpecBaseEClass = createEClass(COMPONENT_SPEC_BASE);
		createEReference(componentSpecBaseEClass, COMPONENT_SPEC_BASE__DOCUMENTATION);
		createEAttribute(componentSpecBaseEClass, COMPONENT_SPEC_BASE__GROUP);
		createEReference(componentSpecBaseEClass, COMPONENT_SPEC_BASE__DEPENDENCIES);
		createEReference(componentSpecBaseEClass, COMPONENT_SPEC_BASE__GENERATORS);
		createEReference(componentSpecBaseEClass, COMPONENT_SPEC_BASE__ARTIFACTS);
		createEReference(componentSpecBaseEClass, COMPONENT_SPEC_BASE__GROUPS);
		createEReference(componentSpecBaseEClass, COMPONENT_SPEC_BASE__ACTIONS);
		createEAttribute(componentSpecBaseEClass, COMPONENT_SPEC_BASE__CATEGORY);
		createEAttribute(componentSpecBaseEClass, COMPONENT_SPEC_BASE__COMPONENT_TYPE);
		createEAttribute(componentSpecBaseEClass, COMPONENT_SPEC_BASE__FILTER);
		createEAttribute(componentSpecBaseEClass, COMPONENT_SPEC_BASE__PROJECT_INFO);
		createEAttribute(componentSpecBaseEClass, COMPONENT_SPEC_BASE__SHORT_DESC);
		createEAttribute(componentSpecBaseEClass, COMPONENT_SPEC_BASE__VERSION_STRING);
		createEAttribute(componentSpecBaseEClass, COMPONENT_SPEC_BASE__VERSION_TYPE);

		cSpecExtensionEClass = createEClass(CSPEC_EXTENSION);
		createEAttribute(cSpecExtensionEClass, CSPEC_EXTENSION__GROUP1);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__ALTER_ACTIONS);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__ALTER_ARTIFACTS);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__ALTER_DEPENDENCIES);
		createEReference(cSpecExtensionEClass, CSPEC_EXTENSION__ALTER_GROUPS);

		definitionsEClass = createEClass(DEFINITIONS);
		createEReference(definitionsEClass, DEFINITIONS__DEFINE);

		dependenciesTypeEClass = createEClass(DEPENDENCIES_TYPE);
		createEReference(dependenciesTypeEClass, DEPENDENCIES_TYPE__DEPENDENCY);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CSPEC);
		createEReference(documentRootEClass, DOCUMENT_ROOT__CSPEC_EXTENSION);

		generatorEClass = createEClass(GENERATOR);
		createEAttribute(generatorEClass, GENERATOR__ATTRIBUTE);
		createEAttribute(generatorEClass, GENERATOR__COMPONENT);
		createEAttribute(generatorEClass, GENERATOR__COMPONENT_TYPE);
		createEAttribute(generatorEClass, GENERATOR__GENERATES);
		createEAttribute(generatorEClass, GENERATOR__GENERATES_TYPE);
		createEAttribute(generatorEClass, GENERATOR__GENERATES_VERSION_STRING);
		createEAttribute(generatorEClass, GENERATOR__GENERATES_VERSION_TYPE);

		generatorsTypeEClass = createEClass(GENERATORS_TYPE);
		createEReference(generatorsTypeEClass, GENERATORS_TYPE__GENERATOR);

		groupEClass = createEClass(GROUP);
		createEAttribute(groupEClass, GROUP__GROUP);
		createEReference(groupEClass, GROUP__DEFINITIONS);
		createEReference(groupEClass, GROUP__ATTRIBUTE);
		createEAttribute(groupEClass, GROUP__FILTER);
		createEAttribute(groupEClass, GROUP__REBASE);

		groupsTypeEClass = createEClass(GROUPS_TYPE);
		createEAttribute(groupsTypeEClass, GROUPS_TYPE__GROUP);
		createEReference(groupsTypeEClass, GROUPS_TYPE__PUBLIC);
		createEReference(groupsTypeEClass, GROUPS_TYPE__PRIVATE);

		importEClass = createEClass(IMPORT);
		createEAttribute(importEClass, IMPORT__ATTRIBUTE);
		createEAttribute(importEClass, IMPORT__FILTER);
		createEAttribute(importEClass, IMPORT__VERSION_DESIGNATOR);

		parameterTypeEClass = createEClass(PARAMETER_TYPE);
		createEAttribute(parameterTypeEClass, PARAMETER_TYPE__MANDATORY);
		createEAttribute(parameterTypeEClass, PARAMETER_TYPE__NAME);
		createEAttribute(parameterTypeEClass, PARAMETER_TYPE__VALUE);

		pathEClass = createEClass(PATH);
		createEAttribute(pathEClass, PATH__PATH);

		prerequisiteEClass = createEClass(PREREQUISITE);
		createEAttribute(prerequisiteEClass, PREREQUISITE__ALIAS);
		createEAttribute(prerequisiteEClass, PREREQUISITE__COMPONENT);
		createEAttribute(prerequisiteEClass, PREREQUISITE__COMPONENT_TYPE);
		createEAttribute(prerequisiteEClass, PREREQUISITE__CONTRIBUTOR);
		createEAttribute(prerequisiteEClass, PREREQUISITE__EXCLUDE_PATTERN);
		createEAttribute(prerequisiteEClass, PREREQUISITE__FILTER);
		createEAttribute(prerequisiteEClass, PREREQUISITE__INCLUDE_PATTERN);
		createEAttribute(prerequisiteEClass, PREREQUISITE__NAME);
		createEAttribute(prerequisiteEClass, PREREQUISITE__OPTIONAL);

		prerequisitesEClass = createEClass(PREREQUISITES);
		createEReference(prerequisitesEClass, PREREQUISITES__DOCUMENTATION);
		createEAttribute(prerequisitesEClass, PREREQUISITES__GROUP);
		createEReference(prerequisitesEClass, PREREQUISITES__ATTRIBUTE);
		createEAttribute(prerequisitesEClass, PREREQUISITES__ALIAS);
		createEAttribute(prerequisitesEClass, PREREQUISITES__REBASE);

		productsTypeEClass = createEClass(PRODUCTS_TYPE);
		createEAttribute(productsTypeEClass, PRODUCTS_TYPE__GROUP);
		createEReference(productsTypeEClass, PRODUCTS_TYPE__PATH);
		createEReference(productsTypeEClass, PRODUCTS_TYPE__PUBLIC);
		createEReference(productsTypeEClass, PRODUCTS_TYPE__PRIVATE);
		createEAttribute(productsTypeEClass, PRODUCTS_TYPE__ALIAS);
		createEAttribute(productsTypeEClass, PRODUCTS_TYPE__BASE);
		createEAttribute(productsTypeEClass, PRODUCTS_TYPE__FILE_COUNT);
		createEAttribute(productsTypeEClass, PRODUCTS_TYPE__PATTERN);
		createEAttribute(productsTypeEClass, PRODUCTS_TYPE__REPLACEMENT);
		createEAttribute(productsTypeEClass, PRODUCTS_TYPE__UP_TO_DATE_POLICY);

		propertiesEClass = createEClass(PROPERTIES);
		createEReference(propertiesEClass, PROPERTIES__PROPERTY);

		removeEClass = createEClass(REMOVE);
		createEAttribute(removeEClass, REMOVE__NAME);

		removePathEClass = createEClass(REMOVE_PATH);
		createEAttribute(removePathEClass, REMOVE_PATH__PATH);

		removePropertyEClass = createEClass(REMOVE_PROPERTY);
		createEAttribute(removePropertyEClass, REMOVE_PROPERTY__KEY);

		renameEClass = createEClass(RENAME);
		createEAttribute(renameEClass, RENAME__NEW_NAME);
		createEAttribute(renameEClass, RENAME__OLD_NAME);

		// Create enums
		upToDatePolicyEEnum = createEEnum(UP_TO_DATE_POLICY);

		// Create data types
		upToDatePolicyObjectEDataType = createEDataType(UP_TO_DATE_POLICY_OBJECT);
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
		return (EAttribute)actionEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_ActorProperties()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_Always()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_AssignConsoleSupport()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_Definitions()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_Enabled()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_Filter()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAction_Group()
	{
		return (EAttribute)actionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_Prerequisites()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_Products()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAction_Properties()
	{
		return (EReference)actionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getActionArtifact()
	{
		return actionArtifactEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActionArtifact_Alias()
	{
		return (EAttribute)actionArtifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getActionsType()
	{
		return actionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getActionsType_Group()
	{
		return (EAttribute)actionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getActionsType_Private()
	{
		return (EReference)actionsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getActionsType_Public()
	{
		return (EReference)actionsTypeEClass.getEStructuralFeatures().get(1);
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
	public EReference getAlterAction_AlterActorProperties()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_AlterPrerequisites()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_AlterProducts()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterAction_AlterProperties()
	{
		return (EReference)alterActionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterAction_Group1()
	{
		return (EAttribute)alterActionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterActionsType()
	{
		return alterActionsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterActionsType_Group()
	{
		return (EAttribute)alterActionsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterActionsType_Private()
	{
		return (EReference)alterActionsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterActionsType_Public()
	{
		return (EReference)alterActionsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterActionsType_Remove()
	{
		return (EReference)alterActionsTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterActionsType_Rename()
	{
		return (EReference)alterActionsTypeEClass.getEStructuralFeatures().get(4);
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
	public EAttribute getAlterArtifact_Group1()
	{
		return (EAttribute)alterArtifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterArtifact_RemovePath()
	{
		return (EReference)alterArtifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterArtifactsType()
	{
		return alterArtifactsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterArtifactsType_Group()
	{
		return (EAttribute)alterArtifactsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterArtifactsType_Private()
	{
		return (EReference)alterArtifactsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterArtifactsType_Public()
	{
		return (EReference)alterArtifactsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterArtifactsType_Remove()
	{
		return (EReference)alterArtifactsTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterArtifactsType_Rename()
	{
		return (EReference)alterArtifactsTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterDependenciesType()
	{
		return alterDependenciesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterDependenciesType_Dependency()
	{
		return (EReference)alterDependenciesTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterDependenciesType_Group()
	{
		return (EAttribute)alterDependenciesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterDependenciesType_Remove()
	{
		return (EReference)alterDependenciesTypeEClass.getEStructuralFeatures().get(2);
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
	public EReference getAlterGroup_AlterAttribute()
	{
		return (EReference)alterGroupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterGroup_Group1()
	{
		return (EAttribute)alterGroupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterGroup_Remove()
	{
		return (EReference)alterGroupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterGroupsType()
	{
		return alterGroupsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterGroupsType_Group()
	{
		return (EAttribute)alterGroupsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterGroupsType_Private()
	{
		return (EReference)alterGroupsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterGroupsType_Public()
	{
		return (EReference)alterGroupsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterGroupsType_Remove()
	{
		return (EReference)alterGroupsTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterGroupsType_Rename()
	{
		return (EReference)alterGroupsTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterPrerequisites()
	{
		return alterPrerequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterPrerequisites_AlterAttribute()
	{
		return (EReference)alterPrerequisitesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterPrerequisites_Group1()
	{
		return (EAttribute)alterPrerequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterPrerequisites_Remove()
	{
		return (EReference)alterPrerequisitesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterProductsType()
	{
		return alterProductsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterProductsType_Group()
	{
		return (EAttribute)alterProductsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterProductsType_Private()
	{
		return (EReference)alterProductsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterProductsType_Public()
	{
		return (EReference)alterProductsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterProductsType_RemovePath()
	{
		return (EReference)alterProductsTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterProductsType_RemoveProduct()
	{
		return (EReference)alterProductsTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getAlterProperties()
	{
		return alterPropertiesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAlterProperties_Group()
	{
		return (EAttribute)alterPropertiesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterProperties_Property()
	{
		return (EReference)alterPropertiesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAlterProperties_Remove()
	{
		return (EReference)alterPropertiesEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getArtifact_Base()
	{
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getArtifact_Definitions()
	{
		return (EReference)artifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getArtifact_Filter()
	{
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getArtifact_Group()
	{
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getArtifact_Path()
	{
		return (EReference)artifactEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getArtifact_Path1()
	{
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getArtifact_Type()
	{
		return (EAttribute)artifactEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getArtifactsType()
	{
		return artifactsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getArtifactsType_Group()
	{
		return (EAttribute)artifactsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getArtifactsType_Private()
	{
		return (EReference)artifactsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getArtifactsType_Public()
	{
		return (EReference)artifactsTypeEClass.getEStructuralFeatures().get(1);
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
	public EReference getAttribute_Documentation()
	{
		return (EReference)attributeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAttribute_Name()
	{
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getAttribute_Parameter()
	{
		return (EReference)attributeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getAttribute_Version()
	{
		return (EAttribute)attributeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getComponentRequest()
	{
		return componentRequestEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentRequest_ComponentType()
	{
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentRequest_Filter()
	{
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getComponentRequest_Import()
	{
		return (EReference)componentRequestEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentRequest_Name()
	{
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentRequest_VersionDesignator()
	{
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentRequest_VersionType()
	{
		return (EAttribute)componentRequestEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getComponentSpec()
	{
		return componentSpecEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpec_Name()
	{
		return (EAttribute)componentSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getComponentSpecBase()
	{
		return componentSpecBaseEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getComponentSpecBase_Actions()
	{
		return (EReference)componentSpecBaseEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getComponentSpecBase_Artifacts()
	{
		return (EReference)componentSpecBaseEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpecBase_Category()
	{
		return (EAttribute)componentSpecBaseEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpecBase_ComponentType()
	{
		return (EAttribute)componentSpecBaseEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getComponentSpecBase_Dependencies()
	{
		return (EReference)componentSpecBaseEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getComponentSpecBase_Documentation()
	{
		return (EReference)componentSpecBaseEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpecBase_Filter()
	{
		return (EAttribute)componentSpecBaseEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getComponentSpecBase_Generators()
	{
		return (EReference)componentSpecBaseEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpecBase_Group()
	{
		return (EAttribute)componentSpecBaseEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getComponentSpecBase_Groups()
	{
		return (EReference)componentSpecBaseEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpecBase_ProjectInfo()
	{
		return (EAttribute)componentSpecBaseEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpecBase_ShortDesc()
	{
		return (EAttribute)componentSpecBaseEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpecBase_VersionString()
	{
		return (EAttribute)componentSpecBaseEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getComponentSpecBase_VersionType()
	{
		return (EAttribute)componentSpecBaseEClass.getEStructuralFeatures().get(13);
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
	public EReference getCSpecExtension_AlterActions()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_AlterArtifacts()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_AlterDependencies()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCSpecExtension_AlterGroups()
	{
		return (EReference)cSpecExtensionEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getCSpecExtension_Group1()
	{
		return (EAttribute)cSpecExtensionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ICSpecXMLFactory getCSpecXMLFactory()
	{
		return (ICSpecXMLFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDefinitions()
	{
		return definitionsEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDefinitions_Define()
	{
		return (EReference)definitionsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDependenciesType()
	{
		return dependenciesTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDependenciesType_Dependency()
	{
		return (EReference)dependenciesTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDocumentRoot()
	{
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Cspec()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_CspecExtension()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getDocumentRoot_Mixed()
	{
		return (EAttribute)documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_XMLNSPrefixMap()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_XSISchemaLocation()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(2);
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
	public EAttribute getGenerator_Component()
	{
		return (EAttribute)generatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGenerator_ComponentType()
	{
		return (EAttribute)generatorEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGenerator_Generates()
	{
		return (EAttribute)generatorEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGenerator_GeneratesType()
	{
		return (EAttribute)generatorEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGenerator_GeneratesVersionString()
	{
		return (EAttribute)generatorEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGenerator_GeneratesVersionType()
	{
		return (EAttribute)generatorEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getGeneratorsType()
	{
		return generatorsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getGeneratorsType_Generator()
	{
		return (EReference)generatorsTypeEClass.getEStructuralFeatures().get(0);
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
	public EReference getGroup_Attribute()
	{
		return (EReference)groupEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getGroup_Definitions()
	{
		return (EReference)groupEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGroup_Filter()
	{
		return (EAttribute)groupEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGroup_Group()
	{
		return (EAttribute)groupEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGroup_Rebase()
	{
		return (EAttribute)groupEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getGroupsType()
	{
		return groupsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGroupsType_Group()
	{
		return (EAttribute)groupsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getGroupsType_Private()
	{
		return (EReference)groupsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getGroupsType_Public()
	{
		return (EReference)groupsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getImport()
	{
		return importEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getImport_Attribute()
	{
		return (EAttribute)importEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getImport_Filter()
	{
		return (EAttribute)importEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getImport_VersionDesignator()
	{
		return (EAttribute)importEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getParameterType()
	{
		return parameterTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getParameterType_Mandatory()
	{
		return (EAttribute)parameterTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getParameterType_Name()
	{
		return (EAttribute)parameterTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getParameterType_Value()
	{
		return (EAttribute)parameterTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPath()
	{
		return pathEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPath_Path()
	{
		return (EAttribute)pathEClass.getEStructuralFeatures().get(0);
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
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_Component()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_ComponentType()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(2);
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
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(4);
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
	public EAttribute getPrerequisite_Name()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisite_Optional()
	{
		return (EAttribute)prerequisiteEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPrerequisites()
	{
		return prerequisitesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisites_Alias()
	{
		return (EAttribute)prerequisitesEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPrerequisites_Attribute()
	{
		return (EReference)prerequisitesEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPrerequisites_Documentation()
	{
		return (EReference)prerequisitesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisites_Group()
	{
		return (EAttribute)prerequisitesEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPrerequisites_Rebase()
	{
		return (EAttribute)prerequisitesEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProductsType()
	{
		return productsTypeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProductsType_Alias()
	{
		return (EAttribute)productsTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProductsType_Base()
	{
		return (EAttribute)productsTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProductsType_FileCount()
	{
		return (EAttribute)productsTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProductsType_Group()
	{
		return (EAttribute)productsTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProductsType_Path()
	{
		return (EReference)productsTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProductsType_Pattern()
	{
		return (EAttribute)productsTypeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProductsType_Private()
	{
		return (EReference)productsTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProductsType_Public()
	{
		return (EReference)productsTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProductsType_Replacement()
	{
		return (EAttribute)productsTypeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProductsType_UpToDatePolicy()
	{
		return (EAttribute)productsTypeEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProperties()
	{
		return propertiesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProperties_Property()
	{
		return (EReference)propertiesEClass.getEStructuralFeatures().get(0);
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
	public EClass getRemoveProperty()
	{
		return removePropertyEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRemoveProperty_Key()
	{
		return (EAttribute)removePropertyEClass.getEStructuralFeatures().get(0);
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
		return (EAttribute)renameEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRename_OldName()
	{
		return (EAttribute)renameEClass.getEStructuralFeatures().get(1);
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getUpToDatePolicyObject()
	{
		return upToDatePolicyObjectEDataType;
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
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);
		CommonPackage theCommonPackage = (CommonPackage)EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		actionEClass.getESuperTypes().add(this.getAttribute());
		actionArtifactEClass.getESuperTypes().add(this.getArtifact());
		alterActionEClass.getESuperTypes().add(this.getAction());
		alterArtifactEClass.getESuperTypes().add(this.getArtifact());
		alterGroupEClass.getESuperTypes().add(this.getGroup());
		alterPrerequisitesEClass.getESuperTypes().add(this.getPrerequisites());
		artifactEClass.getESuperTypes().add(this.getAttribute());
		componentSpecEClass.getESuperTypes().add(this.getComponentSpecBase());
		cSpecExtensionEClass.getESuperTypes().add(this.getComponentSpecBase());
		groupEClass.getESuperTypes().add(this.getAttribute());

		// Initialize classes and features; add operations and parameters
		initEClass(actionEClass, IAction.class, "Action", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAction_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, IAction.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Definitions(), this.getDefinitions(), null, "definitions", null, 0, -1, IAction.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getAction_ActorProperties(), this.getProperties(), null, "actorProperties", null, 0, -1,
				IAction.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Properties(), this.getProperties(), null, "properties", null, 0, -1, IAction.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Prerequisites(), this.getPrerequisites(), null, "prerequisites", null, 0, -1,
				IAction.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAction_Products(), this.getProductsType(), null, "products", null, 0, -1, IAction.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Actor(), theXMLTypePackage.getString(), "actor", null, 0, 1, IAction.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Always(), theXMLTypePackage.getBoolean(), "always", "false", 0, 1, IAction.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_AssignConsoleSupport(), theXMLTypePackage.getBoolean(), "assignConsoleSupport",
				"true", 0, 1, IAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Enabled(), theXMLTypePackage.getBoolean(), "enabled", "true", 0, 1, IAction.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAction_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1, IAction.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(actionArtifactEClass, IActionArtifact.class, "ActionArtifact", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActionArtifact_Alias(), theXMLTypePackage.getString(), "alias", null, 0, 1,
				IActionArtifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(actionsTypeEClass, IActionsType.class, "ActionsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getActionsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IActionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getActionsType_Public(), this.getAction(), null, "public", null, 0, -1, IActionsType.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getActionsType_Private(), this.getAction(), null, "private", null, 0, -1, IActionsType.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		initEClass(alterActionEClass, IAlterAction.class, "AlterAction", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterAction_Group1(), ecorePackage.getEFeatureMapEntry(), "group1", null, 0, -1,
				IAlterAction.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_AlterPrerequisites(), this.getAlterPrerequisites(), null, "alterPrerequisites",
				null, 0, -1, IAlterAction.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_AlterActorProperties(), this.getAlterProperties(), null, "alterActorProperties",
				null, 0, -1, IAlterAction.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_AlterProperties(), this.getAlterProperties(), null, "alterProperties", null, 0,
				-1, IAlterAction.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterAction_AlterProducts(), this.getAlterProductsType(), null, "alterProducts", null, 0, -1,
				IAlterAction.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(alterActionsTypeEClass, IAlterActionsType.class, "AlterActionsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterActionsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IAlterActionsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterActionsType_Public(), this.getAlterAction(), null, "public", null, 0, -1,
				IAlterActionsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterActionsType_Private(), this.getAlterAction(), null, "private", null, 0, -1,
				IAlterActionsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterActionsType_Remove(), this.getRemove(), null, "remove", null, 0, -1,
				IAlterActionsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterActionsType_Rename(), this.getRename(), null, "rename", null, 0, -1,
				IAlterActionsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(alterArtifactEClass, IAlterArtifact.class, "AlterArtifact", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterArtifact_Group1(), ecorePackage.getEFeatureMapEntry(), "group1", null, 0, -1,
				IAlterArtifact.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getAlterArtifact_RemovePath(), this.getRemovePath(), null, "removePath", null, 0, -1,
				IAlterArtifact.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(alterArtifactsTypeEClass, IAlterArtifactsType.class, "AlterArtifactsType", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterArtifactsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IAlterArtifactsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterArtifactsType_Public(), this.getAlterArtifact(), null, "public", null, 0, -1,
				IAlterArtifactsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterArtifactsType_Private(), this.getAlterArtifact(), null, "private", null, 0, -1,
				IAlterArtifactsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterArtifactsType_Remove(), this.getRemove(), null, "remove", null, 0, -1,
				IAlterArtifactsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterArtifactsType_Rename(), this.getRename(), null, "rename", null, 0, -1,
				IAlterArtifactsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(alterDependenciesTypeEClass, IAlterDependenciesType.class, "AlterDependenciesType", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterDependenciesType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IAlterDependenciesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterDependenciesType_Dependency(), this.getComponentRequest(), null, "dependency", null, 0,
				-1, IAlterDependenciesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterDependenciesType_Remove(), this.getRemove(), null, "remove", null, 0, -1,
				IAlterDependenciesType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(alterGroupEClass, IAlterGroup.class, "AlterGroup", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterGroup_Group1(), ecorePackage.getEFeatureMapEntry(), "group1", null, 0, -1,
				IAlterGroup.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getAlterGroup_AlterAttribute(), this.getPrerequisite(), null, "alterAttribute", null, 0, -1,
				IAlterGroup.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterGroup_Remove(), this.getRemove(), null, "remove", null, 0, -1, IAlterGroup.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		initEClass(alterGroupsTypeEClass, IAlterGroupsType.class, "AlterGroupsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterGroupsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IAlterGroupsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getAlterGroupsType_Public(), this.getAlterGroup(), null, "public", null, 0, -1,
				IAlterGroupsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterGroupsType_Private(), this.getAlterGroup(), null, "private", null, 0, -1,
				IAlterGroupsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterGroupsType_Remove(), this.getRemove(), null, "remove", null, 0, -1,
				IAlterGroupsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterGroupsType_Rename(), this.getRename(), null, "rename", null, 0, -1,
				IAlterGroupsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(alterPrerequisitesEClass, IAlterPrerequisites.class, "AlterPrerequisites", !IS_ABSTRACT,
				!IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterPrerequisites_Group1(), ecorePackage.getEFeatureMapEntry(), "group1", null, 0, -1,
				IAlterPrerequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterPrerequisites_AlterAttribute(), this.getPrerequisite(), null, "alterAttribute", null, 0,
				-1, IAlterPrerequisites.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterPrerequisites_Remove(), this.getRemove(), null, "remove", null, 0, -1,
				IAlterPrerequisites.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(alterProductsTypeEClass, IAlterProductsType.class, "AlterProductsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterProductsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IAlterProductsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAlterProductsType_Public(), this.getAlterGroup(), null, "public", null, 0, -1,
				IAlterProductsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterProductsType_Private(), this.getAlterGroup(), null, "private", null, 0, -1,
				IAlterProductsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterProductsType_RemoveProduct(), this.getRemove(), null, "removeProduct", null, 0, -1,
				IAlterProductsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterProductsType_RemovePath(), this.getRemovePath(), null, "removePath", null, 0, -1,
				IAlterProductsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(alterPropertiesEClass, IAlterProperties.class, "AlterProperties", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAlterProperties_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IAlterProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getAlterProperties_Property(), theCommonPackage.getPropertyConstant(), null, "property", null,
				0, -1, IAlterProperties.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getAlterProperties_Remove(), this.getRemoveProperty(), null, "remove", null, 0, -1,
				IAlterProperties.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(artifactEClass, IArtifact.class, "Artifact", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifact_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, IArtifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Definitions(), this.getDefinitions(), null, "definitions", null, 0, -1,
				IArtifact.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifact_Path(), this.getPath(), null, "path", null, 0, -1, IArtifact.class, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getArtifact_Base(), theXMLTypePackage.getString(), "base", null, 0, 1, IArtifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1, IArtifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Path1(), theXMLTypePackage.getString(), "path1", null, 0, 1, IArtifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getArtifact_Type(), theXMLTypePackage.getString(), "type", null, 0, 1, IArtifact.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(artifactsTypeEClass, IArtifactsType.class, "ArtifactsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getArtifactsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IArtifactsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getArtifactsType_Public(), this.getArtifact(), null, "public", null, 0, -1,
				IArtifactsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getArtifactsType_Private(), this.getArtifact(), null, "private", null, 0, -1,
				IArtifactsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(attributeEClass, IAttribute.class, "Attribute", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getAttribute_Documentation(), theCommonPackage.getDocumentation(), null, "documentation", null,
				0, 1, IAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getAttribute_Parameter(), this.getParameterType(), null, "parameter", null, 0, -1,
				IAttribute.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, IAttribute.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getAttribute_Version(), theXMLTypePackage.getString(), "version", null, 0, 1, IAttribute.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(componentRequestEClass, IComponentRequest.class, "ComponentRequest", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentRequest_Import(), this.getImport(), null, "import", null, 0, -1,
				IComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRequest_ComponentType(), theXMLTypePackage.getString(), "componentType", null, 0, 1,
				IComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRequest_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1,
				IComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRequest_Name(), theXMLTypePackage.getString(), "name", null, 0, 1,
				IComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRequest_VersionDesignator(), theXMLTypePackage.getString(), "versionDesignator",
				null, 0, 1, IComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentRequest_VersionType(), theXMLTypePackage.getString(), "versionType", null, 0, 1,
				IComponentRequest.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		addEOperation(componentRequestEClass, theCommonPackage.getVersionRange(), "getRange", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		EOperation op = addEOperation(componentRequestEClass, null, "setRange", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theCommonPackage.getVersionRange(), "range", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(componentSpecEClass, IComponentSpec.class, "ComponentSpec", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getComponentSpec_Name(), theXMLTypePackage.getString(), "name", null, 1, 1,
				IComponentSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(componentSpecBaseEClass, IComponentSpecBase.class, "ComponentSpecBase", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getComponentSpecBase_Documentation(), theCommonPackage.getDocumentation(), null,
				"documentation", null, 0, 1, IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecBase_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				!IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getComponentSpecBase_Dependencies(), this.getDependenciesType(), null, "dependencies", null, 0,
				-1, IComponentSpecBase.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getComponentSpecBase_Generators(), this.getGeneratorsType(), null, "generators", null, 0, -1,
				IComponentSpecBase.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getComponentSpecBase_Artifacts(), this.getArtifactsType(), null, "artifacts", null, 0, -1,
				IComponentSpecBase.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getComponentSpecBase_Groups(), this.getGroupsType(), null, "groups", null, 0, -1,
				IComponentSpecBase.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getComponentSpecBase_Actions(), this.getActionsType(), null, "actions", null, 0, -1,
				IComponentSpecBase.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecBase_Category(), theXMLTypePackage.getString(), "category", null, 0, 1,
				IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecBase_ComponentType(), theXMLTypePackage.getString(), "componentType", null, 0,
				1, IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecBase_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1,
				IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecBase_ProjectInfo(), theCommonPackage.getURL(), "projectInfo", null, 0, 1,
				IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecBase_ShortDesc(), theXMLTypePackage.getString(), "shortDesc", null, 0, 1,
				IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecBase_VersionString(), theXMLTypePackage.getString(), "versionString", null, 0,
				1, IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getComponentSpecBase_VersionType(), theXMLTypePackage.getString(), "versionType", null, 0, 1,
				IComponentSpecBase.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(componentSpecBaseEClass, theCommonPackage.getVersion(), "getVersion", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(componentSpecBaseEClass, null, "setVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theCommonPackage.getVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(cSpecExtensionEClass, ICSpecExtension.class, "CSpecExtension", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCSpecExtension_Group1(), ecorePackage.getEFeatureMapEntry(), "group1", null, 0, -1,
				ICSpecExtension.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_AlterActions(), this.getAlterActionsType(), null, "alterActions", null, 0, -1,
				ICSpecExtension.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_AlterArtifacts(), this.getAlterArtifactsType(), null, "alterArtifacts", null,
				0, -1, ICSpecExtension.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_AlterDependencies(), this.getAlterDependenciesType(), null,
				"alterDependencies", null, 0, -1, ICSpecExtension.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getCSpecExtension_AlterGroups(), this.getAlterGroupsType(), null, "alterGroups", null, 0, -1,
				ICSpecExtension.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(definitionsEClass, IDefinitions.class, "Definitions", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDefinitions_Define(), this.getAttribute(), null, "define", null, 1, -1, IDefinitions.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(dependenciesTypeEClass, IDependenciesType.class, "DependenciesType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDependenciesType_Dependency(), this.getComponentRequest(), null, "dependency", null, 0, -1,
				IDependenciesType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, IDocumentRoot.class, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null,
				"xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null,
				"xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Cspec(), this.getComponentSpec(), null, "cspec", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_CspecExtension(), this.getCSpecExtension(), null, "cspecExtension", null, 0, -2,
				null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(generatorEClass, IGenerator.class, "Generator", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGenerator_Attribute(), theXMLTypePackage.getString(), "attribute", null, 1, 1,
				IGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenerator_Component(), theXMLTypePackage.getString(), "component", null, 0, 1,
				IGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenerator_ComponentType(), theXMLTypePackage.getString(), "componentType", null, 0, 1,
				IGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenerator_Generates(), theXMLTypePackage.getString(), "generates", null, 1, 1,
				IGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenerator_GeneratesType(), theXMLTypePackage.getString(), "generatesType", null, 0, 1,
				IGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenerator_GeneratesVersionString(), theXMLTypePackage.getString(), "generatesVersionString",
				null, 0, 1, IGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGenerator_GeneratesVersionType(), theXMLTypePackage.getString(), "generatesVersionType",
				null, 0, 1, IGenerator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(generatorEClass, theCommonPackage.getVersion(), "getGeneratesVersion", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		initEClass(generatorsTypeEClass, IGeneratorsType.class, "GeneratorsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGeneratorsType_Generator(), this.getGenerator(), null, "generator", null, 0, -1,
				IGeneratorsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupEClass, IGroup.class, "Group", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGroup_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1, IGroup.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGroup_Definitions(), this.getDefinitions(), null, "definitions", null, 0, -1, IGroup.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getGroup_Attribute(), this.getPrerequisite(), null, "attribute", null, 0, -1, IGroup.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroup_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1, IGroup.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroup_Rebase(), theXMLTypePackage.getString(), "rebase", null, 0, 1, IGroup.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(groupsTypeEClass, IGroupsType.class, "GroupsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGroupsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IGroupsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getGroupsType_Public(), this.getGroup(), null, "public", null, 0, -1, IGroupsType.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getGroupsType_Private(), this.getGroup(), null, "private", null, 0, -1, IGroupsType.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);

		initEClass(importEClass, IImport.class, "Import", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getImport_Attribute(), theXMLTypePackage.getString(), "attribute", null, 1, 1, IImport.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImport_Filter(), theXMLTypePackage.getString(), "filter", null, 0, 1, IImport.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getImport_VersionDesignator(), theXMLTypePackage.getString(), "versionDesignator", null, 0, 1,
				IImport.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(parameterTypeEClass, IParameterType.class, "ParameterType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getParameterType_Mandatory(), theXMLTypePackage.getBoolean(), "mandatory", "false", 0, 1,
				IParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterType_Name(), theXMLTypePackage.getString(), "name", null, 1, 1,
				IParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getParameterType_Value(), theXMLTypePackage.getString(), "value", null, 1, 1,
				IParameterType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(pathEClass, IPath.class, "Path", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPath_Path(), theXMLTypePackage.getString(), "path", null, 1, 1, IPath.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(prerequisiteEClass, IPrerequisite.class, "Prerequisite", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPrerequisite_Alias(), theXMLTypePackage.getString(), "alias", null, 0, 1,
				IPrerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Component(), theXMLTypePackage.getString(), "component", null, 0, 1,
				IPrerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_ComponentType(), theXMLTypePackage.getString(), "componentType", null, 0, 1,
				IPrerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Contributor(), theXMLTypePackage.getBoolean(), "contributor", null, 0, 1,
				IPrerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_ExcludePattern(), theCommonPackage.getPattern(), "excludePattern", null, 0, 1,
				IPrerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1,
				IPrerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_IncludePattern(), theCommonPackage.getPattern(), "includePattern", null, 0, 1,
				IPrerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Name(), theXMLTypePackage.getString(), "name", null, 0, 1, IPrerequisite.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisite_Optional(), theXMLTypePackage.getBoolean(), "optional", null, 0, 1,
				IPrerequisite.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(prerequisitesEClass, IPrerequisites.class, "Prerequisites", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPrerequisites_Documentation(), theCommonPackage.getDocumentation(), null, "documentation",
				null, 0, 1, IPrerequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisites_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IPrerequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getPrerequisites_Attribute(), this.getPrerequisite(), null, "attribute", null, 0, -1,
				IPrerequisites.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisites_Alias(), theXMLTypePackage.getString(), "alias", null, 0, 1,
				IPrerequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getPrerequisites_Rebase(), theXMLTypePackage.getString(), "rebase", null, 0, 1,
				IPrerequisites.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(productsTypeEClass, IProductsType.class, "ProductsType", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProductsType_Group(), ecorePackage.getEFeatureMapEntry(), "group", null, 0, -1,
				IProductsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getProductsType_Path(), this.getPath(), null, "path", null, 0, -1, IProductsType.class,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getProductsType_Public(), this.getActionArtifact(), null, "public", null, 0, -1,
				IProductsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProductsType_Private(), this.getActionArtifact(), null, "private", null, 0, -1,
				IProductsType.class, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductsType_Alias(), theXMLTypePackage.getString(), "alias", null, 0, 1,
				IProductsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductsType_Base(), theXMLTypePackage.getString(), "base", null, 0, 1, IProductsType.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductsType_FileCount(), theXMLTypePackage.getInt(), "fileCount", null, 0, 1,
				IProductsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductsType_Pattern(), theCommonPackage.getPattern(), "pattern", null, 0, 1,
				IProductsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductsType_Replacement(), theXMLTypePackage.getString(), "replacement", null, 0, 1,
				IProductsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProductsType_UpToDatePolicy(), this.getUpToDatePolicy(), "upToDatePolicy", null, 0, 1,
				IProductsType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(propertiesEClass, IProperties.class, "Properties", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProperties_Property(), theCommonPackage.getPropertyConstant(), null, "property", null, 0, -1,
				IProperties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removeEClass, IRemove.class, "Remove", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemove_Name(), theXMLTypePackage.getString(), "name", null, 1, 1, IRemove.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removePathEClass, IRemovePath.class, "RemovePath", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemovePath_Path(), theXMLTypePackage.getString(), "path", null, 1, 1, IRemovePath.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(removePropertyEClass, IRemoveProperty.class, "RemoveProperty", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRemoveProperty_Key(), theXMLTypePackage.getString(), "key", null, 1, 1,
				IRemoveProperty.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(renameEClass, IRename.class, "Rename", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRename_NewName(), theXMLTypePackage.getString(), "newName", null, 1, 1, IRename.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRename_OldName(), theXMLTypePackage.getString(), "oldName", null, 1, 1, IRename.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(upToDatePolicyEEnum, UpToDatePolicy.class, "UpToDatePolicy");
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.DEFAULT);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.ACTOR);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.COUNT);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.MAPPER);
		addEEnumLiteral(upToDatePolicyEEnum, UpToDatePolicy.NOTEMPTY);

		// Initialize data types
		initEDataType(upToDatePolicyObjectEDataType, UpToDatePolicy.class, "UpToDatePolicyObject", IS_SERIALIZABLE,
				IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations()
	{
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(actionEClass, source, new String[] { "name", "Action", "kind", "elementOnly" });
		addAnnotation(getAction_Group(), source, new String[] { "kind", "group", "name", "group:4" });
		addAnnotation(getAction_Definitions(), source, new String[] { "kind", "element", "name", "definitions",
				"namespace", "##targetNamespace", "group", "#group:4" });
		addAnnotation(getAction_ActorProperties(), source, new String[] { "kind", "element", "name", "actorProperties",
				"namespace", "##targetNamespace", "group", "#group:4" });
		addAnnotation(getAction_Properties(), source, new String[] { "kind", "element", "name", "properties",
				"namespace", "##targetNamespace", "group", "#group:4" });
		addAnnotation(getAction_Prerequisites(), source, new String[] { "kind", "element", "name", "prerequisites",
				"namespace", "##targetNamespace", "group", "#group:4" });
		addAnnotation(getAction_Products(), source, new String[] { "kind", "element", "name", "products", "namespace",
				"##targetNamespace", "group", "#group:4" });
		addAnnotation(getAction_Actor(), source, new String[] { "kind", "attribute", "name", "actor" });
		addAnnotation(getAction_Always(), source, new String[] { "kind", "attribute", "name", "always" });
		addAnnotation(getAction_AssignConsoleSupport(), source, new String[] { "kind", "attribute", "name",
				"assignConsoleSupport" });
		addAnnotation(getAction_Enabled(), source, new String[] { "kind", "attribute", "name", "enabled" });
		addAnnotation(getAction_Filter(), source, new String[] { "kind", "attribute", "name", "filter" });
		addAnnotation(actionArtifactEClass, source, new String[] { "name", "ActionArtifact", "kind", "elementOnly" });
		addAnnotation(getActionArtifact_Alias(), source, new String[] { "kind", "attribute", "name", "alias" });
		addAnnotation(actionsTypeEClass, source, new String[] { "name", "actions_._type", "kind", "elementOnly" });
		addAnnotation(getActionsType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getActionsType_Public(), source, new String[] { "kind", "element", "name", "public", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getActionsType_Private(), source, new String[] { "kind", "element", "name", "private",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(alterActionEClass, source, new String[] { "name", "AlterAction", "kind", "elementOnly" });
		addAnnotation(getAlterAction_Group1(), source, new String[] { "kind", "group", "name", "group:16" });
		addAnnotation(getAlterAction_AlterPrerequisites(), source, new String[] { "kind", "element", "name",
				"alterPrerequisites", "namespace", "##targetNamespace", "group", "#group:16" });
		addAnnotation(getAlterAction_AlterActorProperties(), source, new String[] { "kind", "element", "name",
				"alterActorProperties", "namespace", "##targetNamespace", "group", "#group:16" });
		addAnnotation(getAlterAction_AlterProperties(), source, new String[] { "kind", "element", "name",
				"alterProperties", "namespace", "##targetNamespace", "group", "#group:16" });
		addAnnotation(getAlterAction_AlterProducts(), source, new String[] { "kind", "element", "name",
				"alterProducts", "namespace", "##targetNamespace", "group", "#group:16" });
		addAnnotation(alterActionsTypeEClass, source, new String[] { "name", "alterActions_._type", "kind",
				"elementOnly" });
		addAnnotation(getAlterActionsType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getAlterActionsType_Public(), source, new String[] { "kind", "element", "name", "public",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterActionsType_Private(), source, new String[] { "kind", "element", "name", "private",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterActionsType_Remove(), source, new String[] { "kind", "element", "name", "remove",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterActionsType_Rename(), source, new String[] { "kind", "element", "name", "rename",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(alterArtifactEClass, source, new String[] { "name", "AlterArtifact", "kind", "elementOnly" });
		addAnnotation(getAlterArtifact_Group1(), source, new String[] { "kind", "group", "name", "group:12" });
		addAnnotation(getAlterArtifact_RemovePath(), source, new String[] { "kind", "element", "name", "removePath",
				"namespace", "##targetNamespace", "group", "#group:12" });
		addAnnotation(alterArtifactsTypeEClass, source, new String[] { "name", "alterArtifacts_._type", "kind",
				"elementOnly" });
		addAnnotation(getAlterArtifactsType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getAlterArtifactsType_Public(), source, new String[] { "kind", "element", "name", "public",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterArtifactsType_Private(), source, new String[] { "kind", "element", "name", "private",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterArtifactsType_Remove(), source, new String[] { "kind", "element", "name", "remove",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterArtifactsType_Rename(), source, new String[] { "kind", "element", "name", "rename",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(alterDependenciesTypeEClass, source, new String[] { "name", "alterDependencies_._type", "kind",
				"elementOnly" });
		addAnnotation(getAlterDependenciesType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getAlterDependenciesType_Dependency(), source, new String[] { "kind", "element", "name",
				"dependency", "namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterDependenciesType_Remove(), source, new String[] { "kind", "element", "name", "remove",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(alterGroupEClass, source, new String[] { "name", "AlterGroup", "kind", "elementOnly" });
		addAnnotation(getAlterGroup_Group1(), source, new String[] { "kind", "group", "name", "group:10" });
		addAnnotation(getAlterGroup_AlterAttribute(), source, new String[] { "kind", "element", "name",
				"alterAttribute", "namespace", "##targetNamespace", "group", "#group:10" });
		addAnnotation(getAlterGroup_Remove(), source, new String[] { "kind", "element", "name", "remove", "namespace",
				"##targetNamespace", "group", "#group:10" });
		addAnnotation(alterGroupsTypeEClass, source,
				new String[] { "name", "alterGroups_._type", "kind", "elementOnly" });
		addAnnotation(getAlterGroupsType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getAlterGroupsType_Public(), source, new String[] { "kind", "element", "name", "public",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterGroupsType_Private(), source, new String[] { "kind", "element", "name", "private",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterGroupsType_Remove(), source, new String[] { "kind", "element", "name", "remove",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterGroupsType_Rename(), source, new String[] { "kind", "element", "name", "rename",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(alterPrerequisitesEClass, source, new String[] { "name", "AlterPrerequisites", "kind",
				"elementOnly" });
		addAnnotation(getAlterPrerequisites_Group1(), source, new String[] { "kind", "group", "name", "group:6" });
		addAnnotation(getAlterPrerequisites_AlterAttribute(), source, new String[] { "kind", "element", "name",
				"alterAttribute", "namespace", "##targetNamespace", "group", "#group:6" });
		addAnnotation(getAlterPrerequisites_Remove(), source, new String[] { "kind", "element", "name", "remove",
				"namespace", "##targetNamespace", "group", "#group:6" });
		addAnnotation(alterProductsTypeEClass, source, new String[] { "name", "alterProducts_._type", "kind",
				"elementOnly" });
		addAnnotation(getAlterProductsType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getAlterProductsType_Public(), source, new String[] { "kind", "element", "name", "public",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterProductsType_Private(), source, new String[] { "kind", "element", "name", "private",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterProductsType_RemoveProduct(), source, new String[] { "kind", "element", "name",
				"removeProduct", "namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterProductsType_RemovePath(), source, new String[] { "kind", "element", "name",
				"removePath", "namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(alterPropertiesEClass, source, new String[] { "name", "AlterProperties", "kind", "elementOnly" });
		addAnnotation(getAlterProperties_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getAlterProperties_Property(), source, new String[] { "kind", "element", "name", "property",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getAlterProperties_Remove(), source, new String[] { "kind", "element", "name", "remove",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(artifactEClass, source, new String[] { "name", "Artifact", "kind", "elementOnly" });
		addAnnotation(getArtifact_Group(), source, new String[] { "kind", "group", "name", "group:4" });
		addAnnotation(getArtifact_Definitions(), source, new String[] { "kind", "element", "name", "definitions",
				"namespace", "##targetNamespace", "group", "#group:4" });
		addAnnotation(getArtifact_Path(), source, new String[] { "kind", "element", "name", "path", "namespace",
				"##targetNamespace", "group", "#group:4" });
		addAnnotation(getArtifact_Base(), source, new String[] { "kind", "attribute", "name", "base" });
		addAnnotation(getArtifact_Filter(), source, new String[] { "kind", "attribute", "name", "filter" });
		addAnnotation(getArtifact_Path1(), source, new String[] { "kind", "attribute", "name", "path" });
		addAnnotation(getArtifact_Type(), source, new String[] { "kind", "attribute", "name", "type" });
		addAnnotation(artifactsTypeEClass, source, new String[] { "name", "artifacts_._type", "kind", "elementOnly" });
		addAnnotation(getArtifactsType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getArtifactsType_Public(), source, new String[] { "kind", "element", "name", "public",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getArtifactsType_Private(), source, new String[] { "kind", "element", "name", "private",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(attributeEClass, source, new String[] { "name", "Attribute", "kind", "elementOnly" });
		addAnnotation(getAttribute_Documentation(), source, new String[] { "kind", "element", "name", "documentation",
				"namespace", "##targetNamespace" });
		addAnnotation(getAttribute_Parameter(), source, new String[] { "kind", "element", "name", "parameter",
				"namespace", "##targetNamespace" });
		addAnnotation(getAttribute_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(getAttribute_Version(), source, new String[] { "kind", "attribute", "name", "version" });
		addAnnotation(componentRequestEClass, source,
				new String[] { "name", "ComponentRequest", "kind", "elementOnly" });
		addAnnotation(getComponentRequest_Import(), source, new String[] { "kind", "element", "name", "import",
				"namespace", "##targetNamespace" });
		addAnnotation(getComponentRequest_ComponentType(), source, new String[] { "kind", "attribute", "name",
				"componentType" });
		addAnnotation(getComponentRequest_Filter(), source, new String[] { "kind", "attribute", "name", "filter" });
		addAnnotation(getComponentRequest_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(getComponentRequest_VersionDesignator(), source, new String[] { "kind", "attribute", "name",
				"versionDesignator" });
		addAnnotation(getComponentRequest_VersionType(), source, new String[] { "kind", "attribute", "name",
				"versionType" });
		addAnnotation(componentSpecEClass, source, new String[] { "name", "ComponentSpec", "kind", "elementOnly" });
		addAnnotation(getComponentSpec_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(componentSpecBaseEClass, source, new String[] { "name", "ComponentSpecBase", "kind",
				"elementOnly" });
		addAnnotation(getComponentSpecBase_Documentation(), source, new String[] { "kind", "element", "name",
				"documentation", "namespace", "##targetNamespace" });
		addAnnotation(getComponentSpecBase_Group(), source, new String[] { "kind", "group", "name", "group:1" });
		addAnnotation(getComponentSpecBase_Dependencies(), source, new String[] { "kind", "element", "name",
				"dependencies", "namespace", "##targetNamespace", "group", "#group:1" });
		addAnnotation(getComponentSpecBase_Generators(), source, new String[] { "kind", "element", "name",
				"generators", "namespace", "##targetNamespace", "group", "#group:1" });
		addAnnotation(getComponentSpecBase_Artifacts(), source, new String[] { "kind", "element", "name", "artifacts",
				"namespace", "##targetNamespace", "group", "#group:1" });
		addAnnotation(getComponentSpecBase_Groups(), source, new String[] { "kind", "element", "name", "groups",
				"namespace", "##targetNamespace", "group", "#group:1" });
		addAnnotation(getComponentSpecBase_Actions(), source, new String[] { "kind", "element", "name", "actions",
				"namespace", "##targetNamespace", "group", "#group:1" });
		addAnnotation(getComponentSpecBase_Category(), source, new String[] { "kind", "attribute", "name", "category" });
		addAnnotation(getComponentSpecBase_ComponentType(), source, new String[] { "kind", "attribute", "name",
				"componentType" });
		addAnnotation(getComponentSpecBase_Filter(), source, new String[] { "kind", "attribute", "name", "filter" });
		addAnnotation(getComponentSpecBase_ProjectInfo(), source, new String[] { "kind", "attribute", "name",
				"projectInfo" });
		addAnnotation(getComponentSpecBase_ShortDesc(), source,
				new String[] { "kind", "attribute", "name", "shortDesc" });
		addAnnotation(getComponentSpecBase_VersionString(), source, new String[] { "kind", "attribute", "name",
				"version" });
		addAnnotation(getComponentSpecBase_VersionType(), source, new String[] { "kind", "attribute", "name",
				"versionType" });
		addAnnotation(cSpecExtensionEClass, source, new String[] { "name", "CSpecExtension", "kind", "elementOnly" });
		addAnnotation(getCSpecExtension_Group1(), source, new String[] { "kind", "group", "name", "group:14" });
		addAnnotation(getCSpecExtension_AlterActions(), source, new String[] { "kind", "element", "name",
				"alterActions", "namespace", "##targetNamespace", "group", "#group:14" });
		addAnnotation(getCSpecExtension_AlterArtifacts(), source, new String[] { "kind", "element", "name",
				"alterArtifacts", "namespace", "##targetNamespace", "group", "#group:14" });
		addAnnotation(getCSpecExtension_AlterDependencies(), source, new String[] { "kind", "element", "name",
				"alterDependencies", "namespace", "##targetNamespace", "group", "#group:14" });
		addAnnotation(getCSpecExtension_AlterGroups(), source, new String[] { "kind", "element", "name", "alterGroups",
				"namespace", "##targetNamespace", "group", "#group:14" });
		addAnnotation(definitionsEClass, source, new String[] { "name", "Definitions", "kind", "elementOnly" });
		addAnnotation(getDefinitions_Define(), source, new String[] { "kind", "element", "name", "define", "namespace",
				"##targetNamespace" });
		addAnnotation(dependenciesTypeEClass, source, new String[] { "name", "dependencies_._type", "kind",
				"elementOnly" });
		addAnnotation(getDependenciesType_Dependency(), source, new String[] { "kind", "element", "name", "dependency",
				"namespace", "##targetNamespace" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name",
				"xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name",
				"xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Cspec(), source, new String[] { "kind", "element", "name", "cspec", "namespace",
				"##targetNamespace" });
		addAnnotation(getDocumentRoot_CspecExtension(), source, new String[] { "kind", "element", "name",
				"cspecExtension", "namespace", "##targetNamespace" });
		addAnnotation(generatorEClass, source, new String[] { "name", "Generator", "kind", "empty" });
		addAnnotation(getGenerator_Attribute(), source, new String[] { "kind", "attribute", "name", "attribute" });
		addAnnotation(getGenerator_Component(), source, new String[] { "kind", "attribute", "name", "component" });
		addAnnotation(getGenerator_ComponentType(), source,
				new String[] { "kind", "attribute", "name", "componentType" });
		addAnnotation(getGenerator_Generates(), source, new String[] { "kind", "attribute", "name", "generates" });
		addAnnotation(getGenerator_GeneratesType(), source,
				new String[] { "kind", "attribute", "name", "generatesType" });
		addAnnotation(getGenerator_GeneratesVersionString(), source, new String[] { "kind", "attribute", "name",
				"generatesVersion" });
		addAnnotation(getGenerator_GeneratesVersionType(), source, new String[] { "kind", "attribute", "name",
				"generatesVersionType" });
		addAnnotation(generatorsTypeEClass, source, new String[] { "name", "generators_._type", "kind", "elementOnly" });
		addAnnotation(getGeneratorsType_Generator(), source, new String[] { "kind", "element", "name", "generator",
				"namespace", "##targetNamespace" });
		addAnnotation(groupEClass, source, new String[] { "name", "Group", "kind", "elementOnly" });
		addAnnotation(getGroup_Group(), source, new String[] { "kind", "group", "name", "group:4" });
		addAnnotation(getGroup_Definitions(), source, new String[] { "kind", "element", "name", "definitions",
				"namespace", "##targetNamespace", "group", "#group:4" });
		addAnnotation(getGroup_Attribute(), source, new String[] { "kind", "element", "name", "attribute", "namespace",
				"##targetNamespace", "group", "#group:4" });
		addAnnotation(getGroup_Filter(), source, new String[] { "kind", "attribute", "name", "filter" });
		addAnnotation(getGroup_Rebase(), source, new String[] { "kind", "attribute", "name", "rebase" });
		addAnnotation(groupsTypeEClass, source, new String[] { "name", "groups_._type", "kind", "elementOnly" });
		addAnnotation(getGroupsType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getGroupsType_Public(), source, new String[] { "kind", "element", "name", "public", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getGroupsType_Private(), source, new String[] { "kind", "element", "name", "private",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(importEClass, source, new String[] { "name", "Import", "kind", "empty" });
		addAnnotation(getImport_Attribute(), source, new String[] { "kind", "attribute", "name", "attribute" });
		addAnnotation(getImport_Filter(), source, new String[] { "kind", "attribute", "name", "filter" });
		addAnnotation(getImport_VersionDesignator(), source, new String[] { "kind", "attribute", "name",
				"versionDesignator" });
		addAnnotation(parameterTypeEClass, source, new String[] { "name", "parameter_._type", "kind", "empty" });
		addAnnotation(getParameterType_Mandatory(), source, new String[] { "kind", "attribute", "name", "mandatory" });
		addAnnotation(getParameterType_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(getParameterType_Value(), source, new String[] { "kind", "attribute", "name", "value" });
		addAnnotation(pathEClass, source, new String[] { "name", "Path", "kind", "empty" });
		addAnnotation(getPath_Path(), source, new String[] { "kind", "attribute", "name", "path" });
		addAnnotation(prerequisiteEClass, source, new String[] { "name", "Prerequisite", "kind", "empty" });
		addAnnotation(getPrerequisite_Alias(), source, new String[] { "kind", "attribute", "name", "alias" });
		addAnnotation(getPrerequisite_Component(), source, new String[] { "kind", "attribute", "name", "component" });
		addAnnotation(getPrerequisite_ComponentType(), source, new String[] { "kind", "attribute", "name",
				"componentType" });
		addAnnotation(getPrerequisite_Contributor(), source,
				new String[] { "kind", "attribute", "name", "contributor" });
		addAnnotation(getPrerequisite_ExcludePattern(), source, new String[] { "kind", "attribute", "name",
				"excludePattern" });
		addAnnotation(getPrerequisite_Filter(), source, new String[] { "kind", "attribute", "name", "filter" });
		addAnnotation(getPrerequisite_IncludePattern(), source, new String[] { "kind", "attribute", "name",
				"includePattern" });
		addAnnotation(getPrerequisite_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(getPrerequisite_Optional(), source, new String[] { "kind", "attribute", "name", "optional" });
		addAnnotation(prerequisitesEClass, source, new String[] { "name", "Prerequisites", "kind", "elementOnly" });
		addAnnotation(getPrerequisites_Documentation(), source, new String[] { "kind", "element", "name",
				"documentation", "namespace", "##targetNamespace" });
		addAnnotation(getPrerequisites_Group(), source, new String[] { "kind", "group", "name", "group:1" });
		addAnnotation(getPrerequisites_Attribute(), source, new String[] { "kind", "element", "name", "attribute",
				"namespace", "##targetNamespace", "group", "#group:1" });
		addAnnotation(getPrerequisites_Alias(), source, new String[] { "kind", "attribute", "name", "alias" });
		addAnnotation(getPrerequisites_Rebase(), source, new String[] { "kind", "attribute", "name", "rebase" });
		addAnnotation(productsTypeEClass, source, new String[] { "name", "products_._type", "kind", "elementOnly" });
		addAnnotation(getProductsType_Group(), source, new String[] { "kind", "group", "name", "group:0" });
		addAnnotation(getProductsType_Path(), source, new String[] { "kind", "element", "name", "path", "namespace",
				"##targetNamespace", "group", "#group:0" });
		addAnnotation(getProductsType_Public(), source, new String[] { "kind", "element", "name", "public",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getProductsType_Private(), source, new String[] { "kind", "element", "name", "private",
				"namespace", "##targetNamespace", "group", "#group:0" });
		addAnnotation(getProductsType_Alias(), source, new String[] { "kind", "attribute", "name", "alias" });
		addAnnotation(getProductsType_Base(), source, new String[] { "kind", "attribute", "name", "base" });
		addAnnotation(getProductsType_FileCount(), source, new String[] { "kind", "attribute", "name", "fileCount" });
		addAnnotation(getProductsType_Pattern(), source, new String[] { "kind", "attribute", "name", "pattern" });
		addAnnotation(getProductsType_Replacement(), source,
				new String[] { "kind", "attribute", "name", "replacement" });
		addAnnotation(getProductsType_UpToDatePolicy(), source, new String[] { "kind", "attribute", "name",
				"upToDatePolicy" });
		addAnnotation(propertiesEClass, source, new String[] { "name", "Properties", "kind", "elementOnly" });
		addAnnotation(getProperties_Property(), source, new String[] { "kind", "element", "name", "property",
				"namespace", "##targetNamespace" });
		addAnnotation(removeEClass, source, new String[] { "name", "Remove", "kind", "empty" });
		addAnnotation(getRemove_Name(), source, new String[] { "kind", "attribute", "name", "name" });
		addAnnotation(removePathEClass, source, new String[] { "name", "RemovePath", "kind", "empty" });
		addAnnotation(getRemovePath_Path(), source, new String[] { "kind", "attribute", "name", "path" });
		addAnnotation(removePropertyEClass, source, new String[] { "name", "RemoveProperty", "kind", "empty" });
		addAnnotation(getRemoveProperty_Key(), source, new String[] { "kind", "attribute", "name", "key" });
		addAnnotation(renameEClass, source, new String[] { "name", "Rename", "kind", "empty" });
		addAnnotation(getRename_NewName(), source, new String[] { "kind", "attribute", "name", "newName" });
		addAnnotation(getRename_OldName(), source, new String[] { "kind", "attribute", "name", "oldName" });
		addAnnotation(upToDatePolicyEEnum, source, new String[] { "name", "UpToDatePolicy" });
		addAnnotation(upToDatePolicyObjectEDataType, source, new String[] { "name", "UpToDatePolicy:Object",
				"baseType", "UpToDatePolicy" });
	}

} // CSpecXMLPackageImpl
