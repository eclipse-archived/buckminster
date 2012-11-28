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
import java.util.List;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;

class ResolverNodeWithJob extends ResolverNode {
	class NodeResolutionJob extends Job {
		private boolean scheduled;

		NodeResolutionJob(String name) {
			super(name);
		}

		@Override
		public boolean belongsTo(Object family) {
			return family == resolver;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			return ResolverNodeWithJob.this.run(monitor);
		}

		ResolverNodeWithJob getNode() {
			return ResolverNodeWithJob.this;
		}

		boolean isScheduled() {
			return scheduled;
		}

		void setScheduled(boolean flag) {
			scheduled = flag;
		}
	}

	private final ResourceMapResolver resolver;

	private final NodeResolutionJob job;

	private static final String SOURCE_SUFFIX = ".source"; //$NON-NLS-1$

	private static final String SOURCE_FEATURE_SUFFIX = ".source.feature"; //$NON-NLS-1$

	public static String getIdWithoutSource(String sourceId) {
		if (sourceId.endsWith(SOURCE_SUFFIX))
			return sourceId.substring(0, sourceId.length() - SOURCE_SUFFIX.length());
		if (sourceId.endsWith(SOURCE_FEATURE_SUFFIX))
			return sourceId.substring(0, sourceId.length() - SOURCE_FEATURE_SUFFIX.length()) + ".feature"; //$NON-NLS-1$
		return null;
	}

	ResolverNodeWithJob(ResourceMapResolver resolver, ResolutionContext context, QualifiedDependency qDep, String requestorInfo) {
		super(context.getNodeQuery(qDep), requestorInfo);
		this.job = new NodeResolutionJob(qDep.getRequest().toString());
		this.resolver = resolver;
	}

	@Override
	public synchronized void addDependencyQualification(QualifiedDependency newQDep, String tagInfo) throws CoreException {
		super.addDependencyQualification(newQDep, tagInfo);
		if (isInvalidated() && !(isForceUnresolved() || isScheduled()))
			resolver.schedule(this);
	}

	protected IStatus run(IProgressMonitor monitor) {
		if (isForceUnresolved()) {
			resolver.resolutionPartDone();
			return Status.OK_STATUS;
		}

		clearInvalidationFlag();
		resolver.addJobMonitor(monitor);
		BOMNode node = null;
		try {
			node = resolve(monitor);
			if (node != null) {
				resolver.resolutionPartDone();
				buildTree(node);
			}
		} catch (CoreException e) {
			RMContext context = resolver.getContext();
			context.addRequestStatus(getQuery().getComponentRequest(), e.getStatus());
			if (!context.isContinueOnError())
				resolver.cancelTopMonitor();
		} catch (OperationCanceledException e) {
			return Status.CANCEL_STATUS;
		} catch (Throwable e) {
			resolver.getContext().addRequestStatus(getQuery().getComponentRequest(), BuckminsterException.wrap(e).getStatus());
		} finally {
			resolver.removeJobMonitor(monitor);
			if (node == null)
				resolver.resolutionPartDone();
		}
		return Status.OK_STATUS;
	}

	NodeResolutionJob getJob() {
		return job;
	}

	boolean isScheduled() {
		return job.isScheduled();
	}

	boolean rebuildTree(BOMNode node) throws CoreException {
		clearInvalidationFlag();
		return buildTree(node);
	}

	void setScheduled(boolean scheduled) {
		job.setScheduled(scheduled);
	}

	private boolean buildTree(BOMNode node) throws CoreException {
		if (isInvalidated())
			return false;

		ResolutionContext context = getQuery().getResolutionContext();
		GeneratorNode generatorNode = context.getGeneratorNode(node.getRequest());
		if (generatorNode != null) {
			setGeneratorNode(generatorNode);
			return false;
		}

		Resolution resolution = node.getResolution();
		if (resolution == null)
			return resolver.schedule(this);

		context = startResolvingChildren(node);
		if (context == null)
			//
			// Must have been invalidated
			//
			return false;

		List<BOMNode> nodeChildren = node.getChildren();
		int top = nodeChildren.size();
		if (top == 0) {
			setResolution(resolution, null);
			return false;
		}

		// This section can *not* be synchronized. If it is, we risk running
		// into
		// deadlocks.
		//
		CSpec cspec = resolution.getCSpec();
		String tagInfo = cspec.getTagInfo(getTagInfo());
		boolean isInSourceForm = IMaterializer.WORKSPACE.equals(resolution.getProvider().getReaderType().getRecommendedMaterializer());
		List<ResolverNode> children = null;
		boolean didSchedule = false;
		for (int idx = 0; idx < top; ++idx) {
			if (isInvalidated())
				return false;

			BOMNode childNode = nodeChildren.get(idx);
			QualifiedDependency childReq = childNode.getQualifiedDependency();
			if (isInSourceForm && childReq.getRequest().isSyntheticSource()) {
				String name = childReq.getRequest().getName();
				if (getIdWithoutSource(name).equals(cspec.getName()))
					// Don't resolve source for source components
					continue;
			}

			ComponentQuery childQuery = childNode.getQuery();
			ResolutionContext childContext = (childQuery == null) ? context : new ResolutionContext(childQuery, context);
			ResolverNode child = resolver.getResolverNode(childContext, childReq, tagInfo);
			if (children == null)
				children = new ArrayList<ResolverNode>();
			children.add(child);
			if (((ResolverNodeWithJob) child).buildTree(childNode))
				didSchedule = true;
		}
		setResolution(resolution, children == null ? null : children.toArray(new ResolverNode[children.size()]));
		return didSchedule;
	}

	private BOMNode resolve(IProgressMonitor monitor) throws CoreException {
		NodeQuery query;
		synchronized (this) {
			query = getQuery();
			if (query.skipComponent() || isResolved()) {
				MonitorUtils.complete(monitor);
				return null;
			}
		}
		if (isInvalidated())
			return null;

		return resolver.innerResolve(query, monitor);
	}
}
