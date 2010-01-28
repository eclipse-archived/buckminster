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
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.ResolvedNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * An implementation of a IResolutionBuilder Creates a {@link org.eclipse.buckminster.core.metadata.model.BOMNode
 * DepNode} based on the dependency information that it can read using a
 * {@link org.eclipse.buckminster.core.reader.IComponentReader IRemoteReader}. The <code>CSPecPacking</code> is
 * delivered as a sequence of SAX events
 * 
 * @author Thomas Hallgren
 */
public interface IResolutionBuilder extends IBuckminsterExtension, Comparable<IResolutionBuilder>
{
	public static final String DEFAULT = "default"; //$NON-NLS-1$

	public static final String CSPEC2CSPEC = "cspec2cspec"; //$NON-NLS-1$

	public static final String PLUGIN2CSPEC = "plugin2cspec"; //$NON-NLS-1$

	public static final String FEATURE2CSPEC = "feature2cspec"; //$NON-NLS-1$

	public static final String CQUERY2BOM = "cquery2BOM"; //$NON-NLS-1$

	/**
	 * The builder obtains whatever input it has and creates a resolved node. The builder may close the reader when it
	 * is done with it. The reader is passed in a one element array. If the builder closes the reader, it must set the
	 * array element to <code>null</code>.
	 * 
	 * @param reader
	 *            The one element array containing the reader.
	 * @param forResolutionAidOnly
	 *            Set to <code>true</code> if the generated node will be used only to extract the component version
	 * @param monitor
	 *            Monitor for cancellation and progress reporting
	 * @return The created node
	 * @throws CoreException
	 */
	BOMNode build(IComponentReader[] reader, boolean forResolutionAidOnly, IProgressMonitor monitor)
			throws CoreException;

	/**
	 * Returns a resolved node.
	 * 
	 * @param reader
	 *            The <code>reader</code> to use when creating the result.
	 * @param cspecBuilder
	 *            The <code>CSPEC</code> for the resolution.
	 * @return The component information.
	 * @throws CoreException
	 */
	ResolvedNode createNode(IComponentReader reader, CSpecBuilder cspecBuilder) throws CoreException;

	/**
	 * Returns the component type of the cspec built by this builder.
	 */
	String getComponentTypeID();

	/**
	 * Returns the nature for which this builder will create a configuration spec or <code>null</code> if no nature is
	 * associated with this builder.
	 * 
	 * @return The name of the associated nature or <code>null</code>.
	 */
	String getNature();

	/**
	 * The weight determines what builder that has precedence in cases where more than one possible builder exists.
	 * 
	 * @return The weight of this builder.
	 */
	int getWeight();
}
