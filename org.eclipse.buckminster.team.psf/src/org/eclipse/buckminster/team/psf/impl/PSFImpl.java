/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.team.psf.impl;

import java.util.Collection;

import org.eclipse.buckminster.team.psf.PSF;
import org.eclipse.buckminster.team.psf.PsfPackage;
import org.eclipse.buckminster.team.psf.RepositoryProvider;
import org.eclipse.buckminster.team.psf.WorkingSet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>PSF</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.PSFImpl#getVersion <em>
 * Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.PSFImpl#getProviders <em>
 * Providers</em>}</li>
 * <li>{@link org.eclipse.buckminster.team.psf.impl.PSFImpl#getWorkingSets <em>
 * Working Sets</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class PSFImpl extends EObjectImpl implements PSF {
	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getProviders() <em>Providers</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getProviders()
	 * @generated
	 * @ordered
	 */
	protected EList<RepositoryProvider> providers;

	/**
	 * The cached value of the '{@link #getWorkingSets() <em>Working Sets</em>}'
	 * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWorkingSets()
	 * @generated
	 * @ordered
	 */
	protected EList<WorkingSet> workingSets;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PSFImpl() {
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
			case PsfPackage.PSF__VERSION:
				return getVersion();
			case PsfPackage.PSF__PROVIDERS:
				return getProviders();
			case PsfPackage.PSF__WORKING_SETS:
				return getWorkingSets();
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
			case PsfPackage.PSF__PROVIDERS:
				return ((InternalEList<?>) getProviders()).basicRemove(otherEnd, msgs);
			case PsfPackage.PSF__WORKING_SETS:
				return ((InternalEList<?>) getWorkingSets()).basicRemove(otherEnd, msgs);
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
			case PsfPackage.PSF__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case PsfPackage.PSF__PROVIDERS:
				return providers != null && !providers.isEmpty();
			case PsfPackage.PSF__WORKING_SETS:
				return workingSets != null && !workingSets.isEmpty();
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
			case PsfPackage.PSF__VERSION:
				setVersion((String) newValue);
				return;
			case PsfPackage.PSF__PROVIDERS:
				getProviders().clear();
				getProviders().addAll((Collection<? extends RepositoryProvider>) newValue);
				return;
			case PsfPackage.PSF__WORKING_SETS:
				getWorkingSets().clear();
				getWorkingSets().addAll((Collection<? extends WorkingSet>) newValue);
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
			case PsfPackage.PSF__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case PsfPackage.PSF__PROVIDERS:
				getProviders().clear();
				return;
			case PsfPackage.PSF__WORKING_SETS:
				getWorkingSets().clear();
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
	public EList<RepositoryProvider> getProviders() {
		if (providers == null) {
			providers = new EObjectContainmentEList<RepositoryProvider>(RepositoryProvider.class, this, PsfPackage.PSF__PROVIDERS);
		}
		return providers;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<WorkingSet> getWorkingSets() {
		if (workingSets == null) {
			workingSets = new EObjectContainmentEList<WorkingSet>(WorkingSet.class, this, PsfPackage.PSF__WORKING_SETS);
		}
		return workingSets;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PsfPackage.PSF__VERSION, oldVersion, version));
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
		result.append(" (version: ");
		result.append(version);
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
		return PsfPackage.Literals.PSF;
	}

} // PSFImpl
