/**************************************************************************
* Copyright (c) 2006-2009, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
***************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;

/**
 * A task that provides the properties of the action context
 */
public class ContextProperty extends Task
{
	private String m_name;

	private String m_receivingProperty;

	/**
	 * Sets the name of the property that will receive the substituted value.
	 * @param receivingProperty A property name.
	 */
	public void setReceivingProperty(String receivingProperty)
	{
		m_receivingProperty = receivingProperty;
	}

	/**
	 * Sets the name of the property to obtain from the action context.
	 * @param name A property name.
	 */
	public void setName(String name)
	{
		m_name = name;
	}

	@Override
	public void execute()
	throws BuildException
	{
		if(m_name == null)
			throw new BuildException("\"name\" must be set");

		if(m_receivingProperty == null)
			m_receivingProperty = m_name;

		IActionContext ctx = AbstractActor.getActiveContext();
		Object v = null;
		if(ctx != null)
			v = ctx.getProperties().get(m_name);
		getProject().setProperty(m_receivingProperty, v == null ? null : v.toString());
	}
}