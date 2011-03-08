/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.model.common.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.buckminster.model.common.CommonPackage;
import org.eclipse.buckminster.model.common.RxGroup;
import org.eclipse.buckminster.model.common.RxPart;
import org.eclipse.buckminster.model.common.RxPattern;
import org.eclipse.buckminster.model.common.util.DynamicFeatureEList;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.notify.impl.NotificationImpl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Rx Group</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.model.common.impl.RxGroupImpl#getRxPartsGroup
 * <em>Rx Parts Group</em>}</li>
 * <li>{@link org.eclipse.buckminster.model.common.impl.RxGroupImpl#getRxParts
 * <em>Rx Parts</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class RxGroupImpl extends RxPartImpl implements RxGroup {
	/**
	 * The cached value of the '{@link #getRxPartsGroup()
	 * <em>Rx Parts Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRxPartsGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap rxPartsGroup;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected RxGroupImpl() {
		super();
	}

	@Override
	public void addPattern(StringBuilder bld, List<RxPart> namedParts) {
		if (getName() != null) {
			bld.append('(');
			namedParts.add(this);
		} else if (isOptional())
			bld.append("(?:"); //$NON-NLS-1$

		addChildrenPatterns(bld, namedParts);

		if (getName() != null)
			bld.append(')');
		else if (isOptional())
			bld.append(")?"); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CommonPackage.RX_GROUP__RX_PARTS_GROUP:
				if (coreType)
					return getRxPartsGroup();
				return ((FeatureMap.Internal) getRxPartsGroup()).getWrapper();
			case CommonPackage.RX_GROUP__RX_PARTS:
				return getRxParts();
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
			case CommonPackage.RX_GROUP__RX_PARTS_GROUP:
				return ((InternalEList<?>) getRxPartsGroup()).basicRemove(otherEnd, msgs);
			case CommonPackage.RX_GROUP__RX_PARTS:
				return ((InternalEList<?>) getRxParts()).basicRemove(otherEnd, msgs);
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
			case CommonPackage.RX_GROUP__RX_PARTS_GROUP:
				return rxPartsGroup != null && !rxPartsGroup.isEmpty();
			case CommonPackage.RX_GROUP__RX_PARTS:
				return !getRxParts().isEmpty();
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
			case CommonPackage.RX_GROUP__RX_PARTS_GROUP:
				((FeatureMap.Internal) getRxPartsGroup()).set(newValue);
				return;
			case CommonPackage.RX_GROUP__RX_PARTS:
				getRxParts().clear();
				getRxParts().addAll((Collection<? extends RxPart>) newValue);
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
			case CommonPackage.RX_GROUP__RX_PARTS_GROUP:
				getRxPartsGroup().clear();
				return;
			case CommonPackage.RX_GROUP__RX_PARTS:
				getRxParts().clear();
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
	public EList<RxPart> getRxParts() {
		return getRxPartsGroup().list(CommonPackage.Literals.RX_GROUP__RX_PARTS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	@SuppressWarnings("serial")
	public FeatureMap getRxPartsGroup() {
		if (rxPartsGroup == null) {
			rxPartsGroup = new BasicFeatureMap(this, CommonPackage.RX_GROUP__RX_PARTS_GROUP) {
				@Override
				public <T> EList<T> list(EStructuralFeature feature) {
					return new DynamicFeatureEList<T>(feature, this) {
						@Override
						protected EStructuralFeature getEStructuralFeature(Object value) {
							if (value instanceof RxPattern)
								return CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__RX_PATTERN;
							if (value instanceof RxGroup)
								return CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__RX_GROUP;
							return getEStructuralFeature();
						}
					};
				}

				@Override
				protected NotificationImpl createNotification(int eventType, EStructuralFeature feature, Object oldObject, Object newObject,
						int index, boolean wasSet) {
					if (feature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__RX_PATTERN
							|| feature == CommonPackage.Literals.ABSTRACT_DOCUMENT_ROOT__RX_GROUP)
						feature = CommonPackage.Literals.RX_GROUP__RX_PARTS;
					return new FeatureMapUtil.FeatureENotificationImpl(owner, eventType, feature, oldObject, newObject, index, wasSet);
				}
			};
		}
		return rxPartsGroup;
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
		result.append(" (rxPartsGroup: ");
		result.append(rxPartsGroup);
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
		return CommonPackage.Literals.RX_GROUP;
	}

	void addChildrenPatterns(StringBuilder bld, List<RxPart> namedParts) {
		Iterator<FeatureMap.Entry> entries = getRxPartsGroup().iterator();
		while (entries.hasNext()) {
			FeatureMap.Entry entry = entries.next();
			RxPart part = (RxPart) entry.getValue();
			if (part.getName() == null) {
				String featureName = entry.getEStructuralFeature().getName();
				if (!(featureName == null || featureName.equals("rxPattern") || featureName.equals("rxGroup")))
					// The feature is something that is added by another package
					// such as the RmapPackage
					// URIMatcher when it adds os, ws, arch, version, etc.
					part.setName("tagged." + featureName);
			}
			part.addPattern(bld, namedParts);
		}
	}

} // RxGroupImpl
