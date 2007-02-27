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

public class OptionDescriptor
{
	private final Character m_shortName;
	private final String m_longName;
	private final int m_type;
	
	public OptionDescriptor(Character shortName, String longName, int type)
	{
		m_shortName = shortName;
		m_longName = longName;
		m_type = type;
	}
	
	public OptionDescriptor(char shortName, String longName, int type)
	{
		m_shortName = new Character(shortName);
		m_longName = longName;
		m_type = type;
	}
	
	public Character getShortName()
	{
		return m_shortName;
	}
	
	public String getLongName()
	{
		return m_longName;
	}
	
	public int getType()
	{
		return m_type;
	}

	public boolean isAcceptableName(String name, boolean isLongName, boolean exact)
	{
		// short names have simple testing
		//
		if (!isLongName)
			return (m_shortName == null ? false : m_shortName.charValue() == name.charAt(0));
		
		// long names are sensitive to exact or non-exact matching
		//
		if (m_longName == null)
			return false;
		
		return (exact ? m_longName.equals(name) : m_longName.startsWith(name));
	}
}

