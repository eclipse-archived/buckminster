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
import java.util.List;

import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.eclipse.buckminster.sax.ChildPoppedListener;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class MapEntryHandler extends GroupAndArtifactHandler implements ChildPoppedListener {
	private GroupAndArtifactHandler aliasHandler;

	private List<GroupAndArtifact> aliases;

	private String name;

	public MapEntryHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void childPopped(ChildHandler child) throws SAXException {
		if (aliases == null)
			aliases = new ArrayList<GroupAndArtifact>();
		aliases.add(((GroupAndArtifactHandler) child).createEntry());
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException {
		ChildHandler ch;
		if (localName.equals(GroupAndArtifact.ALIAS_TAG)) {
			if (aliasHandler == null)
				aliasHandler = new GroupAndArtifactHandler(this);
			ch = aliasHandler;
		} else
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		super.handleAttributes(attrs);
		name = getStringValue(attrs, MapEntry.ATTR_NAME);
		if (aliases != null)
			aliases.clear();
	}

	@Override
	GroupAndArtifact createEntry() {
		return new MapEntry(name, getGroup(), getArtifact(), aliases);
	}

	String getName() {
		return name;
	}
}
