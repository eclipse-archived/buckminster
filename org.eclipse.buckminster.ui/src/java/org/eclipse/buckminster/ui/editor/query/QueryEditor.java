/*******************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.editor.query;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.IComponentRequest;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.query.builder.AdvisorNodeBuilder;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.core.query.model.MutableLevel;
import org.eclipse.buckminster.core.query.model.SourceLevel;
import org.eclipse.buckminster.core.version.VersionSelector;
import org.eclipse.buckminster.osgi.filter.Filter;
import org.eclipse.buckminster.osgi.filter.FilterFactory;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.runtime.URLUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.SaveRunnable;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.actions.BlankQueryAction;
import org.eclipse.buckminster.ui.editor.ArtifactType;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.editor.IDerivedEditorInput;
import org.eclipse.buckminster.ui.editor.Properties;
import org.eclipse.buckminster.ui.editor.PropertiesModifyEvent;
import org.eclipse.buckminster.ui.editor.PropertiesModifyListener;
import org.eclipse.buckminster.ui.editor.VersionDesignator;
import org.eclipse.buckminster.ui.editor.VersionDesignatorEvent;
import org.eclipse.buckminster.ui.editor.VersionDesignatorListener;
import org.eclipse.buckminster.ui.general.editor.structured.IActivator;
import org.eclipse.buckminster.ui.internal.DynamicTableLayout;
import org.eclipse.buckminster.ui.internal.ResolveJob;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorMatchingStrategy;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.part.EditorPart;
import org.osgi.framework.InvalidSyntaxException;

/**
 * @author Karel Brezina
 * 
 */
