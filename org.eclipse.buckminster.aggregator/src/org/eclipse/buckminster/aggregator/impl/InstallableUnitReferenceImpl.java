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

import java.util.Collection;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.InfosProvider;
import org.eclipse.buckminster.aggregator.AggregatorPlugin;
import org.eclipse.buckminster.aggregator.Contribution;
import org.eclipse.buckminster.aggregator.EnabledStatusProvider;
import org.eclipse.buckminster.aggregator.InstallableUnitReference;
import org.eclipse.buckminster.aggregator.MappedRepository;
import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.aggregator.util.GeneralUtils;
import org.eclipse.buckminster.aggregator.util.InstallableUnitUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
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
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl#getErrors <em>Errors</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl#getWarnings <em>Warnings</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.InstallableUnitReferenceImpl#getInfos <em>Infos</em>}</li>
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
	 * @generated NOT
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
	 * The cached value of the '{@link #getErrors() <em>Errors</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getErrors()
	 * @generated
	 * @ordered
	 */
	protected EList<String> errors;

	/**
	 * The cached value of the '{@link #getWarnings() <em>Warnings</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getWarnings()
	 * @generated
	 * @ordered
	 */
	protected EList<String> warnings;

	/**
	 * The cached value of the '{@link #getInfos() <em>Infos</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getInfos()
	 * @generated
	 * @ordered
	 */
	protected EList<String> infos;

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
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == InfosProvider.class)
		{
			switch(derivedFeatureID)
			{
			case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__ERRORS:
				return AggregatorPackage.INFOS_PROVIDER__ERRORS;
			case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__WARNINGS:
				return AggregatorPackage.INFOS_PROVIDER__WARNINGS;
			case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INFOS:
				return AggregatorPackage.INFOS_PROVIDER__INFOS;
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
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
	{
		if(baseClass == InfosProvider.class)
		{
			switch(baseFeatureID)
			{
			case AggregatorPackage.INFOS_PROVIDER__ERRORS:
				return AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__ERRORS;
			case AggregatorPackage.INFOS_PROVIDER__WARNINGS:
				return AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__WARNINGS;
			case AggregatorPackage.INFOS_PROVIDER__INFOS:
				return AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INFOS;
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
	public Object eGet(int featureID, boolean resolve, boolean coreType)
	{
		switch(featureID)
		{
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__STATUS:
			return getStatus();
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__ERRORS:
			return getErrors();
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__WARNINGS:
			return getWarnings();
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INFOS:
			return getInfos();
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
			return getStatus() != null;
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__ERRORS:
			return errors != null && !errors.isEmpty();
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__WARNINGS:
			return warnings != null && !warnings.isEmpty();
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INFOS:
			return infos != null && !infos.isEmpty();
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
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue)
	{
		switch(featureID)
		{
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__ERRORS:
			getErrors().clear();
			getErrors().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__WARNINGS:
			getWarnings().clear();
			getWarnings().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INFOS:
			getInfos().clear();
			getInfos().addAll((Collection<? extends String>)newValue);
			return;
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
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__ERRORS:
			getErrors().clear();
			return;
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__WARNINGS:
			getWarnings().clear();
			return;
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INFOS:
			getInfos().clear();
			return;
		case AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__INSTALLABLE_UNIT:
			setInstallableUnit((InstallableUnit)null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> getErrors()
	{
		errors = new BasicEList<String>();

		if(!isMappedRepositoryBroken()
				&& (getInstallableUnit() != null && Trivial.trim(getInstallableUnit().getId()) == null || getInstallableUnit() == null))
			errors.add(getString("_UI_ErrorMessage_InstallableUnitIsNotAvailable"));

		return errors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> getInfos()
	{
		infos = new BasicEList<String>();

		if(getInstallableUnit() != null)
		{
			VersionedId versionedName = InstallableUnitUtils.getVersionedName(getInstallableUnit());
			Version latestVersion = getLatestVersion();

			if(latestVersion != null && latestVersion.compareTo(versionedName.getVersion()) > 0)
				infos.add(getString("_UI_InfoMessage_InstallableUnitIsAvailableInVersion") + " "
						+ GeneralUtils.stringifyVersion(latestVersion));
		}

		return infos;
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

	public Version getLatestVersion()
	{
		VersionedId versionedName = InstallableUnitUtils.getVersionedName(getInstallableUnit());

		if(versionedName == null)
			return null;

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
			return iu.getVersion();
		}
		else
			return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	synchronized public Status getStatus()
	{
		if(!isBranchDisabledOrMappedRepositoryBroken() && getInstallableUnit() != null)
			if(InstallableUnitUtils.getStatus(getInstallableUnit()).getCode() == StatusCode.BROKEN)
			{
				Version latestVersion = getLatestVersion();

				if(latestVersion != null)
				{
					return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN,
							getString("_UI_InfoMessage_InstallableUnitIsAvailableInVersion") + " "
									+ GeneralUtils.stringifyVersion(latestVersion));
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
	 * @generated
	 */
	public EList<String> getWarnings()
	{
		if(warnings == null)
		{
			warnings = new EDataTypeUniqueEList<String>(String.class, this,
					AggregatorPackage.INSTALLABLE_UNIT_REFERENCE__WARNINGS);
		}
		return warnings;
	}

	public boolean isBranchDisabledOrMappedRepositoryBroken()
	{
		return !isBranchEnabled() || isMappedRepositoryBroken();
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
	public String toString()
	{
		if(eIsProxy())
			return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (errors: ");
		result.append(errors);
		result.append(", warnings: ");
		result.append(warnings);
		result.append(", infos: ");
		result.append(infos);
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
		return AggregatorPackage.Literals.INSTALLABLE_UNIT_REFERENCE;
	}

} // InstallableUnitReferenceImpl
