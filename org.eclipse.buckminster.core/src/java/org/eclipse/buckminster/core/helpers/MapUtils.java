/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 * 
 * Contributors:
 *  Lorenzo Bettini - Initial implementation and API
 ******************************************************************/

package org.eclipse.buckminster.core.helpers;

import java.util.Map;

/**
 * A utility class to deal with the change of
 * {@link org.eclipse.pde.build.IFetchFactory}'s parseMapFileEntry, which now
 * takes a Map<String,Object> instead of Map<String,String>. This utility class
 * should make the porting of existing code to Luna easier.
 * 
 * @author Lorenzo Bettini
 * 
 */
public class MapUtils {

	/**
	 * Utility method that converts the mapped value to String, after checking
	 * whether there is a mapped value.
	 * 
	 * @param map
	 * @param key
	 * @return the mapped String or null
	 */
	public static String getString(Map<String, ? extends Object> map, String key) {
		Object value = map.get(key);
		if (value != null)
			return value.toString();

		return null;
	}
}
