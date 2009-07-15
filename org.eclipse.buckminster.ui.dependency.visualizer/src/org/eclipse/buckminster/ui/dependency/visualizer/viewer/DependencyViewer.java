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
package org.eclipse.buckminster.ui.dependency.visualizer.viewer;

import java.util.List;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.ui.actions.ViewChosenCSpecAction;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.IViewerSettingChangeListener;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingChangeEvent;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.DependencyContentProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.DependencyLabelProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.IDependencyVisualizationLabelProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.IRelationshipProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.SimpleDependencyVisualizationLabelProvider;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.zest.core.viewers.GraphViewer;
import org.eclipse.zest.core.widgets.ZestStyles;
import org.eclipse.zest.layouts.LayoutAlgorithm;

public class DependencyViewer extends GraphViewer implements IViewerSettingChangeListener
{

	private IDependencyVisualizationLabelProvider labelProvider;

	public DependencyViewer(Composite composite, int style)
	{
		super(composite, SWT.NONE);
		// setLayoutAlgorithm(new RadialLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
		// setLayoutAlgorithm(new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
		setConnectionStyle(ZestStyles.CONNECTIONS_DIRECTED);
		// labelProvider = new HighlightDependencyLableProvider(this, null);
		labelProvider = new SimpleDependencyVisualizationLabelProvider(new DependencyLabelProvider(), this);
		setLabelProvider(labelProvider);
		setContentProvider(new DependencyContentProvider());
		getGraphControl().addDisposeListener(new DisposeListener()
		{

			public void widgetDisposed(DisposeEvent e)
			{
				labelProvider.dispose();

			}
		});
		addSelectionChangedListener(new ISelectionChangedListener()
		{

			public void selectionChanged(SelectionChangedEvent event)
			{
				ISelection selection = event.getSelection();
				if(selection.isEmpty())
					labelProvider.highlightSelection(null);
				if(selection instanceof IStructuredSelection)
				{
					IStructuredSelection sel = (IStructuredSelection)selection;
					Object o = sel.getFirstElement();
					if(o instanceof BOMNode)
					{
						BOMNode node = (BOMNode)o;
						labelProvider.highlightSelection(node);
					}

				}
				// labelProvider.highlightSelection(selection)setCurrentSelection(((IStructuredSelection)event.getSelection()).getFirstElement(),
				// ((IStructuredSelection)event.getSelection()).getFirstElement());

			}
		});

		addDoubleClickListener(new IDoubleClickListener()
		{

			public void doubleClick(DoubleClickEvent event)
			{
				Object o = ((IStructuredSelection)event.getSelection()).getFirstElement();
				if(o instanceof BOMNode)
				{
					BOMNode node = (BOMNode)o;
					// List<BOMNode> input = Collections.singletonList(node);
					// setInput(input);
					// labelProvider.setCurrentSelection(node, node);
					DirtyViewCSpecAction action = new DirtyViewCSpecAction();
					action.run(node.getResolution().getCSpec(), PlatformUI.getWorkbench().getActiveWorkbenchWindow());

				}

			}
		});

	}

	/**
	 * calls {@link GraphViewer#setInput(Object)} and {@link IDependencyVisualizationLabelProvider#setRoot(BOMNode)}
	 * 
	 * @param input
	 *            - a list of boms with (currently) exactly one {@link BOMNode}
	 */
	public void setInputAndRoot(List<? extends BOMNode> input)
	{
		if(input.size() != 1)
			throw new IllegalArgumentException("List must contain exactly one bom so far");
		labelProvider.clear();
		setInput(input);
		BOMNode node = input.get(0);
		labelProvider.setRoot(node);
		labelProvider.highlightSelection(null);

	}

	/**
	 * overriden to inform the {@link IDependencyVisualizationLabelProvider} about the new selection
	 */
	@Override
	public void setSelection(ISelection selection, boolean reveal)
	{
		super.setSelection(selection, reveal);
		if(selection.isEmpty())
			labelProvider.highlightSelection(null);
		if(selection instanceof IStructuredSelection)
		{
			IStructuredSelection sel = (IStructuredSelection)selection;
			Object o = sel.getFirstElement();
			if(o instanceof BOMNode)
			{
				BOMNode node = (BOMNode)o;
				labelProvider.highlightSelection(node);
			}

		}
	}

	public void viewerSettingChanged(ViewerSettingChangeEvent event)
	{
		switch(event.getType())
		{
		case PATH_HIGHLIGHTING:
			setRelationshipProvider((IRelationshipProvider)event.getData());
			break;
		case FILTER_ADDED:
			addFilter((ViewerFilter)event.getData());
			applyLayout();
			break;
		case FILTER_REMOVED:
			removeFilter((ViewerFilter)event.getData());
			applyLayout();
			break;
		case LAYOUT_CHANGED:
			setLayoutAlgorithm((LayoutAlgorithm)event.getData());
			applyLayout();
		default:
			break;
		}

	}

	/**
	 * sets the {@link IRelationshipProvider} that is responsible for highlighting interesting paths on selection
	 * 
	 * @param provider
	 */
	private void setRelationshipProvider(IRelationshipProvider provider)
	{
		labelProvider.setRelationshipProvider(provider);

	}

}

// XXX: this is a hack. The CSpecEditorInput is not exported
// so ViewChosenCspecAction is subclassed instead to view a cspec
class DirtyViewCSpecAction extends ViewChosenCSpecAction
{
	@Override
	public void run(CSpec cspec, IWorkbenchWindow wbWin)
	{
		super.run(cspec, wbWin);
	}
}
