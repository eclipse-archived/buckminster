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

public class NonmatchingManifestsException extends BuckminsterException
{
	private static final long serialVersionUID = -9133903877802074178L;

	public NonmatchingManifestsException(Manifest mf1, Manifest mf2)
	{
		super("Manifest " + mf1 + " and " + mf2 + " does not match");
	}
}
