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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A Tree Parent Data Node that is useful as the top node in a data tree. This
 * class supports listening to TreeData events (see ITreeRootNode). When a
 * childNodeChange() has bubbled to this root, a TreeRootNode event is fired.
 * 
 * This enables a view to register a listener for this top node only, and still
 * be notified about any change in the tree.
 * 
 * @author Henrik Lindberg
 * 
 */
public abstract class AbstractTreeRootDataNode extends BasicTreeParentDataNode implements ITreeRootNode {
	private final List<ITreeDataListener> listeners;

	public AbstractTreeRootDataNode(Object data) {
		super(data);
		listeners = new LinkedList<ITreeDataListener>();
	}

	/**
	 * Adds a tree data listener to the set of listeners. A listener is only
	 * added once.
	 */
	@Override
	public void addTreeDataListener(ITreeDataListener listener) {
		if (!listeners.contains(listener))
			listeners.add(listener);
	}

	/**
	 * Whenever a child node has changed, a TreeDataEvent is sent to registered
	 * listeners. Also, if this root has a parent, the event bubbles further up
	 * the chain.
	 */
	@Override
	public void childNodeChanged(ITreeDataNode child) {
		fireTreeDataChanged(child);
		ITreeParentDataNode parent = getParent();
		if (parent != null)
			parent.childNodeChanged(child);
	}

	/**
	 * Removes a tree data listener from the set of listeners. Does nothing if
	 * the listener is not a registered listener.
	 */
	@Override
	public void removeTreeDataListener(ITreeDataListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Fires a tree data change event to register listeners that the changedNode
	 * has changed. The listeners are notified in a UI thread.
	 * 
	 * @param changedNode
	 */
	protected void fireTreeDataChanged(ITreeDataNode changedNode) {
		triggerListeners(TreeDataEvent.changed(this, changedNode));
	}

	/**
	 * Calls listeners in the current thread. A derived class should call this
	 * method from its triggerListener where a suitable runnable for the
	 * environment should have been created.
	 * 
	 * @param e
	 */
	protected void inProcTriggerListeners(final TreeDataEvent e) {
		// copy the listener list or face consequences of event listeners adding
		// or removing
		// listeners when processing events
		ArrayList<ITreeDataListener> listenersCopy = new ArrayList<ITreeDataListener>(listeners);
		for (ITreeDataListener a : listenersCopy)
			a.treeNodeChanged(e);
	}

	/**
	 * Called to trigger listeners - a derived class should create a suitable
	 * thread to update listeners. A non UI implementation that does not need
	 * asynch handling could call the inProcTriggerListeners method directly. An
	 * implementation to use with the UI should perform an asynch exec in the UI
	 * thread and call the inProcTriggerListeners from that thread.
	 * 
	 * @param e
	 */
	abstract protected void triggerListeners(final TreeDataEvent e);

}
