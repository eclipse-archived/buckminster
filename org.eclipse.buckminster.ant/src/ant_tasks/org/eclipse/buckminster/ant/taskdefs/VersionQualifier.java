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
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.equinox.internal.provisional.p2.core.Version;

/**
 * @author Thomas Hallgren
 *
 */
public class VersionQualifier extends Task
{
	private String m_componentName;

	private String m_componentType;

	private File m_propertiesFile;

	private String m_property;

	private String m_qualifier;

	private String m_version;

	private String m_versionType;

	@Override
	public void execute() throws BuildException
	{
		try
		{
	    	if(m_componentName == null)
				throw new BuildException("Missing attribute componentName", getLocation());
			if(m_version == null)
				throw new BuildException("Missing attribute version", getLocation());
			if(m_property == null)
				throw new BuildException("Missing attribute property", getLocation());

			Version v = VersionHelper.createVersion(m_versionType, m_version);
			ComponentIdentifier cid = new ComponentIdentifier(m_componentName, m_componentType, v);
			VersionQualifierTask vq = new VersionQualifierTask(m_propertiesFile, m_qualifier);
			v = vq.replaceQualifier(cid, Collections.<ComponentIdentifier>emptyList());
			String prop = (v == null ? m_version : v.toString());
			getProject().setUserProperty(m_property, prop);
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), this.getLocation());
		}
	}

	public void setComponentName(String componentName)
	{
		m_componentName = componentName;
	}

	public void setComponentType(String componentType)
	{
		m_componentType = componentType;
	}

	public void setProperty(String property)
	{
		m_property = property;
	}

	public void setQualifier(String qualifier)
	{
		m_qualifier = qualifier;
	}

	public void setPropertiesFile(String propertiesFile)
	{
		if(propertiesFile == null || propertiesFile.length() == 0)
			m_propertiesFile = null;
		else
			m_propertiesFile = new File(propertiesFile);
	}

	public void setVersion(String version)
	{
		m_version = version;
	}

	public void setVersionType(String versionType)
	{
		m_versionType = versionType;
	}

}
