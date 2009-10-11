/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Matcher;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.Redirect;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.SearchPath;
import org.eclipse.buckminster.rmap.Transform;
import org.eclipse.buckminster.rmap.URIMatcher;
import org.eclipse.buckminster.rmap.VersionConverter;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class RmapPackageImpl extends EPackageImpl implements RmapPackage
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass matcherEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass redirectEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass locatorEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass searchPathEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass resourceMapEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass providerEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass transformEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass versionConverterEClass = null;

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
	private EClass uriMatcherEClass = null;

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
	 * This method is used to initialize {@link RmapPackage#eINSTANCE} when that field is accessed. Clients should not
	 * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RmapPackage init()
	{
		if(isInited)
			return (RmapPackage)EPackage.Registry.INSTANCE.getEPackage(RmapPackage.eNS_URI);

		// Obtain or create and register package
		RmapPackageImpl theRmapPackage = (RmapPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RmapPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new RmapPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		CommonPackage.eINSTANCE.eClass();
		XMLTypePackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theRmapPackage.createPackageContents();

		// Initialize created meta-data
		theRmapPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRmapPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RmapPackage.eNS_URI, theRmapPackage);
		return theRmapPackage;
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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RmapPackageImpl()
	{
		super(eNS_URI, RmapFactory.eINSTANCE);
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
		matcherEClass = createEClass(MATCHER);
		createEAttribute(matcherEClass, MATCHER__PATTERN);

		redirectEClass = createEClass(REDIRECT);
		createEAttribute(redirectEClass, REDIRECT__HREF);

		locatorEClass = createEClass(LOCATOR);
		createEReference(locatorEClass, LOCATOR__SEARCH_PATH);
		createEAttribute(locatorEClass, LOCATOR__FAIL_ON_ERROR);

		searchPathEClass = createEClass(SEARCH_PATH);
		createEAttribute(searchPathEClass, SEARCH_PATH__NAME);
		createEReference(searchPathEClass, SEARCH_PATH__PROVIDERS);

		resourceMapEClass = createEClass(RESOURCE_MAP);
		createEReference(resourceMapEClass, RESOURCE_MAP__LOCATORS);
		createEReference(resourceMapEClass, RESOURCE_MAP__REDIRECTS);
		createEReference(resourceMapEClass, RESOURCE_MAP__SEARCH_PATHS);
		createEReference(resourceMapEClass, RESOURCE_MAP__PROPERTY_ELEMENTS);
		createEReference(resourceMapEClass, RESOURCE_MAP__PROPERTIES);
		createEReference(resourceMapEClass, RESOURCE_MAP__DOCUMENTATION);

		providerEClass = createEClass(PROVIDER);
		createEAttribute(providerEClass, PROVIDER__COMPONENT_TYPES);
		createEAttribute(providerEClass, PROVIDER__COMPONENT_TYPES_ATTR);
		createEAttribute(providerEClass, PROVIDER__READER_TYPE);
		createEAttribute(providerEClass, PROVIDER__SOURCE);
		createEAttribute(providerEClass, PROVIDER__MUTABLE);
		createEAttribute(providerEClass, PROVIDER__RESOLUTION_FILTER);
		createEReference(providerEClass, PROVIDER__VERSION_CONVERTER);
		createEReference(providerEClass, PROVIDER__URI);
		createEReference(providerEClass, PROVIDER__MATCHERS);
		createEReference(providerEClass, PROVIDER__DOCUMENTATION);

		transformEClass = createEClass(TRANSFORM);
		createEAttribute(transformEClass, TRANSFORM__FROM_PATTERN);
		createEAttribute(transformEClass, TRANSFORM__FROM_REPLACEMENT);
		createEAttribute(transformEClass, TRANSFORM__TO_PATTERN);
		createEAttribute(transformEClass, TRANSFORM__TO_REPLACEMENT);

		versionConverterEClass = createEClass(VERSION_CONVERTER);
		createEAttribute(versionConverterEClass, VERSION_CONVERTER__TYPE);
		createEAttribute(versionConverterEClass, VERSION_CONVERTER__VERSION_TYPE);
		createEReference(versionConverterEClass, VERSION_CONVERTER__TRANSFORMERS);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MATCH);
		createEReference(documentRootEClass, DOCUMENT_ROOT__GROUP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__NAME);
		createEReference(documentRootEClass, DOCUMENT_ROOT__VERSION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__REVISION);
		createEReference(documentRootEClass, DOCUMENT_ROOT__TIMESTAMP);
		createEReference(documentRootEClass, DOCUMENT_ROOT__OS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__WS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__ARCH);
		createEReference(documentRootEClass, DOCUMENT_ROOT__NL);
		createEReference(documentRootEClass, DOCUMENT_ROOT__RMAP);

		uriMatcherEClass = createEClass(URI_MATCHER);
		createEAttribute(uriMatcherEClass, URI_MATCHER__BASE);
		createEAttribute(uriMatcherEClass, URI_MATCHER__RX_PARTS_GROUP);
		createEReference(uriMatcherEClass, URI_MATCHER__RX_PARTS);
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
	public EReference getDocumentRoot_Arch()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Group()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Match()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(3);
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
	public EReference getDocumentRoot_Name()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Nl()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Os()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Revision()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Rmap()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Timestamp()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Version()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Ws()
	{
		return (EReference)documentRootEClass.getEStructuralFeatures().get(10);
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
	public EClass getLocator()
	{
		return locatorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getLocator_FailOnError()
	{
		return (EAttribute)locatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getLocator_SearchPath()
	{
		return (EReference)locatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMatcher()
	{
		return matcherEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatcher_Pattern()
	{
		return (EAttribute)matcherEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProvider()
	{
		return providerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProvider_ComponentTypes()
	{
		return (EAttribute)providerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProvider_ComponentTypesAttr()
	{
		return (EAttribute)providerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProvider_Documentation()
	{
		return (EReference)providerEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProvider_Matchers()
	{
		return (EReference)providerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProvider_Mutable()
	{
		return (EAttribute)providerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProvider_ReaderType()
	{
		return (EAttribute)providerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProvider_ResolutionFilter()
	{
		return (EAttribute)providerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProvider_Source()
	{
		return (EAttribute)providerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProvider_Uri()
	{
		return (EReference)providerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProvider_VersionConverter()
	{
		return (EReference)providerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRedirect()
	{
		return redirectEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRedirect_Href()
	{
		return (EAttribute)redirectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getResourceMap()
	{
		return resourceMapEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceMap_Documentation()
	{
		return (EReference)resourceMapEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceMap_Locators()
	{
		return (EReference)resourceMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceMap_Properties()
	{
		return (EReference)resourceMapEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceMap_PropertyElements()
	{
		return (EReference)resourceMapEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceMap_Redirects()
	{
		return (EReference)resourceMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getResourceMap_SearchPaths()
	{
		return (EReference)resourceMapEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public RmapFactory getRmapFactory()
	{
		return (RmapFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getSearchPath()
	{
		return searchPathEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getSearchPath_Name()
	{
		return (EAttribute)searchPathEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getSearchPath_Providers()
	{
		return (EReference)searchPathEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getTransform()
	{
		return transformEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getTransform_FromPattern()
	{
		return (EAttribute)transformEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getTransform_FromReplacement()
	{
		return (EAttribute)transformEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getTransform_ToPattern()
	{
		return (EAttribute)transformEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getTransform_ToReplacement()
	{
		return (EAttribute)transformEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getURIMatcher()
	{
		return uriMatcherEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getURIMatcher_Base()
	{
		return (EAttribute)uriMatcherEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getURIMatcher_RxParts()
	{
		return (EReference)uriMatcherEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getURIMatcher_RxPartsGroup()
	{
		return (EAttribute)uriMatcherEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getVersionConverter()
	{
		return versionConverterEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getVersionConverter_Transformers()
	{
		return (EReference)versionConverterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getVersionConverter_Type()
	{
		return (EAttribute)versionConverterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getVersionConverter_VersionType()
	{
		return (EAttribute)versionConverterEClass.getEStructuralFeatures().get(1);
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
		XMLTypePackage theXMLTypePackage = (XMLTypePackage)EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		redirectEClass.getESuperTypes().add(this.getMatcher());
		locatorEClass.getESuperTypes().add(this.getMatcher());

		// Initialize classes and features; add operations and parameters
		initEClass(matcherEClass, Matcher.class, "Matcher", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatcher_Pattern(), theCommonPackage.getPattern(), "pattern", null, 0, 1, Matcher.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(redirectEClass, Redirect.class, "Redirect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRedirect_Href(), ecorePackage.getEString(), "href", null, 1, 1, Redirect.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(locatorEClass, Locator.class, "Locator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLocator_SearchPath(), this.getSearchPath(), null, "searchPath", null, 1, 1, Locator.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocator_FailOnError(), ecorePackage.getEBoolean(), "failOnError", "true", 0, 1,
				Locator.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(searchPathEClass, SearchPath.class, "SearchPath", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSearchPath_Name(), theXMLTypePackage.getID(), "name", null, 1, 1, SearchPath.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSearchPath_Providers(), this.getProvider(), null, "providers", null, 0, -1, SearchPath.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceMapEClass, ResourceMap.class, "ResourceMap", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceMap_Locators(), this.getLocator(), null, "locators", null, 0, -1, ResourceMap.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceMap_Redirects(), this.getRedirect(), null, "redirects", null, 0, -1,
				ResourceMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceMap_SearchPaths(), this.getSearchPath(), null, "searchPaths", null, 0, -1,
				ResourceMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceMap_PropertyElements(), theCommonPackage.getPropertyElement(), null,
				"propertyElements", null, 0, -1, ResourceMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceMap_Properties(), theCommonPackage.getPropertyConstant(), null, "properties", null,
				0, -1, ResourceMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceMap_Documentation(), theCommonPackage.getDocumentation(), null, "documentation",
				null, 0, 1, ResourceMap.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(providerEClass, Provider.class, "Provider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProvider_ComponentTypes(), ecorePackage.getEString(), "componentTypes", "", 0, -1,
				Provider.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_ComponentTypesAttr(), ecorePackage.getEString(), "componentTypesAttr", null, 0, 1,
				Provider.class, !IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_ReaderType(), ecorePackage.getEString(), "readerType", null, 1, 1, Provider.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_Source(), ecorePackage.getEBoolean(), "source", "true", 0, 1, Provider.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_Mutable(), ecorePackage.getEBoolean(), "mutable", "true", 0, 1, Provider.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_ResolutionFilter(), ecorePackage.getEString(), "resolutionFilter", null, 0, 1,
				Provider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getProvider_VersionConverter(), this.getVersionConverter(), null, "versionConverter", null, 0,
				1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvider_Uri(), theCommonPackage.getFormat(), null, "uri", null, 1, 1, Provider.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvider_Matchers(), this.getURIMatcher(), null, "matchers", null, 0, -1, Provider.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvider_Documentation(), theCommonPackage.getDocumentation(), null, "documentation", null,
				0, 1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformEClass, Transform.class, "Transform", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransform_FromPattern(), theCommonPackage.getPattern(), "fromPattern", null, 1, 1,
				Transform.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransform_FromReplacement(), ecorePackage.getEString(), "fromReplacement", null, 1, 1,
				Transform.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransform_ToPattern(), theCommonPackage.getPattern(), "toPattern", null, 1, 1,
				Transform.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransform_ToReplacement(), ecorePackage.getEString(), "toReplacement", null, 1, 1,
				Transform.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(versionConverterEClass, VersionConverter.class, "VersionConverter", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVersionConverter_Type(), ecorePackage.getEString(), "type", null, 1, 1,
				VersionConverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getVersionConverter_VersionType(), ecorePackage.getEString(), "versionType", "OSGi", 0, 1,
				VersionConverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getVersionConverter_Transformers(), this.getTransform(), null, "transformers", null, 0, -1,
				VersionConverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, null, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null,
				"xMLNSPrefixMap", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null,
				"xSISchemaLocation", null, 0, -1, null, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Match(), theCommonPackage.getRxPattern(), null, "match", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Group(), theCommonPackage.getRxGroup(), null, "group", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Name(), theCommonPackage.getRxPattern(), null, "name", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Version(), theCommonPackage.getRxPattern(), null, "version", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Revision(), theCommonPackage.getRxPattern(), null, "revision", null, 0, -2,
				null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Timestamp(), theCommonPackage.getRxPattern(), null, "timestamp", null, 0, -2,
				null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Os(), theCommonPackage.getRxPattern(), null, "os", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Ws(), theCommonPackage.getRxPattern(), null, "ws", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Arch(), theCommonPackage.getRxPattern(), null, "arch", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Nl(), theCommonPackage.getRxPattern(), null, "nl", null, 0, -2, null,
				IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE,
				IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Rmap(), this.getResourceMap(), null, "rmap", null, 1, 1, null, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED,
				IS_ORDERED);

		initEClass(uriMatcherEClass, URIMatcher.class, "URIMatcher", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getURIMatcher_Base(), ecorePackage.getEString(), "base", null, 0, 1, URIMatcher.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getURIMatcher_RxPartsGroup(), ecorePackage.getEFeatureMapEntry(), "rxPartsGroup", null, 1, -1,
				URIMatcher.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, !IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEReference(getURIMatcher_RxParts(), theCommonPackage.getRxPart(), null, "rxParts", null, 1, -1,
				URIMatcher.class, IS_TRANSIENT, IS_VOLATILE, !IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

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
		addAnnotation(getLocator_SearchPath(), source, new String[] { "name", "searchPathRef", "kind", "attribute" });
		addAnnotation(getSearchPath_Providers(), source, new String[] { "name", "provider", "namespace",
				"##targetNamespace", "kind", "element" });
		addAnnotation(resourceMapEClass, source, new String[] { "name", "ResourceMap" });
		addAnnotation(getResourceMap_Locators(), source, new String[] { "name", "locator", "namespace",
				"##targetNamespace", "kind", "element" });
		addAnnotation(getResourceMap_Redirects(), source, new String[] { "name", "redirect", "namespace",
				"##targetNamespace", "kind", "element" });
		addAnnotation(getResourceMap_SearchPaths(), source, new String[] { "name", "searchPath", "namespace",
				"##targetNamespace", "kind", "element" });
		addAnnotation(getResourceMap_PropertyElements(), source, new String[] { "name", "propertyElement", "namespace",
				"##targetNamespace", "kind", "element" });
		addAnnotation(getResourceMap_Properties(), source, new String[] { "name", "property", "namespace",
				"##targetNamespace", "kind", "element" });
		addAnnotation(getResourceMap_Documentation(), source, new String[] { "name", "documentation", "kind",
				"element", "namespace", "##targetNamespace" });
		addAnnotation(getProvider_ComponentTypesAttr(), source, new String[] { "name", "componentTypes", "kind",
				"attribute" });
		addAnnotation(getProvider_VersionConverter(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element" });
		addAnnotation(getProvider_Uri(), source, new String[] { "namespace", "##targetNamespace", "kind", "element" });
		addAnnotation(getProvider_Matchers(), source, new String[] { "name", "matcher", "kind", "element", "namespace",
				"##targetNamespace" });
		addAnnotation(getProvider_Documentation(), source, new String[] { "name", "documentation", "kind", "element",
				"namespace", "##targetNamespace" });
		addAnnotation(getVersionConverter_Transformers(), source, new String[] { "name", "transform", "namespace",
				"##targetNamespace", "kind", "element" });
		addAnnotation(documentRootEClass, source, new String[] { "name", "", "kind", "mixed" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed" });
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name",
				"xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name",
				"xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Match(), source, new String[] { "kind", "element", "name", "match", "namespace",
				"##targetNamespace", "affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Group(), source, new String[] { "kind", "element", "name", "group", "namespace",
				"##targetNamespace", "affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Name(), source, new String[] { "kind", "element", "name", "name", "namespace",
				"##targetNamespace", "affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Version(), source,
				new String[] { "kind", "element", "name", "version", "namespace", "##targetNamespace", "affiliation",
						"http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Revision(), source,
				new String[] { "kind", "element", "name", "revision", "namespace", "##targetNamespace", "affiliation",
						"http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Timestamp(), source,
				new String[] { "kind", "element", "name", "timestamp", "namespace", "##targetNamespace", "affiliation",
						"http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Os(), source, new String[] { "kind", "element", "name", "os", "namespace",
				"##targetNamespace", "affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Ws(), source, new String[] { "kind", "element", "name", "ws", "namespace",
				"##targetNamespace", "affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Arch(), source, new String[] { "kind", "element", "name", "arch", "namespace",
				"##targetNamespace", "affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Nl(), source, new String[] { "kind", "element", "name", "nl", "namespace",
				"##targetNamespace", "affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Rmap(), source, new String[] { "namespace", "##targetNamespace", "kind",
				"element", "name", "rmap" });
		addAnnotation(getURIMatcher_Base(), source, new String[] { "kind", "attribute" });
		addAnnotation(getURIMatcher_RxPartsGroup(), source, new String[] { "kind", "group", "name", "rxPart:group",
				"namespace", "http://www.eclipse.org/buckminster/Common-1.0" });
		addAnnotation(getURIMatcher_RxParts(), source, new String[] { "kind", "element", "name", "rxPart", "namespace",
				"http://www.eclipse.org/buckminster/Common-1.0", "group",
				"http://www.eclipse.org/buckminster/Common-1.0#rxPart:group" });
	}

} // RmapPackageImpl
