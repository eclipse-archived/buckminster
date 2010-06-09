/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.remote;

import org.eclipse.core.runtime.CoreException;

/**
 * Provides basic authentication operations
 * 
 * @author Karel Brezina
 * 
 */
public interface IAuthenticatedConnection
{
	public static final int LOGIN_FAILED = -1;

	public static final int LOGIN_OK = 1;

	/**
	 * Checks the login status
	 * 
	 * @return true=logged in
	 * @throws CoreException
	 */
	boolean isLoggedIn() throws CoreException;

	/**
	 * Login to the remote service
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws CoreException
	 */
	int login(String userName, String password) throws CoreException;

	/**
	 * Logout from the remote service
	 * 
	 * @throws CoreException
	 */
	void logout() throws CoreException;
}
