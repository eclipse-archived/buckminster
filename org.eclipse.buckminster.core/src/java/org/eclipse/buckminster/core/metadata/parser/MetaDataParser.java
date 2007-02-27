/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.List;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.parser.AbstractParser;
import org.eclipse.buckminster.core.parser.ParserFactory;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
abstract class MetaDataParser<T> extends AbstractParser<T> implements ChildPoppedListener
{
	public MetaDataParser(List<ParserFactory.ParserExtension> parserExtensions)
	throws SAXException
	{
		this(parserExtensions, false);
	}

	public MetaDataParser(List<ParserFactory.ParserExtension> parserExtensions, boolean validating)
	throws SAXException
	{
		super(parserExtensions, new String[]
  		{
			XMLConstants.XHTML_NS,
			XMLConstants.XML_NS,
			XMLConstants.BM_COMMON_NS,
			XMLConstants.BM_CQUERY_NS,
			XMLConstants.BM_CSPEC_NS,
			XMLConstants.BM_METADATA_NS,
			XMLConstants.BM_RMAP_NS
		}, new String[]
   		{
			XMLConstants.XHTML_RESOURCE,
			XMLConstants.XML_RESOURCE,
 			XMLConstants.BM_COMMON_RESOURCE,
 			XMLConstants.BM_CQUERY_RESOURCE,
 			XMLConstants.BM_CSPEC_RESOURCE,
 			XMLConstants.BM_METADATA_RESOURCE,
 			XMLConstants.BM_RMAP_RESOURCE
 		}, validating);
	}
}

