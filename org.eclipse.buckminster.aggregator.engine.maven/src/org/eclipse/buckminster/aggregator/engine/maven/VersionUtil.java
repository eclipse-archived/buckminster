/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.engine.maven;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.aggregator.util.GeneralUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionRange;

/**
 * @author Filip Hrbek (filip.hrbek@cloudsmith.com)
 * 
 */
public class VersionUtil
{
	private static Pattern s_versionRangePattern = Pattern.compile("^(\\([)([^,]+),([^,]+)(\\)])$");

	private static final Pattern s_timestampPattern = Pattern.compile(//
	"^((?:19|20)\\d{2}(?:0[1-9]|1[012])(?:0[1-9]|[12][0-9]|3[01]))" + // //$NON-NLS-1$
			"(?:\\.((?:[01][0-9]|2[0-3])[0-5][0-9][0-5][0-9]))?$"); //$NON-NLS-1$

	public static Version createVersion(String versionStr) throws CoreException
	{
		versionStr = GeneralUtils.trimmedOrNull(versionStr);
		if(versionStr == null)
			return null;

		Matcher m = s_timestampPattern.matcher(versionStr);
		if(m.matches())
			// Timestamp
			return createVersionFromFormatAndOriginal("S=[0-9];={8};[.S=[0-9];={6};='000000';]", versionStr);

		try
		{
			// Triplet
			return createVersionFromFormatAndOriginal("n[.n=0;[.n=0;]][d?S=M;]", versionStr);
		}
		catch(IllegalArgumentException e)
		{
			// String
			return createVersionFromFormatAndOriginal("S", versionStr);
		}
	}

	public static Version createVersionFromFormatAndOriginal(String format, String versionStr)
	{
		return Version.parseVersion("format(" + format + "):" + versionStr);
	}

	public static VersionRange createVersionRange(String versionRangeString) throws CoreException
	{
		String vr = GeneralUtils.trimmedOrNull(versionRangeString);
		if(vr == null)
			return VersionRange.emptyRange;

		Matcher m = s_versionRangePattern.matcher(vr);
		if(m.matches())
		{
			return new VersionRange(createVersion(m.group(2)), "[".equals(m.group(1)), createVersion(m.group(3)),
					"[".equals(m.group(4)));
		}
		else
		{
			Version v = createVersion(vr);
			return new VersionRange(v, true, Version.MAX_VERSION, true);
		}
	}
}
