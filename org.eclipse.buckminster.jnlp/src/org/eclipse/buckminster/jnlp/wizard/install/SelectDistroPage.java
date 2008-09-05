/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.jnlp.wizard.install;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.helpers.SmartArrayList;
import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.ui.UiUtils;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

/**
 * @author Karel Brezina
 * 
 */
public class SelectDistroPage extends InstallWizardPage
{
	private class VariantViewerComparator extends ViewerComparator
	{
		private int m_column = -1;

		private int m_direction = SWT.NONE;

		@Override
		public int compare(Viewer viewer, Object e1, Object e2)
		{
			if(m_direction == SWT.NONE)
				return 0;

			String ai = ((String[])e1)[m_column];
			String bi = ((String[])e2)[m_column];

			return m_direction == SWT.UP
					? safeStringComarator(ai, bi)
					: safeStringComarator(bi, ai);
		}

		public void setSortColumn(int column)
		{
			if(column == m_column)
			{
				// Same column as last sort; toggle the direction
				switch(m_direction)
				{
				case SWT.NONE:
					m_direction = SWT.UP;
					break;
				case SWT.UP:
					m_direction = SWT.DOWN;
					break;
				default:
					m_direction = SWT.NONE;
				}
			}
			else
			{
				// New column; do an ascending sort
				m_column = column;
				m_direction = SWT.UP;
			}

			m_variantsTableViewer.getTable().setSortColumn(m_columns.get(m_column));
			m_variantsTableViewer.getTable().setSortDirection(m_direction);
		}

		@Override
		public void sort(Viewer viewer, Object[] elements)
		{
			if(m_direction != SWT.NONE)
				super.sort(viewer, elements);
		}

		private int safeStringComarator(String arg0, String arg1)
		{
			if(arg0 == null && arg1 == null)
				return 0;

			if(arg0 == null)
				return -1;

			if(arg1 == null)
				return 1;

			return arg0.compareTo(arg1);
		}

	}

	private static final String DISTRO_COMPATIBLE = "1";

	private static final String DISTRO_BROKEN = "-1";

	private static final String DISTRO_INCOMPATIBLE = "0";

	private static final String ICON_COMPATIBLE = "distro.compatible.gif";

	private static final String ICON_INCOMPATIBLE = "distro.incompatible.gif";

	private static final String ICON_BROKEN = "distro.broken.gif";

	private static final String IMAGE_BOX_COMPATIBLE = "box.enabled.compatible.png";

	private static final String IMAGE_BOX_INCOMPATIBLE = "box.enabled.incompatible.png";

	private static final String IMAGE_BOX_BROKEN = "box.disabled.broken.png";

	private static final String IMAGE_BOX_DISABLED = "box.disabled.png";

	private Font m_normalFont;

	private Font m_boldFont;

	private final Image m_iconCompatible;

	private final Image m_iconIncompatible;

	private final Image m_iconBroken;

	private final Image m_imageBoxCompatible;

	private final Image m_imageBoxIncompatible;

	private final Image m_imageBoxBroken;

	private final Image m_imageBoxDisabled;

	private Button m_incompatibleButton;

	private Button m_brokenButton;

	private TableViewer m_variantsTableViewer;

	private List<TableColumn> m_columns;

	private Label m_selectionBoxLabel;

	private Label m_selectionHeadingLabel;

	private Label m_selectionDetailsLabel;

	private final int COLUMN_COUNT = 6;

	private static final String UNSPECIFIED = "unspecified";

	private static final String[][] DATA = { { DISTRO_COMPATIBLE, "sparc", "solaris", "motif", "europa", "chinese" },
			{ DISTRO_COMPATIBLE, "sparc", "solaris", "motif", "ganymede", "" },
			{ DISTRO_INCOMPATIBLE, "intel32", "windows", "windows", "europa", "" },
			{ DISTRO_COMPATIBLE, "sparc", "solaris", "", "europa", "" },
			{ DISTRO_BROKEN, UNSPECIFIED, UNSPECIFIED, UNSPECIFIED, UNSPECIFIED, UNSPECIFIED }, };

	private String[][] m_data;

	private boolean m_initialized = false;

