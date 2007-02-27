/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.maven.internal;

import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.rmap.model.BidirectionalTransformer;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.rmap.parser.ProviderHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;


public class MavenProviderHandler extends ProviderHandler
{
	private final MappingsHandler m_mappingsHandler = new MappingsHandler(this);

	private Map<String,MapEntry> m_mappings;
	private List<BidirectionalTransformer> m_rules;

	public MavenProviderHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		super.handleAttributes(attrs);
		m_mappings = null;
		m_rules = null;
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch;
		if(MavenProvider.ELEM_MAPPINGS.equals(localName))
			ch = m_mappingsHandler;
		else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void childPopped(ChildHandler child)
	{
		if(child instanceof MappingsHandler)
		{
			m_mappings = ((MappingsHandler)child).getEntriesAndClear();
			m_rules = ((MappingsHandler)child).getRuleAndClear();
		}
		else
			super.childPopped(child);
	}

	@Override
	public Provider getProvider()
	{
		return new MavenProvider(
				getReaderType(),
				getComponentType(),
				getManagedCategories(),
				getVersionConverter(),
				getUriFormat(),
				isMutable(),
				isSource(),
				getDocumentation(),
				m_mappings,
				m_rules);
	}
}
