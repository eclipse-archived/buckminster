/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.ui.Messages;

public class DependenciesDataNode extends BasicTreeParentDataNode
{

	public DependenciesDataNode(Map<String, ComponentRequest> dependencies)
	{
		super(Messages.dependencies);
		for(Entry<String, ComponentRequest> d : dependencies.entrySet())
		{
			addChild(new ComponentReferenceDataNode(new ComponentReference(d.getKey(), d.getValue(),
					ComponentReference.Mode.OUT)));
		}
	}
}
