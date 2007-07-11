package org.eclipse.buckminster.jdt.ant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.buckminster.jdt.internal.SaxableClasspath;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.JavaCore;
import org.xml.sax.SAXException;

public class ProjectClasspath extends Task
{
	public abstract class Entry
	{
		private boolean m_exported;

		private IPath m_path;

		public abstract IClasspathEntry createEntry() throws BuildException;

		public IPath getPath()
		{
			return m_path;
		}

		public boolean isExported()
		{
			return m_exported;
		}

		public void setExported(boolean exported)
		{
			m_exported = exported;
		}

		public void setPath(String path)
		{
			m_path = new Path(path);
		}

		public void setPath(IPath path)
		{
			m_path = path;
		}

		protected IPath getRequiredPath()
		{
			if(m_path == null)
				throw missingRequiredAttribute("path");
			return m_path;
		}
	}

	public class ContainerEntry extends Entry
	{
		@Override
		public IClasspathEntry createEntry() throws BuildException
		{
			return JavaCore.newContainerEntry(getRequiredPath(), isExported());
		}
	}

	public class LibraryEntry extends Entry
	{
		private IPath m_sourceAttachmentPath;

		private IPath m_sourceAttachmentRootPath;

		@Override
		public IClasspathEntry createEntry() throws BuildException
		{
			return JavaCore.newLibraryEntry(getRequiredPath(), m_sourceAttachmentPath,
				m_sourceAttachmentRootPath, isExported());
		}

		public IPath getSourceAttachmentPath()
		{
			return m_sourceAttachmentPath;
		}

		public IPath getSourceAttachmentRootPath()
		{
			return m_sourceAttachmentRootPath;
		}

		public void setSourceAttachmentPath(File sourceAttachmentPath)
		{
			m_sourceAttachmentPath = fileToPath(sourceAttachmentPath);
		}

		public void setSourceAttachmentRootPath(File sourceAttachmentRootPath)
		{
			m_sourceAttachmentRootPath = fileToPath(sourceAttachmentRootPath);
		}
	}

	public class ProjectEntry extends Entry
	{
		@Override
		public IClasspathEntry createEntry() throws BuildException
		{
			return JavaCore.newProjectEntry(getRequiredPath(), isExported());
		}
	}

	public class VariableEntry extends LibraryEntry
	{
		@Override
		public IClasspathEntry createEntry() throws BuildException
		{
			return JavaCore.newVariableEntry(getRequiredPath(), getSourceAttachmentPath(),
				getSourceAttachmentRootPath(), isExported());
		}
	}

	private IPath m_projectDir;

	private ArrayList<Entry> m_entries;

	private ArrayList<FileSet> m_librarySets;

	private ArrayList<Entry> getEntries()
	{
		if(m_entries == null)
			m_entries = new ArrayList<Entry>();
		return m_entries;
	}

	public void addLibrarySet(FileSet fileSet)
	{
		if(m_librarySets == null)
			m_librarySets = new ArrayList<FileSet>();
		m_librarySets.add(fileSet);
	}

	public ContainerEntry createContainerEntry()
	{
		ContainerEntry entry = new ContainerEntry();
		getEntries().add(entry);
		return entry;
	}

	public LibraryEntry createLibraryEntry()
	{
		LibraryEntry entry = new LibraryEntry();
		getEntries().add(entry);
		return entry;
	}

	public ProjectEntry createProjectEntry()
	{
		ProjectEntry entry = new ProjectEntry();
		getEntries().add(entry);
		return entry;
	}

	public VariableEntry createVariableEntry()
	{
		VariableEntry entry = new VariableEntry();
		getEntries().add(entry);
		return entry;
	}

	@Override
	public void execute() throws BuildException
	{
		if(m_projectDir == null)
			throw missingRequiredAttribute("projectDir");

		if(m_librarySets != null)
		{
			for(FileSet fileSet : m_librarySets)
			{
				DirectoryScanner ds = fileSet.getDirectoryScanner(getProject());
				IPath base = fileToPath(ds.getBasedir());
				for(String fileName : ds.getIncludedFiles())
				{
					LibraryEntry le = createLibraryEntry();
					le.setPath(base.append(fileName));
					le.setExported(true);
				}
			}
			m_librarySets = null;
		}
	
		OutputStream output = null;
		try
		{
			output = new BufferedOutputStream(new FileOutputStream(m_projectDir.append(".classpath").toFile()));

			IClasspathEntry[] entries;
			if(m_entries == null)
				entries = new IClasspathEntry[0];
			else
			{
				int top = m_entries.size();
				entries = new IClasspathEntry[top];
				for(int idx = 0; idx < top; ++idx)
					entries[idx] = m_entries.get(idx).createEntry();
			}
			Utils.serialize(new SaxableClasspath(m_projectDir, entries), output);
		}
		catch(SAXException e)
		{
			throw new BuildException(e, getLocation());
		}
		catch(IOException e)
		{
			throw new BuildException(e, getLocation());
		}
		finally
		{
			IOUtils.close(output);
		}
	}

	public void setProjectDir(File projectDir)
	{
		m_projectDir = fileToPath(projectDir);
	}

	BuildException missingRequiredAttribute(String attributeName)
	{
		return new BuildException("Missing required attribute " + attributeName, getLocation());
	}

	static IPath fileToPath(File file) throws BuildException
	{
		try
		{
			return (file == null ? null : Path.fromOSString(file.getCanonicalPath().toString()));
		}
		catch(IOException e)
		{
			throw new BuildException(e);
		}
	}
}
