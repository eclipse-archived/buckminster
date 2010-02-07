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
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
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
import org.eclipse.osgi.util.NLS;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractParser<T> extends TopHandler implements ErrorHandler, IParser<T> {
	private static Pattern saxParseCleaner = Pattern.compile("^cvc-[^:]+:(.*)$"); //$NON-NLS-1$

	private static IFile[] noFiles = new IFile[0];

	public static IFile[] clearMarkers(String systemId) {
		// If the systemId is represented as a resource in the workspace, then
		// remove
		// all problem markers from it.
		//
		IFile[] files = getFilesForSystemId(systemId);
		try {
			for (IFile file : files)
				file.deleteMarkers(IMarker.PROBLEM, true, IResource.DEPTH_INFINITE);
		} catch (CoreException ce) {
			// Ignore, probably a locked workspace
		}
		return files;
	}

	public static XMLReader createXMLReader(boolean validating, boolean withNamespace) throws CoreException {
		try {
			return Utils.createXMLReader(validating, withNamespace);
		} catch (SAXException e) {
			throw BuckminsterException.wrap(e);
		}
	}

	public static void setMarkers(IFile[] files, SAXParseException e) {
		// Annotate the file if "systemId" denotes a resource in a project
		//
		try {
			String msg = e.getMessage();
			Matcher match = saxParseCleaner.matcher(msg);
			if (match.matches())
				msg = match.group(1);

			for (IFile file : files) {
				IMarker marker = file.createMarker(IMarker.PROBLEM);
				marker.setAttribute(IMarker.MESSAGE, msg);
				marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
				marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
				marker.setAttribute(IMarker.LINE_NUMBER, e.getLineNumber());
			}
		} catch (CoreException ce) {
			// Ignore
		}
	}

	private static IFile[] getFilesForSystemId(String systemId) {
		if (systemId == null || systemId.contains(".metadata")) //$NON-NLS-1$
			return noFiles;

		try {
			URL url = new URL(systemId);
			File file = FileUtils.getFile(url);
			if (file == null)
				return noFiles;
			systemId = file.toString();
		} catch (MalformedURLException murle) {
			// Apparently not a valid URL. That's expected
		}
		return ResourcesPlugin.getWorkspace().getRoot().findFilesForLocationURI(new Path(systemId).toFile().toURI());
	}

	private final boolean validating;

	private final List<String> namespaceLocations;

	private final List<ParserFactory.ParserExtension> parserExtensions;

	private HashSet<String> printedWarnings;

	protected AbstractParser(List<ParserFactory.ParserExtension> parserExtensions, String[] namespaces, String[] schemaLocations, boolean validating)
			throws CoreException {
		super(createXMLReader(validating, true));

		this.validating = validating;
		int top = namespaces.length;

		if (top != schemaLocations.length)
			throw new IllegalArgumentException(Messages.The_namespace_and_schemaLocation_arrays_must_be_equal_in_length);
		this.namespaceLocations = new ArrayList<String>();
		for (int idx = 0; idx < top; ++idx) {
			String namespace = namespaces[idx];
			String schemaFile = schemaLocations[idx];
			URL schemaURL = getClass().getResource(schemaFile);
			if (schemaURL == null)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.Unable_to_find_XMLSchema_for_namespace_0, namespace));
			addNamespaceLocation(namespace, schemaURL);
		}

		if (parserExtensions != null) {
			for (ParserFactory.ParserExtension pe : parserExtensions)
				addNamespaceLocation(pe.getNamespace(), pe.getResource());
		}
		this.parserExtensions = parserExtensions;
		setNamespaceAware(true);
		setErrorHandler(this);
	}

	public <H extends ChildHandler> H createContentHandler(AbstractHandler parent, Class<H> handlerClass, String namespace, String xsiType)
			throws SAXException {
		try {
			if (xsiType != null && parserExtensions != null) {
				String ns;
				int colonIndex = xsiType.indexOf(':');
				if (colonIndex > 0) {
					String prefix = xsiType.substring(0, colonIndex);
					ns = getPrefixMapping(prefix);
					if (ns == null)
						throw new SAXParseException(NLS.bind(Messages.Unknown_namespace_prefix_0, prefix), getDocumentLocator());
					xsiType = xsiType.substring(colonIndex + 1);
				} else
					ns = namespace;

				for (ParserFactory.ParserExtension pe : parserExtensions) {
					if (pe.getNamespace().equals(ns))
						return handlerClass.cast(pe.getHandler(parent, xsiType));
				}
			}
			try {
				Constructor<H> ctor = handlerClass.getConstructor(new Class[] { AbstractHandler.class });
				return ctor.newInstance(new Object[] { parent });
			} catch (Exception e) {
				throw BuckminsterException.wrap(e);
			}
		} catch (Exception e) {
			throw new SAXParseException(NLS.bind(Messages.Unable_to_create_extension_handler_0_1, namespace, xsiType), getDocumentLocator(), e);
		}
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		throw e;
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		throw e;
	}

	public void warningOnce(String warning) {
		if (printedWarnings == null)
			printedWarnings = new HashSet<String>();
		else if (printedWarnings.contains(warning))
			return;

		printedWarnings.add(warning);
		CorePlugin.getLogger().warning(warning);
	}

	protected void addNamespaceLocation(String namespace, URL location) {
		namespaceLocations.add(namespace + ' ' + location.toString());
	}

	protected void init() throws SAXException {
		XMLReader reader = getXMLReader();
		if (validating) {
			reader.setFeature("http://apache.org/xml/features/validation/schema", true); //$NON-NLS-1$
			reader.setFeature("http://apache.org/xml/features/validation/schema-full-checking", true); //$NON-NLS-1$
		}

		int len = 0;
		int top = namespaceLocations.size();
		for (int idx = 0; idx < top; ++idx) {
			len += namespaceLocations.get(idx).length();
			len++;
		}
		StringBuilder bld = new StringBuilder(len);
		for (int idx = 0; idx < top; ++idx) {
			if (idx > 0)
				bld.append(' ');
			bld.append(namespaceLocations.get(idx));
		}
		reader.setProperty("http://apache.org/xml/properties/schema/external-schemaLocation", bld.toString()); //$NON-NLS-1$
	}

	protected void parseInput(String systemId, InputStream input) throws CoreException {
		IFile[] files = clearMarkers(systemId);
		try {
			init();
			if (!(input instanceof BufferedInputStream || input instanceof ByteArrayInputStream))
				input = new BufferedInputStream(input);
			InputSource source = new InputSource(input);
			if (systemId != null)
				source.setSystemId(systemId);
			getXMLReader().parse(source);
		} catch (SAXParseException e) {
			setMarkers(files, e);
			throw BuckminsterException.wrap(e);
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		} finally {
			getXMLReader().setContentHandler(this);
		}
	}
}
