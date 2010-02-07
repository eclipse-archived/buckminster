/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class NoSuchActorException extends LocalizedException {
	private static final long serialVersionUID = 3990531669819240624L;

	public NoSuchActorException(String actorName, String actionName) {
		super(NLS.bind(Messages.Action_0_refers_to_actor_with_id_1_but_no_such_actor_registered_with_extension_point_2, new Object[] { actionName,
				actorName, CorePlugin.ACTORS_POINT }));
	}
}
