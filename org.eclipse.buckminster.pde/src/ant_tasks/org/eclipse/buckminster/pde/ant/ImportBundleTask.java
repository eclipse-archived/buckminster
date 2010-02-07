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
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.pde.internal.ImportBundle;
import org.eclipse.core.runtime.Path;

/**
 * @author Thomas Hallgren
 * 
 */
public class ImportBundleTask extends Task {
	private URL siteURL;
	private File destdir;
	private String bundleName;

	@Override
	public void execute() throws BuildException {
		try {
			if (bundleName == null)
				throw this.missingAttribute("bundleName"); //$NON-NLS-1$
			if (destdir == null)
				throw this.missingAttribute("destdir"); //$NON-NLS-1$
			if (siteURL == null)
				throw this.missingAttribute("siteURL"); //$NON-NLS-1$

			ImportBundle importBundle = new ImportBundle(bundleName, siteURL, new Path(destdir.toString()));
			importBundle.execute();
		} catch (Exception e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setBundleName(String bundleName) {
		this.bundleName = bundleName;
	}

	public void setDestdir(File destDir) {
		this.destdir = destDir;
	}

	public void setSiteDir(File siteDir) throws BuildException {
		try {
			this.siteURL = siteDir.toURI().toURL();
		} catch (MalformedURLException e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setSiteURL(String siteURL) throws BuildException {
		try {
			this.siteURL = new URL(siteURL);
		} catch (MalformedURLException e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	protected BuildException missingAttribute(String property) {
		return new BuildException("Missing attribute \"" + property + '"', this.getLocation()); //$NON-NLS-1$
	}
}
