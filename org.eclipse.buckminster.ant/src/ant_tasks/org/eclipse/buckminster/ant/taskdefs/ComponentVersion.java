/*******************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ant.taskdefs;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * Finds the version of a component known to Buckminster.
 * 
 * @author Thomas Hallgren
 */
public class ComponentVersion extends Task {
	private String property;
	private String name;
	private String componentType;
	private String versionDesignator;
	private String designatorType;

	@Override
	public void execute() throws BuildException {
		if (property == null)
			throw new BuildException("\"property\" must be set", getLocation());
		if (name == null)
			throw new BuildException("\"name\" must be set", getLocation());

		try {
			ComponentRequest rq = new ComponentRequest(name, componentType, versionDesignator, designatorType);
			Version version = WorkspaceInfo.getResolution(rq, false).getComponentIdentifier().getVersion();
			if (version != null)
				getProject().setNewProperty(property, version.toString());
		} catch (CoreException e) {
			throw new BuildException(e);
		}
	}

	/**
	 * Sets the type of the component.
	 * 
	 * @param componentType
	 *            The component type
	 */
	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	/**
	 * Sets the version type used by the designator
	 * 
	 * @param designator
	 *            The designator type
	 */
	public void setDesignatorType(String designatorType) {
		this.designatorType = designatorType;
	}

	/**
	 * Sets the name of the component.
	 * 
	 * @param name
	 *            The component name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the name of the property that will receive the project location.
	 * 
	 * @param property
	 *            A property name.
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Sets the designator that constraints valid versions.
	 * 
	 * @param versionDesignator
	 *            The version designator
	 */
	public void setVersionDesignator(String versionDesignator) {
		this.versionDesignator = versionDesignator;
	}
}
