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


/**
 * @author Thomas Hallgren
 *
 */
public abstract class AbstractVersion implements IVersion
{
	private final IVersionType m_type;
	
	protected AbstractVersion(IVersionType type)
	{
		m_type = type;
	}

	public String getQualifier()
	{
		return null;
	}

	public IVersionType getType()
	{
		return m_type;
	}

	public IVersion replaceQualifier(String string)
	{
		return this;
	}

	public long toLong()
	{
		return -1;
	}

	@Override
	public String toString()
	{
		StringBuilder bld = new StringBuilder();
		this.toString(bld);
		return bld.toString();
	}
}
