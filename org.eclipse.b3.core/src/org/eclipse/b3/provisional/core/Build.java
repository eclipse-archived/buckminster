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

import org.eclipse.b3.internal.core.BuildBundle;

public class Build {
		
	/**
	 * Get the default build context. If there are multiple contexts, one is always considered
	 * to be the default. A typical configuration in the IDE will only have one default context
	 * for the build material in the workspace. Multiple contexts may be possible in some server
	 * configuration, or when tools are operating on build models for editing/transformation purposes. 
	 * 
	 * Note that build contexts are OSGi services and are highly dynamic. Use a tracker if
	 * long term use of the context is required.
	 * 
	 * @return the default b3 build context, or null, if no such context exists.
	 */
	public  static IBuildContext getDefaultBuildContext() {
		return BuildBundle.getDefault().getDefaultBuildContext();
	}
	
	public static ILogger getLogger()
	{
		return BuildBundle.getDefault().getBundleLogger();
	}
}
