/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspec.AlterAttribute;
import org.eclipse.buckminster.cspec.AlterGroup;
import org.eclipse.buckminster.cspec.CSpecExtension;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Prerequisite;
import org.eclipse.buckminster.cspec.Remove;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Alter Group</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AlterGroupImpl#getCspecext <em>
 * Cspecext</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterGroupImpl#getReplacePrerequisites
 * <em>Replace Prerequisites</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterGroupImpl#getRemovePrerequisites
 * <em>Remove Prerequisites</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AlterGroupImpl extends GroupImpl implements AlterGroup {
	/**
	 * The cached value of the '{@link #getReplacePrerequisites()
	 * <em>Replace Prerequisites</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplacePrerequisites()
	 * @generated
	 * @ordered
	 */
	protected EList<Prerequisite> replacePrerequisites;

	/**
	 * The cached value of the '{@link #getRemovePrerequisites()
	 * <em>Remove Prerequisites</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRemovePrerequisites()
	 * @generated
	 * @ordered
	 */
	protected EList<Remove> removePrerequisites;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AlterGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetCspecext(CSpecExtension newCspecext, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newCspecext, CspecPackage.ALTER_GROUP__CSPECEXT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == AlterAttribute.class) {
			switch (derivedFeatureID) {
				case CspecPackage.ALTER_GROUP__CSPECEXT:
					return CspecPackage.ALTER_ATTRIBUTE__CSPECEXT;
				default:
					return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case CspecPackage.ALTER_GROUP__CSPECEXT:
				return eInternalContainer().eInverseRemove(this, CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS, CSpecExtension.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == AlterAttribute.class) {
			switch (baseFeatureID) {
				case CspecPackage.ALTER_ATTRIBUTE__CSPECEXT:
					return CspecPackage.ALTER_GROUP__CSPECEXT;
				default:
					return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CspecPackage.ALTER_GROUP__CSPECEXT:
				return getCspecext();
			case CspecPackage.ALTER_GROUP__REPLACE_PREREQUISITES:
				return getReplacePrerequisites();
			case CspecPackage.ALTER_GROUP__REMOVE_PREREQUISITES:
				return getRemovePrerequisites();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CspecPackage.ALTER_GROUP__CSPECEXT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCspecext((CSpecExtension) otherEnd, msgs);
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
			case CspecPackage.ALTER_GROUP__CSPECEXT:
				return basicSetCspecext(null, msgs);
			case CspecPackage.ALTER_GROUP__REPLACE_PREREQUISITES:
				return ((InternalEList<?>) getReplacePrerequisites()).basicRemove(otherEnd, msgs);
			case CspecPackage.ALTER_GROUP__REMOVE_PREREQUISITES:
				return ((InternalEList<?>) getRemovePrerequisites()).basicRemove(otherEnd, msgs);
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
			case CspecPackage.ALTER_GROUP__CSPECEXT:
				return getCspecext() != null;
			case CspecPackage.ALTER_GROUP__REPLACE_PREREQUISITES:
				return replacePrerequisites != null && !replacePrerequisites.isEmpty();
			case CspecPackage.ALTER_GROUP__REMOVE_PREREQUISITES:
				return removePrerequisites != null && !removePrerequisites.isEmpty();
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
			case CspecPackage.ALTER_GROUP__CSPECEXT:
				setCspecext((CSpecExtension) newValue);
				return;
			case CspecPackage.ALTER_GROUP__REPLACE_PREREQUISITES:
				getReplacePrerequisites().clear();
				getReplacePrerequisites().addAll((Collection<? extends Prerequisite>) newValue);
				return;
			case CspecPackage.ALTER_GROUP__REMOVE_PREREQUISITES:
				getRemovePrerequisites().clear();
				getRemovePrerequisites().addAll((Collection<? extends Remove>) newValue);
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
			case CspecPackage.ALTER_GROUP__CSPECEXT:
				setCspecext((CSpecExtension) null);
				return;
			case CspecPackage.ALTER_GROUP__REPLACE_PREREQUISITES:
				getReplacePrerequisites().clear();
				return;
			case CspecPackage.ALTER_GROUP__REMOVE_PREREQUISITES:
				getRemovePrerequisites().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public CSpecExtension getCspecext() {
		if (eContainerFeatureID() != CspecPackage.ALTER_GROUP__CSPECEXT)
			return null;
		return (CSpecExtension) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Remove> getRemovePrerequisites() {
		if (removePrerequisites == null) {
			removePrerequisites = new EObjectContainmentEList<Remove>(Remove.class, this, CspecPackage.ALTER_GROUP__REMOVE_PREREQUISITES);
		}
		return removePrerequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Prerequisite> getReplacePrerequisites() {
		if (replacePrerequisites == null) {
			replacePrerequisites = new EObjectContainmentEList<Prerequisite>(Prerequisite.class, this,
					CspecPackage.ALTER_GROUP__REPLACE_PREREQUISITES);
		}
		return replacePrerequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCspecext(CSpecExtension newCspecext) {
		if (newCspecext != eInternalContainer() || (eContainerFeatureID() != CspecPackage.ALTER_GROUP__CSPECEXT && newCspecext != null)) {
			if (EcoreUtil.isAncestor(this, newCspecext))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCspecext != null)
				msgs = ((InternalEObject) newCspecext).eInverseAdd(this, CspecPackage.CSPEC_EXTENSION__ATTRIBUTE_ALTERATIONS, CSpecExtension.class,
						msgs);
			msgs = basicSetCspecext(newCspecext, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ALTER_GROUP__CSPECEXT, newCspecext, newCspecext));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CspecPackage.Literals.ALTER_GROUP;
	}

} // AlterGroupImpl
