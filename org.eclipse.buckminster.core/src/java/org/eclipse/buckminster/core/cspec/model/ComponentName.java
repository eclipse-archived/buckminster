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

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.helpers.AttributesImpl;

/**
 * A Component Name is something that identifies a component irrespective of version.
 *
 * @author Thomas Hallgren
 */
public class ComponentName extends NamedElement implements Comparable<ComponentName>
{
	public static final String TAG = "componentName";
	public static final String ATTR_CATEGORY = "category";

	private final String m_categoryName;

	ComponentName(ComponentName other)
	{
		super(other.getName());
		m_categoryName = other.getCategory();
	}

	public ComponentName(String name, String categoryName)
	{
		super(name);
		m_categoryName = categoryName;
	}

	@Override
	public boolean equals(Object o)
	{
		if(this == o)
			return true;
		if(!(o instanceof ComponentName))
			return false;

		ComponentName that = (ComponentName)o;
		return this.getName().equals(that.getName())
			&& Trivial.equalsAllowNull(m_categoryName, that.m_categoryName);
	}

	public final String getCategory()
	{
		return m_categoryName;
	}

	public Map<String,String> getProperties()
	{
		HashMap<String,String> p = new HashMap<String,String>();
		p.put(KeyConstants.COMPONENT_NAME, this.getName());
		String category = this.getCategory();
		if(category != null)
			p.put(KeyConstants.CATEGORY_NAME, category);
		return p;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public int hashCode()
	{
		int hc = this.getName().hashCode();
		if(m_categoryName != null)
		{
			hc *= 37;
			hc += m_categoryName.hashCode();
		}
		return hc;
	}

	public boolean matches(ComponentName o)
	{
		return this.equals(o);
	}

	/**
	 * Returns this instance as an explicit {@link ComponentName}, i.e. not
	 * as one of its subclasses. This method should be used when component names
	 * are used as keys where only the component name part is significant.
	 * @return A pure component name.
	 */
	public ComponentName toPureComponentName()
	{
		return this;
	}

	@Override
	public final String toString()
	{
		StringBuilder bld = new StringBuilder();
		this.toString(bld);
		return bld.toString();
	}

	public void toString(StringBuilder bld)
	{
		bld.append(this.getName());
		if(m_categoryName != null)
		{
			bld.append(':');
			bld.append(m_categoryName);
		}
	}

	public int compareTo(ComponentName o)
	{
		int cmp = this.getName().compareTo(o.getName());
		if(cmp == 0)
			cmp = Trivial.compareAllowNull(m_categoryName, o.m_categoryName);
		return cmp;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		if(m_categoryName != null)
			Utils.addAttribute(attrs, ATTR_CATEGORY, m_categoryName);
	}
}
