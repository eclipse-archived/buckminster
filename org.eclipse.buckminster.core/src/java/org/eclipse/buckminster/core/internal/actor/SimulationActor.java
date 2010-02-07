/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.internal.actor;

import java.io.PrintStream;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.osgi.util.NLS;

/**
 * @author kolwing
 * 
 */
public class SimulationActor extends AbstractActor {
	public static final String ID = "simulation"; //$NON-NLS-1$

	public static final String TICKS_PROPERTY = "ticks"; //$NON-NLS-1$

	public static final int TICKS_MIN = 0;

	public static final int TICKS_MAX = 60000;

	public static final int TICKS_DEFAULT = 3000;

	private int ticks = TICKS_DEFAULT;

	@Override
	protected void internalInit() throws CoreException {
		String sTicks = this.getActorProperty(TICKS_PROPERTY);
		if (sTicks != null) {
			int tcks = Integer.parseInt(sTicks);
			if (tcks < TICKS_MIN)
				tcks = TICKS_MIN;
			else if (tcks > TICKS_MAX)
				tcks = TICKS_MAX;
			ticks = tcks;
		}
	}

	@Override
	protected IStatus internalPerform(IActionContext ctx, IProgressMonitor monitor) throws CoreException {
		monitor = MonitorUtils.ensureNotNull(monitor);

		int tcks = ticks;

		try {
			monitor.beginTask(null, tcks);

			PrintStream ps = ctx.getOutputStream();
			ps.print(NLS.bind(Messages.Simulation_0_working_for_1_ticks, ctx.getAction().getName(), Integer.valueOf(tcks)));
			for (int i = 0; i < ticks; i++) {
				Thread.sleep(1);
				MonitorUtils.worked(monitor, 1);
			}
			ps.println(Messages.Finished_working);

			return Status.OK_STATUS;
		} catch (Exception e) {
			throw BuckminsterException.wrap(e);
		} finally {
			monitor.done();
		}
	}
}
