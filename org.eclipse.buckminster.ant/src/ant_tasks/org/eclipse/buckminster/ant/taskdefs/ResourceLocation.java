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
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;

/**
 * Finds the absolute path in the local file system for a given
 * workspace resource.
 *
 * @author Thomas Hallgren
 * @see IContainer#findMember(String)
 * @see IWorkspaceRoot
 * @see IResource#getLocation()
 */
public class ResourceLocation extends Task
{
	private String m_property;
	private String m_path;

	@Override
	public void execute()
	throws BuildException
	{
		if(m_property == null)
			throw new BuildException("\"property\" must be set", getLocation());
		if(m_path == null)
			throw new BuildException("\"name\" must be set", getLocation());

		IWorkspaceRoot wsRoot = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = wsRoot.findMember(m_path);
		IPath absPath = (resource == null) ? null : resource.getLocation();
		if(absPath != null)
			getProject().setNewProperty(m_property, absPath.toOSString());
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
	 * Sets the workspace relative path that appoints the resource for which we
	 * want to obtain the file system absolute path.
	 * @param path The workspace relative resource path.
	 */
	public void setPath(String path)
	{
		m_path = path;
	}
}
