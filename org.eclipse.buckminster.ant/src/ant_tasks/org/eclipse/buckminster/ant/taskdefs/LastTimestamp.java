/**************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ***************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

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
public class LastTimestamp extends Task {
	private String property;
	private String repositoryLocation;
	private String readerType;
	private String versionSelector;
	private String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
	private String timeZone = "UTC";

	@Override
	public void execute() throws BuildException {
		if (property == null)
			throw new BuildException("\"property\" must be set", this.getLocation());

		if (repositoryLocation == null)
			throw new BuildException("\"repositoryLocation\" must be set", this.getLocation());

		if (readerType == null)
			throw new BuildException("\"readerType\" must be set", this.getLocation());

		VersionSelector vs = null;
		if (versionSelector != null)
			vs = VersionSelector.fromString(versionSelector);
		try {
			DateFormat fmt = new SimpleDateFormat(dateFormat);
			fmt.setTimeZone(TimeZone.getTimeZone(timeZone));

			IProgressMonitor nm = new NullProgressMonitor();
			IReaderType rd = CorePlugin.getDefault().getReaderType(readerType);
			getProject().setProperty(property, fmt.format(rd.getLastModification(repositoryLocation, vs, nm)));
		} catch (Exception e) {
			Throwable c = BuckminsterException.unwind(e);
			String msg = c.getMessage();
			if (msg == null)
				msg = c.toString();
			throw new BuildException(msg, c, this.getLocation());
		}
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
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
	 * @param readerType
	 *            the readerType to set
	 */
	public void setReaderType(String readerType) {
		this.readerType = readerType;
	}

	/**
	 * @param repositoryLocation
	 *            the repositoryLocation to set
	 */
	public void setRepositoryLocation(String repositoryLocation) {
		this.repositoryLocation = repositoryLocation;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	/**
	 * @param versionSelector
	 *            the versionSelector to set
	 */
	public void setVersionSelector(String versionSelector) {
		this.versionSelector = versionSelector;
	}
}
