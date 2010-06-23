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

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.ITargetPlatform;
import org.eclipse.buckminster.core.cspec.model.ComponentIdentifier;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.helpers.AbstractExtension;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.equinox.frameworkadmin.BundleInfo;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.TargetPlatform;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;
import org.eclipse.pde.internal.core.target.DirectoryBundleContainer;
import org.eclipse.pde.internal.core.target.TargetPlatformService;
import org.eclipse.pde.internal.core.target.provisional.IBundleContainer;
import org.eclipse.pde.internal.core.target.provisional.IResolvedBundle;
import org.eclipse.pde.internal.core.target.provisional.ITargetDefinition;
import org.eclipse.pde.internal.core.target.provisional.ITargetHandle;
import org.eclipse.pde.internal.core.target.provisional.ITargetPlatformService;
import org.eclipse.pde.internal.core.target.provisional.LoadTargetDefinitionJob;

/**
 * @author Thomas Hallgren
 */
@SuppressWarnings("restriction")
public class PDETargetPlatform extends AbstractExtension implements ITargetPlatform {
	private static interface ITargetDefinitionOperation<T> {
		T run(ITargetDefinition target) throws CoreException;
	}

	private static final String defaultTP = "Buckminster Default TP"; //$NON-NLS-1$

	private static ITargetDefinition currentDefinition;

	private static ITargetHandle currentHandle;

	public static IFeatureModel getBestFeature(final String componentName, final VersionRange versionDesignator, final NodeQuery query) {
		return doWithActivePlatform(new ITargetDefinitionOperation<IFeatureModel>() {
			@Override
			public IFeatureModel run(ITargetDefinition target) throws CoreException {
				IFeatureModel[] allFeatures = target.getAllFeatures();
				if (allFeatures == null)
					return null;

				IFeatureModel candidate = null;
				Version candidateVersion = null;
				for (IFeatureModel featureModel : allFeatures) {
					IFeature feature = featureModel.getFeature();
					if (!componentName.equals(feature.getId()))
						continue;

					Version v = Version.create(feature.getVersion());
					if (v == null) {
						if (candidate == null && versionDesignator == null)
							candidate = featureModel;
						continue;
					}

					if (!(versionDesignator == null || versionDesignator.isIncluded(v))) {
						if (query != null)
							query.logDecision(ResolverDecisionType.VERSION_REJECTED, v, NLS.bind(Messages.not_designated_by_0, versionDesignator));
						continue;
					}

					if (candidateVersion == null || candidateVersion.compareTo(v) < 0) {
						candidate = featureModel;
						candidateVersion = v;
					}
				}
				return candidate;
			}
		});
	}

	public static BundleInfo getBestPlugin(final String componentName, final VersionRange versionDesignator, final NodeQuery query) {
		return doWithActivePlatform(new ITargetDefinitionOperation<BundleInfo>() {
			@Override
			public BundleInfo run(ITargetDefinition target) throws CoreException {
				IResolvedBundle[] allBundles = target.getAllBundles();
				if (allBundles == null)
					return null;

				BundleInfo candidate = null;
				Version candidateVersion = null;
				for (IResolvedBundle bundle : allBundles) {
					BundleInfo bi = bundle.getBundleInfo();
					if (!componentName.equals(bi.getSymbolicName()))
						continue;

					Version v = Version.create(bi.getVersion());
					if (v == null) {
						if (candidate == null && versionDesignator == null)
							candidate = bi;
						continue;
					}

					if (!(versionDesignator == null || versionDesignator.isIncluded(v))) {
						if (query != null)
							query.logDecision(ResolverDecisionType.VERSION_REJECTED, v, NLS.bind(Messages.not_designated_by_0, versionDesignator));
						continue;
					}

					if (candidateVersion == null || candidateVersion.compareTo(v) < 0) {
						candidate = bi;
						candidateVersion = v;
					}
				}
				return candidate;
			}
		});
	}

	private static <T> T doWithActivePlatform(ITargetDefinitionOperation<T> operation) {
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = null;
		try {
			service = bucky.getService(ITargetPlatformService.class);
			synchronized (service) {
				ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
				if (activeHandle == null)
					return null;
				if (currentHandle == null || !activeHandle.equals(currentHandle)) {
					currentDefinition = activeHandle.getTargetDefinition();
					currentHandle = activeHandle;
				}
			}
			return operation.run(currentDefinition);
		} catch (CoreException e) {
			Buckminster.getLogger().warning(e, e.getLocalizedMessage());
			return null;
		} finally {
			bucky.ungetService(service);
		}
	}

	private static File getLocation(ITargetDefinition target) throws CoreException {
		IBundleContainer[] containers = target.getBundleContainers();
		if (containers == null)
			return null;
		for (IBundleContainer container : containers) {
			// bug 285449: the directory bundle container is actually the only
			// we one we can use
			if (container instanceof DirectoryBundleContainer) {
				return new File(((DirectoryBundleContainer) container).getLocation(true));
			}
		}
		return null;
	}

	@Override
	public String getArch() {
		String arch = doWithActivePlatform(new ITargetDefinitionOperation<String>() {
			@Override
			public String run(ITargetDefinition target) {
				return target.getArch();
			}
		});
		return arch == null ? TargetPlatform.getOSArch() : arch;
	}

