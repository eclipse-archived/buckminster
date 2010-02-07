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

import java.util.Map;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

public abstract class PropertyHandler extends ExtensionAwareHandler {
	private String key;

	private boolean mutable;

	public PropertyHandler(AbstractHandler parent) {
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		key = this.getStringValue(attrs, "key"); //$NON-NLS-1$
		mutable = getOptionalBooleanValue(attrs, "mutable", false); //$NON-NLS-1$
	}

	abstract void addYourself(Map<String, String> props);

	final String getKey() {
		return key;
	}

	final boolean getMutable() {
		return mutable;
	}
}
