package org.eclipse.buckminster.pde.ant;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.eclipse.buckminster.ant.types.FileSetGroup;
import org.eclipse.buckminster.pde.tasks.UpdateSiteGenerator;
import org.eclipse.equinox.p2.metadata.Version;

public class UpdateSiteGeneratorTask extends VersionConsolidatorTask {
	private String versionProperty;

	private ArrayList<FileSet> fileSets;

	private ArrayList<FileSetGroup> fileSetGroups;

	/**
	 * Adds a nested <code>&lt;filesetgroup&gt;</code> element.
	 */
	public void add(FileSetGroup fsGroup) throws BuildException {
		if (fileSetGroups == null)
			fileSetGroups = new ArrayList<FileSetGroup>();
		fileSetGroups.add(fsGroup);
	}

	/**
	 * Adds a nested <code>&lt;fileset&gt;</code> element.
	 */
	public void addFileset(FileSet fs) throws BuildException {
		if (fileSets == null)
			fileSets = new ArrayList<FileSet>();
		fileSets.add(fs);
	}

	@Override
	public void execute() throws BuildException {
		try {
			if (fileSetGroups != null) {
				for (FileSetGroup fsg : fileSetGroups)
					for (FileSet fs : fsg.getFileSets())
						addFileset(fs);
				fileSetGroups = null;
			}

			Project proj = getProject();
			List<File> features;
			if (fileSets == null)
				features = Collections.emptyList();
			else {
				features = new ArrayList<File>();
				for (FileSet fs : fileSets) {
					DirectoryScanner ds = fs.getDirectoryScanner(proj);
					File dir = fs.getDir(proj);
					for (String file : ds.getIncludedFiles())
						features.add(new File(dir, file));
				}
			}

			UpdateSiteGenerator generator = new UpdateSiteGenerator(features, getInput(), getOutput(), getPropertiesFile(), getQualifier());
			if (versionProperty != null) {
				Version version = generator.run(true);
				if (version != null)
					getProject().setUserProperty(versionProperty, version.toString());
			} else
				generator.run(false);
		} catch (Exception e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setTemplate(String template) {
		if (template != null && template.length() == 0)
			template = null;
		setInputFile(new File(template));
	}

	public void setVersionProperty(String versionProperty) {
		this.versionProperty = versionProperty;
	}
}
