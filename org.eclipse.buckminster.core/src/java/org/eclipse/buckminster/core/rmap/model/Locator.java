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

import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Locator extends Matcher {
	public static final String TAG = "locator"; //$NON-NLS-1$

	public static final String ATTR_FAIL_ON_ERROR = "failOnError"; //$NON-NLS-1$

	public static final String ATTR_SEARCH_PATH_REF = "searchPathRef"; //$NON-NLS-1$

	private final String searchPath;

	private final boolean failOnError;

	public Locator(ResourceMap owner, String pattern, String searchPath) {
		this(owner, pattern, searchPath, null, true);
	}

	public Locator(ResourceMap owner, String pattern, String searchPath, Filter resolutionFilter) {
		this(owner, pattern, searchPath, resolutionFilter, true);
	}

	public Locator(ResourceMap owner, String pattern, String searchPath, Filter resolutionFilter, boolean failOnError) {
		super(owner, pattern, resolutionFilter);
		this.searchPath = searchPath;
		this.failOnError = failOnError;
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public String getSearchPath() {
		return searchPath;
	}

	public boolean isFailOnError() {
		return failOnError;
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_SEARCH_PATH_REF, searchPath);
		if (!failOnError)
			Utils.addAttribute(attrs, ATTR_FAIL_ON_ERROR, Boolean.toString(failOnError));
		super.addAttributes(attrs);
	}

}
