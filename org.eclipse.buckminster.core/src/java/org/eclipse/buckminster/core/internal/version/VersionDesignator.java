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

import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.core.runtime.CoreException;

/**
 * @author Thomas Hallgren
 */
public abstract class VersionDesignator implements IVersionDesignator
{
	static class GreaterOrEqual extends VersionDesignator
	{
		GreaterOrEqual(IVersion version)
		{
			super(version);
		}

		@Override
		public IVersion getToVersion()
		{
			return null;
		}

		@Override
		public int hashCode()
		{
			return getVersion().hashCode();
		}

		@Override
		public boolean hasUpperBound()
		{
			return false;
		}

		@Override
		public boolean includesUpperBound()
		{
			return false;
		}

		@Override
		ComparisonResult internalCompare(IVersionDesignator o)
		{
			IVersion tVersion = getVersion();

			int cr = tVersion.compareTo(o.getVersion());
			if(o instanceof GreaterOrEqual)
			{
				return cr < 0
						? ComparisonResult.Contains
						: (cr == 0
								? ComparisonResult.Equal
								: ComparisonResult.ContainedBy);
			}

			if(o instanceof PerfectMatch)
				return cr > 0
						? ComparisonResult.Disparate
						: ComparisonResult.Contains;

			if(cr > 0)
				return o.designates(tVersion)
						? ComparisonResult.IntersectLow
						: ComparisonResult.Disparate;

			return ComparisonResult.Contains;
		}

		@Override
		boolean internalDesignates(IVersion version)
		{
			return version.compareTo(getVersion()) >= 0;
		}

		public boolean isIdeal(IVersion version)
		{
			// Since there's no upper bound, we don't know.
			//
			return false;
		}

		@Override
		public String toString()
		{
			return getVersion().toString();
		}
	}

	static class PerfectMatch extends VersionDesignator
	{
		PerfectMatch(IVersion version)
		{
			super(version);
		}

		@Override
		public int hashCode()
		{
			int hash = getVersion().hashCode();
			return hash * 31 ^ hash;
		}

		@Override
		ComparisonResult internalCompare(IVersionDesignator o)
		{
			return o.designates(getVersion())
					? (o instanceof PerfectMatch
							? ComparisonResult.Equal
							: ComparisonResult.ContainedBy)
					: ComparisonResult.Disparate;
		}

		@Override
		boolean internalDesignates(IVersion version)
		{
			return getVersion().compareTo(version) == 0;
		}

		@Override
		public boolean isExplicit()
		{
			return true;
		}

		public boolean isIdeal(IVersion version)
		{
			return getVersion().equals(version);
		}

		@Override
		public String toString()
		{
			StringBuilder bld = new StringBuilder();
			String ver = getVersion().toString();
			bld.append('[');
			bld.append(ver);
			bld.append(',');
			bld.append(ver);
			bld.append(']');
			return bld.toString();
		}
	}

	static class Range extends VersionDesignator
	{
		private final IVersion m_toVersion;

		private final boolean m_fromInclusive;

		private final boolean m_toInclusive;

		Range(IVersion fromVersion, boolean fromInclusive, IVersion toVersion, boolean toInclusive)
		{
			super(fromVersion);
			m_fromInclusive = fromInclusive;
			m_toVersion = toVersion;
			m_toInclusive = toInclusive;
		}

		@Override
		public IVersion getToVersion()
		{
			return m_toVersion;
		}

		@Override
		public int hashCode()
		{
			int hash = getVersion().hashCode();
			hash = hash * 31 ^ m_toVersion.hashCode();
			hash = hash << 2;
			if(!m_fromInclusive)
				hash |= 1;
			if(m_toInclusive)
				hash |= 2;
			return hash;
		}

		@Override
		public boolean includesLowerBound()
		{
			return m_fromInclusive;
		}

		@Override
		public boolean includesUpperBound()
		{
			return m_toInclusive;
		}

