/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.equinox.p2.authoring.P2AuthoringImages;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.ArtifactKeyBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.Parameter;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.ProvidedCapabilityBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.RequiredCapabilityBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointActionBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointDataBuilder;
import org.eclipse.equinox.p2.authoring.internal.InstallableUnitBuilder.TouchpointInstructionBuilder;
import org.eclipse.equinox.p2.authoring.internal.touchpoints.UnknownTouchpoint;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointActionDescriptor;
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;

/**
 * A default LabelProvider for data elements found in P2 authoring artifacts and views.
 * 
 * @author Henrik Lindberg
 * 
 */
public class P2AuthoringLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider, ILabelProvider
{
	public static final String NS_JAVA_PACKAGE = "java.package"; //$NON-NLS-1$

	public static final String NS_OSGI_BUNDLE = "osgi.bundle"; //$NON-NLS-1$

	public static final String NS_ECLIPSE = "org.eclipse.equinox.p2.eclipse.type"; //$NON-NLS-1$

	public static final String NS_IU = "org.eclipse.equinox.p2.iu"; //$NON-NLS-1$

	public P2AuthoringLabelProvider()
	{
	}

//	private Image getHtmlImage()
//	{
//		return P2AuthoringImages.getImageDescriptorForFile("file.html").createImage();
//	}
	
