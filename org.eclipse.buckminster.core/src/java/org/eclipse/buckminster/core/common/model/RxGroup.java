/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
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
public class RxGroup extends RxPart
{
	public static final String TAG = "group";

	private final List<RxPart> m_parts;

	public RxGroup(String name, boolean optional, List<RxPart> parts)
	{
		super(name, optional);
		m_parts = Utils.createUnmodifiableList(parts);
	}

	@Override
	public void addPattern(StringBuilder bld, List<RxPart> namedParts) throws CoreException
	{
		if(getName() != null)
		{
			bld.append('(');
			namedParts.add(this);
		}
		else if(isOptional())
			bld.append("(?:");

		for(RxPart part : m_parts)
			part.addPattern(bld, namedParts);

		if(getName() != null)
			bld.append(')');
		else if(isOptional())
			bld.append(")?");
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	@Override
	protected void emitElements(ContentHandler handler, String namespace, String prefix) throws SAXException
	{
		for(RxPart part : m_parts)
			part.toSax(handler, namespace, prefix, part.getDefaultTag());
	}
}
