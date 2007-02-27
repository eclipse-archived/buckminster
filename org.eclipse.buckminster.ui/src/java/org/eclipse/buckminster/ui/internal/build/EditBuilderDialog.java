/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.ui.internal.build;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.build.AbstractBuckminsterBuilder;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.build.IBuilderEditor;
import org.eclipse.core.resources.ICommand;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.ErrorDialog;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @author kolwing
 */
public class EditBuilderDialog extends Dialog
{
	private final IProject m_project;
	
	private final ICommand m_builder;

	private final String m_fullName;

	private final IConfigurationElement m_cfgElem;

	private IBuilderEditor m_builderEditor;

	private boolean m_enabled;

	private String m_name;

	private boolean m_auto;

	private boolean m_incremental;

	private boolean m_full;

	private boolean m_clean;

	private boolean m_autoPrinting;

	private boolean m_incrementalPrinting;

	private boolean m_fullPrinting;

	private boolean m_cleanPrinting;

	private String m_refreshResource;

	private String m_derivedResource;
	
	private String m_deltaResource;

	/**
	 * @param parentShell
	 */
	public EditBuilderDialog(Shell parentShell, IProject project, ICommand builder, String fullName, IConfigurationElement cfgElem)
	{
		super(parentShell);

		m_project = project;
		m_builder = builder;
		m_fullName = fullName;
		m_cfgElem = cfgElem;

		this.findBuilderEditor();

		Map<String,String> args = getBuilderArguments(m_builder);
		if (args != null)
		{
			m_name = args.get(AbstractBuckminsterBuilder.ARG_GIVEN_NAME_KEY);
			m_enabled = !AbstractBuckminsterBuilder.isDisabled(args);
			m_refreshResource = args.get(AbstractBuckminsterBuilder.ARG_REFRESH_RESOURCE);
			m_derivedResource = args.get(AbstractBuckminsterBuilder.ARG_DERIVED_RESOURCE);
			m_deltaResource = args.get(AbstractBuckminsterBuilder.ARG_DELTA_RESOURCE_KEY);
			m_autoPrinting = AbstractBuckminsterBuilder.isPrintingEnabledForKind(args, IncrementalProjectBuilder.AUTO_BUILD);
			m_incrementalPrinting = AbstractBuckminsterBuilder.isPrintingEnabledForKind(args, IncrementalProjectBuilder.INCREMENTAL_BUILD);
			m_fullPrinting = AbstractBuckminsterBuilder.isPrintingEnabledForKind(args, IncrementalProjectBuilder.FULL_BUILD);
			m_cleanPrinting = AbstractBuckminsterBuilder.isPrintingEnabledForKind(args, IncrementalProjectBuilder.CLEAN_BUILD);
		}

		if (m_builder.isConfigurable())
		{
			m_auto = m_builder.isBuilding(IncrementalProjectBuilder.AUTO_BUILD);
			m_incremental = m_builder.isBuilding(IncrementalProjectBuilder.INCREMENTAL_BUILD);
			m_full = m_builder.isBuilding(IncrementalProjectBuilder.FULL_BUILD);
			m_clean = m_builder.isBuilding(IncrementalProjectBuilder.CLEAN_BUILD);
		}
	}

	public ICommand getBuilder()
	{
		return m_builder;
	}

