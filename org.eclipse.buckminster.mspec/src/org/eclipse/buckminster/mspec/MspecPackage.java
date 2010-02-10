/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains
 * accessors for the meta objects to represent
 * <ul>
 * <li>each class,</li>
 * <li>each feature of each class,</li>
 * <li>each enum,</li>
 * <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc --> <!-- begin-model-doc --> A replacement pattern matched
 * against the component name when creating a workspace project name. Must be
 * used in combination with bindingNameReplacement <!-- end-model-doc -->
 * 
 * @see org.eclipse.buckminster.mspec.MspecFactory
 * @model kind="package"
 * @generated
 */
public interface MspecPackage extends EPackage {
	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that
	 * represent
	 * <ul>
	 * <li>each class,</li>
	 * <li>each feature of each class,</li>
	 * <li>each enum,</li>
	 * <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.mspec.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.mspec.impl.DocumentRootImpl
		 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute
		 * list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>'
		 * map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>'
		 * map feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROPERTY = eINSTANCE.getDocumentRoot_Property();

		/**
		 * The meta object literal for the '<em><b>Property Element</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__PROPERTY_ELEMENT = eINSTANCE.getDocumentRoot_PropertyElement();

		/**
		 * The meta object literal for the '<em><b>Mspec</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MSPEC = eINSTANCE.getDocumentRoot_Mspec();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl
		 * <em>Materialization Node</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl
		 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getMaterializationNode()
		 * @generated
		 */
		EClass MATERIALIZATION_NODE = eINSTANCE.getMaterializationNode();

		/**
		 * The meta object literal for the '<em><b>Name Pattern</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_NODE__NAME_PATTERN = eINSTANCE.getMaterializationNode_NamePattern();

		/**
		 * The meta object literal for the '<em><b>Leaf Artifact</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_NODE__LEAF_ARTIFACT = eINSTANCE.getMaterializationNode_LeafArtifact();

		/**
		 * The meta object literal for the '<em><b>Component Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_NODE__COMPONENT_TYPE = eINSTANCE.getMaterializationNode_ComponentType();

		/**
		 * The meta object literal for the '<em><b>Resource Path</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_NODE__RESOURCE_PATH = eINSTANCE.getMaterializationNode_ResourcePath();

		/**
		 * The meta object literal for the '<em><b>Exclude</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_NODE__EXCLUDE = eINSTANCE.getMaterializationNode_Exclude();

		/**
		 * The meta object literal for the '<em><b>Binding Name Pattern</b></em>
		 * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_NODE__BINDING_NAME_PATTERN = eINSTANCE.getMaterializationNode_BindingNamePattern();

		/**
		 * The meta object literal for the '
		 * <em><b>Binding Name Replacement</b></em>' attribute feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT = eINSTANCE.getMaterializationNode_BindingNameReplacement();

		/**
		 * The meta object literal for the '<em><b>Unpack</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MATERIALIZATION_NODE__UNPACK = eINSTANCE.getMaterializationNode_Unpack();

		/**
		 * The meta object literal for the '<em><b>Filter</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_NODE__FILTER = eINSTANCE.getMaterializationNode_Filter();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl
		 * <em>Materialization Directive</em>}' class. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl
		 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getMaterializationDirective()
		 * @generated
		 */
		EClass MATERIALIZATION_DIRECTIVE = eINSTANCE.getMaterializationDirective();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MATERIALIZATION_DIRECTIVE__DOCUMENTATION = eINSTANCE.getMaterializationDirective_Documentation();

		/**
		 * The meta object literal for the '<em><b>Property Group</b></em>'
		 * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP = eINSTANCE.getMaterializationDirective_PropertyGroup();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MATERIALIZATION_DIRECTIVE__PROPERTIES = eINSTANCE.getMaterializationDirective_Properties();

		/**
		 * The meta object literal for the '<em><b>Conflict Resolution</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION = eINSTANCE.getMaterializationDirective_ConflictResolution();

		/**
		 * The meta object literal for the '<em><b>Install Location</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION = eINSTANCE.getMaterializationDirective_InstallLocation();

		/**
		 * The meta object literal for the '<em><b>Materializer</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_DIRECTIVE__MATERIALIZER = eINSTANCE.getMaterializationDirective_Materializer();

		/**
		 * The meta object literal for the '<em><b>Workspace Location</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION = eINSTANCE.getMaterializationDirective_WorkspaceLocation();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.mspec.impl.MaterializationSpecImpl
		 * <em>Materialization Spec</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.mspec.impl.MaterializationSpecImpl
		 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getMaterializationSpec()
		 * @generated
		 */
		EClass MATERIALIZATION_SPEC = eINSTANCE.getMaterializationSpec();

