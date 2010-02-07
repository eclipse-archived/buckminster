/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.impl;

import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.buckminster.rmap.maven.GroupAndArtifact;
import org.eclipse.buckminster.rmap.maven.MapEntry;
import org.eclipse.buckminster.rmap.maven.Mappings;
import org.eclipse.buckminster.rmap.maven.MavenProvider;
import org.eclipse.buckminster.rmap.maven.MavenFactory;
import org.eclipse.buckminster.rmap.maven.MavenPackage;

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
public class MavenPackageImpl extends EPackageImpl implements MavenPackage {
	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model,
	 * and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link MavenPackage#eINSTANCE} when
	 * that field is accessed. Clients should not invoke it directly. Instead,
	 * they should simply access that field to obtain the package. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static MavenPackage init() {
		if (isInited)
			return (MavenPackage) EPackage.Registry.INSTANCE.getEPackage(MavenPackage.eNS_URI);

		// Obtain or create and register package
		MavenPackageImpl theMavenPackage = (MavenPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof MavenPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI) : new MavenPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		RmapPackage.eINSTANCE.eClass();

		// Create package meta-data objects
		theMavenPackage.createPackageContents();

		// Initialize created meta-data
		theMavenPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theMavenPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(MavenPackage.eNS_URI, theMavenPackage);
		return theMavenPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass groupAndArtifactEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass mapEntryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass mappingsEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass mavenProviderEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static boolean isInited = false;

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
	 * @see org.eclipse.buckminster.rmap.maven.MavenPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private MavenPackageImpl() {
		super(eNS_URI, MavenFactory.eINSTANCE);
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
		groupAndArtifactEClass = createEClass(GROUP_AND_ARTIFACT);
		createEAttribute(groupAndArtifactEClass, GROUP_AND_ARTIFACT__ARTIFACT_ID);
		createEAttribute(groupAndArtifactEClass, GROUP_AND_ARTIFACT__GROUP_ID);

		mapEntryEClass = createEClass(MAP_ENTRY);
		createEAttribute(mapEntryEClass, MAP_ENTRY__NAME);
		createEReference(mapEntryEClass, MAP_ENTRY__ALIASES);

		mappingsEClass = createEClass(MAPPINGS);
		createEReference(mappingsEClass, MAPPINGS__ENTRIES);
		createEReference(mappingsEClass, MAPPINGS__RULES);

		mavenProviderEClass = createEClass(MAVEN_PROVIDER);
		createEReference(mavenProviderEClass, MAVEN_PROVIDER__MAPPINGS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getGroupAndArtifact() {
		return groupAndArtifactEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGroupAndArtifact_ArtifactId() {
		return (EAttribute) groupAndArtifactEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getGroupAndArtifact_GroupId() {
		return (EAttribute) groupAndArtifactEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMapEntry() {
		return mapEntryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMapEntry_Aliases() {
		return (EReference) mapEntryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMapEntry_Name() {
		return (EAttribute) mapEntryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMappings() {
		return mappingsEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMappings_Entries() {
		return (EReference) mappingsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMappings_Rules() {
		return (EReference) mappingsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MavenFactory getMavenFactory() {
		return (MavenFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMavenProvider() {
		return mavenProviderEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMavenProvider_Mappings() {
		return (EReference) mavenProviderEClass.getEStructuralFeatures().get(0);
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
		mapEntryEClass.getESuperTypes().add(this.getGroupAndArtifact());
		mavenProviderEClass.getESuperTypes().add(theRmapPackage.getProvider());

		// Initialize classes and features; add operations and parameters
		initEClass(groupAndArtifactEClass, GroupAndArtifact.class, "GroupAndArtifact", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getGroupAndArtifact_ArtifactId(), ecorePackage.getEString(), "artifactId", null, 0, 1, GroupAndArtifact.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getGroupAndArtifact_GroupId(), ecorePackage.getEString(), "groupId", null, 0, 1, GroupAndArtifact.class, !IS_TRANSIENT,
				!IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mapEntryEClass, MapEntry.class, "MapEntry", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMapEntry_Name(), ecorePackage.getEString(), "name", null, 0, 1, MapEntry.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				!IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMapEntry_Aliases(), this.getGroupAndArtifact(), null, "aliases", null, 0, -1, MapEntry.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mappingsEClass, Mappings.class, "Mappings", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMappings_Entries(), this.getMapEntry(), null, "entries", null, 0, -1, Mappings.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMappings_Rules(), theRmapPackage.getTransform(), null, "rules", null, 0, -1, Mappings.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(mavenProviderEClass, MavenProvider.class, "MavenProvider", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMavenProvider_Mappings(), this.getMappings(), null, "mappings", null, 0, 1, MavenProvider.class, !IS_TRANSIENT,
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
		addAnnotation(getMapEntry_Aliases(), source, new String[] { "name", "alias", "kind", "element" });
		addAnnotation(getMappings_Entries(), source, new String[] { "kind", "element", "name", "entry" });
		addAnnotation(getMappings_Rules(), source, new String[] { "name", "rule", "kind", "element" });
		addAnnotation(getMavenProvider_Mappings(), source, new String[] { "kind", "element" });
	}

} // MavenPackageImpl
