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

import org.eclipse.buckminster.aggregator.p2view.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class P2viewFactoryImpl extends EFactoryImpl implements P2viewFactory
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static P2viewPackage getPackage()
	{
		return P2viewPackage.eINSTANCE;
	}

	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static P2viewFactory init()
	{
		try
		{
			P2viewFactory theP2viewFactory = (P2viewFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/buckminster/2009/aggregator/p2view");
			if(theP2viewFactory != null)
			{
				return theP2viewFactory;
			}
		}
		catch(Exception exception)
		{
			EcorePlugin.INSTANCE.log(exception);
		}
		return new P2viewFactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public P2viewFactoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass)
	{
		switch(eClass.getClassifierID())
		{
		case P2viewPackage.METADATA_REPOSITORY_STRUCTURED_VIEW:
			return createMetadataRepositoryStructuredView();
		case P2viewPackage.INSTALLABLE_UNITS:
			return createInstallableUnits();
		case P2viewPackage.CATEGORIES:
			return createCategories();
		case P2viewPackage.FEATURES:
			return createFeatures();
		case P2viewPackage.PRODUCTS:
			return createProducts();
		case P2viewPackage.BUNDLES:
			return createBundles();
		case P2viewPackage.MISCELLANEOUS:
			return createMiscellaneous();
		case P2viewPackage.CATEGORY:
			return createCategory();
		case P2viewPackage.FEATURE:
			return createFeature();
		case P2viewPackage.PRODUCT:
			return createProduct();
		case P2viewPackage.BUNDLE:
			return createBundle();
		case P2viewPackage.OTHER_IU:
			return createOtherIU();
		case P2viewPackage.DETAILS:
			return createDetails();
		case P2viewPackage.PROPERTIES:
			return createProperties();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Bundle createBundle()
	{
		BundleImpl bundle = new BundleImpl();
		return bundle;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Bundles createBundles()
	{
		BundlesImpl bundles = new BundlesImpl();
		return bundles;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Categories createCategories()
	{
		CategoriesImpl categories = new CategoriesImpl();
		return categories;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Category createCategory()
	{
		CategoryImpl category = new CategoryImpl();
		return category;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Details createDetails()
	{
		DetailsImpl details = new DetailsImpl();
		return details;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Feature createFeature()
	{
		FeatureImpl feature = new FeatureImpl();
		return feature;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Features createFeatures()
	{
		FeaturesImpl features = new FeaturesImpl();
		return features;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InstallableUnits createInstallableUnits()
	{
		InstallableUnitsImpl installableUnits = new InstallableUnitsImpl();
		return installableUnits;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataRepositoryStructuredView createMetadataRepositoryStructuredView()
	{
		MetadataRepositoryStructuredViewImpl metadataRepositoryStructuredView = new MetadataRepositoryStructuredViewImpl();
		return metadataRepositoryStructuredView;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Miscellaneous createMiscellaneous()
	{
		MiscellaneousImpl miscellaneous = new MiscellaneousImpl();
		return miscellaneous;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OtherIU createOtherIU()
	{
		OtherIUImpl otherIU = new OtherIUImpl();
		return otherIU;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Product createProduct()
	{
		ProductImpl product = new ProductImpl();
		return product;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Products createProducts()
	{
		ProductsImpl products = new ProductsImpl();
		return products;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Properties createProperties()
	{
		PropertiesImpl properties = new PropertiesImpl();
		return properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public P2viewPackage getP2viewPackage()
	{
		return (P2viewPackage)getEPackage();
	}

} // P2viewFactoryImpl
