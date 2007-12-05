/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.subversive.internal;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.svn.core.connector.ISVNProgressMonitor;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.utility.ProgressMonitorUtility;
import org.eclipse.team.svn.core.utility.SubProgressMonitorWithInfo;

/**
 * @author Thomas Hallgren
 */
public class SimpleMonitorWrapper implements ISVNProgressMonitor
{
	private final SubProgressMonitorWithInfo m_monitor;

	private SimpleMonitorWrapper(IProgressMonitor monitor, int ticks)
	{
		m_monitor = new SubProgressMonitorWithInfo(monitor, ticks);
	}

	public boolean isActivityCancelled()
	{
		return m_monitor.isCanceled();
	}

	public void progress(int current, int total, ItemState state)
	{
		if(total != IProgressMonitor.UNKNOWN)
		{
			int real = ProgressMonitorUtility.TOTAL_WORK * current / total;
			real -= m_monitor.getCurrentProgress();
			m_monitor.worked(real);
		}
		else
			m_monitor.unknownProgress(current);
	}

	public static ISVNProgressMonitor beginTask(IProgressMonitor monitor, int ticks)
	{
		if(monitor == null)
			return new SVNNullProgressMonitor();

		monitor.beginTask(null, ticks);
		return new SimpleMonitorWrapper(monitor, ticks);
	}
}
