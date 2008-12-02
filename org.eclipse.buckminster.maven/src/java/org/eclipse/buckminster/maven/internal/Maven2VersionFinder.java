/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ecf.core.security.IConnectContext;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class Maven2VersionFinder extends MavenVersionFinder
{
	public static IPath getDefaultLocalRepoPath()
	{
		return new Path(System.getProperty("user.home")).append(".m2").append("repository"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

	public static Document getMetadataDocument(DocumentBuilder docBld, URL url, LocalCache cache, IConnectContext cctx,
			IProgressMonitor monitor) throws CoreException, FileNotFoundException
	{
		try
		{
			AccessibleByteArrayOutputStream buffer = new AccessibleByteArrayOutputStream(0x2000, 0x100000);
			try
			{
				DownloadManager.readInto(url, cctx, buffer, monitor);
				return docBld.parse(buffer.getInputStream());
			}
			catch(SAXParseException e)
			{
				String msg = e.getMessage();
				if(msg == null || !msg.contains("UTF-8")) //$NON-NLS-1$
					throw e;

				InputSource input = new InputSource(buffer.getInputStream());
				input.setEncoding("ISO-8859-1"); //$NON-NLS-1$
				docBld.reset();
				return docBld.parse(input);
			}
		}
		catch(CoreException e)
		{
			docBld.reset();
			throw e;
		}
		catch(FileNotFoundException e)
		{
			docBld.reset();
			throw e;
		}
		catch(Exception e)
		{
			docBld.reset();
			throw BuckminsterException.wrap(e);
		}
	}

	public static String getSnapshotVersion(Document doc, String version)
	{
		String v = null;
		Element versioningElement = getElement(doc, "versioning"); //$NON-NLS-1$
		if(versioningElement != null)
		{
			Element snapshotElement = getElement(versioningElement, "snapshot"); //$NON-NLS-1$
			if(snapshotElement != null)
			{
				Element buildNum = getElement(snapshotElement, "buildNumber"); //$NON-NLS-1$
				if(buildNum != null)
				{
					Element ts = getElement(snapshotElement, "timestamp"); //$NON-NLS-1$
					if(ts != null)
						v = version.substring(0, version.length() - 8) + ts.getTextContent() + '-'
								+ buildNum.getTextContent();
				}
			}
		}
		return v;
	}

	public static List<String> getVersions(Document doc)
	{
		List<String> versionList = null;

		Element versioningElement = getElement(doc, "versioning"); //$NON-NLS-1$
		if(versioningElement != null)
		{
			Element versionsElement = getElement(versioningElement, "versions"); //$NON-NLS-1$
			if(versionsElement != null)
			{
				NodeList versions = versionsElement.getElementsByTagName("version"); //$NON-NLS-1$
				int top = versions.getLength();
				for(int i = 0; i < top; i++)
				{
					if(versionList == null)
						versionList = new ArrayList<String>();
					versionList.add(versions.item(i).getTextContent());
				}
			}
		}
		return versionList == null
				? Collections.<String> emptyList()
				: versionList;
	}

	private static Element getElement(Document doc, String elementName)
	{
		return getElement(doc.getElementsByTagName(elementName));
	}

	private static Element getElement(Element elem, String elementName)
	{
		return elem == null
				? null
				: getElement(elem.getElementsByTagName(elementName));
	}

	private static Element getElement(NodeList nodeList)
	{
		return (nodeList != null && nodeList.getLength() > 0)
				? (Element)nodeList.item(0)
				: null;
	}

	public Maven2VersionFinder(MavenReaderType readerType, Provider provider, IComponentType ctype, NodeQuery query)
			throws CoreException
	{
		super(readerType, provider, ctype, query);
	}

	/**
	 * Returns an array of versions known to this repository.
	 * 
	 * @return known versions or <code>null</code> if not applicable.
	 * @throws CoreException
	 */
	@Override
	List<VersionMatch> getComponentVersions(IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = getQuery();
		IVersionDesignator designator = query.getVersionDesignator();
		if(designator == null)
			designator = VersionFactory.createDesignator(VersionFactory.TripletType, "0.0.0"); //$NON-NLS-1$
		else
		{
			if(designator.getVersion().getType().equals(VersionFactory.OSGiType))
				//
				// Convert the OSGi version to a Triplet version instead.
				//
				designator = VersionFactory.createDesignator(VersionFactory.TripletType, designator.toString());
		}

		List<VersionMatch> versions = new ArrayList<VersionMatch>();
		Maven2ReaderType readerType = (Maven2ReaderType)getReaderType();
		URI uri = getURI();
		StringBuilder pbld = new StringBuilder();
		readerType.appendFolder(pbld, uri.getPath());
		readerType.appendEntryFolder(pbld, getMapEntry());
		String rootPath = pbld.toString();
		IConnectContext cctx = getConnectContext();

		LocalCache lc = getReaderType().getLocalCache();
		IVersionDesignator versionDesignator = query.getVersionDesignator();
		monitor.beginTask(null, 2000);
		try
		{
			DocumentBuilder docBld = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = getMetadataDocument(docBld, MavenReaderType.createURL(uri, rootPath + "maven-metadata.xml"), //$NON-NLS-1$
					lc, cctx, monitor);
			for(String versionStr : getVersions(doc))
			{
				String v = versionStr;
				if(v.endsWith("SNAPSHOT")) //$NON-NLS-1$
				{
					try
					{
						doc = getMetadataDocument(docBld, MavenReaderType.createURL(uri, rootPath + v + "/" //$NON-NLS-1$
								+ "maven-metadata.xml"), lc, cctx, new NullProgressMonitor()); //$NON-NLS-1$
						v = getSnapshotVersion(doc, v);
						if(v == null)
							continue;
					}
					catch(CoreException e)
					{
						logDecision(ResolverDecisionType.VERSION_REJECTED, v, e.getMessage());
						continue;
					}
					catch(FileNotFoundException e)
					{
						// Snapshot not present. This is a valid condition.
						continue;
					}
				}

				IVersion version;
				if(versionDesignator == null)
					version = MavenComponentType.createVersion(v);
				else
				{
					try
					{
						version = versionDesignator.getVersion().getType().fromString(v);
						if(!versionDesignator.designates(version))
							continue;
					}
					catch(VersionSyntaxException e)
					{
						continue;
					}
				}

				pbld.setLength(0);
				pbld.append(versionStr);
				pbld.append('/');
				pbld.append(getMapEntry().getArtifactId());
				pbld.append('-');
				pbld.append(version);
				pbld.append(".jar"); //$NON-NLS-1$
				versions.add(new VersionMatch(version, null, -1, null, pbld.toString()));
			}
			return versions;
		}
		catch(FileNotFoundException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(ParserConfigurationException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}
}
