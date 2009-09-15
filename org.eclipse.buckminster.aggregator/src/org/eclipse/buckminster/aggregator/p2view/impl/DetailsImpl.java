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
import org.eclipse.buckminster.aggregator.p2view.Details;
import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Details</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.DetailsImpl#getInstallableUnit <em>Installable Unit</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DetailsImpl extends MinimalEObjectImpl.Container implements Details
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
	 * The cached value of the '{@link #getInstallableUnit() <em>Installable Unit</em>}' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getInstallableUnit()
	 * @generated
	 * @ordered
	 */
	protected InstallableUnit installableUnit;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DetailsImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InstallableUnit basicGetInstallableUnit()
	{
		return installableUnit;
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
		case P2viewPackage.DETAILS__INSTALLABLE_UNIT:
			if(resolve)
				return getInstallableUnit();
			return basicGetInstallableUnit();
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
		case P2viewPackage.DETAILS__INSTALLABLE_UNIT:
			return installableUnit != null;
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
		case P2viewPackage.DETAILS__INSTALLABLE_UNIT:
			setInstallableUnit((InstallableUnit)newValue);
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
		case P2viewPackage.DETAILS__INSTALLABLE_UNIT:
			setInstallableUnit((InstallableUnit)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InstallableUnit getInstallableUnit()
	{
		if(installableUnit != null && installableUnit.eIsProxy())
		{
			InternalEObject oldInstallableUnit = (InternalEObject)installableUnit;
			installableUnit = (InstallableUnit)eResolveProxy(oldInstallableUnit);
			if(installableUnit != oldInstallableUnit)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, P2viewPackage.DETAILS__INSTALLABLE_UNIT,
							oldInstallableUnit, installableUnit));
			}
		}
		return installableUnit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setInstallableUnit(InstallableUnit newInstallableUnit)
	{
		InstallableUnit oldInstallableUnit = installableUnit;
		installableUnit = newInstallableUnit;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.DETAILS__INSTALLABLE_UNIT,
					oldInstallableUnit, installableUnit));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return P2viewPackage.Literals.DETAILS;
	}

} // DetailsImpl
