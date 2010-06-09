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

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.pde.tasks.ConvertSiteToRuntime;

/**
 * Ant task that unpacks all features, scans the feature.xml for bundles that
 * needs to be unpacked, and then unpacks them.
 * 
 * @author Thomas Hallgren
 */
public class ConvertSiteToRuntimeTask extends Task {
	private File siteDir;

	@Override
	public void execute() throws BuildException {
		try {
			if (siteDir == null)
				throw new BuildException("Missing attribute siteDir", this.getLocation()); //$NON-NLS-1$
			ConvertSiteToRuntime cstr = new ConvertSiteToRuntime(siteDir);
			cstr.run();
		} catch (Exception e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setSitedir(File siteDir) {
		this.siteDir = siteDir;
	}
}