public class QueryEditor extends EditorPart implements IEditorMatchingStrategy {
	class AdvisorNodeLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			AdvisorNodeBuilder node = (AdvisorNodeBuilder) element;
			String lbl;
			switch (columnIndex) {
				case 0:
					lbl = node.getNamePattern() == null ? "" : node.getNamePattern().toString(); //$NON-NLS-1$
					break;
				case 1:
					lbl = node.getComponentTypeID();
					break;
				case 2:
					lbl = node.getFilter() == null ? "" : node.getFilter().toString(); //$NON-NLS-1$
					break;
				default:
					lbl = null;
			}
			return lbl;
		}
	}

	class CheckboxSelectionListener extends SelectionAdapter {
		private Control[] controlsToEnable;

		public CheckboxSelectionListener(Control[] controlsToEnable) {
			this.controlsToEnable = controlsToEnable;
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			Button button = (Button) e.widget;
			boolean enable = button.getSelection();

			for (Control control : controlsToEnable) {
				control.setEnabled(enable);
			}
		}
	}

	class CompoundModifyListener implements VersionDesignatorListener, ModifyListener, PropertiesModifyListener, SelectionListener {

		@Override
		public void modifyProperties(PropertiesModifyEvent e) {
			if (!suppressModifyListener)
				setDirty(true);
		}

		@Override
		public void modifyText(ModifyEvent e) {
			if (!suppressModifyListener)
				setDirty(true);
		}

		@Override
		public void modifyVersionDesignator(VersionDesignatorEvent e) {
			if (!suppressModifyListener)
				setDirty(true);
		}

		@Override
		public void widgetDefaultSelected(SelectionEvent e) {
		}

		@Override
		public void widgetSelected(SelectionEvent e) {
			if (!suppressModifyListener)
				setDirty(true);
		}
	}

	private static final IActivator EMPTY_ACTIVATOR = new IActivator() {

		@Override
		public void activate() {
			// nothing to activate
		}
	};

	private final static int DONT_SAVE = -99;

	private CTabFolder tabFolder;

	private Text componentName;

	private Combo componentType;

	private VersionDesignator versionDesignator;

	private ComponentQueryBuilder componentQuery;

	private Button enableOverride;

	private boolean hasChanges;

	private Button resolveButton;

	private Button materializeButton;

	private Button externalSaveAsButton;

	private Button moveDownButton;

	private Button moveUpButton;

	private boolean mute;

	private Combo mutableLevel;

	private Text namePattern;

	private Text filter;

	private Combo category;

	private Text overlayFolder;

	private Button overlayBrowseButton;

	private Text wantedAttributes;

	private Button prune;

	private boolean needsRefresh;

	private Button newButton;

	private TableViewer nodeTable;

	private Button removeButton;

	private Button requestURLCheckbox;

	private Text requestURL;

	private Button propertyURLCheckbox;

	private Text propertyURL;

	private Tree nodeTree;

	private Combo sourceLevel;

	private Button skipComponent;

	private Button allowCircular;

	private Composite nodesStackComposite;

	private StackLayout nodesStackLayout;

	private HashMap<String, Control> nodesHash;

	private Button useTargetPlatform;

	private Button useWorkspace;

	private Button useMaterialization;

	private Button useResolutionService;

	private Text branchTagPath;

	private Text timestamp;

	private Text revision;

	private VersionDesignator versionOverride;

	private boolean continueOnError;

	private Properties nodeProperties;

	private Text nodeDocumentation;

	private Properties properties;

	private Text shortDesc;

	private Text documentation;

	private CTabItem xmlTab;

	private Text xml;

	private CompoundModifyListener compoundModifyListener;

	private final SimpleDateFormat timestampFormat = (SimpleDateFormat) DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

	private int lastSelectedNode = -1;

	private int lastEditedNode = -1;

	private boolean suppressModifyListener = false;

	public String commitChanges(IComponentRequest[] requestRet) {
		String name = UiUtils.trimmedValue(componentName);
		if (name == null)
			return Messages.the_component_must_have_a_name;

		String componentCategory = null;
		int idx = componentType.getSelectionIndex();
		if (idx >= 0) {
			componentCategory = componentType.getItem(idx);
			if (componentCategory.length() == 0)
				componentCategory = null;
		}
		requestRet[0] = new ComponentRequest(name, componentCategory, versionDesignator.getVersionDesignator());
		return null;
	}

	@Override
	public void createPartControl(Composite parent) {
		Composite topComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, true);
		layout.marginHeight = layout.marginWidth = 0;
		topComposite.setLayout(layout);

		tabFolder = new CTabFolder(topComposite, SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		final CTabItem mainTab = new CTabItem(tabFolder, SWT.NONE);
		mainTab.setText(Messages.main);
		mainTab.setControl(getMainTabControl(tabFolder));

		final CTabItem advisorTab = new CTabItem(tabFolder, SWT.NONE);
		advisorTab.setText(Messages.advisor_nodes);
		advisorTab.setControl(getAdvisorTabControl(tabFolder));

		CTabItem propertiesTab = new CTabItem(tabFolder, SWT.NONE);
		propertiesTab.setText(Messages.properties);
		propertiesTab.setControl(getPropertiesTabControl(tabFolder));

		CTabItem documentationTab = new CTabItem(tabFolder, SWT.NONE);
		documentationTab.setText(Messages.documentation);
		documentationTab.setControl(getDocumentationTabControl(tabFolder));

		xmlTab = new CTabItem(tabFolder, SWT.NONE);
		xmlTab.setText(Messages.xml_content);
		xmlTab.setControl(getXMLTabControl(tabFolder));

		tabFolder.addPaintListener(new PaintListener() {
			@Override
			public void paintControl(PaintEvent e) {
				if (tabFolder.getSelection() == null)
					tabFolder.setSelection(0);
			}
		});

		tabFolder.addSelectionListener(new SelectionAdapter() {
			private final IActivator NODE_TAB_ACTIVATOR = new IActivator() {
				@Override
				public void activate() {
					tabFolder.setSelection(advisorTab);
				}
			};

			private CTabItem lastTab = mainTab;

			@Override
			public void widgetSelected(SelectionEvent e) {
				// save row
				if (lastTab != e.item) {
					if (lastTab == advisorTab)
						if (!saveLastNode(NODE_TAB_ACTIVATOR))
							return;
				}

				if (xmlTab == e.item) {
					if (!commitChangesToQuery())
						MessageDialog.openWarning(getSite().getShell(), null, Messages.xml_content_was_not_updated_due_to_errors);
					else
						xml.setText(getCQueryXML());
				}
				lastTab = (CTabItem) e.item;
			}
		});

		createActionButtons(topComposite);
	}

	public void doExternalSaveAs() {
		if (!commitChangesToQuery())
			return;
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.cquery" }); //$NON-NLS-1$
		final String location = dlg.open();
		if (location == null)
			return;
		saveToPath(new Path(location));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (!commitChangesToQuery())
			return;

		IEditorInput input = getEditorInput();
		if (input == null)
			return;

		IPath path = (input instanceof ILocationProvider) ? ((ILocationProvider) input).getPath(input) : ((IPathEditorInput) input).getPath();

		saveToPath(path);
	}

	@Override
	public void doSaveAs() {
		if (!commitChangesToQuery())
			return;

		IEditorInput input = getEditorInput();
		if (input == null)
			return;

		SaveAsDialog dialog = new SaveAsDialog(getSite().getShell());
		IFile original = (input instanceof IFileEditorInput) ? ((IFileEditorInput) input).getFile() : null;
		if (original != null)
			dialog.setOriginalFile(original);

		if (dialog.open() == Window.CANCEL)
			return;

		IPath filePath = dialog.getResult();
		if (filePath == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFile(filePath);
		saveToPath(file.getLocation());
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		if (!(input instanceof ILocationProvider || input instanceof IPathEditorInput || input instanceof IURIEditorInput))
			throw new PartInitException(Messages.invalid_input);

		setSite(site);

		if (input instanceof IURIEditorInput) {
			try {
				input = EditorUtils.getExternalFileEditorInput((IURIEditorInput) input, ArtifactType.CQUERY);
			} catch (Exception e) {
				throw new PartInitException(Messages.unable_to_open_editor, e);
			}
		}

		InputStream stream = null;
		try {
			IPath path = (input instanceof ILocationProvider) ? ((ILocationProvider) input).getPath(input) : ((IPathEditorInput) input).getPath();

			File file = path.toFile();
			componentQuery = new ComponentQueryBuilder();
			if (file.length() == 0) {
				String defaultName = file.getName();
				if (defaultName.startsWith(BlankQueryAction.TEMP_FILE_PREFIX))
					defaultName = ""; //$NON-NLS-1$
				else {
					int lastDot = defaultName.lastIndexOf('.');
					if (lastDot > 0)
						defaultName = defaultName.substring(0, lastDot);
				}
				componentQuery.setRootRequest(new ComponentRequest(defaultName, null, null));
			} else {
				stream = new FileInputStream(file);
				URL contextURL;
				try {
					// The context URL is normally passed on as the tooltip text
					//
					contextURL = URLUtils.normalizeToURL(input.getToolTipText());
				} catch (MalformedURLException e) {
					contextURL = file.toURI().toURL();
				}
				componentQuery.initFrom(ComponentQuery.fromStream(contextURL, null, stream, true));
				CorePlugin.getLogger().debug("CQUERY Context URL set to %s", componentQuery.getContextURL()); //$NON-NLS-1$
			}
			needsRefresh = true;
			if (componentName != null) {
				refreshQuery();
			}
			setInputWithNotify(input);
			setPartName(input.getName());
		} catch (Throwable e) {
			CoreException ce = BuckminsterException.wrap(e);
			throw new PartInitException(Messages.unable_to_open_editor, ce);
		} finally {
			IOUtils.close(stream);
		}

		compoundModifyListener = new CompoundModifyListener();
	}

	@Override
	public boolean isDirty() {
		return hasChanges;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return true;
	}

	@Override
	public boolean matches(IEditorReference editorRef, IEditorInput input) {
		IEditorPart part = (IEditorPart) editorRef.getPart(false);
		if (part != null) {
			IEditorInput editorInput = part.getEditorInput();
			if (editorInput != null) {
				if (editorInput.equals(input))
					return true;

				if (editorInput instanceof IDerivedEditorInput) {
					IEditorInput originalEditorInput = ((IDerivedEditorInput) editorInput).getOriginalInput();
					if (originalEditorInput.equals(input))
						return true;
				}
			}
		}
		return false;
	}

	@Override
	public void setFocus() {
		tabFolder.setFocus();

		if (needsRefresh)
			refreshQuery();
	}

	private AdvisorNodeBuilder addEmptyNode() {
		AdvisorNodeBuilder node = new AdvisorNodeBuilder();
		node.setNamePattern(Pattern.compile("")); //$NON-NLS-1$
		componentQuery.addAdvisorNode(node);

		return node;
	}

	private void changeNodeSelection() {
		if (!saveLastNode()) {
			if (lastSelectedNode != -1)
				nodeTable.getTable().setSelection(lastSelectedNode);

			return;
		}

		nodeSelectionEvent();
	}

	private boolean commitChangesToQuery() {
		if (nodeTable.getControl().isVisible())
			if (!saveLastNode())
				return false;

		componentQuery.setResourceMapURL(UiUtils.trimmedValue(requestURL));
		componentQuery.setPropertiesURL(UiUtils.trimmedValue(propertyURL));
		properties.fillProperties(componentQuery.getDeclaredProperties());

		String doc = UiUtils.trimmedValue(shortDesc);
		componentQuery.setShortDesc(doc);

		doc = UiUtils.trimmedValue(documentation);
		try {
			componentQuery.setDocumentation(doc == null ? null : Documentation.parse(doc));
		} catch (CoreException e) {
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		ComponentRequest[] requestRet = new ComponentRequest[1];
		String error = commitChanges(requestRet);
		if (error == null)
			componentQuery.setRootRequest(requestRet[0]);
		else {
			MessageDialog.openError(getSite().getShell(), null, error);
			return false;
		}
		return true;
	}

	private void createActionButtons(Composite parent) {
		Composite allButtonsBox = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		// layout.marginHeight = layout.marginWidth = 0;
		allButtonsBox.setLayout(layout);
		allButtonsBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		UiUtils.createCheckButton(allButtonsBox, Messages.continue_on_error, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				continueOnError = ((Button) e.getSource()).getSelection();
			}
		});

		Composite pressButtonsBox = new Composite(allButtonsBox, SWT.NONE);
		layout = new GridLayout(3, true);
		layout.marginHeight = layout.marginWidth = 0;
		pressButtonsBox.setLayout(layout);
		pressButtonsBox.setLayoutData(new GridData(SWT.END, SWT.FILL, true, false));

		resolveButton = UiUtils.createPushButton(pressButtonsBox, Messages.resolve_to_wizard, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadComponent(false);
			}
		});
		resolveButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		materializeButton = UiUtils.createPushButton(pressButtonsBox, Messages.resolve_and_materialize, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadComponent(true);
			}
		});
		materializeButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));

		externalSaveAsButton = UiUtils.createPushButton(pressButtonsBox, Messages.external_save_as, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doExternalSaveAs();
			}
		});
		externalSaveAsButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
	}

	private void createButtonBox(Composite parent) {
		Composite buttonBox = new Composite(parent, SWT.NULL);
		buttonBox.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		FillLayout layout = new FillLayout(SWT.VERTICAL);
		layout.marginWidth = layout.marginHeight = 0;
		layout.spacing = 3;
		buttonBox.setLayout(layout);

		Composite buttonBox1 = new Composite(buttonBox, SWT.NULL);
		// buttonBox1.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
		// false));
		layout = new FillLayout(SWT.HORIZONTAL);
		layout.marginWidth = layout.marginHeight = 0;
		buttonBox1.setLayout(layout);

		Composite buttonBox2 = new Composite(buttonBox, SWT.NULL);
		// buttonBox2.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false,
		// false));
		layout = new FillLayout(SWT.HORIZONTAL);
		layout.marginWidth = layout.marginHeight = 0;
		buttonBox2.setLayout(layout);

		newButton = UiUtils.createPushButton(buttonBox1, Messages.new_label, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				newNode();
			}
		});

		removeButton = UiUtils.createPushButton(buttonBox1, Messages.remove, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				removeNode();
			}
		});

		moveUpButton = UiUtils.createPushButton(buttonBox2, Messages.move_up, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				swapAndReselect(0, -1);
			}
		});

		moveDownButton = UiUtils.createPushButton(buttonBox2, Messages.move_down, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				swapAndReselect(1, 0);
			}
		});
	}

	private void createNodeFields(Composite parent) {
		createNodeTree(parent);

		createNodeStack(parent);
	}

	private void createNodeStack(Composite parent) {
		nodesStackComposite = new Composite(parent, SWT.NONE);
		nodesStackComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		nodesStackLayout = new StackLayout();
		nodesStackLayout.marginHeight = nodesStackLayout.marginWidth = 0;
		nodesStackComposite.setLayout(nodesStackLayout);

		nodesHash = new HashMap<String, Control>();

		Composite geComposite = new Composite(nodesStackComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		geComposite.setLayout(layout);

		nodesHash.put(Messages.general, geComposite);

		EditorUtils.createHeaderLabel(geComposite, Messages.general, 2);

		UiUtils.createGridLabel(geComposite, Messages.name_pattern_with_colon, 1, 0, SWT.NONE);

		namePattern = UiUtils.createGridText(geComposite, 1, 0, SWT.NONE);
		namePattern.addModifyListener(compoundModifyListener);

		UiUtils.createGridLabel(geComposite, Messages.matched_component_type_with_colon, 1, 0, SWT.NONE);

		category = UiUtils.createGridCombo(geComposite, 1, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);
		category.setItems(AbstractComponentType.getComponentTypeIDs(true));
		category.addModifyListener(compoundModifyListener);

		UiUtils.createGridLabel(geComposite, Messages.filter_with_colon, 1, 0, SWT.NONE);

		filter = UiUtils.createGridText(geComposite, 1, 0, SWT.NONE);
		filter.addModifyListener(compoundModifyListener);

		UiUtils.createGridLabel(geComposite, Messages.skip_component_with_colon, 1, 0, SWT.NONE);
		skipComponent = UiUtils.createCheckButton(geComposite, null, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				enableDisableSkipSensitive(false);
			}
		});
		skipComponent.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		skipComponent.addSelectionListener(compoundModifyListener);

		UiUtils.createGridLabel(geComposite, Messages.allow_circular_dependency_with_colon, 1, 0, SWT.NONE);
		allowCircular = UiUtils.createCheckButton(geComposite, null, null);
		allowCircular.addSelectionListener(compoundModifyListener);

		Composite aqComposite = new Composite(nodesStackComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		aqComposite.setLayout(layout);

		nodesHash.put(Messages.attribute_qualification, aqComposite);

		EditorUtils.createHeaderLabel(aqComposite, Messages.attribute_qualification, 2);

		UiUtils.createGridLabel(aqComposite, Messages.attributes_with_colon, 1, 0, SWT.NONE);
		wantedAttributes = UiUtils.createGridText(aqComposite, 0, 0, SWT.NONE);
		wantedAttributes.addModifyListener(compoundModifyListener);
		UiUtils.createGridLabel(aqComposite, Messages.prune_according_to_attributes_with_colon, 1, 0, SWT.NONE);
		prune = UiUtils.createCheckButton(aqComposite, null, null);
		prune.addSelectionListener(compoundModifyListener);

		Composite srComposite = new Composite(nodesStackComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		srComposite.setLayout(layout);

		nodesHash.put(Messages.special_requirements, srComposite);

		EditorUtils.createHeaderLabel(srComposite, Messages.special_requirements, 2);

		UiUtils.createGridLabel(srComposite, Messages.mutable_level_with_colon, 1, 0, SWT.NONE);
		mutableLevel = UiUtils.createGridEnumCombo(srComposite, 0, 0, MutableLevel.values(), null, null, SWT.NONE);
		mutableLevel.addModifyListener(compoundModifyListener);
		UiUtils.createGridLabel(srComposite, Messages.source_level_with_colon, 1, 0, SWT.NONE);
		sourceLevel = UiUtils.createGridEnumCombo(srComposite, 0, 0, SourceLevel.values(), null, null, SWT.NONE);
		sourceLevel.addModifyListener(compoundModifyListener);

		Composite kuComposite = new Composite(nodesStackComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		kuComposite.setLayout(layout);

		nodesHash.put(Messages.resolution_scope, kuComposite);

		EditorUtils.createHeaderLabel(kuComposite, Messages.resolution_scope, 2);

		UiUtils.createGridLabel(kuComposite, Messages.target_platform_with_colon, 1, 0, SWT.NONE);
		useTargetPlatform = UiUtils.createCheckButton(kuComposite, null, null);
		useTargetPlatform.addSelectionListener(compoundModifyListener);
		UiUtils.createGridLabel(kuComposite, Messages.workspace_with_colon, 1, 0, SWT.NONE);
		useWorkspace = UiUtils.createCheckButton(kuComposite, null, null);
		useWorkspace.addSelectionListener(compoundModifyListener);
		UiUtils.createGridLabel(kuComposite, Messages.materialization_with_colon, 1, 0, SWT.NONE);
		useMaterialization = UiUtils.createCheckButton(kuComposite, null, null);
		useMaterialization.addSelectionListener(compoundModifyListener);
		UiUtils.createGridLabel(kuComposite, Messages.resolution_service_with_colon, 1, 0, SWT.NONE);
		useResolutionService = UiUtils.createCheckButton(kuComposite, null, null);
		useResolutionService.addSelectionListener(compoundModifyListener);

		Composite scComposite = new Composite(nodesStackComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		scComposite.setLayout(layout);

		nodesHash.put(Messages.selection_criteria, scComposite);
		EditorUtils.createHeaderLabel(scComposite, Messages.selection_criteria, 2);

		UiUtils.createGridLabel(scComposite, Messages.branch_or_tag_path_with_colon, 1, 0, SWT.NONE);
		branchTagPath = UiUtils.createGridText(scComposite, 1, 0, SWT.NONE);
		branchTagPath.addModifyListener(compoundModifyListener);

		UiUtils.createGridLabel(scComposite, Messages.timestamp_with_colon, 1, 0, SWT.NONE);
		timestamp = UiUtils.createGridText(scComposite, 1, 0, SWT.NONE);
		timestamp.addModifyListener(compoundModifyListener);
		UiUtils.createGridLabel(scComposite, Messages.revision_with_colon, 1, 0, SWT.NONE);
		revision = UiUtils.createGridText(scComposite, 1, 0, SWT.NONE);
		revision.addModifyListener(compoundModifyListener);

		Composite ovComposite = new Composite(nodesStackComposite, SWT.NONE);
		layout = new GridLayout(3, false);
		layout.marginHeight = layout.marginWidth = 0;
		ovComposite.setLayout(layout);

		nodesHash.put(Messages.override, ovComposite);

		EditorUtils.createHeaderLabel(ovComposite, Messages.override, 3);

		UiUtils.createGridLabel(ovComposite, Messages.override_version, 1, 0, SWT.NONE);
		enableOverride = UiUtils.createCheckButton(ovComposite, null, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean selected = ((Button) e.getSource()).getSelection();
				versionOverride.setEnabled(selected);
			}
		});
		enableOverride.addSelectionListener(compoundModifyListener);
		UiUtils.createEmptyLabel(ovComposite);

		versionOverride = new VersionDesignator(ovComposite);
		versionOverride.addVersionDesignatorListener(compoundModifyListener);

		Composite ofComposite = new Composite(nodesStackComposite, SWT.NONE);
		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		ofComposite.setLayout(layout);

		nodesHash.put(Messages.overlay_folder, ofComposite);

		EditorUtils.createHeaderLabel(ofComposite, Messages.overlay_folder_for_prototyping, 2);

		UiUtils.createGridLabel(ofComposite, Messages.folder_with_colon, 1, 0, SWT.NONE);
		overlayFolder = UiUtils.createGridText(ofComposite, 1, 0, SWT.NONE);
		overlayFolder.addModifyListener(compoundModifyListener);
		Label label = UiUtils.createEmptyLabel(ofComposite);
		label.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		overlayBrowseButton = new Button(ofComposite, SWT.PUSH);
		overlayBrowseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
		overlayBrowseButton.setText(Messages.browse_with_dots);
		overlayBrowseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				DirectoryDialog dlg = new DirectoryDialog(getSite().getShell());
				overlayFolder.setText(TextUtils.notNullString(dlg.open()));
			}
		});

		Composite prComposite = new Composite(nodesStackComposite, SWT.NONE);
		layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		prComposite.setLayout(layout);

		nodesHash.put(Messages.properties, prComposite);

		EditorUtils.createHeaderLabel(prComposite, Messages.properties, 1);

		nodeProperties = new Properties(prComposite, SWT.NONE);
		nodeProperties.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		nodeProperties.addPropertiesModifyListener(compoundModifyListener);

		Composite docComposite = new Composite(nodesStackComposite, SWT.NONE);
		layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		docComposite.setLayout(layout);

		nodesHash.put(Messages.documentation, docComposite);

		EditorUtils.createHeaderLabel(docComposite, Messages.documentation, 1);

		nodeDocumentation = UiUtils.createGridText(docComposite, 1, 0, SWT.MULTI);
		nodeDocumentation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		nodeDocumentation.addModifyListener(compoundModifyListener);

		initStackControl();
		/*
		 * // set the same height for nodeTable and node Tree int height =
		 * nodeTree.computeSize(SWT.DEFAULT, SWT.DEFAULT).y + 35;
		 * 
		 * Table table = (Table) nodeTable.getControl(); GridData gridData =
		 * (GridData) table.getLayoutData(); gridData.heightHint = height;
		 * table.setLayoutData(gridData);
		 * 
		 * gridData = (GridData) nodeTree.getLayoutData(); gridData.heightHint =
		 * height; nodeTree.setLayoutData(gridData);
		 * 
		 * gridData = (GridData) nodesStackComposite.getLayoutData();
		 * gridData.heightHint = height + 21;
		 * nodesStackComposite.setLayoutData(gridData);
		 */
	}

	private void createNodeTableGroup(Composite parent) {
		Composite componentTableGroup = new Composite(parent, SWT.NONE);
		GridLayout gl = new GridLayout(1, true);
		gl.marginHeight = gl.marginWidth = 0;
		componentTableGroup.setLayout(gl);
		componentTableGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(componentTableGroup, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);

		table.setHeaderVisible(false);

		String[] columnNames = new String[] { Messages.name_pattern, Messages.component_type, Messages.filter };
		int[] columnWeights = new int[] { 10, 5, 5 };

		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);
		for (int idx = 0; idx < columnNames.length; idx++) {
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, idx);
			tableColumn.setText(columnNames[idx]);
			layout.addColumnData(new ColumnWeightData(columnWeights[idx], true));
		}
		table.setLayout(layout);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		nodeTable = new TableViewer(table);
		nodeTable.setLabelProvider(new AdvisorNodeLabelProvider());
		nodeTable.setContentProvider(new ArrayContentProvider());
		nodeTable.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				changeNodeSelection();
			}
		});

		createButtonBox(componentTableGroup);
	}

	private void createNodeTree(Composite parent) {
		nodeTree = new Tree(parent, SWT.BORDER);
		nodeTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true));
		nodeTree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (e.item != null) {
					TreeItem item = (TreeItem) e.item;
					nodesStackLayout.topControl = nodesHash.get(item.getText());
					nodesStackComposite.layout();
				}
			}
		});

		TreeItem item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.general);

		item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.attribute_qualification);

		item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.special_requirements);

		item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.resolution_scope);

		item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.selection_criteria);

		item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.override);

		item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.overlay_folder);

		item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.properties);

		item = new TreeItem(nodeTree, SWT.NONE);
		item.setText(Messages.documentation);
	}

	private void enableDisableButtonGroup() {
		Table table = nodeTable.getTable();
		int top = table.getItemCount();
		int idx = table.getSelectionIndex();
		newButton.setText(Messages.new_label);
		removeButton.setEnabled(idx >= 0);
		moveUpButton.setEnabled(idx > 0);
		moveDownButton.setEnabled(idx >= 0 && idx < top - 1);

		boolean disableFields = getSelectionIndex() == -1;
		namePattern.setEnabled(!disableFields);
		filter.setEnabled(!disableFields);
		category.setEnabled(!disableFields);
		skipComponent.setEnabled(!disableFields);
		nodeDocumentation.setEnabled(!disableFields);
		enableDisableSkipSensitive(disableFields);
	}

	private void enableDisableSkipSensitive(boolean forceDisable) {
		boolean enableRest = !forceDisable && !skipComponent.getSelection();

		allowCircular.setEnabled(enableRest);
		overlayFolder.setEnabled(enableRest);
		overlayBrowseButton.setEnabled(enableRest);
		wantedAttributes.setEnabled(enableRest);
		prune.setEnabled(enableRest);

		mutableLevel.setEnabled(enableRest);
		sourceLevel.setEnabled(enableRest);

		useTargetPlatform.setEnabled(enableRest);
		useWorkspace.setEnabled(enableRest);
		useMaterialization.setEnabled(enableRest);
		useResolutionService.setEnabled(enableRest);

		branchTagPath.setEnabled(enableRest);
		timestamp.setEnabled(enableRest);
		revision.setEnabled(enableRest);

		enableOverride.setEnabled(enableRest);
		versionOverride.setEnabled(enableRest && enableOverride.getSelection());

		nodeProperties.setEnabled(enableRest);
	}

	private Control getAdvisorTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.advisor_nodes);

		Composite advisorComposite = new Composite(tabComposite, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = layout.marginWidth = 0;
		advisorComposite.setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		advisorComposite.setLayoutData(gridData);

		createNodeTableGroup(advisorComposite);

		createNodeFields(advisorComposite);

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private String getCQueryXML() {
		String cqueryXML = ""; //$NON-NLS-1$
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Utils.serialize(componentQuery.createComponentQuery(), baos);
			cqueryXML = baos.toString();
		} catch (Exception e) {
			// nothing
		}
		return cqueryXML;
	}

	private Control getDocumentationTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.documentation);

		Composite descComposite = new Composite(tabComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		descComposite.setLayout(layout);
		descComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		UiUtils.createGridLabel(descComposite, Messages.short_description_with_colon, 1, 0, SWT.NONE);
		shortDesc = UiUtils.createGridText(descComposite, 1, 0, SWT.NONE, compoundModifyListener);

		Label label = UiUtils.createGridLabel(descComposite, Messages.documentation_with_colon, 1, 0, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
		documentation = UiUtils.createGridText(descComposite, 1, 0, SWT.MULTI | SWT.V_SCROLL, compoundModifyListener);
		documentation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private Control getMainTabControl(CTabFolder parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.main);

		Composite nameComposite = new Composite(tabComposite, SWT.NONE);
		GridLayout layout = new GridLayout(3, false);
		layout.marginRight = 8;
		layout.marginHeight = layout.marginWidth = 0;
		nameComposite.setLayout(layout);
		nameComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		Label label = UiUtils.createGridLabel(nameComposite, Messages.component_name_with_colon, 1, 0, SWT.NONE);
		int labelWidth = label.computeSize(SWT.DEFAULT, SWT.DEFAULT).x + 5;
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridData.widthHint = labelWidth;
		label.setLayoutData(gridData);

		componentName = UiUtils.createGridText(nameComposite, 2, 0, SWT.NONE, compoundModifyListener);

		UiUtils.createGridLabel(nameComposite, Messages.component_type_with_colon, 1, 0, SWT.NONE);
		componentType = UiUtils.createGridCombo(nameComposite, 1, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);

		componentType.setItems(AbstractComponentType.getComponentTypeIDs(true));
		componentType.addModifyListener(compoundModifyListener);

		// not nice but I had to make equal 2 columns form different Composites
		// the purpose of hlpComposite is to create empty space, the same size
		// as componentCategory
		UiUtils.createEmptyPanel(nameComposite);

		int textWidth = componentType.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		gridData = (GridData) componentType.getLayoutData();
		gridData.widthHint = textWidth;
		componentType.setLayoutData(gridData);

		Group versionGroup = new Group(tabComposite, SWT.NONE);
		versionGroup.setText(Messages.version);
		layout = new GridLayout(3, false);
		versionGroup.setLayout(layout);
		versionGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		versionDesignator = new VersionDesignator(versionGroup);
		versionDesignator.addVersionDesignatorListener(compoundModifyListener);

		Control control = versionDesignator.getVersionDsTypeLabel();
		gridData = (GridData) control.getLayoutData();
		gridData.widthHint = labelWidth - layout.marginWidth - 3;
		control.setLayoutData(gridData);

		control = versionDesignator.getVersionDsTypeCombo();
		gridData = (GridData) control.getLayoutData();
		gridData.widthHint = textWidth;
		control.setLayoutData(gridData);

		Group propertiesGroup = new Group(tabComposite, SWT.NO_RADIO_GROUP);

		propertiesGroup.setText(Messages.properties);
		layout = new GridLayout(2, false);
		propertiesGroup.setLayout(layout);
		propertiesGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		propertyURLCheckbox = UiUtils.createCheckButton(propertiesGroup, Messages.user_properties, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.widget;

				if (!button.getSelection()) {
					propertyURL.setText(""); //$NON-NLS-1$
				}
			}
		});
		gridData = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gridData.horizontalSpan = 2;
		propertyURLCheckbox.setLayoutData(gridData);

		label = UiUtils.createGridLabel(propertiesGroup, Messages.properties_with_colon, 1, labelWidth - layout.marginWidth - 3, SWT.NONE);

		Composite propertiesComposite = new Composite(propertiesGroup, SWT.NONE);

		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		propertiesComposite.setLayout(layout);
		propertiesComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		propertyURL = UiUtils.createGridText(propertiesComposite, 1, 0, SWT.NONE, compoundModifyListener);
		Button browseButton = new Button(propertiesComposite, SWT.PUSH);
		browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		browseButton.setText(Messages.browse_with_dots);
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				FileDialog dlg = new FileDialog(getSite().getShell());
				dlg.setFilterExtensions(new String[] { "*.properties" }); //$NON-NLS-1$
				String name = dlg.open();
				if (name == null)
					return;
				try {
					propertyURL.setText(TextUtils.notNullString(new URL(name)));
				} catch (MalformedURLException e) {
					try {
						propertyURL.setText(TextUtils.notNullString(new File(name).toURI().toURL()));
					} catch (MalformedURLException e1) {
					}
				}
			}
		});

		propertyURLCheckbox.addSelectionListener(new CheckboxSelectionListener(new Control[] { label, propertyURL, browseButton }));

		Group rmapGroup = new Group(tabComposite, SWT.NO_RADIO_GROUP);
		rmapGroup.setText(Messages.resource_map);
		layout = new GridLayout(2, false);
		rmapGroup.setLayout(layout);
		rmapGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		requestURLCheckbox = UiUtils.createCheckButton(rmapGroup, Messages.use_resource_map, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Button button = (Button) e.widget;

				if (!button.getSelection()) {
					requestURL.setText(""); //$NON-NLS-1$
				}
			}
		});
		gridData = new GridData(SWT.BEGINNING, SWT.CENTER, false, false);
		gridData.horizontalSpan = 2;
		requestURLCheckbox.setLayoutData(gridData);

		label = UiUtils.createGridLabel(rmapGroup, Messages.rmap_url_with_colon, 1, labelWidth - layout.marginWidth - 3, SWT.NONE);

		Composite rmapComposite = new Composite(rmapGroup, SWT.NONE);

		layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		rmapComposite.setLayout(layout);
		rmapComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		requestURL = UiUtils.createGridText(rmapComposite, 1, 0, SWT.NONE, compoundModifyListener);
		browseButton = new Button(rmapComposite, SWT.PUSH);
		browseButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		browseButton.setText(Messages.browse_with_dots);
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent se) {
				FileDialog dlg = new FileDialog(getSite().getShell());
				dlg.setFilterExtensions(new String[] { "*.rmap" }); //$NON-NLS-1$
				String name = dlg.open();
				if (name == null)
					return;
				try {
					requestURL.setText(TextUtils.notNullString(new URL(name)));
				} catch (MalformedURLException e) {
					try {
						requestURL.setText(TextUtils.notNullString(new File(name).toURI().toURL()));
					} catch (MalformedURLException e1) {
					}
				}
			}
		});

		requestURLCheckbox.addSelectionListener(new CheckboxSelectionListener(new Control[] { label, requestURL, browseButton }));

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private Control getPropertiesTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.properties);

		/*
		 * Group propertiesGroup = new Group(tabComposite, SWT.NONE);
		 * propertiesGroup.setText("Properties"); GridLayout layout = new
		 * GridLayout(1, false); propertiesGroup.setLayout(layout);
		 * propertiesGroup.setLayoutData(new GridData(GridData.FILL,
		 * GridData.FILL, true, true));
		 * 
		 * properties = UiUtils.createNoBorderGridText(propertiesGroup, 1, 0,
		 * compoundModifyListener, SWT.MULTI); properties.setLayoutData(new
		 * GridData(SWT.FILL, SWT.FILL, true, true));
		 */
		properties = new Properties(tabComposite, SWT.NONE);
		properties.addPropertiesModifyListener(compoundModifyListener);

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private AdvisorNodeBuilder getSelectedNode() {
		int idx = nodeTable.getTable().getSelectionIndex();
		return idx >= 0 ? (AdvisorNodeBuilder) nodeTable.getElementAt(idx) : null;
	}

	private int getSelectionIndex() {
		return nodeTable.getTable().getSelectionIndex();
	}

	private Control getXMLTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.xml_content);

		Composite xmlComposite = new Composite(tabComposite, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginHeight = layout.marginWidth = 0;
		xmlComposite.setLayout(layout);
		xmlComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		xml = UiUtils.createGridText(xmlComposite, 1, 0, SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY, null);
		xml.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private void initStackControl() {
		nodeTree.setSelection(nodeTree.getItem(0));
		nodesStackLayout.topControl = nodesHash.get(nodeTree.getItem(0).getText());
		nodesStackComposite.layout();
	}

	private void loadComponent(boolean materialize) {
		if (!commitChangesToQuery())
			return;

		try {
			ResolveJob resolveJob = new ResolveJob(componentQuery.createComponentQuery(), materialize, getSite(), continueOnError);
			resolveJob.schedule();
		} catch (CoreException e) {
			ErrorDialog.openError(getSite().getShell(), null, null, e.getStatus());
		}
	}

	private void newNode() {
		if (!saveLastNode())
			return;

		AdvisorNodeBuilder node = addEmptyNode();
		refreshList();
		selectRow(node);

		setDirty(true);

		nodeSelectionEvent();
	}

	private void nodeSelectionEvent() {
		updateLastNode();
		refreshNodeFields();
		enableDisableButtonGroup();
		initStackControl();
		namePattern.setFocus();
	}

	private void refreshList() {
		nodeTable.setInput(componentQuery.getAdvisoryNodes());
	}

	private void refreshNodeFields() {
		try {
			suppressModifyListener = true;

			AdvisorNodeBuilder node = getSelectedNode();
			if (node == null)
				//
				// Use an empty node as template to get the defaults right.
				//
				node = new AdvisorNodeBuilder();

			allowCircular.setSelection(node.allowCircularDependency());
			namePattern.setText(TextUtils.notNullString(node.getNamePattern()));
			filter.setText(TextUtils.notNullString(node.getFilter()));
			category.select(category.indexOf(TextUtils.notNullString(node.getComponentTypeID())));
			overlayFolder.setText(TextUtils.notNullString(node.getOverlayFolder()));
			wantedAttributes.setText(TextUtils.notNullString(TextUtils.concat(node.getAttributes(), ","))); //$NON-NLS-1$
			prune.setSelection(node.isPrune());
			mutableLevel.select(mutableLevel.indexOf(node.getMutableLevel().toString()));
			sourceLevel.select(sourceLevel.indexOf(node.getSourceLevel().toString()));
			skipComponent.setSelection(node.skipComponent());
			useTargetPlatform.setSelection(node.isUseTargetPlatform());
			useWorkspace.setSelection(node.isUseWorkspace());
			useMaterialization.setSelection(node.isUseMaterialization());
			useResolutionService.setSelection(node.isUseRemoteResolution());

			branchTagPath.setText(TextUtils.notNullString(VersionSelector.toString(node.getBranchTagPath())));
			revision.setText(TextUtils.notNullString(node.getRevision()));
			Date ts = node.getTimestamp();
			timestamp.setText(ts == null ? "" : timestampFormat.format(ts)); //$NON-NLS-1$

			VersionRange vs = node.getVersionOverride();
			boolean enableOverrideFlag = (vs != null);
			enableOverride.setSelection(enableOverrideFlag);
			versionOverride.setEnabled(enableOverrideFlag);
			versionOverride.refreshValues(vs);

			nodeProperties.setProperties(node.getProperties());
			nodeProperties.refreshList();

			Documentation doc = node.getDocumentation();
			nodeDocumentation.setText(TextUtils.notNullString(doc == null ? null : doc.toString()));
		} finally {
			suppressModifyListener = false;
		}
	}

	private void refreshQuery() {
		setDirty(false);
		mute = true;
		try {
			ComponentRequest request = componentQuery.getRootRequest();
			componentName.setText(TextUtils.notNullString(request.getName()));
			componentType.select(componentType.indexOf(TextUtils.notNullString(request.getComponentTypeID())));
			versionDesignator.refreshValues(request.getVersionRange());

			String string = TextUtils.notNullString(componentQuery.getPropertiesURL());
			propertyURL.setText(string);
			propertyURLCheckbox.setSelection(string.length() > 0);
			propertyURLCheckbox.notifyListeners(SWT.Selection, new Event());

			string = TextUtils.notNullString(componentQuery.getResourceMapURL());
			requestURL.setText(string);
			requestURLCheckbox.setSelection(string.length() > 0);
			requestURLCheckbox.notifyListeners(SWT.Selection, new Event());
			properties.setProperties(componentQuery.getDeclaredProperties());
			shortDesc.setText(TextUtils.notNullString(componentQuery.getShortDesc()));
			Documentation doc = componentQuery.getDocumentation();
			documentation.setText(TextUtils.notNullString(doc == null ? "" //$NON-NLS-1$
					: doc.toString()));
			refreshList();
			properties.refreshList();
			needsRefresh = false;
			nodeSelectionEvent();
		} finally {
			mute = false;
		}
	}

	private void removeNode() {
		AdvisorNodeBuilder node = getSelectedNode();
		if (node != null) {
			int last_idx = getSelectionIndex();

			componentQuery.removeAdvisorNode(node);
			setDirty(true);
			lastEditedNode = DONT_SAVE;
			refreshList();

			if (componentQuery.getAdvisoryNodes().size() > last_idx) {
				nodeTable.getTable().setSelection(last_idx);
			} else if (componentQuery.getAdvisoryNodes().size() > 0) {
				nodeTable.getTable().setSelection(last_idx - 1);
			} else {
				nodeTable.getTable().deselectAll();
			}
			nodeSelectionEvent();
		}
	}

	private boolean saveLastNode() {
		return saveLastNode(EMPTY_ACTIVATOR);
	}

	private boolean saveLastNode(IActivator failureActivator) {
		if (lastEditedNode != -1 && lastEditedNode != DONT_SAVE)
			return saveNode(lastEditedNode, failureActivator);

		return true;
	}

	private boolean saveNode(int nodeIdx, IActivator failureActivator) {
		AdvisorNodeBuilder node = (AdvisorNodeBuilder) nodeTable.getElementAt(nodeIdx);
		boolean isNewNode = false;
		if (node == null) {
			node = new AdvisorNodeBuilder();
			isNewNode = true;
		}

		boolean refreshListNeeded = false;
		String patternStr = UiUtils.trimmedValue(namePattern);
		String categoryStr = category.getItem(category.getSelectionIndex());
		if (categoryStr.length() == 0)
			categoryStr = null;

		String filterStr = UiUtils.trimmedValue(filter);
		if (patternStr == null && filterStr == null && categoryStr == null) {
			failureActivator.activate();
			MessageDialog.openError(getSite().getShell(), null, Messages.name_pattern_component_type_or_filter_must_be_set);
			return false;
		}

		Pattern pattern = null;
		if (patternStr != null) {
			try {
				pattern = Pattern.compile(patternStr);
			} catch (PatternSyntaxException e) {
				failureActivator.activate();
				MessageDialog.openError(getSite().getShell(), null, e.getMessage());
				return false;
			}
		}

		Filter nodeFilter = null;
		if (filterStr != null) {
			try {
				nodeFilter = FilterFactory.newInstance(filterStr);
			} catch (InvalidSyntaxException e) {
				failureActivator.activate();
				MessageDialog.openError(getSite().getShell(), null, e.getMessage());
				return false;
			}
		}

		if (!(Trivial.equalsAllowNull(pattern, node.getNamePattern()) && Trivial.equalsAllowNull(category, node.getComponentTypeID()) && Trivial
				.equalsAllowNull(nodeFilter, node.getFilter()))) {
			// Selection criteria changed. Verify that it's not a duplicate
			//
			AdvisorNodeBuilder patternEqual = componentQuery.getNodeByCriteria(pattern, categoryStr, nodeFilter);
			if (patternEqual != null) {
				failureActivator.activate();
				if (!MessageDialog.openQuestion(getSite().getShell(), null, Messages.overwrite_existing_node_with_same_criteria))
					return false;
				componentQuery.removeAdvisorNode(patternEqual);
			}
			refreshListNeeded = true;
		}
		node.setNamePattern(pattern);
		node.setFilter(nodeFilter);
		node.setComponentTypeID(categoryStr);
		node.setAllowCircularDependency(allowCircular.getSelection());

		boolean override = enableOverride.getSelection();
		VersionRange versionOverrideRange = null;
		if (override)
			try {
				versionOverrideRange = versionOverride.getDirectVersionDesignator();
			} catch (CoreException e1) {
				failureActivator.activate();
				ErrorDialog.openError(getSite().getShell(), null, null, e1.getStatus());
				return false;
			}

		try {
			String tmp = UiUtils.trimmedValue(overlayFolder);
			node.setOverlayFolder(tmp == null ? null : URLUtils.normalizeToURL(tmp));
		} catch (Exception e) {
			failureActivator.activate();
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		node.setSkipComponent(skipComponent.getSelection());

		node.getAttributes().clear();
		String tmp = UiUtils.trimmedValue(wantedAttributes);
		if (tmp != null)
			for (String attribute : tmp.split(",")) //$NON-NLS-1$
				node.addAttribute(attribute);
		node.setPrune(prune.getSelection());

		int idx = mutableLevel.getSelectionIndex();
		node.setMutableLevel(idx >= 0 ? MutableLevel.values()[idx] : null);

		idx = sourceLevel.getSelectionIndex();
		node.setSourceLevel(idx >= 0 ? SourceLevel.values()[idx] : null);

		node.setUseTargetPlatform(useTargetPlatform.getSelection());
		node.setUseWorkspace(useWorkspace.getSelection());
		node.setUseMaterialization(useMaterialization.getSelection());
		node.setUseRemoteResolution(useResolutionService.getSelection());

		node.setBranchTagPath(VersionSelector.fromPath(UiUtils.trimmedValue(branchTagPath)));

		node.setRevision(UiUtils.trimmedValue(revision));

		tmp = UiUtils.trimmedValue(timestamp);
		if (tmp != null) {
			try {
				node.setTimestamp(timestampFormat.parse(tmp));
			} catch (ParseException e) {
				failureActivator.activate();
				MessageDialog.openError(getSite().getShell(), null, Messages.timestamp_must_conform_to_format_with_colon
						+ timestampFormat.toPattern());
				return false;
			}
		} else
			node.setTimestamp(null);

		node.setVersionOverride(versionOverrideRange);

		nodeProperties.fillProperties(node.getProperties());

		String doc = UiUtils.trimmedValue(nodeDocumentation);

		try {
			node.setDocumentation(doc == null ? null : Documentation.parse(doc));
		} catch (Exception e) {
			failureActivator.activate();
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		if (isNewNode) {
			// This was an add operation
			//
			componentQuery.addAdvisorNode(node);
			refreshListNeeded = true;
		}
		if (refreshListNeeded) {
			refreshList();
			nodeSelectionEvent();
		}

		enableDisableButtonGroup();
		return true;
	}

	private void saveToPath(IPath path) {
		try {
			SaveRunnable sr = new SaveRunnable(componentQuery.createComponentQuery(), path);
			getSite().getWorkbenchWindow().run(true, true, sr);
			setInputWithNotify(sr.getSavedInput());
			setDirty(false);
			setPartName(path.lastSegment());
			firePropertyChange(IWorkbenchPart.PROP_TITLE);
		} catch (InvocationTargetException e) {
			CoreException t = BuckminsterException.wrap(e);
			String msg = Messages.unable_to_save_file_0 + path;
			CorePlugin.getLogger().error(t, msg);
			ErrorDialog.openError(getSite().getShell(), null, msg, t.getStatus());
		} catch (InterruptedException e) {
		}
	}

	private boolean selectRow(AdvisorNodeBuilder node) {
		int idx = componentQuery.getAdvisoryNodes().indexOf(node);

		if (idx == -1)
			return false;

		nodeTable.getTable().setSelection(idx);

		return true;
	}

	private void setDirty(boolean flag) {
		if (mute || hasChanges == flag)
			return;
		hasChanges = flag;
		firePropertyChange(PROP_DIRTY);
	}

	private void swapAndReselect(int idxOffset, int selectionOffset) {
		if (!saveLastNode()) {
			return;
		}

		Table table = nodeTable.getTable();
		int idx = table.getSelectionIndex() + idxOffset;
		if (idx <= 0)
			return;

		List<AdvisorNodeBuilder> nl = componentQuery.getAdvisoryNodes();
		if (idx >= nl.size())
			return;

		nl.set(idx - 1, nl.set(idx, nl.get(idx - 1)));
		refreshList();
		table.select(idx + selectionOffset);
		nodeSelectionEvent();
		setDirty(true);
	}

	private void updateLastNode() {
		if (getSelectionIndex() != -1) {
			lastSelectedNode = getSelectionIndex();
		}

		lastEditedNode = getSelectionIndex();
	}
}
