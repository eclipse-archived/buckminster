/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.internal.version.DefaultVersion;

/**
 * @author Thomas Hallgren
 */
public class VersionMatch
{
	public static final VersionMatch DEFAULT = new VersionMatch(DefaultVersion.getInstance(), VersionSelectorFactory.MAIN_LATEST);

	private final IVersion m_version;
	private final IVersionSelector m_fixedVersionSelector;
	
	public VersionMatch(IVersion version, IVersionSelector fixedVersionSelector)
	{
		m_version = version;
		m_fixedVersionSelector = fixedVersionSelector;
	}

	public final IVersionSelector getFixedVersionSelector()
	{
		return m_fixedVersionSelector;
	}

	public final IVersion getVersion()
	{
		return m_version;
	}

	public static VersionMatch latestRightNow()
	{
		return new VersionMatch(VersionFactory.defaultVersion(), VersionSelectorFactory.timestamp(System.currentTimeMillis()));
	}

	@Override
	public String toString()
	{
		return "Version: " + m_version + " fixed at: " + m_fixedVersionSelector;
	}
}
