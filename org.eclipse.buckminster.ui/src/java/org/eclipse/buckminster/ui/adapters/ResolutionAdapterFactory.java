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
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Adapter Factory that converts between Resolution and ResolutionDataNode and can convert an IResource to
 * a Resolution or ResolutionDataNode.
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
			return (Resolution)((ResolutionDataNode)adaptableObject).getData();
		
		if(adaptableObject instanceof IResource)
		{
			Resolution r = getResolutionFromResource((IResource)adaptableObject);
			if(r == null)
				return null;
			
			if(adapterType.isAssignableFrom(ResolutionDataNode.class))
				return new ResolutionDataNode(r);
			if(adapterType.isAssignableFrom(Resolution.class))
				return r;
		}
			
		// give up
		return null;
	}

	public Resolution getResolutionFromResource(IResource resource)
	{
		while(resource != null)
		{
			try
			{
				CSpec cspec = WorkspaceInfo.getCSpec(resource);
				if(cspec != null)
				{
					// return the resolution for the found cspec
					return WorkspaceInfo.getResolution(cspec.getComponentIdentifier());
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
