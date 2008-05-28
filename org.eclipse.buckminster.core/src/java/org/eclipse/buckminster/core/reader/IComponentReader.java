/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.reader;

import java.io.Closeable;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.resolver.IResolverBackchannel;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.version.IVersionConverter;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * A Component reader knows how to read a component stored at some arbitrary location. An instance
 * of a component reader is always associated with one specific component.
 * @see IReaderType
 * @author thhal
 */
public interface IComponentReader extends IResolverBackchannel, Closeable
{
	/**
	 * Returns <code>true</code> if this reader is capable of materializing components.
	 */
	boolean canMaterialize();

	/**
	 * Returns the component type
	 */
	IComponentType getComponentType();

	/**
	 * Returns the current node query.
	 */
	NodeQuery getNodeQuery();

	/**
	 * Returns the provider match
	 */
	ProviderMatch getProviderMatch();

	/**
	 * Returns the type that this reader belongs to.
	 */
	IReaderType getReaderType();

	/**
	 * Returns the version converter that converts plain versions into fully fledged version
	 * selectors.
	 * @return A version converter.
	 * @throws CoreException
	 */
	IVersionConverter getVersionConverter() throws CoreException;

	/**
	 * Materialize (download and unpack) the the file appointed by this reader into the
	 * specified <code>location</code>. The implementation must ensure that the
	 * materialization is atomic.
	 * @param location
	 * @param resolution
	 * @param ctx
	 * @param monitor The progress monitor.
	 * @throws CoreException
	 */
	void materialize(IPath location, Resolution resolution, MaterializationContext ctx, IProgressMonitor monitor) throws CoreException;
}
