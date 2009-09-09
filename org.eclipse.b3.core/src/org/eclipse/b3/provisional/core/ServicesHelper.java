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

import java.util.Dictionary;
import java.util.Hashtable;

import org.eclipse.b3.internal.core.BuildBundle;
import org.osgi.framework.Constants;
import org.osgi.framework.Filter;
import org.osgi.framework.InvalidSyntaxException;

public class ServicesHelper {
	
	/**
	 * Returns a tracker filter string for the build context and class.
	 * @param buildContext
	 * @param clazz
	 * @return a tracker filter string
	 * @throws InvalidSyntaxException 
	 */
	public static Filter getTrackerFilter(IBuildContext buildContext, Class<?> clazz) throws InvalidSyntaxException {
		return getTrackerFilter(buildContext.getId(), clazz.getName());
	}
	
	public static Filter getTrackerFilter(String buildContext, Class<?> clazz) throws InvalidSyntaxException {
		return getTrackerFilter(buildContext, clazz.getName());
	}
	
	/**
	 * Returns an tracker filter string for the build context and class
	 * @param buildContext
	 * @param clazz
	 * @return a tracker filter string
	 * @throws InvalidSyntaxException 
	 */
	private static Filter getTrackerFilter(String buildContext, String clazz) throws InvalidSyntaxException
	{
		StringBuffer buf = new StringBuffer(40);
		buf.append("(&"); //$NON-NLS-1$

		buf.append("("); //$NON-NLS-1$
		buf.append(Constants.OBJECTCLASS);
		buf.append("="); //$NON-NLS-1$
		buf.append(clazz.toString());
		buf.append(")"); //$NON-NLS-1$
		
		buf.append("("); //$NON-NLS-1$
		buf.append(IBuildConstants.BUILD_CONTEXT_PROPERTY);
		buf.append("="); //$NON-NLS-1$
		buf.append(buildContext);
		buf.append(")"); //$NON-NLS-1$
		
		buf.append(")"); //$NON-NLS-1$
		return BuildBundle.getDefault().getBundle().getBundleContext().createFilter(buf.toString());
	}

	public static Dictionary<String, String> getBuildContextProperties(IBuildContext buildContext)
	{
		Dictionary<String, String> d = new Hashtable<String, String>();
		d.put(IBuildConstants.BUILD_CONTEXT_PROPERTY, buildContext.getId());
		return d;
	}
}
