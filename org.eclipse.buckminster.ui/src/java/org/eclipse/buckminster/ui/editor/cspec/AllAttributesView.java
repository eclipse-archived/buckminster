/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.ui.editor.cspec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.cspec.builder.ActionArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.ActionBuilder;
import org.eclipse.buckminster.core.cspec.builder.ArtifactBuilder;
import org.eclipse.buckminster.core.cspec.builder.GroupBuilder;
import org.eclipse.buckminster.core.cspec.builder.TopLevelAttributeBuilder;
import org.eclipse.buckminster.ui.DynamicTableLayout;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.editor.cspec.CSpecEditor.CSpecEditorTab;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.FocusListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * @author Karel Brezina
 *
 */
public class AllAttributesView extends Composite
{
	class TableContentProvider implements IStructuredContentProvider
	{
		public void dispose()
		{
			// Nothing to dispose
		}

		public Object[] getElements(Object inputElement)
		{
			return m_table.toArray();
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

		public String getColumnText(Object element, int columnIndex)
		{
			switch(columnIndex)
			{
				case 0:
					return ((TopLevelAttributeBuilder)element).getName();
				case 1:
					return getAttributeType((TopLevelAttributeBuilder)element);
				case 2:
					return Boolean.valueOf(((TopLevelAttributeBuilder)element).isPublic()).toString();
				default:
					return ""; //$NON-NLS-1$
			}
		}

		private String getAttributeType(TopLevelAttributeBuilder builder)
		{
			if (builder instanceof ActionBuilder)
				return Messages.action;
			else if(builder instanceof ActionArtifactBuilder)
				return Messages.product_artifact;
			else if(builder instanceof ArtifactBuilder)
				return Messages.artifact;
			else if(builder instanceof GroupBuilder)
				return Messages.group;
			
			return ""; //$NON-NLS-1$
		}
	}

	private static final String[] TABLE_TITLES = {Messages.name, Messages.type, Messages.public_label};
	
	private static final int[] TABLE_WEIGHTS = {60, 20, 20};

	private CSpecEditor m_cspecEditor;
	
	private TableViewer m_tableViewer;
	
	private List<TopLevelAttributeBuilder> m_table = new ArrayList<TopLevelAttributeBuilder>();
	
	private Map<ActionArtifactBuilder, ActionBuilder> m_aaMap = new HashMap<ActionArtifactBuilder, ActionBuilder>();
	
	private int m_lastSelectedRow = -1;
	
	public AllAttributesView(Composite parent, int style, CSpecEditor editor)
	{
		super(parent, style);
		m_cspecEditor = editor;
		
		initComposite();
	}

	protected void initComposite()
	{
		GridLayout topLayout = new GridLayout(2, false);
		topLayout.marginHeight = topLayout.marginWidth = 0;
		setLayout(topLayout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table table = new Table(this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL
				| SWT.FULL_SELECTION);
		
		Button detailButton = new Button(this, SWT.PUSH);
		detailButton.setText(Messages.show_details);
		detailButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		detailButton.addSelectionListener(new SelectionAdapter()
		{
			@Override
			public void widgetSelected(SelectionEvent e)
			{
				show(m_table.get(m_tableViewer.getTable().getSelectionIndex()));
			}
		});
		
		table.addFocusListener(new FocusListener(){

			public void focusGained(FocusEvent e)
			{
				refresh();
			}

			public void focusLost(FocusEvent e)
			{
				updateLastSelectedRow();				
			}});

		table.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);

		int tableIdx = 0;
		for(int idx = 0; idx < 3; idx++)
		{
			TableColumn tableColumn = new TableColumn(table, SWT.LEFT, tableIdx);
			tableColumn.setText(TABLE_TITLES[idx]);
			layout.addColumnData(new ColumnWeightData(TABLE_WEIGHTS[idx], true));
			tableIdx++;
		}
		table.setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		// gridData.widthHint = 600;
		table.setLayoutData(gridData);
		
		m_tableViewer = new TableViewer(table);
		m_tableViewer.setLabelProvider(new TableLabelProvider());
		m_tableViewer.setContentProvider(new TableContentProvider());
		m_tableViewer.setInput(m_table);
		m_tableViewer.addDoubleClickListener(new IDoubleClickListener()
		{
			public void doubleClick(DoubleClickEvent event)
			{
				if(m_tableViewer.getTable().getSelectionIndex() >= 0)
				{
					show(m_table.get(m_tableViewer.getTable().getSelectionIndex()));
				}
			}
		});
	}

	public void refresh()
	{
		m_table.clear();
		m_table.addAll(m_cspecEditor.getActionBuilders());
		for(ActionBuilder actionBuilder : m_cspecEditor.getActionArtifactBuilders().keySet())
			for(ActionArtifactBuilder actionArtifactBuilder : m_cspecEditor.getActionArtifactBuilders().get(actionBuilder))
			{
				m_table.add(actionArtifactBuilder);
				m_aaMap.put(actionArtifactBuilder, actionBuilder);
			}
		
		m_table.addAll(m_cspecEditor.getArtifactBuilders());
		m_table.addAll(m_cspecEditor.getGroupBuilders());

		Collections.sort(m_table, CSpecEditorUtils.getAttributeComparator());
		
		m_tableViewer.setInput(m_table);
		

		if(getSelectionIndex() == -1 && m_table.size() > 0)
		{
			if(m_lastSelectedRow == -1)
			{
				m_tableViewer.getTable().setSelection(0);
			}
			else
			{
				if(m_lastSelectedRow >= m_table.size())
				{
					m_lastSelectedRow = m_table.size() - 1;
				}
				m_tableViewer.getTable().setSelection(m_lastSelectedRow);
			}
		}
		if(getSelectionIndex() != -1)
		{
			m_lastSelectedRow = getSelectionIndex();
		}
	}
	
	private int getSelectionIndex()
	{
		return m_tableViewer.getTable().getSelectionIndex();
	}

	private void updateLastSelectedRow()
	{
		if(getSelectionIndex() != -1)
		{
			m_lastSelectedRow = getSelectionIndex();
		}
	}


	private void show(TopLevelAttributeBuilder builder)
	{
		if(builder instanceof ActionBuilder)
		{			
			if(m_cspecEditor.getActionsEditor().show(((ActionBuilder)builder), Messages.general))
				m_cspecEditor.switchTab(CSpecEditorTab.ACTIONS);
		} else if(builder instanceof ActionArtifactBuilder)
		{
			if(m_cspecEditor.getActionsEditor().show(m_aaMap.get(builder), Messages.products))
			{	
				m_cspecEditor.switchTab(CSpecEditorTab.ACTIONS);
				m_cspecEditor.getActionsTable().showProductArtifact((ActionArtifactBuilder)builder);
			}
		}
		else if(builder instanceof ArtifactBuilder)
		{
			if(m_cspecEditor.getArtifactsEditor().show(((ArtifactBuilder)builder), Messages.general))
				m_cspecEditor.switchTab(CSpecEditorTab.ARTIFACTS);
		}
		else if(builder instanceof GroupBuilder)
		{
			if(m_cspecEditor.getGroupsEditor().show(((GroupBuilder)builder), Messages.general))
				m_cspecEditor.switchTab(CSpecEditorTab.GROUPS);
		}
	}
	
	@Override
	public boolean setFocus()
	{
		return m_tableViewer.getTable().setFocus();
	}
}
