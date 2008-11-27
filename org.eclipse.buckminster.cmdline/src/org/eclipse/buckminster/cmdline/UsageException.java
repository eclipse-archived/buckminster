/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.cmdline;

/**
 * Exception thrown while parsing the command line. The message is printed as an information to the invoking user.
 * 
 * @author Thomas Hallgren
 */
public class UsageException extends Exception
{
	private static final long serialVersionUID = 8048232638062127928L;

	private final boolean m_emitHelp;

	public UsageException(String msg)
	{
		this(msg, true);
	}

	public UsageException(String msg, boolean emitHelp)
	{
		super(msg);
		m_emitHelp = emitHelp;
	}

	public boolean isEmitHelp()
	{
		return m_emitHelp;
	}
}
