/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.helpers;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class IllegalParameterException extends LocalizedException
{
	private static final long serialVersionUID = 721070047124720053L;

	public IllegalParameterException(String extensionPointId, String id, String parameterName)
	{
		super(NLS.bind(Messages.Parameter_0_is_illegal_for_id_1_extension_point_2,
				new Object[] { parameterName, id, extensionPointId }));
	}
}
