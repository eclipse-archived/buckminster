/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.util.Map;

import org.eclipse.buckminster.core.IBuckminsterExtension;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractExtension implements IBuckminsterExtension, IExecutableExtension
{
	@SuppressWarnings("unchecked")
	private static Map<String, String> getInitializationParameters(Object data)
	{
		return (data instanceof Map)
				? (Map)data
				: null;
	}

	private String m_id;

	/**
	 * Returns the id as it was defined in the extension
	 */
	public String getId()
	{
		return m_id;
	}

	/**
	 * Assing a parameter that originates from the extension definition. Subclasses must override this method if they
	 * want to receive parameters.
	 * 
	 * @param key
	 *            The name of the parameter
	 * @param value
	 *            The parameter value
	 */
	public void setExtensionParameter(String key, String value) throws CoreException
	{
	}

	public void setInitializationData(IConfigurationElement config, String propertyName, Object data)
			throws CoreException
	{
		m_id = config.getAttribute("id"); //$NON-NLS-1$
		Map<String, String> initParams = getInitializationParameters(data);
		if(initParams != null)
		{
			for(Map.Entry<String, String> entry : initParams.entrySet())
				setExtensionParameter(entry.getKey(), entry.getValue());
		}
	}
}
