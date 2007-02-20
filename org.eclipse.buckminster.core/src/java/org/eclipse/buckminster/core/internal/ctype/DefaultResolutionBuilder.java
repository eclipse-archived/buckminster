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

import org.eclipse.buckminster.core.cspec.AbstractResolutionBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;


/**
 * @author thhal
 */
public class DefaultResolutionBuilder extends AbstractResolutionBuilder
{
	public DepNode build(IComponentReader[] readerHandle, IProgressMonitor monitor)
	throws CoreException
	{
		IComponentReader reader = readerHandle[0];
		CSpecBuilder bld = reader.getProviderMatch().createCSpec();
		return createResolution(reader, applyExtensions(bld.createCSpec(), reader, monitor));
	}
}
