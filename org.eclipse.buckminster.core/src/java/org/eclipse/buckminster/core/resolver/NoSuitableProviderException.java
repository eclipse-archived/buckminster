/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class NoSuitableProviderException extends LocalizedException {
	private static final long serialVersionUID = -8197425848026772914L;

	public NoSuitableProviderException(String searchPathName, String componentName) {
		super(NLS.bind(Messages.No_suitable_provider_for_component_0_was_found_in_searchPath_1, componentName, searchPathName));
	}
}
