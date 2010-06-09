package org.eclipse.buckminster.ant.taskdefs;

/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
import java.io.File;
import java.util.Collections;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.ant.tasks.VersionQualifierTask;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.equinox.p2.metadata.Version;

/**
 * @author Thomas Hallgren
 * 
 */
public class VersionQualifier extends Task {
	private String componentName;

	private String componentType;

	private File propertiesFile;

	private String property;

	private String qualifier;

	private String version;

	private String versionType;

	@Override
	public void execute() throws BuildException {
		try {
			if (componentName == null)
				throw new BuildException("Missing attribute componentName", getLocation());
			if (version == null)
				throw new BuildException("Missing attribute version", getLocation());
			if (property == null)
				throw new BuildException("Missing attribute property", getLocation());

			Version v = VersionHelper.createVersion(versionType, version);
			ComponentIdentifier cid = new ComponentIdentifier(componentName, componentType, v);
			VersionQualifierTask vq = new VersionQualifierTask(propertiesFile, qualifier);
			v = vq.replaceQualifier(cid, Collections.<ComponentIdentifier> emptyList());
			String prop = (v == null ? version : v.toString());
			getProject().setUserProperty(property, prop);
		} catch (Exception e) {
			throw new BuildException(e.toString(), this.getLocation());
		}
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}

	public void setComponentType(String componentType) {
		this.componentType = componentType;
	}

	public void setPropertiesFile(String propertiesFile) {
		if (propertiesFile == null || propertiesFile.length() == 0)
			this.propertiesFile = null;
		else
			this.propertiesFile = new File(propertiesFile);
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setVersionType(String versionType) {
		this.versionType = versionType;
	}

}
