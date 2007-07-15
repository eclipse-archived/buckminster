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
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

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
			VersionMatch vm = new VersionMatch(null, null, provider.getSpace(), -1, null, null);
			m_versionMatch = query.isMatch(vm) ? vm : null;
		}

		public VersionMatch getBestVersion(IProgressMonitor monitor)
		throws CoreException
		{
			MonitorUtils.complete(monitor);
			return m_versionMatch;
		}
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

	public void prepareMaterialization(List<Materialization> mtr, MaterializationContext context, IProgressMonitor monitor) throws CoreException
	{
		// FIXME: Perhaps assert that all locations are writeable?
	}

	public VersionMatch getDefaultVersion() throws CoreException
	{
		return VersionMatch.DEFAULT;
	}

	public IPath getFixedLocation(Resolution cr) throws CoreException
	{
		return null;
	}

	public IReaderType getLocalReaderType(boolean destIsFile) throws CoreException
	{
		return CorePlugin.getDefault().getReaderType(destIsFile ? URL : URL_CATALOG);
	}

	public IPath getRootInstallLocation(Resolution cr, MaterializationContext context, boolean[] optional)
	throws CoreException
	{
		optional[0] = true;
		return null;
	}

	public String getRemotePath(String repositoryLocation) throws CoreException
	{
		return null;
	}

	public void shareProject(IProject project, Resolution cr, MaterializationContext context, IProgressMonitor monitor)
	throws CoreException
	{
	}

	public IComponentReader getReader(Resolution cr, RMContext context, IProgressMonitor monitor) throws CoreException
	{
		return this.getReader(cr.getProvider(), cr.getComponentType(), new NodeQuery(context, new QualifiedDependency(
			cr.getRequest(), cr.getAttributes())), cr.getVersionMatch(), monitor);
	}

	public IComponentReader getReader(Provider provider, IComponentType ctype, NodeQuery query, VersionMatch versionMatch, IProgressMonitor monitor)
	throws CoreException
	{
		return this.getReader(new ProviderMatch(provider, ctype, versionMatch, ProviderScore.FAIR, query), monitor);
	}

	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		return new DefaultVersionFinder(provider, ctype, query);
	}

	public void postMaterialization(MaterializationContext contextIProgress, IProgressMonitor monitor) throws CoreException
	{
	}
}