	@Override
	public List<ComponentIdentifier> getComponents() throws CoreException {
		return doWithActivePlatform(new ITargetDefinitionOperation<List<ComponentIdentifier>>() {
			@Override
			public List<ComponentIdentifier> run(ITargetDefinition target) {
				if (!target.isResolved())
					target.resolve(new NullProgressMonitor());
				List<ComponentIdentifier> result = new ArrayList<ComponentIdentifier>();
				for (IFeatureModel feature : target.getAllFeatures()) {
					IFeature f = feature.getFeature();
					result.add(new ComponentIdentifier(f.getId(), IComponentType.ECLIPSE_FEATURE, Version.parseVersion(f.getVersion())));
				}
				for (IResolvedBundle bundle : target.getBundles()) {
					BundleInfo b = bundle.getBundleInfo();
					result.add(new ComponentIdentifier(b.getSymbolicName(), IComponentType.OSGI_BUNDLE, Version.parseVersion(b.getVersion())));
				}
				return result;
			}
		});
	}

	public ITargetDefinition getDefaultPlatform(boolean asActive) throws CoreException {
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = bucky.getService(ITargetPlatformService.class);
		ITargetDefinition dflt = null;
		for (ITargetHandle handle : service.getTargets(null)) {
			ITargetDefinition target = handle.getTargetDefinition();
			if (defaultTP.equals(target.getName())) {
				if (!asActive)
					return target;

				ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
				if (activeHandle != null && activeHandle.equals(handle))
					return target;

				dflt = target;
				break;
			}
		}

		if (dflt == null) {
			// Create a default target platform under the buckminster folder
			//
			IProject buckyProj = CorePlugin.getDefault().getBuckminsterProject(true, null);
			IFolder tpFolder = buckyProj.getFolder("tp"); //$NON-NLS-1$
			if (!tpFolder.exists())
				tpFolder.create(true, false, null);

			dflt = ((TargetPlatformService) service).newDefaultTargetDefinition();
			IBundleContainer runningInstance = dflt.getBundleContainers()[0];
			IBundleContainer directory = service.newDirectoryContainer(tpFolder.getLocation().toOSString());
			dflt.setBundleContainers(new IBundleContainer[] { directory, runningInstance });
			dflt.setName(defaultTP);
			service.saveTargetDefinition(dflt);
		}

		if (asActive) {
			LoadTargetDefinitionJob job = new LoadTargetDefinitionJob(dflt);
			IStatus status = job.run(new NullProgressMonitor());
			if (status.getSeverity() == IStatus.ERROR)
				throw new CoreException(status);
		}
		return dflt;
	}

	@Override
	public File getDefaultPlatformLocation(boolean asActive) throws CoreException {
		return getLocation(getDefaultPlatform(asActive));
	}

	@Override
	public File getLocation() {
		File location = doWithActivePlatform(new ITargetDefinitionOperation<File>() {
			@Override
			public File run(ITargetDefinition target) throws CoreException {
				return getLocation(target);
			}
		});
		// bug 285449: don't fall back on deprecated
		// TargetPlatform.getLocation() setting
		return location;
	}

	@Override
	public String getNL() {
		String nl = doWithActivePlatform(new ITargetDefinitionOperation<String>() {
			@Override
			public String run(ITargetDefinition target) {
				return target.getNL();
			}
		});
		return nl == null ? TargetPlatform.getNL() : nl;
	}

	@Override
	public String getOS() {
		String os = doWithActivePlatform(new ITargetDefinitionOperation<String>() {
			@Override
			public String run(ITargetDefinition target) {
				return target.getOS();
			}
		});
		return os == null ? TargetPlatform.getOS() : os;
	}

	@Override
	public String getWS() {
		String ws = doWithActivePlatform(new ITargetDefinitionOperation<String>() {
			@Override
			public String run(ITargetDefinition target) {
				return target.getWS();
			}
		});
		return ws == null ? TargetPlatform.getWS() : ws;
	}

	@Override
	public void locationsChanged(Set<File> locations) {
		// Check if the given location is contained in the active TP. If that's
		// the case, refresh.
		//
		Logger log = Buckminster.getLogger();
		Buckminster bucky = Buckminster.getDefault();
		ITargetPlatformService service = null;
		try {
			log.debug("Processing changes in target platform locations..."); //$NON-NLS-1$
			service = bucky.getService(ITargetPlatformService.class);
			ITargetHandle activeHandle = service.getWorkspaceTargetHandle();
			if (activeHandle == null) {
				log.debug("Found no active target handle"); //$NON-NLS-1$
				return;
			}

			ITargetDefinition target = activeHandle.getTargetDefinition();
			IBundleContainer[] containers = target.getBundleContainers();
			if (containers == null || containers.length == 0) {
				log.debug("Active target handle has no containers"); //$NON-NLS-1$
				return;
			}

			boolean found = false;
			for (IBundleContainer container : containers) {
				if (container instanceof DirectoryBundleContainer
						&& locations.contains(new File(((DirectoryBundleContainer) container).getLocation(true)))) {
					found = true;
					break;
				}
			}

			if (!found) {
				log.debug("Active target handle has no containers of type DirectoryBundleContainer that matches the given locations"); //$NON-NLS-1$
				return;
			}

			refresh(target);
		} catch (CoreException e) {
			log.warning(e, e.getLocalizedMessage());
		} finally {
			bucky.ungetService(service);
			log.debug("Done processing changes in target platform locations"); //$NON-NLS-1$
		}
	}

	@Override
	public void refresh() {
		doWithActivePlatform(new ITargetDefinitionOperation<Object>() {
			@Override
			public Object run(ITargetDefinition target) throws CoreException {
				refresh(target);
				return null;
			}
		});
	}

	private void refresh(ITargetDefinition target) throws CoreException {
		Logger log = Buckminster.getLogger();
		log.info(NLS.bind(Messages.resetting_target_platform_0, target.getName()));
		target.resolve(new NullProgressMonitor());
		LoadTargetDefinitionJob loadTP = new LoadTargetDefinitionJob(target);
		IStatus loadStatus = loadTP.run(new NullProgressMonitor());
		if (loadStatus.getSeverity() == IStatus.ERROR)
			throw new CoreException(loadStatus);
	}
}