	/**
	 * Returns an image for a descriptor for the default Display. Use this methods for 
	 * created images (that should not be disposed).
	 * @param imageDescriptor
	 * @return
	 */
	public static Image getImageFromDescriptor(ImageDescriptor imageDescriptor)
	{
		return new Image(Display.getDefault(), imageDescriptor.getImageData());		
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
	 * Get the workbench when there is not other starting point. This method uses a restricted API to get the workbench
	 * from UIPlugin.
	 * 
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static IWorkbench getWorkbench()
	{
		return org.eclipse.ui.internal.UIPlugin.getDefault().getWorkbench();
	}

	public Image getRequiredCapabilityImage(RequiredCapabilityBuilder required)
	{
		String namespace = required.getNamespace();
		if(namespace.equals(NS_JAVA_PACKAGE))
			return P2AuthoringImages.getIMG_PACKAGE();
		if(namespace.equals(NS_IU))
			return P2AuthoringImages.getIMG_IU();
		if(namespace.equals(NS_ECLIPSE))
			return P2AuthoringImages.getIMG_PLUGIN();
		if(namespace.equals(NS_OSGI_BUNDLE))
			return P2AuthoringImages.getIMG_BUNDLE();

		// unknown namespace - return image for "required capability"
		return P2AuthoringImages.getIMG_REQ_CAPABILITY();
	}
	public Image getProvidedCapabilityImage(ProvidedCapabilityBuilder provided)
	{
		String namespace = provided.getNamespace();
		if(namespace.equals(NS_JAVA_PACKAGE))
			return P2AuthoringImages.getIMG_PACKAGE();
		if(namespace.equals(NS_IU))
			return P2AuthoringImages.getIMG_IU();
		if(namespace.equals(NS_ECLIPSE))
			return P2AuthoringImages.getIMG_PLUGIN();
		if(namespace.equals(NS_OSGI_BUNDLE))
			return P2AuthoringImages.getIMG_BUNDLE();

		// unknown namespace - return image for "provided capability"
		return P2AuthoringImages.getIMG_PROV_CAPABILITY();
	}

	@Override
	public Image getImage(Object selected)
	{
		Object element = selected;
		if(selected instanceof RequiredCapabilityBuilder)
			return getRequiredCapabilityImage((RequiredCapabilityBuilder)selected);

		if(selected instanceof ProvidedCapabilityBuilder)
			return getProvidedCapabilityImage((ProvidedCapabilityBuilder)selected);

		if(element instanceof IProject)
			return P2AuthoringImages.getIMG_PROJECT();

		if(element instanceof IFolder)
			return P2AuthoringImages.getIMG_FOLDER();

		if(element instanceof IFile)
		{
			IFile file = (IFile)element;
			ImageDescriptor imageDescriptor = getImageDescriptor(file.getName());
			return imageDescriptor == null
					? P2AuthoringImages.getIMG_FILE()
					: getImageFromDescriptor(imageDescriptor);
		}
		if(element instanceof ArtifactKeyBuilder)
			return P2AuthoringImages.getIMG_FILE();

		if(element instanceof TouchpointDataBuilder || element instanceof TouchpointInstructionBuilder)
			return P2AuthoringImages.getIMG_FOLDER();

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
		// note - do not dispose of images that were not created !
		super.dispose();
	}

	public StyledString getStyledText(Object element)
	{
		if(element instanceof IResource)
			return new StyledString(((IResource)element).getName());

		if(element instanceof RequiredCapabilityBuilder)
		{
			RequiredCapabilityBuilder req = (RequiredCapabilityBuilder)element;
			StyledString bld = new StyledString(req.getName());
			bld.append(" : ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
			bld.append(req.getRange(), StyledString.DECORATIONS_STYLER);
			return bld;

		}
		if(element instanceof ProvidedCapabilityBuilder)
		{
			ProvidedCapabilityBuilder req = (ProvidedCapabilityBuilder)element;
			StyledString bld = new StyledString(req.getName());
			bld.append(" : ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
			bld.append(req.getVersion(), StyledString.DECORATIONS_STYLER);
			return bld;

		}
		if(element instanceof ArtifactKeyBuilder)
		{
			ArtifactKeyBuilder artifact = (ArtifactKeyBuilder)element;
			StyledString bld = new StyledString(artifact.getId());
			bld.append(" : ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
			bld.append(artifact.getVersion(), StyledString.DECORATIONS_STYLER);
			return bld;

		}
		if(element instanceof TouchpointDataBuilder)
		{
			StyledString bld = new StyledString(((TouchpointDataBuilder)element).getName());
			return bld;
		}
		if(element instanceof TouchpointInstructionBuilder)
		{
			StyledString bld = new StyledString(((TouchpointInstructionBuilder)element).getPhaseId());
			bld.append(" (", StyledString.COUNTER_STYLER); //$NON-NLS-1$
			TouchpointActionBuilder[] actions = ((TouchpointInstructionBuilder)element).getActions();
			bld.append(Integer.toString(actions == null
					? 0
					: actions.length), StyledString.COUNTER_STYLER);
			bld.append(")", StyledString.COUNTER_STYLER); //$NON-NLS-1$
			return bld;
		}
		if(element instanceof TouchpointActionBuilder)
		{
			TouchpointActionBuilder action = (TouchpointActionBuilder)element;
			StringBuilder buffer = new StringBuilder();
			action.append(buffer);
			StyledString bld = new StyledString();
			// if string > limit, output "..." instead of parameter:value sequence
			if(buffer.length() > 40)
			{
				bld.append(action.getActionKey());
				bld.append("("); //$NON-NLS-1$
				bld.append("...", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(")"); //$NON-NLS-1$
			}
			else
			{
				bld.append(action.getActionKey());
				bld.append("("); //$NON-NLS-1$
				boolean first = true;
				Parameter[] params = action.getParameters();
				for(int i = 0; i < params.length; i++)
				{
					if(!first)
						bld.append(", "); //$NON-NLS-1$
					bld.append(params[i].getName(), StyledString.QUALIFIER_STYLER);
					bld.append(": ", StyledString.QUALIFIER_STYLER); //$NON-NLS-1$
					bld.append(params[i].getValue());
					first = false;
				}
				bld.append(")"); //$NON-NLS-1$
			}
			return bld;

		}
		if(element instanceof ITouchpointTypeDescriptor)
		{
			// use original touchpoint type info if this is an unknown touchpoint
			ITouchpointTypeDescriptor ttd = (ITouchpointTypeDescriptor)element;
			if(ttd.isNull())
				return new StyledString("None");
			
			StyledString bld = new StyledString(ttd instanceof UnknownTouchpoint
					? ((UnknownTouchpoint)ttd).getOriginalTypeId()
					: ttd.getTypeId());
			bld.append(" (", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
			bld.append(ttd instanceof UnknownTouchpoint
					? ((UnknownTouchpoint)ttd).getOriginalVersion()
					: ttd.getVersionString(), StyledString.DECORATIONS_STYLER);
			bld.append(")", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
			if(ttd instanceof UnknownTouchpoint)
				bld.append(" - unknown", StyledString.QUALIFIER_STYLER);
			return bld;
		}
		if(element instanceof ITouchpointActionDescriptor)
		{
			ITouchpointActionDescriptor desc = (ITouchpointActionDescriptor)element;
			StyledString bld = new StyledString(desc.getLabel());
			return bld;
		}
		return new StyledString(element.toString());
	}

}
