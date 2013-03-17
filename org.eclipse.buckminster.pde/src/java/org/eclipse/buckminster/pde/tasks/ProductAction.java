/*******************************************************************************
 * Copyright (c) 2006-2013, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 ******************************************************************************/
package org.eclipse.buckminster.pde.tasks;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.buckminster.core.TargetPlatform;
import org.eclipse.buckminster.core.actor.IActionContext;
import org.eclipse.buckminster.core.version.VersionHelper;
import org.eclipse.buckminster.pde.Messages;
import org.eclipse.buckminster.runtime.Buckminster;
import org.eclipse.buckminster.runtime.Logger;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.frameworkadmin.BundleInfo;
import org.eclipse.equinox.internal.p2.publisher.eclipse.IProductDescriptor;
import org.eclipse.equinox.internal.provisional.frameworkadmin.ConfigData;
import org.eclipse.equinox.p2.metadata.IInstallableUnit;
import org.eclipse.equinox.p2.metadata.ITouchpointData;
import org.eclipse.equinox.p2.metadata.ITouchpointInstruction;
import org.eclipse.equinox.p2.metadata.IVersionedId;
import org.eclipse.equinox.p2.metadata.MetadataFactory;
import org.eclipse.equinox.p2.metadata.MetadataFactory.InstallableUnitDescription;
import org.eclipse.equinox.p2.metadata.Version;
import org.eclipse.equinox.p2.metadata.VersionRange;
import org.eclipse.equinox.p2.publisher.AbstractAdvice;
import org.eclipse.equinox.p2.publisher.AbstractPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherAction;
import org.eclipse.equinox.p2.publisher.IPublisherAdvice;
import org.eclipse.equinox.p2.publisher.IPublisherInfo;
import org.eclipse.equinox.p2.publisher.IPublisherResult;
import org.eclipse.equinox.p2.publisher.PublisherInfo;
import org.eclipse.equinox.p2.publisher.PublisherResult;
import org.eclipse.equinox.p2.publisher.actions.IAdditionalInstallableUnitAdvice;
import org.eclipse.equinox.p2.publisher.actions.ITouchpointAdvice;
import org.eclipse.equinox.p2.publisher.eclipse.ConfigAdvice;
import org.eclipse.equinox.p2.publisher.eclipse.EquinoxLauncherCUAction;
import org.eclipse.equinox.p2.publisher.eclipse.IConfigAdvice;
import org.eclipse.equinox.p2.query.IQuery;
import org.eclipse.equinox.p2.query.IQueryable;
import org.eclipse.equinox.p2.query.QueryUtil;
import org.eclipse.osgi.service.environment.Constants;
import org.eclipse.osgi.util.NLS;
import org.eclipse.pde.internal.build.IPDEBuildConstants;

/**
 * Action that generates version adjusted products
 */
@SuppressWarnings("restriction")
public class ProductAction extends org.eclipse.equinox.p2.publisher.eclipse.ProductAction implements IPDEBuildConstants {

	/**
	 * Special advice that sets the osgi.instance.area.default in case it hasn't
	 * bee set by other means.
	 */
	static class InstanceAreaAdvice extends AbstractAdvice implements IConfigAdvice {
		private final String configSpec;
		private final String instanceArea;

		private static final BundleInfo[] noBundles = new BundleInfo[0];

		InstanceAreaAdvice(String configSpec, String instanceArea) {
			this.configSpec = configSpec;
			this.instanceArea = instanceArea;
		}

		@Override
		public BundleInfo[] getBundles() {
			return noBundles;
		}

		@Override
		public String getConfigSpec() {
			return configSpec;
		}

		@Override
		public Map<String, String> getProperties() {
			return Collections.singletonMap(PROP_OSGI_INSTANCE_AREA_DEFAULT, instanceArea);
		}
	}

	private static final String PROP_OSGI_INSTANCE_AREA_DEFAULT = "osgi.instance.area.default"; //$NON-NLS-1$

	public ProductAction(IActionContext actionContext, String src, IProductDescriptor productDesc, String flvor, File exeFeatureLocation) {
		super(src, new ProductVersionPatcher(productDesc, actionContext), flvor, exeFeatureLocation);
	}

