/**************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
***************************************************************************/
package org.eclipse.buckminster.ant.taskdefs;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * Calculates the last timestamp for a repository and assigns the result to
 * a named property.
 * @author Thomas Hallgren
 */
public class LastRevision extends Task
{
	private String m_property;
	private String m_repositoryLocation;
	private String m_readerType;
	private String m_versionSelector;
	private File m_workingCopy;

	@Override
	public void execute()
	throws BuildException
	{
		if(m_property == null)
			throw new BuildException("\"property\" must be set", this.getLocation());

		if(m_readerType == null)
			throw new BuildException("\"readerType\" must be set", this.getLocation());

		try
		{
			IReaderType readerType = CorePlugin.getDefault().getReaderType(m_readerType);
			IProgressMonitor nm = new NullProgressMonitor();
			long revNo;

			if(m_repositoryLocation == null)
			{
				if(m_workingCopy == null)
					throw new BuildException("\"repositoryLocation\" or \"workingCopy\" must be set", this.getLocation());
				
				if(m_versionSelector != null)
					throw new BuildException("\"versionSelector can only be used together with \"repositoryLocation\" are mutually exclusive", this.getLocation());

				revNo = readerType.getLastRevision(m_workingCopy, nm);
			}
			else
			{
				if(m_workingCopy != null)
					throw new BuildException("\"repositoryLocation\" or \"workingCopy\" are mutually exclusive", this.getLocation());

				VersionSelector versionSelector = null;
				if(m_versionSelector != null)
					versionSelector = VersionSelector.fromString(m_versionSelector);
				revNo = readerType.getLastRevision(m_repositoryLocation, versionSelector, nm);
			}
			getProject().setProperty(m_property, Long.toString(revNo));
		}
		catch(Exception e)
		{
			Throwable c = BuckminsterException.unwind(e);
			String msg = c.getMessage();
			if(msg == null)
				msg = c.toString();
			throw new BuildException(msg, c, this.getLocation());
		}
	}

	/**
	 * Sets the name of the property that will receive the substituted value.
	 * @param property A property name.
	 */
	public void setProperty(String property)
	{
		m_property = property;
	}

	/**
	 * Sets the reader type that is used when determining the revision
	 * @param readerType the readerType to set
	 */
	public void setReaderType(String readerType)
	{
		m_readerType = readerType;
	}

	/**
	 * Sets the optional repository location
	 * @param repositoryLocation the repositoryLocation to set
	 */
	public void setRepositoryLocation(String repositoryLocation)
	{
		m_repositoryLocation = repositoryLocation;
	}

	/**
	 * Sets the optional version selector
	 * @param versionSelector the versionSelector to set
	 */
	public void setVersionSelector(String versionSelector)
	{
		m_versionSelector = versionSelector;
	}

	/**
	 * Sets the optional working copy
	 * @param versionSelector the versionSelector to set
	 */
	public void setWorkingCopy(File workingCopy)
	{
		m_workingCopy = workingCopy;
	}
}
