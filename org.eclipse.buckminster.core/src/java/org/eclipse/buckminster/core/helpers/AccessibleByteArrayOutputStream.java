/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.helpers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.core.Messages;
import org.eclipse.osgi.util.NLS;

/**
 * @author Thomas Hallgren
 */
public class AccessibleByteArrayOutputStream extends ByteArrayOutputStream
{
	private final int m_maxSize;

	public AccessibleByteArrayOutputStream()
	{
		super();
		m_maxSize = -1;
	}

	public AccessibleByteArrayOutputStream(int size)
	{
		super(size);
		m_maxSize = -1;
	}

	public AccessibleByteArrayOutputStream(int size, int maxSize)
	{
		super(size);
		m_maxSize = maxSize;
	}

	/**
	 * Reset the counter to zero so that the stream can be reused.
	 */
	@Override
	public void close() throws IOException
	{
		count = 0;
	}

	/**
	 * Returns an InputStream that can read the content of this output stream up to the point where this call was
	 * issued. Further writes on this OutputStream will not be available for the returned InputStream. The typical
	 * scenario is that some byte image is built with a series of writes and an InputStream is desired that can read
	 * this image without copying it (as would have been done using {@link ByteArrayOutputStream#toByteArray()}.
	 * 
	 * @return
	 */
	public InputStream getInputStream()
	{
		return new ByteArrayInputStream(buf, 0, count);
	}

	@Override
	public void write(byte b[], int off, int len)
	{
		super.write(b, off, len);
		if(m_maxSize > 0 && count >= m_maxSize)
			throw new IllegalStateException(NLS.bind(Messages.Max_size_0_exceeded, Integer.valueOf(m_maxSize)));
	}

	@Override
	public void write(int b)
	{
		super.write(b);
		if(m_maxSize > 0 && count >= m_maxSize)
			throw new IllegalStateException(NLS.bind(Messages.Max_size_0_exceeded, Integer.valueOf(m_maxSize)));
	}
}
