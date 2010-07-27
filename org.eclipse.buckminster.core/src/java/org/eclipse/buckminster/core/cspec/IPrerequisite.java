package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.equinox.p2.metadata.VersionRange;

public interface IPrerequisite extends IAttributeFilter {
	String getAlias();

	String getAttribute();

	String getComponentName();

	String getComponentType();

	VersionRange getVersionRange();

	Filter getFilter();

	String getName();

	boolean isContributor();

	boolean isExternal();
}
