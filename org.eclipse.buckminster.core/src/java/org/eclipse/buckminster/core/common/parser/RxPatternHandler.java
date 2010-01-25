/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.common.parser;

import org.eclipse.buckminster.core.common.model.RxPart;
import org.eclipse.buckminster.core.common.model.RxPattern;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class RxPatternHandler extends RxPartHandler
{
	public static final String TAG = RxPattern.TAG;

	private String m_pattern;

	private String m_prefix;

	private String m_suffix;

	public RxPatternHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public RxPart createPart()
	{
		return new RxPattern(getName(), isOptional(), m_pattern, m_prefix, m_suffix);
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		super.handleAttributes(attrs);
		m_pattern = getStringValue(attrs, RxPattern.ATTR_PATTERN);
		m_prefix = getOptionalStringValue(attrs, RxPattern.ATTR_PREFIX);
		m_suffix = getOptionalStringValue(attrs, RxPattern.ATTR_SUFFIX);
	}

	protected final String getPattern()
	{
		return m_pattern;
	}

	protected final String getPrefix()
	{
		return m_prefix;
	}

	protected final String getSuffix()
	{
		return m_suffix;
	}
}
