/*******************************************************************************
 * Copyright (c) 2009, Cloudsmith Inc and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Cloudsmith Inc - initial API and implementation
 *******************************************************************************/
package org.eclipse.b3.provisional.core.eventbus;

import java.util.EventListener;
import java.util.EventObject;

/**
 * A listener that is notified about events related to b3.
 * @see IBuildEventBus
 */
public interface IBuildListener extends EventListener {

	/**
	 * Notifies the listener about a b3 event.
	 */
	public void notify(EventObject o);
}
