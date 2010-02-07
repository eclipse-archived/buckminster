/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IAlterAction;
import org.eclipse.buckminster.cspecxml.IAlterPrerequisites;
import org.eclipse.buckminster.cspecxml.IAlterProductsType;
import org.eclipse.buckminster.cspecxml.IAlterProperties;
import org.eclipse.buckminster.cspecxml.ICSpecXMLPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Alter Action</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspecxml.impl.AlterActionImpl#getGroup1
 * <em>Group1</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterActionImpl#getAlterPrerequisites
 * <em>Alter Prerequisites</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterActionImpl#getAlterActorProperties
 * <em>Alter Actor Properties </em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterActionImpl#getAlterProperties
 * <em>Alter Properties</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.AlterActionImpl#getAlterProducts
 * <em>Alter Products</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AlterActionImpl extends ActionImpl implements IAlterAction {
	/**
	 * The cached value of the '{@link #getGroup1() <em>Group1</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGroup1()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group1;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AlterActionImpl() {
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
			case ICSpecXMLPackage.ALTER_ACTION__GROUP1:
				if (coreType)
					return getGroup1();
				return ((FeatureMap.Internal) getGroup1()).getWrapper();
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PREREQUISITES:
				return getAlterPrerequisites();
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_ACTOR_PROPERTIES:
				return getAlterActorProperties();
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PROPERTIES:
				return getAlterProperties();
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PRODUCTS:
				return getAlterProducts();
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
			case ICSpecXMLPackage.ALTER_ACTION__GROUP1:
				return ((InternalEList<?>) getGroup1()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PREREQUISITES:
				return ((InternalEList<?>) getAlterPrerequisites()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_ACTOR_PROPERTIES:
				return ((InternalEList<?>) getAlterActorProperties()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PROPERTIES:
				return ((InternalEList<?>) getAlterProperties()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PRODUCTS:
				return ((InternalEList<?>) getAlterProducts()).basicRemove(otherEnd, msgs);
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
			case ICSpecXMLPackage.ALTER_ACTION__GROUP1:
				return group1 != null && !group1.isEmpty();
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PREREQUISITES:
				return !getAlterPrerequisites().isEmpty();
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_ACTOR_PROPERTIES:
				return !getAlterActorProperties().isEmpty();
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PROPERTIES:
				return !getAlterProperties().isEmpty();
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PRODUCTS:
				return !getAlterProducts().isEmpty();
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
			case ICSpecXMLPackage.ALTER_ACTION__GROUP1:
				((FeatureMap.Internal) getGroup1()).set(newValue);
				return;
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PREREQUISITES:
				getAlterPrerequisites().clear();
				getAlterPrerequisites().addAll((Collection<? extends IAlterPrerequisites>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_ACTOR_PROPERTIES:
				getAlterActorProperties().clear();
				getAlterActorProperties().addAll((Collection<? extends IAlterProperties>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PROPERTIES:
				getAlterProperties().clear();
				getAlterProperties().addAll((Collection<? extends IAlterProperties>) newValue);
				return;
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PRODUCTS:
				getAlterProducts().clear();
				getAlterProducts().addAll((Collection<? extends IAlterProductsType>) newValue);
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
			case ICSpecXMLPackage.ALTER_ACTION__GROUP1:
				getGroup1().clear();
				return;
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PREREQUISITES:
				getAlterPrerequisites().clear();
				return;
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_ACTOR_PROPERTIES:
				getAlterActorProperties().clear();
				return;
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PROPERTIES:
				getAlterProperties().clear();
				return;
			case ICSpecXMLPackage.ALTER_ACTION__ALTER_PRODUCTS:
				getAlterProducts().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IAlterProperties> getAlterActorProperties() {
		return getGroup1().list(ICSpecXMLPackage.Literals.ALTER_ACTION__ALTER_ACTOR_PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IAlterPrerequisites> getAlterPrerequisites() {
		return getGroup1().list(ICSpecXMLPackage.Literals.ALTER_ACTION__ALTER_PREREQUISITES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IAlterProductsType> getAlterProducts() {
		return getGroup1().list(ICSpecXMLPackage.Literals.ALTER_ACTION__ALTER_PRODUCTS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<IAlterProperties> getAlterProperties() {
		return getGroup1().list(ICSpecXMLPackage.Literals.ALTER_ACTION__ALTER_PROPERTIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getGroup1() {
		if (group1 == null) {
			group1 = new BasicFeatureMap(this, ICSpecXMLPackage.ALTER_ACTION__GROUP1);
		}
		return group1;
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
		result.append(" (group1: ");
		result.append(group1);
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
		return ICSpecXMLPackage.Literals.ALTER_ACTION;
	}

} // AlterActionImpl
