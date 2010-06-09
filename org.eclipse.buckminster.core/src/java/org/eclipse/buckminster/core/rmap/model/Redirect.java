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
import org.eclipse.buckminster.core.resolver.ResourceMapResolverFactory;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Redirect extends Matcher {
	public static final String TAG = "redirect"; //$NON-NLS-1$

	public static final String ATTR_HREF = "href"; //$NON-NLS-1$

	private final String url;

	public Redirect(ResourceMap owner, String pattern, String url) {
		super(owner, pattern);
		this.url = url;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public ResourceMap getResourceMap(NodeQuery query) throws CoreException {
		ResourceMap rmap = getOwner();
		String expanded = ExpandingProperties.expand(rmap.getProperties(query.getProperties()), url, 0);
		URL resolvedURL = URLUtils.resolveURL(rmap.getContextURL(), expanded);
		query.logDecision(ResolverDecisionType.REDIRECT_TO_RESOURCE_MAP, resolvedURL);
		return ResourceMapResolverFactory.getCachedResourceMap(query.getResolutionContext(), resolvedURL, query.getComponentQuery()
				.getConnectContext());
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_HREF, url);
		super.addAttributes(attrs);
	}
}
