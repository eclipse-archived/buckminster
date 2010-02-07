/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.builder;

import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.equinox.p2.metadata.VersionRange;

/**
 * @author Thomas Hallgren
 */
public class ComponentRequestBuilder implements IComponentRequest {
	private String name;

	private String componentType;

	private VersionRange versionRange;

	private Filter filter;

	public void clear() {
		name = null;
		componentType = null;
		versionRange = null;
		filter = null;
	}

	public ComponentRequest createComponentRequest() {
		return new ComponentRequest(this);
	}

	public boolean designates(IComponentIdentifier id) {
		return Trivial.equalsAllowNull(getName(), id.getName()) && (componentType == null || componentType.equals(id.getComponentTypeID()))
				&& (versionRange == null || versionRange.isIncluded(id.getVersion()));
	}

	public String getComponentTypeID() {
		return componentType;
	}

	public Filter getFilter() {
		return filter;
	}

	public String getName() {
		return name;
	}

	public VersionRange getVersionRange() {
		return versionRange;
	}

	public void initFrom(IComponentRequest request) {
		name = request.getName();
		componentType = request.getComponentTypeID();
		versionRange = request.getVersionRange();
		filter = request.getFilter();
	}

	public void setComponentTypeID(String componentType) {
		this.componentType = componentType;
	}

	public void setFilter(Filter filter) {
		this.filter = filter;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setVersionRange(VersionRange versionRange) {
		this.versionRange = versionRange;
	}
}