		/**
		 * The meta object literal for the '<em><b>Mspec Nodes</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MATERIALIZATION_SPEC__MSPEC_NODES = eINSTANCE.getMaterializationSpec_MspecNodes();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_SPEC__NAME = eINSTANCE.getMaterializationSpec_Name();

		/**
		 * The meta object literal for the '<em><b>Short Desc</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_SPEC__SHORT_DESC = eINSTANCE.getMaterializationSpec_ShortDesc();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_SPEC__URL = eINSTANCE.getMaterializationSpec_Url();

		/**
		 * The meta object literal for the '<em><b>Max Parallel Jobs</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATERIALIZATION_SPEC__MAX_PARALLEL_JOBS = eINSTANCE.getMaterializationSpec_MaxParallelJobs();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.mspec.impl.UnpackImpl <em>Unpack</em>}
		 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.mspec.impl.UnpackImpl
		 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getUnpack()
		 * @generated
		 */
		EClass UNPACK = eINSTANCE.getUnpack();

		/**
		 * The meta object literal for the '<em><b>Expand</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute UNPACK__EXPAND = eINSTANCE.getUnpack_Expand();

		/**
		 * The meta object literal for the '<em><b>Suffix</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute UNPACK__SUFFIX = eINSTANCE.getUnpack_Suffix();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.mspec.ConflictResolution
		 * <em>Conflict Resolution</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.mspec.ConflictResolution
		 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getConflictResolution()
		 * @generated
		 */
		EEnum CONFLICT_RESOLUTION = eINSTANCE.getConflictResolution();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "mspec";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/MetaData-1.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "mspec";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	MspecPackage eINSTANCE = org.eclipse.buckminster.mspec.impl.MspecPackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.mspec.impl.DocumentRootImpl
	 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.mspec.impl.DocumentRootImpl
	 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 0;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY = 3;

	/**
	 * The feature id for the '<em><b>Property Element</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY_ELEMENT = 4;

	/**
	 * The feature id for the '<em><b>Mspec</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MSPEC = 5;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl
	 * <em>Materialization Directive</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.mspec.impl.MaterializationDirectiveImpl
	 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getMaterializationDirective()
	 * @generated
	 */
	int MATERIALIZATION_DIRECTIVE = 2;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_DIRECTIVE__DOCUMENTATION = 0;

	/**
	 * The feature id for the '<em><b>Property Group</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP = 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_DIRECTIVE__PROPERTIES = 2;

	/**
	 * The feature id for the '<em><b>Conflict Resolution</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION = 3;

	/**
	 * The feature id for the '<em><b>Install Location</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION = 4;

	/**
	 * The feature id for the '<em><b>Materializer</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_DIRECTIVE__MATERIALIZER = 5;

	/**
	 * The feature id for the '<em><b>Workspace Location</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION = 6;

	/**
	 * The number of structural features of the '
	 * <em>Materialization Directive</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_DIRECTIVE_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl
	 * <em>Materialization Node</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.mspec.impl.MaterializationNodeImpl
	 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getMaterializationNode()
	 * @generated
	 */
	int MATERIALIZATION_NODE = 1;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__DOCUMENTATION = MATERIALIZATION_DIRECTIVE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Property Group</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__PROPERTY_GROUP = MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__PROPERTIES = MATERIALIZATION_DIRECTIVE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Conflict Resolution</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__CONFLICT_RESOLUTION = MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION;

	/**
	 * The feature id for the '<em><b>Install Location</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__INSTALL_LOCATION = MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION;

	/**
	 * The feature id for the '<em><b>Materializer</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__MATERIALIZER = MATERIALIZATION_DIRECTIVE__MATERIALIZER;

	/**
	 * The feature id for the '<em><b>Workspace Location</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__WORKSPACE_LOCATION = MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION;

	/**
	 * The feature id for the '<em><b>Name Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__NAME_PATTERN = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Leaf Artifact</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__LEAF_ARTIFACT = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Component Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__COMPONENT_TYPE = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Resource Path</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__RESOURCE_PATH = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Exclude</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__EXCLUDE = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Binding Name Pattern</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__BINDING_NAME_PATTERN = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Binding Name Replacement</b></em>'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__BINDING_NAME_REPLACEMENT = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Unpack</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__UNPACK = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Filter</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE__FILTER = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Materialization Node</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_NODE_FEATURE_COUNT = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.mspec.impl.MaterializationSpecImpl
	 * <em>Materialization Spec</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.mspec.impl.MaterializationSpecImpl
	 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getMaterializationSpec()
	 * @generated
	 */
	int MATERIALIZATION_SPEC = 3;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__DOCUMENTATION = MATERIALIZATION_DIRECTIVE__DOCUMENTATION;

