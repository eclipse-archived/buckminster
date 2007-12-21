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
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.buckminster.ant.types.FileSetGroup;
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

	private String m_nl;

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

			Project proj = getProject();
			List<File> files;
			if(m_fileSets == null)
				files = Collections.emptyList();
			else
			{
				files = new ArrayList<File>();
				for(FileSet fs : m_fileSets)
				{
		            DirectoryScanner ds = fs.getDirectoryScanner(proj);
		            File dir = fs.getDir(proj);
		            for(String file : ds.getIncludedFiles())
		            	files.add(new File(dir, file));
				}
			}

			CreateProductBase createProduct = new CreateProductBase(
					m_os, m_ws, m_arch, m_nl, m_productFile, files, new Path(m_outputDir.toString()), getTargetLocation(), m_copyJavaLauncher);

			createProduct.execute();
			for(Map.Entry<String,String> hint : createProduct.getHints().entrySet())
				proj.setUserProperty(hint.getKey(), hint.getValue());
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

	public void setNl(String nl)
	{
		m_nl = nl;
	}
}
