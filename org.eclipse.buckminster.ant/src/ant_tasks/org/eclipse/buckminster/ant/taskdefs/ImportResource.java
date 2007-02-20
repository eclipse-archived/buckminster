/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ant.taskdefs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Vector;

import org.apache.tools.ant.AntClassLoader;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;

/**
 * <p>
 * Task to import another build file found as a resource in the classpath into
 * the current project.
 * <p>
 * It must be 'top level'. On execution it will read another Ant file into the
 * same Project.
 * <p>
 * Examples
 * 
 * <pre>
 *     &lt;buckminster.importResource resource=&quot;publish.ant&quot; /&gt;
 * </pre>
 * 
 * Import targets from a resource in the current classpath.
 * <p>
 */
public class ImportResource extends Task
{
	private Path m_classpath;

	private String m_resource;

	private boolean m_optional;

	/**
	 * Set the classpath to be used for this compilation.
	 * 
	 * @param cp
	 *            the classpath to be used.
	 */
	public void setClasspath(Path cp)
	{
		if(m_classpath == null)
			m_classpath = cp;
		else
			m_classpath.append(cp);
	}

	/**
	 * Adds a path to the classpath.
	 * 
	 * @return a classpath to be configured.
	 */
	public Path createClasspath()
	{
		if(m_classpath == null)
			m_classpath = new Path(getProject());
		return m_classpath.createPath();
	}

	/**
	 * sets the optional attribute
	 * 
	 * @param optional
	 *            if true ignore files that are not present, default is false
	 */
	public void setOptional(boolean optional)
	{
		m_optional = optional;
	}

	/**
	 * the name of the resource to import.
	 * 
	 * @param resource
	 *            the name of the resource
	 */
	public void setResource(String resource)
	{
		if(resource != null && resource.startsWith("/"))
			resource = resource.substring(1);
		m_resource = resource;
	}

	/**
	 * This relies on the task order model.
	 */
	@Override
	public void execute()
	{
		try
		{
			if(m_resource == null)
			{
				throw new BuildException("import requires resource attribute");
			}
			if(this.getOwningTarget() == null || !"".equals(getOwningTarget().getName()))
			{
				throw new BuildException("import only allowed as a top-level task");
			}

			Project p = this.getProject();
			ProjectHelper helper = (ProjectHelper)p.getReference("ant.projectHelper");
			Vector<?> importStack = helper.getImportStack();

			if(importStack.size() == 0)
			{
				// this happens if ant is used with a project
				// helper that doesn't set the import.
				throw new BuildException("import requires support in ProjectHelper");
			}

			p.log("Importing resource " + m_resource, Project.MSG_VERBOSE);

			if(m_classpath != null)
			{
				p.log("using user supplied classpath: " + m_classpath, Project.MSG_DEBUG);
				m_classpath = m_classpath.concatSystemClasspath("ignore");
			}
			else
			{
				m_classpath = new Path(p);
				m_classpath = m_classpath.concatSystemClasspath("only");
				p.log("using system classpath: " + m_classpath, Project.MSG_DEBUG);
			}

			AntClassLoader loader = new AntClassLoader(p.getCoreLoader(), p, m_classpath, false);
			URL resourceURL = loader.getResource(m_resource);

			if(resourceURL == null)
			{
				String message = "Cannot find resource " + m_resource;
				if(m_optional)
				{
					p.log(message, Project.MSG_VERBOSE);
					return;
				}
				throw new BuildException(message);
			}
			
			File resourceFile;
			boolean isLocal = "file".equalsIgnoreCase(resourceURL.getProtocol());
			if(isLocal)
				resourceFile = new File(resourceURL.getPath());
			else
			{
				InputStream input = null;
				OutputStream output = null;
				try
				{
					byte[] buffer = new byte[4096];
					int count;
					input = resourceURL.openStream();
					resourceFile = File.createTempFile("import-", ".ant");
					resourceFile.deleteOnExit();
					output = new FileOutputStream(resourceFile);
					while((count = input.read(buffer)) > 0)
						output.write(buffer, 0, count);
				}
				catch(IOException e)
				{
					throw new BuildException(e.getMessage());
				}
				finally
				{
					closeStream(input);
					closeStream(output);
				}
			}
			helper.parse(p, resourceFile);
			if(!isLocal)
				resourceFile.delete();
		}
		catch(BuildException ex)
		{
			throw ProjectHelper.addLocationToBuildException(ex, getLocation());
		}
	}

	private static void closeStream(OutputStream stream)
	{
		if(stream == null)
			return;
		try { stream.close(); } catch(IOException e) {}
	}
	
	private static void closeStream(InputStream stream)
	{
		if(stream == null)
			return;
		try { stream.close(); } catch(IOException e) {}
	}
}
