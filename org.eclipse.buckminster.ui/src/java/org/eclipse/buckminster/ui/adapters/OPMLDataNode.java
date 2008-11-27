/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.opml.IBody;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.ui.Messages;

public class OPMLDataNode extends BasicTreeParentDataNode
{

	public OPMLDataNode(IOPML data)
	{
		super(data == null
				? Messages.no_ompl
				: data);
		IBody body = data.getBody();
		for(IOutline outline : body.getOutlines())
		{
			addChild(new OutlineDataNode(outline));
		}
	}
}
