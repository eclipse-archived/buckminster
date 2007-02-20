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
import java.util.Set;

import org.eclipse.buckminster.core.common.model.IProperties;

/**
 * A wrapper that brings some sanety into {@link java.util.Properties}.
 *
 * @author Thomas Hallgren
 */
class PropertiesWrapper extends AbstractMap<String, String> implements IProperties
{
	class BackedEntry implements Map.Entry<String, String>
	{
		private final String m_key;

		public BackedEntry(String key)
		{
			m_key = key;
		}

		public String getKey()
		{
			return m_key;
		}

		public String getValue()
		{
			return m_properties.getProperty(m_key);
		}

		public String setValue(String value)
		{
			return (String)m_properties.setProperty(m_key, value);
		}
	}

	abstract class BackedIterator<X> implements Iterator<X>
	{
		private final Enumeration<?> m_names = m_properties.propertyNames();

		public boolean hasNext()
		{
			return m_names.hasMoreElements();
		}

		public String nextKey()
		{
			return (String)m_names.nextElement();
		}

		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}

	private final java.util.Properties m_properties;

	public PropertiesWrapper(java.util.Properties properties)
	{
		m_properties = properties;
	}

	@Override
	public boolean containsKey(Object key)
	{
		return (key instanceof String) ? (m_properties.getProperty((String)key) != null) : false;
	}

	@Override
	public Set<Entry<String, String>> entrySet()
	{
		return new AbstractSet<Entry<String, String>>()
		{
			@Override
			public Iterator<Entry<String, String>> iterator()
			{
				return new BackedIterator<Entry<String, String>>()
				{
					public Entry<String, String> next()
					{
						return new BackedEntry(this.nextKey());
					}
				};
			}

			@Override
			public int size()
			{
				return PropertiesWrapper.this.size();
			}
		};
	}

	@Override
	public String get(Object key)
	{
		return (key instanceof String) ? m_properties.getProperty((String)key) : null;
	}

	@Override
	public Set<String> keySet()
	{
		return new AbstractSet<String>()
		{
			@Override
			public Iterator<String> iterator()
			{
				return new BackedIterator<String>()
				{
					public String next()
					{
						return this.nextKey();
					}
				};
			}
			@Override
			public int size()
			{
				return PropertiesWrapper.this.size();
			}
		};
	}

	public Set<String> immutableKeySet()
	{
		return this.keySet();
	}

	public boolean isMutable(String key)
	{
		return true;
	}

	public Set<String> mutableKeySet()
	{
		return this.keySet();
	}

	@SuppressWarnings("unchecked")
	public Set<String> overlayKeySet()
	{
		return (Set<String>)(Set<?>)m_properties.keySet();
	}

	@Override
	public String put(String key, String value)
	{
		return (String)m_properties.setProperty(key, value);
	}

	public String put(String key, String value, boolean mutable)
	{
		if(!mutable)
			throw new UnsupportedOperationException("put immutable");
		return this.put(key, value);
	}

	public void setMutable(String key, boolean mutable) throws UnsupportedOperationException
	{
		if(!mutable)
			throw new UnsupportedOperationException("setMutable");
	}

	@Override
	public int size()
	{
		// The contained properties instance is mutable and can change
		// without us knowing.
		//
		int sz = 0;
		Enumeration<?> names = m_properties.propertyNames();
		try
		{
			for(;;)
			{
				names.nextElement();
				sz++;
			}
		}
		catch(NoSuchElementException e)
		{
		}
		return sz;
	}

	public void store(OutputStream out, String comments) throws IOException
	{
		BMProperties.store(this, out, comments);
	}

	public boolean supportsMutability()
	{
		return false;
	}

	@Override
	public Collection<String> values()
	{
		return new AbstractCollection<String>()
		{
			@Override
			public Iterator<String> iterator()
			{
				return new BackedIterator<String>()
				{
					public String next()
					{
						return m_properties.getProperty(this.nextKey());
					}
				};
			}
			@Override
			public int size()
			{
				return PropertiesWrapper.this.size();
			}
		};
	}
}