		@Override
		ComparisonResult internalCompare(IVersionDesignator o)
		{
			if(!(o instanceof Range))
			{
				switch(o.compare(this))
				{
				case Contains:
					return ComparisonResult.ContainedBy;
				case ContainedBy:
					return ComparisonResult.Contains;
				case IntersectLow:
					return ComparisonResult.IntersectHigh;
				case IntersectHigh:
					return ComparisonResult.IntersectLow;
				default:
					return ComparisonResult.Equal;
				}
			}
			IVersion mv = getVersion();
			IVersion ov = o.getVersion();

			Range or = (Range)o;
			int compareEnd = m_toVersion.compareTo(ov);
			if(compareEnd < 0 || (compareEnd == 0 && !(m_toInclusive && or.m_fromInclusive)))
				return ComparisonResult.Disparate;

			int compareStart = mv.compareTo(or.m_toVersion);
			if(compareStart > 0 || (compareStart == 0 && !(m_fromInclusive && or.m_toInclusive)))
				return ComparisonResult.Disparate;

			// There is some kind of overlap.
			//
			int compareFrom = mv.compareTo(ov);
			if(compareFrom == 0)
			{
				if(m_fromInclusive)
				{
					if(!or.m_fromInclusive)
						compareFrom = -1;
				}
				else
				{
					if(or.m_fromInclusive)
						compareFrom = 1;
				}
			}

			int compareTo = m_toVersion.compareTo(or.m_toVersion);
			if(compareTo == 0)
			{
				if(m_toInclusive)
				{
					if(!or.m_toInclusive)
						compareTo = 1;
				}
				else
				{
					if(or.m_toInclusive)
						compareTo = -1;
				}
			}

			if(compareFrom < 0)
			{
				return compareTo >= 0
						? ComparisonResult.Contains
						: ComparisonResult.IntersectHigh;
			}

			if(compareFrom > 0)
			{
				return compareTo <= 0
						? ComparisonResult.ContainedBy
						: ComparisonResult.IntersectLow;
			}

			// Range starts are equal
			//
			return compareTo == 0
					? ComparisonResult.Equal
					: (compareTo < 0
							? ComparisonResult.ContainedBy
							: ComparisonResult.Contains);
		}

		@Override
		boolean internalDesignates(IVersion version)
		{
			int compare = getVersion().compareTo(version);
			if(compare > 0 || (compare == 0 && !m_fromInclusive))
				//
				// Before "from" version
				//
				return false;

			compare = m_toVersion.compareTo(version);
			return compare > 0 || (compare == 0 && m_toInclusive);
		}

		public boolean isIdeal(IVersion version)
		{
			return m_toInclusive && m_toVersion.equals(version);
		}

		@Override
		public String toString()
		{
			StringBuilder bld = new StringBuilder();
			bld.append(m_fromInclusive
					? '['
					: '(');
			bld.append(getVersion());
			bld.append(',');
			bld.append(m_toVersion);
			bld.append(m_toInclusive
					? ']'
					: ')');
			return bld.toString();
		}
	}

	public static IVersionDesignator create(IVersion low, boolean includeLow, IVersion high, boolean includeHigh)
	{
		if(includeLow)
		{
			if(high == null)
				return new GreaterOrEqual(low);

			int cmp = low.compareTo(high);
			if(cmp == 0)
				return includeHigh
						? new PerfectMatch(low)
						: null;

			return cmp < 0
					? new Range(low, includeLow, high, includeHigh)
					: null;
		}

		if(high == null)
			return null;

		int cmp = low.compareTo(high);
		if(cmp == 0)
			return null;

		return cmp < 0
				? new Range(low, includeLow, high, includeHigh)
				: null;
	}

	public static IVersionDesignator explicit(IVersion version)
	{
		return new PerfectMatch(version);
	}

	public static IVersionDesignator explicitFromString(IVersionType versionType, String versionString)
			throws VersionSyntaxException
	{
		return explicit(versionType.fromString(versionString));
	}

