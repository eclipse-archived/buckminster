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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.DigestOutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.NullOutputStream;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.buckminster.runtime.MultiTeeOutputStream;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

public class Manifest
{
	private static final String HEADER = "MANIFEST:v0";

	private static final String DESCRIPTION_PREFIX = "DESCRIPTION:";

	private static final String CHECKSUMS_PREFIX = "CHECKSUMS:";

	private static final String ENTRIES_PREFIX = "ENTRIES:";

	public static Manifest create(File fromRoot) throws NoSuchAlgorithmException, IOException,
			ChecksumMismatchException, PathMismatchException
	{
		return Manifest.create(fromRoot, null, null, null, null);
	}

	public static Manifest create(File fromRoot, String algorithm, String assumedLineSeparator, String description,
			IProgressMonitor monitor) throws IOException, NoSuchAlgorithmException, ChecksumMismatchException,
			PathMismatchException
	{
		if (algorithm == null)
			algorithm = Constants.DEFAULT_ALGORITHM;

		if (assumedLineSeparator == null)
			assumedLineSeparator = Constants.LOCAL_LINESEPARATOR;

		File canonicalFromRoot = fromRoot.getCanonicalFile();

		if (description == null)
			description = canonicalFromRoot.getAbsolutePath();

		if (monitor == null)
			monitor = new NullProgressMonitor();

		try
		{
			monitor.beginTask(null, IProgressMonitor.UNKNOWN);

			MessageDigest md = MessageDigest.getInstance(algorithm);
			DigestOutputStream manifestDigestStream = new DigestOutputStream(NullOutputStream.INSTANCE, md);
			List<PathEntry> entries = new ArrayList<PathEntry>();
			recursiveDigests(canonicalFromRoot, algorithm, assumedLineSeparator, manifestDigestStream,
					canonicalFromRoot, entries, monitor);
			manifestDigestStream.close();
			Checksum checksum = new Checksum(md.digest(), algorithm, assumedLineSeparator);

			Manifest mf = new Manifest(description, new Checksum[] { checksum }, entries.toArray(PathEntry.EMPTY_LIST));

			MonitorUtils.worked(monitor, 1);

			return mf;
		}
		finally
		{
			monitor.done();
		}
	}

	public static Manifest merge(Manifest left, Manifest right) throws NonmatchingManifestsException,
			ChecksumMismatchException
	{
		String description = new StringBuilder(left.getDescription()).append(" merged with ").append(
				right.getDescription()).toString();
		if (left.equals(right))
			return new Manifest(description, left.getAllChecksums(), left.getAllEntries());

		if (new Difference(left, right).getResult() == Difference.RESULT.NONMATCHING)
			throw new NonmatchingManifestsException(left, right);

		Set<Checksum> mfChecksums = new TreeSet<Checksum>();
		mfChecksums.addAll(Arrays.asList(left.getAllChecksums()));
		mfChecksums.addAll(Arrays.asList(right.getAllChecksums()));

		Map<String, PathEntry> entryMap = new HashMap<String, PathEntry>();
		for (PathEntry leftEntry : left.getAllEntries())
			entryMap.put(leftEntry.getName(), leftEntry);
		for (PathEntry rightEntry : right.getAllEntries())
		{
			PathEntry existingEntry = entryMap.get(rightEntry.getName());
			assert existingEntry != null : "Unexpectedly null";
			existingEntry.addChecksums(rightEntry.getAllChecksums());
		}

		return new Manifest(description, mfChecksums.toArray(Checksum.EMPTY_LIST), entryMap.values().toArray(
				PathEntry.EMPTY_LIST));
	}

	public static Manifest fromBufferedReader(BufferedReader br, String description) throws MissingDataException,
			IOException, ChecksumMismatchException
	{
		String tmp;

		if (!br.readLine().equals(HEADER))
			throw new MissingDataException("Missing header in persisted Manifest");

		tmp = br.readLine();
		if (!tmp.startsWith(DESCRIPTION_PREFIX))
			throw new MissingDataException("Missing description in persisted Manifest");
		if (description == null)
			description = tmp.substring(DESCRIPTION_PREFIX.length()) + " (persisted)";

		tmp = br.readLine();
		if (!tmp.startsWith(CHECKSUMS_PREFIX))
			throw new MissingDataException("Missing checksums in persisted Manifest");
		Checksum[] checksums = new Checksum[Integer.parseInt(tmp.substring(CHECKSUMS_PREFIX.length()))];
		for (int i = 0; i < checksums.length; i++)
			checksums[i] = Checksum.fromBufferedReader(br);

		tmp = br.readLine();
		if (!tmp.startsWith(ENTRIES_PREFIX))
			throw new MissingDataException("Missing entries in persisted Manifest");
		PathEntry[] entries = new PathEntry[Integer.parseInt(tmp.substring(ENTRIES_PREFIX.length()))];
		for (int i = 0; i < entries.length; i++)
			entries[i] = PathEntry.fromBufferedReader(br);

		return new Manifest(description, checksums, entries);
	}

