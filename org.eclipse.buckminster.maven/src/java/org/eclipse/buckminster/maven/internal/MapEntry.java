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

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

class MapEntry extends GroupAndArtifact {
	public static final String TAG = "entry"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	private final String name;

	private final List<GroupAndArtifact> aliases;

	public MapEntry(String name, String groupId, String artifactId, List<GroupAndArtifact> aliases) {
		super(groupId, artifactId);
		this.name = name;
		this.aliases = Utils.createUnmodifiableList(aliases);
	}

	public List<GroupAndArtifact> getAliases() {
		return aliases;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public final String getName() {
		return name;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_NAME, name);
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException {
		for (GroupAndArtifact alias : aliases)
			alias.toSax(receiver, namespace, prefix, GroupAndArtifact.ALIAS_TAG);
	}
}
