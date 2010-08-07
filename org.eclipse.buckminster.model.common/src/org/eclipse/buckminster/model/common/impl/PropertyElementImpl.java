/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Value;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEMap;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Property Element</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getTypedKey <em>Key</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getValueGroup <em>Value Group</em>}</li>
 *   <li>{@link org.eclipse.buckminster.model.common.impl.PropertyElementImpl#getTypedValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyElementImpl extends EObjectImpl implements BasicEMap.Entry<String,Value> {
	/**
	 * The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTypedKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getTypedKey()
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getValueGroup() <em>Value Group</em>}' attribute list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getValueGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap valueGroup;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected int hash = -1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected PropertyElementImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypedValue(Value newValue, NotificationChain msgs) {
		return ((FeatureMap.Internal)getValueGroup()).basicAdd(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE, newValue, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.PROPERTY_ELEMENT__KEY:
				return getTypedKey();
			case CommonPackage.PROPERTY_ELEMENT__VALUE_GROUP:
				if (coreType) return getValueGroup();
				return ((FeatureMap.Internal)getValueGroup()).getWrapper();
			case CommonPackage.PROPERTY_ELEMENT__VALUE:
				return getTypedValue();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CommonPackage.PROPERTY_ELEMENT__VALUE_GROUP:
				return ((InternalEList<?>)getValueGroup()).basicRemove(otherEnd, msgs);
			case CommonPackage.PROPERTY_ELEMENT__VALUE:
				return basicSetTypedValue(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.PROPERTY_ELEMENT__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case CommonPackage.PROPERTY_ELEMENT__VALUE_GROUP:
				return valueGroup != null && !valueGroup.isEmpty();
			case CommonPackage.PROPERTY_ELEMENT__VALUE:
				return getTypedValue() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CommonPackage.PROPERTY_ELEMENT__KEY:
				setTypedKey((String)newValue);
				return;
			case CommonPackage.PROPERTY_ELEMENT__VALUE_GROUP:
				((FeatureMap.Internal)getValueGroup()).set(newValue);
				return;
			case CommonPackage.PROPERTY_ELEMENT__VALUE:
				setTypedValue((Value)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CommonPackage.PROPERTY_ELEMENT__KEY:
				setTypedKey(KEY_EDEFAULT);
				return;
			case CommonPackage.PROPERTY_ELEMENT__VALUE_GROUP:
				getValueGroup().clear();
				return;
			case CommonPackage.PROPERTY_ELEMENT__VALUE:
				setTypedValue((Value)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	public EMap<String, Value> getEMap() {
		EObject container = eContainer();
		return container == null ? null : (EMap<String, Value>)container.eGet(eContainmentFeature());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public int getHash() {
		if (hash == -1) {
			Object theKey = getKey();
			hash = (theKey == null ? 0 : theKey.hashCode());
		}
		return hash;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public String getKey() {
		return getTypedKey();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getTypedKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Value getTypedValue() {
		return (Value)getValueGroup().get(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Value getValue() {
		return getTypedValue();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap getValueGroup() {
		if (valueGroup == null) {
			valueGroup = new BasicFeatureMap(this, CommonPackage.PROPERTY_ELEMENT__VALUE_GROUP);
		}
		return valueGroup;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setHash(int hash) {
		this.hash = hash;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setKey(String key) {
		setTypedKey(key);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypedKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_ELEMENT__KEY, oldKey, key));
	}

	// This method is put here for a reason.
	// If we declare the value as 'changeable' we get dublicated entries in the
	// create menu (one
	// that, if used, causes xsi:type decl's in the XML). If we don't use
	// 'changeable' then we
	// this method is assumed to exist but it isn't generated
	public void setTypedValue(Value newValue) {
		getValueGroup().set(CommonPackage.Literals.PROPERTY_ELEMENT__VALUE, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Value setValue(Value value) {
		Value oldValue = getValue();
		setTypedValue(value);
		return oldValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (key: ");
		result.append(key);
		result.append(", valueGroup: ");
		result.append(valueGroup);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.PROPERTY_ELEMENT;
	}

} // PropertyElementImpl
