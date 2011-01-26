/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.buckminster.model.common.CommonPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
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
 * <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.rmap.RmapFactory
 * @model kind="package"
 * @generated
 */
public interface RmapPackage extends EPackage {
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
		 * {@link org.eclipse.buckminster.rmap.impl.MatcherImpl
		 * <em>Matcher</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.MatcherImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getMatcher()
		 * @generated
		 */
		EClass MATCHER = eINSTANCE.getMatcher();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCHER__PATTERN = eINSTANCE.getMatcher_Pattern();

		/**
		 * The meta object literal for the '<em><b>Component Types Attr</b></em>
		 * ' attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCHER__COMPONENT_TYPES_ATTR = eINSTANCE.getMatcher_ComponentTypesAttr();

		/**
		 * The meta object literal for the '<em><b>Component Types</b></em>'
		 * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCHER__COMPONENT_TYPES = eINSTANCE.getMatcher_ComponentTypes();

		/**
		 * The meta object literal for the '<em><b>Resolution Filter</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCHER__RESOLUTION_FILTER = eINSTANCE.getMatcher_ResolutionFilter();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.RedirectImpl
		 * <em>Redirect</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.RedirectImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getRedirect()
		 * @generated
		 */
		EClass REDIRECT = eINSTANCE.getRedirect();

		/**
		 * The meta object literal for the '<em><b>Redirect To</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REDIRECT__REDIRECT_TO = eINSTANCE.getRedirect_RedirectTo();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.LocatorImpl
		 * <em>Locator</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.LocatorImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getLocator()
		 * @generated
		 */
		EClass LOCATOR = eINSTANCE.getLocator();

		/**
		 * The meta object literal for the '<em><b>Search Path</b></em>'
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LOCATOR__SEARCH_PATH = eINSTANCE.getLocator_SearchPath();

		/**
		 * The meta object literal for the '<em><b>Fail On Error</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LOCATOR__FAIL_ON_ERROR = eINSTANCE.getLocator_FailOnError();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.SearchPathImpl
		 * <em>Search Path</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.SearchPathImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getSearchPath()
		 * @generated
		 */
		EClass SEARCH_PATH = eINSTANCE.getSearchPath();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SEARCH_PATH__NAME = eINSTANCE.getSearchPath_Name();

		/**
		 * The meta object literal for the '<em><b>Providers</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SEARCH_PATH__PROVIDERS = eINSTANCE.getSearchPath_Providers();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl
		 * <em>Resource Map</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.ResourceMapImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getResourceMap()
		 * @generated
		 */
		EClass RESOURCE_MAP = eINSTANCE.getResourceMap();

		/**
		 * The meta object literal for the '<em><b>Matcher Group</b></em>'
		 * attribute list feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute RESOURCE_MAP__MATCHER_GROUP = eINSTANCE.getResourceMap_MatcherGroup();

		/**
		 * The meta object literal for the '<em><b>Matchers</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__MATCHERS = eINSTANCE.getResourceMap_Matchers();

		/**
		 * The meta object literal for the '<em><b>Search Paths</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__SEARCH_PATHS = eINSTANCE.getResourceMap_SearchPaths();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__DOCUMENTATION = eINSTANCE.getResourceMap_Documentation();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.ProviderImpl
		 * <em>Provider</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.ProviderImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getProvider()
		 * @generated
		 */
		EClass PROVIDER = eINSTANCE.getProvider();

		/**
		 * The meta object literal for the '<em><b>Reader Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__READER_TYPE = eINSTANCE.getProvider_ReaderType();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__SOURCE = eINSTANCE.getProvider_Source();

		/**
		 * The meta object literal for the '<em><b>Mutable</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__MUTABLE = eINSTANCE.getProvider_Mutable();

		/**
		 * The meta object literal for the '<em><b>Version Converter</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROVIDER__VERSION_CONVERTER = eINSTANCE.getProvider_VersionConverter();

		/**
		 * The meta object literal for the '<em><b>URI</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROVIDER__URI = eINSTANCE.getProvider_URI();

		/**
		 * The meta object literal for the '<em><b>Matcher</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROVIDER__MATCHER = eINSTANCE.getProvider_Matcher();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROVIDER__DOCUMENTATION = eINSTANCE.getProvider_Documentation();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.TransformImpl
		 * <em>Transform</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
		 * -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.TransformImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getTransform()
		 * @generated
		 */
		EClass TRANSFORM = eINSTANCE.getTransform();

		/**
		 * The meta object literal for the '<em><b>From Pattern</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSFORM__FROM_PATTERN = eINSTANCE.getTransform_FromPattern();

		/**
		 * The meta object literal for the '<em><b>From Replacement</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSFORM__FROM_REPLACEMENT = eINSTANCE.getTransform_FromReplacement();

		/**
		 * The meta object literal for the '<em><b>To Pattern</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSFORM__TO_PATTERN = eINSTANCE.getTransform_ToPattern();

		/**
		 * The meta object literal for the '<em><b>To Replacement</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSFORM__TO_REPLACEMENT = eINSTANCE.getTransform_ToReplacement();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl
		 * <em>Version Converter</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.VersionConverterImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getVersionConverter()
		 * @generated
		 */
		EClass VERSION_CONVERTER = eINSTANCE.getVersionConverter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSION_CONVERTER__TYPE = eINSTANCE.getVersionConverter_Type();

		/**
		 * The meta object literal for the '<em><b>Transformers</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VERSION_CONVERTER__TRANSFORMERS = eINSTANCE.getVersionConverter_Transformers();

		/**
		 * The meta object literal for the '<em><b>Version Format</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSION_CONVERTER__VERSION_FORMAT = eINSTANCE.getVersionConverter_VersionFormat();

		/**
		 * The meta object literal for the '<em><b>Version Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSION_CONVERTER__VERSION_TYPE = eINSTANCE.getVersionConverter_VersionType();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.DocumentRootImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

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
		 * The meta object literal for the '<em><b>Match</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MATCH = eINSTANCE.getDocumentRoot_Match();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GROUP = eINSTANCE.getDocumentRoot_Group();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NAME = eINSTANCE.getDocumentRoot_Name();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VERSION = eINSTANCE.getDocumentRoot_Version();

		/**
		 * The meta object literal for the '<em><b>Revision</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__REVISION = eINSTANCE.getDocumentRoot_Revision();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TIMESTAMP = eINSTANCE.getDocumentRoot_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Os</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OS = eINSTANCE.getDocumentRoot_Os();

		/**
		 * The meta object literal for the '<em><b>Ws</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WS = eINSTANCE.getDocumentRoot_Ws();

		/**
		 * The meta object literal for the '<em><b>Arch</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ARCH = eINSTANCE.getDocumentRoot_Arch();

		/**
		 * The meta object literal for the '<em><b>Nl</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NL = eINSTANCE.getDocumentRoot_Nl();

		/**
		 * The meta object literal for the '<em><b>Rmap</b></em>' containment
		 * reference feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RMAP = eINSTANCE.getDocumentRoot_Rmap();

		/**
		 * The meta object literal for the '<em><b>Locators</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__LOCATORS = eINSTANCE.getDocumentRoot_Locators();

		/**
		 * The meta object literal for the '<em><b>Matcher</b></em>' reference
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MATCHER = eINSTANCE.getDocumentRoot_Matcher();

		/**
		 * The meta object literal for the '<em><b>Redirects</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__REDIRECTS = eINSTANCE.getDocumentRoot_Redirects();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl
		 * <em>URI Matcher</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.URIMatcherImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getURIMatcher()
		 * @generated
		 */
		EClass URI_MATCHER = eINSTANCE.getURIMatcher();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute URI_MATCHER__BASE = eINSTANCE.getURIMatcher_Base();

		/**
		 * The meta object literal for the '<em><b>Version Format</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute URI_MATCHER__VERSION_FORMAT = eINSTANCE.getURIMatcher_VersionFormat();

		/**
		 * The meta object literal for the '<em><b>Version Type</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute URI_MATCHER__VERSION_TYPE = eINSTANCE.getURIMatcher_VersionType();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.RepositoryImpl
		 * <em>Repository</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.RepositoryImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getRepository()
		 * @generated
		 */
		EClass REPOSITORY = eINSTANCE.getRepository();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY__ID = eINSTANCE.getRepository_Id();

		/**
		 * The meta object literal for the '<em><b>Connection</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference REPOSITORY__CONNECTION = eINSTANCE.getRepository_Connection();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY__TYPE = eINSTANCE.getRepository_Type();

		/**
		 * The meta object literal for the '<em><b>Branches</b></em>'
		 * containment reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference REPOSITORY__BRANCHES = eINSTANCE.getRepository_Branches();

		/**
		 * The meta object literal for the '<em><b>Checkout</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY__CHECKOUT = eINSTANCE.getRepository_Checkout();

		/**
		 * The meta object literal for the '<em><b>Allow Dirty</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY__ALLOW_DIRTY = eINSTANCE.getRepository_AllowDirty();

		/**
		 * The meta object literal for the '<em><b>Update</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REPOSITORY__UPDATE = eINSTANCE.getRepository_Update();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>'
		 * containment reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference REPOSITORY__DOCUMENTATION = eINSTANCE.getRepository_Documentation();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.impl.BranchPointImpl
		 * <em>Branch Point</em>}' class. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.BranchPointImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getBranchPoint()
		 * @generated
		 */
		EClass BRANCH_POINT = eINSTANCE.getBranchPoint();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BRANCH_POINT__NAME = eINSTANCE.getBranchPoint_Name();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BRANCH_POINT__TIMESTAMP = eINSTANCE.getBranchPoint_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Revision</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BRANCH_POINT__REVISION = eINSTANCE.getBranchPoint_Revision();

		/**
		 * The meta object literal for the '<em><b>Tag</b></em>' attribute
		 * feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BRANCH_POINT__TAG = eINSTANCE.getBranchPoint_Tag();

		/**
		 * The meta object literal for the '<em><b>On Merge Conflict</b></em>'
		 * attribute feature. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute BRANCH_POINT__ON_MERGE_CONFLICT = eINSTANCE.getBranchPoint_OnMergeConflict();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.VersionSelectorType
		 * <em>Version Selector Type</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.VersionSelectorType
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getVersionSelectorType()
		 * @generated
		 */
		EEnum VERSION_SELECTOR_TYPE = eINSTANCE.getVersionSelectorType();

		/**
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.rmap.ConflictPolicy
		 * <em>Conflict Policy</em>}' enum. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.ConflictPolicy
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getConflictPolicy()
		 * @generated
		 */
		EEnum CONFLICT_POLICY = eINSTANCE.getConflictPolicy();

		/**
		 * The meta object literal for the '
		 * <em>Transform Mismatch Exception</em>' data type. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.util.TransformMismatchException
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getTransformMismatchException()
		 * @generated
		 */
		EDataType TRANSFORM_MISMATCH_EXCEPTION = eINSTANCE.getTransformMismatchException();

		/**
		 * The meta object literal for the '<em>IComponent Reader</em>' data
		 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.util.IComponentReader
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getIComponentReader()
		 * @generated
		 */
		EDataType ICOMPONENT_READER = eINSTANCE.getIComponentReader();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "rmap";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/RMap-1.0";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "rm";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	RmapPackage eINSTANCE = org.eclipse.buckminster.rmap.impl.RmapPackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.MatcherImpl <em>Matcher</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.MatcherImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getMatcher()
	 * @generated
	 */
	int MATCHER = 3;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.RedirectImpl <em>Redirect</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.RedirectImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getRedirect()
	 * @generated
	 */
	int REDIRECT = 5;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.LocatorImpl <em>Locator</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.LocatorImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getLocator()
	 * @generated
	 */
	int LOCATOR = 2;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.SearchPathImpl
	 * <em>Search Path</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.SearchPathImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getSearchPath()
	 * @generated
	 */
	int SEARCH_PATH = 8;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl
	 * <em>Resource Map</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.ResourceMapImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getResourceMap()
	 * @generated
	 */
	int RESOURCE_MAP = 7;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.ProviderImpl <em>Provider</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.ProviderImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getProvider()
	 * @generated
	 */
	int PROVIDER = 4;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.TransformImpl
	 * <em>Transform</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.TransformImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getTransform()
	 * @generated
	 */
	int TRANSFORM = 9;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl
	 * <em>Version Converter</em>}' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.VersionConverterImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getVersionConverter()
	 * @generated
	 */
	int VERSION_CONVERTER = 11;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.DocumentRootImpl
	 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.DocumentRootImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 1;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl
	 * <em>URI Matcher</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.URIMatcherImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getURIMatcher()
	 * @generated
	 */
	int URI_MATCHER = 10;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.RepositoryImpl
	 * <em>Repository</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.RepositoryImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getRepository()
	 * @generated
	 */
	int REPOSITORY = 6;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.impl.BranchPointImpl
	 * <em>Branch Point</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.BranchPointImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getBranchPoint()
	 * @generated
	 */
	int BRANCH_POINT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRANCH_POINT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRANCH_POINT__TIMESTAMP = 1;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRANCH_POINT__REVISION = 2;

