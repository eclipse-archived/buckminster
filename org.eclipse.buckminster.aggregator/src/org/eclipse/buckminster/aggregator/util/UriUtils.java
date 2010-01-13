/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.p2.repository.RepositoryTransport;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Filip Hrbek
 * 
 */
public class UriUtils
{
	private static final DocumentBuilderFactory s_documentBuilderFactory = DocumentBuilderFactory.newInstance();

	static
	{
		s_documentBuilderFactory.setIgnoringComments(true);
		s_documentBuilderFactory.setValidating(false);
		s_documentBuilderFactory.setNamespaceAware(false);
	}

	/**
	 * Pattern string that matches links that are relative, don't start with '?' or '#' and don't contain a slash (i.e.
	 * folders leading too deep)
	 */
	private static final String s_linkPatternString = "([^?/#][^:\"/]+/?)";

	/**
	 * Pattern that scans for hrefs that are relative, don't start with '?' and don't contain a slash (i.e. folders
	 * leading too deep)
	 */
	private static final Pattern s_htmlPattern = Pattern.compile(
			"<A\\s+HREF=\"" + s_linkPatternString + "\"\\s*>[^<]+</A>", //$NON-NLS-1$
			Pattern.CASE_INSENSITIVE);

	/**
	 * Pattern that scans for links that are relative, don't start with '?' and don't contain a slash (i.e. folders
	 * leading too deep)
	 */
	private static final Pattern s_linkPattern = Pattern.compile(s_linkPatternString, 
			Pattern.CASE_INSENSITIVE);

	/**
	 * Scan a listing obtained using FTP. The file name comes after a timestamp that ends with <hh:mm> or <year> and
	 * might contain a link, i.e. xxx -> yyy.
	 */
	private static final Pattern s_ftpPattern = Pattern.compile(
			"[a-z]+\\s+[0-9]+\\s+(?:(?:[0-9]+:[0-9]+)|(?:[0-9]{4}))\\s+(.+?)(?:([\\r|\\n])|(\\s+->\\s+))", //$NON-NLS-1$
			Pattern.CASE_INSENSITIVE);

	/**
	 * Check if pattern matches an index.html or other index.xxx. We transform such URL's to denote folders instead.
	 */
	private static final Pattern s_indexPath = Pattern.compile("^(.*/)?index\\.[a-z][a-z0-9]+$"); //$NON-NLS-1$

	public static final URI[] EMPTY_URI_ARRAY = new URI[0];

	/**
	 * Appends a trailing slash to <code>uri</code> and returns the result. If the <code>uri</code> already has a
	 * trailing slash, the argument is returned without modification.
	 * 
	 * @param uri
	 *            The <code>uri</code> that should receive the trailing slash. Cannot be <code>null</code>.
	 * @return A <code>uri</code> that has a trailing slash
	 */
	public static URI appendTrailingSlash(URI uri)
	{
		if(!uri.getPath().endsWith("/")) //$NON-NLS-1$
		{
			try
			{
				uri = new URI(uri.getScheme(), uri.getAuthority(), uri.getPath() + '/', uri.getQuery(),
						uri.getFragment());
			}
			catch(RuntimeException e)
			{
				throw e;
			}
			catch(Exception e)
			{
				// Not very likely since original was a URL.
				//
				throw new RuntimeException(e);
			}
		}
		return uri;
	}

	public static URI[] extractHTMLLinks(URI uriToHTML, IProgressMonitor monitor) throws CoreException
	{
		ArrayList<URI> links = new ArrayList<URI>();
		try
		{
			AccessibleByteArrayOutputStream buffer = new AccessibleByteArrayOutputStream(0x2000, 0x200000);
			RepositoryTransport.getInstance().download(uriToHTML, buffer, monitor);
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
				source.setSystemId(uriToHTML.toString());
				Document document = builder.parse(source);
				collectLinks(document.getDocumentElement(), uriToHTML, links);
			}
			catch(SAXException e)
			{
				// HTML was not well formed. Use a scanner instead
				//
				Scanner scanner = new Scanner(buffer.getInputStream());
				URI parent = appendTrailingSlash(uriToHTML);
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
			Buckminster.getLogger().warning(e, e.getMessage());
			return EMPTY_URI_ARRAY;
		}
		catch(FileNotFoundException e)
		{
			return EMPTY_URI_ARRAY;
		}
		catch(IOException e)
		{
			Buckminster.getLogger().warning(e, e.getMessage());
			return EMPTY_URI_ARRAY;
		}
		catch(ParserConfigurationException e)
		{
			Buckminster.getLogger().warning(e, e.getMessage());
			return EMPTY_URI_ARRAY;
		}
		catch(URISyntaxException e)
		{
			Buckminster.getLogger().warning(e, e.getMessage());
			return EMPTY_URI_ARRAY;
		}
		return links.toArray(new URI[links.size()]);
	}

