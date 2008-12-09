/*******************************************************************************
 * Copyright (c) 2004 - 2007
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.subclipse.internal;

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
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.TeamException;
import org.tigris.subversion.subclipse.core.SVNException;
import org.tigris.subversion.subclipse.core.SVNProviderPlugin;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.ISVNInfo;
import org.tigris.subversion.svnclientadapter.SVNClientException;
import org.tigris.subversion.svnclientadapter.SVNRevision;

/**
 * @author Thomas Hallgren
 * @author Guillaume Chatelet
 */
public class SvnReaderType extends GenericReaderType<ISVNDirEntry, SVNRevision>
{

	private abstract class SafeExecute<RETURN_TYPE extends Object>
	{
		final protected RETURN_TYPE failValue;

		public SafeExecute()
		{
			failValue = null;
		}

		public SafeExecute(RETURN_TYPE val)
		{
			failValue = val;
		}

		abstract protected RETURN_TYPE compute(ISVNInfo info);

		public RETURN_TYPE execute(File workingCopy, IProgressMonitor monitor) throws CoreException
		{
			monitor.beginTask(null, 1);
			try
			{
				final ISVNInfo info = getInfoFromWorkingCopy(workingCopy);
				if(info == null)
					return failValue;
				return compute(info);
			}
			catch(SVNClientException e)
			{
				throw BuckminsterException.wrap(e);
			}
			finally
			{
				MonitorUtils.worked(monitor, 1);
				monitor.done();
			}
		}
	}

	private ISVNInfo getInfoFromWorkingCopy(File workingCopy) throws SVNClientException, SVNException
	{
		return SVNProviderPlugin.getPlugin().getSVNClientManager().createSVNClient()
				.getInfoFromWorkingCopy(workingCopy);
	}

	@Override
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		return new SafeExecute<Date>()
		{
			@Override
			protected Date compute(ISVNInfo info)
			{
				return info.getLastChangedDate();
			}

		}.execute(workingCopy, monitor);
	}

	@SuppressWarnings("boxing")
	@Override
	public long getLastRevision(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		return new SafeExecute<Long>(new Long(-1))
		{
			@Override
			protected Long compute(ISVNInfo info)
			{
				final SVNRevision.Number lastRev = info.getLastChangedRevision();
				if(lastRev != null)
					return new Long(lastRev.getNumber());
				return failValue;
			}
		}.execute(workingCopy, monitor);
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		return new SvnRemoteFileReader(this, providerMatch, monitor);
	}

	@Override
	public String getRemoteLocation(File workingCopy, IProgressMonitor monitor) throws CoreException
	{
		return new SafeExecute<String>()
		{
			@Override
			protected String compute(ISVNInfo info)
			{
				return info.getUrl().toString();
			}
		}.execute(workingCopy, monitor);
	}

	@Override
	protected ISubversionSession<ISVNDirEntry, SVNRevision> getSession(String repositoryURI,
			VersionSelector branchOrTag, long revision, Date timestamp, RMContext context) throws CoreException
	{
		return new SvnSession(repositoryURI, branchOrTag, revision, timestamp, context);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery,
			IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new VersionFinder(provider, ctype, nodeQuery);
	}

	@Override
	protected void updateRepositoryMap(IProject project, ISubversionSession<ISVNDirEntry, SVNRevision> session)
			throws TeamException
	{
		RepositoryProvider.map(project, SVNProviderPlugin.PROVIDER_ID);
	}
}
