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
import org.apache.tools.ant.types.FileSet;
import org.eclipse.buckminster.ant.types.FileSetGroup;
import org.eclipse.buckminster.pde.tasks.FeatureConsolidator;

/**
 * Ant task that updates the version of plugin- and feature references found in
 * a feature.xml file to reflect the actual plugin/feature that they appoint
 * within a certain scope given by a set of paths that are used when resolving
 * the appointed components. The elements of the paths should be either a plugin
 * folder, a feature folder, or a zipped folder containing a plugin or feature.
 * 
 * @author Thomas Hallgren
 */
public class FeatureConsolidatorTask extends VersionConsolidatorTask {
	private boolean generateVersionSuffix;

	private int maxVersionSuffixLength = -1;

	private int significantDigits = -1;

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

			if (getInput() == null)
				throw new BuildException("Missing attribute input", getLocation()); //$NON-NLS-1$
			if (getOutput() == null)
				throw new BuildException("Missing attribute output", getLocation()); //$NON-NLS-1$

			Project proj = getProject();
			List<File> featuresAndPlugins;
			if (fileSets == null)
				featuresAndPlugins = Collections.emptyList();
			else {
				featuresAndPlugins = new ArrayList<File>();
				for (FileSet fs : fileSets) {
					DirectoryScanner ds = fs.getDirectoryScanner(proj);
					File dir = fs.getDir(proj);
					for (String file : ds.getIncludedFiles())
						featuresAndPlugins.add(new File(dir, file));
				}
			}
			FeatureConsolidator fc = new FeatureConsolidator(getInput(), getOutput(), getPropertiesFile(), featuresAndPlugins, getQualifier(),
					generateVersionSuffix, maxVersionSuffixLength, significantDigits);
			fc.run();
		} catch (Exception e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setGenerateVersionSuffix(boolean flag) {
		generateVersionSuffix = flag;
	}

	public void setMaxVersionSuffixLength(int len) {
		maxVersionSuffixLength = len;
	}

	public void setSignificantDigits(int count) {
		significantDigits = count;
	}
}
