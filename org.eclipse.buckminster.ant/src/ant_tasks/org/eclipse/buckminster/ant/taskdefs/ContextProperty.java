/**************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ***************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;

/**
 * A task that provides the properties of the action context
 */
public class ContextProperty extends Task {
	private String name;

	private String receivingProperty;

	@Override
	public void execute() throws BuildException {
		if (name == null)
			throw new BuildException("\"name\" must be set");

		if (receivingProperty == null)
			receivingProperty = name;

		IActionContext ctx = AbstractActor.getActiveContext();
		Object v = null;
		if (ctx != null) {
			Map<String, ? extends Object> props = ctx.getProperties();
			if (props.containsKey(name)) {
				v = ctx.getProperties().get(name);
				getProject().setProperty(receivingProperty, v == null ? null : v.toString());
			}
		}
	}

	/**
	 * Sets the name of the property to obtain from the action context.
	 * 
	 * @param name
	 *            A property name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the name of the property that will receive the substituted value.
	 * 
	 * @param receivingProperty
	 *            A property name.
	 */
	public void setReceivingProperty(String receivingProperty) {
		this.receivingProperty = receivingProperty;
	}
}