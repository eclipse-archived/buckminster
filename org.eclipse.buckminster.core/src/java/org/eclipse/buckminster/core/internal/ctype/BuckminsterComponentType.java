/*******************************************************************************
 * Copyright (c) 2004 - 2007
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.internal.ctype;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.ctype.BuckminsterCSpecBuilder;
import org.eclipse.buckminster.core.ctype.IResolutionBuilder;
import org.eclipse.buckminster.core.reader.ICatalogReader;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;

/**
 * @author Thomas Hallgren
 */
public class BuckminsterComponentType extends AbstractComponentType {
	private static final IResolutionBuilder builder = new BuckminsterCSpecBuilder();

	public IResolutionBuilder getResolutionBuilder(IComponentReader reader, IProgressMonitor monitor) throws CoreException {
		// Not that we cannot look for a cquery at the very top. If we did, then
		// we
		// would end up in an endless recursion since an attempt to resolve it
		// would
		// just find it again.
		//
		if (reader instanceof ICatalogReader) {
			NodeQuery query = reader.getNodeQuery();
			boolean atTop = query.getComponentRequest().equals(query.getComponentQuery().getExpandedRootRequest(query.getContext()));
			if (!atTop) {
				return ((ICatalogReader) reader).exists(CorePlugin.CQUERY_FILE, monitor) ? CorePlugin.getDefault().getResolutionBuilder(
						IResolutionBuilder.CQUERY2BOM) : builder; // No need
																	// to
																	// complete
																	// monitor
			}
		}
		MonitorUtils.complete(monitor);
		return builder;
	}
}
