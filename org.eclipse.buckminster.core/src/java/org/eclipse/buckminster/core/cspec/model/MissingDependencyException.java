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
public class MissingDependencyException extends LocalizedException
{
	private static final long serialVersionUID = -3148257588863542456L;

	public MissingDependencyException(String componentName, String dependencyName)
	{
		super(NLS.bind(Messages.MissingDependencyException_Component_0_has_no_declared_dependency_to_component_1,
				componentName, dependencyName));
	}
}
