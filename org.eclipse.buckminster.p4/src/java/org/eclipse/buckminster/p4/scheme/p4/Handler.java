/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.scheme.p4;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.reader.URLReaderType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.osgi.service.url.AbstractURLStreamHandlerService;

/**
 * @author thhal
 */
public class Handler extends AbstractURLStreamHandlerService 
{
	public static final String PROTOCOL = "p4";

	@Override
	public URLConnection openConnection(URL url)
	throws IOException
	{
		return new PerforceConnection(url);
	}

	class PerforceConnection extends URLConnection
	{
		private ICatalogReader m_reader;
		private String m_fileName;

		protected PerforceConnection(URL entryURL)
		{
			super(entryURL);
		}

		@Override
		public void connect()
		throws IOException
		{
			if(connected)
				return;

			CorePlugin plugin = CorePlugin.getDefault();
			try
			{
				URI uri = this.getURL().toURI();
				IPath path = new Path(uri.getPath());

				if(path.segmentCount() < 2)
					throw new MalformedURLException("The path of a p4 URL must have at least 2 segments");
				if(path.hasTrailingSeparator())
					throw new MalformedURLException("The path of a p4 URL must not have a trailing separator");

				URI parentUri = new URI(uri.getScheme(), uri.getHost(), path.removeLastSegments(1).toPortableString(), uri.getFragment());
				m_fileName = path.lastSegment();

				Provider provider = new Provider("p4", "unknown", null, null, new Format(parentUri.toString()), false, false, null);
				IReaderType p4ReaderType = plugin.getReaderType("p4");
				ProviderMatch ri = URLReaderType.getCurrentProviderMatch();
				IProgressMonitor nullMon = new NullProgressMonitor();
				if(ri == null)
				{
					ComponentQueryBuilder cqBld = new ComponentQueryBuilder();
					cqBld.setRootRequest(new ComponentRequest(m_fileName, null, null));
					m_reader = (ICatalogReader)p4ReaderType.getReader(
						provider,
						new RMContext(cqBld.createComponentQuery()).getRootNodeQuery(),
						VersionMatch.DEFAULT, nullMon);
				}
				else
				{
					m_reader = (ICatalogReader)p4ReaderType.getReader(provider, ri.getNodeQuery(), ri.getVersionMatch(), nullMon);
				}
			}
			catch(URISyntaxException e)
			{
				throw new MalformedURLException(e.getMessage());
			}
			catch(CoreException e)
			{
				Throwable t = e.getCause();
				if(t instanceof IOException)
					throw (IOException)t;
				throw new IOException(e.getMessage());
			}
			connected = true;
		}

		@Override
	    public InputStream getInputStream()
	    throws IOException
		{
	    	this.connect();
			try
			{
				return m_reader.readFile(m_fileName, new IStreamConsumer<InputStream>()
				{
					public InputStream consumeStream(IComponentReader reader, String streamName, InputStream stream, IProgressMonitor monitor)
					throws IOException
					{
						final AccessibleByteArrayOutputStream builder = new AccessibleByteArrayOutputStream();
						FileUtils.copyFile(stream, builder, monitor);
						return builder.getInputStream();
					}
				}, new NullProgressMonitor());
			}
			catch(CoreException e)
			{
				Throwable t = e.getCause();
				if(t instanceof IOException)
					throw (IOException)t;
				throw new IOException(e.getMessage());
			}
	    }
	}
}

