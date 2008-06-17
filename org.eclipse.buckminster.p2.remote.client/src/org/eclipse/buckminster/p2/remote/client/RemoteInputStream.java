/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.client;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.p2.remote.IRepositoryDataStream;

/**
 * @author Thomas Hallgren
 */
public class RemoteInputStream extends InputStream
{
	private final IRepositoryDataStream m_remoteStream;

	public RemoteInputStream(IRepositoryDataStream remoteStream)
	{
		m_remoteStream = remoteStream;
	}

	@Override
	public void close() throws IOException
	{
		m_remoteStream.close();
	}

	@Override
	public int read() throws IOException
	{
		byte[] buf = new byte[1];
		int c = read(buf);
		return c == 1 ? (int)(buf[0] & 0xff) : -1;
	}

	@Override
	public int read(byte[] buffer, int offset, int count) throws IOException
	{
		byte[] data = m_remoteStream.read(count);
		if(data == null)
			return -1;

		System.arraycopy(data, 0, buffer, offset, data.length);
		return data.length;
	}

	@Override
	public long skip(long nbytes) throws IOException
	{
		return m_remoteStream.skip(nbytes);
	}
}
