/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.manifest;

import org.eclipse.buckminster.runtime.BuckminsterException;

public class ChecksumMismatchException extends BuckminsterException
{
	private static final long serialVersionUID = -4146695538865611025L;

	public ChecksumMismatchException(Checksum c1, Checksum c2)
	{
		super("Checksums are not equal : " + c1 + " <=> " + c2);
	}
}
