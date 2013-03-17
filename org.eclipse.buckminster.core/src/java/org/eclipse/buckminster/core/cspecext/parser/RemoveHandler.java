/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class RemoveHandler extends ExtensionAwareHandler {
	private final String tag;

	private final String attrName;

	private String name;

	RemoveHandler(AbstractHandler parent, String tag, String attrName) {
		super(parent);
		this.tag = tag;
		this.attrName = attrName;
	}

	@Override
	public final String getTAG() {
		return tag;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException {
		name = this.getStringValue(attrs, attrName);
	}

	final String getValue() {
		return name;
	}
}
