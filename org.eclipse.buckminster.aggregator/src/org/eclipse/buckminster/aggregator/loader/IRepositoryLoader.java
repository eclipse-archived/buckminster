/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.loader;

import java.net.URI;

import org.eclipse.buckminster.aggregator.p2.impl.MetadataRepositoryImpl;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.metadata.repository.IMetadataRepository;

/**
 * @author Filip Hrbek
 * 
 */
public interface IRepositoryLoader
{
	final String EXTENSION_POINT_ID = "org.eclipse.buckminster.aggregator.loader";

	final String EXTENSION_POINT_ATTRIBUTE_NATURE = "nature";

	final String ORIGINAL_PATH_PROPERTY = "original.path";

	final String ORIGINAL_ID_PROPERTY = "original.id";

	void close() throws CoreException;

	IArtifactRepository getArtifactRepository(IMetadataRepository mdr, IProgressMonitor monitor) throws CoreException;

	void load(IProgressMonitor monitor) throws CoreException;

	void open(URI location, MetadataRepositoryImpl mdr) throws CoreException;

	void reload(IProgressMonitor monitor) throws CoreException;
}
