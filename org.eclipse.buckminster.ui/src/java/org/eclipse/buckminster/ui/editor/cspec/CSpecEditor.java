/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.ComponentRequestBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.GeneratorAlreadyDefinedException;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionType;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.sax.Utils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.SaveRunnable;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.ArtifactType;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.editor.IDerivedEditorInput;
import org.eclipse.buckminster.ui.general.editor.ITableModifyListener;
import org.eclipse.buckminster.ui.general.editor.TableModifyEvent;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTableEditor;
import org.eclipse.buckminster.ui.general.editor.structured.FieldModifyEvent;
import org.eclipse.buckminster.ui.general.editor.structured.IActivator;
import org.eclipse.buckminster.ui.general.editor.structured.IFieldModifyListener;
import org.eclipse.buckminster.ui.general.editor.structured.OnePageTableEditor;
import org.eclipse.buckminster.ui.internal.CSpecEditorInput;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
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

/**
 * @author Karel Brezina
 * 
 */
public class CSpecEditor extends EditorPart implements IEditorMatchingStrategy {
	// don't need generics here - need just to setDirty
	class CompoundModifyListener<T> implements ModifyListener, ITableModifyListener<T>, IFieldModifyListener {

		public void modifyField(FieldModifyEvent e) {
			setDirty(true);
		}

		public void modifyTable(TableModifyEvent<T> e) {
			setDirty(true);
		}

		public void modifyText(ModifyEvent e) {
			setDirty(true);
		}
	}

	enum CSpecEditorTab {
		MAIN(0), ACTIONS(1), ARTIFACTS(2), GROUPS(3), ATTRIBUTES(4), DEPENDENCIES(5), GENERATORS(6), DOCUMENTATION(6), XML(8);

		private int seqNum;

		CSpecEditorTab(int seqNum) {
			this.seqNum = seqNum;
		}

		public int getSeqNum() {
			return seqNum;
		}
	}

	private static final String SAVEABLE_CSPEC_NAME = "buckminster.cspec"; //$NON-NLS-1$

	private CSpecBuilder cspec;

	private List<ActionBuilder> actionBuilders = new ArrayList<ActionBuilder>();

	private Map<ActionBuilder, List<ActionArtifactBuilder>> actionArtifactBuilders = new HashMap<ActionBuilder, List<ActionArtifactBuilder>>();

	private List<ArtifactBuilder> artifactBuilders = new ArrayList<ArtifactBuilder>();

	private List<GroupBuilder> groupBuilders = new ArrayList<GroupBuilder>();

	private List<ComponentRequestBuilder> dependencyBuilders = new ArrayList<ComponentRequestBuilder>();

	private List<GeneratorBuilder> generatorBuilders = new ArrayList<GeneratorBuilder>();

	@SuppressWarnings("rawtypes")
	private CompoundModifyListener compoundModifyListener;

	private boolean hasChanges = false;

	private boolean mute = false;

	private boolean needsRefresh = false;

	private boolean readOnly = true;

	private CTabFolder tabFolder;

	private CTabItem mainTab;

	private CTabItem actionsTab;

	private CTabItem artifactsTab;

	private CTabItem groupsTab;

	private CTabItem attributesTab;

	private CTabItem dependenciesTab;

	private CTabItem generatorsTab;

	private CTabItem documentationTab;

	private CTabItem xmlTab;

	private Text componentName;

	private Combo componentType;

	private Text versionString;

	private Combo versionType;

	private ActionsTable actionsTable;

	private OnePageTableEditor<ActionBuilder> actionsEditor;

	private OnePageTableEditor<ArtifactBuilder> artifactsEditor;

	private OnePageTableEditor<GroupBuilder> groupsEditor;

	private AllAttributesView attributesView;

	private SimpleTableEditor<ComponentRequestBuilder> dependenciesEditor;

	private SimpleTableEditor<GeneratorBuilder> generatorsEditor;

	private Text shortDesc;

	private Text documentation;

	private Text xml;

	private Button externalSaveAsButton;

	@Override
	public void createPartControl(Composite parent) {
		Composite topComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, true);
		layout.marginHeight = layout.marginWidth = 0;
		topComposite.setLayout(layout);

