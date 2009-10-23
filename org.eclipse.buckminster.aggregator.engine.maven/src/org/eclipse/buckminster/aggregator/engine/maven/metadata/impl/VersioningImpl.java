/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata.impl;

import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage;
import org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning;
import org.eclipse.buckminster.aggregator.engine.maven.metadata.Versions;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Versioning</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersioningImpl#getRelease <em>Release</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersioningImpl#getVersions <em>Versions
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.VersioningImpl#getLastUpdated <em>Last
 * Updated</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class VersioningImpl extends EObjectImpl implements Versioning
{
	/**
	 * The default value of the '{@link #getRelease() <em>Release</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRelease()
	 * @generated
	 * @ordered
	 */
	protected static final String RELEASE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getRelease() <em>Release</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getRelease()
	 * @generated
	 * @ordered
	 */
	protected String release = RELEASE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVersions() <em>Versions</em>}' containment reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getVersions()
	 * @generated
	 * @ordered
	 */
	protected Versions versions;

	/**
	 * The default value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getLastUpdated()
	 * @generated
	 * @ordered
	 */
	protected static final String LAST_UPDATED_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastUpdated() <em>Last Updated</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLastUpdated()
	 * @generated
	 * @ordered
	 */
	protected String lastUpdated = LAST_UPDATED_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected VersioningImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetVersions(Versions newVersions, NotificationChain msgs)
	{
		Versions oldVersions = versions;
		versions = newVersions;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					MetadataPackage.VERSIONING__VERSIONS, oldVersions, newVersions);
			if(msgs == null)
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
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case MetadataPackage.VERSIONING__RELEASE:
			return getRelease();
		case MetadataPackage.VERSIONING__VERSIONS:
			return getVersions();
		case MetadataPackage.VERSIONING__LAST_UPDATED:
			return getLastUpdated();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
	{
		switch(featureID)
		{
		case MetadataPackage.VERSIONING__VERSIONS:
			return basicSetVersions(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID)
	{
		switch(featureID)
		{
		case MetadataPackage.VERSIONING__RELEASE:
			return RELEASE_EDEFAULT == null
					? release != null
					: !RELEASE_EDEFAULT.equals(release);
		case MetadataPackage.VERSIONING__VERSIONS:
			return versions != null;
		case MetadataPackage.VERSIONING__LAST_UPDATED:
			return LAST_UPDATED_EDEFAULT == null
					? lastUpdated != null
					: !LAST_UPDATED_EDEFAULT.equals(lastUpdated);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case MetadataPackage.VERSIONING__RELEASE:
			setRelease((String)newValue);
			return;
		case MetadataPackage.VERSIONING__VERSIONS:
			setVersions((Versions)newValue);
			return;
		case MetadataPackage.VERSIONING__LAST_UPDATED:
			setLastUpdated((String)newValue);
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
	public void eUnset(int featureID)
	{
		switch(featureID)
		{
		case MetadataPackage.VERSIONING__RELEASE:
			setRelease(RELEASE_EDEFAULT);
			return;
		case MetadataPackage.VERSIONING__VERSIONS:
			setVersions((Versions)null);
			return;
		case MetadataPackage.VERSIONING__LAST_UPDATED:
			setLastUpdated(LAST_UPDATED_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getLastUpdated()
	{
		return lastUpdated;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getRelease()
	{
		return release;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Versions getVersions()
	{
		return versions;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLastUpdated(String newLastUpdated)
	{
		String oldLastUpdated = lastUpdated;
		lastUpdated = newLastUpdated;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.VERSIONING__LAST_UPDATED,
					oldLastUpdated, lastUpdated));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRelease(String newRelease)
	{
		String oldRelease = release;
		release = newRelease;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.VERSIONING__RELEASE, oldRelease,
					release));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersions(Versions newVersions)
	{
		if(newVersions != versions)
		{
			NotificationChain msgs = null;
			if(versions != null)
				msgs = ((InternalEObject)versions).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- MetadataPackage.VERSIONING__VERSIONS, null, msgs);
			if(newVersions != null)
				msgs = ((InternalEObject)newVersions).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- MetadataPackage.VERSIONING__VERSIONS, null, msgs);
			msgs = basicSetVersions(newVersions, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.VERSIONING__VERSIONS, newVersions,
					newVersions));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (release: ");
		result.append(release);
		result.append(", lastUpdated: ");
		result.append(lastUpdated);
		result.append(')');
		return result.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return MetadataPackage.Literals.VERSIONING;
	}

} // VersioningImpl
