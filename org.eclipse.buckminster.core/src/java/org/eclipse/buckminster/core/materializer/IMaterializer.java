/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.materializer;

import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The IMaterializer deals with the task of materializing the components of a
 * {@link org.eclipse.buckminster.core.metadata.model.BillOfMaterials BillOfMaterials}.
 * 
 * @see org.eclipse.buckminster.core.CorePlugin#getMaterializationService(String id)
 * @author Thomas Hallgren
 */
public interface IMaterializer
{
	static final String MATERIALIZERS_POINT = CorePlugin.CORE_NAMESPACE + ".materializers"; //$NON-NLS-1$

	static final String FILE_SYSTEM = "filesystem"; //$NON-NLS-1$

	static final String WORKSPACE = "workspace"; //$NON-NLS-1$

	static final String P2 = "p2"; //$NON-NLS-1$

	static final String MATERIALIZER_PROPERTY = "buckminster.materializer.name"; //$NON-NLS-1$

	/**
	 * Returns true if this materializer can work in parallel with other materializers of the same type.
	 * 
	 * @return true if materializers of this type can be parallelized.
	 */
	boolean canWorkInParallel();

	/**
	 * Returns the default root for the installation.
	 * 
	 * @param context
	 *            The context in which the materialization takes place
	 * @param resolution
	 *            The resolution
	 * @return The default root. It can never be <code>null</code>
	 */
	IPath getDefaultInstallRoot(MaterializationContext context, Resolution resolution) throws CoreException;

	/**
	 * Returns the reader type to use for the materialization. This might differ from the type used for the resolution.
	 * 
	 * @return The reader type to use for materialization
	 */
	IReaderType getMaterializationReaderType(Resolution resolution) throws CoreException;

	/**
	 * <p>
	 * Perform install actions on the given node such as executing generators, binding projects to the Eclipse workspace
	 * etc. The actions will be performed by on a leaf first basis.
	 * </p>
	 * <p>
	 * While the called instance is guaranteed to be the one designated to manage the <code>node</code>, children of the
	 * <code>node</code> might be managed by other instances. This is controlled by the mspec.
	 * 
	 * @param node
	 *            The bill of material node. This is the root of the install.
	 * @param context
	 *            The context for the materialization.
	 * @param generated
	 *            Keeps track of nodes that has been generated to avoid multiple generations.
	 * @param perused
	 *            Keeps track of what has been installed to avoid multiple calls to the same node.
	 * @param monitor
	 *            provides feedback to the user.
	 * @throws CoreException
	 */
	void installRecursive(BOMNode node, MaterializationContext context, Set<String> generated, Set<Resolution> perused,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * Materialize all resolutions from the bill of materials <code>bom</code>.
	 * 
	 * @param resolutions
	 *            The list of things to materialize.
	 * @param context
	 *            The context for the materialization.
	 * @param monitor
	 *            provides feedback to the user.
	 * @return The list of materializations
	 * @throws CoreException
	 */
	List<Materialization> materialize(List<Resolution> resolutions, MaterializationContext context,
			IProgressMonitor monitor) throws CoreException;

	/**
	 * Install the given resolution.
	 * 
	 * @param resolution
	 *            The resolution to install
	 * @param context
	 *            The context for the materialization.
	 * @param monitor
	 *            provides feedback to the user.
	 * @throws CoreException
	 */
	void performInstallAction(Resolution resolution, MaterializationContext context, IProgressMonitor monitor)
			throws CoreException;
}
