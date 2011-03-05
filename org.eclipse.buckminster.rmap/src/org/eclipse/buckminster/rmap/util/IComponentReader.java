/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.rmap.util;

import java.io.Closeable;
import java.util.Map;

/**
 * A Component reader knows how to read a component stored at some arbitrary
 * location. An instance of a component reader is always associated with one
 * specific component.
 * 
 * @see IReaderType
 * @author thhal
 */
public interface IComponentReader extends Closeable {
	/**
	 * Returns the properties defined for the readers context
	 */
	Map<String, String> getProperties();

	/**
	 * Returns the reader type ID
	 */
	String getReaderTypeID();
}
