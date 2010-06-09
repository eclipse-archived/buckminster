/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspecxml.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspecxml.IAlterActionsType;
import org.eclipse.buckminster.cspecxml.IAlterArtifactsType;
import org.eclipse.buckminster.cspecxml.IAlterDependenciesType;
import org.eclipse.buckminster.cspecxml.IAlterGroupsType;
import org.eclipse.buckminster.cspecxml.ICSpecExtension;
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
 * <em><b>CSpec Extension</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl#getGroup1
 * <em>Group1</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl#getAlterActions
 * <em>Alter Actions</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl#getAlterArtifacts
 * <em>Alter Artifacts</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl#getAlterDependencies
 * <em>Alter Dependencies</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspecxml.impl.CSpecExtensionImpl#getAlterGroups
 * <em>Alter Groups</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CSpecExtensionImpl extends ComponentSpecBaseImpl implements ICSpecExtension {
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
	protected CSpecExtensionImpl() {
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
			case ICSpecXMLPackage.CSPEC_EXTENSION__GROUP1:
				if (coreType)
					return getGroup1();
				return ((FeatureMap.Internal) getGroup1()).getWrapper();
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ACTIONS:
				return getAlterActions();
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ARTIFACTS:
				return getAlterArtifacts();
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_DEPENDENCIES:
				return getAlterDependencies();
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_GROUPS:
				return getAlterGroups();
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
			case ICSpecXMLPackage.CSPEC_EXTENSION__GROUP1:
				return ((InternalEList<?>) getGroup1()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ACTIONS:
				return ((InternalEList<?>) getAlterActions()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ARTIFACTS:
				return ((InternalEList<?>) getAlterArtifacts()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_DEPENDENCIES:
				return ((InternalEList<?>) getAlterDependencies()).basicRemove(otherEnd, msgs);
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_GROUPS:
				return ((InternalEList<?>) getAlterGroups()).basicRemove(otherEnd, msgs);
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
			case ICSpecXMLPackage.CSPEC_EXTENSION__GROUP1:
				return group1 != null && !group1.isEmpty();
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ACTIONS:
				return !getAlterActions().isEmpty();
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ARTIFACTS:
				return !getAlterArtifacts().isEmpty();
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_DEPENDENCIES:
				return !getAlterDependencies().isEmpty();
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_GROUPS:
				return !getAlterGroups().isEmpty();
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
			case ICSpecXMLPackage.CSPEC_EXTENSION__GROUP1:
				((FeatureMap.Internal) getGroup1()).set(newValue);
				return;
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ACTIONS:
				getAlterActions().clear();
				getAlterActions().addAll((Collection<? extends IAlterActionsType>) newValue);
				return;
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ARTIFACTS:
				getAlterArtifacts().clear();
				getAlterArtifacts().addAll((Collection<? extends IAlterArtifactsType>) newValue);
				return;
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_DEPENDENCIES:
				getAlterDependencies().clear();
				getAlterDependencies().addAll((Collection<? extends IAlterDependenciesType>) newValue);
				return;
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_GROUPS:
				getAlterGroups().clear();
				getAlterGroups().addAll((Collection<? extends IAlterGroupsType>) newValue);
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
			case ICSpecXMLPackage.CSPEC_EXTENSION__GROUP1:
				getGroup1().clear();
				return;
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ACTIONS:
				getAlterActions().clear();
				return;
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_ARTIFACTS:
				getAlterArtifacts().clear();
				return;
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_DEPENDENCIES:
				getAlterDependencies().clear();
				return;
			case ICSpecXMLPackage.CSPEC_EXTENSION__ALTER_GROUPS:
				getAlterGroups().clear();
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
	public EList<IAlterActionsType> getAlterActions() {
		return getGroup1().list(ICSpecXMLPackage.Literals.CSPEC_EXTENSION__ALTER_ACTIONS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IAlterArtifactsType> getAlterArtifacts() {
		return getGroup1().list(ICSpecXMLPackage.Literals.CSPEC_EXTENSION__ALTER_ARTIFACTS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IAlterDependenciesType> getAlterDependencies() {
		return getGroup1().list(ICSpecXMLPackage.Literals.CSPEC_EXTENSION__ALTER_DEPENDENCIES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<IAlterGroupsType> getAlterGroups() {
		return getGroup1().list(ICSpecXMLPackage.Literals.CSPEC_EXTENSION__ALTER_GROUPS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getGroup1() {
		if (group1 == null) {
			group1 = new BasicFeatureMap(this, ICSpecXMLPackage.CSPEC_EXTENSION__GROUP1);
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
		return ICSpecXMLPackage.Literals.CSPEC_EXTENSION;
	}

} // CSpecExtensionImpl
