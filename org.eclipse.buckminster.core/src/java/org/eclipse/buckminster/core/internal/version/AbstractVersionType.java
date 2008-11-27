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

import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionSyntaxException;

/**
 * @author Thomas Hallgren
 * 
 */
public abstract class AbstractVersionType extends AbstractExtension implements IVersionType
{
	public IVersion coerce(Object object)
	{
		try
		{
			return fromString(object.toString());
		}
		catch(VersionSyntaxException e)
		{
			return null;
		}
	}

	public IVersion fromString(String versionString) throws VersionSyntaxException
	{
		return this.fromString(versionString, 0, new int[1]);
	}

	public boolean isComparableTo(IVersionType other)
	{
		return this == other;
	}
}
