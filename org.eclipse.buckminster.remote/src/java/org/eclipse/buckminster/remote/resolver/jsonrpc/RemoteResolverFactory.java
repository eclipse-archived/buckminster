/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.remote.resolver.jsonrpc;

import java.util.Map;

import org.eclipse.buckminster.remote.resolver.AbstractRemoteResolverFactory;
import org.eclipse.buckminster.remote.resolver.IResolutionServiceConnection;
import org.eclipse.core.runtime.CoreException;

/**
 * Factory that enables access to a <code>RemoteResolver</code>
 * 
 * @author Karel Brezina
 */
public class RemoteResolverFactory extends AbstractRemoteResolverFactory
{
	@Override
	protected IResolutionServiceConnection createResolutionServiceConnection(String providerID, Map<String, Object> properties) throws CoreException
	{	
		return new ResolutionServiceConnection(providerID, (String)properties.get(LOGIN_PARAM), (String)properties.get(PASSWORD_PARAM));
	}
}
