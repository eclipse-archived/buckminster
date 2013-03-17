/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
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
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.editor.cspec.CSpecEditor.CSpecEditorTab;
import org.eclipse.buckminster.ui.internal.DynamicTableLayout;
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
public class AllAttributesView extends Composite {
	class TableContentProvider implements IStructuredContentProvider {
		@Override
		public void dispose() {
			// Nothing to dispose
		}

		@Override
		public Object[] getElements(Object inputElement) {
			return table.toArray();
		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			// Nothing to do
		}
	}

	class TableLabelProvider extends LabelProvider implements ITableLabelProvider {
		@Override
		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		@Override
		public String getColumnText(Object element, int columnIndex) {
			switch (columnIndex) {
				case 0:
					return ((TopLevelAttributeBuilder) element).getName();
				case 1:
					return getAttributeType((TopLevelAttributeBuilder) element);
				case 2:
					return Boolean.valueOf(((TopLevelAttributeBuilder) element).isPublic()).toString();
				default:
					return ""; //$NON-NLS-1$
			}
		}

		private String getAttributeType(TopLevelAttributeBuilder builder) {
			if (builder instanceof ActionBuilder)
				return Messages.action;
			else if (builder instanceof ActionArtifactBuilder)
				return Messages.product_artifact;
			else if (builder instanceof ArtifactBuilder)
				return Messages.artifact;
			else if (builder instanceof GroupBuilder)
				return Messages.group;

			return ""; //$NON-NLS-1$
		}
	}

	private static final String[] TABLE_TITLES = { Messages.name, Messages.type, Messages.public_label };

	private static final int[] TABLE_WEIGHTS = { 60, 20, 20 };

	private CSpecEditor cspecEditor;

	private TableViewer tableViewer;

	private List<TopLevelAttributeBuilder> table = new ArrayList<TopLevelAttributeBuilder>();

	private Map<ActionArtifactBuilder, ActionBuilder> aaMap = new HashMap<ActionArtifactBuilder, ActionBuilder>();

	private int lastSelectedRow = -1;

	public AllAttributesView(Composite parent, int style, CSpecEditor editor) {
		super(parent, style);
		cspecEditor = editor;

		initComposite();
	}

	public void refresh() {
		table.clear();
		table.addAll(cspecEditor.getActionBuilders());
		for (ActionBuilder actionBuilder : cspecEditor.getActionArtifactBuilders().keySet())
			for (ActionArtifactBuilder actionArtifactBuilder : cspecEditor.getActionArtifactBuilders().get(actionBuilder)) {
				table.add(actionArtifactBuilder);
				aaMap.put(actionArtifactBuilder, actionBuilder);
			}

		table.addAll(cspecEditor.getArtifactBuilders());
		table.addAll(cspecEditor.getGroupBuilders());

		Collections.sort(table, CSpecEditorUtils.getAttributeComparator());

		tableViewer.setInput(table);

		if (getSelectionIndex() == -1 && table.size() > 0) {
			if (lastSelectedRow == -1) {
				tableViewer.getTable().setSelection(0);
			} else {
				if (lastSelectedRow >= table.size()) {
					lastSelectedRow = table.size() - 1;
				}
				tableViewer.getTable().setSelection(lastSelectedRow);
			}
		}
		if (getSelectionIndex() != -1) {
			lastSelectedRow = getSelectionIndex();
		}
	}

	@Override
	public boolean setFocus() {
		return tableViewer.getTable().setFocus();
	}

	protected void initComposite() {
		GridLayout topLayout = new GridLayout(2, false);
		topLayout.marginHeight = topLayout.marginWidth = 0;
		setLayout(topLayout);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Table tbl = new Table(this, SWT.BORDER | SWT.SINGLE | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION);

		Button detailButton = new Button(this, SWT.PUSH);
		detailButton.setText(Messages.show_details);
		detailButton.setLayoutData(new GridData(SWT.BEGINNING, SWT.BEGINNING, false, false));
		detailButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				show(table.get(tableViewer.getTable().getSelectionIndex()));
			}
		});

		tbl.addFocusListener(new FocusListener() {

			@Override
			public void focusGained(FocusEvent e) {
				refresh();
			}

			@Override
			public void focusLost(FocusEvent e) {
				updateLastSelectedRow();
			}
		});

		tbl.setHeaderVisible(true);
		DynamicTableLayout layout = new DynamicTableLayout(50);

		int tableIdx = 0;
		for (int idx = 0; idx < 3; idx++) {
			TableColumn tableColumn = new TableColumn(tbl, SWT.LEFT, tableIdx);
			tableColumn.setText(TABLE_TITLES[idx]);
			layout.addColumnData(new ColumnWeightData(TABLE_WEIGHTS[idx], true));
			tableIdx++;
		}
		tbl.setLayout(layout);
		GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true);
		// gridData.widthHint = 600;
		tbl.setLayoutData(gridData);

		tableViewer = new TableViewer(tbl);
		tableViewer.setLabelProvider(new TableLabelProvider());
		tableViewer.setContentProvider(new TableContentProvider());
		tableViewer.setInput(tbl);
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				if (tableViewer.getTable().getSelectionIndex() >= 0) {
					show(table.get(tableViewer.getTable().getSelectionIndex()));
				}
			}
		});
	}

	private int getSelectionIndex() {
		return tableViewer.getTable().getSelectionIndex();
	}

	private void show(TopLevelAttributeBuilder builder) {
		if (builder instanceof ActionBuilder) {
			if (cspecEditor.getActionsEditor().show(((ActionBuilder) builder), Messages.general))
				cspecEditor.switchTab(CSpecEditorTab.ACTIONS);
		} else if (builder instanceof ActionArtifactBuilder) {
			if (cspecEditor.getActionsEditor().show(aaMap.get(builder), Messages.products)) {
				cspecEditor.switchTab(CSpecEditorTab.ACTIONS);
				cspecEditor.getActionsTable().showProductArtifact((ActionArtifactBuilder) builder);
			}
		} else if (builder instanceof ArtifactBuilder) {
			if (cspecEditor.getArtifactsEditor().show(((ArtifactBuilder) builder), Messages.general))
				cspecEditor.switchTab(CSpecEditorTab.ARTIFACTS);
		} else if (builder instanceof GroupBuilder) {
			if (cspecEditor.getGroupsEditor().show(((GroupBuilder) builder), Messages.general))
				cspecEditor.switchTab(CSpecEditorTab.GROUPS);
		}
	}

	private void updateLastSelectedRow() {
		if (getSelectionIndex() != -1) {
			lastSelectedRow = getSelectionIndex();
		}
	}
}
