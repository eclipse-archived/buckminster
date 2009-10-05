/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc --> The <b>Package</b> for the model. It contains accessors for the meta objects to represent
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
public interface RmapPackage extends EPackage
{
	/**
	 * <!-- begin-user-doc --> Defines literals for the meta objects that represent
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
	interface Literals
	{
		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.MatcherImpl <em>Matcher</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.MatcherImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getMatcher()
		 * @generated
		 */
		EClass MATCHER = eINSTANCE.getMatcher();

		/**
		 * The meta object literal for the '<em><b>Pattern</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute MATCHER__PATTERN = eINSTANCE.getMatcher_Pattern();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.RedirectImpl <em>Redirect</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.RedirectImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getRedirect()
		 * @generated
		 */
		EClass REDIRECT = eINSTANCE.getRedirect();

		/**
		 * The meta object literal for the '<em><b>Href</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute REDIRECT__HREF = eINSTANCE.getRedirect_Href();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.LocatorImpl <em>Locator</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.LocatorImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getLocator()
		 * @generated
		 */
		EClass LOCATOR = eINSTANCE.getLocator();

		/**
		 * The meta object literal for the '<em><b>Search Path</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference LOCATOR__SEARCH_PATH = eINSTANCE.getLocator_SearchPath();

		/**
		 * The meta object literal for the '<em><b>Fail On Error</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute LOCATOR__FAIL_ON_ERROR = eINSTANCE.getLocator_FailOnError();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.SearchPathImpl
		 * <em>Search Path</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.SearchPathImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getSearchPath()
		 * @generated
		 */
		EClass SEARCH_PATH = eINSTANCE.getSearchPath();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute SEARCH_PATH__NAME = eINSTANCE.getSearchPath_Name();

		/**
		 * The meta object literal for the '<em><b>Providers</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference SEARCH_PATH__PROVIDERS = eINSTANCE.getSearchPath_Providers();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl
		 * <em>Resource Map</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.ResourceMapImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getResourceMap()
		 * @generated
		 */
		EClass RESOURCE_MAP = eINSTANCE.getResourceMap();

		/**
		 * The meta object literal for the '<em><b>Locators</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__LOCATORS = eINSTANCE.getResourceMap_Locators();

		/**
		 * The meta object literal for the '<em><b>Redirects</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__REDIRECTS = eINSTANCE.getResourceMap_Redirects();

		/**
		 * The meta object literal for the '<em><b>Search Paths</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__SEARCH_PATHS = eINSTANCE.getResourceMap_SearchPaths();

		/**
		 * The meta object literal for the '<em><b>Property Elements</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__PROPERTY_ELEMENTS = eINSTANCE.getResourceMap_PropertyElements();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__PROPERTIES = eINSTANCE.getResourceMap_Properties();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference RESOURCE_MAP__DOCUMENTATION = eINSTANCE.getResourceMap_Documentation();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.ProviderImpl <em>Provider</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.ProviderImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getProvider()
		 * @generated
		 */
		EClass PROVIDER = eINSTANCE.getProvider();

		/**
		 * The meta object literal for the '<em><b>Component Types</b></em>' attribute list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__COMPONENT_TYPES = eINSTANCE.getProvider_ComponentTypes();

		/**
		 * The meta object literal for the '<em><b>Component Types Attr</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__COMPONENT_TYPES_ATTR = eINSTANCE.getProvider_ComponentTypesAttr();

		/**
		 * The meta object literal for the '<em><b>Reader Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__READER_TYPE = eINSTANCE.getProvider_ReaderType();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__SOURCE = eINSTANCE.getProvider_Source();

		/**
		 * The meta object literal for the '<em><b>Mutable</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__MUTABLE = eINSTANCE.getProvider_Mutable();

		/**
		 * The meta object literal for the '<em><b>Resolution Filter</b></em>' attribute feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute PROVIDER__RESOLUTION_FILTER = eINSTANCE.getProvider_ResolutionFilter();

		/**
		 * The meta object literal for the '<em><b>Version Converter</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROVIDER__VERSION_CONVERTER = eINSTANCE.getProvider_VersionConverter();

		/**
		 * The meta object literal for the '<em><b>Uri</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROVIDER__URI = eINSTANCE.getProvider_Uri();

		/**
		 * The meta object literal for the '<em><b>Matchers</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROVIDER__MATCHERS = eINSTANCE.getProvider_Matchers();

		/**
		 * The meta object literal for the '<em><b>Documentation</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROVIDER__DOCUMENTATION = eINSTANCE.getProvider_Documentation();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.TransformImpl <em>Transform</em>}'
		 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.TransformImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getTransform()
		 * @generated
		 */
		EClass TRANSFORM = eINSTANCE.getTransform();

