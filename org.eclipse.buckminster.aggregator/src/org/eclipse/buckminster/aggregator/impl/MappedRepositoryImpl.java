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
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.Category;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.Product;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Mapped Repository</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl#getProducts <em>Products</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl#getBundles <em>Bundles</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl#getFeatures <em>Features</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl#isMapVerbatim <em>Map Verbatim</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl#getMetadataRepository <em>Metadata Repository
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl#getCategories <em>Categories</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MappedRepositoryImpl#getLocation <em>Location</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MappedRepositoryImpl extends MinimalEObjectImpl.Container implements MappedRepository
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

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
	 * The default value of the '{@link #isMapVerbatim() <em>Map Verbatim</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isMapVerbatim()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MAP_VERBATIM_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isMapVerbatim() <em>Map Verbatim</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMapVerbatim()
	 * @generated
	 * @ordered
	 */
	protected static final int MAP_VERBATIM_EFLAG = 1 << 0;

	/**
	 * The cached value of the '{@link #getMetadataRepository() <em>Metadata Repository</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMetadataRepository()
	 * @generated
	 * @ordered
	 */
	protected MetadataRepository metadataRepository;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected MappedRepositoryImpl()
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
		return AggregatorPackage.Literals.MAPPED_REPOSITORY;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Product> getProducts()
	{
		if (products == null) {
			products = new EObjectContainmentEList<Product>(Product.class, this, AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS);
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
			bundles = new EObjectContainmentEList<Bundle>(Bundle.class, this, AggregatorPackage.MAPPED_REPOSITORY__BUNDLES);
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
			features = new EObjectContainmentEList<Feature>(Feature.class, this, AggregatorPackage.MAPPED_REPOSITORY__FEATURES);
		}
		return features;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMapVerbatim()
	{
		return (eFlags & MAP_VERBATIM_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMapVerbatim(boolean newMapVerbatim)
	{
		boolean oldMapVerbatim = (eFlags & MAP_VERBATIM_EFLAG) != 0;
		if (newMapVerbatim) eFlags |= MAP_VERBATIM_EFLAG; else eFlags &= ~MAP_VERBATIM_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAPPED_REPOSITORY__MAP_VERBATIM, oldMapVerbatim, newMapVerbatim));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataRepository getMetadataRepository()
	{
		if (metadataRepository != null && metadataRepository.eIsProxy()) {
			InternalEObject oldMetadataRepository = (InternalEObject)metadataRepository;
			metadataRepository = (MetadataRepository)eResolveProxy(oldMetadataRepository);
			if (metadataRepository != oldMetadataRepository) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY, oldMetadataRepository, metadataRepository));
			}
		}
		return metadataRepository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public MetadataRepository basicGetMetadataRepository()
	{
		return metadataRepository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setMetadataRepository(MetadataRepository newMetadataRepository)
	{
		MetadataRepository oldMetadataRepository = metadataRepository;
		metadataRepository = newMetadataRepository;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY, oldMetadataRepository, metadataRepository));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Category> getCategories()
	{
		if (categories == null) {
			categories = new EObjectContainmentEList<Category>(Category.class, this, AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES);
		}
		return categories;
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
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAPPED_REPOSITORY__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID) {
			case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
				return ((InternalEList<?>)getProducts()).basicRemove(otherEnd, msgs);
			case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
				return ((InternalEList<?>)getBundles()).basicRemove(otherEnd, msgs);
			case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
				return ((InternalEList<?>)getFeatures()).basicRemove(otherEnd, msgs);
			case AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES:
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
			case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
				return getProducts();
			case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
				return getBundles();
			case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
				return getFeatures();
			case AggregatorPackage.MAPPED_REPOSITORY__MAP_VERBATIM:
				return isMapVerbatim();
			case AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY:
				if (resolve) return getMetadataRepository();
				return basicGetMetadataRepository();
			case AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES:
				return getCategories();
			case AggregatorPackage.MAPPED_REPOSITORY__LOCATION:
				return getLocation();
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
			case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
				getProducts().clear();
				getProducts().addAll((Collection<? extends Product>)newValue);
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
				getBundles().clear();
				getBundles().addAll((Collection<? extends Bundle>)newValue);
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
				getFeatures().clear();
				getFeatures().addAll((Collection<? extends Feature>)newValue);
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__MAP_VERBATIM:
				setMapVerbatim((Boolean)newValue);
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY:
				setMetadataRepository((MetadataRepository)newValue);
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES:
				getCategories().clear();
				getCategories().addAll((Collection<? extends Category>)newValue);
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__LOCATION:
				setLocation((String)newValue);
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
			case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
				getProducts().clear();
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
				getBundles().clear();
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
				getFeatures().clear();
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__MAP_VERBATIM:
				setMapVerbatim(MAP_VERBATIM_EDEFAULT);
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY:
				setMetadataRepository((MetadataRepository)null);
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES:
				getCategories().clear();
				return;
			case AggregatorPackage.MAPPED_REPOSITORY__LOCATION:
				setLocation(LOCATION_EDEFAULT);
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
			case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
				return products != null && !products.isEmpty();
			case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
				return bundles != null && !bundles.isEmpty();
			case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
				return features != null && !features.isEmpty();
			case AggregatorPackage.MAPPED_REPOSITORY__MAP_VERBATIM:
				return ((eFlags & MAP_VERBATIM_EFLAG) != 0) != MAP_VERBATIM_EDEFAULT;
			case AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY:
				return metadataRepository != null;
			case AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES:
				return categories != null && !categories.isEmpty();
			case AggregatorPackage.MAPPED_REPOSITORY__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
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
		result.append(" (mapVerbatim: ");
		result.append((eFlags & MAP_VERBATIM_EFLAG) != 0);
		result.append(", location: ");
		result.append(location);
		result.append(')');
		return result.toString();
	}

} // MappedRepositoryImpl
