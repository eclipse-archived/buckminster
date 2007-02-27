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

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.version.IVersion;

/**
 * @author Thomas Hallgren
 */
public class OSGiVersionType extends TripletVersionType
{
	@SuppressWarnings("hiding")
	public static final String ID = "OSGi";

	// Slight variant of the TripletVersionPattern. This pattern will not
	// allow a qualifier unless all three numbers precedes it and the only
	// valid separator between the last digit and the qualifier is the dot.
	//
	private static final Pattern s_OSGiVersionPattern = Pattern.compile(
			"^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+)(?:\\.([^\\(\\)\\[\\],]+))?)?)?([\\)\\],]|$)");

	@Override
	IVersion createVersion(int major, int minor, int micro, String qual)
	{
		return new OSGiVersion(this, major, minor, micro, qual);
	}

	@Override
	Pattern getVersionPattern()
	{
		return s_OSGiVersionPattern;
	}
}