		/**
		 * The meta object literal for the '<em><b>From Pattern</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSFORM__FROM_PATTERN = eINSTANCE.getTransform_FromPattern();

		/**
		 * The meta object literal for the '<em><b>From Replacement</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSFORM__FROM_REPLACEMENT = eINSTANCE.getTransform_FromReplacement();

		/**
		 * The meta object literal for the '<em><b>To Pattern</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSFORM__TO_PATTERN = eINSTANCE.getTransform_ToPattern();

		/**
		 * The meta object literal for the '<em><b>To Replacement</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute TRANSFORM__TO_REPLACEMENT = eINSTANCE.getTransform_ToReplacement();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl
		 * <em>Version Converter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.VersionConverterImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getVersionConverter()
		 * @generated
		 */
		EClass VERSION_CONVERTER = eINSTANCE.getVersionConverter();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSION_CONVERTER__TYPE = eINSTANCE.getVersionConverter_Type();

		/**
		 * The meta object literal for the '<em><b>Version Type</b></em>' attribute feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute VERSION_CONVERTER__VERSION_TYPE = eINSTANCE.getVersionConverter_VersionType();

		/**
		 * The meta object literal for the '<em><b>Transformers</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference VERSION_CONVERTER__TRANSFORMERS = eINSTANCE.getVersionConverter_Transformers();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.DocumentRootImpl
		 * <em>Document Root</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.DocumentRootImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getDocumentRoot()
		 * @generated
		 */
		EClass DOCUMENT_ROOT = eINSTANCE.getDocumentRoot();

		/**
		 * The meta object literal for the '<em><b>Mixed</b></em>' attribute list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute DOCUMENT_ROOT__MIXED = eINSTANCE.getDocumentRoot_Mixed();

		/**
		 * The meta object literal for the '<em><b>XMLNS Prefix Map</b></em>' map feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XMLNS_PREFIX_MAP = eINSTANCE.getDocumentRoot_XMLNSPrefixMap();

		/**
		 * The meta object literal for the '<em><b>XSI Schema Location</b></em>' map feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = eINSTANCE.getDocumentRoot_XSISchemaLocation();

		/**
		 * The meta object literal for the '<em><b>Arch</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__ARCH = eINSTANCE.getDocumentRoot_Arch();

		/**
		 * The meta object literal for the '<em><b>Group</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__GROUP = eINSTANCE.getDocumentRoot_Group();

		/**
		 * The meta object literal for the '<em><b>Match</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__MATCH = eINSTANCE.getDocumentRoot_Match();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NAME = eINSTANCE.getDocumentRoot_Name();

		/**
		 * The meta object literal for the '<em><b>Nl</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__NL = eINSTANCE.getDocumentRoot_Nl();

		/**
		 * The meta object literal for the '<em><b>Os</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__OS = eINSTANCE.getDocumentRoot_Os();

		/**
		 * The meta object literal for the '<em><b>Timestamp</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__TIMESTAMP = eINSTANCE.getDocumentRoot_Timestamp();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__VERSION = eINSTANCE.getDocumentRoot_Version();

		/**
		 * The meta object literal for the '<em><b>Revision</b></em>' containment reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__REVISION = eINSTANCE.getDocumentRoot_Revision();

		/**
		 * The meta object literal for the '<em><b>Ws</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__WS = eINSTANCE.getDocumentRoot_Ws();

		/**
		 * The meta object literal for the '<em><b>Rmap</b></em>' containment reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DOCUMENT_ROOT__RMAP = eINSTANCE.getDocumentRoot_Rmap();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl
		 * <em>URI Matcher</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.rmap.impl.URIMatcherImpl
		 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getURIMatcher()
		 * @generated
		 */
		EClass URI_MATCHER = eINSTANCE.getURIMatcher();

