/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.core.version;

import java.util.List;

import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public interface IQualifierGenerator
{
	IVersion generateQualifier(IActionContext context, ComponentIdentifier cid, List<ComponentIdentifier> dependencies) throws CoreException;
}
