/*******************************************************************************
 * Copyright (c) 2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.mapping.ResourceMapping;
import org.eclipse.core.resources.mapping.ResourceMappingContext;
import org.eclipse.core.resources.mapping.ResourceTraversal;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.ISchedulingRule;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.osgi.util.NLS;
import org.eclipse.team.core.RepositoryProvider;
import org.eclipse.team.core.mapping.ISynchronizationScope;
import org.eclipse.team.core.mapping.provider.SynchronizationScopeManager;

/**
 * A class capturing the common functionality of team actors (actors performing
 * SCM related actions).
 * 
 * @author michal.ruzicka@cloudsmith.com
 */
public abstract class AbstractTeamActor<C extends TeamPerformContext> extends AbstractActor {

	public static IStatus createMultiStatus(String message, IStatus[] statuses) {
		return new MultiStatus(BuckminsterTeam.PLUGIN_ID, IStatus.ERROR, statuses, message, null);
	}

	public static IStatus createStatus(String message) {
		return createStatus(message, null);
	}

	public static IStatus createStatus(String message, Throwable t) {
		return new Status(IStatus.ERROR, BuckminsterTeam.PLUGIN_ID, message, t);
	}

	public static IStatus createStatus(Throwable t) {
		return createStatus(t.getMessage(), t);
	}

	protected static ResourceMapping getResourceMapping(Object o) {
		if (o instanceof ResourceMapping) {
			return (ResourceMapping) o;
		}
		if (o instanceof IAdaptable) {
			IAdaptable adaptable = (IAdaptable) o;
			Object adapted = adaptable.getAdapter(ResourceMapping.class);
			if (adapted instanceof ResourceMapping) {
				return (ResourceMapping) adapted;
			}
		} else {
			Object adapted = Platform.getAdapterManager().getAdapter(o, ResourceMapping.class);
			if (adapted instanceof ResourceMapping) {
				return (ResourceMapping) adapted;
			}
		}
		return null;
	}

	/**
	 * Return a short description of this action.
	 * 
	 * @param actionContext
	 *            the context of this action
	 * @return the short description of this action
	 */
	public abstract String getActionName(IActionContext actionContext);

	protected SynchronizationScopeManager createScopeManager(String scopeName, ResourceMapping[] resourceMappings) {
		return new SynchronizationScopeManager(scopeName, resourceMappings, ResourceMappingContext.LOCAL_CONTEXT, true);
	}

	/**
	 * Create a team perform context from the given action context. This
	 * typically includes processing the actor properties and creating a team
	 * perform context specific to the actor implementing this method.
	 * 
	 * @param actionContext
	 *            the action context to use when building the team perform
	 *            context
	 * @return the team perform context for the given <code>actionContext</code>
	 * @throws CoreException
	 */
	protected abstract C createTeamPerformContext(IActionContext actionContext) throws CoreException;

	protected Map<RepositoryProvider, ProviderResources> getProviderResourcesMap(C teamPerformContext, ISynchronizationScope scope)
			throws CoreException {
		HashMap<RepositoryProvider, ProviderResources> result = new HashMap<RepositoryProvider, ProviderResources>();

		for (ResourceMapping mapping : scope.getMappings()) {
			ResourceTraversal[] traversals = scope.getTraversals(mapping);

			for (IProject project : mapping.getProjects()) {
				RepositoryProvider provider = RepositoryProvider.getProvider(project);
				if (provider != null && teamPerformContext.getReaderTypeForRepositoryProvider(provider.getID()) != null) {
					ProviderResources providerResources = result.get(provider);
					if (providerResources == null) {
						providerResources = new ProviderResources(provider);
						result.put(provider, providerResources);
					}
					providerResources.add(traversals);
				}
			}
		}

		return result;
	}

	/**
	 * Return a short description of an activity performed by this actor on the
	 * given repository provider.
	 * 
	 * @param provider
	 *            the repository provider being operated on by this actor
	 * @return the short description of the activity performed by this actor on
	 *         the given <code>provider</code>
	 */
	protected abstract String getTaskName(RepositoryProvider provider);

	@Override
	protected IStatus internalPerform(IActionContext actionContext, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(getActionName(actionContext), 10000);

		try {
			C teamPerformContext = createTeamPerformContext(actionContext);
			IProject[] projects = WorkspaceInfo.getProjectsInResolution(actionContext.getCSpec().getComponentIdentifier());

			int workAmount = 10000 / (projects.length + 1);
			monitor.worked(workAmount);

			ArrayList<IStatus> statuses = null;

			for (IProject project : projects) {
				try {
					internalTeamPerform(teamPerformContext, project, MonitorUtils.subMonitor(monitor, workAmount));
				} catch (CoreException ce) {
					teamPerformContext.collectStatus(ce.getStatus());
				} catch (Throwable t) {
					teamPerformContext.collectStatus(createStatus(t));
				} finally {
					if (teamPerformContext.hasErrors()) {
						if (statuses == null)
							statuses = new ArrayList<IStatus>();
						statuses.add(teamPerformContext.collectedStatus(NLS.bind(Messages.problems_during_0, NLS.bind(Messages.processing_of_0,
								project.getName()))));
					}
				}
			}

			if (statuses == null)
				return Status.OK_STATUS;

			return createMultiStatus(NLS.bind(Messages.problems_during_0, getActionName(actionContext)), statuses
					.toArray(new IStatus[statuses.size()]));
		} catch (CoreException ce) {
			throw ce;
		} catch (Throwable t) {
			throw new CoreException(createStatus(t));
		} finally {
			monitor.done();
		}
	}

