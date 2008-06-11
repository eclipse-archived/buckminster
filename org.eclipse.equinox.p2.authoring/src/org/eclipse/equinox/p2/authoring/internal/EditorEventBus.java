/*******************************************************************************
 * Copyright (c) 2008
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed below, as Initial Contributors under such license.
 * The text of such license is available at 
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 * 		IBM Corporation - initial API and implementation
 * 		Henrik Lindberg
 *******************************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

import java.util.EventObject;

import org.eclipse.osgi.framework.eventmgr.EventDispatcher;
import org.eclipse.osgi.framework.eventmgr.EventListeners;
import org.eclipse.osgi.framework.eventmgr.EventManager;
import org.eclipse.osgi.framework.eventmgr.ListenerQueue;

/**
 * Default implementation of the {@link IEditorEventBus}.
 */
public class EditorEventBus implements EventDispatcher, IEditorEventBus {
	private EventListeners syncListeners = new EventListeners();
	private EventListeners asyncListeners = new EventListeners();
	private EventManager eventManager = new EventManager("Editor Event Dispatcher"); //$NON-NLS-1$

	public void addListener(IEditorListener listener) {
		if (listener instanceof IAsynchronousEditorListener) {
			synchronized (asyncListeners) {
				asyncListeners.addListener(listener, listener);
			}
		} else {
			synchronized (syncListeners) {
				syncListeners.addListener(listener, listener);
			}
		}
	}

	public void removeListener(IEditorListener listener) {
		if (listener instanceof IAsynchronousEditorListener) {
			synchronized (syncListeners) {
				if (asyncListeners != null) {
					asyncListeners.removeListener(listener);
				}
			}
		} else {
			synchronized (asyncListeners) {
				if (syncListeners != null) {
					syncListeners.removeListener(listener);
				}
			}
		}
	}

	public void publishEvent(EventObject event) {
		ListenerQueue listeners = new ListenerQueue(eventManager);

		/* synchronize while building the listener list */
		synchronized (syncListeners) {
			/* add set of BundleContexts w/ listeners to queue */
			listeners.queueListeners(syncListeners, this);
			/* synchronously dispatch to populate listeners queue */
			listeners.dispatchEventSynchronous(0, event);
		}

		listeners = new ListenerQueue(eventManager);
		synchronized (asyncListeners) {
			listeners.queueListeners(asyncListeners, this);
			listeners.dispatchEventAsynchronous(0, event);
		}
	}

	public void dispatchEvent(Object eventListener, Object listenerObject, int eventAction, Object eventObject) {
		try {
			((IEditorListener) eventListener).notify((EventObject) eventObject);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: log problem
		}
	}

	public void close() {
		eventManager.close();
	}
}
