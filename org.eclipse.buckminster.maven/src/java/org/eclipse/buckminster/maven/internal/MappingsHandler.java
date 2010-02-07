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

class MappingsHandler extends ExtensionAwareHandler implements ChildPoppedListener {
	private BidirectionalTransformerHandler transformerHandler;

	private MapEntryHandler mapEntryHandler;

	private TreeMap<String, MapEntry> entries;

	private List<BidirectionalTransformer> rules;

	public MappingsHandler(AbstractHandler parent) {
		super(parent);
	}

	public void childPopped(ChildHandler child) {
		if (child instanceof MapEntryHandler) {
			if (entries == null)
				entries = new TreeMap<String, MapEntry>();
			MapEntry entry = (MapEntry) ((MapEntryHandler) child).createEntry();
			entries.put(entry.getName(), entry);
		} else if (child instanceof BidirectionalTransformerHandler) {
			if (rules == null)
				rules = new ArrayList<BidirectionalTransformer>();
			rules.add(((BidirectionalTransformerHandler) child).getTransformer());
		}
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (localName.equals(MapEntry.TAG)) {
			if (mapEntryHandler == null)
				mapEntryHandler = new MapEntryHandler(this);
			ch = mapEntryHandler;
		} else if (localName.equals("rule")) //$NON-NLS-1$
		{
			if (transformerHandler == null)
				transformerHandler = new BidirectionalTransformerHandler(this);
			ch = transformerHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	public Map<String, MapEntry> getEntriesAndClear() {
		Map<String, MapEntry> entryMap = entries;
		entries = null;
		return (entryMap == null) ? Collections.<String, MapEntry> emptyMap() : entryMap;
	}

	public List<BidirectionalTransformer> getRuleAndClear() {
		List<BidirectionalTransformer> ruleList = rules;
		rules = null;
		return (ruleList == null) ? Collections.<BidirectionalTransformer> emptyList() : ruleList;
	}
}
