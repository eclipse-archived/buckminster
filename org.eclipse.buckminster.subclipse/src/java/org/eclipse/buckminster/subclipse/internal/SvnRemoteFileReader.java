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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.FileHandle;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.ISVNDirEntry;
import org.tigris.subversion.svnclientadapter.SVNClientException;
import org.tigris.subversion.svnclientadapter.SVNNodeKind;
import org.tigris.subversion.svnclientadapter.SVNRevision;
import org.tigris.subversion.svnclientadapter.SVNUrl;

/**
 * The SVN repository reader assumes that any repository contains the three recommended directories <code>trunk</code>,
 * <code>tags</code>, and <code>branches</code>. A missing <code>tags</code> directory is interpreted as no
 * <code>tags</code>. A missing <code>branches</code> directory is interpreted as no branches. The URL used as the
 * repository identifier must contain the path element trunk. Anything that follows the <code>trunk</code> element in
 * the path will be considered a <code>module</code> path. The repository URL may also contain a query part that in
 * turn may have four different flags:
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
 */
public class SvnRemoteFileReader extends AbstractRemoteReader
{
	private final SvnSession m_session;
	private final ISVNDirEntry[] m_topEntries;
	/**
	 * @param readerType
	 * @param rInfo
	 * @param withResolvedBranch
	 * @throws CoreException
	 */
	public SvnRemoteFileReader(IReaderType readerType, ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException
	{
		super(readerType, rInfo);
		VersionMatch vm = rInfo.getVersionMatch();
		VersionSelector branchOrTag = vm.getBranchOrTag();
		m_session = new SvnSession(rInfo.getRepositoryURI(), branchOrTag, vm.getRevision(), vm.getTimestamp(), rInfo.getNodeQuery().getContext());
		m_topEntries = m_session.listFolder(m_session.getSVNUrl(null), monitor);
		if(m_topEntries.length == 0)
			throw BuckminsterException.fromMessage("Unable to find artifacts at %s", m_session);
	}

	@Override
	public void close()
	{
		m_session.close();
	}

	@Override
	protected void innerGetMatchingRootFiles(Pattern pattern, List<FileHandle> files, IProgressMonitor monitor)
	throws CoreException, IOException
	{
		ArrayList<String> names = null;
		for(ISVNDirEntry dirEntry : m_topEntries)
		{
			String fileName = dirEntry.getPath();
			if(pattern.matcher(fileName).matches())
			{
				if(names == null)
					names = new ArrayList<String>();
				names.add(fileName);
			}
		}
		if(names == null)
			return;

		if(names.size() == 1)
			files.add(innerGetContents(names.get(0), monitor));
		else
		{
			monitor.beginTask(null, names.size() * 100);
			for(String name : names)
				files.add(innerGetContents(name, MonitorUtils.subMonitor(monitor, 100)));
			monitor.done();
		}
	}

	@Override
	protected void innerList(List<String> files, IProgressMonitor monitor) throws CoreException
	{
		for(ISVNDirEntry dirEntry : m_topEntries)
		{
			String fileName = dirEntry.getPath();
			if(dirEntry.getNodeKind() == SVNNodeKind.DIR && ! fileName.endsWith("/"))
				fileName = fileName + "/";
			files.add(fileName);
		}
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
					SVNUrl svnURL = m_session.getSVNUrl(null);
					SVNRevision svnRev = m_session.getRevision();
					CorePlugin.getLogger().debug("Checking out %s using revision %s", svnURL, svnRev);
					m_session.getClientAdapter().checkout(svnURL, destDir, svnRev, true);
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
			throw BuckminsterException.fromMessage("SVN checkout timed out");
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
	public String toString()
	{
		return m_session.toString();
	}

	@Override
	protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor) throws CoreException,
			IOException
	{
		Logger logger = CorePlugin.getLogger();
		IPath path = Path.fromPortableString(fileName);
		String topEntry = path.segment(0);

		boolean found = false;
		for(ISVNDirEntry dirEntry : m_topEntries)
		{
			if(dirEntry.getPath().equals(topEntry))
			{
				found = true;
				break;
			}
		}

		SVNUrl url = m_session.getSVNUrl(fileName);
		SVNRevision revision = m_session.getRevision();
		String key = SvnSession.cacheKey(url, revision);
		if(!found)
			throw new FileNotFoundException(key);

		ISVNClientAdapter clientAdapter = m_session.getClientAdapter();
		File destFile = null;
		OutputStream output = null;
		InputStream input = null;

		int ticksLeft = 3;
		monitor.beginTask(fileName, ticksLeft);

		try
		{
			byte[] buf = new byte[0x1000];

			logger.debug("Reading remote file %s", key);
			input = clientAdapter.getContent(url, revision);
			int bytesRead = input.read(buf);

			if(bytesRead == 0)
			{
				// Suspect file not found
				//
				if(clientAdapter.getDirEntry(url, revision) == null)
				{
					logger.debug("Remote file not found %s", key);
					throw new FileNotFoundException(url.toString());
				}
			}

			destFile = this.createTempFile();
			output = new FileOutputStream(destFile);
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
			FileHandle fh = new FileHandle(fileName, destFile, true);
			destFile = null;
			return fh;
		}
		catch(SVNClientException e)
		{
			// Unwind until we get a message and create an IOException.
			//
			Throwable p = e;
			Throwable t;
			String msg = e.getMessage();
			while(msg == null && (t = p.getCause()) != null)
				p = t;
			if(msg == null)
				msg = e.toString();
			else
			{
				String lcMsg = msg.toLowerCase();
				if(lcMsg.contains("file not found") || lcMsg.contains("path not found"))
				{
					if(logger.isDebugEnabled())
						logger.debug(String.format("Remote file not found %s", key));
					throw new FileNotFoundException(key);
				}
			}
			IOException ioe = new IOException(msg);
			ioe.initCause(p);
			throw ioe;
		}
		finally
		{
			IOUtils.close(input);
			IOUtils.close(output);
			if(destFile != null)
				destFile.delete();
			if(ticksLeft > 0)
    			MonitorUtils.worked(monitor, ticksLeft);
			monitor.done();
		}
	}
}
