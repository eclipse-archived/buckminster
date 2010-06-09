package org.eclipse.buckminster.ant.types;

import java.util.ArrayList;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;

public class Path extends org.apache.tools.ant.types.Path {
	private ArrayList<FileSetGroup> fileSetGroups;

	public Path(Project proj) {
		super(proj);
	}

	/**
	 * Adds a nested <code>&lt;filesetgroup&gt;</code> element.
	 */
	public void add(FileSetGroup fsGroup) throws BuildException {
		if (isReference())
			throw this.noChildrenAllowed();
		if (fileSetGroups == null)
			fileSetGroups = new ArrayList<FileSetGroup>();
		fileSetGroups.add(fsGroup);
	}

	@Override
	public String[] list() {
		if (fileSetGroups != null) {
			for (FileSetGroup fsg : fileSetGroups)
				for (FileSet fs : fsg.getFileSets())
					this.addFileset(fs);
			fileSetGroups = null;
		}
		return super.list();
	}
}
