/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard;

import org.eclipse.buckminster.jnlp.distroprovider.IRemoteDistroProvider;

/**
 * Provides authenticator and information about login credentials and
 * 
 * @author Karel Brezina
 */
public interface ILoginHandler
{
	String getAuthenticatorCurrentUserName();

	String getAuthenticatorLoginKey();

	String getAuthenticatorLoginKeyUserName();

	String getAuthenticatorPassword();

	String getAuthenticatorUserName();

	IRemoteDistroProvider getDistroProvider();

	void removeAuthenticatorLoginKey();

	void setAuthenticatorPassword(String password);

	void setAuthenticatorUserName(String userName);

	void setDistroProvider(IRemoteDistroProvider authenticator);
}
