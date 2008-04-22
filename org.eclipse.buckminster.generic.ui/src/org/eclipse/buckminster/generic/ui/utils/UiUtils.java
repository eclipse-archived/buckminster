package org.eclipse.buckminster.generic.ui.utils;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.resource.ImageDescriptor;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbench;

public class UiUtils
{
	/**
	 * Gets an ImageDescriptor for the File based on what is registered in the editor
	 * registry.
	 * 
	 * @param file
	 * @return
	 */
	public static ImageDescriptor getImageDescriptor(IFile file)
	{
		return getImageDescriptor(getWorkbench(), file.getName());

	}
	public static ImageDescriptor getImageDescriptor(String fileName)
	{
		return getImageDescriptor(getWorkbench(), fileName);
	}
	
	public static ImageDescriptor getImageDescriptor(IWorkbench workbench, String fileName)
	{
		return workbench.getEditorRegistry().getImageDescriptor(fileName);
	}
	
	/**
	 * Get the workbench when there is not other starting point.
	 * This method uses a restricted API to get the workbench from UIPlugin.
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static IWorkbench getWorkbench()
	{
		return org.eclipse.ui.internal.UIPlugin.getDefault().getWorkbench();
	}
	
	/**
	 * Get the workbench from a view part.
	 * @param viewPart
	 * @return
	 */
	public static IWorkbench getWorkbench(IViewPart viewPart)
	{
		return viewPart.getViewSite().getWorkbenchWindow().getWorkbench();
	}
	
	/**
	 * Returns an image for a descriptor for the default Display. Use this methods for 
	 * created images (that should not be disposed).
	 * @param imageDescriptor
	 * @return
	 */
	public static Image getImage(ImageDescriptor imageDescriptor)
	{
		return new Image(Display.getDefault(), imageDescriptor.getImageData());		
	}
}
