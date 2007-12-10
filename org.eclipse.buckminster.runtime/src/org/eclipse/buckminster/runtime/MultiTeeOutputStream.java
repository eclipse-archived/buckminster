/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.runtime;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Thomas Hallgren
 */
public class MultiTeeOutputStream extends OutputStream
{
	private final OutputStream[] m_streams;

	public MultiTeeOutputStream(OutputStream[] streams)
	{
		m_streams = streams;
	}

	@Override
	public void close() throws IOException
	{
		// We do make sure that all streams are closed even if
		// exceptions are thrown. We also make sure that at least
		// one of any number of thrown exceptions is thrown. There
		// might be a loss of exceptions if several streams
		// throw an exception.
		//
		IOException closeException = null;
		int idx = m_streams.length;
		while(--idx >= 0)
		{
			try
			{
				m_streams[idx].close();
			}
			catch(IOException ioe)
			{
				if(closeException == null)
					closeException = ioe;
			}
		}
		if(closeException != null)
			throw closeException;
	}

	@Override
	public void flush() throws IOException
	{
		int idx = m_streams.length;
		while(--idx >= 0)
			m_streams[idx].flush();
	}

	@Override
	public void write(byte[] b, int off, int len) throws IOException
	{
		int idx = m_streams.length;
		while(--idx >= 0)
			m_streams[idx].write(b, off, len);
	}

	@Override
	public void write(byte[] b) throws IOException
	{
		int idx = m_streams.length;
		while(--idx >= 0)
			m_streams[idx].write(b);
	}

	@Override
	public void write(int b) throws IOException
	{
		int idx = m_streams.length;
		while(--idx >= 0)
			m_streams[idx].write(b);
	}
}
