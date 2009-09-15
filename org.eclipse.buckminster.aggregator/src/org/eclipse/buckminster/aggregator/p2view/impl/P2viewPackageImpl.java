/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view.impl;

import org.eclipse.buckminster.aggregator.AggregatorPackage;

import org.eclipse.buckminster.aggregator.impl.AggregatorPackageImpl;

import org.eclipse.buckminster.aggregator.p2.P2Package;

import org.eclipse.buckminster.aggregator.p2.impl.P2PackageImpl;

import org.eclipse.buckminster.aggregator.p2view.Bundle;
import org.eclipse.buckminster.aggregator.p2view.Bundles;
import org.eclipse.buckminster.aggregator.p2view.Categories;
import org.eclipse.buckminster.aggregator.p2view.Category;
import org.eclipse.buckminster.aggregator.p2view.Details;
import org.eclipse.buckminster.aggregator.p2view.Feature;
import org.eclipse.buckminster.aggregator.p2view.Features;
import org.eclipse.buckminster.aggregator.p2view.IUPresentation;
import org.eclipse.buckminster.aggregator.p2view.InstallableUnits;
import org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView;
import org.eclipse.buckminster.aggregator.p2view.Miscellaneous;
import org.eclipse.buckminster.aggregator.p2view.OtherIU;
import org.eclipse.buckminster.aggregator.p2view.P2viewFactory;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;

