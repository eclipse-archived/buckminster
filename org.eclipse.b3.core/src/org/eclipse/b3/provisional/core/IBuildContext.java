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

package org.eclipse.b3.provisional.core;


public interface IBuildContext {
	
	public static final String SERVICE_NAME = IBuildContext.class.getName();

	/**
	 * @return The unique name of this build context.
	 */
	public String getId();
	
	/**
	 * Do stuff for testing
	 * TODO: THIS IS A TEMPORARY TEST METHOD - TO BE REMOVED
	 */
	public void dummyRun();
}
