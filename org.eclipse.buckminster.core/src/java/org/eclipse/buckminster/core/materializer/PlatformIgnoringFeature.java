/*******************************************************************
 * Copyright (c) 2009, Versant GmbH.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Versant GmbH. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Versant GmbH.
 ******************************************************************/

package org.eclipse.buckminster.core.materializer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.PluginVersionIdentifier;
import org.eclipse.update.core.Feature;
import org.eclipse.update.core.IFeature;
import org.eclipse.update.core.IPluginEntry;
import org.eclipse.update.core.VersionedIdentifier;

/**
 * @author Markus Alexander Kuppe <buckminster-dev_eclipse.org at lemmster dot de>
 * 
 *         An {@link IFeature} that ignores platform specific information. Thus installs even non matching platform
 *         specific bundles.
 */
@SuppressWarnings("deprecation")
public class PlatformIgnoringFeature extends Feature
{
	public PlatformIgnoringFeature(MaterializationContext aContext)
	{
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.update.core.Feature#getPluginEntries()
	 */
	@Override
	public IPluginEntry[] getPluginEntries()
	{
		IPluginEntry[] pluginEntries = getRawPluginEntries();

		// normally this could simply return pluginEntries, but because of https://bugs.eclipse.org/213437 it filters
		List<IPluginEntry> result = new ArrayList<IPluginEntry>();
		for(int i = 0; i < pluginEntries.length; i++)
		{
			IPluginEntry iPluginEntry = pluginEntries[i];
			VersionedIdentifier versionedIdentifier = iPluginEntry.getVersionedIdentifier();
			String identifier = versionedIdentifier.getIdentifier();
			PluginVersionIdentifier version = versionedIdentifier.getVersion();
			if((identifier.startsWith("org.eclipse.swt.") || identifier.startsWith("org.eclipse.equinox.launcher.")) //$NON-NLS-1$ //$NON-NLS-2$
					&& (version.getMajorComponent() == 0 && version.getMinorComponent() == 0 && version.getServiceComponent() == 0))
			{
				continue;
			}
			result.add(iPluginEntry);
		}
		return result.toArray(new IPluginEntry[result.size()]);
	}
}
