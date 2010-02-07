/*****************************************************************************
 * Copyright (c) 2007-2009, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.installer;

import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.query.MatchQuery;

/**
 * Queries for IUs which IDs end with ".feature.group"
 * 
 * @author Karel Brezina
 */
public class FeatureQuery extends MatchQuery<IInstallableUnit> {
	public static final String FEATURE_GROUP = ".feature.group"; //$NON-NLS-1$

	@Override
	public boolean isMatch(IInstallableUnit candidate) {
		if (candidate.getId() == null || !candidate.getId().endsWith(FEATURE_GROUP))
			return false;
		return true;
	}
}
