package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.osgi.filter.Filter;

public interface IComponentRequest extends IComponentName
{
	boolean designates(IComponentIdentifier id);

	Filter getFilter();

	IVersionDesignator getVersionDesignator();
}