	@Override
	public IStatus perform(IPublisherInfo publisherInfo, IPublisherResult results, IProgressMonitor monitor) {
		((ProductVersionPatcher) product).setQueryable(results);

		IPublisherResult innerResult = new PublisherResult();
		PublisherInfo innerInfo = new PublisherInfo();
		String[] configs = publisherInfo.getConfigurations();
		if (executablesFeatureLocation == null) {
			// We can only create one single configuration and that's the one of
			// the running platform
			//
			Logger log = Buckminster.getLogger();
			String availableConfig = Platform.getWS() + '.' + Platform.getOS() + '.' + Platform.getOSArch();
			boolean warningsPrinted = false;
			boolean availConfigFound = false;
			for (String config : configs) {
				if (config.equals(availableConfig)) {
					availConfigFound = true;
					continue;
				}
				log.warning(NLS.bind(Messages.Missing_exe_launcher_for_config_0, config));
				warningsPrinted = true;
			}
			if (warningsPrinted)
				log.warning(Messages.Suggest_install_launchers_feature);

			if (!availConfigFound)
				return Status.OK_STATUS;

			innerInfo.setConfigurations(new String[] { availableConfig });
			if (Platform.inDevelopmentMode()) {
				String ilProp = System.getProperty("eclipse.host.location"); //$NON-NLS-1$
				if (ilProp == null) {
					log.warning(Messages.Please_use_selfhost_vmargs);
				} else
					source = ilProp;
			} else
				source = TargetPlatform.getPlatformInstallLocation().getAbsolutePath();
		} else
			innerInfo.setConfigurations(configs);

		innerInfo.setArtifactOptions(publisherInfo.getArtifactOptions());
		innerInfo.setArtifactRepository(publisherInfo.getArtifactRepository());
		innerInfo.setMetadataRepository(publisherInfo.getMetadataRepository());
		innerInfo.setContextArtifactRepository(publisherInfo.getContextArtifactRepository());
		innerInfo.setContextMetadataRepository(innerInfo.getContextMetadataRepository());

		// The inner result must see the launchers since the
		// EquinoxLauncherCUAction created by the
		// ApplicationLauncherAction must find them in order to generate the
		// correct CU's
		//
		IQueryable<IInstallableUnit> bundleScope = getTransitiveBundleScope(results);
		ConfigData dfltStartInfos = new ConfigData(null, null, null, null);
		nextIU: for (IInstallableUnit iu : results.getIUs(null, IPublisherResult.ROOT)) {
			String symbolicId = iu.getId();
			if (symbolicId.startsWith(EquinoxLauncherCUAction.ORG_ECLIPSE_EQUINOX_LAUNCHER)) {
				innerResult.addIU(iu, IPublisherResult.ROOT);
				continue;
			}

			if (product.useFeatures() && symbolicId.endsWith(".feature.group")) { //$NON-NLS-1$
				// Strip '.feature.group'
				String legacyId = symbolicId.substring(0, symbolicId.length() - 14);
				for (IVersionedId feature : product.getFeatures())
					if (legacyId.equals(feature.getId())) {
						innerResult.addIU(iu, IPublisherResult.ROOT);
						continue nextIU;
					}
			}

			for (BundleInfo configBi : product.getBundleInfos()) {
				if (!symbolicId.equals(configBi.getSymbolicName()))
					continue;

				Version configVer = null;
				if (configBi.getVersion() != null) {
					configVer = Version.parseVersion(configBi.getVersion());
					if (configVer.equals(Version.emptyVersion))
						configVer = null;
				}
				if (configVer != null && !configVer.equals(iu.getVersion()))
					continue;

				innerResult.addIU(iu, IPublisherResult.ROOT);
				continue nextIU;
			}

			for (BundleInfo bi : getDefaultStartInfo()) {
				if (!symbolicId.equals(bi.getSymbolicName()))
					continue;

				// Verify that this bundle is in the transitive scope
				// of the product. We don't want to add it if it's not
				// present
				if (bundleScope.query(QueryUtil.createIUQuery(iu), monitor).isEmpty())
					continue;

				innerResult.addIU(iu, IPublisherResult.ROOT);
				dfltStartInfos.addBundle(bi);
				continue nextIU;
			}
		}

		int dfltSICount = dfltStartInfos.getBundles().length;
		for (String configSpec : innerInfo.getConfigurations()) {
			if (dfltSICount > 0)
				innerInfo.addAdvice(new ConfigAdvice(dfltStartInfos, configSpec));

			String os = AbstractPublisherAction.parseConfigSpec(configSpec)[1];
			if (Constants.OS_MACOSX.equals(os)) {
				innerInfo.addAdvice(new InstanceAreaAdvice(configSpec, "@user.home/Documents/workspace")); //$NON-NLS-1$
			} else {
				innerInfo.addAdvice(new InstanceAreaAdvice(configSpec, "@user.home/workspace")); //$NON-NLS-1$				
			}
		}

		IStatus status = super.perform(innerInfo, innerResult, monitor);
		if (status.getSeverity() != IStatus.ERROR)
			results.merge(innerResult, IPublisherResult.MERGE_MATCHING);
		return status;
	}

	@Override
	protected IPublisherAction[] createActions(IPublisherResult results) {
		IPublisherAction[] actions = super.createActions(results);
		if (hasOtherInstanceAreaAdvice()) {
			List<IPublisherAdvice> adviceList = ((PublisherInfo) info).getAdvice();
			int idx = adviceList.size();
			while (--idx >= 0) {
				IPublisherAdvice advice = adviceList.get(idx);
				if (advice instanceof InstanceAreaAdvice)
					adviceList.remove(idx);
			}
		}

		if (getExecutablesLocation() == null) {
			IPublisherAction[] newActions = new IPublisherAction[actions.length + 1];
			System.arraycopy(actions, 0, newActions, 1, actions.length);
			actions = newActions;
			actions[0] = createApplicationExecutableAction(info.getConfigurations());
		}
		return actions;
	}

