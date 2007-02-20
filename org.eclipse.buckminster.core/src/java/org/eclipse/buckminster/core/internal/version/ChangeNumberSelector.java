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
public class ChangeNumberSelector extends QualifiedSelector
{
	private final long m_number;

	public ChangeNumberSelector(String branchName, long number, String typeInfo)
	{
		super(branchName, typeInfo);
		m_number = number;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		return super.equals(o)
			&& (o instanceof ChangeNumberSelector)
			&& m_number == ((ChangeNumberSelector)o).m_number;
	}

	@Override
	public final String getQualifier()
	{
		return Long.toString(m_number);
	}

	@Override
	public final long getNumericQualifier()
	{
		return m_number;
	}

	public VersionSelectorType getType()
	{
		return VersionSelectorType.CHANGE_NUMBER;
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + (int)(m_number ^ (m_number >>> 32));
		
		return hc;
	}

	@Override
	void qualifierToString(StringBuilder bld)
	{
		bld.append('#');
		bld.append(m_number);
	}
}
