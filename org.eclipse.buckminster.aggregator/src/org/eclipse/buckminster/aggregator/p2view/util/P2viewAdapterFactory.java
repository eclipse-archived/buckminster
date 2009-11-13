/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2view.util;

import org.eclipse.buckminster.aggregator.ChildrenProvider;
import org.eclipse.buckminster.aggregator.LabelProvider;
import org.eclipse.buckminster.aggregator.p2view.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides an adapter <code>createXXX</code>
 * method for each class of the model. <!-- end-user-doc -->
 * 
 * @see org.eclipse.buckminster.aggregator.p2view.P2viewPackage
 * @generated
 */
public class P2viewAdapterFactory extends AdapterFactoryImpl
{
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static P2viewPackage modelPackage;

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected P2viewSwitch<Adapter> modelSwitch = new P2viewSwitch<Adapter>()
	{
		@Override
		public Adapter caseBundle(Bundle object)
		{
			return createBundleAdapter();
		}

		@Override
		public Adapter caseBundles(Bundles object)
		{
			return createBundlesAdapter();
		}

		@Override
		public Adapter caseCategories(Categories object)
		{
			return createCategoriesAdapter();
		}

		@Override
		public Adapter caseCategory(Category object)
		{
			return createCategoryAdapter();
		}

		@Override
		public <T> Adapter caseChildrenProvider(ChildrenProvider<T> object)
		{
			return createChildrenProviderAdapter();
		}

		@Override
		public Adapter caseFeature(Feature object)
		{
			return createFeatureAdapter();
		}

		@Override
		public Adapter caseFeatures(Features object)
		{
			return createFeaturesAdapter();
		}

		@Override
		public Adapter caseFragment(Fragment object)
		{
			return createFragmentAdapter();
		}

		@Override
		public Adapter caseFragments(Fragments object)
		{
			return createFragmentsAdapter();
		}

		@Override
		public Adapter caseInstallableUnits(InstallableUnits object)
		{
			return createInstallableUnitsAdapter();
		}

		@Override
		public Adapter caseIProvidedCapability(IProvidedCapability object)
		{
			return createIProvidedCapabilityAdapter();
		}

		@Override
		public Adapter caseIRequiredCapability(IRequiredCapability object)
		{
			return createIRequiredCapabilityAdapter();
		}

		@Override
		public Adapter caseIUDetails(IUDetails object)
		{
			return createIUDetailsAdapter();
		}

		@Override
		public Adapter caseIUPresentation(IUPresentation object)
		{
			return createIUPresentationAdapter();
		}

		@Override
		public Adapter caseIUPresentationWithDetails(IUPresentationWithDetails object)
		{
			return createIUPresentationWithDetailsAdapter();
		}

		@Override
		public Adapter caseLabelProvider(LabelProvider object)
		{
			return createLabelProviderAdapter();
		}

		@Override
		public Adapter caseMetadataRepositoryStructuredView(MetadataRepositoryStructuredView object)
		{
			return createMetadataRepositoryStructuredViewAdapter();
		}

		@Override
		public Adapter caseMiscellaneous(Miscellaneous object)
		{
			return createMiscellaneousAdapter();
		}

		@Override
		public Adapter caseOtherIU(OtherIU object)
		{
			return createOtherIUAdapter();
		}

		@Override
		public Adapter caseProduct(Product object)
		{
			return createProductAdapter();
		}

		@Override
		public Adapter caseProducts(Products object)
		{
			return createProductsAdapter();
		}

		@Override
		public Adapter caseProperties(Properties object)
		{
			return createPropertiesAdapter();
		}

		@Override
		public Adapter caseProvidedCapabilities(ProvidedCapabilities object)
		{
			return createProvidedCapabilitiesAdapter();
		}

		@Override
		public Adapter caseProvidedCapabilityWrapper(ProvidedCapabilityWrapper object)
		{
			return createProvidedCapabilityWrapperAdapter();
		}

		@Override
		public Adapter caseRequiredCapabilities(RequiredCapabilities object)
		{
			return createRequiredCapabilitiesAdapter();
		}

		@Override
		public Adapter caseRequiredCapabilityWrapper(RequiredCapabilityWrapper object)
		{
			return createRequiredCapabilityWrapperAdapter();
		}

		@Override
		public Adapter caseTouchpoints(Touchpoints object)
		{
			return createTouchpointsAdapter();
		}

		@Override
		public Adapter defaultCase(EObject object)
		{
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public P2viewAdapterFactory()
	{
		if(modelPackage == null)
		{
			modelPackage = P2viewPackage.eINSTANCE;
		}
	}

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target)
	{
		return modelSwitch.doSwitch((EObject)target);
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Bundle
	 * <em>Bundle</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Bundle
	 * @generated
	 */
	public Adapter createBundleAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Bundles
	 * <em>Bundles</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Bundles
	 * @generated
	 */
	public Adapter createBundlesAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Categories
	 * <em>Categories</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Categories
	 * @generated
	 */
	public Adapter createCategoriesAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Category
	 * <em>Category</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Category
	 * @generated
	 */
	public Adapter createCategoryAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.ChildrenProvider
	 * <em>Children Provider</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.ChildrenProvider
	 * @generated
	 */
	public Adapter createChildrenProviderAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This default implementation returns null.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Feature
	 * <em>Feature</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Feature
	 * @generated
	 */
	public Adapter createFeatureAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Features
	 * <em>Features</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Features
	 * @generated
	 */
	public Adapter createFeaturesAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Fragment
	 * <em>Fragment</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Fragment
	 * @generated
	 */
	public Adapter createFragmentAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Fragments
	 * <em>Fragments</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Fragments
	 * @generated
	 */
	public Adapter createFragmentsAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.InstallableUnits
	 * <em>Installable Units</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.InstallableUnits
	 * @generated
	 */
	public Adapter createInstallableUnitsAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability <em>IProvided Capability</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability
	 * @generated
	 */
	public Adapter createIProvidedCapabilityAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability <em>IRequired Capability</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability
	 * @generated
	 */
	public Adapter createIRequiredCapabilityAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.IUDetails
	 * <em>IU Details</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUDetails
	 * @generated
	 */
	public Adapter createIUDetailsAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.IUPresentation
	 * <em>IU Presentation</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentation
	 * @generated
	 */
	public Adapter createIUPresentationAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.p2view.IUPresentationWithDetails <em>IU Presentation With Details</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.IUPresentationWithDetails
	 * @generated
	 */
	public Adapter createIUPresentationWithDetailsAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.LabelProvider
	 * <em>Label Provider</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.LabelProvider
	 * @generated
	 */
	public Adapter createLabelProviderAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView
	 * <em>Metadata Repository Structured View</em>}'. <!-- begin-user-doc --> This default implementation returns null
	 * so that we can easily ignore cases; it's useful to ignore a case when inheritance will catch all the cases
	 * anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.MetadataRepositoryStructuredView
	 * @generated
	 */
	public Adapter createMetadataRepositoryStructuredViewAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Miscellaneous
	 * <em>Miscellaneous</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Miscellaneous
	 * @generated
	 */
	public Adapter createMiscellaneousAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.OtherIU
	 * <em>Other IU</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.OtherIU
	 * @generated
	 */
	public Adapter createOtherIUAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Product
	 * <em>Product</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore
	 * cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Product
	 * @generated
	 */
	public Adapter createProductAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Products
	 * <em>Products</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Products
	 * @generated
	 */
	public Adapter createProductsAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Properties
	 * <em>Properties</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Properties
	 * @generated
	 */
	public Adapter createPropertiesAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.p2view.ProvidedCapabilities <em>Provided Capabilities</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.ProvidedCapabilities
	 * @generated
	 */
	public Adapter createProvidedCapabilitiesAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.p2view.ProvidedCapabilityWrapper <em>Provided Capability Wrapper</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.ProvidedCapabilityWrapper
	 * @generated
	 */
	public Adapter createProvidedCapabilityWrapperAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.p2view.RequiredCapabilities <em>Required Capabilities</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's useful to
	 * ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.RequiredCapabilities
	 * @generated
	 */
	public Adapter createRequiredCapabilitiesAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.eclipse.buckminster.aggregator.p2view.RequiredCapabilityWrapper <em>Required Capability Wrapper</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.RequiredCapabilityWrapper
	 * @generated
	 */
	public Adapter createRequiredCapabilityWrapperAdapter()
	{
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.eclipse.buckminster.aggregator.p2view.Touchpoints
	 * <em>Touchpoints</em>}'. <!-- begin-user-doc --> This default implementation returns null so that we can easily
	 * ignore cases; it's useful to ignore a case when inheritance will catch all the cases anyway. <!-- end-user-doc
	 * -->
	 * 
	 * @return the new adapter.
	 * @see org.eclipse.buckminster.aggregator.p2view.Touchpoints
	 * @generated
	 */
	public Adapter createTouchpointsAdapter()
	{
		return null;
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!-- begin-user-doc --> This
	 * implementation returns <code>true</code> if the object is either the model's package or is an instance object of
	 * the model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object)
	{
		if(object == modelPackage)
		{
			return true;
		}
		if(object instanceof EObject)
		{
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

} // P2viewAdapterFactory
