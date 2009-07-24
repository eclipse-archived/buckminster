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
import java.util.regex.Pattern;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.ComponentName;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.materializer.AbstractMaterializer;
import org.eclipse.buckminster.core.materializer.IMaterializer;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.mspec.ConflictResolution;
import org.eclipse.buckminster.core.mspec.IMaterializationNode;
import org.eclipse.buckminster.core.mspec.IMaterializationSpec;
import org.eclipse.buckminster.core.mspec.builder.MaterializationNodeBuilder;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.SaveRunnable;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.internal.DynamicTableLayout;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.TitleAreaDialog;
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
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.IWizardContainer;
import org.eclipse.osgi.util.NLS;
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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.SaveAsDialog;

/**
 * @author Kenneth Olwing
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
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
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			Resolution resolution = (Resolution)element;
			ComponentRequest request = resolution.getRequest();
			String lbl;
			switch(columnIndex)
			{
			case 0:
				lbl = request.getViewName();
				break;
			case 1:
				VersionMatch vm = resolution.getVersionMatch();
				lbl = vm == null
						? "" //$NON-NLS-1$
						: vm.toString();
				break;
			case 2:
				try
				{
					if(!context.getMaterializationSpec().isExcluded(resolution.getComponentIdentifier())
							&& resolution.isMaterializable())
					{
						if(resolution.isMaterialized(context.getArtifactLocation(resolution)))
							lbl = Messages.yes;
						else
							lbl = Messages.no;
					}
					else
						lbl = Messages.not_available_abbreviation;
				}
				catch(Exception e)
				{
					lbl = Messages.error_in_capitals;
					CorePlugin.getLogger().error(e, e.getMessage());
				}
				break;
			default:
				try
				{
					if(!context.getMaterializationSpec().isExcluded(resolution.getComponentIdentifier())
							&& resolution.isMaterializable())
					{
						if(WorkspaceInfo.getResources(resolution.getCSpec().getComponentIdentifier()).length > 0)
							lbl = Messages.yes;
						else
							lbl = Messages.no;
					}
					else
						lbl = Messages.not_available_abbreviation;
				}
				catch(Exception e)
				{
					lbl = Messages.error_in_capitals;
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
				Version vsA = o1.getVersion();
				Version vsB = o2.getVersion();
				if(vsA != null)
					result = (vsB == null)
							? 1
							: vsA.compareTo(vsB);
				else if(vsB != null)
					result = -1;
			}
			return result;
		}
	}

	class ResolutionDetails extends TitleAreaDialog
	{
		private Combo m_conflictResolution;

		private Text m_defaultSuffix;

		private Label m_defaultSuffixLabel;

		private Button m_expandButton;

		private Text m_installLocation;

		private Text m_leafArtifact;

		private Combo m_materializer;

		private Text m_resourcePath;

		private Label m_resourcePathLabel;

		private Button m_unpackButton;

		private Text m_workspaceLocation;

		private Button m_workspaceLocationBrowse;

		private Label m_workspaceLocationLabel;

		private final String m_title;

		private final MaterializationNodeBuilder m_node;

		public ResolutionDetails(Shell parentShell, Resolution resolution) throws CoreException
		{
			super(parentShell);
			m_node = getMaterializationNode(resolution);
			m_title = resolution.getRequest().getViewName();
		}

		@Override
		protected Control createContents(Composite parent)
		{
			Control contents = super.createContents(parent);
			String materializer = m_node.getMaterializerID();
			int matIdx = 0;
			if(materializer != null)
			{
				matIdx = m_materializer.indexOf(materializer);
				if(matIdx < 0)
					matIdx = 0;
			}
			m_materializer.select(matIdx);

			ConflictResolution nodeRes = m_node.getConflictResolution();
			m_conflictResolution.select(nodeRes == null
					? 0
					: nodeRes.ordinal() + 1);
			setTextValue(m_resourcePath, m_node.getResourcePath());
			setTextValue(m_installLocation, m_node.getInstallLocation());
			setTextValue(m_leafArtifact, m_node.getLeafArtifact());
			setTextValue(m_workspaceLocation, m_node.getWorkspaceLocation());

			m_unpackButton.setSelection(m_node.isUnpack());
			m_expandButton.setSelection(m_node.isExpand());
			setTextValue(m_defaultSuffix, m_node.getSuffix());

			setTitle(m_title);
			updateMaterializerView();
			updateUnpackView();

			return contents;
		}

		@Override
		protected Control createDialogArea(Composite parent)
		{
			Control dialogParent = super.createDialogArea(parent);
			Composite myParent = new Composite((Composite)dialogParent, SWT.NONE);
			myParent.setLayout(new GridLayout(5, false));
			myParent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			UiUtils.createGridLabel(myParent, Messages.destination_type_with_colon, 2, 0, SWT.NONE);
			m_materializer = UiUtils.createGridCombo(myParent, 2, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY
					| SWT.SIMPLE);
			UiUtils.createEmptyLabel(myParent).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
			m_materializer.setItems(AbstractMaterializer.getMaterializerIDs(true));
			m_materializer.addSelectionListener(new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					updateMaterializerView();
				}
			});

			m_installLocation = UiUtils.createGridLabeledText(myParent, Messages.parnet_folder_with_colon, 2, 2, false,
					SWT.NONE, null);
			UiUtils.createPushButton(myParent, Messages.browse_with_dots, new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					DirectoryDialog dd = new DirectoryDialog(getShell());
					String newLoc = dd.open();
					if(newLoc != null)
						m_installLocation.setText(newLoc);
				}
			}).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

			m_leafArtifact = UiUtils.createGridLabeledText(myParent, Messages.leaf_artifact_with_colon, 2, 3, false,
					SWT.NONE, null);

			UiUtils.createGridLabel(myParent, Messages.on_non_empty_install_location_with_colon, 2, 0, SWT.NONE);
			m_conflictResolution = UiUtils.createGridCombo(myParent, 2, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY
					| SWT.SIMPLE);
			UiUtils.createEmptyLabel(myParent).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
			m_conflictResolution.add(""); //$NON-NLS-1$
			for(ConflictResolution value : ConflictResolution.values())
				m_conflictResolution.add(value.toString());

			m_unpackButton = UiUtils.createCheckButton(myParent, Messages.unpack, new SelectionAdapter()
			{
				@Override
				public void widgetSelected(SelectionEvent se)
				{
					updateUnpackView();
				}
			});
			m_expandButton = UiUtils.createCheckButton(myParent, Messages.expand, null);
			m_defaultSuffixLabel = UiUtils.createGridLabel(myParent, Messages.default_suffix_with_colon, 1, 0, SWT.NONE);
			m_defaultSuffix = UiUtils.createGridText(myParent, 2, 0, SWT.NONE);

			m_workspaceLocationLabel = UiUtils.createGridLabel(myParent, Messages.workspace_with_colon, 2, 0, SWT.NONE);
			m_workspaceLocation = UiUtils.createGridText(myParent, 2, 0, SWT.NONE);
			m_workspaceLocationBrowse = UiUtils.createPushButton(myParent, Messages.browse_with_dots,
					new SelectionAdapter()
					{
						@Override
						public void widgetSelected(SelectionEvent se)
						{
							DirectoryDialog dd = new DirectoryDialog(getShell());
							String newLoc = dd.open();
							if(newLoc != null)
								m_workspaceLocation.setText(newLoc);
						}
					});
			m_workspaceLocationBrowse.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

			m_resourcePathLabel = UiUtils.createGridLabel(myParent, Messages.project_name_with_colon, 2, 0, SWT.NONE);
			m_resourcePath = UiUtils.createGridText(myParent, 3, 0, SWT.NONE);
			return dialogParent;
		}

		@Override
		protected void okPressed()
		{
			String tmp = UiUtils.trimmedValue(m_installLocation);
			m_node.setInstallLocation(tmp == null
					? null
					: Path.fromOSString(tmp));

			tmp = UiUtils.trimmedValue(m_leafArtifact);
			m_node.setLeafArtifact(tmp == null
					? null
					: Path.fromOSString(tmp));

			int idx = m_materializer.getSelectionIndex();
			m_node.setMaterializerID(idx <= 0
					? null
					: m_materializer.getItem(idx));

			idx = m_conflictResolution.getSelectionIndex();
			m_node.setConflictResolution(idx <= 0
					? null
					: ConflictResolution.values()[idx - 1]);

			if(m_unpackButton.getSelection())
			{
				m_node.setUnpack(true);
				m_node.setExpand(m_expandButton.getSelection());
				m_node.setSuffix(UiUtils.trimmedValue(m_defaultSuffix));
			}
			else
				m_node.setUnpack(false);

			if(isUsingWorkspace())
			{
				tmp = UiUtils.trimmedValue(m_resourcePath);
				m_node.setResourcePath(tmp == null
						? null
						: Path.fromOSString(tmp));

				tmp = UiUtils.trimmedValue(m_workspaceLocation);
				m_node.setWorkspaceLocation(tmp == null
						? null
						: Path.fromOSString(tmp));
			}
			super.okPressed();
		}

		private boolean isUsingWorkspace()
		{
			int idx = m_materializer.getSelectionIndex();
			String id = (idx >= 0)
					? m_materializer.getItem(idx)
					: ""; //$NON-NLS-1$
			boolean useWorkspace;
			if(id.length() == 0)
			{
				int globalIdx = m_globalMaterializer.getSelectionIndex();
				String globalId = (globalIdx >= 0)
						? m_globalMaterializer.getItem(globalIdx)
						: ""; //$NON-NLS-1$
				useWorkspace = IMaterializer.WORKSPACE.equals(globalId);
			}
			else
				useWorkspace = IMaterializer.WORKSPACE.equals(id);
			return useWorkspace;
		}

		private void updateMaterializerView()
		{
			boolean useWorkspace = isUsingWorkspace();
			m_resourcePathLabel.setEnabled(useWorkspace);
			m_resourcePath.setEnabled(useWorkspace);
			m_workspaceLocationLabel.setEnabled(useWorkspace);
			m_workspaceLocation.setEnabled(useWorkspace);
			m_workspaceLocationBrowse.setEnabled(useWorkspace);
		}

		private void updateUnpackView()
		{
			boolean unpack = m_unpackButton.getSelection();
			m_expandButton.setEnabled(unpack);
			m_defaultSuffixLabel.setEnabled(unpack);
			m_defaultSuffix.setEnabled(unpack);
		}
	}

	static class ResolutionViewerSorter extends ViewerSorter
	{
		private final ResolutionComparator m_nodeComparator = new ResolutionComparator();

		@Override
		public int compare(Viewer viewer, Object a, Object b)
		{
			return m_nodeComparator.compare((Resolution)a, (Resolution)b);
		}
	}

	private static void setTextValue(Text text, Object value)
	{
		String txt = ""; //$NON-NLS-1$
		if(value != null)
			txt = value.toString().trim();
		text.setText(txt);
	}

	private Button m_advancedButton;

	private TableViewer m_componentTable;

	private Combo m_globalConflictResolutionCombo;

	private Text m_globalInstallLocation;

	private Combo m_globalMaterializer;

	private Label m_globalWorkspaceLocationLabel;

	private Text m_globalWorkspaceLocation;

	private Button m_globalWorkspaceLocationBrowse;

	private Group m_settingsGroup;

	private Button m_skipButton;

	private Button m_useDefaultsButton;

	public RetrieveAndBindPage()
	{
		super(""); //$NON-NLS-1$
		setDescription(Messages.all_specifications_resolved);
	}

	@Override
	public Composite createControls(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout());
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createComponentTableGroup(topComposite);
		createSettingsGroup(topComposite);
		createButtonGroup(topComposite);
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
			tv.setInput(bom.findAll(null));
			tv.getTable().select(0);
			setGlobalValues();
			setSelectedComponentValues(getSelectedComponent());
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	Resolution getSelectedComponent()
	{
		IStructuredSelection selection = (IStructuredSelection)m_componentTable.getSelection();
		return (selection != null && selection.size() == 1)
				? (Resolution)selection.getFirstElement()
				: null;
	}

	void saveMSPECInFileSystem()
	{
		FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.mspec" }); //$NON-NLS-1$
		String location = dlg.open();
		if(location == null)
			return;
		saveToPath(new Path(location));
	}

	void saveMSPECInWorkspace()
	{
		SaveAsDialog dialog = new SaveAsDialog(getShell());
		if(dialog.open() == Window.CANCEL)
			return;

		IPath filePath = dialog.getResult();
		if(filePath == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFile(filePath);
		saveToPath(file.getLocation());
	}

	private String browseForLocation(Resolution node)
	{
		DirectoryDialog dd = new DirectoryDialog(getShell());
		String dir = dd.open();
		return dir;
	}

	private void createButtonGroup(Composite parent)
	{
		Composite buttons = new Composite(parent, SWT.NONE);
		buttons.setLayout(new GridLayout(3, false));
		buttons.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

		Button saveButton = UiUtils.createPushButton(buttons, Messages.save_mspec, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				saveMSPECInWorkspace();
			}
		});
		saveButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, true, false));

		Button extSaveButton = UiUtils.createPushButton(buttons, Messages.external_save_mspec, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				saveMSPECInFileSystem();
			}
		});
		extSaveButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, false, false));
	}

	private void createComponentTableGroup(Composite parent)
	{
		Group globalSettings = new Group(parent, SWT.NONE);
		globalSettings.setText(Messages.global_settings);
		globalSettings.setLayout(new GridLayout(3, false));
		globalSettings.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		UiUtils.createGridLabel(globalSettings, Messages.destination_type_with_colon, 1, 0, SWT.NONE);
		m_globalMaterializer = UiUtils.createGridCombo(globalSettings, 1, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);
		UiUtils.createEmptyLabel(globalSettings);
		m_globalMaterializer.setItems(AbstractMaterializer.getMaterializerIDs(true));
		m_globalMaterializer.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Combo combo = (Combo)se.getSource();
				int idx = combo.getSelectionIndex();
				String materializerId = (idx >= 0)
						? combo.getItem(idx)
						: null;
				setGlobalMaterializer(materializerId);
			}
		});

		m_globalInstallLocation = UiUtils.createLabeledText(globalSettings, Messages.location_with_colon, false, 0,
				new ModifyListener()
				{
					public void modifyText(ModifyEvent me)
					{
						Text fld = (Text)me.getSource();
						String txt = UiUtils.trimmedValue(fld);
						setGlobalInstallLocation(txt == null
								? null
								: new Path(txt));
					}
				});
		m_globalInstallLocation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		UiUtils.createPushButton(globalSettings, Messages.browse_with_dots, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				String newLoc = browseForLocation(getSelectedComponent());
				if(newLoc != null)
					m_globalInstallLocation.setText(newLoc);
			}
		});

		m_globalWorkspaceLocationLabel = UiUtils.createGridLabel(globalSettings, Messages.workspace_with_dots, 1, 0,
				SWT.NONE);
		m_globalWorkspaceLocation = UiUtils.createGridText(globalSettings, 1, 0, SWT.NONE, new ModifyListener()
		{
			public void modifyText(ModifyEvent me)
			{
				Text fld = (Text)me.getSource();
				String txt = UiUtils.trimmedValue(fld);
				setGlobalWorkspaceLocation(txt == null
						? null
						: new Path(txt));
			}
		});
		m_globalWorkspaceLocationBrowse = UiUtils.createPushButton(globalSettings, Messages.browse_with_dots,
				new SelectionAdapter()
				{
					@Override
					public void widgetSelected(SelectionEvent se)
					{
						String newLoc = browseForLocation(getSelectedComponent());
						if(newLoc != null)
							m_globalWorkspaceLocation.setText(newLoc);
					}
				});

		UiUtils.createGridLabel(globalSettings, Messages.on_non_empty_install_location_with_colon, 1, 0, SWT.NONE);
		m_globalConflictResolutionCombo = UiUtils.createGridCombo(globalSettings, 1, 0, null, null, SWT.DROP_DOWN
				| SWT.READ_ONLY | SWT.SIMPLE);
		UiUtils.createEmptyLabel(globalSettings);
		for(ConflictResolution value : ConflictResolution.values())
			m_globalConflictResolutionCombo.add(value.toString());
		m_globalConflictResolutionCombo.select(0);
		m_globalConflictResolutionCombo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				globalConflictResolutionEvent(((Combo)se.getSource()).getSelectionIndex());
			}
		});

		Group componentTableGroup = new Group(parent, SWT.NONE);
		componentTableGroup.setLayout(new GridLayout());
		componentTableGroup.setText(Messages.selected_components_with_colon);
		componentTableGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(componentTableGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL);

		String[] columnNames = new String[] { Messages.name, Messages.version, Messages.present, Messages.bound };
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
		m_settingsGroup.setLayout(new GridLayout(3, false));
		m_settingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		m_skipButton = UiUtils.createCheckButton(m_settingsGroup, Messages.skip_this_component, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Button b = (Button)se.getSource();
				skipEvent(b.getSelection());
			}
		});
		m_skipButton.setSelection(false);

		m_useDefaultsButton = UiUtils.createCheckButton(m_settingsGroup, Messages.use_defaults, new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent se)
			{
				Button b = (Button)se.getSource();
				useDefaultEvent(b.getSelection());
			}
		});
		m_useDefaultsButton.setSelection(true);

		m_advancedButton = UiUtils.createPushButton(m_settingsGroup, Messages.advanced_with_dots,
				new SelectionAdapter()
				{
					@Override
					public void widgetSelected(SelectionEvent se)
					{
						displayResolutionDetails();
					}
				});
	}

	private void displayResolutionDetails()
	{
		Resolution resolution = getSelectedComponent();
		if(resolution == null)
			return;

		try
		{
			ResolutionDetails resolutionDetails = new ResolutionDetails(getShell(), resolution);
			if(resolutionDetails.open() == Window.OK)
			{
				getQueryWizard().invalidateMaterializationContext();
				updatePageCompletion();
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private TableViewer getComponentTable()
	{
		return m_componentTable;
	}

	private MaterializationNodeBuilder getMaterializationNode(Resolution resolution) throws CoreException
	{
		QueryWizard wizard = getQueryWizard();
		ComponentName cname = resolution.getComponentIdentifier();
		MaterializationSpecBuilder mspec = wizard.getMaterializationSpec();
		MaterializationNodeBuilder node = mspec.getMatchingNodeBuilder(cname);
		if(node == null)
		{
			node = mspec.addNodeBuilder();
			node.setNamePattern(Pattern.compile("^\\Q" + cname.getName() + "\\E$")); //$NON-NLS-1$ //$NON-NLS-2$
			node.setComponentTypeID(cname.getComponentTypeID());
		}
		wizard.invalidateMaterializationContext();
		return node;
	}

	@SuppressWarnings("unchecked")
	private List<Resolution> getTableInput()
	{
		return (List<Resolution>)getComponentTable().getInput();
	}

	private void globalConflictResolutionEvent(int ordinal)
	{
		QueryWizard wizard = getQueryWizard();
		MaterializationContext context = wizard.getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();
		ConflictResolution cr = ConflictResolution.values()[ordinal];
		if(!cr.equals(mspec.getConflictResolution()))
		{
			wizard.getMaterializationSpec().setConflictResolution(cr);
			wizard.invalidateMaterializationContext();
		}
	}

	private void saveToPath(IPath path)
	{
		QueryWizard wizard = getQueryWizard();
		IWizardContainer container = wizard.getContainer();
		try
		{
			MaterializationSpecBuilder mspecBuilder = wizard.getMaterializationSpec();
			IPath parent = path.removeLastSegments(1);
			String name = path.lastSegment();
			if(mspecBuilder.getURL() == null)
			{
				String bomName;
				if(name.endsWith(".mspec")) //$NON-NLS-1$
					bomName = name.substring(0, name.length() - 5) + "bom"; //$NON-NLS-1$
				else
					bomName = name + ".bom"; //$NON-NLS-1$

				IPath bomPath = parent.append(bomName);
				SaveRunnable sr = new SaveRunnable(wizard.getBOM(), bomPath);
				container.run(true, true, sr);
				mspecBuilder.setURL(bomName);
			}

			SaveRunnable sr = new SaveRunnable(mspecBuilder.createMaterializationSpec(), path);
			container.run(true, true, sr);
		}
		catch(InterruptedException e)
		{
		}
		catch(Exception e)
		{
			CoreException t = BuckminsterException.wrap(e);
			String msg = NLS.bind(Messages.unable_to_save_file_0, path);
			CorePlugin.getLogger().error(t, msg);
			ErrorDialog.openError(getShell(), null, msg, t.getStatus());
		}
	}

	private void setGlobalInstallLocation(IPath path)
	{
		try
		{
			QueryWizard wizard = getQueryWizard();
			MaterializationContext context = wizard.getMaterializationContext();
			if(!Trivial.equalsAllowNull(context.getMaterializationSpec().getInstallLocation(), path))
			{
				wizard.getMaterializationSpec().setInstallLocation(path);
				wizard.invalidateMaterializationContext();
				getComponentTable().setInput(wizard.getBOM().findAll(null));
				setSelectedComponentValues(getSelectedComponent());
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setGlobalMaterializer(String materializer)
	{
		try
		{
			QueryWizard wizard = getQueryWizard();
			MaterializationContext context = wizard.getMaterializationContext();
			if(!Trivial.equalsAllowNull(context.getMaterializationSpec().getMaterializerID(), materializer))
			{
				if(materializer != null && materializer.length() == 0)
					materializer = null;
				wizard.getMaterializationSpec().setMaterializerID(materializer);
				wizard.invalidateMaterializationContext();
				getComponentTable().setInput(wizard.getBOM().findAll(null));
				setSelectedComponentValues(getSelectedComponent());
				setGlobalValues();
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setGlobalValues() throws CoreException
	{
		MaterializationContext context = getQueryWizard().getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();

		int matIdx = 0;
		String materializer = mspec.getMaterializerID();
		if(materializer != null)
		{
			matIdx = m_globalMaterializer.indexOf(materializer);
			if(matIdx < 0)
				matIdx = 0;
		}
		m_globalMaterializer.select(matIdx);

		IPath p = mspec.getInstallLocation();
		m_globalInstallLocation.setText(p == null
				? "" //$NON-NLS-1$
				: p.toOSString());

		if(IMaterializer.WORKSPACE.equals(materializer))
		{
			p = mspec.getWorkspaceLocation();
			m_globalWorkspaceLocation.setText(p == null
					? "" //$NON-NLS-1$
					: p.toOSString());

			m_globalWorkspaceLocationLabel.setEnabled(true);
			m_globalWorkspaceLocation.setEnabled(true);
			m_globalWorkspaceLocationBrowse.setEnabled(true);
		}
		else
		{
			m_globalWorkspaceLocationLabel.setEnabled(false);
			m_globalWorkspaceLocation.setEnabled(false);
			m_globalWorkspaceLocationBrowse.setEnabled(false);
		}

		ConflictResolution cr = mspec.getConflictResolution();
		if(cr == null)
			cr = ConflictResolution.getDefault();
		m_globalConflictResolutionCombo.select(cr.ordinal());
	}

	private void setGlobalWorkspaceLocation(IPath path)
	{
		try
		{
			QueryWizard wizard = getQueryWizard();
			MaterializationContext context = wizard.getMaterializationContext();
			if(!Trivial.equalsAllowNull(context.getMaterializationSpec().getWorkspaceLocation(), path))
			{
				wizard.getMaterializationSpec().setWorkspaceLocation(path);
				wizard.invalidateMaterializationContext();
				getComponentTable().setInput(wizard.getBOM().findAll(null));
				setSelectedComponentValues(getSelectedComponent());
			}
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void setSelectedComponentValues(Resolution resolution) throws CoreException
	{
		if(resolution == null)
		{
			UiUtils.setChildrenVisible(m_settingsGroup, false);
			updatePageCompletion();
			return;
		}

		MaterializationContext context = getQueryWizard().getMaterializationContext();
		IMaterializationSpec mspec = context.getMaterializationSpec();
		IMaterializationNode node = mspec.getMatchingNode(resolution.getComponentIdentifier());
		boolean useDefaults = node == null;
		boolean skip = !useDefaults && node.isExclude();
		boolean canMaterialize = resolution.isMaterializable();

		m_settingsGroup.setText(resolution.getRequest().getViewName());

		m_skipButton.setSelection(skip);
		m_useDefaultsButton.setSelection(useDefaults);
		m_useDefaultsButton.setEnabled(!skip && canMaterialize);
		m_advancedButton.setEnabled(!skip && !useDefaults && canMaterialize);
		updatePageCompletion();
	}

	private void skipEvent(boolean skip)
	{
		Resolution resolution = getSelectedComponent();
		if(resolution == null)
			return;

		try
		{
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			IMaterializationNode node = context.getMaterializationSpec().getMatchingNode(
					resolution.getComponentIdentifier());
			if(skip)
			{
				if(node == null || !node.isExclude())
					getMaterializationNode(resolution).setExclude(true);
			}
			else
			{
				if(node != null && node.isExclude())
				{
					useDefaultEvent(true);
					return;
				}
			}

			setSelectedComponentValues(resolution);
		}
		catch(CoreException e)
		{
			displayException(e);
		}
	}

	private void updatePageCompletion() throws CoreException
	{
		setErrorMessage(null);
		setPageComplete(false);

		MaterializationContext context = getQueryWizard().getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();
		List<Resolution> resolutions = getTableInput();
		Collections.sort(resolutions, new ResolutionComparator());

		for(Resolution resolution : resolutions)
		{
			if(!resolution.isMaterializable() || mspec.isExcluded(resolution.getComponentIdentifier()))
				continue;

			String id = resolution.getRequest().getViewName();
			IPath destination = context.getArtifactLocation(resolution);
			File f = destination.toFile();
			if(!f.isAbsolute())
			{
				setErrorMessage(NLS.bind(Messages.the_location_0_for_1_is_not_an_absolute_path, f, id));
				return;
			}

			if(f.exists()
					&& mspec.getConflictResolution(resolution.getComponentIdentifier()) == ConflictResolution.FAIL
					&& !resolution.isMaterialized(destination))
			{
				if(f.isFile())
				{
					setErrorMessage(NLS.bind(Messages.the_location_0_for_1_already_exists_as_a_file, f, id));
					return;
				}
				else if(f.list().length != 0)
				{
					setErrorMessage(NLS.bind(Messages.the_location_0_for_1_exists_but_is_not_empty, f, id));
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
			try
			{
				QueryWizard wizard = getQueryWizard();
				ComponentRequest rq = resolution.getRequest();
				MaterializationSpecBuilder bld = wizard.getMaterializationSpec();
				MaterializationNodeBuilder node = bld.getMatchingNodeBuilder(rq);
				if(flag)
				{
					if(node != null)
					{
						bld.getNodes().remove(node);
						wizard.invalidateMaterializationContext();
					}
				}
				else
				{
					if(node == null)
						getMaterializationNode(resolution);
				}
				setSelectedComponentValues(resolution);
			}
			catch(CoreException e)
			{
				displayException(e);
			}
		}
	}
}
