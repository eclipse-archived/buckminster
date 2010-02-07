/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Rename;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rename</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.RenameImpl#getOldName <em>Old
 * Name</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.RenameImpl#getNewName <em>New
 * Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RenameImpl extends EObjectImpl implements Rename {
	/**
	 * The default value of the '{@link #getOldName() <em>Old Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOldName()
	 * @generated
	 * @ordered
	 */
	protected static final String OLD_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getOldName() <em>Old Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOldName()
	 * @generated
	 * @ordered
	 */
	protected String oldName = OLD_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getNewName() <em>New Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNewName()
	 * @generated
	 * @ordered
	 */
	protected static final String NEW_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNewName() <em>New Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNewName()
	 * @generated
	 * @ordered
	 */
	protected String newName = NEW_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RenameImpl() {
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
			case CspecPackage.RENAME__OLD_NAME:
				return getOldName();
			case CspecPackage.RENAME__NEW_NAME:
				return getNewName();
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
			case CspecPackage.RENAME__OLD_NAME:
				return OLD_NAME_EDEFAULT == null ? oldName != null : !OLD_NAME_EDEFAULT.equals(oldName);
			case CspecPackage.RENAME__NEW_NAME:
				return NEW_NAME_EDEFAULT == null ? newName != null : !NEW_NAME_EDEFAULT.equals(newName);
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
			case CspecPackage.RENAME__OLD_NAME:
				setOldName((String) newValue);
				return;
			case CspecPackage.RENAME__NEW_NAME:
				setNewName((String) newValue);
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
			case CspecPackage.RENAME__OLD_NAME:
				setOldName(OLD_NAME_EDEFAULT);
				return;
			case CspecPackage.RENAME__NEW_NAME:
				setNewName(NEW_NAME_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getNewName() {
		return newName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getOldName() {
		return oldName;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setNewName(String newNewName) {
		String oldNewName = newName;
		newName = newNewName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.RENAME__NEW_NAME, oldNewName, newName));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setOldName(String newOldName) {
		String oldOldName = oldName;
		oldName = newOldName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.RENAME__OLD_NAME, oldOldName, oldName));
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
		result.append(" (oldName: ");
		result.append(oldName);
		result.append(", newName: ");
		result.append(newName);
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
		return CspecPackage.Literals.RENAME;
	}

} // RenameImpl
