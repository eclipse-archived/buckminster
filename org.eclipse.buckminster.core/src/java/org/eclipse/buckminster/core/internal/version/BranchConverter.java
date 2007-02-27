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
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionSelectorType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSelectorFactory;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class BranchConverter extends AbstractConverter
{
	public IVersionSelector createSelector(IVersion version) throws CoreException
	{
		if(version == null || version.equals(VersionFactory.defaultVersion()))
			return VersionSelectorFactory.MAIN_LATEST;

		String selectorComponent = this.createSelectorComponent(version);
		return selectorComponent == null
			? VersionSelectorFactory.MAIN_LATEST
			: VersionSelectorFactory.latest(selectorComponent);
	}

	public IVersion createVersion(IVersionType versionType, IVersionSelector versionSelector) throws CoreException
	{
		if(versionSelector == null || versionSelector.equals(VersionSelectorFactory.MAIN_LATEST))
			return DefaultVersion.getInstance();
		return this.createVersionFromSelectorComponent(versionType, versionSelector.getBranchName());
	}

	public VersionSelectorType getType()
	{
		return VersionSelectorType.LATEST;
	}
}
