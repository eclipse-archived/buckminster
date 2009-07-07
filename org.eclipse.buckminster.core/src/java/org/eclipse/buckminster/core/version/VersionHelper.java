package org.eclipse.buckminster.core.version;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.TimeZone;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.equinox.internal.provisional.p2.core.FormatException;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.equinox.internal.provisional.p2.core.VersionFormat;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.xml.sax.Attributes;

@SuppressWarnings("restriction")
public class VersionHelper
{
	static final String VERSION_TYPES_POINT = CorePlugin.CORE_NAMESPACE + ".versionTypes"; //$NON-NLS-1$

	private static final HashMap<String, VersionType> s_knownTypes = new HashMap<String, VersionType>();

	private static SimpleDateFormat s_timestampFormat = new SimpleDateFormat("yyyyMMdd'.'HHmmss"); //$NON-NLS-1$

	private static SimpleDateFormat s_dateFormat = new SimpleDateFormat("yyyyMMdd"); //$NON-NLS-1$

	static
	{
		try
		{
			Version.parseVersion("1.0.0"); //$NON-NLS-1$
			IExtensionRegistry exReg = Platform.getExtensionRegistry();
			IConfigurationElement[] elems = exReg.getConfigurationElementsFor(VERSION_TYPES_POINT);
			int idx = elems.length;
			elements: while(--idx >= 0)
			{
				IConfigurationElement elem = elems[idx];
				String format = elem.getAttribute("format"); //$NON-NLS-1$
				String id = elem.getAttribute("id"); //$NON-NLS-1$
				VersionType vt = s_knownTypes.get(format);
				if(vt != null)
				{
					String[] labels = vt.getLabels();
					int top = labels.length;
					for(int ldx = 0; ldx < top; ++ldx)
						if(labels[ldx].equals(id))
							continue elements;

					String[] newLabels = new String[top + 1];
					System.arraycopy(labels, 0, newLabels, 0, top);
					newLabels[top] = id;
					vt = new VersionType(VersionFormat.compile(format), newLabels);
				}
				else
				{
					vt = new VersionType(VersionFormat.compile(format), id);
				}
				s_knownTypes.put(format, vt);
			}

		}
		catch(FormatException e)
		{
			throw new ExceptionInInitializerError(e);
		}

		TimeZone utc = TimeZone.getTimeZone("UTC"); //$NON-NLS-1$
		s_timestampFormat.setTimeZone(utc);
		s_dateFormat.setTimeZone(utc);
	}

	public static VersionRange createRange(String versionTypeLabel, String rangeString)
			throws MissingVersionTypeException
	{
		return createRange(getVersionType(versionTypeLabel), rangeString);
	}

	public static VersionRange createRange(VersionFormat versionFormat, String rangeString)
	{
		if(rangeString == null)
			return null;

		if(versionFormat == null || versionFormat.equals(VersionFormat.OSGI_FORMAT))
			return new VersionRange(rangeString);

		StringBuffer bld = new StringBuffer();
		versionFormat.toString(bld);
		bld.append(':');
		bld.append(rangeString);
		return new VersionRange(bld.toString());
	}

	public static VersionRange createRange(VersionType versionType, String rangeString)
	{
		return createRange(versionType == null
				? null
				: versionType.getFormat(), rangeString);
	}

	public static Version createVersion(String versionTypeLabel, String versionString)
			throws MissingVersionTypeException
	{
		return createVersion(getVersionType(versionTypeLabel), versionString);
	}

	public static Version createVersion(VersionFormat versionFormat, String versionString)
	{
		if(versionString == null)
			return null;

		versionString = Trivial.trim(versionString);
		if(versionString == null)
			return null;

		if(versionFormat == null)
			versionFormat = VersionFormat.OSGI_FORMAT;

		return versionFormat.parse(versionString);
	}

	public static Version createVersion(VersionType versionType, String versionString)
	{
		return createVersion(versionType == null
				? null
				: versionType.getFormat(), versionString);
	}

	/**
	 * Returns <code>true</code> if <code>a</code> is equal to <code>b</code> in all but the version qualifier.
	 * 
	 * @param version
	 * @return true when the versions are equal irrespective of qualifiers
	 */
	public static boolean equalsUnqualified(Version a, Version b)
	{
		if(a == null)
			return b == null;

		if(b == null)
			return false;

		return a.equals(b)
				|| (a.isOSGiCompatible() && b.isOSGiCompatible() && a.getMajor() == b.getMajor()
						&& a.getMinor() == b.getMinor() && a.getMicro() == b.getMicro());
	}

	public static VersionRange exactRange(org.osgi.framework.Version v)
	{
		return v == null
				? null
				: exactRange(Version.parseVersion(v.toString()));
	}

	public static VersionRange exactRange(Version v)
	{
		return v == null
				? null
				: new VersionRange(v, true, v, true);
	}

	public static String getHumanReadable(Version version)
	{
		if(version == null)
			return null;

		StringBuffer buf = new StringBuffer();
		getOriginal(version, buf);
		VersionFormat fmt = version.getFormat();
		if(!VersionFormat.OSGI_FORMAT.equals(fmt))
		{
			buf.append('#');
			buf.append(getVersionType(fmt).getId());
		}
		return buf.toString();
	}

