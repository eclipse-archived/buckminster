/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider;

import java.util.List;

import org.apache.commons.httpclient.HttpClient;

/**
 * A distro provider - you can register, login, logout, get a distro, ...
 * 
 * @author Karel Brezina
 */
public interface IRemoteDistroProvider
{
	public static final int LOGIN_FAILED = -1;
	public static final int LOGIN_UNKNOW_KEY = -2;
	public static final int LOGIN_OK 	= 1;
	
	public static final int LOGOUT_FAILED = -1;
	public static final int LOGOUT_OK 	= 1;
	
	public static final int REGISTER_OK = 1;
	public static final int REGISTER_LOGIN_EXISTS = -1;
	public static final int REGISTER_LOGIN_TOO_SHORT = -2;
	public static final int REGISTER_PASSWORD_TOO_SHORT = -3;
	public static final int REGISTER_EMAIL_FORMAT_ERROR = -4;
	public static final int REGISTER_LOGIN_CONTAINS_AT = -5;
	public static final int REGISTER_LOGIN_INVALID = -6;
	public static final int REGISTER_EMAIL_ALREADY_VALIDATED = -7;
	public static final int REGISTER_FAIL = -99;
	
	public static final int FOLDER_ACCESS_OK = 1;
	public static final int FOLDER_ACCESS_FORBIDDEN = -1;
	public static final int FOLDER_ACCESS_FOLDER_NOT_FOUND = -2;
	public static final int FOLDER_ACCESS_INVITATION_EXISTS = -3;
	public static final int FOLDER_ACCESS_INVITATION_EXISTS_EMAIL_NOT_VERIFIED = -4;
	
	void initialize(String serviceURL) throws Exception;
	
	/**
	 * Gets HttpClient which was used for authentication. It should be used for getting password protected content after authentication.
	 * 
	 * @return
	 */
	HttpClient getHttpClient();
	
	int login(String userName, String password) throws Exception;

	/**
	 * The same as {@link IRemoteDistroProvider#login(String, String) login(String, String)}, but:
	 * <ul>
	 * <li>if already logged in and the same credentials are passed in, the original login is kept</li>
	 * <li>logout before login, if already logged in and different credentials are passed in</li>
	 * </ul>
	 * 
	 * @param userName
	 * @param password
	 * @return
	 * @throws Exception
	 */
	int relogin(String userName, String password) throws Exception;

	int login(String loginKey) throws Exception;
	
	/**
	 * The same as {@link IRemoteDistroProvider#login(String) login(String)}, but:
	 * <ul>
	 * <li>if already logged in and the same credentials are passed in, the original login is kept</li>
	 * <li>logout before login, if already logged in and different credentials are passed in</li>
	 * </ul>
	 * @param loginKey
	 * @return
	 * @throws Exception
	 */
	int relogin(String loginKey) throws Exception;
	
	/**
	 * Gets username of the currently logged in user or null if no user is logged in
	 * 
	 * @return
	 */
	String getCurrenlyLoggedUserName();
	
	String getLoginKey() throws Exception;
	
	void keepAlive() throws Exception;
	
	int logout() throws Exception;
	
	/**
	 * Logout and release connection
	 * 
	 * @throws Exception
	 */
	void releaseConnection() throws Exception;
	
	boolean isLoggedIn() throws Exception;
	
	int register(String userName, String password, String email) throws Exception;
	
	int checkFolderReadAccess(final String folderPath) throws Exception;
	
	/**
	 * Creates a new authenticator, that is connected to the same server as the original authenticator
	 * 
	 * @param login true - the new authenticator will be logged in using the same credentials as the original one,
	 * false - the new authenticator will be only initialized
	 * @return
	 * @throws Exception
	 */
	IRemoteDistroProvider createDuplicate(boolean login) throws Exception;
	
	List<DistroVariant> getDistroVariants(Long stackId) throws Exception;
	
	Distro getDistro(Long distroId) throws Exception;
}
