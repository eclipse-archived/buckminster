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
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.jface.operation.IRunnableWithProgress;

public class MaterializeAndBindRunnable implements IRunnableWithProgress
{
	private final MaterializationContext m_context;

	public MaterializeAndBindRunnable(MaterializationContext context)
	{
		m_context = context;
	}

	public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
	{
		try
		{
			MaterializationJob.runDelegated(m_context, monitor);
		}
		catch(OperationCanceledException e)
		{
			throw new InterruptedException();
		}
		catch(Throwable t)
		{
			throw new InvocationTargetException(t);
		}
	}
}
