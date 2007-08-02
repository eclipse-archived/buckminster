/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

/**
 * @author ken1
 */
class URLStreamRetrieverRunnable extends Thread
{
	private static Map s_providers;

	private final URL m_url;

	private InputStream m_stream;

	private Throwable m_thrownException;

	private FileInfoBuilder m_fileInfo;

	private static synchronized IURLConnectionProvider getConnectionProvider(URL url)
	throws InvalidRegistryObjectException, CoreException
	{
		if(s_providers == null)
		{
			IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(
					IURLConnectionProvider.URL_CONNECTION_PROVIDERS_POINT);
			int idx = elems.length;
			if(idx == 0)
			{
				s_providers = Collections.EMPTY_MAP;
				return DefaultURLConnectionProvider.INSTANCE;
			}
			s_providers = new HashMap();
			while(--idx >= 0)
			{
				IConfigurationElement elem = elems[idx];
				s_providers.put(elem.getAttribute("protocol").trim().toLowerCase(), elem.createExecutableExtension("class"));
			}
		}
		IURLConnectionProvider provider = (IURLConnectionProvider)s_providers.get(url.getProtocol().toLowerCase());
		if(provider == null)
			provider = DefaultURLConnectionProvider.INSTANCE;
		return provider;
	}

	public static void getFileInfo(URLConnection conn, FileInfoBuilder infoBuilder)
	{
		String filename = parseContentDisposition(conn.getHeaderField("Content-Disposition"));
		
		if (filename == null || filename.trim().length() == 0)
		{
			filename = new Path(conn.getURL().getFile()).lastSegment();
		}
		
		infoBuilder.setName(filename);
		infoBuilder.setContentType(conn.getContentType());
		infoBuilder.setSize(conn.getContentLength());
	}

	URLStreamRetrieverRunnable(URL url)
	{
		super("URLStreamRetriever");
		if(url == null)
			//
			// Rather have this here than when the tread is run.
			//
			throw new NullPointerException();

		m_url = url;
		m_fileInfo = new FileInfoBuilder();
	}

	public void run()
	{
		try
		{
			URLConnection conn = getConnectionProvider(m_url).openConnection(m_url);
			getFileInfo(conn, m_fileInfo);
			m_stream = conn.getInputStream();
		}
		catch(Throwable t)
		{
			m_thrownException = t;
		}
	}

	void cleanUp()
	{
		if(isAlive())
			interrupt();
		IOUtils.close(m_stream);
		m_stream = null;
	}

	IFileInfo getFileInfo()
	{
		return m_fileInfo;
	}

	InputStream handOverStream() throws IOException
	{
		if(m_thrownException != null)
		{
			// Always throw a new exception here since the m_thrownException
			// stems from another thread. If we don't, we will loose the
			// stacktrace of the thread that is current now.
			//
			String msg = "Unable to open URL " + m_url;
			IOException ioe = null;
			if(m_thrownException instanceof IOException)
			{
				try
				{
					Class exClass = m_thrownException.getClass();
					Constructor ctor = exClass.getConstructor(new Class[] { String.class });
					ioe = (IOException)ctor.newInstance(new Object[] { msg });
				}
				catch(Exception e)
				{
				}
			}
			if(ioe == null)
				ioe = new IOException(msg);

			ioe.initCause(m_thrownException);
			throw ioe;
		}

		InputStream stream = m_stream;
		if(stream == null)
			throw new IOException("No stream retrieved");
		m_stream = null;
		return stream;
	}

	/**
	 * This regular expression is a simple Content-Disposition header parser.
	 * Content-Disposition grammar is quite complex, this is really simplified.
	 * It should be refactored in future versions using proper grammar. 
	 */
	private final static Pattern s_contentDispositionPattern = Pattern.compile(
			".*;\\s*filename\\s*=\\s*(\"(?:[^\"\\\\]|\\\\.)*\"|[^;\"\\s]+)\\s*(?:;|$)");

	private static String parseContentDisposition(String contentDisposition)
	{
		//Context-Dispositon syntax: attachment|inline[;filename="<filename>"]
		//Try to extract the filename form it (and strip quotes if they're there)
		
		if (contentDisposition == null)
			return null;
		
		String filename = null;
		Matcher m = s_contentDispositionPattern.matcher(contentDisposition);
		
		if (m.matches())
		{
			filename = m.group(1);
			if (filename.startsWith("\"") && filename.endsWith("\""))
			{
				filename = filename.substring(1, filename.length()-1).replaceAll("\\\\(.)", "$1");
			}
		}
		
		return filename;
	}
}