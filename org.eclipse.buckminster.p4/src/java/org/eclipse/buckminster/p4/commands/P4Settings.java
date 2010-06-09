/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.p4.commands;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.cmdline.AbstractCommand;
import org.eclipse.buckminster.cmdline.Option;
import org.eclipse.buckminster.cmdline.OptionDescriptor;
import org.eclipse.buckminster.cmdline.OptionValueType;
import org.eclipse.buckminster.cmdline.UsageException;
import org.eclipse.buckminster.core.helpers.BMProperties;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.p4.Messages;
import org.eclipse.buckminster.p4.preferences.P4Preferences;
import org.eclipse.buckminster.p4.preferences.Server;
import org.eclipse.buckminster.p4.preferences.ServerParser;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.osgi.util.NLS;

public class P4Settings extends AbstractCommand
{
	static private final OptionDescriptor REMOVE = new OptionDescriptor('R', "remove", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor LIST = new OptionDescriptor('L', "list", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor IMPORT = new OptionDescriptor('I', "import", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor EXPORT = new OptionDescriptor('E', "export", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor OVERWRITE = new OptionDescriptor('O', "overwrite", OptionValueType.NONE); //$NON-NLS-1$

	static private final OptionDescriptor DEFAULT = new OptionDescriptor('D', "default", OptionValueType.REQUIRED); //$NON-NLS-1$

	static private final OptionDescriptor CURRENT = new OptionDescriptor('C', "current", OptionValueType.NONE); //$NON-NLS-1$

	private final ArrayList<String> m_unparsed = new ArrayList<String>();

	private Option m_selectedOption;

	private boolean m_overwrite;

	private boolean m_current;

	private String m_serverName;

	private File m_file;

	@Override
	protected void getOptionDescriptors(List<OptionDescriptor> appendHere) throws Exception
	{
		appendHere.add(REMOVE);
		appendHere.add(LIST);
		appendHere.add(EXPORT);
		appendHere.add(OVERWRITE);
		appendHere.add(DEFAULT);
		appendHere.add(CURRENT);
	}

	@Override
	protected void handleOption(Option option) throws Exception
	{
		if(option.is(EXPORT) || option.is(REMOVE) || option.is(DEFAULT))
		{
			if(m_selectedOption != null)
				throw new UsageException(Messages.only_one_action_per_invocation_please);

			m_serverName = option.getValue();
			m_selectedOption = option;
			return;
		}

		if(option.is(IMPORT) || option.is(LIST))
		{
			if(m_selectedOption != null)
				throw new UsageException(Messages.only_one_action_per_invocation_please);

			m_selectedOption = option;
			return;
		}

		if(option.is(OVERWRITE))
			m_overwrite = true;
		else if(option.is(CURRENT))
			m_current = true;
		else
			throw new UsageException(Messages.unknown_option);
	}

	@Override
	protected void handleUnparsed(String[] unparsed) throws Exception
	{
		for(String arg : unparsed)
			m_unparsed.add(arg);
	}

	@Override
	protected int run(IProgressMonitor monitor) throws Exception
	{
		if(m_selectedOption == null)
			throw new UsageException(Messages.no_action_was_specified);

		if(m_overwrite && !m_selectedOption.is(IMPORT))
			throw new UsageException(Messages.overwrite_can_only_be_used_with_import);

		if(m_current && !m_selectedOption.is(IMPORT))
			throw new UsageException(Messages.default_can_only_be_used_with_import);

		if((m_selectedOption.is(EXPORT) || m_selectedOption.is(IMPORT)) && m_unparsed.size() == 1)
			m_file = new File(m_unparsed.get(0));
		else if(m_unparsed.size() > 0)
			throw new UsageException(Messages.too_many_arguments);

		if(m_selectedOption.is(LIST))
			this.list();
		else if(m_selectedOption.is(REMOVE))
			this.delete();
		else if(m_selectedOption.is(IMPORT))
			this.importServer();
		else if(m_selectedOption.is(DEFAULT))
			this.makeServerDefault();
		else
			this.exportServer();
		return 0;
	}

	private void delete() throws Exception
	{
		this.getServer().remove();
		P4Preferences.getInstance().save();
	}

	private void exportServer() throws Exception
	{
		OutputStream output = null;
		try
		{
			if(m_file == null)
				output = System.out;
			else
				output = new BufferedOutputStream(new FileOutputStream(m_file));
			Utils.serialize(this.getServer(), output);
		}
		finally
		{
			if(output == System.out)
				output.flush();
			else
				IOUtils.close(output);
		}
	}

	private Server getServer() throws Exception
	{
		P4Preferences prefs = P4Preferences.getInstance();
		Server server = prefs.getServer(m_serverName);
		if(server == null)
			throw BuckminsterException.fromMessage(NLS.bind(Messages.no_such_P4_server_0, m_serverName));
		return server;
	}

	private void importServer() throws Exception
	{
		if(m_current)
		{
			P4Preferences prefs = P4Preferences.getInstance();
			Server server = prefs.configureDefaultServer(BMProperties.getSystemProperties(), m_overwrite);
			server.save();
			return;
		}

		InputStream input = null;
		try
		{
			String fileName;
			if(m_file == null)
			{
				input = System.in;
				fileName = "stdin"; //$NON-NLS-1$
			}
			else
			{
				input = new BufferedInputStream(new FileInputStream(m_file));
				fileName = m_file.toString();
			}
			IParser<Server> parser = new ServerParser(new ServerParser.IAskReplaceOK()
			{
				public boolean isReplaceOK(String serverName)
				{
					return m_overwrite;
				}
			});
			Server server = parser.parse(fileName, input);
			server.save();
		}
		finally
		{
			if(input != System.in)
				IOUtils.close(input);
		}
	}

	private void list() throws Exception
	{
		PrintStream out = System.out;
		P4Preferences prefs = P4Preferences.getInstance();
		Server[] servers = prefs.getServers();
		if(servers.length == 0)
		{
			out.println(Messages.no_p4_servers_have_been_configured);
			return;
		}
		for(Server server : prefs.getServers())
		{
			out.print(server.isDefaultServer()
					? "* " : "  "); //$NON-NLS-1$ //$NON-NLS-2$
			out.println(server.getName());
		}
	}

	private void makeServerDefault() throws Exception
	{
		this.getServer().setAsDefault();
		P4Preferences.getInstance().save();
	}
}
