/*******************************************************************************
 * Copyright (c) 2009 Johannes Utzig.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Johannes Utzig - initial API and implementation
 *******************************************************************************/
package org.eclipse.buckminster.ui.dialogs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import org.eclipse.buckminster.core.common.model.Documentation;
import org.eclipse.buckminster.core.cspec.model.Attribute;
import org.eclipse.buckminster.core.cspec.model.Group;
import org.eclipse.buckminster.runtime.Trivial;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.DialogSettings;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.dialogs.FilteredItemsSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
 * Displays a selection dialog to allow the user to select one or more attributes from a cspec.
 * <p>
 * The choices of the user are made available in {@link InvokeActionDialog#isForceRebuild()},
 * {@link InvokeActionDialog#getSelectedAttributes()} and {@link InvokeActionDialog#getPropertiesFile()}.
 * 
 * @author Johannes Utzig
 * 
 */
public class InvokeActionDialog extends FilteredItemsSelectionDialog
{

	private class AttributeFilter extends ItemsFilter
	{

		public final boolean finalOnlyPublic = InvokeActionDialog.this.onlyPublic;

		@Override
		public boolean equalsFilter(ItemsFilter itemsFilter)
		{
			AttributeFilter filter = (AttributeFilter)itemsFilter;
			if(isOnlyPublic() != filter.isOnlyPublic())
				return false;
			return super.equalsFilter(filter);
		}

		@Override
		public String getPattern()
		{
			// without at least one character the dialog does not fill the content provider
			return super.getPattern() + "*"; //$NON-NLS-1$
		}

		@Override
		public boolean isConsistentItem(Object item)
		{
			return attributes.contains(item);
		}

		public boolean isOnlyPublic()
		{
			return finalOnlyPublic;
		}

		@Override
		public boolean isSubFilter(ItemsFilter itemsFilter)
		{
			AttributeFilter filter = (AttributeFilter)itemsFilter;
			if(isOnlyPublic() != filter.isOnlyPublic())
				return false;
			return super.isSubFilter(filter);
		}

		@Override
		public boolean matchItem(Object item)
		{
			Attribute attrib = (Attribute)item;
			boolean isPrivate = !attrib.isPublic();
			if(isPrivate && isOnlyPublic())
				return false;
			return matches(attrib.getName());
		}

	}

	private class AttributeSelectionHistory extends SelectionHistory
	{

		@Override
		protected Object restoreItemFromMemento(IMemento element)
		{
			String attributeName = element.getString("attribute"); //$NON-NLS-1$ 
			if(attributeName == null)
				return null;
			for(Attribute attribute : attributes)
			{
				if(attributeName.equals(attribute.getQualifiedName()))
					return attribute;
			}
			return null;
		}

		@Override
		protected void storeItemToMemento(Object item, IMemento element)
		{
			element.putString("attribute", item.toString()); //$NON-NLS-1$
		}
	}

	private class DetailsLabelProvider extends LabelProvider
	{
		@Override
		public String getText(Object element)
		{
			if(element instanceof Attribute)
			{
				Attribute attribute = (Attribute)element;
				Documentation documentation = attribute.getDocumentation();
				if(documentation == null)
					return attribute.getName();
				return documentation.toString();
			}
			return element.toString();
		}
	}

	private class ForceRebuildAction extends Action
	{

		private static final String FORCE_REBUILD = "force.rebuild"; //$NON-NLS-1$

		public ForceRebuildAction()
		{
			super(Messages.force_complete_rebuild, IAction.AS_CHECK_BOX);
			boolean forceRebuild = getDialogSettings().getBoolean(FORCE_REBUILD);
			m_forceRebuild = forceRebuild;
			setChecked(m_forceRebuild);
		}

		@Override
		public void run()
		{
			getDialogSettings().put(FORCE_REBUILD, isChecked());
			m_forceRebuild = isChecked();
		}

	}

	private class LabelProvider extends org.eclipse.jface.viewers.LabelProvider implements ILabelProvider
	{
		private Image privateGroup, group, privateAction, action;

		@Override
		public void dispose()
		{
			super.dispose();
			if(privateAction != null)
				privateAction.dispose();
			if(privateGroup != null)
				privateGroup.dispose();
			if(action != null)
				action.dispose();
			if(group != null)
				group.dispose();
		}

		@Override
		public Image getImage(Object element)
		{
			if(element instanceof Attribute)
			{
				Attribute attribute = (Attribute)element;
				if(attribute.isPublic())
				{
					if(attribute instanceof Group)
					{
						if(group == null)
						{
							group = AbstractUIPlugin.imageDescriptorFromPlugin(UiPlugin.getID(), "icons/group.gif").createImage(); //$NON-NLS-1$
						}
						return group;
					}
					if(action == null)
					{
						action = AbstractUIPlugin.imageDescriptorFromPlugin(UiPlugin.getID(), "icons/action.gif").createImage(); //$NON-NLS-1$
					}
					return action;
				}
				if(attribute instanceof Group)
				{
					if(privateGroup == null)
					{
						privateGroup = AbstractUIPlugin.imageDescriptorFromPlugin(UiPlugin.getID(),
								"icons/private_group.gif").createImage(); //$NON-NLS-1$
					}
					return privateGroup;
				}
				if(privateAction == null)
				{
					privateAction = AbstractUIPlugin.imageDescriptorFromPlugin(UiPlugin.getID(),
							"icons/private_action.gif").createImage(); //$NON-NLS-1$
				}
				return privateAction;

			}
			return null;

		}

		@Override
		public String getText(Object element)
		{
			if(element == null)
				return ""; //$NON-NLS-1$
			Attribute attribute = (Attribute)element;
			return attribute.getName();
		}

	}

	private class ShowPrivateAttributesAction extends Action
	{

		private static final String SHOW_PRIVATE = "show.private"; //$NON-NLS-1$

		public ShowPrivateAttributesAction()
		{
			super(Messages.show_private_attributes, IAction.AS_CHECK_BOX);
			boolean showPrivate = getDialogSettings().getBoolean(SHOW_PRIVATE);
			onlyPublic = !showPrivate;
			setChecked(showPrivate);
		}

		@Override
		public void run()
		{
			getDialogSettings().put(SHOW_PRIVATE, isChecked());
			onlyPublic = !isChecked();
			applyFilter();
		}

	}

	private boolean m_forceRebuild;

	public boolean onlyPublic;

	private String propertiesFile;

	private static Comparator<Attribute> s_attributeComparator = new Comparator<Attribute>()
	{
		public int compare(Attribute o1, Attribute o2)
		{
			return o1.getName().compareTo(o2.getName());
		}
	};

	private static final String DIALOG_SETTINGS = "org.eclipse.buckminster.ui.dialogs.InvokeActionDialog"; //$NON-NLS-1$

	private static final String LAST_PROPERTIES = "last.properties"; //$NON-NLS-1$

	private Collection<Attribute> attributes = new ArrayList<Attribute>();

	public InvokeActionDialog(Shell shell, String title, Collection<Attribute> viableAttributes)
	{
		super(shell, true);
		this.attributes = viableAttributes;
		setListLabelProvider(new LabelProvider());
		setTitle(title);
		setMessage(Messages.select_actions_to_perform);
		setSeparatorLabel(Messages.available_attributes);
		setSelectionHistory(new AttributeSelectionHistory());
		setDetailsLabelProvider(new DetailsLabelProvider());

	}

	@Override
	public String getElementName(Object item)
	{
		Attribute attribute = (Attribute)item;
		return attribute.getQualifiedName();
	}

	/**
	 * 
	 * @return the properties file choosen by the user or <code>null</code> if the dialog was canceled or the user
	 *         didn't select a properties file
	 */
	public File getPropertiesFile()
	{
		if(propertiesFile == null || propertiesFile.trim().length() == 0)
			return null;
		return new File(propertiesFile);
	}

	/**
	 * 
	 * @return the attributes selected by the user or <code>null</code> if nothing was selected
	 */
	public List<Attribute> getSelectedAttributes()
	{
		Object[] selection = getResult();
		if(selection == null)
			return null;
		List<Attribute> selectedAttributes = new ArrayList<Attribute>(selection.length);
		for(int i = 0; i < selection.length; i++)
		{
			selectedAttributes.add((Attribute)selection[i]);
		}
		return selectedAttributes;
	}

	public boolean isForceRebuild()
	{
		return m_forceRebuild;
	}

	@Override
	protected Control createExtendedContentArea(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(composite);
		composite.setLayout(new GridLayout(4, false));
		Label label = new Label(composite, SWT.WRAP);
		label.setText(Messages.properties_file_with_colon);

		final CCombo combo = new CCombo(composite, SWT.NONE);
		GridDataFactory.fillDefaults().grab(true, false).applyTo(combo);
		String[] lastProperties = getDialogSettings().getArray(LAST_PROPERTIES);
		if(lastProperties != null)
		{
			for(int i = 0; i < lastProperties.length; i++)
			{
				if(lastProperties[i] == null)
				{
					// make sure that a corrupted dialog setting won't prevent the dialog from opening
					lastProperties[i] = "INVALID"; //$NON-NLS-1$
				}
			}
			String[] temp = new String[lastProperties.length + 1];
			System.arraycopy(lastProperties, 0, temp, 0, lastProperties.length);
			temp[temp.length - 1] = ""; //$NON-NLS-1$
			lastProperties = temp;
		}
		else
		{
			lastProperties = new String[0];
		}
		combo.setItems(lastProperties);
		combo.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				propertiesFile = Trivial.trim(combo.getText());
			}
		});

		combo.addModifyListener(new ModifyListener()
		{

			public void modifyText(ModifyEvent e)
			{
				propertiesFile = combo.getText();
				updateStatus(validateItem(propertiesFile));
			}
		});

		if(combo.getItemCount() > 0)
		{
			combo.select(0);
		}

		Button browse = new Button(composite, SWT.PUSH);
		browse.setText(Messages.filesystem);
		browse.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				FileDialog dialog = new FileDialog(getShell(), SWT.OPEN);
				String fileName = dialog.open();
				if(fileName == null)
					return;
				int index = index(fileName);
				if(index < 0)
				{
					combo.add(fileName);
					index = combo.getItemCount() - 1;
				}

				combo.select(index);

			}

			private int index(String fileName)
			{
				String[] items = combo.getItems();
				for(int i = 0; i < items.length; i++)
				{
					String item = items[i];
					if(fileName.equals(item))
						return i;
				}
				return -1;
			}
		});

		Button browseWS = new Button(composite, SWT.PUSH);
		browseWS.setText(Messages.workspace);
		browseWS.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				String fileName = null;
				ElementTreeSelectionDialog dialog = new ElementTreeSelectionDialog(getShell(),
						new WorkbenchLabelProvider(), new WorkbenchContentProvider());
				dialog.setValidator(new FileValidator());
				dialog.setAllowMultiple(true);
				dialog.setTitle(Messages.action_properties_file_selection);
				dialog.setMessage(Messages.select_action_properties_file);
				dialog.addFilter(new FileExtensionFilter("properties")); //$NON-NLS-1$
				dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
				dialog.create();
				if(dialog.open() == Window.OK)
				{
					Object[] files = dialog.getResult();
					if(files.length > 0)
						fileName = ((IFile)files[0]).getLocation().toOSString();
				}

				if(fileName == null)
					return;
				int index = index(fileName);
				if(index < 0)
				{
					combo.add(fileName);
					index = combo.getItemCount() - 1;
				}
				combo.select(index);
			}

			private int index(String fileName)
			{
				String[] items = combo.getItems();
				for(int i = 0; i < items.length; i++)
				{
					String item = items[i];
					if(fileName.equals(item))
						return i;
				}
				return -1;
			}
		});

		return composite;
	}

	@Override
	protected ItemsFilter createFilter()
	{
		return new AttributeFilter();
	}

	@Override
	protected void fillContentProvider(AbstractContentProvider contentProvider, ItemsFilter itemsFilter,
			IProgressMonitor progressMonitor) throws CoreException
	{
		progressMonitor.beginTask(Messages.collecting_actions, attributes.size());
		for(Attribute attribute : attributes)
		{
			if(itemsFilter.matchItem(attribute))
			{
				contentProvider.add(attribute, itemsFilter);
			}
			progressMonitor.worked(1);
		}
		progressMonitor.done();

	}

	@Override
	protected void fillViewMenu(IMenuManager menuManager)
	{
		menuManager.add(new ShowPrivateAttributesAction());
		menuManager.add(new ForceRebuildAction());
		super.fillViewMenu(menuManager);

	}

	@Override
	protected IDialogSettings getDialogSettings()
	{
		IDialogSettings settings = UiPlugin.getDefault().getDialogSettings().getSection(DIALOG_SETTINGS);

		if(settings == null)
		{
			settings = UiPlugin.getDefault().getDialogSettings().addNewSection(DIALOG_SETTINGS);
		}

		return settings;

	}

	@Override
	protected Comparator<?> getItemsComparator()
	{
		return s_attributeComparator;
	}

	@Override
	protected void okPressed()
	{
		storeDialogPreferences();
		super.okPressed();
	}

	@Override
	protected IStatus validateItem(Object item)
	{
		if(propertiesFile == null || propertiesFile.trim().length() == 0)
			return Status.OK_STATUS;

		File file = new File(propertiesFile);
		if(!file.exists())
		{
			return new Status(IStatus.ERROR, UiPlugin.getID(), Messages.properties_file_does_not_exist);
		}
		if(!file.isFile())
		{
			return new Status(IStatus.ERROR, UiPlugin.getID(), Messages.not_a_valid_file);
		}

		return Status.OK_STATUS;
	}

	/**
	 * adds the dialog preferences to the {@link DialogSettings}.
	 * <p>
	 * the list of the last 5 choosen properties gets persisted and ordered according to their last usage.
	 * 
	 * @see InvokeActionDialog#getDialogSettings()
	 */
	private void storeDialogPreferences()
	{
		String[] lastProperties = getDialogSettings().getArray(LAST_PROPERTIES);
		List<String> propertyList;
		if(lastProperties != null)
		{
			propertyList = new ArrayList<String>(Arrays.asList(lastProperties));
		}
		else
		{
			propertyList = new ArrayList<String>();
		}

		if(propertyList.contains(propertiesFile))
		{
			propertyList.remove(propertiesFile);
		}
		propertyList.add(0, propertiesFile);
		lastProperties = propertyList.subList(0, Math.min(5, propertyList.size())).toArray(
				new String[Math.min(5, propertyList.size())]);
		getDialogSettings().put(LAST_PROPERTIES, lastProperties);

	}
}
