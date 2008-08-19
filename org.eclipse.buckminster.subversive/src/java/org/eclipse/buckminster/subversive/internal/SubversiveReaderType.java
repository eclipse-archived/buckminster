/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.subversive.internal;

import java.io.File;
import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.ctype.IComponentType;
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
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.svn.core.SVNTeamProjectMapper;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.SVNChangeStatus;
import org.eclipse.team.svn.core.connector.SVNEntryInfo;
import org.eclipse.team.svn.core.connector.ISVNConnector.Depth;
import org.eclipse.team.svn.core.extension.CoreExtensionsManager;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.utility.SVNUtility;

/**
 * @author Thomas Hallgren
 */
public class SubversiveReaderType extends CatalogReaderType
{
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException
	{
		return null;
	}

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
		SVNChangeStatus localInfo = getLocalInfo(workingCopy, monitor);
		return localInfo == null
			? null
			: new Date(localInfo.lastChangedDate);
	}

	@Override
	public String getRemoteLocation(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		SVNEntryInfo info = SVNUtility.getSVNInfo(workingCopy);
		MonitorUtils.complete(monitor);
		return info == null
				? null
				: info.url;
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

	private static SVNChangeStatus getLocalInfo(File workingCopy, IProgressMonitor monitor)
	{
		IPath location = Path.fromOSString(workingCopy.toString());
		IPath checkedPath = workingCopy.isFile() ? location.removeLastSegments(1) : location;
		if(!checkedPath.append(SVNUtility.getSVNFolderName()).toFile().exists())
			return null;

		ISVNConnector proxy = CoreExtensionsManager.instance().getSVNConnectorFactory().newInstance();
		try
		{
			SVNChangeStatus[] st = SVNUtility.status(proxy, location.toString(), Depth.IMMEDIATES, ISVNConnector.Options.INCLUDE_UNCHANGED, new SVNNullProgressMonitor());
			if(st == null || st.length == 0)
				return null;

			SVNUtility.reorder(st, true);
			return st[0];
		}
		catch (Exception ex)
		{
			return null;
		}
		finally
		{
			proxy.dispose();
			MonitorUtils.complete(monitor);
		}
	}
	@Override
	public long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		SVNChangeStatus localInfo = getLocalInfo(workingCopy, monitor);
		return localInfo == null ? -1 : localInfo.lastChangedRevision;
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
	public void shareProject(IProject project, Resolution cr, RMContext context, IProgressMonitor monitor)
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
				throw BuckminsterException.fromMessage("Could not create repository resource");
		}
		catch(Exception ex)
		{
			throw BuckminsterException.wrap(ex);
		}
		finally
		{
			session.close();
		}
	}
}
