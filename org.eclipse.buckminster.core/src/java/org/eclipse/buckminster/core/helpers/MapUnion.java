/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author Thomas Hallgren
 */
public class MapUnion<K, V> extends AbstractMap<K, V>
{
	abstract class AbstractIterator<X> implements Iterator<X>
	{
		private Iterator<K> m_currentIterator = m_overlay.keySet().iterator();

		private K m_currentKey = null;

		private boolean m_phase1 = true;

		private K getValidKey(K key)
		{
			if(m_phase1)
			{
				// All keys are valid during phase 1 since they stem from
				// the mutable map.
				//
				if(key != null)
					return key;

				if(m_currentIterator.hasNext())
					return m_currentIterator.next();

				m_currentIterator = m_immutable.keySet().iterator();
				m_phase1 = false;
			}

			// Phase 2.
			// we must check that:
			// a) the key was not included in phase 1
			// b) the key is not in the anti-map.
			//
			if(key != null && !(m_overlay.containsKey(key) || m_antiMap.containsKey(key)))
				return key;

			while(m_currentIterator.hasNext())
			{
				key = m_currentIterator.next();
				if(!(m_overlay.containsKey(key) || m_antiMap.containsKey(key)))
					return key;
			}
			return null;
		}

		public boolean hasNext()
		{
			m_currentKey = this.getValidKey(m_currentKey);
			return m_currentKey != null;
		}

		K nextKey()
		{
			K key = this.getValidKey(m_currentKey);
			if(key == null)
				throw new NoSuchElementException();

			m_currentKey = null; // Force retrieval of next key
			return key;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	class EntryIterator extends AbstractIterator<Map.Entry<K, V>>
	{
		public Map.Entry<K, V> next()
		{
			return new UnionEntry(this.nextKey());
		}
	}

	class KeyIterator extends AbstractIterator<K>
	{
		public K next()
		{
			return this.nextKey();
		}
	}

	class UnionEntry implements Map.Entry<K, V>
	{
		private final K m_key;

		public UnionEntry(K key)
		{
			m_key = key;
		}

		public K getKey()
		{
			return m_key;
		}

		public V getValue()
		{
			return MapUnion.this.get(m_key);
		}

		public V setValue(V value)
		{
			return MapUnion.this.put(m_key, value);
		}
	}

	class ValueIterator extends AbstractIterator<V>
	{
		public V next()
		{
			return MapUnion.this.get(this.nextKey());
		}
	}

	private final HashMap<K, K> m_antiMap;

	private final Map<K, V> m_immutable;

	private final Map<K, V> m_overlay;

	@SuppressWarnings("unchecked")
	// safe anyway, since the map is immutable
	public MapUnion(Map<K, V> mutable, Map<? extends K, ? extends V> immutable)
	{
		m_overlay = mutable;
		m_immutable = (Map<K, V>)immutable;
		m_antiMap = new HashMap<K, K>();
	}

	private MapUnion(Map<K, V> mutable, Map<K, V> immutable, HashMap<K, K> antiMap)
	{
		m_overlay = mutable;
		m_immutable = immutable;

		int shadowCount = 0;
		for(K key : m_overlay.keySet())
			if(m_immutable.containsKey(key))
				++shadowCount;
		m_antiMap = antiMap;
	}

	@Override
	public void clear()
	{
		m_overlay.clear();

		// Add an anti-entry for each key found in the immutable map.
		//
		m_antiMap.clear();
		Iterator<K> itor = m_immutable.keySet().iterator();
		while(itor.hasNext())
		{
			K key = itor.next();
			m_antiMap.put(key, key);
		}
	}

	@Override
	public Object clone()
	{
		HashMap<K, V> mutable = new HashMap<K, V>();
		mutable.putAll(m_overlay);
		HashMap<K, K> antiMap = new HashMap<K, K>();
		antiMap.putAll(m_antiMap);
		return new MapUnion<K, V>(mutable, m_immutable, antiMap);
	}

	@Override
	public boolean containsKey(Object key)
	{
		return m_overlay.containsKey(key) || (m_immutable.containsKey(key) && !m_antiMap.containsKey(key));
	}

	@Override
	public Set<Entry<K, V>> entrySet()
	{
		return new AbstractSet<Entry<K, V>>()
		{
			@Override
			public Iterator<Entry<K, V>> iterator()
			{
				return new EntryIterator();
			}

			@Override
			public int size()
			{
				return MapUnion.this.size();
			}
		};
	}

	@Override
	public V get(Object key)
	{
		V value = m_overlay.get(key);
		if(value == null && !m_overlay.containsKey(key))
		{
			value = m_immutable.get(key);
			if(value != null && m_antiMap.containsKey(key))
				value = null;
		}
		return value;
	}

	@Override
	public Set<K> keySet()
	{
		return new AbstractSet<K>()
		{
			@Override
			public Iterator<K> iterator()
			{
				return new KeyIterator();
			}

			@Override
			public int size()
			{
				return MapUnion.this.size();
			}
		};
	}

	public Set<K> overlayKeySet()
	{
		return m_overlay.keySet();
	}

	@Override
	public V put(K key, V value)
	{
		m_antiMap.remove(key);
		return m_overlay.put(key, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public V remove(Object key)
	{
		V value = null;
		if(m_overlay.containsKey(key))
		{
			if(m_immutable.containsKey(key))
				m_antiMap.put((K)key, (K)key);
			value = m_overlay.remove(key);
		}
		else if(m_immutable.containsKey(key))
		{
			if(!m_antiMap.containsKey(key))
			{
				value = m_immutable.get(key);
				m_antiMap.put((K)key, (K)key);
			}
		}
		return value;
	}

	@Override
	public int size()
	{
		int immutableVisibleCount = 0;
		for(K key : m_immutable.keySet())
			if(!(m_overlay.containsKey(key) || m_antiMap.containsKey(key)))
				immutableVisibleCount++;
		return m_overlay.size() + immutableVisibleCount;
	}

	@Override
	public Collection<V> values()
	{
		return new AbstractCollection<V>()
		{
			@Override
			public Iterator<V> iterator()
			{
				return new ValueIterator();
			}

			@Override
			public int size()
			{
				return MapUnion.this.size();
			}
		};
	}
}
