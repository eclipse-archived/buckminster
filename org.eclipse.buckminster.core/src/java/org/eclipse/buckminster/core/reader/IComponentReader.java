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
import java.io.File;

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
import org.eclipse.ecf.core.security.IConnectContext;

/**
 * A Component reader knows how to read a component stored at some arbitrary
 * location. An instance of a component reader is always associated with one
 * specific component.
 * 
 * @see IReaderType
 * @author thhal
 */
public interface IComponentReader extends IResolverBackchannel, Closeable {
	/**
	 * Returns <code>true</code> if this reader is capable of materializing
	 * components.
	 */
	boolean canMaterialize();

	/**
	 * Returns the component type
	 */
	IComponentType getComponentType();

	/**
	 * Returns the security context used for connect (if any).
	 */
	IConnectContext getConnectContext();

	/**
	 * Returns the location of the artifact (directory or file) that this reader
	 * can read, or <code>null</code> if that location cannot be represented as
	 * a {@link java.io.File file}.
	 * 
	 * @see {@link #isFileSystemReader()}
	 */
	File getLocation() throws CoreException;

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
	 * Returns the version converter that converts plain versions into fully
	 * fledged version selectors.
	 * 
	 * @return A version converter.
	 * @throws CoreException
	 */
	IVersionConverter getVersionConverter() throws CoreException;

	/**
	 * Returns true if this reader reads its file directly form the file system.
	 * This is typically true for the {@link LocalReader} and readers that clone
	 * the repository before accessing it (such as the git reader).
	 * 
	 * @return <code>true</code> to indicate that files are read from the local
	 *         filesystem
	 */
	boolean isFileSystemReader();

	/**
	 * Materialize (download and unpack) the the file appointed by this reader
	 * into the specified <code>location</code>. The implementation must ensure
	 * that the materialization is atomic.
	 * 
	 * @param location
	 * @param resolution
	 * @param ctx
	 * @param monitor
	 *            The progress monitor.
	 * @throws CoreException
	 */
	void materialize(IPath location, Resolution resolution, MaterializationContext ctx, IProgressMonitor monitor) throws CoreException;
}
