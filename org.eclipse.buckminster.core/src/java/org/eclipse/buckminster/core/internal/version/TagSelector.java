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
public class TagSelector extends QualifiedSelector
{
	private final String m_labelName;

	public TagSelector(String branchName, String labelName, String typeInfo)
	{
		super(branchName, typeInfo);
		m_labelName = labelName;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		return super.equals(o)
			&& (o instanceof TagSelector)
			&& this.m_labelName.equals(((TagSelector)o).m_labelName);
	}

	@Override
	public final String getQualifier()
	{
		return m_labelName;
	}

	public VersionSelectorType getType()
	{
		return VersionSelectorType.TAG;
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + m_labelName.hashCode();

		return hc;
	}

	@Override
	void qualifierToString(StringBuilder bld)
	{
		bld.append('/');
		bld.append(m_labelName);
	}
}
