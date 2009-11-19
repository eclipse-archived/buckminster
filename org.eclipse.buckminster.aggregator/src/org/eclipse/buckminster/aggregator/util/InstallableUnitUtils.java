/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.aggregator.AggregatorFactory;
import org.eclipse.buckminster.aggregator.IAggregatorConstants;
import org.eclipse.buckminster.aggregator.InstallableUnitType;
import org.eclipse.buckminster.aggregator.Status;
import org.eclipse.buckminster.aggregator.StatusCode;
import org.eclipse.buckminster.aggregator.p2.InstallableUnit;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.equinox.internal.provisional.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.internal.provisional.p2.metadata.IProvidedCapability;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionedId;

/**
 * @author Karel Brezina
 * 
 */
public class InstallableUnitUtils
{
	private static final Pattern proxyFragmentPattern = Pattern.compile("^//@metadataRepository/@installableUnits\\[id='([^']*)',version='([^']*)'\\]$");

	public static Status getStatus(InstallableUnit iu)
	{
		synchronized(iu)
		{
			return Trivial.trim(iu.getId()) != null
					? AggregatorFactory.eINSTANCE.createStatus(StatusCode.OK)
					: AggregatorFactory.eINSTANCE.createStatus(StatusCode.BROKEN);
		}
	}

	public static InstallableUnitType getType(InstallableUnit iu)
	{
		if("true".equalsIgnoreCase(iu.getProperty(IInstallableUnit.PROP_TYPE_CATEGORY)))
			return InstallableUnitType.CATEGORY;
		if(iu.getId().endsWith(IAggregatorConstants.FEATURE_SUFFIX))
			return InstallableUnitType.FEATURE;
		if("true".equalsIgnoreCase(iu.getProperty(IInstallableUnit.PROP_TYPE_GROUP)))
			return InstallableUnitType.PRODUCT;
		if(isOSGiFragment(iu))
			return InstallableUnitType.FRAGMENT;
		if(isOSGiBundle(iu))
			return InstallableUnitType.BUNDLE;
		return InstallableUnitType.OTHER;
	}

	/**
	 * Obtains the name and version information either from the proxy URI fragment or from attributes. So, it works for
	 * both genuine instance or proxy.
	 */
	public static VersionedId getVersionedName(InstallableUnit iu)
	{
		if(((EObject)iu).eIsProxy())
			return getVersionedNameFromProxy(iu);
		else
			return new VersionedId(iu.getId(), iu.getVersion());
	}

	/**
	 * Obtains the name and version information from the proxy URI fragment
	 */
	public static VersionedId getVersionedNameFromProxy(InstallableUnit iu)
	{
		URI uri = ((InternalEObject)iu).eProxyURI();
		if(uri == null)
			return null;

		String frag = uri.fragment();
		if(frag == null)
			return null;

		Matcher m = proxyFragmentPattern.matcher(frag);
		return m.matches()
				? new VersionedId(m.group(1), m.group(2))
				: null;
	}

	private static boolean isOSGiBundle(InstallableUnit iu)
	{
		for(IProvidedCapability rc : iu.getProvidedCapabilityList())
			if(IAggregatorConstants.NAMESPACE_TYPE.equals(rc.getNamespace())
					&& (IAggregatorConstants.CAPABILITY_TYPE_BUNDLE.equals(rc.getName()) || IAggregatorConstants.CAPABILITY_TYPE_SOURCE.equals(rc.getName())))
				return true;
		return false;
	}

	private static boolean isOSGiFragment(InstallableUnit iu)
	{
		for(IProvidedCapability rc : iu.getProvidedCapabilityList())
			if(IAggregatorConstants.NAMESPACE_OSGI_FRAGMENT.equals(rc.getNamespace()))
				return true;
		return false;
	}

}
