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

import org.eclipse.buckminster.core.version.AbstractVersion;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionSyntaxException;

/**
 * @author Thomas Hallgren
 */
public class DefaultVersion extends AbstractVersion
{
	public static final String NAME = "DEFAULT";
	public static final String TYPE_NAME = "default";

	public static final IVersionType TYPE = new AbstractVersionType()
	{
		public IVersion fromString(String versionString, int startPos, int[] endPos) throws VersionSyntaxException
		{
			if(versionString.substring(startPos).startsWith(DefaultVersion.NAME))
			{
				endPos[0] = startPos + DefaultVersion.NAME.length();
				return DefaultVersion.s_defaultVersion;
			}
			throw new VersionSyntaxException("Not default", versionString, startPos);
		}

		@Override
		public String getId()
		{
			return TYPE_NAME;
		}
	};

	static final IVersion s_defaultVersion = new DefaultVersion(TYPE);

	private DefaultVersion(IVersionType type)
	{
		super(type);
	}

	public static IVersion getInstance()
	{
		return s_defaultVersion;
	}

	/**
	 * The default version is greater than all other versions and
	 * only equal to itself.
	 * @return <code>1</code> if not <code>o</code> equals <code>this</code>
	 */
	public int compareTo(IVersion o)
	{
		return o == this ? 0 : 1;
	}

	public boolean isDefault()
	{
		return true;
	}

	@Override
	public String toString()
	{
		return NAME;
	}

	public void toString(StringBuilder bld)
	{
		bld.append(NAME);
	}
}
