/*******************************************************************************
 * Copyright (c) 2004, 2006
 * Thomas Hallgren, Kenneth Olwing, Mitch Sonies
 * Pontus Rydin, Nils Unden, Peer Torngren
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the individual
 * copyright holders listed above, as Initial Contributors under such license.
 * The text of such license is available at www.eclipse.org.
 *******************************************************************************/
package org.eclipse.buckminster.pde.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.IVersion;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.plugin.IFragmentModel;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.osgi.framework.Version;

/**
 * A Reader type that knows about features and plugins that are part of an Eclipse installation.
 * @author thhal
 */
@SuppressWarnings("restriction")
public class EclipsePlatformReaderType extends CatalogReaderType
{
	@Override
	public IPath getFixedLocation(Resolution cr)
	{
		IVersion version = cr.getVersion();
		String versionString = version == null ? null : version.toString();
		String location;
		ComponentRequest rq = cr.getRequest();
		if(IComponentType.ECLIPSE_FEATURE.equals(rq.getComponentTypeID()))
		{
			IFeatureModel model = this.getBestFeature(rq.getName(), versionString);
			if(model == null)
				return null;
			location = model.getInstallLocation();
		}
		else
		{
			IPluginModelBase model = this.getBestPlugin(rq.getName(), versionString);
			if(model == null)
				return null;
			location = model.getInstallLocation();
		}

		IPath path = null;
		if(location != null)
		{
			path = new Path(location);
			if(path.toFile().isDirectory())
				path = path.addTrailingSeparator();
		}
		return path;
	}

	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new EclipsePlatformReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor) throws CoreException
	{
		MonitorUtils.complete(monitor);
		return new EclipsePlatformVersionFinder(this, provider, ctype, nodeQuery);
	}

	public IFeatureModel getBestFeature(String componentName, String desiredVersion)
	{
		if(desiredVersion == null)
			desiredVersion = "0.0.0";
		return PDECore.getDefault().getFeatureModelManager().findFeatureModel(componentName, desiredVersion);
	}

	public IPluginModelBase getBestPlugin(String componentName, String desiredVersion)
	{
		IPluginModelBase unversioned = null;
		for(IPluginModelBase model : PluginRegistry.getActiveModels())
		{
			BundleDescription desc = model.getBundleDescription();
			if(desc == null)
				continue;

			if(desc.getSymbolicName().equals(componentName))
			{
				Version v = desc.getVersion();
				if(v == null)
				{
					if(desiredVersion == null)
						return model;
					unversioned = model;
					continue;
				}

				if(desiredVersion == null || desiredVersion.equals(v.toString()))
					return model;
			}
		}
		return unversioned;
	}

	public List<IFragmentModel> getFragmentsFor(String pluginId)
	{
		IPluginModelBase plugin = getBestPlugin(pluginId, null);
		if(plugin == null || plugin.isFragmentModel())
			return Collections.<IFragmentModel> emptyList();

		ArrayList<IFragmentModel> frags = null;
		for(IPluginModelBase candidate : PDECore.getDefault().getModelManager().getActiveModels(true))
		{
			if(candidate.isFragmentModel())
			{
				IFragmentModel frag = (IFragmentModel)candidate;
				if(frag.getFragment().getPluginId().equals(pluginId))
				{
					if(frags == null)
						frags = new ArrayList<IFragmentModel>();
					frags.add(frag);
				}
			}
		}
		return frags == null ? Collections.<IFragmentModel> emptyList() : frags;
	}
}
