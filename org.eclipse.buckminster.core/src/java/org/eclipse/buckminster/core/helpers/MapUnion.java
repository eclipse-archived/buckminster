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

import org.eclipse.buckminster.core.common.model.IExpandingMap;

/**
 * @author Thomas Hallgren
 */
public class MapUnion<K, V> extends AbstractMap<K, V> implements IExpandingMap<K, V> {
	abstract class AbstractIterator<X> implements Iterator<X> {
		private Iterator<K> currentIterator = overlay.keySet().iterator();

		private K currentKey = null;

		private boolean phase1 = true;

		@Override
		public boolean hasNext() {
			currentKey = this.getValidKey(currentKey);
			return currentKey != null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		K nextKey() {
			K key = this.getValidKey(currentKey);
			if (key == null)
				throw new NoSuchElementException();

			currentKey = null; // Force retrieval of next key
			return key;
		}

		private K getValidKey(K key) {
			if (phase1) {
				// All keys are valid during phase 1 since they stem from
				// the mutable map.
				//
				if (key != null)
					return key;

				if (currentIterator.hasNext())
					return currentIterator.next();

				currentIterator = immutable.keySet().iterator();
				phase1 = false;
			}

			// Phase 2.
			// we must check that:
			// a) the key was not included in phase 1
			// b) the key is not in the anti-map.
			//
			if (key != null && !(overlay.containsKey(key) || antiMap.containsKey(key)))
				return key;

			while (currentIterator.hasNext()) {
				key = currentIterator.next();
				if (!(overlay.containsKey(key) || antiMap.containsKey(key)))
					return key;
			}
			return null;
		}
	}

	class EntryIterator extends AbstractIterator<Map.Entry<K, V>> {
		@Override
		public Map.Entry<K, V> next() {
			return new UnionEntry(this.nextKey());
		}
	}

	class KeyIterator extends AbstractIterator<K> {
		@Override
		public K next() {
			return this.nextKey();
		}
	}

	class UnionEntry implements Map.Entry<K, V> {
		private final K key;

		public UnionEntry(K key) {
			this.key = key;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return MapUnion.this.get(key);
		}

		@Override
		public V setValue(V value) {
			return MapUnion.this.put(key, value);
		}
	}

	class ValueIterator extends AbstractIterator<V> {
		@Override
		public V next() {
			return MapUnion.this.get(this.nextKey());
		}
	}

	private final HashMap<K, K> antiMap;

	private final Map<K, V> immutable;

	private final Map<K, V> overlay;

	@SuppressWarnings("unchecked")
	// safe anyway, since the map is immutable
	public MapUnion(Map<? extends K, ? extends V> mutable, Map<? extends K, ? extends V> immutable) {
		this.overlay = (Map<K, V>) mutable;
		this.immutable = (Map<K, V>) immutable;
		this.antiMap = new HashMap<K, K>();
	}

	private MapUnion(Map<K, V> mutable, Map<K, V> immutable, HashMap<K, K> antiMap) {
		this.overlay = mutable;
		this.immutable = immutable;
		this.antiMap = antiMap;
	}

	@Override
	public void clear() {
		overlay.clear();

		// Add an anti-entry for each key found in the immutable map.
		//
		antiMap.clear();
		Iterator<K> itor = immutable.keySet().iterator();
		while (itor.hasNext()) {
			K key = itor.next();
			antiMap.put(key, key);
		}
	}

	@Override
	public Object clone() {
		HashMap<K, V> mt = new HashMap<K, V>();
		mt.putAll(overlay);
		HashMap<K, K> am = new HashMap<K, K>();
		am.putAll(antiMap);
		return new MapUnion<K, V>(mt, immutable, am);
	}

	@Override
	public boolean containsKey(Object key) {
		return overlay.containsKey(key) || (immutable.containsKey(key) && !antiMap.containsKey(key));
	}

	@Override
	public Set<Entry<K, V>> entrySet() {
		return new AbstractSet<Entry<K, V>>() {
			@Override
			public Iterator<Entry<K, V>> iterator() {
				return new EntryIterator();
			}

			@Override
			public int size() {
				return MapUnion.this.size();
			}
		};
	}

	@Override
	public V get(Object key) {
		return get(key, this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public V get(Object key, Map<K, V> expansionScope) {
		V value;
		if (overlay instanceof IExpandingMap)
			value = ((IExpandingMap<K, V>) overlay).get(key, expansionScope);
		else
			value = overlay.get(key);

		if (value == null && !overlay.containsKey(key)) {
			if (immutable instanceof IExpandingMap)
				value = ((IExpandingMap<K, V>) immutable).get(key, expansionScope);
			else
				value = immutable.get(key);

			if (value != null && antiMap.containsKey(key))
				value = null;
		}
		return value;
	}

	@Override
	public Set<K> keySet() {
		return new AbstractSet<K>() {
			@Override
			public Iterator<K> iterator() {
				return new KeyIterator();
			}

			@Override
			public int size() {
				return MapUnion.this.size();
			}
		};
	}

	public Set<K> overlayKeySet() {
		return overlay.keySet();
	}

	@Override
	public V put(K key, V value) {
		antiMap.remove(key);
		return overlay.put(key, value);
	}

	@Override
	@SuppressWarnings("unchecked")
	public V remove(Object key) {
		V value = null;
		if (overlay.containsKey(key)) {
			if (immutable.containsKey(key))
				antiMap.put((K) key, (K) key);
			value = overlay.remove(key);
		} else if (immutable.containsKey(key)) {
			if (!antiMap.containsKey(key)) {
				value = immutable.get(key);
				antiMap.put((K) key, (K) key);
			}
		}
		return value;
	}

	@Override
	public int size() {
		int immutableVisibleCount = 0;
		for (K key : immutable.keySet())
			if (!(overlay.containsKey(key) || antiMap.containsKey(key)))
				immutableVisibleCount++;
		return overlay.size() + immutableVisibleCount;
	}

	@Override
	public Collection<V> values() {
		return new AbstractCollection<V>() {
			@Override
			public Iterator<V> iterator() {
				return new ValueIterator();
			}

			@Override
			public int size() {
				return MapUnion.this.size();
			}
		};
	}
}
