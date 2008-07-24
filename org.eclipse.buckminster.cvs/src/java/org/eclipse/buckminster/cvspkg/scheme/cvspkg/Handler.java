/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cvspkg.scheme.cvspkg;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.osgi.service.url.AbstractURLStreamHandlerService;

/**
 * Provides access to single files in a CVS repository using a URL. The URL
 * must be formatted in the following way:<pre>
 * 
 * cvs://[user@]&lt;host&gt;/&lt;path to file&gt;?repository=&lt;repository path&gt;[protocol=&lt;cvs protocol&gt;][#&lt;tag&gt;]
 * 
 * </pre>
 *
 * @author Thomas Hallgren
 */
public class Handler extends AbstractURLStreamHandlerService
{
	public static final String PROTOCOL = "cvs";

	@Override
	public URLConnection openConnection(URL url) throws IOException
	{
		return new CVSConnection(url);
	}

	class CVSConnection extends URLConnection
	{
		private ICatalogReader m_reader;

		private String m_fileName;

		protected CVSConnection(URL entryURL)
		{
			super(entryURL);
		}

		@Override
		public void connect() throws IOException
		{
			if(connected)
				return;

			try
			{
				URI uri = this.getURL().toURI();
				Map<String, String> params = TextUtils.queryAsParameters(uri.getQuery());

				String host = uri.getHost();
				if(host == null)
					throw new MalformedURLException("The host of a cvs URL cannot be empty");

				String rootStr = uri.getPath();
				if(rootStr == null)
					throw new MalformedURLException("The path of a cvs URL cannot be empty");

				IPath rootPath = new Path(rootStr);
				if(!rootPath.isAbsolute())
					throw new MalformedURLException("The path of a cvs URL must be absolute");
				if(rootPath.segmentCount() < 1)
					throw new MalformedURLException("The path of a cvs URL must have at least 1 segment");
				if(rootPath.hasTrailingSeparator())
					throw new MalformedURLException(
						"The path of a cvs URL must not have a trailing separator");

				String moduleStr = uri.getFragment();
				if(moduleStr == null)
					throw new MalformedURLException(
						"A cvs URL must end with #<module path>");

				IPath modulePath = new Path(moduleStr);
				if(!modulePath.isAbsolute())
					throw new MalformedURLException("The module of a cvs URL must be an absolute path");
				if(modulePath.segmentCount() < 1)
					throw new MalformedURLException(
						"The module of a cvs URL must have at least 1 segment");
				if(modulePath.hasTrailingSeparator())
					throw new MalformedURLException(
						"The module of a cvs URL must not have a trailing separator");

				String user = uri.getUserInfo();
				if(user == null)
					user = "anonymous";

				String cvsProto = params.get("method");
				if(cvsProto == null)
					cvsProto = "pserver";

				m_fileName = modulePath.lastSegment();
				modulePath = modulePath.removeLastSegments(1);
				StringBuilder bld = new StringBuilder();
				bld.append(':');
				bld.append(cvsProto);
				bld.append(':');
				bld.append(user);
				bld.append('@');
				bld.append(host);
				bld.append(':');
				bld.append(rootPath.toPortableString());
				if(modulePath.segmentCount() > 0)
				{
					bld.append(',');
					bld.append(modulePath.toPortableString());
				}

				CorePlugin plugin = CorePlugin.getDefault();
				String versionSelector = params.get("version");
				IReaderType cvsReaderType = plugin.getReaderType("cvs");
				VersionMatch vm = versionSelector == null ? null : new VersionMatch(null, VersionSelector.fromString(versionSelector), null, -1, null, null);
				IProgressMonitor nullMon = new NullProgressMonitor();
				Provider provider = new Provider("cvs", new String[] { IComponentType.UNKNOWN }, bld.toString(), null);
				ComponentQueryBuilder cqBld = new ComponentQueryBuilder();
				cqBld.setRootRequest(new ComponentRequest(m_fileName, null, null));
				if(vm == null)
					vm = VersionMatch.DEFAULT;
				m_reader = (ICatalogReader)cvsReaderType.getReader(provider, plugin.getComponentType(IComponentType.UNKNOWN), new ResolutionContext(
					cqBld.createComponentQuery()).getRootNodeQuery(), vm, nullMon);
			}
			catch(URISyntaxException e)
			{
				throw new MalformedURLException(e.getMessage());
			}
			catch(IllegalArgumentException e)
			{
				throw new MalformedURLException(e.getMessage());
			}
			catch(IOException e)
			{
				throw e;
			}
			catch(Exception e)
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
					public InputStream consumeStream(IComponentReader reader, String streamName,
						InputStream stream, IProgressMonitor monitor) throws IOException
					{
						final AccessibleByteArrayOutputStream builder = new AccessibleByteArrayOutputStream();
						FileUtils.copyFile(stream, builder, monitor);
						return new FilterInputStream(builder.getInputStream())
						{
							@Override
							public void close() throws IOException
							{
								try
								{
									super.close();
								}
								finally
								{
									m_reader.close();
								}
							}
						};
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
