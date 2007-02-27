/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractElement implements ISaxableElement
{
	public final void toSax(ContentHandler handler, String namespace, String prefix, String localName) throws SAXException
	{
		AttributesImpl attrs = new AttributesImpl();
		this.addAttributes(attrs);
		String qName = Utils.makeQualifiedName(prefix, localName);
		handler.startElement(namespace, localName, qName, attrs);
		this.emitElements(handler, namespace, prefix);
		handler.endElement(namespace, localName, qName);
	}

	protected abstract void addAttributes(AttributesImpl attrs);

	protected abstract void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException;
}
