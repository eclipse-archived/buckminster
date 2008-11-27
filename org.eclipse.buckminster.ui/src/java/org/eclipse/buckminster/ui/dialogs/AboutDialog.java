/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.dialogs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.buckminster.core.helpers.TextUtils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.UiUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.osgi.framework.Bundle;

/**
 * @author Karel Brezina
 * 
 */
public class AboutDialog extends Dialog
{

	class ExtensionsDialog extends Dialog
	{
		class NavigatorContentProvider implements ITreeContentProvider
		{

			public void dispose()
			{
			}

			public Object[] getChildren(Object parentElement)
			{
				if(parentElement instanceof IExtensionPoint)
				{
					IExtensionPoint extensionPoint = (IExtensionPoint)parentElement;

					return extensionPoint.getConfigurationElements();
				}
				return null;
			}

			public Object[] getElements(Object inputElement)
			{
				return (IExtensionPoint[])inputElement;
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

			private Image m_point;

			private Image m_element;

			public NavigatorLabelProvider()
			{
				m_point = UiPlugin.getImageDescriptor("icons/extension_obj.gif").createImage(); //$NON-NLS-1$
				m_element = UiPlugin.getImageDescriptor("icons/generic_xml_obj.gif").createImage(); //$NON-NLS-1$
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
				if(element instanceof IExtensionPoint)
				{
					return m_point;
				}

				if(element instanceof IConfigurationElement)
				{
					return m_element;
				}

				return null;
			}

			public String getText(Object element)
			{
				if(element instanceof IExtensionPoint)
				{
					return ((IExtensionPoint)element).getUniqueIdentifier();
				}

				if(element instanceof IConfigurationElement)
				{
					IConfigurationElement confElement = (IConfigurationElement)element;
					return TextUtils.notNullString(confElement.getAttribute("id")) + " (" + confElement.getName() + ")"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				}

				return ""; //$NON-NLS-1$
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

		private final static String BUCKMINSTER_PREFIX = "org.eclipse.buckminster"; //$NON-NLS-1$

		private TreeViewer m_treeViewer;

		private IExtensionPoint[] m_extensionPoints;

		public ExtensionsDialog(Shell parentShell)
		{
			super(parentShell);

			IExtensionPoint[] allPoints = Platform.getExtensionRegistry().getExtensionPoints();
			List<IExtensionPoint> bmPoints = new ArrayList<IExtensionPoint>();

			for(IExtensionPoint extensionPoint : allPoints)
			{
				if(extensionPoint.getContributor().getName().startsWith(ExtensionsDialog.BUCKMINSTER_PREFIX))
				{
					if(extensionPoint.getConfigurationElements().length > 0)
					{
						bmPoints.add(extensionPoint);
					}
				}
			}

			Collections.sort(bmPoints, new Comparator<IExtensionPoint>()
			{

				public int compare(IExtensionPoint arg0, IExtensionPoint arg1)
				{
					return arg0.getUniqueIdentifier().compareToIgnoreCase(arg1.getUniqueIdentifier());
				}
			});

			m_extensionPoints = bmPoints.toArray(new IExtensionPoint[0]);
		}

		@Override
		protected void buttonPressed(int buttonId)
		{
			setReturnCode(buttonId);
			close();
		}

		@Override
		protected void configureShell(Shell newShell)
		{
			super.configureShell(newShell);

			newShell.setText(Messages.buckminster_extensions);
		}

		@Override
		protected void createButtonsForButtonBar(Composite parent)
		{
			createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		}

		@Override
		protected Control createDialogArea(Composite parent)
		{
			Composite composite = new Composite(parent, SWT.NONE);
			GridLayout layout = new GridLayout(1, true);
			layout.marginHeight = layout.marginWidth = 10;
			composite.setLayout(layout);
			Tree tree = new Tree(composite, SWT.BORDER);
			tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

			m_treeViewer = new TreeViewer(tree);
			m_treeViewer.setContentProvider(new NavigatorContentProvider());
			m_treeViewer.setLabelProvider(new NavigatorLabelProvider());
			m_treeViewer.setInput(m_extensionPoints);

			return composite;
		}
	}

	public static final int EXTENSIONS_ID = IDialogConstants.CLIENT_ID;

	public static final String EXTENSIONS_LABEL = Messages.extensions;

	// TODO Should be in core bundle
	private static String getCoreVersion()
	{
		Bundle coreBundle = Platform.getBundle("org.eclipse.buckminster.core"); //$NON-NLS-1$
		return coreBundle == null
				? null
				: (String)coreBundle.getHeaders().get("Bundle-Version"); //$NON-NLS-1$
	}

	private Image m_image;

	private ExtensionsDialog m_extensionDialog;

	public AboutDialog(Shell parentShell)
	{
		super(parentShell);

		m_image = UiPlugin.getImageDescriptor("images/buckminster_logo.png").createImage(); //$NON-NLS-1$
		m_extensionDialog = new ExtensionsDialog(getShell());
	}

	@Override
	public boolean close()
	{
		if(m_image != null)
			m_image.dispose();
		return super.close();
	}

	@Override
	protected void buttonPressed(int buttonId)
	{
		switch(buttonId)
		{
		case EXTENSIONS_ID:
			m_extensionDialog.open();
			break;
		case IDialogConstants.OK_ID:
			setReturnCode(buttonId);
			close();
		}
	}

	@Override
	protected void configureShell(Shell newShell)
	{
		super.configureShell(newShell);

		newShell.setText(Messages.about_buckminster);
	}

	@Override
	protected void createButtonsForButtonBar(Composite parent)
	{
		createButton(parent, EXTENSIONS_ID, EXTENSIONS_LABEL, false);
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
	}

	@Override
	protected Control createDialogArea(Composite parent)
	{
		Composite composite = new Composite(parent, SWT.NONE);
		GridData data = new GridData(GridData.FILL_BOTH);
		composite.setLayoutData(data);
		GridLayout layout = new GridLayout(2, false);
		layout.marginTop = layout.marginHeight;
		layout.marginHeight = layout.marginWidth = 0;
		layout.horizontalSpacing += 5;
		composite.setLayout(layout);

		composite.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_WHITE));
		composite.setBackgroundMode(SWT.INHERIT_FORCE);

		Label label = new Label(composite, SWT.NONE);
		label.setImage(m_image);

		Composite bmComposite = new Composite(composite, SWT.NONE);
		data = new GridData(GridData.FILL_BOTH);
		bmComposite.setLayoutData(data);
		layout = new GridLayout(2, false);
		bmComposite.setLayout(layout);

		UiUtils.createGridLabel(bmComposite, Messages.version_with_colon, 0, 0, SWT.NONE);

		UiUtils.createGridLabel(bmComposite, TextUtils.notNullString(getCoreVersion()), 0, 0, SWT.NONE);
		UiUtils.createGridLabel(bmComposite, Messages.wiki_with_colon, 0, 0, SWT.NONE);
		Link wiki = new Link(bmComposite, SWT.NONE);
		wiki.setText("<A>http://wiki.eclipse.org/index.php/BuckminsterWiki</A>"); //$NON-NLS-1$
		wiki.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				Program.launch("http://wiki.eclipse.org/index.php/Buckminster"); //$NON-NLS-1$
			}
		});

		// Build the separator line
		Label separator = new Label(composite, SWT.HORIZONTAL | SWT.SEPARATOR);
		data = new GridData(GridData.FILL_HORIZONTAL);
		data.horizontalSpan = 2;
		separator.setLayoutData(data);

		return composite;
	}
}
