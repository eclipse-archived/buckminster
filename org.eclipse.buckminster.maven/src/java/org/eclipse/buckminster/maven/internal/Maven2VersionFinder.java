/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * @author Thomas Hallgren
 */
public class Maven2VersionFinder extends MavenVersionFinder
{
	public Maven2VersionFinder(MavenReaderType readerType, Provider provider, IComponentType ctype, NodeQuery query)
	throws CoreException
	{
		super(readerType, provider, ctype, query);
	}

	@Override
	URL[] createFileList(IVersionDesignator designator, IProgressMonitor monitor) throws CoreException
	{
		Maven2ReaderType readerType = (Maven2ReaderType)getReaderType();
		URI uri = getURI();
		StringBuilder pbld = new StringBuilder();
		readerType.appendFolder(pbld, uri.getPath());
		readerType.appendEntryFolder(pbld, getMapEntry());
		ArrayList<URL> fileList = new ArrayList<URL>();
		String rootPath = pbld.toString();
		int rootLen = rootPath.length();
		String space = getProvider().getSpace();

		monitor.beginTask(null, 2000);
		try
		{
			DocumentBuilder docBld = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			NodeQuery query = getQuery();
			for(String version : getVersions(query.getVersionDesignator(), readerType, docBld, uri, rootPath, MonitorUtils.subMonitor(monitor, 1000)))
			{
				VersionMatch versionMatch = MavenComponentType.createVersionMatch(version, space, null);
				if(versionMatch != null && query.isMatch(versionMatch))
				{
					pbld.setLength(rootLen);
					pbld.append(version);
					pbld.append('/');

					pbld.append(getMapEntry().getArtifactId());
					pbld.append('-');
					if (isReleaseVersion(version))
						pbld.append(version);
					else
						appendSnapshotIdentifier(pbld, readerType, docBld, uri, rootPath, version, monitor);
					pbld.append(".jar");

					fileList.add(readerType.createURL(uri, pbld.toString()));
				}
			}
			return fileList.toArray(new URL[fileList.size()]);
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

	private List<String> getVersions(IVersionDesignator versionDesignator, Maven2ReaderType readerType, DocumentBuilder docBld, URI uri, String path, IProgressMonitor monitor) throws CoreException
	{
		List<String> versionList = null;

		Document doc = getMetadataDocument(readerType, docBld, uri, path + "maven-metadata.xml", monitor);
		if (doc != null)
		{
			Element versioningElement = (Element) doc.getElementsByTagName("versioning").item(0);
			if(versioningElement != null)
			{
				Element versionsElement = (Element) versioningElement.getElementsByTagName("versions").item(0);
				if(versionsElement != null)
				{
					NodeList versions = versionsElement.getElementsByTagName("version");
					int top = versions.getLength();
					for (int i = 0; i < top; i++)
					{
						String version = versions.item(i).getTextContent();
						if(!isReleaseVersion(versionDesignator) || isReleaseVersion(version))
						{
							if(versionList == null)
								versionList = new ArrayList<String>();
							versionList.add(version);
						}
					}
				}
			}
		}
		return versionList == null ? Collections.<String>emptyList() : versionList;
	}

	private void appendSnapshotIdentifier(StringBuilder pbld, Maven2ReaderType readerType, DocumentBuilder docBld, URI uri, String rootPath, String version, IProgressMonitor monitor) throws CoreException
	{
		Document doc = getMetadataDocument(readerType, docBld, uri, rootPath + version + "/" + "maven-metadata.xml", monitor);
		if(doc != null)
		{
			Element versioningElement = (Element) doc.getElementsByTagName("versioning").item(0);
			if(versioningElement != null)
			{
				Element versionsElement = (Element) versioningElement.getElementsByTagName("snapshot").item(0);
				if(versionsElement != null)
				{
					Element timestampElement = (Element) versionsElement.getElementsByTagName("timestamp").item(0);
					Element buildNumElement = (Element) versionsElement.getElementsByTagName("buildNumber").item(0);
					if(timestampElement != null && buildNumElement != null)
					{
						pbld.append(version.substring(0, version.indexOf("SNAPSHOT")));
						pbld.append(timestampElement.getTextContent());
						pbld.append('-');
						pbld.append(buildNumElement.getTextContent());
						return;
					}
				}
			}
		}
		throw BuckminsterException.fromMessage("Unable to read snapshot metadata");
	}

	private boolean isReleaseVersion(String version)
	{
		return !version.endsWith("SNAPSHOT");
	}

	private boolean isReleaseVersion(IVersionDesignator versionDesignator)
	{
		return (versionDesignator == null)
			? true
			: isReleaseVersion(versionDesignator.getVersion().toString());
	}

	private Document getMetadataDocument(Maven2ReaderType readerType, DocumentBuilder docBld, URI uri, String path, IProgressMonitor monitor) throws CoreException
	{
		URL url = readerType.createURL(uri, path);

		InputStream input = null;
		Document doc;
		try
		{
			input = CorePlugin.getDefault().openCachedURL(url, monitor);
			doc = docBld.parse(input);
		}
		catch(CoreException e)
		{
			docBld.reset();
			throw e;
		}
		catch(Exception e)
		{
			docBld.reset();
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
		}

		// Make sure groupId and artifactId in metadata document match map entry
		String mapEntryGroupId = getMapEntry().getGroupId();
		String mapEntryArtifactId = getMapEntry().getArtifactId();

		String docGroupId = doc.getElementsByTagName("groupId").item(0).getTextContent();
		String docArtifactId = doc.getElementsByTagName("artifactId").item(0).getTextContent();

		if (mapEntryGroupId.equals(docGroupId) && mapEntryArtifactId.equals(docArtifactId))
			return doc;

		CorePlugin.getLogger().warning("Metadata at %s has groupId:artifactId %s:%s  that does not match map entry %s:%s",
			url, docGroupId, docArtifactId, mapEntryGroupId, mapEntryArtifactId);
		return null;
	}
}
