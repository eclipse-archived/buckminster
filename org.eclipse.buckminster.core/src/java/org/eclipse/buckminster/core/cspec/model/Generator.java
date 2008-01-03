/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspec.model;

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Generator extends NamedElement
{
	public static final String ATTR_ATTRIBUTE = "attribute";

	public static final String ATTR_COMPONENT = "component";

	public static final String ATTR_GENERATES = "generates";

	public static final String TAG = "generator";

	private final CSpec m_cspec;

	private final String m_attribute;

	private final String m_component;

	public Generator(CSpec cspec, String component, String attribute, String generates)
	{
		super(generates);
		m_cspec = cspec;
		m_component = component;
		m_attribute = attribute;
	}

	public String getAttribute()
	{
		return m_attribute;
	}

	public CSpec getCSpec()
	{
		return m_cspec;
	}

	public String getComponent()
	{
		return m_component;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public String getGenerates()
	{
		return getName();
	}

	@Override
	public String getNameAttributeName()
	{
		return ATTR_GENERATES;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		super.addAttributes(attrs);
		Utils.addAttribute(attrs, ATTR_ATTRIBUTE, m_attribute);
		if(m_component != null)
			Utils.addAttribute(attrs, ATTR_COMPONENT, m_component);
	}
}
