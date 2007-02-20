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


import java.io.File;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.p4.preferences.Client;
import org.eclipse.buckminster.p4.preferences.DepotMapping;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Text;
import org.osgi.service.prefs.BackingStoreException;

/**
 * @author Thomas Hallgren
 */
public class ClientPane extends NodeListPrefPane
{	
	private Client m_client;
	private DepotMapping m_depotMapping;

	private Text m_clientName;
	private Text m_localRoot;
	private Button m_browse;
	private Button m_defaultClient;

	private Text m_depotMappingName;
	private Text m_depotPattern;
	private Text m_localReplacement;

	public ClientPane(PreferencePage prefPage, Composite parent)
	{
		super(prefPage, parent, 2);
	}

	boolean performOk()
	{
		if(m_depotMapping != null)
			if(!this.assignDepotMappingValues())
				return false;

		if(m_client != null)
			if(!this.assignClientValues())
				return false;

		return true;
	}

	Client getClient()
	{
		return m_client;
	}

	void assignRenamedClient(Client client)
	{
		m_client = client;
		if(client != null)
			m_clientName.setText(client.getName());
		this.assignRenamedDepotMapping(null);
	}

	boolean setClient(Client client)
	{
		boolean hasClient = (client != null);

		m_localRoot.setEnabled(hasClient);
		m_browse.setEnabled(hasClient);
		m_defaultClient.setEnabled(hasClient);

		if(hasClient)
		{
			if(client.equals(m_client))
				return true;
			if(!this.assignClientValues())
				return false;
			m_client = client;
			m_clientName.setText(TextUtils.notNullString(client.getName()));
			m_localRoot.setText(TextUtils.notNullString(client.getLocalRoot()));
			m_defaultClient.setSelection(client.isDefaultClient());
		}
		else
		{
			if(m_client == null)
				return true;
			this.clearClient();
		}

		this.updateList();
		return true;
	}

	void assignRenamedDepotMapping(DepotMapping depotMapping)
	{
		m_depotMapping = depotMapping;
		m_depotMappingName.setText(depotMapping == null ? "" : depotMapping.getName());
	}

	boolean setDepotMapping(DepotMapping depotMapping)
	{
		boolean hasDepotMapping = (depotMapping != null);
		m_depotPattern.setEnabled(hasDepotMapping);
		m_localReplacement.setEnabled(hasDepotMapping);

		if(hasDepotMapping)
		{
			if(depotMapping.equals(m_depotMapping))
				return true;
			if(!this.assignDepotMappingValues())
				return false;
			m_depotMapping = depotMapping;
			m_depotMappingName.setText(depotMapping.getName());
			m_depotPattern.setText(TextUtils.notNullString(depotMapping.getDepotPattern()));
			m_localReplacement.setText(TextUtils.notNullString(depotMapping.getLocalReplacement()));
		}
		else
		{
			if(m_depotMapping == null)
				return true;
			this.clearDepotMapping();
		}
		this.selectionChanged();
		return true;
	}

	private boolean assignClientValues()
	{
		if(m_client == null)
			return true;

		m_client.setLocalRoot(UiUtils.trimmedValue(m_localRoot));
		if(m_defaultClient.getSelection())
			m_client.setAsDefault();
		return true;
	}

	private boolean assignDepotMappingValues()
	{
		if(m_depotMapping == null)
			return true;

		String tmp = UiUtils.trimmedValue(m_depotPattern);
		if(tmp != null)
		{
			if(tmp.charAt(0) != '^')
				tmp = '^' + tmp;
			if(tmp.charAt(tmp.length()-1) != '$')
				tmp = tmp + '$';
			m_depotMapping.setDepotPattern(Pattern.compile(tmp));
		}
		else
			m_depotMapping.setDepotPattern(null);

		m_depotMapping.setLocalReplacement(UiUtils.trimmedValue(m_localReplacement));
		return true;
	}

	@Override
	protected boolean isNewEnabled()
	{
		return m_client != null;
	}

	protected void clearClient()
	{
		m_client = null;
		m_clientName.setText("");
		m_localRoot.setText("");
		m_defaultClient.setSelection(false);
	}

	protected void clearDepotMapping()
	{
		m_depotMapping = null;
		m_depotMappingName.setText("");
		m_depotPattern.setText("");
		m_localReplacement.setText("");
	}

