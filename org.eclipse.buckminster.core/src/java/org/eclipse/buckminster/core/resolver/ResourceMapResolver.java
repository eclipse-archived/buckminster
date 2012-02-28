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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.helpers.FibonacciMonitorWrapper;
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
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
public class ResourceMapResolver extends LocalResolver implements IJobChangeListener, IResolver {
	private boolean singleThreaded = false;

	private boolean holdQueue = false;

	private static int jobCounter = 0;

	private final ArrayList<IProgressMonitor> jobMonitors = new ArrayList<IProgressMonitor>();

	private final IResourceMapResolverFactory factory;

	private IProgressMonitor topMonitor;

	private final LinkedList<ResolverNodeWithJob> waitQueue = new LinkedList<ResolverNodeWithJob>();

	public ResourceMapResolver(IResourceMapResolverFactory factory, ResolutionContext context, boolean singleThreaded) throws CoreException {
		super(context);
		this.factory = factory;
		this.singleThreaded = singleThreaded;
	}

	@Override
	public void aboutToRun(IJobChangeEvent event) {
	}

	@Override
	public void awake(IJobChangeEvent event) {
	}

	@Override
	public void done(IJobChangeEvent event) {
		ResolverNodeWithJob.NodeResolutionJob job = (ResolverNodeWithJob.NodeResolutionJob) event.getJob();
		job.removeJobChangeListener(this);
		ResolverNodeWithJob node = job.getNode();

		synchronized (node) {
			node.setScheduled(false);
			if (node.isInvalidated() && !node.isForceUnresolved())
				schedule(node);
		}
	}

	@Override
	public BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException {
		beginTopMonitor(monitor);
		try {
			ResolutionContext ctx = getContext();
			ComponentQuery query = ctx.getComponentQuery();
			ResolverNodeWithJob topNode = (ResolverNodeWithJob) getResolverNode(ctx,
					new QualifiedDependency(request, query.getAttributes(request, ctx)), null);

			if (singleThreaded) {
				beginTopMonitor(monitor);
				schedule(topNode);
				endTopMonitor();
			} else {
				schedule(topNode);
				waitForCompletion(MonitorUtils.subMonitor(monitor, 1));
			}
			return createBillOfMaterials(topNode);
		} finally {
			endTopMonitor();
		}
	}

	@Override
	public BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException {
		if (bom.isFullyResolved(getContext())) {
			MonitorUtils.complete(monitor);
			return bom;
		}

		beginTopMonitor(monitor);
		try {
			ComponentQuery cquery = bom.getQuery();
			ResolutionContext context = getContext();
			if (!(cquery == null || cquery.equals(context.getComponentQuery())))
				context = new ResolutionContext(cquery, context);

			ResolverNodeWithJob topNode = (ResolverNodeWithJob) getResolverNode(context, bom.getQualifiedDependency(), bom.getTagInfo());

			holdQueue = true;
			if (topNode.rebuildTree(bom)) {
				holdQueue = false;
				if (singleThreaded) {
					IStatus status = context.getStatus();
					if (status.getSeverity() == IStatus.ERROR && !context.isContinueOnError())
						throw new CoreException(status);
				} else {
					scheduleNext();
					waitForCompletion(MonitorUtils.subMonitor(monitor, 1));
				}
				BillOfMaterials newBom = createBillOfMaterials(topNode);
				if (!newBom.contentEqual(bom))
					bom = newBom;
			}
			return bom;
		} finally {
			holdQueue = false;
			endTopMonitor();
		}
	}

	@Override
	public synchronized void running(IJobChangeEvent event) {
		if (topMonitor != null)
			MonitorUtils.worked(topMonitor, 1);
	}

	@Override
	public void scheduled(IJobChangeEvent event) {
	}

	@Override
	public void sleeping(IJobChangeEvent event) {
	}

	synchronized void addJobMonitor(IProgressMonitor monitor) {
		if (singleThreaded)
			return;

		if (topMonitor == null || topMonitor.isCanceled()) {
			monitor.setCanceled(true);
			return;
		}

		int idx = jobMonitors.size();
		while (--idx >= 0)
			if (jobMonitors.get(idx) == monitor)
				return;

		jobMonitors.add(monitor);
	}

	synchronized void cancelTopMonitor() {
		if (topMonitor != null)
			topMonitor.setCanceled(true);
	}

	@Override
	ResolverNode createResolverNode(ResolutionContext context, QualifiedDependency qDep, String requestorInfo) {
		return new ResolverNodeWithJob(this, context, qDep, requestorInfo);
	}

