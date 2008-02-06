/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;

/**
 * @author Thomas Hallgren
 * 
 */
public class MaterializationStatistics
{
	private List<ComponentIdentifier> m_failed = new ArrayList<ComponentIdentifier>();

	private List<ComponentIdentifier> m_kept = new ArrayList<ComponentIdentifier>();

	private List<ComponentIdentifier> m_replaced = new ArrayList<ComponentIdentifier>();

	private List<ComponentIdentifier> m_skipped = new ArrayList<ComponentIdentifier>();

	private List<ComponentIdentifier> m_updated = new ArrayList<ComponentIdentifier>();
	
	public void addFailed(ComponentIdentifier ci)
	{
		m_failed.add(ci);
	}

	public void addKept(ComponentIdentifier ci)
	{
		m_kept.add(ci);
	}

	public void addReplaced(ComponentIdentifier ci)
	{
		m_replaced.add(ci);
	}

	public void addSkipped(ComponentIdentifier ci)
	{
		m_skipped.add(ci);
	}

	public void addUpdated(ComponentIdentifier ci)
	{
		m_updated.add(ci);
	}

	public List<ComponentIdentifier> getFailed()
	{
		return m_failed;
	}

	public List<ComponentIdentifier> getKept()
	{
		return m_kept;
	}

	public List<ComponentIdentifier> getReplaced()
	{
		return m_replaced;
	}

	public List<ComponentIdentifier> getSkipped()
	{
		return m_skipped;
	}

	public List<ComponentIdentifier> getUpdated()
	{
		return m_updated;
	}
}
