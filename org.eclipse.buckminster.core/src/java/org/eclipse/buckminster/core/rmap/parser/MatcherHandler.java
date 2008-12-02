/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.parser;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.core.rmap.model.Locator;
import org.eclipse.buckminster.core.rmap.model.Redirect;
import org.eclipse.buckminster.core.rmap.model.ResourceMap;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
abstract class MatcherHandler extends ExtensionAwareHandler
{
	static class LocatorHandler extends MatcherHandler
	{
		static final String TAG = Locator.TAG;

		public LocatorHandler(AbstractHandler parent)
		{
			super(parent);
		}

		@Override
		public void handleAttributes(Attributes attrs) throws SAXException
		{
			super.handleAttributes(attrs);
			ResourceMap rmap = getResourceMap();
			rmap.addMatcher(new Locator(rmap, getPattern(), getStringValue(attrs, Locator.ATTR_SEARCH_PATH_REF)));
		}
	}

	static class RedirectHandler extends MatcherHandler
	{
		static final String TAG = Redirect.TAG;

		public RedirectHandler(AbstractHandler parent)
		{
			super(parent);
		}

		@Override
		public void handleAttributes(Attributes attrs) throws SAXException
		{
			super.handleAttributes(attrs);
			ResourceMap rmap = getResourceMap();
			String href = getStringValue(attrs, Redirect.ATTR_HREF);
			rmap.addMatcher(new Redirect(rmap, getPattern(), href));
		}
	}

	private String m_pattern;

	public MatcherHandler(AbstractHandler parent)
	{
		super(parent);
	}

	final String getPattern()
	{
		return m_pattern;
	}

	final ResourceMap getResourceMap()
	{
		return ((ResourceMapHandler)getParentHandler()).getResourceMap();
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_pattern = getStringValue(attrs, "pattern"); //$NON-NLS-1$
	}
}
