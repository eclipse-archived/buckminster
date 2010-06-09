/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.impl;

import org.eclipse.buckminster.model.common.CommonPackage;

import org.eclipse.buckminster.mspec.ConflictResolution;
import org.eclipse.buckminster.mspec.MaterializationDirective;
import org.eclipse.buckminster.mspec.MaterializationNode;
import org.eclipse.buckminster.mspec.MaterializationSpec;
import org.eclipse.buckminster.mspec.MspecFactory;
import org.eclipse.buckminster.mspec.MspecPackage;
import org.eclipse.buckminster.mspec.Unpack;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class MspecPackageImpl extends EPackageImpl implements MspecPackage {
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
	private EClass materializationNodeEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass materializationDirectiveEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass materializationSpecEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass unpackEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum conflictResolutionEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link MspecPackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead,
	 * they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MspecPackage init() {
		if (isInited)
			return (MspecPackage) EPackage.Registry.INSTANCE.getEPackage(MspecPackage.eNS_URI);

		// Obtain or create and register package
		MspecPackageImpl theMspecPackage = (MspecPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MspecPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI) : new MspecPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CommonPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMspecPackage.createPackageContents();

		// Initialize created meta-data
		theMspecPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMspecPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MspecPackage.eNS_URI, theMspecPackage);
		return theMspecPackage;
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
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the
	 * package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory
	 * method {@link #init init()}, which also performs initialization of the
	 * package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.buckminster.mspec.MspecPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MspecPackageImpl() {
		super(eNS_URI, MspecFactory.eINSTANCE);
	}

	/**
	 * Creates the meta-model objects for the package. This method is guarded to
	 * have no affect on any invocation but its first. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PROPERTY_ELEMENT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MSPEC);

		materializationNodeEClass = createEClass(MATERIALIZATION_NODE);
		createEAttribute(materializationNodeEClass, MATERIALIZATION_NODE__NAME_PATTERN);
		createEAttribute(materializationNodeEClass, MATERIALIZATION_NODE__LEAF_ARTIFACT);
		createEAttribute(materializationNodeEClass, MATERIALIZATION_NODE__COMPONENT_TYPE);
		createEAttribute(materializationNodeEClass, MATERIALIZATION_NODE__RESOURCE_PATH);
		createEAttribute(materializationNodeEClass, MATERIALIZATION_NODE__EXCLUDE);
		createEAttribute(materializationNodeEClass, MATERIALIZATION_NODE__BINDING_NAME_PATTERN);
		createEAttribute(materializationNodeEClass, MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT);
		createEReference(materializationNodeEClass, MATERIALIZATION_NODE__UNPACK);
		createEAttribute(materializationNodeEClass, MATERIALIZATION_NODE__FILTER);

		materializationDirectiveEClass = createEClass(MATERIALIZATION_DIRECTIVE);
		createEReference(materializationDirectiveEClass, MATERIALIZATION_DIRECTIVE__DOCUMENTATION);
		createEAttribute(materializationDirectiveEClass, MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP);
		createEReference(materializationDirectiveEClass, MATERIALIZATION_DIRECTIVE__PROPERTIES);
		createEAttribute(materializationDirectiveEClass, MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION);
		createEAttribute(materializationDirectiveEClass, MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION);
		createEAttribute(materializationDirectiveEClass, MATERIALIZATION_DIRECTIVE__MATERIALIZER);
		createEAttribute(materializationDirectiveEClass, MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION);

		materializationSpecEClass = createEClass(MATERIALIZATION_SPEC);
		createEReference(materializationSpecEClass, MATERIALIZATION_SPEC__MSPEC_NODES);
		createEAttribute(materializationSpecEClass, MATERIALIZATION_SPEC__NAME);
		createEAttribute(materializationSpecEClass, MATERIALIZATION_SPEC__SHORT_DESC);
		createEAttribute(materializationSpecEClass, MATERIALIZATION_SPEC__URL);
		createEAttribute(materializationSpecEClass, MATERIALIZATION_SPEC__MAX_PARALLEL_JOBS);

		unpackEClass = createEClass(UNPACK);
		createEAttribute(unpackEClass, UNPACK__EXPAND);
		createEAttribute(unpackEClass, UNPACK__SUFFIX);

		// Create enums
		conflictResolutionEEnum = createEEnum(CONFLICT_RESOLUTION);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EEnum getConflictResolution() {
		return conflictResolutionEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Mspec() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_Property() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_PropertyElement() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getMaterializationDirective() {
		return materializationDirectiveEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationDirective_ConflictResolution() {
		return (EAttribute) materializationDirectiveEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getMaterializationDirective_Documentation() {
		return (EReference) materializationDirectiveEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationDirective_InstallLocation() {
		return (EAttribute) materializationDirectiveEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationDirective_Materializer() {
		return (EAttribute) materializationDirectiveEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getMaterializationDirective_Properties() {
		return (EReference) materializationDirectiveEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationDirective_PropertyGroup() {
		return (EAttribute) materializationDirectiveEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationDirective_WorkspaceLocation() {
		return (EAttribute) materializationDirectiveEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getMaterializationNode() {
		return materializationNodeEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationNode_BindingNamePattern() {
		return (EAttribute) materializationNodeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationNode_BindingNameReplacement() {
		return (EAttribute) materializationNodeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationNode_ComponentType() {
		return (EAttribute) materializationNodeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationNode_Exclude() {
		return (EAttribute) materializationNodeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationNode_Filter() {
		return (EAttribute) materializationNodeEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationNode_LeafArtifact() {
		return (EAttribute) materializationNodeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationNode_NamePattern() {
		return (EAttribute) materializationNodeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationNode_ResourcePath() {
		return (EAttribute) materializationNodeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getMaterializationNode_Unpack() {
		return (EReference) materializationNodeEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getMaterializationSpec() {
		return materializationSpecEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationSpec_MaxParallelJobs() {
		return (EAttribute) materializationSpecEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getMaterializationSpec_MspecNodes() {
		return (EReference) materializationSpecEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationSpec_Name() {
		return (EAttribute) materializationSpecEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationSpec_ShortDesc() {
		return (EAttribute) materializationSpecEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getMaterializationSpec_Url() {
		return (EAttribute) materializationSpecEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public MspecFactory getMspecFactory() {
		return (MspecFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getUnpack() {
		return unpackEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getUnpack_Expand() {
		return (EAttribute) unpackEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getUnpack_Suffix() {
		return (EAttribute) unpackEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Complete the initialization of the package and its meta-model. This
	 * method is guarded to have no affect on any invocation but its first. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		CommonPackage theCommonPackage = (CommonPackage) EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		materializationNodeEClass.getESuperTypes().add(this.getMaterializationDirective());
		materializationSpecEClass.getESuperTypes().add(this.getMaterializationDirective());

		// Initialize classes and features; add operations and parameters
		initEClass(documentRootEClass, null, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null,
				IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null,
				IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Property(), theCommonPackage.getPropertyConstant(), null, "property", null, 0, -2, null, IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_PropertyElement(), theCommonPackage.getPropertyElement(), null, "propertyElement", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Mspec(), this.getMaterializationSpec(), null, "mspec", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(materializationNodeEClass, MaterializationNode.class, "MaterializationNode", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMaterializationNode_NamePattern(), theCommonPackage.getPattern(), "namePattern", null, 1, 1, MaterializationNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationNode_LeafArtifact(), ecorePackage.getEString(), "leafArtifact", null, 0, 1, MaterializationNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationNode_ComponentType(), ecorePackage.getEString(), "componentType", null, 0, 1, MaterializationNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationNode_ResourcePath(), ecorePackage.getEString(), "resourcePath", null, 0, 1, MaterializationNode.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationNode_Exclude(), ecorePackage.getEBoolean(), "exclude", null, 0, 1, MaterializationNode.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationNode_BindingNamePattern(), theCommonPackage.getPattern(), "bindingNamePattern", null, 0, 1,
				MaterializationNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationNode_BindingNameReplacement(), ecorePackage.getEString(), "bindingNameReplacement", null, 0, 1,
				MaterializationNode.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMaterializationNode_Unpack(), this.getUnpack(), null, "unpack", null, 0, 1, MaterializationNode.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationNode_Filter(), theCommonPackage.getFilter(), "filter", null, 0, 1, MaterializationNode.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(materializationDirectiveEClass, MaterializationDirective.class, "MaterializationDirective", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMaterializationDirective_Documentation(), theCommonPackage.getDocumentation(), null, "documentation", null, 0, 1,
				MaterializationDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationDirective_PropertyGroup(), ecorePackage.getEFeatureMapEntry(), "propertyGroup", null, 1, -1,
				MaterializationDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEReference(getMaterializationDirective_Properties(), theCommonPackage.getProperty(), null, "properties", null, 1, -1,
				MaterializationDirective.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationDirective_ConflictResolution(), this.getConflictResolution(), "conflictResolution", "UPDATE", 0, 1,
				MaterializationDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getMaterializationDirective_InstallLocation(), ecorePackage.getEString(), "installLocation", null, 0, 1,
				MaterializationDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getMaterializationDirective_Materializer(), ecorePackage.getEString(), "materializer", null, 0, 1,
				MaterializationDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);
		initEAttribute(getMaterializationDirective_WorkspaceLocation(), ecorePackage.getEString(), "workspaceLocation", null, 0, 1,
				MaterializationDirective.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(materializationSpecEClass, MaterializationSpec.class, "MaterializationSpec", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMaterializationSpec_MspecNodes(), this.getMaterializationNode(), null, "mspecNodes", null, 0, -1,
				MaterializationSpec.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationSpec_Name(), ecorePackage.getEString(), "name", null, 0, 1, MaterializationSpec.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationSpec_ShortDesc(), ecorePackage.getEString(), "shortDesc", null, 0, 1, MaterializationSpec.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationSpec_Url(), ecorePackage.getEString(), "url", null, 0, 1, MaterializationSpec.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMaterializationSpec_MaxParallelJobs(), ecorePackage.getEInt(), "maxParallelJobs", null, 0, 1, MaterializationSpec.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unpackEClass, Unpack.class, "Unpack", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUnpack_Expand(), ecorePackage.getEBoolean(), "expand", "true", 0, 1, Unpack.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getUnpack_Suffix(), ecorePackage.getEString(), "suffix", null, 0, 1, Unpack.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(conflictResolutionEEnum, ConflictResolution.class, "ConflictResolution");
		addEEnumLiteral(conflictResolutionEEnum, ConflictResolution.FAIL);
		addEEnumLiteral(conflictResolutionEEnum, ConflictResolution.KEEP);
		addEEnumLiteral(conflictResolutionEEnum, ConflictResolution.REPLACE);
		addEEnumLiteral(conflictResolutionEEnum, ConflictResolution.UPDATE);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// http:///org/eclipse/emf/ecore/util/ExtendedMetaData
		createExtendedMetaDataAnnotations();
	}

	/**
	 * Initializes the annotations for
	 * <b>http:///org/eclipse/emf/ecore/util/ExtendedMetaData</b>. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void createExtendedMetaDataAnnotations() {
		String source = "http:///org/eclipse/emf/ecore/util/ExtendedMetaData";
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name", "xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name", "xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Property(), source, new String[] { "kind", "element", "name", "property", "namespace", "##targetNamespace",
				"affiliation", "http://www.eclipse.org/buckminster/Common-1.0#basicProperty" });
		addAnnotation(getDocumentRoot_PropertyElement(), source, new String[] { "kind", "element", "name", "propertyElement", "namespace",
				"##targetNamespace", "affiliation", "http://www.eclipse.org/buckminster/Common-1.0#basicProperty" });
		addAnnotation(getDocumentRoot_Mspec(), source, new String[] { "kind", "element", "namespace", "##targetNamespace" });
		addAnnotation(getMaterializationNode_Unpack(), source, new String[] { "kind", "element", "name", "unpack", "namespace", "##targetNamespace" });
		addAnnotation(getMaterializationDirective_Documentation(), source, new String[] { "kind", "element", "namespace", "##targetNamespace" });
		addAnnotation(getMaterializationDirective_PropertyGroup(), source, new String[] { "kind", "group", "name", "basicProperty:group",
				"namespace", "http://www.eclipse.org/buckminster/Common-1.0" });
		addAnnotation(getMaterializationDirective_Properties(), source, new String[] { "kind", "element", "name", "basicProperty", "namespace",
				"http://www.eclipse.org/buckminster/Common-1.0", "group", "http://www.eclipse.org/buckminster/Common-1.0#basicProperty:group" });
		addAnnotation(getMaterializationSpec_MspecNodes(), source, new String[] { "name", "mspecNode", "kind", "element", "namespace",
				"##targetNamespace" });
	}

} // MspecPackageImpl
