/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.AttributeBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.CSpecElementBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.model.AttributeAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.GeneratorAlreadyDefinedException;
import org.eclipse.buckminster.core.ctype.AbstractComponentType;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.runtime.BuckminsterException;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.editor.SaveRunnable;
import org.eclipse.buckminster.ui.general.editor.ITableModifyListener;
import org.eclipse.buckminster.ui.general.editor.TableModifyEvent;
import org.eclipse.buckminster.ui.general.editor.simple.SimpleTableEditor;
import org.eclipse.buckminster.ui.general.editor.structured.OnePageTableEditor;
import org.eclipse.buckminster.ui.internal.CSpecEditorInput;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
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
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.editors.text.ILocationProvider;
import org.eclipse.ui.part.EditorPart;
import org.xml.sax.SAXException;

/**
 * @author Karel Brezina
 *
 */
public class CSpecEditor extends EditorPart
{
	@SuppressWarnings("unchecked") // don't need generics here - need just to setDirty
	class CompoundModifyListener implements ModifyListener, ITableModifyListener
	{

		public void modifyText(ModifyEvent e)
		{
			setDirty(true);
		}

		public void modifyTable(TableModifyEvent e)
		{
			setDirty(true);
		}
	}
	
	private static final String SAVEABLE_CSPEC_NAME = "buckminster.cspec";
	
	private static Comparator<CSpecElementBuilder> s_cspecElementComparator = CSpecEditorUtils.getCSpecElementComparator();

	private CSpecBuilder m_cspec;
	
	private List<ActionBuilder> m_actionBuilders = new ArrayList<ActionBuilder>();
	private List<ArtifactBuilder> m_artifactBuilders = new ArrayList<ArtifactBuilder>();
	private List<GroupBuilder> m_groupBuilders = new ArrayList<GroupBuilder>();
	private List<DependencyBuilder> m_dependencyBuilders = new ArrayList<DependencyBuilder>();
	private List<GeneratorBuilder> m_generatorBuilders = new ArrayList<GeneratorBuilder>();
	
	private CompoundModifyListener m_compoundModifyListener;
	
	private boolean m_hasChanges = false;	
	private boolean m_mute = false;	
	private boolean m_needsRefresh = false;
	private boolean m_readOnly = true;
	
	private CTabFolder m_tabFolder;
	private Text m_componentName;
	private Combo m_componentType;
	private Text m_versionString;
	private Combo m_versionType;
	private OnePageTableEditor<ActionBuilder> m_actionsEditor;
	private OnePageTableEditor<ArtifactBuilder> m_artifactsEditor;
	private OnePageTableEditor<GroupBuilder> m_groupsEditor;
	private SimpleTableEditor<DependencyBuilder> m_dependenciesEditor;
	private SimpleTableEditor<GeneratorBuilder> m_generatorsEditor;
	private Text m_shortDesc;
	private Text m_documentation;
	private Button m_externalSaveAsButton;
	
	public void doExternalSaveAs()
	{
		if(!commitChanges())
			return;
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.cspec" });
		final String location = dlg.open();
		if(location == null)
			return;
		saveToPath(new Path(location));
	}

	@Override
	public void doSave(IProgressMonitor monitor)
	{
		if(!commitChanges())
			return;

		IEditorInput input = getEditorInput();
		if(input == null)
			return;

		IPath path = (input instanceof ILocationProvider)
				? ((ILocationProvider)input).getPath(input)
				: ((IPathEditorInput)input).getPath();

		saveToPath(path);
	}

	@Override
	public void doSaveAs()
	{
		if(!commitChanges())
			return;

		IEditorInput input = getEditorInput();
		if(input == null)
			return;

		SaveAsDialog dialog = new SaveAsDialog(getSite().getShell());
		IFile original = (input instanceof IFileEditorInput)
				? ((IFileEditorInput)input).getFile()
				: null;
		if(original != null)
			dialog.setOriginalFile(original);

		if(dialog.open() == Window.CANCEL)
			return;

		IPath filePath = dialog.getResult();
		if(filePath == null)
			return;

		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFile(filePath);
		saveToPath(file.getLocation());
	}

