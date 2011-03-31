/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.cspec.model.Action;
import org.eclipse.buckminster.generic.model.tree.BasicTreeDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;

/**
 * @author macon
 * 
 */
public class ActionDataNode extends BasicTreeDataNode implements ITreeDataNode {

	public ActionDataNode(Action data) {
		super(data);
	}

	@Override
	public String toString() {
		return ((Action) getData()).getName();
	}

}
