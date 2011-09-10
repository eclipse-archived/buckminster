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

import java.util.Locale;

import org.eclipse.buckminster.core.ctype.IComponentType;
import org.eclipse.buckminster.core.reader.IReaderType;
import org.eclipse.buckminster.core.resolver.NodeQuery;
import org.eclipse.buckminster.core.rmap.model.MalformedProviderURIException;
import org.eclipse.buckminster.core.rmap.model.Provider;
import org.eclipse.buckminster.core.version.AbstractVersionFinder;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.core.version.VersionMatch;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.equinox.frameworkadmin.BundleInfo;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.pde.internal.core.ifeature.IFeatureModel;

/**
 * A Reader that knows about features and plugins that are part of an Eclipse
 * installation.
 * 
 * @author thhal
 */
@SuppressWarnings("restriction")
public class EclipsePlatformVersionFinder extends AbstractVersionFinder {
	enum InstalledType {
		FEATURE, PLUGIN
	}

	private final String componentName;

	private final InstalledType type;

	public EclipsePlatformVersionFinder(IReaderType readerType, Provider provider, IComponentType ctype, NodeQuery query) throws CoreException {
		super(provider, ctype, query);
		String uri = provider.getURI(query.getProperties());
		IPath path = new Path(uri);
		if (path.segmentCount() == 2) {
			type = InstalledType.valueOf(path.segment(0).toUpperCase(Locale.ENGLISH));
			if (type != null) {
				componentName = path.segment(1);
				return;
			}
		}
		throw new MalformedProviderURIException(readerType, uri);
	}

	@Override
	public VersionMatch getBestVersion(IProgressMonitor monitor) throws CoreException {
		Version v = null;
		NodeQuery query = getQuery();
		VersionRange dsg = query.getVersionRange();
		if (type == InstalledType.PLUGIN) {
			BundleInfo plugin = PDETargetPlatform.getBestPlugin(componentName, dsg, query);
			if (plugin != null)
				v = VersionHelper.parseVersion(plugin.getVersion());
		} else {
			IFeatureModel feature = PDETargetPlatform.getBestFeature(componentName, dsg, query);
			if (feature != null)
				v = VersionHelper.parseVersion(feature.getFeature().getVersion());
		}
		return (v == null) ? null : new VersionMatch(v, null, -1L, null, null);
	}
}
