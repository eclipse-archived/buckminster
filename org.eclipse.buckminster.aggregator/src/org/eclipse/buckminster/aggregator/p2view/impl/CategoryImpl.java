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

import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnitType;
import org.eclipse.buckminster.aggregator.p2view.Bundles;
import org.eclipse.buckminster.aggregator.p2view.Categories;
import org.eclipse.buckminster.aggregator.p2view.Category;
import org.eclipse.buckminster.aggregator.p2view.Details;
import org.eclipse.buckminster.aggregator.p2view.Features;
import org.eclipse.buckminster.aggregator.p2view.Fragments;
import org.eclipse.buckminster.aggregator.p2view.P2viewFactory;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;
import org.eclipse.buckminster.aggregator.p2view.Products;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Category</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl#getCategoryContainer <em>Category Container
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl#getFeatureContainer <em>Feature Container
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl#getProductContainer <em>Product Container
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl#getBundleContainer <em>Bundle Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl#getFragmentContainer <em>Fragment Container
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.CategoryImpl#getDetails <em>Details</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CategoryImpl extends IUPresentationImpl implements Category
{
	/**
	 * The cached value of the '{@link #getCategoryContainer() <em>Category Container</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCategoryContainer()
	 * @generated
	 * @ordered
	 */
	protected Categories categoryContainer;

	/**
	 * The cached value of the '{@link #getFeatureContainer() <em>Feature Container</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFeatureContainer()
	 * @generated
	 * @ordered
	 */
	protected Features featureContainer;

	/**
	 * The cached value of the '{@link #getProductContainer() <em>Product Container</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProductContainer()
	 * @generated
	 * @ordered
	 */
	protected Products productContainer;

	/**
	 * The cached value of the '{@link #getBundleContainer() <em>Bundle Container</em>}' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getBundleContainer()
	 * @generated
	 * @ordered
	 */
	protected Bundles bundleContainer;

	/**
	 * The cached value of the '{@link #getFragmentContainer() <em>Fragment Container</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFragmentContainer()
	 * @generated
	 * @ordered
	 */
	protected Fragments fragmentContainer;

	/**
	 * The cached value of the '{@link #getDetails() <em>Details</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDetails()
	 * @generated
	 * @ordered
	 */
	protected Details details;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected CategoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected CategoryImpl(InstallableUnit iu)
	{
		super(iu);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Bundles basicGetBundleContainer()
	{
		return bundleContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Categories basicGetCategoryContainer()
	{
		return categoryContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Details basicGetDetails()
	{
		return details;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Features basicGetFeatureContainer()
	{
		return featureContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Fragments basicGetFragmentContainer()
	{
		return fragmentContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Products basicGetProductContainer()
	{
		return productContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case P2viewPackage.CATEGORY__CATEGORY_CONTAINER:
			if(resolve)
				return getCategoryContainer();
			return basicGetCategoryContainer();
		case P2viewPackage.CATEGORY__FEATURE_CONTAINER:
			if(resolve)
				return getFeatureContainer();
			return basicGetFeatureContainer();
		case P2viewPackage.CATEGORY__PRODUCT_CONTAINER:
			if(resolve)
				return getProductContainer();
			return basicGetProductContainer();
		case P2viewPackage.CATEGORY__BUNDLE_CONTAINER:
			if(resolve)
				return getBundleContainer();
			return basicGetBundleContainer();
		case P2viewPackage.CATEGORY__FRAGMENT_CONTAINER:
			if(resolve)
				return getFragmentContainer();
			return basicGetFragmentContainer();
		case P2viewPackage.CATEGORY__DETAILS:
			if(resolve)
				return getDetails();
			return basicGetDetails();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case P2viewPackage.CATEGORY__CATEGORY_CONTAINER:
			return categoryContainer != null;
		case P2viewPackage.CATEGORY__FEATURE_CONTAINER:
			return featureContainer != null;
		case P2viewPackage.CATEGORY__PRODUCT_CONTAINER:
			return productContainer != null;
		case P2viewPackage.CATEGORY__BUNDLE_CONTAINER:
			return bundleContainer != null;
		case P2viewPackage.CATEGORY__FRAGMENT_CONTAINER:
			return fragmentContainer != null;
		case P2viewPackage.CATEGORY__DETAILS:
			return details != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case P2viewPackage.CATEGORY__CATEGORY_CONTAINER:
			setCategoryContainer((Categories)newValue);
			return;
		case P2viewPackage.CATEGORY__FEATURE_CONTAINER:
			setFeatureContainer((Features)newValue);
			return;
		case P2viewPackage.CATEGORY__PRODUCT_CONTAINER:
			setProductContainer((Products)newValue);
			return;
		case P2viewPackage.CATEGORY__BUNDLE_CONTAINER:
			setBundleContainer((Bundles)newValue);
			return;
		case P2viewPackage.CATEGORY__FRAGMENT_CONTAINER:
			setFragmentContainer((Fragments)newValue);
			return;
		case P2viewPackage.CATEGORY__DETAILS:
			setDetails((Details)newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch(featureID)
		{
		case P2viewPackage.CATEGORY__CATEGORY_CONTAINER:
			setCategoryContainer((Categories)null);
			return;
		case P2viewPackage.CATEGORY__FEATURE_CONTAINER:
			setFeatureContainer((Features)null);
			return;
		case P2viewPackage.CATEGORY__PRODUCT_CONTAINER:
			setProductContainer((Products)null);
			return;
		case P2viewPackage.CATEGORY__BUNDLE_CONTAINER:
			setBundleContainer((Bundles)null);
			return;
		case P2viewPackage.CATEGORY__FRAGMENT_CONTAINER:
			setFragmentContainer((Fragments)null);
			return;
		case P2viewPackage.CATEGORY__DETAILS:
			setDetails((Details)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Bundles getBundleContainer()
	{
		if(bundleContainer != null && bundleContainer.eIsProxy())
		{
			InternalEObject oldBundleContainer = (InternalEObject)bundleContainer;
			bundleContainer = (Bundles)eResolveProxy(oldBundleContainer);
			if(bundleContainer != oldBundleContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, P2viewPackage.CATEGORY__BUNDLE_CONTAINER,
							oldBundleContainer, bundleContainer));
			}
		}
		return bundleContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Categories getCategoryContainer()
	{
		if(categoryContainer != null && categoryContainer.eIsProxy())
		{
			InternalEObject oldCategoryContainer = (InternalEObject)categoryContainer;
			categoryContainer = (Categories)eResolveProxy(oldCategoryContainer);
			if(categoryContainer != oldCategoryContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.CATEGORY__CATEGORY_CONTAINER, oldCategoryContainer, categoryContainer));
			}
		}
		return categoryContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Details getDetails()
	{
		if(details != null && details.eIsProxy())
		{
			InternalEObject oldDetails = (InternalEObject)details;
			details = (Details)eResolveProxy(oldDetails);
			if(details != oldDetails)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, P2viewPackage.CATEGORY__DETAILS,
							oldDetails, details));
			}
		}
		return details;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Features getFeatureContainer()
	{
		if(featureContainer != null && featureContainer.eIsProxy())
		{
			InternalEObject oldFeatureContainer = (InternalEObject)featureContainer;
			featureContainer = (Features)eResolveProxy(oldFeatureContainer);
			if(featureContainer != oldFeatureContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.CATEGORY__FEATURE_CONTAINER, oldFeatureContainer, featureContainer));
			}
		}
		return featureContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Fragments getFragmentContainer()
	{
		if(fragmentContainer != null && fragmentContainer.eIsProxy())
		{
			InternalEObject oldFragmentContainer = (InternalEObject)fragmentContainer;
			fragmentContainer = (Fragments)eResolveProxy(oldFragmentContainer);
			if(fragmentContainer != oldFragmentContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.CATEGORY__FRAGMENT_CONTAINER, oldFragmentContainer, fragmentContainer));
			}
		}
		return fragmentContainer;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Bundles getNotNullBundleContainer()
	{
		if(bundleContainer == null)
			setBundleContainer(P2viewFactory.eINSTANCE.createBundles());

		return getBundleContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Categories getNotNullCategoryContainer()
	{
		if(categoryContainer == null)
			setCategoryContainer(P2viewFactory.eINSTANCE.createCategories());

		return getCategoryContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Details getNotNullDetails()
	{
		if(details == null)
			setDetails(P2viewFactory.eINSTANCE.createDetails());

		return getDetails();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Features getNotNullFeatureContainer()
	{
		if(featureContainer == null)
			setFeatureContainer(P2viewFactory.eINSTANCE.createFeatures());

		return getFeatureContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Fragments getNotNullFragmentContainer()
	{
		if(fragmentContainer == null)
			setFragmentContainer(P2viewFactory.eINSTANCE.createFragments());

		return getFragmentContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Products getNotNullProductContainer()
	{
		if(productContainer == null)
			setProductContainer(P2viewFactory.eINSTANCE.createProducts());

		return getProductContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Products getProductContainer()
	{
		if(productContainer != null && productContainer.eIsProxy())
		{
			InternalEObject oldProductContainer = (InternalEObject)productContainer;
			productContainer = (Products)eResolveProxy(oldProductContainer);
			if(productContainer != oldProductContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							P2viewPackage.CATEGORY__PRODUCT_CONTAINER, oldProductContainer, productContainer));
			}
		}
		return productContainer;
	}

	public InstallableUnitType getType()
	{
		return InstallableUnitType.CATEGORY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isNested()
	{
		return "true".equalsIgnoreCase(getNotNullDetails().getInstallableUnit().getProperty(
				IInstallableUnit.PROP_TYPE_GROUP));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBundleContainer(Bundles newBundleContainer)
	{
		Bundles oldBundleContainer = bundleContainer;
		bundleContainer = newBundleContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.CATEGORY__BUNDLE_CONTAINER,
					oldBundleContainer, bundleContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCategoryContainer(Categories newCategoryContainer)
	{
		Categories oldCategoryContainer = categoryContainer;
		categoryContainer = newCategoryContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.CATEGORY__CATEGORY_CONTAINER,
					oldCategoryContainer, categoryContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDetails(Details newDetails)
	{
		Details oldDetails = details;
		details = newDetails;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.CATEGORY__DETAILS, oldDetails, details));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFeatureContainer(Features newFeatureContainer)
	{
		Features oldFeatureContainer = featureContainer;
		featureContainer = newFeatureContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.CATEGORY__FEATURE_CONTAINER,
					oldFeatureContainer, featureContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFragmentContainer(Fragments newFragmentContainer)
	{
		Fragments oldFragmentContainer = fragmentContainer;
		fragmentContainer = newFragmentContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.CATEGORY__FRAGMENT_CONTAINER,
					oldFragmentContainer, fragmentContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setProductContainer(Products newProductContainer)
	{
		Products oldProductContainer = productContainer;
		productContainer = newProductContainer;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.CATEGORY__PRODUCT_CONTAINER,
					oldProductContainer, productContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2viewPackage.Literals.CATEGORY;
	}

} // CategoryImpl
