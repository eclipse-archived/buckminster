/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.impl;

import java.util.Collection;

import org.eclipse.buckminster.model.common.Documentation;
import org.eclipse.buckminster.model.common.Format;

import org.eclipse.buckminster.rmap.BranchPoint;
import org.eclipse.buckminster.rmap.Repository;
import org.eclipse.buckminster.rmap.RmapPackage;

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
 * <em><b>Repository</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.RepositoryImpl#getId <em>Id</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.RepositoryImpl#getConnection <em>Connection</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.RepositoryImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.RepositoryImpl#getBranches <em>Branches</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.RepositoryImpl#isCheckout <em>Checkout</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.RepositoryImpl#isAllowDirty <em>Allow Dirty</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.RepositoryImpl#isUpdate <em>Update</em>}</li>
 *   <li>{@link org.eclipse.buckminster.rmap.impl.RepositoryImpl#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RepositoryImpl extends EObjectImpl implements Repository {
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
	 * The cached value of the '{@link #getConnection() <em>Connection</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getConnection()
	 * @generated
	 * @ordered
	 */
	protected Format connection;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final String TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected String type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getBranches() <em>Branches</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBranches()
	 * @generated
	 * @ordered
	 */
	protected EList<BranchPoint> branches;

	/**
	 * The default value of the '{@link #isCheckout() <em>Checkout</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCheckout()
	 * @generated
	 * @ordered
	 */
	protected static final boolean CHECKOUT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isCheckout() <em>Checkout</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isCheckout()
	 * @generated
	 * @ordered
	 */
	protected boolean checkout = CHECKOUT_EDEFAULT;

	/**
	 * The default value of the '{@link #isAllowDirty() <em>Allow Dirty</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isAllowDirty()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ALLOW_DIRTY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isAllowDirty() <em>Allow Dirty</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isAllowDirty()
	 * @generated
	 * @ordered
	 */
	protected boolean allowDirty = ALLOW_DIRTY_EDEFAULT;

	/**
	 * The default value of the '{@link #isUpdate() <em>Update</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUpdate()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UPDATE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isUpdate() <em>Update</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #isUpdate()
	 * @generated
	 * @ordered
	 */
	protected boolean update = UPDATE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDocumentation()
	 * @generated
	 * @ordered
	 */
	protected Documentation documentation;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected RepositoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetConnection(Format newConnection, NotificationChain msgs) {
		Format oldConnection = connection;
		connection = newConnection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__CONNECTION, oldConnection, newConnection);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDocumentation(Documentation newDocumentation, NotificationChain msgs) {
		Documentation oldDocumentation = documentation;
		documentation = newDocumentation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__DOCUMENTATION, oldDocumentation, newDocumentation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RmapPackage.REPOSITORY__ID:
				return getId();
			case RmapPackage.REPOSITORY__CONNECTION:
				return getConnection();
			case RmapPackage.REPOSITORY__TYPE:
				return getType();
			case RmapPackage.REPOSITORY__BRANCHES:
				return getBranches();
			case RmapPackage.REPOSITORY__CHECKOUT:
				return isCheckout();
			case RmapPackage.REPOSITORY__ALLOW_DIRTY:
				return isAllowDirty();
			case RmapPackage.REPOSITORY__UPDATE:
				return isUpdate();
			case RmapPackage.REPOSITORY__DOCUMENTATION:
				return getDocumentation();
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
			case RmapPackage.REPOSITORY__CONNECTION:
				return basicSetConnection(null, msgs);
			case RmapPackage.REPOSITORY__BRANCHES:
				return ((InternalEList<?>)getBranches()).basicRemove(otherEnd, msgs);
			case RmapPackage.REPOSITORY__DOCUMENTATION:
				return basicSetDocumentation(null, msgs);
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
			case RmapPackage.REPOSITORY__ID:
				return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
			case RmapPackage.REPOSITORY__CONNECTION:
				return connection != null;
			case RmapPackage.REPOSITORY__TYPE:
				return TYPE_EDEFAULT == null ? type != null : !TYPE_EDEFAULT.equals(type);
			case RmapPackage.REPOSITORY__BRANCHES:
				return branches != null && !branches.isEmpty();
			case RmapPackage.REPOSITORY__CHECKOUT:
				return checkout != CHECKOUT_EDEFAULT;
			case RmapPackage.REPOSITORY__ALLOW_DIRTY:
				return allowDirty != ALLOW_DIRTY_EDEFAULT;
			case RmapPackage.REPOSITORY__UPDATE:
				return update != UPDATE_EDEFAULT;
			case RmapPackage.REPOSITORY__DOCUMENTATION:
				return documentation != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case RmapPackage.REPOSITORY__ID:
				setId((String)newValue);
				return;
			case RmapPackage.REPOSITORY__CONNECTION:
				setConnection((Format)newValue);
				return;
			case RmapPackage.REPOSITORY__TYPE:
				setType((String)newValue);
				return;
			case RmapPackage.REPOSITORY__BRANCHES:
				getBranches().clear();
				getBranches().addAll((Collection<? extends BranchPoint>)newValue);
				return;
			case RmapPackage.REPOSITORY__CHECKOUT:
				setCheckout((Boolean)newValue);
				return;
			case RmapPackage.REPOSITORY__ALLOW_DIRTY:
				setAllowDirty((Boolean)newValue);
				return;
			case RmapPackage.REPOSITORY__UPDATE:
				setUpdate((Boolean)newValue);
				return;
			case RmapPackage.REPOSITORY__DOCUMENTATION:
				setDocumentation((Documentation)newValue);
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
			case RmapPackage.REPOSITORY__ID:
				setId(ID_EDEFAULT);
				return;
			case RmapPackage.REPOSITORY__CONNECTION:
				setConnection((Format)null);
				return;
			case RmapPackage.REPOSITORY__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case RmapPackage.REPOSITORY__BRANCHES:
				getBranches().clear();
				return;
			case RmapPackage.REPOSITORY__CHECKOUT:
				setCheckout(CHECKOUT_EDEFAULT);
				return;
			case RmapPackage.REPOSITORY__ALLOW_DIRTY:
				setAllowDirty(ALLOW_DIRTY_EDEFAULT);
				return;
			case RmapPackage.REPOSITORY__UPDATE:
				setUpdate(UPDATE_EDEFAULT);
				return;
			case RmapPackage.REPOSITORY__DOCUMENTATION:
				setDocumentation((Documentation)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public EList<BranchPoint> getBranches() {
		if (branches == null) {
			branches = new EObjectContainmentEList<BranchPoint>(BranchPoint.class, this, RmapPackage.REPOSITORY__BRANCHES);
		}
		return branches;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Format getConnection() {
		return connection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public Documentation getDocumentation() {
		return documentation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public String getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public boolean isAllowDirty() {
		return allowDirty;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public boolean isCheckout() {
		return checkout;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public boolean isUpdate() {
		return update;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setAllowDirty(boolean newAllowDirty) {
		boolean oldAllowDirty = allowDirty;
		allowDirty = newAllowDirty;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__ALLOW_DIRTY, oldAllowDirty, allowDirty));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setCheckout(boolean newCheckout) {
		boolean oldCheckout = checkout;
		checkout = newCheckout;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__CHECKOUT, oldCheckout, checkout));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setConnection(Format newConnection) {
		if (newConnection != connection) {
			NotificationChain msgs = null;
			if (connection != null)
				msgs = ((InternalEObject)connection).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.REPOSITORY__CONNECTION, null, msgs);
			if (newConnection != null)
				msgs = ((InternalEObject)newConnection).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.REPOSITORY__CONNECTION, null, msgs);
			msgs = basicSetConnection(newConnection, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__CONNECTION, newConnection, newConnection));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setDocumentation(Documentation newDocumentation) {
		if (newDocumentation != documentation) {
			NotificationChain msgs = null;
			if (documentation != null)
				msgs = ((InternalEObject)documentation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - RmapPackage.REPOSITORY__DOCUMENTATION, null, msgs);
			if (newDocumentation != null)
				msgs = ((InternalEObject)newDocumentation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - RmapPackage.REPOSITORY__DOCUMENTATION, null, msgs);
			msgs = basicSetDocumentation(newDocumentation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__DOCUMENTATION, newDocumentation, newDocumentation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__ID, oldId, id));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setType(String newType) {
		String oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	public void setUpdate(boolean newUpdate) {
		boolean oldUpdate = update;
		update = newUpdate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RmapPackage.REPOSITORY__UPDATE, oldUpdate, update));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (id: ");
		result.append(id);
		result.append(", type: ");
		result.append(type);
		result.append(", checkout: ");
		result.append(checkout);
		result.append(", allowDirty: ");
		result.append(allowDirty);
		result.append(", update: ");
		result.append(update);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return RmapPackage.Literals.REPOSITORY;
	}

} // RepositoryImpl
