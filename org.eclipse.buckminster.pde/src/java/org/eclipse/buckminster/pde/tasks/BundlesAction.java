/*******************************************************************************
 * Copyright (c) 2008-2010, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.buckminster.core.KeyConstants;
import org.eclipse.buckminster.core.actor.AbstractActor;
import org.eclipse.buckminster.core.cspec.model.CSpec;
import org.eclipse.buckminster.pde.IPDEConstants;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.publisher.actions.IPropertyAdvice;
import org.eclipse.equinox.p2.repository.artifact.IArtifactDescriptor;
import org.eclipse.osgi.service.resolver.BundleDescription;

@SuppressWarnings("restriction")
public class BundlesAction extends org.eclipse.equinox.p2.publisher.eclipse.BundlesAction {

	@SuppressWarnings("serial")
	static class BundlePropertyAdvice extends HashMap<String, String> implements IPropertyAdvice {
		private final String id;
		private final Version version;

		public BundlePropertyAdvice(String id, Version version) {
			this.id = id;
			this.version = version;
		}

		@Override
		public Map<String, String> getArtifactProperties(IInstallableUnit iu, IArtifactDescriptor descriptor) {
			return null;
		}

		@Override
		public Map<String, String> getInstallableUnitProperties(InstallableUnitDescription iu) {
			return this;
		}

		@Override
		public boolean isApplicable(String configSpec, boolean includeDefault, String candidateId, Version candidateVersion) {
			return id.equals(candidateId) && (version == null || version.equals(candidateVersion));
		}
	}

	private final Map<IVersionedId, CSpec> cspecs;

	public BundlesAction(File[] locations, Map<IVersionedId, CSpec> cspecs) {
		super(locations);
		this.cspecs = cspecs;
	}

	@Override
	protected void generateBundleIUs(BundleDescription[] bundleDescriptions, IPublisherInfo publisherInfo, IPublisherResult result,
			IProgressMonitor monitor) {
		Map<String, ? extends Object> props = AbstractActor.getActiveContext().getProperties();
		String buildId = (String) props.get("build.id"); //$NON-NLS-1$

		if (buildId != null) {
			for (BundleDescription bundleDescription : bundleDescriptions)
				createBuildIdAdvice(bundleDescription, publisherInfo, buildId);
		}
		super.generateBundleIUs(bundleDescriptions, publisherInfo, result, monitor);
	}

	private void createBuildIdAdvice(BundleDescription bundleDescription, IPublisherInfo publisherInfo, String buildId) {
		if (bundleDescription == null)
			return;

		CSpec cspec = findCSpec(bundleDescription);
		if (cspec == null)
			return;

		// Does this cspec have a about.mappings action?
		if (cspec.getAttribute(IPDEConstants.ACTION_ABOUT_MAPPINGS) != null) {
			BundlePropertyAdvice advice = new BundlePropertyAdvice(bundleDescription.getSymbolicName(), Version.create(bundleDescription.getVersion()
					.toString()));
			advice.put(KeyConstants.BUILD_ID, buildId);
			publisherInfo.addAdvice(advice);
		}
	}

	/**
	 * Find the cspec that corresponds to the given version. qualifier doesn't
	 * count since it's not expanded in the cspec.
	 */
	private CSpec findCSpec(BundleDescription bundleDescription) {
		String id = bundleDescription.getSymbolicName();
		org.osgi.framework.Version v = bundleDescription.getVersion();
		Integer major = Integer.valueOf(v.getMajor());
		Integer minor = Integer.valueOf(v.getMinor());
		Integer micro = Integer.valueOf(v.getMicro());
		for (Map.Entry<IVersionedId, CSpec> entry : cspecs.entrySet()) {
			IVersionedId vn = entry.getKey();
			if (!id.equals(vn.getId()))
				continue;
			Version vnv = vn.getVersion();
			if (vnv != null && vnv.getSegmentCount() > 3 && major.equals(vnv.getSegment(0)) && minor.equals(vnv.getSegment(1))
					&& micro.equals(vnv.getSegment(2)))
				return entry.getValue();
		}
		return null;
	}
}
