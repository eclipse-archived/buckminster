package org.eclipse.buckminster.pde.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.pde.tasks.JNLPSiteGenerator;
import org.eclipse.buckminster.pde.tasks.UpdateSiteGenerator;

public class UpdateSiteGeneratorTask extends Task
{
	private File m_siteDir;
	private File m_templateSite;
	private File m_siteFile;

	@Override
	public void execute() throws BuildException
	{
		try
		{
			if(m_siteDir == null)
				throw new BuildException("Missing attribute siteDir", this.getLocation());
			if(!m_siteDir.isAbsolute())
				m_siteDir = new File(getProject().getBaseDir(), m_siteDir.toString());
			if(m_siteFile == null)
				m_siteFile = new File(m_siteDir, "site.xml");
			else if(!m_siteFile.isAbsolute())
				m_siteFile = new File(getProject().getBaseDir(), m_siteFile.toString());

			UpdateSiteGenerator generator = new UpdateSiteGenerator(m_siteDir, m_templateSite, m_siteFile);
			generator.run();
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), this.getLocation());
		}
	}

	public void setTemplateSite(String templateSite)
	{
		if(templateSite != null && templateSite.length() == 0)
			templateSite = null;
		m_templateSite = templateSite == null ? null : new File(templateSite);
	}

	public void setSiteFile(File siteFile)
	{
		m_siteFile = siteFile;
	}

	public void setSitedir(File siteDir)
	{
		m_siteDir = siteDir;
	}
}
