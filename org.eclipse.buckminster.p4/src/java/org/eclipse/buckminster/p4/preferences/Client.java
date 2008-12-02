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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.p4.Messages;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

/**
 * @author Thomas Hallgren
 */
public class Client extends NodeWrapper
{
	public static final String TAG = "client"; //$NON-NLS-1$

	public static final String ATTR_NAME = "name"; //$NON-NLS-1$

	public static final String ATTR_LOCAL_ROOT = "localRoot"; //$NON-NLS-1$

	private final Server m_server;

	Client(Server server, Preferences clientNode)
	{
		super(clientNode);
		m_server = server;
	}

	public DepotMapping addDepotMapping(String name) throws BackingStoreException
	{
		Preferences prefs = getPreferences();
		boolean first = (prefs.childrenNames().length == 0);
		if(!first && prefs.nodeExists(name))
			throw new BackingStoreException(Messages.depot_mapping_already_exists);

		return new DepotMapping(this, prefs.node(name));
	}

	public Client createCopy(String newName) throws BackingStoreException
	{
		Client copy = m_server.addClient(newName);
		deepCopy(getPreferences(), copy.getPreferences());
		return copy;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o == this)
			return true;

		if(!super.equals(o))
			return false;

		if(!(o instanceof Client))
			return false;
		Client that = (Client)o;

		if(m_server != that.m_server)
			return false;

		return true;
	}

	public String getDefaultTag()
	{
		return TAG;
	}

	public DepotMapping getDepotMapping(String name) throws BackingStoreException
	{
		Preferences prefs = getPreferences();
		return prefs.nodeExists(name)
				? new DepotMapping(this, prefs.node(name))
				: null;
	}

	public String[] getDepotMappingNames() throws BackingStoreException
	{
		return getPreferences().childrenNames();
	}

	public DepotMapping[] getDepotMappings() throws BackingStoreException
	{
		Preferences prefs = getPreferences();
		ArrayList<DepotMapping> depotMappings = new ArrayList<DepotMapping>();
		for(String child : prefs.childrenNames())
		{
			try
			{
				depotMappings.add(new DepotMapping(this, prefs.node(child)));
			}
			catch(IllegalStateException e)
			{
				// Someone removed this node during iteration
				continue;
			}
		}
		return depotMappings.toArray(new DepotMapping[depotMappings.size()]);
	}

	public final String getLocalRoot()
	{
		return getPreferences().get(Client.ATTR_LOCAL_ROOT, null);
	}

	/**
	 * Returns the client mapping that matches the given <code>depotPath</code>.
	 * 
	 * @param depotPath
	 *            The depotPath to use for the match, must not be null.
	 * @return The matching mapping or <code>null</code> if no match was found.
	 */
	public IPath getMappingForDepot(IPath depotPath) throws BackingStoreException
	{
		String pathString = depotPath.toPortableString();
		for(DepotMapping mapping : getDepotMappings())
		{
			Pattern lpExpr = mapping.getDepotPattern();
			Matcher matcher = lpExpr.matcher(pathString);
			if(matcher.matches())
			{
				String repl = matcher.replaceAll(mapping.getLocalReplacement()).trim();
				if(repl.length() > 0)
					return new Path(repl);
			}
		}
		return null;
	}

	public final Server getServer()
	{
		return m_server;
	}

	@Override
	public int hashCode()
	{
		int hc = super.hashCode();
		hc = 37 * hc + m_server.hashCode();

		return hc;
	}

	public boolean isDefaultClient()
	{
		return getName().equals(m_server.getDefaultClientName());
	}

	@Override
	public void remove() throws BackingStoreException
	{
		if(isDefaultClient())
			m_server.setOtherDefaultClient(getName());
		super.remove();
	}

	/**
	 * Change so that this client becomes the one and only default client.
	 * 
	 * @throws BackingStoreException
	 */
	public void setAsDefault()
	{
		m_server.setDefaultClient(getName());
	}

	public void setLocalRoot(String localRoot)
	{
		putString(Client.ATTR_LOCAL_ROOT, localRoot);
	}

	@Override
	protected void addAttributes(AttributesImpl attrs) throws SAXException
	{
		addAttribute(attrs, ATTR_NAME, getName());
		addAttribute(attrs, ATTR_LOCAL_ROOT, getLocalRoot());
	}

	@Override
	protected void emitElements(ContentHandler receiver, String namespace, String prefix) throws SAXException
	{
		try
		{
			for(DepotMapping depotMapping : getDepotMappings())
				depotMapping.toSax(receiver, namespace, prefix, depotMapping.getDefaultTag());
		}
		catch(BackingStoreException e)
		{
			throw new SAXException(e);
		}
	}
}
