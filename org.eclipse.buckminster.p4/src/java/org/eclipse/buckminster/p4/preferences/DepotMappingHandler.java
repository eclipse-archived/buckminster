/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.preferences;

import java.util.regex.Pattern;

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.osgi.service.prefs.BackingStoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * @author Thomas Hallgren
 */
public class DepotMappingHandler extends ExtensionAwareHandler
{
	final static String TAG = DepotMapping.TAG;

	private DepotMapping m_deoptMapping;

	public DepotMappingHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		String name = this.getStringValue(attrs, DepotMapping.ATTR_NAME);
		try
		{
			m_deoptMapping = ((ClientHandler)this.getParentHandler()).getClient().addDepotMapping(name);
			m_deoptMapping.setDepotPattern(Pattern.compile(this.getStringValue(attrs, DepotMapping.ATTR_DEPOT_PATTERN)));
			m_deoptMapping.setLocalReplacement(this.getStringValue(attrs, DepotMapping.ATTR_LOCAL_REPLACEMENT));
		}
		catch(BackingStoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator(), e);
		}
	}

	DepotMapping getDepotMapping()
	{
		return m_deoptMapping;
	}
}

