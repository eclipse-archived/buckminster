/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.team.core.RepositoryProvider;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractReaderType extends AbstractExtension implements IReaderType
{
	protected class DefaultVersionFinder extends AbstractVersionFinder
	{
		private final VersionMatch m_versionMatch;

		DefaultVersionFinder(Provider provider, IComponentType ctype, NodeQuery query)
		{
			super(provider, ctype, query);
			m_versionMatch = new VersionMatch(null, null, provider.getSpace(), -1, null, null);
		}

		public VersionMatch getBestVersion(IProgressMonitor monitor)
		throws CoreException
		{
			MonitorUtils.complete(monitor);
			return m_versionMatch;
		}
	}

	public void addMaterializationNode(MaterializationSpecBuilder bld, Resolution res) throws CoreException
	{
	}

	public String getRecommendedMaterializer()
	{
		return IMaterializer.WORKSPACE;
	}

	public static IReaderType getTypeForResource(IResource resource) throws CoreException
	{
		if(resource == null)
			return null;

		IProject project = resource.getProject();
		if(project == null)
			return null;

		RepositoryProvider provider = RepositoryProvider.getProvider(project);
		if(provider == null)
			return null;

		String providerId = provider.getID();
		IExtensionRegistry exReg = Platform.getExtensionRegistry();
		for(IConfigurationElement elem : exReg.getConfigurationElementsFor(CorePlugin.READER_TYPE_POINT))
		{
			if(providerId.equals(elem.getAttribute("teamRepositoryId")))
				return CorePlugin.getDefault().getReaderType(elem.getAttribute("id"));
		}
		return null;
	}

	public URL convertToURL(String repositoryLocator, VersionMatch versionSelector) throws CoreException
	{
		return null;
	}

	public String convertFetchFactoryLocator(String fetchFactoryLocator, String componentName)
	throws CoreException
	{
		return fetchFactoryLocator + '/' + componentName;
	}

	public Date getLastModification(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	public long getLastRevision(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor) throws CoreException
	{
		return -1;
	}

	public long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		return -1;
	}

	public String getRemoteLocation(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	public void prepareMaterialization(List<Materialization> mtr, MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
	}

	public VersionMatch getDefaultVersion() throws CoreException
	{
		return VersionMatch.DEFAULT;
	}

	public IPath getFixedLocation(Resolution cr) throws CoreException
	{
		return null;
	}

	public IPath getInstallLocation(Resolution resolution, MaterializationContext context) throws CoreException
	{
		return null;
	}

	public IReaderType getLocalReaderType(boolean destIsFile) throws CoreException
	{
		return CorePlugin.getDefault().getReaderType(destIsFile ? URL : URL_CATALOG);
	}

	public String getRemotePath(String repositoryLocation) throws CoreException
	{
		return null;
	}

	public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor)
	throws CoreException
	{
	}

	public IComponentReader getReader(Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException
	{
		return getReader(cr.getProviderMatch(context), monitor);
	}

	public IComponentReader getReader(Provider provider, IComponentType ctype, NodeQuery query, VersionMatch versionMatch, IProgressMonitor monitor)
	throws CoreException
	{
		return getReader(new ProviderMatch(provider, ctype, versionMatch, ProviderScore.FAIR, query), monitor);
	}

	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		return new DefaultVersionFinder(provider, ctype, query);
	}

	public void postMaterialization(MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
	}
}
