/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.subversive.internal;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.reader.AbstractRemoteReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.polarion.team.svn.core.SVNTeamProjectMapper;
import org.polarion.team.svn.core.client.ClientWrapperException;
import org.polarion.team.svn.core.client.ISVNClientWrapper;
import org.polarion.team.svn.core.client.Revision;
import org.polarion.team.svn.core.operation.SVNNullProgressMonitor;
import org.polarion.team.svn.core.resource.IRepositoryContainer;
import org.polarion.team.svn.core.resource.IRepositoryLocation;
import org.polarion.team.svn.core.svnstorage.SVNRemoteStorage;
import org.tmatesoft.svn.core.SVNURL;

/**
 * The SVN repository reader assumes that any repository contains the three
 * recommended directories <code>trunk</code>, <code>tags</code>, and
 * <code>branches</code>. A missing <code>tags</code> directory is
 * interpreted as no <code>tags</code>. A missing <code>branches</code>
 * directory is interpreted as no branches. The URL used as the repository
 * identifier must contain the path element trunk. Anything that follows the
 * <code>trunk</code> element in the path will be considered a
 * <code>module</code> path. The repository URL may also contain a query part
 * that in turn may have four different flags:
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
 */
public class SubversiveRemoteFileReader extends AbstractRemoteReader {
	private final SubversiveSession m_session;

	private final Revision m_revision;

	/**
	 * @param readerType
	 * @param rInfo
	 * @param withResolvedBranch
	 * @throws CoreException
	 */
	public SubversiveRemoteFileReader(IReaderType readerType, ProviderMatch rInfo) throws CoreException {
		super(readerType, rInfo);
		String tag;
		String branch;
		VersionMatch vm = rInfo.getVersionMatch();
		IVersionConverter vc = rInfo.getVersionConverter();
		IVersionSelector vs = vm.getFixedVersionSelector();
		switch (vc.getType()) {
		case TAG:
			branch = null;
			tag = vm.getVersion().isDefault() ? null : vc.createSelector(vm.getVersion()).getQualifier();
			break;
		default:
			tag = null;
			branch = vs.isDefaultBranch() ? null : vs.getBranchName();
		}
		m_session = new SubversiveSession(rInfo.getRepositoryURI(), branch, tag);
		m_revision = SubversiveReaderType.getSVNRevision(vs);
	}
	
	

	public boolean canMaterialize() {
		return true;
	}

	@Override
	public File innerGetContents (String fileName,boolean[] isTemporary,  IProgressMonitor monitor) throws CoreException, IOException {
		OutputStream output = null;
		InputStream input = null;

		int ticksLeft = 3;
		monitor.beginTask(fileName, ticksLeft);
		try {
			byte[] buf = new byte[0x1000];
			SVNURL url = m_session.getSVNUrl(fileName);

			try {
				ISVNClientWrapper clientWrapper = m_session.getSVNClientWrapper();
				buf = clientWrapper.fileContent(url.toString(), m_revision, m_revision, new SVNNullProgressMonitor());

			} catch (ClientWrapperException e) {
				throw new FileNotFoundException(url.toString());
			}
			input = new ByteArrayInputStream(buf);

			File destFile = this.createTempFile();
			output = new FileOutputStream(destFile);
			output.write(buf, 0, buf.length);
			if (monitor.isCanceled())
				throw new OperationCanceledException();
			if (ticksLeft > 0) {
				monitor.worked(1);
				--ticksLeft;
			}
			isTemporary[0]=true;
			return destFile;
		} finally {
			IOUtils.close(input);
			IOUtils.close(output);
			if (ticksLeft > 0)
				monitor.worked(ticksLeft);
			monitor.done();
		}
	}

	public void innerMaterialize(IPath location, IProgressMonitor monitor) throws CoreException {
		boolean success = false;
		final File destination = location.toFile();
		final Object[] resultSlot = new Object[1];

		monitor.beginTask(this.toString(), 12);

		FileUtils.prepareDestination(destination, true, new SubProgressMonitor(monitor, 2));

		// We need to run the checkout in a separate thread to be able
		// to cancel.
		//
		Thread worker = new Thread() {
			@Override
			public void run() {
				try {
					m_session.getSVNClientWrapper().checkout(m_session.getSVNUrl(null).toString(), destination.toString(), m_revision, m_revision, true, false, new SVNNullProgressMonitor());
					resultSlot[0] = Boolean.TRUE;
				} catch (Throwable e) {
					resultSlot[0] = e;
				}
			}
		};

		try {
			worker.start();
			Object result;
			int workAmount = 0;
			while ((result = resultSlot[0]) == null) {
				if (monitor.isCanceled())
					worker.join(1);
				Thread.sleep(500);
				if (workAmount < 10) {
					monitor.worked(1);
					++workAmount;
				}
			}
			if (result instanceof Throwable)
				throw BuckminsterException.wrap((Throwable) result);
			success = true;
		} catch (InterruptedException e) {
			throw new BuckminsterException("SVN checkout timed out");
		} finally {
			if (!success) {
				try {
					FileUtils.deleteRecursive(destination, new NullProgressMonitor());
				} catch (Throwable t) {
					t.printStackTrace();
				}
			}
			monitor.done();
		}
	}

	@Override
	public String toString() {
		return m_session.toString();
	}

	/**
	 * Map the given project to the Subversion location of this remote file
	 * reader
	 * 
	 * @param project
	 *            The project that should be mapped to the Subversion location.
	 *            This project should be a real, existing project.
	 */
	public void map(IProject project) throws BuckminsterException {
		try {
			IRepositoryLocation location = getRepositoryLocation();
			IRepositoryContainer resource = null;
			resource = location.asRepositoryContainer(m_session.getSVNUrl(null).toString(), true);
			if (resource != null) {
				SVNTeamProjectMapper.map(project, resource);
			} else
				throw new BuckminsterException("Could not create repository resource");
		} catch (Exception ex) {
			throw new BuckminsterException(ex.getMessage());
		}

	}

	/**
	 * Retrieve the repository location to be used for this remote file reader.
	 * 
	 * @return
	 * @throws MalformedURLException
	 *             will be thrown when the URL in the SubversiveSession of this
	 *             remote file reader is not correct
	 */
	private IRepositoryLocation getRepositoryLocation() throws MalformedURLException {
		IRepositoryLocation result = null;
		IRepositoryLocation[] locations = SVNRemoteStorage.instance().getRepositoryLocations();
		for (IRepositoryLocation currentLocation : locations) {
			if (currentLocation.getUrl().startsWith(m_session.getURLLeadIn())) {
				result = currentLocation;
				break;
			}
		}
		if (result == null) {
			IRepositoryLocation location = SVNRemoteStorage.instance().newRepositoryLocation();
			location.setUrl(m_session.getURLLeadIn());
			location.setBranchesLocation("branches");
			location.setTagsLocation("tags");
			location.setTrunkLocation("trunk");
			location.setStructureEnabled(true);
			SVNRemoteStorage.instance().addRepositoryLocation(location);
			result = location;
		}
		return result;
	}
}
