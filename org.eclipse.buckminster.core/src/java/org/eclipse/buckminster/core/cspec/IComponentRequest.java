package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.equinox.p2.metadata.VersionRange;

public interface IComponentRequest extends IComponentName
{
	boolean designates(IComponentIdentifier id);

	Filter getFilter();

	VersionRange getVersionRange();
}
