/*******************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/

package org.eclipse.buckminster.pde.ui.actions;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.WorkspaceInfo;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.version.IVersionDesignator;
import org.eclipse.buckminster.core.version.VersionFactory;
import org.eclipse.buckminster.core.version.VersionSyntaxException;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.service.resolver.BundleSpecification;
import org.eclipse.osgi.service.resolver.VersionRange;
import org.eclipse.pde.core.plugin.IPluginBase;
import org.eclipse.pde.core.plugin.IPluginModelBase;

/**
 * @author Thomas Hallgren
 * 
 */
public abstract class SelectionHelper
{
	public static CSpec selectionChanged(ISelection selection)
	{
		if(!(selection instanceof IStructuredSelection))
			return null;

		IStructuredSelection s = (IStructuredSelection)selection;
		if(s.size() != 1)
			return null;

		Object first = s.getFirstElement();
		try
		{
			Resolution res = null;
			if(first instanceof IPluginModelBase)
				res = getResolution((IPluginModelBase)first);
			else if(first instanceof IPluginBase)
				res = getResolution((IPluginBase)first);
			else if(first instanceof BundleSpecification)
				res = getResolution((BundleSpecification)first);

			if(res != null)
				return res.getCSpec();
		}
		catch(CoreException e)
		{
			CorePlugin.getLogger().warning(e, e.getMessage());
		}
		return null;
	}

	private static Resolution getResolution(BundleSpecification spec) throws CoreException
	{
		IVersionDesignator vd = null;
		VersionRange vr = spec.getVersionRange();
		if(vr != null)
		{
			String vrs = vr.toString();
			if(!"0.0.0".equals(vrs))
			{
				try
				{
					vd = VersionFactory.createDesignator(VersionFactory.OSGiType, vrs);
				}
				catch(VersionSyntaxException e)
				{
					return null;
				}
			}
		}
		ComponentRequest cr = new ComponentRequest(spec.getName(), IComponentType.OSGI_BUNDLE, vd);
		return WorkspaceInfo.getResolution(cr, false);
	}

	private static Resolution getResolution(IPluginBase base) throws CoreException
	{
		return WorkspaceInfo.getResolution(new ComponentIdentifier(base.getId(), IComponentType.OSGI_BUNDLE,
				VersionFactory.OSGiType.coerce(base.getVersion())));
	}

	private static Resolution getResolution(IPluginModelBase model) throws CoreException
	{
		BundleDescription bundleDesc = model.getBundleDescription();
		if(bundleDesc == null)
			return null;

		return WorkspaceInfo.getResolution(new ComponentIdentifier(bundleDesc.getSymbolicName(),
				IComponentType.OSGI_BUNDLE, VersionFactory.OSGiType.coerce(bundleDesc.getVersion())));
	}
}
