/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.providers;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.generic.model.tree.BasicTreeDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.PendingTreeDataNode;
import org.eclipse.buckminster.generic.ui.model.tree.UISafeTreeRootDataNode;
import org.eclipse.buckminster.generic.ui.providers.TreeDataNodeContentProvider;
import org.eclipse.buckminster.ui.adapters.ResolutionDataNode;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IViewSite;

/**
 * Provides one or several instances of Resolution as a Tree.
 * 
 * @author Henrik Lindberg
 *
 */
public class ResolutionsTreeContentProvider extends TreeDataNodeContentProvider
{
	/**
	 * A node that expands itself into a tree of all resolutions in a background thread.
	 * @author henrik
	 *
	 */
	public static class AllResolutionsNode extends PendingTreeDataNode
	{

		@Override
		public ITreeDataNode[] createNode(IProgressMonitor monitor)
		{
			monitor.worked(1);
			List<Resolution> resolutions;
			try
			{
				resolutions = WorkspaceInfo.getAllResolutions();
			}
			catch(CoreException e)
			{
				resolutions = new ArrayList<Resolution>(0);
				e.printStackTrace();
			}
			int size = resolutions.size();
			if(size == 0)
			{
				ITreeDataNode[] empty = new ITreeDataNode[1];
				empty[0] = new BasicTreeDataNode("No components found");
				return empty;
			}
			ITreeDataNode[] result = new ITreeDataNode[size];
			int i = 0;
			for(Resolution r: resolutions)
			{
				result[i++] = new ResolutionDataNode(r);
				monitor.worked(1);
			}
			return result;
		}		
	}
	
	/**
	 * Initializes the content provider with a tree root that delivers events in a UI safe way.
	 */
	@Override
	protected void initialize()
	{		
		UISafeTreeRootDataNode hiddenRoot = new UISafeTreeRootDataNode("resolutions");
		setHiddenRoot(hiddenRoot);
	}
	
	/**
	 * When the input is a single Resolution, or a List<Resolution> a tree is produced.
	 * Changing input first removes the entire current tree, and a new tree is erected.
	 * If the newInput is an instance of IViewSite all resolutions are collected in the
	 * background.
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
		if(newInput instanceof ITreeDataNode)
			root.addChild((ITreeDataNode)newInput);

		// if the node added to the hidden root is a pending node - start expanding it now in the background.
		if(newInput instanceof IViewSite)
		{
			AllResolutionsNode pending = new ResolutionsTreeContentProvider.AllResolutionsNode();
			root.addChild(pending);
			pending.schedule("getting resolutions");
		}
	}
}
