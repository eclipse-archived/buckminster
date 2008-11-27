package org.eclipse.buckminster.core.cspec;

import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.core.cspec.model.UpToDatePolicy;
import org.eclipse.core.runtime.IPath;

public interface IAction extends IAttribute
{
	String getActorName();

	Map<String, String> getActorProperties();

	Group getPrerequisiteGroup();

	String getProductAlias();

	IPath getProductBase();

	int getProductFileCount();

	Set<IPath> getProductPaths();

	Map<String, String> getProperties();

	UpToDatePolicy getUpToDatePolicy();

	boolean isAlways();

	boolean isAssignConsoleSupport();

	boolean isInternal();
}
