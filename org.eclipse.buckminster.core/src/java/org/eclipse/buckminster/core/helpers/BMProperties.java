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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.common.model.IProperties;

public class BMProperties implements IProperties<String> {
	private static final IProperties<String> systemProperties = new PropertiesWrapper() {
		@Override
		protected Properties getProperties() {
			return System.getProperties();
		}
	};

	/** A table of hex digits */
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

	public static IProperties<String> getSystemProperties() {
		return systemProperties;
	}

	public static void store(Map<String, ? extends Object> props, OutputStream out, String comments) throws IOException {
		BufferedWriter awriter;
		awriter = new BufferedWriter(new OutputStreamWriter(out, "8859_1")); //$NON-NLS-1$
		if (comments != null)
			writeln(awriter, "#" + comments); //$NON-NLS-1$
		writeln(awriter, "#" + new Date().toString()); //$NON-NLS-1$
		synchronized (props) {
			for (Entry<String, ? extends Object> e : props.entrySet()) {
				String key = e.getKey();
				key = saveConvert(key, true);

				/*
				 * No need to escape embedded and trailing spaces for value,
				 * hence pass false to flag.
				 */
				writeln(awriter, key + "=" + saveConvert(e.getValue(), false)); //$NON-NLS-1$
			}
		}
		awriter.flush();
	}

	private static String saveConvert(Object value, boolean escapeSpace) {
		String theString = value.toString();
		int len = theString.length();
		int bufLen = len * 2;
		if (bufLen < 0) {
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for (int x = 0; x < len; x++) {
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if ((aChar > 61) && (aChar < 127)) {
				if (aChar == '\\') {
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}
			switch (aChar) {
				case ' ':
					if (x == 0 || escapeSpace)
						outBuffer.append('\\');
					outBuffer.append(' ');
					break;
				case '\t':
					outBuffer.append('\\');
					outBuffer.append('t');
					break;
				case '\n':
					outBuffer.append('\\');
					outBuffer.append('n');
					break;
				case '\r':
					outBuffer.append('\\');
					outBuffer.append('r');
					break;
				case '\f':
					outBuffer.append('\\');
					outBuffer.append('f');
					break;
				case '=': // Fall through
				case '#': // Fall through
				case '!':
					outBuffer.append('\\');
					outBuffer.append(aChar);
					break;
				default:
					if ((aChar < 0x0020) || (aChar > 0x007e)) {
						outBuffer.append('\\');
						outBuffer.append('u');
						outBuffer.append(toHex((aChar >> 12) & 0xF));
						outBuffer.append(toHex((aChar >> 8) & 0xF));
						outBuffer.append(toHex((aChar >> 4) & 0xF));
						outBuffer.append(toHex(aChar & 0xF));
					} else {
						outBuffer.append(aChar);
					}
			}
		}
		return outBuffer.toString();
	}

	/**
	 * Convert a nibble to a hex character
	 * 
	 * @param nibble
	 *            the nibble to convert.
	 */
	private static char toHex(int nibble) {
		return hexDigit[(nibble & 0xF)];
	}

	private static void writeln(BufferedWriter bw, String s) throws IOException {
		bw.write(s);
		bw.newLine();
	}

	private final Map<String, String> map;

	public BMProperties() {
		map = new HashMap<String, String>();
	}

	public BMProperties(InputStream inStream) throws IOException {
		final Properties loader = new Properties();
		loader.load(inStream);
		map = new PropertiesWrapper() {
			@Override
			protected Properties getProperties() {
				return loader;
			}
		};
	}

	public BMProperties(Map<String, String> defaultProps) {
		Map<String, String> props = new HashMap<String, String>();
		if (defaultProps != null)
			props = new MapUnion<String, String>(props, defaultProps);
		map = props;
	}

	public BMProperties(Map<String, String> mutableProps, Map<String, String> defaultProps) {
		if (defaultProps != null)
			mutableProps = new MapUnion<String, String>(mutableProps, defaultProps);
		map = mutableProps;
	}

	public BMProperties(final Properties defaultProps) {
		map = new PropertiesWrapper() {
			@Override
			protected Properties getProperties() {
				return defaultProps;
			}
		};
	}

	@Override
	public void clear() {
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
		return map.entrySet();
	}

	@Override
	public boolean equals(Object o) {
		return this == o || (o instanceof Map<?, ?> && o.equals(map));
	}

	@Override
	public String get(Object key) {
		return map.get(key);
	}

	@Override
	public int hashCode() {
		return map.hashCode();
	}

	@Override
	public Set<String> immutableKeySet() {
		return (map instanceof IProperties<?>) ? ((IProperties<String>) map).immutableKeySet() : map.keySet();
	}

	@Override
	public boolean isEmpty() {
		return map.isEmpty();
	}

	@Override
	public boolean isMutable(String key) {
		return (map instanceof IProperties<?>) ? ((IProperties<String>) map).isMutable(key) : true;
	}

	@Override
	public Set<String> keySet() {
		return map.keySet();
	}

	@Override
	public Set<String> mutableKeySet() {
		return (map instanceof IProperties<?>) ? ((IProperties<String>) map).mutableKeySet() : map.keySet();
	}

	@Override
	public Set<String> overlayKeySet() {
		if (map instanceof IProperties<?>)
			return ((IProperties<String>) map).overlayKeySet();
		if (map instanceof MapUnion<?, ?>)
			return ((MapUnion<String, String>) map).overlayKeySet();
		return map.keySet();
	}

	@Override
	public String put(String key, String value) {
		return map.put(key, value);
	}

	@Override
	public String put(String key, String value, boolean mutable) {
		if (map instanceof IProperties<?>)
			return ((IProperties<String>) map).put(key, value, mutable);
		if (!mutable)
			throw new UnsupportedOperationException(Messages.Put_immutable);
		return map.put(key, value);
	}

	@Override
	public void putAll(Map<? extends String, ? extends String> t) {
		map.putAll(t);
	}

	@Override
	public String remove(Object key) {
		return map.remove(key);
	}

	@Override
	public void setMutable(String key, boolean flag) throws UnsupportedOperationException {
		if (map instanceof IProperties<?>)
			((IProperties<String>) map).setMutable(key, flag);
		else if (!flag)
			throw new UnsupportedOperationException(Messages.SetMutable);
	}

	@Override
	public int size() {
		return map.size();
	}

	@Override
	public void store(OutputStream out, String comments) throws IOException {
		store(map, out, comments);
	}

	@Override
	public boolean supportsMutability() {
		return (map instanceof IProperties<?>) ? ((IProperties<String>) map).supportsMutability() : false;
	}

	@Override
	public Collection<String> values() {
		return map.values();
	}
}
