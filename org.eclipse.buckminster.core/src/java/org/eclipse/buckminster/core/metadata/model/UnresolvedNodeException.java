/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.model;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class UnresolvedNodeException extends LocalizedException {
	private static final long serialVersionUID = 6297785682251941992L;

	public UnresolvedNodeException(ComponentRequest request) {
		super(NLS.bind(Messages.Attempt_to_use_an_unresolved_node_Request_is_0, request));
	}
}
