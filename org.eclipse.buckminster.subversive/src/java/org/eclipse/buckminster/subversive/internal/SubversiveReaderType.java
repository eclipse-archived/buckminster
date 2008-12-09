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
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.subversion.GenericReaderType;
import org.eclipse.buckminster.subversion.ISubversionSession;
import org.eclipse.buckminster.subversive.Messages;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.team.svn.core.SVNTeamProjectMapper;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.SVNChangeStatus;
import org.eclipse.team.svn.core.connector.SVNEntry;
import org.eclipse.team.svn.core.connector.SVNEntryInfo;
import org.eclipse.team.svn.core.connector.ISVNConnector.Depth;
import org.eclipse.team.svn.core.extension.CoreExtensionsManager;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.resource.IRepositoryContainer;
import org.eclipse.team.svn.core.resource.IRepositoryLocation;
import org.eclipse.team.svn.core.utility.SVNUtility;

/**
 * @author Thomas Hallgren
 * @author Guillaume Chatelet
 */
public class SubversiveReaderType extends GenericReaderType<SVNEntry>
{
	private static SVNChangeStatus getLocalInfo(File workingCopy, IProgressMonitor monitor)
	{
		IPath location = Path.fromOSString(workingCopy.toString());
		IPath checkedPath = workingCopy.isFile()
				? location.removeLastSegments(1)
				: location;
		if(!checkedPath.append(SVNUtility.getSVNFolderName()).toFile().exists())
			return null;

		ISVNConnector proxy = CoreExtensionsManager.instance().getSVNConnectorFactory().newInstance();
		try
		{
			SVNChangeStatus[] st = SVNUtility.status(proxy, location.toString(), Depth.IMMEDIATES,
					ISVNConnector.Options.INCLUDE_UNCHANGED, new SVNNullProgressMonitor());
			if(st == null || st.length == 0)
				return null;

			SVNUtility.reorder(st, true);
			return st[0];
		}
		catch(Exception ex)
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
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		SVNChangeStatus localInfo = getLocalInfo(workingCopy, monitor);
		return localInfo == null
				? null
				: new Date(localInfo.lastChangedDate);
	}

	@Override
	public long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		SVNChangeStatus localInfo = getLocalInfo(workingCopy, monitor);
		return localInfo == null
				? -1
				: localInfo.lastChangedRevision;
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		return new SubversiveRemoteFileReader(this, providerMatch, monitor);
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
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery,
			IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new SubversiveVersionFinder(provider, ctype, nodeQuery);
	}

	@Override
	protected ISubversionSession<SVNEntry> getSession(String repositoryURI, VersionSelector branchOrTag, long revision,
			Date timestamp, RMContext context) throws CoreException
	{
		return new SubversiveSession(repositoryURI, branchOrTag, revision, timestamp, context);
	}

	@Override
	protected void updateRepositoryMap(IProject project, ISubversionSession<SVNEntry> session) throws Exception
	{
		IRepositoryLocation location = ((SubversiveSession)session).getRepositoryLocation();
		IRepositoryContainer resource = null;
		resource = location.asRepositoryContainer(session.getSVNUrl().toString(), true);
		if(resource != null)
		{
			SVNTeamProjectMapper.map(project, resource);
		}
		else
			throw BuckminsterException.fromMessage(Messages.could_not_create_repository_resource);
	}
}
