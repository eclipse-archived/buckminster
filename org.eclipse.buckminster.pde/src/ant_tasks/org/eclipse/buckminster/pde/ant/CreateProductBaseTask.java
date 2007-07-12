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
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.eclipse.buckminster.pde.tasks.CreateProductBase;
import org.eclipse.core.runtime.Path;

/**
 * Ant task that creates a product base. The base in this case is a product stripped from all features and plugins.
 * 
 * @author Thomas Hallgren
 */
public class CreateProductBaseTask extends TargetPlatformTask
{
	private boolean m_copyJavaLauncher = true;

	private String m_arch;

	private String m_os;

	private File m_outputDir;

	private File m_productFile;

	private String m_ws;

	@Override
	public void execute() throws BuildException
	{
		try
		{
			CreateProductBase createProduct = new CreateProductBase(
					m_os, m_ws, m_arch, m_productFile, new Path(m_outputDir.toString()), this.getTargetLocation(), m_copyJavaLauncher);
			Project p = this.getProject();
			createProduct.execute();
			for(Map.Entry<String,String> hint : createProduct.getHints().entrySet())
				p.setUserProperty(hint.getKey(), hint.getValue());
		}
		catch(Exception e)
		{
			throw new BuildException(e.getMessage(), this.getLocation());
		}
	}

	public void setArch(String arch)
	{
		m_arch = arch;
	}

	public void setCopyJavaLauncher(boolean flag)
	{
		m_copyJavaLauncher = flag;
	}

	public void setOs(String os)
	{
		m_os = os;
	}

	public void setOutputDir(File outputDir)
	{
		m_outputDir = outputDir;
	}

	public void setProductFile(File productFile)
	{
		m_productFile = productFile;
	}

	public void setWs(String ws)
	{
		m_ws = ws;
	}
}
