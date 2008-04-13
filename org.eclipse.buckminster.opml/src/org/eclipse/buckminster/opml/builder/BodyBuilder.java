/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.builder;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.opml.model.Body;
import org.eclipse.buckminster.opml.model.Outline;

/**
 * @author Thomas Hallgren
 */
public class BodyBuilder
{
	private List<Outline> m_outlines;

	public void clear()
	{
		if(m_outlines != null)
			m_outlines.clear();
	}

	public void addOutline(Outline outline)
	{
		if(m_outlines == null)
			m_outlines = new ArrayList<Outline>();
		m_outlines.add(outline);
	}

	public List<Outline> getOutlines()
	{
		return m_outlines;
	}

	public void initFrom(Body body)
	{
		clear();
		List<Outline> outlines = body.getOutlines();
		int top = outlines.size();
		if(top > 0)
		{
			if(m_outlines == null)
				m_outlines = new ArrayList<Outline>(outlines);
			else
				m_outlines.addAll(outlines);
		}
	}
}
