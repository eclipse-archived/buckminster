/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.cspec.IComponentName;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A Component Name is something that identifies a component irrespective of version.
 *
 * @author Thomas Hallgren
 */
public class ComponentName extends NamedElement implements Comparable<IComponentName>, IComponentName
{
	public static final String TAG = "componentName";
	public static final String ATTR_COMPONENT_TYPE = "componentType";

	private final String m_componentType;

	ComponentName(ComponentName other)
	{
		super(other.getName());
		m_componentType = other.getComponentTypeID();
	}

	public ComponentName(String name, String componentType)
	{
		super(name);
		m_componentType = componentType;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
		if(!(o instanceof ComponentName))
			return false;

		ComponentName that = (ComponentName)o;
		return Trivial.equalsAllowNull(getName(), that.getName())
			&& Trivial.equalsAllowNull(m_componentType, that.m_componentType);
	}

	public IComponentType getComponentType() throws CoreException
	{
		return m_componentType == null ? null : CorePlugin.getDefault().getComponentType(m_componentType);
	}

	public String getComponentTypeID()
	{
		return m_componentType;
	}

	public String getProjectName() throws CoreException
	{
		String name = getName();

		IComponentType ctype = getComponentType();
		if(name == null || ctype == null)
			//
			// No component type.
			//
			return name;
		return ctype.getProjectName(name);
	}

	public Map<String,String> getProperties()
	{
		HashMap<String,String> p = new HashMap<String,String>();
		if(getName() != null)
			p.put(KeyConstants.COMPONENT_NAME, getName());
		if(m_componentType != null)
			p.put(KeyConstants.COMPONENT_TYPE, m_componentType);
		return p;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public int hashCode()
	{
		int hc = getName() == null ? 31 : getName().hashCode();
		if(m_componentType != null)
		{
			hc *= 37;
			hc += m_componentType.hashCode();
		}
		return hc;
	}

	/**
	 * <p>Match this name with another name. The match is done as
	 * follows</p>
	 * <ul>
	 * <li>If names are not equal, the match is always false</li>
	 * <li>If both instances have a component type, it must be equal</li>
	 * <li>If one instance lacks a component type, the types are not considered part of the match</p>
	 * @param o The name to match with this one
	 * @return <code>true</code> if the name match
	 */
	public boolean matches(ComponentName o)
	{
		return Trivial.equalsAllowNull(getName(), o.getName())
			&& (m_componentType == null || o.m_componentType == null || m_componentType.equals(o.m_componentType));
	}

	/**
	 * Returns this instance as an explicit {@link ComponentName}, i.e. not
	 * as one of its subclasses. This method should be used when component names
	 * are used as keys where only the component name part is significant.
	 * @return A pure component name.
	 */
	public IComponentName toPureComponentName()
	{
		return this;
	}

	@Override
	public final String toString()
	{
		StringBuilder bld = new StringBuilder();
		toString(bld);
		return bld.toString();
	}

	public void toString(StringBuilder bld)
	{
		bld.append(getName());
		if(m_componentType != null)
		{
			bld.append(':');
			bld.append(m_componentType);
		}
	}

	public int compareTo(IComponentName o)
	{
		int cmp = Trivial.compareAllowNull(getName(), o.getName());
		if(cmp == 0)
			cmp = Trivial.compareAllowNull(m_componentType, o.getComponentTypeID());
		return cmp;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_componentType != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT_TYPE, m_componentType);
	}
}
