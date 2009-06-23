/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import java.lang.reflect.Method;
import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.RepositoryReference;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.internal.runtime.AdapterManager;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.internal.p2.metadata.repository.CompositeMetadataRepository;
import org.eclipse.equinox.internal.p2.metadata.repository.LocalMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepositoryManager;
import org.eclipse.equinox.internal.provisional.p2.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.query.Query;
import org.eclipse.equinox.internal.provisional.p2.repository.ICompositeRepository;
import org.eclipse.equinox.internal.provisional.p2.repository.IRepository;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Metadata Repository</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getDescription <em>Description</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getProvider <em>Provider</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#isModifiable <em>Modifiable</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getInstallableUnits <em>Installable Units</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getRepositoryReferences <em>Repository References</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl#getPropertyMap <em>Property Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MetadataRepositoryImpl extends MinimalEObjectImpl.Container implements MetadataRepository
{
	private static final Method LocalMetadataRepository_createRepositoriesSnapshot;

	static
	{
		try
		{
			LocalMetadataRepository_createRepositoriesSnapshot = LocalMetadataRepository.class.getDeclaredMethod("createRepositoriesSnapshot");
			LocalMetadataRepository_createRepositoriesSnapshot.setAccessible(true);
		}
		catch(Exception e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	public void addRepositoryReferences(IMetadataRepositoryManager mdrMgr, IMetadataRepository mdr) throws CoreException
	{
		if(mdr instanceof LocalMetadataRepository)
		{
			try
			{
				@SuppressWarnings("unchecked")
				List<org.eclipse.equinox.internal.provisional.spi.p2.metadata.repository.RepositoryReference> refs = (List<org.eclipse.equinox.internal.provisional.spi.p2.metadata.repository.RepositoryReference>)LocalMetadataRepository_createRepositoriesSnapshot.invoke(mdr);
				for(org.eclipse.equinox.internal.provisional.spi.p2.metadata.repository.RepositoryReference ref : refs)
					addReference(ref.Location, ref.Nickname, ref.Type, ref.Options);
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		else if(mdr instanceof CompositeMetadataRepository)
		{
			@SuppressWarnings("unchecked")
			List<URI> children = ((ICompositeRepository)mdr).getChildren();
			for(URI child : children)
				addRepositoryReferences(mdrMgr, mdrMgr.loadRepository(child, null));
		}
	}

	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final URI LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected URI location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected static final String DESCRIPTION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDescription() <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDescription()
	 * @generated
	 * @ordered
	 */
	protected String description = DESCRIPTION_EDEFAULT;

	/**
	 * The default value of the '{@link #getProvider() <em>Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvider()
	 * @generated
	 * @ordered
	 */
	protected static final String PROVIDER_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getProvider() <em>Provider</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getProvider()
	 * @generated
	 * @ordered
	 */
	protected String provider = PROVIDER_EDEFAULT;

	/**
	 * The default value of the '{@link #isModifiable() <em>Modifiable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isModifiable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MODIFIABLE_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isModifiable() <em>Modifiable</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isModifiable()
	 * @generated
	 * @ordered
	 */
	protected static final int MODIFIABLE_EFLAG = 1 << 0;

	/**
	 * The cached value of the '{@link #getInstallableUnits() <em>Installable Units</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getInstallableUnits()
	 * @generated
	 * @ordered
	 */
	protected EList<InstallableUnit> installableUnits;

	/**
	 * The cached value of the '{@link #getRepositoryReferences() <em>Repository References</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRepositoryReferences()
	 * @generated
	 * @ordered
	 */
	protected EList<RepositoryReference> repositoryReferences;

	/**
	 * The cached value of the '{@link #getPropertyMap() <em>Property Map</em>}' map.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPropertyMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> propertyMap;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected MetadataRepositoryImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void addInstallableUnits(IInstallableUnit[] installableUnits) {
		EList<InstallableUnit> iuList = getInstallableUnits();
		for(IInstallableUnit iu : installableUnits)
			iuList.add(InstallableUnitImpl.importToModel(iu));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void addReference(URI location, String nickname, int type, int options) {
		RepositoryReference ref = P2Factory.eINSTANCE.createRepositoryReference();
		ref.setLocation(location);
		ref.setNickname(nickname);
		ref.setType(type);
		ref.setOptions(options);
		getRepositoryReferences().add(ref);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == IAdaptable.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IRepository.class) {
			switch (derivedFeatureID) {
				case P2Package.METADATA_REPOSITORY__LOCATION: return P2Package.IREPOSITORY__LOCATION;
				case P2Package.METADATA_REPOSITORY__NAME: return P2Package.IREPOSITORY__NAME;
				case P2Package.METADATA_REPOSITORY__TYPE: return P2Package.IREPOSITORY__TYPE;
				case P2Package.METADATA_REPOSITORY__VERSION: return P2Package.IREPOSITORY__VERSION;
				case P2Package.METADATA_REPOSITORY__DESCRIPTION: return P2Package.IREPOSITORY__DESCRIPTION;
				case P2Package.METADATA_REPOSITORY__PROVIDER: return P2Package.IREPOSITORY__PROVIDER;
				case P2Package.METADATA_REPOSITORY__MODIFIABLE: return P2Package.IREPOSITORY__MODIFIABLE;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == IAdaptable.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == IRepository.class) {
			switch (baseFeatureID) {
				case P2Package.IREPOSITORY__LOCATION: return P2Package.METADATA_REPOSITORY__LOCATION;
				case P2Package.IREPOSITORY__NAME: return P2Package.METADATA_REPOSITORY__NAME;
				case P2Package.IREPOSITORY__TYPE: return P2Package.METADATA_REPOSITORY__TYPE;
				case P2Package.IREPOSITORY__VERSION: return P2Package.METADATA_REPOSITORY__VERSION;
				case P2Package.IREPOSITORY__DESCRIPTION: return P2Package.METADATA_REPOSITORY__DESCRIPTION;
				case P2Package.IREPOSITORY__PROVIDER: return P2Package.METADATA_REPOSITORY__PROVIDER;
				case P2Package.IREPOSITORY__MODIFIABLE: return P2Package.METADATA_REPOSITORY__MODIFIABLE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID) {
			case P2Package.METADATA_REPOSITORY__LOCATION:
				return getLocation();
			case P2Package.METADATA_REPOSITORY__NAME:
				return getName();
			case P2Package.METADATA_REPOSITORY__TYPE:
				return getType();
			case P2Package.METADATA_REPOSITORY__VERSION:
				return getVersion();
			case P2Package.METADATA_REPOSITORY__DESCRIPTION:
				return getDescription();
			case P2Package.METADATA_REPOSITORY__PROVIDER:
				return getProvider();
			case P2Package.METADATA_REPOSITORY__MODIFIABLE:
				return isModifiable();
			case P2Package.METADATA_REPOSITORY__INSTALLABLE_UNITS:
				return getInstallableUnits();
			case P2Package.METADATA_REPOSITORY__REPOSITORY_REFERENCES:
				return getRepositoryReferences();
			case P2Package.METADATA_REPOSITORY__PROPERTY_MAP:
				if (coreType) return getPropertyMap();
				else return getPropertyMap().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID) {
			case P2Package.METADATA_REPOSITORY__INSTALLABLE_UNITS:
				return ((InternalEList<?>)getInstallableUnits()).basicRemove(otherEnd, msgs);
			case P2Package.METADATA_REPOSITORY__REPOSITORY_REFERENCES:
				return ((InternalEList<?>)getRepositoryReferences()).basicRemove(otherEnd, msgs);
			case P2Package.METADATA_REPOSITORY__PROPERTY_MAP:
				return ((InternalEList<?>)getPropertyMap()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID) {
			case P2Package.METADATA_REPOSITORY__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case P2Package.METADATA_REPOSITORY__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case P2Package.METADATA_REPOSITORY__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case P2Package.METADATA_REPOSITORY__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case P2Package.METADATA_REPOSITORY__DESCRIPTION:
				return DESCRIPTION_EDEFAULT == null ? description != null : !DESCRIPTION_EDEFAULT.equals(description);
			case P2Package.METADATA_REPOSITORY__PROVIDER:
				return PROVIDER_EDEFAULT == null ? provider != null : !PROVIDER_EDEFAULT.equals(provider);
			case P2Package.METADATA_REPOSITORY__MODIFIABLE:
				return ((eFlags & MODIFIABLE_EFLAG) != 0) != MODIFIABLE_EDEFAULT;
			case P2Package.METADATA_REPOSITORY__INSTALLABLE_UNITS:
				return installableUnits != null && !installableUnits.isEmpty();
			case P2Package.METADATA_REPOSITORY__REPOSITORY_REFERENCES:
				return repositoryReferences != null && !repositoryReferences.isEmpty();
			case P2Package.METADATA_REPOSITORY__PROPERTY_MAP:
				return propertyMap != null && !propertyMap.isEmpty();
		}
		return super.eIsSet(featureID);
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
			case P2Package.METADATA_REPOSITORY__LOCATION:
				setLocation((URI)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__NAME:
				setName((String)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__TYPE:
				setType((String)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__VERSION:
				setVersion((String)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__DESCRIPTION:
				setDescription((String)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__PROVIDER:
				setProvider((String)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__MODIFIABLE:
				setModifiable((Boolean)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__INSTALLABLE_UNITS:
				getInstallableUnits().clear();
				getInstallableUnits().addAll((Collection<? extends InstallableUnit>)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__REPOSITORY_REFERENCES:
				getRepositoryReferences().clear();
				getRepositoryReferences().addAll((Collection<? extends RepositoryReference>)newValue);
				return;
			case P2Package.METADATA_REPOSITORY__PROPERTY_MAP:
				((EStructuralFeature.Setting)getPropertyMap()).set(newValue);
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
			case P2Package.METADATA_REPOSITORY__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case P2Package.METADATA_REPOSITORY__NAME:
				setName(NAME_EDEFAULT);
				return;
			case P2Package.METADATA_REPOSITORY__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case P2Package.METADATA_REPOSITORY__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case P2Package.METADATA_REPOSITORY__DESCRIPTION:
				setDescription(DESCRIPTION_EDEFAULT);
				return;
			case P2Package.METADATA_REPOSITORY__PROVIDER:
				setProvider(PROVIDER_EDEFAULT);
				return;
			case P2Package.METADATA_REPOSITORY__MODIFIABLE:
				setModifiable(MODIFIABLE_EDEFAULT);
				return;
			case P2Package.METADATA_REPOSITORY__INSTALLABLE_UNITS:
				getInstallableUnits().clear();
				return;
			case P2Package.METADATA_REPOSITORY__REPOSITORY_REFERENCES:
				getRepositoryReferences().clear();
				return;
			case P2Package.METADATA_REPOSITORY__PROPERTY_MAP:
				getPropertyMap().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		return AdapterManager.getDefault().getAdapter(this, adapter);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<InstallableUnit> getInstallableUnits()
	{
		if (installableUnits == null) {
			installableUnits = new EObjectContainmentEList<InstallableUnit>(InstallableUnit.class, this, P2Package.METADATA_REPOSITORY__INSTALLABLE_UNITS);
		}
		return installableUnits;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public URI getLocation() {
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public Map getProperties() {
		return (Map)getPropertyMap();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EMap<String, String> getPropertyMap() {
		if (propertyMap == null) {
			propertyMap = new EcoreEMap<String,String>(P2Package.Literals.PROPERTY, PropertyImpl.class, this, P2Package.METADATA_REPOSITORY__PROPERTY_MAP);
		}
		return propertyMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getProvider() {
		return provider;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RepositoryReference> getRepositoryReferences()
	{
		if (repositoryReferences == null) {
			repositoryReferences = new EObjectContainmentEList<RepositoryReference>(RepositoryReference.class, this, P2Package.METADATA_REPOSITORY__REPOSITORY_REFERENCES);
		}
		return repositoryReferences;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isModifiable() {
		return (eFlags & MODIFIABLE_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Collector query(Query query, Collector collector, IProgressMonitor progress)
	{
		return query.perform(getInstallableUnits().iterator(), collector);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public void removeAll() {
		getInstallableUnits().clear();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public boolean removeInstallableUnits(Query query, IProgressMonitor monitor) {
		boolean changed = false;
		List<InstallableUnit> units = getInstallableUnits();
		Collector results = query.perform(units.iterator(), new Collector());
		if (results.size() > 0) {
			changed = true;
			units.removeAll(results.toCollection());
		}
		return changed;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDescription(String newDescription) {
		String oldDescription = description;
		description = newDescription;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.METADATA_REPOSITORY__DESCRIPTION, oldDescription, description));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(URI newLocation) {
		URI oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.METADATA_REPOSITORY__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModifiable(boolean newModifiable) {
		boolean oldModifiable = (eFlags & MODIFIABLE_EFLAG) != 0;
		if (newModifiable) eFlags |= MODIFIABLE_EFLAG; else eFlags &= ~MODIFIABLE_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.METADATA_REPOSITORY__MODIFIABLE, oldModifiable, newModifiable));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.METADATA_REPOSITORY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public String setProperty(String key, String value) {
		return getPropertyMap().put(key, value);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setProvider(String newProvider) {
		String oldProvider = provider;
		provider = newProvider;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.METADATA_REPOSITORY__PROVIDER, oldProvider, provider));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.METADATA_REPOSITORY__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.METADATA_REPOSITORY__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (location: ");
		result.append(location);
		result.append(", name: ");
		result.append(name);
		result.append(", type: ");
		result.append(type);
		result.append(", version: ");
		result.append(version);
		result.append(", description: ");
		result.append(description);
		result.append(", provider: ");
		result.append(provider);
		result.append(", modifiable: ");
		result.append((eFlags & MODIFIABLE_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2Package.Literals.METADATA_REPOSITORY;
	}

} // MetadataRepositoryImpl