	@Override
	protected void okPressed()
	{
		if (m_builder.isConfigurable())
		{
			m_builder.setBuilding(IncrementalProjectBuilder.AUTO_BUILD, m_auto);
			m_builder.setBuilding(IncrementalProjectBuilder.INCREMENTAL_BUILD, m_incremental);
			m_builder.setBuilding(IncrementalProjectBuilder.FULL_BUILD, m_full);
			m_builder.setBuilding(IncrementalProjectBuilder.CLEAN_BUILD, m_clean);
		}

		Map<String, String> args = getBuilderArguments(m_builder);
		if (args == null)
			args = new HashMap<String, String>();
	
		if (m_name == null)
			args.remove(AbstractBuckminsterBuilder.ARG_GIVEN_NAME_KEY);
		else
			args.put(AbstractBuckminsterBuilder.ARG_GIVEN_NAME_KEY, m_name);

		AbstractBuckminsterBuilder.setDisabled(args, !m_enabled);

		if (m_refreshResource == null)
			args.remove(AbstractBuckminsterBuilder.ARG_REFRESH_RESOURCE);
		else
			args.put(AbstractBuckminsterBuilder.ARG_REFRESH_RESOURCE, m_refreshResource);

		if (m_derivedResource == null)
			args.remove(AbstractBuckminsterBuilder.ARG_DERIVED_RESOURCE);
		else
			args.put(AbstractBuckminsterBuilder.ARG_DERIVED_RESOURCE, m_derivedResource);

		if (m_deltaResource == null)
			args.remove(AbstractBuckminsterBuilder.ARG_DELTA_RESOURCE_KEY);
		else
			args.put(AbstractBuckminsterBuilder.ARG_DELTA_RESOURCE_KEY, m_deltaResource);

		AbstractBuckminsterBuilder.setPrintingEnabledForKind(args, IncrementalProjectBuilder.AUTO_BUILD, m_autoPrinting);
		AbstractBuckminsterBuilder.setPrintingEnabledForKind(args, IncrementalProjectBuilder.INCREMENTAL_BUILD, m_incrementalPrinting);
		AbstractBuckminsterBuilder.setPrintingEnabledForKind(args, IncrementalProjectBuilder.FULL_BUILD, m_fullPrinting);
		AbstractBuckminsterBuilder.setPrintingEnabledForKind(args, IncrementalProjectBuilder.CLEAN_BUILD, m_cleanPrinting);
		
		m_builder.setArguments(args);

		super.okPressed();
	}

	@Override
	protected Control createContents(Composite parent)
	{
		this.getShell().setText("Edit Builder");

		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout(1, false));
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.createTitlePart(topComposite);
		this.createCommonPart(topComposite);
		this.createSpecificPart(topComposite);

