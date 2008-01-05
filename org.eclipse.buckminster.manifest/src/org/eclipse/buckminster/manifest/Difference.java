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

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Difference
{
	public enum RESULT
	{
		EQUAL, MATCHING, MERGABLE_NONMATCHING, NONMATCHING
	}

	private final Manifest m_left;

	private final Manifest m_right;

	private final Checksum[] m_leftOnlyChecksums;

	private final Checksum[] m_rightOnlyChecksums;

	private final Checksum[] m_commonChecksums;

	private final ChecksumPair[] m_nonMatchingChecksumPairs;

	private final PathEntry[] m_leftOnlyEntries;

	private final PathEntry[] m_rightOnlyEntries;

	private final PathEntry[] m_commonEntries;

	private final PathEntryPair[] m_matchingEntryPairs;

	private final PathEntryPair[] m_nonMatchingEntryPairs;

	private final RESULT m_result;

	/* package */Difference(Manifest left, Manifest right)
	{
		m_left = left;
		m_right = right;

		List<Checksum> leftOnlyChecksums = new ArrayList<Checksum>();
		List<Checksum> rightOnlyChecksums = new ArrayList<Checksum>();
		List<Checksum> commonChecksums = new ArrayList<Checksum>();
		List<ChecksumPair> nonMatchingChecksumPairs = new ArrayList<ChecksumPair>();
		List<PathEntry> leftOnlyEntries = new ArrayList<PathEntry>();
		List<PathEntry> rightOnlyEntries = new ArrayList<PathEntry>();
		List<PathEntry> commonEntries = new ArrayList<PathEntry>();
		List<PathEntryPair> matchingEntryPairs = new ArrayList<PathEntryPair>();
		List<PathEntryPair> nonMatchingEntryPairs = new ArrayList<PathEntryPair>();

		for (Checksum leftChecksum : m_left.getAllChecksums())
		{
			Checksum rightChecksum = m_right.getChecksum(leftChecksum.getAlgorithm(), leftChecksum.getAssumedLineSeparator());
			if (rightChecksum == null)
				leftOnlyChecksums.add(leftChecksum);
			else if (leftChecksum.equals(rightChecksum))
				commonChecksums.add(leftChecksum);
			else
				nonMatchingChecksumPairs.add(new ChecksumPair(leftChecksum, rightChecksum));
		}

		for (Checksum rightChecksum : m_right.getAllChecksums())
			if (m_left.getChecksum(rightChecksum.getAlgorithm(), rightChecksum.getAssumedLineSeparator()) == null)
				rightOnlyChecksums.add(rightChecksum);

		for (PathEntry leftEntry : m_left.getAllEntries())
		{
			PathEntry rightEntry = m_right.getEntry(leftEntry.getName());
			if (rightEntry == null)
				leftOnlyEntries.add(leftEntry);
			else if (leftEntry.equals(rightEntry))
				commonEntries.add(leftEntry);
			else
			{
				PathEntryPair pep = new PathEntryPair(leftEntry, rightEntry);
				if (leftEntry.matches(rightEntry))
					matchingEntryPairs.add(pep);
				else
					nonMatchingEntryPairs.add(pep);
			}
		}

		for (PathEntry rightEntry : m_right.getAllEntries())
			if (m_left.getEntry(rightEntry.getName()) == null)
				rightOnlyEntries.add(rightEntry);

		m_leftOnlyChecksums = leftOnlyChecksums.toArray(Checksum.EMPTY_LIST);
		m_rightOnlyChecksums = rightOnlyChecksums.toArray(Checksum.EMPTY_LIST);
		m_commonChecksums = commonChecksums.toArray(Checksum.EMPTY_LIST);
		m_nonMatchingChecksumPairs = nonMatchingChecksumPairs.toArray(ChecksumPair.EMPTY_LIST);
		m_leftOnlyEntries = leftOnlyEntries.toArray(PathEntry.EMPTY_LIST);
		m_rightOnlyEntries = rightOnlyEntries.toArray(PathEntry.EMPTY_LIST);
		m_commonEntries = commonEntries.toArray(PathEntry.EMPTY_LIST);
		m_matchingEntryPairs = matchingEntryPairs.toArray(PathEntryPair.EMPTY_LIST);
		m_nonMatchingEntryPairs = nonMatchingEntryPairs.toArray(PathEntryPair.EMPTY_LIST);

		if (m_leftOnlyChecksums.length == 0 && m_rightOnlyChecksums.length == 0 && m_nonMatchingChecksumPairs.length == 0 && m_leftOnlyEntries.length == 0
				&& m_rightOnlyEntries.length == 0 && m_matchingEntryPairs.length == 0 && m_nonMatchingEntryPairs.length == 0)
			m_result = RESULT.EQUAL;
		else if (m_nonMatchingChecksumPairs.length == 0 && m_leftOnlyEntries.length == 0 && m_rightOnlyEntries.length == 0
				&& m_nonMatchingEntryPairs.length == 0)
			m_result = RESULT.MATCHING;
		else if (m_nonMatchingChecksumPairs.length == 0 && m_nonMatchingEntryPairs.length == 0)
			m_result = RESULT.MERGABLE_NONMATCHING;
		else
			m_result = RESULT.NONMATCHING;

		assert verifyResult(m_left, m_right, m_result) : "Unexpectedly illogical";
	}

	public Checksum[] getLeftOnlyChecksums()
	{
		return m_leftOnlyChecksums;
	}

	public Checksum[] getRightOnlyChecksums()
	{
		return m_rightOnlyChecksums;
	}

	public Checksum[] getCommonChecksums()
	{
		return m_commonChecksums;
	}

	public ChecksumPair[] getNonMatchingChecksums()
	{
		return m_nonMatchingChecksumPairs;
	}

	public PathEntry[] getLeftOnlyEntries()
	{
		return m_leftOnlyEntries;
	}

	public PathEntry[] getRightOnlyEntries()
	{
		return m_rightOnlyEntries;
	}

	public PathEntry[] getCommonEntries()
	{
		return m_commonEntries;
	}

	public PathEntryPair[] getMatchingEntryPairs()
	{
		return m_matchingEntryPairs;
	}

	public PathEntryPair[] getNonMatchingEntryPairs()
	{
		return m_nonMatchingEntryPairs;
	}

	public RESULT getResult()
	{
		return m_result;
	}

	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder(m_result.toString());
		sb.append(" : ");
		sb.append("checksums=").append(m_commonChecksums.length);
		sb.append('/').append(m_leftOnlyChecksums.length);
		sb.append('/').append(m_rightOnlyChecksums.length);
		sb.append('/').append(m_nonMatchingChecksumPairs.length);
		sb.append(", ");
		sb.append("entries=").append(m_commonEntries.length);
		sb.append('/').append(m_leftOnlyEntries.length);
		sb.append('/').append(m_rightOnlyEntries.length);
		sb.append('/').append(m_matchingEntryPairs.length);
		sb.append('/').append(m_nonMatchingEntryPairs.length);
		sb.append(" : ");
		sb.append(Arrays.toString(new Manifest[] { m_left, m_right }));
		return sb.toString();
	}

	public void report(PrintWriter pw)
	{
		this.report(pw, false);
	}

	public void report(PrintWriter pw, boolean differencesOnly)
	{
		pw.println("*** Manifest difference report ***");
		pw.println("Result: " + m_result);
		pw.println("1:      " + m_left);
		pw.println("2:      " + m_right);

		if (m_result != RESULT.EQUAL)
		{
			// Top level checksums
			//
			pw.println("*** Top level checksum report ***");

			if (!differencesOnly)
			{
				if (m_commonChecksums.length == 0)
					pw.println("There are no common checksums.");
				else
				{
					pw.println("The following checksum(s) are common (" + m_commonChecksums.length + "):");
					for (Checksum c : m_commonChecksums)
						pw.println(c);
				}
			}

			if (m_leftOnlyChecksums.length == 0)
			{
				if (!differencesOnly)
					pw.println("There are no checksums exclusive to the first manifest.");
			}
			else
			{
				pw.println("The following checksum(s) are exclusive to the first manifest (" + m_leftOnlyChecksums.length + "):");
				for (Checksum c : m_leftOnlyChecksums)
					pw.println(c);
			}

			if (m_rightOnlyChecksums.length == 0)
			{
				if (!differencesOnly)
					pw.println("There are no checksums exclusive to the second manifest.");
			}
			else
			{
				pw.println("The following checksum(s) are exclusive to the second manifest (" + m_rightOnlyChecksums.length + "):");
				for (Checksum c : m_rightOnlyChecksums)
					pw.println(c);
			}

			if (m_nonMatchingChecksumPairs.length == 0)
			{
				if (!differencesOnly)
					pw.println("There are no checksums that conflict between the manifests.");
			}
			else
			{
				pw.println("The following checksum(s) conflict between the manifests (" + m_nonMatchingChecksumPairs.length + "):");
				for (ChecksumPair cp : m_nonMatchingChecksumPairs)
					pw.println(cp);
			}

			// Entries
			//
			pw.println("*** Entry report ***");

			if (!differencesOnly)
			{
				if (m_commonEntries.length == 0)
					pw.println("There are no common entries.");
				else
				{
					pw.println("The following entries are common (" + m_commonEntries.length + "):");
					for (PathEntry pe : m_commonEntries)
						pw.println(pe);
				}
			}

			if (m_leftOnlyEntries.length == 0)
			{
				if (!differencesOnly)
					pw.println("There are no entries exclusive to the first manifest.");
			}
			else
			{
				pw.println("The following entry/entries are exclusive to the first manifest (" + m_leftOnlyEntries.length + "):");
				for (PathEntry pe : m_leftOnlyEntries)
					pw.println(pe);
			}

			if (m_rightOnlyEntries.length == 0)
			{
				if (!differencesOnly)
					pw.println("There are no entries exclusive to the second manifest.");
			}
			else
			{
				pw.println("The following entry/entries are exclusive to the second manifest (" + m_rightOnlyEntries.length + "):");
				for (PathEntry pe : m_rightOnlyEntries)
					pw.println(pe);
			}

			if (m_matchingEntryPairs.length == 0)
			{
				if (!differencesOnly)
					pw.println("There are no entries that only match between the manifests.");
			}
			else
			{
				pw.println("The following entry/entries match between the manifests (" + m_matchingEntryPairs.length + "):");
				for (PathEntryPair pep : m_matchingEntryPairs)
					pw.println(pep);
			}

			if (m_nonMatchingEntryPairs.length == 0)
			{
				if (!differencesOnly)
					pw.println("There are no entries that conflict between the manifests.");
			}
			else
			{
				pw.println("The following entry/entries conflict between the manifests (" + m_nonMatchingEntryPairs.length + "):");
				for (PathEntryPair pep : m_nonMatchingEntryPairs)
					pw.println(pep);
			}
		}
	}

	private static boolean verifyResult(Manifest left, Manifest right, Difference.RESULT result)
	{
		boolean equals = left.equals(right);
		return !((result == RESULT.EQUAL && !equals) || (result != RESULT.EQUAL && equals));
	}
}
