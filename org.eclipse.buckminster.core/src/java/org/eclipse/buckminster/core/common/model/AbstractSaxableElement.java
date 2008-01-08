/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractSaxableElement implements ISaxableElement
{
	public void toSax(ContentHandler handler, String namespace, String prefix, String localName)
	throws SAXException
	{
		String qName = Utils.makeQualifiedName(prefix, localName);
		AttributesImpl attrs = new AttributesImpl();
		addAttributes(attrs);
		handler.startElement(namespace, localName, qName, attrs);
		emitElements(handler, getElementNamespace(namespace), getElementPrefix(prefix));
		handler.endElement(namespace, localName, qName);
	}

	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
	}

	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
	}

	protected String getElementNamespace(String namespace) throws SAXException
	{
		return namespace;
	}

	protected String getElementPrefix(String prefix) throws SAXException
	{
		return prefix;
	}
}