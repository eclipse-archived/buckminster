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

import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializerJob;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

public class MaterializeAndBindRunnable extends WorkspaceModifyOperation
{
	private final MaterializationContext m_context;
	
	public MaterializeAndBindRunnable(MaterializationContext context)
	{
		m_context = context;
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
