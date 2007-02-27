/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PythonInputStream extends BufferedInputStream implements PythonStreamConstants
{
	private final byte[] m_buf = new byte[32];
	private boolean m_atEnd = false;

	public PythonInputStream(InputStream stream)
	{
		super(stream);
	}

	/**
	 * Read the next object from the stream. Note that <code>null</code> is a valid
	 * return value that does <b>not</b> imply end of file.
	 * @return The next object on the stream or the special object {@link #EOF} to
	 * denote that the end of the input is reached.
	 * @throws IOException if some other error occurs during reading.
	 */
	@SuppressWarnings("fallthrough")
	public Object readObject() throws IOException
	{
		if(m_atEnd)
			return null;

		int token = this.read();
		switch(token)
		{
		case -1:
			m_atEnd = true;
			// Fall through
		case TYPE_NULL:
			return null;
		case TYPE_STRING:
			return this.readString();
		case TYPE_INT:
			return new Integer(this.readInt32());
		case TYPE_INT64:
			return new Long(this.readInt64());
		case TYPE_FLOAT:
			return new Double(this.readDouble());
		case TYPE_DICT:
			return this.readMap();
		case TYPE_LIST:
			return this.readList();
		case TYPE_TUPLE:
			return this.readTuple();
		}
		throw new StreamCorruptedException();
	}

	public Map<String,String> readStringMap() throws IOException
	{
		if(m_atEnd)
			return null;

		int token = this.read();
		switch(token)
		{
		case -1:
			m_atEnd = true;
			return null;
		case TYPE_DICT:
			return this.readStrings();
		}
		throw new StreamCorruptedException();
	}

	public boolean isAtEnd()
	{
		return m_atEnd;
	}

	private double readDouble() throws IOException
	{
		int len = this.read();
		if(len <= 0 || len > 31)
			throw new StreamCorruptedException();
		this.readFully(m_buf, 0, len);
		return Double.parseDouble(new String(m_buf, 0, len, "US-ASCII"));
	}

	private String readString() throws IOException
	{
		int len = this.readInt32();
		byte[] bytes = new byte[len];
		this.readFully(bytes, 0, len);
		return new String(bytes, ENCODING);
	}

	private Map<?,?> readMap() throws IOException
	{
		Object key = this.readObject();
		if(m_atEnd)
			throw new StreamCorruptedException();

		if(key == null)
			return Collections.emptyMap();

		HashMap<Object,Object> map = new HashMap<Object,Object>();
		do
		{
			map.put(key, this.readObject());
			if(m_atEnd)
				throw new StreamCorruptedException();
			key = this.readObject();
			if(m_atEnd)
				throw new StreamCorruptedException();
		} while(key != null);
		return map;
	}

	private Map<String,String> readStrings() throws IOException
	{
		Object key = this.readObject();
		if(m_atEnd)
			throw new StreamCorruptedException();

		if(key == null)
			return Collections.emptyMap();

		HashMap<String,String> map = new HashMap<String,String>();
		do
		{
			Object val = this.readObject();
			if(val == null || m_atEnd)
				throw new StreamCorruptedException();

			map.put(key.toString(), val.toString());
			key = this.readObject();
			if(m_atEnd)
				throw new StreamCorruptedException();
		} while(key != null);
		return map;
	}

	private List<?> readList() throws IOException
	{
		int top = this.readInt32();
		if(top <= 0)
			return Collections.emptyList();

		ArrayList<Object> list = new ArrayList<Object>(top);
		while(--top >= 0)
		{
			list.add(this.readObject());
			if(m_atEnd)
				throw new StreamCorruptedException();
		}
		return list;
	}

	private Object[] readTuple() throws IOException
	{
		int top = this.readInt32();
		Object[] tuple = new Object[top];
		for(int idx = 0; idx < top; ++idx)
		{
			tuple[idx] = this.readObject();
			if(m_atEnd)
				throw new StreamCorruptedException();
		}
		return tuple;
	}

	private int readInt32() throws IOException
	{
		byte[] bytes = m_buf;
		this.readFully(bytes, 0, 4);
		return (( bytes[3] << 24) +
				((bytes[2] & 255) << 16) +
				((bytes[1] & 255) << 8) +
				((bytes[0] & 255) << 0));
	}

	private long readInt64() throws IOException
	{
		byte[] bytes = m_buf;
		this.readFully(bytes, 0, 8);
		return (((long) bytes[7] << 56) +
				((long)(bytes[6] & 255) << 48) +
				((long)(bytes[5] & 255) << 40) +
				((long)(bytes[4] & 255) << 32) +
				((long)(bytes[3] & 255) << 24) +
				((bytes[2] & 255) << 16) +
				((bytes[1] & 255) << 8) +
				((bytes[0] & 255) << 0));
	}

	private void readFully(byte[] b, int off, int len) throws IOException
	{
		while(len > 0)
		{
			int n = this.read(b, off, len);
			if(n < 0)
				throw new EOFException();
			off += n;
			len -= n;
		}
	}
}

