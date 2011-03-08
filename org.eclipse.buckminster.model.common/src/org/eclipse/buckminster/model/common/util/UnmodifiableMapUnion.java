/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.model.common.util;

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
public class UnmodifiableMapUnion<K, V> extends AbstractMap<K, V> {
	abstract class AbstractIterator<X> implements Iterator<X> {
		private Iterator<? extends K> currentIterator = overlay.keySet().iterator();

		private K currentKey = null;

		private boolean phase1 = true;

		@Override
		public boolean hasNext() {
			currentKey = getValidKey();
			return currentKey != null;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

		K nextKey() {
			K key = getValidKey();
			if (key == null)
				throw new NoSuchElementException();

			currentKey = null; // Force retrieval of next key
			return key;
		}

		private K getValidKey() {
			if (currentKey != null)
				return currentKey;

			if (phase1) {
				// All keys are valid during phase 1 since they stem from
				// the mutable map.
				//
				if (currentIterator.hasNext()) {
					currentKey = currentIterator.next();
					return currentKey;
				}
				currentIterator = map.keySet().iterator();
				phase1 = false;
			}

			while (currentIterator.hasNext()) {
				K key = currentIterator.next();
				if (!overlay.containsKey(key)) {
					currentKey = key;
					break;
				}
			}
			return currentKey;
		}
	}

	class EntryIterator extends AbstractIterator<Map.Entry<K, V>> {

		@Override
		public Map.Entry<K, V> next() {
			return new UnionEntry(nextKey());
		}
	}

	class KeyIterator extends AbstractIterator<K> {

		@Override
		public K next() {
			return nextKey();
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
			return get(key);
		}

		@Override
		public V setValue(V value) {
			throw new UnsupportedOperationException();
		}
	}

	class ValueIterator extends AbstractIterator<V> {

		@Override
		public V next() {
			return get(this.nextKey());
		}
	}

	private final Map<? extends K, ? extends V> map;

	private final Map<? extends K, ? extends V> overlay;

	public UnmodifiableMapUnion(Map<? extends K, ? extends V> overlay, Map<? extends K, ? extends V> map) {
		this.overlay = overlay;
		this.map = map;
	}

	@Override
	public void clear() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean containsKey(Object key) {
		return overlay.containsKey(key) || map.containsKey(key);
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
				return UnmodifiableMapUnion.this.size();
			}
		};
	}

	@Override
	public V get(Object key) {
		V value = overlay.get(key);
		if (value == null && !overlay.containsKey(key))
			value = map.get(key);
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
				return UnmodifiableMapUnion.this.size();
			}
		};
	}

	public Set<? extends K> overlayKeySet() {
		return overlay.keySet();
	}

	@Override
	public V put(K key, V value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	@Override
	public V remove(Object key) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		int immutableVisibleCount = 0;
		for (K key : map.keySet())
			if (!overlay.containsKey(key))
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
				return UnmodifiableMapUnion.this.size();
			}
		};
	}
}
