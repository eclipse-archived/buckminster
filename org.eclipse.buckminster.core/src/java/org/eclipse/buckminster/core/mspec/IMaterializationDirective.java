package org.eclipse.buckminster.core.mspec;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;

public interface IMaterializationDirective extends IAdaptable
{
	Documentation getDocumentation();

	IPath getInstallLocation();

	String getMaterializerID();

	int getMaxParallelJobs();

	IPath getWorkspaceLocation();

	Map<String, String> getProperties();

	ConflictResolution getConflictResolution();
}