	private static void recursiveDigests(File dir, String algorithm, String assumedLineSeparator,
			OutputStream parentDigestStream, File topRoot, List<PathEntry> entries, IProgressMonitor monitor) throws IOException,
			NoSuchAlgorithmException, ChecksumMismatchException, PathMismatchException
	{
		// get the file list, but *always* sort it to ensure we
		// always process things in the same order as this is important
		// for always getting the same digest
		//
		File files[] = dir.listFiles();
		Arrays.sort(files);
		for (File f : files)
		{
			// Make names count for everything we encounter to ensure
			// changes in dir structure also count (new dirs, empty files,
			// changed names). Specifically, write something extra for dirs,
			// will catch dir swapped for an empty file or vice versa
			//
			parentDigestStream.write(f.getName().getBytes());
			if (f.isDirectory())
				parentDigestStream.write(1);

			monitor.subTask(f.toString());

			MessageDigest md = MessageDigest.getInstance(algorithm);
			DigestOutputStream digestStream = new DigestOutputStream(NullOutputStream.INSTANCE, md);
			// the mtos should *not* be closed, only flushed
			MultiTeeOutputStream mtos = new MultiTeeOutputStream(
					new OutputStream[] { digestStream, parentDigestStream });
			if (f.isFile())
			{
				FileInputStream fis = new FileInputStream(f);
				FileUtils.copyFile(fis, mtos, MonitorUtils.subMonitor(monitor, 1));
				fis.close();
			}
			else
				recursiveDigests(f, algorithm, assumedLineSeparator, mtos, topRoot, entries, monitor);
			mtos.flush();
			digestStream.close();
			entries.add(new PathEntry(f, topRoot, new Checksum[] { new Checksum(md.digest(), algorithm,
					assumedLineSeparator) }));
			
			MonitorUtils.worked(monitor, 1);
		}
	}

	private final String m_description;

	// ==========
	// NOTE: These members are used for equals/hashCode calculations
	// If you in any way change, delete or add to them, ensure to
	// update equals/hashCode to account for it.
	//
	private Checksum[] m_checksums = Checksum.EMPTY_LIST;

	private final SortedMap<String, PathEntry> m_entryMap = new TreeMap<String, PathEntry>();

	// must only be created through static methods
	private Manifest(String description, Checksum[] checksums, PathEntry[] entries) throws ChecksumMismatchException
	{
		m_description = description;
		this.addChecksums(checksums);
		for (PathEntry entry : entries)
			m_entryMap.put(entry.getName(), entry);
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
			else if (!c2add.equals(existing))
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

	public String getDescription()
	{
		return m_description;
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

	public String[] getAllEntryNames()
	{
		return m_entryMap.keySet().toArray(new String[m_entryMap.size()]);
	}

	public PathEntry getEntry(String name)
	{
		return m_entryMap.get(name);
	}

	public PathEntry[] getAllEntries()
	{
		return m_entryMap.values().toArray(new PathEntry[m_entryMap.size()]);
	}

	public Difference getDifference(Manifest right)
	{
		return new Difference(this, right);
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder(m_description);
		sb.append("=>");
		sb.append(Arrays.toString(this.getAllChecksums()));
		sb.append("=>");
		sb.append(m_entryMap.size());
		return sb.toString();
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof Manifest))
			return false;
		Manifest that = (Manifest)o;

		if (!Arrays.equals(this.getAllChecksums(), that.getAllChecksums()))
			return false;

		if(!Arrays.equals(this.getAllEntries(), that.getAllEntries()))
			return false;
		
		return true;
	}

	@Override
	public int hashCode()
	{
		int result = 17;
		result = 37 * result + Arrays.hashCode(this.getAllChecksums());
		result = 37 * result + Arrays.hashCode(this.getAllEntries());
		return result;
	}

	public void toPrintWriter(PrintWriter pw)
	{
		pw.println(HEADER);

		pw.print(DESCRIPTION_PREFIX);
		pw.println(m_description);

		pw.print(CHECKSUMS_PREFIX);
		pw.println(m_checksums.length);
		for (Checksum c : m_checksums)
			c.toPrintWriter(pw);

		pw.print(ENTRIES_PREFIX);
		pw.println(m_entryMap.size());
		for (PathEntry pe : m_entryMap.values())
			pe.toPrintWriter(pw);
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
