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

import org.apache.tools.ant.BuildException;
import org.eclipse.buckminster.pde.tasks.SourceBundleConsolidator;

/**
 * Ant task that updates the version qualifier of a bundle and adds an
 * Eclipse-SourceBundle attribute to the manifest
 * 
 * @author Achim Demelt
 */
public class SourceBundleConsolidatorTask extends VersionConsolidatorTask {
	@Override
	public void execute() throws BuildException {
		try {
			if (getInput() == null)
				throw new BuildException("Missing attribute input", getLocation()); //$NON-NLS-1$
			if (getOutput() == null)
				throw new BuildException("Missing attribute output", getLocation()); //$NON-NLS-1$

			SourceBundleConsolidator bc = new SourceBundleConsolidator(getInput(), getOutput(), getPropertiesFile(), getQualifier());
			bc.run();
		} catch (Exception e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}
}
