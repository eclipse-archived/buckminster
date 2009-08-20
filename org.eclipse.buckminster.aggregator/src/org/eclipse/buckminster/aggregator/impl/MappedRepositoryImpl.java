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
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.Feature;
import org.eclipse.buckminster.aggregator.Category;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.Product;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
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
	 * 
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The flag representing the value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final int ENABLED_EFLAG = 1 << 0;

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
	 * The cached value of the '{@link #getBundles() <em>Bundles</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
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
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isMirrorArtifacts() <em>Mirror Artifacts</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isMirrorArtifacts()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MIRROR_ARTIFACTS_EDEFAULT = true;

	/**
	 * The flag representing the value of the '{@link #isMirrorArtifacts() <em>Mirror Artifacts</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMirrorArtifacts()
	 * @generated
	 * @ordered
	 */
	protected static final int MIRROR_ARTIFACTS_EFLAG = 1 << 1;

	/**
	 * The default value of the '{@link #getCategoryPrefix() <em>Category Prefix</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getCategoryPrefix()
	 * @generated
	 * @ordered
	 */
	protected static final String CATEGORY_PREFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCategoryPrefix() <em>Category Prefix</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getCategoryPrefix()
	 * @generated
	 * @ordered
	 */
	protected String categoryPrefix = CATEGORY_PREFIX_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MappedRepositoryImpl()
	{
		super();
		eFlags |= ENABLED_EFLAG;
		eFlags |= MIRROR_ARTIFACTS_EFLAG;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataRepository basicGetMetadataRepository()
	{
		return metadataRepository;
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
		case AggregatorPackage.MAPPED_REPOSITORY__ENABLED:
			return isEnabled();
		case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
			return getProducts();
		case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
			return getBundles();
		case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
			return getFeatures();
		case AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY:
			if(resolve)
				return getMetadataRepository();
			return basicGetMetadataRepository();
		case AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES:
			return getCategories();
		case AggregatorPackage.MAPPED_REPOSITORY__LOCATION:
			return getLocation();
		case AggregatorPackage.MAPPED_REPOSITORY__MIRROR_ARTIFACTS:
			return isMirrorArtifacts();
		case AggregatorPackage.MAPPED_REPOSITORY__CATEGORY_PREFIX:
			return getCategoryPrefix();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
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
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case AggregatorPackage.MAPPED_REPOSITORY__ENABLED:
			return ((eFlags & ENABLED_EFLAG) != 0) != ENABLED_EDEFAULT;
		case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
			return products != null && !products.isEmpty();
		case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
			return bundles != null && !bundles.isEmpty();
		case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
			return features != null && !features.isEmpty();
		case AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY:
			return metadataRepository != null;
		case AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES:
			return categories != null && !categories.isEmpty();
		case AggregatorPackage.MAPPED_REPOSITORY__LOCATION:
			return LOCATION_EDEFAULT == null
					? location != null
					: !LOCATION_EDEFAULT.equals(location);
		case AggregatorPackage.MAPPED_REPOSITORY__MIRROR_ARTIFACTS:
			return ((eFlags & MIRROR_ARTIFACTS_EFLAG) != 0) != MIRROR_ARTIFACTS_EDEFAULT;
		case AggregatorPackage.MAPPED_REPOSITORY__CATEGORY_PREFIX:
			return CATEGORY_PREFIX_EDEFAULT == null
					? categoryPrefix != null
					: !CATEGORY_PREFIX_EDEFAULT.equals(categoryPrefix);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case AggregatorPackage.MAPPED_REPOSITORY__ENABLED:
			setEnabled((Boolean)newValue);
			return;
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
		case AggregatorPackage.MAPPED_REPOSITORY__MIRROR_ARTIFACTS:
			setMirrorArtifacts((Boolean)newValue);
			return;
		case AggregatorPackage.MAPPED_REPOSITORY__CATEGORY_PREFIX:
			setCategoryPrefix((String)newValue);
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
		case AggregatorPackage.MAPPED_REPOSITORY__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		case AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS:
			getProducts().clear();
			return;
		case AggregatorPackage.MAPPED_REPOSITORY__BUNDLES:
			getBundles().clear();
			return;
		case AggregatorPackage.MAPPED_REPOSITORY__FEATURES:
			getFeatures().clear();
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
		case AggregatorPackage.MAPPED_REPOSITORY__MIRROR_ARTIFACTS:
			setMirrorArtifacts(MIRROR_ARTIFACTS_EDEFAULT);
			return;
		case AggregatorPackage.MAPPED_REPOSITORY__CATEGORY_PREFIX:
			setCategoryPrefix(CATEGORY_PREFIX_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Bundle> getBundles()
	{
		if(bundles == null)
		{
			bundles = new EObjectContainmentEList<Bundle>(Bundle.class, this,
					AggregatorPackage.MAPPED_REPOSITORY__BUNDLES);
		}
		return bundles;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Category> getCategories()
	{
		if(categories == null)
		{
			categories = new EObjectContainmentEList<Category>(Category.class, this,
					AggregatorPackage.MAPPED_REPOSITORY__CATEGORIES);
		}
		return categories;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getCategoryPrefix()
	{
		return categoryPrefix;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<MappedUnit> getEnabledUnits()
	{
		EList<Category> categories = getCategories();
		EList<Product> products = getProducts();
		EList<Feature> features = getFeatures();
		EList<Bundle> bundles = getBundles();
		EList<MappedUnit> units = new BasicEList<MappedUnit>(categories.size() + products.size() + features.size()
				+ bundles.size());
		for(Category category : categories)
			if(category.isEnabled())
				units.add(category);
		for(Product product : products)
			if(product.isEnabled())
				units.add(product);
		for(Feature feature : features)
			if(feature.isEnabled())
				units.add(feature);
		for(Bundle bundle : bundles)
			if(bundle.isEnabled())
				units.add(bundle);
		return units;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Feature> getFeatures()
	{
		if(features == null)
		{
			features = new EObjectContainmentEList<Feature>(Feature.class, this,
					AggregatorPackage.MAPPED_REPOSITORY__FEATURES);
		}
		return features;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataRepository getMetadataRepository()
	{
		if(metadataRepository != null && metadataRepository.eIsProxy())
		{
			InternalEObject oldMetadataRepository = (InternalEObject)metadataRepository;
			metadataRepository = (MetadataRepository)eResolveProxy(oldMetadataRepository);
			if(metadataRepository != oldMetadataRepository)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY, oldMetadataRepository,
							metadataRepository));
			}
		}
		return metadataRepository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Product> getProducts()
	{
		if(products == null)
		{
			products = new EObjectContainmentEList<Product>(Product.class, this,
					AggregatorPackage.MAPPED_REPOSITORY__PRODUCTS);
		}
		return products;
	}

	public int getStatus()
	{
		for(MappedUnit unit : getEnabledUnits())
		{
			if(unit.getStatus() != StatusProvider.OK)
				return StatusProvider.BROKEN_CHILD;
		}
		return StatusProvider.OK;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<MappedUnit> getUnits(boolean enabledOnly)
	{
		EList<Category> categories = getCategories();
		EList<Product> products = getProducts();
		EList<Feature> features = getFeatures();
		EList<Bundle> bundles = getBundles();
		EList<MappedUnit> units = new BasicEList<MappedUnit>(categories.size() + products.size() + features.size()
				+ bundles.size());

		if(enabledOnly)
		{
			for(Category category : categories)
				if(category.isEnabled())
					units.add(category);
			for(Product product : products)
				if(product.isEnabled())
					units.add(product);
			for(Feature feature : features)
				if(feature.isEnabled())
					units.add(feature);
			for(Bundle bundle : bundles)
				if(bundle.isEnabled())
					units.add(bundle);
		}
		else
		{
			units.addAll(categories);
			units.addAll(products);
			units.addAll(features);
			units.addAll(bundles);
		}
		return units;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isBranchEnabled()
	{
		return isEnabled() && ((Contribution)eContainer()).isEnabled();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isEnabled()
	{
		return (eFlags & ENABLED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isMapEverything()
	{
		return getFeatures().size() == 0 && getCategories().size() == 0 && getProducts().size() == 0
				&& getBundles().size() == 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isMirrorArtifacts()
	{
		return (eFlags & MIRROR_ARTIFACTS_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCategoryPrefix(String newCategoryPrefix)
	{
		String oldCategoryPrefix = categoryPrefix;
		categoryPrefix = newCategoryPrefix;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAPPED_REPOSITORY__CATEGORY_PREFIX,
					oldCategoryPrefix, categoryPrefix));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEnabled(boolean newEnabled)
	{
		boolean oldEnabled = (eFlags & ENABLED_EFLAG) != 0;
		if(newEnabled)
			eFlags |= ENABLED_EFLAG;
		else
			eFlags &= ~ENABLED_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAPPED_REPOSITORY__ENABLED,
					oldEnabled, newEnabled));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLocation(String newLocation)
	{
		String oldLocation = location;
		location = newLocation;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAPPED_REPOSITORY__LOCATION,
					oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMetadataRepository(MetadataRepository newMetadataRepository)
	{
		MetadataRepository oldMetadataRepository = metadataRepository;
		metadataRepository = newMetadataRepository;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AggregatorPackage.MAPPED_REPOSITORY__METADATA_REPOSITORY, oldMetadataRepository, metadataRepository));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMirrorArtifacts(boolean newMirrorArtifacts)
	{
		boolean oldMirrorArtifacts = (eFlags & MIRROR_ARTIFACTS_EFLAG) != 0;
		if(newMirrorArtifacts)
			eFlags |= MIRROR_ARTIFACTS_EFLAG;
		else
			eFlags &= ~MIRROR_ARTIFACTS_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AggregatorPackage.MAPPED_REPOSITORY__MIRROR_ARTIFACTS, oldMirrorArtifacts, newMirrorArtifacts));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (enabled: ");
		result.append((eFlags & ENABLED_EFLAG) != 0);
		result.append(", location: ");
		result.append(location);
		result.append(", mirrorArtifacts: ");
		result.append((eFlags & MIRROR_ARTIFACTS_EFLAG) != 0);
		result.append(", categoryPrefix: ");
		result.append(categoryPrefix);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return AggregatorPackage.Literals.MAPPED_REPOSITORY;
	}

} // MappedRepositoryImpl
