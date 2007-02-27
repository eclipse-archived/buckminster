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
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.pde.internal.ImportBundle;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 *
 */
public class ImportBundleTask extends Task
{
	private URL m_siteURL;
	private File m_destdir;
	private String m_bundleName;

	@Override
	public void execute() throws BuildException
	{
		try
		{
			if(m_bundleName == null)
				throw this.missingAttribute("bundleName");
			if(m_destdir == null)
				throw this.missingAttribute("destdir");
			if(m_siteURL == null)
				throw this.missingAttribute("siteURL");

			ImportBundle importBundle = new ImportBundle(m_bundleName, m_siteURL, new Path(m_destdir.toString()));
			importBundle.execute();
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setBundleName(String bundleName)
	{
		m_bundleName = bundleName;
	}

	public void setDestdir(File destDir)
	{
		m_destdir = destDir;
	}

	public void setSiteDir(File siteDir) throws BuildException
	{
		try
		{
			m_siteURL = siteDir.toURI().toURL();
		}
		catch(MalformedURLException e)
		{
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setSiteURL(String siteURL) throws BuildException
	{
		try
		{
			m_siteURL = new URL(siteURL);
		}
		catch(MalformedURLException e)
		{
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	protected BuildException missingAttribute(String property)
	{
		return new BuildException("Missing attribute \"" + property + '"', this.getLocation());
	}
}
