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
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.eclipse.buckminster.model.common.CommonFactory;
import org.eclipse.buckminster.model.common.Constant;
import org.eclipse.buckminster.model.common.Value;
import org.eclipse.buckminster.model.common.impl.ValueImpl;

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
public class ExpandingProperties implements IProperties {
	class EntryWrapper implements Entry<String, String> {
		private final Entry<String, Value> entry;

		public EntryWrapper(Entry<String, Value> entry) {
			this.entry = entry;
		}

		@Override
		public String getKey() {
			return entry.getKey();
		}

		@Override
		public String getValue() {
			String value = convertValue(entry.getValue(), 0);
			if (value != null)
				value = expand(ExpandingProperties.this, value, 0);
			return value;
		}

		@Override
		public synchronized String setValue(String value) {
			String key = entry.getKey();
			Value vh = entry.getValue();
			Constant constant = CommonFactory.eINSTANCE.createConstant();
			constant.setValue(value);
			if (!(vh == null || vh.isMutable() || vh.equals(constant)))
				throw new ImmutablePropertyException(key);

			entry.setValue(constant);
			return convertValue(vh, 0);
		}
	}

	public static final int MAX_NESTING_DEPTH = 64;

	public static Map<String, String> createUnmodifiableProperties(Map<String, String> aMap) {
		if (aMap == null || aMap.size() == 0)
			aMap = Collections.emptyMap();
		else
			aMap = Collections.unmodifiableMap(new ExpandingProperties(aMap));
		return aMap;
	}

	public static String expand(Map<String, String> properties, String value, int nestingLevel) {
		return checkedExpand(properties, value, value, nestingLevel);
	}

	private static String checkedExpand(Map<String, String> props, String topValue, String value, int recursionGuard) {
		if (recursionGuard > MAX_NESTING_DEPTH)
			throw new CircularExpansionException(topValue);

		if (value == null)
			return null;

		StringBuilder bld = null;
		int fragmentStart = 0;
		int top = value.length();
		if (top < 4)
			return value;

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
			String propVal = (props instanceof ExpandingProperties) ? ((ExpandingProperties) props).getExpandedProperty(propKey, recursionGuard + 1)
					: props.get(propKey);

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
		return value;
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

	private final Map<String, Value> map;

	public ExpandingProperties() {
		map = new HashMap<String, Value>();
	}

	public ExpandingProperties(int size) {
		map = new HashMap<String, Value>(size);
	}

	public ExpandingProperties(Map<String, String> dflts) {
		Map<String, Value> overlay = new HashMap<String, Value>();
		if (dflts == null || dflts.size() == 0) {
			map = overlay;
			return;
		}

		Map<String, Value> dfltMap;
		if (dflts instanceof ExpandingProperties)
			dfltMap = ((ExpandingProperties) dflts).map;
		else {
			dfltMap = new HashMap<String, Value>(dflts.size());
			for (Map.Entry<String, String> de : dflts.entrySet()) {
				Constant constant = CommonFactory.eINSTANCE.createConstant();
				constant.setValue(de.getValue());
				constant.setMutable(true);
				dfltMap.put(de.getKey(), constant);
			}
		}
		map = new MapUnion<String, Value>(overlay, dfltMap);
	}

	public ExpandingProperties(Map<String, Value> map, Map<String, Value> overlay) {
		if (overlay != null)
			map = new MapUnion<String, Value>(overlay, map);
		this.map = map;
	}

	@Override
	public void clear() {
		if (map.isEmpty())
			return;

		for (Map.Entry<String, Value> ee : map.entrySet())
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
	public Set<Entry<String, String>> entrySet() {
		return new AbstractSet<Entry<String, String>>() {

			@Override
			public Iterator<Entry<String, String>> iterator() {
				return new Iterator<Entry<String, String>>() {
					private final Iterator<Entry<String, Value>> itor = map.entrySet().iterator();

					@Override
					public boolean hasNext() {
						return itor.hasNext();
					}

					@Override
					public Entry<String, String> next() {
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

		return (o instanceof ExpandingProperties) && map.equals(((ExpandingProperties) o).map);
	}

	@Override
	public String get(Object key) {
		return key instanceof String ? getExpandedProperty((String) key, 0) : null;
	}

	public String getExpandedProperty(String key, int recursionGuard) {
		return convertValue(map.get(key), recursionGuard);
	}

	public Value getInternalValue(String key) {
		return map.get(key);
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	@Override
	public Set<String> immutableKeySet() {
		HashSet<String> immutableSet = new HashSet<String>();
		for (Map.Entry<String, Value> me : map.entrySet()) {
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
		Value v = map.get(key);
		return v == null || v.isMutable();
	}

	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Set<String> mutableKeySet() {
		HashSet<String> mutableSet = new HashSet<String>();
		for (Map.Entry<String, Value> me : map.entrySet()) {
			if (me.getValue().isMutable())
				mutableSet.add(me.getKey());
		}
		return mutableSet;
	}

	@Override
	public Set<String> overlayKeySet() {
		return (map instanceof MapUnion<?, ?>) ? ((MapUnion<String, Value>) map).overlayKeySet() : map.keySet();
	}

	@Override
	public String put(String key, String propVal) {
		Constant constant = CommonFactory.eINSTANCE.createConstant();
		constant.setValue(propVal);
		return convertValue(setProperty(key, constant), 0);
	}

	@Override
	public String put(String key, String propVal, boolean mutable) {
		Constant constant = CommonFactory.eINSTANCE.createConstant();
		constant.setValue(propVal);
		constant.setMutable(mutable);
		return convertValue(setProperty(key, constant), 0);
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> t) {
		putAll(t, false);
	}

	public void putAll(Map<? extends String, ? extends String> t, boolean mutable) {
		if (t instanceof ExpandingProperties) {
			// Defer expansion until access.
			//
			for (Map.Entry<String, Value> ee : ((ExpandingProperties) t).map.entrySet()) {
				ee.getValue().setMutable(mutable);
				setProperty(ee.getKey(), ee.getValue());
			}
		} else {
			for (Map.Entry<? extends String, ? extends String> ee : t.entrySet()) {
				Constant constant = CommonFactory.eINSTANCE.createConstant();
				constant.setValue(ee.getValue());
				constant.setMutable(mutable);
				setProperty(ee.getKey(), constant);
			}
		}
	}

	@Override
	public String remove(Object key) {
		if (key instanceof String) {
			String strKey = (String) key;
			Value vh = map.remove(strKey);
			if (vh != null) {
				if (!vh.isMutable()) {
					map.put(strKey, vh);
					throw new ImmutablePropertyException(strKey);
				}
				return ((ValueImpl) vh).checkedGetValue(this, 0);
			}
		}
		return null;
	}

	@Override
	public void setMutable(String key, boolean flag) {
		Value v = map.get(key);
		if (v != null)
			v.setMutable(flag);
	}

	public Value setProperty(String key, Value propertyHolder) {
		Value v = map.put(key, propertyHolder);
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
	public Collection<String> values() {
		return new AbstractCollection<String>() {

			@Override
			public Iterator<String> iterator() {
				return new Iterator<String>() {
					private final Iterator<Value> itor = map.values().iterator();

					@Override
					public boolean hasNext() {
						return itor.hasNext();
					}

					@Override
					public String next() {
						String value = convertValue(itor.next(), 0);
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

	private String convertValue(Value vh, int recursionGuard) {
		return vh == null ? null : ((ValueImpl) vh).checkedGetValue(this, recursionGuard);
	}
}
