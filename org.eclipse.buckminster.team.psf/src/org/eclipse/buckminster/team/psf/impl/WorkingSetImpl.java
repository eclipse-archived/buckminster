/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf.impl;

import java.util.Collection;

import org.eclipse.buckminster.team.psf.Item;
import org.eclipse.buckminster.team.psf.PsfPackage;
import org.eclipse.buckminster.team.psf.WorkingSet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Working Set</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.WorkingSetImpl#getId <em>Id
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.WorkingSetImpl#getLabel <em>
 * Label</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.WorkingSetImpl#getName <em>
 * Name</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.team.psf.impl.WorkingSetImpl#getEditPageId
 * <em>Edit Page Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.WorkingSetImpl#getItems <em>
 * Items</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class WorkingSetImpl extends EObjectImpl implements WorkingSet {
	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getEditPageId() <em>Edit Page Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEditPageId()
	 * @generated
	 * @ordered
	 */
	protected static final String EDIT_PAGE_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEditPageId() <em>Edit Page Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEditPageId()
	 * @generated
	 * @ordered
	 */
	protected String editPageId = EDIT_PAGE_ID_EDEFAULT;

	/**
	 * The cached value of the '{@link #getItems() <em>Items</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getItems()
	 * @generated
	 * @ordered
	 */
	protected EList<Item> items;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected WorkingSetImpl() {
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
			case PsfPackage.WORKING_SET__ID:
				return getId();
			case PsfPackage.WORKING_SET__LABEL:
				return getLabel();
			case PsfPackage.WORKING_SET__NAME:
				return getName();
			case PsfPackage.WORKING_SET__EDIT_PAGE_ID:
				return getEditPageId();
			case PsfPackage.WORKING_SET__ITEMS:
				return getItems();
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
			case PsfPackage.WORKING_SET__ITEMS:
				return ((InternalEList<?>) getItems()).basicRemove(otherEnd, msgs);
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
			case PsfPackage.WORKING_SET__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case PsfPackage.WORKING_SET__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case PsfPackage.WORKING_SET__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case PsfPackage.WORKING_SET__EDIT_PAGE_ID:
				return EDIT_PAGE_ID_EDEFAULT == null ? editPageId != null : !EDIT_PAGE_ID_EDEFAULT.equals(editPageId);
			case PsfPackage.WORKING_SET__ITEMS:
				return items != null && !items.isEmpty();
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case PsfPackage.WORKING_SET__ID:
				setId((String) newValue);
				return;
			case PsfPackage.WORKING_SET__LABEL:
				setLabel((String) newValue);
				return;
			case PsfPackage.WORKING_SET__NAME:
				setName((String) newValue);
				return;
			case PsfPackage.WORKING_SET__EDIT_PAGE_ID:
				setEditPageId((String) newValue);
				return;
			case PsfPackage.WORKING_SET__ITEMS:
				getItems().clear();
				getItems().addAll((Collection<? extends Item>) newValue);
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
			case PsfPackage.WORKING_SET__ID:
				setId(ID_EDEFAULT);
				return;
			case PsfPackage.WORKING_SET__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case PsfPackage.WORKING_SET__NAME:
				setName(NAME_EDEFAULT);
				return;
			case PsfPackage.WORKING_SET__EDIT_PAGE_ID:
				setEditPageId(EDIT_PAGE_ID_EDEFAULT);
				return;
			case PsfPackage.WORKING_SET__ITEMS:
				getItems().clear();
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
	public String getEditPageId() {
		return editPageId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Item> getItems() {
		if (items == null) {
			items = new EObjectContainmentEList<Item>(Item.class, this, PsfPackage.WORKING_SET__ITEMS);
		}
		return items;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEditPageId(String newEditPageId) {
		String oldEditPageId = editPageId;
		editPageId = newEditPageId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.WORKING_SET__EDIT_PAGE_ID, oldEditPageId, editPageId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.WORKING_SET__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.WORKING_SET__LABEL, oldLabel, label));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.WORKING_SET__NAME, oldName, name));
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
		result.append(" (id: ");
		result.append(id);
		result.append(", label: ");
		result.append(label);
		result.append(", name: ");
		result.append(name);
		result.append(", editPageId: ");
		result.append(editPageId);
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
		return PsfPackage.Literals.WORKING_SET;
	}

} // WorkingSetImpl
