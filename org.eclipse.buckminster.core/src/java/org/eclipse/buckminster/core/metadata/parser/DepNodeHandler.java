/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.core.metadata.parser;

import java.util.UUID;

import org.eclipse.buckminster.core.metadata.IUUIDKeyed;
import org.eclipse.buckminster.core.metadata.model.DepNode;
import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 */
abstract class DepNodeHandler extends ExtensionAwareHandler implements IWrapperParent
{
	DepNodeHandler(AbstractHandler parent)
	{
		super(parent);
	}

	public IUUIDKeyed getWrapped(UUID id) throws SAXException
	{
		return ((IDWrapperHandler)getParentHandler()).getWrapped(id);
	}

	abstract DepNode getDepNode() throws SAXException;
}

