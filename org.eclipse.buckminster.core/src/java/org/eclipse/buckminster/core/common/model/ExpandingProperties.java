/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.io.IOException;
import java.io.OutputStream;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.helpers.MapUnion;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * <p>This class works just as a normal <code>Map&lt;String,String&gt;</code> but with two additions:<ul>
 * <li>Values are expanded using ant-style property expansion. The scope of the expansion
 * is the instance itself.</li>
 * <li>Any value can be declared immutable. When doing so, it is guaranteed that the
 * value cannot be changed or removed from the map.</li>
 * </ul></p>
 * 
 * @author Thomas Hallgren
 */
public class ExpandingProperties implements IProperties
{
	class EntryWrapper implements Entry<String, String>
	{
		private final Entry<String, ValueHolder> m_entry;

		public EntryWrapper(Entry<String, ValueHolder> entry)
		{
			m_entry = entry;
		}

		public String getKey()
		{
			return m_entry.getKey();
		}

		public String getValue()
		{
			String value = convertValue(m_entry.getValue(), 0);
			if(value != null)
				value = expand(ExpandingProperties.this, value, 0);
			return value;
		}

		public synchronized String setValue(String value)
		{
			String key = m_entry.getKey();
			ValueHolder vh = m_entry.getValue();
			Constant constant = new Constant(value);
			if(!(vh == null || vh.isMutable() || vh.equals(constant)))
				throw new ImmutablePropertyException(key);
			m_entry.setValue(constant);
			return convertValue(vh, 0);
		}
	}

	public static final int MAX_NESTING_DEPTH = 64;

	public static final String expand(Map<String, String> properties, String value, int nestingLevel)
	{
		return checkedExpand(properties, value, value, nestingLevel);
	}
	private final Map<String, ValueHolder> m_map;

	public ExpandingProperties()
	{
		m_map = new HashMap<String, ValueHolder>();
	}

	public ExpandingProperties(int size)
	{
		m_map = new HashMap<String, ValueHolder>(size);
	}

	public ExpandingProperties(Map<String, String> dflts)
	{
		Map<String, ValueHolder> overlay = new HashMap<String, ValueHolder>();
		if(dflts == null || dflts.size() == 0)
		{
			m_map = overlay;
			return;
		}

		Map<String,ValueHolder> dfltMap;
		if(dflts instanceof ExpandingProperties)
			dfltMap = ((ExpandingProperties)dflts).m_map;
		else
		{
			dfltMap = new HashMap<String,ValueHolder>(dflts.size());
			for(Map.Entry<String, String> de : dflts.entrySet())
			{
				ValueHolder vh = new Constant(de.getValue());
				vh.setMutable(true);
				dfltMap.put(de.getKey(), vh);
			}
		}
		m_map = new MapUnion<String, ValueHolder>(overlay, dfltMap);
	}

	public void clear()
	{
		if(m_map.isEmpty())
			return;
		
		for(Map.Entry<String, ValueHolder> ee : m_map.entrySet())
			if(!ee.getValue().isMutable())
				throw new ImmutablePropertyException(ee.getKey());

		m_map.clear();
	}

	public boolean containsKey(Object key)
	{
		return m_map.containsKey(key);
	}

	public boolean containsValue(Object value)
	{
		return m_map.containsValue(value);
	}

	public Set<Entry<String, String>> entrySet()
	{
		return new AbstractSet<Entry<String, String>>()
		{
			@Override
			public Iterator<Entry<String, String>> iterator()
			{
				return new Iterator<Entry<String, String>>()
				{
					private final Iterator<Entry<String, ValueHolder>> m_itor = m_map.entrySet().iterator();

					public boolean hasNext()
					{
						return m_itor.hasNext();
					}

					public Entry<String, String> next()
					{
						return new EntryWrapper(m_itor.next());
					}

					public void remove()
					{
						throw new UnsupportedOperationException();
					}
				};
			}

			@Override
			public int size()
			{
				return m_map.size();
			}
		};
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		return (o instanceof ExpandingProperties) && m_map.equals(((ExpandingProperties)o).m_map);
	}

	public String get(Object key)
	{
		return key instanceof String
			? getExpandedProperty((String)key, 0)
			: null;
	}

	@Override
	public int hashCode()
	{
		return m_map.hashCode();
	}

