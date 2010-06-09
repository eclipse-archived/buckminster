/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.providers;

import org.eclipse.buckminster.core.cspec.ICSpecData;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.metadata.IResolution;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.generic.model.tree.BasicTreeParentDataNode;
import org.eclipse.buckminster.generic.model.tree.ITreeDataNode;
import org.eclipse.buckminster.generic.ui.utils.UiUtils;
import org.eclipse.buckminster.ui.Messages;
import org.eclipse.buckminster.ui.UiPlugin;
import org.eclipse.buckminster.ui.adapters.ComponentReference;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
 * A default LabelProvider for data elements found in Buckminster artifacts and
 * views.
 * 
 * @author Henrik Lindberg
 * 
 */
public class BuckminsterLabelProvider extends ColumnLabelProvider implements IStyledLabelProvider {
	private Image projectImage;

	private Image folderImage;

	private Image fileImage;

	private Image cspecImage;

	private Image componentImage;

	private Image dependantImage;

	private Image dependencyImage;

	public BuckminsterLabelProvider() {
	}

	@Override
	public void dispose() {
		if (projectImage != null)
			projectImage.dispose();
		if (folderImage != null)
			folderImage.dispose();
		if (fileImage != null)
			fileImage.dispose();
		if (cspecImage != null)
			cspecImage.dispose();
		if (componentImage != null)
			componentImage.dispose();
		if (dependencyImage != null)
			dependencyImage.dispose();
		if (dependantImage != null)
			dependantImage.dispose();

		// note - do not dispose of images that were not created !
		super.dispose();
	}

	@Override
	public Image getImage(Object selected) {
		Object element = selected;
		if (selected instanceof ITreeDataNode)
			element = ((ITreeDataNode) element).getData();

		if (element instanceof IProject)
			return getProjectImage();

		if (element instanceof IFolder)
			return getFolderImage();

		if (element instanceof IFile) {
			IFile file = (IFile) element;
			ImageDescriptor imageDescriptor = UiUtils.getImageDescriptor(file);
			return imageDescriptor == null ? getFileImage() : UiUtils.getImage(imageDescriptor);
		}

		if (element instanceof ICSpecData)
			return getCspecImage();

		if (element instanceof IResolution)
			return getComponentImage();

		if (element instanceof ComponentReference)
			return ((ComponentReference) element).getMode() == ComponentReference.Mode.IN ? getDependantImage() : getDependencyImage();

		// Parents default to Folder
		if (selected instanceof BasicTreeParentDataNode)
			return getFolderImage();

		return null;
	}

	@Override
	public StyledString getStyledText(Object element) {
		if (element instanceof ITreeDataNode)
			element = ((ITreeDataNode) element).getData();
		if (element instanceof IResource)
			return new StyledString(((IResource) element).getName());

		if (element instanceof IResolution) {
			Resolution r = (Resolution) element;
			StyledString bld = new StyledString(r.getName());
			String type = r.getComponentTypeId();
			if (type != null) {
				bld.append(" : ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(type, StyledString.DECORATIONS_STYLER);
			}
			Version version = r.getVersion();
			if (version != null) {
				bld.append(" - ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(VersionHelper.getHumanReadable(version), StyledString.DECORATIONS_STYLER);
			}
			return bld;
		}
		if (element instanceof ComponentReference) {
			ComponentReference ref = (ComponentReference) element;
			StyledString bld = new StyledString(ref.getComponentName());
			ComponentRequest req = ref.getComponentRequest();
			if (req.getComponentTypeID() != null) {
				bld.append(" : ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(req.getComponentTypeID(), StyledString.DECORATIONS_STYLER);
			}
			if (req.getVersionRange() != null) {
				bld.append(" - ", StyledString.DECORATIONS_STYLER); //$NON-NLS-1$
				bld.append(req.getVersionRange().toString(), StyledString.DECORATIONS_STYLER);
			}
			return bld;
		}

		if (element instanceof ICSpecData) {
			return new StyledString(Messages.component_specification_and_cspec_in_paranthesis);
		}
		return new StyledString(element.toString());
	}

	/**
	 * Returns the name of an IResourceElement using getName(), else
	 * element.toString() is used.
	 */
	@Override
	public String getText(Object element) {
		return getStyledText(element).toString();
	}

	private Image getComponentImage() {
		if (componentImage == null)
			componentImage = UiPlugin.getImageDescriptor("icons/component.png").createImage(); //$NON-NLS-1$
		return componentImage;
	}

	private Image getCspecImage() {
		if (cspecImage == null)
			cspecImage = UiPlugin.getImageDescriptor("icons/cspec.png").createImage(); //$NON-NLS-1$
		return cspecImage;
	}

	private Image getDependantImage() {
		if (dependantImage == null)
			dependantImage = UiPlugin.getImageDescriptor("icons/dependent.png").createImage(); //$NON-NLS-1$
		return dependantImage;
	}

	private Image getDependencyImage() {
		if (dependencyImage == null)
			dependencyImage = UiPlugin.getImageDescriptor("icons/dependency.png").createImage(); //$NON-NLS-1$
		return dependencyImage;
	}

	private Image getFileImage() {
		if (fileImage == null)
			fileImage = UiPlugin.getImageDescriptor("icons/file_obj.gif").createImage(); //$NON-NLS-1$
		return fileImage;
	}

	private Image getFolderImage() {
		if (folderImage == null)
			folderImage = UiPlugin.getImageDescriptor("icons/fldr_obj.gif").createImage(); //$NON-NLS-1$
		return folderImage;
	}

	private Image getProjectImage() {
		if (projectImage == null)
			projectImage = UiPlugin.getImageDescriptor("icons/prj_obj.gif").createImage(); //$NON-NLS-1$
		return projectImage;
	}
}