	@Override
	protected void newNode()
	{
		if(!this.assignClientValues())
			return;

		String depotMappingName = this.queryNodeName("Add Client depot mapping", "Depot mapping name", null);
		if(depotMappingName == null)
			return;

		try
		{
			m_client.addDepotMapping(depotMappingName);
			this.addAndSelect(depotMappingName);
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
		if(!this.assignClientValues())
			return;

		DepotMapping depotMapping = m_depotMapping;
		String oldName = depotMapping.getName();
		String newName = this.queryNodeName("Rename Client depot mapping", "Depot mapping name", oldName);
		if(newName == null || newName.equals(oldName))
			return;

		try
		{
			this.assignRenamedDepotMapping(depotMapping.createCopy(newName));
			depotMapping.remove();
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
		if(!this.assignClientValues())
			return;

		this.setErrorMessage(null);
		this.getPreferencePage().setValid(true);
		if(node == null)
			this.setDepotMapping(null);
		else
		{
			try
			{
				this.setDepotMapping(m_client.getDepotMapping(node));
			}
			catch(BackingStoreException e)
			{
				displayException(this.getShell(), e);
			}
		}
	}

	@Override
	protected void removeNode(String item)
	{
		try
		{
			DepotMapping depotMapping = m_client.getDepotMapping(item);
			if(depotMapping != null)
			{
				this.clearDepotMapping();
				depotMapping.remove();
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
		if(m_client != null)
		{
			try
			{
				return m_client.getDepotMappingNames();
			}
			catch(BackingStoreException e)
			{
				displayException(this.getShell(), e);
			}
		}
		return Trivial.EMPTY_STRING_ARRAY;
	}

	public void init()
	{
		Composite clientFields = new Composite(this, SWT.NONE);
		clientFields.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		GridLayout layout = new GridLayout();
		layout.numColumns = 3;
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		clientFields.setLayout(layout);
		m_clientName = UiUtils.createLabeledText(clientFields, "Name", SWT.READ_ONLY, s_tooltipRefresh);
		m_clientName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));

		m_localRoot = UiUtils.createLabeledText(clientFields, "Local root:", SWT.NONE, s_tooltipRefresh);
		m_localRoot.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent e)
			{
				boolean valid = true;
				String errMsg = null;
				String dirName = ((Text)e.getSource()).getText().trim();
				if(dirName.length() > 0)
				{
					File file = new File(dirName);
					valid = file.exists();
					if(!valid && !dirName.contains("${"))
						errMsg = dirName + " does not exist";
				}

				PreferencePage prefPage = getPreferencePage();
				prefPage.setValid(errMsg == null);
				prefPage.setErrorMessage(errMsg);
			}
		});
		m_browse = new Button(clientFields, SWT.PUSH);
		m_browse.setText("Browse...");
		m_browse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent event)
			{
				browseButtonPressed();
			}
		});

		m_defaultClient = new Button(clientFields, SWT.CHECK);	 
		m_defaultClient.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, false, false, 3, 1));
		m_defaultClient.setText("This is the default client");
		m_defaultClient.setEnabled(false);
		this.createListContents("Depot mappings");

		Composite depotMappingFields = new Composite(this, SWT.NONE);
		depotMappingFields.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));
		layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		depotMappingFields.setLayout(layout);

		m_depotMappingName = UiUtils.createLabeledText(depotMappingFields, "Name", SWT.READ_ONLY, null);
		m_depotPattern = UiUtils.createLabeledText(depotMappingFields, "Depot pattern:", SWT.NONE, new ModifyListener()
		{
			public void modifyText(ModifyEvent ev)
			{
				String errMsg = null;
				String lpe = ((Text)ev.getSource()).getText().trim();
				if(lpe.length() > 0)
				{
					try
					{
						Pattern.compile(lpe);
					}
					catch(PatternSyntaxException e)
					{
						errMsg = e.getMessage();
					}
				}
				PreferencePage prefPage = getPreferencePage();
				prefPage.setValid(errMsg == null);
				prefPage.setErrorMessage(errMsg);
			}
		});

		m_depotPattern.setToolTipText(String.format("%s%n%s%n%s",
			"A regular expression used when creating a client root relative ",
			"path that will be used for component materialization ",
			"based on the full depot path of a component"));

		m_localReplacement = UiUtils.createLabeledText(depotMappingFields, "Local replacement:", SWT.NONE, null);
		m_localReplacement.setToolTipText(String.format("%s%n%s%n%s",
			"The replacement string used when creating the client root relative ",
			"path that will be used for component materialization ",
			"based on the full depot path of a component"));
	}

	void browseButtonPressed()
	{
		DirectoryDialog dialog = new DirectoryDialog(this.getShell());
		dialog.setMessage("P4 Client root");

		String dirName = m_localRoot.getText();
		if(!dirName.equals(""))
		{
			File path = new File(dirName);
			if(path.exists())
				dialog.setFilterPath(new Path(dirName).toOSString());
		}

		String selectedDirectory = dialog.open();
		if(selectedDirectory != null)
			m_localRoot.setText(selectedDirectory);
	}
}

