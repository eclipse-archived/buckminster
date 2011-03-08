/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Map;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Properties;
import org.eclipse.buckminster.model.common.Value;
import org.eclipse.buckminster.model.common.util.ExpandingProperties;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Properties</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertiesImpl#getPropertyConstants
 * <em>Property Constants</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertiesImpl#getPropertyElements
 * <em>Property Elements</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class PropertiesImpl extends EObjectImpl implements Properties {
	public static class UniquePreservingEcoreEMap<K, V> extends EcoreEMap<K, V> {
		private static final long serialVersionUID = -949893869873732019L;

		public UniquePreservingEcoreEMap(EClass entryEClass, Class<?> entryClass,
				EList<org.eclipse.emf.common.util.BasicEMap.Entry<K, V>> delegateEList) {
			super(entryEClass, entryClass, delegateEList);
		}

		public UniquePreservingEcoreEMap(EClass entryEClass, Class<?> entryClass, InternalEObject owner, int featureID) {
			super(entryEClass, entryClass, owner, featureID);
		}

		@Override
		public boolean add(Map.Entry<K, V> e) {
			int idx = indexOfKey(e.getKey());
			if (idx >= 0) {
				set(idx, e);
				return false;
			}
			return super.add(e);
		}

		@Override
		public void addUnique(Map.Entry<K, V> e) {
			int idx = indexOfKey(e.getKey());
			if (idx >= 0)
				set(idx, e);
			else
				super.add(e);
		}
	}

	/**
	 * The cached value of the '{@link #getPropertyConstants()
	 * <em>Property Constants</em>}' map. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPropertyConstants()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Value> propertyConstants;

	/**
	 * The cached value of the '{@link #getPropertyElements()
	 * <em>Property Elements</em>}' map. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getPropertyElements()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, Value> propertyElements;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PropertiesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.PROPERTIES__PROPERTY_CONSTANTS:
				if (coreType)
					return getPropertyConstants();
				else
					return getPropertyConstants().map();
			case CommonPackage.PROPERTIES__PROPERTY_ELEMENTS:
				if (coreType)
					return getPropertyElements();
				else
					return getPropertyElements().map();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.PROPERTIES__PROPERTY_CONSTANTS:
				return ((InternalEList<?>) getPropertyConstants()).basicRemove(otherEnd, msgs);
			case CommonPackage.PROPERTIES__PROPERTY_ELEMENTS:
				return ((InternalEList<?>) getPropertyElements()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.PROPERTIES__PROPERTY_CONSTANTS:
				return propertyConstants != null && !propertyConstants.isEmpty();
			case CommonPackage.PROPERTIES__PROPERTY_ELEMENTS:
				return propertyElements != null && !propertyElements.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.PROPERTIES__PROPERTY_CONSTANTS:
				((EStructuralFeature.Setting) getPropertyConstants()).set(newValue);
				return;
			case CommonPackage.PROPERTIES__PROPERTY_ELEMENTS:
				((EStructuralFeature.Setting) getPropertyElements()).set(newValue);
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
	public void eUnset(int featureID) {
		switch (featureID) {
			case CommonPackage.PROPERTIES__PROPERTY_CONSTANTS:
				getPropertyConstants().clear();
				return;
			case CommonPackage.PROPERTIES__PROPERTY_ELEMENTS:
				getPropertyElements().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Map<String, String> getProperties() {
		return new ExpandingProperties(getPropertyElements().map(), getPropertyConstants().map());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EMap<String, Value> getPropertyConstants() {
		if (propertyConstants == null) {
			propertyConstants = new UniquePreservingEcoreEMap<String, Value>(CommonPackage.Literals.PROPERTY_CONSTANT, PropertyConstantImpl.class,
					this, CommonPackage.PROPERTIES__PROPERTY_CONSTANTS);
		}
		return propertyConstants;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public EMap<String, Value> getPropertyElements() {
		if (propertyElements == null) {
			propertyElements = new UniquePreservingEcoreEMap<String, Value>(CommonPackage.Literals.PROPERTY_ELEMENT, PropertyElementImpl.class, this,
					CommonPackage.PROPERTIES__PROPERTY_ELEMENTS);
		}
		return propertyElements;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.PROPERTIES;
	}

} // PropertiesImpl
