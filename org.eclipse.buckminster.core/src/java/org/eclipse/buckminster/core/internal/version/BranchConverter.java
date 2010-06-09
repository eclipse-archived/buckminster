/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.internal.version;

import org.eclipse.buckminster.core.version.AbstractConverter;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.p2.metadata.IVersionFormat;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionFormatException;

/**
 * @author Thomas Hallgren
 */
public class BranchConverter extends AbstractConverter {
	@Override
	public VersionSelector createSelector(Version version) throws CoreException {
		if (version == null)
			return null;

		String selectorComponent = createSelectorComponent(version);
		return selectorComponent == null ? null : VersionSelector.branch(selectorComponent);
	}

	@Override
	public Version createVersion(VersionSelector versionSelector) throws CoreException {
		if (versionSelector == null)
			return null;

		String name = versionSelector.getName();
		if (name.equals(VersionSelector.DEFAULT_BRANCH))
			return null;

		return createVersionFromSelectorComponent(name);
	}

	@Override
	public int getSelectorType() {
		return VersionSelector.BRANCH;
	}

	@Override
	protected IVersionFormat getDefaultVersionFormat() {
		try {
			return Version.compile("S"); //$NON-NLS-1$
		} catch (VersionFormatException e) {
			return null;
		}
	}
}
