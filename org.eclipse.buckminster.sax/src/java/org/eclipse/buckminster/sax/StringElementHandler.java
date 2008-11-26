/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import org.xml.sax.SAXException;

/**
 * A handler that can extract the contents between XML start and end tags without the overhead of creating several
 * objects. Make sure to call {@link #getLengthAndReset()} between each element.
 * 
 * @author Thomas Hallgren
 */
public abstract class StringElementHandler extends ChildHandler
{
	private char[] m_buffer = new char[64];

	private int m_length;

	protected StringElementHandler(AbstractHandler parentHandler)
	{
		super(parentHandler);
	}

	@Override
	public void characters(char[] chars, int start, int length) throws SAXException
	{
		if(m_length == 0)
		{
			// Trim off leading whitespace
			//
			while(length > 0 && Character.isWhitespace(chars[start]))
			{
				++start;
				--length;
			}
			if(length == 0)
				return;

			if(length > m_buffer.length)
				m_buffer = new char[length];
		}
		else
		{
			if(m_length + length > m_buffer.length)
			{
				char[] newBuffer = new char[m_length + (m_length > length
						? m_length
						: length)];
				System.arraycopy(m_buffer, 0, newBuffer, 0, m_length);
				m_buffer = newBuffer;
			}
		}
		System.arraycopy(chars, start, m_buffer, m_length, length);
		m_length += length;
	}

	/**
	 * Returns the raw character buffer.
	 * 
	 * @return the raw character buffer.
	 */
	protected final char[] getBuffer()
	{
		return m_buffer;
	}

	/**
	 * Returns the number of valid characters in the buffer after the start position. The length is trimmed from
	 * whitespace at the end. This method also resets the handler. Susequent calls to this method will return zero until
	 * a call to {@link #characters(char[], int, int)} arrives.
	 * 
	 * @return the whitespace trimmed length of the buffer.
	 */
	protected final int getLengthAndReset()
	{
		int last = m_length - 1;
		while(last >= 0 && Character.isWhitespace(m_buffer[last]))
			--last;
		m_length = 0;
		return last + 1;
	}
}
