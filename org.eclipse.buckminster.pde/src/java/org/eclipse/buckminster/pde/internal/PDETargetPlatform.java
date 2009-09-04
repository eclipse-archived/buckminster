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
import java.util.Set;

import org.eclipse.buckminster.core.ITargetPlatform;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.util.NLS;
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
import org.eclipse.pde.internal.core.target.provisional.LoadTargetDefinitionJob;

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

	public void locationsChanged(Set<File> locations)
	{
		// Check if the given location is contained in the active TP. If that's the case, refresh.
		//
		Logger log = Buckminster.getLogger();
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = null;
		try
		{
			log.debug("Processing changes in target platform locations..."); //$NON-NLS-1$
			service = bucky.getService(ITargetPlatformService.class);
			ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
			if(activeHandle == null)
			{
				log.debug("Found no active target handle"); //$NON-NLS-1$
				return;
			}

			ITargetDefinition target = activeHandle.getTargetDefinition();
			IBundleContainer[] containers = target.getBundleContainers();
			if(containers == null || containers.length == 0)
			{
				log.debug("Active target handle has no containers"); //$NON-NLS-1$
				return;
			}

			boolean found = false;
			for(IBundleContainer container : containers)
			{
				if(container instanceof DirectoryBundleContainer
						&& locations.contains(new File(((DirectoryBundleContainer)container).getLocation(true))))
				{
					found = true;
					break;
				}
			}

			if(!found)
			{
				log.debug("Active target handle has no containers of type DirectoryBundleContainer that matches the given locations"); //$NON-NLS-1$
				return;
			}

			log.info(NLS.bind(Messages.resetting_target_platform_0, target.getName()));
			target.resolve(new NullProgressMonitor());
			LoadTargetDefinitionJob loadTP = new LoadTargetDefinitionJob(target);
			loadTP.run(new NullProgressMonitor());
		}
		catch(CoreException e)
		{
			log.warning(e, e.getLocalizedMessage());
		}
		finally
		{
			bucky.ungetService(service);
			log.debug("Done processing changes in target platform locations"); //$NON-NLS-1$
		}
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
