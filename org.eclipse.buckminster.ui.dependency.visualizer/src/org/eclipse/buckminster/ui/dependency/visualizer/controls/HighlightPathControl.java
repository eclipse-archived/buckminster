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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingChangeEvent;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingType;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.IRelationshipProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.internal.AllPathsToRootRelationshipProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.internal.CompositeRelationshipProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.internal.DirectRelationshipProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.internal.ShortestPathToRootRelationshipProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.internal.UsageRelationshipProvider;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.ui.forms.widgets.FormToolkit;

/**
 * this control allows the user to change what kinds of paths should be highlighted on selection in the viewer
 * 
 * @author Johannes Utzig
 * 
 */
public class HighlightPathControl extends AbstractViewerSettingControl implements SelectionListener
{

	private Button dependencies;

	private Button usage;

	private IRelationshipProvider relationshipProvider;

	private Button shortestPath;

	private Button allPaths;

	private Button noPath;

	public HighlightPathControl(FormToolkit toolkit)
	{
		super(toolkit);

	}

	@Override
	public Control createControl(Composite parent)
	{
		parent = getWidgetToolkit().createComposite(parent);
		parent.setLayout(new GridLayout(1, true));
		dependencies = getWidgetToolkit().createButton(parent, "Dependencies", SWT.CHECK);
		dependencies.setSelection(true);
		dependencies.addSelectionListener(this);
		usage = getWidgetToolkit().createButton(parent, "Usage", SWT.CHECK);
		usage.setSelection(true);
		usage.addSelectionListener(this);

		Group pathToRootDetails = new Group(parent, SWT.SHADOW_ETCHED_IN);
		getWidgetToolkit().adapt(pathToRootDetails);
		pathToRootDetails.setText("Path to Root");
		pathToRootDetails.setLayout(new GridLayout(1, false));
		GridDataFactory.fillDefaults().applyTo(pathToRootDetails);

		shortestPath = getWidgetToolkit().createButton(pathToRootDetails, "Shortest", SWT.RADIO);
		shortestPath.setSelection(false);
		shortestPath.addSelectionListener(this);
		allPaths = getWidgetToolkit().createButton(pathToRootDetails, "All", SWT.RADIO);
		allPaths.setSelection(true);
		allPaths.addSelectionListener(this);
		noPath = getWidgetToolkit().createButton(pathToRootDetails, "None", SWT.RADIO);
		noPath.setSelection(false);
		noPath.addSelectionListener(this);

		// inform about default config
		widgetSelected(null);
		return parent;
	}

	public void widgetDefaultSelected(SelectionEvent e)
	{
		// nothing to do

	}

	public void widgetSelected(SelectionEvent e)
	{
		IRelationshipProvider provider = createRelationshipProvider();
		fireViewerSettingsChangedEvent(new ViewerSettingChangeEvent(this, ViewerSettingType.PATH_HIGHLIGHTING,
				provider, relationshipProvider));
		relationshipProvider = provider;

	}

	private IRelationshipProvider createRelationshipProvider()
	{
		List<IRelationshipProvider> provider = new ArrayList<IRelationshipProvider>();
		if(dependencies.getSelection())
			provider.add(new DirectRelationshipProvider());
		if(usage.getSelection())
			provider.add(new UsageRelationshipProvider());

		if(shortestPath.getSelection())
		{
			provider.add(new ShortestPathToRootRelationshipProvider());
		}
		else if(allPaths.getSelection())
		{
			provider.add(new AllPathsToRootRelationshipProvider());
		}

		return new CompositeRelationshipProvider(provider.toArray(new IRelationshipProvider[provider.size()]));

	}

}
