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

import org.eclipse.buckminster.core.metadata.model.UUIDKeyed;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


class MapEntry extends GroupAndArtifact
{
	@SuppressWarnings("hiding")
	public static final String TAG = "entry";
	public static final String ATTR_NAME = "name";

	private final String m_name;
	private List<GroupAndArtifact> m_aliases;

	public MapEntry(String name, String groupId, String artifactId, List<GroupAndArtifact> aliases)
	{
		super(groupId, artifactId);
		m_name = name;
		m_aliases = UUIDKeyed.createUnmodifiableList(aliases);
	}

	public List<GroupAndArtifact> getAliases()
	{
		return m_aliases;
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	public final String getName()
	{
		return m_name;
	}

	@Override
	void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		for(GroupAndArtifact alias : m_aliases)
			alias.toSax(receiver, namespace, prefix, GroupAndArtifact.ALIAS_TAG);
	}

	@Override
	void addAttributes(AttributesImpl attrs) throws SAXException
	{
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_NAME, m_name);
	}
}
