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
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.URLFileReader;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The URL used by the MavenReader denotes the group directory within one
 * specific repository. The format must be <br/>
 * <code>[&lt;schema&gt;][//&lt;authority&gt;]&lt;path to group&gt;#&lt;artifact&gt;</code><br/>
 * The ability to search trhough multiple repositories is obtained by using the
 * <code>SearchPath</code> or the <code>ResourceMap</code>. The
 * 
 * @author Thomas Hallgren
 */
public class MavenReader extends URLFileReader
{
	private final MapEntry m_mapEntry;

	public MavenReader(MavenReaderType readerType, ProviderMatch rInfo) throws CoreException
	{
		super(readerType, rInfo);
		m_mapEntry = MavenReaderType.getGroupAndArtifact(rInfo.getProvider(), rInfo.getNodeQuery().getComponentRequest());
	}

	IVersionSelector getVersionSelector() throws CoreException
	{
		return getProviderMatch().getVersionMatch().getFixedVersionSelector();
	}

	@Override
	public URL getURL() throws CoreException
	{
		return ((MavenReaderType)getReaderType()).getArtifactURL(getURI(), m_mapEntry, getVersionSelector());
	}

	@Override
	public InputStream open(IProgressMonitor monitor) throws CoreException, IOException
	{
		IPath artifactPath = ((MavenReaderType)getReaderType()).getArtifactPath(m_mapEntry, getVersionSelector());
		return ((MavenReaderType)getReaderType()).getLocalCache().openFile(getURI().toURL(), artifactPath, monitor);
	}

	Document getPOMDocument(IProgressMonitor monitor) throws CoreException
	{
		MavenReaderType rt = (MavenReaderType)getReaderType();
		URL pomURL = rt.getPomURL(getURI(), m_mapEntry, getVersionSelector());
		IPath pomPath = rt.getPomPath(m_mapEntry, getVersionSelector());
		InputStream input = null;
		monitor.beginTask(null, 2000);
		try
		{
			input = rt.getLocalCache().openFile(getURI().toURL(), pomPath, MonitorUtils.subMonitor(monitor, 1000));
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource source = new InputSource(new BufferedInputStream(input));
			source.setSystemId(pomURL.toString());
			return builder.parse(source);
		}
		catch(IOException e)
		{
			// This is OK, we don't have a pom file.
			//
			return null;
		}
		catch(ParserConfigurationException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			IOUtils.close(input);
			MonitorUtils.worked(monitor, 1000);
			monitor.done();
		}
	}
}
