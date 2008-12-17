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

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionSyntaxException;

/**
 * @author Thomas Hallgren
 */
public class TimestampVersionType extends AbstractVersionType
{
	private static SimpleDateFormat s_timestampFormat = new SimpleDateFormat("yyyyMMdd'.'HHmmss"); //$NON-NLS-1$

	private static SimpleDateFormat s_dateFormat = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$

	static
	{
		TimeZone utc = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$
		s_timestampFormat.setTimeZone(utc);
		s_dateFormat.setTimeZone(utc);
	}

	public static String toString(Date ts)
	{
		String string;
		synchronized(s_timestampFormat)
		{
			string = s_timestampFormat.format(ts);
		}
		if(string.endsWith(".000000")) //$NON-NLS-1$
			string = string.substring(0, string.length() - 7);
		return string;
	}

	public static String toString(TimestampVersion tsv)
	{
		return toString(tsv.getTimestamp());
	}

	@Override
	public IVersion coerce(Object object)
	{
		return (object instanceof Date)
				? new TimestampVersion(this, (Date)object)
				: super.coerce(object);
	}

	public IVersion fromString(String versionString, int startPos, int[] endPosRet) throws VersionSyntaxException
	{
		if(versionString == null)
			return null;
		ParsePosition pp = new ParsePosition(startPos);
		Date ts;
		synchronized(s_timestampFormat)
		{
			ts = s_timestampFormat.parse(versionString, pp);
		}
		if(pp.getErrorIndex() >= 0)
		{
			pp.setErrorIndex(-1);
			synchronized(s_dateFormat)
			{
				ts = s_dateFormat.parse(versionString, pp);
			}
			if(pp.getErrorIndex() >= 0)
				throw new VersionSyntaxException(Messages.Not_a_valid_Timestamp_version, versionString, pp.getErrorIndex());
		}
		endPosRet[0] = pp.getIndex();
		return new TimestampVersion(this, ts);
	}
}
