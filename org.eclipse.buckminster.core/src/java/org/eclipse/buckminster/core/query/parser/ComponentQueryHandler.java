/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.query.parser;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.core.common.parser.DocumentationHandler;
import org.eclipse.buckminster.core.common.parser.PropertyManagerHandler;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * @author Thomas Hallgren
 */
public class ComponentQueryHandler extends PropertyManagerHandler
{
	private final ComponentRequestHandler m_componentRequestHandler = new ComponentRequestHandler(this);
	private DocumentationHandler m_documentationHandler;
	private AdvisorNodeHandler m_advisorNodeHandler;

	private final ComponentQueryBuilder m_builder = new ComponentQueryBuilder();

	public ComponentQueryHandler(AbstractHandler parent)
	{
		super(parent, ComponentQuery.TAG);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child == m_componentRequestHandler)
			m_builder.setRootRequest(m_componentRequestHandler.getComponentRequest());
		else if(child == m_advisorNodeHandler)
			m_builder.addAdvisorNode(m_advisorNodeHandler.getAdvisorNodeBuilder());
		else if(child == m_documentationHandler)
			m_builder.setDocumentation(m_documentationHandler.createDocumentation());
		else
			super.childPopped(child);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(ComponentQuery.ELEM_ROOT_REQUEST.equals(localName))
			ch = m_componentRequestHandler;
		else if(AdvisorNodeHandler.TAG.equals(localName))
		{
			if(m_advisorNodeHandler == null)
				m_advisorNodeHandler = new AdvisorNodeHandler(this);
			ch = m_advisorNodeHandler;
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

	public ComponentQuery getComponentQuery() throws SAXException
	{
		if(m_builder.getRootRequest() == null)
			throw new SAXParseException("Missing required element <" +
					XMLConstants.BM_CQUERY_NS + '.' + ComponentQuery.ELEM_ROOT_REQUEST + '>',
					this.getDocumentLocator());
		return m_builder.createComponentQuery();
	}

	@Override
	public Map<String,String> getProperties()
	{
		return m_builder.getProperties();
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		try
		{
			m_builder.clear();
			String tmp = getOptionalStringValue(attrs, ComponentQuery.ATTR_PROPERTIES);
			if(tmp != null)
				m_builder.setPropertiesURL(new URL(tmp));

			tmp = getOptionalStringValue(attrs, ComponentQuery.ATTR_RESOURCE_MAP);
			if(tmp != null)
				m_builder.setResourceMapURL(new URL(tmp));
			m_builder.setShortDesc(getOptionalStringValue(attrs, ComponentQuery.ATTR_SHORT_DESC));
		}
		catch(MalformedURLException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator());
		}
	}
}

