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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;

/**
 * Adapter Factory that converts: IResource to CSpec, CSpecDataNode, Resolution,
 * and ResolutionDataNode
 * 
 * @author Henrik Lindberg
 * 
 */
public class ResourceAdapterFactory implements IAdapterFactory {
	private static Class<?>[] adapterList = { CSpec.class, CSpecDataNode.class, Resolution.class, ResolutionDataNode.class };

	@Override
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Object getAdapter(Object adaptableObject, Class adapterType) {
		if (adaptableObject instanceof IResource) {
			// All adaptions require a cspec for the resource
			CSpec cspec = getCSpecFromResource((IResource) adaptableObject);
			if (cspec == null)
				return null;

			if (adapterType.isAssignableFrom(CSpec.class))
				return cspec;
			if (adapterType.isAssignableFrom(CSpecDataNode.class))
				return new CSpecDataNode(cspec);

			// Remaining adaptions need a Resolution
			Resolution r = null;
			try {
				r = WorkspaceInfo.getResolution(cspec.getComponentIdentifier());
			} catch (CoreException e) {
				return null;
			}
			if (r == null)
				return null;
			if (adapterType.isAssignableFrom(Resolution.class))
				return r;
			if (adapterType.isAssignableFrom(ResolutionDataNode.class))
				return new ResolutionDataNode(r);
		}

		// give up
		return null;
	}

	@Override
	public Class<?>[] getAdapterList() {
		return adapterList;
	}

	public CSpec getCSpecFromResource(IResource resource) {
		while (resource != null) {
			try {
				CSpec cspec = WorkspaceInfo.getCSpec(resource);
				if (cspec != null) {
					return cspec;
				}
				resource = resource.getParent();
			} catch (CoreException e) {
				// ignore
			}
		}
		return null;
	}

}
