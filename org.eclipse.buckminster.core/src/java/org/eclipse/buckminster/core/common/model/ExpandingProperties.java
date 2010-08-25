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
import java.util.Collections;
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
 * <p>
 * This class works just as a normal <code>Map&lt;String,String&gt;</code> but
 * with two additions:
 * <ul>
 * <li>Values are expanded using ant-style property expansion. The scope of the
 * expansion is the instance itself.</li>
 * <li>Any value can be declared immutable. When doing so, it is guaranteed that
 * the value cannot be changed or removed from the map.</li>
 * </ul>
 * </p>
 * 
 * @author Thomas Hallgren
 */
public class ExpandingProperties<T extends Object> implements IProperties<T>, IExpandingMap<String, T> {
	class EntryWrapper implements Entry<String, T> {
		private final Entry<String, ValueHolder<T>> entry;

		public EntryWrapper(Entry<String, ValueHolder<T>> entry) {
			this.entry = entry;
		}

		@Override
		public String getKey() {
			return entry.getKey();
		}

		@Override
		public T getValue() {
			T value = convertValue(entry.getValue(), 0);
			if (value != null)
				value = expand(ExpandingProperties.this, value, 0);
			return value;
		}

		@Override
		public synchronized T setValue(T value) {
			String key = entry.getKey();
			ValueHolder<T> vh = entry.getValue();
			Constant<T> constant = new Constant<T>(value);
			if (!(vh == null || vh.isMutable() || vh.equals(constant)))
				throw new ImmutablePropertyException(key);
			entry.setValue(constant);
			return convertValue(vh, 0);
		}
	}

	public static final int MAX_NESTING_DEPTH = 64;

	public static <T> Map<String, T> createUnmodifiableProperties(Map<String, T> aMap) {
		if (aMap == null || aMap.size() == 0)
			aMap = Collections.emptyMap();
		else
			aMap = Collections.unmodifiableMap(new ExpandingProperties<T>(aMap));
		return aMap;
	}

	public static <T> T expand(Map<String, ? extends Object> properties, T value, int nestingLevel) {
		return checkedExpand(properties, value, value, nestingLevel);
	}

	@SuppressWarnings("unchecked")
	private static <T> T checkedExpand(Map<String, ? extends Object> props, T topValue, T objVal, int recursionGuard) {
		if (!(objVal instanceof String))
			return objVal;

		if (recursionGuard > MAX_NESTING_DEPTH)
			throw new CircularExpansionException((String) topValue);

		String value = (String) objVal;
		StringBuilder bld = null;
		int fragmentStart = 0;
		int top = value.length();
		if (top < 4)
			return objVal;

		--top; // Last character is not of interest
		for (int idx = 0; idx < top; ++idx) {
			char c = value.charAt(idx);
			if (c != '$')
				continue;

			if (value.charAt(idx + 1) == '$') {
				// Let '$$' mean '$'
				//
				if (bld == null)
					bld = new StringBuilder();

				if (idx > fragmentStart)
					bld.append(value.substring(fragmentStart, idx));
				fragmentStart = ++idx;
				continue;
			}

			if (value.charAt(idx + 1) != '{' || idx + 3 >= top)
				//
				// Can't be a ${x} construction.
				//
				continue;

			int startPos = idx + 2;
			int endPos = parsePropertyName(value, startPos, true);
			if (endPos < 0)
				continue;

			String propKey = value.substring(startPos, endPos);

			// We must use the getUnexpandedProperty here if we don't want
			// to put the CircularExpansion check out of business.
			//
			Object propVal = (props instanceof ExpandingProperties<?>) ? ((ExpandingProperties<?>) props).getExpandedProperty(propKey,
					recursionGuard + 1) : props.get(propKey);

			if (propVal != null) {
				if (bld == null)
					bld = new StringBuilder();
				if (idx > fragmentStart)
					bld.append(value.substring(fragmentStart, idx));
				bld.append(checkedExpand(props, topValue, propVal, recursionGuard + 1));
				fragmentStart = endPos + 1;
			}
			idx = endPos;
		}

		if (bld != null) {
			++top; // Last character becomes interesting again
			if (fragmentStart < top)
				bld.append(value.substring(fragmentStart, top));
			value = bld.toString();
		}

		// This cast is safe since we were passed a String to begin with.
		//
		return (T) value;
	}

