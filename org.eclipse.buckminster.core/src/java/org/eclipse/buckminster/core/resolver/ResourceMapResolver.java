/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.resolver;

import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.FibonacciMonitorWrapper;
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.IJobChangeEvent;
import org.eclipse.core.runtime.jobs.IJobChangeListener;
import org.eclipse.core.runtime.jobs.Job;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("serial")
public class ResourceMapResolver extends LocalResolver implements IJobChangeListener, IResolver
{
	private boolean m_holdQueue = false;

	private static int s_jobCounter = 0;

	private final ArrayList<IProgressMonitor> m_jobMonitors = new ArrayList<IProgressMonitor>();

	private final IResourceMapResolverFactory m_factory;

	private IProgressMonitor m_topMonitor;

	private final LinkedList<ResolverNodeWithJob> m_waitQueue = new LinkedList<ResolverNodeWithJob>();

	public ResourceMapResolver(IResourceMapResolverFactory factory, ResolutionContext context) throws CoreException
	{
		super(context);
		m_factory = factory;
	}

	public void aboutToRun(IJobChangeEvent event)
	{
	}

	public void awake(IJobChangeEvent event)
	{
	}

	public void done(IJobChangeEvent event)
	{
		ResolverNodeWithJob.NodeResolutionJob job = (ResolverNodeWithJob.NodeResolutionJob)event.getJob();
		job.removeJobChangeListener(this);
		job.setScheduled(false);
		--s_jobCounter;
		scheduleNext();
	}

	@Override
	ResolverNode createResolverNode(ResolutionContext context, QualifiedDependency qDep)
	{
		return new ResolverNodeWithJob(this, context, qDep);
	}

	@Override
	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException
	{
		beginTopMonitor(monitor);
		try
		{
			ComponentQuery query = getContext().getComponentQuery();
			ResolverNodeWithJob topNode = (ResolverNodeWithJob)getResolverNode(getContext(), new QualifiedDependency(
					request, query.getAttributes(request)));
			schedule(topNode);
			waitForCompletion(MonitorUtils.subMonitor(monitor, 1));
			return createBillOfMaterials(topNode);
		}
		finally
		{
			endTopMonitor();
		}
	}

	@Override
	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException
	{
		if(bom.isFullyResolved())
		{
			MonitorUtils.complete(monitor);
			return bom;
		}

		beginTopMonitor(monitor);
		try
		{
			ComponentQuery cquery = bom.getQuery();
			ResolutionContext context = getContext();
			if(!(cquery == null || cquery.equals(context.getComponentQuery())))
				context = new ResolutionContext(cquery, context);
			ResolverNodeWithJob topNode = (ResolverNodeWithJob)getResolverNode(context, bom.getQualifiedDependency());

			m_holdQueue = true;
			if(topNode.rebuildTree(bom))
			{
				m_holdQueue = false;
				scheduleNext();
				waitForCompletion(MonitorUtils.subMonitor(monitor, 1));
				BillOfMaterials newBom = createBillOfMaterials(topNode);
				if(!newBom.contentEqual(bom))
					bom = newBom;
			}
			return bom;
		}
		finally
		{
			m_holdQueue = false;
			endTopMonitor();
		}
	}

	public synchronized void running(IJobChangeEvent event)
	{
		if(m_topMonitor != null)
			MonitorUtils.worked(m_topMonitor, 1);
	}

	public void scheduled(IJobChangeEvent event)
	{
	}

	public void sleeping(IJobChangeEvent event)
	{
	}

	synchronized void addJobMonitor(IProgressMonitor monitor)
	{
		if(m_topMonitor == null || m_topMonitor.isCanceled())
		{
			monitor.setCanceled(true);
			return;
		}

		int idx = m_jobMonitors.size();
		while(--idx >= 0)
			if(m_jobMonitors.get(idx) == monitor)
				return;

		m_jobMonitors.add(monitor);
	}

	synchronized void removeJobMonitor(IProgressMonitor monitor)
	{
		int idx = m_jobMonitors.size();
		while(--idx >= 0)
		{
			if(m_jobMonitors.get(idx) == monitor)
			{
				m_jobMonitors.remove(idx);
				break;
			}
		}
	}

