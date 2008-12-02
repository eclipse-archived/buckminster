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
import org.eclipse.buckminster.core.version.OSGiVersion;
import org.eclipse.core.runtime.PluginVersionIdentifier;
import org.eclipse.update.core.VersionedIdentifier;
import org.osgi.framework.Version;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("deprecation")
public class OSGiVersionType extends TripletVersionType
{
	// Slight variant of the TripletVersionPattern. This pattern will not
	// allow a qualifier unless all three numbers precedes it and the only
	// valid separator between the last digit and the qualifier is the dot.
	//
	private static final Pattern s_OSGiVersionPattern = Pattern
			.compile("^(\\d+)(?:\\.(\\d+)(?:\\.(\\d+)(?:\\.([^\\(\\)\\[\\],]+))?)?)?([\\)\\],]|$)"); //$NON-NLS-1$

	@Override
	public IVersion coerce(Object object)
	{
		if(object instanceof Version)
		{
			Version v = (Version)object;
			return new OSGiVersion(this, v.getMajor(), v.getMinor(), v.getMicro(), v.getQualifier());
		}
		if(object instanceof PluginVersionIdentifier)
		{
			PluginVersionIdentifier pvi = (PluginVersionIdentifier)object;
			return new OSGiVersion(this, pvi.getMajorComponent(), pvi.getMinorComponent(), pvi.getServiceComponent(),
					pvi.getQualifierComponent());
		}
		if(object instanceof VersionedIdentifier)
			return coerce(((VersionedIdentifier)object).getVersion());
		return super.coerce(object);
	}

	@Override
	IVersion createVersion(int major, int minor, int micro, String qual, String stringForm)
	{
		return new OSGiVersion(this, major, minor, micro, qual);
	}

	@Override
	Pattern getVersionPattern()
	{
		return s_OSGiVersionPattern;
	}
}
