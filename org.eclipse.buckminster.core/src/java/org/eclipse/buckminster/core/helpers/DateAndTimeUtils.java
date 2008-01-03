/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Thomas Hallgren
 */
public abstract class DateAndTimeUtils
{
	public static final String ISO_8601Pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	public static final DateFormat ISO_8601Format;
	public static final TimeZone UTC;

	public static final String[] commonFormats = new String[] { "yyyyMMddHHmm", "yyyyMMdd-HHmm", "yyyyMMdd" };
	public static final DateFormat[] commonFormatters;

	static
	{
		int idx = commonFormats.length;
		commonFormatters = new DateFormat[idx];
		while(--idx >= 0)
		{
			DateFormat dm = new SimpleDateFormat(commonFormats[idx]);
			dm.setTimeZone(DateAndTimeUtils.UTC);
			dm.setLenient(false);
			commonFormatters[idx] = dm;
		}

		ISO_8601Format = new SimpleDateFormat(ISO_8601Pattern);
		UTC = TimeZone.getTimeZone("UTC");
		ISO_8601Format.setCalendar(Calendar.getInstance(UTC));
	}

	/**
	 * Create a date by parsing a string that conforms to the ISO-8601 format
	 * &quot;yyyy-MM-dd'T'HH:mm:ss.SSSZ&quot;. The UTC time zone will be used.
	 * @param dateStr the string to parse
	 * @return the resulting date or <code>null</code> if the <code>dateStr</code> was <code>null</code>.
	 * @throws ParseException if <code>dateStr</code> does not conform to the expected format.
	 */
	public static Date fromISOFormat(String dateStr) throws ParseException
	{
		if(dateStr == null)
			return null;

		synchronized(ISO_8601Format)
		{
			return ISO_8601Format.parse(dateStr);
		}
	}

	/**
	 * Create a string from <code>date</code> that is formatted according to ISO-8601 format
	 * &quot;yyyy-MM-dd'T'HH:mm:ss.SSSZ&quot; and using the UTC time zone.
	 * 
	 * @param date the
	 *            date that should be formatted.
	 * @return The formatted String or <code>null</code> if the argument is null.
	 */
	public static String toISOFormat(Date date)
	{
		if(date == null)
			return null;

		synchronized(ISO_8601Format)
		{
			return ISO_8601Format.format(date);
		}
	}

	public static Date fromString(String timestampStr) throws ParseException
	{
		for(int idx = 0; idx < commonFormatters.length; ++idx)
		{
			try
			{
				return parseSaneDate(commonFormatters[idx], timestampStr);
			}
			catch(ParseException e1)
			{
			}
		}
		throw new ParseException("Unable to parse " + timestampStr + " as a timestamp", 0);
	}

	// Milliseconds corresponding to approximately 10 years
	//
	private static final long SANITY_THRESHOLD = (10 * 365 + 3) * 24 * 60 * 60 * 1000;

	private static Date parseSaneDate(DateFormat mf, String str) throws ParseException
	{
		long now = System.currentTimeMillis();
		Date dt;
		synchronized(mf)
		{
			dt = mf.parse(str);
		}
		long tm = dt.getTime();
		if(tm > now + SANITY_THRESHOLD || tm < now - SANITY_THRESHOLD)
			throw new ParseException("Bogus", 0);
		return dt;
	}
}
