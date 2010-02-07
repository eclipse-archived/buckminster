/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf.impl;

import org.eclipse.buckminster.team.psf.Item;
import org.eclipse.buckminster.team.psf.Project;
import org.eclipse.buckminster.team.psf.PsfFactory;
import org.eclipse.buckminster.team.psf.PsfPackage;
import org.eclipse.buckminster.team.psf.RepositoryProvider;
import org.eclipse.buckminster.team.psf.WorkingSet;

import org.eclipse.emf.ecore.EAttribute;
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
public class PsfPackageImpl extends EPackageImpl implements PsfPackage {
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass psfEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass repositoryProviderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass workingSetEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass itemEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass projectEClass = null;

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
	 * @see org.eclipse.buckminster.team.psf.PsfPackage#eNS_URI
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
		psfEClass = createEClass(PSF);
		createEAttribute(psfEClass, PSF__VERSION);
		createEReference(psfEClass, PSF__PROVIDERS);
		createEReference(psfEClass, PSF__WORKING_SETS);

		repositoryProviderEClass = createEClass(REPOSITORY_PROVIDER);
		createEAttribute(repositoryProviderEClass, REPOSITORY_PROVIDER__ID);
		createEReference(repositoryProviderEClass, REPOSITORY_PROVIDER__PROJECTS);

		workingSetEClass = createEClass(WORKING_SET);
		createEAttribute(workingSetEClass, WORKING_SET__ID);
		createEAttribute(workingSetEClass, WORKING_SET__LABEL);
		createEAttribute(workingSetEClass, WORKING_SET__NAME);
		createEAttribute(workingSetEClass, WORKING_SET__EDIT_PAGE_ID);
		createEReference(workingSetEClass, WORKING_SET__ITEMS);

		itemEClass = createEClass(ITEM);
		createEAttribute(itemEClass, ITEM__FACTORY_ID);
		createEAttribute(itemEClass, ITEM__ELEMENT_ID);
		createEAttribute(itemEClass, ITEM__PATH);
		createEAttribute(itemEClass, ITEM__TYPE);

		projectEClass = createEClass(PROJECT);
		createEAttribute(projectEClass, PROJECT__REFERENCE);

		documentRootEClass = createEClass(DOCUMENT_ROOT);
		createEReference(documentRootEClass, DOCUMENT_ROOT__PSF);
		createEAttribute(documentRootEClass, DOCUMENT_ROOT__MIXED);
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
	public EAttribute getDocumentRoot_Mixed() {
		return (EAttribute) documentRootEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDocumentRoot_Psf() {
		return (EReference) documentRootEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getItem() {
		return itemEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_ElementID() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_FactoryID() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_Path() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getItem_Type() {
		return (EAttribute) itemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProject() {
		return projectEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getProject_Reference() {
		return (EAttribute) projectEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getPSF() {
		return psfEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPSF_Providers() {
		return (EReference) psfEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getPSF_Version() {
		return (EAttribute) psfEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getPSF_WorkingSets() {
		return (EReference) psfEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PsfFactory getPsfFactory() {
		return (PsfFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getRepositoryProvider() {
		return repositoryProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getRepositoryProvider_Id() {
		return (EAttribute) repositoryProviderEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getRepositoryProvider_Projects() {
		return (EReference) repositoryProviderEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getWorkingSet() {
		return workingSetEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getWorkingSet_EditPageId() {
		return (EAttribute) workingSetEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getWorkingSet_Id() {
		return (EAttribute) workingSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getWorkingSet_Items() {
		return (EReference) workingSetEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getWorkingSet_Label() {
		return (EAttribute) workingSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getWorkingSet_Name() {
		return (EAttribute) workingSetEClass.getEStructuralFeatures().get(2);
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

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes

		// Initialize classes and features; add operations and parameters
		initEClass(psfEClass, org.eclipse.buckminster.team.psf.PSF.class, "PSF", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPSF_Version(), ecorePackage.getEString(), "version", null, 1, 1, org.eclipse.buckminster.team.psf.PSF.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPSF_Providers(), this.getRepositoryProvider(), null, "providers", null, 0, -1, org.eclipse.buckminster.team.psf.PSF.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getPSF_WorkingSets(), this.getWorkingSet(), null, "workingSets", null, 0, -1, org.eclipse.buckminster.team.psf.PSF.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(repositoryProviderEClass, RepositoryProvider.class, "RepositoryProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRepositoryProvider_Id(), ecorePackage.getEString(), "id", null, 1, 1, RepositoryProvider.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getRepositoryProvider_Projects(), this.getProject(), null, "projects", null, 0, -1, RepositoryProvider.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(workingSetEClass, WorkingSet.class, "WorkingSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getWorkingSet_Id(), ecorePackage.getEString(), "id", null, 1, 1, WorkingSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkingSet_Label(), ecorePackage.getEString(), "label", null, 0, 1, WorkingSet.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkingSet_Name(), ecorePackage.getEString(), "name", null, 0, 1, WorkingSet.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getWorkingSet_EditPageId(), ecorePackage.getEString(), "editPageId", null, 0, 1, WorkingSet.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getWorkingSet_Items(), this.getItem(), null, "items", null, 0, -1, WorkingSet.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(itemEClass, Item.class, "Item", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getItem_FactoryID(), ecorePackage.getEString(), "factoryID", null, 0, 1, Item.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getItem_ElementID(), ecorePackage.getEString(), "elementID", null, 0, 1, Item.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getItem_Path(), ecorePackage.getEString(), "path", null, 0, 1, Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getItem_Type(), ecorePackage.getEInt(), "type", null, 0, 1, Item.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(projectEClass, Project.class, "Project", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProject_Reference(), ecorePackage.getEString(), "reference", null, 1, 1, Project.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(documentRootEClass, null, "DocumentRoot", !IS_ABSTRACT, !IS_INTERFACE, !IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDocumentRoot_Psf(), this.getPSF(), null, "psf", null, 1, 1, null, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE,
				!IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getDocumentRoot_Mixed(), ecorePackage.getEFeatureMapEntry(), "mixed", null, 0, -1, null, !IS_TRANSIENT, !IS_VOLATILE,
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
		addAnnotation(this, source, new String[] { "qualified", "false" });
		addAnnotation(getPSF_Providers(), source, new String[] { "name", "provider", "kind", "element" });
		addAnnotation(getPSF_WorkingSets(), source, new String[] { "kind", "element" });
		addAnnotation(getRepositoryProvider_Projects(), source, new String[] { "name", "project", "kind", "element" });
		addAnnotation(getWorkingSet_Items(), source, new String[] { "name", "item", "kind", "element" });
		addAnnotation(documentRootEClass, source, new String[] { "kind", "mixed", "name", "" });
		addAnnotation(getDocumentRoot_Psf(), source, new String[] { "kind", "element" });
		addAnnotation(getDocumentRoot_Mixed(), source, new String[] { "kind", "elementWildcard", "name", ":mixed", "namespace", "" });
	}

} // PsfPackageImpl
