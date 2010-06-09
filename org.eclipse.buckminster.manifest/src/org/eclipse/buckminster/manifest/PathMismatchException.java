/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.manifest;

import org.eclipse.buckminster.runtime.BuckminsterException;

public class PathMismatchException extends BuckminsterException
{
	private static final long serialVersionUID = 494929038187184807L;

	public PathMismatchException(String root, String path)
	{
		super(root + " is not a root for " + path);
	}
}
