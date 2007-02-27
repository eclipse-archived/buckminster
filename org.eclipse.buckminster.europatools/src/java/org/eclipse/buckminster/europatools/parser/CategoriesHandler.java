/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.europatools.parser;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.europatools.model.Category;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

class CategoriesHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	private CategoryHandler m_categoryHandler;
	private List<Category> m_categorys;

	public CategoriesHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(Category.TAG.equals(localName))
		{
			if(m_categoryHandler == null)
				m_categoryHandler = new CategoryHandler(this);
			ch = m_categoryHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child)
	{
		if(child == m_categoryHandler)
		{
			if(m_categorys == null)
				m_categorys = new ArrayList<Category>();
			m_categorys.add(m_categoryHandler.getCategory());
		}
	}

	public List<Category> getCategorysAndClear()
	{
		List<Category> Categorys = m_categorys;
		m_categorys = null;
		return Categorys;
	}
}
