/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.ArrayList;

import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.UnresolvedNode;
import org.eclipse.buckminster.core.query.parser.ComponentRequestHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
class UnresolvedNodeHandler extends DepNodeHandler implements ChildPoppedListener
{
	public static final String TAG = UnresolvedNode.TAG;

	private ComponentRequest m_componentRequest;
	private final ComponentRequestHandler m_requestHandler = new ComponentRequestHandler(this);
	private ArrayList<String> m_attributes;
	private AttributeRefHandler m_attributeRefHandler;

	UnresolvedNodeHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(ComponentRequestHandler.TAG.equals(localName))
			ch = m_requestHandler;
		else if(AttributeRefHandler.TAG.equals(localName))
		{
			if(m_attributeRefHandler == null)
				m_attributeRefHandler = new AttributeRefHandler(this);
			ch = m_attributeRefHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child) throws SAXParseException
	{
		if(child == m_requestHandler)
			m_componentRequest = m_requestHandler.getComponentRequest();
		else if(child == m_attributeRefHandler)
		{
			if(m_attributes == null)
				m_attributes = new ArrayList<String>();
			m_attributes.add(m_attributeRefHandler.getName());
		}
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		if(m_attributes != null)
			m_attributes.clear();
		m_componentRequest = null;
	}

	@Override
	DepNode getDepNode()
	{
		return new UnresolvedNode(new QualifiedDependency(m_componentRequest, m_attributes));
	}
}

