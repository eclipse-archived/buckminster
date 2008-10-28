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

import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.FibonacciMonitorWrapper;
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
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
	private boolean m_singleThreaded = false;

	private boolean m_holdQueue = false;

	private static int s_jobCounter = 0;

	private final ArrayList<IProgressMonitor> m_jobMonitors = new ArrayList<IProgressMonitor>();

	private final IResourceMapResolverFactory m_factory;

	private IProgressMonitor m_topMonitor;

	private final LinkedList<ResolverNodeWithJob> m_waitQueue = new LinkedList<ResolverNodeWithJob>();

	public ResourceMapResolver(IResourceMapResolverFactory factory, ResolutionContext context, boolean singleThreaded) throws CoreException
	{
		super(context);
		m_factory = factory;
		m_singleThreaded = singleThreaded;
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
		ResolverNodeWithJob node = job.getNode();

		synchronized(node)
		{
			node.setScheduled(false);
			if(node.isInvalidated())
				schedule(node);
		}
	}

	synchronized void cancelTopMonitor()
	{
		if(m_topMonitor != null)
			m_topMonitor.setCanceled(true);
	}

	@Override
	ResolverNode createResolverNode(ResolutionContext context, QualifiedDependency qDep, String requestorInfo)
	{
		return new ResolverNodeWithJob(this, context, qDep, requestorInfo);
	}

	@Override
	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException
	{
		beginTopMonitor(monitor);
		try
		{
			ComponentQuery query = getContext().getComponentQuery();
			ResolverNodeWithJob topNode = (ResolverNodeWithJob)getResolverNode(getContext(), new QualifiedDependency(
					request, query.getAttributes(request)), null);

			if(m_singleThreaded)
			{
				beginTopMonitor(monitor);
				schedule(topNode);
				endTopMonitor();
			}
			else
			{
				schedule(topNode);
				waitForCompletion(MonitorUtils.subMonitor(monitor, 1));
			}
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

			ResolverNodeWithJob topNode = (ResolverNodeWithJob)getResolverNode(context, bom.getQualifiedDependency(), bom.getTagInfo());

			m_holdQueue = true;
			if(topNode.rebuildTree(bom))
			{
				m_holdQueue = false;
				if(m_singleThreaded)
				{
					IStatus status = context.getStatus();
					if(status.getSeverity() == IStatus.ERROR && !context.isContinueOnError())
						throw new CoreException(status);
				}
				else
				{
					scheduleNext();
					waitForCompletion(MonitorUtils.subMonitor(monitor, 1));
				}
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
		if(m_singleThreaded)
			return;

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

	synchronized void resolutionPartDone()
	{
		if(m_singleThreaded)
			return;

		// Allow another job to enter. The resolution part of the
		// calling job is done.
		//
		--s_jobCounter;
		scheduleNext();
	}

	synchronized void removeJobMonitor(IProgressMonitor monitor)
	{
		if(m_singleThreaded)
			return;

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

	BOMNode innerResolve(NodeQuery query, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			BOMNode node = null;
			if(m_factory.isLocalResolve())
				node = localResolve(query, MonitorUtils.subMonitor(monitor, 5));
			else
				MonitorUtils.worked(monitor, 5);

			if(node == null && query.useResolutionService())
			{
				ComponentQuery cquery = query.getComponentQuery();
				URL rmapURL = cquery.getResolvedResourceMapURL();
				ResourceMap rmap = m_factory.getResourceMap(getContext(), rmapURL, cquery.getConnectContext());
				query.logDecision(ResolverDecisionType.USING_RESOURCE_MAP, rmapURL);
				node = rmap.resolve(query, MonitorUtils.subMonitor(monitor, 95));
			}
			else
				MonitorUtils.worked(monitor, 95);
			return node;
		}
		catch(CoreException e)
		{
			RMContext context = getContext();
			if(!context.isContinueOnError())
				throw e;
			context.addRequestStatus(query.getComponentRequest(), e.getStatus());
			return null;
		}
		finally
		{
			monitor.done();
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

		if(m_singleThreaded)
		{
			node.run(MonitorUtils.subMonitor(m_topMonitor, 1));
			node.setScheduled(false);
		}
		else
		{
			pushOnWaitQueue(node);
			if(!m_holdQueue)
				scheduleNext();
		}
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
					// between instances so we might run into situations
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
			{
				context.clearStatus();
				throw new CoreException(status);
			}
		}
		finally
		{
			monitor.done();
			jobBlocker.release();
		}
	}
}
