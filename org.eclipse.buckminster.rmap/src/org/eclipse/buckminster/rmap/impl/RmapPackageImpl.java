/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.rmap.BranchPoint;
import org.eclipse.buckminster.rmap.ConflictPolicy;
import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.Matcher;
import org.eclipse.buckminster.rmap.Provider;
import org.eclipse.buckminster.rmap.Redirect;
import org.eclipse.buckminster.rmap.Repository;
import org.eclipse.buckminster.rmap.ResourceMap;
import org.eclipse.buckminster.rmap.RmapFactory;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.SearchPath;
import org.eclipse.buckminster.rmap.Transform;
import org.eclipse.buckminster.rmap.URIMatcher;
import org.eclipse.buckminster.rmap.VersionConverter;
import org.eclipse.buckminster.rmap.VersionSelectorType;
import org.eclipse.buckminster.rmap.util.IComponentReader;
import org.eclipse.buckminster.rmap.util.TransformMismatchException;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class RmapPackageImpl extends EPackageImpl implements RmapPackage {
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
	private EClass repositoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass branchPointEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum versionSelectorTypeEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EEnum conflictPolicyEEnum = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType transformMismatchExceptionEDataType = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EDataType iComponentReaderEDataType = null;

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
	 * This method is used to initialize {@link RmapPackage#eINSTANCE} when that
	 * field is accessed. Clients should not invoke it directly. Instead, they
	 * should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RmapPackage init() {
		if (isInited)
			return (RmapPackage) EPackage.Registry.INSTANCE.getEPackage(RmapPackage.eNS_URI);

		// Obtain or create and register package
		RmapPackageImpl theRmapPackage = (RmapPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RmapPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI) : new RmapPackageImpl());

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
	 * @see org.eclipse.buckminster.rmap.RmapPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RmapPackageImpl() {
		super(eNS_URI, RmapFactory.eINSTANCE);
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
		branchPointEClass = createEClass(BRANCH_POINT);
		createEAttribute(branchPointEClass, BRANCH_POINT__NAME);
		createEAttribute(branchPointEClass, BRANCH_POINT__TIMESTAMP);
		createEAttribute(branchPointEClass, BRANCH_POINT__REVISION);
		createEAttribute(branchPointEClass, BRANCH_POINT__TAG);
		createEAttribute(branchPointEClass, BRANCH_POINT__ON_MERGE_CONFLICT);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
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
		createEReference(documentRootEClass, DOCUMENT_ROOT__LOCATORS);
		createEReference(documentRootEClass, DOCUMENT_ROOT__MATCHER);
		createEReference(documentRootEClass, DOCUMENT_ROOT__REDIRECTS);

		locatorEClass = createEClass(LOCATOR);
		createEReference(locatorEClass, LOCATOR__SEARCH_PATH);
		createEAttribute(locatorEClass, LOCATOR__FAIL_ON_ERROR);

		matcherEClass = createEClass(MATCHER);
		createEAttribute(matcherEClass, MATCHER__PATTERN);
		createEAttribute(matcherEClass, MATCHER__COMPONENT_TYPES_ATTR);
		createEAttribute(matcherEClass, MATCHER__COMPONENT_TYPES);
		createEAttribute(matcherEClass, MATCHER__RESOLUTION_FILTER);

		providerEClass = createEClass(PROVIDER);
		createEAttribute(providerEClass, PROVIDER__READER_TYPE);
		createEAttribute(providerEClass, PROVIDER__SOURCE);
		createEAttribute(providerEClass, PROVIDER__MUTABLE);
		createEReference(providerEClass, PROVIDER__VERSION_CONVERTER);
		createEReference(providerEClass, PROVIDER__URI);
		createEReference(providerEClass, PROVIDER__MATCHER);
		createEReference(providerEClass, PROVIDER__DOCUMENTATION);

		redirectEClass = createEClass(REDIRECT);
		createEAttribute(redirectEClass, REDIRECT__REDIRECT_TO);

		repositoryEClass = createEClass(REPOSITORY);
		createEAttribute(repositoryEClass, REPOSITORY__ID);
		createEReference(repositoryEClass, REPOSITORY__CONNECTION);
		createEAttribute(repositoryEClass, REPOSITORY__TYPE);
		createEReference(repositoryEClass, REPOSITORY__BRANCHES);
		createEAttribute(repositoryEClass, REPOSITORY__CHECKOUT);
		createEAttribute(repositoryEClass, REPOSITORY__ALLOW_DIRTY);
		createEAttribute(repositoryEClass, REPOSITORY__UPDATE);
		createEReference(repositoryEClass, REPOSITORY__DOCUMENTATION);

		resourceMapEClass = createEClass(RESOURCE_MAP);
		createEReference(resourceMapEClass, RESOURCE_MAP__DOCUMENTATION);
		createEAttribute(resourceMapEClass, RESOURCE_MAP__MATCHER_GROUP);
		createEReference(resourceMapEClass, RESOURCE_MAP__MATCHERS);
		createEReference(resourceMapEClass, RESOURCE_MAP__SEARCH_PATHS);

		searchPathEClass = createEClass(SEARCH_PATH);
		createEAttribute(searchPathEClass, SEARCH_PATH__NAME);
		createEReference(searchPathEClass, SEARCH_PATH__PROVIDERS);

		transformEClass = createEClass(TRANSFORM);
		createEAttribute(transformEClass, TRANSFORM__FROM_PATTERN);
		createEAttribute(transformEClass, TRANSFORM__FROM_REPLACEMENT);
		createEAttribute(transformEClass, TRANSFORM__TO_PATTERN);
		createEAttribute(transformEClass, TRANSFORM__TO_REPLACEMENT);

		uriMatcherEClass = createEClass(URI_MATCHER);
		createEAttribute(uriMatcherEClass, URI_MATCHER__BASE);
		createEAttribute(uriMatcherEClass, URI_MATCHER__VERSION_FORMAT);
		createEAttribute(uriMatcherEClass, URI_MATCHER__VERSION_TYPE);

		versionConverterEClass = createEClass(VERSION_CONVERTER);
		createEAttribute(versionConverterEClass, VERSION_CONVERTER__TYPE);
		createEReference(versionConverterEClass, VERSION_CONVERTER__TRANSFORMERS);
		createEAttribute(versionConverterEClass, VERSION_CONVERTER__VERSION_FORMAT);
		createEAttribute(versionConverterEClass, VERSION_CONVERTER__VERSION_TYPE);

		// Create enums
		versionSelectorTypeEEnum = createEEnum(VERSION_SELECTOR_TYPE);
		conflictPolicyEEnum = createEEnum(CONFLICT_POLICY);

		// Create data types
		transformMismatchExceptionEDataType = createEDataType(TRANSFORM_MISMATCH_EXCEPTION);
		iComponentReaderEDataType = createEDataType(ICOMPONENT_READER);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getBranchPoint() {
		return branchPointEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getBranchPoint_Name() {
		return (EAttribute) branchPointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getBranchPoint_OnMergeConflict() {
		return (EAttribute) branchPointEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getBranchPoint_Revision() {
		return (EAttribute) branchPointEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getBranchPoint_Tag() {
		return (EAttribute) branchPointEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getBranchPoint_Timestamp() {
		return (EAttribute) branchPointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EEnum getConflictPolicy() {
		return conflictPolicyEEnum;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getDocumentRoot() {
		return documentRootEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Arch() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Group() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Locators() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Match() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Matcher() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(14);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Name() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Nl() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Os() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Redirects() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(15);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Revision() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Rmap() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Timestamp() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Version() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_Ws() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_XMLNSPrefixMap() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getDocumentRoot_XSISchemaLocation() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getIComponentReader() {
		return iComponentReaderEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getLocator() {
		return locatorEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getLocator_FailOnError() {
		return (EAttribute) locatorEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getLocator_SearchPath() {
		return (EReference) locatorEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getMatcher() {
		return matcherEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatcher_ComponentTypes() {
		return (EAttribute) matcherEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatcher_ComponentTypesAttr() {
		return (EAttribute) matcherEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getMatcher_Pattern() {
		return (EAttribute) matcherEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMatcher_ResolutionFilter() {
		return (EAttribute) matcherEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getProvider() {
		return providerEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getProvider_Documentation() {
		return (EReference) providerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getProvider_Matcher() {
		return (EReference) providerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getProvider_Mutable() {
		return (EAttribute) providerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getProvider_ReaderType() {
		return (EAttribute) providerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getProvider_Source() {
		return (EAttribute) providerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getProvider_URI() {
		return (EReference) providerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getProvider_VersionConverter() {
		return (EReference) providerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getRedirect() {
		return redirectEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRedirect_RedirectTo() {
		return (EAttribute) redirectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getRepository() {
		return repositoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getRepository_AllowDirty() {
		return (EAttribute) repositoryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getRepository_Branches() {
		return (EReference) repositoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getRepository_Checkout() {
		return (EAttribute) repositoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getRepository_Connection() {
		return (EReference) repositoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getRepository_Documentation() {
		return (EReference) repositoryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getRepository_Id() {
		return (EAttribute) repositoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getRepository_Type() {
		return (EAttribute) repositoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getRepository_Update() {
		return (EAttribute) repositoryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getResourceMap() {
		return resourceMapEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getResourceMap_Documentation() {
		return (EReference) resourceMapEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getResourceMap_MatcherGroup() {
		return (EAttribute) resourceMapEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getResourceMap_Matchers() {
		return (EReference) resourceMapEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getResourceMap_SearchPaths() {
		return (EReference) resourceMapEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public RmapFactory getRmapFactory() {
		return (RmapFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getSearchPath() {
		return searchPathEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getSearchPath_Name() {
		return (EAttribute) searchPathEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getSearchPath_Providers() {
		return (EReference) searchPathEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getTransform() {
		return transformEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getTransform_FromPattern() {
		return (EAttribute) transformEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getTransform_FromReplacement() {
		return (EAttribute) transformEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getTransform_ToPattern() {
		return (EAttribute) transformEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getTransform_ToReplacement() {
		return (EAttribute) transformEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EDataType getTransformMismatchException() {
		return transformMismatchExceptionEDataType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getURIMatcher() {
		return uriMatcherEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getURIMatcher_Base() {
		return (EAttribute) uriMatcherEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getURIMatcher_VersionFormat() {
		return (EAttribute) uriMatcherEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getURIMatcher_VersionType() {
		return (EAttribute) uriMatcherEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EClass getVersionConverter() {
		return versionConverterEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EReference getVersionConverter_Transformers() {
		return (EReference) versionConverterEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getVersionConverter_Type() {
		return (EAttribute) versionConverterEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getVersionConverter_VersionFormat() {
		return (EAttribute) versionConverterEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public EAttribute getVersionConverter_VersionType() {
		return (EAttribute) versionConverterEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EEnum getVersionSelectorType() {
		return versionSelectorTypeEEnum;
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
		XMLTypePackage theXMLTypePackage = (XMLTypePackage) EPackage.Registry.INSTANCE.getEPackage(XMLTypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		documentRootEClass.getESuperTypes().add(theCommonPackage.getAbstractDocumentRoot());
		locatorEClass.getESuperTypes().add(this.getMatcher());
		providerEClass.getESuperTypes().add(theCommonPackage.getProperties());
		providerEClass.getESuperTypes().add(this.getMatcher());
		redirectEClass.getESuperTypes().add(this.getMatcher());
		resourceMapEClass.getESuperTypes().add(theCommonPackage.getProperties());
		uriMatcherEClass.getESuperTypes().add(theCommonPackage.getRxAssembly());

		// Initialize classes and features; add operations and parameters
		initEClass(branchPointEClass, BranchPoint.class, "BranchPoint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getBranchPoint_Name(), ecorePackage.getEString(), "name", null, 1, 1, BranchPoint.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchPoint_Timestamp(), ecorePackage.getEDate(), "timestamp", null, 0, 1, BranchPoint.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchPoint_Revision(), ecorePackage.getEString(), "revision", null, 0, 1, BranchPoint.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchPoint_Tag(), ecorePackage.getEString(), "tag", null, 0, 1, BranchPoint.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getBranchPoint_OnMergeConflict(), this.getConflictPolicy(), "onMergeConflict", "FAIL", 0, 1, BranchPoint.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, null, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDocumentRoot_XMLNSPrefixMap(), ecorePackage.getEStringToStringMapEntry(), null, "xMLNSPrefixMap", null, 0, -1, null,
				IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_XSISchemaLocation(), ecorePackage.getEStringToStringMapEntry(), null, "xSISchemaLocation", null, 0, -1, null,
				IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Match(), theCommonPackage.getRxPattern(), null, "match", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Group(), theCommonPackage.getRxGroup(), null, "group", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Name(), theCommonPackage.getRxPattern(), null, "name", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Version(), theCommonPackage.getRxPattern(), null, "version", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Revision(), theCommonPackage.getRxPattern(), null, "revision", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Timestamp(), theCommonPackage.getRxPattern(), null, "timestamp", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Os(), theCommonPackage.getRxPattern(), null, "os", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Ws(), theCommonPackage.getRxPattern(), null, "ws", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Arch(), theCommonPackage.getRxPattern(), null, "arch", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Nl(), theCommonPackage.getRxPattern(), null, "nl", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Rmap(), this.getResourceMap(), null, "rmap", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Locators(), this.getLocator(), null, "locators", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Matcher(), this.getMatcher(), null, "matcher", null, 0, -2, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getDocumentRoot_Redirects(), this.getRedirect(), null, "redirects", null, 0, -2, null, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);

		initEClass(locatorEClass, Locator.class, "Locator", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getLocator_SearchPath(), this.getSearchPath(), null, "searchPath", null, 0, 1, Locator.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getLocator_FailOnError(), ecorePackage.getEBoolean(), "failOnError", "true", 0, 1, Locator.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(matcherEClass, Matcher.class, "Matcher", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMatcher_Pattern(), theCommonPackage.getPattern(), "pattern", null, 0, 1, Matcher.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatcher_ComponentTypesAttr(), ecorePackage.getEString(), "componentTypesAttr", null, 0, 1, Matcher.class, !IS_TRANSIENT,
				IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatcher_ComponentTypes(), ecorePackage.getEString(), "componentTypes", "", 0, -1, Matcher.class, IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getMatcher_ResolutionFilter(), theCommonPackage.getFilter(), "resolutionFilter", null, 0, 1, Matcher.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		EOperation op = addEOperation(matcherEClass, ecorePackage.getEBoolean(), "matches", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theCommonPackage.getComponentName(), "componentName", 0, 1, IS_UNIQUE, IS_ORDERED);
		EGenericType g1 = createEGenericType(ecorePackage.getEMap());
		EGenericType g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "properties", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(matcherEClass, this.getResourceMap(), "getResourceMap", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(providerEClass, Provider.class, "Provider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProvider_ReaderType(), ecorePackage.getEString(), "readerType", null, 1, 1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_Source(), ecorePackage.getEBoolean(), "source", "true", 0, 1, Provider.class, !IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEAttribute(getProvider_Mutable(), ecorePackage.getEBoolean(), "mutable", "true", 0, 1, Provider.class, !IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getProvider_VersionConverter(), this.getVersionConverter(), null, "versionConverter", null, 0, 1, Provider.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvider_URI(), theCommonPackage.getFormat(), null, "URI", null, 1, 1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvider_Matcher(), this.getURIMatcher(), null, "matcher", null, 0, 1, Provider.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProvider_Documentation(), theCommonPackage.getDocumentation(), null, "documentation", null, 0, 1, Provider.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(providerEClass, ecorePackage.getEString(), "getURI", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "properties", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(providerEClass, this.getResourceMap(), "getDelegationMap", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, this.getIComponentReader(), "reader", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theCommonPackage.getIStatus(), "problemCollector", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(theCommonPackage.getComponentIdentifier());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEMap());
		g1.getETypeArguments().add(g2);
		EGenericType g3 = createEGenericType(ecorePackage.getEString());
		g2.getETypeArguments().add(g3);
		g3 = createEGenericType(ecorePackage.getEString());
		g2.getETypeArguments().add(g3);
		addEParameter(op, g1, "queryHints", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theCommonPackage.getIProgressMonitor(), "monitor", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, theCommonPackage.getCoreException());

		op = addEOperation(providerEClass, null, "getProperties", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "properties", 0, 1, IS_UNIQUE, IS_ORDERED);
		g1 = createEGenericType(ecorePackage.getEMap());
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		g2 = createEGenericType(ecorePackage.getEString());
		g1.getETypeArguments().add(g2);
		initEOperation(op, g1);

		addEOperation(providerEClass, ecorePackage.getEBoolean(), "hasDelegationMap", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(redirectEClass, Redirect.class, "Redirect", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRedirect_RedirectTo(), ecorePackage.getEString(), "redirectTo", null, 1, 1, Redirect.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(repositoryEClass, Repository.class, "Repository", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepository_Id(), theXMLTypePackage.getID(), "id", null, 1, 1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRepository_Connection(), theCommonPackage.getFormat(), null, "connection", null, 1, 1, Repository.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepository_Type(), ecorePackage.getEString(), "type", null, 1, 1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRepository_Branches(), this.getBranchPoint(), null, "branches", null, 0, -1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepository_Checkout(), ecorePackage.getEBoolean(), "checkout", "true", 0, 1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepository_AllowDirty(), ecorePackage.getEBoolean(), "allowDirty", "false", 0, 1, Repository.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRepository_Update(), ecorePackage.getEBoolean(), "update", "true", 0, 1, Repository.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRepository_Documentation(), theCommonPackage.getDocumentation(), null, "documentation", null, 0, 1, Repository.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(resourceMapEClass, ResourceMap.class, "ResourceMap", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getResourceMap_Documentation(), theCommonPackage.getDocumentation(), null, "documentation", null, 0, 1, ResourceMap.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getResourceMap_MatcherGroup(), ecorePackage.getEFeatureMapEntry(), "matcherGroup", null, 0, -1, ResourceMap.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getResourceMap_Matchers(), this.getMatcher(), null, "matchers", null, 0, -1, ResourceMap.class, IS_TRANSIENT, IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, IS_DERIVED, IS_ORDERED);
		initEReference(getResourceMap_SearchPaths(), this.getSearchPath(), null, "searchPaths", null, 0, -1, ResourceMap.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		getResourceMap_SearchPaths().getEKeys().add(this.getSearchPath_Name());

		addEOperation(resourceMapEClass, theCommonPackage.getURL(), "getContextURL", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(searchPathEClass, SearchPath.class, "SearchPath", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSearchPath_Name(), theXMLTypePackage.getID(), "name", null, 1, 1, SearchPath.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSearchPath_Providers(), this.getProvider(), null, "providers", null, 0, -1, SearchPath.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(transformEClass, Transform.class, "Transform", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTransform_FromPattern(), theCommonPackage.getPattern(), "fromPattern", null, 1, 1, Transform.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransform_FromReplacement(), ecorePackage.getEString(), "fromReplacement", null, 1, 1, Transform.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransform_ToPattern(), theCommonPackage.getPattern(), "toPattern", null, 1, 1, Transform.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTransform_ToReplacement(), ecorePackage.getEString(), "toReplacement", null, 1, 1, Transform.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(transformEClass, ecorePackage.getEString(), "transformFrom", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getTransformMismatchException());

		op = addEOperation(transformEClass, ecorePackage.getEString(), "transformTo", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "source", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEException(op, this.getTransformMismatchException());

		initEClass(uriMatcherEClass, URIMatcher.class, "URIMatcher", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getURIMatcher_Base(), ecorePackage.getEString(), "base", null, 0, 1, URIMatcher.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getURIMatcher_VersionFormat(), theCommonPackage.getIVersionFormat(), "versionFormat", null, 0, 1, URIMatcher.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getURIMatcher_VersionType(), ecorePackage.getEString(), "versionType", null, 0, 1, URIMatcher.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(uriMatcherEClass, ecorePackage.getEString(), "getComponentType", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(versionConverterEClass, VersionConverter.class, "VersionConverter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getVersionConverter_Type(), this.getVersionSelectorType(), "type", "BRANCH", 1, 1, VersionConverter.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getVersionConverter_Transformers(), this.getTransform(), null, "transformers", null, 0, -1, VersionConverter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVersionConverter_VersionFormat(), theCommonPackage.getIVersionFormat(), "versionFormat", null, 0, 1,
				VersionConverter.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getVersionConverter_VersionType(), ecorePackage.getEString(), "versionType", "OSGi", 0, 1, VersionConverter.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		op = addEOperation(versionConverterEClass, theCommonPackage.getVersion(), "createVersion", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, ecorePackage.getEString(), "identifier", 0, 1, IS_UNIQUE, IS_ORDERED);

		op = addEOperation(versionConverterEClass, ecorePackage.getEString(), "createIdentifier", 0, 1, IS_UNIQUE, IS_ORDERED);
		addEParameter(op, theCommonPackage.getVersion(), "version", 0, 1, IS_UNIQUE, IS_ORDERED);

		// Initialize enums and add enum literals
		initEEnum(versionSelectorTypeEEnum, VersionSelectorType.class, "VersionSelectorType");
		addEEnumLiteral(versionSelectorTypeEEnum, VersionSelectorType.BRANCH);
		addEEnumLiteral(versionSelectorTypeEEnum, VersionSelectorType.TAG);

		initEEnum(conflictPolicyEEnum, ConflictPolicy.class, "ConflictPolicy");
		addEEnumLiteral(conflictPolicyEEnum, ConflictPolicy.USE_WORKSPACE);
		addEEnumLiteral(conflictPolicyEEnum, ConflictPolicy.USE_SCM);
		addEEnumLiteral(conflictPolicyEEnum, ConflictPolicy.FAIL);

		// Initialize data types
		initEDataType(transformMismatchExceptionEDataType, TransformMismatchException.class, "TransformMismatchException", IS_SERIALIZABLE,
				!IS_GENERATED_INSTANCE_CLASS);
		initEDataType(iComponentReaderEDataType, IComponentReader.class, "IComponentReader", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS);

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
		addAnnotation(getDocumentRoot_XMLNSPrefixMap(), source, new String[] { "kind", "attribute", "name", "xmlns:prefix" });
		addAnnotation(getDocumentRoot_XSISchemaLocation(), source, new String[] { "kind", "attribute", "name", "xsi:schemaLocation" });
		addAnnotation(getDocumentRoot_Match(), source, new String[] { "kind", "element", "name", "match", "namespace", "##targetNamespace",
				"affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Group(), source, new String[] { "kind", "element", "name", "group", "namespace", "##targetNamespace",
				"affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Name(), source, new String[] { "kind", "element", "name", "name", "namespace", "##targetNamespace",
				"affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Version(), source, new String[] { "kind", "element", "name", "version", "namespace", "##targetNamespace",
				"affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Revision(), source, new String[] { "kind", "element", "name", "revision", "namespace", "##targetNamespace",
				"affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Timestamp(), source, new String[] { "kind", "element", "name", "timestamp", "namespace", "##targetNamespace",
				"affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Os(), source, new String[] { "kind", "element", "name", "os", "namespace", "##targetNamespace", "affiliation",
				"http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Ws(), source, new String[] { "kind", "element", "name", "ws", "namespace", "##targetNamespace", "affiliation",
				"http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Arch(), source, new String[] { "kind", "element", "name", "arch", "namespace", "##targetNamespace",
				"affiliation", "http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Nl(), source, new String[] { "kind", "element", "name", "nl", "namespace", "##targetNamespace", "affiliation",
				"http://www.eclipse.org/buckminster/Common-1.0#rxPart" });
		addAnnotation(getDocumentRoot_Rmap(), source, new String[] { "namespace", "##targetNamespace", "kind", "element", "name", "rmap" });
		addAnnotation(getDocumentRoot_Locators(), source, new String[] { "kind", "element", "name", "locator", "namespace", "##targetNamespace",
				"affiliation", "matcher" });
		addAnnotation(getDocumentRoot_Matcher(), source, new String[] { "kind", "element", "name", "matcher", "namespace", "##targetNamespace" });
		addAnnotation(getDocumentRoot_Redirects(), source, new String[] { "kind", "element", "name", "redirect", "namespace", "##targetNamespace",
				"affiliation", "matcher" });
		addAnnotation(getLocator_SearchPath(), source, new String[] { "name", "searchPathRef", "kind", "attribute" });
		addAnnotation(getMatcher_ComponentTypesAttr(), source, new String[] { "name", "componentTypes", "kind", "attribute" });
		addAnnotation(getProvider_VersionConverter(), source, new String[] { "namespace", "##targetNamespace", "kind", "element" });
		addAnnotation(getProvider_URI(), source, new String[] { "name", "uri", "namespace", "##targetNamespace", "kind", "element" });
		addAnnotation(getProvider_Matcher(), source, new String[] { "name", "matcher", "kind", "element", "namespace", "##targetNamespace" });
		addAnnotation(getProvider_Documentation(), source, new String[] { "name", "documentation", "kind", "element", "namespace",
				"##targetNamespace" });
		addAnnotation(getRedirect_RedirectTo(), source, new String[] { "name", "href", "kind", "attribute" });
		addAnnotation(getRepository_Id(), source, new String[] { "namespace", "" });
		addAnnotation(resourceMapEClass, source, new String[] { "name", "rmap" });
		addAnnotation(getResourceMap_Documentation(), source, new String[] { "name", "documentation", "kind", "element", "namespace",
				"##targetNamespace" });
		addAnnotation(getResourceMap_MatcherGroup(), source, new String[] { "kind", "group", "name", "matcher:group", "namespace",
				"##targetNamespace" });
		addAnnotation(getResourceMap_Matchers(), source, new String[] { "kind", "element", "name", "matcher", "namespace", "##targetNamespace",
				"group", "matcher:group" });
		addAnnotation(getResourceMap_SearchPaths(), source,
				new String[] { "name", "searchPath", "namespace", "##targetNamespace", "kind", "element" });
		addAnnotation(getSearchPath_Providers(), source, new String[] { "name", "provider", "namespace", "##targetNamespace", "kind", "element" });
		addAnnotation(getURIMatcher_Base(), source, new String[] { "kind", "attribute" });
		addAnnotation(getVersionConverter_Transformers(), source, new String[] { "name", "transform", "namespace", "##targetNamespace", "kind",
				"element" });
	}

} // RmapPackageImpl
