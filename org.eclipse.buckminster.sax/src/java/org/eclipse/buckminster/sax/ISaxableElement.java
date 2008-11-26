/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * The ISaxable interface is implemented by model elements in Buckminster. It allows the elements to serialize
 * themselves in XML format.
 * 
 * @author Thomas Hallgren
 */
public interface ISaxableElement
{
	public static final Attributes EMPTY_ATTRIBUTES = new AttributesImpl();

	/**
	 * Returns the default local name for the element.
	 * 
	 * @return The default local name.
	 */
	String getDefaultTag();

	void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException;
}
