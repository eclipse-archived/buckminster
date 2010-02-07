/**************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ***************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.PropertySet;
import org.eclipse.buckminster.ant.tasks.PerformTask;

/**
 * Makes Buckminster aware of a project and performs a workspace bind. This task
 * is ment to be used when projects are created on the fly as the result of a
 * prebind action.
 * 
 * @author Thomas Hallgren
 */
public class Perform extends Task {
	private boolean inWorkspace;
	private boolean quiet;
	private String component;
	private String attribute;

	private final ArrayList<PropertySet> propertySets = new ArrayList<PropertySet>();

	public void addPropertySet(PropertySet propertySet) {
		propertySets.add(propertySet);
	}

	@Override
	public void execute() throws BuildException {
		if (component == null)
			throw new BuildException("\"component\" must be set", this.getLocation());

		if (attribute == null)
			throw new BuildException("\"attribute\" must be set", this.getLocation());

		Map<String, String> properties;
		if (propertySets.isEmpty())
			properties = Collections.emptyMap();
		else {
			properties = new HashMap<String, String>();
			for (PropertySet propertySet : propertySets) {
				Properties props = propertySet.getProperties();
				Enumeration<?> propNames = props.propertyNames();
				while (propNames.hasMoreElements()) {
					String key = (String) propNames.nextElement();
					properties.put(key, props.getProperty(key));
				}
			}
		}
		try {
			PerformTask executor = new PerformTask(component, attribute, inWorkspace, quiet, properties);
			int exitStatus = executor.execute();
			if (exitStatus != 0)
				throw new BuildException("perform exited with " + exitStatus);
		} catch (BuildException e) {
			throw e;
		} catch (Exception e) {
			throw new BuildException(e, this.getLocation());
		}
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public void setComponent(String component) {
		this.component = component;
	}

	public void setInWorkspace(boolean flag) {
		this.inWorkspace = flag;
	}

	public void setQuiet(boolean flag) {
		this.quiet = flag;
	}
}
