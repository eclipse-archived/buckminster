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
import java.util.Date;
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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
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
public class URLCatalogReaderType extends CatalogReaderType {
	private class LastModficationTimeFinder implements IResourceVisitor {
		long timestamp = -1;

		@Override
		public boolean visit(IResource resource) throws CoreException {
			if (resource.isDerived() || resource.isHidden())
				return false;
			long modstamp = resource.getLocalTimeStamp();
			if (modstamp > timestamp)
				timestamp = modstamp;
			return true;
		}

		Date getTimestamp() {
			return timestamp == -1 ? null : new Date(timestamp);
		}
	}

	private static final DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

	/**
	 * Pattern that scans for href's that are relative and don't start with ?
	 */
	private static final Pattern htmlPattern = Pattern.compile("<A\\s+HREF=\"([^?][^:\"]+)\"\\s*>[^<]+</A>", //$NON-NLS-1$
			Pattern.CASE_INSENSITIVE);

	/**
	 * Scan a listing obtained using FTP. The file name comes after a timestamp
	 * that ends with <hh:mm> or <year> and might contain a link, i.e. xxx ->
	 * yyy.
	 */
	private static final Pattern ftpPattern = Pattern.compile(
			"[a-z]+\\s+[0-9]+\\s+(?:(?:[0-9]+:[0-9]+)|(?:[0-9]{4}))\\s+(.+?)(?:([\\r|\\n])|(\\s+->\\s+))", //$NON-NLS-1$
			Pattern.CASE_INSENSITIVE);

	/**
	 * Check if pattern matches an index.html or other index.xxx. We transform
	 * such URL's to denote folders instead.
	 */
	private static final Pattern indexPath = Pattern.compile("^(.*/)?index\\.[a-z][a-z0-9]+$"); //$NON-NLS-1$

	private static final ThreadLocal<ProviderMatch> currentProviderMatch = new InheritableThreadLocal<ProviderMatch>();

	static {
		documentBuilderFactory.setIgnoringComments(true);
		documentBuilderFactory.setValidating(false);
		documentBuilderFactory.setNamespaceAware(false);
	}

	public static URL[] extractHTMLLinks(URL urlToHTML, IConnectContext cctx, IProgressMonitor monitor) throws CoreException {
		ArrayList<URL> links = new ArrayList<URL>();
		try {
			AccessibleByteArrayOutputStream buffer = new AccessibleByteArrayOutputStream(0x2000, 0x200000);
			DownloadManager.readInto(urlToHTML, cctx, buffer, monitor);
			try {
				final DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();

				// Use a very silent error handler
				//
				builder.setErrorHandler(new ErrorHandler() {
					@Override
					public void error(SAXParseException ex) throws SAXException {
						throw ex;
					}

					@Override
					public void fatalError(SAXParseException ex) throws SAXException {
						throw ex;
					}

					@Override
					public void warning(SAXParseException ex) throws SAXException {
					}
				});
				InputSource source = new InputSource(buffer.getInputStream());
				source.setSystemId(urlToHTML.toString());
				Document document = builder.parse(source);
				collectLinks(document.getDocumentElement(), urlToHTML, links);
			} catch (SAXException e) {
				// HTML was not well formed. Use a scanner instead
				//
				Scanner scanner = new Scanner(buffer.getInputStream());
				URL parent = URLUtils.appendTrailingSlash(urlToHTML);
				while (scanner.findWithinHorizon(htmlPattern, 0) != null) {
					MatchResult mr = scanner.match();
					addLink(links, parent, mr.group(1));
				}
				scanner.close();
			}
		} catch (IllegalStateException e) {
			CorePlugin.getLogger().warning(e, e.getMessage());
			return Trivial.EMPTY_URL_ARRAY;
		} catch (FileNotFoundException e) {
			return Trivial.EMPTY_URL_ARRAY;
		} catch (IOException e) {
			CorePlugin.getLogger().warning(e, e.getMessage());
			return Trivial.EMPTY_URL_ARRAY;
		} catch (ParserConfigurationException e) {
			CorePlugin.getLogger().warning(e, e.getMessage());
			return Trivial.EMPTY_URL_ARRAY;
		}
		return links.toArray(new URL[links.size()]);
	}

	public static ProviderMatch getCurrentProviderMatch() {
		return currentProviderMatch.get();
	}

	public static IComponentReader getReader(URL catalog, IProgressMonitor monitor) throws CoreException {
		return getDirectReader(catalog, URL_CATALOG, monitor);
	}

