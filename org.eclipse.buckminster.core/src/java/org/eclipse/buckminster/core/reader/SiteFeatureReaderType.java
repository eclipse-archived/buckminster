/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.reader;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.SiteManager;
import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author Thomas Hallgren
 */
public class SiteFeatureReaderType extends AbstractReaderType
{
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		checkComponentType(providerMatch.getProvider());
		MonitorUtils.complete(monitor);
		return new SiteFeatureReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, NodeQuery nodeQuery, IProgressMonitor monitor)
	throws CoreException
	{
		checkComponentType(provider);
		return new SiteFeatureFinder(provider, nodeQuery, monitor);
	}

	/**
	 * Check that the component type used by the provider is of the type
	 * {@link IComponentType#ECLIPSE_SITE_FEATURE},
	 * throw an exception if not.
	 * @param provider
	 * @throws CoreException
	 */
	public static void checkComponentType(Provider provider) throws CoreException
	{
		if(!IComponentType.ECLIPSE_SITE_FEATURE.equals(provider.getComponentTypeId()))
			throw BuckminsterException.fromMessage("Site reader can only be used with site.feature component type");
	}

	public static synchronized ISite getSite(String siteURLStr, IProgressMonitor monitor) throws CoreException
	{
		URL siteURL;
		try
		{
			siteURL = URLUtils.normalizeToURL(siteURLStr);
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
		return SiteManager.getSite(siteURL, monitor);
	}

	public static ISiteFeatureReference getSiteFeatureReference(ISite site, ComponentIdentifier ci) throws CoreException
	{
		for(ISiteFeatureReference ref : site.getRawFeatureReferences())
			if(isEqual(ci, ref.getVersionedIdentifier()))
				return ref;
		return null;
	}

	public static IFeature getSiteFeature(String siteURLStr, ComponentIdentifier ci, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 200);
		try
		{
			ISite site = getSite(siteURLStr, MonitorUtils.subMonitor(monitor, 100));
			ISiteFeatureReference ref = getSiteFeatureReference(site, ci);
			return (ref == null) ? null : ref.getFeature(MonitorUtils.subMonitor(monitor, 100));
		}
		finally
		{
			monitor.done();
		}		
	}

	private static boolean isEqual(ComponentIdentifier ci, VersionedIdentifier vi)
	{
		if(ci.getName().equals(vi.getIdentifier()))
		{
			IVersion version = ci.getVersion();
			return (version == null || version.equals(VersionFactory.OSGiType.coerce(vi.getVersion())));
		}
		return false;
	}
}
