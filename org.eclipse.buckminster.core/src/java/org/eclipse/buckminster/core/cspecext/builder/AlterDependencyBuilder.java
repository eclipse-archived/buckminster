/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.builder;

import org.eclipse.buckminster.core.cspecext.model.AlterDependency;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * @author Thomas Hallgren
 */
public class AlterDependencyBuilder {
	private final ComponentRequest baseBuilder;

	public AlterDependencyBuilder(ComponentRequest baseBuilder) {
		this.baseBuilder = baseBuilder;
	}

	public void clear() {
		baseBuilder.setId(null);
		baseBuilder.setType(null);
		baseBuilder.setRange(null);
		baseBuilder.setFilter(null);
	}

	public AlterDependency createAlterDependency() throws CoreException {
		return new AlterDependency(EcoreUtil.copy(baseBuilder));
	}

	public String getName() {
		return baseBuilder.getId();
	}
}
