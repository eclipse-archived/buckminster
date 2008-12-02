/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

public class VersionSyntaxException extends LocalizedException
{
	private static final long serialVersionUID = 948708968483188011L;

	public VersionSyntaxException(String reason, String versionString, int errorPos)
	{
		super(NLS.bind(Messages.VersionSyntaxException_Syntax_error_in_version_string_0_at_position_1_2, new Object[] {
				versionString, Integer.valueOf(errorPos), reason }));
	}
}
