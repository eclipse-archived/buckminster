package org.eclipse.buckminster.core.cspec;

import org.eclipse.buckminster.osgi.filter.Filter;

public interface IPrerequisite extends IAttributeFilter
{
	String getAlias();

	String getAttribute();

	String getComponentName();

	String getComponentType();

	Filter getFilter();

	String getName();

	boolean isContributor();

	boolean isExternal();

	boolean isOptional();
}
