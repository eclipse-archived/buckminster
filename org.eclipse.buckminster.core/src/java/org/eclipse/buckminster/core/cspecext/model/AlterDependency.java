/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.buckminster.model.common.impl.ComponentRequestImpl;

/**
 * @author Thomas Hallgren
 */
public class AlterDependency extends ComponentRequestImpl {
	public AlterDependency(ComponentRequest base) {
		setId(base.getId());
		setType(base.getType());
		setRange(base.getRange());
		setFilter(getFilter());
	}

	public void alterDependency(ComponentRequest dep) {
		dep.setType(CSpecExtension.overrideCheckNull(getType(), dep.getType()));
		dep.setRange(CSpecExtension.overrideCheckNull(getRange(), dep.getRange()));
		dep.setFilter(CSpecExtension.overrideCheckNull(getFilter(), dep.getFilter()));
	}
}