	DepNode innerResolve(NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		try
		{
			DepNode node = m_factory.isLocalResolve()
					? localResolve(query)
					: null;
			if(node == null)
			{
				ResourceMap rmap = m_factory.getResourceMap(query.getComponentQuery().getResourceMapURL());
				node = rmap.resolve(query, monitor);
			}
			return node;
		}
		catch(CoreException e)
		{
			RMContext context = getContext();
			if(!context.isContinueOnError())
				throw e;
			context.addException(e.getStatus());
			return null;
		}
	}

	boolean schedule(ResolverNodeWithJob node)
	{
		synchronized(node)
		{
			if(node.isScheduled() || node.isResolved())
				return false;
			node.setScheduled(true);
		}
		pushOnWaitQueue(node);
		if(!m_holdQueue)
			scheduleNext();
		return true;
	}

	private synchronized void beginTopMonitor(IProgressMonitor monitor)
	{
		monitor = new FibonacciMonitorWrapper(monitor);
		monitor.beginTask(null, 50);
		m_topMonitor = monitor;
	}

	private void cancelAllJobs()
	{
		synchronized(m_waitQueue)
		{
			m_waitQueue.clear();
		}

		synchronized(this)
		{
			int idx = m_jobMonitors.size();
			while(--idx >= 0)
				m_jobMonitors.get(idx).setCanceled(true);
			if(m_topMonitor != null)
				m_topMonitor.setCanceled(true);
		}
	}

	private synchronized void endTopMonitor()
	{
		m_topMonitor.done();
		m_topMonitor = null;
	}

	private ResolverNodeWithJob popWaitQueue()
	{
		synchronized(m_waitQueue)
		{
			return m_waitQueue.poll();
		}
	}

	private void pushOnWaitQueue(ResolverNodeWithJob node)
	{
		synchronized(m_waitQueue)
		{
			m_waitQueue.add(node);
		}
	}

	private boolean scheduleNext()
	{
		ArrayList<ResolverNodeWithJob> nodes = null;
		synchronized(getClass())
		{
			int jobsToSchedule = m_factory.getResolverThreadsMax() - s_jobCounter;
			while(--jobsToSchedule >= 0)
			{
				ResolverNodeWithJob node = popWaitQueue();
				if(node == null)
					break;

				if(nodes == null)
					nodes = new ArrayList<ResolverNodeWithJob>();
				nodes.add(node);
				++s_jobCounter;
			}
		}

		if(nodes == null)
			return false;

		int top = nodes.size();
		for(int idx = 0; idx < top; ++idx)
		{
			ResolverNodeWithJob.NodeResolutionJob job = nodes.get(idx).getJob();
			job.addJobChangeListener(this);
			job.schedule();
		}
		return true;
	}

	private void waitForCompletion(IProgressMonitor monitor) throws CoreException
	{
		JobBlocker jobBlocker = new JobBlocker();
		jobBlocker.addNameBlock("Building workspace");
		jobBlocker.addNameBlock("Periodic workspace save.");
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try
		{
			IStatus status;
			RMContext context = getContext();
			try
			{
				for(;;)
				{
					Job.getJobManager().join(this, MonitorUtils.subMonitor(monitor, 1));

					// The waitQueue is ours but the job counter is share
					// between instances so we migth run into situations
					// where we're not yet allowed to schedule anything.
					//
					if(m_waitQueue.isEmpty())
						break;

					while(!scheduleNext())
						//
						// Sleep a while, then try again
						//
						Thread.sleep(100);
				}
				status = context.getStatus();
			}
			catch(OperationCanceledException e)
			{
				status = Status.CANCEL_STATUS;
			}
			catch(InterruptedException e)
			{
				status = Status.CANCEL_STATUS;
			}

			if(status.getSeverity() == IStatus.CANCEL)
			{
				cancelAllJobs();
				throw new OperationCanceledException();
			}

			if(status.getSeverity() == IStatus.ERROR && !context.isContinueOnError())
				throw new CoreException(status);
		}
		finally
		{
			monitor.done();
			jobBlocker.release();
		}
	}
}