		tabFolder = new CTabFolder(topComposite, SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		mainTab = new CTabItem(tabFolder, SWT.NONE);
		mainTab.setText(Messages.main);
		mainTab.setControl(getMainTabControl(tabFolder));
		mainTab.setData(CSpecEditorTab.MAIN);

		actionsTab = new CTabItem(tabFolder, SWT.NONE);
		actionsTab.setText(Messages.actions);
		actionsTab.setControl(getActionsTabControl(tabFolder));
		actionsTab.setData(CSpecEditorTab.ACTIONS);

		artifactsTab = new CTabItem(tabFolder, SWT.NONE);
		artifactsTab.setText(Messages.artifacts);
		artifactsTab.setControl(getArtifactsTabControl(tabFolder));
		artifactsTab.setData(CSpecEditorTab.ARTIFACTS);

		groupsTab = new CTabItem(tabFolder, SWT.NONE);
		groupsTab.setText(Messages.groups);
		groupsTab.setControl(getGroupsTabControl(tabFolder));
		groupsTab.setData(CSpecEditorTab.GROUPS);

		attributesTab = new CTabItem(tabFolder, SWT.NONE);
		attributesTab.setText(Messages.all_attributes);
		attributesTab.setControl(getAttributesTabControl(tabFolder));
		attributesTab.setData(CSpecEditorTab.ATTRIBUTES);

		dependenciesTab = new CTabItem(tabFolder, SWT.NONE);
		dependenciesTab.setText(Messages.dependencies);
		dependenciesTab.setControl(getDependenciesTabControl(tabFolder));
		dependenciesTab.setData(CSpecEditorTab.DEPENDENCIES);

		generatorsTab = new CTabItem(tabFolder, SWT.NONE);
		generatorsTab.setText(Messages.generators);
		generatorsTab.setControl(getGeneratorsTabControl(tabFolder));
		generatorsTab.setData(CSpecEditorTab.GENERATORS);

		documentationTab = new CTabItem(tabFolder, SWT.NONE);
		documentationTab.setText(Messages.documentation);
		documentationTab.setControl(getDocumentationTabControl(tabFolder));
		documentationTab.setData(CSpecEditorTab.DOCUMENTATION);

		xmlTab = new CTabItem(tabFolder, SWT.NONE);
		xmlTab.setText(Messages.xml_content);
		xmlTab.setControl(getXMLTabControl(tabFolder));
		xmlTab.setData(CSpecEditorTab.XML);

		tabFolder.addSelectionListener(new SelectionAdapter() {
			private final IActivator ACTIONS_ACTIVATOR = new IActivator() {
				public void activate() {
					switchTab(CSpecEditorTab.ACTIONS);
				}
			};

			private final IActivator ARTIFACTS_ACTIVATOR = new IActivator() {
				public void activate() {
					switchTab(CSpecEditorTab.ARTIFACTS);
				}
			};

			private final IActivator GROUPS_ACTIVATOR = new IActivator() {
				public void activate() {
					switchTab(CSpecEditorTab.GROUPS);
				}
			};

			private CTabItem lastTab = mainTab;

			@Override
			public void widgetSelected(SelectionEvent e) {
				// save row
				if (lastTab != e.item) {
					if (lastTab == actionsTab)
						if (!actionsEditor.save(ACTIONS_ACTIVATOR))
							return;

					if (lastTab == artifactsTab)
						if (!artifactsEditor.save(ARTIFACTS_ACTIVATOR))
							return;

					if (lastTab == groupsTab)
						if (!groupsEditor.save(GROUPS_ACTIVATOR))
							return;
				}

				if (mainTab == e.item) {
					componentName.setFocus();
				} else if (actionsTab == e.item) {
					actionsEditor.setFocus();
				} else if (artifactsTab == e.item) {
					artifactsEditor.setFocus();
				} else if (groupsTab == e.item) {
					groupsEditor.setFocus();
				} else if (attributesTab == e.item) {
					attributesView.setFocus();
				} else if (dependenciesTab == e.item) {
					dependenciesEditor.setFocus();
				} else if (generatorsTab == e.item) {
					generatorsEditor.setFocus();
				} else if (documentationTab == e.item) {
					shortDesc.setFocus();
				} else if (xmlTab == e.item) {
					if (!commitChanges())
						MessageDialog.openWarning(getSite().getShell(), null, Messages.xml_content_was_not_updated_due_to_errors);
					else
						xml.setText(getCSpecXML());
				}

				lastTab = (CTabItem) e.item;
			}
		});

		createActionButtons(topComposite);
	}

