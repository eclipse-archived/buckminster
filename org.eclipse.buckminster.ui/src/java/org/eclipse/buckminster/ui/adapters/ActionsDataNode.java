/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import java.util.Collection;

import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;
import org.eclipse.buckminster.ui.Messages;

/**
 * @author macon
 * 
 */
public class ActionsDataNode extends BasicTreeParentDataNode implements ITreeDataNode {

	public ActionsDataNode(Collection<Attribute> attributes) {
		super(Messages.actions);

		for (Attribute attribute : attributes) {
			if (attribute instanceof Action && attribute.isPublic()) {
				addChild(new ActionDataNode((Action) attribute));
			}
		}
	}

}