	private void saveToPath(IPath path)
	{
		try
		{
			SaveRunnable sr = new SaveRunnable(m_cspec.createCSpec(), path);
			getSite().getWorkbenchWindow().run(true, true, sr);
			setInputWithNotify(sr.getSavedInput());
			setDirty(false);
			setPartName(path.lastSegment());
			firePropertyChange(IWorkbenchPart.PROP_TITLE);
		}
		catch(InvocationTargetException e)
		{
			CoreException t = BuckminsterException.wrap(e);
			String msg = "Unable to save file " + path;
			CorePlugin.getLogger().error(msg, t);
			ErrorDialog.openError(getSite().getShell(), null, msg, t.getStatus());
		}
		catch(CoreException e)
		{
			String msg = "Unable to save file " + path;
			CorePlugin.getLogger().error(msg, e);
			ErrorDialog.openError(getSite().getShell(), null, msg, e.getStatus());
		}
		catch(InterruptedException e)
		{
		}
	}

	private boolean commitChanges()
	{
		if(m_actionsEditor.isInEditMode())
		{
			if(!MessageDialog.openConfirm(getSite().getShell(), null, "Do you want to discard the current action edit?"))
				return false;
			m_actionsEditor.cancelRow();
		}

		if(m_artifactsEditor.isInEditMode())
		{
			if(!MessageDialog.openConfirm(getSite().getShell(), null, "Do you want to discard the current artifact edit?"))
				return false;
			m_artifactsEditor.cancelRow();
		}

		if(m_groupsEditor.isInEditMode())
		{
			if(!MessageDialog.openConfirm(getSite().getShell(), null, "Do you want to discard the current group edit?"))
				return false;
			m_groupsEditor.cancelRow();
		}

		String name = UiUtils.trimmedValue(m_componentName);
		if(name == null)
		{
			MessageDialog.openError(getSite().getShell(), null, "The component must have a name");
			return false;
		}
		m_cspec.setName(name);

		String componentType = m_componentType.getItem(m_componentType.getSelectionIndex());
		if(componentType.length() == 0)
			componentType = null;
		m_cspec.setComponentTypeID(componentType);
		
		try
		{
			m_cspec.setVersion(UiUtils.trimmedValue(m_versionString), m_versionType.getItem(m_versionType.getSelectionIndex()));
		}
		catch(CoreException e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		try
		{
			Map<String, AttributeBuilder> attributesMap = m_cspec.getAttributes();
			
			if(attributesMap != null)
			{
				attributesMap.clear();
			}
			
			for(ActionBuilder action : m_actionBuilders)
			{
				m_cspec.addAttribute(action);
			}

			for(ArtifactBuilder artifact : m_artifactBuilders)
			{
				m_cspec.addAttribute(artifact);
			}

			for(GroupBuilder group : m_groupBuilders)
			{
				m_cspec.addAttribute(group);
			}
		}
		catch(AttributeAlreadyDefinedException e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}
		
		try
		{
			Map<String, DependencyBuilder> dependeciesMap = m_cspec.getDependencies();
			
			if(dependeciesMap != null)
			{
				dependeciesMap.clear();
			}
			
			for(DependencyBuilder dependency : m_dependencyBuilders)
			{
				m_cspec.addDependency(dependency);
			}
		}
		catch(DependencyAlreadyDefinedException e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}
		
		try
		{
			Map<String, GeneratorBuilder> generatorsMap = m_cspec.getGenerators();
			
			if(generatorsMap != null)
			{
				generatorsMap.clear();
			}

			for(GeneratorBuilder generator : m_generatorBuilders)
			{
				m_cspec.addGenerator(generator);
			}
		}
		catch(GeneratorAlreadyDefinedException e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}
		
		String doc = UiUtils.trimmedValue(m_shortDesc);
		m_cspec.setShortDesc(doc);

		doc = UiUtils.trimmedValue(m_documentation);
		try
		{
			m_cspec.setDocumentation(doc == null ? null : Documentation.parse(doc));
		}
		catch(CoreException e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}

		try
		{
			m_cspec.createCSpec().verifyConsistency();
		}
		catch(CoreException e)
		{
			MessageDialog.openError(getSite().getShell(), null, e.getMessage());
			return false;
		}
		
		return true;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException
	{
		if(!(input instanceof ILocationProvider || input instanceof IPathEditorInput || input instanceof IURIEditorInput || input instanceof CSpecEditorInput))
			throw new PartInitException("Invalid Input");
		setSite(site);

		if(input instanceof IURIEditorInput)
		{
			try
			{
				input = EditorUtils.getExternalFileEditorInput((IURIEditorInput)input, ArtifactType.CSPEC);
			}
			catch(IOException e)
			{
				UiUtils.openError(null, "Unable to open editor", e);
			}
		}

		InputStream stream = null;
		try
		{
			m_cspec = new CSpecBuilder();
			if(input instanceof CSpecEditorInput)
			{
				m_readOnly = true;
				m_cspec.initFrom(((CSpecEditorInput)input).getCSpec());
			}
			else
			{
				IPath path = (input instanceof ILocationProvider)
						? ((ILocationProvider)input).getPath(input)
						: ((IPathEditorInput)input).getPath();
	
				m_readOnly = (! SAVEABLE_CSPEC_NAME.equalsIgnoreCase(path.lastSegment()));
						
				File file = path.toFile();
				if(file.length() != 0)
				{
					String systemId = file.toString();
					stream = new FileInputStream(file);
					IParser<CSpec> parser = CorePlugin.getDefault().getParserFactory().getCSpecParser(true);
					m_cspec.initFrom(parser.parse(systemId, stream));
				}
			}

			m_needsRefresh = true;
			if(m_componentName != null)
			{
				refreshValues();
			}
			
			setInputWithNotify(input);
			setPartName(input.getName() + (m_readOnly ? " (read only)" : ""));
		}
		catch(SAXException e)
		{
			throw new PartInitException(BuckminsterException.wrap(e).getMessage());
		}
		catch(FileNotFoundException e)
		{
			throw new PartInitException(e.getMessage());
		}
		finally
		{
			IOUtils.close(stream);
		}

		m_compoundModifyListener = new CompoundModifyListener();
	}

	private void refreshValues()
	{
		setDirty(false);
		m_mute = true;
		try
		{
			m_componentName.setText(TextUtils.notNullString(m_cspec.getName()));
			m_componentType.select(m_componentType.indexOf(TextUtils.notNullString(m_cspec.getComponentTypeID())));
			m_versionString.setText(TextUtils.notNullString(m_cspec.getVersion().toString()));
			m_versionType.select(m_versionType.indexOf(m_cspec.getVersion().getType().getId()));

			m_actionBuilders.clear();
			m_artifactBuilders.clear();
			m_groupBuilders.clear();
			Map<String, AttributeBuilder> attributesMap = m_cspec.getAttributes();
			if(attributesMap != null)
			{
				AttributeBuilder[] builders = attributesMap.values().toArray(new AttributeBuilder[0]);
				Arrays.sort(builders, s_cspecElementComparator);
				for(AttributeBuilder attribute : builders)
				{
					if(attribute instanceof ActionBuilder)
					{
						m_actionBuilders.add((ActionBuilder) attribute);
					} else if(attribute instanceof ArtifactBuilder)
					{
						m_artifactBuilders.add((ArtifactBuilder) attribute);
					} else if(attribute instanceof GroupBuilder)
					{
						m_groupBuilders.add((GroupBuilder) attribute);
					}
				}
			}
			
			m_dependencyBuilders.clear();
			Map<String, DependencyBuilder> dependenciesMap = m_cspec.getDependencies();
			if(dependenciesMap != null)
			{
				DependencyBuilder[] builders = dependenciesMap.values().toArray(new DependencyBuilder[0]);
				Arrays.sort(builders, s_cspecElementComparator);
				for(DependencyBuilder dependency : builders)
				{
					m_dependencyBuilders.add(dependency);
				}
			}
			
			m_generatorBuilders.clear();
			Map<String, GeneratorBuilder> generatorsMap = m_cspec.getGenerators();
			if(generatorsMap != null)
			{
				GeneratorBuilder[] generators = generatorsMap.values().toArray(new GeneratorBuilder[0]);
				Arrays.sort(generators, s_cspecElementComparator);
				for(GeneratorBuilder generator : generators)
				{
					m_generatorBuilders.add(generator);
				}
			}
			
			m_shortDesc.setText(TextUtils.notNullString(m_cspec.getShortDesc()));
			Documentation doc = m_cspec.getDocumentation();
			m_documentation.setText(TextUtils.notNullString(doc == null
					? ""
					: doc.toString()));
			
			m_actionsEditor.refresh();
			m_artifactsEditor.refresh();
			m_groupsEditor.refresh();
			m_dependenciesEditor.refresh();
			m_generatorsEditor.refresh();

			m_needsRefresh = false;
		}
		finally
		{
			m_mute = false;
		}
	}

	@Override
	public boolean isDirty()
	{
		return m_hasChanges;
	}

	@Override
	public boolean isSaveAsAllowed()
	{
		return ! m_readOnly;
	}

	@Override
	public void createPartControl(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, true);
		layout.marginHeight = layout.marginWidth = 0;
		topComposite.setLayout(layout);

		m_tabFolder = new CTabFolder(topComposite, SWT.BOTTOM);
		m_tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		CTabItem mainTab = new CTabItem(m_tabFolder, SWT.NONE);
		mainTab.setText("Main");
		mainTab.setControl(getMainTabControl(m_tabFolder));

		CTabItem actionsTab = new CTabItem(m_tabFolder, SWT.NONE);
		actionsTab.setText("Actions");
		actionsTab.setControl(getActionsTabControl(m_tabFolder));

		CTabItem artifactsTab = new CTabItem(m_tabFolder, SWT.NONE);
		artifactsTab.setText("Artifacts");
		artifactsTab.setControl(getArtifactsTabControl(m_tabFolder));

		CTabItem groupsTab = new CTabItem(m_tabFolder, SWT.NONE);
		groupsTab.setText("Groups");
		groupsTab.setControl(getGroupsTabControl(m_tabFolder));

		CTabItem dependenciesTab = new CTabItem(m_tabFolder, SWT.NONE);
		dependenciesTab.setText("Dependencies");
		dependenciesTab.setControl(getDependenciesTabControl(m_tabFolder));

		CTabItem generatorsTab = new CTabItem(m_tabFolder, SWT.NONE);
		generatorsTab.setText("Generators");
		generatorsTab.setControl(getGeneratorsTabControl(m_tabFolder));

		CTabItem documentationTab = new CTabItem(m_tabFolder, SWT.NONE);
		documentationTab.setText("Documentation");
		documentationTab.setControl(getDocumentationTabControl(m_tabFolder));
		
		createActionButtons(topComposite);
	}

	private void createActionButtons(Composite parent)
	{
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

		m_externalSaveAsButton = UiUtils.createPushButton(pressButtonsBox, "External Save As", new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				doExternalSaveAs();
			}
		});
		m_externalSaveAsButton.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		m_externalSaveAsButton.setEnabled(false);
	}

	@Override
	public void setFocus()
	{
		m_tabFolder.setFocus();

		if(m_needsRefresh)
		{
			refreshValues();
		}
	}

	private Control getMainTabControl(CTabFolder parent)
	{
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, "Main");

		Composite nameComposite = new Composite(tabComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginRight = 8;
		layout.marginHeight = layout.marginWidth = 0;
		nameComposite.setLayout(layout);
		nameComposite.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		Label label = UiUtils.createGridLabel(nameComposite, "Component name:", 1, 0, SWT.NONE);
		int labelWidth = label.computeSize(SWT.DEFAULT, SWT.DEFAULT).x + 5;
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, false, false);
		gridData.widthHint = labelWidth;
		label.setLayoutData(gridData);

		m_componentName = UiUtils.createGridText(nameComposite, 1, 0, SWT.NONE, m_compoundModifyListener);

		UiUtils.createGridLabel(nameComposite, "Component Type:", 1, 0, SWT.NONE);
		m_componentType = UiUtils.createGridCombo(nameComposite, 1, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);

		m_componentType.setItems(AbstractComponentType.getComponentTypeIDs(true));
		m_componentType.addModifyListener(m_compoundModifyListener);

