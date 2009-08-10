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
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.target.DirectoryBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.IBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class PDETargetPlatform extends AbstractExtension implements ITargetPlatform
{
	private static interface ITargetDefinitionOperation<T>
	{
		T run(ITargetDefinition target) throws CoreException;
	}

	public String getArch()
	{
		String arch = doWithActivePlatform(new ITargetDefinitionOperation<String>()
		{
			public String run(ITargetDefinition target)
			{
				return target.getArch();
			}
		});
		return arch == null
				? TargetPlatform.getOSArch()
				: arch;
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
		File location = doWithActivePlatform(new ITargetDefinitionOperation<File>()
		{
			public File run(ITargetDefinition target) throws CoreException
			{
				IBundleContainer[] containers = target.getBundleContainers();
				if(containers == null)
					return null;
				for(IBundleContainer container : containers)
				{
					// bug 285449: the directory bundle container is actually the only we one we can use
					if(container instanceof DirectoryBundleContainer)
					{
						return new File(((DirectoryBundleContainer)container).getLocation(true));
					}
				}
				return null;
			}
		});
		// bug 285449: don't fall back on deprecated TargetPlatform.getLocation() setting
		return location;
	}

	public String getNL()
	{
		String nl = doWithActivePlatform(new ITargetDefinitionOperation<String>()
		{
			public String run(ITargetDefinition target)
			{
				return target.getNL();
			}
		});
		return nl == null
				? TargetPlatform.getNL()
				: nl;
	}

	public String getOS()
	{
		String os = doWithActivePlatform(new ITargetDefinitionOperation<String>()
		{
			public String run(ITargetDefinition target)
			{
				return target.getOS();
			}
		});
		return os == null
				? TargetPlatform.getOS()
				: os;
	}

	public String getWS()
	{
		String ws = doWithActivePlatform(new ITargetDefinitionOperation<String>()
		{
			public String run(ITargetDefinition target)
			{
				return target.getWS();
			}
		});
		return ws == null
				? TargetPlatform.getWS()
				: ws;
	}

	private <T> T doWithActivePlatform(ITargetDefinitionOperation<T> operation)
	{
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = null;
		try
		{
			service = bucky.getService(ITargetPlatformService.class);
			ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
			if(activeHandle == null)
				return null;
			ITargetDefinition definition = activeHandle.getTargetDefinition();
			return operation.run(definition);
		}
		catch(CoreException e)
		{
			Buckminster.getLogger().warning(e, e.getLocalizedMessage());
			return null;
		}
		finally
		{
			bucky.ungetService(service);
		}
	}
}
