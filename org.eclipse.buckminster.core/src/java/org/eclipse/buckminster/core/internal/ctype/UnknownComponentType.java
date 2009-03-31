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

import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.internal.provisional.p2.core.Version;

/**
 * @author thhal
 */
@SuppressWarnings("restriction")
public class UnknownComponentType extends AbstractComponentType
{
	private static final DefaultResolutionBuilder s_defaultBuilder = new DefaultResolutionBuilder();

	@Override
	public Version getComponentVersion(ProviderMatch rInfo, IProgressMonitor monitor) throws CoreException
	{
		return null;
	}

	public IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor)
			throws CoreException
	{
		ProviderMatch pm = reader.getProviderMatch();
		return (pm.getMatcherMap() == null)
				? s_defaultBuilder
				: new URIMatcherBuilder(pm);
	}
}
