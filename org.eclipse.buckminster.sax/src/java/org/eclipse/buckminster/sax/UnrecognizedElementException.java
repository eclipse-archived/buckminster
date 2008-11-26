/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

/**
 * Thrown when an element is encountered for which no corresponding {@link ChildHandler} can be found.
 * 
 * @author Thomas Hallgren
 */
public class UnrecognizedElementException extends SAXParseException
{
	private static final long serialVersionUID = 3689917275172059185L;

	public UnrecognizedElementException(String inTag, String qName, Locator locator)
	{
		super("Unrecognized element <" + qName + "> encountered in tag <" + inTag + ">", locator);
	}
}
