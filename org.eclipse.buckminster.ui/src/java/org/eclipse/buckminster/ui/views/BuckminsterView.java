/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.views;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.query.builder.ComponentQueryBuilder;
import org.eclipse.buckminster.core.query.model.ComponentQuery;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.buckminster.ui.actions.BlankQueryAction;
import org.eclipse.buckminster.ui.actions.InvokeAction;
import org.eclipse.buckminster.ui.actions.OpenQueryAction;
import org.eclipse.buckminster.ui.actions.ViewCSpecAction;
import org.eclipse.buckminster.ui.actions.ViewChosenCSpecAction;
import org.eclipse.buckminster.ui.dialogs.AboutDialog;
import org.eclipse.buckminster.ui.internal.ResolveJob;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.OpenWithMenu;
import org.eclipse.ui.dialogs.PreferencesUtil;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ViewPart;

/**
 * @author kaja
 * 
 */
public class BuckminsterView extends ViewPart
{

	class ActionsNode extends TreeNode
	{

		private CSpec m_cspec;

		public ActionsNode(CSpec cspec)
		{
			m_cspec = cspec;
		}

		@Override
		public TreeNode[] getChildren()
		{
			try
			{
				/* List<Attribute> viableAttributes = */m_cspec.getAttributesProducedByActions(false);
			}
			catch(CoreException e)
			{
				UiUtils.openError(getViewSite().getShell(), Messages.unable_to_get_child_nodes, e);
			}
			return null;
		}

		@Override
		public String getName()
		{
			return Messages.actions;
		}

		@Override
		public Object getValue()
		{
			return m_cspec;
		}
	}

	// TODO use extension point to get files for BM view
	class BuckminsterFileFilter
	{
		public static final String CONTENT_TYPES_POINT = "org.eclipse.core.runtime.contentTypes"; //$NON-NLS-1$

		public static final String BUCKMINSTER_CORE_PLUGIN = "org.eclipse.buckminster.core"; //$NON-NLS-1$

		private List<String> m_fileExts = new ArrayList<String>();

		public BuckminsterFileFilter()
		{
			IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(
					BuckminsterFileFilter.CONTENT_TYPES_POINT);

			for(IConfigurationElement elem : elems)
			{
				if(BuckminsterFileFilter.BUCKMINSTER_CORE_PLUGIN.equals(elem.getContributor().getName()))
				{
					String[] fileExt = elem.getAttribute("file-extensions").split(","); //$NON-NLS-1$ //$NON-NLS-2$
					m_fileExts.addAll(Arrays.asList(fileExt));
				}
			}

			// TODO remove
			m_fileExts.add("cquery"); //$NON-NLS-1$
		}

		public boolean matchesFile(IFile member)
		{
			for(String fileExt : m_fileExts)
			{
				if(member.getName().endsWith(fileExt))
				{
					return true;
				}
			}

			return false;
		}
	}

	static class BuckminsterPreferences
	{
		final private static String BUCKMINSTER_PLUGIN_PREFIX = "org.eclipse.buckminster"; //$NON-NLS-1$

		final private static String PREFERENCES_POINT = "org.eclipse.ui.preferencePages"; //$NON-NLS-1$

		private static List<String> s_preferenceIds;

		static
		{
			s_preferenceIds = new ArrayList<String>();

			IConfigurationElement[] elems = Platform.getExtensionRegistry().getConfigurationElementsFor(
					BuckminsterPreferences.PREFERENCES_POINT);

			for(IConfigurationElement elem : elems)
			{
				String contributor = elem.getContributor().getName();
				if(contributor.startsWith(BuckminsterPreferences.BUCKMINSTER_PLUGIN_PREFIX))
				{
					s_preferenceIds.add(elem.getAttribute("id")); //$NON-NLS-1$
				}
			}
		}

		public static String[] getIds()
		{
			return s_preferenceIds.toArray(new String[0]);
		}
	}

	class CqueryNode extends TreeNode
	{
		private IFile m_cqueryFile;

		public CqueryNode(IFile cqueryFile)
		{
			m_cqueryFile = cqueryFile;
		}

