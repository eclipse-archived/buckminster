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
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.IActor;
import org.eclipse.buckminster.core.actor.IGlobalContext;
import org.eclipse.buckminster.core.actor.IPerformManager;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.IActionArtifact;
import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.PathGroup;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.ActionArtifact;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Generator;
import org.eclipse.buckminster.core.cspec.model.Prerequisite;
import org.eclipse.buckminster.core.helpers.NullOutputStream;
import org.eclipse.buckminster.core.metadata.AmbigousComponentException;
import org.eclipse.buckminster.core.metadata.MissingComponentException;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
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

	public static IPath expandPath(Map<String, ? extends Object> properties, IPath path)
	{
		if(path != null)
			path = new Path(ExpandingProperties.expand(properties, path.toPortableString(), 0));
		return path;
	}

	public static PerformManager getInstance()
	{
		return INSTANCE;
	}

	private static void addAttributeChildren(GlobalContext ctx, Attribute attribute, Set<String> seen,
			List<Action> ordered) throws CoreException
	{
		if(attribute instanceof ActionArtifact)
			attribute = ((ActionArtifact)attribute).getAction();

		String attrId = attribute.toString();
		if(!seen.contains(attrId))
		{
			seen.add(attrId);
			CSpec cspec = attribute.getCSpec();
			for(Prerequisite preq : attribute.getPrerequisites())
			{
				Attribute ag = null;
				try
				{
					ag = preq.getReferencedAttribute(cspec, ctx);
				}
				catch(MissingComponentException e)
				{
					// The component might need to be generated in which case it's
					// generator must be built.
					//
					if(!CSpec.SELF_ARTIFACT.equals(preq.getAttribute()))
						//
						// A reference to a generated attribute can only reference the
						// component as a whole (i.e. it's 'self' attribute) since there's
						// no way of knowing what other attributes it may have.
						//
						throw e;

					List<Generator> generators = WorkspaceInfo.getGenerators(preq.getComponentName());
					if(generators.size() == 0)
						//
						// There is no known generator for the component
						//
						throw e;

					// If several candidates are found, we prefer the cspec that requested the
					// attribute if it is also a generator.
					//
					CSpec generatorCSpec = null;
					String generatorAttribute = null;
					for(Generator generator : generators)
					{
						CSpec candidate = WorkspaceInfo.getResolution(
								new ComponentRequest(generator.getComponent(), null, null), false).getCSpec();

						if(candidate.equals(cspec))
						{
							generatorCSpec = candidate;
							generatorAttribute = generator.getAttribute();
							break;
						}

						if(generatorCSpec != null)
							//
							// We find a generator for the desired component in more then
							// one other component. This is an ambiguity that we cannot resolve.
							//
							throw new AmbigousComponentException(generator.getComponent());

						generatorCSpec = candidate;
						generatorAttribute = generator.getAttribute();
					}

					// Add the attribute that represents the generated component
					//
					addAttributeChildren(ctx, generatorCSpec.getAttribute(generatorAttribute), seen, ordered);
				}

				if(ag != null)
					addAttributeChildren(ctx, ag, seen, ordered);
			}

			if(attribute instanceof IAction)
				ordered.add((Action)attribute);
		}
	}

	private static List<Action> getOrderedActionList(GlobalContext ctx, List<? extends IAttribute> attributes)
			throws CoreException
	{
		Set<String> seen = new HashSet<String>();
		List<Action> ordered = new ArrayList<Action>();
		for(IAttribute attribute : attributes)
			addAttributeChildren(ctx, (Attribute)attribute, seen, ordered);

		for(IAttribute attribute : attributes)
		{
			if(attribute instanceof IActionArtifact)
				attribute = ((ActionArtifact)attribute).getAction();

			String attrId = attribute.toString();
			if(!seen.contains(attrId))
			{
				seen.add(attrId);
				if(attribute instanceof IAction)
					ordered.add((Action)attribute);
			}
		}
		return ordered;
	}

	private static void makeWorkspaceAwareOfProducts(PerformContext ctx, Action action, IProgressMonitor monitor)
			throws CoreException
	{
		GlobalContext globalCtx = ctx.getGlobalContext();
		if(globalCtx.isWorkspaceRefreshPending())
			return;

		PathGroup[] pathGroups = action.getPathGroups(ctx, null);
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
					refreshAndSetDerivedPath(path.isAbsolute()
							? path
							: base.append(path), alreadyRefreshed, MonitorUtils.subMonitor(groupMonitor, 10));
				groupMonitor.done();
			}
		}
		finally
		{
			monitor.done();
		}
	}

	private static void refreshAndSetDerivedPath(IPath path, HashSet<IPath> alreadyRefreshed, IProgressMonitor monitor)
			throws CoreException
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
		monitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
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

	public IGlobalContext perform(ICSpecData cspec, String attributeName, Map<String, ? extends Object> props,
			boolean forced, boolean quiet, IProgressMonitor monitor) throws CoreException
	{
		return perform(Collections.singletonList(((CSpec)cspec.getAdapter(CSpec.class))
				.getRequiredAttribute(attributeName)), props, forced, quiet, monitor);
	}

	public IStatus perform(List<? extends IAttribute> attributes, IGlobalContext global, IProgressMonitor monitor)
			throws CoreException
	{
		// calculate a flat dependency list of actions to be done
		// adjust as needed when it's a build integration
		//
		GlobalContext globalCtx = (GlobalContext)global;
		List<Action> actionList = getOrderedActionList(globalCtx, attributes);
		monitor.beginTask(null, 100 * actionList.size());
		Logger logger = CorePlugin.getLogger();
		if(logger.isDebugEnabled())
		{
			StringBuilder bld = new StringBuilder(40 + actionList.size() * 40);
			bld.append(Messages.Actions_to_perform_in_order);
			for(Action action : actionList)
			{
				bld.append("\n  "); //$NON-NLS-1$
				action.toString(bld);
			}
			logger.debug(bld.toString());
		}

		MultiStatus retStatus = new MultiStatus(CorePlugin.getID(), IStatus.OK, "", null); //$NON-NLS-1$
		for(Action action : actionList)
		{
			// Check that the action hasn't been executed already. This may happen when actions
			// explicitly call on other actions (such as the fragment actor)
			//
			if(globalCtx.hasPerformedAction(action))
			{
				MonitorUtils.worked(monitor, 100);
				continue;
			}

			globalCtx.addPerformedAction(action);
			IActor actor = ActorFactory.getInstance().getActor(action);
			PrintStream out;
			PrintStream err;
			if(action.isAssignConsoleSupport())
			{
				out = Logger.getOutStream();
				err = Logger.getErrStream();
			}
			else
			{
				out = s_nullPrintStream;
				err = s_nullPrintStream;
			}

			IProgressMonitor cancellationMonitor = MonitorUtils.subMonitor(monitor, 1);
			cancellationMonitor.beginTask(null, IProgressMonitor.UNKNOWN);
			PerformContext ctx = new PerformContext(globalCtx, action, out, err, cancellationMonitor);
			if(!ctx.isForced() && action.isUpToDate(ctx))
			{
				cancellationMonitor.done();
				MonitorUtils.worked(monitor, 99);
				continue;
			}

			IStatus status = actor.perform(ctx, new SubProgressMonitor(monitor, 89,
					SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));
			cancellationMonitor.done();
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
		IStatus status = children.length == 1
				? children[0]
				: retStatus;

		if(status.getSeverity() == IStatus.ERROR)
			throw new CoreException(status);

		return status;
	}

	public IGlobalContext perform(List<? extends IAttribute> attributes, Map<String, ? extends Object> userProps,
			boolean forced, boolean quiet, IProgressMonitor monitor) throws CoreException
	{
		GlobalContext globalCtx = new GlobalContext(userProps, forced, quiet);
		monitor.beginTask(null, 1000);
		try
		{
			globalCtx.setStatus(perform(attributes, globalCtx, MonitorUtils.subMonitor(monitor, 900)));
			return globalCtx;
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
}
