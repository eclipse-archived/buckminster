package org.eclipse.buckminster.p2.remote;

import java.io.Closeable;
import java.io.IOException;

public interface IRepositoryDataStream extends Closeable
{
	/**
	 * Returns the change number that was in effect when this stream was produced.
	 * @return The current change number
	 */
	long getLastChangeNumber() throws IOException;

	/**
	 * Read at most <code>nbytes</code> number of bytes and return the data as a byte array. The
	 * length of the returned array will reflect the actual number of bytes read.
	 * @param nbytes The maximum number of bytes to read.
	 * @return A byte array with a length greater than zero or null in case end of file was reached.
	 * @throws IOException
	 * @see {@link java.io.InputStream#read(byte[], int, int)}
	 */
	byte[] read(int nbytes) throws IOException;

	/**
	 * Skip at most <code>nbytes</code> number of bytes.
	 * @param nbytes the number of bytes to be skipped.
	 * @return the actual number of bytes skipped.
	 * @exception IOException if an I/O error occurs.
	 * @see {@link java.io.InputStream#skip(long)}
	 */
	long skip(long nbytes) throws IOException;
}
