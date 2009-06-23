/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import java.util.Collection;

import org.eclipse.buckminster.aggregator.p2.InstallableUnitFragment;
import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.RequiredCapability;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnitFragment;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Installable Unit Fragment</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitFragmentImpl#getHost <em>Host</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.InstallableUnitFragmentImpl#getHostList <em>Host List</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InstallableUnitFragmentImpl extends InstallableUnitImpl implements InstallableUnitFragment
{
	/**
	 * The default value of the '{@link #getHost() <em>Host</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected static final IRequiredCapability[] HOST_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getHost() <em>Host</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getHost()
	 * @generated
	 * @ordered
	 */
	protected IRequiredCapability[] host = HOST_EDEFAULT;

	/**
	 * The cached value of the '{@link #getHostList() <em>Host List</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getHostList()
	 * @generated
	 * @ordered
	 */
	protected EList<RequiredCapability> hostList;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected InstallableUnitFragmentImpl()
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
		return P2Package.Literals.INSTALLABLE_UNIT_FRAGMENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IRequiredCapability[] getHost()
	{
		return host;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RequiredCapability> getHostList()
	{
		if (hostList == null)
		{
			hostList = new EObjectContainmentEList<RequiredCapability>(RequiredCapability.class, this, P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST_LIST);
		}
		return hostList;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch (featureID)
		{
			case P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST_LIST:
				return ((InternalEList<?>)getHostList()).basicRemove(otherEnd, msgs);
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
		switch (featureID)
		{
			case P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST:
				return getHost();
			case P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST_LIST:
				return getHostList();
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
		switch (featureID)
		{
			case P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST_LIST:
				getHostList().clear();
				getHostList().addAll((Collection<? extends RequiredCapability>)newValue);
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
			case P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST_LIST:
				getHostList().clear();
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
			case P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST:
				return HOST_EDEFAULT == null ? host != null : !HOST_EDEFAULT.equals(host);
			case P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST_LIST:
				return hostList != null && !hostList.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if (baseClass == IInstallableUnitFragment.class)
		{
			switch (derivedFeatureID)
			{
				case P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST: return P2Package.IINSTALLABLE_UNIT_FRAGMENT__HOST;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if (baseClass == IInstallableUnitFragment.class)
		{
			switch (baseFeatureID)
			{
				case P2Package.IINSTALLABLE_UNIT_FRAGMENT__HOST: return P2Package.INSTALLABLE_UNIT_FRAGMENT__HOST;
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
	public String toString()
	{
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (host: ");
		result.append(host);
		result.append(')');
		return result.toString();
	}

} // InstallableUnitFragmentImpl
