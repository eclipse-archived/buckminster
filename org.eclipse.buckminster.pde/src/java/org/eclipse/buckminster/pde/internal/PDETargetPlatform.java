/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.buckminster.core.ITargetPlatform;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class PDETargetPlatform extends AbstractExtension implements ITargetPlatform
{
	public String getArch()
	{
		return TargetPlatform.getOSArch();
	}

	public List<ComponentIdentifier> getComponents() throws CoreException
	{
		PDECore pdeCore = PDECore.getDefault();
		IFeatureModel[] featureModels = pdeCore.getFeatureModelManager().getModels();
		IPluginModelBase[] pluginModels = pdeCore.getModelManager().getActiveModels();
		ArrayList<ComponentIdentifier> bld = new ArrayList<ComponentIdentifier>(featureModels.length
				+ pluginModels.length);

		for(IFeatureModel featureModel : featureModels)
		{
			IFeature feature = featureModel.getFeature();
			Version version = VersionHelper.parseVersion(feature.getVersion());
			bld.add(new ComponentIdentifier(feature.getId(), IComponentType.ECLIPSE_FEATURE, version));
		}
		for(IPluginModelBase pluginModel : pluginModels)
		{
			BundleDescription desc = pluginModel.getBundleDescription();
			if(desc != null)
			{
				Version version = Version.fromOSGiVersion(desc.getVersion());
				bld.add(new ComponentIdentifier(desc.getSymbolicName(), IComponentType.OSGI_BUNDLE, version));
			}
		}
		return bld;
	}

	public File getLocation()
	{
		String location = TargetPlatform.getLocation();
		return (location == null || location.length() == 0)
				? null
				: new File(location);
	}

	public String getNL()
	{
		return TargetPlatform.getNL();
	}

	public String getOS()
	{
		return TargetPlatform.getOS();
	}

	public String getWS()
	{
		return TargetPlatform.getWS();
	}
}
