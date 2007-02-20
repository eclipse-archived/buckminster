/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.internal.ctype;

import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.helpers.AbstractComponentType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
public class UnknownComponentType extends AbstractComponentType
{
	private static final DefaultResolutionBuilder s_defaultBuilder = new DefaultResolutionBuilder();

	public static IResolutionBuilder getDefaultCSpecPackingBuilder()
	{
		return s_defaultBuilder;
	}

	public IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return getDefaultCSpecPackingBuilder();
	}
}

