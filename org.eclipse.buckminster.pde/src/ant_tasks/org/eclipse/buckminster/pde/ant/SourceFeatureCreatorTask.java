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
import org.eclipse.buckminster.pde.ant.VersionConsolidatorTask;
import org.eclipse.buckminster.pde.tasks.SourceFeatureCreator;

/**
 * Ant task creates a source feature for a given feature and a set of source features and source bundles.
 * 
 * @author Thomas Hallgren
 */
public class SourceFeatureCreatorTask extends Task
{
	private ArrayList<FileSet> m_fileSets;

	private ArrayList<FileSetGroup> m_fileSetGroups;

	private File m_input;

	private File m_output;


	/**
	 * Adds a nested <code>&lt;filesetgroup&gt;</code> element.
	 */
	public void add(FileSetGroup fsGroup) throws BuildException
	{
		if(m_fileSetGroups == null)
			m_fileSetGroups = new ArrayList<FileSetGroup>();
		m_fileSetGroups.add(fsGroup);
	}

	/**
	 * Adds a nested <code>&lt;fileset&gt;</code> element.
	 */
	public void addFileset(FileSet fs) throws BuildException
	{
		if(m_fileSets == null)
			m_fileSets = new ArrayList<FileSet>();
		m_fileSets.add(fs);
	}

	@Override
	public void execute() throws BuildException
	{
		try
		{
	    	if(m_fileSetGroups != null)
	    	{
	    		for(FileSetGroup fsg : m_fileSetGroups)
		    		for(FileSet fs : fsg.getFileSets())
		    			addFileset(fs);
	    		m_fileSetGroups = null;
	    	}

	    	if(getInput() == null)
				throw new BuildException("Missing attribute input", getLocation());
			if(getOutput() == null)
				throw new BuildException("Missing attribute output", getLocation());

			Project proj = getProject();
			List<File> featuresAndPlugins;
			if(m_fileSets == null)
				featuresAndPlugins = Collections.emptyList();
			else
			{
				featuresAndPlugins = new ArrayList<File>();
				for(FileSet fs : m_fileSets)
				{
		            DirectoryScanner ds = fs.getDirectoryScanner(proj);
		            File dir = fs.getDir(proj);
		            for(String file : ds.getIncludedFiles())
		            	featuresAndPlugins.add(new File(dir, file));
				}
			}
			SourceFeatureCreator fc = new SourceFeatureCreator(getInput(), getOutput(), featuresAndPlugins);
			fc.run();
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public File getInput()
	{
		return m_input;
	}

	public File getOutput()
	{
		return m_output;
	}

	public void setInputFile(File input)
	{
		m_input = input;
	}

	public void setOutputFile(File output)
	{
		m_output = output;
	}
}
