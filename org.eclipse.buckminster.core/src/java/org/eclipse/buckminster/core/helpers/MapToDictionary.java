/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author Thomas Hallgren
 */
public class MapToDictionary<K, V> extends Dictionary<K, V>
{
	@SuppressWarnings("unchecked")
	public static <K, V> Dictionary<K, V> wrap(Map<K, V> map)
	{
		if(map == null)
			return null;
		if(map instanceof Dictionary)
			return (Dictionary<K, V>)map;
		return new MapToDictionary<K, V>(map);
	}

	private final Map<K, V> m_wrappedMap;

	public MapToDictionary(Map<K, V> wrappedMap)
	{
		m_wrappedMap = wrappedMap;
	}

	@Override
	public Enumeration<V> elements()
	{
		return new IteratorToEnumeration<V>(m_wrappedMap.values().iterator());
	}

	@Override
	public V get(Object key)
	{
		return m_wrappedMap.get(key);
	}

	@Override
	public boolean isEmpty()
	{
		return m_wrappedMap.isEmpty();
	}

	@Override
	public Enumeration<K> keys()
	{
		return new IteratorToEnumeration<K>(m_wrappedMap.keySet().iterator());
	}

	@Override
	public V put(K key, V value)
	{
		return m_wrappedMap.put(key, value);
	}

	@Override
	public V remove(Object key)
	{
		return m_wrappedMap.remove(key);
	}

	@Override
	public int size()
	{
		return m_wrappedMap.size();
	}
}
