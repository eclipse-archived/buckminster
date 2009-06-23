/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.p2.impl;

import java.net.URI;

import org.eclipse.buckminster.aggregator.p2.P2Package;

import org.eclipse.buckminster.aggregator.p2.RepositoryReference;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Repository Reference</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RepositoryReferenceImpl#getLocation <em>Location</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RepositoryReferenceImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RepositoryReferenceImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.buckminster.aggregator.p2.impl.RepositoryReferenceImpl#getNickname <em>Nickname</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RepositoryReferenceImpl extends MinimalEObjectImpl.Container implements RepositoryReference
{
	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final URI LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected URI location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final int TYPE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected int type = TYPE_EDEFAULT;

	/**
	 * The default value of the '{@link #getOptions() <em>Options</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected static final int OPTIONS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getOptions() <em>Options</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getOptions()
	 * @generated
	 * @ordered
	 */
	protected int options = OPTIONS_EDEFAULT;

	/**
	 * The default value of the '{@link #getNickname() <em>Nickname</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getNickname()
	 * @generated
	 * @ordered
	 */
	protected static final String NICKNAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNickname() <em>Nickname</em>}' attribute.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getNickname()
	 * @generated
	 * @ordered
	 */
	protected String nickname = NICKNAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected RepositoryReferenceImpl()
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
		return P2Package.Literals.REPOSITORY_REFERENCE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public URI getLocation()
	{
		return location;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setLocation(URI newLocation)
	{
		URI oldLocation = location;
		location = newLocation;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REPOSITORY_REFERENCE__LOCATION, oldLocation, location));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getType()
	{
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(int newType)
	{
		int oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REPOSITORY_REFERENCE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public int getOptions()
	{
		return options;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOptions(int newOptions)
	{
		int oldOptions = options;
		options = newOptions;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REPOSITORY_REFERENCE__OPTIONS, oldOptions, options));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getNickname()
	{
		return nickname;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setNickname(String newNickname)
	{
		String oldNickname = nickname;
		nickname = newNickname;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, P2Package.REPOSITORY_REFERENCE__NICKNAME, oldNickname, nickname));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch (featureID) {
			case P2Package.REPOSITORY_REFERENCE__LOCATION:
				return getLocation();
			case P2Package.REPOSITORY_REFERENCE__TYPE:
				return getType();
			case P2Package.REPOSITORY_REFERENCE__OPTIONS:
				return getOptions();
			case P2Package.REPOSITORY_REFERENCE__NICKNAME:
				return getNickname();
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
			case P2Package.REPOSITORY_REFERENCE__LOCATION:
				setLocation((URI)newValue);
				return;
			case P2Package.REPOSITORY_REFERENCE__TYPE:
				setType((Integer)newValue);
				return;
			case P2Package.REPOSITORY_REFERENCE__OPTIONS:
				setOptions((Integer)newValue);
				return;
			case P2Package.REPOSITORY_REFERENCE__NICKNAME:
				setNickname((String)newValue);
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
			case P2Package.REPOSITORY_REFERENCE__LOCATION:
				setLocation(LOCATION_EDEFAULT);
				return;
			case P2Package.REPOSITORY_REFERENCE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case P2Package.REPOSITORY_REFERENCE__OPTIONS:
				setOptions(OPTIONS_EDEFAULT);
				return;
			case P2Package.REPOSITORY_REFERENCE__NICKNAME:
				setNickname(NICKNAME_EDEFAULT);
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
			case P2Package.REPOSITORY_REFERENCE__LOCATION:
				return LOCATION_EDEFAULT == null ? location != null : !LOCATION_EDEFAULT.equals(location);
			case P2Package.REPOSITORY_REFERENCE__TYPE:
				return type != TYPE_EDEFAULT;
			case P2Package.REPOSITORY_REFERENCE__OPTIONS:
				return options != OPTIONS_EDEFAULT;
			case P2Package.REPOSITORY_REFERENCE__NICKNAME:
				return NICKNAME_EDEFAULT == null ? nickname != null : !NICKNAME_EDEFAULT.equals(nickname);
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
		result.append(" (location: ");
		result.append(location);
		result.append(", type: ");
		result.append(type);
		result.append(", options: ");
		result.append(options);
		result.append(", nickname: ");
		result.append(nickname);
		result.append(')');
		return result.toString();
	}

} // RepositoryReferenceImpl
