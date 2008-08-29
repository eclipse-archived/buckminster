/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp.cloudsmith.accountservice;

import java.io.Serializable;

/**
 * Methods in this interface are used for remote communication with CSsite
 * 
 * @author Karel Brezina
 */
public interface IAccountService extends Serializable
{
	public static final int LOGIN_FAILED = -1;

	public static final int LOGIN_UNKNOW_KEY = -2;

	public static final int LOGIN_REMOTE_HOST_MISMATCH = -3;

	public static final int LOGIN_OK = 1;

	public static final int LOGOUT_FAILED = -1;

	public static final int LOGOUT_OK = 1;

	public static final int REGISTER_OK = 1;

	public static final int REGISTER_LOGIN_EXISTS = -1;

	public static final int REGISTER_LOGIN_TOO_SHORT = -2;

	public static final int REGISTER_PASSWORD_TOO_SHORT = -3;

	public static final int REGISTER_EMAIL_FORMAT_ERROR = -4;

	public static final int REGISTER_LOGIN_CONTAINS_AT = -5;

	public static final int REGISTER_LOGIN_INVALID = -6;

	public static final int REGISTER_EMAIL_ALREADY_VALIDATED = -7;

	public static final int REGISTER_FAIL = -99;

	public static final int STATUS_OK = 1;

	public static final int STATUS_FAIL = -1;

	public static final int ERROR_NOT_LOGGED_IN = -2;

	public static final int ERROR_TIMEOUT = -3;

	public static final int ERROR_FOLDER_ACCESS_DENIED = -4;

	public static final int ERROR_ARTIFACT_ALREADY_EXISTS = -5;

	public static final int FOLDER_ACCESS_OK = 1;

	public static final int FOLDER_ACCESS_FORBIDDEN = -1;

	public static final int FOLDER_ACCESS_FOLDER_NOT_FOUND = -2;

	public static final int FOLDER_ACCESS_INVITATION_EXISTS = -3;

	public static final int FOLDER_ACCESS_INVITATION_EXISTS_EMAIL_NOT_VERIFIED = -4;

	public int checkFolderReadAccess(String folderName);

	public String getLoginKey();

	public String getRemoteHost();

	public boolean isLoggedIn();

	public void keepAlive();

	public AccountServiceLoginResponse login();

	public AccountServiceLoginResponse login(String loginKey);

	public AccountServiceLoginResponse login(String userName, String password);

	public int logout();

	public int logoutSession();

	public int register(String userName, String password, String email);

	public void setRemoteHost(String remoteHost);
}
