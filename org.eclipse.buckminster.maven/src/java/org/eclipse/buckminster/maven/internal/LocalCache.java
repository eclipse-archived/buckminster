/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.maven.internal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.maven.MavenPlugin;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 * 
 */
public class LocalCache
{
	public static final int MAX_FAILURES = 2;

	private static int hexDigit(byte c)
	{
		int v = 0;
		if(c >= '0' && c <= '9')
			v = c - '0';
		else if(c >= 'a' && c <= 'f')
			v = (c - 'a') + 10;
		else if(c >= 'A' && c <= 'F')
			v = (c - 'A') + 10;
		return v;
	}

	private static void writeHex(byte[] bytes, OutputStream stream) throws IOException
	{
		for(int idx = 0; idx < bytes.length; ++idx)
		{
			byte b = bytes[idx];
			int x = (b & 0xf0) >> 4;
			stream.write(x >= 10
					? x + ('a' - 10)
					: x + '0');
			x = b & 0x0f;
			stream.write(x >= 10
					? x + ('a' - 10)
					: x + '0');
		}
	}

	private static byte[] readHex(String name, InputStream stream, int size) throws CoreException, IOException
	{
		byte[] buffer = new byte[size * 2];
		int bytesRead;
		int remain = buffer.length;
		int totRead = 0;
		while(remain > 0 && (bytesRead = stream.read(buffer, totRead, remain)) > 0)
		{
			totRead += bytesRead;
			remain -= bytesRead;
		}

		if(totRead != buffer.length)
			throw BuckminsterException.fromMessage("Unable to read the 40 character hexadecimal form of the sha1 for "
					+ name);

		byte[] result = new byte[size];
		for(int idx = 0; idx < size; ++idx)
		{
			int cidx = idx << 1;
			int b = (hexDigit(buffer[cidx]) << 4) | hexDigit(buffer[cidx + 1]);
			result[idx] = (byte)(b & 0xff);
		}
		return result;
	}

	private final IPath m_localCacheRoot;

	public LocalCache(IPath localCacheRoot)
	{
		m_localCacheRoot = localCacheRoot;
	}

	public InputStream openFile(URL repository, IPath path, IProgressMonitor monitor) throws CoreException, IOException
	{
		IProgressMonitor subMonitor = monitor;
		int failureCounter = 0;
		for(;;)
		{
			File localFile;
			try
			{
				localFile = obtainLocalFile(repository, path, failureCounter, subMonitor);
				return new FileInputStream(localFile);
			}
			catch(CoreException e)
			{
				throw e;
			}
			catch(FileNotFoundException e)
			{
				throw e;
			}
			catch(IOException e)
			{
				if(++failureCounter == MAX_FAILURES)
					throw e;

				// Increase the attempt counter and try again.
			}
		}
	}

