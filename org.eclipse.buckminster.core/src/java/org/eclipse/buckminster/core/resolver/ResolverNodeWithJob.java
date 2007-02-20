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
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.QualifiedDependency;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.GeneratorNode;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
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

		boolean isScheduled()
		{
			return m_scheduled;
		}

		void setScheduled(boolean flag)
		{
			m_scheduled = flag;
		}
	}

	private final NodeResolutionJob m_job;

	private final ResourceMapResolver m_resolver;

	ResolverNodeWithJob(ResourceMapResolver resolver, RMContext context, QualifiedDependency qDep)
	{
		super(new NodeQuery(context, qDep));
		m_job = new NodeResolutionJob(qDep.getRequest().toString());
		m_resolver = resolver;
	}

	@Override
	public synchronized void addDependencyQualification(QualifiedDependency newQDep) throws CoreException
	{
		super.addDependencyQualification(newQDep);
		if(isInvalidated())
			m_job.setScheduled(false);
	}

	protected IStatus run(IProgressMonitor monitor)
	{
		clearInvalidationFlag();
		m_resolver.addJobMonitor(monitor);
		try
		{
			DepNode node = resolve(monitor);
			if(node != null)
				buildTree(node);
		}
		catch(CoreException e)
		{
			m_resolver.getContext().addResolveException(e.getStatus());
		}
		catch(OperationCanceledException e)
		{
			return Status.CANCEL_STATUS;
		}
		catch(Throwable e)
		{
			CorePlugin.getLogger().warning(e.toString(), e);
			m_resolver.getContext().addResolveException(BuckminsterException.wrap(e).getStatus());
		}
		finally
		{
			m_resolver.removeJobMonitor(monitor);
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
		RMContext context = getQuery().getContext();
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
		ResolverNode[] children = new ResolverNode[top];
		boolean didSchedule = false;
		for(int idx = 0; idx < top; ++idx)
		{
			DepNode childNode = nodeChildren.get(idx);
			ComponentQuery childQuery = childNode.getQuery();
			RMContext childContext = (childQuery == null) ? context : new RMContext(childQuery, context);
			ResolverNode child = m_resolver.getResolverNode(childContext, childNode.getQualifiedDependency());
			children[idx] = child;
			if(((ResolverNodeWithJob)child).buildTree(childNode))
				didSchedule = true;
		}
		setResolution(resolution, children);
		return didSchedule;
	}

	private synchronized DepNode resolve(IProgressMonitor monitor) throws CoreException
	{
		NodeQuery query = getQuery();
		if(query.skipComponent() || isResolved())
		{
			MonitorUtils.complete(monitor);
			return null;
		}
		return m_resolver.innerResolve(query, monitor);
	}
}
