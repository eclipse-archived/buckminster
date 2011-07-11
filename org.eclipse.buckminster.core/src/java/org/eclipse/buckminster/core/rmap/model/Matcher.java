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

import java.util.Map;
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.sax.AbstractSaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public abstract class Matcher extends AbstractSaxableElement {
	public static final String ATTR_PATTERN = "pattern"; //$NON-NLS-1$

	public static final String ATTR_RESOLUTION_FILTER = "resolutionFilter"; //$NON-NLS-1$

	private final ResourceMap owner;

	private final Pattern pattern;

	private final Filter resolutionFilter;

	public Matcher(ResourceMap owner, String pattern, Filter resolutionFilter) {
		this.owner = owner;
		this.pattern = pattern == null ? null : Pattern.compile(pattern);
		this.resolutionFilter = resolutionFilter;
	}

	public final ResourceMap getOwner() {
		return owner;
	}

	public final Pattern getPattern() {
		return pattern;
	}

	public Filter getResolutionFilter() {
		return resolutionFilter;
	}

	/**
	 * Returns true if this provider is a match for the given <code>query</code>
	 * with respect to provided properties. The method will update the filter
	 * attributes map of the query context.
	 * 
	 * @param The
	 *            query to match
	 * @param A
	 *            one element array that will receive the failing filter. Can be
	 *            <code>null</code>.
	 * @return True if this resolution is a match for the given query.
	 * @see RMContext#getFilterAttributeUsageMap()
	 */
	public boolean isFilterMatchFor(NodeQuery query, Filter[] failingFilter) {
		if (resolutionFilter == null)
			return true;

		Map<String, String[]> attributeUsageMap = query.getContext().getFilterAttributeUsageMap();
		Filter resFilter = getResolutionFilter();
		Map<String, ? extends Object> props = query.getProperties();

		resolutionFilter.addConsultedAttributes(attributeUsageMap);
		if (resolutionFilter.matchCase(props))
			return true;

		if (failingFilter != null)
			failingFilter[0] = resFilter;
		return false;
	}

	public final boolean matches(String componentName) {
		return pattern == null || pattern.matcher(componentName).find();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		if (pattern != null)
			Utils.addAttribute(attrs, ATTR_PATTERN, pattern.toString());
	}
}
