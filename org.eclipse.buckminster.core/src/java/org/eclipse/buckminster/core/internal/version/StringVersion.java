/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.internal.version;

import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionFactory;

/**
 * @author Thomas Hallgren
 *
 */
public class StringVersion extends AbstractVersion
{
	private final String m_version;

	StringVersion(StringVersionType type, String version)
	{
		super(type);
		m_version = version;
	}

	public int compareTo(IVersion o)
	{
		if(o == VersionFactory.defaultVersion())
			return 1;
		if(!(o instanceof StringVersion))
			return -1;
		return m_version.compareTo(((StringVersion)o).m_version);
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof StringVersion))
			return false;
		StringVersion that = (StringVersion)o;

		if (!this.m_version.equals(that.m_version))
			return false;

		return true;
	}

	@Override
	public int hashCode()
	{
		return m_version.hashCode();
	}

	public boolean isDefault()
	{
		return false;
	}

	@Override
	public long toLong()
	{
		try
		{
			return Long.parseLong(this.toString());
		}
		catch(NumberFormatException e)
		{
			return -1;
		}
	}

	@Override
	public String toString()
	{
		return m_version;
	}

	public void toString(StringBuilder bld)
	{
		bld.append(m_version);
	}
}
