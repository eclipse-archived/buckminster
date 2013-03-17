/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.tasks;

import org.eclipse.buckminster.sax.ISaxable;
import org.eclipse.buckminster.sax.ISaxableElement;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public abstract class SAXModel implements ISaxable {
	public static void emitBooleanElement(ContentHandler receiver, String tag) throws SAXException {
		startElement(receiver, tag);
		endElement(receiver, tag);
	}

	public static void emitTextElement(ContentHandler receiver, String tag, String text) throws SAXException {
		startElement(receiver, tag);
		receiver.characters(text.toCharArray(), 0, text.length());
		endElement(receiver, tag);
	}

	public static void endElement(ContentHandler receiver, String tag) throws SAXException {
		receiver.endElement("", "", tag); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static void startElement(ContentHandler receiver, String tag) throws SAXException {
		startElement(receiver, tag, ISaxableElement.EMPTY_ATTRIBUTES);
	}

	public static void startElement(ContentHandler receiver, String tag, Attributes attrs) throws SAXException {
		receiver.startElement("", "", tag, attrs); //$NON-NLS-1$ //$NON-NLS-2$
	}
}
