/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.maven.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.core.rmap.parser.BidirectionalTransformerHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


class MappingsHandler extends ExtensionAwareHandler implements ChildPoppedListener
{
	private BidirectionalTransformerHandler m_transformerHandler;
	private MapEntryHandler m_mapEntryHandler;
	private TreeMap<String,MapEntry> m_entries;
	private List<BidirectionalTransformer> m_rules;

	public MappingsHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch;
		if(localName.equals(MapEntry.TAG))
		{
			if(m_mapEntryHandler == null)
				m_mapEntryHandler = new MapEntryHandler(this);
			ch = m_mapEntryHandler;
		}
		else if(localName.equals("rule"))
		{
			if(m_transformerHandler == null)
				m_transformerHandler = new BidirectionalTransformerHandler(this);
			ch = m_transformerHandler;
		}
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public void childPopped(ChildHandler child)
	{
		if(child instanceof MapEntryHandler)
		{
			if(m_entries == null)
				m_entries = new TreeMap<String,MapEntry>();
			MapEntry entry = (MapEntry)((MapEntryHandler)child).createEntry();
			m_entries.put(entry.getName(), entry);
		}
		else if(child instanceof BidirectionalTransformerHandler)
		{
			if(m_rules == null)
				m_rules = new ArrayList<BidirectionalTransformer>();
			m_rules.add(((BidirectionalTransformerHandler)child).getTransformer());
		}
	}

	public Map<String,MapEntry> getEntriesAndClear()
	{
		Map<String,MapEntry> entries = m_entries;
		m_entries = null;
		return (entries == null) ? Collections.<String,MapEntry>emptyMap() : entries;
	}

	public List<BidirectionalTransformer> getRuleAndClear()
	{
		List<BidirectionalTransformer> rules = m_rules;
		m_rules = null;
		return (rules == null) ? Collections.<BidirectionalTransformer>emptyList() : rules;
	}
}
