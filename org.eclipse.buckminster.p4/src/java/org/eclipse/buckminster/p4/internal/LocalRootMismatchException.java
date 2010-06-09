/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.p4.internal;

import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.buckminster.p4.Messages;
import org.eclipse.core.runtime.IPath;
import org.eclipse.osgi.util.NLS;

public class LocalRootMismatchException extends LocalizedException
{
	private static final long serialVersionUID = -2811429378808277844L;

	public LocalRootMismatchException(String clientName, String p4Port, IPath preferencePath, IPath serverPath)
	{
		super(
				NLS
						.bind(
								Messages.the_local_path_declared_in_P4_preferences_for_client_0_and_p4_port_1_is_2_it_should_be_3_according_to_the_client_specification_found_on_the_server,
								new Object[] { clientName, p4Port, preferencePath.toOSString(), serverPath.toOSString() }));
	}
}
