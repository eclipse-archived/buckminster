/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.cache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.UUID;

import org.eclipse.buckminster.cache.download.FileReader;
import org.eclipse.buckminster.cache.policy.ArchivePolicy;
import org.eclipse.buckminster.cache.policy.DigestPolicy;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IFileInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

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
			return !policy.update(remoteFile, localFile, true, monitor);
		}
	}

	public boolean isUpToDate(URL remoteFile, String remoteName, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		return isUpToDate(new ArchivePolicy(this, remoteName), remoteFile, monitor);
	}

	public boolean isUpToDate(URL remoteFile, URL remoteDigest, String algorithm, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException
	{
		return isUpToDate(new DigestPolicy(this, remoteDigest, algorithm, DigestPolicy.DEFAULT_MAX_DIGEST_AGE),
				remoteFile, monitor);
	}

	public InputStream open(IFetchPolicy policy, URL remoteFile, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		String urlStr = remoteFile.toString().intern();
		synchronized(urlStr)
		{
			File localFile = new File(getSubFolder(remoteFile), getHash(urlStr).toString());
			policy.update(remoteFile, localFile, false, monitor);
			return new FileInputStream(localFile);
		}
	}

	public InputStream open(URL remoteFile, String remoteName, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		return open(new ArchivePolicy(this, remoteName), remoteFile, monitor);
	}

	public InputStream open(URL remoteFile, URL remoteDigest, String algorithm, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException
	{
		return open(new DigestPolicy(this, remoteDigest, algorithm, DigestPolicy.DEFAULT_MAX_DIGEST_AGE), remoteFile,
				monitor);
	}

	public InputStream openRemote(URL remoteFile) throws CoreException, FileNotFoundException
	{
		FileReader reader = new FileReader();
		return reader.read(remoteFile);
	}

	public IFileInfo getRemoteInfo(URL remoteFile) throws CoreException, FileNotFoundException
	{
		FileReader reader = new FileReader();
		return reader.readInfo(remoteFile);
	}

	private UUID getHash(String urlStr)
	{
		return UUID.nameUUIDFromBytes(urlStr.getBytes());
	}

	private File getSubFolder(String protocol, String domain)
	{
		File protoFolder = new File(m_location, protocol);
		if(domain == null)
			domain = "localhost";
		return new File(protoFolder, domain);
	}

	private File getSubFolder(URL url)
	{
		return getSubFolder(url.getProtocol(), url.getHost());
	}
}
