/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.remote.resolver;

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.remote.IServiceConnection;
import org.eclipse.core.runtime.CoreException;

/**
 * Provides remote resolution service operations
 * 
 * @author Karel Brezina
 * 
 */
public interface IResolutionServiceConnection extends IServiceConnection
{
	/**
	 * Fires query resolution - it should start an asynchronous method on the server side
	 * 
	 * @param bom
	 *            BillOfMaterials
	 * @throws CoreException
	 */
	public void fireQueryResolution(BillOfMaterials bom) throws CoreException;

	/**
	 * Gets resolution result
	 * 
	 * @return resolved BillOfMaterials
	 * @throws CoreException
	 */
	public BillOfMaterials getResolutionResult() throws CoreException;
}