	public static URL[] list(URL url, IConnectContext cctx, IProgressMonitor monitor) throws CoreException {
		File dir = FileUtils.getFile(url);
		if (dir != null) {
			File[] list = dir.listFiles();
			if (list == null)
				return Trivial.EMPTY_URL_ARRAY;
			int top = list.length;
			if (top == 0)
				return Trivial.EMPTY_URL_ARRAY;
			URL[] result = new URL[top];
			while (--top >= 0) {
				File file = list[top];
				URI uri = URLUtils.normalizeToURI(file.toString(), file.isDirectory());
				try {
					result[top] = uri.toURL();
				} catch (MalformedURLException e) {
					throw BuckminsterException.wrap(e);
				}
			}
			MonitorUtils.complete(monitor);
			return result;
		}

		String proto = url.getProtocol();
		if (proto.equalsIgnoreCase("ftp") || proto.equalsIgnoreCase("sftp")) //$NON-NLS-1$ //$NON-NLS-2$
		{
			final ArrayList<URL> result = new ArrayList<URL>();
			Scanner scanner = null;
			try {
				scanner = new Scanner(DownloadManager.read(url, cctx));
				url = URLUtils.appendTrailingSlash(url);
				while (scanner.findWithinHorizon(ftpPattern, 0) != null) {
					MatchResult mr = scanner.match();
					result.add(new URL(url, mr.group(1)));
				}
				return result.toArray(new URL[result.size()]);
			} catch (CoreException e) {
				CorePlugin.getLogger().warning(e, e.getMessage());
				return Trivial.EMPTY_URL_ARRAY;
			} catch (FileNotFoundException e) {
				return Trivial.EMPTY_URL_ARRAY;
			} catch (IOException e) {
				CorePlugin.getLogger().warning(e, e.getMessage());
				return Trivial.EMPTY_URL_ARRAY;
			} finally {
				if (scanner != null)
					scanner.close();
			}
		}
		return extractHTMLLinks(url, cctx, monitor);
	}

	static IComponentReader getDirectReader(URL url, String readerType, IProgressMonitor monitor) throws CoreException {
		String urlString = url.toString();
		ComponentRequest rq = new ComponentRequest(urlString, null, null);
		ComponentQueryBuilder queryBld = new ComponentQueryBuilder();
		queryBld.setRootRequest(rq);
		queryBld.setPlatformAgnostic(true);
		ResolutionContext context = new ResolutionContext(queryBld.createComponentQuery());
		NodeQuery nq = new NodeQuery(context, rq, null);

		IComponentType ctype = CorePlugin.getDefault().getComponentType(IComponentType.UNKNOWN);
		Provider provider = Provider.immutableProvider(readerType, ctype.getId(), urlString);
		ProviderMatch pm = new ProviderMatch(provider, ctype, VersionMatch.DEFAULT, ProviderScore.GOOD, nq);
		return pm.getReader(monitor);
	}

	private static void addLink(List<URL> links, URL parent, String link) throws MalformedURLException {
		Matcher m = indexPath.matcher(link.toString());
		if (m.matches()) {
			link = m.group(1);
			if (link == null)
				return;
		}

		if (link.equals("../")) //$NON-NLS-1$
			return;

		links.add(new URL(parent, link));
	}

	private static void collectLinks(Element element, URL parent, ArrayList<URL> links) {
		if (element.getNodeName().equals("a")) //$NON-NLS-1$
		{
			try {
				addLink(links, parent, element.getAttribute("href")); //$NON-NLS-1$
			} catch (MalformedURLException e) {
				// Invalid href. Just skip it.
			}
		} else {
			for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling()) {
				if (child.getNodeType() == Node.ELEMENT_NODE)
					collectLinks((Element) child, parent, links);
			}
		}
	}

	@Override
	public URL convertToURL(String repositoryLocator, VersionMatch versionSelector) throws CoreException {
		try {
			return URLUtils.normalizeToURL(repositoryLocator);
		} catch (MalformedURLException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		try {
			return new URI(resolution.getRepository());
		} catch (URISyntaxException e) {
			return null;
		}
	}

	@Override
	public Date getLastModification(File workingCopy, IProgressMonitor monitor) throws CoreException {
		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IPath workingCopyPath = Path.fromOSString(workingCopy.getAbsolutePath());
		IResource resource = wsRoot.getContainerForLocation(workingCopyPath);
		if (resource == null) {
			resource = wsRoot.getFileForLocation(workingCopyPath);
			if (resource == null)
				return null;
		}
		LastModficationTimeFinder timeFinder = new LastModficationTimeFinder();
		resource.accept(timeFinder);
		return timeFinder.getTimestamp();
	}

	public IReaderType getLocalReaderType() {
		return this;
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return new URLCatalogReader(this, providerMatch);
	}

	@Override
	public String getRemotePath(String repositoryLocation) throws CoreException {
		return getURI(repositoryLocation).getPath();
	}

	public URI getURI(Provider provider, Map<String, ? extends Object> properties) throws CoreException {
		return getURI(provider.getURI(properties));
	}

	public URI getURI(ProviderMatch providerMatch) throws CoreException {
		return getURI(providerMatch.getRepositoryURI());
	}

	public URI getURI(String repository) throws CoreException {
		return URLUtils.normalizeToURI(repository, true);
	}
}
