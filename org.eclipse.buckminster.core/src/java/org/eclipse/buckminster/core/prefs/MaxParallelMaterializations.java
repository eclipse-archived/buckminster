/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.prefs;

import org.eclipse.buckminster.cmdline.BasicPreferenceHandler;
import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.materializer.MaterializationJob;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.osgi.util.NLS;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author thhal
 * 
 */
public class MaxParallelMaterializations extends BasicPreferenceHandler {
	@Override
	public String get(String defaultValue) throws CoreException {
		return Integer.toString(MaterializationJob.getMaxParallelJobs());
	}

	@Override
	public void set(String prefValue) throws BackingStoreException {
		try {
			int maxJobs = Integer.parseInt(prefValue);
			if (maxJobs > 0 && maxJobs <= 20) {
				MaterializationJob.setMaxParallelJobs(maxJobs);
				BuckminsterPreferences.getNode().flush();
				return;
			}
		} catch (NumberFormatException e) {
		}
		throw new IllegalArgumentException(String.format(NLS.bind(Messages._0_illegal_value_for_maxParallelMaterialisations, prefValue)));

	}

	@Override
	public void unset() throws BackingStoreException {
		MaterializationJob.setMaxParallelJobs(MaterializationJob.MAX_PARALLEL_JOBS_DEFAULT);
		BuckminsterPreferences.getNode().flush();
	}
}
