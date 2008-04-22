/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.generic.model.tree.BasicTreeDataNode;

public class CSpecDataNode extends BasicTreeDataNode
{

	public CSpecDataNode(CSpec data)
	{
		super(data);
	}
}