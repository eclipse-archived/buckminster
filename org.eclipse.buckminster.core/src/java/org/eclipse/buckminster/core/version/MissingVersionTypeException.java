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

/**
 * @author Thomas Hallgren
 */
public class MissingVersionTypeException extends LocalizedException
{
	private static final long serialVersionUID = -318573955585348638L;

	public MissingVersionTypeException(String versionTypeId)
	{
		super(NLS.bind(Messages.No_version_type_with_id_0_has_been_registered_with_extension_point_1, versionTypeId,
				VersionHelper.VERSION_TYPES_POINT));
	}
}
