/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.metadata.impl;

import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetaData;
import org.eclipse.buckminster.aggregator.engine.maven.metadata.MetadataPackage;
import org.eclipse.buckminster.aggregator.engine.maven.metadata.Versioning;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Meta Data</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetaDataImpl#getGroupId <em>Group Id</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetaDataImpl#getArtifactId <em>Artifact Id
 * </em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetaDataImpl#getVersion <em>Version</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.metadata.impl.MetaDataImpl#getVersioning <em>Versioning
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MetaDataImpl extends EObjectImpl implements MetaData
{
	/**
	 * The default value of the '{@link #getGroupId() <em>Group Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected static final String GROUP_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getGroupId() <em>Group Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroupId()
	 * @generated
	 * @ordered
	 */
	protected String groupId = GROUP_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected static final String ARTIFACT_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getArtifactId() <em>Artifact Id</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getArtifactId()
	 * @generated
	 * @ordered
	 */
	protected String artifactId = ARTIFACT_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The cached value of the '{@link #getVersioning() <em>Versioning</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getVersioning()
	 * @generated
	 * @ordered
	 */
	protected Versioning versioning;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MetaDataImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetVersioning(Versioning newVersioning, NotificationChain msgs)
	{
		Versioning oldVersioning = versioning;
		versioning = newVersioning;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					MetadataPackage.META_DATA__VERSIONING, oldVersioning, newVersioning);
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
		case MetadataPackage.META_DATA__GROUP_ID:
			return getGroupId();
		case MetadataPackage.META_DATA__ARTIFACT_ID:
			return getArtifactId();
		case MetadataPackage.META_DATA__VERSION:
			return getVersion();
		case MetadataPackage.META_DATA__VERSIONING:
			return getVersioning();
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
		case MetadataPackage.META_DATA__VERSIONING:
			return basicSetVersioning(null, msgs);
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
		case MetadataPackage.META_DATA__GROUP_ID:
			return GROUP_ID_EDEFAULT == null
					? groupId != null
					: !GROUP_ID_EDEFAULT.equals(groupId);
		case MetadataPackage.META_DATA__ARTIFACT_ID:
			return ARTIFACT_ID_EDEFAULT == null
					? artifactId != null
					: !ARTIFACT_ID_EDEFAULT.equals(artifactId);
		case MetadataPackage.META_DATA__VERSION:
			return VERSION_EDEFAULT == null
					? version != null
					: !VERSION_EDEFAULT.equals(version);
		case MetadataPackage.META_DATA__VERSIONING:
			return versioning != null;
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
		case MetadataPackage.META_DATA__GROUP_ID:
			setGroupId((String)newValue);
			return;
		case MetadataPackage.META_DATA__ARTIFACT_ID:
			setArtifactId((String)newValue);
			return;
		case MetadataPackage.META_DATA__VERSION:
			setVersion((String)newValue);
			return;
		case MetadataPackage.META_DATA__VERSIONING:
			setVersioning((Versioning)newValue);
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
		case MetadataPackage.META_DATA__GROUP_ID:
			setGroupId(GROUP_ID_EDEFAULT);
			return;
		case MetadataPackage.META_DATA__ARTIFACT_ID:
			setArtifactId(ARTIFACT_ID_EDEFAULT);
			return;
		case MetadataPackage.META_DATA__VERSION:
			setVersion(VERSION_EDEFAULT);
			return;
		case MetadataPackage.META_DATA__VERSIONING:
			setVersioning((Versioning)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getArtifactId()
	{
		return artifactId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getVersion()
	{
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Versioning getVersioning()
	{
		return versioning;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setArtifactId(String newArtifactId)
	{
		String oldArtifactId = artifactId;
		artifactId = newArtifactId;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.META_DATA__ARTIFACT_ID,
					oldArtifactId, artifactId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setGroupId(String newGroupId)
	{
		String oldGroupId = groupId;
		groupId = newGroupId;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.META_DATA__GROUP_ID, oldGroupId,
					groupId));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersion(String newVersion)
	{
		String oldVersion = version;
		version = newVersion;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.META_DATA__VERSION, oldVersion,
					version));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setVersioning(Versioning newVersioning)
	{
		if(newVersioning != versioning)
		{
			NotificationChain msgs = null;
			if(versioning != null)
				msgs = ((InternalEObject)versioning).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- MetadataPackage.META_DATA__VERSIONING, null, msgs);
			if(newVersioning != null)
				msgs = ((InternalEObject)newVersioning).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- MetadataPackage.META_DATA__VERSIONING, null, msgs);
			msgs = basicSetVersioning(newVersioning, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, MetadataPackage.META_DATA__VERSIONING, newVersioning,
					newVersioning));
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
		result.append(" (groupId: ");
		result.append(groupId);
		result.append(", artifactId: ");
		result.append(artifactId);
		result.append(", version: ");
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
	protected EClass eStaticClass()
	{
		return MetadataPackage.Literals.META_DATA;
	}

} // MetaDataImpl
