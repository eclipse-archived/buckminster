/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.InstallationUnit;
import org.eclipse.buckminster.aggregator.Repository;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Installation Unit</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallationUnitImpl#getId <em>Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallationUnitImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallationUnitImpl#getRepository <em>Repository</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallationUnitImpl#isMapped <em>Mapped</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class InstallationUnitImpl extends MinimalEObjectImpl.Container implements InstallationUnit
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRepository() <em>Repository</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected Repository repository;

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
	protected InstallationUnitImpl()
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
		return AggregatorPackage.Literals.INSTALLATION_UNIT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setId(String newId)
	{
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.INSTALLATION_UNIT__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion()
	{
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion)
	{
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.INSTALLATION_UNIT__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Repository getRepository()
	{
		if (repository != null && repository.eIsProxy()) {
			InternalEObject oldRepository = (InternalEObject)repository;
			repository = (Repository)eResolveProxy(oldRepository);
			if (repository != oldRepository) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AggregatorPackage.INSTALLATION_UNIT__REPOSITORY, oldRepository, repository));
			}
		}
		return repository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Repository basicGetRepository()
	{
		return repository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepository(Repository newRepository)
	{
		Repository oldRepository = repository;
		repository = newRepository;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.INSTALLATION_UNIT__REPOSITORY, oldRepository, repository));
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
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.INSTALLATION_UNIT__MAPPED, oldMapped, newMapped));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID) {
			case AggregatorPackage.INSTALLATION_UNIT__ID:
				return getId();
			case AggregatorPackage.INSTALLATION_UNIT__VERSION:
				return getVersion();
			case AggregatorPackage.INSTALLATION_UNIT__REPOSITORY:
				if (resolve) return getRepository();
				return basicGetRepository();
			case AggregatorPackage.INSTALLATION_UNIT__MAPPED:
				return isMapped();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID) {
			case AggregatorPackage.INSTALLATION_UNIT__ID:
				setId((String)newValue);
				return;
			case AggregatorPackage.INSTALLATION_UNIT__VERSION:
				setVersion((String)newValue);
				return;
			case AggregatorPackage.INSTALLATION_UNIT__REPOSITORY:
				setRepository((Repository)newValue);
				return;
			case AggregatorPackage.INSTALLATION_UNIT__MAPPED:
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
			case AggregatorPackage.INSTALLATION_UNIT__ID:
				setId(ID_EDEFAULT);
				return;
			case AggregatorPackage.INSTALLATION_UNIT__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case AggregatorPackage.INSTALLATION_UNIT__REPOSITORY:
				setRepository((Repository)null);
				return;
			case AggregatorPackage.INSTALLATION_UNIT__MAPPED:
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
			case AggregatorPackage.INSTALLATION_UNIT__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case AggregatorPackage.INSTALLATION_UNIT__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case AggregatorPackage.INSTALLATION_UNIT__REPOSITORY:
				return repository != null;
			case AggregatorPackage.INSTALLATION_UNIT__MAPPED:
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
		result.append(" (id: ");
		result.append(id);
		result.append(", version: ");
		result.append(version);
		result.append(", mapped: ");
		result.append((eFlags & MAPPED_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

} // InstallationUnitImpl
