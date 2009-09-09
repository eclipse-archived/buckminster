/*******************************************************************************
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *     Cloudsmith Inc. - adaption to b3
 *******************************************************************************/
package org.eclipse.b3.provisional.core.eventbus;

import java.util.EventObject;
import org.eclipse.osgi.framework.eventmgr.EventDispatcher;

/**
 * The bus for events related to b3. This service can be used to register
 * a listener to receive b3 events, or to broadcast events.
 */
public interface IBuildEventBus extends EventDispatcher {
	/**
	 * The name used for obtaining a reference to the event bus service.
	 */
	public static final String SERVICE_NAME = IBuildEventBus.class.getName();

	public abstract void addListener(IBuildListener toAdd);

	public abstract void removeListener(IBuildListener toRemove);

	public abstract void publishEvent(EventObject event);

	/**
	 * Closes the event bus.  This will stop dispatching of any events currently
	 * being processed by the bus. Events published after the bus is closed
	 * are ignored.
	 */
	public abstract void close();

}