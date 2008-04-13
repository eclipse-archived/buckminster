/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.opml.parser;

import org.eclipse.buckminster.opml.builder.BodyBuilder;
import org.eclipse.buckminster.opml.builder.OutlineBuilder;
import org.eclipse.buckminster.opml.model.Body;
import org.eclipse.buckminster.opml.model.Outline;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * SAX Parser for the OPML Outline element
 *
 * @author Thomas Hallgren
 */
class OutlineHandler extends BodyHandler
{
	@SuppressWarnings("hiding")
	public static final String TAG = Outline.TAG;

	OutlineHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		OutlineBuilder bld = (OutlineBuilder)getBodyBuilder();
		bld.clear();
		bld.setCategory(getOptionalStringValue(attrs, Outline.ATTR_CATEGORY));		
		bld.setCreated(getOptionalDate(attrs, Outline.ATTR_CREATED));
		bld.setDescription(getOptionalStringValue(attrs, Outline.ATTR_DESCRIPTION));
		bld.setHtmlUrl(getOptionalURI(attrs, Outline.ATTR_HTML_URL));
		bld.setBreakpoint(getOptionalBooleanValue(attrs, Outline.ATTR_IS_BREAKPOINT, false));
		bld.setComment(getOptionalBooleanValue(attrs, Outline.ATTR_IS_COMMENT, false));
		bld.setLanguage(getOptionalStringValue(attrs, Outline.ATTR_LANGUAGE));		
		bld.setText(getStringValue(attrs, Outline.ATTR_TEXT));		
		bld.setTitle(getOptionalStringValue(attrs, Outline.ATTR_TITLE));		
		bld.setTypeString(getOptionalStringValue(attrs, Outline.ATTR_TYPE));		
		bld.setUrl(getOptionalURI(attrs, Outline.ATTR_URL));		
		bld.setVersion(getOptionalStringValue(attrs, Outline.ATTR_VERSION));		
		bld.setXmlUrl(getOptionalURI(attrs, Outline.ATTR_XML_URL));		
	}

	@Override
	public Body getBody()
	{
		return new Outline((OutlineBuilder)getBodyBuilder());
	}

	@Override
	protected BodyBuilder createBodyBuilder()
	{
		return new OutlineBuilder();
	}
}
