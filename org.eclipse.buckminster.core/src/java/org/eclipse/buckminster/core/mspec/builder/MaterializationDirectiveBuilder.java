/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.mspec.builder;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.mspec.IMaterializationDirective;
import org.eclipse.core.runtime.IPath;

/**
 * @author Thomas Hallgren
 * 
 */
public abstract class MaterializationDirectiveBuilder implements IMaterializationDirective {
	private Documentation documentation;

	private IPath installLocation;

	private IPath workspaceLocation;

	private String materializerID;

	private final HashMap<String, String> properties = new HashMap<String, String>();

	private ConflictResolution conflictResolution;

	private int maxParallelJobs = -1;

	public void clear() {
		maxParallelJobs = -1;
		installLocation = null;
		workspaceLocation = null;
		materializerID = null;
		conflictResolution = null;
		documentation = null;
		properties.clear();
	}

	@Override
	public ConflictResolution getConflictResolution() {
		return conflictResolution;
	}

	@Override
	public Documentation getDocumentation() {
		return documentation;
	}

	@Override
	public IPath getInstallLocation() {
		return installLocation;
	}

	@Override
	public String getMaterializerID() {
		return materializerID;
	}

	@Override
	public int getMaxParallelJobs() {
		return maxParallelJobs;
	}

	@Override
	public Map<String, String> getProperties() {
		return properties;
	}

	@Override
	public IPath getWorkspaceLocation() {
		return workspaceLocation;
	}

	public void initFrom(IMaterializationDirective md) {
		clear();
		documentation = md.getDocumentation();
		installLocation = md.getInstallLocation();
		workspaceLocation = md.getWorkspaceLocation();
		materializerID = md.getMaterializerID();
		maxParallelJobs = md.getMaxParallelJobs();
		conflictResolution = md.getConflictResolution();
		properties.putAll(md.getProperties());
	}

	public void setConflictResolution(ConflictResolution conflictResolution) {
		this.conflictResolution = conflictResolution;
	}

	public void setDocumentation(Documentation documentation) {
		this.documentation = documentation;
	}

	public void setInstallLocation(IPath installLocation) {
		this.installLocation = installLocation;
	}

	public void setMaterializerID(String materializerID) {
		this.materializerID = materializerID;
	}

	public void setMaxParallelJobs(int maxParallelJobs) {
		this.maxParallelJobs = maxParallelJobs;
	}

	public void setWorkspaceLocation(IPath workspaceLocation) {
		this.workspaceLocation = workspaceLocation;
	}
}
