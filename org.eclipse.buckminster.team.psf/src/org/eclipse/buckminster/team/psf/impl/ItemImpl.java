/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf.impl;

import org.eclipse.buckminster.team.psf.Item;
import org.eclipse.buckminster.team.psf.PsfPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Item</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.ItemImpl#getFactoryID <em>
 * Factory ID</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.ItemImpl#getElementID <em>
 * Element ID</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.ItemImpl#getPath <em>Path
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.ItemImpl#getType <em>Type
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ItemImpl extends EObjectImpl implements Item {
	/**
	 * The default value of the '{@link #getFactoryID() <em>Factory ID</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFactoryID()
	 * @generated
	 * @ordered
	 */
	protected static final String FACTORY_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getFactoryID() <em>Factory ID</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getFactoryID()
	 * @generated
	 * @ordered
	 */
	protected String factoryID = FACTORY_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getElementID() <em>Element ID</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getElementID()
	 * @generated
	 * @ordered
	 */
	protected static final String ELEMENT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getElementID() <em>Element ID</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getElementID()
	 * @generated
	 * @ordered
	 */
	protected String elementID = ELEMENT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected static final String PATH_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPath() <em>Path</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPath()
	 * @generated
	 * @ordered
	 */
	protected String path = PATH_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final int TYPE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected int type = TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ItemImpl() {
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
			case PsfPackage.ITEM__FACTORY_ID:
				return getFactoryID();
			case PsfPackage.ITEM__ELEMENT_ID:
				return getElementID();
			case PsfPackage.ITEM__PATH:
				return getPath();
			case PsfPackage.ITEM__TYPE:
				return getType();
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
			case PsfPackage.ITEM__FACTORY_ID:
				return FACTORY_ID_EDEFAULT == null ? factoryID != null : !FACTORY_ID_EDEFAULT.equals(factoryID);
			case PsfPackage.ITEM__ELEMENT_ID:
				return ELEMENT_ID_EDEFAULT == null ? elementID != null : !ELEMENT_ID_EDEFAULT.equals(elementID);
			case PsfPackage.ITEM__PATH:
				return PATH_EDEFAULT == null ? path != null : !PATH_EDEFAULT.equals(path);
			case PsfPackage.ITEM__TYPE:
				return type != TYPE_EDEFAULT;
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
			case PsfPackage.ITEM__FACTORY_ID:
				setFactoryID((String) newValue);
				return;
			case PsfPackage.ITEM__ELEMENT_ID:
				setElementID((String) newValue);
				return;
			case PsfPackage.ITEM__PATH:
				setPath((String) newValue);
				return;
			case PsfPackage.ITEM__TYPE:
				setType((Integer) newValue);
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
			case PsfPackage.ITEM__FACTORY_ID:
				setFactoryID(FACTORY_ID_EDEFAULT);
				return;
			case PsfPackage.ITEM__ELEMENT_ID:
				setElementID(ELEMENT_ID_EDEFAULT);
				return;
			case PsfPackage.ITEM__PATH:
				setPath(PATH_EDEFAULT);
				return;
			case PsfPackage.ITEM__TYPE:
				setType(TYPE_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getElementID() {
		return elementID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getFactoryID() {
		return factoryID;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getPath() {
		return path;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public int getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setElementID(String newElementID) {
		String oldElementID = elementID;
		elementID = newElementID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.ITEM__ELEMENT_ID, oldElementID, elementID));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setFactoryID(String newFactoryID) {
		String oldFactoryID = factoryID;
		factoryID = newFactoryID;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.ITEM__FACTORY_ID, oldFactoryID, factoryID));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setPath(String newPath) {
		String oldPath = path;
		path = newPath;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.ITEM__PATH, oldPath, path));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setType(int newType) {
		int oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.ITEM__TYPE, oldType, type));
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
		result.append(" (factoryID: ");
		result.append(factoryID);
		result.append(", elementID: ");
		result.append(elementID);
		result.append(", path: ");
		result.append(path);
		result.append(", type: ");
		result.append(type);
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
		return PsfPackage.Literals.ITEM;
	}

} // ItemImpl
