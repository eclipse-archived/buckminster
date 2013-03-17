/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.download.DownloadManager;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ecf.core.security.IConnectContext;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 * 
 */
public class Maven2ReaderType extends MavenReaderType {
	public static Document getMetadataDocument(DocumentBuilder docBld, URL url, LocalCache cache, IConnectContext cctx, IProgressMonitor monitor)
			throws CoreException, FileNotFoundException {
		try {
			AccessibleByteArrayOutputStream buffer = new AccessibleByteArrayOutputStream(0x2000, 0x100000);
			try {
				DownloadManager.readInto(url, cctx, buffer, monitor);
				return docBld.parse(buffer.getInputStream());
			} catch (SAXParseException e) {
				String msg = e.getMessage();
				if (msg == null || !msg.contains("UTF-8")) //$NON-NLS-1$
					throw e;

				InputSource input = new InputSource(buffer.getInputStream());
				input.setEncoding("ISO-8859-1"); //$NON-NLS-1$
				docBld.reset();
				return docBld.parse(input);
			}
		} catch (CoreException e) {
			docBld.reset();
			throw e;
		} catch (FileNotFoundException e) {
			docBld.reset();
			throw e;
		} catch (Exception e) {
			docBld.reset();
			throw BuckminsterException.wrap(e);
		}
	}

	public static String getSnapshotVersion(Document doc, String version) {
		String v = null;
		Element versioningElement = getElement(doc, "versioning"); //$NON-NLS-1$
		if (versioningElement != null) {
			Element snapshotElement = getElement(versioningElement, "snapshot"); //$NON-NLS-1$
			if (snapshotElement != null) {
				Element buildNum = getElement(snapshotElement, "buildNumber"); //$NON-NLS-1$
				if (buildNum != null) {
					Element ts = getElement(snapshotElement, "timestamp"); //$NON-NLS-1$
					if (ts != null)
						v = version.substring(0, version.length() - 8) + ts.getTextContent() + '-' + buildNum.getTextContent();
				}
			}
		}
		return v;
	}

	public static List<String> getVersions(Document doc) {
		List<String> versionList = null;

		Element versioningElement = getElement(doc, "versioning"); //$NON-NLS-1$
		if (versioningElement != null) {
			Element versionsElement = getElement(versioningElement, "versions"); //$NON-NLS-1$
			if (versionsElement != null) {
				NodeList versions = versionsElement.getElementsByTagName("version"); //$NON-NLS-1$
				int top = versions.getLength();
				for (int i = 0; i < top; i++) {
					if (versionList == null)
						versionList = new ArrayList<String>();
					versionList.add(versions.item(i).getTextContent());
				}
			}
		}
		return versionList == null ? Collections.<String> emptyList() : versionList;
	}

	private static Element getElement(Document doc, String elementName) {
		return getElement(doc.getElementsByTagName(elementName));
	}

	private static Element getElement(Element elem, String elementName) {
		return elem == null ? null : getElement(elem.getElementsByTagName(elementName));
	}

