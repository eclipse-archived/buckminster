/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf.impl;

import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.buckminster.rmap.psf.PSFProvider;
import org.eclipse.buckminster.rmap.psf.PsfFactory;
import org.eclipse.buckminster.rmap.psf.PsfPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class PsfPackageImpl extends EPackageImpl implements PsfPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass psfProviderEClass = null;

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
	 * This method is used to initialize {@link PsfPackage#eINSTANCE} when that
	 * field is accessed. Clients should not invoke it directly. Instead, they
	 * should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PsfPackage init() {
		if (isInited)
			return (PsfPackage) EPackage.Registry.INSTANCE.getEPackage(PsfPackage.eNS_URI);

		// Obtain or create and register package
		PsfPackageImpl thePsfPackage = (PsfPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PsfPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI) : new PsfPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		RmapPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePsfPackage.createPackageContents();

		// Initialize created meta-data
		thePsfPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePsfPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PsfPackage.eNS_URI, thePsfPackage);
		return thePsfPackage;
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
	 * @see org.eclipse.buckminster.rmap.psf.PsfPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PsfPackageImpl() {
		super(eNS_URI, PsfFactory.eINSTANCE);
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
		psfProviderEClass = createEClass(PSF_PROVIDER);
		createEAttribute(psfProviderEClass, PSF_PROVIDER__PSF_FILE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PsfFactory getPsfFactory() {
		return (PsfFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getPSFProvider() {
		return psfProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EAttribute getPSFProvider_PsfFile() {
		return (EAttribute) psfProviderEClass.getEStructuralFeatures().get(0);
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
		RmapPackage theRmapPackage = (RmapPackage) EPackage.Registry.INSTANCE.getEPackage(RmapPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		psfProviderEClass.getESuperTypes().add(theRmapPackage.getProvider());

		// Initialize classes and features; add operations and parameters
		initEClass(psfProviderEClass, PSFProvider.class, "PSFProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPSFProvider_PsfFile(), ecorePackage.getEString(), "psfFile", null, 0, 1, PSFProvider.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		addAnnotation(getPSFProvider_PsfFile(), source, new String[] { "kind", "attribute" });
	}

} // PsfPackageImpl
