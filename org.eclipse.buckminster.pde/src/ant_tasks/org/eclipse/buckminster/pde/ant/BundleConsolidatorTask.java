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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.buckminster.ant.types.FileSetGroup;
import org.eclipse.buckminster.pde.tasks.BundleConsolidator;

/**
 * Ant task that updates the version qualifier of a bundle.
 * 
 * @author Thomas Hallgren
 */
public class BundleConsolidatorTask extends Task
{
	private File m_input;

	private File m_output;

	private File m_propertiesFile;
	
	private String m_qualifier;

	@Override
	public void execute() throws BuildException
	{
		try
		{
	    	if(m_input == null)
				throw new BuildException("Missing attribute input", getLocation());
			if(m_output == null)
				throw new BuildException("Missing attribute output", getLocation());

			BundleConsolidator bc = new BundleConsolidator(m_input, m_output, m_propertiesFile, m_qualifier);
			bc.run();
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), this.getLocation());
		}
	}

	public void setQualifier(String qualifier)
	{
		m_qualifier = qualifier;
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
}
