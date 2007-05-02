/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.ui.wizards;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.model.ConflictResolution;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.IVersionSelector;
import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.LabeledCombo;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

/**
 * @author Kenneth Olwing
 * @author Thomas Hallgren
 */
public class RetrieveAndBindPage extends AbstractQueryPage
{
	class ComponentLabelProvider extends LabelProvider implements ITableLabelProvider
	{
		public Image getColumnImage(Object element, int columnIndex)
		{
			return null;
		}

		public String getColumnText(Object element, int columnIndex)
		{
			RMContext context = getContext();
			Resolution resolution = (Resolution)element;
			ComponentRequest request = resolution.getRequest();
			String lbl;
			switch(columnIndex)
			{
			case 0:
				lbl = request.getViewName();
				break;
			case 1:
				IVersionSelector vs = resolution.getVersionMatch().getFixedVersionSelector();
				lbl = vs == null ? "" : vs.toString();
				break;
			case 2:
				try
				{
					if(resolution.isMaterializable())
					{
						if(resolution.isMaterialized(context.getDestination(resolution)))
							lbl = "Yes";
						else
							lbl = "No";
					}
					else
						lbl = "N/A";
				}
				catch(CoreException e)
				{
					lbl = "ERROR";
					CorePlugin.getLogger().error(e.getMessage(), e);
				}
				break;
			default:
				try
				{
					if(resolution.isMaterializable())
					{
						if(WorkspaceInfo.getResource(resolution.getCSpec().getComponentIdentifier()) != null)
							lbl = "Yes";
						else
							lbl = "No";
					}
					else
						lbl = "N/A";
				}
				catch(CoreException e)
				{
					lbl = "ERROR";
					CorePlugin.getLogger().error(e.getMessage(), e);
				}
			}
		return lbl;
		}
	}

	static class ResolutionComparator implements Comparator<Resolution>
	{
		public int compare(Resolution o1, Resolution o2)
		{
			int result = o1.getRequest().getViewName().compareTo(o2.getRequest().getViewName());
			if(result == 0)
			{
				IVersion vsA = o1.getVersion();
				IVersion vsB = o2.getVersion();
				if(vsA != null)
					result = (vsB == null) ? 1 : vsA.compareTo(vsB);
				else if(vsB != null)
					result = -1;
			}
			return result;
		}
	}

	static class ResolutionViewerSorter extends ViewerSorter
	{
		private final ResolutionComparator m_nodeComparator = new ResolutionComparator();

		@Override
		public int compare(Viewer viewer, Object a, Object b)
		{
			return m_nodeComparator.compare(
				(Resolution)a,
				(Resolution)b);
		}
	}

	private TableViewer m_componentTable;

	private LabeledCombo m_whenNotEmptyCombo;

	private Button m_skipButton;

	private Group m_settingsGroup;

	private Composite m_settingsLocationComposite;

	private Text m_settingsLocationText;
	private Composite m_settingsProjectComposite;

	private Text m_settingsProjectNameText;

	private Button m_useDefaultsButton;

	public RetrieveAndBindPage()
	{
		super("");
		setDescription(UiPlugin.getResourceString("RetrieveAndBindPage.description")); //$NON-NLS-1$
	}

	@Override
	public Composite createControls(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout());
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createComponentTableGroup(topComposite);
		createSettingsGroup(topComposite);

