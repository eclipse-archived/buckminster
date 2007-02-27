/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.p4.ui.prefs;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.p4.preferences.P4Preferences;
import org.eclipse.buckminster.p4.preferences.Server;
import org.eclipse.buckminster.p4.preferences.ServerParser;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author Thomas Hallgren
 */
public class RootPane extends NodeListPrefPane
{
	private ServerPane m_serverPane;

	public RootPane(PreferencePage prefPage, Composite parent)
	{
		super(prefPage, parent, 1);
	}

	public void init()
	{
		Composite buttonBox = this.createListContents("Known P4 Ports");
		Button importButton = UiUtils.createPushButton(buttonBox, "Import...", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				importServer();
			}
		});
		importButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		m_serverPane = new ServerPane(this.getPreferencePage(), this);
		m_serverPane.init(buttonBox);
		this.updateList();
		this.selectionChanged();
	}

	boolean performOk()
	{
		if(m_serverPane.performOk())
		{
			try
			{
				P4Preferences.getInstance().save();
				return true;
			}
			catch(BackingStoreException e)
			{
				displayException(this.getShell(), e);
			}
		}
		return false;
	}

	@Override
	protected boolean isNewEnabled()
	{
		return true;
	}

	@Override
	protected void selectionChanged()
	{
		super.selectionChanged();
		m_serverPane.selectionChanged();
	}

	@Override
	protected void newNode()
	{
		String serverName = this.queryNodeName("Add P4 Server", "P4 Port", null);
		if(serverName == null)
			return;

		try
		{
			P4Preferences.getInstance().addServer(serverName);
			this.addAndSelect(serverName);
		}
		catch(BackingStoreException e)
		{
			displayException(this.getShell(), e);
			return;
		}
	}

	@Override
	protected void renameNode()
	{
		Server server = m_serverPane.getServer();
		String oldName = server.getName();
		String newName = this.queryNodeName("Change P4 Port", "P4 Port", oldName);
		if(newName == null || newName.equals(oldName))
			return;

		try
		{
			m_serverPane.assignRenamedServer(server.createCopy(newName));
			server.remove();
			this.updateAndSelect();
		}
		catch(BackingStoreException e)
		{
			displayException(this.getShell(), e);
			return;
		}
	}

	@Override
	protected void editNode(String node)
	{
		setErrorMessage(null);
		if(node == null)
			m_serverPane.setServer(null);
		else
		{
			try
			{
				m_serverPane.setServer(P4Preferences.getInstance().getServer(node));
			}
			catch(BackingStoreException e)
			{
				displayException(this.getShell(), e);
			}
		}
	}

	@Override
	protected void removeNode(String serverName)
	{
		try
		{
			Server server = P4Preferences.getInstance().getServer(serverName);
			if(server != null)
			{
				m_serverPane.clearServer();
				server.remove();
			}
		}
		catch(BackingStoreException e)
		{
			displayException(this.getShell(), e);
		}
	}

	@Override
	protected String[] getListContents()
	{
		P4Preferences prefs = P4Preferences.getInstance();
		try
		{
			return prefs.getServerNames();
		}
		catch(BackingStoreException e)
		{
			displayException(this.getShell(), e);
			return Trivial.EMPTY_STRING_ARRAY;
		}
	}

	private void importServer()
	{
		FileDialog dlg = new FileDialog(this.getShell());
		dlg.setFilterExtensions(new String[] { '*' + Server.FILE_EXTENSION });
		String name = dlg.open();
		if(name == null)
			return;

		InputStream input = null;
		try
		{
			File file = new File(name);
			input = new BufferedInputStream(new FileInputStream(file));
			IParser<Server> parser = new ServerParser(new ServerParser.IAskReplaceOK()
			{
				public boolean isReplaceOK(String serverName)
				{
					return MessageDialog.openConfirm(getShell(), null, "Ok to replace server " + serverName);
				}
			});
			m_serverPane.assignRenamedServer(parser.parse(name, input));
			this.updateAndSelect();
		}
		catch(ServerParser.ReplaceDeniedException e)
		{
			return;
		}
		catch(Exception e)
		{
			displayException(this.getShell(), e);
		}
		finally
		{
			IOUtils.close(input);
		}
	}
}