	public void doExternalSaveAs() {
		if (!commitChanges())
			return;
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.cspec" }); //$NON-NLS-1$
		final String location = dlg.open();
		if (location == null)
			return;
		saveToPath(new Path(location));
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		if (!commitChanges())
			return;

		IEditorInput input = getEditorInput();
		if (input == null)
			return;

		IPath path = (input instanceof ILocationProvider) ? ((ILocationProvider) input).getPath(input) : ((IPathEditorInput) input).getPath();

		saveToPath(path);
	}

	@Override
	public void doSaveAs() {
		if (!commitChanges())
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
		if (!(input instanceof ILocationProvider || input instanceof IPathEditorInput || input instanceof IURIEditorInput || input instanceof CSpecEditorInput))
			throw new PartInitException(Messages.invalid_input);
		setSite(site);

		if (input instanceof IURIEditorInput) {
			try {
				input = EditorUtils.getExternalFileEditorInput((IURIEditorInput) input, ArtifactType.CSPEC);
			} catch (Exception e) {
				throw new PartInitException(Messages.unable_to_open_editor, e);
			}
		}

		InputStream stream = null;
		try {
			cspec = new CSpecBuilder();
			if (input instanceof CSpecEditorInput) {
				readOnly = true;
				cspec.initFrom(((CSpecEditorInput) input).getCSpec());
			} else {
				IPath path = (input instanceof ILocationProvider) ? ((ILocationProvider) input).getPath(input) : ((IPathEditorInput) input).getPath();

				readOnly = (!SAVEABLE_CSPEC_NAME.equalsIgnoreCase(path.lastSegment()));

				File file = path.toFile();
				if (file.length() != 0) {
					String systemId = file.toString();
					stream = new FileInputStream(file);
					IParser<CSpec> parser = CorePlugin.getDefault().getParserFactory().getCSpecParser(true);
					cspec.initFrom(parser.parse(systemId, stream));
				}
			}

			needsRefresh = true;
			if (componentName != null) {
				refreshValues();
			}

			setInputWithNotify(input);
			setPartName(input.getName() + (readOnly ? Messages.read_only_in_paranthesis : "")); //$NON-NLS-1$
		} catch (Exception e) {
			throw new PartInitException(BuckminsterException.wrap(e).getMessage());
		} finally {
			IOUtils.close(stream);
		}
		compoundModifyListener = new CompoundModifyListener<Object>();
	}

	@Override
	public boolean isDirty() {
		return hasChanges;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return !readOnly;
	}

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

	public void setEnabled(boolean enabled) {
		componentName.setEnabled(enabled);
		componentType.setEnabled(enabled);
		versionString.setEnabled(enabled);
		versionType.setEnabled(enabled);
		actionsEditor.setEnabled(enabled);
		artifactsEditor.setEnabled(enabled);
		groupsEditor.setEnabled(enabled);
		dependenciesEditor.setEnabled(enabled);
		generatorsEditor.setEnabled(enabled);
		shortDesc.setEnabled(enabled);
		documentation.setEnabled(enabled);
	}

	@Override
	public void setFocus() {
		tabFolder.setFocus();

		if (needsRefresh) {
			refreshValues();
		}
	}

	Map<ActionBuilder, List<ActionArtifactBuilder>> getActionArtifactBuilders() {
		return actionArtifactBuilders;
	}

	List<ActionBuilder> getActionBuilders() {
		return actionBuilders;
	}

	OnePageTableEditor<ActionBuilder> getActionsEditor() {
		return actionsEditor;
	}

	ActionsTable getActionsTable() {
		return actionsTable;
	}

	List<ArtifactBuilder> getArtifactBuilders() {
		return artifactBuilders;
	}

	OnePageTableEditor<ArtifactBuilder> getArtifactsEditor() {
		return artifactsEditor;
	}

