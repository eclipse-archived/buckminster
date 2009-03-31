package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;

@SuppressWarnings("restriction")
public interface IComponentRequest extends IComponentName
{
	boolean designates(IComponentIdentifier id);

	Filter getFilter();

	VersionRange getVersionRange();
}
