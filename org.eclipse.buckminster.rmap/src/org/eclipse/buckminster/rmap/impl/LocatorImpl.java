/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import org.eclipse.buckminster.rmap.Locator;
import org.eclipse.buckminster.rmap.RmapPackage;
import org.eclipse.buckminster.rmap.SearchPath;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Locator</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.impl.LocatorImpl#getSearchPath <em>
 * Search Path</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.impl.LocatorImpl#isFailOnError <em>
 * Fail On Error</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class LocatorImpl extends MatcherImpl implements Locator {
	/**
	 * The cached value of the '{@link #getSearchPath() <em>Search Path</em>}'
	 * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSearchPath()
	 * @generated
	 * @ordered
	 */
	protected SearchPath searchPath;

	/**
	 * The default value of the '{@link #isFailOnError() <em>Fail On Error</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isFailOnError()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FAIL_ON_ERROR_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isFailOnError() <em>Fail On Error</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isFailOnError()
	 * @generated
	 * @ordered
	 */
	protected boolean failOnError = FAIL_ON_ERROR_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected LocatorImpl() {
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
			case RmapPackage.LOCATOR__SEARCH_PATH:
				return getSearchPath();
			case RmapPackage.LOCATOR__FAIL_ON_ERROR:
				return isFailOnError();
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
			case RmapPackage.LOCATOR__SEARCH_PATH:
				return searchPath != null;
			case RmapPackage.LOCATOR__FAIL_ON_ERROR:
				return failOnError != FAIL_ON_ERROR_EDEFAULT;
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
			case RmapPackage.LOCATOR__SEARCH_PATH:
				setSearchPath((SearchPath) newValue);
				return;
			case RmapPackage.LOCATOR__FAIL_ON_ERROR:
				setFailOnError((Boolean) newValue);
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
			case RmapPackage.LOCATOR__SEARCH_PATH:
				setSearchPath((SearchPath) null);
				return;
			case RmapPackage.LOCATOR__FAIL_ON_ERROR:
				setFailOnError(FAIL_ON_ERROR_EDEFAULT);
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
	public SearchPath getSearchPath() {
		return searchPath;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isFailOnError() {
		return failOnError;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setFailOnError(boolean newFailOnError) {
		boolean oldFailOnError = failOnError;
		failOnError = newFailOnError;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.LOCATOR__FAIL_ON_ERROR, oldFailOnError, failOnError));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSearchPath(SearchPath newSearchPath) {
		SearchPath oldSearchPath = searchPath;
		searchPath = newSearchPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.LOCATOR__SEARCH_PATH, oldSearchPath, searchPath));
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
		result.append(" (failOnError: ");
		result.append(failOnError);
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
		return RmapPackage.Literals.LOCATOR;
	}

} // LocatorImpl