	String[] getAttributeNames(String excludeName) {
		List<String> nameList = new ArrayList<String>();

		for (ActionBuilder builder : actionBuilders) {
			if (builder.getName() != null) {
				nameList.add(builder.getName());
			}
		}
		for (List<ActionArtifactBuilder> list : actionArtifactBuilders.values())
			for (ActionArtifactBuilder builder : list)
				nameList.add(builder.getName());

		for (ArtifactBuilder builder : artifactBuilders) {
			if (builder.getName() != null) {
				nameList.add(builder.getName());
			}
		}
		for (GroupBuilder builder : groupBuilders) {
			if (builder.getName() != null) {
				nameList.add(builder.getName());
			}
		}

		if (excludeName != null) {
			nameList.remove(excludeName);
		}

		String[] array = nameList.toArray(new String[0]);
		Arrays.sort(array, new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		return array;
	}

	String[] getComponentNames() {
		List<String> list = new ArrayList<String>();

		for (ComponentRequestBuilder builder : dependencyBuilders) {
			if (builder.getName() != null) {
				list.add(builder.getName());
			}
		}

		String[] array = list.toArray(new String[0]);
		Arrays.sort(array, new Comparator<String>() {

			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});

		return array;
	}

	ComponentRequestBuilder getDependencyBuilder(String cname) {
		if (cname == null)
			return null;

		for (ComponentRequestBuilder builder : dependencyBuilders) {
			if (cname.equals(builder.getName())) {
				return builder;
			}
		}

		return null;
	}

	List<ComponentRequestBuilder> getDependencyBuilders() {
		return dependencyBuilders;
	}

	List<GeneratorBuilder> getGeneratorBuilders() {
		return generatorBuilders;
	}

	List<GroupBuilder> getGroupBuilders() {
		return groupBuilders;
	}

	OnePageTableEditor<GroupBuilder> getGroupsEditor() {
		return groupsEditor;
	}

	void switchTab(CSpecEditorTab tab) {
		tabFolder.setSelection(tab.getSeqNum());
	}

	private void addToActionArtifactBuilderMap(ActionArtifactBuilder actionArtifactbuilder) {
		if (actionArtifactbuilder.getActionName() == null)
			return;

		ActionBuilder actionBuilder = findActionBuilder(actionArtifactbuilder.getActionName());

		if (actionBuilder != null) {
			List<ActionArtifactBuilder> list = actionArtifactBuilders.get(actionBuilder);

			if (list == null) {
				list = new ArrayList<ActionArtifactBuilder>();
				actionArtifactBuilders.put(actionBuilder, list);
			}

			list.add(actionArtifactbuilder);
		}
	}

	private boolean commitChanges() {
		if (actionsEditor.isVisible())
			if (!actionsEditor.save())
				return false;

		if (artifactsEditor.isVisible())
			if (!artifactsEditor.save())
				return false;

		if (groupsEditor.isVisible())
			if (!groupsEditor.save())
				return false;

		String name = UiUtils.trimmedValue(componentName);
		if (name == null) {
			MessageDialog.openError(getSite().getShell(), null, Messages.the_component_must_have_a_name);
			return false;
		}
		cspec.setName(name);

		String cType = componentType.getItem(componentType.getSelectionIndex());
		if (cType.length() == 0)
			cType = null;
		cspec.setComponentTypeID(cType);

		try {
			cspec.setVersion(VersionHelper.createVersion(versionType.getItem(versionType.getSelectionIndex()), UiUtils.trimmedValue(versionString)));
		} catch (CoreException e) {
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		try {
			Map<String, AttributeBuilder> attributesMap = cspec.getAttributes();

			if (attributesMap != null) {
				attributesMap.clear();
			}

			for (ActionBuilder action : actionBuilders) {
				cspec.addAttribute(action);
			}

			for (List<ActionArtifactBuilder> list : actionArtifactBuilders.values())
				for (ActionArtifactBuilder item : list)
					cspec.addAttribute(item);

			for (ArtifactBuilder artifact : artifactBuilders) {
				cspec.addAttribute(artifact);
			}

			for (GroupBuilder group : groupBuilders) {
				cspec.addAttribute(group);
			}
		} catch (AttributeAlreadyDefinedException e) {
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		try {
			Map<String, ComponentRequestBuilder> dependeciesMap = cspec.getDependencyMap();

			if (dependeciesMap != null) {
				dependeciesMap.clear();
			}

			for (ComponentRequestBuilder dependency : dependencyBuilders) {
				cspec.addDependency(dependency);
			}
		} catch (CoreException e) {
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		try {
			Collection<GeneratorBuilder> generators = cspec.getGeneratorList();

			if (generators.size() > 0) {
				generators.clear();
			}

			for (GeneratorBuilder generator : generatorBuilders) {
				cspec.addGenerator(generator);
			}
		} catch (GeneratorAlreadyDefinedException e) {
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		String doc = UiUtils.trimmedValue(shortDesc);
		cspec.setShortDesc(doc);

		doc = UiUtils.trimmedValue(documentation);
		try {
			cspec.setDocumentation(doc == null ? null : Documentation.parse(doc));
		} catch (CoreException e) {
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		try {
			cspec.createCSpec().verifyConsistency();
		} catch (CoreException e) {
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
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

		Composite pressButtonsBox = new Composite(allButtonsBox, SWT.NONE);
		layout = new GridLayout(1, true);
		layout.marginHeight = layout.marginWidth = 0;
		pressButtonsBox.setLayout(layout);
		pressButtonsBox.setLayoutData(new GridData(SWT.END, SWT.FILL, true, false));

		externalSaveAsButton = UiUtils.createPushButton(pressButtonsBox, Messages.external_save_as, new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				doExternalSaveAs();
			}
		});
		externalSaveAsButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
	}

	private ActionBuilder findActionBuilder(String actionName) {
		for (ActionBuilder builder : actionBuilders)
			if (actionName.equals(builder.getName()))
				return builder;

		return null;
	}

	private Control getActionsTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.actions);

		ActionsTable table = new ActionsTable(this, actionBuilders, actionArtifactBuilders, cspec, !isSaveAsAllowed());
		table.addFieldModifyListener(compoundModifyListener);

		actionsEditor = new OnePageTableEditor<ActionBuilder>(tabComposite, table, false, SWT.NONE);

		actionsTable = table;

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private Control getArtifactsTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.artifacts);

		ArtifactsTable table = new ArtifactsTable(this, artifactBuilders, cspec, !isSaveAsAllowed());
		table.addFieldModifyListener(compoundModifyListener);

		artifactsEditor = new OnePageTableEditor<ArtifactBuilder>(tabComposite, table, false, SWT.NONE);

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private Control getAttributesTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.all_attributes);

		attributesView = new AllAttributesView(tabComposite, SWT.NONE, this);

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private String getCSpecXML() {
		String cspecXML = ""; //$NON-NLS-1$
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			Utils.serialize(cspec.createCSpec(), baos);
			cspecXML = baos.toString();
		} catch (Exception e) {
			// nothing
		}
		return cspecXML;
	}

	@SuppressWarnings("unchecked")
	private Control getDependenciesTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.dependencies);