	private static int parsePropertyName(String source, int startIndex, boolean inResolve) {
		if (source == null)
			return -1;

		int top = source.length();
		if (startIndex >= top)
			return -1;

		int idx = startIndex;
		char c = source.charAt(idx++);
		if (!Character.isJavaIdentifierStart(c))
			return -1;

		while (idx < top) {
			char last = c;
			c = source.charAt(idx++);
			if (c == '.') {
				// Check that the name does not:
				// - have repeated dots, i.e. ".."
				// - end with a dot
				//
				if (last == '.' || idx == top || (inResolve && source.charAt(idx) == '}'))
					return -1;
			} else {
				if (inResolve && c == '}')
					return idx - 1;

				if (!Character.isJavaIdentifierPart(c))
					return -1; // Illegal character
			}
		}
		return top;
	}

	private final Map<String, ValueHolder<T>> map;

	public ExpandingProperties() {
		map = new HashMap<String, ValueHolder<T>>();
	}

	public ExpandingProperties(int size) {
		map = new HashMap<String, ValueHolder<T>>(size);
	}

	@SuppressWarnings("unchecked")
	public ExpandingProperties(Map<String, ? extends T> dflts) {
		Map<String, ValueHolder<T>> overlay = new HashMap<String, ValueHolder<T>>();
		if (dflts == null || dflts.size() == 0) {
			map = overlay;
			return;
		}

		Map<String, ValueHolder<T>> dfltMap;
		if (dflts instanceof ExpandingProperties<?>)
			dfltMap = ((ExpandingProperties<T>) dflts).map;
		else {
			dfltMap = new HashMap<String, ValueHolder<T>>(dflts.size());
			for (Map.Entry<String, ? extends T> de : dflts.entrySet()) {
				ValueHolder<T> vh = new Constant<T>(de.getValue());
				vh.setMutable(true);
				dfltMap.put(de.getKey(), vh);
			}
		}
		map = new MapUnion<String, ValueHolder<T>>(overlay, dfltMap);
	}

	@Override
	public void clear() {
		if (map.isEmpty())
			return;

		for (Map.Entry<String, ValueHolder<T>> ee : map.entrySet())
			if (!ee.getValue().isMutable())
				throw new ImmutablePropertyException(ee.getKey());

		map.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		return map.containsKey(key);
	}

	@Override
	public boolean containsValue(Object value) {
		return map.containsValue(value);
	}

