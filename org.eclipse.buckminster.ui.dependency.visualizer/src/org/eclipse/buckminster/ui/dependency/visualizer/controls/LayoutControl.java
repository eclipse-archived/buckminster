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
package org.eclipse.buckminster.ui.dependency.visualizer.controls;

import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingChangeEvent;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingType;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.zest.layouts.LayoutAlgorithm;
import org.eclipse.zest.layouts.LayoutStyles;
import org.eclipse.zest.layouts.algorithms.CompositeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.DirectedGraphLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.GridLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.HorizontalShift;
import org.eclipse.zest.layouts.algorithms.HorizontalTreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.RadialLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
import org.eclipse.zest.layouts.algorithms.VerticalLayoutAlgorithm;

/**
 * this control lets the user picked a {@link LayoutAlgorithm} for the graph viewer
 * 
 * @author Johannes Utzig
 * 
 */
public class LayoutControl extends AbstractViewerSettingControl
{

	class LayoutSelectionListener extends SelectionAdapter
	{
		private LayoutAlgorithm algorithm;

		public LayoutSelectionListener(LayoutAlgorithm algorithm)
		{
			super();
			this.algorithm = algorithm;
		}

		@Override
		public void widgetSelected(SelectionEvent e)
		{
			if(currentSelection != algorithm)
			{
				currentSelection = algorithm;
				fireViewerSettingsChangedEvent(new ViewerSettingChangeEvent(LayoutControl.this,
						ViewerSettingType.LAYOUT_CHANGED, algorithm, currentSelection));
			}

		}

	}

	private LayoutAlgorithm currentSelection;

	public LayoutControl(FormToolkit toolkit)
	{
		super(toolkit);

	}

	@Override
	public Control createControl(Composite parent)
	{

		Composite layoutComposite = getWidgetToolkit().createComposite(parent);
		layoutComposite.setLayout(new GridLayout(1, true));
		final Button spring = getWidgetToolkit().createButton(layoutComposite, "Spring Layout", SWT.RADIO);
		final Button directed = getWidgetToolkit().createButton(layoutComposite, "Directed Layout", SWT.RADIO);
		final Button grid = getWidgetToolkit().createButton(layoutComposite, "Grid Layout", SWT.RADIO);
		final Button horizontal = getWidgetToolkit().createButton(layoutComposite, "Horizontal Layout", SWT.RADIO);
		final Button vertical = getWidgetToolkit().createButton(layoutComposite, "Vertical Layout", SWT.RADIO);
		final Button tree = getWidgetToolkit().createButton(layoutComposite, "Tree Layout", SWT.RADIO);
		final Button horizontalTree = getWidgetToolkit().createButton(layoutComposite, "Horizontal Tree Layout",
				SWT.RADIO);
		final Button radial = getWidgetToolkit().createButton(layoutComposite, "Radial Layout", SWT.RADIO);

		tree.setSelection(true);
		LayoutAlgorithm algo = addHorizontalShift(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
		spring.addSelectionListener(new LayoutSelectionListener(algo));

		algo = addHorizontalShift(addHorizontalShift(new DirectedGraphLayoutAlgorithm(
				LayoutStyles.NO_LAYOUT_NODE_RESIZING)));
		directed.addSelectionListener(new LayoutSelectionListener(algo));

		algo = addHorizontalShift(addHorizontalShift(new GridLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING)));
		grid.addSelectionListener(new LayoutSelectionListener(algo));

		algo = addHorizontalShift(addHorizontalShift(new HorizontalLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING)));
		horizontal.addSelectionListener(new LayoutSelectionListener(algo));

		algo = addHorizontalShift(addHorizontalShift(new VerticalLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING)));
		vertical.addSelectionListener(new LayoutSelectionListener(algo));

		algo = addHorizontalShift(addHorizontalShift(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING)));
		// set default
		currentSelection = algo;
		tree.addSelectionListener(new LayoutSelectionListener(algo));

		algo = addHorizontalShift(addHorizontalShift(new HorizontalTreeLayoutAlgorithm(
				LayoutStyles.NO_LAYOUT_NODE_RESIZING)));
		horizontalTree.addSelectionListener(new LayoutSelectionListener(algo));

		algo = addHorizontalShift(addHorizontalShift(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING)));
		radial.addSelectionListener(new LayoutSelectionListener(algo));

		fireViewerSettingsChangedEvent(new ViewerSettingChangeEvent(this, ViewerSettingType.LAYOUT_CHANGED,
				currentSelection, null));
		return layoutComposite;
	}

	private LayoutAlgorithm addHorizontalShift(LayoutAlgorithm layoutAlgorithm)
	{
		return new CompositeLayoutAlgorithm(new LayoutAlgorithm[] { layoutAlgorithm,
				new HorizontalShift(LayoutStyles.NO_LAYOUT_NODE_RESIZING) });
	}
}