		DependenciesTable table = new DependenciesTable(dependencyBuilders, cspec, !isSaveAsAllowed());
		table.addTableModifyListener(compoundModifyListener);

		dependenciesEditor = new SimpleTableEditor<ComponentRequestBuilder>(tabComposite, table, null, Messages.cspec_editor_dependency, null, null,
				SWT.NONE);

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private Control getDocumentationTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.documentation);

		Composite descComposite = new Composite(tabComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		descComposite.setLayout(layout);
		descComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		UiUtils.createGridLabel(descComposite, Messages.short_description_with_colon, 1, 0, SWT.NONE);
		shortDesc = UiUtils.createGridText(descComposite, 1, 0, isSaveAsAllowed() ? SWT.NONE : SWT.READ_ONLY, compoundModifyListener);

		Label label = UiUtils.createGridLabel(descComposite, Messages.documentation_with_colon, 1, 0, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
		documentation = UiUtils.createGridText(descComposite, 1, 0, (isSaveAsAllowed() ? SWT.NONE : SWT.READ_ONLY) | SWT.MULTI | SWT.V_SCROLL,
				compoundModifyListener);
		documentation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	@SuppressWarnings("unchecked")
	private Control getGeneratorsTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.generators);

		GeneratorsTable table = new GeneratorsTable(this, generatorBuilders, cspec, !isSaveAsAllowed());
		table.addTableModifyListener(compoundModifyListener);

		generatorsEditor = new SimpleTableEditor<GeneratorBuilder>(tabComposite, table, null, Messages.cspec_editor_generator, null, null, SWT.NONE);

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private Control getGroupsTabControl(Composite parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.groups);

		GroupsTable table = new GroupsTable(this, groupBuilders, cspec, !isSaveAsAllowed());
		table.addFieldModifyListener(compoundModifyListener);

		groupsEditor = new OnePageTableEditor<GroupBuilder>(tabComposite, table, false, SWT.NONE);

		return EditorUtils.getOptimizedControl(tabComposite);
	}

	private Control getMainTabControl(CTabFolder parent) {
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, Messages.main);

		Composite nameComposite = new Composite(tabComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 8;
		layout.marginHeight = layout.marginWidth = 0;
		nameComposite.setLayout(layout);
		nameComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		Label label = UiUtils.createGridLabel(nameComposite, Messages.component_name_with_colon, 1, 0, SWT.NONE);
		int labelWidth = label.computeSize(SWT.DEFAULT, SWT.DEFAULT).x + 5;
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridData.widthHint = labelWidth;
		label.setLayoutData(gridData);

		componentName = UiUtils.createGridText(nameComposite, 1, 0, isSaveAsAllowed() ? SWT.NONE : SWT.READ_ONLY, compoundModifyListener);

		UiUtils.createGridLabel(nameComposite, Messages.component_type_with_colon, 1, 0, SWT.NONE);
		componentType = UiUtils.createGridCombo(nameComposite, 1, 0, !isSaveAsAllowed(), null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);

		componentType.setItems(AbstractComponentType.getComponentTypeIDs(true));
		componentType.addModifyListener(compoundModifyListener);

		/*
		 * // not nice but I had to make equal 2 columns form different
		 * Composites // the purpose of hlpComposite is to create empty space,
		 * the same size // as componentCategory
		 * UiUtils.createEmptyPanel(nameComposite);
		 * 
		 * int textWidth = componentCategory.computeSize(SWT.DEFAULT,
		 * SWT.DEFAULT).x; gridData =
		 * (GridData)componentCategory.getLayoutData(); gridData.widthHint =
		 * textWidth; componentCategory.setLayoutData(gridData);
		 */
		Group versionGroup = new Group(tabComposite, SWT.NONE);
		versionGroup.setText(Messages.version);
		layout = new GridLayout(2, false);
		versionGroup.setLayout(layout);
		versionGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		Label versionLabel = UiUtils.createGridLabel(versionGroup, Messages.version_with_colon, 1, 0, SWT.NONE);
		gridData = (GridData) versionLabel.getLayoutData();
		gridData.widthHint = labelWidth - layout.marginWidth - 3;
		versionLabel.setLayoutData(gridData);

		versionString = UiUtils.createGridText(versionGroup, 1, 0, isSaveAsAllowed() ? SWT.NONE : SWT.READ_ONLY, compoundModifyListener);
		/*
		 * gridData = (GridData)versionString.getLayoutData();
		 * gridData.widthHint = textWidth;
		 * versionString.setLayoutData(gridData);
		 * 
		 * UiUtils.createEmptyPanel(versionGroup);
		 */
		UiUtils.createGridLabel(versionGroup, Messages.type_with_colon, 1, 0, SWT.NONE);
		versionType = UiUtils.createGridCombo(versionGroup, 1, 0, !isSaveAsAllowed(), null, null, SWT.DROP_DOWN | SWT.READ_ONLY | SWT.SIMPLE);

		List<VersionType> knownTypes = VersionHelper.getKnownTypes();
		int idx = knownTypes.size();
		String[] versionTypes = new String[idx];
		while (--idx >= 0)
			versionTypes[idx] = knownTypes.get(idx).getId();

		versionType.setItems(versionTypes);
		versionType.select(versionType.indexOf("OSGi")); //$NON-NLS-1$
		versionType.addModifyListener(compoundModifyListener);
		/*
		 * UiUtils.createEmptyPanel(versionGroup);
		 */
		return EditorUtils.getOptimizedControl(tabComposite);
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

	private void refreshValues() {
		setDirty(false);
		mute = true;
		try {
			componentName.setText(TextUtils.notNullString(cspec.getName()));
			componentType.select(componentType.indexOf(TextUtils.notNullString(cspec.getComponentTypeID())));
			Version version = cspec.getVersion();
			if (version == null) {
				versionString.setText(""); //$NON-NLS-1$
				versionType.select(versionType.indexOf(VersionType.OSGI));
			} else {
				versionString.setText(TextUtils.notNullString(VersionHelper.getHumanReadable(version)));
				versionType.select(versionType.indexOf(VersionHelper.getVersionType(version).getId()));
			}

			actionBuilders.clear();
			actionArtifactBuilders.clear();
			artifactBuilders.clear();
			groupBuilders.clear();
			Map<String, AttributeBuilder> attributesMap = cspec.getAttributes();
			if (attributesMap != null) {
				AttributeBuilder[] builders = attributesMap.values().toArray(new AttributeBuilder[0]);
				Arrays.sort(builders, CSpecEditorUtils.getAttributeComparator());
				List<ActionArtifactBuilder> tmp_actionArtifactBuilders = new ArrayList<ActionArtifactBuilder>();
				for (AttributeBuilder attribute : builders) {
					if (attribute instanceof ActionBuilder) {
						actionBuilders.add((ActionBuilder) attribute);
					} else if (attribute instanceof ActionArtifactBuilder) {
						tmp_actionArtifactBuilders.add((ActionArtifactBuilder) attribute);
					} else if (attribute instanceof ArtifactBuilder) {
						artifactBuilders.add((ArtifactBuilder) attribute);
					} else if (attribute instanceof GroupBuilder) {
						groupBuilders.add((GroupBuilder) attribute);
					}
				}
				for (ActionArtifactBuilder builder : tmp_actionArtifactBuilders) {
					addToActionArtifactBuilderMap(builder);
				}
			}

			dependencyBuilders.clear();
			Collection<ComponentRequestBuilder> dependencies = cspec.getDependencies();
			if (dependencies != null) {
				ComponentRequestBuilder[] builders = dependencies.toArray(new ComponentRequestBuilder[0]);
				Arrays.sort(builders, CSpecEditorUtils.getComponentComparator());
				for (ComponentRequestBuilder dependency : builders) {
					dependencyBuilders.add(dependency);
				}
			}

			generatorBuilders.clear();
			Collection<GeneratorBuilder> generatorsSet = cspec.getGeneratorList();
			if (generatorsSet != null) {
				GeneratorBuilder[] generators = generatorsSet.toArray(new GeneratorBuilder[0]);
				Arrays.sort(generators, CSpecEditorUtils.getCSpecElementComparator());
				for (GeneratorBuilder generator : generators) {
					generatorBuilders.add(generator);
				}
			}

			shortDesc.setText(TextUtils.notNullString(cspec.getShortDesc()));
			Documentation doc = cspec.getDocumentation();
			documentation.setText(TextUtils.notNullString(doc == null ? "" //$NON-NLS-1$
					: doc.toString()));

			xml.setText(getCSpecXML());

			actionsEditor.refresh();
			artifactsEditor.refresh();
			groupsEditor.refresh();
			dependenciesEditor.refresh();
			generatorsEditor.refresh();

			needsRefresh = false;
		} finally {
			mute = false;
		}
	}

	private void saveToPath(IPath path) {
		try {
			SaveRunnable sr = new SaveRunnable(cspec.createCSpec(), path);
			getSite().getWorkbenchWindow().run(true, true, sr);
			setInputWithNotify(sr.getSavedInput());
			setDirty(false);
			setPartName(path.lastSegment());
			firePropertyChange(IWorkbenchPart.PROP_TITLE);
		} catch (InvocationTargetException e) {
			CoreException t = BuckminsterException.wrap(e);
			String msg = NLS.bind(Messages.unable_to_save_file_0, path);
			CorePlugin.getLogger().error(t, msg);
			ErrorDialog.openError(getSite().getShell(), null, msg, t.getStatus());
		} catch (InterruptedException e) {
		}
	}

	private void setDirty(boolean flag) {
		if (readOnly || mute || hasChanges == flag)
			return;

		hasChanges = flag;
		firePropertyChange(PROP_DIRTY);
	}
}
