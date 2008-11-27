/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.providers;

import java.util.List;

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;
import org.eclipse.buckminster.generic.ui.utils.UiUtils;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.OutlineType;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.adapters.ComponentReference;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * A default LabelProvider for data elements found in Buckminster artifacts and views.
 * 
 * @author Henrik Lindberg
 *
 */
public class BuckminsterLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider
{
	private Image m_projectImage;
	private Image m_folderImage;
	private Image m_fileImage;
	private Image m_cspecImage;
	private Image m_componentImage;
	private Image m_rssImage;
	private Image m_htmlImage;
	private Image m_dependantImage;
	private Image m_dependencyImage;

	public BuckminsterLabelProvider()
	{
	}
	
	private Image getProjectImage()
	{
		if(m_projectImage == null)
			m_projectImage = UiPlugin.getImageDescriptor("icons/prj_obj.gif").createImage(); //$NON-NLS-1$
		return m_projectImage;
	}
	private Image getFolderImage()
	{
		if(m_folderImage == null)
			m_folderImage = UiPlugin.getImageDescriptor("icons/fldr_obj.gif").createImage(); //$NON-NLS-1$
		return m_folderImage;
	}
	private Image getFileImage()
	{
		if(m_fileImage == null)
			m_fileImage = UiPlugin.getImageDescriptor("icons/file_obj.gif").createImage(); //$NON-NLS-1$
		return m_fileImage;
	}
	private Image getCspecImage()
	{
		if(m_cspecImage == null)
			m_cspecImage = UiPlugin.getImageDescriptor("icons/cspec.png").createImage(); //$NON-NLS-1$
		return m_cspecImage;
	}
	private Image getComponentImage()
	{
		if(m_componentImage == null)
			m_componentImage = UiPlugin.getImageDescriptor("icons/component.png").createImage(); //$NON-NLS-1$
		return m_componentImage;
	}
	private Image getRssImage()
	{
		if(m_rssImage == null)
			m_rssImage = UiPlugin.getImageDescriptor("icons/rsslink.gif").createImage(); //$NON-NLS-1$
		return m_rssImage;
	}
	private Image getHtmlImage()
	{
		if(m_htmlImage == null)
			m_htmlImage = 	UiUtils.getImageDescriptor("file.html").createImage(); //$NON-NLS-1$
		return m_htmlImage;
	}
	private Image getDependantImage()
	{
		if(m_dependantImage == null)
			m_dependantImage = UiPlugin.getImageDescriptor("icons/dependent.png").createImage(); //$NON-NLS-1$
		return m_dependantImage;
	}
	private Image getDependencyImage()
	{
		if(m_dependencyImage == null)
			m_dependencyImage = UiPlugin.getImageDescriptor("icons/dependency.png").createImage(); //$NON-NLS-1$
		return m_dependencyImage;
	}
	
	@Override
	public Image getImage(Object selected)
	{
		Object element = selected;
		if(selected instanceof ITreeDataNode)
			element = ((ITreeDataNode)element).getData();
		
		if(element instanceof IProject)
			return getProjectImage();

		if(element instanceof IFolder)
			return getFolderImage();

		if(element instanceof IFile)
		{
			IFile file = (IFile) element;
			ImageDescriptor imageDescriptor = UiUtils.getImageDescriptor(file);
			return imageDescriptor == null ?
						getFileImage() 
					: 	UiUtils.getImage(imageDescriptor);
		}
		
		if(element instanceof ICSpecData)
			return getCspecImage();
		
		if(element instanceof IResolution)
			return getComponentImage();
		
		// OPML stuff
		if(element instanceof IOPML)
			return getFolderImage();
		
		if(element instanceof IOutline)
		{
			// An outline that has sub-outlines is shown as a folder
			//
			List<? extends IOutline> outlines = ((IOutline)element).getOutlines();
			if(((IOutline)element).getType() == OutlineType.UNKNOWN || ((IOutline)element).getType() == OutlineType.TEXT || (outlines != null && outlines.size() > 0))
				return getFolderImage();
			// An outline that is a link is shown as a browseable image
			if(((IOutline)element).getType() == OutlineType.LINK)
				return getHtmlImage();
			return getRssImage();
		}
		if(element instanceof ComponentReference)
			return ((ComponentReference)element).getMode() == ComponentReference.Mode.IN
				? getDependantImage()
				: getDependencyImage();

		// Parents default to Folder
		if(selected instanceof BasicTreeParentDataNode)
			return getFolderImage();
		
		return null;
	}

	/**
	 * Returns the name of an IResourceElement using getName(), else element.toString() is used.
	 */
	@Override
	public String getText(Object element)
	{
		return getStyledText(element).toString();
	}

	@Override
	public void dispose()
	{
		if(m_projectImage != null)
			m_projectImage.dispose();
		if(m_folderImage != null)
			m_folderImage.dispose();
		if(m_fileImage != null)
			m_fileImage.dispose();
		if(m_cspecImage != null)
			m_cspecImage.dispose();
		if(m_componentImage != null)
			m_componentImage.dispose();
		if(m_htmlImage != null)
			m_htmlImage.dispose();
		if(m_dependencyImage != null)
			m_dependencyImage.dispose();
		if(m_dependantImage != null)
			m_dependantImage.dispose();
		
		// note - do not dispose of images that were not created !
		super.dispose();
	}

	public StyledString getStyledText(Object element)
	{
		if(element instanceof ITreeDataNode)
			element = ((ITreeDataNode)element).getData();
		if(element instanceof IResource)
			return new StyledString(((IResource)element).getName());
		
		if(element instanceof IResolution)
		{
			Resolution r = (Resolution)element;
			StyledString bld = new StyledString(r.getName());
			String type = r.getComponentTypeId();
			if(type != null)
			{
				bld.append(" : ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(type, StyledString.DECORATIONS_STYLER);
			}
			IVersion version = r.getVersion();
			if(version != null)
			{
				bld.append(" - ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(version.toString(), StyledString.DECORATIONS_STYLER);
			}
			return bld;
		}
		if(element instanceof ComponentReference)
		{
			ComponentReference ref = (ComponentReference)element;
			StyledString bld = new StyledString(ref.getComponentName());
			ComponentRequest req = ref.getComponentRequest();
			if(req.getComponentTypeID() != null)
			{
				bld.append(" : ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(req.getComponentTypeID(), StyledString.DECORATIONS_STYLER);
			}
			if(req.getVersionDesignator() != null)
			{
				bld.append(" - ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(req.getVersionDesignator().toString(), StyledString.DECORATIONS_STYLER);
			}
			return bld;
		}
			
		if(element instanceof ICSpecData)
		{
			return new StyledString(Messages.component_specification);
		}
		if(element instanceof IOPML)
		{
			return new StyledString(Messages.component_information);
		}
		if(element instanceof IOutline)
		{
			IOutline outline = (IOutline)element;
			return new StyledString(outline.getText());
		}
		return new StyledString(element.toString());
	}

}
