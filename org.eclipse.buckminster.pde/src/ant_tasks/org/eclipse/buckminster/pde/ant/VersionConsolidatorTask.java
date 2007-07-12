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

import org.apache.tools.ant.Task;

/**
 * Ant task that updates the version qualifier of a bundle.
 * 
 * @author Thomas Hallgren
 */
public abstract class VersionConsolidatorTask extends Task
{
	private File m_input;

	private File m_output;

	private File m_propertiesFile;
	
	private String m_qualifier;

	public File getInput()
	{
		return m_input;
	}

	public File getOutput()
	{
		return m_output;
	}

	public File getPropertiesFile()
	{
		return m_propertiesFile;
	}

	public String getQualifier()
	{
		return m_qualifier;
	}

	public void setInputFile(File input)
	{
		m_input = input;
	}

	public void setOutputFile(File output)
	{
		m_output = output;
	}

	public void setPropertiesFile(String propertiesFile)
	{
		if(propertiesFile == null || propertiesFile.length() == 0)
			m_propertiesFile = null;
		else
			m_propertiesFile = new File(propertiesFile);
	}

	public void setQualifier(String qualifier)
	{
		m_qualifier = qualifier;
	}
}
