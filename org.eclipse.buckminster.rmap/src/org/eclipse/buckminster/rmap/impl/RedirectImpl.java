/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import org.eclipse.buckminster.rmap.Redirect;
import org.eclipse.buckminster.rmap.RmapPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Redirect</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.RedirectImpl#getRedirectTo <em>
 * Redirect To</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RedirectImpl extends MatcherImpl implements Redirect {
	/**
	 * The default value of the '{@link #getRedirectTo() <em>Redirect To</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRedirectTo()
	 * @generated
	 * @ordered
	 */
	protected static final String REDIRECT_TO_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRedirectTo() <em>Redirect To</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRedirectTo()
	 * @generated
	 * @ordered
	 */
	protected String redirectTo = REDIRECT_TO_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RedirectImpl() {
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
			case RmapPackage.REDIRECT__REDIRECT_TO:
				return getRedirectTo();
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
			case RmapPackage.REDIRECT__REDIRECT_TO:
				return REDIRECT_TO_EDEFAULT == null ? redirectTo != null : !REDIRECT_TO_EDEFAULT.equals(redirectTo);
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
			case RmapPackage.REDIRECT__REDIRECT_TO:
				setRedirectTo((String) newValue);
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
			case RmapPackage.REDIRECT__REDIRECT_TO:
				setRedirectTo(REDIRECT_TO_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String getRedirectTo() {
		return redirectTo;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void setRedirectTo(String newRedirectTo) {
		String oldRedirectTo = redirectTo;
		redirectTo = newRedirectTo;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.REDIRECT__REDIRECT_TO, oldRedirectTo, redirectTo));
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
		result.append(" (redirectTo: ");
		result.append(redirectTo);
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
		return RmapPackage.Literals.REDIRECT;
	}

} // RedirectImpl
