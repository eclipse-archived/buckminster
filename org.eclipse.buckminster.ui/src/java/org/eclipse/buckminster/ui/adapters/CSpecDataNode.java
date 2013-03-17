/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import java.util.Collection;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;

public class CSpecDataNode extends BasicTreeParentDataNode {

	public CSpecDataNode(CSpec data) {
		super(data);

		// add dependencies
		Collection<ComponentRequest> dependencies = data.getDependencies();
		if (dependencies != null && dependencies.size() > 0)
			addChild(new DependenciesDataNode(dependencies));

		// add actions
		Map<String, Attribute> attributes = data.getAttributes();
		if (attributes != null && attributes.size() > 0)
			addChild(new ActionsDataNode(attributes.values()));
	}
}