	/**
	 * The feature id for the '<em><b>Property Group</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__PROPERTY_GROUP = MATERIALIZATION_DIRECTIVE__PROPERTY_GROUP;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__PROPERTIES = MATERIALIZATION_DIRECTIVE__PROPERTIES;

	/**
	 * The feature id for the '<em><b>Conflict Resolution</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__CONFLICT_RESOLUTION = MATERIALIZATION_DIRECTIVE__CONFLICT_RESOLUTION;

	/**
	 * The feature id for the '<em><b>Install Location</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__INSTALL_LOCATION = MATERIALIZATION_DIRECTIVE__INSTALL_LOCATION;

	/**
	 * The feature id for the '<em><b>Materializer</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__MATERIALIZER = MATERIALIZATION_DIRECTIVE__MATERIALIZER;

	/**
	 * The feature id for the '<em><b>Workspace Location</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__WORKSPACE_LOCATION = MATERIALIZATION_DIRECTIVE__WORKSPACE_LOCATION;

	/**
	 * The feature id for the '<em><b>Mspec Nodes</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__MSPEC_NODES = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__NAME = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Short Desc</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__SHORT_DESC = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__URL = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Max Parallel Jobs</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC__MAX_PARALLEL_JOBS = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Materialization Spec</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATERIALIZATION_SPEC_FEATURE_COUNT = MATERIALIZATION_DIRECTIVE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.mspec.impl.UnpackImpl <em>Unpack</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.mspec.impl.UnpackImpl
	 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getUnpack()
	 * @generated
	 */
	int UNPACK = 4;

	/**
	 * The feature id for the '<em><b>Expand</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UNPACK__EXPAND = 0;

	/**
	 * The feature id for the '<em><b>Suffix</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UNPACK__SUFFIX = 1;

	/**
	 * The number of structural features of the '<em>Unpack</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int UNPACK_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.mspec.ConflictResolution
	 * <em>Conflict Resolution</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.mspec.ConflictResolution
	 * @see org.eclipse.buckminster.mspec.impl.MspecPackageImpl#getConflictResolution()
	 * @generated
	 */
	int CONFLICT_RESOLUTION = 5;

	/**
	 * Returns the meta object for enum '
	 * {@link org.eclipse.buckminster.mspec.ConflictResolution
	 * <em>Conflict Resolution</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for enum '<em>Conflict Resolution</em>'.
	 * @see org.eclipse.buckminster.mspec.ConflictResolution
	 * @generated
	 */
	EEnum getConflictResolution();

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.ecore.EObject
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.emf.ecore.EObject
	 * @model extendedMetaData="name='' kind='mixed'"
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.emf.ecore.EObject#getMixed <em>Mixed</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getMspec <em>Mspec</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Mspec</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getMspec()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Mspec();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Property</em>
	 *         '.
	 * @see org.eclipse.emf.ecore.EObject#getProperty()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Property();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getPropertyElement
	 * <em>Property Element</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Property Element</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getPropertyElement()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_PropertyElement();

