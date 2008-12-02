/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class GeneratorAlreadyDefinedException extends LocalizedException
{
	private static final long serialVersionUID = 6814925010076371632L;

	public GeneratorAlreadyDefinedException(String componentName, String generates)
	{
		super(
				NLS
						.bind(
								Messages.GeneratorAlreadyDefinedException_A_generator_that_generates_0_is_defined_more_then_once_in_component_1,
								generates, componentName));
	}
}
