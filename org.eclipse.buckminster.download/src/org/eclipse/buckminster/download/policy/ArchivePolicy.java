/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.policy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import org.eclipse.buckminster.download.ICache;
import org.eclipse.buckminster.download.Installer;
import org.eclipse.buckminster.download.Messages;
import org.eclipse.buckminster.download.internal.FileReader;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 * 
 */
public class ArchivePolicy extends AbstractFetchPolicy {
	private static final Object THREADLOCK = new Object();

	/**
	 * Creates directories in a synchronized block. Note: The same method is in
	 * the org.eclipse.buckminster.core.helpers.FileUtils class, however, for
	 * the dependency hierarchy reasons, this package is not accessible from
	 * here. This could be solved by refactoring the dependencies.
	 * 
	 * @param directory
	 *            The path for which all directories should be created
	 * @throws CoreException
	 *             If the directories cannot be created
	 */
	private static void mkdirs(File directory) throws CoreException {
		synchronized (THREADLOCK) {
			if (directory == null || directory.exists() && !directory.isDirectory())
				throw BuckminsterException.fromMessage(NLS.bind(Messages.error_0_cause_1, NLS.bind(Messages.unable_to_create_directory_0,
						directory != null ? directory : "(null)"), Messages.not_a_directory)); //$NON-NLS-1$

			if (!directory.exists() && !directory.mkdirs())
				throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_create_directory_0, directory));
		}
	}

	private final String remoteName;

	private final IConnectContext connectContext;

	public ArchivePolicy(ICache cache, IConnectContext cctx, String remoteName) {
		super(cache);
		this.remoteName = remoteName;
		this.connectContext = cctx;
	}

	@Override
	public boolean update(URL remoteFile, File localFile, boolean checkOnly, IFileInfo[] fiHandle, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.begin(monitor, 1000);
		try {
			long localFileTS = localFile.lastModified();
			if (localFileTS != 0L) {
				long localAge = System.currentTimeMillis() - localFileTS;
				if (localAge <= DEFAULT_MAX_LOCAL_AGE) {
					if (fiHandle != null)
						fiHandle[0] = readLocalFileInfo(remoteFile);
					return false;
				}

				IFileInfo fi;
				try {
					fi = getCache().getRemoteInfo(remoteFile, connectContext);
					if (fiHandle != null)
						fiHandle[0] = fi;
				} catch (FileNotFoundException e) {
					localFile.delete();
					throw BuckminsterException.wrap(e);
				}
				if (fi.getSize() == localFile.length() && fi.getLastModified() != 0L && fi.getLastModified() <= localFile.lastModified()) {
					// Update the timestamp on the local file to reflec the
					// check that
					// we just made.
					//
					localFile.setLastModified(System.currentTimeMillis());
					return false;
				}
			}
			MonitorUtils.worked(monitor, 100);
			if (checkOnly)
				return true;

			File tempFile = new File(localFile.getPath() + ".tmp"); //$NON-NLS-1$
			String fileName = readRemoteFile(remoteFile, tempFile, fiHandle, MonitorUtils.subMonitor(monitor, 800));
			if (remoteName != null)
				fileName = remoteName;

			try {
				Installer.getInstaller(fileName, true).validate(tempFile, MonitorUtils.subMonitor(monitor, 100));
			} catch (CoreException e) {
				tempFile.delete();
				throw e;
			}
			safeRename(tempFile, localFile);
			return true;
		} finally {
			MonitorUtils.done(monitor);
		}
	}

	protected String readRemoteFile(URL url, File localFile, IFileInfo[] fiHandle, IProgressMonitor monitor) throws CoreException {
		// Set up the file transfer
		//
		OutputStream output = null;
		try {
			localFile = localFile.getCanonicalFile();
			File parentFolder = localFile.getParentFile();
			if (parentFolder != null)
				mkdirs(parentFolder);
			output = new FileOutputStream(localFile);
			FileReader retriever = new FileReader(connectContext);
			retriever.readInto(url, output, monitor);
			IFileInfo fileInfo = retriever.getLastFileInfo();
			saveLocalFileInfo(url, fileInfo);
			if (fiHandle != null)
				fiHandle[0] = fileInfo;
			return fileInfo.getRemoteName();
		} catch (IOException e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(output);
		}
	}

}