	public Set<String> immutableKeySet()
	{
		HashSet<String> immutableSet = new HashSet<String>();
		for(Map.Entry<String, ValueHolder> me : m_map.entrySet())
		{
			if(!me.getValue().isMutable())
				immutableSet.add(me.getKey());
		}
		return immutableSet;
	}

	public boolean isEmpty()
	{
		return m_map.isEmpty();
	}

	public boolean isMutable(String key)
	{
		ValueHolder v = m_map.get(key);
		return v == null || v.isMutable();
	}

	public Set<String> keySet()
	{
		return m_map.keySet();
	}

	public Set<String> mutableKeySet()
	{
		HashSet<String> mutableSet = new HashSet<String>();
		for(Map.Entry<String, ValueHolder> me : m_map.entrySet())
		{
			if(me.getValue().isMutable())
				mutableSet.add(me.getKey());
		}
		return mutableSet;
	}

	public Set<String> overlayKeySet()
	{
		return (m_map instanceof MapUnion)
			? ((MapUnion<String, ValueHolder>)m_map).overlayKeySet()
			: m_map.keySet();
	}

	public String put(String key, String propVal)
	{
		return convertValue(setProperty(key, new Constant(propVal)), 0);
	}

	public String put(String key, String propVal, boolean mutable)
	{
		Constant vh = new Constant(propVal);
		vh.setMutable(mutable);
		return convertValue(setProperty(key, vh), 0);
	}

	public void putAll(Map<? extends String, ? extends String> t)
	{
		putAll(t, false);
	}

	public void putAll(Map<? extends String, ? extends String> t, boolean mutable)
	{
		if(t instanceof ExpandingProperties)
		{
			// Defer expansion until access.
			//
			for(Map.Entry<String, ValueHolder> ee : ((ExpandingProperties)t).m_map.entrySet())
			{
				ee.getValue().setMutable(mutable);
				setProperty(ee.getKey(), ee.getValue());
			}
		}
		else
		{
			for(Map.Entry<? extends String, ? extends String> ee : t.entrySet())
			{
				ValueHolder vh = new Constant(ee.getValue());
				vh.setMutable(mutable);
				setProperty(ee.getKey(), vh);
			}
		}
	}

	public String remove(Object key)
	{
		if(key instanceof String)
		{
			String strKey = (String)key;
			ValueHolder vh = m_map.remove(strKey);
			if(vh != null)
			{
				if(!vh.isMutable())
				{
					m_map.put(strKey, vh);
					throw new ImmutablePropertyException(strKey);
				}
				return vh.checkedGetValue(this, 0);
			}
		}
		return null;
	}

	public void setMutable(String key, boolean flag)
	{
		ValueHolder v = m_map.get(key);
		if(v != null)
			v.setMutable(flag);
	}

	public ValueHolder setProperty(String key, ValueHolder propertyHolder)
	{
		ValueHolder v = m_map.put(key, propertyHolder);
		if(!(v == null || v.isMutable() || v.equals(propertyHolder)))
		{
			m_map.put(key, v);
			throw new ImmutablePropertyException(key);
		}
		return v;
	}

	public int size()
	{
		return m_map.size();
	}

	public void store(OutputStream out, String comments) throws IOException
	{
		BMProperties.store(this, out, comments);
	}

	public boolean supportsMutability()
	{
		return true;
	}

	public Collection<String> values()
	{
		return new AbstractCollection<String>()
		{
			@Override
			public Iterator<String> iterator()
			{
				return new Iterator<String>()
				{
					private final Iterator<ValueHolder> m_itor = m_map.values().iterator();

					public boolean hasNext()
					{
						return m_itor.hasNext();
					}

					public String next()
					{
						String value = convertValue(m_itor.next(), 0);
						if(value != null)
							value = expand(ExpandingProperties.this, value, 0);
						return value;
					}

					public void remove()
					{
						throw new UnsupportedOperationException();
					}
				};
			}

			@Override
			public int size()
			{
				return m_map.size();
			}
		};
	}

