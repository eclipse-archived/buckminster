/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.core.rmap.model;

import java.net.URL;

import org.eclipse.buckminster.core.common.model.ExpandingProperties;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Redirect extends Matcher
{
	public static final String TAG = "redirect"; //$NON-NLS-1$

	public static final String ATTR_HREF = "href"; //$NON-NLS-1$

	private final String m_url;

	public Redirect(ResourceMap owner, String pattern, String url)
	{
		super(owner, pattern);
		m_url = url;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		Utils.addAttribute(attrs, ATTR_HREF, m_url);
		super.addAttributes(attrs);
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	public SearchPath getSearchPath(NodeQuery query) throws CoreException
	{
		ResourceMap rmap = getOwner();
		String expanded = ExpandingProperties.expand(rmap.getProperties(query.getProperties()), m_url, 0);
		URL url = URLUtils.resolveURL(rmap.getContextURL(), expanded);
		query.logDecision(ResolverDecisionType.REDIRECT_TO_RESOURCE_MAP, url);
		return ResourceMap.fromURL(url, query.getComponentQuery().getConnectContext()).getSearchPath(query);
	}
}
