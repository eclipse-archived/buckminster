/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view;

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
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewFactory
 * @model kind="package"
 * @generated
 */
public interface P2viewPackage extends EPackage
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
		 * The meta object literal for the '
		 * {@link org.eclipse.buckminster.aggregator.p2view.impl.MetadataRepositoryStructuredViewImpl
		 * <em>Metadata Repository Structured View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.MetadataRepositoryStructuredViewImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getMetadataRepositoryStructuredView()
		 * @generated
		 */
		EClass METADATA_REPOSITORY_STRUCTURED_VIEW = eINSTANCE.getMetadataRepositoryStructuredView();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute METADATA_REPOSITORY_STRUCTURED_VIEW__NAME = eINSTANCE.getMetadataRepositoryStructuredView_Name();

		/**
		 * The meta object literal for the '<em><b>Installable Unit List</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST = eINSTANCE.getMetadataRepositoryStructuredView_InstallableUnitList();

		/**
		 * The meta object literal for the '<em><b>Properties</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES = eINSTANCE.getMetadataRepositoryStructuredView_Properties();

		/**
		 * The meta object literal for the '<em><b>Metadata Repository</b></em>' containment reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference METADATA_REPOSITORY_STRUCTURED_VIEW__METADATA_REPOSITORY = eINSTANCE.getMetadataRepositoryStructuredView_MetadataRepository();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.InstallableUnitsImpl
		 * <em>Installable Units</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.InstallableUnitsImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getInstallableUnits()
		 * @generated
		 */
		EClass INSTALLABLE_UNITS = eINSTANCE.getInstallableUnits();

		/**
		 * The meta object literal for the '<em><b>Category Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNITS__CATEGORY_CONTAINER = eINSTANCE.getInstallableUnits_CategoryContainer();

		/**
		 * The meta object literal for the '<em><b>Feature Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNITS__FEATURE_CONTAINER = eINSTANCE.getInstallableUnits_FeatureContainer();

		/**
		 * The meta object literal for the '<em><b>Product Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNITS__PRODUCT_CONTAINER = eINSTANCE.getInstallableUnits_ProductContainer();

		/**
		 * The meta object literal for the '<em><b>Bundle Container</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNITS__BUNDLE_CONTAINER = eINSTANCE.getInstallableUnits_BundleContainer();

		/**
		 * The meta object literal for the '<em><b>Fragment Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNITS__FRAGMENT_CONTAINER = eINSTANCE.getInstallableUnits_FragmentContainer();

		/**
		 * The meta object literal for the '<em><b>Miscellaneous Container</b></em>' reference feature. <!--
		 * begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference INSTALLABLE_UNITS__MISCELLANEOUS_CONTAINER = eINSTANCE.getInstallableUnits_MiscellaneousContainer();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoriesImpl
		 * <em>Categories</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.CategoriesImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getCategories()
		 * @generated
		 */
		EClass CATEGORIES = eINSTANCE.getCategories();

		/**
		 * The meta object literal for the '<em><b>Categories</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CATEGORIES__CATEGORIES = eINSTANCE.getCategories_Categories();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.FeaturesImpl
		 * <em>Features</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.FeaturesImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getFeatures()
		 * @generated
		 */
		EClass FEATURES = eINSTANCE.getFeatures();

		/**
		 * The meta object literal for the '<em><b>Features</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FEATURES__FEATURES = eINSTANCE.getFeatures_Features();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.ProductsImpl
		 * <em>Products</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.ProductsImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getProducts()
		 * @generated
		 */
		EClass PRODUCTS = eINSTANCE.getProducts();

		/**
		 * The meta object literal for the '<em><b>Products</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PRODUCTS__PRODUCTS = eINSTANCE.getProducts_Products();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.BundlesImpl
		 * <em>Bundles</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.BundlesImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getBundles()
		 * @generated
		 */
		EClass BUNDLES = eINSTANCE.getBundles();

		/**
		 * The meta object literal for the '<em><b>Bundles</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference BUNDLES__BUNDLES = eINSTANCE.getBundles_Bundles();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.FragmentsImpl
		 * <em>Fragments</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.FragmentsImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getFragments()
		 * @generated
		 */
		EClass FRAGMENTS = eINSTANCE.getFragments();

		/**
		 * The meta object literal for the '<em><b>Fragments</b></em>' reference list feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FRAGMENTS__FRAGMENTS = eINSTANCE.getFragments_Fragments();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.MiscellaneousImpl
		 * <em>Miscellaneous</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.MiscellaneousImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getMiscellaneous()
		 * @generated
		 */
		EClass MISCELLANEOUS = eINSTANCE.getMiscellaneous();

		/**
		 * The meta object literal for the '<em><b>Others</b></em>' reference list feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference MISCELLANEOUS__OTHERS = eINSTANCE.getMiscellaneous_Others();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationImpl
		 * <em>IU Presentation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getIUPresentation()
		 * @generated
		 */
		EClass IU_PRESENTATION = eINSTANCE.getIUPresentation();

		/**
		 * The meta object literal for the '<em><b>Id</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IU_PRESENTATION__ID = eINSTANCE.getIUPresentation_Id();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IU_PRESENTATION__VERSION = eINSTANCE.getIUPresentation_Version();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IU_PRESENTATION__NAME = eINSTANCE.getIUPresentation_Name();

		/**
		 * The meta object literal for the '<em><b>Label</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IU_PRESENTATION__LABEL = eINSTANCE.getIUPresentation_Label();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IU_PRESENTATION__DESCRIPTION = eINSTANCE.getIUPresentation_Description();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EAttribute IU_PRESENTATION__TYPE = eINSTANCE.getIUPresentation_Type();

		/**
		 * The meta object literal for the '<em><b>Iu</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference IU_PRESENTATION__IU = eINSTANCE.getIUPresentation_Iu();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl
		 * <em>Category</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getCategory()
		 * @generated
		 */
		EClass CATEGORY = eINSTANCE.getCategory();

		/**
		 * The meta object literal for the '<em><b>Category Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CATEGORY__CATEGORY_CONTAINER = eINSTANCE.getCategory_CategoryContainer();

		/**
		 * The meta object literal for the '<em><b>Feature Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CATEGORY__FEATURE_CONTAINER = eINSTANCE.getCategory_FeatureContainer();

		/**
		 * The meta object literal for the '<em><b>Product Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CATEGORY__PRODUCT_CONTAINER = eINSTANCE.getCategory_ProductContainer();

		/**
		 * The meta object literal for the '<em><b>Bundle Container</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CATEGORY__BUNDLE_CONTAINER = eINSTANCE.getCategory_BundleContainer();

		/**
		 * The meta object literal for the '<em><b>Fragment Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CATEGORY__FRAGMENT_CONTAINER = eINSTANCE.getCategory_FragmentContainer();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference CATEGORY__DETAILS = eINSTANCE.getCategory_Details();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.FeatureImpl
		 * <em>Feature</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.FeatureImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getFeature()
		 * @generated
		 */
		EClass FEATURE = eINSTANCE.getFeature();

		/**
		 * The meta object literal for the '<em><b>Feature Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FEATURE__FEATURE_CONTAINER = eINSTANCE.getFeature_FeatureContainer();

		/**
		 * The meta object literal for the '<em><b>Bundle Container</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FEATURE__BUNDLE_CONTAINER = eINSTANCE.getFeature_BundleContainer();

		/**
		 * The meta object literal for the '<em><b>Fragment Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FEATURE__FRAGMENT_CONTAINER = eINSTANCE.getFeature_FragmentContainer();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference FEATURE__DETAILS = eINSTANCE.getFeature_Details();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.ProductImpl
		 * <em>Product</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.ProductImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getProduct()
		 * @generated
		 */
		EClass PRODUCT = eINSTANCE.getProduct();

		/**
		 * The meta object literal for the '<em><b>Feature Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PRODUCT__FEATURE_CONTAINER = eINSTANCE.getProduct_FeatureContainer();

		/**
		 * The meta object literal for the '<em><b>Bundle Container</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PRODUCT__BUNDLE_CONTAINER = eINSTANCE.getProduct_BundleContainer();

		/**
		 * The meta object literal for the '<em><b>Fragment Container</b></em>' reference feature. <!-- begin-user-doc
		 * --> <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PRODUCT__FRAGMENT_CONTAINER = eINSTANCE.getProduct_FragmentContainer();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PRODUCT__DETAILS = eINSTANCE.getProduct_Details();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.BundleImpl
		 * <em>Bundle</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.BundleImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getBundle()
		 * @generated
		 */
		EClass BUNDLE = eINSTANCE.getBundle();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference BUNDLE__DETAILS = eINSTANCE.getBundle_Details();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.FragmentImpl
		 * <em>Fragment</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.FragmentImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getFragment()
		 * @generated
		 */
		EClass FRAGMENT = eINSTANCE.getFragment();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.OtherIUImpl
		 * <em>Other IU</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.OtherIUImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getOtherIU()
		 * @generated
		 */
		EClass OTHER_IU = eINSTANCE.getOtherIU();

		/**
		 * The meta object literal for the '<em><b>Details</b></em>' reference feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference OTHER_IU__DETAILS = eINSTANCE.getOtherIU_Details();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.DetailsImpl
		 * <em>Details</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.DetailsImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getDetails()
		 * @generated
		 */
		EClass DETAILS = eINSTANCE.getDetails();

		/**
		 * The meta object literal for the '<em><b>Installable Unit</b></em>' reference feature. <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * 
		 * @generated
		 */
		EReference DETAILS__INSTALLABLE_UNIT = eINSTANCE.getDetails_InstallableUnit();

		/**
		 * The meta object literal for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.PropertiesImpl
		 * <em>Properties</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
		 * 
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.PropertiesImpl
		 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getProperties()
		 * @generated
		 */
		EClass PROPERTIES = eINSTANCE.getProperties();

		/**
		 * The meta object literal for the '<em><b>Property Map</b></em>' map feature. <!-- begin-user-doc --> <!--
		 * end-user-doc -->
		 * 
		 * @generated
		 */
		EReference PROPERTIES__PROPERTY_MAP = eINSTANCE.getProperties_PropertyMap();

	}

	/**
	 * The package name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNAME = "p2view";

	/**
	 * The package namespace URI. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_URI = "http://www.eclipse.org/buckminster/2009/aggregator/p2view";

	/**
	 * The package namespace name. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	String eNS_PREFIX = "p2view";

	/**
	 * The singleton instance of the package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	P2viewPackage eINSTANCE = org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl.init();

	/**
	 * The meta object id for the '
	 * {@link org.eclipse.buckminster.aggregator.p2view.impl.MetadataRepositoryStructuredViewImpl
	 * <em>Metadata Repository Structured View</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.MetadataRepositoryStructuredViewImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getMetadataRepositoryStructuredView()
	 * @generated
	 */
	int METADATA_REPOSITORY_STRUCTURED_VIEW = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_STRUCTURED_VIEW__NAME = 0;

	/**
	 * The feature id for the '<em><b>Installable Unit List</b></em>' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_STRUCTURED_VIEW__INSTALLABLE_UNIT_LIST = 1;

	/**
	 * The feature id for the '<em><b>Properties</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_STRUCTURED_VIEW__PROPERTIES = 2;

	/**
	 * The feature id for the '<em><b>Metadata Repository</b></em>' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_STRUCTURED_VIEW__METADATA_REPOSITORY = 3;

	/**
	 * The number of structural features of the '<em>Metadata Repository Structured View</em>' class. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int METADATA_REPOSITORY_STRUCTURED_VIEW_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.InstallableUnitsImpl
	 * <em>Installable Units</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.InstallableUnitsImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getInstallableUnits()
	 * @generated
	 */
	int INSTALLABLE_UNITS = 1;

	/**
	 * The feature id for the '<em><b>Category Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNITS__CATEGORY_CONTAINER = 0;

	/**
	 * The feature id for the '<em><b>Feature Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNITS__FEATURE_CONTAINER = 1;

	/**
	 * The feature id for the '<em><b>Product Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNITS__PRODUCT_CONTAINER = 2;

	/**
	 * The feature id for the '<em><b>Bundle Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNITS__BUNDLE_CONTAINER = 3;

	/**
	 * The feature id for the '<em><b>Fragment Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNITS__FRAGMENT_CONTAINER = 4;

	/**
	 * The feature id for the '<em><b>Miscellaneous Container</b></em>' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNITS__MISCELLANEOUS_CONTAINER = 5;

	/**
	 * The number of structural features of the '<em>Installable Units</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int INSTALLABLE_UNITS_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoriesImpl
	 * <em>Categories</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.CategoriesImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getCategories()
	 * @generated
	 */
	int CATEGORIES = 2;

	/**
	 * The feature id for the '<em><b>Categories</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORIES__CATEGORIES = 0;

	/**
	 * The number of structural features of the '<em>Categories</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORIES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.FeaturesImpl <em>Features</em>}
	 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.FeaturesImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getFeatures()
	 * @generated
	 */
	int FEATURES = 3;

	/**
	 * The feature id for the '<em><b>Features</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURES__FEATURES = 0;

	/**
	 * The number of structural features of the '<em>Features</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.ProductsImpl <em>Products</em>}
	 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.ProductsImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getProducts()
	 * @generated
	 */
	int PRODUCTS = 4;

	/**
	 * The feature id for the '<em><b>Products</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS__PRODUCTS = 0;

	/**
	 * The number of structural features of the '<em>Products</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCTS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.BundlesImpl <em>Bundles</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.BundlesImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getBundles()
	 * @generated
	 */
	int BUNDLES = 5;

	/**
	 * The feature id for the '<em><b>Bundles</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLES__BUNDLES = 0;

	/**
	 * The number of structural features of the '<em>Bundles</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLES_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.FragmentsImpl
	 * <em>Fragments</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.FragmentsImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getFragments()
	 * @generated
	 */
	int FRAGMENTS = 6;

	/**
	 * The feature id for the '<em><b>Fragments</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENTS__FRAGMENTS = 0;

	/**
	 * The number of structural features of the '<em>Fragments</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENTS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.MiscellaneousImpl
	 * <em>Miscellaneous</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.MiscellaneousImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getMiscellaneous()
	 * @generated
	 */
	int MISCELLANEOUS = 7;

	/**
	 * The feature id for the '<em><b>Others</b></em>' reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MISCELLANEOUS__OTHERS = 0;

	/**
	 * The number of structural features of the '<em>Miscellaneous</em>' class. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int MISCELLANEOUS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationImpl
	 * <em>IU Presentation</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.IUPresentationImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getIUPresentation()
	 * @generated
	 */
	int IU_PRESENTATION = 8;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IU_PRESENTATION__ID = 0;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IU_PRESENTATION__VERSION = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IU_PRESENTATION__NAME = 2;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IU_PRESENTATION__LABEL = 3;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IU_PRESENTATION__DESCRIPTION = 4;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IU_PRESENTATION__TYPE = 5;

	/**
	 * The feature id for the '<em><b>Iu</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IU_PRESENTATION__IU = 6;

	/**
	 * The number of structural features of the '<em>IU Presentation</em>' class. <!-- begin-user-doc --> <!-- =======
	 * The number of structural features of the '<em>IU Presentation</em>' class. <!-- begin-user-doc --> <!-- >>>>>>>
	 * .r10620 end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int IU_PRESENTATION_FEATURE_COUNT = 7;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl <em>Category</em>}
	 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getCategory()
	 * @generated
	 */
	int CATEGORY = 9;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__ID = IU_PRESENTATION__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__VERSION = IU_PRESENTATION__VERSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__NAME = IU_PRESENTATION__NAME;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__LABEL = IU_PRESENTATION__LABEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__DESCRIPTION = IU_PRESENTATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__TYPE = IU_PRESENTATION__TYPE;

	/**
	 * The feature id for the '<em><b>Iu</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__IU = IU_PRESENTATION__IU;

	/**
	 * The feature id for the '<em><b>Category Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__CATEGORY_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Feature Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__FEATURE_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Product Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__PRODUCT_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Bundle Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__BUNDLE_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Fragment Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__FRAGMENT_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Details</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY__DETAILS = IU_PRESENTATION_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Category</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int CATEGORY_FEATURE_COUNT = IU_PRESENTATION_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.FeatureImpl <em>Feature</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.FeatureImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getFeature()
	 * @generated
	 */
	int FEATURE = 10;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__ID = IU_PRESENTATION__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__VERSION = IU_PRESENTATION__VERSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__NAME = IU_PRESENTATION__NAME;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__LABEL = IU_PRESENTATION__LABEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__DESCRIPTION = IU_PRESENTATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__TYPE = IU_PRESENTATION__TYPE;

	/**
	 * The feature id for the '<em><b>Iu</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__IU = IU_PRESENTATION__IU;

	/**
	 * The feature id for the '<em><b>Feature Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__FEATURE_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bundle Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__BUNDLE_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fragment Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__FRAGMENT_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE__DETAILS = IU_PRESENTATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Feature</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FEATURE_FEATURE_COUNT = IU_PRESENTATION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.ProductImpl <em>Product</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.ProductImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getProduct()
	 * @generated
	 */
	int PRODUCT = 11;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__ID = IU_PRESENTATION__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__VERSION = IU_PRESENTATION__VERSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__NAME = IU_PRESENTATION__NAME;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__LABEL = IU_PRESENTATION__LABEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__DESCRIPTION = IU_PRESENTATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__TYPE = IU_PRESENTATION__TYPE;

	/**
	 * The feature id for the '<em><b>Iu</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__IU = IU_PRESENTATION__IU;

	/**
	 * The feature id for the '<em><b>Feature Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__FEATURE_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Bundle Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__BUNDLE_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Fragment Container</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__FRAGMENT_CONTAINER = IU_PRESENTATION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Details</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT__DETAILS = IU_PRESENTATION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Product</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PRODUCT_FEATURE_COUNT = IU_PRESENTATION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.BundleImpl <em>Bundle</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.BundleImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getBundle()
	 * @generated
	 */
	int BUNDLE = 12;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__ID = IU_PRESENTATION__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__VERSION = IU_PRESENTATION__VERSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__NAME = IU_PRESENTATION__NAME;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__LABEL = IU_PRESENTATION__LABEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__DESCRIPTION = IU_PRESENTATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__TYPE = IU_PRESENTATION__TYPE;

	/**
	 * The feature id for the '<em><b>Iu</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__IU = IU_PRESENTATION__IU;

	/**
	 * The feature id for the '<em><b>Details</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE__DETAILS = IU_PRESENTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Bundle</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int BUNDLE_FEATURE_COUNT = IU_PRESENTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.FragmentImpl <em>Fragment</em>}
	 * ' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.FragmentImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getFragment()
	 * @generated
	 */
	int FRAGMENT = 13;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__ID = BUNDLE__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__VERSION = BUNDLE__VERSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__NAME = BUNDLE__NAME;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__LABEL = BUNDLE__LABEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__DESCRIPTION = BUNDLE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__TYPE = BUNDLE__TYPE;

	/**
	 * The feature id for the '<em><b>Iu</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__IU = BUNDLE__IU;

	/**
	 * The feature id for the '<em><b>Details</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT__DETAILS = BUNDLE__DETAILS;

	/**
	 * The number of structural features of the '<em>Fragment</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int FRAGMENT_FEATURE_COUNT = BUNDLE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.OtherIUImpl <em>Other IU</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.OtherIUImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getOtherIU()
	 * @generated
	 */
	int OTHER_IU = 14;

	/**
	 * The feature id for the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU__ID = IU_PRESENTATION__ID;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU__VERSION = IU_PRESENTATION__VERSION;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU__NAME = IU_PRESENTATION__NAME;

	/**
	 * The feature id for the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU__LABEL = IU_PRESENTATION__LABEL;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU__DESCRIPTION = IU_PRESENTATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU__TYPE = IU_PRESENTATION__TYPE;

	/**
	 * The feature id for the '<em><b>Iu</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU__IU = IU_PRESENTATION__IU;

	/**
	 * The feature id for the '<em><b>Details</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU__DETAILS = IU_PRESENTATION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Other IU</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int OTHER_IU_FEATURE_COUNT = IU_PRESENTATION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.DetailsImpl <em>Details</em>}'
	 * class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.DetailsImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getDetails()
	 * @generated
	 */
	int DETAILS = 15;

	/**
	 * The feature id for the '<em><b>Installable Unit</b></em>' reference. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DETAILS__INSTALLABLE_UNIT = 0;

	/**
	 * The number of structural features of the '<em>Details</em>' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int DETAILS_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link org.eclipse.buckminster.aggregator.p2view.impl.PropertiesImpl
	 * <em>Properties</em>}' class. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.PropertiesImpl
	 * @see org.eclipse.buckminster.aggregator.p2view.impl.P2viewPackageImpl#getProperties()
	 * @generated
	 */
	int PROPERTIES = 16;

	/**
	 * The feature id for the '<em><b>Property Map</b></em>' map. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTIES__PROPERTY_MAP = 0;

	/**
	 * The number of structural features of the '<em>Properties</em>' class. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 * @ordered
	 */
	int PROPERTIES_FEATURE_COUNT = 1;

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Bundle <em>Bundle</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Bundle</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Bundle
	 * @generated
	 */
	EClass getBundle();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.aggregator.p2view.Bundle#getDetails
	 * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Details</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Bundle#getDetails()
	 * @see #getBundle()
	 * @generated
	 */
	EReference getBundle_Details();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Bundles <em>Bundles</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Bundles</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Bundles
	 * @generated
	 */
	EClass getBundles();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Bundles#getBundles <em>Bundles</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Bundles</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Bundles#getBundles()
	 * @see #getBundles()
	 * @generated
	 */
	EReference getBundles_Bundles();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Categories
	 * <em>Categories</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Categories</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Categories
	 * @generated
	 */
	EClass getCategories();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Categories#getCategories <em>Categories</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Categories</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Categories#getCategories()
	 * @see #getCategories()
	 * @generated
	 */
	EReference getCategories_Categories();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Category <em>Category</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Category</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Category
	 * @generated
	 */
	EClass getCategory();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Category#getBundleContainer <em>Bundle Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Bundle Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Category#getBundleContainer()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_BundleContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Category#getCategoryContainer <em>Category Container</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Category Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Category#getCategoryContainer()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_CategoryContainer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.aggregator.p2view.Category#getDetails
	 * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Details</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Category#getDetails()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_Details();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Category#getFeatureContainer <em>Feature Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Feature Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Category#getFeatureContainer()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_FeatureContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Category#getFragmentContainer <em>Fragment Container</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Fragment Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Category#getFragmentContainer()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_FragmentContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Category#getProductContainer <em>Product Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Product Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Category#getProductContainer()
	 * @see #getCategory()
	 * @generated
	 */
	EReference getCategory_ProductContainer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Details <em>Details</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Details</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Details
	 * @generated
	 */
	EClass getDetails();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Details#getInstallableUnit <em>Installable Unit</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Installable Unit</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Details#getInstallableUnit()
	 * @see #getDetails()
	 * @generated
	 */
	EReference getDetails_InstallableUnit();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Feature <em>Feature</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Feature</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Feature
	 * @generated
	 */
	EClass getFeature();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Feature#getBundleContainer <em>Bundle Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Bundle Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Feature#getBundleContainer()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_BundleContainer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.aggregator.p2view.Feature#getDetails
	 * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Details</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Feature#getDetails()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_Details();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Feature#getFeatureContainer <em>Feature Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Feature Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Feature#getFeatureContainer()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_FeatureContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Feature#getFragmentContainer <em>Fragment Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Fragment Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Feature#getFragmentContainer()
	 * @see #getFeature()
	 * @generated
	 */
	EReference getFeature_FragmentContainer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Features <em>Features</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Features</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Features
	 * @generated
	 */
	EClass getFeatures();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Features#getFeatures <em>Features</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Features</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Features#getFeatures()
	 * @see #getFeatures()
	 * @generated
	 */
	EReference getFeatures_Features();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Fragment <em>Fragment</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Fragment</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Fragment
	 * @generated
	 */
	EClass getFragment();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Fragments <em>Fragments</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Fragments</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Fragments
	 * @generated
	 */
	EClass getFragments();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Fragments#getFragments <em>Fragments</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Fragments</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Fragments#getFragments()
	 * @see #getFragments()
	 * @generated
	 */
	EReference getFragments_Fragments();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.InstallableUnits
	 * <em>Installable Units</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Installable Units</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.InstallableUnits
	 * @generated
	 */
	EClass getInstallableUnits();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getBundleContainer <em>Bundle Container</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Bundle Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getBundleContainer()
	 * @see #getInstallableUnits()
	 * @generated
	 */
	EReference getInstallableUnits_BundleContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getCategoryContainer
	 * <em>Category Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Category Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getCategoryContainer()
	 * @see #getInstallableUnits()
	 * @generated
	 */
	EReference getInstallableUnits_CategoryContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getFeatureContainer <em>Feature Container</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Feature Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getFeatureContainer()
	 * @see #getInstallableUnits()
	 * @generated
	 */
	EReference getInstallableUnits_FeatureContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getFragmentContainer
	 * <em>Fragment Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Fragment Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getFragmentContainer()
	 * @see #getInstallableUnits()
	 * @generated
	 */
	EReference getInstallableUnits_FragmentContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getMiscellaneousContainer
	 * <em>Miscellaneous Container</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Miscellaneous Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getMiscellaneousContainer()
	 * @see #getInstallableUnits()
	 * @generated
	 */
	EReference getInstallableUnits_MiscellaneousContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getProductContainer <em>Product Container</em>}
	 * '. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Product Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.InstallableUnits#getProductContainer()
	 * @see #getInstallableUnits()
	 * @generated
	 */
	EReference getInstallableUnits_ProductContainer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.IUPresentation
	 * <em>IU Presentation</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>IU Presentation</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation
	 * @generated
	 */
	EClass getIUPresentation();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUPresentation#getDescription <em>Description</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation#getDescription()
	 * @see #getIUPresentation()
	 * @generated
	 */
	EAttribute getIUPresentation_Description();

	/**
	 * Returns the meta object for the attribute '{@link org.eclipse.buckminster.aggregator.p2view.IUPresentation#getId
	 * <em>Id</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Id</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation#getId()
	 * @see #getIUPresentation()
	 * @generated
	 */
	EAttribute getIUPresentation_Id();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.aggregator.p2view.IUPresentation#getIu
	 * <em>Iu</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Iu</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation#getIu()
	 * @see #getIUPresentation()
	 * @generated
	 */
	EReference getIUPresentation_Iu();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUPresentation#getLabel <em>Label</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Label</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation#getLabel()
	 * @see #getIUPresentation()
	 * @generated
	 */
	EAttribute getIUPresentation_Label();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUPresentation#getName <em>Name</em>}'. <!-- begin-user-doc -->
	 * ======= Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUPresentation#getName <em>Name</em>}'. <!-- begin-user-doc -->
	 * >>>>>>> .r10620 <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation#getName()
	 * @see #getIUPresentation()
	 * @generated
	 */
	EAttribute getIUPresentation_Name();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUPresentation#getType <em>Type</em>}'. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation#getType()
	 * @see #getIUPresentation()
	 * @generated
	 */
	EAttribute getIUPresentation_Type();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUPresentation#getVersion <em>Version</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation#getVersion()
	 * @see #getIUPresentation()
	 * @generated
	 */
	EAttribute getIUPresentation_Version();

	/**
	 * Returns the meta object for class '
	 * {@link org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView
	 * <em>Metadata Repository Structured View</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Metadata Repository Structured View</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView
	 * @generated
	 */
	EClass getMetadataRepositoryStructuredView();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView#getInstallableUnitList
	 * <em>Installable Unit List</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Installable Unit List</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView#getInstallableUnitList()
	 * @see #getMetadataRepositoryStructuredView()
	 * @generated
	 */
	EReference getMetadataRepositoryStructuredView_InstallableUnitList();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView#getMetadataRepository
	 * <em>Metadata Repository</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Metadata Repository</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView#getMetadataRepository()
	 * @see #getMetadataRepositoryStructuredView()
	 * @generated
	 */
	EReference getMetadataRepositoryStructuredView_MetadataRepository();

	/**
	 * Returns the meta object for the attribute '
	 * {@link org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView#getName <em>Name</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView#getName()
	 * @see #getMetadataRepositoryStructuredView()
	 * @generated
	 */
	EAttribute getMetadataRepositoryStructuredView_Name();

	/**
	 * Returns the meta object for the containment reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView#getProperties
	 * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the containment reference '<em>Properties</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView#getProperties()
	 * @see #getMetadataRepositoryStructuredView()
	 * @generated
	 */
	EReference getMetadataRepositoryStructuredView_Properties();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Miscellaneous
	 * <em>Miscellaneous</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Miscellaneous</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Miscellaneous
	 * @generated
	 */
	EClass getMiscellaneous();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Miscellaneous#getOthers <em>Others</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Others</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Miscellaneous#getOthers()
	 * @see #getMiscellaneous()
	 * @generated
	 */
	EReference getMiscellaneous_Others();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.OtherIU <em>Other IU</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Other IU</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.OtherIU
	 * @generated
	 */
	EClass getOtherIU();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.aggregator.p2view.OtherIU#getDetails
	 * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Details</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.OtherIU#getDetails()
	 * @see #getOtherIU()
	 * @generated
	 */
	EReference getOtherIU_Details();

	/**
	 * Returns the factory that creates the instances of the model. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	P2viewFactory getP2viewFactory();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Product <em>Product</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Product</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Product
	 * @generated
	 */
	EClass getProduct();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Product#getBundleContainer <em>Bundle Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Bundle Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Product#getBundleContainer()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_BundleContainer();

	/**
	 * Returns the meta object for the reference '{@link org.eclipse.buckminster.aggregator.p2view.Product#getDetails
	 * <em>Details</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Details</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Product#getDetails()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_Details();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Product#getFeatureContainer <em>Feature Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Feature Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Product#getFeatureContainer()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_FeatureContainer();

	/**
	 * Returns the meta object for the reference '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Product#getFragmentContainer <em>Fragment Container</em>}'. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference '<em>Fragment Container</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Product#getFragmentContainer()
	 * @see #getProduct()
	 * @generated
	 */
	EReference getProduct_FragmentContainer();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Products <em>Products</em>}'.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Products</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Products
	 * @generated
	 */
	EClass getProducts();

	/**
	 * Returns the meta object for the reference list '
	 * {@link org.eclipse.buckminster.aggregator.p2view.Products#getProducts <em>Products</em>}'. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the reference list '<em>Products</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Products#getProducts()
	 * @see #getProducts()
	 * @generated
	 */
	EReference getProducts_Products();

	/**
	 * Returns the meta object for class '{@link org.eclipse.buckminster.aggregator.p2view.Properties
	 * <em>Properties</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for class '<em>Properties</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Properties
	 * @generated
	 */
	EClass getProperties();

	/**
	 * Returns the meta object for the map '{@link org.eclipse.buckminster.aggregator.p2view.Properties#getPropertyMap
	 * <em>Property Map</em>}'. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return the meta object for the map '<em>Property Map</em>'.
	 * @see org.eclipse.buckminster.aggregator.p2view.Properties#getPropertyMap()
	 * @see #getProperties()
	 * @generated
	 */
	EReference getProperties_PropertyMap();

} // P2viewPackage