	/**
	 * Returns the meta object for the map '
	 * {@link org.eclipse.emf.ecore.EObject#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '
	 * {@link org.eclipse.emf.ecore.EObject#getXSISchemaLocation
	 * <em>XSI Schema Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective
	 * <em>Materialization Directive</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Materialization Directive</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationDirective
	 * @generated
	 */
	EClass getMaterializationDirective();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getConflictResolution
	 * <em>Conflict Resolution</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Conflict Resolution</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationDirective#getConflictResolution()
	 * @see #getMaterializationDirective()
	 * @generated
	 */
	EAttribute getMaterializationDirective_ConflictResolution();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getDocumentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Documentation</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationDirective#getDocumentation()
	 * @see #getMaterializationDirective()
	 * @generated
	 */
	EReference getMaterializationDirective_Documentation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getInstallLocation
	 * <em>Install Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Install Location</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationDirective#getInstallLocation()
	 * @see #getMaterializationDirective()
	 * @generated
	 */
	EAttribute getMaterializationDirective_InstallLocation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getMaterializer
	 * <em>Materializer</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Materializer</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationDirective#getMaterializer()
	 * @see #getMaterializationDirective()
	 * @generated
	 */
	EAttribute getMaterializationDirective_Materializer();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getProperties
	 * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Properties</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationDirective#getProperties()
	 * @see #getMaterializationDirective()
	 * @generated
	 */
	EReference getMaterializationDirective_Properties();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getPropertyGroup
	 * <em>Property Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Property Group</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationDirective#getPropertyGroup()
	 * @see #getMaterializationDirective()
	 * @generated
	 */
	EAttribute getMaterializationDirective_PropertyGroup();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationDirective#getWorkspaceLocation
	 * <em>Workspace Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Workspace Location</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationDirective#getWorkspaceLocation()
	 * @see #getMaterializationDirective()
	 * @generated
	 */
	EAttribute getMaterializationDirective_WorkspaceLocation();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode
	 * <em>Materialization Node</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Materialization Node</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode
	 * @generated
	 */
	EClass getMaterializationNode();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#getBindingNamePattern
	 * <em>Binding Name Pattern</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Binding Name Pattern</em>
	 *         '.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#getBindingNamePattern()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EAttribute getMaterializationNode_BindingNamePattern();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#getBindingNameReplacement
	 * <em>Binding Name Replacement</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute '
	 *         <em>Binding Name Replacement</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#getBindingNameReplacement()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EAttribute getMaterializationNode_BindingNameReplacement();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#getComponentType
	 * <em>Component Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component Type</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#getComponentType()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EAttribute getMaterializationNode_ComponentType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#isExclude
	 * <em>Exclude</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Exclude</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#isExclude()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EAttribute getMaterializationNode_Exclude();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#getFilter
	 * <em>Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Filter</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#getFilter()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EAttribute getMaterializationNode_Filter();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#getLeafArtifact
	 * <em>Leaf Artifact</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Leaf Artifact</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#getLeafArtifact()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EAttribute getMaterializationNode_LeafArtifact();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#getNamePattern
	 * <em>Name Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name Pattern</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#getNamePattern()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EAttribute getMaterializationNode_NamePattern();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#getResourcePath
	 * <em>Resource Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Resource Path</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#getResourcePath()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EAttribute getMaterializationNode_ResourcePath();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.mspec.MaterializationNode#getUnpack
	 * <em>Unpack</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Unpack</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationNode#getUnpack()
	 * @see #getMaterializationNode()
	 * @generated
	 */
	EReference getMaterializationNode_Unpack();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec
	 * <em>Materialization Spec</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Materialization Spec</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationSpec
	 * @generated
	 */
	EClass getMaterializationSpec();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getMaxParallelJobs
	 * <em>Max Parallel Jobs</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Max Parallel Jobs</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationSpec#getMaxParallelJobs()
	 * @see #getMaterializationSpec()
	 * @generated
	 */
	EAttribute getMaterializationSpec_MaxParallelJobs();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getMspecNodes
	 * <em>Mspec Nodes</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Mspec Nodes</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationSpec#getMspecNodes()
	 * @see #getMaterializationSpec()
	 * @generated
	 */
	EReference getMaterializationSpec_MspecNodes();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationSpec#getName()
	 * @see #getMaterializationSpec()
	 * @generated
	 */
	EAttribute getMaterializationSpec_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getShortDesc
	 * <em>Short Desc</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Short Desc</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationSpec#getShortDesc()
	 * @see #getMaterializationSpec()
	 * @generated
	 */
	EAttribute getMaterializationSpec_ShortDesc();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.MaterializationSpec#getUrl
	 * <em>Url</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see org.eclipse.buckminster.mspec.MaterializationSpec#getUrl()
	 * @see #getMaterializationSpec()
	 * @generated
	 */
	EAttribute getMaterializationSpec_Url();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	MspecFactory getMspecFactory();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.mspec.Unpack <em>Unpack</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Unpack</em>'.
	 * @see org.eclipse.buckminster.mspec.Unpack
	 * @generated
	 */
	EClass getUnpack();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.Unpack#isExpand <em>Expand</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Expand</em>'.
	 * @see org.eclipse.buckminster.mspec.Unpack#isExpand()
	 * @see #getUnpack()
	 * @generated
	 */
	EAttribute getUnpack_Expand();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.mspec.Unpack#getSuffix <em>Suffix</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Suffix</em>'.
	 * @see org.eclipse.buckminster.mspec.Unpack#getSuffix()
	 * @see #getUnpack()
	 * @generated
	 */
	EAttribute getUnpack_Suffix();

} // MspecPackage
