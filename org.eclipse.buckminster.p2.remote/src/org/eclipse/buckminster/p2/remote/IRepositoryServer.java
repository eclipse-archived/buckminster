package org.eclipse.buckminster.p2.remote;

import java.util.List;

import org.eclipse.equinox.internal.provisional.p2.core.ProvisionException;

public interface IRepositoryServer
{
	static final String SERVICE_NAME = "repositoryServer"; //$NON-NLS-1$

	/**
	 * Create a new facade for interaction with a artifact repository managed on the remote server.
	 * An exception is thrown if a artifact facade named <code>repositoryName</code> already exists.
	 * @param facadeName The name for the new facade
	 * @return A facade for the repository
	 * @throws ProvisionException
	 * @throws SecurityException if the user is not logged in as a repository administrator.
	 */
	IRepositoryFacade createArtifactRepositoryFacade(String facadeName) throws ProvisionException;

	/**
	 * Create a new facade for interaction with a metadata repository managed on the remote server.
	 * An exception is thrown if a metadata facade named <code>repositoryName</code> already exists.
	 * @param facadeName The name for the new facade
	 * @return A facade for the repository
	 * @throws ProvisionException
	 * @throws SecurityException if the user is not logged in as a repository administrator.
	 */
	IRepositoryFacade createMetadataRepositoryFacade(String facadeName) throws ProvisionException;

	/**
	 * Delete an artifact repository facade and all underlying metadata from the server
	 * @param facadeName The name by which the facade is registered in the remote server
	 * @return true if the facade existed and was deleted and false if it did not exist.
	 * @throws ProvisionException
	 * @throws SecurityException if the user is not logged in as a repository administrator.
	 */
	boolean deleteArtifactRepositoryFacade(String facadeName) throws ProvisionException;

	/**
	 * Delete a metadata repository facade and all underlying metadata from the server
	 * @param facadeName The name by which the facade is registered in the remote server
	 * @return true if the facade existed and was deleted and false if it did not exist.
	 * @throws ProvisionException
	 * @throws SecurityException if the user is not logged in as a repository administrator.
	 */
	boolean deleteMetadataRepositoryFacade(String facadeName) throws ProvisionException;

	/**
	 * Return a facade for interaction with a artifact repository.
	 * @param facadeName The name by which the facade is registered in the remote server
	 * @return A facade
	 * @throws ProvisionException
	 */
	IRepositoryFacade getArtifactRepositoryFacade(String facadeName) throws ProvisionException;

	/**
	 * Obtains a list of names of all artifact repository facades known to this server.
	 * @return An alphabetically ordered list of facade names that might be empty but never
	 *         <code>null</code>.
	 */
	List<String> getArtifactRepositoryFacadeNames();

	/**
	 * Return a facade for interaction with a metadata repository.
	 * @param facadeName The name by which the facade is registered in the remote server
	 * @return A facade
	 * @throws ProvisionException
	 */
	IRepositoryFacade getMetadataRepositoryFacade(String facadeName) throws ProvisionException;

	/**
	 * Obtains a list of names of all metadata repository facades known to this server.
	 * @return An alphabetically ordered list of facade names that might be empty but never
	 *         <code>null</code>.
	 */
	List<String> getMetadataRepositoryFacadeNames();
}