	BOMNode innerResolve(NodeQuery query, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 100);
		try {
			BOMNode node = null;
			ComponentQuery cquery = null;
			URL rmapURL = null;
			ResourceMap rmap = null;
			boolean mayUseLocalResolver = true;

			if (query.useResolutionService()) {
				cquery = query.getComponentQuery();
				rmapURL = cquery.getResolvedResourceMapURL();
				rmap = factory.getResourceMap(getContext(), rmapURL, cquery.getConnectContext());
				Provider directProvider = rmap.getFirstProvider(query);
				if (directProvider != null && directProvider.hasLocalCache())
					// Use the given provider! Make no attempt to use the
					// LocalProvider
					mayUseLocalResolver = false;
			}

			if (mayUseLocalResolver) {
				if (factory.isLocalResolve()) {
					query.logDecision(ResolverDecisionType.USING_RESOLVER, "Local resolver"); //$NON-NLS-1$
					node = localResolve(query, MonitorUtils.subMonitor(monitor, 5));
				} else {
					query.logDecision(ResolverDecisionType.RESOLVER_REJECTED, "All local resolvers"); //$NON-NLS-1$
					MonitorUtils.worked(monitor, 5);
				}
			}

			if (node == null && rmap != null) {
				query.logDecision(ResolverDecisionType.USING_RESOURCE_MAP, rmapURL);
				node = rmap.resolve(query, MonitorUtils.subMonitor(monitor, 95));
			} else
				MonitorUtils.worked(monitor, 95);

			return node;
		} catch (CoreException e) {
			RMContext context = getContext();
			if (!context.isContinueOnError())
				throw e;
			context.addRequestStatus(query.getComponentRequest(), e.getStatus());
			return null;
		} finally {
			monitor.done();
		}
	}

	synchronized void removeJobMonitor(IProgressMonitor monitor) {
		if (singleThreaded)
			return;

		int idx = jobMonitors.size();
		while (--idx >= 0) {
			if (jobMonitors.get(idx) == monitor) {
				jobMonitors.remove(idx);
				break;
			}
		}
	}

	synchronized void resolutionPartDone() {
		if (singleThreaded)
			return;

		// Allow another job to enter. The resolution part of the
		// calling job is done.
		//
		--jobCounter;
		scheduleNext();
	}

	boolean schedule(ResolverNodeWithJob node) {
		synchronized (node) {
			if (node.isScheduled() || node.isResolved())
				return false;
			node.setScheduled(true);
		}

		if (singleThreaded) {
			node.run(MonitorUtils.subMonitor(topMonitor, 1));
			node.setScheduled(false);
		} else {
			pushOnWaitQueue(node);
			if (!holdQueue)
				scheduleNext();
		}
		return true;
	}

	private synchronized void beginTopMonitor(IProgressMonitor monitor) {
		monitor = new FibonacciMonitorWrapper(monitor);
		monitor.beginTask(null, 50);
		topMonitor = monitor;
	}

	private void cancelAllJobs() {
		synchronized (waitQueue) {
			waitQueue.clear();
		}

		synchronized (this) {
			int idx = jobMonitors.size();
			while (--idx >= 0)
				jobMonitors.get(idx).setCanceled(true);
			if (topMonitor != null)
				topMonitor.setCanceled(true);
		}
	}

	private synchronized void endTopMonitor() {
		topMonitor.done();
		topMonitor = null;
	}

	private ResolverNodeWithJob popWaitQueue() {
		synchronized (waitQueue) {
			return waitQueue.poll();
		}
	}

	private void pushOnWaitQueue(ResolverNodeWithJob node) {
		synchronized (waitQueue) {
			waitQueue.add(node);
		}
	}

	private boolean scheduleNext() {
		ArrayList<ResolverNodeWithJob> nodes = null;
		synchronized (getClass()) {
			int jobsToSchedule = factory.getResolverThreadsMax() - jobCounter;
			while (--jobsToSchedule >= 0) {
				ResolverNodeWithJob node = popWaitQueue();
				if (node == null)
					break;

				if (nodes == null)
					nodes = new ArrayList<ResolverNodeWithJob>();
				nodes.add(node);
				++jobCounter;
			}
		}

		if (nodes == null)
			return false;

		int top = nodes.size();
		for (int idx = 0; idx < top; ++idx) {
			ResolverNodeWithJob.NodeResolutionJob job = nodes.get(idx).getJob();
			job.addJobChangeListener(this);
			job.schedule();
		}
		return true;
	}

	private void waitForCompletion(IProgressMonitor monitor) throws CoreException {
		JobBlocker jobBlocker = new JobBlocker();
		jobBlocker.addNameBlock(Messages.Building_workspace);
		jobBlocker.addNameBlock(Messages.Periodic_workspace_save);
		monitor.beginTask(null, IProgressMonitor.UNKNOWN);
		try {
			IStatus status;
			RMContext context = getContext();
			try {
				for (;;) {
					Job.getJobManager().join(this, MonitorUtils.subMonitor(monitor, 1));

					// The waitQueue is ours but the job counter is share
					// between instances so we might run into situations
					// where we're not yet allowed to schedule anything.
					//
					if (waitQueue.isEmpty())
						break;

					while (!scheduleNext())
						//
						// Sleep a while, then try again
						//
						Thread.sleep(100);
				}
				status = context.getStatus();
			} catch (OperationCanceledException e) {
				status = Status.CANCEL_STATUS;
			} catch (InterruptedException e) {
				status = Status.CANCEL_STATUS;
			}

			if (status.getSeverity() == IStatus.CANCEL) {
				cancelAllJobs();
				throw new OperationCanceledException();
			}

			if (status.getSeverity() == IStatus.ERROR && !context.isContinueOnError()) {
				throw new CoreException(status);
			}
		} finally {
			monitor.done();
			jobBlocker.release();
		}
	}
}
