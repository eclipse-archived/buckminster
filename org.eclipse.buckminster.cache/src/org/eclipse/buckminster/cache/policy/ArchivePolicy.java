/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.cache.policy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.DateUtil;
import org.eclipse.buckminster.cache.ICache;
import org.eclipse.buckminster.cache.download.FileReader;
import org.eclipse.buckminster.cache.unpack.Installer;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 * 
 */
public class ArchivePolicy extends AbstractFetchPolicy
{
	static class RemoteInfo
	{
		long fileLength;
		long lastModified;
	}

	public static final String LAST_MODIFIED_HEADER = "Last-Modified"; //$NON-NLS-1$

	private final HttpClient m_httpClient;
	private final String m_remoteName;

	public ArchivePolicy(ICache cache, String remoteName)
	{
		super(cache);
		m_remoteName = remoteName;
		m_httpClient = new HttpClient(new MultiThreadedHttpConnectionManager());
	}

	public boolean update(URL remoteFile, File localFile, boolean checkOnly, IProgressMonitor monitor)
			throws CoreException
	{
		MonitorUtils.begin(monitor, 1000);
		try
		{
			if(localFile.exists())
			{
				RemoteInfo ri;
				try
				{
					ri = getRemoteInfo(remoteFile);
				}
				catch(FileNotFoundException e)
				{
					localFile.delete();
					throw BuckminsterException.wrap(e);
				}
				catch(IOException e)
				{
					throw BuckminsterException.wrap(e);
				}
				if(ri.fileLength == localFile.length() && ri.lastModified <= localFile.lastModified())
					return false;
			}
			MonitorUtils.worked(monitor, 100);
			if(checkOnly)
				return true;

			File tempFile = new File(localFile.getPath() + ".tmp");
			String fileName = readRemoteFile(remoteFile, tempFile, MonitorUtils.subMonitor(monitor, 800));
			if(m_remoteName != null)
				fileName = m_remoteName;

			try
			{
				Installer.getInstaller(fileName).validate(tempFile, MonitorUtils.subMonitor(monitor, 100));
			}
			catch(CoreException e)
			{
				tempFile.delete();
				throw e;
			}
			safeRename(tempFile, localFile);
			return true;
		}
		finally
		{
			MonitorUtils.done(monitor);
		}
	}

	protected String readRemoteFile(URL url, File localFile, IProgressMonitor monitor) throws CoreException
	{
		// Set up the file transfer
		//
		OutputStream output = null;
		try
		{
			localFile = localFile.getCanonicalFile();
			File parentFolder = localFile.getParentFile();
			if(parentFolder != null)
				parentFolder.mkdirs();
			output = new FileOutputStream(localFile);
			FileReader retriever = new FileReader();
			retriever.readURL(url, output, monitor);
			return retriever.getRemoteName();
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

	private RemoteInfo getRemoteInfo(URL url) throws IOException
	{
		String urlStr = url.toString();
		GetMethod getMethod = new GetMethod(urlStr);
		getMethod.setFollowRedirects(true);

		int code = m_httpClient.executeMethod(getMethod);
		try
		{
			if(code == HttpURLConnection.HTTP_NOT_FOUND)
				throw new FileNotFoundException(urlStr);

			if(!(code == HttpURLConnection.HTTP_PARTIAL || code == HttpURLConnection.HTTP_OK))
				throw new IOException("HTTP response " + code);

			RemoteInfo ri = new RemoteInfo();
			Header lastModifiedHeader = getMethod.getResponseHeader(LAST_MODIFIED_HEADER);
			if(lastModifiedHeader != null)
			{
				String lastModifiedString = lastModifiedHeader.getValue();
				if(lastModifiedString != null)
				{
					try
					{
						ri.lastModified = DateUtil.parseDate(lastModifiedString).getTime();
					}
					catch(Exception e)
					{
					}
				}
			}
			ri.fileLength = getMethod.getResponseContentLength();
			return ri;
		}
		finally
		{
			getMethod.releaseConnection();
		}
	}
}
