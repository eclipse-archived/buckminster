/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/
package org.eclipse.buckminster.generic.model.tree;

import org.eclipse.buckminster.generic.Messages;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author Henrik Lindberg
 *
 */
public abstract class PendingTreeDataNode extends BasicTreeDataNode
{

	private Job m_getNodeJob;
	public PendingTreeDataNode()
	{
		super(Messages.pending_);
	}
	/**
	 * Schedules a job that replaces the pending node with the node
	 * created by the {@link #createNode(IProgressMonitor)} method.
	 */
	public synchronized void schedule(final String jobName)
	{
		if(m_getNodeJob != null)
			return; // already created and scheduled
		m_getNodeJob = new Job(jobName){

			@Override
			protected IStatus run(IProgressMonitor monitor)
			{
				try
				{
					monitor.beginTask(jobName, IProgressMonitor.UNKNOWN);					
					ITreeDataNode[] nodes = createNode(monitor);
					getParent().replaceChild(PendingTreeDataNode.this, nodes);
					return Status.OK_STATUS;				
				}
				finally
				{
					monitor.done();
				}
			}
		};
		m_getNodeJob.setUser(false);
		m_getNodeJob.setPriority(Job.LONG);
		m_getNodeJob.schedule();
	}
	/**
	 * Produce and return the replacement nodes for this pending node.
	 * @return
	 */
	public abstract ITreeDataNode[] createNode(IProgressMonitor monitor);

}
