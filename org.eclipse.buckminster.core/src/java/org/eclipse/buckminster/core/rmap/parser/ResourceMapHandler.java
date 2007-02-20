/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.parser;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


/**
 * @author Thomas Hallgren
 */
public class ResourceMapHandler extends PropertyManagerHandler
{
	private SearchPathHandler m_searchPathHandler;
	private MatcherHandler.LocatorHandler m_locatorHandler;
	private MatcherHandler.RedirectHandler m_redirectHandler;
	private DocumentationHandler m_documentationHandler;
	private ResourceMap m_resourceMap;

	public ResourceMapHandler(AbstractHandler parent)
	{
		super(parent, ResourceMap.TAG);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(SearchPathHandler.TAG.equals(localName))
		{
			if(m_searchPathHandler == null)
				m_searchPathHandler = new SearchPathHandler(this);
			ch = m_searchPathHandler;
		}
		else if(MatcherHandler.LocatorHandler.TAG.equals(localName))
		{
			if(m_locatorHandler == null)
				m_locatorHandler = new MatcherHandler.LocatorHandler(this);
			ch = m_locatorHandler;
		}
		else if(MatcherHandler.RedirectHandler.TAG.equals(localName))
		{
			if(m_redirectHandler == null)
				m_redirectHandler = new MatcherHandler.RedirectHandler(this);
			ch = m_redirectHandler;
		}
		else if(DocumentationHandler.TAG.equals(localName))
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
	public void endElement(String uri, String localName, String qName)
	throws SAXException
	{
		super.endElement(uri, localName, qName);
		((ResourceMapParser)this.getTopHandler()).setResourceMap(this.getResourceMap());
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_documentationHandler)
			getResourceMap().setDocumentation(m_documentationHandler.createDocumentation());
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_resourceMap = null;
	}

	public ResourceMap getResourceMap()
	{
		if(m_resourceMap == null)
			m_resourceMap = new ResourceMap();
		return m_resourceMap;
	}

	@Override
	public ExpandingProperties getProperties()
	{
		return (ExpandingProperties)this.getResourceMap().getProperties();
	}
}

