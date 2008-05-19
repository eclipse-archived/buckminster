/*******************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.opml.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.opml.IBody;
import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.builder.BodyBuilder;
import org.eclipse.buckminster.opml.builder.OutlineBuilder;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class Body extends AbstractSaxableElement implements IBody
{
	public static final String TAG = "body";

	private final List<Outline> m_outlines;

	public Body(BodyBuilder bodyBuilder)
	{
		List<OutlineBuilder> outlineBuilders = bodyBuilder.getOutlineBuilders();
		int top = outlineBuilders.size();
		if(top == 0)
			m_outlines = Collections.emptyList();
		else
		{
			List<Outline> outlines = new ArrayList<Outline>(top);
			for(OutlineBuilder outlineBuilder : outlineBuilders)
				outlines.add(new Outline(outlineBuilder));
			m_outlines = Utils.createUnmodifiableList(outlines);
		}
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public List<? extends IOutline> getOutlines()
	{
		return m_outlines;
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		for(Outline outline : m_outlines)
			outline.toSax(handler, namespace, prefix, outline.getDefaultTag());
	}
}
