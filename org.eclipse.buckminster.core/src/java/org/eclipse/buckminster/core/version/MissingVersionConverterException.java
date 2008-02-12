/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.LocalizedException;

/**
 * @author Thomas Hallgren
 */
public class MissingVersionConverterException extends LocalizedException
{
	private static final long serialVersionUID = 5414510222542515832L;

	public MissingVersionConverterException(String versionConverterId)
	{
		super("No version converter with id %s has been registered with extension-point %s",
			versionConverterId, CorePlugin.VERSION_CONVERTERS_POINT);
	}
}

