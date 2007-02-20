/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.svn.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.tigris.subversion.svnclientadapter.ISVNClientAdapter;
import org.tigris.subversion.svnclientadapter.SVNClientException;
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
	private final SVNRevision m_revision;

	private final SvnSession m_session;

	/**
	 * @param readerType
	 * @param rInfo
	 * @param withResolvedBranch
	 * @throws CoreException
	 */
	public SvnRemoteFileReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
		String tag = null;
		String branch = null;
		VersionMatch vm = rInfo.getVersionMatch();
		IVersionConverter vc = rInfo.getVersionConverter();
		IVersionSelector vs = vm.getFixedVersionSelector();
		if(vc != null)
		{
			switch(vc.getType())
			{
			case TAG:
				branch = null;
				tag = vm.getVersion().isDefault()
						? null
						: vc.createSelector(vm.getVersion()).getQualifier();
				break;
			default:
				tag = null;
				branch = vs.isDefaultBranch()
						? null
						: vs.getBranchName();
			}
		}
		m_session = new SvnSession(rInfo.getRepositoryURI(), branch, tag);
		m_revision = SvnReaderType.getSVNRevision(vs);
	}

	public boolean canMaterialize()
	{
		return true;
	}

	@Override
	public void close()
	{
		m_session.close();
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
					m_session.getClientAdapter().checkout(m_session.getSVNUrl(null), destDir, m_revision, true);
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
			throw new BuckminsterException("SVN checkout timed out");
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
	protected File innerGetContents(String fileName, boolean[] isTemporary, IProgressMonitor monitor) throws CoreException,
			IOException
	{
		ISVNClientAdapter clientAdapter = m_session.getClientAdapter();
		OutputStream output = null;
		InputStream input = null;

		int ticksLeft = 3;
		monitor.beginTask(fileName, ticksLeft);
		try
		{
			byte[] buf = new byte[0x1000];
			SVNUrl url = m_session.getSVNUrl(fileName);
			input = clientAdapter.getContent(url, m_revision);
			int bytesRead = input.read(buf);

			if(bytesRead == 0)
			{
				// Suspect file not found
				//
				if(clientAdapter.getDirEntry(url, m_revision) == null)
					throw new FileNotFoundException(url.toString());
			}

			File destFile = this.createTempFile();
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
			isTemporary[0] = true;
			return destFile;
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
				if(msg.toLowerCase().contains("file not found"))
					throw new FileNotFoundException(msg);
			}
			IOException ioe = new IOException(msg);
			ioe.initCause(p);
			throw ioe;
		}
		finally
		{
			IOUtils.close(input);
			IOUtils.close(output);
			if(ticksLeft > 0)
    			MonitorUtils.worked(monitor, ticksLeft);
			monitor.done();
		}
	}
}
