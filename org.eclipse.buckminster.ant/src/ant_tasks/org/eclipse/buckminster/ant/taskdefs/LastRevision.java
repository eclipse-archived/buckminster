/**************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ***************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * Calculates the last timestamp for a repository and assigns the result to a
 * named property.
 * 
 * @author Thomas Hallgren
 */
public class LastRevision extends Task {
	private String property;
	private String repositoryLocation;
	private String readerType;
	private String versionSelector;
	private File workingCopy;

	@Override
	public void execute() throws BuildException {
		if (property == null)
			throw new BuildException("\"property\" must be set", this.getLocation());

		if (readerType == null)
			throw new BuildException("\"readerType\" must be set", this.getLocation());

		try {
			IReaderType rd = CorePlugin.getDefault().getReaderType(readerType);
			IProgressMonitor nm = new NullProgressMonitor();
			long revNo;

			if (repositoryLocation == null) {
				if (workingCopy == null)
					throw new BuildException("\"repositoryLocation\" or \"workingCopy\" must be set", this.getLocation());

				if (versionSelector != null)
					throw new BuildException("\"versionSelector can only be used together with \"repositoryLocation\" are mutually exclusive", this
							.getLocation());

				revNo = rd.getLastRevision(workingCopy, nm);
			} else {
				if (workingCopy != null)
					throw new BuildException("\"repositoryLocation\" or \"workingCopy\" are mutually exclusive", this.getLocation());

				VersionSelector vs = null;
				if (versionSelector != null)
					vs = VersionSelector.fromString(versionSelector);
				revNo = rd.getLastRevision(repositoryLocation, vs, nm);
			}
			getProject().setProperty(property, Long.toString(revNo));
		} catch (Exception e) {
			Throwable c = BuckminsterException.unwind(e);
			String msg = c.getMessage();
			if (msg == null)
				msg = c.toString();
			throw new BuildException(msg, c, this.getLocation());
		}
	}

	/**
	 * Sets the name of the property that will receive the substituted value.
	 * 
	 * @param property
	 *            A property name.
	 */
	public void setProperty(String property) {
		this.property = property;
	}

	/**
	 * Sets the reader type that is used when determining the revision
	 * 
	 * @param readerType
	 *            the readerType to set
	 */
	public void setReaderType(String readerType) {
		this.readerType = readerType;
	}

	/**
	 * Sets the optional repository location
	 * 
	 * @param repositoryLocation
	 *            the repositoryLocation to set
	 */
	public void setRepositoryLocation(String repositoryLocation) {
		this.repositoryLocation = repositoryLocation;
	}

	/**
	 * Sets the optional version selector
	 * 
	 * @param versionSelector
	 *            the versionSelector to set
	 */
	public void setVersionSelector(String versionSelector) {
		this.versionSelector = versionSelector;
	}

	/**
	 * Sets the optional working copy
	 * 
	 * @param versionSelector
	 *            the versionSelector to set
	 */
	public void setWorkingCopy(File workingCopy) {
		this.workingCopy = workingCopy;
	}
}
