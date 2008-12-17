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

import org.eclipse.buckminster.core.Messages;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.VersionSyntaxException;

/**
 * @author Thomas Hallgren
 */
public class StringVersionType extends AbstractVersionType
{
	public IVersion fromString(String versionString, int startPos, int[] endPosRet) throws VersionSyntaxException
	{
		if(versionString == null)
			return null;
		int top = versionString.length();
		int idx = startPos;
		for(; idx < top; ++idx)
		{
			char c = versionString.charAt(idx);
			switch(c)
			{
			case '[':
			case ']':
			case '(':
			case ')':
			case ',':
				break;
			default:
				continue;
			}
			break;
		}
		if(idx == startPos)
			throw new VersionSyntaxException(Messages.Not_a_valid_String_version, versionString, startPos);
		endPosRet[0] = idx;
		return new StringVersion(this, versionString.substring(startPos, idx));
	}
}
