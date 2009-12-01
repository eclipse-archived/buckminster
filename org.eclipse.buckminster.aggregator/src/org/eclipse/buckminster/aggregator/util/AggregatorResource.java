/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.util;

import java.util.Map;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.xmi.XMIResource;

/**
 * @author Karel Brezina
 * 
 */
public interface AggregatorResource extends XMIResource
{
	/**
	 * The {@link #getInfos} feature {@link org.eclipse.emf.common.notify.Notification#getFeatureID ID}.
	 */
	int RESOURCE__INFOS = 1001;

	int RESOURCE__ANALYSIS_STARTED = 1002;

	int RESOURCE__ANALYSIS_FINISHED = 1003;

	/**
	 * Analysis aggregator errors, warnings & infos
	 */
	void analyzeResource();

	/**
	 * Returns a list of the infos in the resource; each error will be of type
	 * {@link org.eclipse.emf.ecore.resource.Resource.Diagnostic}.
	 * 
	 * @return a list of the infos in the resource.
	 * @see #load(Map)
	 */
	EList<Diagnostic> getInfos();
}
