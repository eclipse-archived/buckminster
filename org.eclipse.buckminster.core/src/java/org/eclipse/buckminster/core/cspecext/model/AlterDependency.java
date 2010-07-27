/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.model;

import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * @author Thomas Hallgren
 */
public class AlterDependency implements IComponentRequest {
	private final ComponentRequest base;

	public AlterDependency(ComponentRequest base) {
		this.base = base;
	}

	public void alterDependency(ComponentRequestBuilder dep) {
		dep.setComponentTypeID(CSpecExtension.overrideCheckNull(base.getComponentTypeID(), dep.getComponentTypeID()));
		dep.setVersionRange(CSpecExtension.overrideCheckNull(base.getVersionRange(), dep.getVersionRange()));
		dep.setFilter(CSpecExtension.overrideCheckNull(base.getFilter(), dep.getFilter()));
	}

	@Override
	public boolean designates(IComponentIdentifier id) {
		return base.designates(id);
	}

	@Override
	public String getComponentTypeID() {
		return base.getComponentTypeID();
	}

	@Override
	public Filter getFilter() {
		return base.getFilter();
	}

	@Override
	public String getName() {
		return base.getName();
	}

	@Override
	public VersionRange getVersionRange() {
		return base.getVersionRange();
	}
}
