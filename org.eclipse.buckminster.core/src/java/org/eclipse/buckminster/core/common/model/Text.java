/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.common.model;

import org.eclipse.buckminster.sax.ISaxableElement;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 * 
 */
public class Text implements ISaxableElement {
	private final char[] content;

	public Text(char[] content) {
		this.content = content;
	}

	@Override
	public String getDefaultTag() {
		return "p"; //$NON-NLS-1$
	}

	@Override
	public void toSax(ContentHandler receiver, String namespace, String prefix, String localName) throws SAXException {
		receiver.characters(content, 0, content.length);
	}

	@Override
	public String toString() {
		return new String(content);
	}
}
