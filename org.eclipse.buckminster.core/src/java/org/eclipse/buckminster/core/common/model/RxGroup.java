/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.core.common.model;

import java.util.List;

import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.CoreException;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class RxGroup extends RxPart {
	public static final String TAG = "group"; //$NON-NLS-1$

	private final List<RxPart> parts;

	public RxGroup(String name, boolean optional, List<RxPart> parts) {
		super(name, optional);
		this.parts = Utils.createUnmodifiableList(parts);
	}

	@Override
	public void addPattern(StringBuilder bld, List<RxPart> namedParts) throws CoreException {
		if (getName() != null) {
			bld.append('(');
			namedParts.add(this);
		} else if (isOptional())
			bld.append("(?:"); //$NON-NLS-1$

		for (RxPart part : parts)
			part.addPattern(bld, namedParts);

		if (getName() != null)
			bld.append(')');
		else if (isOptional())
			bld.append(")?"); //$NON-NLS-1$
	}

	@Override
	public String getDefaultTag() {
		return TAG;
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException {
		for (RxPart part : parts)
			part.toSax(handler, namespace, prefix, part.getDefaultTag());
	}
}
