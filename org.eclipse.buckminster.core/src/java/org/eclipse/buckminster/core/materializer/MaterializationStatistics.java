/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

/**
 * @author Thomas Hallgren
 * 
 */
public class MaterializationStatistics
{
	private int m_failed = 0;

	private int m_kept = 0;

	private int m_replaced = 0;

	private int m_skipped = 0;

	private int m_updated = 0;

	public void addFailed()
	{
		m_failed++;
	}

	public void addKept()
	{
		m_kept++;
	}

	public void addReplaced()
	{
		m_replaced++;
	}

	public void addSkipped()
	{
		m_skipped++;
	}

	public void addUpdated()
	{
		m_updated++;
	}

	public int getFailed()
	{
		return m_failed;
	}

	public int getKept()
	{
		return m_kept;
	}

	public int getReplaced()
	{
		return m_replaced;
	}

	public int getSkipped()
	{
		return m_skipped;
	}

	public int getTotal()
	{
		return m_failed + m_kept + m_replaced + m_skipped + m_updated;
	}

	public int getUpdated()
	{
		return m_updated;
	}
}
