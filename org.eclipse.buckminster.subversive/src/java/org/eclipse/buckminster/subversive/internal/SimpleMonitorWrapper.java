/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.subversive.internal;

import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.team.svn.core.connector.ISVNProgressMonitor;
import org.eclipse.team.svn.core.operation.SVNNullProgressMonitor;
import org.eclipse.team.svn.core.utility.ProgressMonitorUtility;
import org.eclipse.team.svn.core.utility.SubProgressMonitorWithInfo;

/**
 * @author Thomas Hallgren
 */
public class SimpleMonitorWrapper implements ISVNProgressMonitor {
	public static ISVNProgressMonitor beginTask(IProgressMonitor monitor, int ticks) {
		if (monitor == null)
			return new SVNNullProgressMonitor();

		monitor.beginTask(null, ticks);
		return new SimpleMonitorWrapper(monitor, ticks);
	}

	private final SubProgressMonitorWithInfo monitor;

	private SimpleMonitorWrapper(IProgressMonitor monitor, int ticks) {
		this.monitor = new SubProgressMonitorWithInfo(monitor, ticks);
	}

	@Override
	public boolean isActivityCancelled() {
		return monitor.isCanceled();
	}

	@Override
	public void progress(int current, int total, ItemState state) {
		if (total != IProgressMonitor.UNKNOWN) {
			int real = ProgressMonitorUtility.TOTAL_WORK * current / total;
			real -= monitor.getCurrentProgress();
			monitor.worked(real);
		} else
			monitor.unknownProgress(current);
	}

	// This method is no longer necessary since it's not
	// declared in the inteface. We keep it to ensure
	// backward compatibility
	public void reportError(String msg) {
		Buckminster.getLogger().info("SVN error: " + msg); //$NON-NLS-1$
	}
}
