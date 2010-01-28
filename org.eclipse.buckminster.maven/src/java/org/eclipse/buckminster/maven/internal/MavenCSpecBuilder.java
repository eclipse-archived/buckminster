/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.ctype.MissingCSpecSourceException;
import org.eclipse.buckminster.core.helpers.AccessibleByteArrayOutputStream;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IFileReader;
import org.eclipse.buckminster.core.reader.IStreamConsumer;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.maven.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class MavenCSpecBuilder extends AbstractResolutionBuilder implements IStreamConsumer<Document>
{
	public BOMNode build(IComponentReader[] readerHandle, boolean forResolutionAidOnly, IProgressMonitor monitor)
			throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		ProviderMatch ri = reader.getProviderMatch();
		monitor.beginTask(null, 3000);
		monitor.subTask(Messages.generating_cspec_from_maven_artifact);
		try
		{
			Document pomDoc;
			IProgressMonitor subMon = MonitorUtils.subMonitor(monitor, 2000);
			if(reader instanceof MavenReader)
			{
				// We are reading from a maven repository. In that case, we will
				// allow a missing pom file.
				//
				pomDoc = ((MavenReader)reader).getPOMDocument(subMon);
			}
			else
			{
				// Some other reader is used. This reader is either reading a source
				// directory (catalog reader) or a pom/project file directly. In any
				// case, we consider a missing file an exceptional condition.
				//
				try
				{
					if(reader instanceof ICatalogReader)
					{
						try
						{
							pomDoc = ((ICatalogReader)reader).readFile("pom.xml", this, subMon); //$NON-NLS-1$
						}
						catch(FileNotFoundException e)
						{
							pomDoc = ((ICatalogReader)reader).readFile("project.xml", this, subMon); //$NON-NLS-1$
						}
					}
					else
						pomDoc = ((IFileReader)reader).readFile(this, subMon);
				}
				catch(FileNotFoundException e2)
				{
					throw new MissingCSpecSourceException(reader.getProviderMatch());
				}
			}

			CSpecBuilder cspecBld = ri.createCSpec();
			cspecBld.setComponentTypeID(MavenComponentType.ID);
			GroupBuilder archives = AbstractComponentType.addSelfAsJarArtifactGroups(cspecBld);
			if(pomDoc != null)
			{
				ExpandingProperties<String> properties = new ExpandingProperties<String>();
				String packaging = MavenComponentType.addDependencies(reader, pomDoc, cspecBld, archives, properties);
				if(reader instanceof MavenReader && !"jar".equals(packaging)) //$NON-NLS-1$
					((MavenReader)reader).setPackaging(packaging);
			}

			applyExtensions(cspecBld, forResolutionAidOnly, reader, MonitorUtils.subMonitor(monitor, 1000));
			return createNode(reader, cspecBld);
		}
		catch(IOException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}

	public Document consumeStream(IComponentReader reader, String streamName, InputStream stream,
			IProgressMonitor monitor) throws CoreException, IOException
	{
		monitor.beginTask(streamName, 1);
		try
		{
			AccessibleByteArrayOutputStream buffer = new AccessibleByteArrayOutputStream(0x2000, 0x100000);
			FileUtils.copyFile(stream, buffer, new byte[0x1000], monitor);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBld = factory.newDocumentBuilder();
			try
			{
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
		catch(SAXException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(ParserConfigurationException e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			MonitorUtils.worked(monitor, 1);
			monitor.done();
		}
	}
}
