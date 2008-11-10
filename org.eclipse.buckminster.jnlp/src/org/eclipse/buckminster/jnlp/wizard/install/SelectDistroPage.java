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

import org.eclipse.buckminster.jnlp.MaterializationConstants;
import org.eclipse.buckminster.jnlp.MaterializationUtils;
import org.eclipse.buckminster.jnlp.distroprovider.DistroVariant;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
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

			String ai = getColumnValueFromVariant((DistroVariant) e1, m_column);
			String bi = getColumnValueFromVariant((DistroVariant) e2, m_column);

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

	private Font m_boldFont;

	private Font m_italicFont;

	private Color m_orangeColor;
	
	private Color m_redColor;
	
	private final Image m_iconCompatible;

	private final Image m_iconIncompatible;

	private final Image m_iconBroken;

	private final Image m_imageBoxCompatible;

	private final Image m_imageBoxIncompatible;

	private final Image m_imageBoxBroken;

	private final Image m_imageBoxDisabled;

	private Composite m_topComposite;

	private StackLayout m_stackLayout;
	
	private Composite m_noDistroPageComposite;
	
	private Label m_noDistroLabel;
	
	private Composite m_pageComposite;
	
	private Button m_incompatibleButton;

	private Button m_brokenButton;

	private TableViewer m_variantsTableViewer;

	private List<TableColumn> m_columns;

	private Label m_selectionBoxLabel;

	private Label m_selectionHeadingLabel;

	private Composite m_selectionComposite;
	
	private Composite m_selectionDetailsComposite;
	
	private static final String UNSPECIFIED = "unspecified";

	private List<DistroVariant> m_data;

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
		m_topComposite = new Composite(parent, SWT.NONE);
		m_stackLayout = new StackLayout();
		m_topComposite.setLayout(m_stackLayout);
		
		m_noDistroPageComposite = new Composite(m_topComposite, SWT.NONE);
		m_noDistroPageComposite.setLayout(new GridLayout(1, false));

		m_noDistroLabel = new Label(m_noDistroPageComposite, SWT.WRAP);
		m_noDistroLabel.setText("Sorry! The publisher of this stack hasn't packaged it in a distro available for download.");

		FontData[] fontData = m_noDistroLabel.getFont().getFontData();	
		fontData[0].setStyle(SWT.BOLD);
		m_boldFont = new Font(getShell().getDisplay(), fontData);
		fontData[0].setStyle(SWT.ITALIC);
		m_italicFont = new Font(getShell().getDisplay(), fontData);
		m_orangeColor = new Color(getShell().getDisplay(), 255, 161, 68);
		m_redColor = getShell().getDisplay().getSystemColor(SWT.COLOR_RED);

		m_noDistroLabel.setFont(m_boldFont);

		m_pageComposite = new Composite(m_topComposite, SWT.NONE);
		m_pageComposite.setLayout(new GridLayout(1, false));
		
		Label label = new Label(m_pageComposite, SWT.NONE);
		label.setText("The following distros appear to be compatible with your platform:");
		label.setFont(m_boldFont);

		Composite flagsComposite = new Composite(m_pageComposite, SWT.NONE);
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

		final Table variantsTable = new Table(m_pageComposite, SWT.BORDER | SWT.FULL_SELECTION);
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

			@SuppressWarnings("unchecked")
			public Object[] getElements(Object inputElement)
			{
				return ((List<DistroVariant>)inputElement).toArray(new DistroVariant[0]);
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
				if(columnIndex == 0)
				{
					String status = getColumnValueFromVariant((DistroVariant)element, columnIndex);

					return DISTRO_COMPATIBLE.equals(status)
							? m_iconCompatible
							: (DISTRO_INCOMPATIBLE.equals(status)
									? m_iconIncompatible
									: m_iconBroken);				
				}	

				return null;
			}

			public String getColumnText(Object element, int columnIndex)
			{
				if(columnIndex > 0)
					return getColumnValueFromVariant((DistroVariant)element, columnIndex);

				return null;
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
		column.setAlignment(SWT.CENTER);
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
				DistroVariant variant = (DistroVariant)item.getData();

				for(Control control : m_selectionDetailsComposite.getChildren())
					control.dispose();
					
				Label selectionDetailsLabel = new Label(m_selectionDetailsComposite, SWT.NONE);
				selectionDetailsLabel.setFont(m_boldFont);
				
				String variantStatus = getColumnValueFromVariant(variant, 0);

				if(DISTRO_COMPATIBLE.equals(variantStatus))
				{
					m_selectionBoxLabel.setImage(m_imageBoxCompatible);

					if(variant.isSimplePackaging())
					{
						m_selectionHeadingLabel.setText("Simple packaging:");
						selectionDetailsLabel.setText("(unspecified)");
					}
					else
					{
						m_selectionHeadingLabel.setText("Distro variant for:");
						selectionDetailsLabel.setText(variant.getPlatformString());
					}
				}
				else if(DISTRO_INCOMPATIBLE.equals(variantStatus))
				{
					m_selectionBoxLabel.setImage(m_imageBoxIncompatible);
					m_selectionHeadingLabel.setText("Incompatible distro variant for:");
					
					selectionDetailsLabel.dispose();					
					addSelectionDetailsPlatformLabel(variant.getArch(), variant.isArchCompatible());
					addSelectionDetailsPlatformLabel(variant.getOS(), variant.isOSCompatible());
					addSelectionDetailsPlatformLabel(variant.getWS(), variant.isWSCompatible());
					addSelectionDetailsPlatformLabel(variant.getRelease(), variant.isReleaseCompatible());
					addSelectionDetailsPlatformLabel(variant.getNL(), variant.isNLCompatible());
				}
				else if(DISTRO_BROKEN.equals(variantStatus))
				{
					m_selectionBoxLabel.setImage(m_imageBoxBroken);
					m_selectionHeadingLabel.setText("Distro broken - not downloadable:");
					selectionDetailsLabel.setText("(publisher attention needed)");
					selectionDetailsLabel.setFont(null);
				}
				else
				{
					m_selectionBoxLabel.setImage(m_imageBoxDisabled);
					m_selectionHeadingLabel.setText("No selected packaging");
					selectionDetailsLabel.setText("");
				}
				getContainer().updateButtons();
				
				m_selectionDetailsComposite.layout();
				
				m_selectionComposite.pack();
				m_selectionComposite.layout();
			}
		});

		m_selectionComposite = new Composite(m_pageComposite, SWT.NONE);
		gridLayout = new GridLayout(2, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		m_selectionComposite.setLayout(gridLayout);

		m_selectionBoxLabel = new Label(m_selectionComposite, SWT.NONE);

		Composite selectionDetailsComposite = new Composite(m_selectionComposite, SWT.NONE);
		gridLayout = new GridLayout(1, false);
		gridLayout.marginHeight = gridLayout.marginWidth = 0;
		selectionDetailsComposite.setLayout(gridLayout);

		m_selectionHeadingLabel = new Label(selectionDetailsComposite, SWT.NONE);
		m_selectionDetailsComposite = new Composite(selectionDetailsComposite, SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		rowLayout.marginLeft = 10;
		rowLayout.justify = true;
		rowLayout.spacing = 0;
		rowLayout.wrap = false;
		m_selectionDetailsComposite.setLayout(rowLayout);

		setControl(m_topComposite);
	}

	@Override
	protected void beforeDisplaySetup()
	{
		if(getInstallWizard().getDistroVariants() == null || getInstallWizard().getDistroVariants().size() == 0)
		{
			// dynamic width of m_noDistroLabel
			GridData layoutData = (GridData)m_noDistroLabel.getLayoutData();
			layoutData.widthHint = m_noDistroLabel.getShell().getSize().x - 20;
			m_noDistroPageComposite.layout();
			m_stackLayout.topControl = m_noDistroPageComposite;
			m_topComposite.layout();
		}
		else if(!m_initialized)
		{
			initializeTable();
			setupFilters();

			m_pageComposite.layout();
			m_stackLayout.topControl = m_pageComposite;
			m_topComposite.layout();
		}
		
		// distros are selected here
		getInstallWizard().setDistro(null);
	}

	private void setupFilters()
	{
		boolean compatible = false;
		boolean incompatible = false;
		boolean broken = false;
		
		for(DistroVariant variant : getInstallWizard().getDistroVariants())
		{
			if(variant.isCompatible() && !variant.isBroken())
				compatible = true;

			if(!variant.isCompatible() && !variant.isBroken())
				incompatible = true;

			if(variant.isBroken())
				broken = true;				
		}
		
		if(!incompatible)
			m_incompatibleButton.setEnabled(false);
		
		if(!broken)
			m_brokenButton.setEnabled(false);
		
		if(!compatible)
		{
			if(incompatible)
			{
				m_incompatibleButton.setSelection(true);
				m_incompatibleButton.notifyListeners(SWT.Selection, new Event());
			}
			else if(broken)
			{
				m_brokenButton.setSelection(true);
				m_brokenButton.notifyListeners(SWT.Selection, new Event());
			}
		}
	}

	private void initializeTable()
	{
		m_data = new ArrayList<DistroVariant>();

		for(DistroVariant variant : getInstallWizard().getDistroVariants())
		{
			if(!variant.isCompatible() && !variant.isBroken() && !m_incompatibleButton.getSelection()
					|| variant.isBroken() && !m_brokenButton.getSelection())
				continue;

			m_data.add(variant);
		}

		m_variantsTableViewer.setInput(m_data);
		m_variantsTableViewer.refresh();
		packColumns();
		selectFirstRow();
		setTableItemFontAndColor();
		m_initialized = true;
	}

	private void setTableItemFontAndColor()
	{
		TableItem[] items = m_variantsTableViewer.getTable().getItems();
		
		for(TableItem item : items)
		{
			DistroVariant variant = (DistroVariant)item.getData();
			
			if(variant.isBroken())
				item.setForeground(m_orangeColor);
			else
				item.setForeground(null);
			
			if(variant.isSimplePackaging())
				item.setFont(m_italicFont);
			else
				item.setFont(null);
		}
	}

	private void packColumns()
	{
		m_variantsTableViewer.getTable().setRedraw(false);
		setTableItemFontAndColor();
		for(TableColumn col : m_columns)
			col.pack();
		m_variantsTableViewer.getTable().setRedraw(true);
	}

	private void selectFirstRow()
	{
		Table table = m_variantsTableViewer.getTable();
		if(table.getItems().length > 0)
		{
			table.setSelection(0);
			table.showSelection();
			Event event = new Event();
			event.item = table.getItem(0);
			table.notifyListeners(SWT.Selection, event);
		}
	}

    @Override
	public boolean performPageCommit()
	{
    	if(isCurrentPage())
    	{
			TableItem[] selection = m_variantsTableViewer.getTable().getSelection();
			DistroVariant variant = (DistroVariant)selection[0].getData();
			
			getInstallWizard().retrieveDistro(variant.getDistroId());
    	}
    	
    	return true;
	}	
	
	@Override
	public boolean isPageComplete()
	{
		if(isCurrentPage())
		{
			if(getInstallWizard().getDistroVariants() == null || getInstallWizard().getDistroVariants().size() == 0)
				return false;
			
			TableItem[] selection = m_variantsTableViewer.getTable().getSelection();

			if(selection != null && selection.length == 1)
			{
				DistroVariant variant = (DistroVariant)selection[0].getData();

				if(variant.isBroken())
					return false;

				return true;
			}
		}
		else
			if(getInstallWizard().getDistro() != null)
				return true;

		return false;
	}
	
	private String getColumnValueFromVariant(DistroVariant variant, int column)
	{
		if(column > 0 && variant.isSimplePackaging())
			return UNSPECIFIED;
		
		switch(column)
		{
		case 0:
			return (variant.isBroken()
					? DISTRO_BROKEN
					: (variant.isCompatible()
							? DISTRO_COMPATIBLE
							: DISTRO_INCOMPATIBLE));
		case 1:
			return variant.getArch();

		case 2:
			return variant.getOS();

		case 3:
			return variant.getWS();

		case 4:
			return variant.getRelease();

		case 5:
			return variant.getNL();

		default:
			throw new IllegalArgumentException("Column number " + column + " does NOT exist.");
		}
	}
	

	private void addSelectionDetailsPlatformLabel(String property, boolean isCompatible)
	{
		if(property != null)
		{
			if(m_selectionDetailsComposite.getChildren().length > 0)
				addSelectionDetailsPlatformLabelSeparator();
			
			Label label = new Label(m_selectionDetailsComposite, SWT.NONE);
			label.setFont(m_boldFont);
			label.setText(property);
			if(!isCompatible)
				label.setForeground(m_redColor);
		}
	}
	
	private void addSelectionDetailsPlatformLabelSeparator()
	{
		Label comma = new Label(m_selectionDetailsComposite, SWT.NONE);
		comma.setFont(m_boldFont);
		comma.setText(", ");
	}
}
