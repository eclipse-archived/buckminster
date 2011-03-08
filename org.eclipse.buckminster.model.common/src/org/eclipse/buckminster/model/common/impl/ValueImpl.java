/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.Value;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Value</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.model.common.impl.ValueImpl#isMutable <em>
 * Mutable</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class ValueImpl extends EObjectImpl implements Value {
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
	public static final String NO_VALUE = ""; //$NON-NLS-1$

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ValueImpl() {
		super();
	}

	/**
	 * Returns the resolved value of this holder using <code>properties</code>
	 * as the scope.
	 * 
	 * @param properties
	 *            The scope used when resolving the value.
	 * @param recursionGuard
	 *            A guard that is increased for each recursive expansion that is
	 *            made.
	 * @return A string representing the resolved value.
	 * @throws IllegalArgumentException
	 *             if the <code>recursionGuard</code> reaches its threshold.
	 */
	public abstract String checkedGetValue(Map<String, String> properties, int recursionGuard);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.VALUE__MUTABLE:
				return isMutable();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CommonPackage.VALUE__MUTABLE:
				return mutable != MUTABLE_EDEFAULT;
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
			case CommonPackage.VALUE__MUTABLE:
				setMutable((Boolean) newValue);
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
			case CommonPackage.VALUE__MUTABLE:
				setMutable(MUTABLE_EDEFAULT);
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
	public String getValue(Map<String, String> properties) {
		return checkedGetValue(properties, 0);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */

	@Override
	public boolean isMultiValued() {
		return false;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean isMutable() {
		return mutable;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setMutable(boolean newMutable) {
		boolean oldMutable = mutable;
		mutable = newMutable;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CommonPackage.VALUE__MUTABLE, oldMutable, mutable));
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
		result.append(" (mutable: ");
		result.append(mutable);
		result.append(')');
		return result.toString();
	}

	/**
	 * Returns resolved value array of this holder using <code>properties</code>
	 * as the scope.
	 * 
	 * @param properties
	 *            The scope used when resolving the values
	 * @param recursionGuard
	 *            A guard that is increased for each recursive expansion that is
	 *            made.
	 * @return A string array representing the resolved values
	 * @throws IllegalArgumentException
	 *             if the <code>recursionGuard</code> reaches its threshold.
	 */
	protected List<String> checkedGetValues(Map<String, String> properties, int recursionGuard) {
		return Collections.singletonList(checkedGetValue(properties, recursionGuard));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return CommonPackage.Literals.VALUE;
	}
} // ValueImpl
