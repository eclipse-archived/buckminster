/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.actor;

import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.IAttribute;
import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
 * @author kolwing
 * @author Thomas Hallgren
 */
public interface IPerformManager
{
	IGlobalContext perform(ICSpecData cspec, String attributeName, Map<String, String> props, boolean forced, boolean quiet, IProgressMonitor monitor) throws CoreException;

	IGlobalContext perform(List<? extends IAttribute> attributes, Map<String, String> props, boolean forced, boolean quiet, IProgressMonitor monitor) throws CoreException;

	IStatus perform(List<? extends IAttribute> attributes, IGlobalContext globalCtx, IProgressMonitor monitor) throws CoreException;
}
