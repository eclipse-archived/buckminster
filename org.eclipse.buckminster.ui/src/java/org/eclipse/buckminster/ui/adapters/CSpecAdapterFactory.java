/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Adapter Factory that converts between CSpec and CSpecDataNode and can convert an IResource to
 * a CSpec or CSpecDataNode.
 * 
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
		
		if(adaptableObject instanceof CSpecDataNode && adapterType.isAssignableFrom(CSpec.class))
			return (CSpec)((CSpecDataNode)adaptableObject).getData();
		
		if(adaptableObject instanceof IResource)
		{
			CSpec cspec = getCSpecFromResource((IResource)adaptableObject);
			if(cspec == null)
				return null;
			
			if(adapterType.isAssignableFrom(CSpecDataNode.class))
				return new CSpecDataNode(cspec);
			if(adapterType.isAssignableFrom(CSpec.class))
				return cspec;
		}
			
		// give up
		return null;
	}

	public CSpec getCSpecFromResource(IResource resource)
	{
		while(resource != null)
		{
			try
			{
				CSpec cspec = WorkspaceInfo.getCSpec(resource);
				if(cspec != null)
				{
					return cspec;
				}
				resource = resource.getParent();
			}
			catch(CoreException e)
			{
				// ignore
			}
		}		
		return null;
	}
	@SuppressWarnings("unchecked")
	public Class[] getAdapterList()
	{
		return s_adapterList;
	}

}
