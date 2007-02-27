/**************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
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
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * Calculates the last timestamp for a repository and assigns the result to
 * a named property.
 * @author Thomas Hallgren
 */
public class LastTimestamp extends Task
{
	private String m_property;
	private String m_repositoryLocation;
	private String m_readerType;
	private String m_versionSelector;
	private String m_dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
	private String m_timeZone = "UTC";

	@Override
	public void execute()
	throws BuildException
	{
		if(m_property == null)
			throw new BuildException("\"property\" must be set", this.getLocation());

		if(m_repositoryLocation == null)
			throw new BuildException("\"repositoryLocation\" must be set", this.getLocation());

		if(m_readerType == null)
			throw new BuildException("\"readerType\" must be set", this.getLocation());

		IVersionSelector versionSelector = null;
		if(m_versionSelector != null)
		{
			try
			{
				versionSelector = VersionSelectorFactory.fromString(m_versionSelector);
			}
			catch(CoreException e)
			{
				throw new BuildException(e.getMessage(), getLocation());
			}
		}
		IVersionFinder versionFinder = null;
		try
		{
			DateFormat fmt = new SimpleDateFormat(m_dateFormat);
			fmt.setTimeZone(TimeZone.getTimeZone(m_timeZone));

			IProgressMonitor nm = new NullProgressMonitor();
			IReaderType readerType = CorePlugin.getDefault().getReaderType(m_readerType);
			this.getProject().setProperty(m_property, fmt.format(readerType.getLastModification(m_repositoryLocation, versionSelector, nm)));
		}
		catch(Exception e)
		{
			Throwable c = BuckminsterException.unwind(e);
			String msg = c.getMessage();
			if(msg == null)
				msg = c.toString();
			throw new BuildException(msg, c, this.getLocation());
		}
		finally
		{
			if(versionFinder != null)
				versionFinder.close();
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

	public void setDateFormat(String dateFormat)
	{
		m_dateFormat = dateFormat;
	}

	public void setTimeZone(String timeZone)
	{
		m_timeZone = timeZone;
	}

	/**
	 * @param readerType the readerType to set
	 */
	public void setReaderType(String readerType)
	{
		m_readerType = readerType;
	}

	/**
	 * @param repositoryLocation the repositoryLocation to set
	 */
	public void setRepositoryLocation(String repositoryLocation)
	{
		m_repositoryLocation = repositoryLocation;
	}

	/**
	 * @param versionSelector the versionSelector to set
	 */
	public void setVersionSelector(String versionSelector)
	{
		m_versionSelector = versionSelector;
	}
}