	void emitProperties(ContentHandler handler, String namespace, String prefix, boolean includeDefaults) throws SAXException
	{
		String plName = "property";
		String pqName = Utils.makeQualifiedName(prefix, plName);
		String pelName = "propertyElement";
		String peqName = Utils.makeQualifiedName(prefix, pelName);
		AttributesImpl attrs = new AttributesImpl();

		TreeSet<String> sorted = new TreeSet<String>();
		if(includeDefaults)
		{
			for(String key : keySet())
				sorted.add(key);
		}
		else
		{
			for(String name : overlayKeySet())
				sorted.add(name);
		}

		for(String name : sorted)
		{
			ValueHolder value = m_map.get(name);
			if(value == null)
				continue;

			if(includeDefaults && value instanceof Constant)
			{
				// We still don't include unmodified system properties.
				//
				String sysValue = System.getProperty(name);
				if(sysValue != null && sysValue.equals(value))
					continue;
			}

			attrs.clear();
			Utils.addAttribute(attrs, "key", name);
			if(value.isMutable())
				Utils.addAttribute(attrs, "mutable", "true");
			if(value instanceof Constant)
			{
				Utils.addAttribute(attrs, "value", value.toString());
				handler.startElement(namespace, plName, pqName, attrs);
				handler.endElement(namespace, plName, pqName);
			}
			else
			{
				handler.startElement(namespace, pelName, peqName, attrs);
				value.toSax(handler, namespace, prefix, value.getDefaultTag());
				handler.endElement(namespace, pelName, peqName);
			}
		}
	}

	private static String checkedExpand(Map<String, String> props, String topValue, String value, int recursionGuard)
	{
		if(value == null)
			return null;

		if(recursionGuard > MAX_NESTING_DEPTH)
			throw new CircularExpansionException(topValue);

		StringBuilder bld = null;
		int fragmentStart = 0;
		int top = value.length();
		if(top < 4)
			return value;

		--top; // Last character is not of interest
		for(int idx = 0; idx < top; ++idx)
		{
			char c = value.charAt(idx);
			if(c != '$')
				continue;

			if(value.charAt(idx + 1) == '$')
			{
				// Let '$$' mean '$'
				//
				if(bld == null)
					bld = new StringBuilder();

				if(idx > fragmentStart)
					bld.append(value.substring(fragmentStart, idx));
				fragmentStart = ++idx;
				continue;
			}

			if(value.charAt(idx + 1) != '{' || idx + 3 >= top)
				//
				// Can't be a ${x} construction.
				//
				continue;

			int startPos = idx + 2;
			int endPos = parsePropertyName(value, startPos, true);
			if(endPos < 0)
				continue;

			String propKey = value.substring(startPos, endPos);

			// We must use the getUnexpandedProperty here if we don't want
			// to put the CircularExpansion check out of business.
			//
			String propVal = (props instanceof ExpandingProperties)
					? ((ExpandingProperties)props).getExpandedProperty(propKey, recursionGuard + 1)
					: props.get(propKey);

			if(propVal != null)
			{
				if(bld == null)
					bld = new StringBuilder();
				if(idx > fragmentStart)
					bld.append(value.substring(fragmentStart, idx));
				bld.append(checkedExpand(props, topValue, propVal, recursionGuard + 1));
				fragmentStart = endPos + 1;
			}
			idx = endPos + 1;
		}

		if(bld != null)
		{
			++top; // Last character becomes interesting again
			if(fragmentStart < top)
				bld.append(value.substring(fragmentStart, top));
			return bld.toString();
		}

		// No substitution occured
		//
		return value;
	}

	private static int parsePropertyName(String source, int startIndex, boolean inResolve)
	{
		if(source == null)
			return -1;

		int top = source.length();
		if(startIndex >= top)
			return -1;

		int idx = startIndex;
		char c = source.charAt(idx++);
		if(!Character.isJavaIdentifierStart(c))
			return -1;

		while(idx < top)
		{
			char last = c;
			c = source.charAt(idx++);
			if(c == '.')
			{
				// Check that the name does not:
				// - have repeated dots, i.e. ".."
				// - end with a dot
				//
				if(last == '.' || idx == top || (inResolve && source.charAt(idx) == '}'))
					return -1;
			}
			else
			{
				if(inResolve && c == '}')
					return idx - 1;

				if(!Character.isJavaIdentifierPart(c))
					return -1; // Illegal character
			}
		}
		return top;
	}

	private String convertValue(ValueHolder vh, int recursionGuard)
	{
		return vh == null ? null : vh.checkedGetValue(this, recursionGuard);
	}

	String getExpandedProperty(String key, int recursionGuard)
	{
		return convertValue(m_map.get(key), recursionGuard);
	}
}
