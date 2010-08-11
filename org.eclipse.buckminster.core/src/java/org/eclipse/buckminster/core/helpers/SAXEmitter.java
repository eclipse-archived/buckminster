/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.buckminster.model.common.ComponentIdentifier;
import org.eclipse.buckminster.model.common.ComponentName;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.util.IProperties;
import org.eclipse.buckminster.model.common.util.MapUnion;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * This class contains the methods needed during a period when some common model
 * classes are used in non-ecore models. The class will be removed when all
 * models has been transformed into ecore.
 * 
 * @author Thomas Hallgren
 */
public abstract class SAXEmitter {
	public static void addAttributes(ComponentIdentifier ci, AttributesImpl attrs) {
		addAttributes((ComponentName) ci, attrs);
		if (ci.getVersion() != null)
			Utils.addAttribute(attrs, "version", ci.getVersion().toString()); //$NON-NLS-1$
	}

	public static void addAttributes(ComponentRequest request, AttributesImpl attrs) {
		addAttributes((ComponentName) request, attrs);
		if (request.getRange() != null)
			Utils.addAttribute(attrs, "versionDesignator", request.getRange().toString()); //$NON-NLS-1$
		if (request.getFilter() != null)
			Utils.addAttribute(attrs, "filter", request.getFilter().toString()); //$NON-NLS-1$
	}

	public static void emit(ContentHandler handler, ComponentIdentifier ci, String namespace, String prefix, String localName) throws SAXException {
		String qName = Utils.makeQualifiedName(prefix, localName);
		AttributesImpl attrs = new AttributesImpl();
		addAttributes(ci, attrs);
		handler.startElement(namespace, localName, qName, attrs);
		handler.endElement(namespace, localName, qName);
	}

	public static void emit(ContentHandler handler, ComponentRequest request, String namespace, String prefix, String localName) throws SAXException {
		String qName = Utils.makeQualifiedName(prefix, localName);
		AttributesImpl attrs = new AttributesImpl();
		addAttributes(request, attrs);
		handler.startElement(namespace, localName, qName, attrs);
		handler.endElement(namespace, localName, qName);
	}

	public static void emitComponentRequests(String namespace, String prefix, String localName, String elemName,
			Collection<ComponentRequest> collection, ContentHandler handler) throws SAXException {
		if (collection.isEmpty())
			return;
		String qName = Utils.makeQualifiedName(prefix, localName);
		handler.startElement(namespace, localName, qName, ISaxableElement.EMPTY_ATTRIBUTES);
		for (ComponentRequest elem : collection)
			emit(handler, elem, namespace, prefix, elemName);
		handler.endElement(namespace, localName, qName);
	}

	public static void emitProperties(ContentHandler handler, Map<String, String> props, String namespace, String prefix, boolean raw,
			boolean includeDefaults) throws SAXException {
		TreeSet<String> sorted = new TreeSet<String>();
		if (includeDefaults) {
			for (String name : props.keySet()) {
				// We don't include the system properties here. Perhaps we
				// should? But
				// then again, in order to have everything, we'd need the
				// environment,
				// and the OS configuration info as well (at least).
				//
				String sysValue = System.getProperty(name);
				if (sysValue != null && sysValue.equals(props.get(name)))
					continue;

				sorted.add(name);
			}
		} else {
			Set<String> keySet;
			if (props instanceof IProperties)
				keySet = ((IProperties) props).overlayKeySet();
			else if (props instanceof MapUnion<?, ?>)
				keySet = ((MapUnion<String, String>) props).overlayKeySet();
			else
				keySet = props.keySet();
			for (String name : keySet)
				sorted.add(name);
		}

		String plName = "property"; //$NON-NLS-1$
		String pqName = Utils.makeQualifiedName(prefix, plName);

		AttributesImpl attrs = new AttributesImpl();
		boolean withMutableAttr = (props instanceof IProperties) && ((IProperties) props).supportsMutability();
		for (String name : sorted) {
			String value = props.get(name);
			if (value == null || value.length() == 0)
				continue;

			attrs.clear();
			Utils.addAttribute(attrs, "key", name); //$NON-NLS-1$
			Utils.addAttribute(attrs, "value", value); //$NON-NLS-1$
			if (withMutableAttr && ((IProperties) props).isMutable(name))
				Utils.addAttribute(attrs, "mutable", "true"); //$NON-NLS-1$ //$NON-NLS-2$
			handler.startElement(namespace, plName, pqName, attrs);
			handler.endElement(namespace, plName, pqName);
		}
	}

	private static void addAttributes(ComponentName cn, AttributesImpl attrs) {
		if (cn.getId() != null)
			Utils.addAttribute(attrs, "name", cn.getId()); //$NON-NLS-1$
		if (cn.getType() != null)
			Utils.addAttribute(attrs, "componentType", cn.getType()); //$NON-NLS-1$
	}
}
