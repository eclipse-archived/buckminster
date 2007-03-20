/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author thhal
 */
public class URLReaderType extends AbstractReaderType
{
	private static final ThreadLocal<ProviderMatch> s_currentProviderMatch = new InheritableThreadLocal<ProviderMatch>();

	public static IComponentReader getReader(URL externalFile, IProgressMonitor monitor) throws CoreException
	{
		return getDirectReader(externalFile, IReaderType.URL, monitor);
	}

	public static ProviderMatch getCurrentProviderMatch()
	{
		return s_currentProviderMatch.get();
	}

	static IComponentReader getDirectReader(URL url, String readerType, IProgressMonitor monitor) throws CoreException
	{
		String urlString = url.toString();
		ComponentRequest rq = new ComponentRequest(urlString, null, null);
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(rq);
		RMContext context = new RMContext(queryBld.createComponentQuery());
		NodeQuery nq = new NodeQuery(context, rq, null);

		Provider provider = new Provider(readerType, IComponentType.UNKNOWN, null, null, new Format(urlString), false, false, null);
		ProviderMatch pm = new ProviderMatch(provider, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);
		return provider.getReaderType().getReader(pm, monitor);
	}

	@Override
	public URL convertToURL(String repositoryLocator, IVersionSelector versionSelector) throws CoreException
	{
		try
		{
			return URLUtils.normalizeToURL(repositoryLocator);
		}
		catch(MalformedURLException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public IReaderType getLocalReaderType()
	{
		return this;
	}

	@Override
	public IPath getMaterializationLocation(Resolution cr, RMContext context, boolean[] optional) throws CoreException
	{
		try
		{
			optional[0] = true;

			IComponentType cType = cr.getProvider().getComponentType();
			if(cType.hasProjectDescription())
				//
				// Returning null here suggests using the location of the workspace
				//
				return null;

			// a URL with, say, '%20', in it, won't be properly translated to ' ' by the URL class
			// solution: pass it through URI which will do the right thing.
			// Also, run it through java.lang.File to ensure we get native separators - we never know where the
			// path will be used...
			//
			URI uri = new URI(cr.getRepository(context));
			IPath path = new Path(new File(uri.getPath()).getPath());
			return CorePlugin.getDefault().getBuckminsterProjectLocation().append("url-cache").append(path);
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		ProviderMatch oldMatch = s_currentProviderMatch.get();
		s_currentProviderMatch.set(providerMatch);
		try
		{
			URLFileReader reader = new URLFileReader(this, providerMatch);
			if(!reader.exists(monitor))
				throw new FileNotFoundException(reader.getURL().toString());
			return reader;
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			s_currentProviderMatch.set(oldMatch);
		}
	}

	public URI getURI(Provider provider, Map<String,String> properties) throws CoreException
	{
		return getURI(provider.getURI(properties));
	}

	public URI getURI(ProviderMatch providerMatch) throws CoreException
	{
		return getURI(providerMatch.getRepositoryURI());
	}

	public URI getURI(String repository) throws CoreException
	{
		URI uri;
		try
		{
			uri = new URI(repository);
		}
		catch(URISyntaxException e)
		{
			if(repository.indexOf(' ') < 0)
				throw BuckminsterException.wrap(e);

			try
			{
				uri = new URI(repository.replaceAll("\\s", "%20"));
			}
			catch(URISyntaxException e2)
			{
				throw BuckminsterException.wrap(e2);
			}
		}

		String scheme = uri.getScheme();
		String auth = uri.getAuthority();
		String path = uri.getPath();
		String query = uri.getQuery();
		String fragment = uri.getFragment();
		boolean change = false;
		if(!(isFileReader() || path.endsWith("/")))
		{
			path += "/";
			change = true;
		}

		if(scheme == null)
		{
			scheme = "file";
			change = true;
		}

		try
		{
			if(change)
				uri = new URI(scheme, auth, path, query, fragment);
			return uri;
		}
		catch(URISyntaxException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public boolean isFileReader()
	{
		return true;
	}
}
