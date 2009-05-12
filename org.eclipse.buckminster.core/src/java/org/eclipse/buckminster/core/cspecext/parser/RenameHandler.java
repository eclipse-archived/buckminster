/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.cspecext.parser;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
class RenameHandler extends ExtensionAwareHandler
{
	public static final String TAG = "rename"; //$NON-NLS-1$

	private String m_oldName;

	private String m_newName;

	RenameHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public final String getTAG()
	{
		return TAG;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		m_oldName = getStringValue(attrs, "oldName"); //$NON-NLS-1$
		m_newName = getStringValue(attrs, "newName"); //$NON-NLS-1$
	}

	String getNewName()
	{
		return m_newName;
	}

	String getOldName()
	{
		return m_oldName;
	}
}
