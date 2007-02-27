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

import org.eclipse.buckminster.runtime.Trivial;

/**
 * @author Thomas Hallgren
 */
abstract class QualifiedSelector extends VersionSelector
{
	private final String m_branchName;

	private final String m_typeInfo;

	protected QualifiedSelector(String branchName, String typeInfo)
	{
		m_typeInfo = typeInfo;
		m_branchName = branchName;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		if(!(o instanceof QualifiedSelector))
			return false;
		QualifiedSelector that = (QualifiedSelector)o;

		return Trivial.equalsAllowNull(m_branchName, that.m_branchName)
			&& Trivial.equalsAllowNull(m_typeInfo, that.m_typeInfo);
	}

	/**
	 * @return Returns the branchName.
	 */
	public final String getBranchName()
	{
		return m_branchName;
	}

	public String getQualifier()
	{
		return null;
	}

	public long getNumericQualifier()
	{
		return 0;
	}
	public final String getTypeInfo()
	{
		return m_typeInfo;
	}


	@Override
	public int hashCode()
	{
		int hashCode = m_branchName.hashCode();
		if(m_typeInfo != null)
			hashCode = hashCode * 31 + m_typeInfo.hashCode();
		return hashCode;
	}

	@Override
	public String toString()
	{
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	public final void toString(StringBuilder bld)
	{
		if(!DEFAULT_BRANCH.equals(m_branchName))
			bld.append(m_branchName);
		qualifierToString(bld);
		if(m_typeInfo != null)
		{
			bld.append('|');
			bld.append(m_typeInfo);
		}
	}

	abstract void qualifierToString(StringBuilder bld);
}
