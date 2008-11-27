/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.opml.IOutline;

public class OutlineDataNode extends BasicTreeParentDataNode
{

	public OutlineDataNode(IOutline data)
	{
		super(data);
		for(IOutline outline : data.getOutlines())
			addChild(new OutlineDataNode(outline));
	}

}
