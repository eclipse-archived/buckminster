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
import org.eclipse.buckminster.aggregator.p2view.Features;
import org.eclipse.buckminster.aggregator.p2view.Fragments;
import org.eclipse.buckminster.aggregator.p2view.P2viewFactory;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;
import org.eclipse.buckminster.aggregator.p2view.Product;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Product</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.ProductImpl#getFeatureContainer <em>Feature Container</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.ProductImpl#getBundleContainer <em>Bundle Container</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.ProductImpl#getFragmentContainer <em>Fragment Container
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ProductImpl extends IUPresentationImpl implements Product
{
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ProductImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ProductImpl(InstallableUnit iu)
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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case P2viewPackage.PRODUCT__FEATURE_CONTAINER:
			if(resolve)
				return getFeatureContainer();
			return basicGetFeatureContainer();
		case P2viewPackage.PRODUCT__BUNDLE_CONTAINER:
			if(resolve)
				return getBundleContainer();
			return basicGetBundleContainer();
		case P2viewPackage.PRODUCT__FRAGMENT_CONTAINER:
			if(resolve)
				return getFragmentContainer();
			return basicGetFragmentContainer();
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
		case P2viewPackage.PRODUCT__FEATURE_CONTAINER:
			return featureContainer != null;
		case P2viewPackage.PRODUCT__BUNDLE_CONTAINER:
			return bundleContainer != null;
		case P2viewPackage.PRODUCT__FRAGMENT_CONTAINER:
			return fragmentContainer != null;
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
		case P2viewPackage.PRODUCT__FEATURE_CONTAINER:
			setFeatureContainer((Features)newValue);
			return;
		case P2viewPackage.PRODUCT__BUNDLE_CONTAINER:
			setBundleContainer((Bundles)newValue);
			return;
		case P2viewPackage.PRODUCT__FRAGMENT_CONTAINER:
			setFragmentContainer((Fragments)newValue);
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
		case P2viewPackage.PRODUCT__FEATURE_CONTAINER:
			setFeatureContainer((Features)null);
			return;
		case P2viewPackage.PRODUCT__BUNDLE_CONTAINER:
			setBundleContainer((Bundles)null);
			return;
		case P2viewPackage.PRODUCT__FRAGMENT_CONTAINER:
			setFragmentContainer((Fragments)null);
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, P2viewPackage.PRODUCT__BUNDLE_CONTAINER,
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
	public Features getFeatureContainer()
	{
		if(featureContainer != null && featureContainer.eIsProxy())
		{
			InternalEObject oldFeatureContainer = (InternalEObject)featureContainer;
			featureContainer = (Features)eResolveProxy(oldFeatureContainer);
			if(featureContainer != oldFeatureContainer)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, P2viewPackage.PRODUCT__FEATURE_CONTAINER,
							oldFeatureContainer, featureContainer));
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
							P2viewPackage.PRODUCT__FRAGMENT_CONTAINER, oldFragmentContainer, fragmentContainer));
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

	public InstallableUnitType getType()
	{
		return InstallableUnitType.PRODUCT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.PRODUCT__BUNDLE_CONTAINER,
					oldBundleContainer, bundleContainer));
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
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.PRODUCT__FEATURE_CONTAINER,
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
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.PRODUCT__FRAGMENT_CONTAINER,
					oldFragmentContainer, fragmentContainer));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2viewPackage.Literals.PRODUCT;
	}

} // ProductImpl
