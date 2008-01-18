/*****************************************************************************
* Copyright (c) 2006-2007, Cloudsmith Inc.
* The code, documentation and other materials contained herein have been
* licensed under the Eclipse Public License - v 1.0 by the copyright holder
* listed above, as the Initial Contributor under such license. The text of
* such license is available at www.eclipse.org.
*****************************************************************************/

package org.eclipse.buckminster.remote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;

/**
 * Remote service provide utils
 * 
 * @author Filip Hrbek
 */
public class ProviderUtil
{
	private static HashMap<String, IServiceProvider> s_serviceCache = new HashMap<String, IServiceProvider>();
	private static boolean s_registryScanned = false;
	
	public static final void registerProvider(IServiceProvider provider) throws CoreException
	{
		scanExtensionRegistry();

		if (s_serviceCache.get(provider.getId()) == null)
			s_serviceCache.put(provider.getId(), provider);
		else
			throw new BuckminsterException("A provider with ID " + provider.getId() + " is already registered");
	}

	/**
	 * Returns a list of all registered Buckminster service providers
	 * 
	 * @return list of providers' IDs; if there is no provider available, the list is empty (but never null)
	 */
	public static final List<String> getProviderIdList()
	{
		List<String> list = new ArrayList<String>();

		scanExtensionRegistry();

		for (IServiceProvider provider : s_serviceCache.values())
			list.add(provider.getId());
		
		return list;
	}

	/**
	 * Searches for a provider of the ID specified
	 * 
	 * @param providerID
	 * @return provider configuration
	 * @throws NoSuchProviderException if there is no provider of the specified ID registered
	 */
	public static final IServiceProvider findProvider(String providerID) throws NoSuchProviderException
	{
		scanExtensionRegistry();

		IServiceProvider provider;

		if ((provider = s_serviceCache.get(providerID)) != null)
			return provider;

		throw new NoSuchProviderException("Provider '" + providerID + "' was not found");
	}

	/**
	 * Creates initialization point URL from hostname, port and initialization path
	 * 
	 * @param provider service provider
	 * @return
	 */
	public static final String getInitializationPoint(IServiceProvider provider)
	{
		return buildURLString(provider.getProtocol(), provider.getHost(), provider.getPort(), provider.getInitializationPath());
	}

	/**
	 * Creates target point URL from hostname, port and service path
	 * 
	 * @param provider service provider
	 * @return
	 */
	public static final String getTargetPoint(IServiceProvider provider)
	{
		return buildURLString(provider.getProtocol(), provider.getHost(), provider.getPort(), provider.getServicePath());
	}

	/**
	 * Builds URL string from hostname, port and service path
	 * 
	 * @param protocol
	 * @param host hostname or IP address
	 * @param port port
	 * @param servicePath service path
	 * @return
	 */
	public static final String buildURLString(String protocol, String host, String port, String path)
	{
		return
			protocol + "://" + host +
			(port == null ? "" : (":" + port)) +
			(path == null ? "" : path);
	}

	private static void scanExtensionRegistry()
	{
		if (!s_registryScanned)
		{
			for(IConfigurationElement elem : Platform.getExtensionRegistry().getConfigurationElementsFor(
					RemoteConstants.PROVIDERS_POINT))
			{
				IServiceProvider provider = new ServiceProvider(
						elem.getAttribute(RemoteConstants.PROVIDER_ID_ATTR),
						elem.getAttribute(RemoteConstants.PROVIDER_NAME_ATTR),
						elem.getAttribute(RemoteConstants.PROVIDER_PROTOCOL_ATTR),
						elem.getAttribute(RemoteConstants.PROVIDER_HOSTNAME_ATTR),
						elem.getAttribute(RemoteConstants.PROVIDER_PORT_ATTR),
						elem.getAttribute(RemoteConstants.PROVIDER_INITIALIZATION_PATH_ATTR),
						elem.getAttribute(RemoteConstants.PROVIDER_SERVICE_PATH_ATTR),
						RemoteConstants.BOOLEAN_TRUE.equalsIgnoreCase(elem.getAttribute(RemoteConstants.PROVIDER_LOGIN_SUPPORT)) ? true : false
						);
				s_serviceCache.put(provider.getId(), provider);
			}
			
			s_registryScanned = true;
		}
	}
}
