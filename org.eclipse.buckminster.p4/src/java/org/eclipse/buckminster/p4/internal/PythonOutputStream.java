/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.internal;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.p4.Messages;
import org.eclipse.osgi.util.NLS;

public class PythonOutputStream extends BufferedOutputStream implements PythonStreamConstants
{
	public PythonOutputStream(OutputStream output)
	{
		super(output);
	}

	public void writeObject(Object obj) throws IOException
	{
		if(obj == null)
			this.writeNull();
		else if(obj instanceof String)
			this.writeString((String)obj);
		else if(obj instanceof Integer)
			this.writeInt(((Integer)obj).intValue());
		else if(obj instanceof Map)
			this.writeMap((Map<?,?>)obj);
		else if(obj instanceof List)
			this.writeList((List<?>)obj);
		else if(obj instanceof Double)
			this.writeDouble(((Double)obj).doubleValue());
		else if(obj instanceof Long)
			this.writeLong(((Long)obj).longValue());
		else if(obj instanceof Object[])
			this.writeTuple((Object[])obj);
		else
			throw new IOException(NLS.bind(Messages.unable_to_write_object_of_type_0, obj.getClass()));
	}

	private void writeInt(int v) throws IOException
	{
		out.write(TYPE_INT);
		this.writeInt32(v);
	}

	private void writeLong(long v) throws IOException
	{
		out.write(TYPE_INT64);
		this.writeInt64(v);
	}

	private void writeDouble(double v) throws IOException
	{
		String dblStr = Double.toString(v);
		out.write(TYPE_FLOAT);
		out.write(dblStr.length());
		out.write(dblStr.getBytes("US-ASCII")); //$NON-NLS-1$
	}

	private void writeString(String str) throws IOException
	{
		byte[] bytes = str.getBytes(ENCODING);
		out.write(TYPE_STRING);
		this.writeInt32(bytes.length);
		out.write(bytes);
	}

	private void writeNull() throws IOException
	{
		out.write('0');
	}

	private void writeMap(Map<?, ?> map) throws IOException
	{
		out.write(TYPE_DICT);
		for(Map.Entry<?, ?> entry : map.entrySet())
		{
			this.writeObject(entry.getKey());
			this.writeObject(entry.getValue());
		}
		out.write(TYPE_NULL);
	}

	private void writeTuple(Object[] tuple) throws IOException
	{
		out.write(TYPE_TUPLE);
		this.writeInt32(tuple.length);
		for(Object val : tuple)
			this.writeObject(val);
	}

	private void writeList(List<?> list) throws IOException
	{
		out.write(TYPE_LIST);
		this.writeInt32(list.size());
		for(Object val : list)
			this.writeObject(val);
	}

	private void writeInt32(int v) throws IOException
	{
		out.write((v >>>  0) & 0xFF);
		out.write((v >>>  8) & 0xFF);
		out.write((v >>> 16) & 0xFF);
		out.write((v >>> 24) & 0xFF);
	}

	private void writeInt64(long v) throws IOException
	{
		out.write((int)((v >>>  0) & 0xFF));
		out.write((int)((v >>>  8) & 0xFF));
		out.write((int)((v >>> 16) & 0xFF));
		out.write((int)((v >>> 24) & 0xFF));
		out.write((int)((v >>> 32) & 0xFF));
		out.write((int)((v >>> 40) & 0xFF));
		out.write((int)((v >>> 48) & 0xFF));
		out.write((int)((v >>> 56) & 0xFF));
	}
}

