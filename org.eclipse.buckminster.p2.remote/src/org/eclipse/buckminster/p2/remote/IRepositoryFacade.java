package org.eclipse.buckminster.p2.remote;

import java.io.IOException;
import java.net.URI;

import org.eclipse.buckminster.p2.remote.change.SynchronizationBlock;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;

public interface IRepositoryFacade
{
	/**
	 * Returns a synchronization block containing all changes that has been made to a repository
	 * since the change number indicated by sequenceNumber
	 * @param sequenceNumber The number of the last change known by this client
	 * @param refresh True if the facade should be refreshed prior to fetching the block
	 * @return A synchronization block containing all changes.
	 * @throws CoreException
	 */
	SynchronizationBlock getChanges(long sequenceNumber, boolean refresh) throws CoreException;

	/**
	 * Returns the name of this facade
	 * @return The name of the facade
	 */
	String getName();

	/**
	 * Return a stream that can produce the data for the repository.
	 * @return
	 */
	IRepositoryDataStream getRepositoryData() throws IOException;

	/**
	 * Register a mirror with this facade.
	 * @param repositoryMirror A URI denoting the repository that should be mirrored by this facade.
	 * @param ldapFilter A filter used for the mirroring, can be null in which case everything is
	 *            mirrored.
	 * @throws ProvisionException
	 */
	void registerMirror(URI repositoryMirror, String ldapFilter) throws ProvisionException;
}
