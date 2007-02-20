/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import org.eclipse.buckminster.core.cspec.model.NamedElement;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class AttributeRefHandler extends ExtensionAwareHandler
{
	public static final String TAG = UnresolvedNode.ELEM_ATTRIBUTE;
	private String m_name;

	public AttributeRefHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_name = getStringValue(attrs, NamedElement.ATTR_NAME);
	}

	final String getName()
	{
		return m_name;
	}
}
