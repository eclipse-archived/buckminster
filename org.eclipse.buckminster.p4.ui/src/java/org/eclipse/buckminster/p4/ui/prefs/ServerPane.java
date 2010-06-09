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

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.p4.P4Plugin;
import org.eclipse.buckminster.p4.preferences.Client;
import org.eclipse.buckminster.p4.preferences.Server;
import org.eclipse.buckminster.p4.ui.Messages;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.SaveRunnable;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author Thomas Hallgren
 */
public class ServerPane extends NodeListPrefPane
{
	private Text m_serverName;

	private Text m_user;

	private Text m_password;

	private Text m_passwordCheck;

	private Button m_defaultServer;

	private Button m_export;

	private ClientPane m_clientPane;

	private Server m_server;

	public ServerPane(PreferencePage prefPage, Composite parent)
	{
		super(prefPage, parent, 2);
	}

	public void init(Composite buttonBox)
	{
		m_export = UiUtils.createPushButton(buttonBox, Messages.export_with_dots, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				exportServer();
			}
		});
		m_export.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Composite serverFields = new Composite(this, SWT.NONE);
		GridData gd = new GridData(SWT.FILL, SWT.FILL, true, false);
		gd.horizontalSpan = 2;
		serverFields.setLayoutData(gd);
		GridLayout layout = new GridLayout();
		layout.numColumns = 2;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		serverFields.setLayout(layout);
		m_serverName = UiUtils.createLabeledText(serverFields, Messages.P4_port_with_colon, SWT.READ_ONLY,
				s_tooltipRefresh);
		m_user = UiUtils.createLabeledText(serverFields, Messages.user, SWT.NONE, s_tooltipRefresh);
		m_password = UiUtils.createLabeledText(serverFields, Messages.password, SWT.PASSWORD, null);
		m_passwordCheck = UiUtils.createLabeledText(serverFields, Messages.retype_password, SWT.PASSWORD, null);

		m_defaultServer = new Button(serverFields, SWT.CHECK);
		m_defaultServer.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 3, 1));
		m_defaultServer.setText(Messages.this_is_the_default_server);
		m_defaultServer.setEnabled(false);
		this.createListContents(Messages.P4_clients);

		m_clientPane = new ClientPane(this.getPreferencePage(), this);
		m_clientPane.init();
	}

	protected void clearServer()
	{
		m_server = null;
		m_serverName.setText(""); //$NON-NLS-1$
		m_user.setText(""); //$NON-NLS-1$
		m_password.setText(""); //$NON-NLS-1$
		m_passwordCheck.setText(""); //$NON-NLS-1$
		m_defaultServer.setSelection(false);
		m_clientPane.clearClient();
		this.updateList();
	}

	@Override
	protected void editNode(String node)
	{
		if(!this.assignServerValues())
			return;

		this.setErrorMessage(null);
		this.getPreferencePage().setValid(true);
		if(node == null)
			m_clientPane.setClient(null);
		else
		{
			try
			{
				m_clientPane.setClient(m_server.getClient(node));
			}
			catch(BackingStoreException e)
			{
				displayException(this.getShell(), e);
			}
		}
	}

	@Override
	protected String[] getListContents()
	{
		if(m_server != null)
		{
			try
			{
				return m_server.getClientNames();
			}
			catch(BackingStoreException e)
			{
				displayException(this.getShell(), e);
			}
		}
		return Trivial.EMPTY_STRING_ARRAY;
	}

	@Override
	protected boolean isNewEnabled()
	{
		return m_server != null;
	}

	@Override
	protected void newNode()
	{
		if(!this.assignServerValues())
			return;

		String clientName = this.queryNodeName(Messages.add_P4_client, Messages.client_name, null);
		if(clientName == null)
			return;

		try
		{
			m_server.addClient(clientName);
			this.addAndSelect(clientName);
		}
		catch(BackingStoreException e)
		{
			displayException(this.getShell(), e);
			return;
		}
	}

	@Override
	protected void removeNode(String item)
	{
		try
		{
			Client client = m_server.getClient(item);
			if(client != null)
			{
				m_clientPane.clearClient();
				client.remove();
			}
		}
		catch(BackingStoreException e)
		{
			displayException(this.getShell(), e);
		}
	}

	@Override
	protected void renameNode()
	{
		if(!this.assignServerValues())
			return;

		Client client = m_clientPane.getClient();
		String oldName = client.getName();
		String newName = this.queryNodeName(Messages.rename_P4_client, Messages.client_name, oldName);
		if(newName == null || newName.equals(oldName))
			return;

		try
		{
			m_clientPane.assignRenamedClient(client.createCopy(newName));
			client.remove();
			this.updateAndSelect();
		}
		catch(BackingStoreException e)
		{
			displayException(this.getShell(), e);
			return;
		}
	}

	@Override
	protected void selectionChanged()
	{
		super.selectionChanged();
		m_clientPane.selectionChanged();
	}

	void assignRenamedServer(Server server)
	{
		m_server = server;
		if(server != null)
			m_serverName.setText(server.getName());
		m_clientPane.assignRenamedClient(null);
	}

	void exportServer()
	{
		FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { '*' + Server.FILE_EXTENSION });
		final String location = dlg.open();
		if(location == null)
			return;

		SaveRunnable sb = new SaveRunnable(m_server, new Path(location));
		try
		{
			sb.run(new NullProgressMonitor());
		}
		catch(Exception e)
		{
			CoreException t = BuckminsterException.wrap(e);
			String msg = NLS.bind(Messages.unable_to_save_file_0, location);
			P4Plugin.getLogger().error(t, msg);
			ErrorDialog.openError(getShell(), null, msg, t.getStatus());
		}
	}

	Server getServer()
	{
		return m_server;
	}

	boolean performOk()
	{
		if(!m_clientPane.performOk())
			return false;

		if(m_server != null)
			if(!this.assignServerValues())
				return false;

		return true;
	}

	boolean setServer(Server server)
	{
		boolean hasServer = (server != null);

		m_user.setEnabled(hasServer);
		m_password.setEnabled(hasServer);
		m_passwordCheck.setEnabled(hasServer);
		m_defaultServer.setEnabled(hasServer);
		m_export.setEnabled(hasServer);

		if(hasServer)
		{
			if(server.equals(m_server))
				return true;
			if(!this.assignServerValues())
				return false;
			m_server = server;
			m_serverName.setText(TextUtils.notNullString(server.getName()));
			m_user.setText(TextUtils.notNullString(server.getUser()));

			String pwd = TextUtils.notNullString(server.getPassword());
			m_password.setText(pwd);
			m_passwordCheck.setText(pwd);
			m_defaultServer.setSelection(server.isDefaultServer());
		}
		else
		{
			if(m_server == null)
				return true;
			this.clearServer();
		}

		this.updateList();
		return true;
	}

	private boolean assignServerValues()
	{
		if(m_server == null)
			return true;

		String newPassword = UiUtils.trimmedValue(m_password);
		if(newPassword != null && !newPassword.equals(UiUtils.trimmedValue(m_passwordCheck)))
		{
			MessageDialog.openError(this.getShell(), null, Messages.passwords_dont_match);
			return false;
		}
		m_server.setPassword(newPassword);
		m_server.setUser(UiUtils.trimmedValue(m_user));
		if(m_defaultServer.getSelection())
			m_server.setAsDefault();
		return true;
	}
}
