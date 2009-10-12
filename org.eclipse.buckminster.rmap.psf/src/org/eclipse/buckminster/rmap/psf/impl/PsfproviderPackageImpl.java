/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.psf.impl;

import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.psf.PSFProvider;
import org.eclipse.buckminster.rmap.psf.PsfproviderFactory;
import org.eclipse.buckminster.rmap.psf.PsfproviderPackage;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class PsfproviderPackageImpl extends EPackageImpl implements PsfproviderPackage
{
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
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link PsfproviderPackage#eINSTANCE} when that field is accessed. Clients
	 * should not invoke it directly. Instead, they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PsfproviderPackage init()
	{
		if(isInited)
			return (PsfproviderPackage)EPackage.Registry.INSTANCE.getEPackage(PsfproviderPackage.eNS_URI);

		// Obtain or create and register package
		PsfproviderPackageImpl thePsfproviderPackage = (PsfproviderPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PsfproviderPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new PsfproviderPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		RmapPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePsfproviderPackage.createPackageContents();

		// Initialize created meta-data
		thePsfproviderPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePsfproviderPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PsfproviderPackage.eNS_URI, thePsfproviderPackage);
		return thePsfproviderPackage;
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
	 * @see org.eclipse.buckminster.rmap.psf.PsfproviderPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PsfproviderPackageImpl()
	{
		super(eNS_URI, PsfproviderFactory.eINSTANCE);
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
		psfProviderEClass = createEClass(PSF_PROVIDER);
		createEAttribute(psfProviderEClass, PSF_PROVIDER__PSF_FILE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPSFProvider()
	{
		return psfProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPSFProvider_PsfFile()
	{
		return (EAttribute)psfProviderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PsfproviderFactory getPsfproviderFactory()
	{
		return (PsfproviderFactory)getEFactoryInstance();
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
		RmapPackage theRmapPackage = (RmapPackage)EPackage.Registry.INSTANCE.getEPackage(RmapPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		psfProviderEClass.getESuperTypes().add(theRmapPackage.getProvider());

		// Initialize classes and features; add operations and parameters
		initEClass(psfProviderEClass, PSFProvider.class, "PSFProvider", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPSFProvider_PsfFile(), ecorePackage.getEString(), "psfFile", null, 0, 1, PSFProvider.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		addAnnotation(getPSFProvider_PsfFile(), source, new String[] { "kind", "attribute" });
	}

} // PsfproviderPackageImpl