		/**
		 * The meta object literal for the '<em><b>Base</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute URI_MATCHER__BASE = eINSTANCE.getURIMatcher_Base();

		/**
		 * The meta object literal for the '<em><b>Rx Parts Group</b></em>' attribute list feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute URI_MATCHER__RX_PARTS_GROUP = eINSTANCE.getURIMatcher_RxPartsGroup();

		/**
		 * The meta object literal for the '<em><b>Rx Parts</b></em>' containment reference list feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference URI_MATCHER__RX_PARTS = eINSTANCE.getURIMatcher_RxParts();

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
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	RmapPackage eINSTANCE = org.eclipse.buckminster.rmap.impl.RmapPackageImpl.init();

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.MatcherImpl <em>Matcher</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.MatcherImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getMatcher()
	 * @generated
	 */
	int MATCHER = 0;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCHER__PATTERN = 0;

	/**
	 * The number of structural features of the '<em>Matcher</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MATCHER_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.RedirectImpl <em>Redirect</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.RedirectImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getRedirect()
	 * @generated
	 */
	int REDIRECT = 1;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT__PATTERN = MATCHER__PATTERN;

	/**
	 * The feature id for the '<em><b>Href</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT__HREF = MATCHER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Redirect</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int REDIRECT_FEATURE_COUNT = MATCHER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.LocatorImpl <em>Locator</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.LocatorImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getLocator()
	 * @generated
	 */
	int LOCATOR = 2;

	/**
	 * The feature id for the '<em><b>Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__PATTERN = MATCHER__PATTERN;

	/**
	 * The feature id for the '<em><b>Search Path</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__SEARCH_PATH = MATCHER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Fail On Error</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR__FAIL_ON_ERROR = MATCHER_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Locator</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int LOCATOR_FEATURE_COUNT = MATCHER_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.SearchPathImpl <em>Search Path</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.SearchPathImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getSearchPath()
	 * @generated
	 */
	int SEARCH_PATH = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEARCH_PATH__NAME = 0;

	/**
	 * The feature id for the '<em><b>Providers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEARCH_PATH__PROVIDERS = 1;

	/**
	 * The number of structural features of the '<em>Search Path</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int SEARCH_PATH_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.ResourceMapImpl <em>Resource Map</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.ResourceMapImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getResourceMap()
	 * @generated
	 */
	int RESOURCE_MAP = 4;

	/**
	 * The feature id for the '<em><b>Locators</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__LOCATORS = 0;

	/**
	 * The feature id for the '<em><b>Redirects</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__REDIRECTS = 1;

	/**
	 * The feature id for the '<em><b>Search Paths</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__SEARCH_PATHS = 2;

	/**
	 * The feature id for the '<em><b>Property Elements</b></em>' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__PROPERTY_ELEMENTS = 3;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__PROPERTIES = 4;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP__DOCUMENTATION = 5;

	/**
	 * The number of structural features of the '<em>Resource Map</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int RESOURCE_MAP_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.ProviderImpl <em>Provider</em>}' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.ProviderImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getProvider()
	 * @generated
	 */
	int PROVIDER = 5;

	/**
	 * The feature id for the '<em><b>Component Types</b></em>' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__COMPONENT_TYPES = 0;

	/**
	 * The feature id for the '<em><b>Component Types Attr</b></em>' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__COMPONENT_TYPES_ATTR = 1;

	/**
	 * The feature id for the '<em><b>Reader Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__READER_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__SOURCE = 3;

	/**
	 * The feature id for the '<em><b>Mutable</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__MUTABLE = 4;

	/**
	 * The feature id for the '<em><b>Resolution Filter</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__RESOLUTION_FILTER = 5;

	/**
	 * The feature id for the '<em><b>Version Converter</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__VERSION_CONVERTER = 6;

	/**
	 * The feature id for the '<em><b>Uri</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__URI = 7;

	/**
	 * The feature id for the '<em><b>Matchers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__MATCHERS = 8;

	/**
	 * The feature id for the '<em><b>Documentation</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER__DOCUMENTATION = 9;

	/**
	 * The number of structural features of the '<em>Provider</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROVIDER_FEATURE_COUNT = 10;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.TransformImpl <em>Transform</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.TransformImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getTransform()
	 * @generated
	 */
	int TRANSFORM = 6;

