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
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;

/**
 * @author kolwing
 * @author Thomas Hallgren
 */
public class PerformManager implements IPerformManager {
	abstract class ActionInvocation {
		abstract IStatus execute(GlobalContext globalCtx, IProgressMonitor monitor) throws CoreException;

		abstract Action getAction();

		abstract void toString(StringBuilder bld);
	}

	class DeferredActionInvocation extends ActionInvocation {
		private final CSpec requestor;

		private final Prerequisite prerequisite;

		public DeferredActionInvocation(CSpec requestor, Prerequisite prerequisite) {
			this.requestor = requestor;
			this.prerequisite = prerequisite;
		}

		@Override
		IStatus execute(GlobalContext globalCtx, IProgressMonitor monitor) throws CoreException {
			CSpec referenced = prerequisite.getReferencedCSpec(requestor, globalCtx);
			if (referenced == null)
				// Dependency not enabled
				return Status.OK_STATUS;

			Attribute attr = referenced.getRequiredAttribute(prerequisite.getName());
			Filter filter = attr.getFilter();
			if (filter != null && !filter.matches(globalCtx.getProperties()))
				// Attribute is not enabled
				return Status.OK_STATUS;

			return internalPerform(Collections.singletonList(attr), globalCtx, monitor);
		}

		@Override
		Action getAction() {
			return null;
		}

		@Override
		void toString(StringBuilder bld) {
			bld.append("Deferred invocation of "); //$NON-NLS-1$
			bld.append(prerequisite);
		}
	}

	class DirectActionInvocation extends ActionInvocation {
		private final Action action;

		private PerformContext performContext;

		DirectActionInvocation(Action action) {
			this.action = action;
		}

		@Override
		IStatus execute(GlobalContext globalCtx, IProgressMonitor monitor) throws CoreException {
			PrintStream out;
			PrintStream err;
			if (action.isAssignConsoleSupport()) {
				out = Logger.getOutStream();
				err = Logger.getErrStream();
			} else {
				out = nullPrintStream;
				err = nullPrintStream;
			}

			MonitorUtils.begin(monitor, 100);
			performContext = new PerformContext(globalCtx, action, out, err, monitor);
			if (globalCtx.hasPerformedAction(action)) {
				MonitorUtils.done(monitor);
				return Status.OK_STATUS;
			}
			globalCtx.addPerformedAction(action);

			if (!performContext.isForced() && action.isUpToDate(performContext)) {
				MonitorUtils.done(monitor);
				return Status.OK_STATUS;
			}

			IActor actor = ActorFactory.getInstance().getActor(action);
			IStatus status = actor.perform(performContext, new SubProgressMonitor(monitor, 90, SubProgressMonitor.PREPEND_MAIN_LABEL_TO_SUBTASK));

			if (status.getSeverity() == IStatus.CANCEL)
				throw new OperationCanceledException();

			if (status.getSeverity() != IStatus.ERROR)
				makeWorkspaceAwareOfProducts(MonitorUtils.subMonitor(monitor, 10));
			MonitorUtils.done(monitor);
			return status;
		}

		@Override
		final Action getAction() {
			return action;
		}

		final PerformContext getPerformContext() {
			return performContext;
		}

		@Override
		void toString(StringBuilder bld) {
			action.toString(bld);
		}

		private void makeWorkspaceAwareOfProducts(IProgressMonitor monitor) throws CoreException {
			PathGroup[] pathGroups = action.getPathGroups(performContext, null);
			int ticks = 100 * pathGroups.length;
			monitor.beginTask(null, ticks);
			try {
				HashSet<IPath> alreadyRefreshed = new HashSet<IPath>();
				for (PathGroup pathGroup : pathGroups) {
					IProgressMonitor groupMonitor = MonitorUtils.subMonitor(monitor, 100);

					IPath[] paths = pathGroup.getPaths();
					groupMonitor.beginTask(null, 10 * paths.length);
					IPath base = pathGroup.getBase();
					for (IPath path : paths)
						refreshAndSetDerivedPath(path.isAbsolute() ? path : base.append(path), alreadyRefreshed,
								MonitorUtils.subMonitor(groupMonitor, 10));
					groupMonitor.done();
				}
			} finally {
				MonitorUtils.done(monitor);
			}
		}
	}

	class GeneratorInvocation extends DirectActionInvocation {
		private final Generator generator;

		public GeneratorInvocation(Generator generator, Action generatorAction) {
			super(generatorAction);
			this.generator = generator;
		}

		@Override
		IStatus execute(GlobalContext globalCtx, IProgressMonitor monitor) throws CoreException {
			IStatus status = super.execute(globalCtx, monitor);
			if (status.getSeverity() == IStatus.ERROR)
				return status;
			generator.registerGeneratedResolution(getAction().getPathGroups(getPerformContext(), null), globalCtx, monitor);
			return status;
		}
	}

