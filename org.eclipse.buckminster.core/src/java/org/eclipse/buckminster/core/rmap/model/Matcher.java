/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.common.model.AbstractSaxableElement;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author Thomas Hallgren
 */
public abstract class Matcher extends AbstractSaxableElement
{
	public static final String ATTR_PATTERN = "pattern";

	private final ResourceMap m_owner;
	private final Pattern m_pattern;

	public Matcher(ResourceMap owner, String pattern)
	{
		m_owner = owner;
		m_pattern = Pattern.compile(pattern);
	}

	public final boolean matches(String componentName)
	{
		return m_pattern.matcher(componentName).find();
	}

	public final ResourceMap getOwner()
	{
		return m_owner;
	}

	public final Pattern getPattern()
	{
		return m_pattern;
	}

	public abstract SearchPath getSearchPath(NodeQuery query)
	throws CoreException;

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_PATTERN, m_pattern.toString());
	}
}

