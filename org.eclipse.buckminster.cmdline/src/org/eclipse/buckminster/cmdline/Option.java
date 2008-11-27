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

public class Option
{
	private final OptionDescriptor m_optionDescriptor;

	private final String m_name;

	private final String m_value;

	private final boolean m_isLongName;

	public Option(OptionDescriptor optionDescriptor, String name, String value, boolean isLongName)
	{
		m_optionDescriptor = optionDescriptor;
		m_name = name;
		m_value = value;
		m_isLongName = isLongName;
	}

	public String getName()
	{
		return m_name;
	}

	public String getValue()
	{
		return m_value;
	}

	public boolean is(OptionDescriptor optionDescriptor)
	{
		return optionDescriptor == m_optionDescriptor;
	}

	public boolean isLongName()
	{
		return m_isLongName;
	}

	@Override
	public String toString()
	{
		StringBuffer bld = new StringBuffer();
		bld.append('-');
		if(m_isLongName)
			bld.append('-');
		bld.append(m_name);
		if(m_value != null)
		{
			bld.append(' ');
			bld.append(m_value);
		}
		return bld.toString();
	}
}
