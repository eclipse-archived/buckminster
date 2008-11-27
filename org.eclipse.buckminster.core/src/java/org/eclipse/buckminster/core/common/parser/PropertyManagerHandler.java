/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import java.util.Map;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public abstract class PropertyManagerHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	private PropertyConstantHandler m_propertyConstantHandler;

	private PropertyElementHandler m_propertyElementHandler;

	private final String m_tag;

	public PropertyManagerHandler(AbstractHandler parent, String tag)
	{
		super(parent);
		m_tag = tag;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child instanceof PropertyHandler)
			((PropertyHandler)child).addYourself(getProperties());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(PropertyConstantHandler.TAG.equals(localName))
		{
			if(m_propertyConstantHandler == null)
				m_propertyConstantHandler = new PropertyConstantHandler(this);
			ch = m_propertyConstantHandler;
		}
		else if(PropertyElementHandler.TAG.equals(localName))
		{
			if(m_propertyElementHandler == null)
				m_propertyElementHandler = new PropertyElementHandler(this);
			ch = m_propertyElementHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public abstract Map<String, String> getProperties();

	@Override
	public String getTAG()
	{
		return m_tag;
	}
}
