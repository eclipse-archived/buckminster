/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.rmap.maven.impl;

import org.eclipse.buckminster.rmap.impl.ProviderImpl;

import org.eclipse.buckminster.rmap.maven.Mappings;
import org.eclipse.buckminster.rmap.maven.MavenProvider;
import org.eclipse.buckminster.rmap.maven.MavenPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Maven Provider</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.eclipse.buckminster.rmap.maven.impl.MavenProviderImpl#getMappings
 * <em>Mappings</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MavenProviderImpl extends ProviderImpl implements MavenProvider {
	/**
	 * The cached value of the '{@link #getMappings() <em>Mappings</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMappings()
	 * @generated
	 * @ordered
	 */
	protected Mappings mappings;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MavenProviderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetMappings(Mappings newMappings, NotificationChain msgs) {
		Mappings oldMappings = mappings;
		mappings = newMappings;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, MavenPackage.MAVEN_PROVIDER__MAPPINGS, oldMappings,
					newMappings);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				return getMappings();
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
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				return basicSetMappings(null, msgs);
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
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				return mappings != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				setMappings((Mappings) newValue);
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
			case MavenPackage.MAVEN_PROVIDER__MAPPINGS:
				setMappings((Mappings) null);
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
	public Mappings getMappings() {
		return mappings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMappings(Mappings newMappings) {
		if (newMappings != mappings) {
			NotificationChain msgs = null;
			if (mappings != null)
				msgs = ((InternalEObject) mappings).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - MavenPackage.MAVEN_PROVIDER__MAPPINGS, null, msgs);
			if (newMappings != null)
				msgs = ((InternalEObject) newMappings).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - MavenPackage.MAVEN_PROVIDER__MAPPINGS, null, msgs);
			msgs = basicSetMappings(newMappings, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MavenPackage.MAVEN_PROVIDER__MAPPINGS, newMappings, newMappings));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return MavenPackage.Literals.MAVEN_PROVIDER;
	}

} // MavenProviderImpl
