/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 */
public class PackageNames extends Task
{
	private String m_property;

	private final ArrayList<FileSet> m_fileSets = new ArrayList<FileSet>();

	public void addFileset(FileSet fileSet)
	{
		m_fileSets.add(fileSet);
	}

	@Override
	public void execute() throws BuildException
	{
		if(m_property == null)
			throw new BuildException("\"property\" must be set");

		if(m_fileSets.isEmpty())
			throw new BuildException("Please use at least one nested fileset");

		Project antProj = getProject();
		HashSet<IPath> packages = new HashSet<IPath>();
		for(FileSet fileSet : m_fileSets)
		{
			DirectoryScanner ds = fileSet.getDirectoryScanner(antProj);
			File base = ds.getBasedir();
			for(String fileName : ds.getIncludedFiles())
			{
				File file = new File(base, fileName);
				JarFile jarFile = null;
				try
				{
					jarFile = new JarFile(file);
					Enumeration<JarEntry> entries = jarFile.entries();
					while(entries.hasMoreElements())
					{
						JarEntry jarEntry = entries.nextElement();
						if(jarEntry.isDirectory())
							continue;
						IPath entryPath = new Path(jarEntry.getName());
						if(entryPath.segmentCount() < 2)
							continue;

						packages.add(entryPath.removeLastSegments(1));
					}
				}
				catch(IOException e)
				{
					throw new BuildException(e, getLocation());
				}
				finally
				{
					if(jarFile != null)
						try
						{
							jarFile.close();
						}
						catch(IOException e)
						{
						}
				}
			}
		}

		StringBuilder bld = new StringBuilder(packages.size() * 30);
		for(IPath pkgPath : packages)
		{
			if(bld.length() > 0)
				bld.append(',');
			int top = pkgPath.segmentCount();
			bld.append(pkgPath.segment(0));
			for(int idx = 1; idx < top; ++idx)
			{
				bld.append('.');
				bld.append(pkgPath.segment(idx));
			}
		}
		antProj.setProperty(m_property, bld.toString());
	}

	public void setProperty(String property)
	{
		m_property = property;
	}
}
