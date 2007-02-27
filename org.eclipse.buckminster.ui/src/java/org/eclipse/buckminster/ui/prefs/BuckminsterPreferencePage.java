/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.prefs;

import java.util.ArrayList;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.FileUtils;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.resolver.ResolverFactoryMaintainer;
import org.eclipse.buckminster.runtime.BuckminsterPreferences;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.LabeledCombo;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.osgi.service.prefs.BackingStoreException;

public class BuckminsterPreferencePage extends PreferencePage implements IWorkbenchPreferencePage
{
	private Button m_addButton;

	private Text m_bmProjectPath;

	private Button m_bmProjectPathBrowse;

	private Button m_defaultButton;

	private Button m_eclipseLoggerToConsoleButton;

	private LabeledCombo m_logLevelConsole;

	private LabeledCombo m_logLevelEclipseLogger;

	private Button m_moveDownButton;

	private Button m_moveUpButton;

	private ListViewer m_queryResolvers;

	private ListViewer m_queryResolversToAdd;

	private String[] m_registeredResolverFactories;

	private Button m_removeButton;

	private Text m_siteName;

	public BuckminsterPreferencePage()
	{
		setValid(false);
	}

	public void init(IWorkbench workbench)
	{
		// noop
	}

	@Override
	public boolean performOk()
	{
		return apply();
	}

