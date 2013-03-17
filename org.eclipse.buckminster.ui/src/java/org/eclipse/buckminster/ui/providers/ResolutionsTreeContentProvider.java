/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.generic.model.tree.BasicTreeDataNode;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.PendingTreeDataNode;
import org.eclipse.buckminster.generic.ui.model.tree.UISafeTreeRootDataNode;
import org.eclipse.buckminster.generic.ui.providers.TreeDataNodeContentProvider;
import org.eclipse.buckminster.ui.Messages;
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
public class ResolutionsTreeContentProvider extends TreeDataNodeContentProvider {
	/**
	 * A node that expands itself into a tree of all resolutions in a background
	 * thread.
	 * 
	 * @author Henrik Lindberg
	 * 
	 */
	public static class AllResolutionsNode extends PendingTreeDataNode {

		@Override
		public ITreeDataNode[] createNode(IProgressMonitor monitor) {
			monitor.worked(1);
			List<Resolution> resolutions;
			try {
				resolutions = WorkspaceInfo.getAllResolutions();
			} catch (CoreException e) {
				resolutions = new ArrayList<Resolution>(0);
				e.printStackTrace();
			}
			int size = resolutions.size();
			if (size == 0) {
				ITreeDataNode[] empty = new ITreeDataNode[1];
				empty[0] = new BasicTreeDataNode(Messages.no_components_found);
				return empty;
			}
			ITreeDataNode[] result = new ITreeDataNode[2];
			ITreeParentDataNode ws = new BasicTreeParentDataNode(Messages.workspace_components);
			ITreeParentDataNode tp = new BasicTreeParentDataNode(Messages.target_platform_components);
			result[0] = ws;
			result[1] = tp;

			for (Resolution r : resolutions) {
				// Divide nodes on target platform and workspace parent nodes
				ResolutionDataNode rd = new ResolutionDataNode(r);
				if (IReaderType.ECLIPSE_PLATFORM.equals(r.getProvider().getReaderTypeId()))
					tp.addChild(rd);
				else
					ws.addChild(rd);
				monitor.worked(1);
			}
			return result;
		}
	}

	public enum Mode {
		ALL, SINGLE, ;
	}

	Mode mode;

	public ResolutionsTreeContentProvider() {
		this(Mode.ALL);
	}

	public ResolutionsTreeContentProvider(Mode mode) {
		this.mode = mode;
	}

	/**
	 * When the input is a single Resolution, or a List<Resolution> a tree is
	 * produced. Changing input first removes the entire current tree, and a new
	 * tree is erected. If the newInput is an instance of IViewSite all
	 * resolutions are collected in the background.
	 */
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		// if nothing changes
		if (oldInput == newInput && !(newInput instanceof IViewSite))
			return;

		super.inputChanged(viewer, oldInput, newInput);
		ITreeParentDataNode root = getHiddenRoot();
		if (root == null) {
			initialize();
			root = getHiddenRoot();
		}
		if (newInput instanceof List<?>) {
			root.removeAllChildren();
			@SuppressWarnings("unchecked")
			List<Resolution> resolutions = (List<Resolution>) newInput;
			if (resolutions.size() < 1)
				return; // empty
			for (Resolution r : resolutions)
				root.addChild(new ResolutionDataNode(r));
			return;
		}
		if (newInput instanceof Resolution) {
			ITreeDataNode[] children = root.getChildren();
			if (!(children.length > 0 && children[0] instanceof ResolutionDataNode && newInput.equals(((ResolutionDataNode) children[0]).getData()))) {
				root.removeAllChildren();
				root.addChild(new ResolutionDataNode((Resolution) newInput));
			}
		}
		if (newInput instanceof ITreeDataNode) {
			root.removeAllChildren();
			root.addChild((ITreeDataNode) newInput);
		}
		// if the node added to the hidden root is a pending node - start
		// expanding it now in the background.
		if (newInput instanceof IViewSite) {
			root.removeAllChildren();
			if (mode == Mode.ALL) {
				AllResolutionsNode pending = new ResolutionsTreeContentProvider.AllResolutionsNode();
				root.addChild(pending);
				pending.schedule(Messages.getting_resolutions);
			} else {
				root.addChild(new BasicTreeDataNode(Messages.nothing_to_display_with_dot));
			}
		}
	}

	/**
	 * Initializes the content provider with a tree root that delivers events in
	 * a UI safe way.
	 */
	@Override
	protected void initialize() {
		UISafeTreeRootDataNode hiddenRoot = new UISafeTreeRootDataNode(Messages.resolutions);
		setHiddenRoot(hiddenRoot);
	}
}
