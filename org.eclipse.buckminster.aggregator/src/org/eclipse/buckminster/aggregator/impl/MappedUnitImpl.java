/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.MappedUnit;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Mapped Unit</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.MappedUnitImpl#isEnabled <em>Enabled</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.impl.MappedUnitImpl#getInstallableUnit <em>Installable Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class MappedUnitImpl extends MinimalEObjectImpl.Container implements MappedUnit
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The flag representing the value of the '{@link #isEnabled() <em>Enabled</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final int ENABLED_EFLAG = 1 << 0;

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
	protected MappedUnitImpl()
	{
		super();
		eFlags |= ENABLED_EFLAG;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return AggregatorPackage.Literals.MAPPED_UNIT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isEnabled()
	{
		return (eFlags & ENABLED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setEnabled(boolean newEnabled)
	{
		boolean oldEnabled = (eFlags & ENABLED_EFLAG) != 0;
		if (newEnabled) eFlags |= ENABLED_EFLAG; else eFlags &= ~ENABLED_EFLAG;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAPPED_UNIT__ENABLED, oldEnabled, newEnabled));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InstallableUnit getInstallableUnit()
	{
		if (installableUnit != null && installableUnit.eIsProxy())
		{
			InternalEObject oldInstallableUnit = (InternalEObject)installableUnit;
			installableUnit = (InstallableUnit)eResolveProxy(oldInstallableUnit);
			if (installableUnit != oldInstallableUnit)
			{
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, AggregatorPackage.MAPPED_UNIT__INSTALLABLE_UNIT, oldInstallableUnit, installableUnit));
			}
		}
		return installableUnit;
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
	public void setInstallableUnit(InstallableUnit newInstallableUnit)
	{
		InstallableUnit oldInstallableUnit = installableUnit;
		installableUnit = newInstallableUnit;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, AggregatorPackage.MAPPED_UNIT__INSTALLABLE_UNIT, oldInstallableUnit, installableUnit));
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
			case AggregatorPackage.MAPPED_UNIT__ENABLED:
				return isEnabled();
			case AggregatorPackage.MAPPED_UNIT__INSTALLABLE_UNIT:
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
	public void eSet(int featureID, Object newValue)
	{
		switch (featureID)
		{
			case AggregatorPackage.MAPPED_UNIT__ENABLED:
				setEnabled((Boolean)newValue);
				return;
			case AggregatorPackage.MAPPED_UNIT__INSTALLABLE_UNIT:
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
			case AggregatorPackage.MAPPED_UNIT__ENABLED:
				setEnabled(ENABLED_EDEFAULT);
				return;
			case AggregatorPackage.MAPPED_UNIT__INSTALLABLE_UNIT:
				setInstallableUnit((InstallableUnit)null);
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
		switch (featureID)
		{
			case AggregatorPackage.MAPPED_UNIT__ENABLED:
				return ((eFlags & ENABLED_EFLAG) != 0) != ENABLED_EDEFAULT;
			case AggregatorPackage.MAPPED_UNIT__INSTALLABLE_UNIT:
				return installableUnit != null;
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
		result.append(" (enabled: ");
		result.append((eFlags & ENABLED_EFLAG) != 0);
		result.append(')');
		return result.toString();
	}

} // MappedUnitImpl
