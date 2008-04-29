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
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Plugin;
import org.eclipse.core.runtime.Status;


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
	private Plugin m_plugin;
	
	protected PluginClassHandle(Plugin plugin, IConfigurationElement configElement, Class<T> clazz, String requiredElement)
	{
		m_plugin = plugin;
		m_configElement = configElement;
		m_clazz = clazz;
		
		if(!configElement.getName().equals(requiredElement))
			throw new IllegalArgumentException("Not correct requiredElement: "+
					configElement.getName() +
					". Expected: " +
					requiredElement);
	}
	
	protected void logLoadError(IConfigurationElement configElement, Exception e)
	{
		String name = m_configElement.getName();
		String msg = "Failed to load extension point element named" +
				(name == null ? "[missing name attribute]" : name) +
				" in " +
				configElement.getDeclaringExtension().getNamespaceIdentifier();
		ILog log = m_plugin.getLog();
		
		log.log(new Status(IStatus.ERROR, m_plugin.getBundle().getSymbolicName(), msg));
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