		return topComposite;
	}

	@Override
	protected void pageIsShowing()
	{
		super.pageIsShowing();
		QueryWizard wizard = getQueryWizard();
		try
		{
			TableViewer tv = getComponentTable();
			BillOfMaterials bom = wizard.getBOM();
			tv.setInput(bom.findAll(wizard.getNodesToSkip()));
			tv.getTable().select(0);

			Resolution node = getSelectedComponent();
			if(node != null)
				setSelectedComponentValues(node);
			updatePageCompletion();
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	Resolution getSelectedComponent()
	{
		IStructuredSelection selection = (IStructuredSelection)m_componentTable.getSelection();
		return (selection != null && selection.size() == 1) ? (Resolution)selection.getFirstElement() : null;
	}

	private String browseForLocation(Resolution node)
	{
		// FIXME - this should use a dialog supplied by the repo provider
		DirectoryDialog dd = new DirectoryDialog(getShell());
		String dir = dd.open();

		return dir;
	}

	private void createComponentTableGroup(Composite parent)
	{
		Group componentTableGroup = new Group(parent, SWT.NONE);
		componentTableGroup.setLayout(new GridLayout());
		componentTableGroup.setText("Selected components:");
		componentTableGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(componentTableGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

		String[] columnNames = new String[] { "Name", "Version", "Present", "Bound" };
		int[] columnWeights = new int[] { 20, 10, 5, 5 };

		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(150);
		for(int idx = 0; idx < columnNames.length; idx++)
		{
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
			tableColumn.setText(columnNames[idx]);
			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
		}
		table.setLayout(layout);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		m_componentTable = new TableViewer(table);
		m_componentTable.setLabelProvider(new ComponentLabelProvider());
		m_componentTable.setContentProvider(new ArrayContentProvider());
		m_componentTable.setSorter(new ResolutionViewerSorter());
		m_componentTable.addSelectionChangedListener(new ISelectionChangedListener()
		{
			public void selectionChanged(SelectionChangedEvent event)
			{
				IStructuredSelection selection = (IStructuredSelection)event.getSelection();
				if(selection.size() == 1)
					try
					{
						setSelectedComponentValues((Resolution)selection.getFirstElement());
					}
					catch(CoreException e)
					{
						displayException(e);
					}
			}
		});
	}

	private void createSettingsGroup(Composite parent)
	{
		m_settingsGroup = new Group(parent, SWT.NONE);
		m_settingsGroup.setLayout(new GridLayout());
		m_settingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		m_settingsGroup.setText("");

		m_skipButton = new Button(m_settingsGroup, SWT.CHECK);
		m_skipButton.setText("Skip this component");
		m_skipButton.setSelection(false);
		m_skipButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Button b = (Button)se.getSource();
				skipEvent(b.getSelection());
			}
		});

		m_useDefaultsButton = new Button(m_settingsGroup, SWT.CHECK);
		m_useDefaultsButton.setText("Use defaults");
		m_useDefaultsButton.setSelection(true);
		m_useDefaultsButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Button b = (Button)se.getSource();
				useDefaultEvent(b.getSelection());
			}
		});

		createSettingsLocationComposite(m_settingsGroup);

		m_whenNotEmptyCombo = UiUtils.createEnumCombo(m_settingsGroup, "When destination is not empty", ConflictResolution.values(), new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				whenNotEmptyEvent(((Combo)se.getSource()).getSelectionIndex());
			}
		});
		createSettingsProjectComposite(m_settingsGroup);
	}

	private void createSettingsLocationComposite(Composite parent)
	{
		m_settingsLocationComposite = new Composite(parent, SWT.NONE);
		m_settingsLocationComposite.setLayout(new GridLayout(3, false));
		m_settingsLocationComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Label locationLabel = new Label(m_settingsLocationComposite, SWT.NONE);
		locationLabel.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));
		locationLabel.setText("Location:");

		m_settingsLocationText = new Text(m_settingsLocationComposite, SWT.BORDER);
		m_settingsLocationText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		m_settingsLocationText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				Text fld = (Text)me.getSource();
				String txt = fld.getText().trim();
				setLocation(getSelectedComponent(), txt.length() == 0 ? null : new Path(txt));
			}
		});

		Button browseButton = new Button(m_settingsLocationComposite, SWT.PUSH);
		browseButton.setText("Browse...");
		browseButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				String newLoc = browseForLocation(getSelectedComponent());
				if (newLoc != null)
					m_settingsLocationText.setText(newLoc);
			}
		});

		UiUtils.setEnabledRecursively(m_settingsLocationComposite, false);
	}

	private void createSettingsProjectComposite(Composite parent)
	{
		m_settingsProjectComposite = new Composite(parent, SWT.NONE);
		m_settingsProjectComposite.setLayout(new GridLayout(3, false));
		m_settingsProjectComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Label projectLabel = new Label(m_settingsProjectComposite, SWT.NONE);
		projectLabel.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false));
		projectLabel.setText("ProjectBinding name:");

		m_settingsProjectNameText = new Text(m_settingsProjectComposite, SWT.BORDER);
		m_settingsProjectNameText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		m_settingsProjectNameText.addModifyListener(new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				Text fld = (Text)me.getSource();
				setProjectName(getSelectedComponent(), fld.getText().trim());
			}
		});
		UiUtils.setEnabledRecursively(m_settingsProjectComposite, false);
	}

	private TableViewer getComponentTable()
	{
		return m_componentTable;
	}

	@SuppressWarnings("unchecked")
	private List<Resolution> getTableInput()
	{
		return (List<Resolution>)getComponentTable().getInput();
	}

	private void setLocation(Resolution selected, IPath dir)
	{
		try
		{
			getContext().setDestination(selected, dir);
			updatePageCompletion();
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setProjectName(Resolution resolution, String name)
	{
		try
		{
			getContext().setProjectName(resolution, name);
			updatePageCompletion();
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setSelectedComponentValues(Resolution resolution) throws CoreException
	{
		RMContext context = getContext();
		boolean useDefaults = context.isUsingMaterializationDefaults(resolution);
		boolean skip = getQueryWizard().getNodesToSkip().contains(resolution);
		IPath destination = context.getDestination(resolution);

		m_settingsGroup.setText(resolution.getRequest().getViewName());
		m_settingsProjectNameText.setText(context.getProjectName(resolution));
		m_settingsLocationText.setText(destination.toOSString());
		m_skipButton.setSelection(skip);
		m_useDefaultsButton.setSelection(useDefaults);
		m_whenNotEmptyCombo.select(context.getNotEmptyAction(resolution).ordinal());
		UiUtils.setEnabledRecursively(m_settingsLocationComposite, context.isDestinationChangeable(resolution) && !useDefaults);
		UiUtils.setEnabledRecursively(m_settingsProjectComposite, !useDefaults);
		m_settingsProjectNameText.setEnabled(!useDefaults);

		// Show/Hide all except the button.
		//
		UiUtils.setChildrenVisible(m_settingsGroup, !skip);
		if(skip)
			m_skipButton.setVisible(true);
		m_whenNotEmptyCombo.setVisible(!skip && destination != null && destination.toFile().exists());
		m_whenNotEmptyCombo.setEnabled(!useDefaults);
	}

	private void skipEvent(boolean skip)
	{
		Resolution resolution = getSelectedComponent();
		if(resolution != null)
		{
			Set<Resolution> notThese = getQueryWizard().getNodesToSkip();
			if(skip)
				notThese.add(resolution);
			else
				notThese.remove(resolution);
			try
			{
				setSelectedComponentValues(resolution);
				updatePageCompletion();
			}
			catch(CoreException e)
			{
				displayException(e);
			}
		}
	}

	private void updatePageCompletion() throws CoreException
	{
		setErrorMessage(null);
		setPageComplete(false);

		RMContext context = getContext();
		List<Resolution> resolutions = getTableInput();
		Collections.sort(resolutions, new ResolutionComparator());
		Set<Resolution> notThese = getQueryWizard().getNodesToSkip();

		for (Resolution resolution : resolutions)
		{
			if(notThese.contains(resolution) || !resolution.isMaterializable())
				continue;

			String id = resolution.getRequest().getViewName();
			IPath destination = context.getDestination(resolution);
			File f = destination.toFile();
			if(!f.isAbsolute())
			{
				setErrorMessage("The location " + f + " for " + id + " is not an absolute path");
				return;
			}

			if (f.exists() && context.getNotEmptyAction(resolution) == ConflictResolution.FAIL && !resolution.isMaterialized(destination))
			{
				if (f.isFile())
				{
					setErrorMessage("The location " + f + " for " + id + " already exists as a file");
					return;
				}
				else if (f.list().length != 0)
				{
					setErrorMessage("The location " + f + " for " + id + " exists but is not empty");
					return;
				}
			}
		}
		setPageComplete(true);
	}

	private void useDefaultEvent(boolean flag)
	{
		Resolution resolution = getSelectedComponent();
		if(resolution != null)
		{
			getContext().setUseMaterializationDefaults(resolution, flag);
			try
			{
				setSelectedComponentValues(resolution);
				updatePageCompletion();
			}
			catch(CoreException e)
			{
				displayException(e);
			}
		}
	}

	private void whenNotEmptyEvent(int ordinal)
	{
		Resolution resolution = getSelectedComponent();
		if(resolution != null && ordinal >= 0)
			try
			{
				getContext().setNotEmptyAction(resolution, ConflictResolution.values()[ordinal]);
			}
			catch(CoreException e)
			{
				displayException(e);
			}
	}
}

