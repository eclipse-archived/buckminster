/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.version;

import java.util.Comparator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.Messages;

/**
 * @author Thomas Hallgren
 */
public abstract class AbstractTripletVersion extends AbstractVersion
{
	private static final Pattern s_numPattern = Pattern.compile("^([A-Za-z_-]*)(\\d+)(.*)$"); //$NON-NLS-1$

	private static final Comparator<String> s_defaultComparator = new Comparator<String>()
	{
		// Let null be greater then everything else in order
		// to get 1.0.0.beta < 1.0.0
		//
		public int compare(String o1, String o2)
		{
			if(o1 == null)
				return o2 == null
						? 0
						: 1;
			if(o2 == null)
				return -1;

			// Check if the qualifier conforms to the pattern
			// xxx<n> so that we trap things like:
			//
			// beta-2, FCS-13
			//
			Matcher m1 = s_numPattern.matcher(o1);
			if(m1.matches())
			{
				Matcher m2 = s_numPattern.matcher(o2);
				if(m2.matches())
				{
					int cmp = m1.group(1).compareTo(m2.group(1));
					if(cmp != 0)
						return cmp;

					// The one with the highest number wins here
					//
					int v1 = Integer.parseInt(m1.group(2));
					int v2 = Integer.parseInt(m2.group(2));
					if(v1 < v2)
						return -1;
					if(v1 > v2)
						return 1;

					// Apply the rest of the rules on the reminder
					//
					o1 = m1.group(3);
					o2 = m2.group(3);
				}
			}

			// We hava special rule that says that if the two strings
			// are equal up to max(o1len, o2len), then the one with the
			// smallest length is considered greater. This is because
			// the qualifier often is used to furhter qualify the most
			// important artifact, i.e.
			//
			// 1.0.0.beta
			// 1.0.0.beta-javasource
			//
			// It is also analog with saying that 1.0.0 is greater then 1.0.0.beta
			//
			int o1len = o1.length();
			int o2len = o2.length();
			if(o1len > o2len && o1.startsWith(o2))
				return -1;

			if(o2len > o1len && o2.startsWith(o1))
				return 1;

			return o1.compareTo(o2);
		}
	};

	private final int m_major;

	private final int m_minor;

	private final int m_micro;

	private final String m_qualifier;

	public AbstractTripletVersion(IVersionType type, int major, int minor, int micro, String qualifier)
	{
		super(type);
		m_major = major;
		m_minor = minor;
		m_micro = micro;
		if(qualifier != null && qualifier.length() == 0)
			qualifier = null;
		m_qualifier = qualifier;
	}

	/**
	 * Compares this <code>Version</code> object to another object.
	 * <p>
	 * A version is considered to be <b>less than </b> another version if its major component is less than the other
	 * version's major component, or the major components are equal and its minor component is less than the other
	 * version's minor component, or the major and minor components are equal and its micro component is less than the
	 * other version's micro component, or the major, minor and micro components are equal and it's qualifier component
	 * is less than the other version's qualifier component (using <code>String.compareTo</code>).
	 * </p>
	 * <p>
	 * A version is considered to be <b>equal to</b> another version if the major, minor and micro components are equal
	 * and the qualifier component is equal (using <code>String.compareTo</code>). A missing qualifier is considered
	 * greater then any existing qualifier.
	 * </p>
	 * 
	 * @param object
	 *            The <code>Version</code> object to be compared.
	 * @return A negative integer, zero, or a positive integer if this object is less than, equal to, or greater than
	 *         the specified <code>Version</code> object.
	 * @throws ClassCastException
	 *             If the specified object is not a <code>OSGiVersion</code>.
	 */
	public int compareTo(IVersion o)
	{
		if(!(o instanceof AbstractTripletVersion))
			throw new IllegalArgumentException(Messages.AbstractTripletVersion_Not_a_Triplet);

		AbstractTripletVersion ov = (AbstractTripletVersion)o;
		if(m_major > ov.m_major)
			return 1;
		if(m_major < ov.m_major)
			return -1;

		if(m_minor > ov.m_minor)
			return 1;
		if(m_minor < ov.m_minor)
			return -1;

		if(m_micro > ov.m_micro)
			return 1;
		if(m_micro < ov.m_micro)
			return -1;

		// A version that lacks a qualifier is considered less then one
		// that has it, i.e. 1.0.0.M2 < 1.0.0
		//
		return this.getQualifierComparator().compare(m_qualifier, ov.m_qualifier);
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		if(!(o instanceof AbstractTripletVersion))
			return false;
		AbstractTripletVersion that = (AbstractTripletVersion)o;

		if(this.compareTo(that) != 0)
			return false;

		return true;
	}

	@Override
	public boolean equalsUnqualified(IVersion version)
	{
		if(version == this)
			return true;

		if(!(version instanceof AbstractTripletVersion))
			return false;

		AbstractTripletVersion that = (AbstractTripletVersion)version;
		return getMajor() == that.getMajor() && getMinor() == that.getMinor() && getMicro() == that.getMicro();
	}

	public final int getMajor()
	{
		return m_major;
	}

	public final int getMicro()
	{
		return m_micro;
	}

	public final int getMinor()
	{
		return m_minor;
	}

	@Override
	public final String getQualifier()
	{
		return m_qualifier;
	}

	public Comparator<String> getQualifierComparator()
	{
		return s_defaultComparator;
	}

	@Override
	public int hashCode()
	{
		int hc = 17;

		hc = 37 * hc + m_major;
		hc = 37 * hc + m_minor;
		hc = 37 * hc + m_micro;
		hc = 37 * hc + (m_qualifier == null
				? 0
				: m_qualifier.hashCode());

		return hc;
	}
}
