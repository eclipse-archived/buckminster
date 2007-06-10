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
import org.eclipse.buckminster.pde.tasks.FeatureConsolidator;

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
	private File m_input;

	private File m_output;

	private boolean m_generateVersionSuffix;

	private File m_propertiesFile;

	private int m_maxVersionSuffixLength = -1;
	
	private String m_qualifier;

	private int m_significantDigits = -1;

	private ArrayList<FileSet> m_fileSets;

	private ArrayList<FileSetGroup> m_fileSetGroups;

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

	    	if(m_input == null)
				throw new BuildException("Missing attribute input", getLocation());
			if(m_output == null)
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
			FeatureConsolidator fc = new FeatureConsolidator(m_input, m_output, m_propertiesFile, featuresAndPlugins, m_qualifier, m_generateVersionSuffix, m_maxVersionSuffixLength, m_significantDigits);
			fc.run();
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), this.getLocation());
		}
	}

	public void setGenerateVersionSuffix(boolean flag)
	{
		m_generateVersionSuffix = flag;
	}

	public void setMaxVersionSuffixLength(int len)
	{
		m_maxVersionSuffixLength = len;
	}

	public void setSignificantDigits(int count)
	{
		m_significantDigits = count;
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
