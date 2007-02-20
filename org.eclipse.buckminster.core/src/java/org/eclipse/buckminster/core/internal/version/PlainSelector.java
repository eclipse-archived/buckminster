/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.internal.version;

import org.eclipse.buckminster.core.version.VersionSelectorType;

/**
 * @author Thomas Hallgren
 */
public class PlainSelector extends VersionSelector
{
	private static final long serialVersionUID = -4830983748084572892L;

	private final String m_version;

	public PlainSelector(String version)
	{
		m_version = version;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		if(!(o instanceof PlainSelector))
			return false;
		PlainSelector that = (PlainSelector)o;

		if(!this.m_version.equals(that.m_version))
			return false;

		return true;
	}

	public String getBranchName()
	{
		return null;
	}

	public String getQualifier()
	{
		return null;
	}

	public long getNumericQualifier()
	{
		return 0;
	}

	public VersionSelectorType getType()
	{
		return VersionSelectorType.PLAIN;
	}

	public String getTypeInfo()
	{
		return null;
	}

	@Override
	public int hashCode()
	{
		return m_version.hashCode();
	}

	@Override
	public String toString()
	{
		return m_version;
	}
}
