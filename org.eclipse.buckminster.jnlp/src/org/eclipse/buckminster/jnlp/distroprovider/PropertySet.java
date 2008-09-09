/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.distroprovider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Karel Brezina
 * 
 */
public class PropertySet
{
	private Map<String, String> m_properties = new HashMap<String, String>();

	public Map<String, String> getProperties()
	{
		return m_properties;
	}

	public String getProperty(String name)
	{
		return m_properties.get(name);
	}

	public void setProperties(Map<String, String> properties)
	{
		m_properties = properties;
	}

	public void setProperty(String name, String value)
	{
		m_properties.put(name, value);
	}
}