		@Override
		public String getName()
		{
			return Messages.component_query;
		}

		@Override
		public Object getValue()
		{
			return m_cqueryFile;
		}
	}

	class CspecNode extends TreeNode
	{

		private ICSpecData m_cspec;

		public CspecNode(ICSpecData cspec)
		{
			m_cspec = cspec;
		}

		@Override
		public String getName()
		{
			return Messages.component_specification;
		}

		@Override
		public Object getValue()
		{
			return m_cspec;
		}
	}

	class NavigatorContentProvider implements ITreeContentProvider
	{

		public void dispose()
		{
		}

		public Object[] getChildren(Object parentElement)
		{
			if(parentElement instanceof IContainer)
			{
				try
				{
					List<IResource> filteredMembers = new ArrayList<IResource>();
					IResource[] members = ((IContainer)parentElement).members();

					for(IResource member : members)
					{
						if(member instanceof IFile && !m_fileFilter.matchesFile((IFile)member))
						{
							continue;
						}

						filteredMembers.add(member);
					}

					return filteredMembers.toArray();
				}
				catch(CoreException e)
				{
					UiUtils.openError(getViewSite().getShell(), Messages.unable_to_get_child_nodes, e);
				}
			}
			return null;
		}

		public Object[] getElements(Object inputElement)
		{
			return ((IWorkspaceRoot)inputElement).getProjects();
		}

		public Object getParent(Object element)
		{
			return ((IResource)element).getParent();
		}

		public boolean hasChildren(Object element)
		{
			Object[] children = getChildren(element);
			return children == null
					? false
					: children.length > 0;
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{
			// Nothing to change
		}

	}

	class NavigatorLabelProvider implements ILabelProvider
	{

		private List<ILabelProviderListener> m_listeners = new ArrayList<ILabelProviderListener>();

		private Image m_projectImage;

		private Image m_folderImage;

		private Image m_fileImage;

		public NavigatorLabelProvider()
		{
			m_projectImage = UiPlugin.getImageDescriptor("icons/prj_obj.gif").createImage(); //$NON-NLS-1$
			m_folderImage = UiPlugin.getImageDescriptor("icons/fldr_obj.gif").createImage(); //$NON-NLS-1$
			m_fileImage = UiPlugin.getImageDescriptor("icons/file_obj.gif").createImage(); //$NON-NLS-1$
		}

		public void addListener(ILabelProviderListener listener)
		{
			m_listeners.add(listener);
		}

		public void dispose()
		{
		}

		public Image getImage(Object element)
		{
			if(element instanceof IProject)
			{
				return m_projectImage;
			}

			if(element instanceof IFolder)
			{
				return m_folderImage;
			}

			if(element instanceof IFile)
			{
				IFile file = (IFile)element;

				IEditorRegistry editorRegistry = getWorkbenchWindow().getWorkbench().getEditorRegistry();

				ImageDescriptor imageDescriptor = editorRegistry.getImageDescriptor(file.getName());

				return imageDescriptor == null
						? m_fileImage
						: new Image(Display.getDefault(), imageDescriptor.getImageData());
			}

			return null;
		}

		public String getText(Object element)
		{
			return ((IResource)element).getName();
		}

		public boolean isLabelProperty(Object element, String property)
		{
			return false;
		}

		public void removeListener(ILabelProviderListener listener)
		{
			m_listeners.remove(listener);
		}
	}

	class ProjectNode extends TreeNode
	{

		private IProject m_project;

		public ProjectNode(IProject project)
		{
			m_project = project;
		}

