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

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class GroupAndArtifactHandler extends ExtensionAwareHandler {
	private String group;

	private String artifact;

	public GroupAndArtifactHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		group = this.getStringValue(attrs, GroupAndArtifact.ATTR_GROUP_ID);
		artifact = this.getStringValue(attrs, GroupAndArtifact.ATTR_ARTIFACT_ID);
	}

	GroupAndArtifact createEntry() {
		return new GroupAndArtifact(group, artifact);
	}

	String getArtifact() {
		return artifact;
	}

	String getGroup() {
		return group;
	}
}
