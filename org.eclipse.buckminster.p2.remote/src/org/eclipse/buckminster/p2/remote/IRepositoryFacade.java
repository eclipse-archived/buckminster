package org.eclipse.buckminster.p2.remote;

import java.net.URI;

import org.eclipse.buckminster.p2.remote.change.SynchronizationBlock;
import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;
import org.eclipse.equinox.internal.provisional.p2.query.Query;

public interface IRepositoryFacade
{
	/**
	 * Returns a synchronization block containing all changes that has been made to a repository
	 * since the change number indicated by sequenceNumber
	 * @param sequenceNumber The number of the last change known by this client
	 * @return A synchronization block containing all changes.
	 * @throws ProvisionException
	 */
	SynchronizationBlock getChanges(long sequenceNumber) throws ProvisionException;

	/**
	 * Returns the name of this facade
	 * @return The name of the facade
	 */
	String getName();

	/**
	 * Return a stream that can produce the data for the repository.
	 * @return
	 */
	IRepositoryDataStream getRepositoryData() throws ProvisionException;

	/**
	 * Register a mirror with this facade.
	 * @param repositoryMirror A URI denoting the repository that should be mirrored by this facade.
	 * @param query A filter used for the mirroring, can be null in which case everything is
	 *            mirrored. The query must be serializable.
	 * @throws ProvisionException
	 */
	void registerMirror(URI repositoryMirror, Query query) throws ProvisionException;
}
