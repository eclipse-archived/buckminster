/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.pde.impl;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.buckminster.rmap.pde.PDEMapProvider;
import org.eclipse.buckminster.rmap.pde.PdeFactory;
import org.eclipse.buckminster.rmap.pde.PdePackage;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class PdePackageImpl extends EPackageImpl implements PdePackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass pdeMapProviderEClass = null;

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
	 * This method is used to initialize {@link PdePackage#eINSTANCE} when that
	 * field is accessed. Clients should not invoke it directly. Instead, they
	 * should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static PdePackage init() {
		if (isInited)
			return (PdePackage) EPackage.Registry.INSTANCE.getEPackage(PdePackage.eNS_URI);

		// Obtain or create and register package
		PdePackageImpl thePdePackage = (PdePackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof PdePackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI) : new PdePackageImpl());

		isInited = true;

		// Initialize simple dependencies
		RmapPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		thePdePackage.createPackageContents();

		// Initialize created meta-data
		thePdePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		thePdePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(PdePackage.eNS_URI, thePdePackage);
		return thePdePackage;
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
	 * @see org.eclipse.buckminster.rmap.pde.PdePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private PdePackageImpl() {
		super(eNS_URI, PdeFactory.eINSTANCE);
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
		pdeMapProviderEClass = createEClass(PDE_MAP_PROVIDER);
		createEReference(pdeMapProviderEClass, PDE_MAP_PROVIDER__REPLACE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PdeFactory getPdeFactory() {
		return (PdeFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EClass getPDEMapProvider() {
		return pdeMapProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EReference getPDEMapProvider_Replace() {
		return (EReference) pdeMapProviderEClass.getEStructuralFeatures().get(0);
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
		CommonPackage theCommonPackage = (CommonPackage) EPackage.Registry.INSTANCE.getEPackage(CommonPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		pdeMapProviderEClass.getESuperTypes().add(theRmapPackage.getProvider());

		// Initialize classes and features; add operations and parameters
		initEClass(pdeMapProviderEClass, PDEMapProvider.class, "PDEMapProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getPDEMapProvider_Replace(), theCommonPackage.getReplace(), null, "replace", null, 0, 1, PDEMapProvider.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		addAnnotation(getPDEMapProvider_Replace(), source, new String[] { "name", "replace", "kind", "element", "namespace", "##targetNamespace" });
	}

} // PdePackageImpl
