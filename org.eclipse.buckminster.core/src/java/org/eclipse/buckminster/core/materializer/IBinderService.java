/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.List;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.metadata.model.WorkspaceBinding;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @see org.eclipse.buckminster.core.CorePlugin#getBinderService()
 * @author Thomas Hallgren
 */
public interface IBinderService
{
	/**
	 * Bind the components listed in <code>bindings</code> to the workspace.
	 * @param bindings
	 * @param bom
	 * @param context The context for the binding.
	 * @param monitor The progress monitor
	 * @throws CoreException 
	 */
	void bind(List<WorkspaceBinding> bindings, RMContext context, IProgressMonitor monitor)
	throws InterruptedException, CoreException;
}

