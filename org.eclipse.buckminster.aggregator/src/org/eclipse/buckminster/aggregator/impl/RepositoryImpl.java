/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.Bundle;
import org.eclipse.buckminster.aggregator.Category;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.Product;
import org.eclipse.buckminster.aggregator.Repository;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Repository</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl#getLabel <em>Label</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl#getProducts <em>Products</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl#getBundles <em>Bundles</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl#getFeatures <em>Features</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl#getCategories <em>Categories</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.RepositoryImpl#isMapped <em>Mapped</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RepositoryImpl extends MinimalEObjectImpl.Container implements Repository
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProducts() <em>Products</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<Product> products;

	/**
	 * The cached value of the '{@link #getBundles() <em>Bundles</em>}' containment reference list.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getBundles()
	 * @generated
	 * @ordered
	 */
	protected EList<Bundle> bundles;

	/**
	 * The cached value of the '{@link #getFeatures() <em>Features</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFeatures()
	 * @generated
	 * @ordered
	 */
	protected EList<Feature> features;

	/**
	 * The cached value of the '{@link #getCategories() <em>Categories</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCategories()
	 * @generated
	 * @ordered
	 */
	protected EList<Category> categories;

	/**
	 * The default value of the '{@link #isMapped() <em>Mapped</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isMapped()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MAPPED_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isMapped() <em>Mapped</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMapped()
	 * @generated
	 * @ordered
	 */
	protected static final int MAPPED_EFLAG = 1 << 0;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected RepositoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return AggregatorPackage.Literals.REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(String newLocation)
	{
		String oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.REPOSITORY__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLabel(String newLabel)
	{
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.REPOSITORY__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Product> getProducts()
	{
		if (products == null) {
			products = new EObjectContainmentEList<Product>(Product.class, this, AggregatorPackage.REPOSITORY__PRODUCTS);
		}
		return products;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Bundle> getBundles()
	{
		if (bundles == null) {
			bundles = new EObjectContainmentEList<Bundle>(Bundle.class, this, AggregatorPackage.REPOSITORY__BUNDLES);
		}
		return bundles;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Feature> getFeatures()
	{
		if (features == null) {
			features = new EObjectContainmentEList<Feature>(Feature.class, this, AggregatorPackage.REPOSITORY__FEATURES);
		}
		return features;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Category> getCategories()
	{
		if (categories == null) {
			categories = new EObjectContainmentEList<Category>(Category.class, this, AggregatorPackage.REPOSITORY__CATEGORIES);
		}
		return categories;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMapped()
	{
		return (eFlags & MAPPED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapped(boolean newMapped)
	{
		boolean oldMapped = (eFlags & MAPPED_EFLAG) != 0;
		if (newMapped) eFlags |= MAPPED_EFLAG; else eFlags &= ~MAPPED_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.REPOSITORY__MAPPED, oldMapped, newMapped));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID) {
			case AggregatorPackage.REPOSITORY__PRODUCTS:
				return ((InternalEList<?>)getProducts()).basicRemove(otherEnd, msgs);
			case AggregatorPackage.REPOSITORY__BUNDLES:
				return ((InternalEList<?>)getBundles()).basicRemove(otherEnd, msgs);
			case AggregatorPackage.REPOSITORY__FEATURES:
				return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
			case AggregatorPackage.REPOSITORY__CATEGORIES:
				return ((InternalEList<?>)getCategories()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID) {
			case AggregatorPackage.REPOSITORY__LOCATION:
				return getLocation();
			case AggregatorPackage.REPOSITORY__LABEL:
				return getLabel();
			case AggregatorPackage.REPOSITORY__PRODUCTS:
				return getProducts();
			case AggregatorPackage.REPOSITORY__BUNDLES:
				return getBundles();
			case AggregatorPackage.REPOSITORY__FEATURES:
				return getFeatures();
			case AggregatorPackage.REPOSITORY__CATEGORIES:
				return getCategories();
			case AggregatorPackage.REPOSITORY__MAPPED:
				return isMapped();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID) {
			case AggregatorPackage.REPOSITORY__LOCATION:
				setLocation((String)newValue);
				return;
			case AggregatorPackage.REPOSITORY__LABEL:
				setLabel((String)newValue);
				return;
			case AggregatorPackage.REPOSITORY__PRODUCTS:
				getProducts().clear();
				getProducts().addAll((Collection<? extends Product>)newValue);
				return;
			case AggregatorPackage.REPOSITORY__BUNDLES:
				getBundles().clear();
				getBundles().addAll((Collection<? extends Bundle>)newValue);
				return;
			case AggregatorPackage.REPOSITORY__FEATURES:
				getFeatures().clear();
				getFeatures().addAll((Collection<? extends Feature>)newValue);
				return;
			case AggregatorPackage.REPOSITORY__CATEGORIES:
				getCategories().clear();
				getCategories().addAll((Collection<? extends Category>)newValue);
				return;
			case AggregatorPackage.REPOSITORY__MAPPED:
				setMapped((Boolean)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID)
	{
		switch (featureID) {
			case AggregatorPackage.REPOSITORY__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case AggregatorPackage.REPOSITORY__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case AggregatorPackage.REPOSITORY__PRODUCTS:
				getProducts().clear();
				return;
			case AggregatorPackage.REPOSITORY__BUNDLES:
				getBundles().clear();
				return;
			case AggregatorPackage.REPOSITORY__FEATURES:
				getFeatures().clear();
				return;
			case AggregatorPackage.REPOSITORY__CATEGORIES:
				getCategories().clear();
				return;
			case AggregatorPackage.REPOSITORY__MAPPED:
				setMapped(MAPPED_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID) {
			case AggregatorPackage.REPOSITORY__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case AggregatorPackage.REPOSITORY__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case AggregatorPackage.REPOSITORY__PRODUCTS:
				return products != null && !products.isEmpty();
			case AggregatorPackage.REPOSITORY__BUNDLES:
				return bundles != null && !bundles.isEmpty();
			case AggregatorPackage.REPOSITORY__FEATURES:
				return features != null && !features.isEmpty();
			case AggregatorPackage.REPOSITORY__CATEGORIES:
				return categories != null && !categories.isEmpty();
			case AggregatorPackage.REPOSITORY__MAPPED:
				return ((eFlags & MAPPED_EFLAG) != 0) != MAPPED_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (location: ");
		result.append(location);
		result.append(", label: ");
		result.append(label);
		result.append(", mapped: ");
		result.append((eFlags & MAPPED_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

} // RepositoryImpl
