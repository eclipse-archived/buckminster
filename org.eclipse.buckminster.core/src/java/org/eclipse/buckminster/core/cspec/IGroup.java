package org.eclipse.buckminster.core.cspec;

import org.eclipse.core.runtime.IPath;

public interface IGroup extends IAttribute
{
	IPath getPrerequisiteRebase();
}
