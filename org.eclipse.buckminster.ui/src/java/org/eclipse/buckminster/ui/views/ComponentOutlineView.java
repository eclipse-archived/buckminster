/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.views;

import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.ui.providers.ResolutionsTreeContentProvider;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.model.IWorkbenchAdapter;
import org.eclipse.ui.part.EditorPart;

/**
 * The ComponentOutlineView shows the outline of one component. The Outline changes as the selected page
 * changes. The Resolution for the currently selected item is obtained, and an outline is produced for
 * this resolution.
 * 
 * @author Henrik Lindberg
 *
 */
public class ComponentOutlineView extends ComponentBrowserView
{
	private ISelectionListener m_selectionListener;
		
	/**
	 * Call-back that creates and initializes the viewer.
	 */
	@Override
	public void createPartControl(Composite parent)
	{
		super.createPartControl(parent);
		hookPageSelection();
	}	
	@Override
	protected ResolutionsTreeContentProvider getContentProvider()
	{
		return new ResolutionsTreeContentProvider(ResolutionsTreeContentProvider.Mode.SINGLE);
	}

	private void pageSelectionChanged(IWorkbenchPart part, ISelection selection)
	{
		if(part == this)
			return;

		Resolution r = null;
		if(part instanceof EditorPart)
		{
			IEditorInput input = ((EditorPart)part).getEditorInput();
			IResource resource = (IResource)input.getAdapter(IResource.class);
			if(resource != null)
				r = (Resolution)resource.getAdapter(Resolution.class);
			else
				return; // If editor is not for a resource, stay on the same component
		}
		else
		{
			if(!(selection instanceof IStructuredSelection))
				return;
			IStructuredSelection sel = (IStructuredSelection)selection;
			
			if(!sel.isEmpty() && (sel.getFirstElement() instanceof IAdaptable))
			{			
				IAdaptable element = (IAdaptable)sel.getFirstElement();
				// If adaptable to Resolution directly
				r = (Resolution)element.getAdapter(Resolution.class);
				
				if(r == null)
				{
					// Try to adapt it to Resource first
					IResource resource = (IResource)element.getAdapter(IResource.class);
					if(resource != null)
					{
						// get the project of the resource
						resource = resource.getProject();
						r = (Resolution)resource.getAdapter(Resolution.class);				
					}
				}
				if(r == null)
				{
					IWorkbenchAdapter wbAdapter = (IWorkbenchAdapter)element.getAdapter(IWorkbenchAdapter.class);
					while(wbAdapter != null)
					{
						IResource resource = null;
						Object parent = wbAdapter.getParent(element);
						if(parent != null && parent instanceof IAdaptable)
							resource = (IResource)((IAdaptable)parent).getAdapter(IResource.class);
						if(resource != null)
							r = (Resolution)resource.getAdapter(Resolution.class);
						if(r != null)
							break;
						
						wbAdapter = (parent == null || !(parent instanceof IAdaptable) )
								? null 
								: (IWorkbenchAdapter)((IAdaptable)parent).getAdapter(IWorkbenchAdapter.class);
						if(wbAdapter != null)
							element = (IAdaptable)parent;
					}
						
				}
			}
			
		}
		Object input = r == null ? getViewSite() : r;
		if(m_viewer.getInput() == input)
			return;
		m_viewer.setInput(input);
		m_viewer.expandAll();
	}

	private void hookPageSelection()
	{
		m_selectionListener = new ISelectionListener()
		{
			public void selectionChanged(IWorkbenchPart part, ISelection selection)
			{
				pageSelectionChanged(part, selection);
			}
		};
	getSite().getPage().addPostSelectionListener(m_selectionListener);
	}
	@Override
	public void dispose()
	{
		if(m_selectionListener != null)
			getSite().getPage().removePostSelectionListener(m_selectionListener);
		super.dispose();
	}
	@Override
	public boolean isAutoExpand()
	{
		return true;
	}
	
}
