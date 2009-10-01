/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.aggregator.IAggregatorConstants;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.buckminster.aggregator.p2.ArtifactKey;
import org.eclipse.buckminster.aggregator.p2.Copyright;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnitType;
import org.eclipse.buckminster.aggregator.p2.License;
import org.eclipse.buckminster.aggregator.p2.P2Factory;
import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.ProvidedCapability;
import org.eclipse.buckminster.aggregator.p2.RequiredCapability;
import org.eclipse.buckminster.aggregator.p2.TouchpointData;
import org.eclipse.buckminster.aggregator.p2.TouchpointInstruction;
import org.eclipse.buckminster.aggregator.p2.TouchpointType;
import org.eclipse.buckminster.aggregator.p2.UpdateDescriptor;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.equinox.internal.p2.core.helpers.OrderedProperties;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionedName;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;
import org.eclipse.equinox.internal.provisional.p2.metadata.ICopyright;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment;
import org.eclipse.equinox.internal.provisional.p2.metadata.ILicense;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointData;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointInstruction;
import org.eclipse.equinox.internal.provisional.p2.metadata.ITouchpointType;
import org.eclipse.equinox.internal.provisional.p2.metadata.IUpdateDescriptor;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Installable Unit</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getFilter <em>Filter</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getTouchpointType <em>Touchpoint Type</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#isFragment <em>Fragment</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#isResolved <em>Resolved</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#isSingleton <em>Singleton</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getUpdateDescriptor <em>Update Descriptor
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getLicense <em>License</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getCopyright <em>Copyright</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getArtifactList <em>Artifact List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getProvidedCapabilityList <em>Provided
 * Capability List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getRequiredCapabilityList <em>Required
 * Capability List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getMetaRequiredCapabilityList <em>Meta
 * Required Capability List</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getPropertyMap <em>Property Map</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitImpl#getTouchpointDataList <em>Touchpoint Data
 * List</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class InstallableUnitImpl extends MinimalEObjectImpl.Container implements InstallableUnit
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
	 * The default value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected static final String FILTER_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getFilter() <em>Filter</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getFilter()
	 * @generated
	 * @ordered
	 */
	protected String filter = FILTER_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTouchpointType() <em>Touchpoint Type</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTouchpointType()
	 * @generated
	 * @ordered
	 */
	protected ITouchpointType touchpointType;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final Version VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected Version version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #isResolved() <em>Resolved</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isResolved()
	 * @generated
	 * @ordered
	 */
	protected static final boolean RESOLVED_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isResolved() <em>Resolved</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isResolved()
	 * @generated
	 * @ordered
	 */
	protected static final int RESOLVED_EFLAG = 1 << 0;

	/**
	 * The default value of the '{@link #isSingleton() <em>Singleton</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isSingleton()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SINGLETON_EDEFAULT = false;

	/**
	 * The flag representing the value of the '{@link #isSingleton() <em>Singleton</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #isSingleton()
	 * @generated
	 * @ordered
	 */
	protected static final int SINGLETON_EFLAG = 1 << 1;

	/**
	 * The cached value of the '{@link #getUpdateDescriptor() <em>Update Descriptor</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getUpdateDescriptor()
	 * @generated
	 * @ordered
	 */
	protected IUpdateDescriptor updateDescriptor;

	/**
	 * The cached value of the '{@link #getLicense() <em>License</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getLicense()
	 * @generated
	 * @ordered
	 */
	protected ILicense license;

	/**
	 * The cached value of the '{@link #getCopyright() <em>Copyright</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getCopyright()
	 * @generated
	 * @ordered
	 */
	protected ICopyright copyright;

	/**
	 * The cached value of the '{@link #getArtifactList() <em>Artifact List</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getArtifactList()
	 * @generated
	 * @ordered
	 */
	protected EList<ArtifactKey> artifactList;

	/**
	 * The cached value of the '{@link #getProvidedCapabilityList() <em>Provided Capability List</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProvidedCapabilityList()
	 * @generated
	 * @ordered
	 */
	protected EList<ProvidedCapability> providedCapabilityList;

	/**
	 * The cached value of the '{@link #getRequiredCapabilityList() <em>Required Capability List</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRequiredCapabilityList()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredCapability> requiredCapabilityList;

	/**
	 * The cached value of the '{@link #getMetaRequiredCapabilityList() <em>Meta Required Capability List</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMetaRequiredCapabilityList()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredCapability> metaRequiredCapabilityList;

	/**
	 * The cached value of the '{@link #getPropertyMap() <em>Property Map</em>}' map. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPropertyMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> propertyMap;

	/**
	 * The cached value of the '{@link #getTouchpointDataList() <em>Touchpoint Data List</em>}' containment reference
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTouchpointDataList()
	 * @generated
	 * @ordered
	 */
	protected EList<TouchpointData> touchpointDataList;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final InstallableUnitType TYPE_EDEFAULT = InstallableUnitType.BUNDLE;

	private static final Pattern proxyFragmentPattern = Pattern.compile("^//@metadataRepository/@installableUnits\\[id='([^']*)',version='([^']*)'\\]$");

	public static InstallableUnit importToModel(IInstallableUnit iu)
	{
		if(iu == null)
			return null;

		P2Factory factory = P2Factory.eINSTANCE;
		InstallableUnitImpl miu;
		if(iu instanceof IInstallableUnitFragment)
		{
			InstallableUnitFragmentImpl miuf = (InstallableUnitFragmentImpl)factory.createInstallableUnitFragment();
			List<RequiredCapability> mhosts = miuf.getHostList();
			for(IRequiredCapability host : ((IInstallableUnitFragment)iu).getHost())
				mhosts.add(importToModel(host));
			miu = miuf;
		}
		else
			miu = (InstallableUnitImpl)factory.createInstallableUnit();

		miu.setCopyright(importToModel(iu.getCopyright()));
		miu.setFilter(iu.getFilter());
		miu.setId(iu.getId());
		miu.setLicense(importToModel(iu.getLicense()));
		miu.setResolved(iu.isResolved());
		miu.setSingleton(iu.isSingleton());
		miu.setTouchpointType(importToModel(iu.getTouchpointType()));
		miu.setUpdateDescriptor(importToModel(iu.getUpdateDescriptor()));
		miu.setVersion(iu.getVersion());

		@SuppressWarnings("unchecked")
		Map<String, String> props = iu.getProperties();
		if(props.size() > 0)
			miu.getPropertyMap().putAll(props);

		List<ArtifactKey> keys = miu.getArtifactList();
		for(IArtifactKey key : iu.getArtifacts())
			keys.add(importToModel(key));

		List<RequiredCapability> rcs = miu.getMetaRequiredCapabilityList();
		for(IRequiredCapability rc : iu.getMetaRequiredCapabilities())
			rcs.add(importToModel(rc));

		rcs = miu.getRequiredCapabilityList();
		for(IRequiredCapability rc : iu.getRequiredCapabilities())
			rcs.add(importToModel(rc));

		List<ProvidedCapability> pcs = miu.getProvidedCapabilityList();
		for(IProvidedCapability pc : iu.getProvidedCapabilities())
			pcs.add(importToModel(pc));

		List<TouchpointData> tds = miu.getTouchpointDataList();
		for(ITouchpointData td : iu.getTouchpointData())
			tds.add(importToModel(td));

		return miu;
	}

	private static ArtifactKey importToModel(IArtifactKey key)
	{
		if(key == null)
			return null;
		ArtifactKeyImpl mkey = (ArtifactKeyImpl)P2Factory.eINSTANCE.createArtifactKey();
		mkey.setClassifier(key.getClassifier());
		mkey.setId(key.getId());
		mkey.setVersion(key.getVersion());
		return mkey;
	}

	private static Copyright importToModel(ICopyright cr)
	{
		if(cr == null)
			return null;
		CopyrightImpl mcr = (CopyrightImpl)P2Factory.eINSTANCE.createCopyright();
		mcr.setBody(cr.getBody());
		mcr.setLocation(cr.getLocation());
		return mcr;
	}

	private static License importToModel(ILicense lc)
	{
		if(lc == null)
			return null;
		LicenseImpl mlc = (LicenseImpl)P2Factory.eINSTANCE.createLicense();
		mlc.setBody(lc.getBody());
		mlc.setDigest(lc.getDigest());
		mlc.setLocation(lc.getLocation());
		return mlc;
	}

	private static ProvidedCapability importToModel(IProvidedCapability pc)
	{
		if(pc == null)
			return null;
		ProvidedCapabilityImpl mrq = (ProvidedCapabilityImpl)P2Factory.eINSTANCE.createProvidedCapability();
		mrq.setName(pc.getName());
		mrq.setNamespace(pc.getNamespace());
		mrq.setVersion(pc.getVersion());
		return mrq;
	}

	private static RequiredCapability importToModel(IRequiredCapability rc)
	{
		if(rc == null)
			return null;
		RequiredCapabilityImpl mrc = (RequiredCapabilityImpl)P2Factory.eINSTANCE.createRequiredCapability();
		mrc.setFilter(rc.getFilter());
		mrc.setGreedy(rc.isGreedy());
		mrc.setMultiple(rc.isMultiple());
		mrc.setName(rc.getName());
		mrc.setNamespace(rc.getNamespace());
		mrc.setOptional(rc.isOptional());
		mrc.setRange(rc.getRange());
		mrc.setSelectors(rc.getSelectors());
		return mrc;
	}

	private static TouchpointData importToModel(ITouchpointData ptd)
	{
		if(ptd == null)
			return null;
		TouchpointData mtpd = P2Factory.eINSTANCE.createTouchpointData();
		EMap<String, TouchpointInstruction> minstrMap = mtpd.getInstructionMap();
		@SuppressWarnings("unchecked")
		Map<String, ITouchpointInstruction> instrMap = ptd.getInstructions();
		for(Map.Entry<String, ITouchpointInstruction> instr : instrMap.entrySet())
			minstrMap.put(instr.getKey(), importToModel(instr.getValue()));
		return mtpd;
	}

	private static TouchpointInstruction importToModel(ITouchpointInstruction ti)
	{
		if(ti == null)
			return null;
		TouchpointInstructionImpl mti = (TouchpointInstructionImpl)P2Factory.eINSTANCE.createTouchpointInstruction();
		mti.setBody(ti.getBody());
		mti.setImportAttribute(ti.getImportAttribute());
		return mti;
	}

	private static TouchpointType importToModel(ITouchpointType tpt)
	{
		if(tpt == null)
			return null;
		TouchpointTypeImpl mtpt = (TouchpointTypeImpl)P2Factory.eINSTANCE.createTouchpointType();
		mtpt.setId(tpt.getId());
		mtpt.setVersion(tpt.getVersion());
		return mtpt;
	}

	private static UpdateDescriptor importToModel(IUpdateDescriptor ud)
	{
		if(ud == null)
			return null;
		UpdateDescriptorImpl mud = (UpdateDescriptorImpl)P2Factory.eINSTANCE.createUpdateDescriptor();
		mud.setDescription(ud.getDescription());
		mud.setId(ud.getId());
		mud.setRange(ud.getRange());
		mud.setSeverity(ud.getSeverity());
		return mud;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected InstallableUnitImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetCopyright(ICopyright newCopyright, NotificationChain msgs)
	{
		ICopyright oldCopyright = copyright;
		copyright = newCopyright;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					P2Package.INSTALLABLE_UNIT__COPYRIGHT, oldCopyright, newCopyright);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetLicense(ILicense newLicense, NotificationChain msgs)
	{
		ILicense oldLicense = license;
		license = newLicense;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					P2Package.INSTALLABLE_UNIT__LICENSE, oldLicense, newLicense);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetTouchpointType(ITouchpointType newTouchpointType, NotificationChain msgs)
	{
		ITouchpointType oldTouchpointType = touchpointType;
		touchpointType = newTouchpointType;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE, oldTouchpointType, newTouchpointType);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetUpdateDescriptor(IUpdateDescriptor newUpdateDescriptor, NotificationChain msgs)
	{
		IUpdateDescriptor oldUpdateDescriptor = updateDescriptor;
		updateDescriptor = newUpdateDescriptor;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR, oldUpdateDescriptor, newUpdateDescriptor);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public int compareTo(Object toCompareTo)
	{
		if(!(toCompareTo instanceof IInstallableUnit))
			return -1;

		IInstallableUnit other = (IInstallableUnit)toCompareTo;
		int idCmp = getId().compareTo(other.getId());
		return idCmp == 0
				? getVersion().compareTo(other.getVersion())
				: idCmp;
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
		case P2Package.INSTALLABLE_UNIT__FILTER:
			return getFilter();
		case P2Package.INSTALLABLE_UNIT__ID:
			return getId();
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE:
			return getTouchpointType();
		case P2Package.INSTALLABLE_UNIT__VERSION:
			return getVersion();
		case P2Package.INSTALLABLE_UNIT__RESOLVED:
			return isResolved();
		case P2Package.INSTALLABLE_UNIT__SINGLETON:
			return isSingleton();
		case P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR:
			return getUpdateDescriptor();
		case P2Package.INSTALLABLE_UNIT__LICENSE:
			return getLicense();
		case P2Package.INSTALLABLE_UNIT__COPYRIGHT:
			return getCopyright();
		case P2Package.INSTALLABLE_UNIT__ARTIFACT_LIST:
			return getArtifactList();
		case P2Package.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST:
			return getProvidedCapabilityList();
		case P2Package.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST:
			return getRequiredCapabilityList();
		case P2Package.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST:
			return getMetaRequiredCapabilityList();
		case P2Package.INSTALLABLE_UNIT__PROPERTY_MAP:
			if(coreType)
				return getPropertyMap();
			else
				return getPropertyMap().map();
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST:
			return getTouchpointDataList();
		case P2Package.INSTALLABLE_UNIT__TYPE:
			return getType();
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
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE:
			return basicSetTouchpointType(null, msgs);
		case P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR:
			return basicSetUpdateDescriptor(null, msgs);
		case P2Package.INSTALLABLE_UNIT__LICENSE:
			return basicSetLicense(null, msgs);
		case P2Package.INSTALLABLE_UNIT__COPYRIGHT:
			return basicSetCopyright(null, msgs);
		case P2Package.INSTALLABLE_UNIT__ARTIFACT_LIST:
			return ((InternalEList<?>)getArtifactList()).basicRemove(otherEnd, msgs);
		case P2Package.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST:
			return ((InternalEList<?>)getProvidedCapabilityList()).basicRemove(otherEnd, msgs);
		case P2Package.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST:
			return ((InternalEList<?>)getRequiredCapabilityList()).basicRemove(otherEnd, msgs);
		case P2Package.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST:
			return ((InternalEList<?>)getMetaRequiredCapabilityList()).basicRemove(otherEnd, msgs);
		case P2Package.INSTALLABLE_UNIT__PROPERTY_MAP:
			return ((InternalEList<?>)getPropertyMap()).basicRemove(otherEnd, msgs);
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST:
			return ((InternalEList<?>)getTouchpointDataList()).basicRemove(otherEnd, msgs);
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
		case P2Package.INSTALLABLE_UNIT__FILTER:
			return FILTER_EDEFAULT == null
					? filter != null
					: !FILTER_EDEFAULT.equals(filter);
		case P2Package.INSTALLABLE_UNIT__ID:
			return ID_EDEFAULT == null
					? id != null
					: !ID_EDEFAULT.equals(id);
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE:
			return touchpointType != null;
		case P2Package.INSTALLABLE_UNIT__VERSION:
			return VERSION_EDEFAULT == null
					? version != null
					: !VERSION_EDEFAULT.equals(version);
		case P2Package.INSTALLABLE_UNIT__RESOLVED:
			return ((eFlags & RESOLVED_EFLAG) != 0) != RESOLVED_EDEFAULT;
		case P2Package.INSTALLABLE_UNIT__SINGLETON:
			return ((eFlags & SINGLETON_EFLAG) != 0) != SINGLETON_EDEFAULT;
		case P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR:
			return updateDescriptor != null;
		case P2Package.INSTALLABLE_UNIT__LICENSE:
			return license != null;
		case P2Package.INSTALLABLE_UNIT__COPYRIGHT:
			return copyright != null;
		case P2Package.INSTALLABLE_UNIT__ARTIFACT_LIST:
			return artifactList != null && !artifactList.isEmpty();
		case P2Package.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST:
			return providedCapabilityList != null && !providedCapabilityList.isEmpty();
		case P2Package.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST:
			return requiredCapabilityList != null && !requiredCapabilityList.isEmpty();
		case P2Package.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST:
			return metaRequiredCapabilityList != null && !metaRequiredCapabilityList.isEmpty();
		case P2Package.INSTALLABLE_UNIT__PROPERTY_MAP:
			return propertyMap != null && !propertyMap.isEmpty();
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST:
			return touchpointDataList != null && !touchpointDataList.isEmpty();
		case P2Package.INSTALLABLE_UNIT__TYPE:
			return getType() != TYPE_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	public boolean equals(Object obj)
	{
		if(this == obj)
			return true;
		if(!(obj instanceof IInstallableUnit))
			return false;
		final IInstallableUnit other = (IInstallableUnit)obj;
		String thisId = Trivial.trim(id);
		String otherId = Trivial.trim(other.getId());

		if(thisId == null)
		{
			if(otherId != null)
				return false;
		}
		else if(!thisId.equals(otherId))
			return false;
		if(getVersion() == null)
		{
			if(other.getVersion() != null)
				return false;

			if(other instanceof InstallableUnitImpl)
			{
				VersionedName thisVn = getVersionedNameFromProxy();
				VersionedName otherVn = ((InstallableUnit)other).getVersionedNameFromProxy();

				if(thisVn == null)
				{
					if(otherVn != null)
						return false;
				}
				else if(!thisVn.equals(otherVn))
					return false;
			}
		}
		else if(!getVersion().equals(other.getVersion()))
			return false;
		return true;
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
		case P2Package.INSTALLABLE_UNIT__FILTER:
			setFilter((String)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__ID:
			setId((String)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE:
			setTouchpointType((ITouchpointType)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__VERSION:
			setVersion((Version)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__RESOLVED:
			setResolved((Boolean)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__SINGLETON:
			setSingleton((Boolean)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR:
			setUpdateDescriptor((IUpdateDescriptor)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__LICENSE:
			setLicense((ILicense)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__COPYRIGHT:
			setCopyright((ICopyright)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__ARTIFACT_LIST:
			getArtifactList().clear();
			getArtifactList().addAll((Collection<? extends ArtifactKey>)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST:
			getProvidedCapabilityList().clear();
			getProvidedCapabilityList().addAll((Collection<? extends ProvidedCapability>)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST:
			getRequiredCapabilityList().clear();
			getRequiredCapabilityList().addAll((Collection<? extends RequiredCapability>)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST:
			getMetaRequiredCapabilityList().clear();
			getMetaRequiredCapabilityList().addAll((Collection<? extends RequiredCapability>)newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__PROPERTY_MAP:
			((EStructuralFeature.Setting)getPropertyMap()).set(newValue);
			return;
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST:
			getTouchpointDataList().clear();
			getTouchpointDataList().addAll((Collection<? extends TouchpointData>)newValue);
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
		case P2Package.INSTALLABLE_UNIT__FILTER:
			setFilter(FILTER_EDEFAULT);
			return;
		case P2Package.INSTALLABLE_UNIT__ID:
			setId(ID_EDEFAULT);
			return;
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE:
			setTouchpointType((ITouchpointType)null);
			return;
		case P2Package.INSTALLABLE_UNIT__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case P2Package.INSTALLABLE_UNIT__RESOLVED:
			setResolved(RESOLVED_EDEFAULT);
			return;
		case P2Package.INSTALLABLE_UNIT__SINGLETON:
			setSingleton(SINGLETON_EDEFAULT);
			return;
		case P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR:
			setUpdateDescriptor((IUpdateDescriptor)null);
			return;
		case P2Package.INSTALLABLE_UNIT__LICENSE:
			setLicense((ILicense)null);
			return;
		case P2Package.INSTALLABLE_UNIT__COPYRIGHT:
			setCopyright((ICopyright)null);
			return;
		case P2Package.INSTALLABLE_UNIT__ARTIFACT_LIST:
			getArtifactList().clear();
			return;
		case P2Package.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST:
			getProvidedCapabilityList().clear();
			return;
		case P2Package.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST:
			getRequiredCapabilityList().clear();
			return;
		case P2Package.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST:
			getMetaRequiredCapabilityList().clear();
			return;
		case P2Package.INSTALLABLE_UNIT__PROPERTY_MAP:
			getPropertyMap().clear();
			return;
		case P2Package.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST:
			getTouchpointDataList().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ArtifactKey> getArtifactList()
	{
		if(artifactList == null)
		{
			artifactList = new EObjectContainmentEList<ArtifactKey>(ArtifactKey.class, this,
					P2Package.INSTALLABLE_UNIT__ARTIFACT_LIST);
		}
		return artifactList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IArtifactKey[] getArtifacts()
	{
		List<ArtifactKey> list = getArtifactList();
		return list.toArray(new IArtifactKey[list.size()]);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ICopyright getCopyright()
	{
		return copyright;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getFilter()
	{
		return filter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IInstallableUnitFragment[] getFragments()
	{
		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ILicense getLicense()
	{
		return license;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IRequiredCapability[] getMetaRequiredCapabilities()
	{
		List<RequiredCapability> list = getMetaRequiredCapabilityList();
		return list.toArray(new IRequiredCapability[list.size()]);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<RequiredCapability> getMetaRequiredCapabilityList()
	{
		if(metaRequiredCapabilityList == null)
		{
			metaRequiredCapabilityList = new EObjectContainmentEList<RequiredCapability>(RequiredCapability.class,
					this, P2Package.INSTALLABLE_UNIT__META_REQUIRED_CAPABILITY_LIST);
		}
		return metaRequiredCapabilityList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@SuppressWarnings("unchecked")
	public Map getProperties()
	{
		// Retain the order of the properties
		//
		EMap<String, String> propertyEntries = getPropertyMap();
		int top = propertyEntries.size();
		if(top == 0)
			return Collections.emptyMap();

		if(top == 1)
		{
			Map.Entry<String, String> entry = propertyEntries.get(0);
			return Collections.singletonMap(entry.getKey(), entry.getValue());
		}

		OrderedProperties props = new OrderedProperties(top);
		for(int idx = 0; idx < top; ++idx)
		{
			Map.Entry<String, String> entry = propertyEntries.get(idx);
			props.put(entry.getKey(), entry.getValue());
		}
		return props;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getProperty(String key)
	{
		return getPropertyMap().get(key);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EMap<String, String> getPropertyMap()
	{
		if(propertyMap == null)
		{
			propertyMap = new EcoreEMap<String, String>(P2Package.Literals.PROPERTY, PropertyImpl.class, this,
					P2Package.INSTALLABLE_UNIT__PROPERTY_MAP);
		}
		return propertyMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IProvidedCapability[] getProvidedCapabilities()
	{
		List<ProvidedCapability> list = getProvidedCapabilityList();
		return list.toArray(new IProvidedCapability[list.size()]);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ProvidedCapability> getProvidedCapabilityList()
	{
		if(providedCapabilityList == null)
		{
			providedCapabilityList = new EObjectContainmentEList<ProvidedCapability>(ProvidedCapability.class, this,
					P2Package.INSTALLABLE_UNIT__PROVIDED_CAPABILITY_LIST);
		}
		return providedCapabilityList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IRequiredCapability[] getRequiredCapabilities()
	{
		List<RequiredCapability> list = getRequiredCapabilityList();
		return list.toArray(new IRequiredCapability[list.size()]);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<RequiredCapability> getRequiredCapabilityList()
	{
		if(requiredCapabilityList == null)
		{
			requiredCapabilityList = new EObjectContainmentEList<RequiredCapability>(RequiredCapability.class, this,
					P2Package.INSTALLABLE_UNIT__REQUIRED_CAPABILITY_LIST);
		}
		return requiredCapabilityList;
	}

	public int getStatus()
	{
		return Trivial.trim(getId()) != null
				? StatusProvider.OK
				: StatusProvider.BROKEN;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ITouchpointData[] getTouchpointData()
	{
		List<TouchpointData> list = getTouchpointDataList();
		return list.toArray(new ITouchpointData[list.size()]);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<TouchpointData> getTouchpointDataList()
	{
		if(touchpointDataList == null)
		{
			touchpointDataList = new EObjectContainmentEList<TouchpointData>(TouchpointData.class, this,
					P2Package.INSTALLABLE_UNIT__TOUCHPOINT_DATA_LIST);
		}
		return touchpointDataList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public ITouchpointType getTouchpointType()
	{
		return touchpointType != null
				? touchpointType
				: eIsProxy()
						? null
						: ITouchpointType.NONE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public InstallableUnitType getType()
	{
		if("true".equalsIgnoreCase(getProperty(IInstallableUnit.PROP_TYPE_CATEGORY)))
			return InstallableUnitType.CATEGORY;
		if(getId().endsWith(IAggregatorConstants.FEATURE_SUFFIX))
			return InstallableUnitType.FEATURE;
		if("true".equalsIgnoreCase(getProperty(IInstallableUnit.PROP_TYPE_GROUP)))
			return InstallableUnitType.PRODUCT;
		if(isOSGiFragment())
			return InstallableUnitType.FRAGMENT;
		if(isOSGiBundle())
			return InstallableUnitType.BUNDLE;
		return InstallableUnitType.OTHER;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public IUpdateDescriptor getUpdateDescriptor()
	{
		return updateDescriptor;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Version getVersion()
	{
		return version;
	}

	public VersionedName getVersionedName()
	{
		if(eIsProxy())
			return getVersionedNameFromProxy();
		else
			return new VersionedName(getId(), getVersion());
	}

	public VersionedName getVersionedNameFromProxy()
	{
		URI uri = eProxyURI();
		if(uri == null)
			return null;

		String frag = uri.fragment();
		if(frag == null)
			return null;

		Matcher m = proxyFragmentPattern.matcher(frag);
		return m.matches()
				? new VersionedName(m.group(1), m.group(2))
				: null;
	}

	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null)
				? 0
				: id.hashCode());
		result = prime * result + ((getVersion() == null)
				? 0
				: getVersion().hashCode());
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isFragment()
	{
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isResolved()
	{
		return (eFlags & RESOLVED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isSingleton()
	{
		return (eFlags & SINGLETON_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean satisfies(IRequiredCapability candidate)
	{
		for(ProvidedCapability provided : getProvidedCapabilityList())
			if(provided.satisfies(candidate))
				return true;
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCopyright(ICopyright newCopyright)
	{
		if(newCopyright != copyright)
		{
			NotificationChain msgs = null;
			if(copyright != null)
				msgs = ((InternalEObject)copyright).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- P2Package.INSTALLABLE_UNIT__COPYRIGHT, null, msgs);
			if(newCopyright != null)
				msgs = ((InternalEObject)newCopyright).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- P2Package.INSTALLABLE_UNIT__COPYRIGHT, null, msgs);
			msgs = basicSetCopyright(newCopyright, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__COPYRIGHT, newCopyright,
					newCopyright));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFilter(String newFilter)
	{
		String oldFilter = filter;
		filter = newFilter;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__FILTER, oldFilter, filter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setId(String newId)
	{
		String oldId = id;
		id = newId;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLicense(ILicense newLicense)
	{
		if(newLicense != license)
		{
			NotificationChain msgs = null;
			if(license != null)
				msgs = ((InternalEObject)license).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- P2Package.INSTALLABLE_UNIT__LICENSE, null, msgs);
			if(newLicense != null)
				msgs = ((InternalEObject)newLicense).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- P2Package.INSTALLABLE_UNIT__LICENSE, null, msgs);
			msgs = basicSetLicense(newLicense, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__LICENSE, newLicense,
					newLicense));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setResolved(boolean newResolved)
	{
		boolean oldResolved = (eFlags & RESOLVED_EFLAG) != 0;
		if(newResolved)
			eFlags |= RESOLVED_EFLAG;
		else
			eFlags &= ~RESOLVED_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__RESOLVED, oldResolved,
					newResolved));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSingleton(boolean newSingleton)
	{
		boolean oldSingleton = (eFlags & SINGLETON_EFLAG) != 0;
		if(newSingleton)
			eFlags |= SINGLETON_EFLAG;
		else
			eFlags &= ~SINGLETON_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__SINGLETON, oldSingleton,
					newSingleton));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTouchpointType(ITouchpointType newTouchpointType)
	{
		if(newTouchpointType != touchpointType)
		{
			NotificationChain msgs = null;
			if(touchpointType != null)
				msgs = ((InternalEObject)touchpointType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE, null, msgs);
			if(newTouchpointType != null)
				msgs = ((InternalEObject)newTouchpointType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE, null, msgs);
			msgs = basicSetTouchpointType(newTouchpointType, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__TOUCHPOINT_TYPE,
					newTouchpointType, newTouchpointType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setUpdateDescriptor(IUpdateDescriptor newUpdateDescriptor)
	{
		if(newUpdateDescriptor != updateDescriptor)
		{
			NotificationChain msgs = null;
			if(updateDescriptor != null)
				msgs = ((InternalEObject)updateDescriptor).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR, null, msgs);
			if(newUpdateDescriptor != null)
				msgs = ((InternalEObject)newUpdateDescriptor).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR, null, msgs);
			msgs = basicSetUpdateDescriptor(newUpdateDescriptor, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__UPDATE_DESCRIPTOR,
					newUpdateDescriptor, newUpdateDescriptor));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersion(Version newVersion)
	{
		Version oldVersion = version;
		version = newVersion;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.INSTALLABLE_UNIT__VERSION, oldVersion,
					version));
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
		result.append(" (filter: ");
		result.append(filter);
		result.append(", id: ");
		result.append(id);
		result.append(", version: ");
		result.append(version);
		result.append(", resolved: ");
		result.append((eFlags & RESOLVED_EFLAG) != 0);
		result.append(", singleton: ");
		result.append((eFlags & SINGLETON_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public IInstallableUnit unresolved()
	{
		return this;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2Package.Literals.INSTALLABLE_UNIT;
	}

	private boolean isOSGiBundle()
	{
		for(IProvidedCapability rc : getProvidedCapabilityList())
			if(IAggregatorConstants.NAMESPACE_TYPE.equals(rc.getNamespace())
					&& (IAggregatorConstants.CAPABILITY_TYPE_BUNDLE.equals(rc.getName()) || IAggregatorConstants.CAPABILITY_TYPE_SOURCE.equals(rc.getName())))
				return true;
		return false;
	}

	private boolean isOSGiFragment()
	{
		for(IProvidedCapability rc : getProvidedCapabilityList())
			if(IAggregatorConstants.NAMESPACE_OSGI_FRAGMENT.equals(rc.getNamespace()))
				return true;
		return false;
	}

} // InstallableUnitImpl
