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
import java.util.Map;
import java.util.UUID;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.actor.IGlobalContext;
import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.cspec.IAction;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.FileUtils.DeleteException;
import org.eclipse.buckminster.core.metadata.ModelCache;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

public class GlobalContext extends ModelCache implements IGlobalContext
{
	private final ArrayList<Integer> m_executedEclipseKinds = new ArrayList<Integer>();
	private final ArrayList<IPath> m_scheduledRemovals = new ArrayList<IPath>();
	private final Map<UUID,Object> m_invocationCache = new HashMap<UUID,Object>();
	private final HashSet<Action> m_actionsPerformed = new HashSet<Action>();
	private final Map<String,String> m_globalProps;
	private final Map<String,String> m_userProps;
	private final boolean m_forcedExecution;

	private boolean m_workspaceRefreshPending;
	private IStatus m_status;

	public GlobalContext(Map<String,String> userProps, boolean forcedExecution)
	{
		m_globalProps = RMContext.getGlobalPropertyAdditions();
		m_userProps = userProps == null ? Collections.<String,String>emptyMap() : userProps;
		m_forcedExecution = forcedExecution;
	}

	public Map<UUID,Object> getInvocationCache()
	{
		return m_invocationCache ;
	}

	public Map<String,String> getExecutionProperties(Attribute attribute) throws CoreException
	{
		Map<String,String> actionProps = (attribute instanceof IAction) ? ((IAction)attribute).getProperties() : Collections.<String,String>emptyMap();
		int mapSize = m_globalProps.size() + actionProps.size() + 10;
		ExpandingProperties allProps = new ExpandingProperties(mapSize);
		allProps.putAll(m_globalProps, true);
		allProps.putAll(actionProps, true);
		allProps.putAll(m_userProps);
		attribute.addDynamicProperties(allProps);
		return allProps;
	}

	public IStatus getStatus()
	{
		return m_status;
	}

	public void setStatus(IStatus status)
	{
		m_status = status;
	}

	public void scheduleRemoval(IPath path)
	{
		if(!path.isAbsolute())
			throw new IllegalArgumentException("Only absolute paths can be scheduled for removal");

		int idx = m_scheduledRemovals.size();
		while(--idx >= 0)
		{
			IPath alreadyScheduled = m_scheduledRemovals.get(idx);
			if(alreadyScheduled.isPrefixOf(path))
				return;
			if(path.isPrefixOf(alreadyScheduled))
				m_scheduledRemovals.remove(idx);
		}
		m_scheduledRemovals.add(path);
	}

	/**
	 * Add an <code>action</code> to the set of performed actions.
	 * @param action The action to add.
	 */
	void addPerformedAction(Action action)
	{
		m_actionsPerformed.add(action);
	}

	Map<String,String> getUserProperties()
	{
		return m_userProps;
	}

	boolean hasExecutedKind(int kind)
	{
		return m_executedEclipseKinds.contains(new Integer(kind));
	}

	/**
	 * Checks if the <code>action</code> is among the actions that were added using
	 * the {@link #addPerformedAction(Action)} method.
	 * @param action The action to check
	 * @return <code>true</code> if the action has been added.
	 */
	boolean hasPerformedAction(IAction action)
	{
		return m_actionsPerformed.contains(action);
	}

	boolean isForcedExecution()
	{
		return m_forcedExecution;
	}

	boolean isWorkspaceRefreshPending()
	{
		return m_workspaceRefreshPending;
	}

	void kindWasExecuted(int kind)
	{
		Integer objKind = new Integer(kind);
		if(!m_executedEclipseKinds.contains(objKind))
			m_executedEclipseKinds.add(objKind);
	}

	synchronized void removeScheduled(IProgressMonitor monitor)
	{
		int idx = m_scheduledRemovals.size();
		monitor.beginTask(null, idx * 10);
		try
		{
			while(--idx >= 0)
			{
				try
				{
					FileUtils.deleteRecursive(m_scheduledRemovals.get(idx).toFile(), MonitorUtils.subMonitor(monitor, 10));
				}
				catch(DeleteException e)
				{
					CorePlugin.getLogger().warning(e.getMessage());
				}
			}
		}
		finally
		{
			monitor.done();
		}
		m_scheduledRemovals.clear();
	}

	void setWorkspaceRefreshPending(boolean flag)
	{
		m_workspaceRefreshPending = flag;
	}
}
