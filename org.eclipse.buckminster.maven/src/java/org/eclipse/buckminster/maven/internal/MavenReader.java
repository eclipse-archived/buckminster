/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.eclipse.buckminster.core.reader.URLFileReader;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

/**
 * The URL used by the MavenReader denotes the group directory within one
 * specific repository. The format must be <br/>
 * <code>[&lt;schema&gt;][//&lt;authority&gt;]&lt;path to group&gt;#&lt;artifact&gt;</code>
 * <br/>
 * The ability to search trhough multiple repositories is obtained by using the
 * <code>SearchPath</code> or the <code>ResourceMap</code>. The
 * 
 * @author Thomas Hallgren
 */
public class MavenReader extends URLFileReader implements ILocationResolver {
	private final MapEntry mapEntry;

	public MavenReader(MavenReaderType readerType, ProviderMatch rInfo) throws CoreException {
		super(readerType, rInfo, readerType.getURI(rInfo));
		mapEntry = MavenReaderType.getGroupAndArtifact(rInfo.getProvider(), rInfo.getNodeQuery().getComponentRequest());
	}

	@Override
	public URL getURL() throws CoreException {
		return ((MavenReaderType) getReaderType()).getArtifactURL(getURI(), mapEntry, getVersionMatch());
	}

	@Override
	public InputStream open(IProgressMonitor monitor) throws CoreException, IOException {
		IPath artifactPath = ((MavenReaderType) getReaderType()).getArtifactPath(mapEntry, getVersionMatch());
		return ((MavenReaderType) getReaderType()).getLocalCache().openFile(getURI().toURL(), getConnectContext(), artifactPath, monitor);
	}

	Document getPOMDocument(IProgressMonitor monitor) throws CoreException {
		MavenReaderType rt = (MavenReaderType) getReaderType();
		VersionMatch vs = getVersionMatch();
		IPath pomPath = rt.getPomPath(mapEntry, vs);
		return getPOMDocument(mapEntry, vs, pomPath, monitor);
	}

	Document getPOMDocument(MapEntry entry, VersionMatch vs, IPath pomPath, IProgressMonitor monitor) throws CoreException {
		MavenReaderType rt = (MavenReaderType) getReaderType();
		URI repoURI = getURI();
		InputStream input = null;
		monitor.beginTask(null, 2000);
		try {
			URL repoURL = repoURI.toURL();
			input = rt.getLocalCache().openFile(repoURI.toURL(), getConnectContext(), pomPath, MonitorUtils.subMonitor(monitor, 1000));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			String repoPath = repoURL.getPath();
			if (!repoPath.endsWith("/")) //$NON-NLS-1$
				repoPath += "/"; //$NON-NLS-1$
			repoPath += pomPath;
			try {
				InputSource source = new InputSource(new BufferedInputStream(input));
				source.setSystemId(new URI(repoURI.getScheme(), repoURI.getAuthority(), repoPath, repoURI.getQuery(), repoURI.getFragment())
						.toString());
				return builder.parse(source);
			} catch (SAXParseException e) {
				String msg = e.getMessage();
				if (msg == null || !msg.contains("UTF-8")) //$NON-NLS-1$
					throw e;

				IOUtils.close(input);
				input = rt.getLocalCache().openFile(repoURI.toURL(), getConnectContext(), pomPath, MonitorUtils.subMonitor(monitor, 1000));
				InputSource source = new InputSource(new BufferedInputStream(input));
				source.setSystemId(new URI(repoURI.getScheme(), repoURI.getAuthority(), repoPath, repoURI.getQuery(), repoURI.getFragment())
						.toString());
				source.setEncoding("ISO-8859-1"); //$NON-NLS-1$
				builder.reset();
				return builder.parse(source);
			}
		} catch (IOException e) {
			// This is OK, we don't have a pom file.
			//
			return null;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		} finally {
			IOUtils.close(input);
			MonitorUtils.worked(monitor, 1000);
			monitor.done();
		}
	}

	VersionMatch getVersionMatch() throws CoreException {
		return getProviderMatch().getVersionMatch();
	}

	void setPackaging(String packaging) {
		((MavenReaderType) getReaderType()).setPackaging(getProviderMatch(), packaging);
	}
}
