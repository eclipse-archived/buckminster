/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.subversive.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
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
import org.eclipse.buckminster.subversive.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.util.NLS;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.ISVNProgressMonitor;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.connector.SVNEntry;
import org.eclipse.team.svn.core.connector.SVNEntryRevisionReference;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.connector.SVNEntry.Kind;
import org.eclipse.team.svn.core.operation.file.GetFileContentOperation;

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
 */
public class SubversiveRemoteFileReader extends AbstractRemoteReader
{
	private final SubversiveSession m_session;

	private final SVNEntry[] m_topEntries;

	/**
	 * @param readerType
	 * @param rInfo
	 * @param withResolvedBranch
	 * @throws CoreException
	 */
	public SubversiveRemoteFileReader(IReaderType readerType, ProviderMatch rInfo, IProgressMonitor monitor)
			throws CoreException
	{
		super(readerType, rInfo);
		VersionMatch vm = rInfo.getVersionMatch();
		VersionSelector branchOrTag = vm.getBranchOrTag();
		m_session = new SubversiveSession(rInfo.getRepositoryURI(), branchOrTag, vm.getRevision(), vm.getTimestamp(),
				rInfo.getNodeQuery().getContext());
		m_topEntries = m_session.listFolder(m_session.getSVNUrl(null), monitor);
		if(m_topEntries.length == 0)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_find_artifacts_at_0, m_session));
	}

	@Override
	public void close()
	{
		m_session.close();
	}

	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException
	{
		boolean success = false;
		File destDir = destination.toFile();

		ISVNProgressMonitor svnMon = SimpleMonitorWrapper.beginTask(monitor, 12);
		try
		{
			m_session.getSVNProxy().checkout(
					new SVNEntryRevisionReference(m_session.getSVNUrl(null).toString(), null, m_session.getRevision()),
					destDir.toString(), ISVNConnector.Depth.INFINITY, ISVNConnector.Options.IGNORE_EXTERNALS, svnMon);
			success = true;
		}
		catch(SVNConnectorException e)
		{
			throw BuckminsterException.wrap(e);
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
	protected FileHandle innerGetContents(String fileName, IProgressMonitor monitor) throws CoreException, IOException
	{
		Logger logger = CorePlugin.getLogger();
		IPath path = Path.fromPortableString(fileName);
		String topEntry = path.segment(0);

		boolean found = false;
		for(SVNEntry dirEntry : m_topEntries)
		{
			if(topEntry.equals(dirEntry.path))
			{
				found = true;
				break;
			}
		}

		URI url = m_session.getSVNUrl(fileName);
		SVNRevision revision = m_session.getRevision();
		String key = SubversiveSession.cacheKey(url, revision);
		if(!found)
			throw new FileNotFoundException(key);

		File destFile = null;
		OutputStream output = null;
		InputStream input = null;

		MonitorUtils.begin(monitor, 200);
		ISVNProgressMonitor svnMon = SimpleMonitorWrapper.beginTask(MonitorUtils.subMonitor(monitor, 180), 100);

		try
		{
			logger.debug(NLS.bind(Messages.reading_remote_file_0, key));
			ISVNConnector proxy = m_session.getSVNProxy();
			destFile = this.createTempFile();
			output = new FileOutputStream(destFile);
			proxy.streamFileContent(new SVNEntryRevisionReference(url.toString(), null, revision),
					GetFileContentOperation.DEFAULT_BUFFER_SIZE, output, svnMon);
			IOUtils.close(output);

			if(destFile.length() == 0)
			{
				// Suspect file not found
				//
				if(m_session.getDirEntry(url, revision, MonitorUtils.subMonitor(monitor, 20)) == null)
				{
					logger.debug(NLS.bind(Messages.remote_file_not_found_0, key));
					throw new FileNotFoundException(url.toString());
				}
			}
			else
				MonitorUtils.worked(monitor, 20);

			FileHandle fh = new FileHandle(fileName, destFile, true);
			destFile = null;
			return fh;
		}
		catch(SVNConnectorException e)
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
				if(lcMsg.contains(Messages.exception_part_file_not_found)
						|| lcMsg.contains(Messages.exception_part_path_not_found)
						|| lcMsg.contains(Messages.exception_part_unable_to_find_repository_location))
				{
					if(logger.isDebugEnabled())
						logger.debug(NLS.bind(Messages.remote_file_not_found_0, key));
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
			monitor.done();
		}
	}

	@Override
	protected void innerGetMatchingRootFiles(Pattern pattern, List<FileHandle> files, IProgressMonitor monitor)
			throws CoreException, IOException
	{
		ArrayList<String> names = null;
		for(SVNEntry dirEntry : m_topEntries)
		{
			String fileName = dirEntry.path;
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
		for(SVNEntry dirEntry : m_topEntries)
		{
			String fileName = dirEntry.path;
			if(dirEntry.nodeKind == Kind.DIR && !fileName.endsWith("/")) //$NON-NLS-1$
				fileName = fileName + "/"; //$NON-NLS-1$
			files.add(fileName);
		}
	}
}