	public static String getHumanReadable(VersionRange range)
	{
		if(range == null)
			return null;

		VersionFormat fmt = range.getFormat();
		StringBuffer buf = new StringBuffer();
		if(VersionFormat.OSGI_FORMAT.equals(fmt))
			range.toString(buf);
		else
		{
			if(range.getMaximum().equals(Version.MAX_VERSION) && range.getIncludeMaximum())
				//
				// Return a string that corresponds to the range >= minimum version
				//
				getOriginal(range.getMinimum(), buf);
			else
			{
				buf.append(range.getIncludeMinimum()
						? '['
						: '(');
				getOriginal(range.getMinimum(), buf);
				buf.append(',');
				getOriginal(range.getMaximum(), buf);
				buf.append(range.getIncludeMaximum()
						? ']'
						: ')');
			}
			buf.append('#');
			buf.append(getVersionType(fmt).getId());
		}
		return buf.toString();
	}

	public static List<VersionType> getKnownTypes()
	{
		ArrayList<VersionType> knownTypes = new ArrayList<VersionType>(s_knownTypes.values());
		Collections.sort(knownTypes, new Comparator<VersionType>()
		{

			public int compare(VersionType o1, VersionType o2)
			{
				return o1.getId().compareTo(o2.getId());
			}
		});
		return knownTypes;
	}

	public static String getOriginal(Version version)
	{
		if(version == null)
			return null;

		StringBuffer sb = new StringBuffer();
		getOriginal(version, sb);
		return sb.toString();
	}

	public static void getOriginal(Version version, StringBuffer sb)
	{
		if(version == null)
			return;

		String orig = version.getOriginal();
		if(orig != null)
			sb.append(orig);
		else
		{
			if(VersionFormat.OSGI_FORMAT.equals(version.getFormat()))
				version.toString(sb);
			else
				version.rawToString(sb, false);
		}
	}

	public static VersionType getVersionType(String id) throws MissingVersionTypeException
	{
		if(id == null)
			id = VersionType.OSGI;

		for(VersionType vt : s_knownTypes.values())
			for(String label : vt.getLabels())
				if(label.equals(id))
					return vt;

		throw new MissingVersionTypeException(id);
	}

	public static VersionType getVersionType(VersionFormat format)
	{
		if(format == null)
			return null;

		String fmtString = format.toString();
		fmtString = fmtString.substring(7, fmtString.length() - 1);
		synchronized(s_knownTypes)
		{
			VersionType vt = s_knownTypes.get(fmtString);
			if(vt == null)
			{
				// Create a default type that uses the actual format string
				// as its label
				//
				vt = new VersionType(format, fmtString);
				s_knownTypes.put(fmtString, vt);
			}
			return vt;
		}
	}

	public static VersionRange greaterOrEqualRange(Version version)
	{
		return version == null
				? null
				: new VersionRange(version.toString());
	}

	public static Version parseVersion(String versionStr)
	{
		Version version = Version.parseVersion(versionStr);
		if(version.equals(Version.emptyVersion))
			version = null;
		return version;
	}

	public static Version parseVersionAttributes(Attributes attrs) throws CoreException
	{
		String tmp = AbstractHandler.getOptionalStringValue(attrs, ComponentIdentifier.ATTR_VERSION);
		if(tmp == null)
			return null;

		try
		{
			String type = AbstractHandler.getOptionalStringValue(attrs, ComponentIdentifier.ATTR_VERSION_TYPE);
			return (type == null)
					? parseVersion(tmp)
					: createVersion(type, tmp);
		}
		catch(IllegalArgumentException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static VersionRange parseVersionRangeAttributes(Attributes attrs) throws CoreException
	{
		return parseVersionRangeAttributes(attrs, ComponentRequest.ATTR_VERSION_DESIGNATOR,
				ComponentIdentifier.ATTR_VERSION_TYPE);
	}

	public static VersionRange parseVersionRangeAttributes(Attributes attrs, String versionAttr, String versionTypeAttr)
			throws CoreException
	{
		String tmp = AbstractHandler.getOptionalStringValue(attrs, versionAttr);
		if(tmp == null)
			return null;

		try
		{
			String type = AbstractHandler.getOptionalStringValue(attrs, versionTypeAttr);
			return (type == null)
					? new VersionRange(tmp)
					: createRange(type, tmp);
		}
		catch(IllegalArgumentException e)
		{
			throw BuckminsterException.wrap(e);
		}
	}

	public static Version replaceQualifier(Version version, String qualifier)
	{
		if(version == null || !version.isOSGiCompatible())
			return version;

		String stringForm = version.toString();
		String qual = version.getQualifier();
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
			stringForm = stringForm + '.' + qualifier;
		return version.getFormat().parse(stringForm);
	}

	public static Object toTimestampString(Date timestamp)
	{
		return s_timestampFormat.format(timestamp);
	}
}