	private static final PerformManager INSTANCE = new PerformManager();

	private static final PrintStream nullPrintStream = new PrintStream(NullOutputStream.INSTANCE);

	public static IPath expandPath(Map<String, ? extends Object> properties, IPath path) {
		if (path != null)
			path = new Path(ExpandingProperties.expand(properties, path.toPortableString(), 0));
		return path;
	}

	public static PerformManager getInstance() {
		return INSTANCE;
	}

	private static void refreshAndSetDerivedPath(IPath path, HashSet<IPath> alreadyRefreshed, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask(null, 100);
		try {
			String leaf = null;
			if (!path.hasTrailingSeparator()) {
				// Path denotes a file, refresh its directory
				//
				leaf = path.lastSegment();
				path = path.removeLastSegments(1).addTrailingSeparator();
			}

			if (alreadyRefreshed.contains(path))
				return;
			alreadyRefreshed.add(path);

			IContainer container = ResourcesPlugin.getWorkspace().getRoot().getContainerForLocation(path);
			if (container == null)
				return;

			if (!(container instanceof IProject))
				refreshParents(container.getParent(), MonitorUtils.subMonitor(monitor, 50));
			else
				MonitorUtils.worked(monitor, 50);
			container.refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 40));

			if (leaf != null) {
				IFile file = container.getFile(new Path(leaf));
				if (file.exists())
					file.setDerived(true, MonitorUtils.subMonitor(monitor, 10));
				else
					MonitorUtils.worked(monitor, 10);
			} else {
				if (!(container instanceof IProgressMonitor))
					container.setDerived(true, MonitorUtils.subMonitor(monitor, 10));
				else
					MonitorUtils.worked(monitor, 10);
			}
		} finally {
			monitor.done();
		}
	}

	private static void refreshParents(IContainer container, IProgressMonitor monitor) throws CoreException {
		monitor.beginTask("", IProgressMonitor.UNKNOWN); //$NON-NLS-1$
		try {
			if (container != null) {
				// Don't refresh the parent of a project since that would be
				// refreshing the
				// whole workspace.
				//
				if (!(container instanceof IProject))
					refreshParents(container.getParent(), MonitorUtils.subMonitor(monitor, 1));
				container.refreshLocal(IResource.DEPTH_ZERO, MonitorUtils.subMonitor(monitor, 1));
			}
		} finally {
			monitor.done();
		}
	}

	@Override
	public IGlobalContext perform(ICSpecData cspec, String attributeName, Map<String, ? extends Object> props, boolean forced, boolean quiet,
			IProgressMonitor monitor) throws CoreException {
		return perform(Collections.singletonList(((CSpec) cspec.getAdapter(CSpec.class)).getRequiredAttribute(attributeName)), props, forced, quiet,
				monitor);
	}

	@Override
	public IStatus perform(List<? extends IAttribute> attributes, IGlobalContext global, IProgressMonitor monitor) throws CoreException {
		WorkspaceInfo.pushPerformContext(global);
		try {
			return internalPerform(attributes, global, monitor);
		} finally {
			WorkspaceInfo.popPerformContext();
		}
	}

	@Override
	public IGlobalContext perform(List<? extends IAttribute> attributes, Map<String, ? extends Object> userProps, boolean forced, boolean quiet,
			IProgressMonitor monitor) throws CoreException {
		GlobalContext globalCtx = new GlobalContext(userProps, forced, quiet);
		monitor.beginTask(null, 1000);
		try {
			globalCtx.setStatus(perform(attributes, globalCtx, MonitorUtils.subMonitor(monitor, 900)));
			return globalCtx;
		} finally {
			globalCtx.removeScheduled(MonitorUtils.subMonitor(monitor, 50));
			if (globalCtx.isWorkspaceRefreshPending())
				ResourcesPlugin.getWorkspace().getRoot().refreshLocal(IResource.DEPTH_INFINITE, MonitorUtils.subMonitor(monitor, 50));
			monitor.done();
		}
	}

	private void addAttributeChildren(GlobalContext ctx, Attribute attribute, Set<String> seen, List<ActionInvocation> ordered,
			Generator attrGenerator) throws CoreException {
		if (attribute instanceof ActionArtifact)
			attribute = ((ActionArtifact) attribute).getAction();

		String attrId = attribute.toString();
		if (!seen.add(attrId))
			return;

		CSpec cspec = attribute.getCSpec();
		for (Prerequisite preq : attribute.getPrerequisites()) {
			try {
				Attribute ag = preq.getReferencedAttribute(cspec, ctx);
				if (ag != null)
					addAttributeChildren(ctx, ag, seen, ordered, null);
			} catch (MissingComponentException e) {
				// The component might need to be generated in which case it's
				// generator must be built.
				//
				String cName;
				String cType;
				if (preq.getComponentName() == null) {
					cName = cspec.getName();
					cType = cspec.getComponentTypeID();
				} else {
					ComponentRequest rq = cspec.getDependency(preq.getComponentName(), preq.getComponentType(), preq.getVersionRange());
					cName = rq.getName();
					cType = rq.getComponentTypeID();
				}

				List<Generator> generators = WorkspaceInfo.getGenerators(new ComponentRequest(cName, cType, null));
				if (generators.size() == 0)
					//
					// There is no known generator for the component
					//
					throw e;

				// If several candidates are found, we prefer the cspec that
				// requested the
				// attribute if it is also a generator.
				//
				CSpec generatorCSpec = null;
				Generator actionGenerator = null;
				for (Generator generator : generators) {
					String component = generator.getComponent();
					CSpec candidate = component == null ? generator.getCSpec() : WorkspaceInfo.getResolution(
							new ComponentRequest(component, null, null), false).getCSpec();

					if (candidate.equals(cspec)) {
						actionGenerator = generator;
						generatorCSpec = candidate;
						break;
					}

					if (!(generatorCSpec == null || generatorCSpec.equals(candidate)))
						//
						// We find a generator for the desired component in more
						// then
						// one other component. This is an ambiguity that we
						// cannot resolve.
						//
						throw new AmbigousComponentException(generator.getGenerates());

					actionGenerator = generator;
					generatorCSpec = candidate;
				}

				// Add the attribute that represents the generated component
				//
				Attribute ag = generatorCSpec.getAttribute(actionGenerator.getAttribute());
				if (seen.contains(ag.toString())) {
					// Make sure it was added as a generator
					//
					int top = ordered.size();
					for (int idx = 0; idx < top; ++idx) {
						ActionInvocation ai = ordered.get(idx);
						if (ai.getAction() == ag && !(ai instanceof GeneratorInvocation)) {
							ordered.set(idx, new GeneratorInvocation(actionGenerator, ai.getAction()));
							ordered.add(new DeferredActionInvocation(cspec, preq));
							break;
						}
					}
				} else {
					addAttributeChildren(ctx, ag, seen, ordered, actionGenerator);
					ordered.add(new DeferredActionInvocation(cspec, preq));
				}
			}
		}

		if (attribute instanceof Action) {
			Action action = (Action) attribute;
			ordered.add(attrGenerator == null ? new DirectActionInvocation(action) : new GeneratorInvocation(attrGenerator, action));
		}
	}

	private List<ActionInvocation> getOrderedActionList(GlobalContext ctx, List<? extends IAttribute> attributes) throws CoreException {
		Set<String> seen = new HashSet<String>();
		List<ActionInvocation> ordered = new ArrayList<ActionInvocation>();
		for (IAttribute attribute : attributes)
			addAttributeChildren(ctx, (Attribute) attribute, seen, ordered, null);

		for (IAttribute attribute : attributes) {
			if (attribute instanceof IActionArtifact)
				attribute = ((ActionArtifact) attribute).getAction();

			String attrId = attribute.toString();
			if (!seen.contains(attrId)) {
				seen.add(attrId);
				if (attribute instanceof Action)
					ordered.add(new DirectActionInvocation((Action) attribute));
			}
		}
		return ordered;
	}

	private IStatus internalPerform(List<? extends IAttribute> attributes, IGlobalContext global, IProgressMonitor monitor) throws CoreException {
		// calculate a flat dependency list of actions to be done
		// adjust as needed when it's a build integration
		//
		GlobalContext globalCtx = (GlobalContext) global;
		List<ActionInvocation> actionList = getOrderedActionList(globalCtx, attributes);
		monitor.beginTask(null, 100 * actionList.size());
		Logger logger = CorePlugin.getLogger();
		if (logger.isDebugEnabled()) {
			StringBuilder bld = new StringBuilder(40 + actionList.size() * 40);
			bld.append(Messages.Actions_to_perform_in_order);
			for (ActionInvocation action : actionList) {
				bld.append("\n  "); //$NON-NLS-1$
				action.toString(bld);
			}
			logger.debug(bld.toString());
		}

		MultiStatus retStatus = new MultiStatus(CorePlugin.getID(), IStatus.OK, "", null); //$NON-NLS-1$
		for (ActionInvocation action : actionList) {
			// Check that the action hasn't been executed already. This may
			// happen when actions
			// explicitly call on other actions (such as the fragment actor)
			//
			IStatus status = action.execute(globalCtx, MonitorUtils.subMonitor(monitor, 100));
			switch (status.getSeverity()) {
				case IStatus.WARNING:
				case IStatus.INFO:
					retStatus.add(status);
					// fall through

				case IStatus.OK:
					continue;

				case IStatus.ERROR:
					retStatus.add(status);
					break;
			}
			break;
		}

		IStatus[] children = retStatus.getChildren();
		IStatus status = children.length == 1 ? children[0] : retStatus;

		if (status.getSeverity() == IStatus.ERROR)
			throw new CoreException(status);

		return status;
	}
}
