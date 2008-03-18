/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.runtime;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * @author kolwing
 */
public class MonitorUtils
{
	public static void begin(IProgressMonitor monitor, int ticks) throws OperationCanceledException
	{
		begin(monitor, null, ticks);
	}

	public static void begin(IProgressMonitor monitor, String taskName, int ticks) throws OperationCanceledException
	{
		if(monitor != null)
		{
			checkedTestCancelStatus(monitor);
			monitor.beginTask(taskName, ticks);
		}
	}

	/**
	 * Any method receiving a monitor must always do *something* with it. It can be passed on to
	 * another method (in which case the current receiver must not touch it in any other way) Or,
	 * the receiver can do the beginTask()/done() pattern and pass sub-monitors to others. Sometimes
	 * the receiver decides that it should do nothing. It still *must* have the monitor 'completed'
	 * in order to correctly signal callers that the receiver 'did some work' (even if it was
	 * nothing)
	 * @param monitor the monitor to run beginTask()/isCanceled()/done() for
	 * @throws OperationCanceledException if the monitor was cancelled
	 */
	public static void complete(IProgressMonitor monitor) throws OperationCanceledException
	{
		if(monitor != null)
		{
			try
			{
				checkedTestCancelStatus(monitor);
				monitor.beginTask(null, 1);
			}
			finally
			{
				monitor.done();
			}
		}
	}

	public static void done(IProgressMonitor monitor) throws OperationCanceledException
	{
		if(monitor != null)
		{
			checkedTestCancelStatus(monitor);
			monitor.done();
		}
	}

	/**
	 * Makes it easier to follow the pattern of ensuring there's a real monitor to talk to for the
	 * rest of the code in a monitor receiver
	 * @param monitor the monitor to check for nullness and replace with a 'real' monitor if
	 *            necessary
	 * @return The argument or a new {@link NullProgressMonitor} if the argument was
	 *         <code>null</code>
	 * @throws OperationCanceledException if the monitor was cancelled
	 */
	public static IProgressMonitor ensureNotNull(IProgressMonitor monitor) throws OperationCanceledException
	{
		if(monitor == null)
			monitor = new NullProgressMonitor();
		else
			checkedTestCancelStatus(monitor);
		return monitor;
	}

	/**
	 * Creates a new sub-progress monitor for the given monitor. The sub 
	 * progress monitor uses the given number of work ticks from its 
	 * parent monitor. A check for cancellation is made prior to the
	 * creation of the sub-progress monitor.
	 * @param monitor the parent progress monitor
	 * @param ticks the number of work ticks allocated from the
	 *    parent monitor
	 * @return The sub montior
	 * @throws OperationCanceledException if the monitor was cancelled
	 */
	public static IProgressMonitor subMonitor(IProgressMonitor monitor, int ticks) throws OperationCanceledException
	{
		if(monitor != null)
		{
			checkedTestCancelStatus(monitor);
			monitor = new SubProgressMonitor(monitor, ticks, 0);
		}
		return monitor;
	}

	/**
	 * Creates a new sub-progress monitor for the given monitor. The sub 
	 * progress monitor uses the given number of work ticks from its 
	 * parent monitor. A check for cancellation is made prior to the
	 * creation of the sub-progress monitor.
	 * @param monitor the parent progress monitor
	 * @param ticks the number of work ticks allocated from the
	 *    parent monitor
	 * @param style one of
	 *    <ul>
	 *    <li> <code>SUPPRESS_SUBTASK_LABEL</code> </li>
	 *    <li> <code>PREPEND_MAIN_LABEL_TO_SUBTASK</code> </li>
	 *    </ul>
	 * @see SubProgressMonitor#SUPPRESS_SUBTASK_LABEL
	 * @see SubProgressMonitor#PREPEND_MAIN_LABEL_TO_SUBTASK
	 * @return The sub monitor
	 * @throws OperationCanceledException if the monitor was cancelled
	 */
	public static IProgressMonitor subMonitor(IProgressMonitor monitor, int ticks, boolean prependMainLabelToSubtask) throws OperationCanceledException
	{
		if(monitor != null)
		{
			checkedTestCancelStatus(monitor);
			int style = prependMainLabelToSubtask ? SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK : 0;
			monitor = new SubProgressMonitor(monitor, ticks, style);
		}
		return monitor;
	}

	/**
	 * The typical pattern is to always check cancellation before doing any action. Use this to
	 * easily make the check a one-liner.
	 * @param monitor the monitor to check for cancellation
	 * @throws OperationCanceledException if the monitor was cancelled
	 */
	public static void testCancelStatus(IProgressMonitor monitor) throws OperationCanceledException
	{
		if(monitor != null && monitor.isCanceled())
			throw new OperationCanceledException();
	}

	/**
	 * A monitor should timely be checked for cancellation. This method makes it simple to ensure it
	 * is done at every work tick.
	 * @param monitor the monitor to test and then tick
	 * @param work the tick amount
	 * @throws OperationCanceledException if the monitor was cancelled
	 */
	public static void worked(IProgressMonitor monitor, int work) throws OperationCanceledException
	{
		if(monitor != null)
		{
			checkedTestCancelStatus(monitor);
			monitor.worked(work);
		}
	}

	private static void checkedTestCancelStatus(IProgressMonitor monitor) throws OperationCanceledException
	{
		if(monitor.isCanceled())
			throw new OperationCanceledException();
	}

}