		@Override
		public TreeNode[] getChildren()
		{
			List<TreeNode> children = new ArrayList<TreeNode>();

			try
			{
				CSpec cspec = WorkspaceInfo.getCSpec(m_project);
				cspec.getAttributesProducedByActions(false);
				TreeNode child = new CspecNode(cspec);
				child.setParent(this);
				children.add(child);

				for(IResource resource : m_project.members())
				{
					if(resource instanceof IFile && resource.getName().endsWith("cquery")) //$NON-NLS-1$
					{
						child = new CqueryNode((IFile)resource);
						child.setParent(this);
						children.add(child);
						break;
					}
				}

				if(cspec.getAttributesProducedByActions(false).size() > 0)
				{
					child = new ActionsNode(cspec);
					child.setParent(this);
					children.add(child);
				}
			}
			catch(CoreException e)
			{
				UiUtils.openError(getViewSite().getShell(), Messages.unable_to_get_child_nodes, e);
			}

			return children.toArray(new TreeNode[0]);
		}

		@Override
		public String getName()
		{
			return m_project.getName();
		}

		@Override
		public Object getValue()
		{
			return m_project;
		}
	}

	class RootNode extends TreeNode
	{

		@Override
		public TreeNode[] getChildren()
		{
			IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			List<TreeNode> children = new ArrayList<TreeNode>();

			for(IProject project : projects)
			{
				TreeNode child = new ProjectNode(project);
				child.setParent(this);
				children.add(child);
			}

			return children.toArray(new TreeNode[0]);
		}
	}

	abstract class TreeNode
	{
		private TreeNode m_parent = null;

		public TreeNode[] getChildren()
		{
			return null;
		}

		public String getName()
		{
			return ""; //$NON-NLS-1$
		}

		public TreeNode getParent()
		{
			return m_parent;
		}

		public Object getValue()
		{
			return null;
		}

		public boolean hasChildren()
		{
			TreeNode[] children = getChildren();
			return children == null
					? false
					: getChildren().length > 0;
		}

		public void setParent(TreeNode parent)
		{
			m_parent = parent;
		}
	}

	private CTabFolder m_tabFolder;

	private TreeViewer m_treeViewer;

	private Text m_infoText;

	private IWorkspaceRoot m_workspaceRoot;

	private BuckminsterFileFilter m_fileFilter;

	private Image m_bmImage;

	private IAction m_openEditorAction;

	private IAction m_viewCSpecAction;

	private IAction m_openCQueryAction;

	private IAction m_viewPreferencesAction;

	private IAction m_viewAboutAction;

	private IAction m_invokeActionAction;

	private IAction m_viewCspecAction;

	private IAction m_publishAction;

	private IAction m_resolveToWizardAction;

	private IAction m_resolveAndMaterializeAction;

	public BuckminsterView()
	{
		m_workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
		m_workspaceRoot.getWorkspace().addResourceChangeListener(new IResourceChangeListener()
		{

			public void resourceChanged(IResourceChangeEvent event)
			{
				refreshTree();
			}
		});

		m_fileFilter = new BuckminsterFileFilter();
		m_bmImage = UiPlugin.getImageDescriptor("images/buckminster_logo.png").createImage(); //$NON-NLS-1$
	}

	@Override
	public void createPartControl(Composite parent)
	{
		Composite topComposite = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = layout.marginWidth = 0;
		topComposite.setLayout(layout);
		topComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		createActions();
		createMenu();
		createToolbar();

		m_tabFolder = new CTabFolder(topComposite, SWT.BOTTOM);
		m_tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		CTabItem navigatorTab = new CTabItem(m_tabFolder, SWT.NONE);
		navigatorTab.setText(Messages.navigator);
		navigatorTab.setControl(getNavigatorTabControl(m_tabFolder));

		CTabItem repositoryTab = new CTabItem(m_tabFolder, SWT.NONE);
		repositoryTab.setText(Messages.repository);
		repositoryTab.setControl(getRepositoryTabControl(m_tabFolder));

		m_tabFolder.setSelection(navigatorTab);

		Label imageLabel = new Label(topComposite, SWT.NONE);
		imageLabel.setAlignment(SWT.CENTER);
		imageLabel.setImage(m_bmImage);
		imageLabel.setLayoutData(new GridData(SWT.BEGINNING, SWT.BOTTOM, false, false));

		createContextMenu();
	}

	@Override
	public void setFocus()
	{
		m_tabFolder.setFocus();
	}

