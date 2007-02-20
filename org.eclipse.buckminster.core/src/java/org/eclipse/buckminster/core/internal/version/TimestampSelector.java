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

import java.text.ParseException;
import java.util.Date;

import org.eclipse.buckminster.core.helpers.DateAndTimeUtils;
import org.eclipse.buckminster.core.version.VersionSelectorType;


/**
 * @author Thomas Hallgren
 */
public class TimestampSelector extends QualifiedSelector
{
	private final long m_timestamp;

	public static Date parseTimestamp(String timestamp) throws ParseException
	{
		return DateAndTimeUtils.fromISOFormat(timestamp);
	}

	public TimestampSelector(String branchName, long timestamp, String typeInfo)
	{
		super(branchName, typeInfo);
		m_timestamp = timestamp;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		return super.equals(o)
			&& (o instanceof TimestampSelector)
			&& m_timestamp == ((TimestampSelector)o).m_timestamp;
	}

	@Override
	public final String getQualifier()
	{
		return DateAndTimeUtils.toISOFormat(new Date(m_timestamp));
	}

	@Override
	public final long getNumericQualifier()
	{
		return m_timestamp;
	}

	public VersionSelectorType getType()
	{
		return VersionSelectorType.TIMESTAMP;
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + (int)(m_timestamp ^ (m_timestamp >>> 32));
		
		return hc;
	}

	@Override
	void qualifierToString(StringBuilder bld)
	{
		bld.append('@');
		bld.append(this.getQualifier());
	}
}
