/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.reader;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.update.core.ISite;
import org.eclipse.update.core.ISiteFeatureReference;
import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author Thomas Hallgren
 */
public class SiteFeatureFinder implements IVersionFinder
{
	private final ISite m_site;
	private final ComponentRequest m_request;

	SiteFeatureFinder(String repositoryURI, ComponentRequest request, IProgressMonitor monitor) throws CoreException
	{
		m_site = SiteFeatureReaderType.getSite(repositoryURI, monitor);
		m_request = request;
	}

	public void close()
	{
	}

	@SuppressWarnings("deprecation")
	public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor) throws CoreException
	{
		String name = m_request.getName();
		boolean defaultMatched = query.matches(VersionFactory.defaultVersion());

		IVersion bestFit = null;
		for(ISiteFeatureReference featureRef : m_site.getRawFeatureReferences())
		{
			VersionedIdentifier versionAndId = featureRef.getVersionedIdentifier();

			if(!name.equals(versionAndId.getIdentifier()))
				continue;

			IVersion version;
			try
			{
				version = VersionFactory.OSGiType.fromString(versionAndId.getVersion().toString());
			}
			catch(Exception e)
			{
				// Unable to convert feature version to an OSGi version
				//
				continue;
			}

			if(!(defaultMatched || query.matches(version)))
				continue;

			if(query.matches(version) && (bestFit == null || version.compareTo(bestFit) > 0))
				bestFit = version;
		}
		return (bestFit == null) ? null : new VersionMatch(bestFit, VersionSelectorFactory.tag(bestFit.toString()));
	}

	public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException
	{
		IVersionDesignator designator = VersionFactory.createDesignator(VersionFactory.OSGiType, "0.0.0");
		IVersionQuery query = VersionSelectorFactory.createQuery(null, designator);
		return getBestVersion(query, monitor);
	}
}
