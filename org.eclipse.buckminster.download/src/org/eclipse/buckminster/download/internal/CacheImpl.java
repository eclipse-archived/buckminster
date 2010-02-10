/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.download.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.UUID;

import org.eclipse.buckminster.download.ICache;
import org.eclipse.buckminster.download.IFetchPolicy;
import org.eclipse.buckminster.download.Messages;
import org.eclipse.buckminster.download.policy.ArchivePolicy;
import org.eclipse.buckminster.download.policy.DigestPolicy;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.FileInfoBuilder;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class CacheImpl implements ICache {
	public static final String LAST_MODIFIED_HEADER = "Last-Modified"; //$NON-NLS-1$

	private static File asLocal(URL url) {
		try {
			url = FileLocator.resolve(url);
			return "file".equalsIgnoreCase(url.getProtocol()) ? new File(url.toURI()) : null;
		} catch (IOException e) {
			return null;
		} catch (URISyntaxException e) {
			return null;
		}
	}

	private final File location;

	public CacheImpl(File location) throws CoreException {
		this.location = location;
		if (!(location.isDirectory() || location.mkdirs()))
			throw BuckminsterException.fromMessage(NLS.bind(Messages.unable_to_access_cache_0, location));
	}

	public UUID getHash(String urlStr) {
		return UUID.nameUUIDFromBytes(urlStr.getBytes());
	}

	@Override
	public File getLocation() {
		return location;
	}

	@Override
	public IFileInfo getRemoteInfo(URL remoteFile, IConnectContext cctx) throws CoreException, FileNotFoundException {
		File localFile = asLocal(remoteFile);
		if (localFile != null) {
			FileInfoBuilder fib = new FileInfoBuilder();
			fib.setLastModified(localFile.lastModified());
			fib.setName(localFile.getAbsolutePath());
			fib.setSize(localFile.length());
			return fib;
		}
		FileReader reader = new FileReader(cctx);
		return reader.readInfo(remoteFile);
	}

	public File getSubFolder(String protocol, String domain) {
		File protoFolder = new File(location, protocol);
		if (domain == null)
			domain = "localhost"; //$NON-NLS-1$
		return new File(protoFolder, domain);
	}

	public File getSubFolder(URL url) {
		return getSubFolder(url.getProtocol(), url.getHost());
	}

	@Override
	public boolean isUpToDate(IFetchPolicy policy, URL remoteFile, IProgressMonitor monitor) throws CoreException, FileNotFoundException {
		String urlStr = remoteFile.toString().intern();
		synchronized (urlStr) {
			File localFile = asLocal(remoteFile);
			if (localFile != null)
				return localFile.canRead();

			localFile = new File(getSubFolder(remoteFile), getHash(urlStr).toString());
			return !policy.update(remoteFile, localFile, true, null, monitor);
		}
	}

	@Override
	public boolean isUpToDate(URL remoteFile, IConnectContext cctx, String remoteName, IProgressMonitor monitor) throws CoreException,
			FileNotFoundException {
		return isUpToDate(new ArchivePolicy(this, cctx, remoteName), remoteFile, monitor);
	}

	@Override
	public boolean isUpToDate(URL remoteFile, URL remoteDigest, IConnectContext cctx, String algorithm, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException {
		return isUpToDate(new DigestPolicy(this, remoteDigest, cctx, algorithm, DigestPolicy.DEFAULT_MAX_DIGEST_AGE), remoteFile, monitor);
	}

	@Override
	public InputStream open(IFetchPolicy policy, URL remoteFile, IFileInfo[] fiHandle, IProgressMonitor monitor) throws CoreException,
			FileNotFoundException {
		File file = asLocal(remoteFile);
		if (file != null)
			return new FileInputStream(file);

		String urlStr = remoteFile.toString().intern();
		synchronized (urlStr) {
			File localFile = new File(getSubFolder(remoteFile), getHash(urlStr).toString());
			policy.update(remoteFile, localFile, false, fiHandle, monitor);
			return new FileInputStream(localFile);
		}
	}

	@Override
	public InputStream open(URL remoteFile, IConnectContext cctx, String remoteName, IFileInfo[] fiHandle, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException {
		return open(new ArchivePolicy(this, cctx, remoteName), remoteFile, fiHandle, monitor);
	}

	@Override
	public InputStream open(URL remoteFile, URL remoteDigest, IConnectContext cctx, String algorithm, IFileInfo[] fiHandle, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException {
		return open(new DigestPolicy(this, remoteDigest, cctx, algorithm, DigestPolicy.DEFAULT_MAX_DIGEST_AGE), remoteFile, fiHandle, monitor);
	}

	@Override
	public InputStream openRemote(URL remoteFile, IConnectContext cctx) throws CoreException, FileNotFoundException {
		File file = asLocal(remoteFile);
		if (file != null)
			return new FileInputStream(file);

		FileReader reader = new FileReader(cctx);
		return reader.read(remoteFile);
	}
}
