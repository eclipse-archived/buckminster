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

package org.eclipse.b3.internal.singlesetup;

import java.util.EventObject;

import org.eclipse.b3.internal.core.BuildEventBus;
import org.eclipse.b3.provisional.core.IBuildConstants;
import org.eclipse.b3.provisional.core.IBuildContext;
import org.eclipse.b3.provisional.core.eventbus.IBuildListener;

public class DefaultBuildContext extends BuildEventBus implements IBuildContext {

	public DefaultBuildContext() {
		// TODO: initialize with stuff from IDE
	}

	/**
	 * Add a listener that gets all events
	 * 
	 * @param listener
	 */
	public void addDebugListener(IBuildListener listener) {
		super.addListener(listener);
	}

	public void dummyRun() {
		EventObject eo = new EventObject(this);
		// Send some events
		for (int i = 0; i < 10; i++)
			publishEvent(eo);
	}

	public String getId() {
		return IBuildConstants.DEFAULT_BUILD_CONTEXT;
	}

	public void removeDebugListener(IBuildListener listener) {
		super.removeListener(listener);
	}
}
