/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.buckminster.aggregator.presentation;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.buckminster.aggregator.engine.Builder;
import org.eclipse.buckminster.aggregator.engine.Engine;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.xml.sax.SAXException;

/**
 * This is the action bar contributor for the Aggregator model editor. <!-- begin-user-doc --> <!-- end-user-doc -->
 * 
 * @generated
 */
public class AggregatorActionBarContributor extends EditingDomainActionBarContributor implements
		ISelectionChangedListener
{
	class BuildRepoAction extends Action
	{

		private boolean m_verifyOnly;

		public BuildRepoAction(boolean verifyOnly)
		{
			m_verifyOnly = verifyOnly;

			if(m_verifyOnly)
			{
				setText("Verify Repository");
			}
			else
			{
				setText("Build Repository");
				Object imageURL = AggregatorEditorPlugin.INSTANCE.getImage("full/obj16/start_task.gif");

				if(imageURL != null && imageURL instanceof URL)
					setImageDescriptor(ImageDescriptor.createFromURL((URL)imageURL));
			}
		}

		@Override
		public void run()
		{
			if(getActiveEditor() == null)
			{
				MessageBox messageBox = new MessageBox(getActiveEditor().getSite().getShell(), SWT.ICON_ERROR | SWT.OK);
				messageBox.setMessage("No editor is active");
				messageBox.open();
				return;
			}

			if(saveModel())
			{
				try
				{
					new ProgressMonitorDialog(getActiveEditor().getSite().getShell()).run(true, true,
							new IRunnableWithProgress()
							{

								public void run(IProgressMonitor monitor) throws InvocationTargetException,
										InterruptedException
								{
									try
									{
										Builder builder = new Builder();
										org.eclipse.emf.common.util.URI emfURI = ((IEditingDomainProvider)activeEditorPart).getEditingDomain().getResourceSet().getResources().get(
												0).getURI();
										URL fileURL = FileLocator.toFileURL(new URI(emfURI.toString()).toURL());
										if(!"file".equals(fileURL.getProtocol()))
											throw new Exception("URI scheme is not \"file\"");
										URI uri = new URI(
												fileURL.getProtocol()
														+ ":/"
														+ URLEncoder.encode(fileURL.getPath(), "UTF-8").replaceAll(
																"\\+", "%20"));
										builder.setBuildModelLocation(new File(uri));
										builder.setVerifyOnly(m_verifyOnly);
										builder.run(monitor);
									}
									catch(Throwable e)
									{
										throw new InvocationTargetException(e);
									}
								}
							});
				}
				catch(InvocationTargetException e)
				{
					Throwable cause = unwind(e);
					IStatus status = (cause instanceof CoreException)
							? ((CoreException)cause).getStatus()
							: new Status(IStatus.ERROR, Engine.PLUGIN_ID, IStatus.OK, cause.getMessage(), cause);

					ErrorDialog dialog = new ErrorDialog(null, "Error",
							"Repository builder has not finished successfully", status, IStatus.OK | IStatus.INFO
									| IStatus.WARNING | IStatus.ERROR);
					dialog.open();
				}
				catch(InterruptedException e)
				{
					// interrupted by user
				}
			}

		}

		protected boolean saveModel()
		{
			if(getActiveEditor() == null || !getActiveEditor().isDirty())
				return true;

			getActiveEditor().doSave(new NullProgressMonitor());

			if(getActiveEditor().isDirty())
			{
				MessageBox messageBox = new MessageBox(getActiveEditor().getSite().getShell(), SWT.ICON_ERROR | SWT.OK);
				messageBox.setMessage("Cannot save aggregator definition");
				messageBox.open();
				return false;
			}

			return true;
		}

		private Throwable unwind(Throwable t)
		{
			for(;;)
			{
				Class<?> tc = t.getClass();

				// We don't use instanceof operator since we want
				// the explicit class, not subclasses.
				//
				if(tc != RuntimeException.class && tc != InvocationTargetException.class && tc != SAXException.class
						&& tc != IOException.class)
					break;

				Throwable cause = t.getCause();
				if(cause == null)
					break;

				String msg = t.getMessage();
				if(msg != null && !msg.equals(cause.toString()))
					break;

				t = cause;
			}
			return t;
		}

	}

	/**
	 * This keeps track of the active editor. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IEditorPart activeEditorPart;

	/**
	 * This keeps track of the current selection provider. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ISelectionProvider selectionProvider;

	/**
	 * This action opens the Properties view. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IAction showPropertiesViewAction = new Action(
			AggregatorEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item"))
	{
		@Override
		public void run()
		{
			try
			{
				getPage().showView("org.eclipse.ui.views.PropertySheet");
			}
			catch(PartInitException exception)
			{
				AggregatorEditorPlugin.INSTANCE.log(exception);
			}
		}
	};

	/**
	 * This action refreshes the viewer of the current editor if the editor implements
	 * {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IAction refreshViewerAction = new Action(
			AggregatorEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item"))
	{
		@Override
		public boolean isEnabled()
		{
			return activeEditorPart instanceof IViewerProvider;
		}

		@Override
		public void run()
		{
			if(activeEditorPart instanceof IViewerProvider)
			{
				Viewer viewer = ((IViewerProvider)activeEditorPart).getViewer();
				if(viewer != null)
				{
					viewer.refresh();
				}
			}
		}
	};

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to each descriptor
	 * generated for the current selection by the item provider. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<IAction> createChildActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateChild actions. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IMenuManager createChildMenuManager;

	/**
	 * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding to each descriptor
	 * generated for the current selection by the item provider. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Collection<IAction> createSiblingActions;

	/**
	 * This is the menu manager into which menu contribution items should be added for CreateSibling actions. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected IMenuManager createSiblingMenuManager;

	protected BuildRepoAction m_buildRepoAction;

	protected BuildRepoAction m_verifyRepoAction;

	/**
	 * This creates an instance of the contributor. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public AggregatorActionBarContributor()
	{
		super(ADDITIONS_LAST_STYLE);
		loadResourceAction = new LoadResourceAction();
		validateAction = new ValidateAction();
		controlAction = new ControlAction();
		m_buildRepoAction = new BuildRepoAction(false);
		m_verifyRepoAction = new BuildRepoAction(true);
	}

	/**
	 * This adds to the menu bar a menu and some separators for editor additions, as well as the sub-menus for object
	 * creation items. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void contributeToMenu(IMenuManager menuManager)
	{
		super.contributeToMenu(menuManager);

		IMenuManager submenuManager = new MenuManager(
				AggregatorEditorPlugin.INSTANCE.getString("_UI_AggregatorEditor_menu"),
				"org.eclipse.buckminster.aggregatorMenuID");
		menuManager.insertAfter("additions", submenuManager);
		submenuManager.add(new Separator("settings"));
		submenuManager.add(new Separator("actions"));
		submenuManager.add(new Separator("additions"));
		submenuManager.add(new Separator("additions-end"));

		// Prepare for CreateChild item addition or removal.
		//
		createChildMenuManager = new MenuManager(AggregatorEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		submenuManager.insertBefore("additions", createChildMenuManager);

		// Prepare for CreateSibling item addition or removal.
		//
		createSiblingMenuManager = new MenuManager(
				AggregatorEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		submenuManager.insertBefore("additions", createSiblingMenuManager);

		// Force an update because Eclipse hides empty menus now.
		//
		submenuManager.addMenuListener(new IMenuListener()
		{
			public void menuAboutToShow(IMenuManager menuManager)
			{
				menuManager.updateAll(true);
			}
		});

		addGlobalActions(submenuManager);
	}

	/**
	 * This adds Separators for editor additions to the tool bar. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager)
	{
		toolBarManager.add(new Separator("aggregator-settings"));
		toolBarManager.add(new Separator("aggregator-additions"));
	}

	/**
	 * This populates the pop-up menu before it appears. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void menuAboutToShow(IMenuManager menuManager)
	{
		super.menuAboutToShow(menuManager);
		MenuManager submenuManager = null;

		submenuManager = new MenuManager(AggregatorEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
		populateManager(submenuManager, createChildActions, null);
		menuManager.insertBefore("edit", submenuManager);

		submenuManager = new MenuManager(AggregatorEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
		populateManager(submenuManager, createSiblingActions, null);
		menuManager.insertBefore("edit", submenuManager);
	}

	/**
	 * This implements {@link org.eclipse.jface.viewers.ISelectionChangedListener}, handling
	 * {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for the children and siblings that can be
	 * added to the selected object and updating the menus accordingly. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void selectionChanged(SelectionChangedEvent event)
	{
		// Remove any menu items for old selection.
		//
		if(createChildMenuManager != null)
		{
			depopulateManager(createChildMenuManager, createChildActions);
		}
		if(createSiblingMenuManager != null)
		{
			depopulateManager(createSiblingMenuManager, createSiblingActions);
		}

		// Query the new selection for appropriate new child/sibling descriptors
		//
		Collection<?> newChildDescriptors = null;
		Collection<?> newSiblingDescriptors = null;

		ISelection selection = event.getSelection();
		if(selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1)
		{
			Object object = ((IStructuredSelection)selection).getFirstElement();

			EditingDomain domain = ((IEditingDomainProvider)activeEditorPart).getEditingDomain();

			newChildDescriptors = domain.getNewChildDescriptors(object, null);
			newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
		}

		// Generate actions for selection; populate and redraw the menus.
		//
		createChildActions = generateCreateChildActions(newChildDescriptors, selection);
		createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);

		if(createChildMenuManager != null)
		{
			populateManager(createChildMenuManager, createChildActions, null);
			createChildMenuManager.update(true);
		}
		if(createSiblingMenuManager != null)
		{
			populateManager(createSiblingMenuManager, createSiblingActions, null);
			createSiblingMenuManager.update(true);
		}
	}

	/**
	 * When the active editor changes, this remembers the change and registers with it as a selection provider. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setActiveEditor(IEditorPart part)
	{
		super.setActiveEditor(part);
		activeEditorPart = part;

		// Switch to the new selection provider.
		//
		if(selectionProvider != null)
		{
			selectionProvider.removeSelectionChangedListener(this);
		}
		if(part == null)
		{
			selectionProvider = null;
		}
		else
		{
			selectionProvider = part.getSite().getSelectionProvider();
			selectionProvider.addSelectionChangedListener(this);

			// Fake a selection changed event to update the menus.
			//
			if(selectionProvider.getSelection() != null)
			{
				selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
			}
		}
	}

	@Override
	protected void addGlobalActions(IMenuManager menuManager)
	{
		menuManager.insertBefore("additions", new ActionContributionItem(m_buildRepoAction));
		menuManager.insertBefore("additions", new ActionContributionItem(m_verifyRepoAction));
		menuManager.insertBefore("additions", new Separator());

		addGlobalActionsGen(menuManager);
	}

	/**
	 * This inserts global actions before the "additions-end" separator. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void addGlobalActionsGen(IMenuManager menuManager)
	{
		menuManager.insertAfter("additions-end", new Separator("ui-actions"));
		menuManager.insertAfter("ui-actions", showPropertiesViewAction);

		refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
		menuManager.insertAfter("ui-actions", refreshViewerAction);

		super.addGlobalActions(menuManager);
	}

	/**
	 * This removes from the specified <code>manager</code> all {@link org.eclipse.jface.action.ActionContributionItem}s
	 * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void depopulateManager(IContributionManager manager, Collection<? extends IAction> actions)
	{
		if(actions != null)
		{
			IContributionItem[] items = manager.getItems();
			for(int i = 0; i < items.length; i++)
			{
				// Look into SubContributionItems
				//
				IContributionItem contributionItem = items[i];
				while(contributionItem instanceof SubContributionItem)
				{
					contributionItem = ((SubContributionItem)contributionItem).getInnerItem();
				}

				// Delete the ActionContributionItems with matching action.
				//
				if(contributionItem instanceof ActionContributionItem)
				{
					IAction action = ((ActionContributionItem)contributionItem).getAction();
					if(actions.contains(action))
					{
						manager.remove(contributionItem);
					}
				}
			}
		}
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for each object in
	 * <code>descriptors</code>, and returns the collection of these actions. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection)
	{
		Collection<IAction> actions = new ArrayList<IAction>();
		if(descriptors != null)
		{
			for(Object descriptor : descriptors)
			{
				actions.add(new CreateChildAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * This generates a {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each object in
	 * <code>descriptors</code>, and returns the collection of these actions. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @generated
	 */
	protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection)
	{
		Collection<IAction> actions = new ArrayList<IAction>();
		if(descriptors != null)
		{
			for(Object descriptor : descriptors)
			{
				actions.add(new CreateSiblingAction(activeEditorPart, selection, descriptor));
			}
		}
		return actions;
	}

	/**
	 * This populates the specified <code>manager</code> with {@link org.eclipse.jface.action.ActionContributionItem}s
	 * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection, by
	 * inserting them before the specified contribution item <code>contributionID</code>. If <code>contributionID</code>
	 * is <code>null</code>, they are simply added. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void populateManager(IContributionManager manager, Collection<? extends IAction> actions,
			String contributionID)
	{
		if(actions != null)
		{
			for(IAction action : actions)
			{
				if(contributionID != null)
				{
					manager.insertBefore(contributionID, action);
				}
				else
				{
					manager.add(action);
				}
			}
		}
	}

	/**
	 * This ensures that a delete action will clean up all references to deleted objects. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected boolean removeAllReferencesOnDelete()
	{
		return true;
	}

}
