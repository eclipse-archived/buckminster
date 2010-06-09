/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;

/**
 * @author Thomas Hallgren
 */
public class AlterDependency {
	private final ComponentRequest base;

	public AlterDependency(ComponentRequest base) {
		this.base = base;
	}

	public void alterDependency(ComponentRequestBuilder dep) {
		dep.setComponentTypeID(CSpecExtension.overrideCheckNull(base.getComponentTypeID(), dep.getComponentTypeID()));
		dep.setVersionRange(CSpecExtension.overrideCheckNull(base.getVersionRange(), dep.getVersionRange()));
	}

	public String getComponentTypeID() {
		return base.getComponentTypeID();
	}

	public String getName() {
		return base.getName();
	}
}
