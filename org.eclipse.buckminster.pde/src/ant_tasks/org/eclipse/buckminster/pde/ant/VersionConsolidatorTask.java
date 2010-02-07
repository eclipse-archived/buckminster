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

import org.apache.tools.ant.Task;

/**
 * Ant task that updates the version qualifier of a bundle.
 * 
 * @author Thomas Hallgren
 */
public abstract class VersionConsolidatorTask extends Task {
	private File input;

	private File output;

	private File propertiesFile;

	private String qualifier;

	public File getInput() {
		return input;
	}

	public File getOutput() {
		return output;
	}

	public File getPropertiesFile() {
		return propertiesFile;
	}

	public String getQualifier() {
		return qualifier;
	}

	public void setInputFile(File input) {
		this.input = input;
	}

	public void setOutputFile(File output) {
		this.output = output;
	}

	public void setPropertiesFile(String propertiesFile) {
		if (propertiesFile == null || propertiesFile.length() == 0)
			this.propertiesFile = null;
		else
			this.propertiesFile = new File(propertiesFile);
	}

	public void setQualifier(String qualifier) {
		this.qualifier = qualifier;
	}
}
