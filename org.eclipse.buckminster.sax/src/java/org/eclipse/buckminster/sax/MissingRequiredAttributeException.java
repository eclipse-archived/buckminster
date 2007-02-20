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
 * This exception is thrown when a handler attempts to obtain the
 * value of a required attribute that has not been set.
 * @author Thomas Hallgren
 */
public class MissingRequiredAttributeException extends SAXParseException
{
	private static final long serialVersionUID = 3905528185301841204L;

	/**
	 * @param tagName Name of element where attribute is required
	 * @param attrName Name of the required attribute
	 * @param locator Location in input where the error was encountered
	 */
	public MissingRequiredAttributeException(String tagName, String attrName, Locator locator)
	{
		super("Element <" + tagName +
			"> is missing the required attribute: " + attrName, locator);
	}
}
