/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.cvspkg.ant;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.cvspkg.internal.CVSSession;
import org.eclipse.buckminster.cvspkg.internal.RepositoryMetaData;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * Performs substitutions on a given value and assigns the result to
 * a named property.
 * @author Thomas Hallgren
 */
public class LastTimestamp extends Task
{
	private String m_property;
	private String m_cvsRoot;
	private String m_dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
	private String m_timeZone = "UTC";

	@Override
	public void execute()
	throws BuildException
	{
		if(m_property == null)
			throw new BuildException("\"property\" must be set", this.getLocation());

		if(m_cvsRoot == null)
			throw new BuildException("\"cvsRoot\" must be set", this.getLocation());

		CVSSession session = null;
		try
		{
			DateFormat fmt = new SimpleDateFormat(m_dateFormat);
			fmt.setTimeZone(TimeZone.getTimeZone(m_timeZone));

			IProgressMonitor nm = new NullProgressMonitor();
			session = new CVSSession(m_cvsRoot);
			RepositoryMetaData metaData = RepositoryMetaData.getMetaData(session, null, nm);
			this.getProject().setProperty(m_property, fmt.format(metaData.getLastModification()));
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
			if(session != null)
				session.close();
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
	 * Sets the CVSRoot.
	 * @param value
	 */
	public void setCVSRoot(String value)
	{
		m_cvsRoot = value;
	}

	public void setDateFormat(String dateFormat)
	{
		m_dateFormat = dateFormat;
	}

	public void setTimeZone(String timeZone)
	{
		m_timeZone = timeZone;
	}
}
