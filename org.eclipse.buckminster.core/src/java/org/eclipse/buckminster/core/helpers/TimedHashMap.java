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
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author thhal
 */
public class TimedHashMap<K, V> implements Map<K, V> {
	public interface EvictionPolicy<EK, EV> {
		void evict(Entry<EK, EV> entry);
	}

	final class TimedEntry extends TimerTask implements Map.Entry<K, V> {
		private final K key;

		private V value;

		TimedEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		@Override
		public void run() {
			TimedEntry val = null;
			synchronized (map) {
				val = map.remove(key);
			}
			if (val != null && evictionPolicy != null)
				evictionPolicy.evict(val);
		}

		public V setValue(V value) {
			V old = value;
			this.remove();
			this.value = value;
			timer.schedule(this, keepAliveTime);
			return old;
		}

		void remove() {
			this.cancel();
			if (evictionPolicy != null)
				evictionPolicy.evict(this);
		}
	}

	private static final Timer timer = new Timer();

	private final long keepAliveTime;

	private final EvictionPolicy<K, V> evictionPolicy;

	private final HashMap<K, TimedEntry> map = new HashMap<K, TimedEntry>();

	public TimedHashMap(long keepAliveTime, EvictionPolicy<K, V> evictionPolicy) {
		this.keepAliveTime = keepAliveTime;
		this.evictionPolicy = evictionPolicy;
	}

	public void cancel(K key) {
		TimedEntry entry = map.get(key);
		if (entry != null)
			entry.cancel();
	}

	public void clear() {
		synchronized (map) {
			for (TimedEntry entry : map.values())
				entry.remove();
			map.clear();
		}
	}

	public boolean containsKey(Object key) {
		synchronized (map) {
			return map.containsKey(key);
		}
	}

	public boolean containsValue(Object value) {
		synchronized (map) {
			for (Entry<K, V> te : map.values()) {
				Object tv = te.getValue();
				if (value == null) {
					if (tv == null)
						return true;
				} else if (value.equals(tv))
					return true;
			}
		}
		return false;
	}

	public Set<Entry<K, V>> entrySet() {
		final Iterator<TimedEntry> entries = map.values().iterator();
		return new AbstractSet<Entry<K, V>>() {
			@Override
			public Iterator<Entry<K, V>> iterator() {
				return new Iterator<Entry<K, V>>() {

					public boolean hasNext() {
						return entries.hasNext();
					}

					public Entry<K, V> next() {
						return entries.next();
					}

					public void remove() {
						entries.remove();
					}
				};
			}

			@Override
			public int size() {
				return map.size();
			}
		};
	}

	public V get(Object key) {
		synchronized (map) {
			Entry<K, V> te = map.get(key);
			return te == null ? null : te.getValue();
		}
	}

	public boolean isEmpty() {
		return map.isEmpty();
	}

	public Set<K> keySet() {
		return map.keySet();
	}

	public V put(K key, V value) {
		V oldVal;
		TimedEntry entry = new TimedEntry(key, value);
		synchronized (map) {
			TimedEntry oldEntry = map.get(key);

			if (oldEntry != null) {
				oldVal = oldEntry.getValue();
				oldEntry.remove();
			} else
				oldVal = null;

			map.put(key, entry);
		}
		if (scheduleOnPut())
			timer.schedule(entry, keepAliveTime);
		return oldVal;
	}

	public void putAll(Map<? extends K, ? extends V> t) {
		for (Entry<? extends K, ? extends V> entry : t.entrySet())
			this.put(entry.getKey(), entry.getValue());
	}

	public V remove(Object key) {
		synchronized (map) {
			TimedEntry te = map.remove(key);
			if (te == null)
				return null;
			V oldVal = te.getValue();
			te.remove();
			return oldVal;
		}
	}

	public void schedule(K key) {
		TimedEntry entry = map.get(key);
		if (entry != null)
			timer.schedule(entry, keepAliveTime);
	}

	public boolean scheduleOnPut() {
		return true;
	}

	public int size() {
		return map.size();
	}

	public Collection<V> values() {
		final Iterator<TimedEntry> entries = map.values().iterator();
		return new AbstractCollection<V>() {
			@Override
			public Iterator<V> iterator() {
				return new Iterator<V>() {

					public boolean hasNext() {
						return entries.hasNext();
					}

					public V next() {
						return entries.next().getValue();
					}

					public void remove() {
						entries.remove();
					}
				};
			}

			@Override
			public int size() {
				return map.size();
			}
		};
	}
}
