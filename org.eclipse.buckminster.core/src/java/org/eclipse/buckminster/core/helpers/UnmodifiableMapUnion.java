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
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * @author Thomas Hallgren
 */
public class UnmodifiableMapUnion<K, V> extends AbstractMap<K, V>
{
	abstract class AbstractIterator<X> implements Iterator<X>
	{
		private Iterator<? extends K> m_currentIterator = m_overlay.keySet().iterator();

		private K m_currentKey = null;

		private boolean m_phase1 = true;

		public boolean hasNext()
		{
			m_currentKey = getValidKey();
			return m_currentKey != null;
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}

		K nextKey()
		{
			K key = getValidKey();
			if(key == null)
				throw new NoSuchElementException();

			m_currentKey = null; // Force retrieval of next key
			return key;
		}

		private K getValidKey()
		{
			if(m_currentKey != null)
				return m_currentKey;

			if(m_phase1)
			{
				// All keys are valid during phase 1 since they stem from
				// the mutable map.
				//
				if(m_currentIterator.hasNext())
				{
					m_currentKey = m_currentIterator.next();
					return m_currentKey;
				}
				m_currentIterator = m_map.keySet().iterator();
				m_phase1 = false;
			}

			while(m_currentIterator.hasNext())
			{
				K key = m_currentIterator.next();
				if(!m_overlay.containsKey(key))
				{
					m_currentKey = key;
					break;
				}
			}
			return m_currentKey;
		}
	}

	class EntryIterator extends AbstractIterator<Map.Entry<K, V>>
	{
		public Map.Entry<K, V> next()
		{
			return new UnionEntry(nextKey());
		}
	}

	class KeyIterator extends AbstractIterator<K>
	{
		public K next()
		{
			return nextKey();
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
			return get(m_key);
		}

		public V setValue(V value)
		{
			throw new UnsupportedOperationException();
		}
	}

	class ValueIterator extends AbstractIterator<V>
	{
		public V next()
		{
			return get(this.nextKey());
		}
	}

	private final Map<? extends K, ? extends V> m_map;

	private final Map<? extends K, ? extends V> m_overlay;

	public UnmodifiableMapUnion(Map<? extends K, ? extends V> overlay, Map<? extends K, ? extends V> map)
	{
		m_overlay = overlay;
		m_map = map;
	}

	@Override
	public void clear()
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key)
	{
		return m_overlay.containsKey(key) || m_map.containsKey(key);
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
				return UnmodifiableMapUnion.this.size();
			}
		};
	}

	@Override
	public V get(Object key)
	{
		V value = m_overlay.get(key);
		if(value == null && !m_overlay.containsKey(key))
			value = m_map.get(key);
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
				return UnmodifiableMapUnion.this.size();
			}
		};
	}

	public Set<? extends K> overlayKeySet()
	{
		return m_overlay.keySet();
	}

	@Override
	public V put(K key, V value)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object key)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public int size()
	{
		int immutableVisibleCount = 0;
		for(K key : m_map.keySet())
			if(!m_overlay.containsKey(key))
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
				return UnmodifiableMapUnion.this.size();
			}
		};
	}
}
