/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.helpers.LocalizedException;

public class VersionSyntaxException extends LocalizedException
{
	private static final long serialVersionUID = -8507054890719577145L;

	private final String[] m_args;
	public VersionSyntaxException(String reason, String versionString, int errorPos)
	{
		super("Syntax error in version string {0} at position {1}: {2}");
		m_args = new String[] { versionString, Integer.toString(errorPos), reason };
		this.assignMessage();
	}

	@Override
	protected String[] getArguments()
	{
		return m_args;
	}
}
