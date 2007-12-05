/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.subversive.internal;

import java.io.File;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.svn.core.SVNTeamProjectMapper;
import org.eclipse.team.svn.core.connector.SVNEntryInfo;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.utility.SVNUtility;

/**
 * @author Thomas Hallgren
 */
public class SubversiveReaderType extends CatalogReaderType
{
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		return new SubversiveRemoteFileReader(this, providerMatch, monitor);
	}

	@Override
	public Date getLastModification(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor)
			throws CoreException
	{
		monitor.beginTask(null, 1);
		SubversiveSession session = new SubversiveSession(repositoryLocation, versionSelector, -1L, null,
				new RMContext(null));
		try
		{
			return session.getLastTimestamp();
		}
		finally
		{
			session.close();
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		SVNEntryInfo info = SVNUtility.getSVNInfo(workingCopy);
		MonitorUtils.complete(monitor);
		return info == null
				? null
				: new Date(info.lastChangedDate);
	}

	@Override
	public long getLastRevision(String repositoryLocation, VersionSelector versionSelector, IProgressMonitor monitor)
			throws CoreException
	{
		monitor.beginTask(null, 1);
		SubversiveSession session = new SubversiveSession(repositoryLocation, versionSelector, -1L, null,
				new RMContext(null));
		try
		{
			return session.getLastChangeNumber();
		}
		finally
		{
			session.close();
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}

	@Override
	public long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		SVNEntryInfo info = SVNUtility.getSVNInfo(workingCopy);
		MonitorUtils.complete(monitor);
		return info == null
				? -1
				: info.lastChangedRevision;
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery,
			IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new SubversiveVersionFinder(provider, ctype, nodeQuery);
	}

	/**
	 * Map the given project to the Subversion location of this remote file reader
	 * 
	 * @param project
	 *            The project that should be mapped to the Subversion location. This project should be a real, existing
	 *            project.
	 */
	@Override
	public void shareProject(IProject project, Resolution cr, MaterializationContext context, IProgressMonitor monitor)
			throws CoreException
	{
		SubversiveSession.createCommonRoots(context);
		VersionMatch vm = cr.getVersionMatch();
		SubversiveSession session = new SubversiveSession(cr.getRepository(), vm.getBranchOrTag(), vm.getRevision(), vm
				.getTimestamp(), context);
		try
		{
			IRepositoryLocation location = session.getRepositoryLocation();
			IRepositoryContainer resource = null;
			resource = location.asRepositoryContainer(session.getSVNUrl(null).toString(), true);
			if(resource != null)
			{
				SVNTeamProjectMapper.map(project, resource);
			}
			else
				throw new BuckminsterException("Could not create repository resource");
		}
		catch(Exception ex)
		{
			throw new BuckminsterException(ex.getMessage());
		}
		finally
		{
			session.close();
		}
	}
}