	/**
	 * The feature id for the '<em><b>Tag</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRANCH_POINT__TAG = 3;

	/**
	 * The feature id for the '<em><b>On Merge Conflict</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRANCH_POINT__ON_MERGE_CONFLICT = 4;

	/**
	 * The number of structural features of the '<em>Branch Point</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BRANCH_POINT_FEATURE_COUNT = 5;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = CommonPackage.ABSTRACT_DOCUMENT_ROOT__MIXED;

	/**
	 * The feature id for the '<em><b>Basic Value</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__BASIC_VALUE = CommonPackage.ABSTRACT_DOCUMENT_ROOT__BASIC_VALUE;

	/**
	 * The feature id for the '<em><b>Constant</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__CONSTANT = CommonPackage.ABSTRACT_DOCUMENT_ROOT__CONSTANT;

	/**
	 * The feature id for the '<em><b>Format</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__FORMAT = CommonPackage.ABSTRACT_DOCUMENT_ROOT__FORMAT;

	/**
	 * The feature id for the '<em><b>Property Ref</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__PROPERTY_REF = CommonPackage.ABSTRACT_DOCUMENT_ROOT__PROPERTY_REF;

	/**
	 * The feature id for the '<em><b>Replace</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REPLACE = CommonPackage.ABSTRACT_DOCUMENT_ROOT__REPLACE;

	/**
	 * The feature id for the '<em><b>Split</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__SPLIT = CommonPackage.ABSTRACT_DOCUMENT_ROOT__SPLIT;

	/**
	 * The feature id for the '<em><b>To Lower</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TO_LOWER = CommonPackage.ABSTRACT_DOCUMENT_ROOT__TO_LOWER;

	/**
	 * The feature id for the '<em><b>To Upper</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TO_UPPER = CommonPackage.ABSTRACT_DOCUMENT_ROOT__TO_UPPER;

	/**
	 * The feature id for the '<em><b>Rx Part</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RX_PART = CommonPackage.ABSTRACT_DOCUMENT_ROOT__RX_PART;

	/**
	 * The feature id for the '<em><b>Rx Pattern</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RX_PATTERN = CommonPackage.ABSTRACT_DOCUMENT_ROOT__RX_PATTERN;

	/**
	 * The feature id for the '<em><b>Rx Group</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RX_GROUP = CommonPackage.ABSTRACT_DOCUMENT_ROOT__RX_GROUP;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Match</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MATCH = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Group</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GROUP = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NAME = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Version</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VERSION = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REVISION = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TIMESTAMP = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Os</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OS = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Ws</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WS = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Arch</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ARCH = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 10;

	/**
	 * The feature id for the '<em><b>Nl</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NL = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Rmap</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RMAP = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 12;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__LOCATORS = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 13;

	/**
	 * The feature id for the '<em><b>Matcher</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MATCHER = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 14;

	/**
	 * The feature id for the '<em><b>Redirects</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REDIRECTS = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 15;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = CommonPackage.ABSTRACT_DOCUMENT_ROOT_FEATURE_COUNT + 16;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCHER__PATTERN = 0;

	/**
	 * The feature id for the '<em><b>Component Types Attr</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCHER__COMPONENT_TYPES_ATTR = 1;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCHER__COMPONENT_TYPES = 2;

	/**
	 * The feature id for the '<em><b>Resolution Filter</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCHER__RESOLUTION_FILTER = 3;

	/**
	 * The number of structural features of the '<em>Matcher</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCHER_FEATURE_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__PATTERN = MATCHER__PATTERN;

	/**
	 * The feature id for the '<em><b>Component Types Attr</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__COMPONENT_TYPES_ATTR = MATCHER__COMPONENT_TYPES_ATTR;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__COMPONENT_TYPES = MATCHER__COMPONENT_TYPES;

	/**
	 * The feature id for the '<em><b>Resolution Filter</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__RESOLUTION_FILTER = MATCHER__RESOLUTION_FILTER;

	/**
	 * The feature id for the '<em><b>Search Path</b></em>' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__SEARCH_PATH = MATCHER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fail On Error</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__FAIL_ON_ERROR = MATCHER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Locator</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR_FEATURE_COUNT = MATCHER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Property Constants</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__PROPERTY_CONSTANTS = CommonPackage.PROPERTIES__PROPERTY_CONSTANTS;

	/**
	 * The feature id for the '<em><b>Property Elements</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__PROPERTY_ELEMENTS = CommonPackage.PROPERTIES__PROPERTY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__PATTERN = CommonPackage.PROPERTIES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Component Types Attr</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__COMPONENT_TYPES_ATTR = CommonPackage.PROPERTIES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__COMPONENT_TYPES = CommonPackage.PROPERTIES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Resolution Filter</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__RESOLUTION_FILTER = CommonPackage.PROPERTIES_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Reader Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__READER_TYPE = CommonPackage.PROPERTIES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__SOURCE = CommonPackage.PROPERTIES_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__MUTABLE = CommonPackage.PROPERTIES_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Version Converter</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__VERSION_CONVERTER = CommonPackage.PROPERTIES_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>URI</b></em>' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__URI = CommonPackage.PROPERTIES_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Matcher</b></em>' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__MATCHER = CommonPackage.PROPERTIES_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__DOCUMENTATION = CommonPackage.PROPERTIES_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Provider</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER_FEATURE_COUNT = CommonPackage.PROPERTIES_FEATURE_COUNT + 11;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT__PATTERN = MATCHER__PATTERN;

	/**
	 * The feature id for the '<em><b>Component Types Attr</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT__COMPONENT_TYPES_ATTR = MATCHER__COMPONENT_TYPES_ATTR;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT__COMPONENT_TYPES = MATCHER__COMPONENT_TYPES;

	/**
	 * The feature id for the '<em><b>Resolution Filter</b></em>' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT__RESOLUTION_FILTER = MATCHER__RESOLUTION_FILTER;

	/**
	 * The feature id for the '<em><b>Redirect To</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT__REDIRECT_TO = MATCHER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Redirect</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT_FEATURE_COUNT = MATCHER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__ID = 0;

	/**
	 * The feature id for the '<em><b>Connection</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__CONNECTION = 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__TYPE = 2;

	/**
	 * The feature id for the '<em><b>Branches</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__BRANCHES = 3;

	/**
	 * The feature id for the '<em><b>Checkout</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__CHECKOUT = 4;

	/**
	 * The feature id for the '<em><b>Allow Dirty</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__ALLOW_DIRTY = 5;

	/**
	 * The feature id for the '<em><b>Update</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__UPDATE = 6;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY__DOCUMENTATION = 7;

	/**
	 * The number of structural features of the '<em>Repository</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REPOSITORY_FEATURE_COUNT = 8;

	/**
	 * The feature id for the '<em><b>Property Constants</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__PROPERTY_CONSTANTS = CommonPackage.PROPERTIES__PROPERTY_CONSTANTS;

	/**
	 * The feature id for the '<em><b>Property Elements</b></em>' map. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__PROPERTY_ELEMENTS = CommonPackage.PROPERTIES__PROPERTY_ELEMENTS;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__DOCUMENTATION = CommonPackage.PROPERTIES_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Matcher Group</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__MATCHER_GROUP = CommonPackage.PROPERTIES_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Matchers</b></em>' reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__MATCHERS = CommonPackage.PROPERTIES_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Search Paths</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__SEARCH_PATHS = CommonPackage.PROPERTIES_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Resource Map</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP_FEATURE_COUNT = CommonPackage.PROPERTIES_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEARCH_PATH__NAME = 0;

	/**
	 * The feature id for the '<em><b>Providers</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEARCH_PATH__PROVIDERS = 1;

	/**
	 * The number of structural features of the '<em>Search Path</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEARCH_PATH_FEATURE_COUNT = 2;

	/**
	 * The feature id for the '<em><b>From Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM__FROM_PATTERN = 0;

	/**
	 * The feature id for the '<em><b>From Replacement</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM__FROM_REPLACEMENT = 1;

	/**
	 * The feature id for the '<em><b>To Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM__TO_PATTERN = 2;

	/**
	 * The feature id for the '<em><b>To Replacement</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM__TO_REPLACEMENT = 3;

	/**
	 * The number of structural features of the '<em>Transform</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM_FEATURE_COUNT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__NAME = CommonPackage.RX_ASSEMBLY__NAME;

	/**
	 * The feature id for the '<em><b>Optional</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__OPTIONAL = CommonPackage.RX_ASSEMBLY__OPTIONAL;

	/**
	 * The feature id for the '<em><b>Rx Parts Group</b></em>' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__RX_PARTS_GROUP = CommonPackage.RX_ASSEMBLY__RX_PARTS_GROUP;

	/**
	 * The feature id for the '<em><b>Rx Parts</b></em>' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__RX_PARTS = CommonPackage.RX_ASSEMBLY__RX_PARTS;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__PATTERN = CommonPackage.RX_ASSEMBLY__PATTERN;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__BASE = CommonPackage.RX_ASSEMBLY_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Version Format</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__VERSION_FORMAT = CommonPackage.RX_ASSEMBLY_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Version Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__VERSION_TYPE = CommonPackage.RX_ASSEMBLY_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>URI Matcher</em>' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER_FEATURE_COUNT = CommonPackage.RX_ASSEMBLY_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Transformers</b></em>' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER__TRANSFORMERS = 1;

	/**
	 * The feature id for the '<em><b>Version Format</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER__VERSION_FORMAT = 2;

	/**
	 * The feature id for the '<em><b>Version Type</b></em>' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER__VERSION_TYPE = 3;

	/**
	 * The number of structural features of the '<em>Version Converter</em>'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.VersionSelectorType
	 * <em>Version Selector Type</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.VersionSelectorType
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getVersionSelectorType()
	 * @generated
	 */
	int VERSION_SELECTOR_TYPE = 12;

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.rmap.ConflictPolicy
	 * <em>Conflict Policy</em>}' enum. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.ConflictPolicy
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getConflictPolicy()
	 * @generated
	 */
	int CONFLICT_POLICY = 13;

	/**
	 * The meta object id for the '<em>Transform Mismatch Exception</em>' data
	 * type. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.util.TransformMismatchException
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getTransformMismatchException()
	 * @generated
	 */
	int TRANSFORM_MISMATCH_EXCEPTION = 14;

	/**
	 * The meta object id for the '<em>IComponent Reader</em>' data type. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.util.IComponentReader
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getIComponentReader()
	 * @generated
	 */
	int ICOMPONENT_READER = 15;

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint <em>Branch Point</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Branch Point</em>'.
	 * @see org.eclipse.buckminster.rmap.BranchPoint
	 * @generated
	 */
	EClass getBranchPoint();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.rmap.BranchPoint#getName()
	 * @see #getBranchPoint()
	 * @generated
	 */
	EAttribute getBranchPoint_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getOnMergeConflict
	 * <em>On Merge Conflict</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>On Merge Conflict</em>'.
	 * @see org.eclipse.buckminster.rmap.BranchPoint#getOnMergeConflict()
	 * @see #getBranchPoint()
	 * @generated
	 */
	EAttribute getBranchPoint_OnMergeConflict();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getRevision
	 * <em>Revision</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Revision</em>'.
	 * @see org.eclipse.buckminster.rmap.BranchPoint#getRevision()
	 * @see #getBranchPoint()
	 * @generated
	 */
	EAttribute getBranchPoint_Revision();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getTag <em>Tag</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Tag</em>'.
	 * @see org.eclipse.buckminster.rmap.BranchPoint#getTag()
	 * @see #getBranchPoint()
	 * @generated
	 */
	EAttribute getBranchPoint_Tag();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.BranchPoint#getTimestamp
	 * <em>Timestamp</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Timestamp</em>'.
	 * @see org.eclipse.buckminster.rmap.BranchPoint#getTimestamp()
	 * @see #getBranchPoint()
	 * @generated
	 */
	EAttribute getBranchPoint_Timestamp();

	/**
	 * Returns the meta object for enum '
	 * {@link org.eclipse.buckminster.rmap.ConflictPolicy
	 * <em>Conflict Policy</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Conflict Policy</em>'.
	 * @see org.eclipse.buckminster.rmap.ConflictPolicy
	 * @generated
	 */
	EEnum getConflictPolicy();

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
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getArch <em>Arch</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Arch</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getArch()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Arch();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getGroup <em>Group</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Group</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getGroup()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Group();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getLocators <em>Locators</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Locators</em>
	 *         '.
	 * @see org.eclipse.emf.ecore.EObject#getLocators()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Locators();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getMatch <em>Match</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Match</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getMatch()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Match();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.emf.ecore.EObject#getMatcher <em>Matcher</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Matcher</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getMatcher()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Matcher();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Name();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getNl <em>Nl</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Nl</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getNl()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Nl();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getOs <em>Os</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Os</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getOs()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Os();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getRedirects <em>Redirects</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Redirects</em>
	 *         '.
	 * @see org.eclipse.emf.ecore.EObject#getRedirects()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Redirects();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getRevision <em>Revision</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Revision</em>
	 *         '.
	 * @see org.eclipse.emf.ecore.EObject#getRevision()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Revision();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getRmap <em>Rmap</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Rmap</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getRmap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Rmap();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getTimestamp <em>Timestamp</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Timestamp</em>
	 *         '.
	 * @see org.eclipse.emf.ecore.EObject#getTimestamp()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Timestamp();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Version</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getVersion()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Version();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.emf.ecore.EObject#getWs <em>Ws</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Ws</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getWs()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Ws();

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
	 * Returns the meta object for data type '
	 * {@link org.eclipse.buckminster.rmap.util.IComponentReader
	 * <em>IComponent Reader</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for data type '<em>IComponent Reader</em>'.
	 * @see org.eclipse.buckminster.rmap.util.IComponentReader
	 * @model instanceClass="org.eclipse.buckminster.rmap.util.IComponentReader"
	 * @generated
	 */
	EDataType getIComponentReader();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.Locator <em>Locator</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Locator</em>'.
	 * @see org.eclipse.buckminster.rmap.Locator
	 * @generated
	 */
	EClass getLocator();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Locator#isFailOnError
	 * <em>Fail On Error</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Fail On Error</em>'.
	 * @see org.eclipse.buckminster.rmap.Locator#isFailOnError()
	 * @see #getLocator()
	 * @generated
	 */
	EAttribute getLocator_FailOnError();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.rmap.Locator#getSearchPath
	 * <em>Search Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Search Path</em>'.
	 * @see org.eclipse.buckminster.rmap.Locator#getSearchPath()
	 * @see #getLocator()
	 * @generated
	 */
	EReference getLocator_SearchPath();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.Matcher <em>Matcher</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Matcher</em>'.
	 * @see org.eclipse.buckminster.rmap.Matcher
	 * @generated
	 */
	EClass getMatcher();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.rmap.Matcher#getComponentTypes
	 * <em>Component Types</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Component Types</em>
	 *         '.
	 * @see org.eclipse.buckminster.rmap.Matcher#getComponentTypes()
	 * @see #getMatcher()
	 * @generated
	 */
	EAttribute getMatcher_ComponentTypes();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Matcher#getComponentTypesAttr
	 * <em>Component Types Attr</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component Types Attr</em>
	 *         '.
	 * @see org.eclipse.buckminster.rmap.Matcher#getComponentTypesAttr()
	 * @see #getMatcher()
	 * @generated
	 */
	EAttribute getMatcher_ComponentTypesAttr();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Matcher#getPattern <em>Pattern</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.eclipse.buckminster.rmap.Matcher#getPattern()
	 * @see #getMatcher()
	 * @generated
	 */
	EAttribute getMatcher_Pattern();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Matcher#getResolutionFilter
	 * <em>Resolution Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>Resolution Filter</em>'.
	 * @see org.eclipse.buckminster.rmap.Matcher#getResolutionFilter()
	 * @see #getMatcher()
	 * @generated
	 */
	EAttribute getMatcher_ResolutionFilter();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.Provider <em>Provider</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Provider</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider
	 * @generated
	 */
	EClass getProvider();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.Provider#getDocumentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Documentation</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getDocumentation()
	 * @see #getProvider()
	 * @generated
	 */
	EReference getProvider_Documentation();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.Provider#getMatcher <em>Matcher</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Matcher</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getMatcher()
	 * @see #getProvider()
	 * @generated
	 */
	EReference getProvider_Matcher();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Provider#isMutable <em>Mutable</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Mutable</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#isMutable()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_Mutable();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Provider#getReaderType
	 * <em>Reader Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Reader Type</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getReaderType()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_ReaderType();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Provider#isSource <em>Source</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#isSource()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_Source();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.Provider#getURI <em>URI</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>URI</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getURI()
	 * @see #getProvider()
	 * @generated
	 */
	EReference getProvider_URI();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.Provider#getVersionConverter
	 * <em>Version Converter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Version Converter</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getVersionConverter()
	 * @see #getProvider()
	 * @generated
	 */
	EReference getProvider_VersionConverter();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.Redirect <em>Redirect</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Redirect</em>'.
	 * @see org.eclipse.buckminster.rmap.Redirect
	 * @generated
	 */
	EClass getRedirect();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Redirect#getRedirectTo
	 * <em>Redirect To</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Redirect To</em>'.
	 * @see org.eclipse.buckminster.rmap.Redirect#getRedirectTo()
	 * @see #getRedirect()
	 * @generated
	 */
	EAttribute getRedirect_RedirectTo();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.Repository <em>Repository</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Repository</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository
	 * @generated
	 */
	EClass getRepository();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Repository#isAllowDirty
	 * <em>Allow Dirty</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Allow Dirty</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository#isAllowDirty()
	 * @see #getRepository()
	 * @generated
	 */
	EAttribute getRepository_AllowDirty();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.Repository#getBranches
	 * <em>Branches</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Branches</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository#getBranches()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_Branches();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Repository#isCheckout
	 * <em>Checkout</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Checkout</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository#isCheckout()
	 * @see #getRepository()
	 * @generated
	 */
	EAttribute getRepository_Checkout();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.Repository#getConnection
	 * <em>Connection</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Connection</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository#getConnection()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_Connection();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.Repository#getDocumentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Documentation</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository#getDocumentation()
	 * @see #getRepository()
	 * @generated
	 */
	EReference getRepository_Documentation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Repository#getId <em>Id</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository#getId()
	 * @see #getRepository()
	 * @generated
	 */
	EAttribute getRepository_Id();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Repository#getType <em>Type</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository#getType()
	 * @see #getRepository()
	 * @generated
	 */
	EAttribute getRepository_Type();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Repository#isUpdate <em>Update</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Update</em>'.
	 * @see org.eclipse.buckminster.rmap.Repository#isUpdate()
	 * @see #getRepository()
	 * @generated
	 */
	EAttribute getRepository_Update();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap <em>Resource Map</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Resource Map</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap
	 * @generated
	 */
	EClass getResourceMap();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getDocumentation
	 * <em>Documentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '
	 *         <em>Documentation</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getDocumentation()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_Documentation();

	/**
	 * Returns the meta object for the attribute list '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getMatcherGroup
	 * <em>Matcher Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Matcher Group</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getMatcherGroup()
	 * @see #getResourceMap()
	 * @generated
	 */
	EAttribute getResourceMap_MatcherGroup();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getMatchers
	 * <em>Matchers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Matchers</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getMatchers()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_Matchers();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getSearchPaths
	 * <em>Search Paths</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Search Paths</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getSearchPaths()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_SearchPaths();

	/**
	 * Returns the factory that creates the instances of the model. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RmapFactory getRmapFactory();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.SearchPath <em>Search Path</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Search Path</em>'.
	 * @see org.eclipse.buckminster.rmap.SearchPath
	 * @generated
	 */
	EClass getSearchPath();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.SearchPath#getName <em>Name</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.rmap.SearchPath#getName()
	 * @see #getSearchPath()
	 * @generated
	 */
	EAttribute getSearchPath_Name();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.SearchPath#getProviders
	 * <em>Providers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Providers</em>'.
	 * @see org.eclipse.buckminster.rmap.SearchPath#getProviders()
	 * @see #getSearchPath()
	 * @generated
	 */
	EReference getSearchPath_Providers();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.Transform <em>Transform</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Transform</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform
	 * @generated
	 */
	EClass getTransform();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Transform#getFromPattern
	 * <em>From Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>From Pattern</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform#getFromPattern()
	 * @see #getTransform()
	 * @generated
	 */
	EAttribute getTransform_FromPattern();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Transform#getFromReplacement
	 * <em>From Replacement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for the attribute '<em>From Replacement</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform#getFromReplacement()
	 * @see #getTransform()
	 * @generated
	 */
	EAttribute getTransform_FromReplacement();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Transform#getToPattern
	 * <em>To Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>To Pattern</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform#getToPattern()
	 * @see #getTransform()
	 * @generated
	 */
	EAttribute getTransform_ToPattern();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.Transform#getToReplacement
	 * <em>To Replacement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>To Replacement</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform#getToReplacement()
	 * @see #getTransform()
	 * @generated
	 */
	EAttribute getTransform_ToReplacement();

	/**
	 * Returns the meta object for data type '
	 * {@link org.eclipse.buckminster.rmap.util.TransformMismatchException
	 * <em>Transform Mismatch Exception</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for data type '
	 *         <em>Transform Mismatch Exception</em>'.
	 * @see org.eclipse.buckminster.rmap.util.TransformMismatchException
	 * @model instanceClass=
	 *        "org.eclipse.buckminster.rmap.util.TransformMismatchException"
	 * @generated
	 */
	EDataType getTransformMismatchException();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.URIMatcher <em>URI Matcher</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>URI Matcher</em>'.
	 * @see org.eclipse.buckminster.rmap.URIMatcher
	 * @generated
	 */
	EClass getURIMatcher();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.URIMatcher#getBase <em>Base</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Base</em>'.
	 * @see org.eclipse.buckminster.rmap.URIMatcher#getBase()
	 * @see #getURIMatcher()
	 * @generated
	 */
	EAttribute getURIMatcher_Base();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.URIMatcher#getVersionFormat
	 * <em>Version Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version Format</em>'.
	 * @see org.eclipse.buckminster.rmap.URIMatcher#getVersionFormat()
	 * @see #getURIMatcher()
	 * @generated
	 */
	EAttribute getURIMatcher_VersionFormat();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.URIMatcher#getVersionType
	 * <em>Version Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version Type</em>'.
	 * @see org.eclipse.buckminster.rmap.URIMatcher#getVersionType()
	 * @see #getURIMatcher()
	 * @generated
	 */
	EAttribute getURIMatcher_VersionType();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter
	 * <em>Version Converter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @return the meta object for class '<em>Version Converter</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter
	 * @generated
	 */
	EClass getVersionConverter();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#getTransformers
	 * <em>Transformers</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '
	 *         <em>Transformers</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter#getTransformers()
	 * @see #getVersionConverter()
	 * @generated
	 */
	EReference getVersionConverter_Transformers();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter#getType()
	 * @see #getVersionConverter()
	 * @generated
	 */
	EAttribute getVersionConverter_Type();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#getVersionFormat
	 * <em>Version Format</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version Format</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter#getVersionFormat()
	 * @see #getVersionConverter()
	 * @generated
	 */
	EAttribute getVersionConverter_VersionFormat();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#getVersionType
	 * <em>Version Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version Type</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter#getVersionType()
	 * @see #getVersionConverter()
	 * @generated
	 */
	EAttribute getVersionConverter_VersionType();

	/**
	 * Returns the meta object for enum '
	 * {@link org.eclipse.buckminster.rmap.VersionSelectorType
	 * <em>Version Selector Type</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for enum '<em>Version Selector Type</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionSelectorType
	 * @generated
	 */
	EEnum getVersionSelectorType();

} // RmapPackage
