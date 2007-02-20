/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.parser;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class BidirectionalTransformerHandler extends ExtensionAwareHandler
{
	static final String TAG = BidirectionalTransformer.TAG;
	private BidirectionalTransformer m_transformer;

	public BidirectionalTransformerHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		try
		{
			m_transformer = new BidirectionalTransformer(
				Pattern.compile(getStringValue(attrs, BidirectionalTransformer.ATTR_TO_PATTERN)),
				getStringValue(attrs, BidirectionalTransformer.ATTR_TO_REPLACEMENT),
				Pattern.compile(getStringValue(attrs, BidirectionalTransformer.ATTR_FROM_PATTERN)),
				getStringValue(attrs, BidirectionalTransformer.ATTR_FROM_REPLACEMENT));
		}
		catch(PatternSyntaxException e)
		{
			throw new SAXParseException(e.getMessage(), getDocumentLocator());
		}
	}

	public BidirectionalTransformer getTransformer()
	{
		return m_transformer;
	}
}

