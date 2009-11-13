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

import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.LabelProvider;

import org.eclipse.buckminster.aggregator.p2.ProvidedCapability;

import org.eclipse.buckminster.aggregator.p2view.P2viewPackage;
import org.eclipse.buckminster.aggregator.p2view.ProvidedCapabilityWrapper;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Provided Capability Wrapper</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.ProvidedCapabilityWrapperImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.ProvidedCapabilityWrapperImpl#getNamespace <em>Namespace
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.ProvidedCapabilityWrapperImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.ProvidedCapabilityWrapperImpl#getLabel <em>Label</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2view.impl.ProvidedCapabilityWrapperImpl#getGenuine <em>Genuine</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ProvidedCapabilityWrapperImpl extends MinimalEObjectImpl.Container implements ProvidedCapabilityWrapper
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
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNamespace() <em>Namespace</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected static final String NAMESPACE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected String namespace = NAMESPACE_EDEFAULT;

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
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
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
	 * The cached value of the '{@link #getGenuine() <em>Genuine</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGenuine()
	 * @generated
	 * @ordered
	 */
	protected ProvidedCapability genuine;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ProvidedCapabilityWrapperImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	protected ProvidedCapabilityWrapperImpl(ProvidedCapability pc)
	{
		super();
		genuine = pc;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == LabelProvider.class)
		{
			switch(derivedFeatureID)
			{
			case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__LABEL:
				return AggregatorPackage.LABEL_PROVIDER__LABEL;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if(baseClass == LabelProvider.class)
		{
			switch(baseFeatureID)
			{
			case AggregatorPackage.LABEL_PROVIDER__LABEL:
				return P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__LABEL;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__NAME:
			return getName();
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__NAMESPACE:
			return getNamespace();
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__VERSION:
			return getVersion();
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__LABEL:
			return getLabel();
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__GENUINE:
			return getGenuine();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__LABEL:
			return LABEL_EDEFAULT == null
					? label != null
					: !LABEL_EDEFAULT.equals(label);
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__GENUINE:
			return genuine != null;
		default:
			return ((InternalEObject)genuine).eIsSet(featureID);
		}
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
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__NAME:
			setName((String)newValue);
			return;
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__NAMESPACE:
			setNamespace((String)newValue);
			return;
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__VERSION:
			setVersion((Version)newValue);
			return;
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__LABEL:
			setLabel((String)newValue);
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
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__NAME:
			setName(NAME_EDEFAULT);
			return;
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__NAMESPACE:
			setNamespace(NAMESPACE_EDEFAULT);
			return;
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ProvidedCapability getGenuine()
	{
		return genuine;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getLabel()
	{
		return label;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getName()
	{
		return genuine.getName();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getNamespace()
	{
		return genuine.getNamespace();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Version getVersion()
	{
		return genuine.getVersion();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean satisfies(IRequiredCapability requirement)
	{
		return genuine.satisfies(requirement);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLabel(String newLabel)
	{
		String oldLabel = label;
		label = newLabel;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2viewPackage.PROVIDED_CAPABILITY_WRAPPER__LABEL,
					oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setName(String newName)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setNamespace(String newNamespace)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void setVersion(Version newVersion)
	{
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(getName());
		result.append(", namespace: ");
		result.append(getNamespace());
		result.append(", version: ");
		result.append(getVersion());
		result.append(", label: ");
		result.append(label);
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
		return P2viewPackage.Literals.PROVIDED_CAPABILITY_WRAPPER;
	}

} // ProvidedCapabilityWrapperImpl
