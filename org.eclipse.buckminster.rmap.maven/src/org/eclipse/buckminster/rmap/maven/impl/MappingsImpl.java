/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.impl;

import java.util.Collection;

import org.eclipse.buckminster.rmap.Transform;

import org.eclipse.buckminster.rmap.maven.MapEntry;
import org.eclipse.buckminster.rmap.maven.Mappings;
import org.eclipse.buckminster.rmap.maven.MavenPackage;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Mappings</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.rmap.maven.impl.MappingsImpl#getEntries
 * <em>Entries</em>}</li>
 * <li>{@link org.eclipse.buckminster.rmap.maven.impl.MappingsImpl#getRules <em>
 * Rules</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MappingsImpl extends EObjectImpl implements Mappings {
	/**
	 * The cached value of the '{@link #getEntries() <em>Entries</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEntries()
	 * @generated
	 * @ordered
	 */
	protected EList<MapEntry> entries;

	/**
	 * The cached value of the '{@link #getRules() <em>Rules</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRules()
	 * @generated
	 * @ordered
	 */
	protected EList<Transform> rules;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MappingsImpl() {
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
			case MavenPackage.MAPPINGS__ENTRIES:
				return getEntries();
			case MavenPackage.MAPPINGS__RULES:
				return getRules();
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
			case MavenPackage.MAPPINGS__ENTRIES:
				return ((InternalEList<?>) getEntries()).basicRemove(otherEnd, msgs);
			case MavenPackage.MAPPINGS__RULES:
				return ((InternalEList<?>) getRules()).basicRemove(otherEnd, msgs);
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
			case MavenPackage.MAPPINGS__ENTRIES:
				return entries != null && !entries.isEmpty();
			case MavenPackage.MAPPINGS__RULES:
				return rules != null && !rules.isEmpty();
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
			case MavenPackage.MAPPINGS__ENTRIES:
				getEntries().clear();
				getEntries().addAll((Collection<? extends MapEntry>) newValue);
				return;
			case MavenPackage.MAPPINGS__RULES:
				getRules().clear();
				getRules().addAll((Collection<? extends Transform>) newValue);
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
			case MavenPackage.MAPPINGS__ENTRIES:
				getEntries().clear();
				return;
			case MavenPackage.MAPPINGS__RULES:
				getRules().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<MapEntry> getEntries() {
		if (entries == null) {
			entries = new EObjectContainmentEList<MapEntry>(MapEntry.class, this, MavenPackage.MAPPINGS__ENTRIES);
		}
		return entries;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Transform> getRules() {
		if (rules == null) {
			rules = new EObjectContainmentEList<Transform>(Transform.class, this, MavenPackage.MAPPINGS__RULES);
		}
		return rules;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MavenPackage.Literals.MAPPINGS;
	}

} // MappingsImpl
