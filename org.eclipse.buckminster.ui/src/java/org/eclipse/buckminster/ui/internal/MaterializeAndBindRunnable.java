/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.internal;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.JobBlocker;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializerJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public class MaterializeAndBindRunnable extends WorkspaceModifyOperation
{
	private final MaterializationContext m_context;
	
	public MaterializeAndBindRunnable(MaterializationContext context)
	{
		m_context = context;
	}

	public static void run(IRunnableContext runner, MaterializationContext context) throws CoreException
	{
		MaterializeAndBindRunnable runnable = new MaterializeAndBindRunnable(context);
		JobBlocker blocker = MaterializerJob.blockJobs();
		try
		{
			runner.run(true, true, runnable);
			
			// We wait to give the event manager a chance to deliver all
			// events while the JobBlocker still active. This gives us
			// a chance to add dynamic dependencies to projects
			//
			Thread.sleep(3000);
		}
		catch(InvocationTargetException e)
		{
			throw BuckminsterException.wrap(e);
		}
		catch(InterruptedException e)
		{
			throw new OperationCanceledException();
		}
		finally
		{
			blocker.release();
		}
	}

	@Override
	protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException
	{
		try
		{
			MaterializerJob.runDelegated(m_context, monitor);
		}
		catch(OperationCanceledException e)
		{
			throw new InterruptedException();
		}
		catch(CoreException e)
		{
			throw e;
		}
		catch(Throwable t)
		{
			throw new InvocationTargetException(t);
		}
	}
}
