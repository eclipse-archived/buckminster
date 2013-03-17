/*****************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.sax;

import org.xml.sax.SAXException;

/**
 * A handler that can extract the contents between XML start and end tags
 * without the overhead of creating several objects. Make sure to call
 * {@link #getLengthAndReset()} between each element.
 * 
 * @author Thomas Hallgren
 */
public abstract class StringElementHandler extends ChildHandler {
	private char[] buffer = new char[64];

	private int validLength;

	protected StringElementHandler(AbstractHandler parentHandler) {
		super(parentHandler);
	}

	@Override
	public void characters(char[] chars, int start, int length) throws SAXException {
		if (validLength == 0) {
			// Trim off leading whitespace
			//
			while (length > 0 && Character.isWhitespace(chars[start])) {
				++start;
				--length;
			}
			if (length == 0)
				return;

			if (length > buffer.length)
				buffer = new char[length];
		} else {
			if (validLength + length > buffer.length) {
				char[] newBuffer = new char[validLength + (validLength > length ? validLength :length)];
				System.arraycopy(buffer, 0, newBuffer, 0, validLength);
				buffer = newBuffer;
			}
		}
		System.arraycopy(chars, start, buffer, validLength, length);
		validLength += length;
	}

	/**
	 * Returns the raw character buffer.
	 * 
	 * @return the raw character buffer.
	 */
	protected final char[] getBuffer() {
		return buffer;
	}

	/**
	 * Returns the number of valid characters in the buffer after the start
	 * position. The length is trimmed from whitespace at the end. This method
	 * also resets the handler. Susequent calls to this method will return zero
	 * until a call to {@link #characters(char[], int, int)} arrives.
	 * 
	 * @return the whitespace trimmed length of the buffer.
	 */
	protected final int getLengthAndReset() {
		int last = validLength - 1;
		while (last >= 0 && Character.isWhitespace(buffer[last]))
			--last;
		validLength = 0;
		return last + 1;
	}
}
