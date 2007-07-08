/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author Thomas Hallgren
 */
public class SiteFeatureFinder extends AbstractVersionFinder
{
	private final ISite m_site;
	private final ComponentRequest m_request;

	SiteFeatureFinder(Provider provider, IComponentType ctype, NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		super(provider, ctype, query);
		m_site = SiteFeatureReaderType.getSite(provider.getURI(query.getProperties()), monitor);
		m_request = query.getComponentRequest();
	}

	public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException
	{
		String space = getProvider().getSpace();
		String name = m_request.getName();
		IVersion bestFit = null;
		for(ISiteFeatureReference featureRef : m_site.getRawFeatureReferences())
		{
			VersionedIdentifier versionAndId = featureRef.getVersionedIdentifier();

			if(!name.equals(versionAndId.getIdentifier()))
				continue;

			IVersion version;
			try
			{
				version = VersionFactory.OSGiType.coerce(versionAndId.getVersion());
			}
			catch(Exception e)
			{
				// Unable to convert feature version to an OSGi version
				//
				continue;
			}

			boolean isMatch = getQuery().isMatch(version, null, space);
			if(isMatch && (bestFit == null || version.compareTo(bestFit) > 0))
				bestFit = version;
		}
		return (bestFit == null) ? null : new VersionMatch(bestFit, null, space, -1, null, null);
	}
}