import org.eclipse.buckminster.aggregator.p2view.Product;
import org.eclipse.buckminster.aggregator.p2view.Products;
import org.eclipse.buckminster.aggregator.p2view.Properties;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Package</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class P2viewPackageImpl extends EPackageImpl implements P2viewPackage
{
	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>
	 * This method is used to initialize {@link P2viewPackage#eINSTANCE} when that field is accessed. Clients should not
	 * invoke it directly. Instead, they should simply access that field to obtain the package. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static P2viewPackage init()
	{
		if(isInited)
			return (P2viewPackage)EPackage.Registry.INSTANCE.getEPackage(P2viewPackage.eNS_URI);

		// Obtain or create and register package
		P2viewPackageImpl theP2viewPackage = (P2viewPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof P2viewPackageImpl
				? EPackage.Registry.INSTANCE.get(eNS_URI)
				: new P2viewPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		AggregatorPackageImpl theAggregatorPackage = (AggregatorPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(AggregatorPackage.eNS_URI) instanceof AggregatorPackageImpl
				? EPackage.Registry.INSTANCE.getEPackage(AggregatorPackage.eNS_URI)
				: AggregatorPackage.eINSTANCE);
		P2PackageImpl theP2Package = (P2PackageImpl)(EPackage.Registry.INSTANCE.getEPackage(P2Package.eNS_URI) instanceof P2PackageImpl
				? EPackage.Registry.INSTANCE.getEPackage(P2Package.eNS_URI)
				: P2Package.eINSTANCE);

		// Create package meta-data objects
		theP2viewPackage.createPackageContents();
		theAggregatorPackage.createPackageContents();
		theP2Package.createPackageContents();

		// Initialize created meta-data
		theP2viewPackage.initializePackageContents();
		theAggregatorPackage.initializePackageContents();
		theP2Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theP2viewPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(P2viewPackage.eNS_URI, theP2viewPackage);
		return theP2viewPackage;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass metadataRepositoryStructuredViewEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass installableUnitsEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass categoriesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass featuresEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass productsEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass bundlesEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass miscellaneousEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass iuPresentationEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass categoryEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass featureEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass productEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass bundleEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass otherIUEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass detailsEClass = null;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private EClass propertiesEClass = null;

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
	 * Creates an instance of the model <b>Package</b>, registered with {@link org.eclipse.emf.ecore.EPackage.Registry
	 * EPackage.Registry} by the package package URI value.
	 * <p>
	 * Note: the correct way to create the package is via the static factory method {@link #init init()}, which also
	 * performs initialization of the package, or returns the registered package, if one already exists. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private P2viewPackageImpl()
	{
		super(eNS_URI, P2viewFactory.eINSTANCE);
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
		metadataRepositoryStructuredViewEClass = createEClass(METADATA_REPOSITORY_STRUCTURED_VIEW);
		createEAttribute(metadataRepositoryStructuredViewEClass, METADATA_REPOSITORY_STRUCTURED_VIEW__NAME);
		createEReference(metadataRepositoryStructuredViewEClass,
				METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST);
		createEReference(metadataRepositoryStructuredViewEClass, METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES);

		installableUnitsEClass = createEClass(INSTALLABLE_UNITS);
		createEReference(installableUnitsEClass, INSTALLABLE_UNITS__CATEGORY_CONTAINER);
		createEReference(installableUnitsEClass, INSTALLABLE_UNITS__FEATURE_CONTAINER);
		createEReference(installableUnitsEClass, INSTALLABLE_UNITS__PRODUCT_CONTAINER);
		createEReference(installableUnitsEClass, INSTALLABLE_UNITS__BUNDLE_CONTAINER);
		createEReference(installableUnitsEClass, INSTALLABLE_UNITS__MISCELLANEOUS_CONTAINER);

		categoriesEClass = createEClass(CATEGORIES);
		createEReference(categoriesEClass, CATEGORIES__CATEGORIES);

		featuresEClass = createEClass(FEATURES);
		createEReference(featuresEClass, FEATURES__FEATURES);

		productsEClass = createEClass(PRODUCTS);
		createEReference(productsEClass, PRODUCTS__PRODUCTS);

		bundlesEClass = createEClass(BUNDLES);
		createEReference(bundlesEClass, BUNDLES__BUNDLES);

		miscellaneousEClass = createEClass(MISCELLANEOUS);
		createEReference(miscellaneousEClass, MISCELLANEOUS__OTHERS);

		iuPresentationEClass = createEClass(IU_PRESENTATION);
		createEAttribute(iuPresentationEClass, IU_PRESENTATION__ID);
		createEAttribute(iuPresentationEClass, IU_PRESENTATION__VERSION);
		createEAttribute(iuPresentationEClass, IU_PRESENTATION__NAME);
		createEAttribute(iuPresentationEClass, IU_PRESENTATION__DESCRIPTION);

		categoryEClass = createEClass(CATEGORY);
		createEReference(categoryEClass, CATEGORY__CATEGORY_CONTAINER);
		createEReference(categoryEClass, CATEGORY__FEATURE_CONTAINER);
		createEReference(categoryEClass, CATEGORY__PRODUCT_CONTAINER);
		createEReference(categoryEClass, CATEGORY__BUNDLE_CONTAINER);
		createEReference(categoryEClass, CATEGORY__DETAILS);

		featureEClass = createEClass(FEATURE);
		createEReference(featureEClass, FEATURE__FEATURE_CONTAINER);
		createEReference(featureEClass, FEATURE__BUNDLE_CONTAINER);
		createEReference(featureEClass, FEATURE__DETAILS);

		productEClass = createEClass(PRODUCT);
		createEReference(productEClass, PRODUCT__FEATURE_CONTAINER);
		createEReference(productEClass, PRODUCT__BUNDLE_CONTAINER);
		createEReference(productEClass, PRODUCT__DETAILS);

		bundleEClass = createEClass(BUNDLE);
		createEReference(bundleEClass, BUNDLE__DETAILS);

		otherIUEClass = createEClass(OTHER_IU);
		createEReference(otherIUEClass, OTHER_IU__DETAILS);

		detailsEClass = createEClass(DETAILS);
		createEReference(detailsEClass, DETAILS__INSTALLABLE_UNIT);

		propertiesEClass = createEClass(PROPERTIES);
		createEReference(propertiesEClass, PROPERTIES__PROPERTY_MAP);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getBundle()
	{
		return bundleEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBundle_Details()
	{
		return (EReference)bundleEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getBundles()
	{
		return bundlesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getBundles_Bundles()
	{
		return (EReference)bundlesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCategories()
	{
		return categoriesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCategories_Categories()
	{
		return (EReference)categoriesEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getCategory()
	{
		return categoryEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCategory_BundleContainer()
	{
		return (EReference)categoryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCategory_CategoryContainer()
	{
		return (EReference)categoryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCategory_Details()
	{
		return (EReference)categoryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCategory_FeatureContainer()
	{
		return (EReference)categoryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getCategory_ProductContainer()
	{
		return (EReference)categoryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getDetails()
	{
		return detailsEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getDetails_InstallableUnit()
	{
		return (EReference)detailsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFeature()
	{
		return featureEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getFeature_BundleContainer()
	{
		return (EReference)featureEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getFeature_Details()
	{
		return (EReference)featureEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getFeature_FeatureContainer()
	{
		return (EReference)featureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getFeatures()
	{
		return featuresEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getFeatures_Features()
	{
		return (EReference)featuresEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getInstallableUnits()
	{
		return installableUnitsEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getInstallableUnits_BundleContainer()
	{
		return (EReference)installableUnitsEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getInstallableUnits_CategoryContainer()
	{
		return (EReference)installableUnitsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getInstallableUnits_FeatureContainer()
	{
		return (EReference)installableUnitsEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getInstallableUnits_MiscellaneousContainer()
	{
		return (EReference)installableUnitsEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getInstallableUnits_ProductContainer()
	{
		return (EReference)installableUnitsEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getIUPresentation()
	{
		return iuPresentationEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getIUPresentation_Description()
	{
		return (EAttribute)iuPresentationEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getIUPresentation_Id()
	{
		return (EAttribute)iuPresentationEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getIUPresentation_Name()
	{
		return (EAttribute)iuPresentationEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getIUPresentation_Version()
	{
		return (EAttribute)iuPresentationEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMetadataRepositoryStructuredView()
	{
		return metadataRepositoryStructuredViewEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMetadataRepositoryStructuredView_InstallableUnitList()
	{
		return (EReference)metadataRepositoryStructuredViewEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EAttribute getMetadataRepositoryStructuredView_Name()
	{
		return (EAttribute)metadataRepositoryStructuredViewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMetadataRepositoryStructuredView_Properties()
	{
		return (EReference)metadataRepositoryStructuredViewEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getMiscellaneous()
	{
		return miscellaneousEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getMiscellaneous_Others()
	{
		return (EReference)miscellaneousEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getOtherIU()
	{
		return otherIUEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getOtherIU_Details()
	{
		return (EReference)otherIUEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public P2viewFactory getP2viewFactory()
	{
		return (P2viewFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProduct()
	{
		return productEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProduct_BundleContainer()
	{
		return (EReference)productEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProduct_Details()
	{
		return (EReference)productEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProduct_FeatureContainer()
	{
		return (EReference)productEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProducts()
	{
		return productsEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProducts_Products()
	{
		return (EReference)productsEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EClass getProperties()
	{
		return propertiesEClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EReference getProperties_PropertyMap()
	{
		return (EReference)propertiesEClass.getEStructuralFeatures().get(0);
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
		P2Package theP2Package = (P2Package)EPackage.Registry.INSTANCE.getEPackage(P2Package.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		categoryEClass.getESuperTypes().add(this.getIUPresentation());
		featureEClass.getESuperTypes().add(this.getIUPresentation());
		productEClass.getESuperTypes().add(this.getIUPresentation());
		bundleEClass.getESuperTypes().add(this.getIUPresentation());
		otherIUEClass.getESuperTypes().add(this.getIUPresentation());

		// Initialize classes and features; add operations and parameters
		initEClass(metadataRepositoryStructuredViewEClass, MetadataRepositoryStructuredView.class,
				"MetadataRepositoryStructuredView", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getMetadataRepositoryStructuredView_Name(), ecorePackage.getEString(), "name", null, 0, 1,
				MetadataRepositoryStructuredView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE,
				!IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetadataRepositoryStructuredView_InstallableUnitList(), this.getInstallableUnits(), null,
				"installableUnitList", null, 0, 1, MetadataRepositoryStructuredView.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, !IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getMetadataRepositoryStructuredView_Properties(), this.getProperties(), null, "properties",
				null, 0, 1, MetadataRepositoryStructuredView.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE,
				IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(installableUnitsEClass, InstallableUnits.class, "InstallableUnits", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInstallableUnits_CategoryContainer(), this.getCategories(), null, "categoryContainer", null,
				0, 1, InstallableUnits.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnits_FeatureContainer(), this.getFeatures(), null, "featureContainer", null, 0,
				1, InstallableUnits.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnits_ProductContainer(), this.getProducts(), null, "productContainer", null, 0,
				1, InstallableUnits.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE,
				IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnits_BundleContainer(), this.getBundles(), null, "bundleContainer", null, 0, 1,
				InstallableUnits.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInstallableUnits_MiscellaneousContainer(), this.getMiscellaneous(), null,
				"miscellaneousContainer", null, 0, 1, InstallableUnits.class, !IS_TRANSIENT, !IS_VOLATILE,
				IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(installableUnitsEClass, this.getCategories(), "getNotNullCategoryContainer", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		addEOperation(installableUnitsEClass, this.getFeatures(), "getNotNullFeatureContainer", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		addEOperation(installableUnitsEClass, this.getProducts(), "getNotNullProductContainer", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		addEOperation(installableUnitsEClass, this.getBundles(), "getNotNullBundleContainer", 0, 1, IS_UNIQUE,
				IS_ORDERED);

		addEOperation(installableUnitsEClass, this.getMiscellaneous(), "getNotNullMiscellaneousContainer", 0, 1,
				IS_UNIQUE, IS_ORDERED);

		initEClass(categoriesEClass, Categories.class, "Categories", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCategories_Categories(), this.getCategory(), null, "categories", null, 0, -1,
				Categories.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(featuresEClass, Features.class, "Features", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeatures_Features(), this.getFeature(), null, "features", null, 0, -1, Features.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(productsEClass, Products.class, "Products", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProducts_Products(), this.getProduct(), null, "products", null, 0, -1, Products.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(bundlesEClass, Bundles.class, "Bundles", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBundles_Bundles(), this.getBundle(), null, "bundles", null, 0, -1, Bundles.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(miscellaneousEClass, Miscellaneous.class, "Miscellaneous", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getMiscellaneous_Others(), this.getOtherIU(), null, "others", null, 0, -1, Miscellaneous.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(iuPresentationEClass, IUPresentation.class, "IUPresentation", IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIUPresentation_Id(), ecorePackage.getEString(), "id", null, 0, 1, IUPresentation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIUPresentation_Version(), theP2Package.getVersion(), "version", null, 0, 1,
				IUPresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);
		initEAttribute(getIUPresentation_Name(), ecorePackage.getEString(), "name", null, 0, 1, IUPresentation.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getIUPresentation_Description(), ecorePackage.getEString(), "description", null, 0, 1,
				IUPresentation.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE,
				!IS_DERIVED, IS_ORDERED);

		initEClass(categoryEClass, Category.class, "Category", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCategory_CategoryContainer(), this.getCategories(), null, "categoryContainer", null, 0, 1,
				Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCategory_FeatureContainer(), this.getFeatures(), null, "featureContainer", null, 0, 1,
				Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCategory_ProductContainer(), this.getProducts(), null, "productContainer", null, 0, 1,
				Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCategory_BundleContainer(), this.getBundles(), null, "bundleContainer", null, 0, 1,
				Category.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCategory_Details(), this.getDetails(), null, "details", null, 0, 1, Category.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(categoryEClass, this.getCategories(), "getNotNullCategoryContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(categoryEClass, this.getFeatures(), "getNotNullFeatureContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(categoryEClass, this.getProducts(), "getNotNullProductContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(categoryEClass, this.getBundles(), "getNotNullBundleContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(categoryEClass, this.getDetails(), "getNotNullDetails", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(featureEClass, Feature.class, "Feature", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getFeature_FeatureContainer(), this.getFeatures(), null, "featureContainer", null, 0, 1,
				Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeature_BundleContainer(), this.getBundles(), null, "bundleContainer", null, 0, 1,
				Feature.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getFeature_Details(), this.getDetails(), null, "details", null, 0, 1, Feature.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(featureEClass, this.getFeatures(), "getNotNullFeatureContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(featureEClass, this.getBundles(), "getNotNullBundleContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(featureEClass, this.getDetails(), "getNotNullDetails", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(productEClass, Product.class, "Product", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProduct_FeatureContainer(), this.getFeatures(), null, "featureContainer", null, 0, 1,
				Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduct_BundleContainer(), this.getBundles(), null, "bundleContainer", null, 0, 1,
				Product.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getProduct_Details(), this.getDetails(), null, "details", null, 0, 1, Product.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(productEClass, this.getFeatures(), "getNotNullFeatureContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(productEClass, this.getBundles(), "getNotNullBundleContainer", 0, 1, IS_UNIQUE, IS_ORDERED);

		addEOperation(productEClass, this.getDetails(), "getNotNullDetails", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(bundleEClass, Bundle.class, "Bundle", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getBundle_Details(), this.getDetails(), null, "details", null, 0, 1, Bundle.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(bundleEClass, this.getDetails(), "getNotNullDetails", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(otherIUEClass, OtherIU.class, "OtherIU", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getOtherIU_Details(), this.getDetails(), null, "details", null, 0, 1, OtherIU.class,
				!IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE,
				IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		addEOperation(otherIUEClass, this.getDetails(), "getNotNullDetails", 0, 1, IS_UNIQUE, IS_ORDERED);

		initEClass(detailsEClass, Details.class, "Details", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getDetails_InstallableUnit(), theP2Package.getInstallableUnit(), null, "installableUnit", null,
				0, 1, Details.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(propertiesEClass, Properties.class, "Properties", !IS_ABSTRACT, !IS_INTERFACE,
				IS_GENERATED_INSTANCE_CLASS);
		initEReference(getProperties_PropertyMap(), theP2Package.getProperty(), null, "propertyMap", null, 0, -1,
				Properties.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES,
				!IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} // P2viewPackageImpl
