/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;

import org.eclipse.buckminster.model.common.PropertyConstant;
import org.eclipse.buckminster.model.common.PropertyElement;

import org.eclipse.buckminster.rmap.Properties;
import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Properties</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.PropertiesImpl#getProperties <em>Properties</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.PropertiesImpl#getPropertyElements <em>Property Elements</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PropertiesImpl extends EObjectImpl implements Properties
{
	/**
	 * The cached value of the '{@link #getProperties() <em>Properties</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyConstant> properties;

	/**
	 * The cached value of the '{@link #getPropertyElements() <em>Property Elements</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPropertyElements()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyElement> propertyElements;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PropertiesImpl()
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
		case RmapPackage.PROPERTIES__PROPERTIES:
			return getProperties();
		case RmapPackage.PROPERTIES__PROPERTY_ELEMENTS:
			return getPropertyElements();
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
		case RmapPackage.PROPERTIES__PROPERTIES:
			return ((InternalEList<?>)getProperties()).basicRemove(otherEnd, msgs);
		case RmapPackage.PROPERTIES__PROPERTY_ELEMENTS:
			return ((InternalEList<?>)getPropertyElements()).basicRemove(otherEnd, msgs);
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
		case RmapPackage.PROPERTIES__PROPERTIES:
			return properties != null && !properties.isEmpty();
		case RmapPackage.PROPERTIES__PROPERTY_ELEMENTS:
			return propertyElements != null && !propertyElements.isEmpty();
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
		case RmapPackage.PROPERTIES__PROPERTIES:
			getProperties().clear();
			getProperties().addAll((Collection<? extends PropertyConstant>)newValue);
			return;
		case RmapPackage.PROPERTIES__PROPERTY_ELEMENTS:
			getPropertyElements().clear();
			getPropertyElements().addAll((Collection<? extends PropertyElement>)newValue);
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
		case RmapPackage.PROPERTIES__PROPERTIES:
			getProperties().clear();
			return;
		case RmapPackage.PROPERTIES__PROPERTY_ELEMENTS:
			getPropertyElements().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<PropertyConstant> getProperties()
	{
		if(properties == null)
		{
			properties = new EObjectContainmentEList<PropertyConstant>(PropertyConstant.class, this,
					RmapPackage.PROPERTIES__PROPERTIES);
		}
		return properties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<PropertyElement> getPropertyElements()
	{
		if(propertyElements == null)
		{
			propertyElements = new EObjectContainmentEList<PropertyElement>(PropertyElement.class, this,
					RmapPackage.PROPERTIES__PROPERTY_ELEMENTS);
		}
		return propertyElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return RmapPackage.Literals.PROPERTIES;
	}

} // PropertiesImpl
