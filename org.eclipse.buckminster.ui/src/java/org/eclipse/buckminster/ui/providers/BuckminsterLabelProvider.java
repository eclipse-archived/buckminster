/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.providers;

import org.eclipse.buckminster.opml.model.OPML;
import org.eclipse.buckminster.opml.model.Outline;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;

import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;
import org.eclipse.buckminster.generic.ui.utils.UiUtils;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;


/**
 * A default LabelProvider for data elements found in Buckminster artifacts and views.
 * 
 * @author Henrik Lindberg
 *
 */
public class BuckminsterLabelProvider extends LabelProvider
{
	private Image m_projectImage;
	private Image m_folderImage;
	private Image m_fileImage;
	private Image m_cspecImage;
	private Image m_componentImage;

	public BuckminsterLabelProvider()
	{
	}
	
	private Image getProjectImage()
	{
		if(m_projectImage == null)
			m_projectImage = UiPlugin.getImageDescriptor("icons/prj_obj.gif").createImage();
		return m_projectImage;
	}
	private Image getFolderImage()
	{
		if(m_folderImage == null)
			m_folderImage = UiPlugin.getImageDescriptor("icons/fldr_obj.gif").createImage();
		return m_folderImage;
	}
	private Image getFileImage()
	{
		if(m_fileImage == null)
			m_fileImage = UiPlugin.getImageDescriptor("icons/file_obj.gif").createImage();
		return m_fileImage;
	}
	private Image getCspecImage()
	{
		if(m_cspecImage == null)
			m_cspecImage = UiPlugin.getImageDescriptor("icons/cspec.png").createImage();
		return m_cspecImage;
	}
	private Image getComponentImage()
	{
		if(m_componentImage == null)
			m_componentImage = UiPlugin.getImageDescriptor("icons/component.png").createImage();
		return m_componentImage;
	}
	@Override
	public Image getImage(Object element)
	{
		if(element instanceof ITreeDataNode)
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
		
		if(element instanceof CSpec)
			return getCspecImage();
		
		if(element instanceof Resolution)
			return getComponentImage();
		return null;
	}

	/**
	 * Returns the name of an IResourceElement using getName(), else element.toString() is used.
	 */
	@Override
	public String getText(Object element)
	{
		if(element instanceof ITreeDataNode)
			element = ((ITreeDataNode)element).getData();
		if(element instanceof IResource)
			return ((IResource)element).getName();
		if(element instanceof Resolution)
		{
			Resolution r = (Resolution)element;
			StringBuilder bld = new StringBuilder(r.getName());
			String type = r.getComponentTypeId();
			if(type != null)
			{
				bld.append(" : ");
				bld.append(type);
			}
			IVersion version = r.getVersion();
			if(version != null)
			{
				bld.append(" - ");
				bld.append(version.toString());
			}
			return bld.toString();
		}
		if(element instanceof CSpec)
		{
			return "Component Specification (CSpec)";
		}
		if(element instanceof OPML)
		{
			return "Feeds (OPML)";
		}
		if(element instanceof Outline)
		{
			Outline outline = (Outline)element;
			return outline.getText();
		}
		return element.toString();
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
		
		// note - do not dispose of images that were not created !
		super.dispose();
	}

}
