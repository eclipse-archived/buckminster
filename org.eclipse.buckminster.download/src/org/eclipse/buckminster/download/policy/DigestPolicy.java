/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.download.policy;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.eclipse.buckminster.download.ICache;
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
public class DigestPolicy extends AbstractFetchPolicy
{
	static class BytesFromHexBuilder extends ByteArrayOutputStream
	{
		private final int m_byteCount;

		public BytesFromHexBuilder(int digestLength)
		{
			m_byteCount = digestLength * 2;
		}

		public synchronized byte[] getBytes() throws CoreException
		{
			if(m_byteCount > count)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.digest_not_fully_read_expected_0_got_1,
					String.valueOf(m_byteCount), String.valueOf(count)));
			return Hex.decode(buf, m_byteCount);
		}
	}

	public static final int DEFAULT_MAX_DIGEST_AGE = 30000;

	public static final int MAX_RETRIES = 3;

	private final String m_algorithm;

	private final URL m_remoteDigest;

	private final IConnectContext m_connectContext;

	private final int m_digestLength;

	private final int m_maxDigestAge;

	public DigestPolicy(ICache cache, URL remoteDigest, IConnectContext cctx, String algorithm, int maxDigestAge) throws CoreException
	{
		super(cache);
		m_remoteDigest = remoteDigest;
		m_algorithm = algorithm;
		m_maxDigestAge = maxDigestAge;
		m_connectContext = cctx;
		try
		{
			m_digestLength = MessageDigest.getInstance(algorithm).getDigestLength();
		}
		catch(NoSuchAlgorithmException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public boolean update(URL remoteFile, File localFile, boolean checkOnly, IFileInfo[] fiHandle, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		byte[] localDigest;
		byte[] remoteDigest = null;

		MonitorUtils.begin(monitor, checkOnly ? 100 : 1000);
		try
		{
			File localDigestFile = getLocalDigest(localFile);
			if(localFile.exists())
			{
				boolean localDigestCalculated = false;
				long digestTS = localDigestFile.lastModified();
				if(digestTS != 0L)
				{
					long digestAge = System.currentTimeMillis() - digestTS;
					if(digestAge < m_maxDigestAge)
						return false;
					localDigest = readLocalDigest(localDigestFile);
				}
				else
				{
					// No local digest file exists. Calculate digest from the local file
					//
					localDigest = calculateDigest(localFile);
					localDigestCalculated = true;
				}

				// The local digest is either calculated or too old to be "trusted". Let's read it
				// from the remote source and verify that it hasn't changed
				//
				remoteDigest = readRemoteDigest();
				if(Arrays.equals(remoteDigest, localDigest))
				{
					if(localDigestCalculated)
						writeLocalDigest(localDigestFile, localDigest);
					else
						localDigestFile.setLastModified(System.currentTimeMillis());
					return false;
				}

				if(checkOnly)
					return true;
			}
			else
			{
				if(checkOnly)
					return true;

				remoteDigest = readRemoteDigest();				
			}

			MonitorUtils.worked(monitor, 100);	
			File tempFile = new File(localFile.getPath() + ".tmp"); //$NON-NLS-1$

			IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 800);
			for(int idx = 0;; ++idx)
			{
				try
				{
					localDigest = readRemoteFile(remoteFile, tempFile, fiHandle, subMon);
				}
				catch(CoreException e)
				{
					tempFile.delete();
					if(idx < MAX_RETRIES)
					{
						Throwable cause = e.getStatus().getException();
						if(cause instanceof ConnectException)
						{
							try
							{
								Thread.sleep(3000);
							}
							catch(InterruptedException e1)
							{
							}
							subMon = MonitorUtils.subMonitor(monitor, 100 / (MAX_RETRIES - 1));
							continue;
						}
					}
					throw e;
				}
	
				if(Arrays.equals(remoteDigest, localDigest))
				{
					// File transfer was successful
					//
					safeRename(tempFile, localFile);
					writeLocalDigest(localDigestFile, localDigest);
					return true;
				}
	
				tempFile.delete();
				if(idx < MAX_RETRIES)
					continue;
	
				throw BuckminsterException.fromMessage(NLS.bind(Messages.digest_mismatch_reading_0, remoteFile));
			}
		}
		finally
		{
			MonitorUtils.done(monitor);
		}
	}

	protected byte[] calculateDigest(File content) throws CoreException
	{
		InputStream input = null;
		MessageDigest md = getDigest();
		try
		{
			byte[] buf = new byte[8192];
			int count;
			input = new FileInputStream(content);
			while((count = input.read(buf)) >= 0)
				md.update(buf, 0, count);
			return md.digest();
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	protected MessageDigest getDigest() throws CoreException
	{
		try
		{
			return MessageDigest.getInstance(m_algorithm);
		}
		catch(NoSuchAlgorithmException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	protected byte[] readLocalDigest(File localDigestFile) throws CoreException
	{
		InputStream input = null;
		try
		{
			input = new FileInputStream(localDigestFile);
			return Hex.readHex(localDigestFile.getPath(), input, m_digestLength);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}

	protected byte[] readRemoteDigest() throws CoreException, FileNotFoundException
	{
		FileReader reader = new FileReader(m_connectContext);
		BytesFromHexBuilder digestByteBuilder = new BytesFromHexBuilder(m_digestLength);
		reader.readInto(m_remoteDigest, digestByteBuilder, null);
		return digestByteBuilder.getBytes();
	}

	protected byte[] readRemoteFile(URL url, File localFile, IFileInfo[] fiHandle, IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		// Set up the file transfer
		//
		MessageDigest md = getDigest();
		DigestOutputStream output = null;
		try
		{
			File parentFolder = localFile.getParentFile();
			if(parentFolder != null)
				parentFolder.mkdirs();
			output = new DigestOutputStream(new FileOutputStream(localFile), md);
			FileReader retriever = new FileReader(m_connectContext);
			retriever.readInto(url, output, monitor);
			IFileInfo fi = retriever.getLastFileInfo();
			saveLocalFileInfo(url, fi);
			if(fiHandle != null)
				fiHandle[0] = fi;
		}
		finally
		{
			IOUtils.close(output);
		}
		return md.digest();
	}

	protected void writeLocalDigest(File localDigestFile, byte[] localDigest) throws CoreException
	{
		OutputStream output = null;
		try
		{
			File parentFolder = localDigestFile.getParentFile();
			if(parentFolder != null)
				parentFolder.mkdirs();
			output = new FileOutputStream(localDigestFile);
			Hex.writeHex(localDigest, output);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(output);
		}
	}

	protected File getLocalDigest(File localFile)
	{
		return new File(localFile.getPath() + '.' + m_algorithm.toLowerCase());
	}
}
