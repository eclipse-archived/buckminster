// TODO: Remove license that should not be used
/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/
/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Platform;

public class ModelPart implements IAdaptable
{
	ModelPart m_parent;

	/**
	 * Returns the model part's parent, or null if it does not have a parent. Adapt to ModelRoot to check
	 * if the object is the root of the model.
	 * @return
	 */
	public ModelPart getParent()
	{
		return m_parent;
	}

	public void setParent(ModelPart parent)
	{
		m_parent = parent;
	}
	public void notifyChanged()
	{
		if(m_parent != null)
			m_parent.notifyChanged();
	}
	/**
	 * Default implementation of {@link IAdaptable#getAdapter(Class)}.
	 */
	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter)
	{
		if(adapter.isInstance(this))
			return this;
		return Platform.getAdapterManager().getAdapter(this, adapter);
	}		
	
}