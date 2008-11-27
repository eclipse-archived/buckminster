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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.internal.version.VersionDesignator;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 * 
 */
public class VersionFactory
{
	public static final IVersionType StringType;

	public static final IVersionType OSGiType;

	public static final IVersionType TripletType;

	public static final IVersionType TimestampType;

	static
	{
		try
		{
			OSGiType = CorePlugin.getDefault().getVersionType(IVersionType.OSGI);
			TimestampType = CorePlugin.getDefault().getVersionType(IVersionType.TIMESTAMP);
			StringType = CorePlugin.getDefault().getVersionType(IVersionType.STRING);
			TripletType = CorePlugin.getDefault().getVersionType(IVersionType.TRIPLET);
		}
		catch(CoreException e)
		{
			throw new ExceptionInInitializerError(e);
		}
	}

	public static IVersionDesignator createDesignator(IVersionType versionType, String versionString)
			throws VersionSyntaxException
	{
		return VersionDesignator.fromString(versionType, versionString);
	}

	public static IVersionDesignator createDesignator(String versionType, String versionString) throws CoreException
	{
		return createDesignator(CorePlugin.getDefault().getVersionType(versionType), versionString);
	}

	public static IVersionDesignator createExplicitDesignator(IVersion version)
	{
		return VersionDesignator.explicit(version);
	}

	public static IVersionDesignator createExplicitDesignator(IVersionType versionType, String versionString)
			throws VersionSyntaxException
	{
		return VersionDesignator.explicitFromString(versionType, versionString);
	}

	public static IVersionDesignator createExplicitDesignator(String versionType, String versionString)
			throws CoreException
	{
		return createExplicitDesignator(CorePlugin.getDefault().getVersionType(versionType), versionString);
	}

	public static IVersionDesignator createGTEqualDesignator(IVersion version)
	{
		return VersionDesignator.GTEqual(version);
	}

	public static IVersionDesignator createRangeDesignator(IVersion low, boolean includeLow, IVersion high,
			boolean includeHigh)
	{
		return VersionDesignator.create(low, includeLow, high, includeHigh);
	}

	public static IVersion createVersion(String versionType, String versionString) throws CoreException
	{
		IVersionType vt = CorePlugin.getDefault().getVersionType(versionType);
		return vt.fromString(versionString);
	}
}
