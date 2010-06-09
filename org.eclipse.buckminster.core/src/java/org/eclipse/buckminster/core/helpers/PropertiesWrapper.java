/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.helpers;

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

import org.eclipse.buckminster.core.common.model.IProperties;

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

		@Override
		public String getKey() {
			return key;
		}

		@Override
		public String getValue() {
			return getProperties().getProperty(key);
		}

		@Override
		public String setValue(String value) {
			return (String) getProperties().setProperty(key, value);
		}
	}

	abstract class BackedIterator<X> implements Iterator<X> {
		private final Enumeration<?> names = getProperties().propertyNames();

		@Override
		public boolean hasNext() {
			return names.hasMoreElements();
		}

		public String nextKey() {
			return (String) names.nextElement();
		}

		@Override
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
					@Override
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

	@Override
	public Set<String> immutableKeySet() {
		return this.keySet();
	}

	@Override
	public boolean isMutable(String key) {
		return true;
	}

	@Override
	public Set<String> keySet() {
		return new AbstractSet<String>() {
			@Override
			public Iterator<String> iterator() {
				return new BackedIterator<String>() {
					@Override
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

	@Override
	public Set<String> mutableKeySet() {
		return this.keySet();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Set<String> overlayKeySet() {
		return (Set<String>) (Set<?>) getProperties().keySet();
	}

	@Override
	public String put(String key, String value) {
		return (String) getProperties().setProperty(key, value);
	}

	@Override
	public String put(String key, String value, boolean mutable) {
		if (!mutable)
			throw new UnsupportedOperationException("put immutable"); //$NON-NLS-1$
		return this.put(key, value);
	}

	@Override
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

	@Override
	public void store(OutputStream out, String comments) throws IOException {
		BMProperties.store(this, out, comments);
	}

	@Override
	public boolean supportsMutability() {
		return false;
	}

	@Override
	public Collection<String> values() {
		return new AbstractCollection<String>() {
			@Override
			public Iterator<String> iterator() {
				return new BackedIterator<String>() {
					@Override
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
