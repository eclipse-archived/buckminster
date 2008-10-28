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
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import org.eclipse.buckminster.download.ICache;
import org.eclipse.buckminster.download.IFetchPolicy;
import org.eclipse.buckminster.download.policy.ArchivePolicy;
import org.eclipse.buckminster.download.policy.DigestPolicy;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;

/**
 * @author Thomas Hallgren
 */
public class CacheImpl implements ICache
{
	public static final String LAST_MODIFIED_HEADER = "Last-Modified"; //$NON-NLS-1$

	private final File m_location;

	public CacheImpl(File location) throws CoreException
	{
		m_location = location;
		if(!(m_location.isDirectory() || m_location.mkdirs()))
			throw BuckminsterException.fromMessage("Unable to access cache folder at %s", location);
	}

	public File getLocation()
	{
		return m_location;
	}

	public boolean isUpToDate(IFetchPolicy policy, URL remoteFile, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		String urlStr = remoteFile.toString().intern();
		synchronized(urlStr)
		{
			File localFile = new File(getSubFolder(remoteFile), getHash(urlStr).toString());
			return !policy.update(remoteFile, localFile, true, null, monitor);
		}
	}

	public boolean isUpToDate(URL remoteFile, IConnectContext cctx, String remoteName, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		return isUpToDate(new ArchivePolicy(this, cctx, remoteName), remoteFile, monitor);
	}

	public boolean isUpToDate(URL remoteFile, URL remoteDigest, IConnectContext cctx, String algorithm, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException
	{
		return isUpToDate(new DigestPolicy(this, remoteDigest, cctx, algorithm, DigestPolicy.DEFAULT_MAX_DIGEST_AGE),
				remoteFile, monitor);
	}

	public InputStream open(IFetchPolicy policy, URL remoteFile, IFileInfo[] fiHandle, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		String urlStr = remoteFile.toString().intern();
		synchronized(urlStr)
		{
			File localFile = new File(getSubFolder(remoteFile), getHash(urlStr).toString());
			policy.update(remoteFile, localFile, false, fiHandle, monitor);
			return new FileInputStream(localFile);
		}
	}

	public InputStream open(URL remoteFile, IConnectContext cctx, String remoteName, IFileInfo[] fiHandle, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		return open(new ArchivePolicy(this, cctx, remoteName), remoteFile, fiHandle, monitor);
	}

	public InputStream open(URL remoteFile, URL remoteDigest, IConnectContext cctx, String algorithm, IFileInfo[] fiHandle, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException
	{
		return open(new DigestPolicy(this, remoteDigest, cctx, algorithm, DigestPolicy.DEFAULT_MAX_DIGEST_AGE),
				remoteFile, fiHandle, monitor);
	}

	public InputStream openRemote(URL remoteFile, IConnectContext cctx) throws CoreException, FileNotFoundException
	{
		FileReader reader = new FileReader(cctx);
		return reader.read(remoteFile);
	}

	public IFileInfo getRemoteInfo(URL remoteFile, IConnectContext cctx) throws CoreException, FileNotFoundException
	{
		FileReader reader = new FileReader(cctx);
		return reader.readInfo(remoteFile);
	}

	public UUID getHash(String urlStr)
	{
		return UUID.nameUUIDFromBytes(urlStr.getBytes());
	}

	public File getSubFolder(String protocol, String domain)
	{
		File protoFolder = new File(m_location, protocol);
		if(domain == null)
			domain = "localhost";
		return new File(protoFolder, domain);
	}

	public File getSubFolder(URL url)
	{
		return getSubFolder(url.getProtocol(), url.getHost());
	}
}
