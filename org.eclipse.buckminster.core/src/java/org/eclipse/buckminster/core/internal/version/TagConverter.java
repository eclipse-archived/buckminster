/*******************************************************************************
 * Copyright (c) 2004, 2006
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
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public class TagConverter extends AbstractConverter
{
	public VersionSelector createSelector(IVersion version) throws CoreException
	{
		if(version == null)
			return null;

		String selectorComponent = createSelectorComponent(version);
		return selectorComponent == null
				? null
				: VersionSelector.tag(selectorComponent);
	}

	public IVersion createVersion(VersionSelector versionSelector) throws CoreException
	{
		return versionSelector == null
				? null
				: createVersionFromSelectorComponent(versionSelector.getName());
	}

	@Override
	protected IVersionType getDefaultVersionType()
	{
		return VersionFactory.OSGiType;
	}

	public int getSelectorType()
	{
		return VersionSelector.TAG;
	}
}