	protected void internalTeamPerform(C teamPerformContext, IProject project, IProgressMonitor monitor) throws CoreException, InterruptedException {
		ResourceMapping resourceMapping = getResourceMapping(project);

		monitor.beginTask(NLS.bind(Messages.processing_project_0, project.getName()), 1100);
		try {
			if (resourceMapping != null) {
				SynchronizationScopeManager scopeManager = createScopeManager("Scope manager for " + project.getName(), //$NON-NLS-1$
						new ResourceMapping[] { resourceMapping });

				scopeManager.initialize(MonitorUtils.subMonitor(monitor, 100));

				Map<RepositoryProvider, ProviderResources> providerResourcesMap = getProviderResourcesMap(teamPerformContext, scopeManager.getScope());

				processProviderResourcesMap(teamPerformContext, providerResourcesMap, MonitorUtils.subMonitor(monitor, 1000));
			}
		} finally {
			monitor.done();
		}
	}

	/**
	 * Process any non-traversed (depth-zero) resources that were in the logical
	 * model that primed this action.
	 * 
	 * @param teamPerformContext
	 *            the team perform context of this action
	 * @param provider
	 *            the repository provider associated with the project containing
	 *            the folders
	 * @param nontraversedFolders
	 *            the folders
	 * @param monitor
	 *            a progress monitor
	 */
	protected void processNontraversedResources(C teamPerformContext, RepositoryProvider provider, IResource[] nontraversedFolders,
			IProgressMonitor monitor) throws CoreException, InterruptedException {
		// Default is to do nothing
	}

	/**
	 * Process resources from the given {@link ProviderResources}.
	 * 
	 * @param teamPerformContext
	 *            the team perform context of this action
	 * @param providerResources
	 *            a set of resources to process along with a repository provider
	 *            they are associated with
	 * @param monitor
	 *            a progress monitor
	 * @throws CoreException
	 * @throws InterruptedException
	 */
	protected void processProviderResources(C teamPerformContext, ProviderResources providerResources, IProgressMonitor monitor)
			throws CoreException, InterruptedException {
		RepositoryProvider provider = providerResources.getProvider();
		IResource[] deepResources = providerResources.getDeepResources();
		IResource[] shallowResources = providerResources.getShallowResources();
		IResource[] nontraversedFolders = providerResources.getNontraversedFolders();
		int workAmount = (deepResources.length > 0 ? 100 : 0) + (shallowResources.length > 0 ? 100 : 0) + (nontraversedFolders.length > 0 ? 10 : 0);

		monitor.beginTask(getTaskName(provider), workAmount);

		try {
			if (workAmount == 0)
				return;
			final ISchedulingRule rule = provider.getProject();
			try {
				Job.getJobManager().beginRule(rule, monitor);
				if (deepResources.length > 0)
					processResources(teamPerformContext, provider, deepResources, true /* recurse */, MonitorUtils.subMonitor(monitor, 100));
				if (shallowResources.length > 0)
					processResources(teamPerformContext, provider, shallowResources, false /* recurse */, MonitorUtils.subMonitor(monitor, 100));
				if (nontraversedFolders.length > 0)
					processNontraversedResources(teamPerformContext, provider, nontraversedFolders, MonitorUtils.subMonitor(monitor, 10));
			} finally {
				Job.getJobManager().endRule(rule);
			}
		} finally {
			monitor.done();
		}
	}

	protected void processProviderResourcesMap(C teamPerformContext, Map<RepositoryProvider, ProviderResources> providerResourcesMap,
			IProgressMonitor monitor) throws CoreException, InterruptedException {
		Set<Entry<RepositoryProvider, ProviderResources>> entrySet = providerResourcesMap.entrySet();

		monitor.beginTask(null, entrySet.size() * 1000);

		for (Entry<RepositoryProvider, ProviderResources> entry : entrySet) {
			ProviderResources traversalEntry = entry.getValue();
			monitor.setTaskName(getTaskName(traversalEntry.getProvider()));
			processProviderResources(teamPerformContext, traversalEntry, MonitorUtils.subMonitor(monitor, 1000));
		}

		monitor.done();
	}

	/**
	 * Process the given resources.
	 * 
	 * @param teamPerformContext
	 *            the context to use when processing the resources
	 * @param provider
	 *            a repository provider the given <code>resources</code> are
	 *            associated with
	 * @param resources
	 *            the resources to process
	 * @param recurse
	 *            whether the processing should be deep or shallow
	 * @param monitor
	 *            a progress monitor
	 * @throws CoreException
	 * @throws InterruptedException
	 */
	protected abstract void processResources(C teamPerformContext, RepositoryProvider provider, IResource[] resources, boolean recurse,
			IProgressMonitor monitor) throws CoreException, InterruptedException;
}
