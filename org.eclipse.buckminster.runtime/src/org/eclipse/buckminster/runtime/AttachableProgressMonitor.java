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
package org.eclipse.buckminster.runtime;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

public class AttachableProgressMonitor {

	private class ProgressMonitor implements IProgressMonitor {

		private int totalWork;

		private int ticks;

		private String task;

		private String subTask;

		private IProgressMonitor mainProgressMonitor;

		public ProgressMonitor(IProgressMonitor mainprogressMonitor) {
			this.mainProgressMonitor = mainprogressMonitor;
		}

		@Override
		public void beginTask(String name, int work) {
			this.totalWork = work;
			this.task = name;
			mainProgressMonitor.beginTask(name, work);
			for (IProgressMonitor attachedMonitor : attachedMonitors) {
				attachedMonitor.beginTask(name, work);
			}

		}

		@Override
		public void done() {
			mainProgressMonitor.done();
			for (IProgressMonitor attachedMonitor : attachedMonitors) {
				attachedMonitor.done();
			}

		}

		@Override
		public void internalWorked(double work) {
			mainProgressMonitor.internalWorked(work);
			for (IProgressMonitor attachedMonitor : attachedMonitors) {
				attachedMonitor.internalWorked(work);
			}
		}

		@Override
		public boolean isCanceled() {
			return mainProgressMonitor.isCanceled();
		}

		@Override
		public void setCanceled(boolean value) {
			mainProgressMonitor.setCanceled(value);
			for (IProgressMonitor attachedMonitor : attachedMonitors) {
				attachedMonitor.setCanceled(value);
			}

		}

		@Override
		public void setTaskName(String name) {
			mainProgressMonitor.setTaskName(name);
			for (IProgressMonitor attachedMonitor : attachedMonitors) {
				attachedMonitor.setTaskName(name);
			}

		}

		@Override
		public void subTask(String name) {
			this.subTask = name;
			mainProgressMonitor.subTask(name);
			for (IProgressMonitor attachedMonitor : attachedMonitors) {
				attachedMonitor.subTask(name);
			}

		}

		@Override
		public void worked(int work) {
			ticks += work;
			mainProgressMonitor.worked(work);
			for (IProgressMonitor attachedMonitor : attachedMonitors) {
				attachedMonitor.worked(work);
			}

		}

		void attachProgressMonitor(IProgressMonitor toAttach) {

			if (task != null) {
				toAttach.beginTask(task, totalWork);
				toAttach.worked(ticks);
			}

			if (subTask != null) {
				toAttach.subTask(subTask);
			}

		}
	}

	private ProgressMonitor monitor;

	private List<IProgressMonitor> attachedMonitors = new ArrayList<IProgressMonitor>();

	/**
	 * attaches the given IProgressMonitor to the main progress monitor that was
	 * passed in {@link AttachableProgressMonitor#wrap(IProgressMonitor)}. All
	 * work done on this main progress monitor will be mirrored to the attached
	 * progress monitors. If the main progress monitor already did work before
	 * this method was invoked, the monitor to be attached will be adjusted to
	 * the current state of the main monitor. It
	 * {@link AttachableProgressMonitor#wrap(IProgressMonitor)} has not been
	 * called yet, the given monitor will be attached as soon as a main monitor
	 * is being wrapped.
	 * 
	 * @param toAttach
	 *            - the monitor to be attached to the main monitor
	 * @throws RuntimeException
	 *             if wrap was not called prior to this method
	 * @throws IllegalArgumentException
	 *             if the given monitor is <code>null</code>
	 * @see AttachableProgressMonitor#wrap(IProgressMonitor)
	 */
	public void attachProgressMonitor(IProgressMonitor toAttach) {

		if (toAttach == null) {
			throw new IllegalArgumentException("The given IProgressMonitor must not be null"); //$NON-NLS-1$
		}
		if (monitor != null) {
			monitor.attachProgressMonitor(monitor);

		}
		attachedMonitors.add(toAttach);

	}

	/**
	 * Wraps this {@link AttachableProgressMonitor} around the given
	 * {@link IProgressMonitor}.
	 * <p>
	 * The {@link AttachableProgressMonitor} will mirror all calls to the given
	 * {@link IProgressMonitor} to all monitors that get attached in
	 * {@link AttachableProgressMonitor#attachProgressMonitor(IProgressMonitor)}
	 * . This method <b>must</b> be called prior to call
	 * {@link AttachableProgressMonitor#attachedMonitors} and <b>must not</b> be
	 * called more than once.
	 * 
	 * @param mainProgressMonitor
	 *            the ProgressMonitor that monitors the actual work
	 * @return A {@link ProgressMonitor} that forwards all calls to the given
	 *         mainProgressMonitor and all attached ones.
	 * @throws IllegalArgumentException
	 *             if the given {@link IProgressMonitor} is <code>null</code>
	 * @throws RuntimeException
	 *             if this method is called more than once
	 * @see AttachableProgressMonitor#attachedMonitors
	 */
	public IProgressMonitor wrap(IProgressMonitor mainProgressMonitor) {
		if (mainProgressMonitor == null) {
			throw new IllegalArgumentException("The given IProgressMonitor must not be null"); //$NON-NLS-1$
		}
		if (monitor != null) {
			throw new RuntimeException("Only one ProgressMonitor can be wrapped at a time"); //$NON-NLS-1$
		}
		monitor = new ProgressMonitor(mainProgressMonitor);
		for (IProgressMonitor attachedMonitor : attachedMonitors) {
			monitor.attachProgressMonitor(attachedMonitor);
		}
		return monitor;
	}

}
