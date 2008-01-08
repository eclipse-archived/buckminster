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

import java.io.BufferedInputStream;
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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.materializer.IMaterializer;
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
import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class URLCatalogReaderType extends CatalogReaderType
{
	private static final DocumentBuilderFactory s_documentBuilderFactory = DocumentBuilderFactory.newInstance();

	static
	{
		s_documentBuilderFactory.setIgnoringComments(true);
		s_documentBuilderFactory.setValidating(false);
		s_documentBuilderFactory.setNamespaceAware(false);
	}

	/**
	 * Pattern that scans for href's that are relative and don't start with ?
	 */
	private static final Pattern s_htmlPattern = Pattern.compile("<A\\s+HREF=\"([^?/][^:\"]+)\"\\s*>[^<]+</A>",
			Pattern.CASE_INSENSITIVE);

	/**
	 * Scan a listing obtained using FTP. The file name comes after a timestamp that ends with <hh:mm> or <year> and
	 * might contain a link, i.e. xxx -> yyy.
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
		ComponentRequest rq = new ComponentRequest(urlString, null, null, null);
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(rq);
		queryBld.setPlatformAgnostic(true);
		ResolutionContext context = new ResolutionContext(queryBld.createComponentQuery());
		NodeQuery nq = new NodeQuery(context, rq, null);

		IComponentType ctype = CorePlugin.getDefault().getComponentType(IComponentType.UNKNOWN);
		Provider provider = new Provider(readerType, new String[] { ctype.getId() }, urlString);
		ProviderMatch pm = new ProviderMatch(provider, ctype, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);
		return pm.getReader(monitor);
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

	public URI getURI(Provider provider, Map<String, String> properties) throws CoreException
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

	@Override
	public String getRecommendedMaterializer()
	{
		return IMaterializer.FILE_SYSTEM;
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

	public static URL[] extractHTMLLinks(URL urlToHTML, IProgressMonitor monitor) throws CoreException
	{
		ArrayList<URL> links = new ArrayList<URL>();
		InputStream pageSource = null;
		try
		{
			try
			{
				pageSource = CorePlugin.getDefault().openCachedURL(urlToHTML, monitor);
				final DocumentBuilder builder = s_documentBuilderFactory.newDocumentBuilder();
				
				// Use a very silent error handler
				//
				builder.setErrorHandler(new ErrorHandler()
				{
					public void error(SAXParseException ex) throws SAXException
					{
						throw ex;
					}
					public void fatalError(SAXParseException ex) throws SAXException
					{
						throw ex;
					}
					public void warning(SAXParseException ex) throws SAXException
					{
					}
				});
				InputSource source = new InputSource(new BufferedInputStream(pageSource));
				source.setSystemId(urlToHTML.toString());
				Document document = builder.parse(source);
				collectLinks(document.getDocumentElement(), urlToHTML, links);
			}
			catch(SAXException e)
			{
				// HTML was not well formed. Use a scanner instead
				//
				pageSource = CorePlugin.getDefault().openCachedURL(urlToHTML, monitor);
				Scanner scanner = new Scanner(pageSource);
				URL parent = URLUtils.appendTrailingSlash(urlToHTML);
				while(scanner.findWithinHorizon(s_htmlPattern, 0) != null)
				{
					MatchResult mr = scanner.match();
					links.add(new URL(parent, mr.group(1)));
				}
			}
		}
		catch(FileNotFoundException e)
		{
			return Trivial.EMPTY_URL_ARRAY;
		}
		catch(IOException e)
		{
			CorePlugin.getLogger().warning(e, e.getMessage());
			return Trivial.EMPTY_URL_ARRAY;
		}
		catch(ParserConfigurationException e)
		{
			CorePlugin.getLogger().warning(e, e.getMessage());
			return Trivial.EMPTY_URL_ARRAY;
		}
		return links.toArray(new URL[links.size()]);
	}

	public static URL[] list(URL url, IProgressMonitor monitor) throws CoreException
	{
		File dir = FileUtils.getFile(url);
		if(dir != null)
		{
			File[] list = dir.listFiles();
			if(list == null)
				return Trivial.EMPTY_URL_ARRAY;
			int top = list.length;
			if(top == 0)
				return Trivial.EMPTY_URL_ARRAY;
			URL[] result = new URL[top];
			while(--top >= 0)
			{
				File file = list[top];
				URI uri = URLUtils.normalizeToURI(file.toString(), file.isDirectory());
				try
				{
					result[top] = uri.toURL();
				}
				catch(MalformedURLException e)
				{
					throw BuckminsterException.wrap(e);
				}
			}
			MonitorUtils.complete(monitor);
			return result;
		}

		String proto = url.getProtocol();
		if(proto.equalsIgnoreCase("ftp") || proto.equalsIgnoreCase("sftp"))
		{
			final ArrayList<URL> result = new ArrayList<URL>();
			InputStream pageSource = null;
			try
			{
				pageSource = CorePlugin.getDefault().openCachedURL(url, monitor);
				url = URLUtils.appendTrailingSlash(url);
				Scanner scanner = new Scanner(pageSource);
				while(scanner.findWithinHorizon(s_ftpPattern, 0) != null)
				{
					MatchResult mr = scanner.match();
					result.add(new URL(url, mr.group(1)));
				}
				return result.toArray(new URL[result.size()]);
			}
			catch(CoreException e)
			{
				CorePlugin.getLogger().warning(e, e.getMessage());
				return Trivial.EMPTY_URL_ARRAY;
			}
			catch(FileNotFoundException e)
			{
				return Trivial.EMPTY_URL_ARRAY;
			}
			catch(IOException e)
			{
				CorePlugin.getLogger().warning(e, e.getMessage());
				return Trivial.EMPTY_URL_ARRAY;
			}
			finally
			{
				IOUtils.close(pageSource);
			}
		}
		return extractHTMLLinks(url, monitor);
	}

	private static void collectLinks(Element element, URL parent, ArrayList<URL> links)
	{
		if(element.getNodeName().equals("a"))
		{
			try
			{
				links.add(new URL(parent, element.getAttribute("href")));
			}
			catch(MalformedURLException e)
			{
				// Invalid href. Just skip it.
			}
		}
		else
		{
			for(Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
			{
				if(child.getNodeType() == Node.ELEMENT_NODE)
					collectLinks((Element)child, parent, links);
			}
		}
	}
}
