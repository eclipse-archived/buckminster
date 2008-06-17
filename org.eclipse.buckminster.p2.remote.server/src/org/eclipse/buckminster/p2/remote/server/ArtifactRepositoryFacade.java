/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepository;
import org.eclipse.equinox.internal.p2.artifact.repository.simple.SimpleArtifactRepositoryIO;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.osgi.framework.Filter;

/**
 * @author Thomas Hallgren
 */
public class ArtifactRepositoryFacade extends RepositoryFacade
{
	public ArtifactRepositoryFacade(String name, LoggingArtifactRepository repository)
	{
		super(name, repository);
	}

	@Override
	protected void refreshMirror(URI repoURI, Filter filter) throws ProvisionException
	{
		// TODO Auto-generated method stub
	}

	@Override
	protected void writeRepository(OutputStream output) throws IOException
	{
		new SimpleArtifactRepositoryIO().write((SimpleArtifactRepository)getRepository().wrappedRepository,
			output);
	}
}
