package org.eclipse.buckminster.core.cspec;

import java.util.Set;

import org.eclipse.core.runtime.IPath;

public interface IArtifact extends IAttribute {
	IPath getBase();

	Set<IPath> getPaths();
}
