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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;

/**
 * @author Thomas Hallgren
 */
public abstract class VersionSelector implements IVersionSelector
{
	private static final Pattern s_versionExp = Pattern.compile("^\\s*(.*?)([@/#][^@/#]*?)?(?:\\|(.+))?\\s*$");

	/**
	 * The number expression will match any string that starts with a
	 * &quot;#&quot; and then consists entirely of digits.
	 */
	private static final Pattern s_numberExp = Pattern.compile("^#(\\d+)$");

	/**
	 * The tag expression will match any string that starts with &quot;/&quot;.
	 * Example:
	 * 
	 * <pre>
	 *  /3.1.0
	 *  /v1_0_b2
	 *  /2.0-dev
	 * </pre>
	 */
	private static final Pattern s_tagExp = Pattern.compile("^/(.*)$");

	public boolean isDefaultBranch()
	{
		return DEFAULT_BRANCH.equals(this.getBranchName());
	}

	public static IVersionSelector fromString(CharSequence versionString) throws BuckminsterException
	{
		if(versionString == null || versionString.length() == 0)
			return null;

		Matcher m = s_versionExp.matcher(versionString);
		if(m.matches())
		{
			String branch = m.group(1);
			if(branch.length() == 0)
				branch = DEFAULT_BRANCH;

			String qualifier = m.group(2);
			String artifactType = m.group(3);
			if(qualifier == null)
				return new PlainSelector(branch);

			m = s_tagExp.matcher(qualifier);
			if(m.matches())
			{
				String tag = m.group(1);
				return (tag == null || TAG_LATEST.equals(tag))
					? VersionSelectorFactory.latest(branch, artifactType)
					: VersionSelectorFactory.tag(branch, tag, artifactType);
			}

			try
			{
				Date d = TimestampSelector.parseTimestamp(qualifier.substring(1));
				return VersionSelectorFactory.timestamp(branch, d.getTime(), artifactType);
			}
			catch(ParseException e)
			{}

			m = s_numberExp.matcher(qualifier);
			if(m.matches())
				return VersionSelectorFactory.changeNumber(branch, Long.parseLong(m.group(1)), artifactType);
		}
		throw new BuckminsterException("Unable to parse version string \"" + versionString + "\"");
	}
}
