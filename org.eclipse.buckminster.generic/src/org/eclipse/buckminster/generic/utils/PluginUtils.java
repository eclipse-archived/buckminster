/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.generic.utils;

import org.eclipse.buckminster.generic.Messages;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.osgi.util.NLS;

/**
 * A set of utility methods for handling of common plugin tasks.
 * 
 * @author Thomas Hallgren
 * @author Henrik Lindberg
 * 
 */
public class PluginUtils {
	/**
	 * Returns an attribute from an IConfigurationElement, or the default value
	 * if attribute not present. If both attribute and default value are
	 * missing, an IllegalArgumentException is thrown.
	 * 
	 * @param configElement
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String getAttribute(IConfigurationElement configElement, String name, String defaultValue) {
		String value = configElement.getAttribute(name);
		if (value != null)
			return value;
		if (defaultValue != null)
			return defaultValue;
		throw new IllegalArgumentException(NLS.bind(Messages.missing_attribute_0, name));
	}

	/**
	 * Returns a String[] of attributes from an IConfigurationElement sequence.
	 * If sequence is missing, an IllegalArgumentException is thrown. An empty
	 * String[] is returned if sequence was empty.
	 * 
	 * @param configElement
	 * @param name
	 * @param defaultValue
	 * @return
	 */
	public static String[] getAttributeArray(IConfigurationElement configElement, String name) {
		IConfigurationElement[] childElements = configElement.getChildren(name);
		if (childElements.length < 1)
			throw new IllegalArgumentException(NLS.bind(Messages.missing_sequence_0, name));
		String[] result = new String[childElements.length];
		for (int i = 0; i < childElements.length; i++)
			result[i] = getAttribute(childElements[i], "name", null); //$NON-NLS-1$
		return result;
	}
}
