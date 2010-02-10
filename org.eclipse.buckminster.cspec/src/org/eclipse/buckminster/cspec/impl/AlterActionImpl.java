/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.cspec.impl;

import java.util.Collection;

import org.eclipse.buckminster.cspec.AlterAction;
import org.eclipse.buckminster.cspec.AlterAttribute;
import org.eclipse.buckminster.cspec.AlterGroup;
import org.eclipse.buckminster.cspec.CSpecExtension;
import org.eclipse.buckminster.cspec.CspecPackage;
import org.eclipse.buckminster.cspec.Prerequisite;
import org.eclipse.buckminster.cspec.Remove;

import org.eclipse.buckminster.model.common.PropertyConstant;

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
 * <em><b>Alter Action</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getCspecext
 * <em>Cspecext</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getReplacePrerequisites
 * <em>Replace Prerequisites</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getRemovePrerequisites
 * <em>Remove Prerequisites</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getReplaceProperties
 * <em>Replace Properties</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getReplaceActorProperties
 * <em>Replace Actor Properties</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getRemoveProperties
 * <em>Remove Properties</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getRemoveActorProperties
 * <em>Remove Actor Properties</em>}</li>
 * <li>
 * {@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getRemoveProducts
 * <em>Remove Products</em>}</li>
 * <li>{@link org.eclipse.buckminster.cspec.impl.AlterActionImpl#getRemovePaths
 * <em>Remove Paths</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AlterActionImpl extends ActionImpl implements AlterAction {
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
	 * The cached value of the '{@link #getReplaceProperties()
	 * <em>Replace Properties</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplaceProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyConstant> replaceProperties;

	/**
	 * The cached value of the '{@link #getReplaceActorProperties()
	 * <em>Replace Actor Properties</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getReplaceActorProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<PropertyConstant> replaceActorProperties;

	/**
	 * The cached value of the '{@link #getRemoveProperties()
	 * <em>Remove Properties</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRemoveProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Remove> removeProperties;

	/**
	 * The cached value of the '{@link #getRemoveActorProperties()
	 * <em>Remove Actor Properties</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRemoveActorProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<Remove> removeActorProperties;

	/**
	 * The cached value of the '{@link #getRemoveProducts()
	 * <em>Remove Products</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRemoveProducts()
	 * @generated
	 * @ordered
	 */
	protected EList<Remove> removeProducts;

	/**
	 * The cached value of the '{@link #getRemovePaths() <em>Remove Paths</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRemovePaths()
	 * @generated
	 * @ordered
	 */
	protected EList<Remove> removePaths;

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
	public NotificationChain basicSetCspecext(CSpecExtension newCspecext, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject) newCspecext, CspecPackage.ALTER_ACTION__CSPECEXT, msgs);
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
				case CspecPackage.ALTER_ACTION__CSPECEXT:
					return CspecPackage.ALTER_ATTRIBUTE__CSPECEXT;
				default:
					return -1;
			}
		}
		if (baseClass == AlterGroup.class) {
			switch (derivedFeatureID) {
				case CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES:
					return CspecPackage.ALTER_GROUP__REPLACE_PREREQUISITES;
				case CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES:
					return CspecPackage.ALTER_GROUP__REMOVE_PREREQUISITES;
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
			case CspecPackage.ALTER_ACTION__CSPECEXT:
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
					return CspecPackage.ALTER_ACTION__CSPECEXT;
				default:
					return -1;
			}
		}
		if (baseClass == AlterGroup.class) {
			switch (baseFeatureID) {
				case CspecPackage.ALTER_GROUP__REPLACE_PREREQUISITES:
					return CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES;
				case CspecPackage.ALTER_GROUP__REMOVE_PREREQUISITES:
					return CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES;
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
			case CspecPackage.ALTER_ACTION__CSPECEXT:
				return getCspecext();
			case CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES:
				return getReplacePrerequisites();
			case CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES:
				return getRemovePrerequisites();
			case CspecPackage.ALTER_ACTION__REPLACE_PROPERTIES:
				return getReplaceProperties();
			case CspecPackage.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES:
				return getReplaceActorProperties();
			case CspecPackage.ALTER_ACTION__REMOVE_PROPERTIES:
				return getRemoveProperties();
			case CspecPackage.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES:
				return getRemoveActorProperties();
			case CspecPackage.ALTER_ACTION__REMOVE_PRODUCTS:
				return getRemoveProducts();
			case CspecPackage.ALTER_ACTION__REMOVE_PATHS:
				return getRemovePaths();
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
			case CspecPackage.ALTER_ACTION__CSPECEXT:
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
			case CspecPackage.ALTER_ACTION__CSPECEXT:
				return basicSetCspecext(null, msgs);
			case CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES:
				return ((InternalEList<?>) getReplacePrerequisites()).basicRemove(otherEnd, msgs);
			case CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES:
				return ((InternalEList<?>) getRemovePrerequisites()).basicRemove(otherEnd, msgs);
			case CspecPackage.ALTER_ACTION__REPLACE_PROPERTIES:
				return ((InternalEList<?>) getReplaceProperties()).basicRemove(otherEnd, msgs);
			case CspecPackage.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES:
				return ((InternalEList<?>) getReplaceActorProperties()).basicRemove(otherEnd, msgs);
			case CspecPackage.ALTER_ACTION__REMOVE_PROPERTIES:
				return ((InternalEList<?>) getRemoveProperties()).basicRemove(otherEnd, msgs);
			case CspecPackage.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES:
				return ((InternalEList<?>) getRemoveActorProperties()).basicRemove(otherEnd, msgs);
			case CspecPackage.ALTER_ACTION__REMOVE_PRODUCTS:
				return ((InternalEList<?>) getRemoveProducts()).basicRemove(otherEnd, msgs);
			case CspecPackage.ALTER_ACTION__REMOVE_PATHS:
				return ((InternalEList<?>) getRemovePaths()).basicRemove(otherEnd, msgs);
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
			case CspecPackage.ALTER_ACTION__CSPECEXT:
				return getCspecext() != null;
			case CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES:
				return replacePrerequisites != null && !replacePrerequisites.isEmpty();
			case CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES:
				return removePrerequisites != null && !removePrerequisites.isEmpty();
			case CspecPackage.ALTER_ACTION__REPLACE_PROPERTIES:
				return replaceProperties != null && !replaceProperties.isEmpty();
			case CspecPackage.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES:
				return replaceActorProperties != null && !replaceActorProperties.isEmpty();
			case CspecPackage.ALTER_ACTION__REMOVE_PROPERTIES:
				return removeProperties != null && !removeProperties.isEmpty();
			case CspecPackage.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES:
				return removeActorProperties != null && !removeActorProperties.isEmpty();
			case CspecPackage.ALTER_ACTION__REMOVE_PRODUCTS:
				return removeProducts != null && !removeProducts.isEmpty();
			case CspecPackage.ALTER_ACTION__REMOVE_PATHS:
				return removePaths != null && !removePaths.isEmpty();
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
			case CspecPackage.ALTER_ACTION__CSPECEXT:
				setCspecext((CSpecExtension) newValue);
				return;
			case CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES:
				getReplacePrerequisites().clear();
				getReplacePrerequisites().addAll((Collection<? extends Prerequisite>) newValue);
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES:
				getRemovePrerequisites().clear();
				getRemovePrerequisites().addAll((Collection<? extends Remove>) newValue);
				return;
			case CspecPackage.ALTER_ACTION__REPLACE_PROPERTIES:
				getReplaceProperties().clear();
				getReplaceProperties().addAll((Collection<? extends PropertyConstant>) newValue);
				return;
			case CspecPackage.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES:
				getReplaceActorProperties().clear();
				getReplaceActorProperties().addAll((Collection<? extends PropertyConstant>) newValue);
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_PROPERTIES:
				getRemoveProperties().clear();
				getRemoveProperties().addAll((Collection<? extends Remove>) newValue);
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES:
				getRemoveActorProperties().clear();
				getRemoveActorProperties().addAll((Collection<? extends Remove>) newValue);
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_PRODUCTS:
				getRemoveProducts().clear();
				getRemoveProducts().addAll((Collection<? extends Remove>) newValue);
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_PATHS:
				getRemovePaths().clear();
				getRemovePaths().addAll((Collection<? extends Remove>) newValue);
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
			case CspecPackage.ALTER_ACTION__CSPECEXT:
				setCspecext((CSpecExtension) null);
				return;
			case CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES:
				getReplacePrerequisites().clear();
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES:
				getRemovePrerequisites().clear();
				return;
			case CspecPackage.ALTER_ACTION__REPLACE_PROPERTIES:
				getReplaceProperties().clear();
				return;
			case CspecPackage.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES:
				getReplaceActorProperties().clear();
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_PROPERTIES:
				getRemoveProperties().clear();
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES:
				getRemoveActorProperties().clear();
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_PRODUCTS:
				getRemoveProducts().clear();
				return;
			case CspecPackage.ALTER_ACTION__REMOVE_PATHS:
				getRemovePaths().clear();
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
	public CSpecExtension getCspecext() {
		if (eContainerFeatureID() != CspecPackage.ALTER_ACTION__CSPECEXT)
			return null;
		return (CSpecExtension) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Remove> getRemoveActorProperties() {
		if (removeActorProperties == null) {
			removeActorProperties = new EObjectContainmentEList<Remove>(Remove.class, this, CspecPackage.ALTER_ACTION__REMOVE_ACTOR_PROPERTIES);
		}
		return removeActorProperties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Remove> getRemovePaths() {
		if (removePaths == null) {
			removePaths = new EObjectContainmentEList<Remove>(Remove.class, this, CspecPackage.ALTER_ACTION__REMOVE_PATHS);
		}
		return removePaths;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Remove> getRemovePrerequisites() {
		if (removePrerequisites == null) {
			removePrerequisites = new EObjectContainmentEList<Remove>(Remove.class, this, CspecPackage.ALTER_ACTION__REMOVE_PREREQUISITES);
		}
		return removePrerequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Remove> getRemoveProducts() {
		if (removeProducts == null) {
			removeProducts = new EObjectContainmentEList<Remove>(Remove.class, this, CspecPackage.ALTER_ACTION__REMOVE_PRODUCTS);
		}
		return removeProducts;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Remove> getRemoveProperties() {
		if (removeProperties == null) {
			removeProperties = new EObjectContainmentEList<Remove>(Remove.class, this, CspecPackage.ALTER_ACTION__REMOVE_PROPERTIES);
		}
		return removeProperties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<PropertyConstant> getReplaceActorProperties() {
		if (replaceActorProperties == null) {
			replaceActorProperties = new EObjectContainmentEList<PropertyConstant>(PropertyConstant.class, this,
					CspecPackage.ALTER_ACTION__REPLACE_ACTOR_PROPERTIES);
		}
		return replaceActorProperties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Prerequisite> getReplacePrerequisites() {
		if (replacePrerequisites == null) {
			replacePrerequisites = new EObjectContainmentEList<Prerequisite>(Prerequisite.class, this,
					CspecPackage.ALTER_ACTION__REPLACE_PREREQUISITES);
		}
		return replacePrerequisites;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<PropertyConstant> getReplaceProperties() {
		if (replaceProperties == null) {
			replaceProperties = new EObjectContainmentEList<PropertyConstant>(PropertyConstant.class, this,
					CspecPackage.ALTER_ACTION__REPLACE_PROPERTIES);
		}
		return replaceProperties;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCspecext(CSpecExtension newCspecext) {
		if (newCspecext != eInternalContainer() || (eContainerFeatureID() != CspecPackage.ALTER_ACTION__CSPECEXT && newCspecext != null)) {
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
			eNotify(new ENotificationImpl(this, Notification.SET, CspecPackage.ALTER_ACTION__CSPECEXT, newCspecext, newCspecext));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CspecPackage.Literals.ALTER_ACTION;
	}

} // AlterActionImpl