	private void changeInfo()
	{
		IResource resource = getResourceSelection();

		String info = ""; //$NON-NLS-1$

		if(resource != null)
		{
			info = getProjectInfo(resource.getProject());
		}

		m_infoText.setText(info);
	}

	// TODO Most of the actions should come here from extension point
	private void createActions()
	{
		m_openEditorAction = new Action(Messages.open)
		{
			@Override
			public void run()
			{
				IResource resource = getResourceSelection();

				if(resource != null && resource instanceof IFile)
				{
					IFile file = (IFile)resource;
					IWorkbenchPage workbenchPage = getSite().getWorkbenchWindow().getActivePage();
					try
					{
						IEditorRegistry editorRegistry = getViewSite().getWorkbenchWindow().getWorkbench()
								.getEditorRegistry();

						IEditorDescriptor editorDescriptor = editorRegistry.getDefaultEditor(file.getName());
						String editorId = editorDescriptor.getId();
						if(editorId != null)
						{
							IDE.openEditor(workbenchPage, new FileEditorInput(file), editorId);
						}
					}
					catch(PartInitException e)
					{
						UiUtils.openError(getViewSite().getShell(), Messages.unable_to_open_editor, e);
					}
				}

			}
		};

		m_viewCSpecAction = new Action(Messages.view_the_cspec_of_a_component)
		{
			@Override
			public void run()
			{
				IWorkbenchWindowActionDelegate action = new ViewChosenCSpecAction();
				action.init(getWorkbenchWindow());
				action.selectionChanged(this, m_treeViewer.getSelection());
				action.run(this);
				action.dispose();
			}
		};
		m_viewCSpecAction.setImageDescriptor(UiPlugin.getImageDescriptor("icons/cspec.png")); //$NON-NLS-1$

		m_openCQueryAction = new Action(Messages.open_a_component_query)
		{
			@Override
			public void run()
			{
				IWorkbenchWindowActionDelegate action = new OpenQueryAction();
				action.init(getWorkbenchWindow());
				action.selectionChanged(this, m_treeViewer.getSelection());
				action.run(this);
				action.dispose();
			}
		};
		m_openCQueryAction.setImageDescriptor(UiPlugin.getImageDescriptor("icons/cquery.png")); //$NON-NLS-1$

		m_viewPreferencesAction = new Action(Messages.preferences)
		{
			@Override
			public void run()
			{
				PreferenceDialog dialog = PreferencesUtil.createPreferenceDialogOn(getWorkbenchWindow().getShell(),
						null, BuckminsterPreferences.getIds(), null);
				dialog.open();
			}
		};

		m_viewAboutAction = new Action(Messages.about)
		{
			@Override
			public void run()
			{
				AboutDialog dialog = new AboutDialog(getWorkbenchWindow().getShell());
				dialog.open();
			}
		};

		m_invokeActionAction = new Action(Messages.invoke_action)
		{
			@Override
			public void run()
			{
				IObjectActionDelegate action = new InvokeAction();
				action.setActivePart(this, getWorkbenchWindow().getActivePage().getActivePart());
				action.selectionChanged(this, m_treeViewer.getSelection());
				action.run(this);
			}
		};

		m_viewCspecAction = new Action(Messages.view_cspec)
		{
			@Override
			public ImageDescriptor getImageDescriptor()
			{
				return UiPlugin.getImageDescriptor("icons/cspec.png"); //$NON-NLS-1$
			}

			@Override
			public void run()
			{
				IObjectActionDelegate action = new ViewCSpecAction();
				action.setActivePart(this, getWorkbenchWindow().getActivePage().getActivePart());
				action.selectionChanged(this, m_treeViewer.getSelection());
				action.run(this);
			}
		};

		m_publishAction = new Action(Messages.publish)
		{
			@Override
			public ImageDescriptor getImageDescriptor()
			{
				return UiPlugin.getImageDescriptor("icons/publish.png"); //$NON-NLS-1$
			}

			@Override
			public void run()
			{
				// TODO implement
			}
		};

		m_resolveToWizardAction = new Action(Messages.resolve_to_wizard)
		{
			@Override
			public ImageDescriptor getImageDescriptor()
			{
				return UiPlugin.getImageDescriptor("icons/resolve.png"); //$NON-NLS-1$
			}

			@Override
			public void run()
			{
				loadComponent(false);
			}
		};

		m_resolveAndMaterializeAction = new Action(Messages.resolve_and_materialize)
		{
			@Override
			public ImageDescriptor getImageDescriptor()
			{
				return UiPlugin.getImageDescriptor("icons/resolve.png"); //$NON-NLS-1$
			}

			@Override
			public void run()
			{
				loadComponent(true);
			}
		};
	}

