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

import java.io.InputStream;

import org.eclipse.core.runtime.CoreException;

public interface IParser<T>
{
	/**
	 * Parse the input stream and deliver a result.
	 * 
	 * @param systemId
	 *            The \"name\" of the input stream
	 * @param stream
	 *            The stream that provides the input
	 * @return The internalized model
	 * @throws CoreException
	 *             parsing failed for some reason.
	 */
	T parse(String systemId, InputStream stream) throws CoreException;
}
