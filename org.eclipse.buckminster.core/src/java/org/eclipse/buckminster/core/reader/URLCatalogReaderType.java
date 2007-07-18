/*******************************************************************************
 * Copyright (c) 2004 - 2007
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
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class URLCatalogReaderType extends CatalogReaderType
{
	/**
	 * Pattern that scans for href's that are relative and don't start with ?
	 */
	private static final Pattern s_htmlPattern = Pattern.compile("<A\\s+HREF=\"([^?/][^:\"]+)\"\\s*>[^<]+</A>",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Scan a listing obtained using FTP. The file name comes after a timestamp
	 * that ends with <hh:mm> or <year> and might contain a link, i.e. xxx ->
	 * yyy.
	 */
	private static final Pattern s_ftpPattern = Pattern.compile(
			"[a-z]+\\s+[0-9]+\\s+(?:(?:[0-9]+:[0-9]+)|(?:[0-9]{4}))\\s+(.+?)(?:([\\r|\\n])|(\\s+->\\s+))",
			Pattern.CASE_INSENSITIVE);
	private static final ThreadLocal<ProviderMatch> s_currentProviderMatch = new InheritableThreadLocal<ProviderMatch>();

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
		ResolutionContext context = new ResolutionContext(queryBld.createComponentQuery());
		NodeQuery nq = new NodeQuery(context, rq, null);

		IComponentType ctype = CorePlugin.getDefault().getComponentType(IComponentType.UNKNOWN);
		Provider provider = new Provider(readerType, new String[] { ctype.getId() }, null, new Format(urlString), null, false, false, null);
		ProviderMatch pm = new ProviderMatch(provider, ctype, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);
		return provider.getReaderType().getReader(pm, monitor);
	}

	@Override
	public URL convertToURL(String repositoryLocator, VersionMatch versionSelector) throws CoreException
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
		return URLUtils.normalizeToURI(repository, true);
	}

	@Override
	public String getRemotePath(String repositoryLocation) throws CoreException
	{
		return getURI(repositoryLocation).getPath();
	}

	public static IComponentReader getReader(URL catalog, IProgressMonitor monitor) throws CoreException
	{
		return getDirectReader(catalog, URL_CATALOG, monitor);
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new URLCatalogReader(this, providerMatch);
	}

	public static IPath[] list(final URL url, IProgressMonitor monitor)
	{
		File dir = FileUtils.getFile(url);
		if(dir != null)
		{
			File[] list = dir.listFiles();
			if(list == null)
				return Trivial.EMPTY_PATH_ARRAY;
			int top = list.length;
			if(top == 0)
				return Trivial.EMPTY_PATH_ARRAY;
			IPath[] result = new IPath[top];
			while(--top >= 0)
			{
				File file = list[top];
				IPath path = new Path(list[top].getName());
				if(file.isDirectory())
					path = path.addTrailingSeparator();
				result[top] = path;
			}
			return result;
		}

		final ArrayList<IPath> result = new ArrayList<IPath>();
		InputStream pageSource = null;
		try
		{
			pageSource = CorePlugin.getDefault().openCachedURL(url, monitor);
			Pattern scanPattern;
			if(url.getProtocol().equals("ftp"))
				scanPattern = s_ftpPattern;
			else
				// Assume output is html
				//
				scanPattern = s_htmlPattern;

			Scanner scanner = new Scanner(pageSource);
			while(scanner.findWithinHorizon(scanPattern, 0) != null)
			{
				MatchResult mr = scanner.match();
				result.add(new Path(mr.group(1)));
			}
			return result.toArray(new IPath[result.size()]);
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().warning(e.getMessage(), e);
			return Trivial.EMPTY_PATH_ARRAY;
		}
		catch(FileNotFoundException e)
		{
			return Trivial.EMPTY_PATH_ARRAY;
		}
		catch(IOException e)
		{
			CorePlugin.getLogger().warning(e.getMessage(), e);
			return Trivial.EMPTY_PATH_ARRAY;
		}
		finally
		{
			IOUtils.close(pageSource);
		}
	}
}