	private void createContextMenu()
	{
		// Create menu manager
		MenuManager menuMgr = new MenuManager();
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener()
		{

			public void menuAboutToShow(IMenuManager manager)
			{
				fillContextMenu(manager);
			}

		});

		// Create menu
		Menu menu = menuMgr.createContextMenu(m_treeViewer.getControl());
		m_treeViewer.getControl().setMenu(menu);
		// Register menu for extension - I don't want extensions here
		// getSite().registerContextMenu(menuMgr, m_treeViewer);
	}

	private void createInfo(Composite parent)
	{
		Label label = UiUtils.createGridLabel(parent, Messages.info_with_colon, 0, 0, SWT.NONE);
		label.setForeground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));
		m_infoText = UiUtils.createGridText(parent, 0, 0, SWT.MULTI | SWT.READ_ONLY | SWT.WRAP);
		// m_projectInfoText.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WIDGET_BACKGROUND));

		GridData layoutData = (GridData)m_infoText.getLayoutData();
		layoutData.heightHint = 80;
	}

	private void createMenu()
	{
		IMenuManager mgr = getViewSite().getActionBars().getMenuManager();
		mgr.add(m_viewPreferencesAction);
		mgr.add(new Separator());
		mgr.add(m_viewAboutAction);
	}

	private void createNavigator(Composite parent)
	{
		Tree tree = new Tree(parent, SWT.NONE);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		m_treeViewer = new TreeViewer(tree);
		m_treeViewer.setContentProvider(new NavigatorContentProvider());
		m_treeViewer.setLabelProvider(new NavigatorLabelProvider());
		m_treeViewer.setInput(m_workspaceRoot);

		m_treeViewer.addDoubleClickListener(new IDoubleClickListener()
		{

			public void doubleClick(DoubleClickEvent event)
			{
				m_openEditorAction.run();
			}
		});

		m_treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{

			public void selectionChanged(SelectionChangedEvent event)
			{
				changeInfo();
			}
		});
	}

	private void createToolbar()
	{
		IToolBarManager mgr = getViewSite().getActionBars().getToolBarManager();
		mgr.add(m_viewCSpecAction);
		mgr.add(m_openCQueryAction);
	}

	private void fillContextMenu(IMenuManager menuMgr)
	{
		IResource selectedResource = getResourceSelection();

		if(selectedResource == null)
		{
			return;
		}

		if(selectedResource instanceof IFile)
		{
			menuMgr.add(m_openEditorAction);

			MenuManager subMenuMgr = new MenuManager(Messages.open_with);
			OpenWithMenu openWithMenu = new OpenWithMenu(getWorkbenchWindow().getActivePage(), selectedResource);
			subMenuMgr.add(openWithMenu);
			menuMgr.add(subMenuMgr);

			String fileExt = ((IFile)selectedResource).getFileExtension();

			// TODO Action (or it's wrapper) should have it's own filter test
			if(Arrays.asList(new String[] { "cspec", "cquery", "bom" }).contains(fileExt)) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			{
				menuMgr.add(new Separator());
				menuMgr.add(m_publishAction);
			}

			// TODO Action (or it's wrapper) should have it's own filter test
			if(Arrays.asList(new String[] { "cquery" }).contains(fileExt)) //$NON-NLS-1$
			{
				menuMgr.add(m_resolveToWizardAction);
				menuMgr.add(m_resolveAndMaterializeAction);
			}

			// Place for extensions - I don't want extensions here
			// menuMgr.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		}

		if(selectedResource instanceof IProject)
		{
			menuMgr.add(m_invokeActionAction);
			menuMgr.add(m_viewCspecAction);
		}

	}

	private Control getNavigatorTabControl(Composite parent)
	{
		Composite tabComposite = getTabComposite(parent);

		createNavigator(tabComposite);
		createInfo(tabComposite);

		return tabComposite;
	}

	private String getProjectInfo(IProject project)
	{
		boolean knownProject = isProjectKnown(project);

		if(knownProject)
		{
			return Messages.buckminster_understands_project_metadata;
		}

		return Messages.buckminster_does_not_understand_project_metadata;
	}

	private Control getRepositoryTabControl(Composite parent)
	{
		Composite tabComposite = getTabComposite(parent);

		Link newAccountLink = new Link(tabComposite, SWT.NONE);
		newAccountLink.setText("<A>" + Messages.create_new_repository_identity + "</A");
		newAccountLink.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Program.launch("www.cloudsmith.com"); //$NON-NLS-1$
			}
		});

		return tabComposite;
	}

	private IResource getResourceSelection()
	{
		ISelection selection = m_treeViewer.getSelection();
		if(selection != null && selection instanceof TreeSelection)
		{
			Object selected = ((TreeSelection)selection).getFirstElement();
			if(selected instanceof IResource)
			{
				return (IResource)selected;
			}
		}

		return null;
	}

	private Composite getTabComposite(Composite parent)
	{
		Composite tabComposite = new Composite(parent, SWT.NONE);
		tabComposite.setLayout(new GridLayout(1, true));
		tabComposite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		tabComposite.setBackgroundMode(SWT.INHERIT_FORCE);

		return tabComposite;
	}

	private IWorkbenchWindow getWorkbenchWindow()
	{
		return getViewSite().getWorkbenchWindow();
	}

	private boolean isProjectKnown(IProject project)
	{
		try
		{
			String[] natureIds = project.getDescription().getNatureIds();

			for(String natureId : natureIds)
			{
				if(natureId.endsWith("PluginNature") || natureId.endsWith("FeatureNature")) //$NON-NLS-1$ //$NON-NLS-2$
				{
					return true;
				}
			}
		}
		catch(CoreException e)
		{
			UiUtils.openError(getViewSite().getShell(), Messages.project_is_not_open, e);
		}

		return false;
	}

	private void loadComponent(boolean materialize)
	{
		IResource resource = getResourceSelection();

		if(resource instanceof IFile)
		{
			File file = resource.getLocation().toFile();
			ComponentQueryBuilder componentQuery = new ComponentQueryBuilder();
			InputStream stream = null;

			if(file.length() == 0)
			{
				String defaultName = file.getName();
				if(defaultName.startsWith(BlankQueryAction.TEMP_FILE_PREFIX))
					defaultName = ""; //$NON-NLS-1$
				else
				{
					int lastDot = defaultName.lastIndexOf('.');
					if(lastDot > 0)
						defaultName = defaultName.substring(0, lastDot);
				}
				componentQuery.setRootRequest(new ComponentRequest(defaultName, null, null));
			}
			else
			{
				try
				{
					stream = new FileInputStream(file);
					componentQuery.initFrom(ComponentQuery.fromStream(file.toURI().toURL(), null, stream, true));
				}
				catch(Exception e)
				{
					UiUtils.openError(getViewSite().getShell(), Messages.unable_to_read_cquery, e);
				}
			}

			if("cquery".equalsIgnoreCase(resource.getFileExtension())) //$NON-NLS-1$
			{
				try
				{
					ResolveJob resolveJob = new ResolveJob(componentQuery.createComponentQuery(), materialize,
							getSite(), false);
					resolveJob.schedule();
				}
				catch(CoreException e)
				{
					UiUtils.openError(getViewSite().getShell(), null, e);
				}
			}
		}
	}

	private void refreshTree()
	{
		this.getViewSite().getShell().getDisplay().asyncExec(new Runnable()
		{

			public void run()
			{
				m_treeViewer.refresh();
			}
		});
	}
}