	@Override
	protected Control createContents(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout(1, false));
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createSiteNameGroup(topComposite);
		createBuckminsterProjectLocation(topComposite);
		createResolveControlGroup(topComposite);
		createLogConfGroup(topComposite);
		setControls();
		return topComposite;
	}

	@Override
	protected void performApply()
	{
		apply();
	}

	@Override
	protected void performDefaults()
	{
		setControls();
	}

	private void addResolver()
	{
		moveItem(m_queryResolversToAdd, m_queryResolvers);
	}

	private boolean apply()
	{
		BuckminsterPreferences.setSiteName(UiUtils.trimmedValue(m_siteName));

		String tmp = UiUtils.trimmedValue(m_bmProjectPath);
		IPath path = null;
		if(tmp != null)
		{
			path = new Path(tmp);
			IPath defaultLocation = CorePlugin.getDefault().getBuckminsterProjectLocation();
			if(FileUtils.pathEquals(path, defaultLocation))
				path = null;
		}
		BuckminsterPreferences.setBuckminsterProjectContents(path);

		BuckminsterPreferences.setLogLevelConsole(LogLevel.values()[m_logLevelConsole.getSelectionIndex()].getLogLevel());
		BuckminsterPreferences.setLogLevelEclipseLogger(LogLevel.values()[m_logLevelEclipseLogger.getSelectionIndex()].getLogLevel());
		BuckminsterPreferences.setEclipseLoggerToConsole(m_eclipseLoggerToConsoleButton.getSelection());
		BuckminsterPreferences.setQueryResolverSortOrder((m_queryResolvers == null)
				? getRegisteredResolverFactories()
				: m_queryResolvers.getList().getItems());
		try
		{
			BuckminsterPreferences.save();
			return true;
		}
		catch(BackingStoreException e)
		{
			setErrorMessage("Failed to store preferences");
			return false;
		}
	}

	private void createBuckminsterProjectLocation(Composite parent)
	{
		Group group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(3, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		group.setText("Buckminster project contents");

		m_defaultButton = new Button(group, SWT.CHECK);
		m_defaultButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.FILL, true, true, 3, 1));
		m_defaultButton.setText("Use default");
		m_defaultButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				if(((Button)se.getSource()).getSelection())
				{
					m_bmProjectPath.setText(CorePlugin.getDefault().getBuckminsterProjectLocation().toOSString());
					m_bmProjectPath.setEditable(false);
					m_bmProjectPathBrowse.setEnabled(false);
				}
				else
				{
					m_bmProjectPath.setEditable(true);
					m_bmProjectPathBrowse.setEnabled(true);
				}
			}
		});

		Label lbl = new Label(group, SWT.NONE);
		lbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		lbl.setText("Directory:");

		m_bmProjectPath = new Text(group, SWT.BORDER);
		m_bmProjectPath.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		m_bmProjectPath.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				setErrorMessage(null);
				String path = ((Text)me.widget).getText().trim();
				if(path.length() == 0)
				{
					setValid(false);
					setErrorMessage("Invalid directory");
					return;
				}
				setValid(true);
				m_defaultButton.setSelection(FileUtils.pathEquals(new Path(path), CorePlugin.getDefault()
						.getBuckminsterProjectLocation()));
			}
		});

		m_bmProjectPathBrowse = new Button(group, SWT.PUSH);
		m_bmProjectPathBrowse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		m_bmProjectPathBrowse.setText("Browse...");
		m_bmProjectPathBrowse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				DirectoryDialog dlg = new DirectoryDialog(getShell(), SWT.OPEN);
				dlg.setMessage("Select the .buckminster project contents directory");
				String name = dlg.open();
				if(name != null)
					m_bmProjectPath.setText(name);
			}
		});

		boolean hasBuckminsterProject;
		try
		{
			hasBuckminsterProject = CorePlugin.getDefault().getBuckminsterProject(false, null) != null;
		}
		catch(CoreException e)
		{
			hasBuckminsterProject = false;
		}

		if(hasBuckminsterProject)
		{
			m_defaultButton.setEnabled(false);
			m_bmProjectPath.setEnabled(false);
			m_bmProjectPathBrowse.setEnabled(false);
		}
	}

	private void createLogConfGroup(Composite parent)
	{
		Group loggingGroup = new Group(parent, SWT.NONE);
		loggingGroup.setLayout(new GridLayout(3, false));
		loggingGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		loggingGroup.setText("Logging control");
		m_logLevelConsole = UiUtils.createEnumCombo(loggingGroup, "Console level:", LogLevel.values(), null);
		m_logLevelConsole.select(0);
		m_logLevelEclipseLogger = UiUtils.createEnumCombo(loggingGroup, "Eclipse level:", LogLevel.values(), null);
		m_logLevelEclipseLogger.select(0);
		m_eclipseLoggerToConsoleButton = UiUtils.createCheckButton(loggingGroup, "Eclipse -> Console", null);
	}

	private void createResolotionOrderGroup(Composite parent)
	{
		Group resolveOrderGroup = new Group(parent, SWT.NONE);
		resolveOrderGroup.setLayout(new GridLayout(3, false));
		resolveOrderGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 3, 1));
		resolveOrderGroup.setText("Order of resolution");
		m_queryResolvers = new ListViewer(resolveOrderGroup, SWT.BORDER);
		m_queryResolvers.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_queryResolvers.setContentProvider(new ArrayContentProvider());
		m_queryResolvers.setLabelProvider(new LabelProvider());
		m_queryResolvers.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				enableDisableUpDownButtons();
			}
		});

		Composite buttonBox = new Composite(resolveOrderGroup, SWT.NONE);
		buttonBox.setLayout(new GridLayout(1, true));
		buttonBox.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, true));

		m_addButton = UiUtils.createPushButton(buttonBox, "<-- Add", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				addResolver();
			}
		});
		m_addButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_removeButton = UiUtils.createPushButton(buttonBox, "Remove -->", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				removeResolver();
			}
		});
		m_removeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_moveUpButton = UiUtils.createPushButton(buttonBox, "Move up", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				swapAndReselect(0, -1);
			}
		});
		m_moveUpButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_moveDownButton = UiUtils.createPushButton(buttonBox, "Move down", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				swapAndReselect(1, 0);
			}
		});
		m_moveDownButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_queryResolversToAdd = new ListViewer(resolveOrderGroup, SWT.BORDER);
		m_queryResolversToAdd.getList().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		m_queryResolversToAdd.setContentProvider(new ArrayContentProvider());
		m_queryResolversToAdd.setLabelProvider(new LabelProvider());
		m_queryResolversToAdd.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				enableDisableUpDownButtons();
			}
		});

	}

	private void createResolveControlGroup(Composite parent)
	{
		Group resolveControlGroup = new Group(parent, SWT.NONE);
		resolveControlGroup.setLayout(new GridLayout(3, false));
		resolveControlGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		resolveControlGroup.setText("Resolver control");
		String[] registeredFactories = getRegisteredResolverFactories();
		if(registeredFactories.length > 1)
			createResolotionOrderGroup(resolveControlGroup);
	}

	private void createSiteNameGroup(Composite parent)
	{
		Group siteNameGroup = new Group(parent, SWT.NONE);
		siteNameGroup.setLayout(new GridLayout(1, false));
		siteNameGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		siteNameGroup.setText("Site name:");

		m_siteName = new Text(siteNameGroup, SWT.BORDER);
		m_siteName.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		m_siteName.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				setValid(true);
				setErrorMessage(null);
				String s = ((Text)me.widget).getText().trim();
				if(s.length() == 0)
				{
					setValid(false);
					setErrorMessage("Missing sitename");
				}
			}
		});
	}

	private void enableDisableUpDownButtons()
	{
		List list = m_queryResolvers.getList();
		int top = list.getItemCount();
		int idx = list.getSelectionIndex();

		List addList = m_queryResolversToAdd.getList();
		m_addButton.setEnabled(addList.getItemCount() > 0 && addList.getSelectionIndex() >= 0);

		m_moveUpButton.setEnabled(idx > 0);
		m_removeButton.setEnabled(idx >= 0);
		m_moveDownButton.setEnabled(idx >= 0 && idx < top - 1);
	}

	private String[] getPossibleResolverAdditions()
	{
		String[] registered = getRegisteredResolverFactories();
		String[] current = m_queryResolvers.getList().getItems();
		ArrayList<String> possible = null;
		for(String name : registered)
		{
			boolean found = false;
			for(String currName : current)
				if(currName.equals(name))
				{
					found = true;
					break;
				}
			if(found)
				continue;
			if(possible == null)
				possible = new ArrayList<String>();
			possible.add(name);
		}
		return possible == null
				? Trivial.EMPTY_STRING_ARRAY
				: possible.toArray(new String[possible.size()]);
	}

	private synchronized String[] getRegisteredResolverFactories()
	{
		if(m_registeredResolverFactories == null)
			m_registeredResolverFactories = ResolverFactoryMaintainer.getRegisterFactoryIDs();
		return m_registeredResolverFactories;
	}

	private void moveItem(ListViewer from, ListViewer to)
	{
		List list = from.getList();
		int idx = list.getSelectionIndex();
		if(idx < 0)
			return;

		to.add(list.getItem(idx));
		list.remove(idx);
		int top = list.getItemCount();
		if(idx >= top)
			idx = top - 1;
		if(idx >= 0)
			list.select(idx);
		enableDisableUpDownButtons();
	}

	private void removeResolver()
	{
		moveItem(m_queryResolvers, m_queryResolversToAdd);
	}

	private void setControls()
	{
		m_siteName.setText(TextUtils.notNullString(BuckminsterPreferences.getSiteName()));

		IPath location = BuckminsterPreferences.getBuckminterProjectContents();
		IPath defaultLocation = CorePlugin.getDefault().getBuckminsterProjectLocation();
		boolean isDefault;
		if(location == null)
		{
			location = defaultLocation;
			isDefault = true;
		}
		else
			isDefault = FileUtils.pathEquals(location, defaultLocation);

		m_defaultButton.setSelection(isDefault);
		m_bmProjectPath.setEditable(!isDefault);
		m_bmProjectPathBrowse.setEnabled(!isDefault);
		m_bmProjectPath.setText(location == null ? "" : location.toOSString());

		LogLevel.setComboLogLevel(m_logLevelConsole, BuckminsterPreferences.getLogLevelConsole());
		LogLevel.setComboLogLevel(m_logLevelEclipseLogger, BuckminsterPreferences.getLogLevelEclipseLogger());
		m_eclipseLoggerToConsoleButton.setSelection(BuckminsterPreferences.isEclipseLoggerToConsole());

		if(m_queryResolvers != null)
		{
			m_queryResolvers.setInput(BuckminsterPreferences.getQueryResolverSortOrder());
			m_queryResolvers.getList().select(0);
			m_queryResolversToAdd.setInput(getPossibleResolverAdditions());
			enableDisableUpDownButtons();
		}
	}

	private void swapAndReselect(int idxOffset, int selectionOffset)
	{
		List list = m_queryResolvers.getList();
		int idx = list.getSelectionIndex() + idxOffset;
		if(idx <= 0)
			return;

		String[] items = list.getItems();
		if(idx >= items.length)
			return;

		String moved = items[idx - 1];
		items[idx - 1] = items[idx];
		items[idx] = moved;
		list.setItems(items);
		list.select(idx + selectionOffset);
		enableDisableUpDownButtons();
	}
}
