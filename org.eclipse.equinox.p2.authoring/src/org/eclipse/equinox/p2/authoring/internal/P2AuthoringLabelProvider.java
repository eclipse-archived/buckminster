/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.equinox.p2.authoring.internal;

import java.util.List;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;
import org.eclipse.buckminster.generic.ui.utils.UiUtils;
import org.eclipse.buckminster.opml.IOPML;
import org.eclipse.buckminster.opml.IOutline;
import org.eclipse.buckminster.opml.OutlineType;
import org.eclipse.buckminster.ui.adapters.ComponentReference;
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
import org.eclipse.equinox.p2.authoring.spi.ITouchpointTypeDescriptor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.graphics.Image;
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
	
	private Image getHtmlImage()
	{
		return P2AuthoringImages.getImageDescriptorForFile("file.html").createImage();
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
	@Override
	public Image getImage(Object selected)
	{
		Object element = selected;
		if(selected instanceof RequiredCapabilityBuilder)
			return getRequiredCapabilityImage((RequiredCapabilityBuilder)selected);
		
		if(selected instanceof ITreeDataNode)
			element = ((ITreeDataNode)element).getData();
		
		if(element instanceof IProject)
			return P2AuthoringImages.getIMG_PROJECT();

		if(element instanceof IFolder)
			return P2AuthoringImages.getIMG_FOLDER();

		if(element instanceof IFile)
		{
			IFile file = (IFile) element;
			ImageDescriptor imageDescriptor = UiUtils.getImageDescriptor(file);
			return imageDescriptor == null ?
						P2AuthoringImages.getIMG_FILE() 
					: 	UiUtils.getImage(imageDescriptor);
		}
		if(element instanceof ArtifactKeyBuilder)
			return P2AuthoringImages.getIMG_FILE();
		
		if(element instanceof TouchpointDataBuilder || element instanceof TouchpointInstructionBuilder)
			return P2AuthoringImages.getIMG_FOLDER();
			
		// OPML stuff
		if(element instanceof IOPML)
			return P2AuthoringImages.getIMG_FOLDER();
		
		if(element instanceof IOutline)
		{
			// An outline that has sub-outlines is shown as a folder
			//
			List<? extends IOutline> outlines = ((IOutline)element).getOutlines();
			if(((IOutline)element).getType() == OutlineType.UNKNOWN || (outlines != null && outlines.size() > 0))
				return P2AuthoringImages.getIMG_FOLDER();
			// An outline that is a link is shown as a browseable image
			if(((IOutline)element).getType() == OutlineType.LINK)
				return getHtmlImage();
			return P2AuthoringImages.getIMG_RSS();
		}

		// Parents default to Folder
		if(selected instanceof BasicTreeParentDataNode)
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
		if(element instanceof ITreeDataNode)
			element = ((ITreeDataNode)element).getData();
		if(element instanceof IResource)
			return new StyledString(((IResource)element).getName());
		
		if(element instanceof Resolution)
		{
			Resolution r = (Resolution)element;
			StyledString bld = new StyledString(r.getName());
			String type = r.getComponentTypeId();
			if(type != null)
			{
				bld.append(" : ", StyledString.DECORATIONS_STYLER);
				bld.append(type, StyledString.DECORATIONS_STYLER);
			}
			IVersion version = r.getVersion();
			if(version != null)
			{
				bld.append(" - ", StyledString.DECORATIONS_STYLER);
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
				bld.append(" : ", StyledString.DECORATIONS_STYLER);
				bld.append(req.getComponentTypeID(), StyledString.DECORATIONS_STYLER);
			}
			if(req.getVersionDesignator() != null)
			{
				bld.append(" - ", StyledString.DECORATIONS_STYLER);
				bld.append(req.getVersionDesignator().toString(), StyledString.DECORATIONS_STYLER);
			}
			return bld;
		}
		if(element instanceof RequiredCapabilityBuilder)
		{
			RequiredCapabilityBuilder req = (RequiredCapabilityBuilder)element;
			StyledString bld = new StyledString(req.getName());
			bld.append(" : ", StyledString.DECORATIONS_STYLER);
			bld.append(req.getRange(), StyledString.DECORATIONS_STYLER);
			return bld;
			
		}
		if(element instanceof ProvidedCapabilityBuilder)
		{
			ProvidedCapabilityBuilder req = (ProvidedCapabilityBuilder)element;
			StyledString bld = new StyledString(req.getName());
			bld.append(" : ", StyledString.DECORATIONS_STYLER);
			bld.append(req.getVersion(), StyledString.DECORATIONS_STYLER);
			return bld;
			
		}
		if(element instanceof ArtifactKeyBuilder)
		{
			ArtifactKeyBuilder artifact = (ArtifactKeyBuilder)element;
			StyledString bld = new StyledString(artifact.getId());
			bld.append(" : ", StyledString.DECORATIONS_STYLER);
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
			bld.append(" (", StyledString.COUNTER_STYLER);
			TouchpointActionBuilder[] actions = ((TouchpointInstructionBuilder)element).getActions();
			bld.append(Integer.toString(actions == null ? 0 : actions.length), StyledString.COUNTER_STYLER);
			bld.append(")", StyledString.COUNTER_STYLER);			
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
				bld.append("(");
				bld.append("...", StyledString.DECORATIONS_STYLER);
				bld.append(")");
			}
			else
			{
				bld.append(action.getActionKey());
				bld.append("(");
				boolean first = true;
				Parameter[] params = action.getParameters();
				for(int i = 0; i < params.length; i++)
				{
					if(!first)
						bld.append(", ");
					bld.append(params[i].getName(), StyledString.QUALIFIER_STYLER);
					bld.append(": ", StyledString.QUALIFIER_STYLER);
					bld.append(params[i].getValue());
					first = false;
				}
				bld.append(")");
			}
			return bld;
			
		}
		if(element instanceof ITouchpointTypeDescriptor)
		{
			ITouchpointTypeDescriptor ttd = (ITouchpointTypeDescriptor)element;
			StyledString bld = new StyledString(ttd.getTypeId());
			bld.append(" (", StyledString.DECORATIONS_STYLER);
			bld.append(ttd.getVersionString(), StyledString.DECORATIONS_STYLER);
			bld.append(")", StyledString.DECORATIONS_STYLER);
			return bld;
		}
		if(element instanceof CSpec)
		{
			return new StyledString("Component Specification (CSpec)");
		}
		if(element instanceof IOPML)
		{
			return new StyledString("Component Information");
		}
		if(element instanceof IOutline)
		{
			IOutline outline = (IOutline)element;
			return new StyledString(outline.getText());
		}
		return new StyledString(element.toString());
	}

}