	/**
	 * The feature id for the '<em><b>From Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM__FROM_PATTERN = 0;

	/**
	 * The feature id for the '<em><b>From Replacement</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM__FROM_REPLACEMENT = 1;

	/**
	 * The feature id for the '<em><b>To Pattern</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM__TO_PATTERN = 2;

	/**
	 * The feature id for the '<em><b>To Replacement</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM__TO_REPLACEMENT = 3;

	/**
	 * The number of structural features of the '<em>Transform</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int TRANSFORM_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.VersionConverterImpl
	 * <em>Version Converter</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.VersionConverterImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getVersionConverter()
	 * @generated
	 */
	int VERSION_CONVERTER = 7;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER__TYPE = 0;

	/**
	 * The feature id for the '<em><b>Version Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER__VERSION_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Transformers</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER__TRANSFORMERS = 2;

	/**
	 * The number of structural features of the '<em>Version Converter</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int VERSION_CONVERTER_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.DocumentRootImpl <em>Document Root</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.DocumentRootImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getDocumentRoot()
	 * @generated
	 */
	int DOCUMENT_ROOT = 8;

	/**
	 * The feature id for the '<em><b>Mixed</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MIXED = 0;

	/**
	 * The feature id for the '<em><b>XMLNS Prefix Map</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XMLNS_PREFIX_MAP = 1;

	/**
	 * The feature id for the '<em><b>XSI Schema Location</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__XSI_SCHEMA_LOCATION = 2;

	/**
	 * The feature id for the '<em><b>Match</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__MATCH = 3;

	/**
	 * The feature id for the '<em><b>Group</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__GROUP = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NAME = 5;

	/**
	 * The feature id for the '<em><b>Version</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__VERSION = 6;

	/**
	 * The feature id for the '<em><b>Revision</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__REVISION = 7;

	/**
	 * The feature id for the '<em><b>Timestamp</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__TIMESTAMP = 8;

	/**
	 * The feature id for the '<em><b>Os</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__OS = 9;

	/**
	 * The feature id for the '<em><b>Ws</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__WS = 10;

	/**
	 * The feature id for the '<em><b>Arch</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__ARCH = 11;

	/**
	 * The feature id for the '<em><b>Nl</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__NL = 12;

	/**
	 * The feature id for the '<em><b>Rmap</b></em>' containment reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT__RMAP = 13;

	/**
	 * The number of structural features of the '<em>Document Root</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_ROOT_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl <em>URI Matcher</em>}' class.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.rmap.impl.URIMatcherImpl
	 * @see org.eclipse.buckminster.rmap.impl.RmapPackageImpl#getURIMatcher()
	 * @generated
	 */
	int URI_MATCHER = 9;

