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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.RxAssembly;
import org.eclipse.buckminster.core.common.model.RxGroup;
import org.eclipse.buckminster.core.common.model.RxPart;
import org.eclipse.buckminster.core.common.model.RxPattern;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class RxAssemblyHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	private final HashMap<String, RxPartHandler> m_partHandlers = new HashMap<String, RxPartHandler>();

	private ArrayList<RxPart> m_parts;

	public RxAssemblyHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		return getPartHandler(this, localName, m_partHandlers);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_parts = null;
	}

	public void childPopped(ChildHandler child) throws SAXException
	{
		if(child instanceof RxPartHandler)
		{
			if(m_parts == null)
				m_parts = new ArrayList<RxPart>();
			m_parts.add(((RxPartHandler)child).createPart());
		}
	}

	protected ArrayList<RxPart> getParts()
	{
		return m_parts;
	}

	static RxPartHandler getPartHandler(ExtensionAwareHandler parent, String localName,
			Map<String, RxPartHandler> handlerCache)
	{
		synchronized(handlerCache)
		{
			RxPartHandler ch = handlerCache.get(localName);
			if(ch != null)
				return ch;

			if(RxGroup.TAG.equals(localName))
				ch = new RxGroupHandler(parent);
			else if(RxPattern.TAG.equals(localName))
				ch = new RxPatternHandler(parent);
			else
				ch = new TaggedRxPatternHandler(parent, localName);

			handlerCache.put(localName, ch);
			return ch;
		}
	}

	public RxAssembly createAssembly() throws SAXException
	{
		try
		{
			return new RxAssembly(m_parts);
		}
		catch(Exception e)
		{
			throw new SAXParseException(e.getMessage(), getDocumentLocator(), e);
		}
	}
}
