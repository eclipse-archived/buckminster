/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.aggregator.presentation;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.edit.ui.action.ValidateAction;

/**
 * ValidateAction that uses aggregator problem marker
 * 
 * @author Karel Brezina
 */
public class AggregatorValidateAction extends ValidateAction
{
	public static class AggregatorResourcesUtil extends EclipseResourcesUtil
	{
		@Override
		protected String getMarkerID()
		{
			return AggregatorEditor.AGGREGATOR_PERSISTENT_PROBLEM_MARKER;
		}
	}

	{
		eclipseResourcesUtil = EMFPlugin.IS_RESOURCES_BUNDLE_AVAILABLE
				? new AggregatorResourcesUtil()
				: null;
	}
}