/*
		// not nice but I had to make equal 2 columns form different Composites
		// the purpose of hlpComposite is to create empty space, the same size
		// as m_componentCategory
		UiUtils.createEmptyPanel(nameComposite);

		int textWidth = m_componentCategory.computeSize(SWT.DEFAULT, SWT.DEFAULT).x;
		gridData = (GridData)m_componentCategory.getLayoutData();
		gridData.widthHint = textWidth;
		m_componentCategory.setLayoutData(gridData);
*/
		Group versionGroup = new Group(tabComposite, SWT.NONE);
		versionGroup.setText("Version");
		layout = new GridLayout(2, false);
		versionGroup.setLayout(layout);
		versionGroup.setLayoutData(new GridData(GridData.FILL, GridData.FILL, true, false));

		Label versionLabel = UiUtils.createGridLabel(versionGroup, "Version:", 1, 0, SWT.NONE);
		gridData = (GridData)versionLabel.getLayoutData();
		gridData.widthHint = labelWidth - layout.marginWidth - 3;
		versionLabel.setLayoutData(gridData);

		m_versionString = UiUtils.createGridText(versionGroup, 1, 0, SWT.NONE, m_compoundModifyListener);
/*		
		gridData = (GridData)m_versionString.getLayoutData();
		gridData.widthHint = textWidth;
		m_versionString.setLayoutData(gridData);

		UiUtils.createEmptyPanel(versionGroup);
*/
		UiUtils.createGridLabel(versionGroup, "Type:", 1, 0, SWT.NONE);
		m_versionType = UiUtils.createGridCombo(versionGroup, 1, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);

		String[] versionTypes = CorePlugin.getDefault().getExtensionIds(CorePlugin.VERSION_TYPES_POINT);

		m_versionType.setItems(versionTypes);
		m_versionType.select(m_versionType.indexOf("OSGi"));
		m_versionType.addModifyListener(m_compoundModifyListener);
