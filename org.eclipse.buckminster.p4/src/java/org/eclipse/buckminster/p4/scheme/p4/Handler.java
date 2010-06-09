/*******************************************************************************
 * Copyright (c) 2004 - 2007
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
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.p4.Messages;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.osgi.service.url.AbstractURLStreamHandlerService;

/**
 * @author Thomas Hallgren
 */
public class Handler extends AbstractURLStreamHandlerService
{
	class PerforceConnection extends URLConnection
	{
		private ICatalogReader m_reader;

		private String m_fileName;

		protected PerforceConnection(URL entryURL)
		{
			super(entryURL);
		}

		@Override
		public void connect() throws IOException
		{
			if(connected)
				return;

			CorePlugin plugin = CorePlugin.getDefault();
			try
			{
				URI uri = this.getURL().toURI();
				IPath path = new Path(uri.getPath());

				if(path.segmentCount() < 2)
					throw new MalformedURLException(Messages.the_path_of_a_p4_URL_must_have_at_least_2_segments);
				if(path.hasTrailingSeparator())
					throw new MalformedURLException(Messages.the_path_of_a_p4_URL_must_not_have_a_trailing_separator);

				URI parentUri = new URI(uri.getScheme(), uri.getHost(), path.removeLastSegments(1).toPortableString(),
						uri.getFragment());
				m_fileName = path.lastSegment();

				Provider provider = new Provider(
						"p4", new String[] { IComponentType.UNKNOWN }, parentUri.toString(), null); //$NON-NLS-1$
				IReaderType p4ReaderType = plugin.getReaderType("p4"); //$NON-NLS-1$
				IProgressMonitor nullMon = new NullProgressMonitor();
				ComponentQueryBuilder cqBld = new ComponentQueryBuilder();
				cqBld.setRootRequest(new ComponentRequest(m_fileName, null, null));
				m_reader = (ICatalogReader)p4ReaderType.getReader(provider, plugin
						.getComponentType(IComponentType.UNKNOWN), new ResolutionContext(cqBld.createComponentQuery())
						.getRootNodeQuery(), VersionMatch.DEFAULT, nullMon);
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
		public InputStream getInputStream() throws IOException
		{
			this.connect();
			try
			{
				return m_reader.readFile(m_fileName, new IStreamConsumer<InputStream>()
				{
					public InputStream consumeStream(IComponentReader reader, String streamName, InputStream stream,
							IProgressMonitor monitor) throws IOException
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

	public static final String PROTOCOL = "p4"; //$NON-NLS-1$

	@Override
	public URLConnection openConnection(URL url) throws IOException
	{
		return new PerforceConnection(url);
	}
}
