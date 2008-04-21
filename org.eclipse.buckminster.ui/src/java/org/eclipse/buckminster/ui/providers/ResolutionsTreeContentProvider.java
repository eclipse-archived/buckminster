/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.providers;

import java.util.List;

import org.eclipse.buckminster.core.cspec.model.CSpec;

import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.generic.model.tree.BasicTreeDataNode;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.generic.ui.model.tree.UISafeTreeRootDataNode;
import org.eclipse.buckminster.generic.ui.providers.TreeDataNodeContentProvider;
import org.eclipse.buckminster.opml.model.Body;
import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.opml.model.Outline;
import org.eclipse.jface.viewers.Viewer;

/**
 * Provides all resolutions as a tree.
 * 
 * @author Henrik Lindberg
 *
 */
public class ResolutionsTreeContentProvider extends TreeDataNodeContentProvider
{
	public static class ResolutionDataNode extends BasicTreeParentDataNode
	{

		public ResolutionDataNode(Resolution data)
		{
			super(data);
			// add cspec child node
			addChild(new CSpecDataNode(data.getCSpec()));
			
			// add opml child node
			OPML opml = data.getOPML();
			if(opml != null)
				addChild(new OPMLDataNode(data.getOPML()));
		}		
	}

	public static class CSpecDataNode extends BasicTreeDataNode
	{

		public CSpecDataNode(CSpec data)
		{
			super(data);
		}
	}
	public static class OPMLDataNode extends BasicTreeParentDataNode
	{

		public OPMLDataNode(OPML data)
		{
			super(data == null ? "no opml" : data);
			Body body = data.getBody();
			for(Outline outline : body.getOutlines())
			{
				addChild(new OutlineDataNode(outline));
			}
		}
	}
	public static class OutlineDataNode extends BasicTreeDataNode
	{

		public OutlineDataNode(Outline data)
		{
			super(data);
			List<Outline> outlines = data.getOutlines();
		}
		
	}
	@Override
	protected void initialize()
	{		
		UISafeTreeRootDataNode hiddenRoot = new UISafeTreeRootDataNode("resolutions");
		setHiddenRoot(hiddenRoot);
	}
	
	/**
	 * When the input is a single Resolution, or a List<Resolution> a tree is produced.
	 * Changing input first removed the entire current tree, and a new tree is erected.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
	{
		super.inputChanged(viewer, oldInput, newInput);
		ITreeParentDataNode root = getHiddenRoot();
		if(root == null)
		{
			initialize();
			root = getHiddenRoot();
		}
		root.removeAllChildren();
		if(newInput instanceof List)
		{
			List<Resolution> resolutions = (List<Resolution>)newInput;
			if(resolutions.size() < 1)
				return; // empty
			for(Resolution r: resolutions)
				root.addChild(new ResolutionDataNode(r));
			return;
		}
		if(newInput instanceof Resolution)
			root.addChild(new ResolutionDataNode((Resolution)newInput));
	}
}
