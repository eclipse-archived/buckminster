/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.runtime;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.buckminster.runtime.internal.DefaultCertificateTrustInquiry;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public abstract class URLUtils
{
	private static ICertificateTrustInquiry s_trustInquiry = new DefaultCertificateTrustInquiry();

	public static ICertificateTrustInquiry getTrustInquiry()
	{
		return s_trustInquiry;
	}

	public static boolean isLocalURL(URL url)
	{
		String proto = url.getProtocol();
		if(proto.equals("jar") || proto.equals("reference"))
		{
			String spec = url.getFile();
			int sepIdx = spec.indexOf(':');
			if(sepIdx == -1)
				return false;
			proto = spec.substring(0, sepIdx);
		}
		return "file".equals(proto) || "platform".equals(proto) || proto.startsWith("bundle");
	}

	/**
	 * This is a recommended way to retrieve the input stream for a generic URL. Any code doing
	 * 'url.openStream()', should instead do 'URLUtils.openStream(url, <monitor-or-null>). There are
	 * two benefits: 1) if a monitor is used, the action is cancellable. 2) it allows us to unify
	 * behavior in the presence of certain URL protols (e.g. https => may need to handle SSL
	 * certificates)
	 * @param url the url to get the input stream from
	 * @param monitor a monitor to report progress to and check for cancellation. Can be null.
	 */
	public static InputStream openStream(URL url, IProgressMonitor monitor) throws IOException
	{
		return openStream(url, monitor, null);
	}

	/**
	 * This is a recommended way to retrieve the input stream for a generic URL. Any code doing
	 * 'url.openStream()', should instead do 'URLUtils.openStream(url, <monitor-or-null>). There are
	 * two benefits: 1) if a monitor is used, the action is cancellable. 2) it allows us to unify
	 * behavior in the presence of certain URL protols (e.g. https => may need to handle SSL
	 * certificates)
	 * @param url the url to get the input stream from
	 * @param monitor a monitor to report progress to and check for cancellation. Can be null.
	 * @param fileInfo file info to set (if available)
	 */
	public static InputStream openStream(URL url, IProgressMonitor monitor, FileInfoBuilder fileInfo) throws IOException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);
		URLStreamRetrieverRunnable usrr = new URLStreamRetrieverRunnable(url);
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try
		{
			usrr.setDaemon(true);
			usrr.start();
			while(usrr.isAlive())
			{
				usrr.join(200);
    			MonitorUtils.worked(monitor, 1);
			}
			
			if (usrr.getFileInfo() != null && fileInfo != null)
				fileInfo.setAll(usrr.getFileInfo());
			
			return usrr.handOverStream();
		}
		catch(InterruptedException e)
		{
			throw new IOException("Interrupted");
		}
		finally
		{
			usrr.cleanUp();
			monitor.done();
		}
	}

	public static URL normalizeToURL(String surl) throws MalformedURLException
	{
		if(surl == null)
			return null;

		try
		{
			return new URL(surl);
		}
		catch(MalformedURLException e)
		{
			// Do a space check.
			//
			if(surl.indexOf(' ') > 0)
			{
				try
				{
					return new URL(surl.replaceAll("\\s", "%20"));
				}
				catch(MalformedURLException me1)
				{
				}
			}

			try
			{
				return new File(surl).toURI().toURL();
			}
			catch(MalformedURLException me2)
			{
				// Throw the original exception
				//
				throw e;
			}
		}
	}

	public static void setTrustInquiry(ICertificateTrustInquiry trustInquiry)
	{
		if(trustInquiry == null)
			trustInquiry = new DefaultCertificateTrustInquiry();
		s_trustInquiry = trustInquiry;
	}
}
