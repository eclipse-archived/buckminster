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
public class DependencyAlreadyDefinedException extends LocalizedException {
	private static final long serialVersionUID = 8844107002854853175L;

	public DependencyAlreadyDefinedException(String componentName, String dependencyName) {
		super(NLS.bind(Messages.Dependency_0_is_defined_more_then_once_in_component_1, dependencyName, componentName));
	}
}
