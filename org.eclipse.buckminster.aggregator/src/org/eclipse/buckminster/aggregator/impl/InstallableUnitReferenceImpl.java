/**
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.impl;

import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.AggregatorPlugin;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.EnabledStatusProvider;
import org.eclipse.buckminster.aggregator.InstallableUnitReference;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.util.InstallableUnitUtils;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionedId;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Collector;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.CompositeQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.InstallableUnitQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.LatestIUVersionQuery;
import org.eclipse.equinox.internal.provisional.p2.metadata.query.Query;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Installable Unit Reference</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl#getStatus <em>Status</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl#getInstallableUnit <em>Installable
 * Unit</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public abstract class InstallableUnitReferenceImpl extends MinimalEObjectImpl.Container implements
		InstallableUnitReference
{
	/**
	 * This looks up a string in the plugin's plugin.properties file. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	private static String getString(String key)
	{
		return AggregatorPlugin.INSTANCE.getString(key);
	}

	/**
	 * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected int eFlags = 0;

	/**
	 * The cached value of the '{@link #getStatus() <em>Status</em>}' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStatus()
	 * @generated
	 * @ordered
	 */
	protected Status status;

	/**
	 * The cached value of the '{@link #getInstallableUnit() <em>Installable Unit</em>}' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getInstallableUnit()
	 * @generated
	 * @ordered
	 */
	protected InstallableUnit installableUnit;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected InstallableUnitReferenceImpl()
	{
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InstallableUnit basicGetInstallableUnit()
	{
		return installableUnit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Status basicGetStatus()
	{
		return status;
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
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__STATUS:
			if(resolve)
				return getStatus();
			return basicGetStatus();
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
			if(resolve)
				return getInstallableUnit();
			return basicGetInstallableUnit();
		}
		return super.eGet(featureID, resolve, coreType);
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
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__STATUS:
			return status != null;
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
			return installableUnit != null;
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
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
			setInstallableUnit((InstallableUnit)newValue);
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
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
			setInstallableUnit((InstallableUnit)null);
			return;
		}
		super.eUnset(featureID);
	}

	public InstallableUnit getInstallableUnit()
	{
		return isMappedRepositoryBroken()
				? null
				: getInstallableUnitGen();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public InstallableUnit getInstallableUnit(boolean forceResolve)
	{
		if(forceResolve)
			return getInstallableUnit();

		return basicGetInstallableUnit();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public InstallableUnit getInstallableUnitGen()
	{
		if(installableUnit != null && ((EObject)installableUnit).eIsProxy())
		{
			InternalEObject oldInstallableUnit = (InternalEObject)installableUnit;
			installableUnit = (InstallableUnit)eResolveProxy(oldInstallableUnit);
			if(installableUnit != oldInstallableUnit)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT, oldInstallableUnit,
							installableUnit));
			}
		}
		return installableUnit;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	synchronized public Status getStatus()
	{
		if(isMappedRepositoryBroken())
			return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN,
					((MappedRepository)eContainer()).getLocation() != null
							? getString("_UI_ErrorMessage_RepositoryIsNotAvailable")
							: getString("_UI_ErrorMessage_RepositoryIsNotSet"));

		if(isBranchEnabled() && getInstallableUnit() != null)
			if(InstallableUnitUtils.getStatus(getInstallableUnit()).getCode() == StatusCode.BROKEN)
			{
				VersionedId versionedName = InstallableUnitUtils.getVersionedName(getInstallableUnit());

				Query query = new InstallableUnitQuery(versionedName.getId());

				Collector ius = ((MappedRepository)eContainer()).getMetadataRepository().query(
						new CompositeQuery(new Query[] { query, new LatestIUVersionQuery() }), new Collector(),
						new NullProgressMonitor());

				if(ius.size() <= 0)
				{
					ius = ((MappedRepository)eContainer()).getMetadataRepository().query(query, new Collector(),
							new NullProgressMonitor());
				}

				if(ius.size() > 0)
				{
					InstallableUnit iu = (InstallableUnit)ius.toArray(InstallableUnit.class)[0];
					return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN,
							getString("_UI_ErrorMessage_InstallableUnitIsAvailableInVersion") + " "
									+ iu.getVersion().toString());
				}
				else
					return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN,
							getString("_UI_ErrorMessage_InstallableUnitIsNotAvailable"));
			}

		return AggregatorFactory.eINSTANCE.createStatus(StatusCode.OK);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isBranchEnabled()
	{
		if(this instanceof EnabledStatusProvider && !((EnabledStatusProvider)this).isEnabled())
			return false;

		MappedRepository mappedRepository = (MappedRepository)eContainer();

		// a new MappedUnit without any container is enabled - used by commands that add MappedUnits
		if(mappedRepository == null)
			return true;

		if(!mappedRepository.isEnabled())
			return false;

		Contribution contribution = (Contribution)((EObject)mappedRepository).eContainer();
		if(contribution != null && !contribution.isEnabled())
			return false;

		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isMappedRepositoryBroken()
	{
		MappedRepository repo = (MappedRepository)eContainer();
		return repo == null || repo.getMetadataRepository() == null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setInstallableUnit(InstallableUnit newInstallableUnit)
	{
		InstallableUnit oldInstallableUnit = installableUnit;
		installableUnit = newInstallableUnit;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT, oldInstallableUnit, installableUnit));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass()
	{
		return AggregatorPackage.Literals.INSTALLABLE_UNIT_REFERENCE;
	}

} // InstallableUnitReferenceImpl
