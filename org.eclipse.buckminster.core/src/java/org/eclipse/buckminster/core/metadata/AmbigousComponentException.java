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
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class AmbigousComponentException extends LocalizedException {
	private static final long serialVersionUID = -7828103645630248088L;

	public AmbigousComponentException(String componentName) {
		super(NLS.bind(Messages.More_then_one_version_of_component_0_known_to_Buckminster, componentName));
	}
}
