/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.utils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * A set of utility methods for dealing with progress monitors.
 * 
 * @author Thomas Hallgren
 * @author Henrik Lindberg
 * 
 */
public class ProgressUtils {
	/**
	 * Check for cancellation.
	 * 
	 * @param monitor
	 *            The monitor to check
	 * @throws OperationCanceledException
	 *             if the monitor is cancelled.
	 */
	public static void checkIfCancelled(IProgressMonitor monitor) {
		if (monitor.isCanceled())
			throw new OperationCanceledException();
	}

	/**
	 * Check for cancellation, begin a new task, then end it. Used for monitors
	 * passed to methods that discovers that they don't need to do any work.
	 * 
	 * @param monitor
	 *            The monitor to begin and end.
	 */
	public static void noop(IProgressMonitor monitor) {
		checkIfCancelled(monitor);
		monitor.beginTask(null, 1);
		monitor.done();
	}

	/**
	 * Check for cancellation and create a sub progress monitor.
	 * 
	 * @param monitor
	 *            The parent monitor
	 * @param ticks
	 *            The number of ticks to consume in the parent.
	 * @return A sub progress monitor.
	 * @throws OperationCanceledException
	 *             if the monitor is canceled.
	 */
	public static IProgressMonitor submon(IProgressMonitor monitor, int ticks) {
		checkIfCancelled(monitor);
		return new SubProgressMonitor(monitor, ticks);
	}

	/**
	 * Check for cancellation and tell the monitor that work has been performed.
	 * 
	 * @param monitor
	 *            The monitor
	 * @param work
	 *            The number of ticks to consume in the monitor.
	 * @throws OperationCanceledException
	 *             if the monitor is cancelled.
	 */
	public static void worked(IProgressMonitor monitor, int work) {
		checkIfCancelled(monitor);
		monitor.worked(work);
	}
}
