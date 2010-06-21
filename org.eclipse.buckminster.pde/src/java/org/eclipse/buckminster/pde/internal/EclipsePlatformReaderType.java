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

import java.io.File;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.RMContext;
import org.eclipse.buckminster.core.cspec.model.ComponentRequest;
import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.metadata.model.Resolution;
import org.eclipse.buckminster.core.reader.CatalogReaderType;
import org.eclipse.buckminster.core.reader.IComponentReader;
import org.eclipse.buckminster.core.reader.IVersionFinder;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.resolver.ResolverDecisionType;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.ProviderMatch;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.MonitorUtils;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.frameworkadmin.BundleInfo;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.osgi.service.resolver.BundleDescription;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.core.plugin.IPluginModelBase;
import org.eclipse.pde.core.plugin.PluginRegistry;
import org.eclipse.pde.internal.core.IPluginModelListener;
import org.eclipse.pde.internal.core.PDECore;
import org.eclipse.pde.internal.core.PluginModelDelta;
import org.eclipse.pde.internal.core.ifeature.IFeature;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * A Reader type that knows about features and plugins that are part of an
 * Eclipse installation.
 * 
 * @author thhal
 */
@SuppressWarnings({ "restriction" })
public class EclipsePlatformReaderType extends CatalogReaderType {
	private static final Map<String, IPluginModelBase[]> activeMap = new HashMap<String, IPluginModelBase[]>();

	static {
		PDECore.getDefault().getModelManager().addPluginModelListener(new IPluginModelListener() {
			@Override
			public void modelsChanged(PluginModelDelta delta) {
				if (delta.getKind() != 0)
					clearCache();
			}
		});
	}

	public static void clearCache() {
		synchronized (activeMap) {
			activeMap.clear();
		}
	}

	public static IFeatureModel getBestFeature(String componentName, VersionRange versionDesignator, NodeQuery query) {
		IFeatureModel candidate = null;
		Version candidateVersion = null;
		for (IFeatureModel model : PDECore.getDefault().getFeatureModelManager().findFeatureModels(componentName)) {
			IFeature feature = model.getFeature();
			String ov = feature.getVersion();
			if (ov == null) {
				if (candidate == null && versionDesignator == null)
					candidate = model;
				continue;
			}

			Version v = VersionHelper.parseVersion(ov);
			if (!(versionDesignator == null || versionDesignator.isIncluded(v))) {
				if (query != null)
					query.logDecision(ResolverDecisionType.VERSION_REJECTED, v, NLS.bind(Messages.not_designated_by_0, versionDesignator));
				continue;
			}

			if (candidateVersion == null || candidateVersion.compareTo(v) < 0) {
				candidate = model;
				candidateVersion = v;
			}
		}
		return candidate;
	}

	public static IPluginModelBase getBestPlugin(String componentName, VersionRange versionDesignator, NodeQuery query) {
		IPluginModelBase candidate = null;
		Version candidateVersion = null;
		synchronized (activeMap) {
			if (activeMap.isEmpty()) {
				for (IPluginModelBase model : PluginRegistry.getAllModels()) {
					BundleDescription desc = model.getBundleDescription();
					String id = desc.getSymbolicName();
					IPluginModelBase[] mbArr = activeMap.get(id);
					if (mbArr == null)
						mbArr = new IPluginModelBase[] { model };
					else {
						IPluginModelBase[] newArr = new IPluginModelBase[mbArr.length + 1];
						System.arraycopy(mbArr, 0, newArr, 0, mbArr.length);
						newArr[mbArr.length] = model;
						mbArr = newArr;
					}
					activeMap.put(id, mbArr);
				}
			}
			IPluginModelBase[] mbArr = activeMap.get(componentName);
			if (mbArr == null)
				return null;

			for (IPluginModelBase model : mbArr) {
				BundleDescription desc = model.getBundleDescription();
				if (desc == null)
					continue;

				org.osgi.framework.Version ov = desc.getVersion();
				if (ov == null) {
					if (candidate == null && versionDesignator == null)
						candidate = model;
					continue;
				}

				Version v = Version.createOSGi(ov.getMajor(), ov.getMinor(), ov.getMicro(), ov.getQualifier());
				if (!(versionDesignator == null || versionDesignator.isIncluded(v))) {
					if (query != null)
						query.logDecision(ResolverDecisionType.VERSION_REJECTED, v, NLS.bind(Messages.not_designated_by_0, versionDesignator));
					continue;
				}

				if (candidateVersion == null || candidateVersion.compareTo(v) < 0) {
					candidate = model;
					candidateVersion = v;
				}
			}
			return candidate;
		}
	}

	static File getBundleLocation(BundleInfo plugin) {
		if (plugin == null)
			return null;
		URI il = plugin.getLocation();
		if (il == null)
			return null;
		if (!"file".equalsIgnoreCase(il.getScheme())) { //$NON-NLS-1$
			try {
				java.net.URL fileIL = FileLocator.toFileURL(il.toURL());
				if ("file".equalsIgnoreCase(fileIL.getProtocol())) //$NON-NLS-1$
					return null;
				il = fileIL.toURI();
			} catch (Exception e) {
				return null;
			}
		}
		return new File(il);
	}

	@Override
	public URI getArtifactURL(Resolution resolution, RMContext context) throws CoreException {
		return null;
	}

	@Override
	public IPath getFixedLocation(Resolution cr) {
		Version version = cr.getVersion();
		VersionRange vd = version == null ? null : VersionHelper.exactRange(version);
		String location;
		ComponentRequest rq = cr.getRequest();
		if (IComponentType.ECLIPSE_FEATURE.equals(rq.getComponentTypeID())) {
			IFeatureModel model = getBestFeature(rq.getName(), vd, null);
			if (model == null)
				return null;
			location = model.getInstallLocation();
		} else {
			BundleInfo plugin = PDETargetPlatform.getBestPlugin(rq.getName(), vd, null);
			if (plugin == null)
				return null;
			File fileLoc = getBundleLocation(plugin);
			if (fileLoc == null)
				return null;
			location = fileLoc.getAbsolutePath();
		}

		IPath path = null;
		if (location != null) {
			path = new Path(location);
			if (path.toFile().isDirectory())
				path = path.addTrailingSeparator();
		}
		return path;
	}

	@Override
	public IComponentReader getReader(ProviderMatch providerMatch, IProgressMonitor monitor) throws CoreException {
		MonitorUtils.complete(monitor);
		return new EclipsePlatformReader(this, providerMatch);
	}

	@Override
	public IVersionFinder getVersionFinder(Provider provider, IComponentType ctype, NodeQuery nodeQuery, IProgressMonitor monitor)
			throws CoreException {
		MonitorUtils.complete(monitor);
		return new EclipsePlatformVersionFinder(this, provider, ctype, nodeQuery);
	}
}
