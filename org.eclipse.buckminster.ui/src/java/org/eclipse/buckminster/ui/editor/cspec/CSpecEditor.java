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
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.builder.CSpecBuilder;
import org.eclipse.buckminster.core.cspec.builder.DependencyBuilder;
import org.eclipse.buckminster.core.cspec.builder.GeneratorBuilder;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentCategory;
import org.eclipse.buckminster.core.cspec.model.DependencyAlreadyDefinedException;
import org.eclipse.buckminster.core.cspec.model.GeneratorAlreadyDefinedException;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.ui.general.editor.ITableModifyListener;
import org.eclipse.buckminster.ui.general.editor.TableEditor;
import org.eclipse.buckminster.ui.general.editor.TableModifyEvent;
import org.eclipse.buckminster.runtime.IOUtils;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.editor.EditorUtils;
import org.eclipse.buckminster.ui.editor.SaveRunnable;
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

	private CSpecBuilder m_cspec;
	
	private List<DependencyBuilder> m_dependencyBuilders = new ArrayList<DependencyBuilder>();
	private List<GeneratorBuilder> m_generatorBuilders = new ArrayList<GeneratorBuilder>();
	
	private CompoundModifyListener m_compoundModifyListener;
	
	private boolean m_hasChanges = false;	
	private boolean m_mute = false;	
	private boolean m_needsRefresh = false;
	
	private CTabFolder m_tabFolder;
	private Text m_componentName;
	private Combo m_componentCategory;
	private Text m_versionString;
	private Combo m_versionType;
	private TableEditor<DependencyBuilder> m_dependenciesEditor;
	private TableEditor<GeneratorBuilder> m_generatorsEditor;
	private Text m_shortDesc;
	private Text m_documentation;
	private Button m_externalSaveAsButton;
	
	public void doExternalSaveAs()
	{
		if(!commitChanges())
			return;
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setFilterExtensions(new String[] { "*.cquery" });
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
// TODO use it
/*		
		if(m_nodeEditMode)
		{
			if(!MessageDialog.openConfirm(getSite().getShell(), null, "Do you want to discard the current node edit?"))
				return false;
			cancelNode();
		}
*/
		String name = UiUtils.trimmedValue(m_componentName);
		if(name == null)
		{
			MessageDialog.openError(getSite().getShell(), null, "The component must have a name");
			return false;
		}
		m_cspec.setName(name);

		String category = m_componentCategory.getItem(m_componentCategory.getSelectionIndex());
		if(category.length() == 0)
			category = null;
		m_cspec.setCategory(category);
		
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

		return true;
	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException
	{
		if(!(input instanceof ILocationProvider || input instanceof IPathEditorInput))
			throw new PartInitException("Invalid Input");
		setSite(site);

		InputStream stream = null;
		try
		{
			IPath path = (input instanceof ILocationProvider)
					? ((ILocationProvider)input).getPath(input)
					: ((IPathEditorInput)input).getPath();

			File file = path.toFile();
			m_cspec = new CSpecBuilder();
			if(file.length() != 0)
			{
				String systemId = file.toString();
				stream = new FileInputStream(file);
				IParser<CSpec> parser = CorePlugin.getDefault().getParserFactory().getCSpecParser(true);
				m_cspec.initFrom(parser.parse(systemId, stream));
			}
			
			m_needsRefresh = true;
			if(m_componentName != null)
			{
				refreshValues();
			}
			
			setInputWithNotify(input);
			setPartName(input.getName());
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
			m_componentCategory.select(m_componentCategory.indexOf(TextUtils.notNullString(m_cspec.getCategory())));
			m_versionString.setText(TextUtils.notNullString(m_cspec.getVersion().toString()));
			m_versionType.select(m_versionType.indexOf(m_cspec.getVersion().getType().getId()));

			m_dependencyBuilders.clear();
			Map<String, DependencyBuilder> dependenciesMap = m_cspec.getDependencies();
			if(dependenciesMap != null)
			{
				for(DependencyBuilder dependency : dependenciesMap.values())
				{
					m_dependencyBuilders.add(dependency);
				}
			}
			
			m_generatorBuilders.clear();
			Map<String, GeneratorBuilder> generatorsMap = m_cspec.getGenerators();
			if(generatorsMap != null)
			{
				for(GeneratorBuilder generator : generatorsMap.values())
				{
					m_generatorBuilders.add(generator);
				}
			}
			
			m_shortDesc.setText(TextUtils.notNullString(m_cspec.getShortDesc()));
			Documentation doc = m_cspec.getDocumentation();
			m_documentation.setText(TextUtils.notNullString(doc == null
					? ""
					: doc.toString()));
			
			m_dependenciesEditor.refresh();
			m_generatorsEditor.refresh();

// TODO use it
/*		
			refreshList();
			nodeSelectionEvent();
*/
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
		return true;
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

		m_componentName = UiUtils.createGridText(nameComposite, 1, 0, m_compoundModifyListener, SWT.NONE);

		UiUtils.createGridLabel(nameComposite, "Category:", 1, 0, SWT.NONE);
		m_componentCategory = UiUtils.createGridCombo(nameComposite, 1, 0, null, null, SWT.DROP_DOWN | SWT.READ_ONLY
				| SWT.SIMPLE);

		m_componentCategory.setItems(ComponentCategory.getCategoryNames(true));
		m_componentCategory.addModifyListener(m_compoundModifyListener);

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

		m_versionString = UiUtils.createGridText(versionGroup, 1, 0, m_compoundModifyListener, SWT.NONE);
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
	private Control getDependenciesTabControl(Composite parent)
	{
		Composite tabComposite = EditorUtils.getNamedTabComposite(parent, "Dependencies");

		DependencyTable table = new DependencyTable(m_dependencyBuilders, m_cspec);
		table.addTableModifyListener(m_compoundModifyListener);
		
		m_dependenciesEditor = new TableEditor<DependencyBuilder>(
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

		GeneratorsTable table = new GeneratorsTable(m_generatorBuilders, m_cspec);
		table.addTableModifyListener(m_compoundModifyListener);
		
		m_generatorsEditor = new TableEditor<GeneratorBuilder>(
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
		m_shortDesc = UiUtils.createGridText(descComposite, 1, 0, m_compoundModifyListener, SWT.NONE);

		Label label = UiUtils.createGridLabel(descComposite, "Documentation:", 1, 0, SWT.NONE);
		label.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, false, false));
		m_documentation = UiUtils.createGridText(descComposite, 1, 0, m_compoundModifyListener, SWT.MULTI
				| SWT.V_SCROLL);
		m_documentation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		return tabComposite;
	}

	private void setDirty(boolean flag)
	{
		if(m_mute || m_hasChanges == flag)
			return;
		m_hasChanges = flag;
		m_externalSaveAsButton.setEnabled(flag);
		firePropertyChange(PROP_DIRTY);
	}

}
