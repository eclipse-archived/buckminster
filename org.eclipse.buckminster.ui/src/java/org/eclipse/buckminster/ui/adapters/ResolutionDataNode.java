/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;

public class ResolutionDataNode extends BasicTreeParentDataNode {

	public ResolutionDataNode(Resolution data) {
		super(data);
		// add cspec child node
		addChild(new CSpecDataNode(data.getCSpec()));
	}
}
