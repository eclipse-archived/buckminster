/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.actor.IActor;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.ActionArtifact;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.helpers.NullOutputStream;
import org.eclipse.buckminster.core.metadata.model.IModelCache;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * @author kolwing
 * @author Thomas Hallgren
 */
public class PerformManager implements IPerformManager
{
	private static final PerformManager INSTANCE = new PerformManager();
	private static final PrintStream s_nullPrintStream = new PrintStream(NullOutputStream.INSTANCE);

	public static PerformManager getInstance()
	{
		return INSTANCE;
	}

	public IStatus perform(CSpec cspec, String attributeName, Map<String, String> props, boolean forced,
		IProgressMonitor monitor) throws CoreException
	{
		return this.perform(Collections.singletonList(cspec.getRequiredAttribute(attributeName)), props,
			forced, monitor);
	}

	public IStatus perform(List<Attribute> attributes, Map<String, String> props, boolean forced,
		IProgressMonitor monitor) throws CoreException
	{
		// calculate a flat dependency list of actions to be done
		// adjust as needed when it's a build integration
		//
		GlobalContext globalCtx = new GlobalContext();
		List<Action> actionList = getOrderedActionList(globalCtx, attributes);
		monitor.beginTask(null, 100 + 100 * actionList.size());
		try
		{
			Logger logger = CorePlugin.getLogger();
			if(logger.isDebugEnabled())
			{
				StringBuilder bld = new StringBuilder(40 + actionList.size() * 40);
				bld.append("Actions to perform (in order)");
				for(Action action : actionList)
				{
					bld.append("\n  ");
					action.toString(bld);
				}
				logger.debug(bld.toString());
			}

			MultiStatus retStatus = new MultiStatus(CorePlugin.getID(), IStatus.OK, "", null);
			for(Action action : actionList)
			{
				// We use ExpandingProperties all the way so that the expansion is deferred
				//
				Map<String, String> userProps = new ExpandingProperties();

				// enter all the context properties defined in the action as user props
				//
				userProps.putAll(action.getProperties());

				// enter all the global properties passed to this perform
				//
				if(props != null)
					userProps.putAll(props);

				// Add action specific dynamic properties
				//
				action.addDynamicProperties(userProps);

				IActor actor = ActorFactory.getInstance().getActor(action);
				PrintStream out;
				PrintStream err;
				if(action.assignConsoleSupport())
				{
					out = Logger.getOutStream();
					err = Logger.getErrStream();
				}
				else
				{
					out = s_nullPrintStream;
					err = s_nullPrintStream;
				}
				PerformContext ctx = new PerformContext(globalCtx, action, userProps, forced, out, err);
				if(!forced && action.isUpToDate(ctx))
				{
					if(logger.isDebugEnabled())
						logger.debug("action " + action + " is up to date");
					MonitorUtils.worked(monitor, 100);
					continue;
				}

				IStatus status = actor.perform(ctx, new SubProgressMonitor(monitor, 90, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
				MonitorUtils.testCancelStatus(monitor);

				switch(status.getSeverity())
				{
				case IStatus.WARNING:
				case IStatus.INFO:
					retStatus.add(status);
					// fall through

				case IStatus.OK:
					makeWorkspaceAwareOfProducts(ctx, action, MonitorUtils.subMonitor(monitor, 10));
					continue;

				case IStatus.CANCEL:
					throw new OperationCanceledException();

				case IStatus.ERROR:
					retStatus.add(status);
					break;
				}
				break;
			}

			IStatus[] children = retStatus.getChildren();
			IStatus status = children.length == 1 ? children[0] : retStatus;

			if(status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);

			return status;
		}
		finally
		{
			globalCtx.removeScheduled(MonitorUtils.subMonitor(monitor, 50));
			if(globalCtx.isWorkspaceRefreshPending())
				ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE,
					MonitorUtils.subMonitor(monitor, 50));
			monitor.done();
		}
	}

	public static IPath expandPath(Map<String, String> properties, IPath path)
	{
		if(path != null)
			path = new Path(ExpandingProperties.expand(properties, path.toPortableString(), 0));
		return path;
	}

	private static void makeWorkspaceAwareOfProducts(PerformContext ctx, Action action,
		IProgressMonitor monitor) throws CoreException
	{
		GlobalContext globalCtx = ctx.getGlobalContext();
		if(globalCtx.isWorkspaceRefreshPending())
			return;

		PathGroup[] pathGroups = action.getPathGroups(ctx);
		if(pathGroups.length == 0)
		{
			globalCtx.setWorkspaceRefreshPending(true);
			return;
		}

		monitor.beginTask(null, 100 * pathGroups.length);
		try
		{
			HashSet<IPath> alreadyRefreshed = new HashSet<IPath>();
			for(PathGroup pathGroup : pathGroups)
			{
				IProgressMonitor groupMonitor = MonitorUtils.subMonitor(monitor, 100);
				
				IPath[] paths = pathGroup.getPaths();
				groupMonitor.beginTask(null, 10 * paths.length);
				IPath base = pathGroup.getBase();
				for(IPath path : paths)
					refreshAndSetDerivedPath(base.append(path), alreadyRefreshed, MonitorUtils.subMonitor(
						groupMonitor, 10));
				groupMonitor.done();
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private static void refreshAndSetDerivedPath(IPath path, HashSet<IPath> alreadyRefreshed,
		IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask(null, 100);
		try
		{
			String leaf = null;
			if(!path.hasTrailingSeparator())
			{
				// Path denotes a file, refresh its directory
				//
				leaf = path.lastSegment();
				path = path.removeLastSegments(1).addTrailingSeparator();
			}

			if(alreadyRefreshed.contains(path))
				return;
			alreadyRefreshed.add(path);

			IContainer container = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path);
			if(container == null)
				return;

			if(!(container instanceof IProject))
				refreshParents(container.getParent(), MonitorUtils.subMonitor(monitor, 50));
			else
				MonitorUtils.worked(monitor, 50);
			container.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 50));

			if(leaf != null)
			{
				IFile file = container.getFile(new Path(leaf));
				if(file.exists())
					file.setDerived(true);
			}
			else
			{
				container.accept(new IResourceVisitor()
				{
					public boolean visit(IResource resource) throws CoreException
					{
						resource.setDerived(true);
						return true;
					}
				});
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private static void refreshParents(IContainer container, IProgressMonitor monitor) throws CoreException
	{
		monitor.beginTask("", IProgressMonitor.UNKNOWN);
		try
		{
			if(container != null)
			{
				// Don't refresh the parent of a project since that would be refreshing the
				// whole workspace.
				//
				if(!(container instanceof IProject))
					refreshParents(container.getParent(), MonitorUtils.subMonitor(monitor, 1));
				container.refreshLocal(IResource.DEPTH_ZERO, MonitorUtils.subMonitor(monitor, 1));
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private static List<Action> getOrderedActionList(IModelCache ctx, List<Attribute> attributes) throws CoreException
	{
		Set<Attribute> seen = new HashSet<Attribute>();
		List<Action> ordered = new ArrayList<Action>();
		for(Attribute attribute : attributes)
			addAttributeChildren(ctx, attribute, seen, ordered);

		for(Attribute attribute : attributes)
		{
			if(attribute instanceof ActionArtifact)
				attribute = ((ActionArtifact)attribute).getAction();

			if(!seen.contains(attribute))
			{
				seen.add(attribute);
				if(attribute instanceof Action)
					ordered.add((Action)attribute);
			}
		}
		return ordered;
	}

	private static void addAttributeChildren(IModelCache ctx, Attribute attribute, Set<Attribute> seen, List<Action> ordered)
	throws CoreException
	{
		if(attribute instanceof ActionArtifact)
			attribute = ((ActionArtifact)attribute).getAction();

		if(!seen.contains(attribute))
		{
			seen.add(attribute);
			CSpec cspec = attribute.getCSpec();
			for(Prerequisite preq : attribute.getPrerequisites())
				addAttributeChildren(ctx, preq.getReferencedAttribute(cspec, ctx), seen, ordered);
			if(attribute instanceof Action)
				ordered.add((Action)attribute);
		}
	}
}
