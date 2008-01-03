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

import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;


/**
 * @author Thomas Hallgren
 */
public class Locator extends Matcher
{
	public static final String TAG = "locator";
	public static final String ATTR_SEARCH_PATH_REF = "searchPathRef";

	private final String m_searchPath;

	public Locator(ResourceMap owner, String pattern, String searchPath)
	{
		super(owner, pattern);
		m_searchPath = searchPath;
	}

	@Override
	public SearchPath getSearchPath(NodeQuery query)
	throws SearchPathNotFoundException
	{
		return getOwner().getSearchPathByReference(m_searchPath);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public String getSearchPathName()
	{
		return m_searchPath;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_SEARCH_PATH_REF, m_searchPath.toString());
		super.addAttributes(attrs);
	}

}

