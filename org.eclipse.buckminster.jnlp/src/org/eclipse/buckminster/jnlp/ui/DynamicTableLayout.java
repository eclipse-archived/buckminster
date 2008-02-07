/*******************************************************************************
 * Copyright (c) 2004, 2005
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/

package org.eclipse.buckminster.jnlp.ui;

import java.util.ArrayList;

import org.eclipse.jface.viewers.ColumnLayoutData;
import org.eclipse.jface.viewers.ColumnPixelData;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

/**
 * A dynamic layout for a table that will resize the columns according to
 * the client area. The layout will also deal with the fact that a vertical
 * scrollbar may or may not be visible.<br/>
 * Call {@link #addColumnData(ColumnLayoutData)} to add columns.
 * @author Thomas Hallgren
 */
public class DynamicTableLayout extends Layout
{
	/**
	 * The number of extra pixels taken as horizontal trim by the table column.
	 * To ensure there are N pixels available for the content of the column,
	 * assign N+COLUMN_TRIM for the column width.
	 * @since 3.1
	 */
	private static final int COLUMN_TRIM = "carbon".equals(SWT.getPlatform()) ? 24 : 3; //$NON-NLS-1$

	/**
	 * The list of column layout data.
	 */
	private final ArrayList<ColumnLayoutData> m_columns = new ArrayList<ColumnLayoutData>();
	
	/**
	 * Minimum height of the table.
	 */
	private final int m_minimumHeight;

	/**
	 * Creates a new table layout.
	 */
	public DynamicTableLayout(int minimumHeight)
	{
		m_minimumHeight = minimumHeight;
	}

	/**
	 * Adds a new column of data to this table layout.
	 * @param data the column layout data
	 */
	public void addColumnData(ColumnLayoutData data)
	{
		if(!((data instanceof ColumnPixelData) || (data instanceof ColumnWeightData)))
			throw new IllegalArgumentException("Unknown ColumnLayoutData derivate");
		m_columns.add(data);
	}

	/*
	 * (non-Javadoc) Method declared on Layout.
	 */
	@Override
	public Point computeSize(Composite c, int wHint, int hHint, boolean flush)
	{
		if(wHint != SWT.DEFAULT && hHint != SWT.DEFAULT)
			return new Point(wHint, hHint);

		Table table = (Table)c;

		// To avoid recursions.
		//
		table.setLayout(null);
		Point result;
		try
		{
			// Use native layout algorithm
			//
			result = table.computeSize(wHint, hHint, flush);
		}
		finally
		{
			table.setLayout(this);
		}

		int width = 0;
		for(ColumnLayoutData layoutData : m_columns)
		{
			if(layoutData instanceof ColumnPixelData)
			{
				ColumnPixelData col = (ColumnPixelData)layoutData;
				width += col.width;
				if(col.addTrim)
					width += COLUMN_TRIM;
			}
			else
			{
				ColumnWeightData col = (ColumnWeightData)layoutData;
				width += col.minimumWidth;
			}
		}
		if(width > result.x)
			result.x = width;
		if(m_minimumHeight > result.y)
			result.y = m_minimumHeight;
		return result;
	}

	/*
	 * (non-Javadoc) Method declared on Layout.
	 */
	@Override
	public void layout(Composite c, boolean flush)
	{
		Table table = (Table)c;
		int width = table.getClientArea().width;

		// Layout is being called with an invalid value the first time
		// it is being called on Linux. This method resets the
		// Layout to null so we make sure we run it only when
		// the value is OK.
		//
		if(width <= 1)
			return;

		ScrollBar vScroll = table.getVerticalBar();
		if(vScroll != null && !vScroll.isEnabled())
			width += vScroll.getSize().x;

		TableColumn[] tableColumns = table.getColumns();
		int size = Math.min(m_columns.size(), tableColumns.length);
		int[] widths = new int[size];
		int fixedWidth = 0;
		int numberOfWeightColumns = 0;
		int totalWeight = 0;

		// First calc space occupied by fixed columns
		for(int idx = 0; idx < size; idx++)
		{
			ColumnLayoutData col = m_columns.get(idx);
			if(col instanceof ColumnPixelData)
			{
				ColumnPixelData cpd = (ColumnPixelData)col;
				int pixels = cpd.width;
				if(cpd.addTrim)
					pixels += COLUMN_TRIM;
				widths[idx] = pixels;
				fixedWidth += pixels;
			}
			else
			{
				numberOfWeightColumns++;
				totalWeight += ((ColumnWeightData)col).weight;
			}
		}

		// Do we have columns that have a weight
		//
		if(numberOfWeightColumns > 0)
		{
			// Now distribute the rest to the columns with weight.
			//
			int rest = width - fixedWidth;
			int totalDistributed = 0;
			for(int idx = 0; idx < size; ++idx)
			{
				ColumnLayoutData col = m_columns.get(idx);
				if(col instanceof ColumnWeightData)
				{
					ColumnWeightData cw = (ColumnWeightData)col;
					// calculate weight as above
					// int weight = firstTime ? cw.weight :
					// tableColumns[i].getWidth();
					int weight = cw.weight;
					int pixels = totalWeight == 0 ? 0 : weight * rest / totalWeight;
					if(pixels < cw.minimumWidth)
						pixels = cw.minimumWidth;
					totalDistributed += pixels;
					widths[idx] = pixels;
				}
			}

			// Distribute any remaining pixels to columns with weight.
			//
			int diff = rest - totalDistributed;
			for(int idx = 0; diff > 0; ++idx)
			{
				if(idx == size)
					idx = 0;
				ColumnLayoutData col = m_columns.get(idx);
				if(col instanceof ColumnWeightData)
				{
					++widths[idx];
					--diff;
				}
			}
		}

		for(int idx = 0; idx < size; idx++)
			tableColumns[idx].setWidth(widths[idx]);
	}
}
