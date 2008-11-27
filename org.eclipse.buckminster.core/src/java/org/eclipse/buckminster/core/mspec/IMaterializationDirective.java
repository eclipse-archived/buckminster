package org.eclipse.buckminster.core.mspec;

import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IPath;

public interface IMaterializationDirective extends IAdaptable
{
	ConflictResolution getConflictResolution();

	Documentation getDocumentation();

	IPath getInstallLocation();

	String getMaterializerID();

	int getMaxParallelJobs();

	Map<String, String> getProperties();

	IPath getWorkspaceLocation();
}
