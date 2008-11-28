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

package org.eclipse.buckminster.generic.ui.providers;

import org.eclipse.buckminster.generic.model.tree.ITreeDataListener;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeRootNode;
import org.eclipse.buckminster.generic.model.tree.TreeDataEvent;
import org.eclipse.buckminster.generic.ui.Messages;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;

/**
 * The TreeDataNodeContentProvider uses an ITreeRootNode to hold the tree's data. 
 * 
 * This content provider registers itself as a listener to change in the ITreeRootNode and refreshes
 * the view when events occur. A UI safe implementation of ITreeRootNode must be used.
 * 
 * @author Henrik Lindberg
 *
 */
public abstract class TreeDataNodeContentProvider implements IStructuredContentProvider, ITreeContentProvider, ITreeDataListener
{
		private ITreeRootNode m_invisibleRoot;
		private TreeViewer m_treeViewer;
		
		public void inputChanged(Viewer v, Object oldInput, Object newInput)
		{
			if(!(v instanceof TreeViewer))
				throw new IllegalArgumentException(Messages.only_TreeView_accepted);
			m_treeViewer = (TreeViewer)v;
		}

		public void treeNodeChanged(TreeDataEvent event)
		{
			switch(event.getType())
			{
			case CHANGE:
				// if the changed node is the root node - refresh the entire tree
				if(event.getNode() instanceof ITreeRootNode)
					m_treeViewer.refresh();
				else
					m_treeViewer.refresh(event.getNode());
				break;
			}
		}
			
		public void dispose()
		{
			m_invisibleRoot.removeTreeDataListener(this);
		}

		public Object[] getElements(Object parent)
		{
			// if asking for the data that was used to build a tree, then return the root holding the tree.
			// the previous version used the ViewSite as this outermost parent
			// but that only works for a content provider where the tree always shows the same content
			//
			if(!(parent instanceof ITreeDataNode)) // Was (parent instanceof IViewSite)
			{
				if(m_invisibleRoot == null)
					initialize();
				return getChildren(m_invisibleRoot);
			}
			return getChildren(parent);
		}

		public Object getParent(Object child)
		{
			if(child instanceof ITreeDataNode)
			{
				return ((ITreeDataNode)child).getParent();
			}
			return null;
		}

		public Object[] getChildren(Object parent)
		{
			if(parent instanceof ITreeParentDataNode)
			{
				return ((ITreeParentDataNode)parent).getChildren();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent)
		{
			if(parent instanceof ITreeParentDataNode)
				return ((ITreeParentDataNode)parent).hasChildren();
			return false;
		}

		/* Initialize the hidden root. Should call setHiddenRoot() with the root to use.
		 */
		protected abstract void initialize();
		
		protected ITreeParentDataNode getHiddenRoot()
		{
			return m_invisibleRoot;
		}
		
		protected void setHiddenRoot(ITreeRootNode hiddenRoot)
		{
			if(m_invisibleRoot != null)
				m_invisibleRoot.removeTreeDataListener(this);
			m_invisibleRoot = hiddenRoot;
			m_invisibleRoot.addTreeDataListener(this);
		}
	}