	public SelectDistroPage()
	{
		super(MaterializationConstants.STEP_SELECT_DISTRO, "Select Distro",
				"Select from the available packagings of this stack.", null);
		m_iconCompatible = MaterializationUtils.getImage(ICON_COMPATIBLE);
		m_iconIncompatible = MaterializationUtils.getImage(ICON_INCOMPATIBLE);
		m_iconBroken = MaterializationUtils.getImage(ICON_BROKEN);
		m_imageBoxCompatible = MaterializationUtils.getImage(IMAGE_BOX_COMPATIBLE);
		m_imageBoxIncompatible = MaterializationUtils.getImage(IMAGE_BOX_INCOMPATIBLE);
		m_imageBoxBroken = MaterializationUtils.getImage(IMAGE_BOX_BROKEN);
		m_imageBoxDisabled = MaterializationUtils.getImage(IMAGE_BOX_DISABLED);
	}

	public void createControl(Composite parent)
	{
		final Composite pageComposite = new Composite(parent, SWT.NONE);
		pageComposite.setLayout(new GridLayout(1, false));

		Label label = new Label(pageComposite, SWT.NONE);
		label.setText("The following distros appear to be compatible with your platform:");

		FontData[] fontData = label.getFont().getFontData();
		fontData[0].setStyle(SWT.NORMAL);
		m_normalFont = new Font(getShell().getDisplay(), fontData);
		fontData[0].setStyle(SWT.BOLD);
		m_boldFont = new Font(getShell().getDisplay(), fontData);

		label.setFont(m_boldFont);

		Composite flagsComposite = new Composite(pageComposite, SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 0;
		flagsComposite.setLayout(gridLayout);

		label = new Label(flagsComposite, SWT.NONE);
		label.setText("Show");
		GridData gridData = new GridData();
		gridData.verticalSpan = 2;
		gridData.verticalAlignment = SWT.TOP;
		label.setLayoutData(gridData);
		m_incompatibleButton = new Button(flagsComposite, SWT.CHECK);
		m_incompatibleButton.setText("incompatible packagings");

		m_brokenButton = new Button(flagsComposite, SWT.CHECK);
		m_brokenButton.setText("broken packagings");

		SelectionListener distroFilterListener = new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				initializeTable();
			}
		};

		m_incompatibleButton.addSelectionListener(distroFilterListener);
		m_brokenButton.addSelectionListener(distroFilterListener);

		final Table variantsTable = new Table(pageComposite, SWT.BORDER | SWT.FULL_SELECTION);
		variantsTable.setLayoutData(new GridData(GridData.FILL_BOTH));
		variantsTable.setHeaderVisible(true);
		variantsTable.setLinesVisible(true);

		m_variantsTableViewer = new TableViewer(variantsTable);
		m_variantsTableViewer.setContentProvider(new IStructuredContentProvider()
		{

			public void dispose()
			{
				// nothing to dispose
			}

			public Object[] getElements(Object inputElement)
			{
				return (String[][])inputElement;
			}

			public void inputChanged(Viewer arg0, Object arg1, Object arg2)
			{
				// nothing to do
			}
		});

		m_variantsTableViewer.setLabelProvider(new ITableLabelProvider()
		{

			private List<ILabelProviderListener> m_listeners = new ArrayList<ILabelProviderListener>();

			public void addListener(ILabelProviderListener arg0)
			{
				m_listeners.add(arg0);
			}

			public void dispose()
			{
				// nothing to dispose
			}

			public Image getColumnImage(Object element, int columnIndex)
			{
				String[] row = (String[])element;

				if(columnIndex == 0)
					return DISTRO_COMPATIBLE.equals(row[0])
							? m_iconCompatible
							: (DISTRO_INCOMPATIBLE.equals(row[0])
									? m_iconIncompatible
									: m_iconBroken);

				return null;
			}

			public String getColumnText(Object element, int columnIndex)
			{
				String[] row = (String[])element;

				if(columnIndex > 0)
					return row[columnIndex];

				return "";
			}

			public boolean isLabelProperty(Object arg0, String arg1)
			{
				return false;
			}

			public void removeListener(ILabelProviderListener arg0)
			{
				m_listeners.remove(arg0);

			}
		});

		m_variantsTableViewer.setComparator(new VariantViewerComparator());

		m_columns = new ArrayList<TableColumn>();
		TableColumn column = new TableColumn(variantsTable, SWT.NONE);
		column.setText("Compatible");
		m_columns.add(column);
		column = new TableColumn(variantsTable, SWT.NONE);
		column.setText("CPU Architecture");
		m_columns.add(column);
		column = new TableColumn(variantsTable, SWT.NONE);
		column.setText("Operating System");
		m_columns.add(column);
		column = new TableColumn(variantsTable, SWT.NONE);
		column.setText("Windowing System");
		m_columns.add(column);
		column = new TableColumn(variantsTable, SWT.NONE);
		column.setText("Other Releases");
		m_columns.add(column);
		column = new TableColumn(variantsTable, SWT.NONE);
		column.setText("Language");
		m_columns.add(column);

