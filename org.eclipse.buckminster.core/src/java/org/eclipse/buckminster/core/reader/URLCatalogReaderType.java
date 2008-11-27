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
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolutionContext;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.model.ProviderScore;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
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

	/**
	 * Check if pattern matches an index.html or other index.xxx. We transform such URL's to denote folders instead.
	 */
	private static final Pattern s_indexPath = Pattern.compile("^(.*/)?index\\.[a-z][a-z0-9]+$");

	private static final ThreadLocal<ProviderMatch> s_currentProviderMatch = new InheritableThreadLocal<ProviderMatch>();

	static
	{
		s_documentBuilderFactory.setIgnoringComments(true);
		s_documentBuilderFactory.setValidating(false);
		s_documentBuilderFactory.setNamespaceAware(false);
	}

	private static void addLink(List<URL> links, URL parent, String link) throws MalformedURLException
	{
		Matcher m = s_indexPath.matcher(link.toString());
		if(m.matches())
		{
			link = m.group(1);
			if(link == null)
				return;
		}

		if(link.equals("../"))
			return;

		links.add(new URL(parent, link));
	}

	private static void collectLinks(Element element, URL parent, ArrayList<URL> links)
	{
		if(element.getNodeName().equals("a"))
		{
			try
			{
				addLink(links, parent, element.getAttribute("href"));
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

	public static URL[] extractHTMLLinks(URL urlToHTML, IConnectContext cctx, IProgressMonitor monitor)
			throws CoreException
	{
		ArrayList<URL> links = new ArrayList<URL>();
		try
		{
			AccessibleByteArrayOutputStream buffer = new AccessibleByteArrayOutputStream(0x2000, 0x200000);
			DownloadManager.readInto(urlToHTML, cctx, buffer, monitor);
			try
			{
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
				InputSource source = new InputSource(buffer.getInputStream());
				source.setSystemId(urlToHTML.toString());
				Document document = builder.parse(source);
				collectLinks(document.getDocumentElement(), urlToHTML, links);
			}
			catch(SAXException e)
			{
				// HTML was not well formed. Use a scanner instead
				//
				Scanner scanner = new Scanner(buffer.getInputStream());
				URL parent = URLUtils.appendTrailingSlash(urlToHTML);
				while(scanner.findWithinHorizon(s_htmlPattern, 0) != null)
				{
					MatchResult mr = scanner.match();
					addLink(links, parent, mr.group(1));
				}
				scanner.close();
			}
		}
		catch(IllegalStateException e)
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
		catch(ParserConfigurationException e)
		{
			CorePlugin.getLogger().warning(e, e.getMessage());
			return Trivial.EMPTY_URL_ARRAY;
		}
		return links.toArray(new URL[links.size()]);
	}

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
		Provider provider = new Provider(readerType, new String[] { ctype.getId() }, urlString, null);
		ProviderMatch pm = new ProviderMatch(provider, ctype, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);
		return pm.getReader(monitor);
	}

	public static IComponentReader getReader(URL catalog, IProgressMonitor monitor) throws CoreException
	{
		return getDirectReader(catalog, URL_CATALOG, monitor);
	}

	public static URL[] list(URL url, IConnectContext cctx, IProgressMonitor monitor) throws CoreException
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
			Scanner scanner = null;
			try
			{
				scanner = new Scanner(DownloadManager.read(url, cctx));
				url = URLUtils.appendTrailingSlash(url);
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
				if(scanner != null)
					scanner.close();
			}
		}
		return extractHTMLLinks(url, cctx, monitor);
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

	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException
	{
		try
		{
			return new URI(resolution.getRepository());
		}
		catch(URISyntaxException e)
		{
			return null;
		}
	}

	public IReaderType getLocalReaderType()
	{
		return this;
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new URLCatalogReader(this, providerMatch);
	}

	@Override
	public String getRemotePath(String repositoryLocation) throws CoreException
	{
		return getURI(repositoryLocation).getPath();
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
}
