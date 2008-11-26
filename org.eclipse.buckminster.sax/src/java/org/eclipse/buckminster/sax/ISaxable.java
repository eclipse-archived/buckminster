/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * The ISaxable interface is implemented by root models in Buckminster. It allows the models to serialize themselves in
 * XML format.
 * 
 * @author Thomas Hallgren
 */
public interface ISaxable
{
	void toSax(ContentHandler receiver) throws SAXException;
}
