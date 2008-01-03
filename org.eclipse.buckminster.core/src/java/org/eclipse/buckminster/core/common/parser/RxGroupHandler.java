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

import org.eclipse.buckminster.core.common.model.RxGroup;
import org.eclipse.buckminster.core.common.model.RxPart;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class RxGroupHandler extends RxPartHandler implements ChildPoppedListener
{
	public static final String TAG = RxGroup.TAG;

	private final HashMap<String,RxPartHandler> m_partHandlers = new HashMap<String, RxPartHandler>();

	private ArrayList<RxPart> m_parts;

	public RxGroupHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		return RxAssemblyHandler.getPartHandler(this, localName, m_partHandlers);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		m_parts = null;
	}

	@Override
	public RxPart createPart()
	{
		return new RxGroup(getName(), isOptional(), m_parts);
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
}
