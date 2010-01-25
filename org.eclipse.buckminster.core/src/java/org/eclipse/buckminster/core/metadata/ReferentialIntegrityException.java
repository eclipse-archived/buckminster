/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.metadata.model.IUUIDPersisted;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class ReferentialIntegrityException extends LocalizedException
{
	private static final long serialVersionUID = 7053885060385428942L;

	public ReferentialIntegrityException(IUUIDPersisted instance, String operation, String reason)
	{
		super(NLS.bind(Messages.Unable_to_0_the_1_with_id_2_3, new Object[] { operation, instance.getClass().getName(),
				instance.getId(), reason }));
	}
}
