/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.UUID;

import org.eclipse.buckminster.core.metadata.model.Materialization;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.core.runtime.Path;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
public class MaterializationHandler extends ExtensionAwareHandler
{
	public static final String TAG = Materialization.TAG;

	private Materialization m_materialization;

	public MaterializationHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		m_materialization = new Materialization(
				Path.fromPortableString(this.getStringValue(attrs, Materialization.ATTR_LOCATION)),
				UUID.fromString(this.getStringValue(attrs, Materialization.ATTR_RESOLUTION_ID)));
	}

	public Materialization getMaterialization()
	{
		return m_materialization;
	}
}

