/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspecext.model.AlterDependency;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class AlterDependencyBuilder {
	private final ComponentRequestBuilder baseBuilder;

	public AlterDependencyBuilder(ComponentRequestBuilder baseBuilder) {
		this.baseBuilder = baseBuilder;
	}

	public void clear() {
		baseBuilder.clear();
	}

	public AlterDependency createAlterDependency() throws CoreException {
		return new AlterDependency(baseBuilder.createComponentRequest());
	}

	public String getName() {
		return baseBuilder.getName();
	}
}
