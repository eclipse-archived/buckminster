/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.manifest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.core.runtime.IProgressMonitor;

public class Checksum implements Comparable<Checksum>
{
	public static final Checksum[] EMPTY_LIST = new Checksum[0];

	private static final String HEADER = "CHECKSUM:v0";

	private static final String ALGORITHM_PREFIX = "ALGORITHM:";

	private static final String ASSUMEDLINESEPARATOR_PREFIX = "ASSUMEDLINESEPARATOR:";

	private static final String DIGEST_PREFIX = "DIGEST:";

	private final byte[] m_digest;

	private final String m_algorithm;

	private final String m_assumedLineSeparator;

	public static Checksum fromBufferedReader(BufferedReader br) throws IOException, MissingDataException
	{
		String tmp;

		if (!br.readLine().equals(HEADER))
			throw new MissingDataException("Missing header in persisted Checksum");

		tmp = br.readLine();
		if (!tmp.startsWith(ALGORITHM_PREFIX))
			throw new MissingDataException("Missing algorithm in persisted Checksum");
		String algorithm = tmp.substring(ALGORITHM_PREFIX.length());

		tmp = br.readLine();
		if (!tmp.startsWith(ASSUMEDLINESEPARATOR_PREFIX))
			throw new MissingDataException("Missing assumed line separator in persisted Checksum");
		String assumedLineSeparator = new String(TextUtils.makeByteArrayFromHexString(tmp
				.substring(ASSUMEDLINESEPARATOR_PREFIX.length())));

		tmp = br.readLine();
		if (!tmp.startsWith(DIGEST_PREFIX))
			throw new MissingDataException("Missing digest in persisted Checksum");
		byte[] digest = TextUtils.makeByteArrayFromHexString(tmp.substring(DIGEST_PREFIX.length()));

		return new Checksum(digest, algorithm, assumedLineSeparator);
	}

	public Checksum(File path, IProgressMonitor monitor) throws IOException, NoSuchAlgorithmException
	{
		this(path, null, null, monitor);
	}

	public Checksum(File path, String algorithm, String assumedLineSeparator, IProgressMonitor monitor) throws IOException, NoSuchAlgorithmException
	{
		if (algorithm == null)
			algorithm = Constants.DEFAULT_ALGORITHM;
		if (assumedLineSeparator == null)
			assumedLineSeparator = Constants.LOCAL_LINESEPARATOR;
		m_digest = FileUtils.calculateDigest(path, algorithm, monitor);
		m_algorithm = algorithm;
		m_assumedLineSeparator = assumedLineSeparator;
	}

	/**
	 * Only intended for internal use when reading a persisted manifest
	 * 
	 * @param name
	 * @param checksums
	 * @throws Exception
	 */
	/* package */Checksum(byte[] digest, String algorithm, String assumedLineSeparator)
	{
		m_digest = new byte[digest.length];
		System.arraycopy(digest, 0, m_digest, 0, digest.length);
		m_algorithm = algorithm;
		m_assumedLineSeparator = assumedLineSeparator;
	}

	public byte[] getBytes()
	{
		return m_digest;
	}

	public String getAlgorithm()
	{
		return m_algorithm;
	}

	public String getAssumedLineSeparator()
	{
		return m_assumedLineSeparator;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder(m_algorithm);
		sb.append(':');
		sb.append(TextUtils.makeHexString(m_assumedLineSeparator.getBytes()));
		sb.append(':');
		sb.append(TextUtils.makeHexString(m_digest));
		return sb.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof Checksum))
			return false;
		Checksum that = (Checksum)o;

		if (!this.m_algorithm.equals(that.m_algorithm))
			return false;

		if (!this.m_assumedLineSeparator.equals(that.m_assumedLineSeparator))
			return false;

		if(!Arrays.equals(this.m_digest, that.m_digest))
			return false;
		
		return true;
	}

	@Override
	public int hashCode()
	{
		int result = 17;
		result = 37 * result + this.m_algorithm.hashCode();
		result = 37 * result + this.m_assumedLineSeparator.hashCode();
		result = 37 * result + Arrays.hashCode(this.m_digest);
		return result;
	}

	public int compareTo(Checksum that)
	{
		if (that == this)
			return 0;

		int v = this.m_algorithm.compareTo(that.m_algorithm);
		if (v != 0)
			return v;

		v = this.m_assumedLineSeparator.compareTo(that.m_assumedLineSeparator);
		if (v != 0)
			return v;

		if (Arrays.equals(this.m_digest, that.m_digest))
			return 0;

		assert this.m_digest.length == that.m_digest.length : "Digest size difference";
		int i = 0;
		while (i < this.m_digest.length)
		{
			if (this.m_digest[i] != that.m_digest[i])
				return (this.m_digest[i] > that.m_digest[i]) ? 1 : -1;
			i++;
		}

		throw new InternalError();
	}

	public void toPrintWriter(PrintWriter pw)
	{
		pw.println(HEADER);

		pw.print(ALGORITHM_PREFIX);
		pw.println(m_algorithm);

		pw.print(ASSUMEDLINESEPARATOR_PREFIX);
		pw.println(TextUtils.makeHexString(m_assumedLineSeparator.getBytes()));

		pw.print(DIGEST_PREFIX);
		pw.println(TextUtils.makeHexString(m_digest));
	}
}
