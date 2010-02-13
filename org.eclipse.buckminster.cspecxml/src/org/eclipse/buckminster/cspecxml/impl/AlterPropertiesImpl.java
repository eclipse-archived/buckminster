/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IAlterProperties;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;
import org.eclipse.buckminster.cspecxml.IRemoveProperty;

import org.eclipse.buckminster.model.common.PropertyConstant;

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
 * <em><b>Alter Properties</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterPropertiesImpl#getGroup
 * <em>Group</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterPropertiesImpl#getProperty
 * <em>Property</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterPropertiesImpl#getRemove
 * <em>Remove</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AlterPropertiesImpl extends EObjectImpl implements IAlterProperties {
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
	protected AlterPropertiesImpl() {
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
			case ICSpecXMLPackage.ALTER_PROPERTIES__GROUP:
				if (coreType)
					return getGroup();
				return ((FeatureMap.Internal) getGroup()).getWrapper();
			case ICSpecXMLPackage.ALTER_PROPERTIES__PROPERTY:
				return getProperty();
			case ICSpecXMLPackage.ALTER_PROPERTIES__REMOVE:
				return getRemove();
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
			case ICSpecXMLPackage.ALTER_PROPERTIES__GROUP:
				return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_PROPERTIES__PROPERTY:
				return ((InternalEList<?>) getProperty()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_PROPERTIES__REMOVE:
				return ((InternalEList<?>) getRemove()).basicRemove(otherEnd, msgs);
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
			case ICSpecXMLPackage.ALTER_PROPERTIES__GROUP:
				return group != null && !group.isEmpty();
			case ICSpecXMLPackage.ALTER_PROPERTIES__PROPERTY:
				return !getProperty().isEmpty();
			case ICSpecXMLPackage.ALTER_PROPERTIES__REMOVE:
				return !getRemove().isEmpty();
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
			case ICSpecXMLPackage.ALTER_PROPERTIES__GROUP:
				((FeatureMap.Internal) getGroup()).set(newValue);
				return;
			case ICSpecXMLPackage.ALTER_PROPERTIES__PROPERTY:
				getProperty().clear();
				getProperty().addAll((Collection<? extends PropertyConstant>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_PROPERTIES__REMOVE:
				getRemove().clear();
				getRemove().addAll((Collection<? extends IRemoveProperty>) newValue);
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
			case ICSpecXMLPackage.ALTER_PROPERTIES__GROUP:
				getGroup().clear();
				return;
			case ICSpecXMLPackage.ALTER_PROPERTIES__PROPERTY:
				getProperty().clear();
				return;
			case ICSpecXMLPackage.ALTER_PROPERTIES__REMOVE:
				getRemove().clear();
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
			group = new BasicFeatureMap(this, ICSpecXMLPackage.ALTER_PROPERTIES__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<PropertyConstant> getProperty() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_PROPERTIES__PROPERTY);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IRemoveProperty> getRemove() {
		return getGroup().list(ICSpecXMLPackage.Literals.ALTER_PROPERTIES__REMOVE);
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
		return ICSpecXMLPackage.Literals.ALTER_PROPERTIES;
	}

} // AlterPropertiesImpl
