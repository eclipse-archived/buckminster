/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspec.AlterAttribute;
import org.eclipse.buckminster.cspec.CSpecExtension;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Generator;
import org.eclipse.buckminster.cspec.Remove;
import org.eclipse.buckminster.cspec.Rename;

import org.eclipse.buckminster.model.common.ComponentRequest;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>CSpec Extension</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl#getAttributeAlterations
 * <em>Attribute Alterations </em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl#getRenameAttributes
 * <em>Rename Attributes</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl#getRemoveDependencies
 * <em>Remove Dependencies</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl#getRemoveGenerators
 * <em>Remove Generators</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl#getReplaceGenerators
 * <em>Replace Generators</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.CSpecExtensionImpl#getReplaceDependencies
 * <em>Replace Dependencies </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class CSpecExtensionImpl extends CSpecImpl implements CSpecExtension {
	/**
	 * The cached value of the '{@link #getAttributeAlterations()
	 * <em>Attribute Alterations</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAttributeAlterations()
	 * @generated
	 * @ordered
	 */
	protected EList<AlterAttribute> attributeAlterations;

	/**
	 * The cached value of the '{@link #getRenameAttributes()
	 * <em>Rename Attributes</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRenameAttributes()
	 * @generated
	 * @ordered
	 */
	protected EList<Rename> renameAttributes;

	/**
	 * The cached value of the '{@link #getRemoveDependencies()
	 * <em>Remove Dependencies</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRemoveDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<Remove> removeDependencies;

	/**
	 * The cached value of the '{@link #getRemoveGenerators()
	 * <em>Remove Generators</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRemoveGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<Remove> removeGenerators;

	/**
	 * The cached value of the '{@link #getReplaceGenerators()
	 * <em>Replace Generators</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplaceGenerators()
	 * @generated
	 * @ordered
	 */
	protected EList<Generator> replaceGenerators;

	/**
	 * The cached value of the '{@link #getReplaceDependencies()
	 * <em>Replace Dependencies</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplaceDependencies()
	 * @generated
	 * @ordered
	 */
	protected EList<ComponentRequest> replaceDependencies;

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
			case CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS:
				return getAttributeAlterations();
			case CspecPackage.CSPEC_EXTENSION__RENAME_ATTRIBUTES:
				return getRenameAttributes();
			case CspecPackage.CSPEC_EXTENSION__REMOVE_DEPENDENCIES:
				return getRemoveDependencies();
			case CspecPackage.CSPEC_EXTENSION__REMOVE_GENERATORS:
				return getRemoveGenerators();
			case CspecPackage.CSPEC_EXTENSION__REPLACE_GENERATORS:
				return getReplaceGenerators();
			case CspecPackage.CSPEC_EXTENSION__REPLACE_DEPENDENCIES:
				return getReplaceDependencies();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS:
				return ((InternalEList<InternalEObject>) (InternalEList<?>) getAttributeAlterations()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS:
				return ((InternalEList<?>) getAttributeAlterations()).basicRemove(otherEnd, msgs);
			case CspecPackage.CSPEC_EXTENSION__RENAME_ATTRIBUTES:
				return ((InternalEList<?>) getRenameAttributes()).basicRemove(otherEnd, msgs);
			case CspecPackage.CSPEC_EXTENSION__REMOVE_DEPENDENCIES:
				return ((InternalEList<?>) getRemoveDependencies()).basicRemove(otherEnd, msgs);
			case CspecPackage.CSPEC_EXTENSION__REMOVE_GENERATORS:
				return ((InternalEList<?>) getRemoveGenerators()).basicRemove(otherEnd, msgs);
			case CspecPackage.CSPEC_EXTENSION__REPLACE_GENERATORS:
				return ((InternalEList<?>) getReplaceGenerators()).basicRemove(otherEnd, msgs);
			case CspecPackage.CSPEC_EXTENSION__REPLACE_DEPENDENCIES:
				return ((InternalEList<?>) getReplaceDependencies()).basicRemove(otherEnd, msgs);
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
			case CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS:
				return attributeAlterations != null && !attributeAlterations.isEmpty();
			case CspecPackage.CSPEC_EXTENSION__RENAME_ATTRIBUTES:
				return renameAttributes != null && !renameAttributes.isEmpty();
			case CspecPackage.CSPEC_EXTENSION__REMOVE_DEPENDENCIES:
				return removeDependencies != null && !removeDependencies.isEmpty();
			case CspecPackage.CSPEC_EXTENSION__REMOVE_GENERATORS:
				return removeGenerators != null && !removeGenerators.isEmpty();
			case CspecPackage.CSPEC_EXTENSION__REPLACE_GENERATORS:
				return replaceGenerators != null && !replaceGenerators.isEmpty();
			case CspecPackage.CSPEC_EXTENSION__REPLACE_DEPENDENCIES:
				return replaceDependencies != null && !replaceDependencies.isEmpty();
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
			case CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS:
				getAttributeAlterations().clear();
				getAttributeAlterations().addAll((Collection<? extends AlterAttribute>) newValue);
				return;
			case CspecPackage.CSPEC_EXTENSION__RENAME_ATTRIBUTES:
				getRenameAttributes().clear();
				getRenameAttributes().addAll((Collection<? extends Rename>) newValue);
				return;
			case CspecPackage.CSPEC_EXTENSION__REMOVE_DEPENDENCIES:
				getRemoveDependencies().clear();
				getRemoveDependencies().addAll((Collection<? extends Remove>) newValue);
				return;
			case CspecPackage.CSPEC_EXTENSION__REMOVE_GENERATORS:
				getRemoveGenerators().clear();
				getRemoveGenerators().addAll((Collection<? extends Remove>) newValue);
				return;
			case CspecPackage.CSPEC_EXTENSION__REPLACE_GENERATORS:
				getReplaceGenerators().clear();
				getReplaceGenerators().addAll((Collection<? extends Generator>) newValue);
				return;
			case CspecPackage.CSPEC_EXTENSION__REPLACE_DEPENDENCIES:
				getReplaceDependencies().clear();
				getReplaceDependencies().addAll((Collection<? extends ComponentRequest>) newValue);
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
			case CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS:
				getAttributeAlterations().clear();
				return;
			case CspecPackage.CSPEC_EXTENSION__RENAME_ATTRIBUTES:
				getRenameAttributes().clear();
				return;
			case CspecPackage.CSPEC_EXTENSION__REMOVE_DEPENDENCIES:
				getRemoveDependencies().clear();
				return;
			case CspecPackage.CSPEC_EXTENSION__REMOVE_GENERATORS:
				getRemoveGenerators().clear();
				return;
			case CspecPackage.CSPEC_EXTENSION__REPLACE_GENERATORS:
				getReplaceGenerators().clear();
				return;
			case CspecPackage.CSPEC_EXTENSION__REPLACE_DEPENDENCIES:
				getReplaceDependencies().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<AlterAttribute> getAttributeAlterations() {
		if (attributeAlterations == null) {
			attributeAlterations = new EObjectContainmentWithInverseEList<AlterAttribute>(AlterAttribute.class, this,
					CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS, CspecPackage.ALTER_ATTRIBUTE__CSPECEXT);
		}
		return attributeAlterations;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Remove> getRemoveDependencies() {
		if (removeDependencies == null) {
			removeDependencies = new EObjectContainmentEList<Remove>(Remove.class, this, CspecPackage.CSPEC_EXTENSION__REMOVE_DEPENDENCIES);
		}
		return removeDependencies;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Remove> getRemoveGenerators() {
		if (removeGenerators == null) {
			removeGenerators = new EObjectContainmentEList<Remove>(Remove.class, this, CspecPackage.CSPEC_EXTENSION__REMOVE_GENERATORS);
		}
		return removeGenerators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Rename> getRenameAttributes() {
		if (renameAttributes == null) {
			renameAttributes = new EObjectContainmentEList<Rename>(Rename.class, this, CspecPackage.CSPEC_EXTENSION__RENAME_ATTRIBUTES);
		}
		return renameAttributes;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<ComponentRequest> getReplaceDependencies() {
		if (replaceDependencies == null) {
			replaceDependencies = new EObjectContainmentEList<ComponentRequest>(ComponentRequest.class, this,
					CspecPackage.CSPEC_EXTENSION__REPLACE_DEPENDENCIES);
		}
		return replaceDependencies;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Generator> getReplaceGenerators() {
		if (replaceGenerators == null) {
			replaceGenerators = new EObjectContainmentEList<Generator>(Generator.class, this, CspecPackage.CSPEC_EXTENSION__REPLACE_GENERATORS);
		}
		return replaceGenerators;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CspecPackage.Literals.CSPEC_EXTENSION;
	}

} // CSpecExtensionImpl
