/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.engine.maven.pom.impl;

import org.eclipse.buckminster.aggregator.engine.maven.pom.DeploymentRepository;
import org.eclipse.buckminster.aggregator.engine.maven.pom.DistributionManagement;
import org.eclipse.buckminster.aggregator.engine.maven.pom.PomPackage;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Relocation;
import org.eclipse.buckminster.aggregator.engine.maven.pom.Site;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Distribution Management</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.DistributionManagementImpl#getRepository <em>
 * Repository</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.DistributionManagementImpl#getSnapshotRepository
 * <em>Snapshot Repository</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.DistributionManagementImpl#getSite <em>Site</em>}
 * </li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.DistributionManagementImpl#getDownloadUrl <em>
 * Download Url</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.DistributionManagementImpl#getRelocation <em>
 * Relocation</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.engine.maven.pom.impl.DistributionManagementImpl#getStatus <em>Status
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DistributionManagementImpl extends EObjectImpl implements DistributionManagement
{
	/**
	 * The cached value of the '{@link #getRepository() <em>Repository</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getRepository()
	 * @generated
	 * @ordered
	 */
	protected DeploymentRepository repository;

	/**
	 * The cached value of the '{@link #getSnapshotRepository() <em>Snapshot Repository</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSnapshotRepository()
	 * @generated
	 * @ordered
	 */
	protected DeploymentRepository snapshotRepository;

	/**
	 * The cached value of the '{@link #getSite() <em>Site</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getSite()
	 * @generated
	 * @ordered
	 */
	protected Site site;

	/**
	 * The default value of the '{@link #getDownloadUrl() <em>Download Url</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getDownloadUrl()
	 * @generated
	 * @ordered
	 */
	protected static final String DOWNLOAD_URL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDownloadUrl() <em>Download Url</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getDownloadUrl()
	 * @generated
	 * @ordered
	 */
	protected String downloadUrl = DOWNLOAD_URL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRelocation() <em>Relocation</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getRelocation()
	 * @generated
	 * @ordered
	 */
	protected Relocation relocation;

	/**
	 * The default value of the '{@link #getStatus() <em>Status</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected static final String STATUS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected String status = STATUS_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DistributionManagementImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetRelocation(Relocation newRelocation, NotificationChain msgs)
	{
		Relocation oldRelocation = relocation;
		relocation = newRelocation;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION, oldRelocation, newRelocation);
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
	public NotificationChain basicSetRepository(DeploymentRepository newRepository, NotificationChain msgs)
	{
		DeploymentRepository oldRepository = repository;
		repository = newRepository;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY, oldRepository, newRepository);
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
	public NotificationChain basicSetSite(Site newSite, NotificationChain msgs)
	{
		Site oldSite = site;
		site = newSite;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.DISTRIBUTION_MANAGEMENT__SITE, oldSite, newSite);
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
	public NotificationChain basicSetSnapshotRepository(DeploymentRepository newSnapshotRepository,
			NotificationChain msgs)
	{
		DeploymentRepository oldSnapshotRepository = snapshotRepository;
		snapshotRepository = newSnapshotRepository;
		if(eNotificationRequired())
		{
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY, oldSnapshotRepository,
					newSnapshotRepository);
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
		case PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY:
			return getRepository();
		case PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY:
			return getSnapshotRepository();
		case PomPackage.DISTRIBUTION_MANAGEMENT__SITE:
			return getSite();
		case PomPackage.DISTRIBUTION_MANAGEMENT__DOWNLOAD_URL:
			return getDownloadUrl();
		case PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION:
			return getRelocation();
		case PomPackage.DISTRIBUTION_MANAGEMENT__STATUS:
			return getStatus();
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
		case PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY:
			return basicSetRepository(null, msgs);
		case PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY:
			return basicSetSnapshotRepository(null, msgs);
		case PomPackage.DISTRIBUTION_MANAGEMENT__SITE:
			return basicSetSite(null, msgs);
		case PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION:
			return basicSetRelocation(null, msgs);
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
		case PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY:
			return repository != null;
		case PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY:
			return snapshotRepository != null;
		case PomPackage.DISTRIBUTION_MANAGEMENT__SITE:
			return site != null;
		case PomPackage.DISTRIBUTION_MANAGEMENT__DOWNLOAD_URL:
			return DOWNLOAD_URL_EDEFAULT == null
					? downloadUrl != null
					: !DOWNLOAD_URL_EDEFAULT.equals(downloadUrl);
		case PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION:
			return relocation != null;
		case PomPackage.DISTRIBUTION_MANAGEMENT__STATUS:
			return STATUS_EDEFAULT == null
					? status != null
					: !STATUS_EDEFAULT.equals(status);
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
		case PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY:
			setRepository((DeploymentRepository)newValue);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY:
			setSnapshotRepository((DeploymentRepository)newValue);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__SITE:
			setSite((Site)newValue);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__DOWNLOAD_URL:
			setDownloadUrl((String)newValue);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION:
			setRelocation((Relocation)newValue);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__STATUS:
			setStatus((String)newValue);
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
		case PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY:
			setRepository((DeploymentRepository)null);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY:
			setSnapshotRepository((DeploymentRepository)null);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__SITE:
			setSite((Site)null);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__DOWNLOAD_URL:
			setDownloadUrl(DOWNLOAD_URL_EDEFAULT);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION:
			setRelocation((Relocation)null);
			return;
		case PomPackage.DISTRIBUTION_MANAGEMENT__STATUS:
			setStatus(STATUS_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getDownloadUrl()
	{
		return downloadUrl;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Relocation getRelocation()
	{
		return relocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DeploymentRepository getRepository()
	{
		return repository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Site getSite()
	{
		return site;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public DeploymentRepository getSnapshotRepository()
	{
		return snapshotRepository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDownloadUrl(String newDownloadUrl)
	{
		String oldDownloadUrl = downloadUrl;
		downloadUrl = newDownloadUrl;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.DISTRIBUTION_MANAGEMENT__DOWNLOAD_URL,
					oldDownloadUrl, downloadUrl));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRelocation(Relocation newRelocation)
	{
		if(newRelocation != relocation)
		{
			NotificationChain msgs = null;
			if(relocation != null)
				msgs = ((InternalEObject)relocation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION, null, msgs);
			if(newRelocation != null)
				msgs = ((InternalEObject)newRelocation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION, null, msgs);
			msgs = basicSetRelocation(newRelocation, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.DISTRIBUTION_MANAGEMENT__RELOCATION,
					newRelocation, newRelocation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setRepository(DeploymentRepository newRepository)
	{
		if(newRepository != repository)
		{
			NotificationChain msgs = null;
			if(repository != null)
				msgs = ((InternalEObject)repository).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY, null, msgs);
			if(newRepository != null)
				msgs = ((InternalEObject)newRepository).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY, null, msgs);
			msgs = basicSetRepository(newRepository, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.DISTRIBUTION_MANAGEMENT__REPOSITORY,
					newRepository, newRepository));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSite(Site newSite)
	{
		if(newSite != site)
		{
			NotificationChain msgs = null;
			if(site != null)
				msgs = ((InternalEObject)site).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.DISTRIBUTION_MANAGEMENT__SITE, null, msgs);
			if(newSite != null)
				msgs = ((InternalEObject)newSite).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.DISTRIBUTION_MANAGEMENT__SITE, null, msgs);
			msgs = basicSetSite(newSite, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.DISTRIBUTION_MANAGEMENT__SITE, newSite,
					newSite));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setSnapshotRepository(DeploymentRepository newSnapshotRepository)
	{
		if(newSnapshotRepository != snapshotRepository)
		{
			NotificationChain msgs = null;
			if(snapshotRepository != null)
				msgs = ((InternalEObject)snapshotRepository).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY, null, msgs);
			if(newSnapshotRepository != null)
				msgs = ((InternalEObject)newSnapshotRepository).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY, null, msgs);
			msgs = basicSetSnapshotRepository(newSnapshotRepository, msgs);
			if(msgs != null)
				msgs.dispatch();
		}
		else if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PomPackage.DISTRIBUTION_MANAGEMENT__SNAPSHOT_REPOSITORY, newSnapshotRepository,
					newSnapshotRepository));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setStatus(String newStatus)
	{
		String oldStatus = status;
		status = newStatus;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, PomPackage.DISTRIBUTION_MANAGEMENT__STATUS,
					oldStatus, status));
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
		result.append(" (downloadUrl: ");
		result.append(downloadUrl);
		result.append(", status: ");
		result.append(status);
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
		return PomPackage.Literals.DISTRIBUTION_MANAGEMENT;
	}

} // DistributionManagementImpl
