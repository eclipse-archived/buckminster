/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.mspec.impl;

import org.eclipse.buckminster.mspec.MspecPackage;
import org.eclipse.buckminster.mspec.Unpack;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Unpack</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.mspec.impl.UnpackImpl#isExpand <em>Expand
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.mspec.impl.UnpackImpl#getSuffix <em>Suffix
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class UnpackImpl extends EObjectImpl implements Unpack {
	/**
	 * The default value of the '{@link #isExpand() <em>Expand</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isExpand()
	 * @generated
	 * @ordered
	 */
	protected static final boolean EXPAND_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isExpand() <em>Expand</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isExpand()
	 * @generated
	 * @ordered
	 */
	protected boolean expand = EXPAND_EDEFAULT;

	/**
	 * The default value of the '{@link #getSuffix() <em>Suffix</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSuffix()
	 * @generated
	 * @ordered
	 */
	protected static final String SUFFIX_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSuffix() <em>Suffix</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSuffix()
	 * @generated
	 * @ordered
	 */
	protected String suffix = SUFFIX_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected UnpackImpl() {
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
			case MspecPackage.UNPACK__EXPAND:
				return isExpand();
			case MspecPackage.UNPACK__SUFFIX:
				return getSuffix();
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
			case MspecPackage.UNPACK__EXPAND:
				return expand != EXPAND_EDEFAULT;
			case MspecPackage.UNPACK__SUFFIX:
				return SUFFIX_EDEFAULT == null ? suffix != null : !SUFFIX_EDEFAULT.equals(suffix);
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
			case MspecPackage.UNPACK__EXPAND:
				setExpand((Boolean) newValue);
				return;
			case MspecPackage.UNPACK__SUFFIX:
				setSuffix((String) newValue);
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
			case MspecPackage.UNPACK__EXPAND:
				setExpand(EXPAND_EDEFAULT);
				return;
			case MspecPackage.UNPACK__SUFFIX:
				setSuffix(SUFFIX_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getSuffix() {
		return suffix;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isExpand() {
		return expand;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setExpand(boolean newExpand) {
		boolean oldExpand = expand;
		expand = newExpand;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.UNPACK__EXPAND, oldExpand, expand));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSuffix(String newSuffix) {
		String oldSuffix = suffix;
		suffix = newSuffix;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MspecPackage.UNPACK__SUFFIX, oldSuffix, suffix));
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
		result.append(" (expand: ");
		result.append(expand);
		result.append(", suffix: ");
		result.append(suffix);
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
		return MspecPackage.Literals.UNPACK;
	}

} // UnpackImpl
