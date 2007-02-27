/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.pde.internal;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.ctype.EclipseComponentType;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author thhal
 */
@SuppressWarnings("restriction")
public class EclipseInstalledType extends EclipseComponentType
{
	@Override
	public IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor)
	throws CoreException
	{
		monitor.beginTask(null, 5000);
		String category = null;
		if(reader instanceof EclipsePlatformReader)
			category = ((EclipsePlatformReader)reader).getCategory();
		else if(reader instanceof ICatalogReader)
			category = guessCategory((ICatalogReader)reader, monitor);

		if(category == null)
			throw new BuckminsterException("Unable to determine component category");

		String builderId = KeyConstants.PLUGIN_CATEGORY.equals(category) ? IResolutionBuilder.PLUGIN2CSPEC
			: IResolutionBuilder.FEATURE2CSPEC;

		return CorePlugin.getDefault().getResolutionBuilder(builderId);
	}
}
