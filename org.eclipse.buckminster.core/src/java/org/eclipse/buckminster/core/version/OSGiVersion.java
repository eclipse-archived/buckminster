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

import java.util.Comparator;

/**
 * @author Thomas Hallgren
 */
public class OSGiVersion extends TripletVersion
{
	private static final Comparator<String> s_defaultComparator = new Comparator<String>()
	{
		// Let a string with content be greater then null so that we get
		// 1.0.0.beta > 1.0.0 since that's OSGi standard
		//
		public int compare(String o1, String o2)
		{
			if(o1 == null)
				return o2 == null ? 0 : -1;
			if(o2 == null)
				return 1;

			if(o1.equals(o2))
				return 0;

			// We consider an unresolved qualifier to be higher then anything else
			//
			if(o1.equals("qualifier"))
				return 1;
			if(o2.equals("qualifier"))
				return -1;

			return o1.compareTo(o2);
		}
	};

	public OSGiVersion(IVersionType type, int major, int minor, int micro, String qualifier)
	{
		super(type, major, minor, micro, qualifier);
	}

	@Override
	public Comparator<String> getQualifierComparator()
	{
		return s_defaultComparator;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this)
			return true;

		if (!(o instanceof OSGiVersion))
			return false;
		OSGiVersion that = (OSGiVersion)o;

		if (this.compareTo(that) != 0)
			return false;

		return true;
	}

	/**
	 * Appends the string representation of this version identifier. The format
	 * of the version string will be <code>major.minor.micro</code> if
	 * qualifier is the <code>null</code> or
	 * <code>major.minor.micro.qualifier</code> otherwise.
	 */
	@Override
	public void toString(StringBuilder bld)
	{
		bld.append(getMajor());
		bld.append('.');
		bld.append(getMinor());
		bld.append('.');
		bld.append(getMicro());
		if(getQualifier() != null)
		{
			bld.append('.');
			bld.append(getQualifier());
		}
	}
}