	private static Element getElement(NodeList nodeList) {
		return (nodeList != null && nodeList.getLength() > 0) ? (Element) nodeList.item(0) : null;
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException {
		MonitorUtils.complete(monitor);
		return new Maven2VersionFinder(this, provider, ctype, nodeQuery);
	}

	@Override
	void appendArtifactFolder(StringBuilder pbld, IMapEntry mapEntry, VersionMatch vm) throws CoreException {
		String artifactPath = vm.getArtifactInfo();
		appendEntryFolder(pbld, mapEntry);
		pbld.append(artifactPath, 0, artifactPath.lastIndexOf('/') + 1);
	}

	void appendEntryFolder(StringBuilder pbld, IMapEntry mapEntry) throws CoreException {
		StringTokenizer tokens = new StringTokenizer(mapEntry.getGroupId(), "."); //$NON-NLS-1$
		while (tokens.hasMoreTokens())
			appendFolder(pbld, tokens.nextToken());
		appendFolder(pbld, mapEntry.getArtifactId());
	}

	@Override
	void appendFileName(StringBuilder pbld, String artifactID, VersionMatch vm, String extension) throws CoreException {
		if (extension == null) {
			String artifactPath = vm.getArtifactInfo();
			pbld.append(artifactPath, artifactPath.lastIndexOf('/') + 1, artifactPath.length());
		} else {
			pbld.append(artifactID);
			pbld.append('-');
			pbld.append(VersionHelper.getOriginal(vm.getVersion()));
			pbld.append(extension);
		}
	}

	@Override
	void appendPathToArtifact(StringBuilder pbld, IMapEntry mapEntry, VersionMatch vs) throws CoreException {
		appendEntryFolder(pbld, mapEntry);
		pbld.append(vs.getArtifactInfo());
	}

	@Override
	void appendPomFolder(StringBuilder pbld, IMapEntry mapEntry, VersionMatch vs) throws CoreException {
		appendArtifactFolder(pbld, mapEntry, vs);
	}

	VersionMatch createVersionMatch(DocumentBuilder docBld, ILocationResolver resolver, IMapEntry mapEntry, VersionRange range, String versionStr)
			throws CoreException {
		URI uri = resolver.getURI();
		StringBuilder pbld = new StringBuilder();
		appendFolder(pbld, uri.getPath());
		appendEntryFolder(pbld, mapEntry);
		String rootPath = pbld.toString();

		String v = versionStr;
		if (v.endsWith("SNAPSHOT")) //$NON-NLS-1$
		{
			try {
				LocalCache lc = getLocalCache();
				Document doc = getMetadataDocument(docBld, createURL(uri, rootPath + v + "/" //$NON-NLS-1$
						+ "maven-metadata.xml"), lc, resolver.getConnectContext(), new NullProgressMonitor()); //$NON-NLS-1$
				v = getSnapshotVersion(doc, v);
				if (v == null)
					return null;
			} catch (CoreException e) {
				resolver.logDecision(ResolverDecisionType.VERSION_REJECTED, v, e.getMessage());
				return null;
			} catch (FileNotFoundException e) {
				// Snapshot not present. This is a valid condition.
				return null;
			}
		}

		Version version;
		if (range == null)
			version = MavenComponentType.createVersion(v);
		else {
			try {
				version = range.getFormat().parse(v);
				if (!range.isIncluded(version))
					return null;
			} catch (IllegalArgumentException e) {
				return null;
			}
		}
		pbld.setLength(0);
		pbld.append(versionStr);
		pbld.append('/');
		pbld.append(mapEntry.getArtifactId());
		pbld.append('-');
		pbld.append(VersionHelper.getOriginal(version));
		if (mapEntry instanceof SourceMapEntry)
			pbld.append("-sources"); //$NON-NLS-1$
		pbld.append(".jar"); //$NON-NLS-1$
		return new VersionMatch(version, null, -1, null, pbld.toString());
	}

	@Override
	VersionMatch createVersionMatch(ILocationResolver resolver, IMapEntry mapEntry, String versionStr) throws CoreException {
		if (versionStr == null)
			return super.createVersionMatch(resolver, mapEntry, versionStr);

		try {
			VersionMatch vm = createVersionMatch(DocumentBuilderFactory.newInstance().newDocumentBuilder(), resolver, mapEntry, null, versionStr);
			if (vm == null)
				vm = super.createVersionMatch(resolver, mapEntry, versionStr);
			return vm;
		} catch (ParserConfigurationException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	@Override
	IPath getDefaultLocalRepoPath() {
		return Maven2VersionFinder.getDefaultLocalRepoPath();
	}

	@Override
	String getMaterializationFolder() {
		return "maven2"; //$NON-NLS-1$
	}

	@Override
	void setPackaging(ProviderMatch providerMatch, String packaging) {
		VersionMatch vm = providerMatch.getVersionMatch();
		String artifactInfo = vm.getArtifactInfo();
		int suffixDelim = artifactInfo.lastIndexOf('.');
		artifactInfo = artifactInfo.substring(0, suffixDelim + 1) + packaging;
		providerMatch.setVersionMatch(new VersionMatch(vm.getVersion(), vm.getBranchOrTag(), vm.getRevision(), vm.getTimestamp(), artifactInfo));
	}
}
