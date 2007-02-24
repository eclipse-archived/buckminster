/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.common.model.Format;
import org.eclipse.buckminster.core.common.model.ValueHolder;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class PropertyElementHandler extends PropertyHandler implements ChildPoppedListener
{
	static final String TAG = "propertyElement";

	private ConstantHandler m_constantHandler;
	private FormatHandler m_formatHandler;
	private PropertyRefHandler m_propertyRefHandler;
	private ReplaceHandler m_replaceHandler;
	private SplitHandler m_splitHandler;
	private ToLowerHandler m_toLowerHandler;
	private ToUpperHandler m_toUpperHandler;

	private ValueHolder m_source;

	public PropertyElementHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(ConstantHandler.TAG.equals(localName))
		{
			if(m_constantHandler == null)
				m_constantHandler = new ConstantHandler(this);
			ch = m_constantHandler;
		}
		else if(Format.TAG.equals(localName))
		{
			if(m_formatHandler == null)
				m_formatHandler = new FormatHandler(this);
			ch = m_formatHandler;
		}
		else if(PropertyRefHandler.TAG.equals(localName))
		{
			if(m_propertyRefHandler == null)
				m_propertyRefHandler = new PropertyRefHandler(this);
			ch = m_propertyRefHandler;
		}
		else if(ReplaceHandler.TAG.equals(localName))
		{
			if(m_replaceHandler == null)
				m_replaceHandler = new ReplaceHandler(this);
			ch = m_replaceHandler;
		}
		else if(SplitHandler.TAG.equals(localName))
		{
			if(m_splitHandler == null)
				m_splitHandler = new SplitHandler(this);
			ch = m_splitHandler;
		}
		else if(ToLowerHandler.TAG.equals(localName))
		{
			if(m_toLowerHandler == null)
				m_toLowerHandler = new ToLowerHandler(this);
			ch = m_toLowerHandler;
		}
		else if(ToUpperHandler.TAG.equals(localName))
		{
			if(m_toUpperHandler == null)
				m_toUpperHandler = new ToUpperHandler(this);
			ch = m_toUpperHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child)
	{
		m_source = ((ValueHandler)child).getValueHolder();
	}

	@Override
	void addYourself(Map<String,String> props)
	{
		String key = getKey();
		if(props instanceof ExpandingProperties)
		{
			m_source.setMutable(getMutable());
			((ExpandingProperties)props).setProperty(key, m_source);
		}
		else
			props.put(key, m_source.getValue(props));
	}
}

