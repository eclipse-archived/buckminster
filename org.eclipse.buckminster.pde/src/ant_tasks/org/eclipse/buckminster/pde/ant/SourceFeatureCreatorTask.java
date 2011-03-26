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
import org.eclipse.buckminster.pde.tasks.SourceFeatureCreator;

/**
 * Ant task creates a source feature for a given feature and a set of source
 * features and source bundles.
 * 
 * @author Thomas Hallgren
 */
public class SourceFeatureCreatorTask extends Task {
	private NestedFileSets featuresAndBundles;

	private NestedFileSets translations;

	private File input;

	private File output;

	public static class NestedFileSets {
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
		
		public List<File> getFiles(Project proj) {
			if (fileSetGroups != null) {
				for (FileSetGroup fsg : fileSetGroups)
					for (FileSet fs : fsg.getFileSets())
						addFileset(fs);
				fileSetGroups = null;
			}
			if (fileSets == null)
				return Collections.emptyList();
			List<File> files = new ArrayList<File>();
			for (FileSet fs : fileSets) {
				DirectoryScanner ds = fs.getDirectoryScanner(proj);
				File dir = fs.getDir(proj);
				for (String file : ds.getIncludedFiles())
					files.add(new File(dir, file));
			}
			return files;
		}
	}

	@Override
	public void execute() throws BuildException {
		try {
			if (getInput() == null)
				throw new BuildException("Missing attribute inputFile", getLocation()); //$NON-NLS-1$
			if (getOutput() == null)
				throw new BuildException("Missing attribute outputDir", getLocation()); //$NON-NLS-1$

			Project proj = getProject();
			List<File> featuresAndPlugins = (featuresAndBundles == null) ? null : featuresAndBundles.getFiles(proj);
			List<File> translationFiles = (translations == null) ? null : translations.getFiles(proj);
			SourceFeatureCreator fc = new SourceFeatureCreator(getInput(), translationFiles, getOutput(), featuresAndPlugins);
			fc.run();
		} catch (Exception e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public File getInput() {
		return input;
	}

	public File getOutput() {
		return output;
	}

	public void setInputFile(File input) {
		this.input = input;
	}

	public void setOutputDir(File output) {
		this.output = output;
	}

	public NestedFileSets createFeaturesAndBundles() {
		featuresAndBundles = new NestedFileSets();
		return featuresAndBundles;
	}

	public NestedFileSets createTranslations() {
		translations = new NestedFileSets();
		return translations;
	}
}