		SelectionListener columnSelectionListener = new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				((VariantViewerComparator)m_variantsTableViewer.getComparator()).setSortColumn(m_columns
						.indexOf(arg0.widget));
				m_variantsTableViewer.refresh();
				packColumns();
				selectFirstRow();
			}
		};

		for(TableColumn col : m_columns)
			col.addSelectionListener(columnSelectionListener);

		variantsTable.addSelectionListener(new SelectionAdapter()
		{

			@Override
			public void widgetSelected(SelectionEvent arg0)
			{
				TableItem item = (TableItem)arg0.item;
				String[] row = (String[])item.getData();

				m_selectionDetailsLabel.setFont(m_boldFont);

				if(DISTRO_COMPATIBLE.equals(row[0]))
				{
					m_selectionBoxLabel.setImage(m_imageBoxCompatible);

					if(simplePackaging())
					{
						m_selectionHeadingLabel.setText("Simple packaging:");
						m_selectionDetailsLabel.setText("(unspecified)");
					}
					else
					{
						m_selectionHeadingLabel.setText("Distro variant for:");
						m_selectionDetailsLabel.setText(getPlatformString(row));
					}
				}
				else if(DISTRO_INCOMPATIBLE.equals(row[0]))
				{
					m_selectionBoxLabel.setImage(m_imageBoxIncompatible);
					m_selectionHeadingLabel.setText("Incompatible distro variant for:");
					m_selectionDetailsLabel.setText(getPlatformString(row));
				}
				else if(DISTRO_BROKEN.equals(row[0]))
				{
					m_selectionBoxLabel.setImage(m_imageBoxBroken);
					m_selectionHeadingLabel.setText("Distro broken - not downloadable:");
					m_selectionDetailsLabel.setText("(publisher attention needed)");
					m_selectionDetailsLabel.setFont(m_normalFont);
				}
				else
				{
					m_selectionBoxLabel.setImage(m_imageBoxDisabled);
					m_selectionHeadingLabel.setText("No selected packaging");
					m_selectionDetailsLabel.setText("");
				}
				pageComposite.layout();
			}
		});

		Composite selectionComposite = new Composite(pageComposite, SWT.NONE);
		gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		selectionComposite.setLayout(gridLayout);

		m_selectionBoxLabel = new Label(selectionComposite, SWT.NONE);

		Composite selectionDetailsComposite = new Composite(selectionComposite, SWT.NONE);
		gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		selectionDetailsComposite.setLayout(gridLayout);

		m_selectionHeadingLabel = new Label(selectionDetailsComposite, SWT.NONE);
		m_selectionDetailsLabel = new Label(selectionDetailsComposite, SWT.NONE);
		gridData = new GridData();
		gridData.horizontalIndent = 10;
		m_selectionDetailsLabel.setLayoutData(gridData);
		m_selectionDetailsLabel.setFont(m_boldFont);

		setControl(pageComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		if(!m_initialized)
		{
			initializeTable();
		}
	}

	private String getPlatformString(String[] row)
	{
		List<String> platform = new SmartArrayList<String>();
		for(int i = 1; i < COLUMN_COUNT; i++)
			if(UiUtils.trimmedValue(row[i]) != null)
				platform.add(row[i]);

		return platform.toString();
	}

	private void initializeTable()
	{
		List<String[]> dataList = new ArrayList<String[]>();

		for(String[] row : DATA)
		{
			if(DISTRO_INCOMPATIBLE.equals(row[0]) && !m_incompatibleButton.getSelection()
					|| DISTRO_BROKEN.equals(row[0]) && !m_brokenButton.getSelection())
				continue;

			dataList.add(row);
		}
		m_data = dataList.toArray(new String[0][]);

		m_variantsTableViewer.setInput(m_data);
		m_variantsTableViewer.refresh();
		packColumns();
		selectFirstRow();
		m_initialized = true;
	}

	private void packColumns()
	{
		m_variantsTableViewer.getTable().setRedraw(false);
		for(TableColumn col : m_columns)
			col.pack();
		m_variantsTableViewer.getTable().setRedraw(true);
	}

	private void selectFirstRow()
	{
		Table table = m_variantsTableViewer.getTable();
		table.setSelection(0);
		table.showSelection();
		Event event = new Event();
		event.item = table.getItem(0);
		table.notifyListeners(SWT.Selection, event);
	}

	private boolean simplePackaging()
	{
		for(int i = 1; i < COLUMN_COUNT; i++)
			if(!UNSPECIFIED.equals(m_data[i]))
				return false;

		return true;
	}
}
