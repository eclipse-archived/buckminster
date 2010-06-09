/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.p2.remote.server;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.buckminster.p2.remote.IRepositoryDataStream;
import org.eclipse.buckminster.p2.remote.marshall.Base64;

public class RepositoryDataStream implements IRepositoryDataStream
{
	private final long m_lastChangeNumber;

	private final InputStream m_input;

	byte[] buffer = null;

	public RepositoryDataStream(InputStream input, long lastChangeNumber)
	{
		m_lastChangeNumber = lastChangeNumber;
		m_input = input;
	}

	public void close() throws IOException
	{
		m_input.close();
	}

	public long getLastChangeNumber()
	{
		return m_lastChangeNumber;
	}

	public String read(int nbytes) throws IOException
	{
		if(buffer == null || buffer.length < nbytes)
			buffer = new byte[nbytes];

		int count = m_input.read(buffer, 0, nbytes);
		return new String(Base64.encode(buffer, 0, count));
	}

	public long skip(long nbytes) throws IOException
	{
		return m_input.skip(nbytes);
	}
}
