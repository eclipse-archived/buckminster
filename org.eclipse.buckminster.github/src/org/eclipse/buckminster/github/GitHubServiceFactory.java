package org.eclipse.buckminster.github;

import org.eclipse.buckminster.github.service.RepoService;
import org.eclipse.buckminster.github.service.impl.GitHubServiceFactoryImpl;

/**
 * This factory provides access to services in the GitHub v3 API
 */
public interface GitHubServiceFactory {
	GitHubServiceFactory INSTANCE = new GitHubServiceFactoryImpl();

	/**
	 * Creates a repository service that will use the provided
	 * <code>authToken</code> in its requests to GitHub
	 * 
	 * @param authToken
	 *            The OAuth token to use. Can be <code>null</code> in which case
	 *            only read-only permissions apply
	 * @return The created service
	 */
	RepoService createRepoService(String authToken);

	/**
	 * <p>
	 * Obtains an OAuth access token from GitHub on behalf of the given
	 * <code>login</code> and <code>password</code>. The token provides access
	 * for the application identified with <code>clientId</code> and
	 * <code>clientSecret</code>. Access is granted for the given
	 * <code>scopes</code>.
	 * </p>
	 * <p>
	 * Please visit <a href="http://developer.github.com/v3/oauth/">The GitHub
	 * OAuth page</a> for more information.
	 * </p>
	 * 
	 * @param login
	 *            The login that identifies the user making the request
	 * @param password
	 *            The password for the user (used in basic authentication)
	 * @param clientId
	 *            The GitHub client id for the authorized application
	 * @param clientSecret
	 *            The GitHub client secret key for the authorized application
	 * @param scopes
	 * @return An OAuth authentication code that can be used when accessing the
	 *         GitHub API.
	 * @throws GitHubException
	 *             If the token could not be obtained
	 */
	String getAccessToken(String login, String password, String clientId, String clientSecret, String... scopes) throws GitHubException;
}