	public static File getFile(URI uri) throws MalformedURLException
	{
		if(uri == null)
			return null;

		String proto = uri.toURL().getProtocol();
		if(proto == null)
		{
			return new File(uri);
		}
		else if("file".equalsIgnoreCase(proto)) //$NON-NLS-1$
		{
			String path = uri.toString().replace("file:", "");
			return new File(path);
		}
		return null;
	}

	public static URI[] list(URI uri, IProgressMonitor monitor) throws CoreException
	{
		File dir = null;

		try
		{
			dir = getFile(uri);
		}
		catch(MalformedURLException e)
		{
			Buckminster.getLogger().warning(e, e.getMessage());
			return EMPTY_URI_ARRAY;
		}

		if(dir != null)
		{
			File[] list = dir.listFiles();
			if(list == null)
				return EMPTY_URI_ARRAY;
			int top = list.length;
			if(top == 0)
				return EMPTY_URI_ARRAY;
			URI[] result = new URI[top];
			while(--top >= 0)
			{
				File file = list[top];
				URI fileUri = URLUtils.normalizeToURI(file.toString(), file.isDirectory());
				result[top] = fileUri;
			}
			MonitorUtils.complete(monitor);
			return result;
		}

		String proto = null;
		try
		{
			proto = uri.toURL().getProtocol();
		}
		catch(MalformedURLException e)
		{
			Buckminster.getLogger().warning(e, e.getMessage());
			return EMPTY_URI_ARRAY;
		}

		if(proto.equalsIgnoreCase("ftp") || proto.equalsIgnoreCase("sftp")) //$NON-NLS-1$ //$NON-NLS-2$
		{
			final ArrayList<URI> result = new ArrayList<URI>();
			Scanner scanner = null;
			try
			{
				scanner = new Scanner(RepositoryTransport.getInstance().stream(uri, monitor));
				uri = appendTrailingSlash(uri);
				while(scanner.findWithinHorizon(s_ftpPattern, 0) != null)
				{
					MatchResult mr = scanner.match();
					result.add(new URI(uri.getScheme(), uri.getAuthority(), uri.getPath() + '/' + mr.group(1),
							uri.getQuery(), uri.getFragment()));
				}
				return result.toArray(new URI[result.size()]);
			}
			catch(CoreException e)
			{
				Buckminster.getLogger().warning(e, e.getMessage());
				return EMPTY_URI_ARRAY;
			}
			catch(FileNotFoundException e)
			{
				return EMPTY_URI_ARRAY;
			}
			catch(IOException e)
			{
				Buckminster.getLogger().warning(e, e.getMessage());
				return EMPTY_URI_ARRAY;
			}
			catch(URISyntaxException e)
			{
				Buckminster.getLogger().warning(e, e.getMessage());
				return EMPTY_URI_ARRAY;
			}
			finally
			{
				if(scanner != null)
					scanner.close();
			}
		}
		return extractHTMLLinks(uri, monitor);
	}

	private static void addLink(List<URI> links, URI parent, String link) throws URISyntaxException
	{
		Matcher m = s_indexPath.matcher(link.toString());
		if(m.matches())
		{
			link = m.group(1);
			if(link == null)
				return;
		}

		if(link.equals("../")) //$NON-NLS-1$
			return;

		links.add(new URI(parent.getScheme(), parent.getAuthority(), parent.getPath() + link, parent.getQuery(),
				parent.getFragment()));
	}

	private static void collectLinks(Element element, URI parent, ArrayList<URI> links) throws URISyntaxException
	{
		if(element.getNodeName().equals("a")) //$NON-NLS-1$
		{
			String link = element.getAttribute("href"); //$NON-NLS-1$
			if(s_linkPattern.matcher(link).matches())
				addLink(links, parent, link);
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
