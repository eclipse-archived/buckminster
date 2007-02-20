/**************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
***************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.util.FileUtils;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.resolver.LocalResolver;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

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

		InputStream input = null;
		IProjectDescription projDesc = null;
		try
		{
			input = new BufferedInputStream(new FileInputStream(new File(m_projectDir, IProjectDescription.DESCRIPTION_FILE_NAME)));
			projDesc = ResourcesPlugin.getWorkspace().loadProjectDescription(input);
		}
		catch(Exception e)
		{
			throw new BuildException(e, this.getLocation());
		}
		finally
		{
			FileUtils.close(input);
		}

		try
		{
			// Set up whats needed to simulate a cquery that resolved to a local reader
			// with eclipse.installed component type.
			//
			ComponentQueryBuilder qbld = new ComponentQueryBuilder();
			qbld.setRootRequest(new ComponentRequest(projDesc.getName(), null, null));
			RMContext context = new RMContext(qbld.createComponentQuery());

			IPath projectPath = Path.fromOSString(m_projectDir.toString());
			Materialization mat = new Materialization(projectPath, LocalResolver.fromPath(projectPath, projDesc.getName()));
			mat.store();

			IMaterializer wsMat = CorePlugin.getDefault().getMaterializer(IMaterializer.WORKSPACE);
			wsMat.performInstallAction(mat.getResolution(), context, new NullProgressMonitor());
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
