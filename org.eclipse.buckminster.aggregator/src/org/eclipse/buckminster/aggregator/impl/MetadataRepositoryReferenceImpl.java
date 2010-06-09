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

import org.eclipse.buckminster.aggregator.Aggregator;
import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.AggregatorPackage;
import org.eclipse.buckminster.aggregator.AggregatorPlugin;
import org.eclipse.buckminster.aggregator.InfosProvider;
import org.eclipse.buckminster.aggregator.MetadataRepositoryReference;
import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.buckminster.aggregator.StatusProvider;
import org.eclipse.buckminster.aggregator.p2.MetadataRepository;
import org.eclipse.buckminster.aggregator.p2.util.MetadataRepositoryResourceImpl;
import org.eclipse.buckminster.aggregator.util.AggregatorResource;
import org.eclipse.buckminster.aggregator.util.GeneralUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.equinox.internal.p2.core.helpers.StringHelper;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Metadata Repository Reference</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl#isEnabled <em>Enabled</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl#getStatus <em>Status</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl#getErrors <em>Errors</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl#getWarnings <em>Warnings</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl#getInfos <em>Infos</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl#getMetadataRepository <em>Metadata
 * Repository</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl#getLocation <em>Location</em>}</li>
 * <li>{@link org.eclipse.buckminster.aggregator.impl.MetadataRepositoryReferenceImpl#getNature <em>Nature</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MetadataRepositoryReferenceImpl extends MinimalEObjectImpl.Container implements
		MetadataRepositoryReference
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
	 * The default value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ENABLED_EDEFAULT = true;

	/**
	 * The flag representing the value of the '{@link #isEnabled() <em>Enabled</em>}' attribute. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #isEnabled()
	 * @generated
	 * @ordered
	 */
	protected static final int ENABLED_EFLAG = 1 << 0;

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
	 * The cached value of the '{@link #getMetadataRepository() <em>Metadata Repository</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getMetadataRepository()
	 * @generated
	 * @ordered
	 */
	protected MetadataRepository metadataRepository;

	/**
	 * The default value of the '{@link #getLocation() <em>Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected static final String LOCATION_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLocation() <em>Location</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLocation()
	 * @generated
	 * @ordered
	 */
	protected String location = LOCATION_EDEFAULT;

	/**
	 * The default value of the '{@link #getNature() <em>Nature</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNature()
	 * @generated
	 * @ordered
	 */
	protected static final String NATURE_EDEFAULT = "p2";

	/**
	 * The cached value of the '{@link #getNature() <em>Nature</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getNature()
	 * @generated
	 * @ordered
	 */
	protected String nature = NATURE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MetadataRepositoryReferenceImpl()
	{
		super();
		eFlags |= ENABLED_EFLAG;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataRepository basicGetMetadataRepository()
	{
		return metadataRepository;
	}

	synchronized public void cancelRepositoryLoad()
	{
		MetadataRepositoryResourceImpl.cancelLoadRepository(getNature(), getResolvedLocation(), getAggregator());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
	{
		if(baseClass == StatusProvider.class)
		{
			switch(derivedFeatureID)
			{
			case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__STATUS:
				return AggregatorPackage.STATUS_PROVIDER__STATUS;
			default:
				return -1;
			}
		}
		if(baseClass == InfosProvider.class)
		{
			switch(derivedFeatureID)
			{
			case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ERRORS:
				return AggregatorPackage.INFOS_PROVIDER__ERRORS;
			case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__WARNINGS:
				return AggregatorPackage.INFOS_PROVIDER__WARNINGS;
			case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__INFOS:
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
		if(baseClass == StatusProvider.class)
		{
			switch(baseFeatureID)
			{
			case AggregatorPackage.STATUS_PROVIDER__STATUS:
				return AggregatorPackage.METADATA_REPOSITORY_REFERENCE__STATUS;
			default:
				return -1;
			}
		}
		if(baseClass == InfosProvider.class)
		{
			switch(baseFeatureID)
			{
			case AggregatorPackage.INFOS_PROVIDER__ERRORS:
				return AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ERRORS;
			case AggregatorPackage.INFOS_PROVIDER__WARNINGS:
				return AggregatorPackage.METADATA_REPOSITORY_REFERENCE__WARNINGS;
			case AggregatorPackage.INFOS_PROVIDER__INFOS:
				return AggregatorPackage.METADATA_REPOSITORY_REFERENCE__INFOS;
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
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ENABLED:
			return isEnabled();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__STATUS:
			return getStatus();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ERRORS:
			return getErrors();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__WARNINGS:
			return getWarnings();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__INFOS:
			return getInfos();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY:
			if(resolve)
				return getMetadataRepository();
			return basicGetMetadataRepository();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__LOCATION:
			return getLocation();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__NATURE:
			return getNature();
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
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ENABLED:
			return ((eFlags & ENABLED_EFLAG) != 0) != ENABLED_EDEFAULT;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__STATUS:
			return getStatus() != null;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ERRORS:
			return errors != null && !errors.isEmpty();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__WARNINGS:
			return warnings != null && !warnings.isEmpty();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__INFOS:
			return infos != null && !infos.isEmpty();
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY:
			return metadataRepository != null;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__LOCATION:
			return LOCATION_EDEFAULT == null
					? location != null
					: !LOCATION_EDEFAULT.equals(location);
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__NATURE:
			return NATURE_EDEFAULT == null
					? nature != null
					: !NATURE_EDEFAULT.equals(nature);
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
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ENABLED:
			setEnabled((Boolean)newValue);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ERRORS:
			getErrors().clear();
			getErrors().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__WARNINGS:
			getWarnings().clear();
			getWarnings().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__INFOS:
			getInfos().clear();
			getInfos().addAll((Collection<? extends String>)newValue);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY:
			setMetadataRepository((MetadataRepository)newValue);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__LOCATION:
			setLocation((String)newValue);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__NATURE:
			setNature((String)newValue);
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
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ENABLED:
			setEnabled(ENABLED_EDEFAULT);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ERRORS:
			getErrors().clear();
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__WARNINGS:
			getWarnings().clear();
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__INFOS:
			getInfos().clear();
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY:
			setMetadataRepository((MetadataRepository)null);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__LOCATION:
			setLocation(LOCATION_EDEFAULT);
			return;
		case AggregatorPackage.METADATA_REPOSITORY_REFERENCE__NATURE:
			setNature(NATURE_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Aggregator getAggregator()
	{
		return (Aggregator)eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public EList<String> getErrors()
	{
		errors = new BasicEList<String>();

		String nature = getNature();
		String location = getResolvedLocation();
		if(nature == null || location == null)
		{
			// Node is incomplete and doesn't appoint a repository just yet.
			errors.add(getString("_UI_ErrorMessage_RepositoryIsNotSet"));
			return errors;
		}

		MetadataRepositoryResourceImpl res = (MetadataRepositoryResourceImpl)MetadataRepositoryResourceImpl.getResourceForNatureAndLocation(
				nature, location, getAggregator());

		if(res == null)
		{
			errors.add(getString("_UI_ErrorMessage_RepositoryIsNotAvailable"));
			return errors;
		}

		return errors;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getInfos()
	{
		if(infos == null)
		{
			infos = new EDataTypeUniqueEList<String>(String.class, this,
					AggregatorPackage.METADATA_REPOSITORY_REFERENCE__INFOS);
		}
		return infos;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getLocation()
	{
		return location;
	}

	/**
	 * Prevent MDR from being loaded if the mapping is disabled
	 * 
	 * @generated NOT
	 */
	public MetadataRepository getMetadataRepository()
	{
		if(!isBranchEnabled())
			return null;

		return getMetadataRepositoryGen();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public MetadataRepository getMetadataRepository(boolean forceResolve)
	{
		if(forceResolve)
			return getMetadataRepositoryGen();

		return basicGetMetadataRepository();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MetadataRepository getMetadataRepositoryGen()
	{
		if(metadataRepository != null && ((EObject)metadataRepository).eIsProxy())
		{
			InternalEObject oldMetadataRepository = (InternalEObject)metadataRepository;
			metadataRepository = (MetadataRepository)eResolveProxy(oldMetadataRepository);
			if(metadataRepository != oldMetadataRepository)
			{
				if(eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							AggregatorPackage.METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY,
							oldMetadataRepository, metadataRepository));
			}
		}
		return metadataRepository;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String getNature()
	{
		return nature;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public String getResolvedLocation()
	{
		String location = Trivial.trim(getLocation());
		if(location == null)
			return null;

		location = location.replaceAll("\\s", "%20").replace('\\', '/');
		if(location.charAt(location.length() - 1) == '/')
			location = location.substring(0, location.length() - 1);

		if(location.length() > 1 && location.charAt(1) == ':' && Character.isLetter(location.charAt(0)))
			// Path starting with a Windows drive letter
			return "file:/" + location;

		if(location.charAt(0) == '/')
			// Absolute path
			return "file:" + location;

		int colonIdx = location.indexOf(':');
		if(colonIdx > 0)
		{
			// Check that characters from start to colon is a valid scheme.
			int idx = 0;
			char c = location.charAt(0);
			if((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))
			{
				for(++idx; idx < colonIdx; ++idx)
				{
					c = location.charAt(idx);
					if(!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9') || c == '-'
							|| c == '+' || c == '.'))
						break;
				}
			}
			if(idx < colonIdx)
				colonIdx = -1;
		}

		if(colonIdx <= 0)
		{
			// Not a valid scheme so assume relative path
			URI base = ((EObject)getAggregator()).eResource().getURI();
			if(base != null)
				location = base.trimSegments(1).appendSegments(StringHelper.getArrayFromString(location, '/')).toString();
		}

		return location;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Status getStatus()
	{
		if(isBranchEnabled())
		{
			// status is ok only if MDR is not null and is resolvable
			if(getMetadataRepository() != null && !((EObject)getMetadataRepository()).eIsProxy())
				return AggregatorFactory.eINSTANCE.createStatus(StatusCode.OK);
			else
			{
				String nature = getNature();
				String location = getResolvedLocation();
				if(nature == null || location == null)
					// Node is incomplete and doesn't appoint a repository just yet.
					return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN,
							getString("_UI_ErrorMessage_RepositoryIsNotSet"));

				MetadataRepositoryResourceImpl res = (MetadataRepositoryResourceImpl)MetadataRepositoryResourceImpl.getResourceForNatureAndLocation(
						nature, location, getAggregator());

				if(res != null)
					return res.getStatus();

				return AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN,
						getString("_UI_ErrorMessage_RepositoryIsNotAvailable"));
			}
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
					AggregatorPackage.METADATA_REPOSITORY_REFERENCE__WARNINGS);
		}
		return warnings;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public boolean isBranchEnabled()
	{
		return isEnabled();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public boolean isEnabled()
	{
		return (eFlags & ENABLED_EFLAG) != 0;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public void onRepositoryLoad()
	{
		((AggregatorResource)eResource()).analyzeResource();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setEnabled(boolean newEnabled)
	{
		boolean oldEnabled = (eFlags & ENABLED_EFLAG) != 0;
		if(newEnabled)
			eFlags |= ENABLED_EFLAG;
		else
			eFlags &= ~ENABLED_EFLAG;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AggregatorPackage.METADATA_REPOSITORY_REFERENCE__ENABLED, oldEnabled, newEnabled));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLocation(String newLocation)
	{
		String oldLocation = location;
		location = newLocation;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AggregatorPackage.METADATA_REPOSITORY_REFERENCE__LOCATION, oldLocation, location));
	}

	synchronized public void setMetadataRepository(MetadataRepository newMetadataRepository)
	{
		setMetadataRepositoryGen(newMetadataRepository);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setMetadataRepositoryGen(MetadataRepository newMetadataRepository)
	{
		MetadataRepository oldMetadataRepository = metadataRepository;
		metadataRepository = newMetadataRepository;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AggregatorPackage.METADATA_REPOSITORY_REFERENCE__METADATA_REPOSITORY, oldMetadataRepository,
					metadataRepository));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setNature(String newNature)
	{
		String oldNature = nature;
		nature = newNature;
		if(eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					AggregatorPackage.METADATA_REPOSITORY_REFERENCE__NATURE, oldNature, nature));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	synchronized public void startRepositoryLoad(final boolean forceReload)
	{
		if(GeneralUtils.trimmedOrNull(getLocation()) == null)
		{
			setMetadataRepository(null);
			onRepositoryLoad();
			return;
		}

		String nature = getNature();
		String resolvedLocation = getResolvedLocation();
		Aggregator aggregator = getAggregator();
		Resource res = MetadataRepositoryResourceImpl.getResourceForNatureAndLocation(nature, resolvedLocation,
				aggregator);
		if(res != null)
			((MetadataRepositoryResourceImpl)res).startAsynchronousLoad(forceReload);
		else
		{
			onRepositoryLoad();
			return;
		}
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
		result.append(" (enabled: ");
		result.append((eFlags & ENABLED_EFLAG) != 0);
		result.append(", errors: ");
		result.append(errors);
		result.append(", warnings: ");
		result.append(warnings);
		result.append(", infos: ");
		result.append(infos);
		result.append(", location: ");
		result.append(location);
		result.append(", nature: ");
		result.append(nature);
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
		return AggregatorPackage.Literals.METADATA_REPOSITORY_REFERENCE;
	}
} // MetadataRepositoryReferenceImpl
