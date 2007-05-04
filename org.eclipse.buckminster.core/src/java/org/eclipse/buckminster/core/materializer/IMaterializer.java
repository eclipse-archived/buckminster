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
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The IMaterializer deals with the task of materializing the components of a
 * {@link BillOfMaterials}.
 * @see org.eclipse.buckminster.core.CorePlugin#getMaterializationService(String id)
 * @author Thomas Hallgren
 */
public interface IMaterializer
{
	static final String MATERIALIZERS_POINT = CorePlugin.CORE_NAMESPACE + ".materializers";
	static final String FILE_SYSTEM = "filesystem";
	static final String WORKSPACE = "workspace";
	static final String SITE_MIRROR = "site.mirror";
	static final Object MATERIALIZER_PROPERTY = "buckminster.materializer.name";

	/**
	 * Returns the default root for the installation.
	 *
	 * @return
	 */
	public IPath getDefaultInstallRoot(MaterializationContext context) throws CoreException;

	/**
	 * Materialize all resolutions from the bill of materials <code>bom</code> except the ones listed
	 * in <code>excludes</code>.
	 * @param resolutions The list of things to materialize.
	 * @param context The context for the materialization.
	 * @param monitor provides feedback to the user.
	 * @return The list of materializations
	 * @throws CoreException
	 */
	List<Materialization> materialize(List<Resolution> resolutions, MaterializationContext context, IProgressMonitor monitor) throws CoreException;

	/**
	 * Install the given resolution. This method is normally called as part of {@link
	 * #performInstallActions(BillOfMaterials, Set, RMContext, IProgressMonitor) but it is represented
	 * here for the benefit of generator that migth need to call it explicitly for one single
	 * component.
	 * @param resolution The resolution to install
	 * @param context The context for the materialization.
	 * @param monitor provides feedback to the user.
	 * @throws CoreException
	 */
	public void performInstallAction(Resolution resolution, MaterializationContext context, IProgressMonitor monitor)
	throws CoreException;

	/**
	 * <p>Perform install actions on the given node such as executing generators, binding projects
	 * to the Eclipse workspace etc. The actions will be performed by on a leaf first basis.</p>
	 * <p>While the called instance is guaranteed to be the one designated to manage the <code>node</code>,
	 * children of the <code>node</code> might be managed by other instances. This
	 * is controlled by the mspec.
	 * @param node The bill of material node. This is the root of the install.
	 * @param context The context for the materialization.
	 * @param generated Keeps track of nodes that has been generated to avoid multiple generations.
	 * @param perused Keeps track of what has been installed to avoid multiple calls to the same node.
	 * @param monitor provides feedback to the user.
	 * @throws CoreException
	 */
	public void installRecursive(DepNode node, MaterializationContext context,
			Set<String> generated, Set<Resolution> perused, IProgressMonitor monitor) throws CoreException;
}
