/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.common.model;

import org.eclipse.buckminster.sax.ISaxableElement;
import org.eclipse.buckminster.sax.Utils;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 * 
 */
public class FlowWithAttributes extends Flow {
	private final String[] keyNamePairs;

	public FlowWithAttributes(String tag, ISaxableElement[] children, String[] keyNamePairs) {
		super(tag, children);
		this.keyNamePairs = keyNamePairs;
	}

	@Override
	public Attributes getAttributes() {
		AttributesImpl attrs = new AttributesImpl();
		int top = keyNamePairs.length;
		for (int idx = 0; idx < top; idx += 2)
			Utils.addAttribute(attrs, keyNamePairs[idx], keyNamePairs[idx + 1]);
		return attrs;
	}

	@Override
	String[] getKeyNamePairs() {
		return keyNamePairs;
	}
}
