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

import java.util.ArrayList;

import org.eclipse.buckminster.core.XMLConstants;
import org.eclipse.buckminster.sax.ISaxable;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Server extends NodeWrapper implements ISaxable
{
	public static final String BM_SERVER_NS = XMLConstants.BM_PREFIX + "P4Server-1.0";
	public static final String BM_SERVER_PREFIX = "p4s";
	public static final String BM_SERVER_RESOURCE = "/p4server-1.0.xsd";
	public static final String FILE_EXTENSION = ".p4srv";
	public static final String TAG = "server";
	public static final String ATTR_NAME = "name";
	public static final String ATTR_PASSWORD = "password";
	public static final String ATTR_USER = "user";
	public static final String ATTR_DEFAULT_CLIENT = "defaultClient";

	Server(Preferences preferences)
	{
		super(preferences);
	}

	public Client addClient(String name) throws BackingStoreException
	{
		Preferences prefs = getPreferences();
		boolean first = (prefs.childrenNames().length == 0);
		if(!first && prefs.nodeExists(name))
			throw new BackingStoreException("Client already exists");

		Client client = new Client(this, prefs.node(name));
		if(first)
			setDefaultClient(name);

		return client;
	}

	public Server createCopy(String newName) throws BackingStoreException
	{
		Server copy = P4Preferences.getInstance().addServer(newName);
		deepCopy(getPreferences(), copy.getPreferences());
		return copy;
	}

	public Client getClient(String name) throws BackingStoreException
	{
		Preferences prefs = getPreferences();
		return prefs.nodeExists(name) ? new Client(this, prefs.node(name)) : null;
	}

	public String getPassword()
	{
		return getPreferences().get(ATTR_PASSWORD, null);
	}

	public String getUser()
	{
		return getPreferences().get(ATTR_USER, null);
	}

	public String getDefaultClientName()
	{
		return getPreferences().get(ATTR_DEFAULT_CLIENT, null);
	}

	public String[] getClientNames() throws BackingStoreException
	{
		return getPreferences().childrenNames();
	}

	public Client[] getClients() throws BackingStoreException
	{
		Preferences prefs = getPreferences();
		ArrayList<Client> clients = new ArrayList<Client>();
		for (String child : prefs.childrenNames())
		{
			try
			{
				clients.add(new Client(this, prefs.node(child)));
			}
			catch (IllegalStateException e)
			{
				// Someone removed this node during iteration
				continue;
			}
		}
		return clients.toArray(new Client[clients.size()]);
	}

	public Client getDefaultClient() throws BackingStoreException
	{
		String defaultName = getDefaultClientName();
		if(defaultName != null)
		{
			Client defaultClient = getClient(defaultName);
			if(defaultClient != null)
				return defaultClient;
		}
		throw new BackingStoreException("No default client exists");
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public void setDefaultClient(String clientName)
	{
		putString(ATTR_DEFAULT_CLIENT, clientName);
	}

	public boolean isDefaultServer()
	{
		return getName().equals(P4Preferences.getInstance().getDefaultServerName());
	}

	@Override
	public void remove() throws BackingStoreException
	{
		if(isDefaultServer())
			P4Preferences.getInstance().setOtherDefaultServer(getName());
		super.remove();
	}

	public void setAsDefault()
	{
		P4Preferences.getInstance().setDefaultServer(getName());
	}

	public void setOtherDefaultClient(String clientName) throws BackingStoreException
	{
		Preferences prefs = getPreferences();
		for (String childName : prefs.childrenNames())
		{
			if(!childName.equals(clientName))
			{
				setDefaultClient(childName);
				break;
			}
		}
	}

	public void setPassword(String password)
	{
		putString(ATTR_PASSWORD, password);
	}

	public void setUser(String user)
	{
		putString(ATTR_USER, user);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		addAttribute(attrs, ATTR_NAME, getName());
		addAttribute(attrs, ATTR_DEFAULT_CLIENT, getDefaultClientName());
		addAttribute(attrs, ATTR_USER, getUser());
		addAttribute(attrs, ATTR_PASSWORD, getPassword());
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		try
		{
			for(Client client : getClients())
				client.toSax(receiver, namespace, prefix, client.getDefaultTag());
		}
		catch(BackingStoreException e)
		{
			throw new SAXException(e);
		}
	}

	public void toSax(ContentHandler receiver) throws SAXException
	{
		receiver.startDocument();
		receiver.startPrefixMapping(BM_SERVER_PREFIX, BM_SERVER_NS);
		toSax(receiver, BM_SERVER_NS, BM_SERVER_PREFIX, getDefaultTag());
		receiver.endPrefixMapping(BM_SERVER_PREFIX);
		receiver.endDocument();
	}
}
