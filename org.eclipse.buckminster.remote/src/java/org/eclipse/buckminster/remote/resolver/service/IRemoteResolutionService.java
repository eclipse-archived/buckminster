/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.remote.resolver.service;

import java.io.Serializable;

import org.eclipse.buckminster.remote.IProgressInfo;

/**
 * Methods in this interface are used for remote communication with remote resolution server. They are called locally and exercised on the remote server
 * 
 * @author Karel Brezina
 */
public interface IRemoteResolutionService extends Serializable
{
	public static final int LOGIN_FAILED = -1;
	
	public static final int LOGIN_OK = 1;
	
	/**
	 * Login to the remote service
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	int login(String userName, String password);
	
	/**
	 * Logout from the remote service
	 */
	void logout();
	
	/**
	 * Checks the login status
	 * 
	 * @return
	 */
	boolean isLoggedIn();
	
	/**
	 * Cancel resolution
	 */
	void cancel();
	
	/**
	 * Get progress information
	 * @return
	 */
	IProgressInfo getProgressInfo();

	/**
	 * Fires BOM resolution
	 * 
	 * @param bomImage BillOfMaterial image
	 */
	void fireBomResolution(byte[] bomImage);

	/**
	 * Gets resolution results
	 * 
	 * @return resolved BillOfMaterial image
	 */
	byte[] getResolutionResult();
}
