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
import org.eclipse.buckminster.core.helpers.FilterUtils;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.IVersionType;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.osgi.framework.Filter;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A ComponentRequest is part of a requirement. All referenced components must be available in a
 * workspace for a requirment to be fulfilled. A component can be further qualified using target
 * references in cases when only a part of the component is needed. The ComponentRequest uses a
 * singleton pattern and is optimized for use as key in a Map or Set.
 * @author thhal
 */
public class ComponentRequest extends ComponentName implements IComponentRequest
{
	@SuppressWarnings("hiding")
	public static final String TAG = "component";

	static public final String ATTR_VERSION_DESIGNATOR = "versionDesignator";

	static public final String ATTR_VERSION_TYPE = "versionType";

	public static final String ATTR_FILTER = "filter";

	private final IVersionDesignator m_versionDesignator;

	private final Filter m_filter;

	public ComponentRequest(String name, String componentType, IVersionDesignator versionDesignator)
	{
		super(name, componentType);
		m_versionDesignator = versionDesignator;
		m_filter = null;
	}

	public ComponentRequest(String name, String componentType, String versionDesignatorStr, String versionTypeId)
	throws CoreException
	{
		this(name, componentType, versionDesignatorStr, versionTypeId, null);
	}

	public ComponentRequest(ComponentRequestBuilder bld)
	{
		super(bld.getName(), bld.getComponentTypeID());
		m_versionDesignator = bld.getVersionDesignator();
		m_filter = bld.getFilter();
	}

	public ComponentRequest(String name, String componentType, String versionDesignatorStr, String versionTypeId, Filter filter)
	throws CoreException
	{
		super(name, componentType);
		IVersionDesignator versionDesignator = null;
		if(versionDesignatorStr != null)
			versionDesignator = VersionFactory.createDesignator(versionTypeId, versionDesignatorStr);
		m_versionDesignator = versionDesignator;
		m_filter = filter;
	}

	public boolean designates(IComponentIdentifier id)
	{
		return Trivial.equalsAllowNull(getName(), id.getName())
			&& (getComponentTypeID() == null || getComponentTypeID().equals(id.getComponentTypeID()))
			&& (m_versionDesignator == null || m_versionDesignator.designates(id.getVersion()));
	}

	/**
	 * Returns true if this component reference is equal to obj with respect to name,
	 * versionSelector, and match rule.
	 */
	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		return super.equals(o)
			&& Trivial.equalsAllowNull(m_versionDesignator, ((ComponentRequest)o).m_versionDesignator)
			&& Trivial.equalsAllowNull(m_filter, ((ComponentRequest)o).m_filter);
	}

	@Override
	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public Map<String, String> getProperties()
	{
		Map<String, String> p = super.getProperties();
		if(m_versionDesignator != null)
		{
			p.put(KeyConstants.VERSION_DESIGNATOR, m_versionDesignator.toString());
			p.put(KeyConstants.VERSION_TYPE, m_versionDesignator.getVersion().getType().getId());
		}
		return p;
	}

	public IVersionDesignator getVersionDesignator()
	{
		return m_versionDesignator;
	}

	public String getViewName()
	{
		StringBuilder bld = new StringBuilder();
		appendViewName(bld);
		return bld.toString();
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

	/**
	 * Returns the hashCode for this component request.
	 */
	@Override
	public int hashCode()
	{
		int hash = super.hashCode();
		hash = 31 * hash + (m_versionDesignator == null ? 0 : m_versionDesignator.hashCode());
		return 31 * hash + (m_filter == null ? 0 : m_filter.hashCode());
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

		IVersionDesignator thisVD = getVersionDesignator();
		IVersionDesignator thatVD = that.getVersionDesignator();
		if(thisVD == null)
			return thatVD == null ? this : that;

		if(thatVD == null)
			return this;

		IVersionDesignator mergedVD = thisVD.merge(thatVD);
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
		if(m_versionDesignator != null)
		{
			bld.append('/');
			bld.append(m_versionDesignator);
			bld.append('#');
			bld.append(m_versionDesignator.getVersion().getType().getId());
		}
		if(m_filter != null)
			bld.append(m_filter);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_versionDesignator != null)
		{
			Utils.addAttribute(attrs, ATTR_VERSION_DESIGNATOR, m_versionDesignator.toString());
			IVersion version = m_versionDesignator.getVersion();
			if(version != null)
			{
				IVersionType vt = version.getType();
				if(vt != null)
					Utils.addAttribute(attrs, ATTR_VERSION_TYPE, vt.getId());
			}
		}
		if(m_filter != null)
			Utils.addAttribute(attrs, ATTR_FILTER, m_filter.toString());
	}

	public Filter getFilter()
	{
		return m_filter;
	}

	public boolean isEnabled(Map<String, String> properties)
	{
		return m_filter == null || FilterUtils.isMatch(m_filter, properties);
	}
}
