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
import java.util.Set;

import org.eclipse.buckminster.core.common.model.IProperties;

public class BMProperties implements IProperties
{
	private static final IProperties s_systemProperties = new PropertiesWrapper(System.getProperties());

	/** A table of hex digits */
	private static final char[] hexDigit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E',
			'F' };

	public static void store(Map<String,String> props, OutputStream out, String comments) throws IOException
	{
		BufferedWriter awriter;
		awriter = new BufferedWriter(new OutputStreamWriter(out, "8859_1"));
		if(comments != null)
			writeln(awriter, "#" + comments);
		writeln(awriter, "#" + new Date().toString());
		synchronized(props)
		{
			for(Entry<String, String> e : props.entrySet())
			{
				String key = e.getKey();
				key = saveConvert(key, true);
	
				/*
				 * No need to escape embedded and trailing spaces for value, hence
				 * pass false to flag.
				 */
				writeln(awriter, key + "=" + saveConvert(e.getValue(), false));
			}
		}
		awriter.flush();
	}

    private final Map<String, String> m_map;

	public BMProperties()
	{
		m_map = new HashMap<String,String>();
	}

	public BMProperties(InputStream inStream) throws IOException
	{
    	java.util.Properties loader = new java.util.Properties();
    	loader.load(inStream);
		m_map = new PropertiesWrapper(loader);
	}

	public BMProperties(java.util.Properties defaultProps)
	{
		m_map = new PropertiesWrapper(defaultProps);
	}

	public BMProperties(Map<String, String> defaultProps)
	{
		Map<String,String> props = new HashMap<String,String>();
		if(defaultProps != null)
			props = new MapUnion<String,String>(props, defaultProps);
		m_map = props;
	}

	public BMProperties(Map<String, String> mutableProps, Map<String, String> defaultProps)
	{
		if(defaultProps != null)
			mutableProps = new MapUnion<String,String>(mutableProps, defaultProps);
		m_map = mutableProps;
	}

	public void clear()
	{
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
		return m_map.entrySet();
	}

	@Override
	public boolean equals(Object o)
	{
		return this == o || (o instanceof Map && o.equals(m_map));
	}

	public String get(Object key)
	{
		return m_map.get(key);
	}

	@Override
	public int hashCode()
	{
		return m_map.hashCode();
	}

	public boolean isEmpty()
	{
		return m_map.isEmpty();
	}

	public Set<String> keySet()
	{
		return m_map.keySet();
	}

	public Set<String> immutableKeySet()
	{
		return (m_map instanceof IProperties)
			? ((IProperties)m_map).immutableKeySet()
			: m_map.keySet();
	}

	public boolean isMutable(String key)
	{
		return (m_map instanceof IProperties)
			? ((IProperties)m_map).isMutable(key)
			: true;
	}

	public Set<String> mutableKeySet()
	{
		return (m_map instanceof IProperties)
			? ((IProperties)m_map).mutableKeySet()
			: m_map.keySet();
	}

	public Set<String> overlayKeySet()
	{
		if(m_map instanceof IProperties)
			return ((IProperties)m_map).overlayKeySet();
		if(m_map instanceof MapUnion)
			return ((MapUnion<String,String>)m_map).overlayKeySet();
		return m_map.keySet();
	}

	public String put(String key, String value)
	{
		return m_map.put(key, value);
	}

	public String put(String key, String value, boolean mutable)
	{
		if(m_map instanceof IProperties)
			return ((IProperties)m_map).put(key, value, mutable);
		if(!mutable)
			throw new UnsupportedOperationException("put immutable");
		return m_map.put(key, value);
	}

	public void putAll(Map<? extends String, ? extends String> t)
	{
		m_map.putAll(t);
	}

	public String remove(Object key)
	{
		return m_map.remove(key);
	}

	public int size()
	{
		return m_map.size();
	}

	public void store(OutputStream out, String comments) throws IOException
	{
		store(m_map, out, comments);
	}

	public Collection<String> values()
	{
		return m_map.values();
	}

	public static IProperties getSystemProperties()
	{
		return s_systemProperties;
	}

	private static String saveConvert(String theString, boolean escapeSpace)
	{
		int len = theString.length();
		int bufLen = len * 2;
		if(bufLen < 0)
		{
			bufLen = Integer.MAX_VALUE;
		}
		StringBuffer outBuffer = new StringBuffer(bufLen);

		for(int x = 0; x < len; x++)
		{
			char aChar = theString.charAt(x);
			// Handle common case first, selecting largest block that
			// avoids the specials below
			if((aChar > 61) && (aChar < 127))
			{
				if(aChar == '\\')
				{
					outBuffer.append('\\');
					outBuffer.append('\\');
					continue;
				}
				outBuffer.append(aChar);
				continue;
			}
			switch(aChar)
			{
			case ' ':
				if(x == 0 || escapeSpace)
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
				if((aChar < 0x0020) || (aChar > 0x007e))
				{
					outBuffer.append('\\');
					outBuffer.append('u');
					outBuffer.append(toHex((aChar >> 12) & 0xF));
					outBuffer.append(toHex((aChar >> 8) & 0xF));
					outBuffer.append(toHex((aChar >> 4) & 0xF));
					outBuffer.append(toHex(aChar & 0xF));
				}
				else
				{
					outBuffer.append(aChar);
				}
			}
		}
		return outBuffer.toString();
	}

	/**
	 * Convert a nibble to a hex character
	 * @param nibble the nibble to convert.
	 */
	private static char toHex(int nibble)
	{
		return hexDigit[(nibble & 0xF)];
	}

	private static void writeln(BufferedWriter bw, String s) throws IOException
	{
		bw.write(s);
		bw.newLine();
	}

	public void setMutable(String key, boolean flag) throws UnsupportedOperationException
	{
		if(m_map instanceof IProperties)
			((IProperties)m_map).setMutable(key, flag);
		else if(!flag)
			throw new UnsupportedOperationException("setMutable");
	}

	public boolean supportsMutability()
	{
		return (m_map instanceof IProperties)
			? ((IProperties)m_map).supportsMutability()
			: false;
	}
}
