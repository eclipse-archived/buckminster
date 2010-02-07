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
import org.eclipse.equinox.p2.metadata.Version;
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
public class RetrieveAndBindPage extends AbstractQueryPage {
	class ComponentLabelProvider extends LabelProvider implements ITableLabelProvider {
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public String getColumnText(Object element, int columnIndex) {
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			Resolution resolution = (Resolution) element;
			ComponentRequest request = resolution.getRequest();
			String lbl;
			switch (columnIndex) {
				case 0:
					lbl = request.getViewName();
					break;
				case 1:
					VersionMatch vm = resolution.getVersionMatch();
					lbl = vm == null ? "" //$NON-NLS-1$
							: vm.toString();
					break;
				case 2:
					try {
						if (!context.getMaterializationSpec().isExcluded(resolution) && resolution.isMaterializable()) {
							if (resolution.isMaterialized(context.getArtifactLocation(resolution)))
								lbl = Messages.yes;
							else
								lbl = Messages.no;
						} else
							lbl = Messages.not_available_abbreviation;
					} catch (Exception e) {
						lbl = Messages.error_in_capitals;
						CorePlugin.getLogger().error(e, e.getMessage());
					}
					break;
				default:
					try {
						if (!context.getMaterializationSpec().isExcluded(resolution) && resolution.isMaterializable()) {
							if (WorkspaceInfo.getResources(resolution.getCSpec().getComponentIdentifier()).length > 0)
								lbl = Messages.yes;
							else
								lbl = Messages.no;
						} else
							lbl = Messages.not_available_abbreviation;
					} catch (Exception e) {
						lbl = Messages.error_in_capitals;
						CorePlugin.getLogger().error(e.getMessage(), e);
					}
			}
			return lbl;
		}
	}

	static class ResolutionComparator implements Comparator<Resolution> {
		public int compare(Resolution o1, Resolution o2) {
			int result = o1.getRequest().getViewName().compareTo(o2.getRequest().getViewName());
			if (result == 0) {
				Version vsA = o1.getVersion();
				Version vsB = o2.getVersion();
				if (vsA != null)
					result = (vsB == null) ? 1 : vsA.compareTo(vsB);
				else if (vsB != null)
					result = -1;
			}
			return result;
		}
	}

	class ResolutionDetails extends TitleAreaDialog {
		private Combo conflictResolution;

		private Text defaultSuffix;

		private Label defaultSuffixLabel;

		private Button expandButton;

		private Text installLocation;

		private Text leafArtifact;

		private Combo materializer;

		private Text resourcePath;

		private Label resourcePathLabel;

		private Button unpackButton;

		private Text workspaceLocation;

		private Button workspaceLocationBrowse;

		private Label workspaceLocationLabel;

		private final String title;

		private final MaterializationNodeBuilder node;

		public ResolutionDetails(Shell parentShell, Resolution resolution) throws CoreException {
			super(parentShell);
			node = getMaterializationNode(resolution);
			title = resolution.getRequest().getViewName();
		}

		@Override
		protected Control createContents(Composite parent) {
			Control contents = super.createContents(parent);
			String materializerId = node.getMaterializerID();
			int matIdx = 0;
			if (materializerId != null) {
				matIdx = materializer.indexOf(materializerId);
				if (matIdx < 0)
					matIdx = 0;
			}
			materializer.select(matIdx);

			ConflictResolution nodeRes = node.getConflictResolution();
			conflictResolution.select(nodeRes == null ? 0 : nodeRes.ordinal() + 1);
			setTextValue(resourcePath, node.getResourcePath());
			setTextValue(installLocation, node.getInstallLocation());
			setTextValue(leafArtifact, node.getLeafArtifact());
			setTextValue(workspaceLocation, node.getWorkspaceLocation());

			unpackButton.setSelection(node.isUnpack());
			expandButton.setSelection(node.isExpand());
			setTextValue(defaultSuffix, node.getSuffix());

			setTitle(title);
			updateMaterializerView();
			updateUnpackView();

			return contents;
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Control dialogParent = super.createDialogArea(parent);
			Composite myParent = new Composite((Composite) dialogParent, SWT.NONE);
			myParent.setLayout(new GridLayout(5, false));
			myParent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

			UiUtils.createGridLabel(myParent, Messages.destination_type_with_colon, 2, 0, SWT.NONE);
			materializer = UiUtils.createGridCombo(myParent, 2, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
			UiUtils.createEmptyLabel(myParent).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
			materializer.setItems(AbstractMaterializer.getMaterializerIDs(true));
			materializer.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent se) {
					updateMaterializerView();
				}
			});

