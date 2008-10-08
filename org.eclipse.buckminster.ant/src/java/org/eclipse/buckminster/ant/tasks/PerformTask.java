/**************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
***************************************************************************/
package org.eclipse.buckminster.ant.tasks;

import java.util.Map;

import org.eclipse.buckminster.core.commands.Perform;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;

/**
 * Perform a Buckminster action
 *
 * @author Thomas Hallgren
 */
public class PerformTask
{
	private final String m_component;
	private final String m_attribute;
	private final Map<String,String> m_properties;

	public PerformTask(String component, String attribute, Map<String,String> properties)
	{
		m_component = component;
		m_attribute = attribute;
		m_properties = properties;
	}

	public int execute() throws CoreException
	{
		Perform performCommand = new Perform();

		CSpec cspec = WorkspaceInfo.getResolution(ComponentIdentifier.parse(m_component)).getCSpec();
		performCommand.addAttribute(cspec.getRequiredAttribute(m_attribute));
		performCommand.addProperties(m_properties);
		try
		{
			return performCommand.run("perform");
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
	}
}
