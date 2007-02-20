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

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * The IMaterializer deals with the task of materializing the components of a
 * {@link BillOfMaterials}.
 * @see org.eclipse.buckminster.core.CorePlugin#getMaterializationService(String id)
 * @author Thomas Hallgren
 */
public interface IMaterializer
{
	static final String FILE_SYSTEM = "filesystem";
	static final String WORKSPACE = "workspace";
	static final String SITE_MIRROR = "site.mirror";
	static final Object MATERIALIZER_PROPERTY = "buckminster.materializer.name";

	/**
	 * Materialize all resolutions from the bill of materials <code>bom</code> except the ones listed
	 * in <code>excludes</code>.
	 * @param bom The bill of material needed to resolve component dependencies The order containing
	 *            info about what it is that needs to be materialized.
	 * @param excludes Resolutions that should be excluded from the materialization
	 * @param context The context for the materialization.
	 * @param monitor provides feedback to the user.
	 * @return The list of materializations
	 * @throws CoreException
	 */
	List<Materialization> materialize(BillOfMaterials bom, Set<Resolution> excludes, RMContext context,
		IProgressMonitor monitor) throws CoreException;

	/**
	 * Install the given resolution. This method is normally called as part of {@link
	 * #performInstallActions(BillOfMaterials, Set, RMContext, IProgressMonitor) but it is represented
	 * here for the benefit of generator that migth need to call it explicitly for one single
	 * component.
	 * @param resolution The resolution to install
	 * @param context The context for the install
	 * @param monitor provides feedback to the user.
	 * @throws CoreException
	 */
	public void performInstallAction(Resolution resolution, RMContext context, IProgressMonitor monitor)
	throws CoreException;

	/**
	 * Perform install actions on the given bom such as executing generators, binding projects
	 * to the Eclipse workspace etc.
	 * @param bom The bill of material needed to resolve component dependencies The order containing
	 *            info about what it is that needs to be materialized.
	 * @param excludes Resolutions that should be excluded from the install
	 * @param context The context for the install.
	 * @param monitor provides feedback to the user.
	 * @throws CoreException
	 */
	void performInstallActions(BillOfMaterials bom, Set<Resolution> excludes, RMContext context,
			IProgressMonitor monitor) throws CoreException;
}
