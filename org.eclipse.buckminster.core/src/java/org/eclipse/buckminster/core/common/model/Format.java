/*****************************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.common.model;

import java.text.MessageFormat;
import java.util.Map;

import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * An instance of this class acts as a {@link java.text.MessageFormat} that gets
 * its values from a {@link ValueHolder} instance.
 * 
 * @author Thomas Hallgren
 */
public class Format extends ValueHolderFilter {
	public static final String ATTR_FORMAT = "format"; //$NON-NLS-1$

	public static final String TAG = "format"; //$NON-NLS-1$

	private final String format;

	public Format(String pattern) {
		format = pattern;
	}

	@Override
	public boolean equals(Object o) {
		return super.equals(o) && format.equals(((Format) o).format);
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	public String getFormat() {
		return format;
	}

	@Override
	public int hashCode() {
		int hc = super.hashCode();
		hc = 37 * hc + format.hashCode();
		return hc;
	}

	@Override
	public String toString() {
		return getFormat();
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException {
		Utils.addAttribute(attrs, ATTR_FORMAT, format);
	}

	@Override
	protected String checkedGetValue(Map<String, ? extends Object> properties, int recursionGuard) {
		String fmt = ExpandingProperties.expand(properties, format, recursionGuard + 1);
		MessageFormat messageFormat = new MessageFormat(fmt);
		return messageFormat.format(checkedGetSourceValues(properties, recursionGuard + 1).toArray());
	}
}