	@Override
	protected IPublisherAction createApplicationExecutableAction(String[] configSpecs) {
		return new ApplicationLauncherAction(id, version, flavor, executableName, getExecutablesLocation(), configSpecs);
	}

	protected boolean hasOtherInstanceAreaAdvice() {
		List<IPublisherAdvice> adviceList = ((PublisherInfo) info).getAdvice();

		int idx = adviceList.size();
		while (--idx >= 0) {
			IPublisherAdvice advice = adviceList.get(idx);
			if (advice instanceof IConfigAdvice && !(advice instanceof InstanceAreaAdvice)) {
				IConfigAdvice pfAdvice = (IConfigAdvice) advice;
				if (pfAdvice.getProperties().containsKey(PROP_OSGI_INSTANCE_AREA_DEFAULT))
					return true;
			}
			if (advice instanceof ITouchpointAdvice) {
				ITouchpointData tpData = MetadataFactory.createTouchpointData(Collections.<String, Object> emptyMap());
				ITouchpointAdvice tpAdvice = (ITouchpointAdvice) advice;
				tpData = tpAdvice.getTouchpointData(tpData);
				Object configure = tpData.getInstruction("configure"); //$NON-NLS-1$
				if (configure instanceof ITouchpointInstruction) {
					String configString = ((ITouchpointInstruction) configure).getBody();
					if (configString.startsWith("setProgramProperty") && configString.contains(PROP_OSGI_INSTANCE_AREA_DEFAULT)) //$NON-NLS-1$
						return true;
				}
			}
			if (advice instanceof IAdditionalInstallableUnitAdvice) {
				IAdditionalInstallableUnitAdvice iuAdvice = (IAdditionalInstallableUnitAdvice) advice;
				InstallableUnitDescription[] descs = iuAdvice.getAdditionalInstallableUnitDescriptions(null);
				if (descs != null) {
					for (InstallableUnitDescription desc : descs) {
						for (ITouchpointData tpData : desc.getTouchpointData()) {
							Object configure = tpData.getInstruction("configure"); //$NON-NLS-1$
							if (configure instanceof ITouchpointInstruction) {
								String configString = ((ITouchpointInstruction) configure).getBody();
								if (configString.startsWith("setProgramProperty") && configString.contains(PROP_OSGI_INSTANCE_AREA_DEFAULT)) //$NON-NLS-1$
									return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	IQueryable<IInstallableUnit> getTransitiveBundleScope(IPublisherResult results) {
		IProgressMonitor monitor = new NullProgressMonitor();
		List<IInstallableUnit> roots = new ArrayList<IInstallableUnit>();
		if (!product.useFeatures()) {
			for (IVersionedId bundle : product.getBundles(false)) {
				VersionRange vr = VersionHelper.unqualifiedRange(bundle.getVersion());
				IQuery<IInstallableUnit> iuQuery = QueryUtil.createIUQuery(bundle.getId(), vr);
				Iterator<IInstallableUnit> itor = results.query(iuQuery, monitor).iterator();
				while (itor.hasNext())
					roots.add(itor.next());
			}
		} else {
			for (IVersionedId feature : product.getFeatures()) {
				VersionRange vr = VersionHelper.unqualifiedRange(feature.getVersion());
				IQuery<IInstallableUnit> iuQuery = QueryUtil.createIUQuery(feature.getId() + ".feature.group", vr); //$NON-NLS-1$
				Iterator<IInstallableUnit> itor = results.query(iuQuery, monitor).iterator();
				while (itor.hasNext())
					roots.add(itor.next());
			}
		}

		IQuery<IInstallableUnit> traversal = QueryUtil.createQuery( //
				"$0.traverse(set(), _, { rqCache, parent | " + // //$NON-NLS-1$
						"parent.requirements.unique(rqCache)" + // //$NON-NLS-1$
						".collect(rc | everything.select(iu | iu ~= rc)).flatten()})", roots); //$NON-NLS-1$
		return results.query(traversal, monitor);
	}

	private BundleInfo[] getDefaultStartInfo() {
		BundleInfo[] defaults = new BundleInfo[6];
		defaults[0] = new BundleInfo(BUNDLE_SIMPLE_CONFIGURATOR, null, null, 1, true);
		defaults[1] = new BundleInfo(BUNDLE_EQUINOX_COMMON, null, null, 2, true);
		defaults[2] = new BundleInfo(BUNDLE_OSGI, null, null, -1, true);
		defaults[3] = new BundleInfo(BUNDLE_UPDATE_CONFIGURATOR, null, null, 4, true);
		defaults[4] = new BundleInfo(BUNDLE_CORE_RUNTIME, null, null, 4, true);
		defaults[5] = new BundleInfo(BUNDLE_DS, null, null, 2, true);
		return defaults;
	}
}
