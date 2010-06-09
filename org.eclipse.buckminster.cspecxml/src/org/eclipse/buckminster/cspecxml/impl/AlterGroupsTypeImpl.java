/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IAlterGroup;
import org.eclipse.buckminster.cspecxml.IAlterGroupsType;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IRemove;
import org.eclipse.buckminster.cspecxml.IRename;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Alter Groups Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl#getGroup
 * <em>Group</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl#getPublic
 * <em>Public</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl#getPrivate
 * <em>Private</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl#getRemove
 * <em>Remove</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterGroupsTypeImpl#getRename
 * <em>Rename</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AlterGroupsTypeImpl extends EObjectImpl implements IAlterGroupsType {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AlterGroupsTypeImpl() {
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
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__GROUP:
				if (coreType)
					return getGroup();
				return ((FeatureMap.Internal) getGroup()).getWrapper();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PUBLIC:
				return getPublic();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PRIVATE:
				return getPrivate();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__REMOVE:
				return getRemove();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__RENAME:
				return getRename();
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
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__GROUP:
				return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PUBLIC:
				return ((InternalEList<?>) getPublic()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PRIVATE:
				return ((InternalEList<?>) getPrivate()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__REMOVE:
				return ((InternalEList<?>) getRemove()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__RENAME:
				return ((InternalEList<?>) getRename()).basicRemove(otherEnd, msgs);
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
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PUBLIC:
				return !getPublic().isEmpty();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PRIVATE:
				return !getPrivate().isEmpty();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__REMOVE:
				return !getRemove().isEmpty();
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__RENAME:
				return !getRename().isEmpty();
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
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__GROUP:
				((FeatureMap.Internal) getGroup()).set(newValue);
				return;
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PUBLIC:
				getPublic().clear();
				getPublic().addAll((Collection<? extends IAlterGroup>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PRIVATE:
				getPrivate().clear();
				getPrivate().addAll((Collection<? extends IAlterGroup>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__REMOVE:
				getRemove().clear();
				getRemove().addAll((Collection<? extends IRemove>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__RENAME:
				getRename().clear();
				getRename().addAll((Collection<? extends IRename>) newValue);
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
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__GROUP:
				getGroup().clear();
				return;
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PUBLIC:
				getPublic().clear();
				return;
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__PRIVATE:
				getPrivate().clear();
				return;
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__REMOVE:
				getRemove().clear();
				return;
			case ICSpecXMLPackage.ALTER_GROUPS_TYPE__RENAME:
				getRename().clear();
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
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, ICSpecXMLPackage.ALTER_GROUPS_TYPE__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IAlterGroup> getPrivate() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_GROUPS_TYPE__PRIVATE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IAlterGroup> getPublic() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_GROUPS_TYPE__PUBLIC);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IRemove> getRemove() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_GROUPS_TYPE__REMOVE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IRename> getRename() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_GROUPS_TYPE__RENAME);
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
		result.append(" (group: ");
		result.append(group);
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
		return ICSpecXMLPackage.Literals.ALTER_GROUPS_TYPE;
	}

} // AlterGroupsTypeImpl
