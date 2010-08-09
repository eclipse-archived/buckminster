/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.resolver;

import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.model.common.ComponentRequest;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public interface IResolver extends IResolverBackchannel {
	/**
	 * Returns the context associated with this resolver
	 */
	ResolutionContext getContext();

	/**
	 * Returns <code>true</code> if this resolver will perform a recursive
	 * resolve.
	 */
	boolean isRecursiveResolve();

	/**
	 * Resolve the component denoted by <code>request</code>.
	 * 
	 * @param request
	 *            The request that denotes the desired top component of the
	 *            resulting <code>BillOfMaterials</code>.
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return The resulting bill of materials. Might be partly resolved.
	 * @throws CoreException
	 */
	BillOfMaterials resolve(ComponentRequest request, IProgressMonitor monitor) throws CoreException;

	/**
	 * This method performs the same task as
	 * 
	 * <pre>
	 * resolve(context, context.getComponentQuery().getRootRequest(), monitor);
	 * </pre>
	 * 
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return The resulting bill of materials. Might be partly resolved.
	 * @throws CoreException
	 */
	BillOfMaterials resolve(IProgressMonitor monitor) throws CoreException;

	/**
	 * Attemt to resolve unresolved nodes of the <code>bom</code>. The
	 * resolution process will use the
	 * {@link org.eclipse.buckminster.core.query.model.ComponentQuery
	 * ComponentQuery} passed in the <code>context</code> , not the one stored
	 * in the <code>bom</code>.
	 * 
	 * @param monitor
	 *            The monitor used for progress reporting
	 * @return The resulting bill of materials. Might still be partly resolved.
	 * @throws CoreException
	 */
	BillOfMaterials resolveRemaining(BillOfMaterials bom, IProgressMonitor monitor) throws CoreException;

	/**
	 * Tell the resolver to perform a one step resolve only or to traverse and
	 * attempt to resolve each dependency until the complete graph is resolved.
	 * 
	 * @param flag
	 *            <code>true</code> if a full resolve is desired
	 */
	void setRecursiveResolve(boolean flag);
}
