/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.buckminster.core.cspec.IComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.materializer.MaterializationContext;
import org.eclipse.buckminster.core.materializer.MaterializationStatistics;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author Karel Brezina
 *
 */
public class ComponentListPanel
{
	private static final String ICON_STATUS_OK = "status.ok.gif";
	
	private static final String ICON_STATUS_FAILED = "status.error.gif";
	
	private final Image m_iconStatusOK = MaterializationUtils.getImage(ICON_STATUS_OK);

	private final Image m_iconStatusFailed = MaterializationUtils.getImage(ICON_STATUS_FAILED);

	class TableContentProvider implements IStructuredContentProvider
	{
		public void dispose()
		{
			// Nothing to dispose
		}

		public Object[] getElements(Object inputElement)
		{
			return m_data.toArray();
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
		{
			// Nothing to do
		}
	}

	class TableLabelProvider extends LabelProvider implements ITableLabelProvider
	{
		public Image getColumnImage(Object element, int columnIndex)
		{
			if(columnIndex == 0)
			{
				ComponentPath componentPath = (ComponentPath)element;

				if(componentPath.isFailed())
					return m_iconStatusFailed;

				return m_iconStatusOK;
			}
			
			return null;
		}

		public String getColumnText(Object element, int columnIndex)
		{
			ComponentPath componentPath = (ComponentPath)element;
			
			if(columnIndex == 0)
				return null;
			else if(columnIndex == 1)
				return componentPath.getComponentIdentifier().getName();
			else
				return componentPath.getPath() == null ? null : componentPath.getPath().toOSString();
		}
	}
	
	class ComponentPath implements Comparable<ComponentPath>
	{
		private ComponentIdentifier m_componentIdentifier;
		
		private boolean m_failed = false;
		
		private IPath m_path;

		public ComponentPath(ComponentIdentifier componentIdentifier, IPath path)
		{
			this(componentIdentifier, false, path);
		}

		public ComponentPath(ComponentIdentifier componentIdentifier, boolean failed, IPath path)
		{
			m_componentIdentifier = componentIdentifier;
			m_failed = failed;
			m_path = path;
		}

		public IComponentIdentifier getComponentIdentifier()
		{
			return m_componentIdentifier;
		}

		public boolean isFailed()
		{
			return m_failed;
		}

		public IPath getPath()
		{
			return m_path;
		}

		public int compareTo(ComponentPath componentPath)
		{
			return m_componentIdentifier.compareTo(componentPath.getComponentIdentifier());
		}		
	}

	private Set<ComponentPath> m_data = new TreeSet<ComponentPath>();
	
	private TableViewer m_tableViewer;
	
	private Menu m_tableMenu;
	
	public Control createControl(Composite parent)
	{
		m_tableViewer = new TableViewer(parent, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);
		m_tableViewer.setLabelProvider(new TableLabelProvider());
		m_tableViewer.setContentProvider(new TableContentProvider());
		m_tableViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				openSelectedFolder();
			}
		});

		final Table table = m_tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		new TableColumn(table, SWT.LEFT, 0);
		new TableColumn(table, SWT.LEFT, 1).setText("Component");
		new TableColumn(table, SWT.LEFT, 2).setText("Destination Folder");

		table.setHeaderVisible(true);

	    m_tableMenu = new Menu(table);
	    MenuItem menuItem = new MenuItem(m_tableMenu, SWT.CASCADE);
	    menuItem.setText("Open Folder");
	    menuItem.addSelectionListener(new SelectionAdapter(){

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				openSelectedFolder();
			}
		});
	    table.setMenu(m_tableMenu);
		
		table.addMouseTrackListener(new MouseTrackAdapter()
		{

			@Override
			public void mouseExit(MouseEvent e)
			{
				table.setToolTipText(null);
			}

			@Override
			public void mouseHover(MouseEvent e)
			{
				TableItem item = table.getItem(new Point(e.x, e.y));
				String toolTipText = "Double-click to open selected folder";

				if(item != null && ((ComponentPath)item.getData()).isFailed())
					toolTipText = "Cancelled";

				table.setToolTipText(toolTipText);
			}
		});
		
		table.addSelectionListener(new SelectionAdapter(){

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				TableItem item = (TableItem)e.item;
				
				if(item != null && ((ComponentPath)item.getData()).isFailed())
					table.setMenu(null);
				else
					table.setMenu(m_tableMenu);
			}
		});
		
		return m_tableViewer.getTable();
	}
	
	private void openSelectedFolder()
	{
		ComponentPath componentPath = (ComponentPath)((IStructuredSelection)m_tableViewer.getSelection()).getFirstElement();
		
		if(componentPath.getPath() != null)
			Program.launch(componentPath.getPath().toOSString());
	}
	
	public void update(MaterializationContext context)
	{
		MaterializationStatistics ms = context.getMaterializationStatistics();
		
		m_data.clear();
		
		Set<ComponentIdentifier> identifiers = new HashSet<ComponentIdentifier>();
		identifiers.addAll(ms.getKept());
		identifiers.addAll(ms.getReplaced());
		identifiers.addAll(ms.getUpdated());
		
		for(ComponentIdentifier ci : identifiers)
		{
			IPath path = null;
			try
			{
				path = WorkspaceInfo.getComponentLocation(ci);

				if(path != null && !new File(path.toOSString()).isDirectory())
					path = path.removeLastSegments(1);			
			}
			catch(CoreException e)
			{
				// path == null
			}
			
			if(path != null)
				m_data.add(new ComponentPath(ci, path.removeTrailingSeparator()));
		}
		
		for(ComponentIdentifier ci : ms.getFailed())
			m_data.add(new ComponentPath(ci, true, null));
			
		m_tableViewer.setInput(m_data);

		for (int i=0; i < 3; i++)
			m_tableViewer.getTable().getColumn(i).pack();
	}
}
