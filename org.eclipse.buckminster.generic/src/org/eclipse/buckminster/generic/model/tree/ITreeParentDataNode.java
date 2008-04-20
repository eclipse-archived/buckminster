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

package org.eclipse.buckminster.generic.model.tree;

/**
 * A Tree Data Node that has children.
 * @author Henrik Lindberg
 *
 */
public interface ITreeParentDataNode extends ITreeDataNode
{
		void addChild(ITreeDataNode child);
		void removeChild(ITreeDataNode child);
		void replaceChild(ITreeDataNode oldChild, ITreeDataNode[] newChildren);
		void replaceChild(ITreeDataNode oldChild, ITreeDataNode newChild);
		ITreeDataNode[] getChildren();
		boolean hasChildren();
		public int getChildCount();

		/**
		 * Return the child that represents the data
		 * @param data
		 * @return
		 */
		public ITreeDataNode findChild(Object data);
		void onOpen();
		
}
