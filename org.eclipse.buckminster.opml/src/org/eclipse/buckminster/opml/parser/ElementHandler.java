/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.opml.parser;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;

import org.eclipse.buckminster.opml.model.Head;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Abstract child element handler for elements in the OPML schema
 *
 * @author Thomas Hallgren
 */
abstract class ElementHandler extends ChildHandler
{
	ElementHandler(AbstractHandler parent)
	{
		super(parent);
	}

	protected Date getOptionalDate(Attributes attrs, String attrName) throws SAXException
	{
		String tmp = getOptionalStringValue(attrs, attrName);
		if(tmp == null)
			return null;

		try
		{
			return Head.RFC_822_FORMAT.parse(tmp);
		}
		catch(ParseException e)
		{
			throw new SAXParseException("Attribute " + attrName + " does not represent a valid RFC822 formatted date", getDocumentLocator());
		}
	}

	protected URI getOptionalURI(Attributes attrs, String attrName) throws SAXException
	{
		String tmp = getOptionalStringValue(attrs, attrName);
		if(tmp == null)
			return null;

		try
		{
			return new URI(tmp);
		}
		catch(URISyntaxException e)
		{
			throw new SAXParseException("Attribute " +attrName + " does not represent a valid URI", getDocumentLocator());
		}
	}
}
