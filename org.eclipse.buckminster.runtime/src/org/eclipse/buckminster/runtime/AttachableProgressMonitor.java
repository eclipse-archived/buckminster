package org.eclipse.buckminster.runtime;

/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

public class AttachableProgressMonitor
{

	private class ProgressMonitor implements IProgressMonitor
	{

		private int m_totalWork;

		private int m_ticks;

		private String m_task;

		private String m_subTask;

		private IProgressMonitor m_mainProgressMonitor;

		public ProgressMonitor(IProgressMonitor mainprogressMonitor)
		{
			this.m_mainProgressMonitor = mainprogressMonitor;
		}

		public void beginTask(String name, int totalWork)
		{
			this.m_totalWork = totalWork;
			this.m_task = name;
			m_mainProgressMonitor.beginTask(name, totalWork);
			for(IProgressMonitor monitor : m_attachedMonitors)
			{
				monitor.beginTask(name, totalWork);
			}

		}

		public void done()
		{
			m_mainProgressMonitor.done();
			for(IProgressMonitor monitor : m_attachedMonitors)
			{
				monitor.done();
			}

		}

		public void internalWorked(double work)
		{
			m_mainProgressMonitor.internalWorked(work);
			for(IProgressMonitor subMonitor : m_attachedMonitors)
			{
				subMonitor.internalWorked(work);
			}
		}

		public boolean isCanceled()
		{
			return m_mainProgressMonitor.isCanceled();
		}

		public void setCanceled(boolean value)
		{
			m_mainProgressMonitor.setCanceled(value);
			for(IProgressMonitor monitor : m_attachedMonitors)
			{
				monitor.setCanceled(value);
			}

		}

		public void setTaskName(String name)
		{
			m_mainProgressMonitor.setTaskName(name);
			for(IProgressMonitor monitor : m_attachedMonitors)
			{
				monitor.setTaskName(name);
			}

		}

		public void subTask(String name)
		{
			this.m_subTask = name;
			m_mainProgressMonitor.subTask(name);
			for(IProgressMonitor monitor : m_attachedMonitors)
			{
				monitor.subTask(name);
			}

		}

		public void worked(int work)
		{
			m_ticks += work;
			m_mainProgressMonitor.worked(work);
			for(IProgressMonitor monitor : m_attachedMonitors)
			{
				monitor.worked(work);
			}

		}

		void attachProgressMonitor(IProgressMonitor toAttach)
		{

			if(m_task != null)
			{
				toAttach.beginTask(m_task, m_totalWork);
				toAttach.worked(m_ticks);
			}

			if(m_subTask != null)
			{
				toAttach.subTask(m_subTask);
			}

		}
	}

	private ProgressMonitor m_monitor;

	private List<IProgressMonitor> m_attachedMonitors = new ArrayList<IProgressMonitor>();

	/**
	 * attaches the given IProgressMonitor to the main progress monitor that was passed in
	 * {@link AttachableProgressMonitor#wrap(IProgressMonitor)}. All work done on this main progress monitor will be
	 * mirrored to the attached progress monitors. If the main progress monitor already did work before this method was
	 * invoked, the monitor to be attached will be adjusted to the current state of the main monitor. It
	 * {@link AttachableProgressMonitor#wrap(IProgressMonitor)} has not been called yet, the given monitor will be
	 * attached as soon as a main monitor is being wrapped.
	 * 
	 * @param toAttach
	 *            - the monitor to be attached to the main monitor
	 * @throws RuntimeException
	 *             if wrap was not called prior to this method
	 * @throws IllegalArgumentException
	 *             if the given monitor is <code>null</code>
	 * @see AttachableProgressMonitor#wrap(IProgressMonitor)
	 */
	public void attachProgressMonitor(IProgressMonitor toAttach)
	{

		if(toAttach == null)
		{
			throw new IllegalArgumentException("The given IProgressMonitor must not be null"); //$NON-NLS-1$
		}
		if(m_monitor != null)
		{
			m_monitor.attachProgressMonitor(m_monitor);

		}
		m_attachedMonitors.add(toAttach);

	}

	/**
	 * Wraps this {@link AttachableProgressMonitor} around the given {@link IProgressMonitor}.
	 * <p>
	 * The {@link AttachableProgressMonitor} will mirror all calls to the given {@link IProgressMonitor} to all monitors
	 * that get attached in {@link AttachableProgressMonitor#attachProgressMonitor(IProgressMonitor)}. This method
	 * <b>must</b> be called prior to call {@link AttachableProgressMonitor#m_attachedMonitors} and <b>must not</b> be
	 * called more than once.
	 * 
	 * @param mainProgressMonitor
	 *            the ProgressMonitor that monitors the actual work
	 * @return A {@link ProgressMonitor} that forwards all calls to the given mainProgressMonitor and all attached ones.
	 * @throws IllegalArgumentException
	 *             if the given {@link IProgressMonitor} is <code>null</code>
	 * @throws RuntimeException
	 *             if this method is called more than once
	 * @see AttachableProgressMonitor#m_attachedMonitors
	 */
	public IProgressMonitor wrap(IProgressMonitor mainProgressMonitor)
	{
		if(mainProgressMonitor == null)
		{
			throw new IllegalArgumentException("The given IProgressMonitor must not be null"); //$NON-NLS-1$
		}
		if(m_monitor != null)
		{
			throw new RuntimeException("Only one ProgressMonitor can be wrapped at a time"); //$NON-NLS-1$
		}
		m_monitor = new ProgressMonitor(mainProgressMonitor);
		for(IProgressMonitor monitor : m_attachedMonitors)
		{
			m_monitor.attachProgressMonitor(monitor);
		}
		return m_monitor;
	}

}
