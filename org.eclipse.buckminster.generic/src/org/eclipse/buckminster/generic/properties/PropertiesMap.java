/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/
package org.eclipse.buckminster.generic.properties;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Utility class helping to read properties as Strings. Usage: - instantiate
 * from java.util.Properties instance - modify the map - get Properties instance
 * back
 * 
 */
public class PropertiesMap extends HashMap<String, String> {
	private static final long serialVersionUID = -5519568336458564954L;

	/**
	 * Create an empty properties map.
	 */
	public PropertiesMap() {
		// do nothing
	}

	/**
	 * Create a properties map initialized from Properties
	 * 
	 * @param properties
	 */
	public PropertiesMap(Properties properties) {
		// add all the properties
		for (Map.Entry<Object, Object> e : properties.entrySet()) {
			String key = (String) e.getKey();
			String val = (String) e.getValue();
			put(key, val);
		}
	}

	public Properties asProperties() {
		Properties p = new Properties();
		p.putAll(this);
		return p;
	}

	/**
	 * Returns the value of the property, or the default value if it did not
	 * exist. The property is parsed using Boolean.parseBoolean(String)
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public boolean getBooleanProperty(String key, boolean defaultValue) {
		String val = get(key);
		return val == null ? defaultValue : Boolean.parseBoolean(val);
	}

	/**
	 * Returns the value of the property, or the default value if it did not
	 * exist.
	 * 
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public String getStringProperty(String key, String defaultValue) {
		String val = get(key);
		return val == null ? defaultValue : val;
	}
}
