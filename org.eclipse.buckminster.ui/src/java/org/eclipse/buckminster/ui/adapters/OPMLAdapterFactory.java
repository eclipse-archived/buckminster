/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.opml.model.Outline;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Adapter Factory that converts:
 * 
 * OPML to OPMLDataNode
 * OPMLDataNode to OPML, Resolution, and ResolutionDataNode
 *
 * Resolution to OPML
 * Resolution to OPMLDataNode
 * 
 * Outline to OutlineDataNode
 * OutlineDataNode to Outline, OPML, OPMLDataNode, Resolution, and ResolutionDataNode
 * 
 * @author Henrik Lindberg
 *
 */
public class OPMLAdapterFactory implements IAdapterFactory
{
	@SuppressWarnings("unchecked")
	private static Class[] s_adapterList = { 
			OPML.class, OPMLDataNode.class,
			Outline.class, OutlineDataNode.class,
			Resolution.class, ResolutionDataNode.class 
			};
	
	@SuppressWarnings("unchecked")
	public Object getAdapter(Object adaptableObject, Class adapterType)
	{
		if(adaptableObject instanceof IOPML)
			return fromOPML((IOPML)adaptableObject, adapterType);
		
		if(adaptableObject instanceof IOutline)
			return fromOutline((Outline)adaptableObject, adapterType);
		
		if(adaptableObject instanceof Resolution)
			return fromResolution((Resolution)adaptableObject, adapterType);
		
		if(adaptableObject instanceof OPMLDataNode)
			return fromOPMLDataNode((OPMLDataNode)adaptableObject, adapterType);
		
		if(adaptableObject instanceof OutlineDataNode)
			return fromOutlineDataNode((OutlineDataNode)adaptableObject, adapterType);

		// give up
		return null;
	}

	@SuppressWarnings("unchecked")
	public Object fromOPML(IOPML adapted, Class clazz)
	{
		if(clazz.isAssignableFrom(OPMLDataNode.class))
			return new OPMLDataNode(adapted);
		
		if(clazz.isAssignableFrom(OPML.class))
			return adapted;
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public Object fromOutline(Outline adapted, Class clazz)
	{
		if(clazz.isAssignableFrom(OutlineDataNode.class))
			return new OutlineDataNode(adapted);
		
		if(clazz.isAssignableFrom(Outline.class))
			return adapted;
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public Object fromResolution(Resolution adapted, Class clazz)
	{
		if(clazz.isAssignableFrom(ResolutionDataNode.class))
			return new ResolutionDataNode(adapted);
		
		if(clazz.isAssignableFrom(OPML.class))
			return adapted.getOPML();
		
		if(clazz.isAssignableFrom(OPMLDataNode.class))
			{
			IOPML opml = adapted.getOPML();
			return opml == null ? null : new OPMLDataNode(opml);
			}

		if(clazz.isAssignableFrom(Resolution.class))
			return adapted;
		
		return null;
	}
	@SuppressWarnings("unchecked")
	public Object fromOPMLDataNode(OPMLDataNode adapted, Class clazz)
	{
		// No Adaption
		if(clazz.isAssignableFrom(OPMLDataNode.class))
			return adapted;

		// OPML
		if(clazz.isAssignableFrom(OPML.class))
			return adapted.getData(); // an OPML
		
		// The remaining adaptions need a parent that is a Resolution
		//
		ITreeParentDataNode parent = adapted.getParent();
		if(parent == null || !(parent instanceof ResolutionDataNode))
			return null;
		
		// ResolutionDataNode
		if(clazz.isAssignableFrom(ResolutionDataNode.class))
			return parent;
		
		// Resolution
		if(clazz.isAssignableFrom(Resolution.class))
			return parent.getData(); // a Resolution
				
		return null;
	}
	@SuppressWarnings("unchecked")
	public Object fromOutlineDataNode(OutlineDataNode adapted, Class clazz)
	{
		// no adaption
		if(clazz.isAssignableFrom(OutlineDataNode.class))
			return adapted;

		// Outline
		if(clazz.isAssignableFrom(Outline.class))
			return adapted.getData(); // an Outline

		// The remaining adaptions need a parent that is an OPML
		//
		ITreeParentDataNode parent = adapted.getParent();
		while(parent != null && !(parent instanceof OPMLDataNode))
			parent = parent.getParent();
		
		if(parent == null || !(parent instanceof OPMLDataNode))
			return null;
		
		// OPML
		if(clazz.isAssignableFrom(OPML.class))
			return parent.getData(); // an OPML

		// OPMLDataNode
		if(clazz.isAssignableFrom(OPMLDataNode.class))
			return parent; // an OPMLDataNode
		
		// The remaining adaptions need a parent of the OPMLDataNode that is a Resolution
		//
		parent = parent.getParent();
		if(parent == null || !(parent instanceof ResolutionDataNode))
			return null;
		
		// ResolutionDataNode
		if(clazz.isAssignableFrom(ResolutionDataNode.class))
			return parent;
		
		// Resolution
		if(clazz.isAssignableFrom(Resolution.class))
			return parent.getData(); // a Resolution
		
		return null;
	}
		
	@SuppressWarnings("unchecked")
	public Class[] getAdapterList()
	{
		return s_adapterList;
	}

}