	@SuppressWarnings("fallthrough")
	public static IVersionDesignator fromString(IVersionType versionType, String versionString)
			throws VersionSyntaxException
	{
		if(versionString == null)
			throw new IllegalArgumentException("Version string cannot be null");

		int top = versionString.length();
		if(top == 0)
			throw new IllegalArgumentException("Version string cannot be empty");

		int idx = 0;
		while(idx < top && Character.isWhitespace(versionString.charAt(idx)))
			++idx;

		IVersionDesignator result;
		boolean fromInclusive = false;
		int[] endPosHolder = new int[1];
		switch(versionString.charAt(idx))
		{
		case '[':
			fromInclusive = true;
			// Fall through
		case '(':
			++idx;
			while(idx < top && Character.isWhitespace(versionString.charAt(idx)))
				++idx;

			IVersion fromVersion = versionType.fromString(versionString, idx, endPosHolder);
			idx = endPosHolder[0];
			while(idx < top && Character.isWhitespace(versionString.charAt(idx)))
				++idx;

			if(idx >= top || versionString.charAt(idx) != ',')
			{
				if(fromInclusive && versionString.charAt(idx) == ']')
				{
					// Short form [<version>] of perfect match
					//
					result = new PerfectMatch(fromVersion);
					break;
				}
				throw new VersionSyntaxException("expected ','", versionString, idx);
			}
			++idx;
			while(idx < top && Character.isWhitespace(versionString.charAt(idx)))
				++idx;

			IVersion toVersion = versionType.fromString(versionString, idx, endPosHolder);
			idx = endPosHolder[0];
			while(idx < top && Character.isWhitespace(versionString.charAt(idx)))
				++idx;

			char endChar = idx < top
					? versionString.charAt(idx)
					: 0;
			boolean toInclusive = false;
			switch(endChar)
			{
			case ']':
				toInclusive = true;
				// fall through
			case ')':
				int compare = fromVersion.compareTo(toVersion);
				if(compare > 0 || (compare == 0 && !(fromInclusive && toInclusive)))
					throw new VersionSyntaxException("Negative version range", versionString, 0);
				result = (compare == 0)
						? new PerfectMatch(fromVersion)
						: new Range(fromVersion, fromInclusive, toVersion, toInclusive);
				break;
			default:
				throw new VersionSyntaxException("expected ']' or ')'", versionString, idx);
			}
			break;
		default:
			result = new GreaterOrEqual(versionType.fromString(versionString));
		}
		return result;
	}

	public static IVersionDesignator GTEqual(IVersion version)
	{
		return new GreaterOrEqual(version);
	}

	private final IVersion m_version;

	protected VersionDesignator(IVersion version)
	{
		m_version = version;
	}

	public final ComparisonResult compare(IVersionDesignator designator)
	{
		return designator.getVersion().getType().isComparableTo(m_version.getType())
				? internalCompare(designator)
				: ComparisonResult.Disparate;
	}

	public boolean designates(IVersion version)
	{
		return version == null
				? false
				: (version.getType().isComparableTo(m_version.getType())
						? internalDesignates(version)
						: false);
	}

	public final boolean designates(String version) throws VersionSyntaxException
	{
		return version == null
				? false
				: internalDesignates(m_version.getType().fromString(version));
	}

	@Override
	public final boolean equals(Object o)
	{
		return o instanceof IVersionDesignator && compare((IVersionDesignator)o) == ComparisonResult.Equal;
	}

	public IVersion getToVersion()
	{
		return m_version;
	}

	public IVersion getVersion()
	{
		return m_version;
	}

	public boolean hasUpperBound()
	{
		return true;
	}

	public boolean includesLowerBound()
	{
		return true;
	}

	public boolean includesUpperBound()
	{
		return true;
	}

	abstract ComparisonResult internalCompare(IVersionDesignator designator);

	abstract boolean internalDesignates(IVersion version);

	public boolean isExplicit()
	{
		return false;
	}

	public IVersionDesignator merge(IVersionDesignator that) throws CoreException
	{
		// In case an OSGiVersion is merged with something else like
		// the TripletVersion, we must first make sure that the merged
		// version can express itself as an OSGiVersion and convert it.
		//
		IVersionDesignator self = this;
		IVersionType thisType = getVersion().getType();
		IVersionType thatType = that.getVersion().getType();

		if(thisType instanceof OSGiVersionType)
		{
			if(!(thatType instanceof OSGiVersionType))
				that = VersionFactory.createDesignator(thisType, that.toString());
		}
		else
		{
			if(thatType instanceof OSGiVersionType)
				self = VersionFactory.createDesignator(thatType, toString());
		}

		switch(self.compare(that))
		{
		case Equal:
		case ContainedBy:
			return self;
		case Contains:
			return that;
		case IntersectHigh:
			return create(self.getVersion(), self.includesLowerBound(), that.getToVersion(), that.includesUpperBound());
		case IntersectLow:
			return create(that.getVersion(), that.includesLowerBound(), self.getToVersion(), self.includesUpperBound());
		default:
			return null;
		}
	}
}
