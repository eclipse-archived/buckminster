/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Adapter Factory that converts between Resolution and ResolutionDataNode.
 * 
 * @author Henrik Lindberg
 * 
 */
public class ResolutionAdapterFactory implements IAdapterFactory
{
	@SuppressWarnings("unchecked")
	private static Class[] s_adapterList = { Resolution.class, ResolutionDataNode.class };

	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptableObject, Class adapterType)
	{
		if(adaptableObject instanceof Resolution && adapterType.isAssignableFrom(ResolutionDataNode.class))
			return new ResolutionDataNode((Resolution)adaptableObject);

		if(adaptableObject instanceof ResolutionDataNode && adapterType.isAssignableFrom(Resolution.class))
			return ((ResolutionDataNode)adaptableObject).getData();

		// give up
		return null;
	}

	@SuppressWarnings("unchecked")
	public Class[] getAdapterList()
	{
		return s_adapterList;
	}

}
