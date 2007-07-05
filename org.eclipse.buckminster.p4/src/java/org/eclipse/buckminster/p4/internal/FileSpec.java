/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.eclipse.core.runtime.IPath;


/**
 * @author thhal
 */
public class FileSpec
{
	public static final Specifier NONE = new Specifier("#none");
	public static final Specifier HEAD = new Specifier("#head");
	public static final Specifier HAVE = new Specifier("#have");

	public static class Specifier
	{
		private final String m_revision;

		Specifier(String revision)
		{
			m_revision = revision;
		}

		@Override
		public int hashCode()
		{
			return m_revision.hashCode();
		}

		@Override
		public boolean equals(Object o)
		{
			if (o == this)
				return true;

			if (!(o instanceof Specifier))
				return false;
			Specifier that = (Specifier)o;

			if (!this.m_revision.equals(that.m_revision))
				return false;

			return true;
		}

		@Override
		public String toString()
		{
			return m_revision;
		}
	}

	public static class ChangeNumber extends Specifier
	{
		public ChangeNumber(int number)
		{
			super("@" + Integer.toString(number));
		}
	}

	public static class Timestamp extends Specifier
	{
		// Somewhat weird format that uses ':' to separate the date from the time of day.
		//
		private static DateFormat s_dateFormat = new SimpleDateFormat("yyyy/MM/dd:HH:mm:ss");

		private static String formatDate(long time, TimeZone tz)
		{
			synchronized(s_dateFormat)
			{
				s_dateFormat.setTimeZone(tz);
				return s_dateFormat.format(new Date(time));
			}
		}

		public Timestamp(long time, TimeZone tz)
		{
			super("@" + formatDate(time, tz));
		}
	}

	public static class FileRevision extends Specifier
	{
		public FileRevision(int number)
		{
			super("#" + Integer.toString(number));
		}
	}

	public static class Label extends Specifier
	{
		public Label(String label)
		{
			super("@" + label);
		}
	}

	private final IPath m_path;
	private final Specifier m_revision;

	public FileSpec(IPath path, Specifier revision)
	{
		m_path = path;
		m_revision = (revision == null) ? HEAD : revision;
	}

	public Specifier getRevision()
	{
		return m_revision;
	}

	public IPath getPath()
	{
		return m_path;
	}

	@Override
	public String toString()
	{
		String expanded = DepotObject.expandEscapedChars(m_path.toString());
		boolean needQuote = (expanded.indexOf(' ') >= 0);
		if(m_revision.equals(HEAD))
		{
			if(needQuote)
			{
				StringBuilder bld = new StringBuilder();
				bld.append('"');
				bld.append(expanded);
				bld.append('"');
				expanded = bld.toString();
			}
		}
		else
		{
			StringBuilder bld = new StringBuilder();
			if(needQuote)
				bld.append('"');
			bld.append(expanded);
			bld.append(m_revision);
			if(needQuote)
				bld.append('"');
			expanded = bld.toString();
		}
		return expanded;
	}
}

