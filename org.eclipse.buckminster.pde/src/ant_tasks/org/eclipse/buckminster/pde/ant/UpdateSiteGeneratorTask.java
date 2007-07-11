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
import org.eclipse.buckminster.pde.tasks.UpdateSiteGenerator;

public class UpdateSiteGeneratorTask extends Task
{
	private File m_template;
	private File m_outputFile;

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
			if(m_outputFile == null)
				throw new BuildException("Missing attribute outputFile", getLocation());

	    	if(m_fileSetGroups != null)
	    	{
	    		for(FileSetGroup fsg : m_fileSetGroups)
		    		for(FileSet fs : fsg.getFileSets())
		    			addFileset(fs);
	    		m_fileSetGroups = null;
	    	}

			Project proj = getProject();
			List<File> features;
			if(m_fileSets == null)
				features = Collections.emptyList();
			else
			{
				features = new ArrayList<File>();
				for(FileSet fs : m_fileSets)
				{
		            DirectoryScanner ds = fs.getDirectoryScanner(proj);
		            File dir = fs.getDir(proj);
		            for(String file : ds.getIncludedFiles())
		            	features.add(new File(dir, file));
				}
			}

			UpdateSiteGenerator generator = new UpdateSiteGenerator(features, m_template, m_outputFile);
			generator.run();
		}
		catch(Exception e)
		{
			throw new BuildException(e.toString(), this.getLocation());
		}
	}

	public void setTemplate(String template)
	{
		if(template != null && template.length() == 0)
			template = null;
		m_template = template == null ? null : new File(template);
	}

	public void setOutputFile(File outputFile)
	{
		m_outputFile = outputFile;
	}
}
