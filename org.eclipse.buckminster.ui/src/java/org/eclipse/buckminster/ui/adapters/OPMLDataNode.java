/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.opml.model.Body;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.opml.model.Outline;

public class OPMLDataNode extends BasicTreeParentDataNode
{

	public OPMLDataNode(OPML data)
	{
		super(data == null ? "no opml" : data);
		Body body = data.getBody();
		for(Outline outline : body.getOutlines())
		{
			addChild(new OutlineDataNode(outline));
		}
	}
}