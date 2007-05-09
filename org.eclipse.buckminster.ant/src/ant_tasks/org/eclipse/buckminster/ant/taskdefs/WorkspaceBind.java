/**************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
***************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.ant.tasks.WorkspaceBindTask;

/**
 * Makes Buckminster aware of a project and performs a workspace bind. This task
 * is ment to be used when projects are created on the fly as the result of a
 * prebind action.
 *
 * @author Thomas Hallgren
 */
public class WorkspaceBind extends Task
{
	private File m_projectDir;

	@Override
	public void execute()
	throws BuildException
	{
		if(m_projectDir == null)
			throw new BuildException("\"projectDir\" must be set", this.getLocation());

		if(!m_projectDir.isDirectory())
			throw new BuildException(m_projectDir + " is not a directory", this.getLocation());

		WorkspaceBindTask executor = new WorkspaceBindTask(m_projectDir);
		try
		{
			executor.execute();
		}
		catch(Exception e)
		{
			throw new BuildException(e, this.getLocation());
		}
	}

	/**
	 * Sets the project root
	 * @param An absolute path denoting the project folder.
	 */
	public void setProjectDir(File dir)
	{
		m_projectDir = dir;
	}
}
