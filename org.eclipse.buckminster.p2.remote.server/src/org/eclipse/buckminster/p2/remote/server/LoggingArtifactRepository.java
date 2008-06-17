/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.p2.remote.server;

import java.io.File;
import java.io.OutputStream;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactDescriptor;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRepository;
import org.eclipse.equinox.internal.provisional.p2.artifact.repository.IArtifactRequest;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.metadata.IArtifactKey;

public class LoggingArtifactRepository extends LoggingRepository implements IArtifactRepository
{
	public LoggingArtifactRepository(IArtifactRepository remoteRepository, File changeLogFile)
	throws CoreException
	{
		super(remoteRepository, changeLogFile);
	}

	public void addDescriptor(IArtifactDescriptor descriptor)
	{
	}

	public void addDescriptors(IArtifactDescriptor[] descriptors)
	{
	}

	public boolean contains(IArtifactDescriptor descriptor)
	{
		return getWrapped().contains(descriptor);
	}

	public boolean contains(IArtifactKey key)
	{
		return getWrapped().contains(key);
	}

	public IStatus getArtifact(IArtifactDescriptor descriptor, OutputStream destination,
		IProgressMonitor monitor)
	{
		return getWrapped().getArtifact(descriptor, destination, monitor);
	}

	public IArtifactDescriptor[] getArtifactDescriptors(IArtifactKey key)
	{
		return getWrapped().getArtifactDescriptors(key);
	}

	public IArtifactKey[] getArtifactKeys()
	{
		return getWrapped().getArtifactKeys();
	}

	public IStatus getArtifacts(IArtifactRequest[] requests, IProgressMonitor monitor)
	{
		return getWrapped().getArtifacts(requests, monitor);
	}

	public OutputStream getOutputStream(IArtifactDescriptor descriptor) throws ProvisionException
	{
		return getWrapped().getOutputStream(descriptor);
	}

	public void removeAll()
	{
	}

	public void removeDescriptor(IArtifactDescriptor descriptor)
	{
	}

	public void removeDescriptor(IArtifactKey key)
	{
	}

	private IArtifactRepository getWrapped()
	{
		return (IArtifactRepository)wrappedRepository;
	}
}
