/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.model.common.util;

import java.io.IOException;
import java.io.OutputStream;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

/**
 * A wrapper that brings some sanety into {@link java.util.Properties}.
 * 
 * @author Thomas Hallgren
 */
abstract class PropertiesWrapper extends AbstractMap<String, String> implements IProperties<String> {
	class BackedEntry implements Map.Entry<String, String> {
		private final String key;

		public BackedEntry(String key) {
			this.key = key;
		}

		public String getKey() {
			return key;
		}

		public String getValue() {
			return getProperties().getProperty(key);
		}

		public String setValue(String value) {
			return (String) getProperties().setProperty(key, value);
		}
	}

	abstract class BackedIterator<X> implements Iterator<X> {
		private final Enumeration<?> names = getProperties().propertyNames();

		public boolean hasNext() {
			return names.hasMoreElements();
		}

		public String nextKey() {
			return (String) names.nextElement();
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

	@Override
	public boolean containsKey(Object key) {
		return (key instanceof String) ? (getProperties().getProperty((String) key) != null) : false;
	}

	@Override
	public Set<Entry<String, String>> entrySet() {
		return new AbstractSet<Entry<String, String>>() {

			@Override
			public Iterator<Entry<String, String>> iterator() {
				return new BackedIterator<Entry<String, String>>() {

					public Entry<String, String> next() {
						return new BackedEntry(this.nextKey());
					}
				};
			}

			@Override
			public int size() {
				return PropertiesWrapper.this.size();
			}
		};
	}

	@Override
	public String get(Object key) {
		return (key instanceof String) ? getProperties().getProperty((String) key) : null;
	}

	public Set<String> immutableKeySet() {
		return this.keySet();
	}

	public boolean isMutable(String key) {
		return true;
	}

	@Override
	public Set<String> keySet() {
		return new AbstractSet<String>() {

			@Override
			public Iterator<String> iterator() {
				return new BackedIterator<String>() {

					public String next() {
						return this.nextKey();
					}
				};
			}

			@Override
			public int size() {
				return PropertiesWrapper.this.size();
			}
		};
	}

	public Set<String> mutableKeySet() {
		return this.keySet();
	}

	@SuppressWarnings("unchecked")
	public Set<String> overlayKeySet() {
		return (Set<String>) (Set<?>) getProperties().keySet();
	}

	@Override
	public String put(String key, String value) {
		return (String) getProperties().setProperty(key, value);
	}

	public String put(String key, String value, boolean mutable) {
		if (!mutable)
			throw new UnsupportedOperationException("put immutable"); //$NON-NLS-1$
		return this.put(key, value);
	}

	public void setMutable(String key, boolean mutable) throws UnsupportedOperationException {
		if (!mutable)
			throw new UnsupportedOperationException("setMutable"); //$NON-NLS-1$
	}

	@Override
	public int size() {
		// The contained properties instance is mutable and can change
		// without us knowing.
		//
		int sz = 0;
		Enumeration<?> names = getProperties().propertyNames();
		try {
			for (;;) {
				names.nextElement();
				sz++;
			}
		} catch (NoSuchElementException e) {
		}
		return sz;
	}

	public void store(OutputStream out, String comments) throws IOException {
		BMProperties.store(this, out, comments);
	}

	public boolean supportsMutability() {
		return false;
	}

	@Override
	public Collection<String> values() {
		return new AbstractCollection<String>() {

			@Override
			public Iterator<String> iterator() {
				return new BackedIterator<String>() {

					public String next() {
						return getProperties().getProperty(this.nextKey());
					}
				};
			}

			@Override
			public int size() {
				return PropertiesWrapper.this.size();
			}
		};
	}

	protected abstract Properties getProperties();
}