/*
		UiUtils.createEmptyPanel(versionGroup);
*/
		return tabComposite;
	}
	
	@SuppressWarnings("unchecked")
	private Control getActionsTabControl(Composite parent)
	{
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, "Actions");

		ActionsTable table = new ActionsTable(this, m_actionBuilders, m_cspec);
		table.addTableModifyListener(m_compoundModifyListener);
		
		m_actionsEditor = new OnePageTableEditor<ActionBuilder>(
				tabComposite,
				table,
				false,
				SWT.NONE);

		return tabComposite;
	}

	@SuppressWarnings("unchecked")
	private Control getArtifactsTabControl(Composite parent)
	{
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, "Artifacts");

		ArtifactsTable table = new ArtifactsTable(this, m_artifactBuilders, m_cspec);
		table.addTableModifyListener(m_compoundModifyListener);
		
		m_artifactsEditor = new OnePageTableEditor<ArtifactBuilder>(
				tabComposite,
				table,
				false,
				SWT.NONE);

		return tabComposite;
	}

	@SuppressWarnings("unchecked")
	private Control getGroupsTabControl(Composite parent)
	{
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, "Groups");

		GroupsTable table = new GroupsTable(this, m_groupBuilders, m_cspec);
		table.addTableModifyListener(m_compoundModifyListener);
		
		m_groupsEditor = new OnePageTableEditor<GroupBuilder>(
				tabComposite,
				table,
				false,
				SWT.NONE);

		return tabComposite;
	}

	@SuppressWarnings("unchecked")
	private Control getDependenciesTabControl(Composite parent)
	{
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, "Dependencies");

		DependenciesTable table = new DependenciesTable(m_dependencyBuilders, m_cspec);
		table.addTableModifyListener(m_compoundModifyListener);
		
		m_dependenciesEditor = new SimpleTableEditor<DependencyBuilder>(
				tabComposite,
				table,
				null,
				"CSpec Editor - Dependency",
				null,
				null,
				SWT.NONE);

		return tabComposite;
	}

	@SuppressWarnings("unchecked")
	private Control getGeneratorsTabControl(Composite parent)
	{
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, "Generators");

		GeneratorsTable table = new GeneratorsTable(this, m_generatorBuilders, m_cspec);
		table.addTableModifyListener(m_compoundModifyListener);
		
		m_generatorsEditor = new SimpleTableEditor<GeneratorBuilder>(
				tabComposite,
				table,
				null,
				"CSpec Editor - Generator",
				null,
				null,
				SWT.NONE);

		return tabComposite;
	}

	private Control getDocumentationTabControl(Composite parent)
	{
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, "Documentation");

		Composite descComposite = new Composite(tabComposite, SWT.NONE);
		GridLayout layout = new GridLayout(2, false);
		layout.marginHeight = layout.marginWidth = 0;
		descComposite.setLayout(layout);
		descComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		UiUtils.createGridLabel(descComposite, "Short Description:", 1, 0, SWT.NONE);
		m_shortDesc = UiUtils.createGridText(descComposite, 1, 0, SWT.NONE, m_compoundModifyListener);

		Label label = UiUtils.createGridLabel(descComposite, "Documentation:", 1, 0, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
		m_documentation = UiUtils.createGridText(descComposite, 1, 0, SWT.MULTI
				| SWT.V_SCROLL, m_compoundModifyListener);
		m_documentation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		return tabComposite;
	}

	private void setDirty(boolean flag)
	{
		if(m_readOnly || m_mute || m_hasChanges == flag)
			return;
		
		m_hasChanges = flag;
		m_externalSaveAsButton.setEnabled(flag);
		firePropertyChange(PROP_DIRTY);
	}
	
	List<ActionBuilder> getActionBuilders()
	{
		return m_actionBuilders;
	}
	
	List<ArtifactBuilder> getArtifactBuilders()
	{
		return m_artifactBuilders;
	}
	
	List<GroupBuilder> getGroupBuilders()
	{
		return m_groupBuilders;
	}
	
	List<DependencyBuilder> getDependencyBuilders()
	{
		return m_dependencyBuilders;
	}
	
	List<GeneratorBuilder> getGeneratorBuilders()
	{
		return m_generatorBuilders;
	}
	
	String[] getAttributeNames(String excludeName)
	{
		List<String> list = new ArrayList<String>();
		
		for(ActionBuilder builder : m_actionBuilders)
		{
			if(builder.getName() != null)
			{
				list.add(builder.getName());
			}
		}
		for(ArtifactBuilder builder : m_artifactBuilders)
		{
			if(builder.getName() != null)
			{
				list.add(builder.getName());
			}
		}
		for(GroupBuilder builder : m_groupBuilders)
		{
			if(builder.getName() != null)
			{
				list.add(builder.getName());
			}
		}
		
		if(excludeName != null)
		{
			list.remove(excludeName);
		}
		
		String[] array = list.toArray(new String[0]);
		Arrays.sort(array, new Comparator<String>(){

			public int compare(String o1, String o2)
			{
				return o1.compareTo(o2);
			}});
		
		return array;
	}
	
	String[] getComponentNames()
	{
		List<String> list = new ArrayList<String>();
		
		for(DependencyBuilder builder : m_dependencyBuilders)
		{
			if(builder.getName() != null)
			{
				list.add(builder.getName());
			}
		}
		
		String[] array = list.toArray(new String[0]);
		Arrays.sort(array, new Comparator<String>(){

			public int compare(String o1, String o2)
			{
				return o1.compareTo(o2);
			}});
		
		return array;
	}
}
