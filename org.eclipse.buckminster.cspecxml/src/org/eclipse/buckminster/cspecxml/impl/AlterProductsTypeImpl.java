/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IAlterGroup;
import org.eclipse.buckminster.cspecxml.IAlterProductsType;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IRemove;
import org.eclipse.buckminster.cspecxml.IRemovePath;

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
 * <em><b>Alter Products Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl#getGroup
 * <em>Group</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl#getPublic
 * <em>Public</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl#getPrivate
 * <em>Private</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl#getRemoveProduct
 * <em>Remove Product</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterProductsTypeImpl#getRemovePath
 * <em>Remove Path</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AlterProductsTypeImpl extends EObjectImpl implements IAlterProductsType {
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
	protected AlterProductsTypeImpl() {
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
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__GROUP:
				if (coreType)
					return getGroup();
				return ((FeatureMap.Internal) getGroup()).getWrapper();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PUBLIC:
				return getPublic();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PRIVATE:
				return getPrivate();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT:
				return getRemoveProduct();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PATH:
				return getRemovePath();
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
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__GROUP:
				return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PUBLIC:
				return ((InternalEList<?>) getPublic()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PRIVATE:
				return ((InternalEList<?>) getPrivate()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT:
				return ((InternalEList<?>) getRemoveProduct()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PATH:
				return ((InternalEList<?>) getRemovePath()).basicRemove(otherEnd, msgs);
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
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__GROUP:
				return group != null && !group.isEmpty();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PUBLIC:
				return !getPublic().isEmpty();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PRIVATE:
				return !getPrivate().isEmpty();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT:
				return !getRemoveProduct().isEmpty();
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PATH:
				return !getRemovePath().isEmpty();
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
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__GROUP:
				((FeatureMap.Internal) getGroup()).set(newValue);
				return;
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PUBLIC:
				getPublic().clear();
				getPublic().addAll((Collection<? extends IAlterGroup>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PRIVATE:
				getPrivate().clear();
				getPrivate().addAll((Collection<? extends IAlterGroup>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT:
				getRemoveProduct().clear();
				getRemoveProduct().addAll((Collection<? extends IRemove>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PATH:
				getRemovePath().clear();
				getRemovePath().addAll((Collection<? extends IRemovePath>) newValue);
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
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__GROUP:
				getGroup().clear();
				return;
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PUBLIC:
				getPublic().clear();
				return;
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__PRIVATE:
				getPrivate().clear();
				return;
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT:
				getRemoveProduct().clear();
				return;
			case ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__REMOVE_PATH:
				getRemovePath().clear();
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
			group = new BasicFeatureMap(this, ICSpecXMLPackage.ALTER_PRODUCTS_TYPE__GROUP);
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
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_PRODUCTS_TYPE__PRIVATE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IAlterGroup> getPublic() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_PRODUCTS_TYPE__PUBLIC);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IRemovePath> getRemovePath() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_PRODUCTS_TYPE__REMOVE_PATH);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IRemove> getRemoveProduct() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_PRODUCTS_TYPE__REMOVE_PRODUCT);
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
		return ICSpecXMLPackage.Literals.ALTER_PRODUCTS_TYPE;
	}

} // AlterProductsTypeImpl
