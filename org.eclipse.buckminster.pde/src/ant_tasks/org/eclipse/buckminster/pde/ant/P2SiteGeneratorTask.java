/*******************************************************************************
 * Copyright (c) 2006-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.pde.tasks.P2SiteGenerator;

/**
 * Ant task that unpacks all features, scans the feature.xml for bundles that needs to be unpacked, and then unpacks
 * them.
 * 
 * @author Thomas Hallgren
 */
public class P2SiteGeneratorTask extends Task
{
	private File m_siteDir;

	private File m_topFeatureDir;

	@Override
	public void execute() throws BuildException
	{
		try
		{
			if(m_siteDir == null)
				throw new BuildException("Missing attribute siteDir", this.getLocation());
			if(m_topFeatureDir == null)
				throw new BuildException("Missing attribute topFeatureDir", this.getLocation());
			P2SiteGenerator siteGen = new P2SiteGenerator();
			siteGen.run(m_topFeatureDir, m_siteDir);
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setSitedir(File siteDir)
	{
		m_siteDir = siteDir;
	}

	public void setTopFeaturedir(File topFeatureDir)
	{
		m_topFeatureDir = topFeatureDir;
	}
}
