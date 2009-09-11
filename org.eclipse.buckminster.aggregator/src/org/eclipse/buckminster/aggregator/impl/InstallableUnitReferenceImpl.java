/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.InstallableUnitReference;
import org.eclipse.buckminster.aggregator.MappedRepository;

import org.eclipse.buckminster.aggregator.p2.InstallableUnit;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Installable Unit Reference</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl#getInstallableUnit <em>Installable Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class InstallableUnitReferenceImpl extends MinimalEObjectImpl.Container implements
		InstallableUnitReference
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The cached value of the '{@link #getInstallableUnit() <em>Installable Unit</em>}' reference.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getInstallableUnit()
	 * @generated
	 * @ordered
	 */
	protected InstallableUnit installableUnit;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected InstallableUnitReferenceImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InstallableUnit basicGetInstallableUnit()
	{
		return installableUnit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID)
		{
			case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
				if (resolve) return getInstallableUnit();
				return basicGetInstallableUnit();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch (featureID)
		{
			case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
				return installableUnit != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
				setInstallableUnit((InstallableUnit)newValue);
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
		switch (featureID)
		{
			case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
				setInstallableUnit((InstallableUnit)null);
				return;
		}
		super.eUnset(featureID);
	}

	public InstallableUnit getInstallableUnit()
	{
		return isMappedRepositoryBroken()
				? null
				: getInstallableUnitGen();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public InstallableUnit getInstallableUnit(boolean forceResolve)
	{
		if(forceResolve)
			return getInstallableUnit();

		return basicGetInstallableUnit();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InstallableUnit getInstallableUnitGen()
	{
		if (installableUnit != null && installableUnit.eIsProxy())
		{
			InternalEObject oldInstallableUnit = (InternalEObject)installableUnit;
			installableUnit = (InstallableUnit)eResolveProxy(oldInstallableUnit);
			if (installableUnit != oldInstallableUnit)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT, oldInstallableUnit, installableUnit));
			}
		}
		return installableUnit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isMappedRepositoryBroken()
	{
		MappedRepository repo = (MappedRepository)eContainer();
		return repo == null || repo.getMetadataRepository() == null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setInstallableUnit(InstallableUnit newInstallableUnit)
	{
		InstallableUnit oldInstallableUnit = installableUnit;
		installableUnit = newInstallableUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT, oldInstallableUnit, installableUnit));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return AggregatorPackage.Literals.INSTALLABLE_UNIT_REFERENCE;
	}

} // InstallableUnitReferenceImpl
