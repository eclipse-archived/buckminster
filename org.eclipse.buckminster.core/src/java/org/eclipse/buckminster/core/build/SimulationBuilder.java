/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.build;

import java.io.PrintStream;
import java.util.Map;

import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

/**
 * @author kolwing
 *
 */
public class SimulationBuilder extends AbstractBuckminsterBuilder
{
	public static final String TICKS_KEY = "ticks";
	
	public static final int TICKS_MIN = 0;
	public static final int TICKS_MAX = 60000;
	public static final int TICKS_DEFAULT = 3000;

	@Override
	protected IProject[] doBuild(int kind, Map<String,String> args, IProgressMonitor monitor) throws CoreException
	{
		int ticks = -1;
		try
		{
			ticks = Integer.parseInt(args.get(TICKS_KEY).toString().trim());
		}
		catch (Exception e)
		{
			// ignore
		}
		if (ticks < TICKS_MIN || ticks > TICKS_MAX)
			ticks = TICKS_DEFAULT;
				
		if (monitor == null)
			monitor = new NullProgressMonitor();

		monitor.beginTask(null, ticks);
		monitor.subTask("Simulation");

		try
		{
			PrintStream ps = this.getOutStream();
			ps.print("Simulation (" + AbstractBuckminsterBuilder.kindToString(kind) + "): working for " + ticks + " ticks...");
			for (int i = 0 ; i < ticks ; i++)
			{
				Thread.sleep(1);
				MonitorUtils.worked(monitor, 1);
			}
			ps.println("finished working");
		}
		catch (Exception e)
		{
			throw new InternalError();
		}
		finally
		{
			monitor.done();
		}

		return null;
	}
}
