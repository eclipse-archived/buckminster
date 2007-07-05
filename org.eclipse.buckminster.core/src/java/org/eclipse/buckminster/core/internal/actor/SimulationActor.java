/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import java.io.PrintStream;

import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
 * @author kolwing
 * 
 */
public class SimulationActor extends AbstractActor
{
	public static final String ID = "simulation";

	public static final String TICKS_PROPERTY = "ticks";

	public static final int TICKS_MIN = 0;

	public static final int TICKS_MAX = 60000;

	public static final int TICKS_DEFAULT = 3000;

	private int m_ticks = TICKS_DEFAULT;

	@Override
	protected void internalInit() throws CoreException
	{
		String sTicks = this.getActorProperty(TICKS_PROPERTY);
		if(sTicks != null)
		{
			int ticks = Integer.parseInt(sTicks);
			if(ticks < TICKS_MIN)
				ticks = TICKS_MIN;
			else if(ticks > TICKS_MAX)
				ticks = TICKS_MAX;
			m_ticks = ticks;
		}
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException
	{
		monitor = MonitorUtils.ensureNotNull(monitor);

		int ticks = m_ticks;

		try
		{
			monitor.beginTask(null, ticks);

			PrintStream ps = ctx.getOutputStream();
			ps.print("Simulation (" + ctx.getAction().getName() + "): working for " + ticks + " ticks...");
			for(int i = 0; i < ticks; i++)
			{
				Thread.sleep(1);
				MonitorUtils.worked(monitor, 1);
			}
			ps.println("finished working");

			return Status.OK_STATUS;
		}
		catch(Exception e)
		{
			throw BuckminsterException.wrap(e);
		}
		finally
		{
			monitor.done();
		}
	}
}