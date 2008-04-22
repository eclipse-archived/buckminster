/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import java.util.List;

import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.opml.model.Outline;

public class OutlineDataNode extends BasicTreeParentDataNode
{

	public OutlineDataNode(Outline data)
	{
		super(data);
		List<Outline> outlines = data.getOutlines();
		if(outlines != null)
			for(Outline outline : outlines)
				addChild(new OutlineDataNode(outline));
	}
	
}