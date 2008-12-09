/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.subclipse.internal;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.subclipse.Messages;
import org.eclipse.buckminster.subversion.GenericCache;
import org.eclipse.buckminster.subversion.GenericRemoteReader;
import org.eclipse.buckminster.subversion.ISubversionSession;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.osgi.util.NLS;
import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.SVNRevision;
import org.tigris.subversion.svnclientadapter.SVNUrl;

/**
 * The SVN repository reader assumes that any repository contains the three recommended directories <code>trunk</code>,
 * <code>tags</code>, and <code>branches</code>. A missing <code>tags</code> directory is interpreted as no
 * <code>tags</code>. A missing <code>branches</code> directory is interpreted as no branches. The URL used as the
 * repository identifier must contain the path element trunk. Anything that follows the <code>trunk</code> element in
 * the path will be considered a <code>module</code> path. The repository URL may also contain a query part that in turn
 * may have four different flags:
 * <dl>
 * <dt>moduleBeforeTag</dt>
 * <dd>When resolving a tag, put the module name between the <code>tags</code> directory and the actual tag</dd>
 * <dt>moduleAfterTag</dt>
 * <dd>When resolving a tag, append the module name after the actual tag</dd>
 * <dt>moduleBeforeBranch</dt>
 * <dd>When resolving a branch, put the module name between the <code>branches</code> directory and the actual branch</dd>
 * <dt>moduleAfterBranch</dt>
 * <dd>When resolving a branch, append the module name after the actual branch</dd>
 * </dl>
 * A fragment in the repository URL will be treated as a sub-module. It will be appended at the end of the resolved URL.
 * 
 * @author Thomas Hallgren
 * @author Guillaume Chatelet
 */
public class SvnRemoteFileReader extends GenericRemoteReader<ISVNDirEntry>
{

	/**
	 * @param readerType
	 * @param rInfo
	 * @param withResolvedBranch
	 * @throws CoreException
	 */
	public SvnRemoteFileReader(IReaderType readerType, ProviderMatch rInfo, IProgressMonitor monitor)
			throws CoreException
	{
		super(readerType, rInfo, monitor);
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		boolean success = false;
		final File destDir = destination.toFile();
		final Object[] resultSlot = new Object[1];

		monitor.beginTask(this.toString(), 12);

		// We need to run the checkout in a separate thread to be able
		// to cancel.
		//
		Thread worker = new Thread()
		{
			@Override
			public void run()
			{
				try
				{
					SVNUrl svnURL = TypeTranslator.from(getSession().getSVNUrl(null));
					SVNRevision svnRev = getSession().getInnerRevision();
					CorePlugin.getLogger().debug(NLS.bind(Messages.checking_out_0_using_revision_1, svnURL, svnRev));
					getSession().getClientAdapter().checkout(svnURL, destDir, svnRev, true);
					resultSlot[0] = Boolean.TRUE;
				}
				catch(Throwable e)
				{
					resultSlot[0] = e;
				}
			}
		};

		try
		{
			worker.start();
			Object result;
			int workAmount = 0;
			while((result = resultSlot[0]) == null)
			{
				if(monitor.isCanceled())
				{
					worker.interrupt();
					throw new OperationCanceledException();
				}
				Thread.sleep(500);
				if(workAmount < 10)
				{
					MonitorUtils.worked(monitor, 1);
					++workAmount;
				}
			}
			if(result instanceof Throwable)
				throw BuckminsterException.wrap((Throwable)result);
			success = true;
		}
		catch(InterruptedException e)
		{
			throw BuckminsterException.fromMessage(Messages.svn_checkout_timed_out);
		}
		finally
		{
			if(!success)
			{
				try
				{
					FileUtils.deleteRecursive(destDir, new NullProgressMonitor());
				}
				catch(Throwable t)
				{
					t.printStackTrace();
				}
			}
			monitor.done();
		}
	}

	@Override
	protected void fetchRemoteFile(URI url, org.eclipse.team.svn.core.connector.SVNRevision revision,
			OutputStream output, IProgressMonitor monitor) throws Exception
	{
		int ticksLeft = 3;
		InputStream input = null;
		byte[] buf = new byte[0x1000];
		final ISVNClientAdapter clientAdapter = getSession().getClientAdapter();
		input = clientAdapter.getContent(TypeTranslator.from(url), TypeTranslator.from(revision));
		int bytesRead = input.read(buf);

		while(bytesRead > 0)
		{
			output.write(buf, 0, bytesRead);
			bytesRead = input.read(buf);
			if(ticksLeft > 0)
			{
				MonitorUtils.worked(monitor, 1);
				--ticksLeft;
			}
			else
				MonitorUtils.testCancelStatus(monitor);
		}
	}

	@Override
	protected ISubversionSession<ISVNDirEntry> getSession(String repositoryURI, VersionSelector branchOrTag,
			long revision, Date timestamp, RMContext context) throws CoreException
	{
		return new SvnSession(repositoryURI, branchOrTag, revision, timestamp, context);
	}

	@Override
	protected ISVNDirEntry[] getTopEntries(IProgressMonitor monitor) throws CoreException
	{
		final URI url = getSession().getSVNUrl(null);
		return getSession().innerListFolder(url, monitor);
	}

	@Override
	protected boolean remoteFileExists(URI url, org.eclipse.team.svn.core.connector.SVNRevision revision,
			IProgressMonitor monitor) throws CoreException
	{
		try
		{
			final ISVNClientAdapter clientAdapter = getSession().getClientAdapter();
			return clientAdapter.getDirEntry(TypeTranslator.from(url), TypeTranslator.from(revision)) != null;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	protected String storeInCache(String fileName) throws CoreException
	{
		final SvnSession session = getSession();
		return GenericCache.cacheKey(session.getSVNUrl(fileName), session.getRevision());
	}

	private SvnSession getSession()
	{
		return (SvnSession)m_session;
	}
}
