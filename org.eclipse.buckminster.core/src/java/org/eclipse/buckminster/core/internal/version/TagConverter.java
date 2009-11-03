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
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.metadata.Version;
import org.eclipse.equinox.internal.provisional.p2.metadata.VersionFormat;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class TagConverter extends AbstractConverter
{
	public VersionSelector createSelector(Version version) throws CoreException
	{
		if(version == null)
			return null;

		String selectorComponent = createSelectorComponent(version);
		return selectorComponent == null
				? null
				: VersionSelector.tag(selectorComponent);
	}

	public Version createVersion(VersionSelector versionSelector) throws CoreException
	{
		return versionSelector == null
				? null
				: createVersionFromSelectorComponent(versionSelector.getName());
	}

	public int getSelectorType()
	{
		return VersionSelector.TAG;
	}

	@Override
	protected VersionFormat getDefaultVersionFormat()
	{
		return VersionFormat.OSGI_FORMAT;
	}
}