	private static byte[] readRemoteDigest(StringBuilder urlBld, String suffix, int nBytes, IProgressMonitor monitor) throws CoreException
	{
		int len = urlBld.length();
		urlBld.append(suffix);
		String urlStr = urlBld.toString();
		urlBld.setLength(len);

		InputStream input = null;
		try
		{
			input = URLUtils.openStream(new URL(urlStr), MonitorUtils.subMonitor(monitor, 5));
			return readHex(urlStr, input, nBytes);
		}
		catch(IOException e)
		{
			return null;
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	private static final String SHA1_SUFFIX = ".sha1";
	private static final int SHA1_LEN = 20;

	private static final String MD5_SUFFIX = ".md5";
	private static final int MD5_LEN = 16;

	private File obtainLocalFile(URL repository, IPath path, int failureCounter, IProgressMonitor monitor) throws IOException,
			CoreException
	{
		IPath fullPath = m_localCacheRoot.append(path);
		File file = fullPath.toFile();

		StringBuilder urlBld = new StringBuilder(repository.toExternalForm());
		if(urlBld.charAt(urlBld.length() - 1) != '/')
			urlBld.append('/');

		urlBld.append(path.toPortableString());
		URL remoteURL = new URL(urlBld.toString());

		IPath containingFolder = fullPath.removeLastSegments(1);

		IPath md5Path = containingFolder.append(path.lastSegment() + MD5_SUFFIX);
		File md5File = md5Path.toFile();


		byte[] remoteSha1 = null;
		byte[] remoteMd5 = null;
		if((failureCounter & 1) == 0)
		{
			remoteMd5 = readRemoteDigest(urlBld, MD5_SUFFIX, MD5_LEN, MonitorUtils.subMonitor(monitor, 5));
			if(remoteMd5 == null)
				remoteSha1 = readRemoteDigest(urlBld, SHA1_SUFFIX, SHA1_LEN, MonitorUtils.subMonitor(monitor, 5));
		}
		else
		{
			remoteSha1 = readRemoteDigest(urlBld, SHA1_SUFFIX, SHA1_LEN, MonitorUtils.subMonitor(monitor, 5));
			if(remoteSha1 == null)
				remoteMd5 = readRemoteDigest(urlBld, MD5_SUFFIX, MD5_LEN, MonitorUtils.subMonitor(monitor, 5));
		}

		byte[] remoteDigest;
		File localDigestFile;
		int digestSize;
		if(remoteMd5 == null)
		{
			remoteDigest = remoteSha1;
			localDigestFile = containingFolder.append(path.lastSegment() + SHA1_SUFFIX).toFile();
			digestSize = SHA1_LEN;
		}
		else
		{
			remoteDigest = remoteMd5;
			localDigestFile = md5File;
			digestSize = MD5_LEN;
		}
		InputStream input = null;
		if(remoteDigest != null)
		{
			// Compare local and with remote digest for equality
			//
			try
			{
				input = new FileInputStream(localDigestFile);
				byte[] localDigest = readHex(localDigestFile.toString(), input, digestSize);
				if(Arrays.equals(remoteDigest, localDigest))
				{
					// We should have a local file if we have a local digest but
					// we better make sure
					//
					if(file.exists())
						return file;
				}
			}
			catch(FileNotFoundException e)
			{
				// We don't have a local digest. That's OK.
			}
			catch(CoreException e)
			{
				// The local digest file is corrupt. Disregard...
			}
			finally
			{
				IOUtils.close(input);
			}
		}

		MessageDigest md;
		try
		{
			md = MessageDigest.getInstance(remoteSha1 == null ? "MD5" : "SHA1");
			md.reset();
		}
		catch(NoSuchAlgorithmException e)
		{
			throw BuckminsterException.wrap(e);
		}

		OutputStream output = null;
		try
		{

			byte[] transferBuffer = new byte[0x2000];
			File outputDir = containingFolder.toFile();
			if(!(outputDir.exists() || outputDir.mkdirs()))
				throw new IOException("Unable to create directory " + outputDir);

			input = URLUtils.openStream(remoteURL, MonitorUtils.subMonitor(monitor, 5));
			output = new FileOutputStream(file);
			int len;
			while((len = input.read(transferBuffer)) > 0)
			{
				md.update(transferBuffer, 0, len);
				output.write(transferBuffer, 0, len);
			}
		}
		finally
		{
			IOUtils.close(input);
			IOUtils.close(output);
		}

		byte[] localDigest = md.digest();

		boolean matchingDigest; 
		if(remoteDigest == null)
		{
			MavenPlugin.getLogger().warning("Unable to find Digest for " + remoteURL);
			matchingDigest = false;
		}
		else
			matchingDigest = Arrays.equals(remoteDigest, localDigest);

		if(matchingDigest || remoteDigest == null || failureCounter == MAX_FAILURES - 1)
		{

			if(remoteDigest != null && !matchingDigest && failureCounter == MAX_FAILURES - 1)
				//
				// The maven repo is not perfect. Sometimes the MD5 and SHA1 are incorrect
				// due to replace of the actual jar
				//
				MavenPlugin.getLogger().warning("Digest for " + remoteURL + " still doesn't match after " + MAX_FAILURES + " download attempts. Corrupt repo?");

			try
			{
				output = new FileOutputStream(localDigestFile);
				writeHex(localDigest, output);
			}
			finally
			{
				IOUtils.close(output);
			}
			return file;
		}

		// These one is corrupt somehow
		//
		localDigestFile.delete();
		file.delete();
		throw new IOException("Digest mismatch after download for " + remoteURL);
	}

	public IPath getRootPath()
	{
		return m_localCacheRoot;
	}
}
