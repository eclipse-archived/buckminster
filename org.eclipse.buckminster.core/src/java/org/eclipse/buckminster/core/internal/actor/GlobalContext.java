/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.actor.IGlobalContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.FileUtils.DeleteException;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.osgi.util.NLS;

public class GlobalContext extends ModelCache implements IGlobalContext {
	private final ArrayList<Integer> executedEclipseKinds = new ArrayList<Integer>();

	private final ArrayList<IPath> scheduledRemovals = new ArrayList<IPath>();

	private final Map<UUID, Object> invocationCache = new HashMap<UUID, Object>();

	private final HashSet<Action> actionsPerformed = new HashSet<Action>();

	private final Map<String, ? extends Object> globalProps;

	private final boolean forcedExecution;

	private final boolean quietExecution;

	private boolean workspaceRefreshPending;

	private IStatus status;

	private List<Resolution> generatedResolutions;

	private List<Materialization> generatedMaterializations;

	public GlobalContext(Map<String, ? extends Object> userProps, boolean forcedExecution, boolean quietExecution) {
		super(userProps);
		this.globalProps = RMContext.getGlobalPropertyAdditions();
		this.forcedExecution = forcedExecution;
		this.quietExecution = quietExecution;
	}

	public synchronized void addGeneratedResolution(Resolution resolution, IPath location) {
		if (generatedResolutions == null) {
			generatedResolutions = new ArrayList<Resolution>();
			generatedMaterializations = new ArrayList<Materialization>();
		}
		generatedResolutions.add(resolution);
		generatedMaterializations.add(new Materialization(location, resolution.getComponentIdentifier()));
	}

	public Map<String, ? extends Object> getExecutionProperties(Attribute attribute) throws CoreException {
		Map<String, String> actionProps = (attribute instanceof IAction) ? ((IAction) attribute).getProperties() : Collections
				.<String, String> emptyMap();
		int mapSize = globalProps.size() + actionProps.size() + 10;
		ExpandingProperties<Object> allProps = new ExpandingProperties<Object>(mapSize);
		allProps.putAll(globalProps, true);
		allProps.putAll(actionProps, true);
		allProps.putAll(super.getProperties());
		attribute.addDynamicProperties(allProps);
		return allProps;
	}

	public synchronized Materialization getGeneratedMaterialization(IComponentIdentifier ci) {
		if (generatedMaterializations != null) {
			for (Materialization mat : generatedMaterializations)
				if (ci.equals(mat.getComponentIdentifier()))
					return mat;
		}
		return null;
	}

	public synchronized Resolution getGeneratedResolution(IComponentRequest request) {
		if (generatedResolutions != null) {
			for (Resolution res : generatedResolutions)
				if (request.designates(res.getComponentIdentifier()))
					return res;
		}
		return null;
	}

	public Map<UUID, Object> getInvocationCache() {
		return invocationCache;
	}

	/**
	 * Adding global properties to user properties<br>
	 * <a href=https://bugs.eclipse.org/bugs/show_bug.cgi?id=252146>Bug
	 * 252146</a>
	 * 
	 * @author Guillaume CHATELET
	 */
	@Override
	public synchronized Map<String, ? extends Object> getProperties() {
		Map<String, ? extends Object> userProperties = super.getProperties();
		ExpandingProperties<Object> allProps = new ExpandingProperties<Object>(userProperties.size() + globalProps.size());
		allProps.putAll(globalProps, true);
		allProps.putAll(userProperties);
		return allProps;
	}

	public IStatus getStatus() {
		return status;
	}

	public void scheduleRemoval(IPath path) {
		if (!path.isAbsolute())
			throw new IllegalArgumentException(NLS.bind(Messages.Only_absolute_paths_can_be_scheduled_for_removal_0, path.toOSString()));

		int idx = scheduledRemovals.size();
		while (--idx >= 0) {
			IPath alreadyScheduled = scheduledRemovals.get(idx);
			if (alreadyScheduled.isPrefixOf(path))
				return;
			if (path.isPrefixOf(alreadyScheduled))
				scheduledRemovals.remove(idx);
		}
		scheduledRemovals.add(path);
	}

	public void setStatus(IStatus status) {
		this.status = status;
	}

	/**
	 * Add an <code>action</code> to the set of performed actions.
	 * 
	 * @param action
	 *            The action to add.
	 */
	void addPerformedAction(Action action) {
		actionsPerformed.add(action);
	}

	boolean hasExecutedKind(int kind) {
		return executedEclipseKinds.contains(new Integer(kind));
	}

	/**
	 * Checks if the <code>action</code> is among the actions that were added
	 * using the {@link #addPerformedAction(Action)} method.
	 * 
	 * @param action
	 *            The action to check
	 * @return <code>true</code> if the action has been added.
	 */
	boolean hasPerformedAction(IAction action) {
		return actionsPerformed.contains(action);
	}

	boolean isForcedExecution() {
		return forcedExecution;
	}

	boolean isQuietExecution() {
		return quietExecution;
	}

	boolean isWorkspaceRefreshPending() {
		return workspaceRefreshPending;
	}

	void kindWasExecuted(int kind) {
		Integer objKind = new Integer(kind);
		if (!executedEclipseKinds.contains(objKind))
			executedEclipseKinds.add(objKind);
	}

	synchronized void removeScheduled(IProgressMonitor monitor) {
		int idx = scheduledRemovals.size();
		monitor.beginTask(null, idx * 10);
		try {
			while (--idx >= 0) {
				try {
					FileUtils.deleteRecursive(scheduledRemovals.get(idx).toFile(), MonitorUtils.subMonitor(monitor, 10));
				} catch (DeleteException e) {
					CorePlugin.getLogger().warning(e.getMessage());
				}
			}
		} finally {
			monitor.done();
		}
		scheduledRemovals.clear();
	}

	void setWorkspaceRefreshPending(boolean flag) {
		workspaceRefreshPending = flag;
	}
}
