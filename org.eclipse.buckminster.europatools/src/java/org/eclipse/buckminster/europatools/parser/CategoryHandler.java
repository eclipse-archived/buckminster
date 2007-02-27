/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.parser;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.europatools.model.Category;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class CategoryHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	private String m_name;
	private String m_label;
	private Documentation m_description;
	private DocumentationHandler m_documentationHandler;

	public CategoryHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(Category.DESCRIPTION_TAG.equals(localName))
		{
			if(m_documentationHandler == null)
				m_documentationHandler = new DocumentationHandler(this);
			ch = m_documentationHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_name = getStringValue(attrs, Category.ATTR_NAME);
		m_label = getOptionalStringValue(attrs, Category.ATTR_LABEL);
		m_description = null;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_documentationHandler)
			m_description = m_documentationHandler.createDocumentation();
	}

	Category getCategory()
	{
		return new Category(m_name, m_label, m_description);
	}
}
