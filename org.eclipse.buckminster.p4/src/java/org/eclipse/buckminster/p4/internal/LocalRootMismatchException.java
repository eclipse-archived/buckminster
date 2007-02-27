/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.p4.internal;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.core.runtime.IPath;

public class LocalRootMismatchException extends LocalizedException
{
	private static final long serialVersionUID = -2062206287757785210L;

	private final String[] m_args;
	public LocalRootMismatchException(String clientName, String p4Port, IPath preferencePath, IPath serverPath)
	{
		super("The local path declared in P4 preferences for client {0} and p4 port {1} is {2}. " +
				"It should be {3} according to the client specification found on the server");

		m_args = new String[] {
			clientName, p4Port, preferencePath.toOSString(), serverPath.toOSString() };
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_args;
	}
}
