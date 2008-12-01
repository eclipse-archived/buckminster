/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline;

import org.eclipse.osgi.util.NLS;

class IllegalCommandAliasException extends RuntimeException
{
	private static final long serialVersionUID = 680587450449028199L;

	public IllegalCommandAliasException(String message)
	{
		super(NLS.bind(Messages.IllegalCommandAliasException_The_command_alias_0_is_illegal, message));
	}
}
