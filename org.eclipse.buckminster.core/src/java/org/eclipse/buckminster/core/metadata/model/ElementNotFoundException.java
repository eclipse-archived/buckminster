/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import java.util.UUID;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.core.metadata.ISaxableStorage;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class ElementNotFoundException extends LocalizedException
{
	private static final long serialVersionUID = 853871657215671889L;

	public ElementNotFoundException(ISaxableStorage<?> storage, UUID uuid)
	{
		super(NLS.bind(Messages.ElementNotFoundException_No_element_with_id_0_was_found_in_storage_1, uuid, storage
				.getName()));
	}
}