		Composite buttonComposite = new Composite(topComposite, SWT.NONE);
		buttonComposite.setLayout(new GridLayout(1, false));
		buttonComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.NONE, false, false));
		this.createButtonBar(buttonComposite);

		return topComposite;
	}

	private void createTitlePart(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setLayoutData(new GridData(SWT.LEFT, SWT.NONE, true, false));
		lbl.setText(m_fullName);

		Button enabled = new Button(composite, SWT.CHECK);
		enabled.setText("Enabled");
		enabled.setSelection(m_enabled);
		enabled.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				m_enabled = ((Button) se.getSource()).getSelection();
			}
		});
	}

	private void createCommonPart(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		this.createNameComposite(composite);
		this.createTriggerComposite(composite);
		this.createPrintingComposite(composite);
		this.createRefreshResourceComposite(composite);
		this.createDerivedResourceComposite(composite);
		this.createDeltaResourceComposite(composite);
	}

	private void createNameComposite(Composite parent)
	{
		Group grp = new Group(parent, SWT.NONE);
		grp.setLayout(new GridLayout(1, false));
		grp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		grp.setText("Name:");

		final Text text = new Text(grp, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		if (m_name != null)
			text.setText(m_name);
		text.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				String s = text.getText().trim();
				m_name = s.length() > 0 ? s : null;
			}
		});
		text.setFocus();
	}

	Button m_autoButton;
	Button m_incrementalButton;
	Button m_fullButton;
	Button m_cleanButton;

	private void createTriggerComposite(Composite parent)
	{
		if (m_builder.isConfigurable())
		{
			Group grp = new Group(parent, SWT.NONE);
			grp.setLayout(new GridLayout(2, true));
			grp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
			grp.setText("Triggers:");

			m_autoButton = new Button(grp, SWT.CHECK);
			m_autoButton.setText("Auto");
			m_autoButton.setSelection(m_auto);
			m_autoButton.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					m_auto = ((Button) se.getSource()).getSelection();
				}
			});

			m_incrementalButton = new Button(grp, SWT.CHECK);
			m_incrementalButton.setText("Incremental");
			m_incrementalButton.setSelection(m_incremental);
			m_incrementalButton.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					m_incremental = ((Button) se.getSource()).getSelection();
				}
			});

			m_fullButton = new Button(grp, SWT.CHECK);
			m_fullButton.setText("Full");
			m_fullButton.setSelection(m_full);
			m_fullButton.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					m_full = ((Button) se.getSource()).getSelection();
				}
			});

			m_cleanButton = new Button(grp, SWT.CHECK);
			m_cleanButton.setText("Clean");
			m_cleanButton.setSelection(m_clean);
			m_cleanButton.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					m_clean = ((Button) se.getSource()).getSelection();
				}
			});
		}
	}

	private void createPrintingComposite(Composite parent)
	{
			Group grp = new Group(parent, SWT.NONE);
			grp.setLayout(new GridLayout(2, true));
			grp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
			grp.setText("Enable console support:");

			Button auto = new Button(grp, SWT.CHECK);
			auto.setText("Auto");
			auto.setSelection(m_autoPrinting);
			auto.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					m_autoPrinting = ((Button) se.getSource()).getSelection();
				}
			});

			Button incr = new Button(grp, SWT.CHECK);
			incr.setText("Incremental");
			incr.setSelection(m_incrementalPrinting);
			incr.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					m_incrementalPrinting = ((Button) se.getSource()).getSelection();
				}
			});

			Button full = new Button(grp, SWT.CHECK);
			full.setText("Full");
			full.setSelection(m_fullPrinting);
			full.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					m_fullPrinting = ((Button) se.getSource()).getSelection();
				}
			});

			Button clean = new Button(grp, SWT.CHECK);
			clean.setText("Clean");
			clean.setSelection(m_cleanPrinting);
			clean.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					m_cleanPrinting = ((Button) se.getSource()).getSelection();
				}
			});
	}

	private void createRefreshResourceComposite(Composite parent)
	{
		Group grp = new Group(parent, SWT.NONE);
		grp.setLayout(new GridLayout(1, false));
		grp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		grp.setText("Refresh resource:");

		final Text text = new Text(grp, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		if (m_refreshResource != null)
			text.setText(m_refreshResource);
		text.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				String s = text.getText().trim();
				m_refreshResource = s.length() > 0 ? s : null;
			}
		});
	}

	private void createDerivedResourceComposite(Composite parent)
	{
		Group grp = new Group(parent, SWT.NONE);
		grp.setLayout(new GridLayout(1, false));
		grp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		grp.setText("Derived resource:");

		final Text text = new Text(grp, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		if (m_derivedResource != null)
			text.setText(m_derivedResource);
		text.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				String s = text.getText().trim();
				m_derivedResource = s.length() > 0 ? s : null;
			}
		});
	}

	private void createDeltaResourceComposite(Composite parent)
	{
		Group grp = new Group(parent, SWT.NONE);
		grp.setLayout(new GridLayout(1, false));
		grp.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		grp.setText("Delta resource:");

		final Text text = new Text(grp, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
		if (m_deltaResource != null)
			text.setText(m_deltaResource);
		text.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				String s = text.getText().trim();
				m_deltaResource = s.length() > 0 ? s : null;
			}
		});
	}

	private void createSpecificPart(Composite parent)
	{
		if (m_builderEditor != null)
		{
			Composite composite = new Composite(parent, SWT.NONE);
			composite.setLayout(new GridLayout(1, false));
			composite.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

			Button spec = new Button(composite, SWT.PUSH);
			spec.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));
			spec.setText("Specific settings");
			spec.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					try
					{
						Map<String, String> args = m_builderEditor.edit(EditBuilderDialog.this.getShell(), m_project, m_fullName, getBuilderArguments(m_builder));
						if (args != null)
							m_builder.setArguments(args);
					} catch (CoreException ce)
					{
						ErrorDialog.openError(EditBuilderDialog.this.getShell(), "Failed to call builder editor", m_builder.getBuilderName(), ce.getStatus());
					}
				}
			});
		}
	}

	private void findBuilderEditor()
	{
		IExtensionRegistry er = Platform.getExtensionRegistry();

		String builderId = m_cfgElem.getDeclaringExtension().getUniqueIdentifier();
		for (IConfigurationElement elem : er.getConfigurationElementsFor(UiPlugin.BUILDER_EDITORS_POINT))
			if (elem.getAttribute("builder").equals(builderId))
			{
				try
				{
					m_builderEditor = (IBuilderEditor) elem.createExecutableExtension("class");
					return;
				} catch (CoreException ce)
				{
					ErrorDialog.openError(this.getShell(), "Failed to instantiate builder editor", builderId, ce.getStatus());
				}
			}
	}

	@SuppressWarnings("unchecked")
	private static Map<String, String> getBuilderArguments(ICommand builder)
	{
		return builder.getArguments();
	}
}
