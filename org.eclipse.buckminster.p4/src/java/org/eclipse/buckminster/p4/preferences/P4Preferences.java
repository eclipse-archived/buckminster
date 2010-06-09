/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.preferences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.buckminster.p4.Messages;
import org.eclipse.buckminster.p4.P4Plugin;
import org.eclipse.buckminster.p4.internal.Connection;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.osgi.util.NLS;
import org.osgi.service.prefs.BackingStoreException;
import org.osgi.service.prefs.Preferences;

/**
 * @author Thomas Hallgren
 */
public class P4Preferences
{
	public static final String ATTR_DEFAULT_SERVER = "defaultServer"; //$NON-NLS-1$

	private static final P4Preferences s_instance = new P4Preferences();

	/**
	 * Configure a default server entry based on either the result of executing <code>p4 set</code> or, if that fails,
	 * the environment variables: <code>P4USER</code>, <code>P4CLIENT</code>, <code>P4PORT</code>, and
	 * <code>P4PASSWD</code>.
	 * 
	 * @return The configured server entry.
	 * @throws BuckminsterException
	 */
	private static final Pattern rxPattern = Pattern.compile("^(P4[A-Z]+)=(.+?)(?:\\s+\\(set[^\\)]*\\))?+$"); //$NON-NLS-1$

	public static P4Preferences getInstance()
	{
		return s_instance;
	}

	private final Preferences m_preferences = Platform.getPreferencesService().getRootNode().node(InstanceScope.SCOPE)
			.node(P4Plugin.PLUGIN_ID);

	public Server addServer(String name) throws BackingStoreException
	{
		if(m_preferences.nodeExists(name))
			throw new BackingStoreException(Messages.name_already_exists);
		Server server = new Server(m_preferences.node(name));
		if(m_preferences.childrenNames().length == 1)
			this.setDefaultServer(name);
		return server;
	}

	public Server configureDefaultServer(Map<String, ? extends Object> scope, boolean overwrite)
			throws BackingStoreException, CoreException
	{
		BufferedReader reader = null;
		Map<String, String> p4Properties;
		try
		{
			Process process = Runtime.getRuntime().exec(new String[] { P4Plugin.getDefault().getP4Binary(), "set" }); //$NON-NLS-1$
			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			p4Properties = new HashMap<String, String>();
			String line;
			while((line = reader.readLine()) != null)
			{
				Matcher m = rxPattern.matcher(line);
				if(m.matches())
					p4Properties.put(m.group(1), m.group(2));
			}
		}
		catch(IOException e)
		{
			p4Properties = System.getenv();
		}
		finally
		{
			IOUtils.close(reader);
		}

		String p4Port = p4Properties.get("P4PORT"); //$NON-NLS-1$
		if(p4Port == null)
			throw BuckminsterException.fromMessage(Messages.missing_environment_P4PORT);

		String p4User = p4Properties.get("P4USER"); //$NON-NLS-1$
		if(p4User == null)
			p4User = System.getProperty("user.name"); //$NON-NLS-1$

		String p4Client = p4Properties.get("P4CLIENT"); //$NON-NLS-1$
		if(p4Client == null)
			throw BuckminsterException.fromMessage(Messages.missing_environment_P4CLIENT);

		Server server = this.getServer(p4Port);
		if(server == null)
			server = this.addServer(p4Port);
		else
		{
			if(!overwrite)
				throw BuckminsterException.fromMessage(NLS.bind(Messages.server_0_already_exists, p4Port));
			server.clear();
		}

		server.setUser(p4User);
		String p4Passwd = p4Properties.get("P4PASSWD"); //$NON-NLS-1$
		if(p4Passwd != null)
			server.setPassword(p4Passwd);

		server.setAsDefault();
		Client client = server.addClient(p4Client);

		// Make an attempt to set the default root as stipulated
		// by the server if possible.
		//
		try
		{
			Connection conn = new Connection(scope, client, p4Port);
			conn.getClientSpec();
		}
		catch(CoreException e)
		{
			// Will fail when we're off-line. Not a big deal here
		}
		client.setAsDefault();

		this.save();
		return server;
	}

	public Server getDefaultServer() throws BackingStoreException
	{
		String name = this.getDefaultServerName();
		return name == null
				? null
				: this.getServer(name);
	}

	public String getDefaultServerName()
	{
		return m_preferences.get(ATTR_DEFAULT_SERVER, null);
	}

	public Server getServer(String name) throws BackingStoreException
	{
		return m_preferences.nodeExists(name)
				? new Server(m_preferences.node(name))
				: null;
	}

	public String[] getServerNames() throws BackingStoreException
	{
		return m_preferences.childrenNames();
	}

	public Server[] getServers() throws BackingStoreException
	{
		ArrayList<Server> servers = new ArrayList<Server>();
		for(String child : m_preferences.childrenNames())
		{
			try
			{
				servers.add(new Server(m_preferences.node(child)));
			}
			catch(IllegalStateException e)
			{
				// Someone removed this node during iteration
				continue;
			}
		}
		return servers.toArray(new Server[servers.size()]);
	}

	public void save() throws BackingStoreException
	{
		m_preferences.flush();
	}

	public void setDefaultServer(String serverName)
	{
		if(serverName == null)
			m_preferences.remove(ATTR_DEFAULT_SERVER);
		else
			m_preferences.put(ATTR_DEFAULT_SERVER, serverName);
	}

	public void setOtherDefaultServer(String serverName) throws BackingStoreException
	{
		for(String childName : m_preferences.childrenNames())
		{
			if(!childName.equals(serverName))
			{
				this.setDefaultServer(childName);
				break;
			}
		}
	}
}
