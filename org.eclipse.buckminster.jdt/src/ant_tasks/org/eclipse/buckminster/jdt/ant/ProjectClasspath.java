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

public class ProjectClasspath extends Task {
	public class ContainerEntry extends Entry {
		@Override
		public IClasspathEntry createEntry() throws BuildException {
			return JavaCore.newContainerEntry(getRequiredPath(), isExported());
		}
	}

	public abstract class Entry {
		private boolean exported;

		private IPath path;

		public abstract IClasspathEntry createEntry() throws BuildException;

		public IPath getPath() {
			return path;
		}

		public boolean isExported() {
			return exported;
		}

		public void setExported(boolean exported) {
			this.exported = exported;
		}

		public void setPath(IPath path) {
			this.path = path;
		}

		public void setPath(String path) {
			this.path = new Path(path);
		}

		protected IPath getRequiredPath() {
			if (path == null)
				throw missingRequiredAttribute("path");
			return path;
		}
	}

	public class LibraryEntry extends Entry {
		private IPath sourceAttachmentPath;

		private IPath sourceAttachmentRootPath;

		@Override
		public IClasspathEntry createEntry() throws BuildException {
			return JavaCore.newLibraryEntry(getRequiredPath(), sourceAttachmentPath, sourceAttachmentRootPath, isExported());
		}

		public IPath getSourceAttachmentPath() {
			return sourceAttachmentPath;
		}

		public IPath getSourceAttachmentRootPath() {
			return sourceAttachmentRootPath;
		}

		public void setSourceAttachmentPath(File sourceAttachmentPath) {
			this.sourceAttachmentPath = fileToPath(sourceAttachmentPath);
		}

		public void setSourceAttachmentRootPath(File sourceAttachmentRootPath) {
			this.sourceAttachmentRootPath = fileToPath(sourceAttachmentRootPath);
		}
	}

	public class ProjectEntry extends Entry {
		@Override
		public IClasspathEntry createEntry() throws BuildException {
			return JavaCore.newProjectEntry(getRequiredPath(), isExported());
		}
	}

	public class VariableEntry extends LibraryEntry {
		@Override
		public IClasspathEntry createEntry() throws BuildException {
			return JavaCore.newVariableEntry(getRequiredPath(), getSourceAttachmentPath(), getSourceAttachmentRootPath(), isExported());
		}
	}

	static IPath fileToPath(File file) throws BuildException {
		try {
			return (file == null ? null : Path.fromOSString(file.getCanonicalPath().toString()));
		} catch (IOException e) {
			throw new BuildException(e);
		}
	}

	private IPath projectDir;

	private ArrayList<Entry> entries;

	private ArrayList<FileSet> librarySets;

	public void addLibrarySet(FileSet fileSet) {
		if (librarySets == null)
			librarySets = new ArrayList<FileSet>();
		librarySets.add(fileSet);
	}

	public ContainerEntry createContainerEntry() {
		ContainerEntry entry = new ContainerEntry();
		getEntries().add(entry);
		return entry;
	}

	public LibraryEntry createLibraryEntry() {
		LibraryEntry entry = new LibraryEntry();
		getEntries().add(entry);
		return entry;
	}

	public ProjectEntry createProjectEntry() {
		ProjectEntry entry = new ProjectEntry();
		getEntries().add(entry);
		return entry;
	}

	public VariableEntry createVariableEntry() {
		VariableEntry entry = new VariableEntry();
		getEntries().add(entry);
		return entry;
	}

	@Override
	public void execute() throws BuildException {
		if (projectDir == null)
			throw missingRequiredAttribute("projectDir");

		if (librarySets != null) {
			for (FileSet fileSet : librarySets) {
				DirectoryScanner ds = fileSet.getDirectoryScanner(getProject());
				IPath base = fileToPath(ds.getBasedir());
				for (String fileName : ds.getIncludedFiles()) {
					LibraryEntry le = createLibraryEntry();
					le.setPath(base.append(fileName));
					le.setExported(true);
				}
			}
			librarySets = null;
		}

		OutputStream output = null;
		try {
			output = new BufferedOutputStream(new FileOutputStream(projectDir.append(".classpath").toFile()));

			IClasspathEntry[] classPaths;
			if (entries == null)
				classPaths = new IClasspathEntry[0];
			else {
				int top = entries.size();
				classPaths = new IClasspathEntry[top];
				for (int idx = 0; idx < top; ++idx)
					classPaths[idx] = entries.get(idx).createEntry();
			}
			Utils.serialize(new SaxableClasspath(projectDir, classPaths), output);
		} catch (SAXException e) {
			throw new BuildException(e, getLocation());
		} catch (IOException e) {
			throw new BuildException(e, getLocation());
		} finally {
			IOUtils.close(output);
		}
	}

	public void setProjectDir(File projectDir) {
		this.projectDir = fileToPath(projectDir);
	}

	BuildException missingRequiredAttribute(String attributeName) {
		return new BuildException("Missing required attribute " + attributeName, getLocation());
	}

	private ArrayList<Entry> getEntries() {
		if (entries == null)
			entries = new ArrayList<Entry>();
		return entries;
	}
}
