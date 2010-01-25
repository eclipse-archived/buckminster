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

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.core.rmap.model.SearchPath;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class SearchPathHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	static final String TAG = SearchPath.TAG;

	private SearchPath m_searchPath;

	public SearchPathHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child instanceof ProviderHandler)
			m_searchPath.addProvider(((ProviderHandler)child).getProvider());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(ProviderHandler.TAG.equals(localName))
			ch = createContentHandler(ProviderHandler.class, uri, attrs);
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		ResourceMap rmap = ((ResourceMapHandler)getParentHandler()).getResourceMap();
		m_searchPath = new SearchPath(rmap, getStringValue(attrs, SearchPath.ATTR_NAME));
		rmap.addSearchPath(m_searchPath);
	}

	SearchPath getSearchPath()
	{
		return m_searchPath;
	}
}
