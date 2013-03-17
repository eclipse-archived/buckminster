/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.List;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.helpers.LocalizedException;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class CircularDependencyException extends LocalizedException {
	private static final long serialVersionUID = -6927435120101723921L;

	private static String buildChain(List<String> componentNames) {
		StringBuilder bld = new StringBuilder();
		bld.append(componentNames.get(0));
		int top = componentNames.size();
		for (int idx = 1; idx < top; ++idx) {
			bld.append(" -> "); //$NON-NLS-1$
			bld.append(componentNames.get(idx));
		}
		return bld.toString();
	}

	public CircularDependencyException(List<String> componentNames) {
		super(NLS.bind(Messages.Circular_component_dependency_detected_Chain_is_0, buildChain(componentNames)));
	}
}
