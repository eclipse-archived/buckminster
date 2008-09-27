/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.version;

import org.eclipse.buckminster.core.internal.version.TripletVersionType;

/**
 * @author Thomas Hallgren
 */
public class TripletVersion extends AbstractTripletVersion
{
	private final String m_stringForm;

	public TripletVersion(IVersionType type, int major, int minor, int micro, String qualifier, String stringForm)
	{
		super(type, major, minor, micro, qualifier);
		m_stringForm = stringForm;
	}

	@Override
	public IVersion replaceQualifier(String qualifier)
	{
		String stringForm = m_stringForm;
		String qual = getQualifier();
		if(qual != null)
		{
			if(qualifier != null)
				//
				// Preserve qualifier separator
				//
				stringForm = stringForm.substring(0, stringForm.length() - qual.length()) + qualifier;
			else
				stringForm = stringForm.substring(0, stringForm.length() - (qual.length() + 1));
		}
		else
			stringForm = stringForm + '-' + qualifier;
		return new TripletVersion(getType(), getMajor(), getMinor(), getMicro(), qualifier, stringForm);
	}

	public boolean hasMinor()
	{
		return TripletVersionType.hasMinor(m_stringForm);
	}

	public boolean hasMicro()
	{
		return TripletVersionType.hasMicro(m_stringForm);
	}

	@Override
	public String toString()
	{
		return m_stringForm;
	}

	public void toString(StringBuilder bld)
	{
		bld.append(m_stringForm);
	}
}
