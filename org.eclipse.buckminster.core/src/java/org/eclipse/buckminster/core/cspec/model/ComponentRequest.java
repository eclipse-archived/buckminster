/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.VersionRange;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A ComponentRequest is part of a requirement. All referenced components must be available in a workspace for a
 * requirment to be fulfilled. A component can be further qualified using target references in cases when only a part of
 * the component is needed. The ComponentRequest uses a singleton pattern and is optimized for use as key in a Map or
 * Set.
 * 
 * @author thhal
 */
@SuppressWarnings("restriction")
public class ComponentRequest extends ComponentName implements IComponentRequest
{
	@SuppressWarnings("hiding")
	public static final String TAG = "component"; //$NON-NLS-1$

	static public final String ATTR_VERSION_DESIGNATOR = "versionDesignator"; //$NON-NLS-1$

	static public final String ATTR_VERSION_TYPE = "versionType"; //$NON-NLS-1$

	public static final String ATTR_FILTER = "filter"; //$NON-NLS-1$

	private final VersionRange m_versionRange;

	private final Filter m_filter;

	public static final String FILTER_ECLIPSE_P2_OPTIONAL = "(!(eclipse.p2.optional=false))"; //$NON-NLS-1$

	public ComponentRequest(ComponentRequestBuilder bld)
	{
		super(bld.getName(), bld.getComponentTypeID());
		m_versionRange = bld.getVersionRange();
		m_filter = bld.getFilter();
	}

	public ComponentRequest(String name, String componentType, String versionRangeStr, String versionTypeId)
			throws CoreException
	{
		this(name, componentType, versionRangeStr, versionTypeId, null);
	}

	public ComponentRequest(String name, String componentType, String versionRangeStr, String versionTypeId,
			Filter filter) throws CoreException
	{
		super(name, componentType);
		m_versionRange = VersionHelper.createRange(versionTypeId, versionRangeStr);
		m_filter = filter;
	}

	public ComponentRequest(String name, String componentType, VersionRange versionRange)
	{
		super(name, componentType);
		m_versionRange = versionRange;
		m_filter = null;
	}

	public void appendViewName(StringBuilder bld)
	{
		bld.append(getName());
		String componentType = getComponentTypeID();
		if(componentType != null)
		{
			bld.append(':');
			bld.append(componentType);
		}
		if(m_filter != null)
			bld.append(m_filter);
	}

	public boolean designates(IComponentIdentifier id)
	{
		return Trivial.equalsAllowNull(getName(), id.getName())
				&& (getComponentTypeID() == null || getComponentTypeID().equals(id.getComponentTypeID()))
				&& (m_versionRange == null || m_versionRange.isIncluded(id.getVersion()));
	}

	/**
	 * Returns true if this component reference is equal to obj with respect to name, versionSelector, and match rule.
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		return super.equals(o) && Trivial.equalsAllowNull(m_versionRange, ((ComponentRequest)o).m_versionRange)
				&& Trivial.equalsAllowNull(m_filter, ((ComponentRequest)o).m_filter);
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	@Override
	public Map<String, String> getProperties()
	{
		Map<String, String> p = super.getProperties();
		if(m_versionRange != null)
			p.put(KeyConstants.VERSION_DESIGNATOR, m_versionRange.toString());
		return p;
	}

	public VersionRange getVersionRange()
	{
		return m_versionRange;
	}

	public String getViewName()
	{
		StringBuilder bld = new StringBuilder();
		appendViewName(bld);
		return bld.toString();
	}

	/**
	 * Returns the hashCode for this component request.
	 */
	@Override
	public int hashCode()
	{
		int hash = super.hashCode();
		hash = 31 * hash + (m_versionRange == null
				? 0
				: m_versionRange.hashCode());
		return 31 * hash + (m_filter == null
				? 0
				: m_filter.hashCode());
	}

	public boolean isEnabled(Map<String, ? extends Object> properties)
	{
		return m_filter == null || m_filter.matchCase(properties);
	}

	public boolean isOptional()
	{
		return m_filter != null && m_filter.toString().contains(FILTER_ECLIPSE_P2_OPTIONAL);
	}

	public ComponentRequest mergeDesignator(ComponentRequest that) throws CoreException
	{
		if(!Trivial.equalsAllowNull(getName(), that.getName()))
			throw new ComponentRequestConflictException(this, that);

		String thisCType = getComponentTypeID();
		String thatCType = that.getComponentTypeID();
		if(thisCType == null)
			thisCType = thatCType;
		else if(thatCType != null && !thisCType.equals(thatCType))
			throw new ComponentRequestConflictException(this, that);

		VersionRange thisVD = getVersionRange();
		VersionRange thatVD = that.getVersionRange();
		if(thisVD == null)
			return thatVD == null
					? this
					: that;

		if(thatVD == null)
			return this;

		VersionRange mergedVD = thisVD.intersect(thatVD);
		if(mergedVD == thisVD)
			return this;

		if(mergedVD == null)
			throw new ComponentRequestConflictException(this, that);

		return new ComponentRequest(getName(), thisCType, mergedVD);
	}

	@Override
	public ComponentName toPureComponentName()
	{
		return new ComponentName(this);
	}

	@Override
	public void toString(StringBuilder bld)
	{
		super.toString(bld);
		if(m_versionRange != null)
		{
			bld.append('/');
			bld.append(VersionHelper.getHumanReadable(m_versionRange));
		}
		if(m_filter != null)
			bld.append(m_filter);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_versionRange != null)
			Utils.addAttribute(attrs, ATTR_VERSION_DESIGNATOR, m_versionRange.toString());
		if(m_filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, m_filter.toString());
	}
}
