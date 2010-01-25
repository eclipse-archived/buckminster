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
public class ComponentVersion extends Task
{
	private String m_property;
	private String m_name;
	private String m_componentType;
	private String m_versionDesignator;
	private String m_designatorType;

	@Override
	public void execute()
	throws BuildException
	{
		if(m_property == null)
			throw new BuildException("\"property\" must be set", getLocation());
		if(m_name == null)
			throw new BuildException("\"name\" must be set", getLocation());

		try
		{
			ComponentRequest rq = new ComponentRequest(m_name, m_componentType, m_versionDesignator, m_designatorType);
			Version version = WorkspaceInfo.getResolution(rq, false).getComponentIdentifier().getVersion();
			if(version != null)
				getProject().setNewProperty(m_property, version.toString());
		}
		catch(CoreException e)
		{
			throw new BuildException(e);
		}
	}

	/**
	 * Sets the name of the property that will receive the project location.
	 * @param property A property name.
	 */
	public void setProperty(String property)
	{
		m_property = property;
	}

	/**
	 * Sets the name of the component.
	 * @param name The component name
	 */
	public void setName(String name)
	{
		m_name = name;
	}

	/**
	 * Sets the type of the component.
	 * @param componentType The component type
	 */
	public void setComponentType(String componentType)
	{
		m_componentType = componentType;
	}

	/**
	 * Sets the designator that constraints valid versions.
	 * @param versionDesignator The version designator
	 */
	public void setVersionDesignator(String versionDesignator)
	{
		m_versionDesignator = versionDesignator;
	}

	/**
	 * Sets the version type used by the designator
	 * @param designator The designator type
	 */
	public void setDesignatorType(String designatorType)
	{
		m_designatorType = designatorType;
	}
}
