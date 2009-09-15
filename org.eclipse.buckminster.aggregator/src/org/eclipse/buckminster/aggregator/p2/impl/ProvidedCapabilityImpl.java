/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import org.eclipse.buckminster.aggregator.p2.P2Package;
import org.eclipse.buckminster.aggregator.p2.ProvidedCapability;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.equinox.internal.provisional.p2.core.Version;

import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.IRequiredCapability;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Provided Capability</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl#getName <em>Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl#getNamespace <em>Namespace</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.p2.impl.ProvidedCapabilityImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ProvidedCapabilityImpl extends MinimalEObjectImpl.Container implements ProvidedCapability
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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ProvidedCapabilityImpl()
	{
		super();
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
		case P2Package.PROVIDED_CAPABILITY__NAME:
			return getName();
		case P2Package.PROVIDED_CAPABILITY__NAMESPACE:
			return getNamespace();
		case P2Package.PROVIDED_CAPABILITY__VERSION:
			return getVersion();
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
		case P2Package.PROVIDED_CAPABILITY__NAME:
			return NAME_EDEFAULT == null
					? name != null
					: !NAME_EDEFAULT.equals(name);
		case P2Package.PROVIDED_CAPABILITY__NAMESPACE:
			return NAMESPACE_EDEFAULT == null
					? namespace != null
					: !NAMESPACE_EDEFAULT.equals(namespace);
		case P2Package.PROVIDED_CAPABILITY__VERSION:
			return VERSION_EDEFAULT == null
					? version != null
					: !VERSION_EDEFAULT.equals(version);
		}
		return super.eIsSet(featureID);
	}

	public boolean equals(Object other)
	{
		if(other == null)
			return false;
		if(!(other instanceof IProvidedCapability))
			return false;
		IProvidedCapability otherCapability = (IProvidedCapability)other;
		if(!(namespace.equals(otherCapability.getNamespace())))
			return false;
		if(!(name.equals(otherCapability.getName())))
			return false;
		return version.equals(otherCapability.getVersion());
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
		case P2Package.PROVIDED_CAPABILITY__NAME:
			setName((String)newValue);
			return;
		case P2Package.PROVIDED_CAPABILITY__NAMESPACE:
			setNamespace((String)newValue);
			return;
		case P2Package.PROVIDED_CAPABILITY__VERSION:
			setVersion((Version)newValue);
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
		case P2Package.PROVIDED_CAPABILITY__NAME:
			setName(NAME_EDEFAULT);
			return;
		case P2Package.PROVIDED_CAPABILITY__NAMESPACE:
			setNamespace(NAMESPACE_EDEFAULT);
			return;
		case P2Package.PROVIDED_CAPABILITY__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getNamespace()
	{
		return namespace;
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

	public int hashCode()
	{
		return namespace.hashCode() * name.hashCode() * version.hashCode();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean satisfies(IRequiredCapability candidate)
	{
		if(getName() == null || !getName().equals(candidate.getName()))
			return false;
		if(getNamespace() == null || !getNamespace().equals(candidate.getNamespace()))
			return false;
		return candidate.getRange().isIncluded(version);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setName(String newName)
	{
		String oldName = name;
		name = newName;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.PROVIDED_CAPABILITY__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setNamespace(String newNamespace)
	{
		String oldNamespace = namespace;
		namespace = newNamespace;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.PROVIDED_CAPABILITY__NAMESPACE,
					oldNamespace, namespace));
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
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.PROVIDED_CAPABILITY__VERSION, oldVersion,
					version));
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
		result.append(namespace);
		result.append('/');
		result.append(name);
		result.append('/');
		version.toString(result);
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
		return P2Package.Literals.PROVIDED_CAPABILITY;
	}

} // ProvidedCapabilityImpl
