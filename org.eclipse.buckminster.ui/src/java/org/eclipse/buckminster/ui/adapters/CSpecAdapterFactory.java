/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Adapter Factory that converts between CSpec and CSpecDataNode
 * @author Henrik Lindberg
 *
 */
public class CSpecAdapterFactory implements IAdapterFactory
{
	@SuppressWarnings("unchecked")
	private static Class[] s_adapterList = { CSpec.class, CSpecDataNode.class };
	
	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptableObject, Class adapterType)
	{
		if(adaptableObject instanceof CSpec && adapterType.isAssignableFrom(CSpecDataNode.class))
			return new CSpecDataNode((CSpec)adaptableObject);
		
		if(adaptableObject instanceof IAdaptable)
			return ((IAdaptable)adaptableObject).getAdapter(adapterType);
		
		// give up
		return null;
	}


	@SuppressWarnings("unchecked")
	public Class[] getAdapterList()
	{
		// TODO Auto-generated method stub
		return s_adapterList;
	}

}
