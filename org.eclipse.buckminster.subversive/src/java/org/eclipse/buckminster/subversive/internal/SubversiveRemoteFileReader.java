/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.subversive.internal;

import java.io.File;
import java.io.OutputStream;
import java.net.URI;
import java.util.Date;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.subversion.GenericCache;
import org.eclipse.buckminster.subversion.GenericRemoteReader;
import org.eclipse.buckminster.subversion.ISubversionSession;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.team.svn.core.connector.ISVNConnector;
import org.eclipse.team.svn.core.connector.ISVNProgressMonitor;
import org.eclipse.team.svn.core.connector.SVNConnectorException;
import org.eclipse.team.svn.core.connector.SVNEntry;
import org.eclipse.team.svn.core.connector.SVNEntryRevisionReference;
import org.eclipse.team.svn.core.connector.SVNRevision;
import org.eclipse.team.svn.core.operation.file.GetFileContentOperation;

/**
 * The SVN repository reader assumes that any repository contains the three
 * recommended directories <code>trunk</code>, <code>tags</code>, and
 * <code>branches</code>. A missing <code>tags</code> directory is interpreted
 * as no <code>tags</code>. A missing <code>branches</code> directory is
 * interpreted as no branches. The URL used as the repository identifier must
 * contain the path element trunk. Anything that follows the <code>trunk</code>
 * element in the path will be considered a <code>module</code> path. The
 * repository URL may also contain a query part that in turn may have four
 * different flags:
 * <dl>
 * <dt>moduleBeforeTag</dt>
 * <dd>When resolving a tag, put the module name between the <code>tags</code>
 * directory and the actual tag</dd>
 * <dt>moduleAfterTag</dt>
 * <dd>When resolving a tag, append the module name after the actual tag</dd>
 * <dt>moduleBeforeBranch</dt>
 * <dd>When resolving a branch, put the module name between the
 * <code>branches</code> directory and the actual branch</dd>
 * <dt>moduleAfterBranch</dt>
 * <dd>When resolving a branch, append the module name after the actual branch</dd>
 * </dl>
 * A fragment in the repository URL will be treated as a sub-module. It will be
 * appended at the end of the resolved URL.
 * 
 * @author Thomas Hallgren
 * @author Guillaume Chatelet
 */
public class SubversiveRemoteFileReader extends GenericRemoteReader<SVNEntry, SVNRevision> {

	/**
	 * @param readerType
	 * @param rInfo
	 * @param withResolvedBranch
	 * @throws CoreException
	 */
	public SubversiveRemoteFileReader(IReaderType readerType, ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException {
		super(readerType, rInfo, monitor);
	}

	@Override
	public void innerMaterialize(IPath destination, IProgressMonitor monitor) throws CoreException {
		boolean success = false;
		File destDir = destination.toFile();

		ISVNProgressMonitor svnMon = SimpleMonitorWrapper.beginTask(monitor, 12);
		try {
			getSession().getSVNProxy().checkout(
					new SVNEntryRevisionReference(session.getSVNUrl().toString(), session.getRevision(), session.getRevision()), destDir.toString(),
					ISVNConnector.Depth.INFINITY, ISVNConnector.Options.FORCE, svnMon);
			success = true;
		} catch (SVNConnectorException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			if (!success) {
				try {
					FileUtils.deleteRecursive(destDir, new NullProgressMonitor());
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			monitor.done();
		}
	}

	@Override
	protected void fetchRemoteFile(URI url, SVNRevision revision, OutputStream output, IProgressMonitor subMonitor) throws Exception {
		final ISVNProgressMonitor svnMon = SimpleMonitorWrapper.beginTask(subMonitor, 100);
		final ISVNConnector proxy = getSession().getSVNProxy();
		final SVNEntryRevisionReference entry = new SVNEntryRevisionReference(url.toString(), revision, revision);
		proxy.streamFileContent(entry, GetFileContentOperation.DEFAULT_BUFFER_SIZE, output, svnMon);
	}

	@Override
	protected ISubversionSession<SVNEntry, SVNRevision> getSession(String repositoryURI, VersionSelector branchOrTag, long revision, Date timestamp,
			RMContext context) throws CoreException {
		return new SubversiveSession(repositoryURI, branchOrTag, revision, timestamp, context);
	}

	@Override
	protected SVNEntry[] getTopEntries(IProgressMonitor monitor) throws CoreException {
		return session.listFolder(session.getSVNUrl(), monitor);
	}

	@Override
	protected boolean remoteFileExists(URI url, SVNRevision revision, IProgressMonitor monitor) throws CoreException {
		return getSession().getDirEntry(url, revision, monitor) != null;
	}

	@Override
	protected String storeInCache(String fileName) throws CoreException {
		final URI url = session.getSVNUrl(fileName);
		final SVNRevision revision = session.getRevision();
		return GenericCache.cacheKey(url, revision);
	}

	private SubversiveSession getSession() {
		return (SubversiveSession) session;
	}
}
