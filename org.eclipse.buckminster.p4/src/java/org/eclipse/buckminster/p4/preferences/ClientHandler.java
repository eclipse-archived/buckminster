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

import org.eclipse.buckminster.core.parser.ExtensionAwareHandler;
import org.eclipse.buckminster.sax.AbstractHandler;
import org.eclipse.buckminster.sax.ChildHandler;
import org.osgi.service.prefs.BackingStoreException;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


/**
 * @author Thomas Hallgren
 */
public class ClientHandler extends ExtensionAwareHandler
{
	final static String TAG = Client.TAG;

	private final DepotMappingHandler m_depotMappingHandler = new DepotMappingHandler(this);
	private Client m_client;

	public ClientHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs)
	throws SAXException
	{
		ChildHandler ch = null;
		if(DepotMapping.TAG.equals(localName))
			ch = m_depotMappingHandler;
		if(ch == null)
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs)
	throws SAXException
	{
		String name = this.getStringValue(attrs, Client.ATTR_NAME);
		try
		{
			m_client = ((ServerHandler)this.getParentHandler()).getServer().addClient(name);
			m_client.setLocalRoot(getOptionalStringValue(attrs, Client.ATTR_LOCAL_ROOT));
		}
		catch(BackingStoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator(), e);
		}
	}

	Client getClient()
	{
		return m_client;
	}
}

