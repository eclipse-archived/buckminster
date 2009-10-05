/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Format;
import org.eclipse.buckminster.model.common.PropertyElement;
import org.eclipse.buckminster.model.common.PropertyRef;
import org.eclipse.buckminster.model.common.Replace;
import org.eclipse.buckminster.model.common.ToLower;
import org.eclipse.buckminster.model.common.ToUpper;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object ' <em><b>Property Element</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getConstant <em>Constant</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getFormat <em>Format</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getPropertyRef <em>Property Ref</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getReplace <em>Replace</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getToLower <em>To Lower</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getToUpper <em>To Upper</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PropertyElementImpl extends PropertyImpl implements PropertyElement
{
	/**
	 * The cached value of the '{@link #getConstant() <em>Constant</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getConstant()
	 * @generated
	 * @ordered
	 */
	protected Constant constant;

	/**
	 * The cached value of the '{@link #getFormat() <em>Format</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getFormat()
	 * @generated
	 * @ordered
	 */
	protected Format format;

	/**
	 * The cached value of the '{@link #getPropertyRef() <em>Property Ref</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPropertyRef()
	 * @generated
	 * @ordered
	 */
	protected PropertyRef propertyRef;

	/**
	 * The cached value of the '{@link #getReplace() <em>Replace</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getReplace()
	 * @generated
	 * @ordered
	 */
	protected Replace replace;

	/**
	 * The cached value of the '{@link #getToLower() <em>To Lower</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getToLower()
	 * @generated
	 * @ordered
	 */
	protected ToLower toLower;

	/**
	 * The cached value of the '{@link #getToUpper() <em>To Upper</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getToUpper()
	 * @generated
	 * @ordered
	 */
	protected ToUpper toUpper;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PropertyElementImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetConstant(Constant newConstant, NotificationChain msgs)
	{
		Constant oldConstant = constant;
		constant = newConstant;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CommonPackage.PROPERTY_ELEMENT__CONSTANT, oldConstant, newConstant);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetFormat(Format newFormat, NotificationChain msgs)
	{
		Format oldFormat = format;
		format = newFormat;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CommonPackage.PROPERTY_ELEMENT__FORMAT, oldFormat, newFormat);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetPropertyRef(PropertyRef newPropertyRef, NotificationChain msgs)
	{
		PropertyRef oldPropertyRef = propertyRef;
		propertyRef = newPropertyRef;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF, oldPropertyRef, newPropertyRef);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetReplace(Replace newReplace, NotificationChain msgs)
	{
		Replace oldReplace = replace;
		replace = newReplace;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CommonPackage.PROPERTY_ELEMENT__REPLACE, oldReplace, newReplace);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetToLower(ToLower newToLower, NotificationChain msgs)
	{
		ToLower oldToLower = toLower;
		toLower = newToLower;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CommonPackage.PROPERTY_ELEMENT__TO_LOWER, oldToLower, newToLower);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetToUpper(ToUpper newToUpper, NotificationChain msgs)
	{
		ToUpper oldToUpper = toUpper;
		toUpper = newToUpper;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					CommonPackage.PROPERTY_ELEMENT__TO_UPPER, oldToUpper, newToUpper);
			if(msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
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
		case CommonPackage.PROPERTY_ELEMENT__CONSTANT:
			return getConstant();
		case CommonPackage.PROPERTY_ELEMENT__FORMAT:
			return getFormat();
		case CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF:
			return getPropertyRef();
		case CommonPackage.PROPERTY_ELEMENT__REPLACE:
			return getReplace();
		case CommonPackage.PROPERTY_ELEMENT__TO_LOWER:
			return getToLower();
		case CommonPackage.PROPERTY_ELEMENT__TO_UPPER:
			return getToUpper();
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
		case CommonPackage.PROPERTY_ELEMENT__CONSTANT:
			return basicSetConstant(null, msgs);
		case CommonPackage.PROPERTY_ELEMENT__FORMAT:
			return basicSetFormat(null, msgs);
		case CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF:
			return basicSetPropertyRef(null, msgs);
		case CommonPackage.PROPERTY_ELEMENT__REPLACE:
			return basicSetReplace(null, msgs);
		case CommonPackage.PROPERTY_ELEMENT__TO_LOWER:
			return basicSetToLower(null, msgs);
		case CommonPackage.PROPERTY_ELEMENT__TO_UPPER:
			return basicSetToUpper(null, msgs);
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
		case CommonPackage.PROPERTY_ELEMENT__CONSTANT:
			return constant != null;
		case CommonPackage.PROPERTY_ELEMENT__FORMAT:
			return format != null;
		case CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF:
			return propertyRef != null;
		case CommonPackage.PROPERTY_ELEMENT__REPLACE:
			return replace != null;
		case CommonPackage.PROPERTY_ELEMENT__TO_LOWER:
			return toLower != null;
		case CommonPackage.PROPERTY_ELEMENT__TO_UPPER:
			return toUpper != null;
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
		case CommonPackage.PROPERTY_ELEMENT__CONSTANT:
			setConstant((Constant)newValue);
			return;
		case CommonPackage.PROPERTY_ELEMENT__FORMAT:
			setFormat((Format)newValue);
			return;
		case CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF:
			setPropertyRef((PropertyRef)newValue);
			return;
		case CommonPackage.PROPERTY_ELEMENT__REPLACE:
			setReplace((Replace)newValue);
			return;
		case CommonPackage.PROPERTY_ELEMENT__TO_LOWER:
			setToLower((ToLower)newValue);
			return;
		case CommonPackage.PROPERTY_ELEMENT__TO_UPPER:
			setToUpper((ToUpper)newValue);
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
		case CommonPackage.PROPERTY_ELEMENT__CONSTANT:
			setConstant((Constant)null);
			return;
		case CommonPackage.PROPERTY_ELEMENT__FORMAT:
			setFormat((Format)null);
			return;
		case CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF:
			setPropertyRef((PropertyRef)null);
			return;
		case CommonPackage.PROPERTY_ELEMENT__REPLACE:
			setReplace((Replace)null);
			return;
		case CommonPackage.PROPERTY_ELEMENT__TO_LOWER:
			setToLower((ToLower)null);
			return;
		case CommonPackage.PROPERTY_ELEMENT__TO_UPPER:
			setToUpper((ToUpper)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Constant getConstant()
	{
		return constant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Format getFormat()
	{
		return format;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public PropertyRef getPropertyRef()
	{
		return propertyRef;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Replace getReplace()
	{
		return replace;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ToLower getToLower()
	{
		return toLower;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public ToUpper getToUpper()
	{
		return toUpper;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setConstant(Constant newConstant)
	{
		if(newConstant != constant)
		{
			NotificationChain msgs = null;
			if(constant != null)
				msgs = ((InternalEObject)constant).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__CONSTANT, null, msgs);
			if(newConstant != null)
				msgs = ((InternalEObject)newConstant).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__CONSTANT, null, msgs);
			msgs = basicSetConstant(newConstant, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_ELEMENT__CONSTANT,
					newConstant, newConstant));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFormat(Format newFormat)
	{
		if(newFormat != format)
		{
			NotificationChain msgs = null;
			if(format != null)
				msgs = ((InternalEObject)format).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__FORMAT, null, msgs);
			if(newFormat != null)
				msgs = ((InternalEObject)newFormat).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__FORMAT, null, msgs);
			msgs = basicSetFormat(newFormat, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_ELEMENT__FORMAT, newFormat,
					newFormat));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPropertyRef(PropertyRef newPropertyRef)
	{
		if(newPropertyRef != propertyRef)
		{
			NotificationChain msgs = null;
			if(propertyRef != null)
				msgs = ((InternalEObject)propertyRef).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF, null, msgs);
			if(newPropertyRef != null)
				msgs = ((InternalEObject)newPropertyRef).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF, null, msgs);
			msgs = basicSetPropertyRef(newPropertyRef, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_ELEMENT__PROPERTY_REF,
					newPropertyRef, newPropertyRef));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setReplace(Replace newReplace)
	{
		if(newReplace != replace)
		{
			NotificationChain msgs = null;
			if(replace != null)
				msgs = ((InternalEObject)replace).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__REPLACE, null, msgs);
			if(newReplace != null)
				msgs = ((InternalEObject)newReplace).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__REPLACE, null, msgs);
			msgs = basicSetReplace(newReplace, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_ELEMENT__REPLACE, newReplace,
					newReplace));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setToLower(ToLower newToLower)
	{
		if(newToLower != toLower)
		{
			NotificationChain msgs = null;
			if(toLower != null)
				msgs = ((InternalEObject)toLower).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__TO_LOWER, null, msgs);
			if(newToLower != null)
				msgs = ((InternalEObject)newToLower).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__TO_LOWER, null, msgs);
			msgs = basicSetToLower(newToLower, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_ELEMENT__TO_LOWER, newToLower,
					newToLower));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setToUpper(ToUpper newToUpper)
	{
		if(newToUpper != toUpper)
		{
			NotificationChain msgs = null;
			if(toUpper != null)
				msgs = ((InternalEObject)toUpper).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__TO_UPPER, null, msgs);
			if(newToUpper != null)
				msgs = ((InternalEObject)newToUpper).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- CommonPackage.PROPERTY_ELEMENT__TO_UPPER, null, msgs);
			msgs = basicSetToUpper(newToUpper, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_ELEMENT__TO_UPPER, newToUpper,
					newToUpper));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return CommonPackage.Literals.PROPERTY_ELEMENT;
	}

} // PropertyElementImpl
