/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import java.util.Map;
import java.util.Stack;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.internal.actor.ActorFactory;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;

/**
 * @author kolwing
 * 
 */
public abstract class AbstractActor implements IActor, IExecutableExtension
{
	private static InheritableThreadLocal<Stack<IActionContext>> s_actionContext = new InheritableThreadLocal<Stack<IActionContext>>();

	private String m_name;

	private String m_id;

	private final Logger m_logger;

	private Action m_action;

	public AbstractActor()
	{
		m_logger = CorePlugin.getLogger();
	}

	public static IActionContext getActiveContext()
	{
		Stack<IActionContext> ctxStack = s_actionContext.get();
		if(ctxStack == null || ctxStack.isEmpty())
			throw new IllegalStateException("No active IActionContext");
		return ctxStack.peek();
	}

	public static boolean getBooleanProperty(Map<String,String> properties, String key, boolean dflt)
	{
		String propVal = properties.get(key);
		if(propVal == null)
			return dflt;
		if("true".equalsIgnoreCase(propVal))
			return true;
		if("false".equalsIgnoreCase(propVal))
			return false;
		throw new IllegalArgumentException('\'' + propVal + "' is not a valid value of a boolean property");
	}

	public final String getId()
	{
		return m_id;
	}

	public final String getName()
	{
		return m_name;
	}

	public final void init(Action action) throws CoreException
	{
		m_action = action;
		if(m_logger.isDebugEnabled())
		{
			StringBuilder bld = new StringBuilder();
			bld.append("init actor: ");
			bld.append(this.getId());
			bld.append('[');
			action.toString(bld);
			bld.append(']');
			loggableProps(bld, action.getActorProperties());
			m_logger.debug(bld.toString());
		}
		this.internalInit();
	}

	public final synchronized IStatus perform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		// the stored context is per perform only; if referenced otherwise we ensure that
		// null is received, triggering a NPE
		//
		Map<String,String> props = ctx.getProperties();
		Stack<IActionContext> ctxStack = s_actionContext.get();
		if(ctxStack == null)
		{
			ctxStack = new Stack<IActionContext>();
			s_actionContext.set(ctxStack);
		}
		ctxStack.push(ctx);
		try
		{
			Action action = ctx.getAction();
			StringBuilder bld = new StringBuilder();
			bld.append("[start ");
			action.toString(bld);
			bld.append(']');
			if(m_logger.isDebugEnabled())
			{
				loggableActionInfo(bld);
				loggableProps(bld, props);
			}
			m_logger.info(bld.toString());
			ctx.scheduleRemoval(new Path(props.get(KeyConstants.ACTION_TEMP)));
			IStatus status = internalPerform(ctx, monitor);
			bld.setLength(0);
			bld.append("[end ");
			action.toString(bld);
			bld.append(']');
			m_logger.info(bld.toString());
			return status;
		}
		catch(Throwable t)
		{
			throw BuckminsterException.wrap(t);
		}
		finally
		{
			ctxStack.pop();
			if(ctxStack.isEmpty())
				s_actionContext.set(null);
		}
	}

	public final void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException
	{
		m_name = config.getAttribute(ActorFactory.ACTOR_NAME_ATTR);
		m_id = config.getAttribute(ActorFactory.ACTOR_ID_ATTR);
	}

	protected final Logger getLogger()
	{
		return m_logger;
	}

	protected final String getActorProperty(String key)
	{
		return m_action.getActorProperties().get(key);
	}

	protected void internalInit() throws CoreException
	{
		// noop
	}

	abstract protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException;

	private void loggableProps(StringBuilder sb, Map<String, String> props)
	{
		for(Map.Entry<String, String> entry : props.entrySet())
		{
			sb.append("\n  ");
			sb.append(entry.getKey()).append('=').append(entry.getValue());
		}
	}

	private void loggableActionInfo(StringBuilder sb)
	{
		if(m_action.getPrerequisitesAlias() != null)
		{
			sb.append("\n  Prerequisite alias = ");
			sb.append(m_action.getPrerequisitesAlias());
		}
		if(m_action.getPrerequisiteRebase() != null)
		{
			sb.append("\n  Prerequisite rebase = ");
			sb.append(m_action.getPrerequisiteRebase().toOSString());
		}
		if(m_action.getProductAlias() != null)
		{
			sb.append("\n  Product alias = ");
			sb.append(m_action.getProductAlias());
		}
		if(m_action.getProductBase() != null)
		{
			sb.append("\n  Product base = ");
			sb.append(m_action.getProductBase().toOSString());
		}
	}
}
