/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import org.eclipse.buckminster.cspec.AlterAttribute;
import org.eclipse.buckminster.cspec.CSpecExtension;
import org.eclipse.buckminster.cspec.CspecPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Alter Attribute</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AlterAttributeImpl#getCspecext <em>Cspecext</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class AlterAttributeImpl extends EObjectImpl implements AlterAttribute
{
	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AlterAttributeImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetCspecext(CSpecExtension newCspecext, NotificationChain msgs)
	{
		msgs = eBasicSetContainer((InternalEObject)newCspecext, CspecPackage.ALTER_ATTRIBUTE__CSPECEXT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
	{
		switch(eContainerFeatureID())
		{
		case CspecPackage.ALTER_ATTRIBUTE__CSPECEXT:
			return eInternalContainer().eInverseRemove(this, CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS,
					CSpecExtension.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
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
		case CspecPackage.ALTER_ATTRIBUTE__CSPECEXT:
			return getCspecext();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case CspecPackage.ALTER_ATTRIBUTE__CSPECEXT:
			if(eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetCspecext((CSpecExtension)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
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
		case CspecPackage.ALTER_ATTRIBUTE__CSPECEXT:
			return basicSetCspecext(null, msgs);
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
		case CspecPackage.ALTER_ATTRIBUTE__CSPECEXT:
			return getCspecext() != null;
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
		case CspecPackage.ALTER_ATTRIBUTE__CSPECEXT:
			setCspecext((CSpecExtension)newValue);
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
		case CspecPackage.ALTER_ATTRIBUTE__CSPECEXT:
			setCspecext((CSpecExtension)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpecExtension getCspecext()
	{
		if(eContainerFeatureID() != CspecPackage.ALTER_ATTRIBUTE__CSPECEXT)
			return null;
		return (CSpecExtension)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCspecext(CSpecExtension newCspecext)
	{
		if(newCspecext != eInternalContainer()
				|| (eContainerFeatureID() != CspecPackage.ALTER_ATTRIBUTE__CSPECEXT && newCspecext != null))
		{
			if(EcoreUtil.isAncestor(this, newCspecext))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if(eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if(newCspecext != null)
				msgs = ((InternalEObject)newCspecext).eInverseAdd(this,
						CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS, CSpecExtension.class, msgs);
			msgs = basicSetCspecext(newCspecext, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ALTER_ATTRIBUTE__CSPECEXT, newCspecext,
					newCspecext));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return CspecPackage.Literals.ALTER_ATTRIBUTE;
	}

} // AlterAttributeImpl