			installLocation = UiUtils.createGridLabeledText(myParent, Messages.parnet_folder_with_colon, 2, 2, false, SWT.NONE, null);
			UiUtils.createPushButton(myParent, Messages.browse_with_dots, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent se) {
					DirectoryDialog dd = new DirectoryDialog(getShell());
					String newLoc = dd.open();
					if (newLoc != null)
						installLocation.setText(newLoc);
				}
			}).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

			leafArtifact = UiUtils.createGridLabeledText(myParent, Messages.leaf_artifact_with_colon, 2, 3, false, SWT.NONE, null);

			UiUtils.createGridLabel(myParent, Messages.on_non_empty_install_location_with_colon, 2, 0, SWT.NONE);
			conflictResolution = UiUtils.createGridCombo(myParent, 2, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
			UiUtils.createEmptyLabel(myParent).setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1));
			conflictResolution.add(""); //$NON-NLS-1$
			for (ConflictResolution value : ConflictResolution.values())
				conflictResolution.add(value.toString());

			unpackButton = UiUtils.createCheckButton(myParent, Messages.unpack, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent se) {
					updateUnpackView();
				}
			});
			expandButton = UiUtils.createCheckButton(myParent, Messages.expand, null);
			defaultSuffixLabel = UiUtils.createGridLabel(myParent, Messages.default_suffix_with_colon, 1, 0, SWT.NONE);
			defaultSuffix = UiUtils.createGridText(myParent, 2, 0, SWT.NONE);

			workspaceLocationLabel = UiUtils.createGridLabel(myParent, Messages.workspace_with_colon, 2, 0, SWT.NONE);
			workspaceLocation = UiUtils.createGridText(myParent, 2, 0, SWT.NONE);
			workspaceLocationBrowse = UiUtils.createPushButton(myParent, Messages.browse_with_dots, new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent se) {
					DirectoryDialog dd = new DirectoryDialog(getShell());
					String newLoc = dd.open();
					if (newLoc != null)
						workspaceLocation.setText(newLoc);
				}
			});
			workspaceLocationBrowse.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));

			resourcePathLabel = UiUtils.createGridLabel(myParent, Messages.project_name_with_colon, 2, 0, SWT.NONE);
			resourcePath = UiUtils.createGridText(myParent, 3, 0, SWT.NONE);
			return dialogParent;
		}

		@Override
		protected void okPressed() {
			String tmp = UiUtils.trimmedValue(installLocation);
			node.setInstallLocation(tmp == null ? null : Path.fromOSString(tmp));

			tmp = UiUtils.trimmedValue(leafArtifact);
			node.setLeafArtifact(tmp == null ? null : Path.fromOSString(tmp));

			int idx = materializer.getSelectionIndex();
			node.setMaterializerID(idx <= 0 ? null : materializer.getItem(idx));

			idx = conflictResolution.getSelectionIndex();
			node.setConflictResolution(idx <= 0 ? null : ConflictResolution.values()[idx - 1]);

			if (unpackButton.getSelection()) {
				node.setUnpack(true);
				node.setExpand(expandButton.getSelection());
				node.setSuffix(UiUtils.trimmedValue(defaultSuffix));
			} else
				node.setUnpack(false);

			if (isUsingWorkspace()) {
				tmp = UiUtils.trimmedValue(resourcePath);
				node.setResourcePath(tmp == null ? null : Path.fromOSString(tmp));

				tmp = UiUtils.trimmedValue(workspaceLocation);
				node.setWorkspaceLocation(tmp == null ? null : Path.fromOSString(tmp));
			}
			super.okPressed();
		}

		private boolean isUsingWorkspace() {
			int idx = materializer.getSelectionIndex();
			String id = (idx >= 0) ? materializer.getItem(idx) : ""; //$NON-NLS-1$
			boolean useWorkspace;
			if (id.length() == 0) {
				int globalIdx = globalMaterializer.getSelectionIndex();
				String globalId = (globalIdx >= 0) ? globalMaterializer.getItem(globalIdx) : ""; //$NON-NLS-1$
				useWorkspace = IMaterializer.WORKSPACE.equals(globalId);
			} else
				useWorkspace = IMaterializer.WORKSPACE.equals(id);
			return useWorkspace;
		}

		private void updateMaterializerView() {
			boolean useWorkspace = isUsingWorkspace();
			resourcePathLabel.setEnabled(useWorkspace);
			resourcePath.setEnabled(useWorkspace);
			workspaceLocationLabel.setEnabled(useWorkspace);
			workspaceLocation.setEnabled(useWorkspace);
			workspaceLocationBrowse.setEnabled(useWorkspace);
		}

		private void updateUnpackView() {
			boolean unpack = unpackButton.getSelection();
			expandButton.setEnabled(unpack);
			defaultSuffixLabel.setEnabled(unpack);
			defaultSuffix.setEnabled(unpack);
		}
	}

	static class ResolutionViewerSorter extends ViewerSorter {
		private final ResolutionComparator nodeComparator = new ResolutionComparator();

		@Override
		public int compare(Viewer viewer, Object a, Object b) {
			return nodeComparator.compare((Resolution) a, (Resolution) b);
		}
	}

	private static void setTextValue(Text text, Object value) {
		String txt = ""; //$NON-NLS-1$
		if (value != null)
			txt = value.toString().trim();
		text.setText(txt);
	}

	private Button advancedButton;

	private TableViewer componentTable;

	private Combo globalConflictResolutionCombo;

	private Text globalInstallLocation;

	private Combo globalMaterializer;

	private Label globalWorkspaceLocationLabel;

	private Text globalWorkspaceLocation;

	private Button globalWorkspaceLocationBrowse;

	private Group settingsGroup;

	private Button skipButton;

	private Button useDefaultsButton;

	public RetrieveAndBindPage() {
		super(""); //$NON-NLS-1$
		setDescription(Messages.all_specifications_resolved);
	}

	@Override
	public Composite createControls(Composite parent) {
		Composite topComposite = new Composite(parent, SWT.NONE);
		topComposite.setLayout(new GridLayout());
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createComponentTableGroup(topComposite);
		createSettingsGroup(topComposite);
		createButtonGroup(topComposite);
		return topComposite;
	}

	@Override
	protected void pageIsShowing() {
		super.pageIsShowing();
		QueryWizard wizard = getQueryWizard();
		try {
			TableViewer tv = getComponentTable();
			BillOfMaterials bom = wizard.getBOM();
			tv.setInput(bom.findAll(null));
			tv.getTable().select(0);
			setGlobalValues();
			setSelectedComponentValues(getSelectedComponent());
		} catch (CoreException e) {
			displayException(e);
		}
	}

	Resolution getSelectedComponent() {
		IStructuredSelection selection = (IStructuredSelection) componentTable.getSelection();
		return (selection != null && selection.size() == 1) ? (Resolution) selection.getFirstElement() : null;
	}

	void saveMSPECInFileSystem() {
		FileDialog dlg = new FileDialog(getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.mspec" }); //$NON-NLS-1$
		String location = dlg.open();
		if (location == null)
			return;
		saveToPath(new Path(location));
	}

	void saveMSPECInWorkspace() {
		SaveAsDialog dialog = new SaveAsDialog(getShell());
		if (dialog.open() == Window.CANCEL)
			return;

		IPath filePath = dialog.getResult();
		if (filePath == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFile(filePath);
		saveToPath(file.getLocation());
	}

	private String browseForLocation(Resolution node) {
		DirectoryDialog dd = new DirectoryDialog(getShell());
		String dir = dd.open();
		return dir;
	}

	private void createButtonGroup(Composite parent) {
		Composite buttons = new Composite(parent, SWT.NONE);
		buttons.setLayout(new GridLayout(3, false));
		buttons.setLayoutData(new GridData(SWT.FILL, SWT.BOTTOM, true, false));

		Button saveButton = UiUtils.createPushButton(buttons, Messages.save_mspec, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveMSPECInWorkspace();
			}
		});
		saveButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, true, false));

		Button extSaveButton = UiUtils.createPushButton(buttons, Messages.external_save_mspec, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				saveMSPECInFileSystem();
			}
		});
		extSaveButton.setLayoutData(new GridData(SWT.TRAIL, SWT.TOP, false, false));
	}

	private void createComponentTableGroup(Composite parent) {
		Group globalSettings = new Group(parent, SWT.NONE);
		globalSettings.setText(Messages.global_settings);
		globalSettings.setLayout(new GridLayout(3, false));
		globalSettings.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		UiUtils.createGridLabel(globalSettings, Messages.destination_type_with_colon, 1, 0, SWT.NONE);
		globalMaterializer = UiUtils.createGridCombo(globalSettings, 1, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
		UiUtils.createEmptyLabel(globalSettings);
		globalMaterializer.setItems(AbstractMaterializer.getMaterializerIDs(true));
		globalMaterializer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				Combo combo = (Combo) se.getSource();
				int idx = combo.getSelectionIndex();
				String materializerId = (idx >= 0) ? combo.getItem(idx) : null;
				setGlobalMaterializer(materializerId);
			}
		});

		globalInstallLocation = UiUtils.createLabeledText(globalSettings, Messages.location_with_colon, false, 0, new ModifyListener() {
			public void modifyText(ModifyEvent me) {
				Text fld = (Text) me.getSource();
				String txt = UiUtils.trimmedValue(fld);
				setGlobalInstallLocation(txt == null ? null : new Path(txt));
			}
		});
		globalInstallLocation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		UiUtils.createPushButton(globalSettings, Messages.browse_with_dots, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				String newLoc = browseForLocation(getSelectedComponent());
				if (newLoc != null)
					globalInstallLocation.setText(newLoc);
			}
		});

		globalWorkspaceLocationLabel = UiUtils.createGridLabel(globalSettings, Messages.workspace_with_dots, 1, 0, SWT.NONE);
		globalWorkspaceLocation = UiUtils.createGridText(globalSettings, 1, 0, SWT.NONE, new ModifyListener() {
			public void modifyText(ModifyEvent me) {
				Text fld = (Text) me.getSource();
				String txt = UiUtils.trimmedValue(fld);
				setGlobalWorkspaceLocation(txt == null ? null : new Path(txt));
			}
		});
		globalWorkspaceLocationBrowse = UiUtils.createPushButton(globalSettings, Messages.browse_with_dots, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				String newLoc = browseForLocation(getSelectedComponent());
				if (newLoc != null)
					globalWorkspaceLocation.setText(newLoc);
			}
		});

		UiUtils.createGridLabel(globalSettings, Messages.on_non_empty_install_location_with_colon, 1, 0, SWT.NONE);
		globalConflictResolutionCombo = UiUtils.createGridCombo(globalSettings, 1, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
		UiUtils.createEmptyLabel(globalSettings);
		for (ConflictResolution value : ConflictResolution.values())
			globalConflictResolutionCombo.add(value.toString());
		globalConflictResolutionCombo.select(0);
		globalConflictResolutionCombo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				globalConflictResolutionEvent(((Combo) se.getSource()).getSelectionIndex());
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
		for (int idx = 0; idx < columnNames.length; idx++) {
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
			tableColumn.setText(columnNames[idx]);
			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
		}
		table.setLayout(layout);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		componentTable = new TableViewer(table);
		componentTable.setLabelProvider(new ComponentLabelProvider());
		componentTable.setContentProvider(new ArrayContentProvider());
		componentTable.setSorter(new ResolutionViewerSorter());
		componentTable.addSelectionChangedListener(new ISelectionChangedListener() {
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection selection = (IStructuredSelection) event.getSelection();
				if (selection.size() == 1)
					try {
						setSelectedComponentValues((Resolution) selection.getFirstElement());
					} catch (CoreException e) {
						displayException(e);
					}
			}
		});
	}

	private void createSettingsGroup(Composite parent) {
		settingsGroup = new Group(parent, SWT.NONE);
		settingsGroup.setLayout(new GridLayout(3, false));
		settingsGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		skipButton = UiUtils.createCheckButton(settingsGroup, Messages.skip_this_component, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				Button b = (Button) se.getSource();
				skipEvent(b.getSelection());
			}
		});
		skipButton.setSelection(false);

		useDefaultsButton = UiUtils.createCheckButton(settingsGroup, Messages.use_defaults, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				Button b = (Button) se.getSource();
				useDefaultEvent(b.getSelection());
			}
		});
		useDefaultsButton.setSelection(true);

		advancedButton = UiUtils.createPushButton(settingsGroup, Messages.advanced_with_dots, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				displayResolutionDetails();
			}
		});
	}

	private void displayResolutionDetails() {
		Resolution resolution = getSelectedComponent();
		if (resolution == null)
			return;

		try {
			ResolutionDetails resolutionDetails = new ResolutionDetails(getShell(), resolution);
			if (resolutionDetails.open() == Window.OK) {
				getQueryWizard().invalidateMaterializationContext();
				updatePageCompletion();
			}
		} catch (CoreException e) {
			displayException(e);
		}
	}

	private TableViewer getComponentTable() {
		return componentTable;
	}

	private MaterializationNodeBuilder getMaterializationNode(Resolution resolution) throws CoreException {
		QueryWizard wizard = getQueryWizard();
		ComponentName cname = resolution.getComponentIdentifier();
		MaterializationSpecBuilder mspec = wizard.getMaterializationSpec();
		MaterializationNodeBuilder node = mspec.getMatchingNodeBuilder(cname);
		if (node == null) {
			node = mspec.addNodeBuilder();
			node.setNamePattern(Pattern.compile("^\\Q" + cname.getName() + "\\E$")); //$NON-NLS-1$ //$NON-NLS-2$
			node.setComponentTypeID(cname.getComponentTypeID());
		}
		wizard.invalidateMaterializationContext();
		return node;
	}

	@SuppressWarnings("unchecked")
	private List<Resolution> getTableInput() {
		return (List<Resolution>) getComponentTable().getInput();
	}

	private void globalConflictResolutionEvent(int ordinal) {
		QueryWizard wizard = getQueryWizard();
		MaterializationContext context = wizard.getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();
		ConflictResolution cr = ConflictResolution.values()[ordinal];
		if (!cr.equals(mspec.getConflictResolution())) {
			wizard.getMaterializationSpec().setConflictResolution(cr);
			wizard.invalidateMaterializationContext();
		}
	}

	private void saveToPath(IPath path) {
		QueryWizard wizard = getQueryWizard();
		IWizardContainer container = wizard.getContainer();
		try {
			MaterializationSpecBuilder mspecBuilder = wizard.getMaterializationSpec();
			IPath parent = path.removeLastSegments(1);
			String name = path.lastSegment();
			if (mspecBuilder.getURL() == null) {
				String bomName;
				if (name.endsWith(".mspec")) //$NON-NLS-1$
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
		} catch (InterruptedException e) {
		} catch (Exception e) {
			CoreException t = BuckminsterException.wrap(e);
			String msg = NLS.bind(Messages.unable_to_save_file_0, path);
			CorePlugin.getLogger().error(t, msg);
			ErrorDialog.openError(getShell(), null, msg, t.getStatus());
		}
	}

	private void setGlobalInstallLocation(IPath path) {
		try {
			QueryWizard wizard = getQueryWizard();
			MaterializationContext context = wizard.getMaterializationContext();
			if (!Trivial.equalsAllowNull(context.getMaterializationSpec().getInstallLocation(), path)) {
				wizard.getMaterializationSpec().setInstallLocation(path);
				wizard.invalidateMaterializationContext();
				getComponentTable().setInput(wizard.getBOM().findAll(null));
				setSelectedComponentValues(getSelectedComponent());
			}
		} catch (CoreException e) {
			displayException(e);
		}
	}

	private void setGlobalMaterializer(String materializer) {
		try {
			QueryWizard wizard = getQueryWizard();
			MaterializationContext context = wizard.getMaterializationContext();
			if (!Trivial.equalsAllowNull(context.getMaterializationSpec().getMaterializerID(), materializer)) {
				if (materializer != null && materializer.length() == 0)
					materializer = null;
				wizard.getMaterializationSpec().setMaterializerID(materializer);
				wizard.invalidateMaterializationContext();
				getComponentTable().setInput(wizard.getBOM().findAll(null));
				setSelectedComponentValues(getSelectedComponent());
				setGlobalValues();
			}
		} catch (CoreException e) {
			displayException(e);
		}
	}

	private void setGlobalValues() throws CoreException {
		MaterializationContext context = getQueryWizard().getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();

		int matIdx = 0;
		String materializer = mspec.getMaterializerID();
		if (materializer != null) {
			matIdx = globalMaterializer.indexOf(materializer);
			if (matIdx < 0)
				matIdx = 0;
		}
		globalMaterializer.select(matIdx);

		IPath p = mspec.getInstallLocation();
		globalInstallLocation.setText(p == null ? "" //$NON-NLS-1$
				: p.toOSString());

		if (IMaterializer.WORKSPACE.equals(materializer)) {
			p = mspec.getWorkspaceLocation();
			globalWorkspaceLocation.setText(p == null ? "" //$NON-NLS-1$
					: p.toOSString());

			globalWorkspaceLocationLabel.setEnabled(true);
			globalWorkspaceLocation.setEnabled(true);
			globalWorkspaceLocationBrowse.setEnabled(true);
		} else {
			globalWorkspaceLocationLabel.setEnabled(false);
			globalWorkspaceLocation.setEnabled(false);
			globalWorkspaceLocationBrowse.setEnabled(false);
		}

		ConflictResolution cr = mspec.getConflictResolution();
		if (cr == null)
			cr = ConflictResolution.getDefault();
		globalConflictResolutionCombo.select(cr.ordinal());
	}

	private void setGlobalWorkspaceLocation(IPath path) {
		try {
			QueryWizard wizard = getQueryWizard();
			MaterializationContext context = wizard.getMaterializationContext();
			if (!Trivial.equalsAllowNull(context.getMaterializationSpec().getWorkspaceLocation(), path)) {
				wizard.getMaterializationSpec().setWorkspaceLocation(path);
				wizard.invalidateMaterializationContext();
				getComponentTable().setInput(wizard.getBOM().findAll(null));
				setSelectedComponentValues(getSelectedComponent());
			}
		} catch (CoreException e) {
			displayException(e);
		}
	}

	private void setSelectedComponentValues(Resolution resolution) throws CoreException {
		if (resolution == null) {
			UiUtils.setChildrenVisible(settingsGroup, false);
			updatePageCompletion();
			return;
		}

		MaterializationContext context = getQueryWizard().getMaterializationContext();
		IMaterializationSpec mspec = context.getMaterializationSpec();
		IMaterializationNode node = mspec.getMatchingNode(resolution);
		boolean useDefaults = node == null;
		boolean skip = !useDefaults && node.isExclude();
		boolean canMaterialize = resolution.isMaterializable();

		settingsGroup.setText(resolution.getRequest().getViewName());

		skipButton.setSelection(skip);
		useDefaultsButton.setSelection(useDefaults);
		useDefaultsButton.setEnabled(!skip && canMaterialize);
		advancedButton.setEnabled(!skip && !useDefaults && canMaterialize);
		updatePageCompletion();
	}

	private void skipEvent(boolean skip) {
		Resolution resolution = getSelectedComponent();
		if (resolution == null)
			return;

		try {
			MaterializationContext context = getQueryWizard().getMaterializationContext();
			IMaterializationNode node = context.getMaterializationSpec().getMatchingNode(resolution);
			if (skip) {
				if (node == null || !node.isExclude())
					getMaterializationNode(resolution).setExclude(true);
			} else {
				if (node != null && node.isExclude()) {
					useDefaultEvent(true);
					return;
				}
			}

			setSelectedComponentValues(resolution);
		} catch (CoreException e) {
			displayException(e);
		}
	}

	private void updatePageCompletion() throws CoreException {
		setErrorMessage(null);
		setPageComplete(false);

		MaterializationContext context = getQueryWizard().getMaterializationContext();
		MaterializationSpec mspec = context.getMaterializationSpec();
		List<Resolution> resolutions = getTableInput();
		Collections.sort(resolutions, new ResolutionComparator());

		for (Resolution resolution : resolutions) {
			if (!resolution.isMaterializable() || mspec.isExcluded(resolution))
				continue;

			String id = resolution.getRequest().getViewName();
			IPath destination = context.getArtifactLocation(resolution);
			File f = destination.toFile();
			if (!f.isAbsolute()) {
				setErrorMessage(NLS.bind(Messages.the_location_0_for_1_is_not_an_absolute_path, f, id));
				return;
			}

			if (f.exists() && mspec.getConflictResolution(resolution) == ConflictResolution.FAIL && !resolution.isMaterialized(destination)) {
				if (f.isFile()) {
					setErrorMessage(NLS.bind(Messages.the_location_0_for_1_already_exists_as_a_file, f, id));
					return;
				} else if (f.list().length != 0) {
					setErrorMessage(NLS.bind(Messages.the_location_0_for_1_exists_but_is_not_empty, f, id));
					return;
				}
			}
		}
		setPageComplete(true);
	}

	private void useDefaultEvent(boolean flag) {
		Resolution resolution = getSelectedComponent();
		if (resolution != null) {
			try {
				QueryWizard wizard = getQueryWizard();
				ComponentRequest rq = resolution.getRequest();
				MaterializationSpecBuilder bld = wizard.getMaterializationSpec();
				MaterializationNodeBuilder node = bld.getMatchingNodeBuilder(rq);
				if (flag) {
					if (node != null) {
						bld.getNodes().remove(node);
						wizard.invalidateMaterializationContext();
					}
				} else {
					if (node == null)
						getMaterializationNode(resolution);
				}
				setSelectedComponentValues(resolution);
			} catch (CoreException e) {
				displayException(e);
			}
		}
	}
}
