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

import org.eclipse.buckminster.opml.IBody;
import org.eclipse.buckminster.opml.IOutline;

/**
 * @author Thomas Hallgren
 */
public class BodyBuilder implements IBody
{
	private ArrayList<OutlineBuilder> m_outlines = new ArrayList<OutlineBuilder>();

	public void clear()
	{
		m_outlines.clear();
	}

	public OutlineBuilder addOutline()
	{
		OutlineBuilder outlineBuilder = new OutlineBuilder();
		m_outlines.add(outlineBuilder);
		return outlineBuilder;
	}

	public List<? extends IOutline> getOutlines()
	{
		return m_outlines;
	}

	public List<OutlineBuilder> getOutlineBuilders()
	{
		return m_outlines;
	}

	public void initFrom(IBody body)
	{
		clear();
		for(IOutline outline : body.getOutlines())
			addOutline().initFrom(outline);
	}
}
