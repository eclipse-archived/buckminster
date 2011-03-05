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
 * @author Matt Biggs
 */
class ScopeHandler extends ExtensionAwareHandler {
	private String name;

	private boolean exclude;

	public ScopeHandler(AbstractHandler parent) {
		super(parent);
	}

	Scope createEntry() {
		return new Scope(name, exclude);
	}

	String getName() {
		return name;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		name = this.getStringValue(attrs, Scope.ATTR_NAME);
		exclude = this.getBooleanValue(attrs, Scope.ATTR_EXCLUDE);
	}

	boolean isExcluded() {
		return exclude;
	}
}
