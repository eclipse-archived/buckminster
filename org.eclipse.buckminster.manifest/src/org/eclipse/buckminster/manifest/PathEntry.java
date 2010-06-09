/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.manifest;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;

public class PathEntry implements Comparable<PathEntry>
{
	private static final String HEADER = "PATHENTRY:v0";

	private static final String NAME_PREFIX = "NAME:";

	private static final String CHECKSUMS_PREFIX = "CHECKSUMS:";

	public static final PathEntry[] EMPTY_LIST = new PathEntry[0];

	private final String m_name;

	private Checksum[] m_checksums = Checksum.EMPTY_LIST;

	public static PathEntry fromBufferedReader(BufferedReader br) throws IOException, MissingDataException, ChecksumMismatchException
	{
		String tmp;

		if (!br.readLine().equals(HEADER))
			throw new MissingDataException("Missing header in persisted PathEntry");

		tmp = br.readLine();
		if (!tmp.startsWith(NAME_PREFIX))
			throw new MissingDataException("Missing name in persisted PathEntry");
		String name = tmp.substring(NAME_PREFIX.length());

		tmp = br.readLine();
		if (!tmp.startsWith(CHECKSUMS_PREFIX))
			throw new MissingDataException("Missing checksums in persisted PathEntry");
		Checksum[] checksums = new Checksum[Integer.parseInt(tmp.substring(CHECKSUMS_PREFIX.length()))];
		for (int i = 0; i < checksums.length; i++)
			checksums[i] = Checksum.fromBufferedReader(br);

		return new PathEntry(name, checksums);
	}

	public PathEntry(File path, File root, IProgressMonitor monitor) throws ChecksumMismatchException, PathMismatchException, NoSuchAlgorithmException, IOException
	{
		this(path, root, null, null, monitor);
	}

	public PathEntry(File path, File root, String algorithm, String assumedLineSeparator, IProgressMonitor monitor) throws ChecksumMismatchException, PathMismatchException, NoSuchAlgorithmException, IOException
	{
		this(path, root, new Checksum[] { new Checksum(path, assumedLineSeparator, algorithm, monitor) });
	}

	public PathEntry(File path, File root, Checksum[] checksums) throws ChecksumMismatchException, PathMismatchException
	{
		this(canonicalize(path, root), checksums);
	}

	/* package */PathEntry(String name, Checksum[] checksums) throws ChecksumMismatchException
	{
		m_name = this.canonicalizeSeparator(name);
		this.addChecksums(checksums);
	}

	/* package */void addChecksums(Checksum[] checksums) throws ChecksumMismatchException
	{
		// only add checksums if we don't have them
		// if an attempt is made to add a checksum we do have, ensure the
		// existing one really is equal to existing
		//
		List<Checksum> checksums2Add = new ArrayList<Checksum>();
		for (Checksum c2add : checksums)
		{
			Checksum existing = this.internalGetChecksum(c2add.getAlgorithm(), c2add.getAssumedLineSeparator(),
					checksums2Add);
			if (existing == null)
				checksums2Add.add(c2add);
			else
				if (!c2add.equals(existing))
					throw new ChecksumMismatchException(existing, c2add);
		}

		int sz = checksums2Add.size();
		if (sz > 0)
		{
			Checksum[] newChecksums = new Checksum[m_checksums.length + sz];
			System.arraycopy(m_checksums, 0, newChecksums, 0, m_checksums.length);
			System.arraycopy(checksums2Add.toArray(Checksum.EMPTY_LIST), 0, newChecksums, m_checksums.length, sz);
			m_checksums = newChecksums;
			Arrays.sort(m_checksums);
		}
	}

	public String getName()
	{
		return m_name;
	}

	public boolean isDirectory()
	{
		return m_name.endsWith(Constants.CANONICAL_SEPARATOR);
	}

	public Checksum getChecksum()
	{
		return this.getChecksum(null, null);
	}

	public Checksum getChecksum(String algorithm, String assumedLineSeparator)
	{
		return this.internalGetChecksum(algorithm, assumedLineSeparator, null);
	}

	public Checksum[] getAllChecksums()
	{
		return m_checksums;
	}

	public boolean matches(PathEntry that)
	{
		if (this.equals(that))
			return true;

		if (!this.m_name.equals(that.m_name))
			return false;

		for (Checksum thisChecksum : this.getAllChecksums())
		{
			Checksum thatChecksum = that.getChecksum(thisChecksum.getAlgorithm(), thisChecksum
					.getAssumedLineSeparator());
			if (thatChecksum != null && !thisChecksum.equals(thatChecksum))
				return false;
		}

		for (Checksum thatChecksum : that.getAllChecksums())
		{
			Checksum thisChecksum = this.getChecksum(thatChecksum.getAlgorithm(), thatChecksum
					.getAssumedLineSeparator());
			if (thisChecksum != null && !thatChecksum.equals(thisChecksum))
				return false;
		}

		return true;
	}

	public int compareTo(PathEntry that)
	{
		if (that == this)
			return 0;

		int v = this.m_name.compareTo(that.m_name);
		if (v != 0)
			return v;

		if (Arrays.equals(this.m_checksums, that.m_checksums))
			return 0;

		int i = 0;
		while (i < this.m_checksums.length)
		{
			if (that.m_checksums.length <= i)
				return 1;
			v = this.m_checksums[i].compareTo(that.m_checksums[i]);
			if (v != 0)
				return v;
			i++;
		}

		if (that.m_checksums.length >= i)
			return -1;

		throw new InternalError();
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof PathEntry))
			return false;
		PathEntry that = (PathEntry)o;

		if (!this.m_name.equals(that.m_name))
			return false;

		if (!Arrays.equals(this.m_checksums, that.m_checksums))
			return false;
		
		return true;
	}

	@Override
	public int hashCode()
	{
		int result = 17;
		result = 37 * result + this.m_name.hashCode();
		result = 37 * result + Arrays.hashCode(this.m_checksums);
		return result;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder(m_name);
		sb.append(Arrays.toString(this.getAllChecksums()));
		return sb.toString();
	}

	public void toPrintWriter(PrintWriter pw)
	{
		pw.println(HEADER);

		pw.print(NAME_PREFIX);
		pw.println(m_name);

		pw.print(CHECKSUMS_PREFIX);
		pw.println(m_checksums.length);
		for (Checksum c : m_checksums)
			c.toPrintWriter(pw);
	}

	private static String canonicalize(File path, File root) throws PathMismatchException
	{
		String strPath = path.getPath();
		String rootPath = root.getPath();
		if (!strPath.startsWith(rootPath))
			throw new PathMismatchException(rootPath, strPath);
		StringBuilder sb = new StringBuilder(strPath.substring(rootPath.length() + 1));
		if (path.isDirectory())
			sb.append(File.separatorChar);
		return sb.toString();
	}

	private String canonicalizeSeparator(String name)
	{
		return name.replace(File.separatorChar, Constants.CANONICAL_SEPARATOR_CHAR);
	}

	private Checksum internalGetChecksum(String algorithm, String assumedLineSeparator, List<Checksum> additionalList)
	{
		for (Checksum c : m_checksums)
			if ((algorithm == null || c.getAlgorithm().equals(algorithm))
					&& (assumedLineSeparator == null || c.getAssumedLineSeparator().equals(assumedLineSeparator)))
				return c;

		if (additionalList != null)
			for (Checksum c : additionalList)
				if ((algorithm == null || c.getAlgorithm().equals(algorithm))
						&& (assumedLineSeparator == null || c.getAssumedLineSeparator().equals(assumedLineSeparator)))
					return c;

		return null;
	}
}
