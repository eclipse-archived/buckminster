package org.eclipse.buckminster.pde.ant;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.pde.tasks.JNLPSiteGenerator;

public class JNLPSiteGeneratorTask extends Task {
	private File siteDir;

	@Override
	public void execute() throws BuildException {
		try {
			if (siteDir == null)
				throw new BuildException("Missing attribute siteDir", this.getLocation()); //$NON-NLS-1$
			JNLPSiteGenerator generator = new JNLPSiteGenerator(siteDir);
			generator.run();
		} catch (Exception e) {
			throw new BuildException(e.toString(), e, this.getLocation());
		}
	}

	public void setSitedir(File siteDir) {
		this.siteDir = siteDir;
	}
}
