/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.plugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;


/**
 * Handle to a class in a plugin.
 * @author Henrik Lindberg
 */
public class PluginClassHandle<T>
{
	private final static String ATT_CLASS = "class";
	protected final IConfigurationElement m_configElement;
	private final Class<T> m_clazz;
	private T m_handle;
	private AbstractPlugin m_plugin;
	
	protected PluginClassHandle(AbstractPlugin plugin, IConfigurationElement configElement, Class<T> clazz, String requiredElement)
	{
		m_plugin = plugin;
		m_configElement = configElement;
		m_clazz = clazz;
		
		if(!configElement.getName().equals(requiredElement))
			throw new IllegalArgumentException("Not correct requiredElement: "+
					configElement.getName() +
					"expected: " +
					requiredElement);
	}
	
	protected void logLoadError(IConfigurationElement configElement, Exception e)
	{
		String name = m_configElement.getName();
		String msg = "Failed to load extension point element named" +
				(name == null ? "[missing name attribute]" : name) +
				" in " +
				configElement.getDeclaringExtension().getNamespaceIdentifier();
		m_plugin.logError(msg, e);
	}
	
	public synchronized T getHandle()
	{
		if(m_handle != null)
			return m_handle;
		try
		{
			m_handle = m_clazz.cast(m_configElement.createExecutableExtension(ATT_CLASS));
		}
		catch(CoreException e)
		{
			logLoadError(m_configElement, e);
		}
		return m_handle;
	}
}
