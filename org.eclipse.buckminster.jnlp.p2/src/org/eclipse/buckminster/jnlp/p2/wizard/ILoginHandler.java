/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.p2.wizard;

import org.eclipse.buckminster.jnlp.distroprovider.IRemoteDistroProvider;

/**
 * Provides authenticator and information about login credentials and
 * 
 * @author Karel Brezina
 */
public interface ILoginHandler
{
	String getAuthenticatorLoginKey();
	
	void removeAuthenticatorLoginKey();

	String getAuthenticatorLoginKeyUserName();
	
	String getAuthenticatorCurrentUserName();
	
	String getAuthenticatorUserName();

	void setAuthenticatorUserName(String userName);
	
	String getAuthenticatorPassword();

	void setAuthenticatorPassword(String password);
	
	IRemoteDistroProvider getDistroProvider();
	
	void setDistroProvider(IRemoteDistroProvider authenticator);
}
