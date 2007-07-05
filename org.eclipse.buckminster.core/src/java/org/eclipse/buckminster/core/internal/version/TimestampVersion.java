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

import java.util.Date;

import org.eclipse.buckminster.core.version.AbstractVersion;
import org.eclipse.buckminster.core.version.IVersion;

/**
 * @author Thomas Hallgren
 */
public class TimestampVersion extends AbstractVersion
{
	private final Date m_timestamp;

	TimestampVersion(TimestampVersionType type, Date date)
	{
		super(type);
		m_timestamp = date;
	}

	/**
	 * Compares this <code>Version</code> object to another object.
	 * 
	 * @param object The <code>Version</code> object to be compared.
	 * @return A negative integer, zero, or a positive integer if this object is
	 *         less than, equal to, or greater than the specified
	 *         <code>Version</code> object.
	 * @throws ClassCastException If the specified object is not a
	 *         <code>TimestampVersion</code>.
	 */
	public int compareTo(IVersion o)
	{
		if(!(o instanceof TimestampVersion))
			throw new IllegalArgumentException("Not a TimestampVersion");

		// The following line throws a ClassCastException unless
		// o is indeed a Version. That's OK and expected.
		//
		return getTimestamp().compareTo(((TimestampVersion)o).getTimestamp());
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof TimestampVersion))
			return false;
		TimestampVersion that = (TimestampVersion)o;

		if (this.compareTo(that) != 0)
			return false;

		return true;
	}

	public final Date getTimestamp()
	{
		return m_timestamp;
	}

	@Override
	public int hashCode()
	{
		return m_timestamp.hashCode();
	}

	@Override
	public long toLong()
	{
		return m_timestamp.getTime();
	}

	/**
	 * Returns the string representation of this version identifier. The format
	 * of the version string will be <code>major.minor.micro</code> if
	 * qualifier is the <code>null</code> or
	 * <code>major.minor.micro.qualifier</code> otherwise.
	 * 
	 * @return The string representation of this version identifier.
	 */
	@Override
	public String toString()
	{
		return TimestampVersionType.toString(this);
	}

	public void toString(StringBuilder bld)
	{
		bld.append(TimestampVersionType.toString(this));
	}
}
