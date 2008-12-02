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
public class ServerHandler extends ExtensionAwareHandler
{
	final static String TAG = Server.TAG;

	private final ClientHandler m_clientHandler = new ClientHandler(this);

	private Server m_server;

	public ServerHandler(AbstractHandler parent)
	{
		super(parent);
	}

	@Override
	public ChildHandler createHandler(String uri, String localName, Attributes attrs) throws SAXException
	{
		ChildHandler ch = null;
		if(Client.TAG.equals(localName))
			ch = m_clientHandler;
		if(ch == null)
			ch = super.createHandler(uri, localName, attrs);
		return ch;
	}

	@Override
	public void handleAttributes(Attributes attrs) throws SAXException
	{
		String name = this.getStringValue(attrs, Server.ATTR_NAME);
		P4Preferences prefs = P4Preferences.getInstance();
		try
		{
			m_server = prefs.getServer(name);
			if(m_server != null)
			{
				if(!((ServerParser)this.getTopHandler()).isReplaceOK(name))
					throw new ServerParser.ReplaceDeniedException();
				m_server.clear();
			}
			else
				m_server = prefs.addServer(name);

			m_server.setDefaultClient(getOptionalStringValue(attrs, Server.ATTR_DEFAULT_CLIENT));
			m_server.setPassword(getOptionalStringValue(attrs, Server.ATTR_PASSWORD));
			m_server.setUser(getOptionalStringValue(attrs, Server.ATTR_USER));
		}
		catch(BackingStoreException e)
		{
			throw new SAXParseException(e.getMessage(), this.getDocumentLocator(), e);
		}
	}

	Server getServer()
	{
		return m_server;
	}
}
