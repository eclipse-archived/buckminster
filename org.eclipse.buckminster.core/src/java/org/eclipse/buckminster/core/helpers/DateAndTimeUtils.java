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

	static
	{
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
}
