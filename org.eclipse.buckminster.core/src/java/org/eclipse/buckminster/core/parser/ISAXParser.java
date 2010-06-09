/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.parser;

import org.xml.sax.ContentHandler;

public interface ISAXParser<T> extends ContentHandler {
	/**
	 * Return the result. This method should not be called until the handler has
	 * received the call to
	 * {@link ContentHandler#endElement(String, String, String)}.
	 * 
	 * @return The result produced by this parser
	 */
	T getResult();
}
