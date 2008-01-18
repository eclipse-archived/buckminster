/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.List;

import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class RxPart extends AbstractSaxableElement
{
	public static final String ATTR_NAME = "name";
	public static final String ATTR_OPTIONAL = "optional";

	private final String m_name;
	private final boolean m_optional;

	protected RxPart(String name, boolean optional)
	{
		m_name = name;
		m_optional = optional;
	}

	public abstract void addPattern(StringBuilder bld, List<RxPart> namedParts) throws CoreException;

	public String getName()
	{
		return m_name;
	}

	public boolean isTagged()
	{
		return false;
	}

	public boolean isOptional()
	{
		return m_optional;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs)
	{
		if(!(isTagged() || m_name == null))
			Utils.addAttribute(attrs, ATTR_NAME, m_name);
		if(m_optional)
			Utils.addAttribute(attrs, ATTR_OPTIONAL, Boolean.TRUE.toString());
	}
}
