/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Constant;
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

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Property Constant</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyConstantImpl#getTypedKey
 * <em>Key</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyConstantImpl#getTypedValue
 * <em>Value</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyConstantImpl#isMutable
 * <em>Mutable</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.PropertyConstantImpl#getStringValue
 * <em>String Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PropertyConstantImpl extends EObjectImpl implements BasicEMap.Entry<String, Value> {
	/**
	 * The default value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTypedKey()
	 * @generated
	 * @ordered
	 */
	protected static final String KEY_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTypedKey() <em>Key</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTypedKey()
	 * @generated
	 * @ordered
	 */
	protected String key = KEY_EDEFAULT;

	/**
	 * The default value of the '{@link #isMutable() <em>Mutable</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMutable()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MUTABLE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isMutable() <em>Mutable</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isMutable()
	 * @generated
	 * @ordered
	 */
	protected boolean mutable = MUTABLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStringValue() <em>String Value</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStringValue()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_VALUE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringValue() <em>String Value</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStringValue()
	 * @generated
	 * @ordered
	 */
	protected String stringValue = STRING_VALUE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected int hash = -1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PropertyConstantImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetTypedValue(Value newValue, NotificationChain msgs) {
		// TODO: implement this method to set the contained 'Value' containment
		// reference
		// -> this method is automatically invoked to keep the containment
		// relationship in synch
		// -> do not modify other features
		// -> return msgs, after adding any generated Notification to it (if it
		// is null, a NotificationChain object must be created first)
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.PROPERTY_CONSTANT__KEY:
				return getTypedKey();
			case CommonPackage.PROPERTY_CONSTANT__VALUE:
				return getTypedValue();
			case CommonPackage.PROPERTY_CONSTANT__MUTABLE:
				return isMutable();
			case CommonPackage.PROPERTY_CONSTANT__STRING_VALUE:
				return getStringValue();
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
			case CommonPackage.PROPERTY_CONSTANT__VALUE:
				return basicSetTypedValue(null, msgs);
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
			case CommonPackage.PROPERTY_CONSTANT__KEY:
				return KEY_EDEFAULT == null ? key != null : !KEY_EDEFAULT.equals(key);
			case CommonPackage.PROPERTY_CONSTANT__VALUE:
				return getTypedValue() != null;
			case CommonPackage.PROPERTY_CONSTANT__MUTABLE:
				return mutable != MUTABLE_EDEFAULT;
			case CommonPackage.PROPERTY_CONSTANT__STRING_VALUE:
				return STRING_VALUE_EDEFAULT == null ? stringValue != null : !STRING_VALUE_EDEFAULT.equals(stringValue);
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
			case CommonPackage.PROPERTY_CONSTANT__KEY:
				setTypedKey((String) newValue);
				return;
			case CommonPackage.PROPERTY_CONSTANT__MUTABLE:
				setMutable((Boolean) newValue);
				return;
			case CommonPackage.PROPERTY_CONSTANT__STRING_VALUE:
				setStringValue((String) newValue);
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
			case CommonPackage.PROPERTY_CONSTANT__KEY:
				setTypedKey(KEY_EDEFAULT);
				return;
			case CommonPackage.PROPERTY_CONSTANT__MUTABLE:
				setMutable(MUTABLE_EDEFAULT);
				return;
			case CommonPackage.PROPERTY_CONSTANT__STRING_VALUE:
				setStringValue(STRING_VALUE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@SuppressWarnings("unchecked")
	public EMap<String, Value> getEMap() {
		EObject container = eContainer();
		return container == null ? null : (EMap<String, Value>) container.eGet(eContainmentFeature());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public int getHash() {
		if (hash == -1) {
			Object theKey = getKey();
			hash = (theKey == null ? 0 : theKey.hashCode());
		}
		return hash;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getKey() {
		return getTypedKey();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getStringValue() {
		return stringValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getTypedKey() {
		return key;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Value getTypedValue() {
		String tmp = getStringValue();
		if (tmp == null)
			return null;
		Constant c = CommonFactory.eINSTANCE.createConstant();
		c.setValue(tmp);
		c.setMutable(isMutable());
		return c;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Value getValue() {
		return getTypedValue();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isMutable() {
		return mutable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setHash(int hash) {
		this.hash = hash;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setKey(String key) {
		setTypedKey(key);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMutable(boolean newMutable) {
		boolean oldMutable = mutable;
		mutable = newMutable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_CONSTANT__MUTABLE, oldMutable, mutable));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStringValue(String newStringValue) {
		String oldStringValue = stringValue;
		stringValue = newStringValue;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_CONSTANT__STRING_VALUE, oldStringValue, stringValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setTypedKey(String newKey) {
		String oldKey = key;
		key = newKey;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.PROPERTY_CONSTANT__KEY, oldKey, key));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Value setTypedValue(Value value) {
		Value oldValue = getValue();
		if (value == null) {
			setStringValue(STRING_VALUE_EDEFAULT);
			setMutable(MUTABLE_EDEFAULT);
		} else if (value instanceof Constant) {
			setStringValue(((Constant) value).getValue());
			setMutable(value.isMutable());
		}
		return oldValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Value setValue(Value value) {
		Value oldValue = getValue();
		setTypedValue(value);
		return oldValue;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (key: ");
		result.append(key);
		result.append(", mutable: ");
		result.append(mutable);
		result.append(", stringValue: ");
		result.append(stringValue);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.PROPERTY_CONSTANT;
	}

} // PropertyConstantImpl
