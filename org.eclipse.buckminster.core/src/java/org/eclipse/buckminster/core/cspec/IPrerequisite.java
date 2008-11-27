package org.eclipse.buckminster.core.cspec;

public interface IPrerequisite extends IAttributeFilter
{
	String getAlias();

	String getAttribute();

	String getComponentName();

	String getName();

	boolean isContributor();

	boolean isExternal();

	boolean isOptional();
}
