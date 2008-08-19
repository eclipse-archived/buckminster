package org.eclipse.buckminster.core.cspec;

import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.core.runtime.IPath;

public interface IAction extends IAttribute
{
	String getActorName();

	Group getPrerequisiteGroup();

	String getProductAlias();

	IPath getProductBase();

	int getProductFileCount();

	Set<IPath> getProductPaths();

	UpToDatePolicy getUpToDatePolicy();

	Map<String, String> getActorProperties();

	Map<String, String> getProperties();

	boolean isAssignConsoleSupport();

	boolean isAlways();

	boolean isInternal();
}
