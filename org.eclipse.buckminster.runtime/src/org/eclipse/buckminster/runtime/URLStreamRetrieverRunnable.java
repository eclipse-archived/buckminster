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
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
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

	URLStreamRetrieverRunnable(URL url)
	{
		super("URLStreamRetriever");
		if(url == null)
			//
			// Rather have this here than when the tread is run.
			//
			throw new NullPointerException();

		m_url = url;
	}

	public void run()
	{
		try
		{
			m_stream = getConnectionProvider(m_url).openConnection(m_url).getInputStream();
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

	InputStream handOverStream() throws IOException
	{
		if(m_thrownException != null)
		{
			IOException ioe = null;
			if(m_thrownException instanceof IOException)
				ioe = (IOException)m_thrownException;
			else
			{
				ioe = new IOException(m_thrownException.getMessage());
				ioe.initCause(m_thrownException);
			}
			throw ioe;
		}

		InputStream stream = m_stream;
		if(stream == null)
			throw new IOException("No stream retrieved");
		m_stream = null;
		return stream;
	}
}