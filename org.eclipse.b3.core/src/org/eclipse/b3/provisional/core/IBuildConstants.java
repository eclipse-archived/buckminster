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

public interface IBuildConstants {

	/**
	 * Name of the default b3 build context.
	 */
	public final static String DEFAULT_BUILD_CONTEXT = "org.eclipse.b3.core.defaultContext"; //$NON-NLS-1$

	/**
	 * Name of the OSGi-service-property containing the name of a {@link IBuildContext}.
	 */
	public final static String BUILD_CONTEXT_PROPERTY = "org.eclipse.b3.core.prop.buildContext"; //$NON-NLS-1$

}
