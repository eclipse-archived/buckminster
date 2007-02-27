/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.ctype;

import org.eclipse.buckminster.core.IBuckminsterExtension;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A IComponentType knows how to find dependency information in the components that it represents,
 * often in combination with a {@link IResolutionBuilder}. A good example is the
 * <code>eclipse-project</code> type that will choose a <code>CSPecBuilder</code> depending on
 * the natures it finds in the .project file.
 * @see org.eclipse.buckminster.core.CorePlugin#getComponentType(String)
 * @author Thomas Hallgren
 */
public interface IComponentType extends IBuckminsterExtension
{
	// Some well known component types included in the core distro
	//
	public static final String ECLIPSE_INSTALLED = "eclipse.installed";

	public static final String ECLIPSE_PROJECT = "eclipse.project";

	public static final String BUCKMINSTER = "buckminster";

	public static final String CVS = "cvs";

	public static final String JAR = "jar";

	public static final String UNKNOWN = "unknown";

	public static final String ECLIPSE_SITE_FEATURE = "site.feature";

	/**
	 * Returns true if components of this type has a ProjectDescription (i.e. a .project file).
	 * @return true if components has a ProjectDescription.
	 * @throws BuckminsterException
	 */
	boolean hasProjectDescription() throws BuckminsterException;

	/**
	 * Creates a {@link DepNode} based on the dependency information in
	 * <code>providerMatch</code>. A provider will normally create a node where only the
	 * top element is resolved but a provider can also find a previously resolved
	 * {@link org.eclipse.buckminster.core.metadata.model.BillOfMaterials BillOfMaterials}. When it
	 * does that instance will be returned.
	 * @param providerMatch The information needed to find the source.
	 * @return The resolved node. This entry is never <code>null</code>.
	 * @throws CoreException If the node could not be resolved.
	 */
	DepNode getResolution(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException;

	/**
	 * Returns the builder used when building the Resolution
	 */
	IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor)
	throws CoreException;
}