	/**
	 * The feature id for the '<em><b>Base</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__BASE = 0;

	/**
	 * The feature id for the '<em><b>Rx Parts Group</b></em>' attribute list. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__RX_PARTS_GROUP = 1;

	/**
	 * The feature id for the '<em><b>Rx Parts</b></em>' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER__RX_PARTS = 2;

	/**
	 * The number of structural features of the '<em>URI Matcher</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int URI_MATCHER_FEATURE_COUNT = 3;

	/**
	 * Returns the meta object for class '{@link org.eclipse.emf.ecore.EObject <em>Document Root</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Document Root</em>'.
	 * @see org.eclipse.emf.ecore.EObject
	 * @model extendedMetaData="name='' kind='mixed'"
	 * @generated
	 */
	EClass getDocumentRoot();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getArch
	 * <em>Arch</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Arch</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getArch()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Arch();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getGroup
	 * <em>Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Group</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getGroup()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Group();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getMatch
	 * <em>Match</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Match</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getMatch()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Match();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.emf.ecore.EObject#getMixed <em>Mixed</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Mixed</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getMixed()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EAttribute getDocumentRoot_Mixed();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getName
	 * <em>Name</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Name</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getName()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Name();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getNl <em>Nl</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Nl</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getNl()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Nl();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getOs <em>Os</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Os</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getOs()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Os();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getRevision
	 * <em>Revision</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Revision</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getRevision()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Revision();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getRmap
	 * <em>Rmap</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Rmap</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getRmap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Rmap();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getTimestamp
	 * <em>Timestamp</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Timestamp</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getTimestamp()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Timestamp();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getVersion
	 * <em>Version</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Version</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getVersion()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Version();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.EObject#getWs <em>Ws</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Ws</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getWs()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_Ws();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.emf.ecore.EObject#getXMLNSPrefixMap
	 * <em>XMLNS Prefix Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XMLNS Prefix Map</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getXMLNSPrefixMap()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XMLNSPrefixMap();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.emf.ecore.EObject#getXSISchemaLocation
	 * <em>XSI Schema Location</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>XSI Schema Location</em>'.
	 * @see org.eclipse.emf.ecore.EObject#getXSISchemaLocation()
	 * @see #getDocumentRoot()
	 * @generated
	 */
	EReference getDocumentRoot_XSISchemaLocation();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.Locator <em>Locator</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Locator</em>'.
	 * @see org.eclipse.buckminster.rmap.Locator
	 * @generated
	 */
	EClass getLocator();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Locator#isFailOnError
	 * <em>Fail On Error</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Fail On Error</em>'.
	 * @see org.eclipse.buckminster.rmap.Locator#isFailOnError()
	 * @see #getLocator()
	 * @generated
	 */
	EAttribute getLocator_FailOnError();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.rmap.Locator#getSearchPath
	 * <em>Search Path</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Search Path</em>'.
	 * @see org.eclipse.buckminster.rmap.Locator#getSearchPath()
	 * @see #getLocator()
	 * @generated
	 */
	EReference getLocator_SearchPath();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.Matcher <em>Matcher</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Matcher</em>'.
	 * @see org.eclipse.buckminster.rmap.Matcher
	 * @generated
	 */
	EClass getMatcher();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Matcher#getPattern
	 * <em>Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Pattern</em>'.
	 * @see org.eclipse.buckminster.rmap.Matcher#getPattern()
	 * @see #getMatcher()
	 * @generated
	 */
	EAttribute getMatcher_Pattern();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.Provider <em>Provider</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Provider</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider
	 * @generated
	 */
	EClass getProvider();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.buckminster.rmap.Provider#getComponentTypes
	 * <em>Component Types</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Component Types</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getComponentTypes()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_ComponentTypes();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Provider#getComponentTypesAttr
	 * <em>Component Types Attr</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Component Types Attr</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getComponentTypesAttr()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_ComponentTypesAttr();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.Provider#getDocumentation <em>Documentation</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getDocumentation()
	 * @see #getProvider()
	 * @generated
	 */
	EReference getProvider_Documentation();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.Provider#getMatchers <em>Matchers</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Matchers</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getMatchers()
	 * @see #getProvider()
	 * @generated
	 */
	EReference getProvider_Matchers();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Provider#isMutable
	 * <em>Mutable</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Mutable</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#isMutable()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_Mutable();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Provider#getReaderType
	 * <em>Reader Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Reader Type</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getReaderType()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_ReaderType();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Provider#getResolutionFilter
	 * <em>Resolution Filter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Resolution Filter</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getResolutionFilter()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_ResolutionFilter();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Provider#isSource <em>Source</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Source</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#isSource()
	 * @see #getProvider()
	 * @generated
	 */
	EAttribute getProvider_Source();

