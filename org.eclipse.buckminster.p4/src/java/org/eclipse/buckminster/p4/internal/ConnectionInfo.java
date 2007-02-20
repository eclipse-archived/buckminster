/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.util.Formatter;
import java.util.TimeZone;

/**
 * @author thhal
 */
public class ConnectionInfo
{
	private final String m_user;

	private final String m_client;

	private final String m_clientRoot;

	private final String m_address;

	private final boolean m_securityEnabled;
	
	private final TimeZone m_timeZone;

	public ConnectionInfo(String user, String client, String root, String address, TimeZone timeZone, boolean securityEnabled)
	{
		m_user = user;
		m_client = client;
		m_clientRoot = root;
		m_address = address;
		m_timeZone = timeZone;
		m_securityEnabled = securityEnabled;
	}

	private static String asString(String str)
	{
		return str == null ? "null" : str;
	}

	@Override
	public String toString()
	{
		Formatter fmt = new Formatter();
		fmt.format("user: %s%nclient: %s%nroot: %s%naddress: %s%ntimeZone: %s%nsecurity: %s",
			asString(m_user),
			asString(m_client),
			asString(m_clientRoot),
			asString(m_address),
			m_timeZone.getDisplayName(true, TimeZone.SHORT),
			m_securityEnabled ? "true" : "false");
		return fmt.toString();
	}

	/**
	 * @return Returns the address.
	 */
	public final String getAddress()
	{
		return m_address;
	}

	/**
	 * @return Returns the client.
	 */
	public final String getClient()
	{
		return m_client;
	}

	/**
	 * @return Returns the root.
	 */
	public final String getClientRoot()
	{
		return m_clientRoot;
	}

	/**
	 * @return Returns the securityEnabled.
	 */
	public final boolean isSecurityEnabled()
	{
		return m_securityEnabled;
	}

	/**
	 * @return Returns the user.
	 */
	public final String getUser()
	{
		return m_user;
	}

	/**
	 * @return Returns the timeZone.
	 */
	public final TimeZone getTimeZone()
	{
		return m_timeZone;
	}
}

