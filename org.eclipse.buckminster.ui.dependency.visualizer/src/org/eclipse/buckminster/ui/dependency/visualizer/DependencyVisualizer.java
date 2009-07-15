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
package org.eclipse.buckminster.ui.dependency.visualizer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.metadata.model.BOMNode;
import org.eclipse.buckminster.core.metadata.model.BillOfMaterials;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.FilterControl;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.HighlightPathControl;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.LayoutControl;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.IViewerSettingChangeListener;
import org.eclipse.buckminster.ui.dependency.visualizer.controls.listener.ViewerSettingChangeEvent;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.DependencyViewer;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.DependencyLabelProvider;
import org.eclipse.buckminster.ui.dependency.visualizer.viewer.provider.DependencyTreeContentProvider;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePath;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Form;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.part.EditorPart;

/**
 * The main class for the dependency visualization.
 * 
 * This is the editor registered for bom files
 * 
 * @author Johannes Utzig
 * 
 */
public class DependencyVisualizer extends EditorPart
{

	private DependencyViewer graphViewer;

	private BillOfMaterials bom;

	private FormToolkit toolkit;

	private TreeViewer treeViewer;

	@Override
	public void createPartControl(Composite parent)
	{
		parent.setLayout(new FillLayout());

		Form form = toolkit.createForm(parent);
		form.setText("Dependency Visualization");
		parent = form.getBody();
		parent.setLayout(new GridLayout(2, false));
		SashForm sashForm = new SashForm(parent, SWT.HORIZONTAL | SWT.SMOOTH);
		GridDataFactory.fillDefaults().grab(true, true).applyTo(sashForm);
		createNavigationSection(sashForm);
		Section graphSection = toolkit.createSection(sashForm, ExpandableComposite.EXPANDED
				| ExpandableComposite.TITLE_BAR);
		sashForm.setWeights(new int[] { 25, 70 });

		graphSection.setText("Graph");

		graphSection.setLayout(new FillLayout());
		this.graphViewer = new DependencyViewer(graphSection, SWT.NONE);

		graphViewer.setInputAndRoot(Collections.singletonList(bom));

		graphSection.setClient(graphViewer.getControl());

		createActionsSection(parent);

	}

	/**
	 * this implementation does nothing
	 */
	@Override
	public void doSave(IProgressMonitor monitor)
	{
		// nothing to do

	}

	/**
	 * this implementation does nothing
	 */
	@Override
	public void doSaveAs()
	{
		// nothing to do

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException
	{
		setSite(site);
		setInput(input);

		toolkit = new FormToolkit(site.getShell().getDisplay());

		if(input instanceof IFileEditorInput)
		{
			IFileEditorInput editorInput = (IFileEditorInput)input;
			try
			{
				IParser<BillOfMaterials> parser = CorePlugin.getDefault().getParserFactory().getBillOfMaterialsParser(
						true);
				InputStream in = editorInput.getFile().getContents();
				bom = parser.parse(editorInput.getName(), in);
				setPartName(bom.getViewName());
				in.close();
			}
			catch(CoreException e)
			{
				Activator.getDefault().log(e);
			}
			catch(IOException e)
			{
				Activator.getDefault().log(e);
			}
		}

	}

	@Override
	public boolean isDirty()
	{
		return false;
	}

	@Override
	public boolean isSaveAsAllowed()
	{
		return false;
	}

	@Override
	public void setFocus()
	{
		graphViewer.getControl().setFocus();

	}

	private void createActionsSection(Composite parent)
	{
		Section actions = toolkit.createSection(parent, ExpandableComposite.EXPANDED | ExpandableComposite.TWISTIE
				| ExpandableComposite.COMPACT | ExpandableComposite.TITLE_BAR);

		actions.setLayout(new FillLayout());

		ScrolledComposite scroll = new ScrolledComposite(actions, SWT.V_SCROLL)
		{
			@Override
			public Point computeSize(int wHint, int hHint, boolean changed)
			{
				return new Point(getMinWidth(), 100);
			}
		};
		toolkit.adapt(scroll);

		Composite actionComposite = toolkit.createComposite(scroll);

		GridLayout layout = new GridLayout(1, true);
		layout.marginBottom = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginTop = 0;

		actionComposite.setLayout(layout);
		GridDataFactory.fillDefaults().applyTo(actionComposite);

		Section filterSection = toolkit.createSection(actionComposite, ExpandableComposite.COMPACT
				| ExpandableComposite.SHORT_TITLE_BAR | ExpandableComposite.EXPANDED);
		filterSection.setText("Filters");
		filterSection.setLayout(new GridLayout(1, true));
		filterSection.setClient(createFilterComposite(filterSection));
		GridDataFactory.fillDefaults().applyTo(filterSection);

		Section layoutSection = toolkit.createSection(actionComposite, ExpandableComposite.COMPACT
				| ExpandableComposite.SHORT_TITLE_BAR | ExpandableComposite.EXPANDED);
		layoutSection.setLayout(new FillLayout());
		layoutSection.setText("Layout");
		layoutSection.setClient(createLayoutComposite(layoutSection));
		GridDataFactory.fillDefaults().applyTo(layoutSection);

		Section pathSection = toolkit.createSection(actionComposite, ExpandableComposite.COMPACT
				| ExpandableComposite.SHORT_TITLE_BAR | ExpandableComposite.EXPANDED);
		pathSection.setLayout(new FillLayout());
		pathSection.setText("Path Highlighting");
		pathSection.setClient(createHighlightComposite(pathSection));
		GridDataFactory.fillDefaults().applyTo(pathSection);

		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);
		scroll.setMinSize(actionComposite.computeSize(SWT.DEFAULT, SWT.DEFAULT));

		scroll.setContent(actionComposite);

		scroll.layout();
		actions.setClient(scroll);
		actions.setText("Settings");
		GridDataFactory.fillDefaults().applyTo(actions);

	}

