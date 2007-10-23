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

import java.util.List;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.metadata.model.DepNode;
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

class ResolverNodeWithJob extends ResolverNode
{
	class NodeResolutionJob extends Job
	{
		private boolean m_scheduled;

		NodeResolutionJob(String name)
		{
			super(name);
		}

		@Override
		public boolean belongsTo(Object family)
		{
			return family == m_resolver;
		}

		@Override
		protected IStatus run(IProgressMonitor monitor)
		{
			return ResolverNodeWithJob.this.run(monitor);
		}

		ResolverNodeWithJob getNode()
		{
			return ResolverNodeWithJob.this;
		}

		boolean isScheduled()
		{
			return m_scheduled;
		}

		void setScheduled(boolean flag)
		{
			m_scheduled = flag;
		}
	}

	private final ResourceMapResolver m_resolver;

	private final NodeResolutionJob m_job;

	ResolverNodeWithJob(ResourceMapResolver resolver, ResolutionContext context, QualifiedDependency qDep, String requestorInfo)
	{
		super(new NodeQuery(context, qDep), requestorInfo);
		m_job = new NodeResolutionJob(qDep.getRequest().toString());
		m_resolver = resolver;
	}

	@Override
	public synchronized void addDependencyQualification(QualifiedDependency newQDep) throws CoreException
	{
		super.addDependencyQualification(newQDep);
		if(isInvalidated() && !isScheduled())
			m_resolver.schedule(this);
	}

	protected IStatus run(IProgressMonitor monitor)
	{
		clearInvalidationFlag();
		m_resolver.addJobMonitor(monitor);
		DepNode node = null;
		try
		{
			node = resolve(monitor);
			if(node != null)
			{
				m_resolver.resolutionPartDone();
				buildTree(node);
			}
		}
		catch(CoreException e)
		{
			m_resolver.getContext().addException(getQuery().getComponentRequest(), e.getStatus());
		}
		catch(OperationCanceledException e)
		{
			return Status.CANCEL_STATUS;
		}
		catch(Throwable e)
		{
			CorePlugin.getLogger().warning(e.toString(), e);
			m_resolver.getContext().addException(getQuery().getComponentRequest(), BuckminsterException.wrap(e).getStatus());
		}
		finally
		{
			m_resolver.removeJobMonitor(monitor);
			if(node == null)
				m_resolver.resolutionPartDone();
		}
		return Status.OK_STATUS;
	}

	NodeResolutionJob getJob()
	{
		return m_job;
	}

	boolean isScheduled()
	{
		return m_job.isScheduled();
	}

	boolean rebuildTree(DepNode node) throws CoreException
	{
		clearInvalidationFlag();
		return buildTree(node);
	}

	void setScheduled(boolean scheduled)
	{
		m_job.setScheduled(scheduled);
	}

	private boolean buildTree(DepNode node) throws CoreException
	{
		if(isInvalidated())
			return false;

		ResolutionContext context = getQuery().getResolutionContext();
		GeneratorNode generatorNode = context.getGeneratorNode(node.getRequest().getName());
		if(generatorNode != null)
		{
			setGeneratorNode(generatorNode);
			return false;
		}

		Resolution resolution = node.getResolution();
		if(resolution == null)
			return m_resolver.schedule(this);

		context = startResolvingChildren(node);
		if(context == null)
			//
			// Must have been invalidated
			//
			return false;

		List<DepNode> nodeChildren = node.getChildren();
		int top = nodeChildren.size();
		if(top == 0)
		{
			setResolution(resolution, null);
			return false;
		}

		// This section can *not* be synchronized. If it is, we risk running into
		// deadlocks.
		//
		String tagInfo = resolution.getCSpec().getTagInfo(getTagInfo());
		ResolverNode[] children = new ResolverNode[top];
		boolean didSchedule = false;
		for(int idx = 0; idx < top; ++idx)
		{
			if(isInvalidated())
				return false;

			DepNode childNode = nodeChildren.get(idx);
			ComponentQuery childQuery = childNode.getQuery();
			ResolutionContext childContext = (childQuery == null) ? context : new ResolutionContext(childQuery, context);
			ResolverNode child = m_resolver.getResolverNode(childContext, childNode.getQualifiedDependency(), tagInfo);
			children[idx] = child;
			if(((ResolverNodeWithJob)child).buildTree(childNode))
				didSchedule = true;
		}
		setResolution(resolution, children);
		return didSchedule;
	}

	private DepNode resolve(IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query;
		synchronized(this)
		{
			query = getQuery();
			if(query.skipComponent() || isResolved())
			{
				MonitorUtils.complete(monitor);
				return null;
			}
		}
		if(isInvalidated())
			return null;

		return m_resolver.innerResolve(query, monitor);
	}
}
