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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.TripletVersion;
import org.eclipse.buckminster.core.version.VersionSyntaxException;

/**
 * @author Thomas Hallgren
 */
public class TripletVersionType extends AbstractVersionType
{
	private static final Pattern s_TripletVersionPattern = Pattern
			.compile("^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+))?)?(?:[-.]?([^\\(\\)\\[\\],]+))?([\\)\\],]|$)");

	private static boolean hasGroup(String stringForm, int group)
	{
		if(stringForm == null)
			return false;
		Matcher m = s_TripletVersionPattern.matcher(stringForm);
		if(!m.find())
			return false;
		return m.group(group) != null;
	}

	public static boolean hasMicro(String stringForm)
	{
		return hasGroup(stringForm, 3);
	}

	public static boolean hasMinor(String stringForm)
	{
		return hasGroup(stringForm, 2);
	}

	private static int intGroup(Matcher m, int groupNumber)
	{
		String g = m.group(groupNumber);
		return g == null
				? 0
				: Integer.parseInt(g);
	}

	IVersion createVersion(int major, int minor, int micro, String qual, String stringForm)
	{
		return new TripletVersion(this, major, minor, micro, qual, stringForm);
	}

	public IVersion fromString(String versionString, int startPos, int[] endPosRet) throws VersionSyntaxException
	{
		if(versionString == null)
			return null;
		Matcher m = getVersionPattern().matcher(versionString.substring(startPos));
		if(m.find())
		{
			endPosRet[0] = startPos + m.end() - m.group(5).length();
			try
			{
				return createVersion(intGroup(m, 1), intGroup(m, 2), intGroup(m, 3), m.group(4), versionString
						.substring(startPos, endPosRet[0]));
			}
			catch(NumberFormatException e)
			{
			}
		}
		throw new VersionSyntaxException("Not a valid " + this.getId() + " version", versionString, startPos);
	}

	Pattern getVersionPattern()
	{
		return s_TripletVersionPattern;
	}

	@Override
	public boolean isComparableTo(IVersionType other)
	{
		return other instanceof TripletVersionType;
	}
}
