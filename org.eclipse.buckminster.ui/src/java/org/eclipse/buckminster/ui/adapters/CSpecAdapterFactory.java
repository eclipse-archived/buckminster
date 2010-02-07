/*******************************************************************
 * Copyright (c) 2006-2008, Cloudsmith Inc.
 * The code, documentation and other materials contained herein
 * are the sole and exclusive property of Cloudsmith Inc. and may
 * not be disclosed, used, modified, copied or distributed without
 * prior written consent or license from Cloudsmith Inc.
 ******************************************************************/

package org.eclipse.buckminster.ui.adapters;

import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Adapter Factory that converts: CSpec and CSpecDataNode to each other, and to
 * Resolution and ResolutionDataNode IResource to CSpec, or CSpecDataNode.
 * 
 * @author Henrik Lindberg
 * 
 */
public class CSpecAdapterFactory implements IAdapterFactory {
	private static Class<?>[] adapterList = { CSpec.class, CSpecDataNode.class, Resolution.class, ResolutionDataNode.class };

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Object adaptCSpec(CSpec cspec, Class adapterType) {
		if (adapterType.isAssignableFrom(CSpec.class))
			return cspec; // duh

		if (adapterType.isAssignableFrom(CSpecDataNode.class))
			return new CSpecDataNode(cspec);

		if (adapterType.isAssignableFrom(Resolution.class))
			try {
				return WorkspaceInfo.getResolution(cspec.getComponentIdentifier());
			} catch (CoreException e) {
				return null;
			}
		if (adapterType.isAssignableFrom(ResolutionDataNode.class))
			try {
				return new ResolutionDataNode(WorkspaceInfo.getResolution(cspec.getComponentIdentifier()));
			} catch (CoreException e) {
				return null;
			}
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof CSpec)
			return adaptCSpec((CSpec) adaptableObject, adapterType);

		if (adaptableObject instanceof CSpecDataNode)
			return adaptCSpec((CSpec) ((CSpecDataNode) adaptableObject).getData(), adapterType);

		// give up
		return null;
	}

	public Class<?>[] getAdapterList() {
		return adapterList;
	}

}
