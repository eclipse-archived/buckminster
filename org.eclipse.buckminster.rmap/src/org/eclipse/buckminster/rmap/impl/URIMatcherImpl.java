/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;

import org.eclipse.buckminster.model.common.RxPart;

import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.URIMatcher;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>URI Matcher</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl#getBase <em>Base</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.URIMatcherImpl#getRxParts <em>Rx Parts</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class URIMatcherImpl extends EObjectImpl implements URIMatcher
{
	/**
	 * The default value of the '{@link #getBase() <em>Base</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBase() <em>Base</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getBase()
	 * @generated
	 * @ordered
	 */
	protected String base = BASE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRxParts() <em>Rx Parts</em>}' containment reference list. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getRxParts()
	 * @generated
	 * @ordered
	 */
	protected EList<RxPart> rxParts;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected URIMatcherImpl()
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
		case RmapPackage.URI_MATCHER__BASE:
			return getBase();
		case RmapPackage.URI_MATCHER__RX_PARTS:
			return getRxParts();
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
		case RmapPackage.URI_MATCHER__RX_PARTS:
			return ((InternalEList<?>)getRxParts()).basicRemove(otherEnd, msgs);
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
		case RmapPackage.URI_MATCHER__BASE:
			return BASE_EDEFAULT == null
					? base != null
					: !BASE_EDEFAULT.equals(base);
		case RmapPackage.URI_MATCHER__RX_PARTS:
			return rxParts != null && !rxParts.isEmpty();
		}
		return super.eIsSet(featureID);
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
		case RmapPackage.URI_MATCHER__BASE:
			setBase((String)newValue);
			return;
		case RmapPackage.URI_MATCHER__RX_PARTS:
			getRxParts().clear();
			getRxParts().addAll((Collection<? extends RxPart>)newValue);
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
		case RmapPackage.URI_MATCHER__BASE:
			setBase(BASE_EDEFAULT);
			return;
		case RmapPackage.URI_MATCHER__RX_PARTS:
			getRxParts().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getBase()
	{
		return base;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<RxPart> getRxParts()
	{
		if(rxParts == null)
		{
			rxParts = new EObjectContainmentEList<RxPart>(RxPart.class, this, RmapPackage.URI_MATCHER__RX_PARTS);
		}
		return rxParts;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setBase(String newBase)
	{
		String oldBase = base;
		base = newBase;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.URI_MATCHER__BASE, oldBase, base));
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
		result.append(" (base: ");
		result.append(base);
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
		return RmapPackage.Literals.URI_MATCHER;
	}

} // URIMatcherImpl