	/**
	 * Returns the meta object for the containment reference '{@link org.eclipse.buckminster.rmap.Provider#getUri
	 * <em>Uri</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Uri</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getUri()
	 * @see #getProvider()
	 * @generated
	 */
	EReference getProvider_Uri();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.Provider#getVersionConverter <em>Version Converter</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Version Converter</em>'.
	 * @see org.eclipse.buckminster.rmap.Provider#getVersionConverter()
	 * @see #getProvider()
	 * @generated
	 */
	EReference getProvider_VersionConverter();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.Redirect <em>Redirect</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Redirect</em>'.
	 * @see org.eclipse.buckminster.rmap.Redirect
	 * @generated
	 */
	EClass getRedirect();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Redirect#getHref <em>Href</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Href</em>'.
	 * @see org.eclipse.buckminster.rmap.Redirect#getHref()
	 * @see #getRedirect()
	 * @generated
	 */
	EAttribute getRedirect_Href();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.ResourceMap <em>Resource Map</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Resource Map</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap
	 * @generated
	 */
	EClass getResourceMap();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getDocumentation <em>Documentation</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Documentation</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getDocumentation()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_Documentation();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getLocators <em>Locators</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Locators</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getLocators()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_Locators();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getProperties <em>Properties</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Properties</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getProperties()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_Properties();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getPropertyElements <em>Property Elements</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Property Elements</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getPropertyElements()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_PropertyElements();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getRedirects <em>Redirects</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Redirects</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getRedirects()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_Redirects();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.ResourceMap#getSearchPaths <em>Search Paths</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Search Paths</em>'.
	 * @see org.eclipse.buckminster.rmap.ResourceMap#getSearchPaths()
	 * @see #getResourceMap()
	 * @generated
	 */
	EReference getResourceMap_SearchPaths();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RmapFactory getRmapFactory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.SearchPath <em>Search Path</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Search Path</em>'.
	 * @see org.eclipse.buckminster.rmap.SearchPath
	 * @generated
	 */
	EClass getSearchPath();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.SearchPath#getName <em>Name</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.rmap.SearchPath#getName()
	 * @see #getSearchPath()
	 * @generated
	 */
	EAttribute getSearchPath_Name();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.SearchPath#getProviders <em>Providers</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Providers</em>'.
	 * @see org.eclipse.buckminster.rmap.SearchPath#getProviders()
	 * @see #getSearchPath()
	 * @generated
	 */
	EReference getSearchPath_Providers();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.Transform <em>Transform</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Transform</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform
	 * @generated
	 */
	EClass getTransform();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Transform#getFromPattern
	 * <em>From Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>From Pattern</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform#getFromPattern()
	 * @see #getTransform()
	 * @generated
	 */
	EAttribute getTransform_FromPattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Transform#getFromReplacement
	 * <em>From Replacement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>From Replacement</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform#getFromReplacement()
	 * @see #getTransform()
	 * @generated
	 */
	EAttribute getTransform_FromReplacement();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Transform#getToPattern
	 * <em>To Pattern</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>To Pattern</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform#getToPattern()
	 * @see #getTransform()
	 * @generated
	 */
	EAttribute getTransform_ToPattern();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.Transform#getToReplacement
	 * <em>To Replacement</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>To Replacement</em>'.
	 * @see org.eclipse.buckminster.rmap.Transform#getToReplacement()
	 * @see #getTransform()
	 * @generated
	 */
	EAttribute getTransform_ToReplacement();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.URIMatcher <em>URI Matcher</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>URI Matcher</em>'.
	 * @see org.eclipse.buckminster.rmap.URIMatcher
	 * @generated
	 */
	EClass getURIMatcher();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.URIMatcher#getBase <em>Base</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Base</em>'.
	 * @see org.eclipse.buckminster.rmap.URIMatcher#getBase()
	 * @see #getURIMatcher()
	 * @generated
	 */
	EAttribute getURIMatcher_Base();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.URIMatcher#getRxParts <em>Rx Parts</em>}'. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Rx Parts</em>'.
	 * @see org.eclipse.buckminster.rmap.URIMatcher#getRxParts()
	 * @see #getURIMatcher()
	 * @generated
	 */
	EReference getURIMatcher_RxParts();

	/**
	 * Returns the meta object for the attribute list '{@link org.eclipse.buckminster.rmap.URIMatcher#getRxPartsGroup
	 * <em>Rx Parts Group</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute list '<em>Rx Parts Group</em>'.
	 * @see org.eclipse.buckminster.rmap.URIMatcher#getRxPartsGroup()
	 * @see #getURIMatcher()
	 * @generated
	 */
	EAttribute getURIMatcher_RxPartsGroup();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.rmap.VersionConverter
	 * <em>Version Converter</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Version Converter</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter
	 * @generated
	 */
	EClass getVersionConverter();

	/**
	 * Returns the meta object for the containment reference list '
	 * {@link org.eclipse.buckminster.rmap.VersionConverter#getTransformers <em>Transformers</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference list '<em>Transformers</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter#getTransformers()
	 * @see #getVersionConverter()
	 * @generated
	 */
	EReference getVersionConverter_Transformers();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.VersionConverter#getType
	 * <em>Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter#getType()
	 * @see #getVersionConverter()
	 * @generated
	 */
	EAttribute getVersionConverter_Type();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.rmap.VersionConverter#getVersionType
	 * <em>Version Type</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version Type</em>'.
	 * @see org.eclipse.buckminster.rmap.VersionConverter#getVersionType()
	 * @see #getVersionConverter()
	 * @generated
	 */
	EAttribute getVersionConverter_VersionType();

} // RmapPackage