	private Control createFilterComposite(Section filterSection)
	{
		FilterControl control = new FilterControl(toolkit);
		control.addViewerSettingChangeListener(graphViewer);
		control.addViewerSettingChangeListener(new IViewerSettingChangeListener()
		{

			public void viewerSettingChanged(ViewerSettingChangeEvent event)
			{
				switch(event.getType())
				{
				case FILTER_ADDED:
					treeViewer.addFilter((ViewerFilter)event.getData());
					break;
				case FILTER_REMOVED:
					treeViewer.removeFilter((ViewerFilter)event.getData());
					break;
				default:
					break;
				}

			}
		});
		return control.createControl(filterSection);

	}

	private Control createHighlightComposite(Section layoutSection)
	{
		HighlightPathControl control = new HighlightPathControl(toolkit);
		control.addViewerSettingChangeListener(graphViewer);
		return control.createControl(layoutSection);
	}

	private Control createLayoutComposite(Section layoutSection)
	{
		LayoutControl control = new LayoutControl(toolkit);
		control.addViewerSettingChangeListener(graphViewer);
		return control.createControl(layoutSection);
	}

	private void createNavigationSection(Composite parent)
	{
		Section navigationSection = toolkit.createSection(parent, ExpandableComposite.EXPANDED
				| ExpandableComposite.TITLE_BAR | ExpandableComposite.COMPACT | ExpandableComposite.TWISTIE);
		GridDataFactory.fillDefaults().grab(false, true).applyTo(navigationSection);
		navigationSection.setText("Navgiation");
		navigationSection.setLayout(new FillLayout());

		treeViewer = new TreeViewer(toolkit.createTree(navigationSection, SWT.NONE));
		treeViewer.setAutoExpandLevel(1);
		treeViewer.setUseHashlookup(true);
		navigationSection.setClient(treeViewer.getControl());
		final DependencyTreeContentProvider treeContentProvider = new DependencyTreeContentProvider(treeViewer);
		treeViewer.setContentProvider(treeContentProvider);
		treeViewer.setLabelProvider(new DependencyLabelProvider());
		List<BOMNode> input = new ArrayList<BOMNode>();
		input.add(bom);
		treeViewer.setInput(input);
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener()
		{

			public void selectionChanged(SelectionChangedEvent event)
			{
				Object o = ((IStructuredSelection)event.getSelection()).getFirstElement();
				// zest seems to have issues with only one element in the graph (and it wouldn't make much sense anyway)
				if(treeContentProvider.hasChildren(o))
				{
					if(o instanceof BOMNode)
					{
						BOMNode node = (BOMNode)o;
						DependencyVisualizer.this.graphViewer.setInputAndRoot(Collections.singletonList(node));
					}
				}
				else
				{
					// set selection to match the tree
					if(event.getSelection() instanceof ITreeSelection)
					{
						ITreeSelection treeSelection = (ITreeSelection)event.getSelection();
						TreePath[] paths = treeSelection.getPaths();
						if(paths == null || paths.length == 0)
						{
							// the previously selected element got filtered out.
							// fall back to the root
							DependencyVisualizer.this.graphViewer.setInputAndRoot(Collections.singletonList(bom));
							DependencyVisualizer.this.graphViewer.setSelection(new StructuredSelection(bom), true);
							return;
						}
						TreePath path = paths[0].getParentPath();
						if(path != null)
						{
							Object parent = path.getLastSegment();
							if(parent instanceof BOMNode)
							{
								BOMNode node = (BOMNode)parent;
								DependencyVisualizer.this.graphViewer.setInputAndRoot(Collections.singletonList(node));
								DependencyVisualizer.this.graphViewer.setSelection(event.getSelection(), true);
							}

						}

					}

				}

			}
		});

	}

}
