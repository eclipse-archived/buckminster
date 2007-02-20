/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Reference;
import org.eclipse.buckminster.pde.internal.FeatureConsolidator;

/**
 * Ant task that updates the version of plugins and included features found in a feature.xml file to reflect the actual
 * plugin/feature that they appoint within a certain scope given by a set of paths that are used when resolving the
 * appointed components. The elements of the paths should be either a plugin folder, a feature folder, or a zipped
 * folder containing a plugin or feature.
 * 
 * @author Thomas Hallgren
 */
public class FeatureConsolidatorTask extends Task
{
	private File m_featureFile;

	private Path m_featureAndPluginPath;

	@Override
	public void execute() throws BuildException
	{
		try
		{
			if(m_featureFile == null)
				throw new BuildException("Missing attribute featureFile", this.getLocation());
			if(m_featureAndPluginPath == null)
				throw new BuildException("Missing nested element featureAndPluginPath", this.getLocation());
			FeatureConsolidator fc = new FeatureConsolidator(m_featureFile, m_featureAndPluginPath.list());
			fc.run();
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), this.getLocation());
		}
	}

	public Path createFeatureAndPluginPath()
	{
		if(m_featureAndPluginPath == null)
			m_featureAndPluginPath = new Path(this.getProject());
		return m_featureAndPluginPath.createPath();
	}

	public void setFeatureFile(File featureFile)
	{
		m_featureFile = featureFile;
	}

	public void setFeatureAndPluginPathRef(Reference r)
	{
		this.createFeatureAndPluginPath().setRefid(r);
	}

	public void setFeatureAndPluginPath(Path featureAndPluginPath)
	{
		if(m_featureAndPluginPath == null)
			m_featureAndPluginPath = featureAndPluginPath;
		else
			m_featureAndPluginPath.append(featureAndPluginPath);
	}
}
