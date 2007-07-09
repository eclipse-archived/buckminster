/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.parser;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.TopHandler;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;


/**
 * @author Thomas Hallgren
 */
public abstract class AbstractParser<T> extends TopHandler implements ErrorHandler, IParser<T>
{
	private final boolean m_validating;
	private final String m_namespaceLocations;
	private final List<ParserFactory.ParserExtension> m_parserExtensions;
	private HashSet<String> m_printedWarnings;

	protected AbstractParser(List<ParserFactory.ParserExtension> parserExtensions, String[] namespaces, String[] schemaLocations, boolean validating)
	throws SAXException
	{
		super(Utils.createXMLReader(validating, true));

		m_validating = validating;
		int top = namespaces.length;

		if(top != schemaLocations.length)
			throw new IllegalArgumentException("the namespace and schemaLocation arrays must be equal in length");
		StringBuilder namespaceLocations = new StringBuilder();		
		for(int idx = 0; idx < top; ++idx)
		{
			String namespace = namespaces[idx];
			String schemaFile = schemaLocations[idx];
			URL schemaURL = this.getClass().getResource(schemaFile);
			if(schemaURL == null)
				throw new SAXException("Unable to find XMLSchema for namespace " + namespace);
			if(idx > 0)
				namespaceLocations.append(' ');
			namespaceLocations.append(namespace);
			namespaceLocations.append(' ');
			namespaceLocations.append(schemaURL.toString());
		}

		if(parserExtensions != null)
		{
			for(ParserFactory.ParserExtension pe : parserExtensions)
			{
				if(namespaceLocations.length() > 0)
					namespaceLocations.append(' ');
				namespaceLocations.append(pe.getNamespace());
				namespaceLocations.append(' ');
				namespaceLocations.append(pe.getResource().toString());
			}
		}
		m_parserExtensions = parserExtensions;
		m_namespaceLocations = namespaceLocations.toString();
		this.setNamespaceAware(true);
		this.setErrorHandler(this);
	}

	protected void init()
	throws SAXException
	{
		XMLReader reader = this.getXMLReader();
		if(m_validating)
		{
			reader.setFeature("http://apache.org/xml/features/validation/schema", true);
			reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true);
		}
		reader.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", m_namespaceLocations);
	}

	@Override
	public void error(SAXParseException e)
	throws SAXException
    {
		throw e;
    }

	public void warningOnce(String warning)
	{
		if(m_printedWarnings == null)
			m_printedWarnings = new HashSet<String>();
		else if(m_printedWarnings.contains(warning))
			return;

		m_printedWarnings.add(warning);
		CorePlugin.getLogger().warning(warning);
	}

	private static Pattern s_saxParseCleaner = Pattern.compile("^cvc-[^:]+:(.*)$");

	protected void parseInput(String systemId, InputStream input) throws SAXException
	{
		// If the systemId is represented as a resource in the workspace, then remove
		// all problem markers from it.
		//
		IFile[] files = getFilesForSystemId(systemId);
		try
		{
			for(IFile file : files)
				file.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		}
		catch(CoreException ce)
		{
			// Ignore, probably a locked workspace
		}

		try
		{
			this.init();
			if(!(input instanceof BufferedInputStream || input instanceof ByteArrayInputStream))
				input = new BufferedInputStream(input);
			InputSource source = new InputSource(input);
			if(systemId != null)
				source.setSystemId(systemId);
			this.getXMLReader().parse(source);
		}
		catch(SAXParseException e)
		{
			// Annotate the file if "systemId" denotes a resource in a project
			//
			try
			{
				String msg = e.getMessage();
				Matcher match = s_saxParseCleaner.matcher(msg);
				if(match.matches())
					msg = match.group(1);
				for(IFile file : files)
				{
					IMarker marker = file.createMarker(IMarker.PROBLEM);
					marker.setAttribute(IMarker.MESSAGE, msg);
					marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
					marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
					marker.setAttribute(IMarker.LINE_NUMBER, e.getLineNumber());
				}
			}
			catch(CoreException ce)
			{
				// Ignore
			}
			throw e;
		}
		catch(IOException e)
		{
			throw new SAXException(e);
		}
	}

	public <H extends ChildHandler> H createContentHandler(AbstractHandler parent, Class<H> handlerClass, String namespace, String xsiType)
	throws SAXException
	{
		try
		{
			if(xsiType != null && m_parserExtensions != null)
			{
				String ns;
				int colonIndex = xsiType.indexOf(':');
				if(colonIndex > 0)
				{
					String prefix = xsiType.substring(0, colonIndex);
					ns = this.getPrefixMapping(prefix);
					if(ns == null)
						throw new SAXParseException("Unknown namespace prefix: " + prefix, this.getDocumentLocator());
					xsiType = xsiType.substring(colonIndex + 1);
				}
				else
					ns = namespace;

				for(ParserFactory.ParserExtension pe : m_parserExtensions)
				{
					if(pe.getNamespace().equals(ns))
						return handlerClass.cast(pe.getHandler(parent, xsiType));
				}
			}
			try
			{
				Constructor<H> ctor = handlerClass.getConstructor(new Class[] { AbstractHandler.class });
				return ctor.newInstance(new Object[] { parent });
			}
			catch(Exception e)
			{
				throw BuckminsterException.wrap(e);
			}
		}
		catch(Exception e)
		{
			throw new SAXParseException("Unable to create extension handler " + namespace + ':' + xsiType, this.getDocumentLocator(), e);
		}
	}

	@Override
	public void warning(SAXParseException e)
	throws SAXException
    {
		throw e;
    }

	private static IFile[] s_noFiles = new IFile[0];

	private static IFile[] getFilesForSystemId(String systemId)
	{
		if(systemId == null || systemId.contains(".metadata"))
			return s_noFiles;

		try
		{
			URL url = new URL(systemId);
			File file = FileUtils.getFile(url);
			if(file == null)
				return s_noFiles;
			systemId = file.toString();
		}
		catch(MalformedURLException murle)
		{
			// Apparently not a valid URL. That's expected
		}
		return ResourcesPlugin.getWorkspace().getRoot().findFilesForLocation(new Path(systemId));
	}
}

