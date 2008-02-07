/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.materializer.MaterializationStatistics;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.jnlp.ui.DynamicTableLayout;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.program.Program;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Karel Brezina
 *
 */
public class ComponentListPanel
{
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
			return null;
		}

		@SuppressWarnings("unchecked")
		public String getColumnText(Object element, int columnIndex)
		{
			ComponentPath componentPath = (ComponentPath)element;
			return columnIndex == 0 ? componentPath.getComponentIdentifier().getName() : componentPath.getPath().toOSString();
		}
	}
	
	class ComponentPath implements Comparable<ComponentPath>
	{
		private ComponentIdentifier m_componentIdentifier;
		
		private IPath m_path;

		public ComponentPath(ComponentIdentifier componentIdentifier, IPath path)
		{
			m_componentIdentifier = componentIdentifier;
			m_path = path;
		}

		public ComponentIdentifier getComponentIdentifier()
		{
			return m_componentIdentifier;
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

		Table table = m_tableViewer.getTable();
		DynamicTableLayout layout = new DynamicTableLayout(50);
		
		new TableColumn(table, SWT.LEFT, 0).setText("Component");
		layout.addColumnData(new ColumnWeightData(30, true));

		new TableColumn(table, SWT.LEFT, 1).setText("Destination Folder");
		layout.addColumnData(new ColumnWeightData(70, true));

		table.setLayout(layout);
		table.setHeaderVisible(true);
		table.setToolTipText("Double-click to open selected folder");

	    Menu menu = new Menu(table);
	    MenuItem menuItem = new MenuItem(menu, SWT.CASCADE);
	    menuItem.setText("Open Folder");
	    menuItem.addSelectionListener(new SelectionAdapter(){

			@Override
			public void widgetSelected(SelectionEvent e)
			{
				openSelectedFolder();
			}
		});
	    table.setMenu(menu);
		
		return m_tableViewer.getTable();
	}
	
	private void openSelectedFolder()
	{
		ComponentPath componentPath = (ComponentPath)((IStructuredSelection)m_tableViewer.getSelection()).getFirstElement();
		Program.launch(componentPath.getPath().toOSString());
	}
	
	public void update(MaterializationStatistics ms)
	{
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
		
		m_tableViewer.setInput(m_data);
	}
}
