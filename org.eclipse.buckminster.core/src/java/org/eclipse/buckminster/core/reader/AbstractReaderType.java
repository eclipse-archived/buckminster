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
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.IVersionQuery;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public abstract class AbstractReaderType extends AbstractExtension implements IReaderType
{
	protected class DefaultVersionFinder implements IVersionFinder
	{
		public VersionMatch getBestVersion(IVersionQuery query, IProgressMonitor monitor)
		throws CoreException
		{
			return query.matches(VersionSelectorFactory.MAIN_LATEST)
				? AbstractReaderType.this.getDefaultVersion() : null;
		}

		public VersionMatch getDefaultVersion(IProgressMonitor monitor) throws CoreException
		{
			return AbstractReaderType.this.getDefaultVersion();
		}

		public void close()
		{
			// Nothing.
		}
	}

	public URL convertToURL(String repositoryLocator, IVersionSelector versionSelector) throws CoreException
	{
		return null;
	}

	public String convertFetchFactoryLocator(String fetchFactoryLocator, String componentName)
	throws CoreException
	{
		return fetchFactoryLocator + '/' + componentName;
	}

	public Date getLastModification(String repositoryLocation, IVersionSelector versionSelector, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	public long getLastRevision(String repositoryLocation, IVersionSelector versionSelector, IProgressMonitor monitor) throws CoreException
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

	public IPath getMaterializationLocation(Resolution cr, MaterializationContext context, boolean[] optional)
	throws CoreException
	{
		optional[0] = true;
		return null;
	}

	public boolean isFileReader()
	{
		return false;
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
		return this.getReader(cr.getProvider(), new NodeQuery(context, new QualifiedDependency(
			cr.getRequest(), cr.getAttributes())), cr.getVersionMatch(), monitor);
	}

	public IComponentReader getReader(Provider provider, NodeQuery query, VersionMatch versionMatch, IProgressMonitor monitor)
	throws CoreException
	{
		return this.getReader(new ProviderMatch(provider, versionMatch, ProviderScore.FAIR, query), monitor);
	}

	public IVersionFinder getVersionFinder(Provider provider, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException
	{
		return new DefaultVersionFinder();
	}

	public void postMaterialization(MaterializationContext contextIProgress, IProgressMonitor monitor) throws CoreException
	{
	}
}