	@Override
	public Set<Entry<String, T>> entrySet() {
		return new AbstractSet<Entry<String, T>>() {
			@Override
			public Iterator<Entry<String, T>> iterator() {
				return new Iterator<Entry<String, T>>() {
					private final Iterator<Entry<String, ValueHolder<T>>> itor = map.entrySet().iterator();

					@Override
					public boolean hasNext() {
						return itor.hasNext();
					}

					@Override
					public Entry<String, T> next() {
						return new EntryWrapper(itor.next());
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}

			@Override
			public int size() {
				return map.size();
			}
		};
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;

		return (o instanceof ExpandingProperties<?>) && map.equals(((ExpandingProperties<?>) o).map);
	}

	@Override
	public T get(Object key) {
		return key instanceof String ? getExpandedProperty((String) key, 0) : null;
	}

	@Override
	public T get(Object key, Map<String, T> expansionScope) {
		ValueHolder<T> vh = map.get(key);
		return vh == null ? null : vh.checkedGetValue(expansionScope, 0);
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	@Override
	public Set<String> immutableKeySet() {
		HashSet<String> immutableSet = new HashSet<String>();
		for (Map.Entry<String, ValueHolder<T>> me : map.entrySet()) {
			if (!me.getValue().isMutable())
				immutableSet.add(me.getKey());
		}
		return immutableSet;
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean isMutable(String key) {
		ValueHolder<T> v = map.get(key);
		return v == null || v.isMutable();
	}

	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Set<String> mutableKeySet() {
		HashSet<String> mutableSet = new HashSet<String>();
		for (Map.Entry<String, ValueHolder<T>> me : map.entrySet()) {
			if (me.getValue().isMutable())
				mutableSet.add(me.getKey());
		}
		return mutableSet;
	}

	@Override
	public Set<String> overlayKeySet() {
		return (map instanceof MapUnion<?, ?>) ? ((MapUnion<String, ValueHolder<T>>) map).overlayKeySet() : map.keySet();
	}

	@Override
	public T put(String key, T propVal) {
		return convertValue(setProperty(key, new Constant<T>(propVal)), 0);
	}

	@Override
	public T put(String key, T propVal, boolean mutable) {
		Constant<T> vh = new Constant<T>(propVal);
		vh.setMutable(mutable);
		return convertValue(setProperty(key, vh), 0);
	}

	@Override
	public void putAll(Map<? extends String, ? extends T> t) {
		putAll(t, false);
	}

	@SuppressWarnings("unchecked")
	public void putAll(Map<? extends String, ? extends T> t, boolean mutable) {
		if (t instanceof ExpandingProperties<?>) {
			// Defer expansion until access.
			//
			for (Map.Entry<String, ValueHolder<T>> ee : ((ExpandingProperties<T>) t).map.entrySet()) {
				ee.getValue().setMutable(mutable);
				setProperty(ee.getKey(), ee.getValue());
			}
		} else {
			for (Map.Entry<? extends String, ? extends T> ee : t.entrySet()) {
				ValueHolder<T> vh = new Constant<T>(ee.getValue());
				vh.setMutable(mutable);
				setProperty(ee.getKey(), vh);
			}
		}
	}

	@Override
	public T remove(Object key) {
		if (key instanceof String) {
			String strKey = (String) key;
			ValueHolder<T> vh = map.remove(strKey);
			if (vh != null) {
				if (!vh.isMutable()) {
					map.put(strKey, vh);
					throw new ImmutablePropertyException(strKey);
				}
				return vh.checkedGetValue(this, 0);
			}
		}
		return null;
	}

	@Override
	public void setMutable(String key, boolean flag) {
		ValueHolder<T> v = map.get(key);
		if (v != null)
			v.setMutable(flag);
	}

	public ValueHolder<T> setProperty(String key, ValueHolder<T> propertyHolder) {
		ValueHolder<T> v = map.put(key, propertyHolder);
		if (!(v == null || v.isMutable() || v.equals(propertyHolder))) {
			map.put(key, v);
			throw new ImmutablePropertyException(key);
		}
		return v;
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public void store(OutputStream out, String comments) throws IOException {
		BMProperties.store(this, out, comments);
	}

	@Override
	public boolean supportsMutability() {
		return true;
	}

	@Override
	public Collection<T> values() {
		return new AbstractCollection<T>() {
			@Override
			public Iterator<T> iterator() {
				return new Iterator<T>() {
					private final Iterator<ValueHolder<T>> itor = map.values().iterator();

					@Override
					public boolean hasNext() {
						return itor.hasNext();
					}

					@Override
					public T next() {
						T value = convertValue(itor.next(), 0);
						if (value != null)
							value = expand(ExpandingProperties.this, value, 0);
						return value;
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}

			@Override
			public int size() {
				return map.size();
			}
		};
	}

	void emitProperties(ContentHandler handler, String namespace, String prefix, boolean includeDefaults) throws SAXException {
		String plName = "property"; //$NON-NLS-1$
		String pqName = Utils.makeQualifiedName(prefix, plName);
		String pelName = "propertyElement"; //$NON-NLS-1$
		String peqName = Utils.makeQualifiedName(prefix, pelName);
		AttributesImpl attrs = new AttributesImpl();

		TreeSet<String> sorted = new TreeSet<String>();
		if (includeDefaults) {
			for (String key : keySet())
				sorted.add(key);
		} else {
			for (String name : overlayKeySet())
				sorted.add(name);
		}

		for (String name : sorted) {
			ValueHolder<T> value = map.get(name);
			if (value == null)
				continue;

			if (includeDefaults && value instanceof Constant<?>) {
				// We still don't include unmodified system properties.
				//
				String sysValue = System.getProperty(name);
				if (sysValue != null && sysValue.equals(value))
					continue;
			}

			attrs.clear();
			Utils.addAttribute(attrs, "key", name); //$NON-NLS-1$
			if (value.isMutable())
				Utils.addAttribute(attrs, "mutable", "true"); //$NON-NLS-1$ //$NON-NLS-2$
			if (value instanceof Constant<?>) {
				Utils.addAttribute(attrs, "value", value.toString()); //$NON-NLS-1$
				handler.startElement(namespace, plName, pqName, attrs);
				handler.endElement(namespace, plName, pqName);
			} else {
				handler.startElement(namespace, pelName, peqName, attrs);
				value.toSax(handler, namespace, prefix, value.getDefaultTag());
				handler.endElement(namespace, pelName, peqName);
			}
		}
	}

	T getExpandedProperty(String key, int recursionGuard) {
		return convertValue(map.get(key), recursionGuard);
	}

	private T convertValue(ValueHolder<T> vh, int recursionGuard) {
		return vh == null ? null : vh.checkedGetValue(this, recursionGuard);
	}
}
